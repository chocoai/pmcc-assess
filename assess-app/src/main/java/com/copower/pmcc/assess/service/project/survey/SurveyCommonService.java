package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineDataInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.dto.output.report.SurveyCorrelationCardVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.examine.*;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyCommonService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private ExamineBlockService examineBlockService;
    @Autowired
    private ExamineEstateLandStateService examineEstateLandStateService;
    @Autowired
    private ExamineEstateService examineEstateService;
    @Autowired
    private ExamineHouseService examineHouseService;
    @Autowired
    private ExamineHouseTradingService examineHouseTradingService;
    @Autowired
    private ExamineUnitService examineUnitService;



    /**
     * 获取所属权证信息
     *
     * @param correlationCard
     * @param projectPlanDetails
     * @param declareRecords
     * @return
     */
    public List<SurveyCorrelationCardVo> getSurveyCorrelationCardVos(String correlationCard, ProjectPlanDetails projectPlanDetails, List<DeclareRecord> declareRecords) {
//        if(StringUtils.isBlank(correlationCard)) return null;
        List<SurveyCorrelationCardVo> surveyCorrelationCardVos = Lists.newArrayList();
        List<Integer> correlationCardIds = Lists.newArrayList();
        if (StringUtils.isNotBlank(correlationCard)) {
            List<String> list = FormatUtils.transformString2List(correlationCard);
            correlationCardIds = FormatUtils.ListStringToListInteger(list);
        }
        if (CollectionUtils.isNotEmpty(declareRecords)) {
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
     *
     * @param tableName
     * @param tableId
     * @param surveyLocaltion
     */
    public void downLoadLocationImage(String tableName, Integer tableId, String surveyLocaltion) {
        String localDir = baseAttachmentService.createTempBasePath(commonService.thisUserAccount());
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
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableId(tableId);
        baseAttachment.setTableName(tableName);
        baseAttachment.setFieldsName("survey_localtion");
        baseAttachment.setFtpFileName(ftpFileName);
        baseAttachment.setFileExtension("jpg");
        baseAttachment.setFilePath(ftpDirName);
        baseAttachment.setFileName("定位图.jpg");
        baseAttachment.setFileSize(FileUtils.getSize(new File(localDir + File.separator + imageName).length()));
        baseAttachment.setCreater(processControllerComponent.getThisUser());
        //baseAttachment.setModifier(processControllerComponent.getThisUser());
        baseAttachmentService.addAttachment(baseAttachment);
    }

    /**
     * 保存动态表单数据
     *
     * @param formId
     * @param formData
     * @param tableName
     * @param tableId
     * @return
     * @throws BusinessException
     */
    public Integer saveDynamicForm(Integer formId, String formData, String tableName, Integer tableId) throws BusinessException {
        if (formId == null) return 0;
        FormConfigureDetailDto configureDetailDto = new FormConfigureDetailDto();
        configureDetailDto.setFormData(formData);
        configureDetailDto.setFormModuleId(formId);
        configureDetailDto.setTableId(tableId);
        configureDetailDto.setTableName(tableName);
        return formConfigureService.saveSimpleData(configureDetailDto);
    }

    /**
     * 获取房产所有调查表单
     *
     * @return
     */
    public List<KeyValueDto> getExamineFormTypeList() {
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        KeyValueDto keyValueDto = new KeyValueDto();
        keyValueDto.setKey(AssessExamineTaskConstant.FC_RESIDENCE);
        keyValueDto.setValue(dataExamineTaskService.getCacheDataExamineTaskByFieldName(AssessExamineTaskConstant.FC_RESIDENCE).getName());
        keyValueDtoList.add(keyValueDto);

        keyValueDto = new KeyValueDto();
        keyValueDto.setKey(AssessExamineTaskConstant.FC_INDUSTRY);
        keyValueDto.setValue(dataExamineTaskService.getCacheDataExamineTaskByFieldName(AssessExamineTaskConstant.FC_INDUSTRY).getName());
        keyValueDtoList.add(keyValueDto);
        return keyValueDtoList;
    }

    /**
     * 获取需处理的任务集合
     *
     * @param planDetailsId
     * @param userAccount
     * @return
     */
    public Map<String, List<SurveyExamineTaskVo>> getExamineTaskByUserAccount(Integer planDetailsId, String userAccount) {
        Map<String, List<SurveyExamineTaskVo>> map = Maps.newHashMap();
        List<SurveyExamineTaskVo> blockTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> estateTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> buildingTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> unitTaskList = Lists.newArrayList();
        List<SurveyExamineTaskVo> houseTaskList = Lists.newArrayList();
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        surveyExamineTask.setUserAccount(userAccount);
        List<SurveyExamineTask> examineTaskList = surveyExamineTaskService.getSurveyExamineTaskList(surveyExamineTask);
        List<SurveyExamineTaskVo> examineTaskVos = surveyExamineTaskService.getSurveyExamineTaskVos(examineTaskList, true);
        if (CollectionUtils.isNotEmpty(examineTaskVos)) {
            for (SurveyExamineTaskVo examineTaskVo : examineTaskVos) {
                if (StringUtils.isNotBlank(examineTaskVo.getApplyUrl())) {
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.BLOCK))
                        blockTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.ESTATE))
                        estateTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.BUILDING))
                        buildingTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.UNIT))
                        unitTaskList.add(examineTaskVo);
                    if (examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.HOUSE))
                        houseTaskList.add(examineTaskVo);
                }
            }
        }
        map.put(AssessExamineTaskConstant.BLOCK, blockTaskList);
        map.put(AssessExamineTaskConstant.ESTATE, estateTaskList);
        map.put(AssessExamineTaskConstant.BUILDING, buildingTaskList);
        map.put(AssessExamineTaskConstant.UNIT, unitTaskList);
        map.put(AssessExamineTaskConstant.HOUSE, houseTaskList);
        return map;
    }





    /**
     * 更新任务状态
     *
     * @param planDetailsId
     * @param userAccount
     * @param projectStatusEnum
     */
    public void updateExamineTaskStatus(Integer planDetailsId, String userAccount, ProjectStatusEnum projectStatusEnum) {
        //查询条件
        SurveyExamineTask where = new SurveyExamineTask();
        where.setPlanDetailsId(planDetailsId);
        where.setUserAccount(userAccount);
        //更新内容
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setTaskStatus(projectStatusEnum.getKey());

        surveyExamineTaskService.updateSurveyExamineTask(surveyExamineTask, where);
    }

    /**
     * @param planDetailsId
     * @return
     */
    public boolean isAllTaskFinish(Integer planDetailsId) {
        SurveyExamineTask where = new SurveyExamineTask();
        where.setPlanDetailsId(planDetailsId);
        int allCount = surveyExamineTaskService.getSurveyExamineTaskCount(where);

        where.setTaskStatus(ProjectStatusEnum.FINISH.getKey());
        int finishCount = surveyExamineTaskService.getSurveyExamineTaskCount(where);
        return allCount == finishCount;
    }

    /**
     * 获取调查数据信息
     *
     * @param declareId
     * @return
     */
    public SurveyExamineDataInfoVo getExamineDataInfoVo(Integer declareId) {
        SurveyExamineDataInfoVo surveyExamineDataInfoVo = new SurveyExamineDataInfoVo();
        surveyExamineDataInfoVo.setExamineBlockVo(examineBlockService.getExamineBlockVo(examineBlockService.getBlockByDeclareId(declareId)));
        surveyExamineDataInfoVo.setExamineEstateLandStateVo(examineEstateLandStateService.getExamineEstateLandStateVo(examineEstateLandStateService.getEstateLandStateByDeclareId(declareId)));
        surveyExamineDataInfoVo.setExamineEstateVo(examineEstateService.getExamineEstateVo(examineEstateService.getEstateByDeclareId(declareId)));
        surveyExamineDataInfoVo.setExamineUnitVo(examineUnitService.getExamineUnitVo(examineUnitService.getUnitByDeclareId(declareId)));
        surveyExamineDataInfoVo.setExamineHouseVo(examineHouseService.getExamineHouseVo(examineHouseService.getHouseByDeclareId(declareId)));
        surveyExamineDataInfoVo.setExamineHouseTradingVo(examineHouseTradingService.getExamineHouseTradingVo(examineHouseTradingService.getHouseTradingByDeclareId(declareId)));
        return surveyExamineDataInfoVo;
    }
}
