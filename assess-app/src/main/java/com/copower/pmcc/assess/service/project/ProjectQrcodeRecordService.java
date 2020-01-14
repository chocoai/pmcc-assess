package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectQrcodeRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectQrcodeRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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
     * @return
     * @throws Exception
     */
    public SysAttachmentDto toolBaseOrCode(ProjectTakeNumber projectTakeNumber) throws Exception {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectTakeNumber.getProjectId());
        ProjectDocumentDto projectDocumentDto = new ProjectDocumentDto();
        projectDocumentDto.setProjectName(projectInfo.getProjectName());
        adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId());
        projectDocumentDto.setCompanyName(adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId()).getOrganizationName());
        projectDocumentDto.setCustomer(projectTakeNumber.getRealEstateAppraiser());
        projectDocumentDto.setDocumentNumber(projectTakeNumber.getNumberValue());
        projectDocumentDto.setAppKey(applicationConstant.getAppKey());

        projectDocumentDto.setProjectId(projectTakeNumber.getProjectId());

        //二维码跳转错误,现在没有解决
        projectDocumentDto.setErpProjectId(projectTakeNumber.getProjectId());


        projectDocumentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class));
        projectDocumentDto.setFieldsName(String.join("_", ProjectTakeNumber.class.getSimpleName(), "BaseOrCode"));
        projectDocumentDto.setTableId(projectTakeNumber.getId());
        projectDocumentDto.setReportDate(DateUtils.formatDate(projectTakeNumber.getReportIssuanceDate(), DateUtils.DATE_CHINESE_PATTERN));
        projectDocumentDto.setReportMember(publicService.getUserNameByAccount(projectTakeNumber.getRealEstateAppraiser()));
        projectDocumentDto.setCreator(projectTakeNumber.getRealEstateAppraiser());
        ProjectDocumentDto document = erpRpcToolsService.saveProjectDocument(projectDocumentDto);
        if (document != null){
            BeanUtils.copyProperties(document,projectDocumentDto);
        }
        String qrCode = "";
        if (StringUtils.isNotBlank(projectDocumentDto.getQrcode())) {
            qrCode = projectDocumentDto.getQrcode();
        }else {
            StringBuilder stringBuilder = new StringBuilder(8);
            stringBuilder.append("").append("文号:").append(projectTakeNumber.getNumberValue()).append(";").append(StringUtils.repeat("\n\r\t", 1));
            qrCode = stringBuilder.toString();
        }
        String folder = System.getProperty("java.io.tmpdir");
        String imagePath = String.join("", folder, File.separator, UUID.randomUUID().toString(), ".", "jpg");
        FileUtils.base64ToImage(qrCode, imagePath);
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(projectDocumentDto.getTableId());
        sysAttachmentDto.setTableName(projectDocumentDto.getTableName());
        sysAttachmentDto.setFieldsName(projectDocumentDto.getFieldsName());
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto) ;
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
}
