package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.dto.output.report.SurveyCorrelationCardVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.examine.*;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
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
import org.springframework.transaction.annotation.Transactional;

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
    private SurveyLocaleExploreService surveyLocaleExploreService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
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
        keyValueDto.setValue("住宅商业办公");
        keyValueDtoList.add(keyValueDto);

        keyValueDto = new KeyValueDto();
        keyValueDto.setKey(AssessExamineTaskConstant.FC_INDUSTRY);
        keyValueDto.setValue("工业仓储");
        keyValueDtoList.add(keyValueDto);
        return keyValueDtoList;
    }

    /**
     * 初始化该调查表下的所有任务
     *
     * @param surveyExamineTaskDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void initExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        //清除原有数据
        surveyExamineTaskService.deleteTaskByPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
        //保存主表信息
        if (surveyExamineTaskDto.getExamineType().equals(ExamineTypeEnum.EXPLORE.getId())) {//查勘
            SurveyLocaleExplore surveyLocaleExplore = new SurveyLocaleExplore();
            surveyLocaleExplore.setId(surveyExamineTaskDto.getTableId());
            surveyLocaleExplore.setProjectId(surveyExamineTaskDto.getProjectId());
            surveyLocaleExplore.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
            surveyLocaleExplore.setExamineFormType(surveyExamineTaskDto.getExamineFormType());
            surveyLocaleExplore.setDeclareRecordId(surveyExamineTaskDto.getDeclareRecordId());
            surveyLocaleExplore.setCreator(commonService.thisUserAccount());
            surveyLocaleExploreService.save(surveyLocaleExplore);
        } else {//案例
            SurveyCaseStudy surveyCaseStudy = new SurveyCaseStudy();
            surveyCaseStudy.setId(surveyExamineTaskDto.getTableId());
            surveyCaseStudy.setProjectId(surveyExamineTaskDto.getProjectId());
            surveyCaseStudy.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
            surveyCaseStudy.setExamineFormType(surveyExamineTaskDto.getExamineFormType());
            surveyCaseStudy.setDeclareRecordId(surveyExamineTaskDto.getDeclareRecordId());
            surveyCaseStudy.setCreator(commonService.thisUserAccount());
            surveyCaseStudyService.save(surveyCaseStudy);
        }


        List<DataExamineTask> examineTaskList = dataExamineTaskService.getCacheDataExamineTaskListByKey(surveyExamineTaskDto.getExamineFormType());

        if (CollectionUtils.isNotEmpty(examineTaskList)) {
            SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
            surveyExamineTask.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
            surveyExamineTask.setDeclareId(surveyExamineTaskDto.getDeclareRecordId());
            surveyExamineTask.setExamineType(surveyExamineTaskDto.getExamineType());
            surveyExamineTask.setTaskStatus(ProjectStatusEnum.WAIT.getKey());
            surveyExamineTask.setCreator(commonService.thisUserAccount());
            //第一层级
            for (DataExamineTask dataExamineTask : examineTaskList) {
                surveyExamineTask.setId(0);
                surveyExamineTask.setPid(0);
                surveyExamineTask.setName(dataExamineTask.getName());
                surveyExamineTask.setDataTaskId(dataExamineTask.getId());
                surveyExamineTask.setSorting(dataExamineTask.getSorting());
                surveyExamineTask.setBisMust(dataExamineTask.getBisMust());
                surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
                Integer pid = surveyExamineTask.getId();
                List<DataExamineTask> taskList = dataExamineTaskService.getCacheDataExamineTaskListByPid(dataExamineTask.getId());
                //第二层级
                if (CollectionUtils.isNotEmpty(taskList)) {
                    for (DataExamineTask examineTask : taskList) {
                        surveyExamineTask.setId(0);
                        surveyExamineTask.setPid(pid);
                        surveyExamineTask.setName(examineTask.getName());
                        surveyExamineTask.setUserAccount(surveyExamineTaskDto.getUserAccount());
                        surveyExamineTask.setDataTaskId(examineTask.getId());
                        surveyExamineTask.setSorting(examineTask.getSorting());
                        surveyExamineTask.setBisMust(examineTask.getBisMust());
                        surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
                    }
                }
            }
        }
    }

    /**
     * 获取需处理的任务集合
     * @param planDetailsId
     * @param userAccount
     * @return
     */
    public Map<String, List<SurveyExamineTaskVo>> getExamineTaskByUserAccount(Integer planDetailsId, String userAccount) {
        Map<String, List<SurveyExamineTaskVo>> map = Maps.newHashMap();
        List<SurveyExamineTaskVo> blockTaskList=Lists.newArrayList();
        List<SurveyExamineTaskVo> estateTaskList=Lists.newArrayList();
        List<SurveyExamineTaskVo> buildingTaskList=Lists.newArrayList();
        List<SurveyExamineTaskVo> unitTaskList=Lists.newArrayList();
        List<SurveyExamineTaskVo> houseTaskList=Lists.newArrayList();
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        surveyExamineTask.setUserAccount(userAccount);
        List<SurveyExamineTask> examineTaskList = surveyExamineTaskService.getSurveyExamineTaskList(surveyExamineTask);
        List<SurveyExamineTaskVo> examineTaskVos = surveyExamineTaskService.getSurveyExamineTaskVos(examineTaskList,true);
        if(CollectionUtils.isNotEmpty(examineTaskVos)){
            for (SurveyExamineTaskVo examineTaskVo : examineTaskVos) {
                if(StringUtils.isNotBlank(examineTaskVo.getApplyUrl())){
                    if(examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.BLOCK))
                        blockTaskList.add(examineTaskVo);
                    if(examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.ESTATE))
                        estateTaskList.add(examineTaskVo);
                    if(examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.BUILDING))
                        buildingTaskList.add(examineTaskVo);
                    if(examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.UNIT))
                        unitTaskList.add(examineTaskVo);
                    if(examineTaskVo.getFieldName().contains(AssessExamineTaskConstant.HOUSE))
                        houseTaskList.add(examineTaskVo);
                }
            }
        }
        map.put(AssessExamineTaskConstant.BLOCK,blockTaskList);
        map.put(AssessExamineTaskConstant.ESTATE,estateTaskList);
        map.put(AssessExamineTaskConstant.BUILDING,buildingTaskList);
        map.put(AssessExamineTaskConstant.UNIT,unitTaskList);
        map.put(AssessExamineTaskConstant.HOUSE,houseTaskList);
        return map;
    }

    /**
     * 保存调查信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveExamineDataInfo(String formData) throws BusinessException {
        if(StringUtils.isBlank(formData))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        List<KeyValueDto> keyValueDtoList = JSON.parseArray(formData, KeyValueDto.class);
        if(CollectionUtils.isNotEmpty(keyValueDtoList)){
            for (KeyValueDto keyValueDto : keyValueDtoList) {
                switch (keyValueDto.getKey()){
                    case AssessExamineTaskConstant.FC_RESIDENCE_BLOCK_BASE:
                        ExamineBlock examineBlock = JSON.parseObject(keyValueDto.getValue(), ExamineBlock.class);
                        examineBlockService.saveBlock(examineBlock);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_ESTATE_BASE:
                        ExamineEstate examineEstate = JSON.parseObject(keyValueDto.getValue(), ExamineEstate.class);
                        examineEstateService.saveEstate(examineEstate);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_ESTATE_LAND_STATE:
                        ExamineEstateLandState examineEstateLandState = JSON.parseObject(keyValueDto.getValue(), ExamineEstateLandState.class);
                        examineEstateLandStateService.saveEstateLandState(examineEstateLandState);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_UNIT_BASE:
                        ExamineUnit examineUnit = JSON.parseObject(keyValueDto.getValue(), ExamineUnit.class);
                        examineUnitService.saveUnit(examineUnit);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_HOUSE_BASE:
                        ExamineHouse examineHouse = JSON.parseObject(keyValueDto.getValue(), ExamineHouse.class);
                        examineHouseService.saveHouse(examineHouse);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_HOUSE_TRADING:
                        ExamineHouseTrading examineHouseTrading = JSON.parseObject(keyValueDto.getValue(), ExamineHouseTrading.class);
                        examineHouseTradingService.saveHouseTrading(examineHouseTrading);
                        break;
                }
            }
        }
    }
}
