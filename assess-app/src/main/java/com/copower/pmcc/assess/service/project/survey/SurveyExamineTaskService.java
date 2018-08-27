package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExamineTaskDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.examine.*;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineTaskService {
    @Autowired
    private SurveyExamineTaskDao surveyExamineTaskDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
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
     * 获取调查任务
     *
     * @param id
     * @return
     */
    public SurveyExamineTask getSurveyExamineTask(Integer id) {
        return surveyExamineTaskDao.getSurveyExamineTaskById(id);
    }

    /**
     * 获取调查任务
     *
     * @param surveyExamineTask
     * @return
     */
    public List<SurveyExamineTask> getSurveyExamineTaskList(SurveyExamineTask surveyExamineTask) {
        return surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
    }

    /**
     * 获取调查任务
     *
     * @param planDetailsId
     * @return
     */
    public int getRuningTaskCount(Integer planDetailsId) {
        return surveyExamineTaskDao.getTaskCountByStatus(planDetailsId,ProjectStatusEnum.WAIT.getKey());
    }

    public List<CustomSurveyExamineTask> getCustomeExamineTaskList(Integer planDetailsId, String userAccount) {
        return surveyExamineTaskDao.getCustomExamineTaskList(planDetailsId, userAccount);
    }

    /**
     * 获取调查任务
     *
     * @param planDetailsId
     * @return
     */
    public List<SurveyExamineTask> getTaskListByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        return surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
    }

    /**
     * 获取vos
     *
     * @param taskList
     * @return
     */
    public List<SurveyExamineTaskVo> getSurveyExamineTaskVos(List<SurveyExamineTask> taskList, boolean isFull) {
        if (CollectionUtils.isEmpty(taskList)) return null;
        return LangUtils.transform(taskList, p -> {
            SurveyExamineTaskVo surveyExamineTaskVo = new SurveyExamineTaskVo();
            BeanUtils.copyProperties(p, surveyExamineTaskVo);
            if (p.getPid() != null && p.getPid() > 0)
                surveyExamineTaskVo.set_parentId(p.getPid());
            if (StringUtils.isNotBlank(p.getUserAccount())) {
                surveyExamineTaskVo.setUserName(publicService.getUserNameByAccount(p.getUserAccount()));
            }
            if (StringUtils.isNotBlank(p.getTaskStatus())) {
                surveyExamineTaskVo.setTaskStatusName(ProjectStatusEnum.getNameByKey(p.getTaskStatus()));
            }
            if (Boolean.TRUE == isFull) {
                DataExamineTask dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskById(p.getDataTaskId());
                if (dataExamineTask != null) {
                    surveyExamineTaskVo.setFieldName(dataExamineTask.getFieldName());
                    surveyExamineTaskVo.setApplyUrl(dataExamineTask.getApplyUrl());
                    surveyExamineTaskVo.setDetailUrl(dataExamineTask.getDetailUrl());
                }
            }
            return surveyExamineTaskVo;
        });
    }

    public List<SurveyExamineTaskVo> getSurveyExamineTaskVos(List<CustomSurveyExamineTask> taskList) {
        if (CollectionUtils.isEmpty(taskList)) return null;
        return LangUtils.transform(taskList, p -> {
            SurveyExamineTaskVo surveyExamineTaskVo = new SurveyExamineTaskVo();
            BeanUtils.copyProperties(p, surveyExamineTaskVo);
            if (p.getPid() != null && p.getPid() > 0)
                surveyExamineTaskVo.set_parentId(p.getPid());
            if (StringUtils.isNotBlank(p.getUserAccount())) {
                surveyExamineTaskVo.setUserName(publicService.getUserNameByAccount(p.getUserAccount()));
            }
            if (StringUtils.isNotBlank(p.getTaskStatus())) {
                surveyExamineTaskVo.setTaskStatusName(ProjectStatusEnum.getNameByKey(p.getTaskStatus()));
            }
            return surveyExamineTaskVo;
        });
    }


    /**
     * 删除任务
     *
     * @param planDetailsId
     */
    public void deleteTaskByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        surveyExamineTaskDao.deleteSurveyExamineTask(surveyExamineTask);
    }

    /**
     * 保存选择的调查任务
     *
     * @param surveyExamineTaskDto
     * @throws BusinessException
     */
    @Transactional
    public void saveSelectExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(surveyExamineTaskDto.getDataTaskIds()));
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer id : list) {
                DataExamineTask dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskById(id);
                if (dataExamineTask != null) {
                    SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
                    surveyExamineTask.setPid(surveyExamineTaskDto.getPid());
                    surveyExamineTask.setUserAccount(surveyExamineTaskDto.getUserAccount());
                    surveyExamineTask.setBisMust(dataExamineTask.getBisMust());
                    surveyExamineTask.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
                    surveyExamineTask.setName(dataExamineTask.getName());
                    surveyExamineTask.setSorting(dataExamineTask.getSorting());
                    surveyExamineTask.setExamineType(surveyExamineTaskDto.getExamineType());
                    surveyExamineTask.setDeclareId(surveyExamineTaskDto.getDeclareRecordId());
                    surveyExamineTask.setDataTaskId(id);
                    surveyExamineTask.setTaskStatus(ProjectStatusEnum.WAIT.getKey());
                    surveyExamineTask.setCreator(commonService.thisUserAccount());
                    surveyExamineTaskDao.addSurveyExamineTask(surveyExamineTask);
                }
            }
        }
    }

    /**
     * 保存调查任务
     *
     * @param surveyExamineTask
     * @return
     */
    public void saveSurveyExamineTask(SurveyExamineTask surveyExamineTask) throws BusinessException {
        if (surveyExamineTask == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyExamineTask.getId() != null && surveyExamineTask.getId() > 0) {
            surveyExamineTaskDao.updateSurveyExamineTask(surveyExamineTask);
        } else {
            surveyExamineTask.setCreator(commonService.thisUserAccount());
            surveyExamineTaskDao.addSurveyExamineTask(surveyExamineTask);
        }
    }

    /**
     * 更新调查任务
     *
     * @param surveyExamineTask
     * @param where
     */
    public void updateSurveyExamineTask(SurveyExamineTask surveyExamineTask, SurveyExamineTask where) {
        surveyExamineTaskDao.updateSurveyExamineTask(surveyExamineTask, where);
    }

    /**
     * 删除调查任务
     *
     * @param id
     */
    public void deleteSurveyExamineTask(Integer id) {
        surveyExamineTaskDao.deleteSurveyExamineTask(id);
    }

    /**
     * 获取数据总条数
     *
     * @param surveyExamineTask
     * @return
     */
    public int getSurveyExamineTaskCount(SurveyExamineTask surveyExamineTask) {
        return surveyExamineTaskDao.getSurveyExamineTaskCount(surveyExamineTask);
    }

    /**
     * 保存批量设置
     *
     * @param ids
     * @param userAccount
     */
    public void saveFastSet(String ids, String userAccount) throws BusinessException {
        if (StringUtils.isBlank(ids))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (StringUtils.isBlank(userAccount))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer id : list) {
                SurveyExamineTask surveyExamineTask = surveyExamineTaskDao.getSurveyExamineTaskById(id);
                if (surveyExamineTask != null) {
                    surveyExamineTask.setUserAccount(userAccount);
                    surveyExamineTaskDao.updateSurveyExamineTask(surveyExamineTask);
                }
            }
        }

    }


    /**
     * 获取可添加的任务
     *
     * @param dataTaskId
     * @param planDetailsId
     * @return
     */
    public List<DataExamineTask> getCanAppendTaskList(Integer dataTaskId, Integer pid, Integer planDetailsId) {
        List<DataExamineTask> dataExamineTasks = dataExamineTaskService.getCacheDataExamineTaskListByPid(dataTaskId);//获取所有任务
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        surveyExamineTask.setPid(pid);
        List<SurveyExamineTask> examineTaskList = surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
        if (CollectionUtils.isNotEmpty(examineTaskList)) {
            dataExamineTasks.removeIf(p -> {
                for (SurveyExamineTask examineTask : examineTaskList) {
                    if (examineTask.getDataTaskId().equals(p.getId()))
                        return true;
                }
                return false;
            });
        }

        return dataExamineTasks;
    }

    /**
     * 初始化该调查表下的所有任务
     *
     * @param surveyExamineTaskDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void initExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        //清除原有数据
        deleteTaskByPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());

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
                saveSurveyExamineTask(surveyExamineTask);
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
                        saveSurveyExamineTask(surveyExamineTask);
                    }
                }
            }
        }
    }

    /**
     * 确认分派
     *
     * @param surveyExamineTaskDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void confirmAssignment(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(surveyExamineTaskDto.getPlanDetailsId());
        if (surveyExamineTaskDto.getExamineInfoId() == null) {
            SurveyExamineInfo surveyExamineInfo = new SurveyExamineInfo();
            surveyExamineInfo.setExamineType(surveyExamineTaskDto.getExamineType());
            surveyExamineInfo.setId(surveyExamineTaskDto.getExamineInfoId());
            surveyExamineInfo.setProjectId(surveyExamineTaskDto.getProjectId());
            surveyExamineInfo.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
            surveyExamineInfo.setExamineFormType(surveyExamineTaskDto.getExamineFormType());
            surveyExamineInfo.setDeclareRecordId(surveyExamineTaskDto.getDeclareRecordId());
            surveyExamineInfo.setBisAssignment(true);
            surveyExamineInfo.setCreator(commonService.thisUserAccount());
            surveyExamineInfoService.save(surveyExamineInfo);
        }

        //清除还为运行的计划任务,同时查询待提交任务，一同清除
        //根据现有任务分派情况，确定子计划任务及待提交任务
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setPid(planDetails.getId());
        where.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        List<ProjectPlanDetails> projectDetails = projectPlanDetailsService.getProjectDetails(where);
        if (CollectionUtils.isNotEmpty(projectDetails)) {
            deletePlanDetailsAndTask(projectDetails);
        }

        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
        surveyExamineTask.setTaskStatus(ProjectStatusEnum.WAIT.getKey());
        List<SurveyExamineTask> examineTaskList = surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
        if (CollectionUtils.isNotEmpty(examineTaskList)) {
            HashSet<String> hashSet = Sets.newHashSet();
            for (SurveyExamineTask examineTask : examineTaskList) {
                if (StringUtils.isNotBlank(examineTask.getUserAccount()))
                    hashSet.add(examineTask.getUserAccount());
            }
            if (!hashSet.isEmpty()) {
                ProjectWorkStage workStage = projectWorkStageService.cacheProjectWorkStage(planDetails.getProjectWorkStageId());
                ProjectInfo projectInfo = projectInfoService.getProjectInfoById(planDetails.getProjectId());
                String phaseKey = AssessPhaseKeyConstant.SCENE_EXPLORE_EXAMINE;
                if (ExamineTypeEnum.CASE.getId().equals(surveyExamineTaskDto.getExamineType())) {
                    phaseKey = AssessPhaseKeyConstant.CASE_STUDY_EXAMINE;
                }
                ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(phaseKey);
                for (String userAccount : hashSet) {
                    //添加计划任务子项及待提交任务
                    ProjectPlanDetails taskPlanDetails = new ProjectPlanDetails();
                    BeanUtils.copyProperties(planDetails, taskPlanDetails);
                    taskPlanDetails.setId(0);
                    taskPlanDetails.setPid(planDetails.getId());
                    SysUserDto sysUser = erpRpcUserService.getSysUser(userAccount);
                    taskPlanDetails.setProjectPhaseId(projectPhase.getId());
                    taskPlanDetails.setExecuteUserAccount(userAccount);
                    taskPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
                    taskPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                    taskPlanDetails.setCreator(commonService.thisUserAccount());
                    taskPlanDetails.setProjectPhaseName(String.format("%s-%s", planDetails.getProjectPhaseName(), publicService.getUserNameByAccount(userAccount)));
                    projectPlanDetailsService.saveProjectPlanDetails(taskPlanDetails);
                    //添加任务
                    ProjectResponsibilityDto projectTask = new ProjectResponsibilityDto();
                    projectTask.setProjectId(planDetails.getProjectId());
                    projectTask.setProjectName(projectInfo.getProjectName());
                    projectTask.setPlanEndTime(planDetails.getPlanEndDate());
                    projectTask.setPlanId(planDetails.getPlanId());
                    projectTask.setPlanDetailsId(taskPlanDetails.getId());
                    projectTask.setModel(ResponsibileModelEnum.TASK.getId());
                    projectTask.setConclusion(ResponsibileModelEnum.TASK.getName());
                    projectTask.setUserAccount(userAccount);
                    projectTask.setBisEnable(true);
                    projectTask.setAppKey(applicationConstant.getAppKey());
                    projectTask.setPlanDetailsName(String.format("%s[%s]", workStage.getWorkStageName(), taskPlanDetails.getProjectPhaseName()));
                    projectPlanService.updateProjectTaskUrl(ResponsibileModelEnum.TASK, projectTask);
                    projectTask.setCreator(commonService.thisUserAccount());
                    bpmRpcProjectTaskService.saveProjectTask(projectTask);
                }
            }
        }
    }

    /**
     * 删除计划及待提交任务
     *
     * @param list
     */
    public void deletePlanDetailsAndTask(List<ProjectPlanDetails> list) {
        if (CollectionUtils.isEmpty(list)) return;
        for (ProjectPlanDetails projectDetail : list) {
            //删除
            projectPlanDetailsService.deleteProjectPlanDetails(projectDetail.getId());
            //找出待提交任务-删除
            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setProjectId(projectDetail.getProjectId());
            projectResponsibilityDto.setPlanDetailsId(projectDetail.getId());
            List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);
            if (CollectionUtils.isNotEmpty(projectTaskList)) {
                for (ProjectResponsibilityDto responsibilityDto : projectTaskList) {
                    //删除此任务
                    bpmRpcProjectTaskService.deleteProjectTask(responsibilityDto.getId());
                }
            }
        }
    }

    /**
     * 获取调查信息by申报id
     *
     * @param declareId
     * @return
     */
    public List<ProjectPlanDetails> getPlanDetailsByDeclareId(Integer declareId) {
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setDeclareRecordId(declareId);
        projectPlanDetails.setBisLastLayer(true);
        return projectPlanDetailsService.getProjectDetails(projectPlanDetails);
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
                    case AssessExamineTaskConstant.FC_INDUSTRY_BLOCK_BASE:
                    case AssessExamineTaskConstant.FC_RESIDENCE_BLOCK_BASE:
                        ExamineBlock examineBlock = JSON.parseObject(keyValueDto.getValue(), ExamineBlock.class);
                        examineBlockService.saveBlock(examineBlock);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_ESTATE_BASE:
                    case AssessExamineTaskConstant.FC_INDUSTRY_ESTATE_BASE:
                        ExamineEstate examineEstate = JSON.parseObject(keyValueDto.getValue(), ExamineEstate.class);
                        examineEstateService.saveEstate(examineEstate);
                        //更新附件
                        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ExamineEstate.class), examineEstate.getId());
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_ESTATE_LAND_STATE:
                    case AssessExamineTaskConstant.FC_INDUSTRY_ESTATE_LAND_STATE:
                        ExamineEstateLandState examineEstateLandState = JSON.parseObject(keyValueDto.getValue(), ExamineEstateLandState.class);
                        examineEstateLandStateService.saveEstateLandState(examineEstateLandState);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_UNIT_BASE:
                    case AssessExamineTaskConstant.FC_INDUSTRY_UNIT_BASE:
                        ExamineUnit examineUnit = JSON.parseObject(keyValueDto.getValue(), ExamineUnit.class);
                        examineUnitService.saveUnit(examineUnit);
                        break;
                    case AssessExamineTaskConstant.FC_RESIDENCE_HOUSE_BASE:
                    case AssessExamineTaskConstant.FC_INDUSTRY_HOUSE_BASE:
                        ExamineHouse examineHouse = JSON.parseObject(keyValueDto.getValue(), ExamineHouse.class);
                        examineHouseService.saveHouse(examineHouse);
                        //更新附件
                        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ExamineHouse.class), examineHouse.getId());
                        break;
                    case AssessExamineTaskConstant.FC_INDUSTRY_HOUSE_TRADING:
                    case AssessExamineTaskConstant.FC_RESIDENCE_HOUSE_TRADING:
                        ExamineHouseTrading examineHouseTrading = JSON.parseObject(keyValueDto.getValue(), ExamineHouseTrading.class);
                        examineHouseTradingService.saveHouseTrading(examineHouseTrading);
                        break;
                }
            }
        }
    }
}
