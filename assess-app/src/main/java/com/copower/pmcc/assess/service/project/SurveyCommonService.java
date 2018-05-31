package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.output.report.SurveyCorrelationCardVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyCommonService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private FormConfigureService formConfigureService;


    /**
     * 获取所属权证信息
     * @param correlationCard
     * @param projectPlanDetails
     * @param declareRecords
     * @return
     */
    public List<SurveyCorrelationCardVo> getSurveyCorrelationCardVos(String correlationCard, ProjectPlanDetails projectPlanDetails, List<DeclareRecord> declareRecords) {
        if(StringUtils.isBlank(correlationCard)) return null;
        List<SurveyCorrelationCardVo> surveyCorrelationCardVos = Lists.newArrayList();
        List<Integer> correlationCardIds = Lists.newArrayList();
        if (StringUtils.isNotBlank(correlationCard)) {
            List<String> list = FormatUtils.transformString2List(correlationCard);
            correlationCardIds = FormatUtils.ListStringToListInteger(list);
        }
        if(CollectionUtils.isNotEmpty(declareRecords)){
            for (DeclareRecord declareRecord : declareRecords) {
                if (declareRecord.getId().equals(projectPlanDetails.getDeclareRecordId())) continue;
                SurveyCorrelationCardVo surveyCorrelationCardVo = new SurveyCorrelationCardVo();
                surveyCorrelationCardVo.setId(declareRecord.getId());
                surveyCorrelationCardVo.setName(declareRecord.getName());
                surveyCorrelationCardVo.setBisChecked(correlationCardIds.contains(declareRecord.getId()));
                surveyCorrelationCardVos.add(surveyCorrelationCardVo);
            }
        }
        return surveyCorrelationCardVos;
    }

    /**
     * 下载定位图片
     * @param tableName
     * @param tableId
     * @param surveyLocaltion
     */
    public void downLoadLocationImage(String tableName,Integer tableId,String surveyLocaltion){
        String localDir = baseAttachmentService.createBasePath(baseAttachmentService.getTempUploadPath(), DateUtils.formatNowToYMD(), DateUtils.formatNowToYMDHMS());
        String imageName = baseAttachmentService.createNoRepeatFileName("jpg");
        String url = String.format("%s?location=%s&zoom=17&size=900*600&markers=mid,,A:%s&key=%s",
                BaseConstant.MPA_API_URL, surveyLocaltion, surveyLocaltion, BaseConstant.MAP_WEB_SERVICE_KEY);
        try {
            NetDownloadUtils.download(url, imageName, localDir);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //再将图片上传到FTP
        String ftpFileName = baseAttachmentService.createNoRepeatFileName("jpg");
        String ftpDirName = baseAttachmentService.createFTPBasePath(SurveyLocaleExploreDetail.class.getSimpleName(),
                DateUtils.formatNowToYMD(), "surveyLocaltion");
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpDirName, new FileInputStream(localDir + File.separator + imageName), ftpFileName);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //数据库添加定位图片记录
        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setTableId(tableId);
        baseAttachment.setTableName(tableName);
        baseAttachment.setFieldsName("survey_localtion");
        baseAttachment.setFtpFilesName(ftpFileName);
        baseAttachment.setFileExtension("jpg");
        baseAttachment.setFilePath(ftpDirName);
        baseAttachment.setFileName("定位图.jpg");
        baseAttachment.setFileSize(FileUtils.getSize(new File(localDir + File.separator + imageName).length()));
        baseAttachment.setCreater(processControllerComponent.getThisUser());
        baseAttachment.setModifier(processControllerComponent.getThisUser());
        baseAttachmentDao.addAttachment(baseAttachment);
    }

    /**
     * 保存动态表单数据
     * @param formId
     * @param formData
     * @param tableName
     * @param tableId
     * @return
     * @throws BusinessException
     */
    public Integer saveDynamicForm(Integer formId,String formData,String tableName,Integer tableId ) throws BusinessException {
        if (formId == null) return 0;
        FormConfigureDetailDto configureDetailDto = new FormConfigureDetailDto();
        configureDetailDto.setFormData(formData);
        configureDetailDto.setFormModuleId(formId);
        configureDetailDto.setTableId(tableId);
        configureDetailDto.setTableName(tableName);
        return formConfigureService.saveSimpleData(configureDetailDto);
    }


}
