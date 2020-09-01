package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectQrcodeRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.erp.api.dto.ProjectDocumentDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * Created by kings on 2019-6-5.
 */
@Service
public class ProjectQrcodeRecordService {
    @Autowired
    private ProjectQrcodeRecordDao projectQrcodeRecordDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private AdRpcQualificationsService adRpcQualificationsService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private GenerateReportInfoService generateReportInfoService;

    /**
     * 获取数据
     *
     * @param projectId
     * @param areaId
     * @param reportType
     * @return
     */
    public ProjectQrcodeRecord getProjectQrcodeRecode(Integer projectId, Integer areaId, Integer reportType) {
        return projectQrcodeRecordDao.getProjectQrcodeRecord(projectId, areaId, reportType);
    }


    /**
     * 形成二维码附件图片
     *
     * @param projectTakeNumber
     * @param projectTakeNumberDetail
     * @return
     * @throws Exception
     */
    public SysAttachmentDto toolBaseOrCode(ProjectTakeNumber projectTakeNumber, ProjectTakeNumberDetail projectTakeNumberDetail) throws Exception {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectTakeNumber.getProjectId());
        ProjectDocumentDto projectDocumentDto = new ProjectDocumentDto();
        projectDocumentDto.setProjectName(projectInfo.getProjectName());
        projectDocumentDto.setCompanyName(adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId()).getOrganizationName());
        projectDocumentDto.setCustomer(projectTakeNumber.getRealEstateAppraiser());
        projectDocumentDto.setDocumentNumber(projectTakeNumberDetail.getNumberValue());
        projectDocumentDto.setAppKey(applicationConstant.getAppKey());
        projectDocumentDto.setProjectId(projectTakeNumber.getProjectId());
        projectDocumentDto.setFieldsName(String.join("", "ProjectTakeNumber_BaseOrCode", projectTakeNumberDetail.getId().toString()));
        projectDocumentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumberDetail.class));
        projectDocumentDto.setTableId(projectTakeNumberDetail.getId());
        projectDocumentDto.setReportDate(DateUtils.formatDate(projectTakeNumber.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN));
        projectDocumentDto.setReportMember(publicService.getUserNameByAccount(projectTakeNumber.getRealEstateAppraiser()));
        projectDocumentDto.setCreator(projectTakeNumber.getRealEstateAppraiser());
        ProjectDocumentDto document = erpRpcToolsService.saveProjectDocument(projectDocumentDto);
        if (document != null) {
            BeanUtils.copyProperties(document, projectDocumentDto);
        }
        String qrCode = projectDocumentDto.getQrcode();
        String folder = System.getProperty("java.io.tmpdir");
        String imagePath = String.join("", folder, File.separator, UUID.randomUUID().toString(), ".", "jpg");
        FileUtils.base64ToImage(qrCode, imagePath);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(projectDocumentDto.getTableId());
        sysAttachmentDto.setTableName(projectDocumentDto.getTableName());
        sysAttachmentDto.setFieldsName(projectDocumentDto.getFieldsName());
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        //上传形成附件
        return baseAttachmentService.importAjaxFileHandle(imagePath, sysAttachmentDto);
    }

    /**
     * 保存数据
     *
     * @param projectQrcodeRecord
     */
    public void saveProjectQrcodeRecode(ProjectQrcodeRecord projectQrcodeRecord) {
        if (projectQrcodeRecord.getId() == null || projectQrcodeRecord.getId() <= 0) {
            if (StringUtils.isBlank(projectQrcodeRecord.getCreator())) {
                projectQrcodeRecord.setCreator(commonService.thisUserAccount());
            }
            projectQrcodeRecordDao.addProjectQrcodeRecord(projectQrcodeRecord);
        } else {
            projectQrcodeRecordDao.editProjectQrcodeRecord(projectQrcodeRecord);
        }
    }

    /**
     * 获取报告二维码
     *
     * @return
     */
    public String getReportQrcode(GenerateReportGroup reportGroup, String reportType, String documentNumber, String client) throws Exception {
        //1.先从本地查看是否已生成过二维码
        //2.如果已生成直接返回已生成的二维码
        if (reportGroup == null || StringUtils.isBlank(reportType)) return null;
        GenerateReportInfo generateReportInfo = generateReportInfoService.getGenerateReportInfoByAreaId(reportGroup.getAreaGroupId());
        Integer reportTypeId = baseDataDicService.getCacheDataDicByFieldName(reportType).getId();
        Integer projectId = generateReportInfo.getProjectId();
        Integer areaId = generateReportInfo.getAreaGroupId();
        ProjectQrcodeRecord qrcodeRecode = getProjectQrcodeRecode(projectId, areaId, reportTypeId);
        String qrCode = null;
        if (qrcodeRecode != null) {
            qrCode = qrcodeRecode.getQrcode();//更新部分信息
            ProjectDocumentDto projectDocumentDto = erpRpcToolsService.getProjectDocumentById(qrcodeRecode.getProjectDocumentId());
            if (projectDocumentDto != null) {
                projectDocumentDto.setReportDate(DateUtils.formatDate(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN));
                projectDocumentDto.setReportMember(publicService.getUserNameByAccount(generateReportInfo.getRealEstateAppraiser()));
                erpRpcToolsService.saveProjectDocument(projectDocumentDto);
            }
        } else {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(generateReportInfo.getProjectId());
            AdCompanyQualificationDto qualificationDto = adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId());
            ProjectDocumentDto projectDocumentDto = new ProjectDocumentDto();
            projectDocumentDto.setProjectName(projectInfo.getProjectName());
            projectDocumentDto.setCustomer(client);
            projectDocumentDto.setCompanyName(qualificationDto != null ? qualificationDto.getOrganizationName() : "");
            projectDocumentDto.setDocumentNumber(documentNumber);
            projectDocumentDto.setProjectId(projectInfo.getId());
            projectDocumentDto.setAppKey(applicationConstant.getAppKey());
            projectDocumentDto.setTableName(FormatUtils.entityNameConvertToTableName(GenerateReportInfo.class));
            projectDocumentDto.setTableId(generateReportInfo.getId());
            projectDocumentDto.setFieldsName(generateCommonMethod.getReportFieldsName(reportType, reportGroup));
            projectDocumentDto.setReportDate(DateUtils.formatDate(generateReportInfo.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN));
            projectDocumentDto.setReportMember(publicService.getUserNameByAccount(generateReportInfo.getRealEstateAppraiser()));
            projectDocumentDto = erpRpcToolsService.saveProjectDocument(projectDocumentDto);

            qrcodeRecode = new ProjectQrcodeRecord();
            qrcodeRecode.setProjectId(projectId);
            qrcodeRecode.setAreaId(areaId);
            qrcodeRecode.setReportType(reportTypeId);
            qrcodeRecode.setProjectDocumentId(projectDocumentDto.getId());
            qrcodeRecode.setQrcode(projectDocumentDto.getQrcode());
            saveProjectQrcodeRecode(qrcodeRecode);
            qrCode = projectDocumentDto.getQrcode();
            projectDocumentDto.setFieldsName(generateCommonMethod.getReportFieldsName(reportType, reportGroup));
            erpRpcToolsService.saveProjectDocument(projectDocumentDto);
        }
        return qrCode;
    }

}
