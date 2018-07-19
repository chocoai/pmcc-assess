package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineDataInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.dto.output.report.SurveyCorrelationCardVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.event.project.ProjectInfoEvent;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.examine.*;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
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
import com.copower.pmcc.erp.constant.ApplicationConstant;
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
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private SurveyExamineItemService surveyExamineItemService;


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
     * 初始化该调查表下的所有任务
     *
     * @param surveyExamineTaskDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void initExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        //清除原有数据
        surveyExamineTaskService.deleteTaskByPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());

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
     * 保存调查信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveExamineDataInfo(String formData) throws BusinessException {
        if (StringUtils.isBlank(formData))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        List<KeyValueDto> keyValueDtoList = JSON.parseArray(formData, KeyValueDto.class);
        if (CollectionUtils.isNotEmpty(keyValueDtoList)) {
            for (KeyValueDto keyValueDto : keyValueDtoList) {
                switch (keyValueDto.getKey()) {
                    case AssessExamineTaskConstant.FC_RESIDENCE_BLOCK_BASE:
                        ExamineBlock examineBlock = JSON.parseObject(keyValueDto.getValue(), ExamineBlock.class);
                        examineBlockService.saveBlock(examineBlock);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_ESTATE_BASE:
                        ExamineEstate examineEstate = JSON.parseObject(keyValueDto.getValue(), ExamineEstate.class);
                        examineEstateService.saveEstate(examineEstate);
                        //更新附件
                        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ExamineEstate.class), examineEstate.getId());
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
                        //更新附件
                        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ExamineHouse.class), examineHouse.getId());
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_HOUSE_TRADING:
                        ExamineHouseTrading examineHouseTrading = JSON.parseObject(keyValueDto.getValue(), ExamineHouseTrading.class);
                        examineHouseTradingService.saveHouseTrading(examineHouseTrading);
                        break;
                }
            }
        }
    }

    /**
     * 提交调查信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitExamineDataInfo(String formData, Integer planDetailsId) throws BusinessException {
        //1.保存信息
        //2.先检查是否需要发流程 如果不需要则更新任务状态 如果需要发流程则发起流程，写写入主表，更新任务状态为运行中，
        //3.检查是否所有任务都已提交完成 如果都已完成则更新planDetails的状态 并确认是否走下一个阶段任务
        saveExamineDataInfo(formData);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        //取到该事项所配置的流程
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.isBlank(projectPhase.getBoxName())) {//不走流程
            updateExamineTaskStatus(planDetailsId, commonService.thisUserAccount(), ProjectStatusEnum.FINISH);
            if (isAllTaskFinish(planDetailsId)) {
                projectPlanDetails.setStatus(ProjectStatusEnum.FINISH.getKey());
                projectPlanDetailsService.updateProjectPlanDetails(projectPlanDetails);
                if (projectPlanDetailsService.isAllPlanDetailsFinish(projectPlanDetails.getPlanId())) {
                    projectTaskAllService.startTaskAllApproval(projectPlanDetails.getPlanId());
                }
            }
        } else {//提交流程
            //先保存流程主表
            SurveyExamineItem surveyExamineItem=new SurveyExamineItem();
            surveyExamineItem.setProjectId(projectPlanDetails.getProjectId());
            surveyExamineItem.setPlanDetailsId(projectPlanDetails.getId());
            surveyExamineItem.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
            surveyExamineItem.setCreator(commonService.thisUserAccount());
            surveyExamineItemService.save(surveyExamineItem);

            ProcessUserDto processUserDto = null;
            //发起相应的流程
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
            String folio = String.format("%s->%s", projectPhase.getProjectPhaseName(), declareRecord.getName());
            Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectPhase.getBoxName());
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setStartUser(commonService.thisUserAccount());
            processInfo.setProjectId(projectPlanDetails.getProjectId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(SurveyExamineItem.class));
            processInfo.setTableId(surveyExamineItem.getId());
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setWorkStage(projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId()).getWorkStageName());
            processInfo.setProcessEventExecutorName(ProjectInfoEvent.class.getSimpleName());
            processInfo.setWorkStageId(projectPlanDetails.getProjectWorkStageId());
            processInfo.setAppKey(applicationConstant.getAppKey());

            try {
                processUserDto = processControllerComponent.processStart(processInfo, commonService.thisUserAccount(), false);
            } catch (BpmException e) {
                logger.info("提交调查信息,发起流程异常",e);
                throw new BusinessException(e.getMessage());
            }
            surveyExamineItem.setStatus(ProcessStatusEnum.RUN.getValue());
            surveyExamineItem.setProcessInsId(processUserDto.getProcessInsId());
            surveyExamineItemService.save(surveyExamineItem);
        }
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
