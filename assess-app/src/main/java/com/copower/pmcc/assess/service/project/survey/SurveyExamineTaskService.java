package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomSurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExamineTaskDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
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

import java.util.ArrayList;
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
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private SurveySceneExploreService surveySceneExploreService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ResidueRatioService residueRatioService;

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
        return surveyExamineTaskDao.getTaskCountByStatus(planDetailsId, ProjectStatusEnum.WAIT.getKey());
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
    @Transactional(rollbackFor = {Exception.class})
    public void saveSelectExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(surveyExamineTaskDto.getDataTaskIds()));
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer id : list) {
                DataExamineTask dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskById(id);
                if (dataExamineTask != null) {
                    SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
                    surveyExamineTask.setPid(surveyExamineTaskDto.getPlanDetailsId());
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
        //获取所有任务
        List<DataExamineTask> dataExamineTasks = dataExamineTaskService.getCacheDataExamineTaskListByPid(dataTaskId);
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
     * 任务分派
     *
     * @param planDetailsId
     * @param examineFormType
     * @param examineTypeEnum
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void examineTaskAssignment(Integer planDetailsId, String examineFormType, ExamineTypeEnum examineTypeEnum, Integer transactionType) throws BusinessException {
        if (this.checkAssignmentTask(planDetailsId)) {
            throw new BusinessException("请不要重复添加");
        }
        ;
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        ProjectWorkStage workStage = projectWorkStageService.cacheProjectWorkStage(planDetails.getProjectWorkStageId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(planDetails.getProjectId());
        String phaseKey = null;
        if (examineTypeEnum.getId().equals(ExamineTypeEnum.EXPLORE.getId()))
            phaseKey = AssessPhaseKeyConstant.COMMON_SCENE_EXPLORE_EXAMINE;
        if (examineTypeEnum.getId().equals(ExamineTypeEnum.CASE.getId()))
            phaseKey = AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(phaseKey);
        //添加计划任务子项及待提交任务

        ProjectPlanDetails taskPlanDetails = new ProjectPlanDetails();
        BeanUtils.copyProperties(planDetails, taskPlanDetails);
        taskPlanDetails.setId(null);
        taskPlanDetails.setPid(planDetails.getId());
        SysUserDto sysUser = erpRpcUserService.getSysUser(commonService.thisUserAccount());
        taskPlanDetails.setProjectPhaseId(projectPhase.getId());
        taskPlanDetails.setExecuteUserAccount(commonService.thisUserAccount());
        taskPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
        taskPlanDetails.setBisLastLayer(true);
        taskPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        taskPlanDetails.setCreator(commonService.thisUserAccount());
        taskPlanDetails.setProjectPhaseName(String.format("%s-%s", planDetails.getProjectPhaseName(), publicService.getUserNameByAccount(commonService.thisUserAccount())));
        projectPlanDetailsService.saveProjectPlanDetails(taskPlanDetails);

        SurveyExamineInfo surveyExamineInfo = new SurveyExamineInfo();
        surveyExamineInfo.setExamineType(examineTypeEnum.getId());
        surveyExamineInfo.setProjectId(planDetails.getProjectId());
        surveyExamineInfo.setPlanDetailsId(planDetails.getId());
        surveyExamineInfo.setExamineFormType(examineFormType);
        surveyExamineInfo.setTransactionType(transactionType);
        surveyExamineInfo.setDeclareRecordId(planDetails.getDeclareRecordId());
        surveyExamineInfo.setBisAssignment(true);
        surveyExamineInfo.setCreator(commonService.thisUserAccount());
        surveyExamineInfoService.save(surveyExamineInfo);
        //save 保存查勘内容(工业与非工业)
        this.saveSurveyExamineTask(planDetails, examineFormType, examineTypeEnum);
        //添加任务
        ProjectResponsibilityDto projectTask = new ProjectResponsibilityDto();
        projectTask.setProjectId(planDetails.getProjectId());
        projectTask.setProjectName(projectInfo.getProjectName());
        projectTask.setPlanEndTime(planDetails.getPlanEndDate());
        projectTask.setPlanId(planDetails.getPlanId());
        projectTask.setPlanDetailsId(taskPlanDetails.getId());
        projectTask.setModel(ResponsibileModelEnum.TASK.getId());
        projectTask.setConclusion(ResponsibileModelEnum.TASK.getName());
        projectTask.setUserAccount(commonService.thisUserAccount());
        projectTask.setBisEnable(true);
        projectTask.setAppKey(applicationConstant.getAppKey());
        projectTask.setPlanDetailsName(String.format("%s[%s]", workStage.getWorkStageName(), taskPlanDetails.getProjectPhaseName()));
        projectPlanService.updateProjectTaskUrl(ResponsibileModelEnum.TASK, projectTask);
        projectTask.setCreator(commonService.thisUserAccount());
        bpmRpcProjectTaskService.saveProjectTask(projectTask);
    }

    private void saveSurveyExamineTask(ProjectPlanDetails planDetails, String examineFormType, ExamineTypeEnum examineTypeEnum) throws BusinessException {
        if (StringUtils.isNotBlank(examineFormType)) {
            List<DataExamineTask> dataExamineTaskList = dataExamineTaskService.getCacheDataExamineTaskListByKey(examineFormType);
            List<DataExamineTask> examineTaskAllList = new ArrayList<DataExamineTask>(10);
            if (CollectionUtils.isNotEmpty(dataExamineTaskList)) {
                for (DataExamineTask examineTask : dataExamineTaskList) {
                    List<DataExamineTask> dataExamineTasks = dataExamineTaskService.getCacheDataExamineTaskListByPid(examineTask.getId());
                    if (CollectionUtils.isNotEmpty(dataExamineTasks)) {
                        examineTaskAllList.addAll(dataExamineTasks);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(examineTaskAllList)) {
                for (DataExamineTask examineTask : examineTaskAllList) {
                    SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
                    surveyExamineTask.setPid(planDetails.getPid());
                    surveyExamineTask.setUserAccount(planDetails.getExecuteUserAccount());
                    surveyExamineTask.setBisMust(examineTask.getBisMust());
                    surveyExamineTask.setPlanDetailsId(planDetails.getId());
                    surveyExamineTask.setName(examineTask.getName());
                    surveyExamineTask.setSorting(examineTask.getSorting());
                    surveyExamineTask.setExamineType(examineTypeEnum.getId());
                    surveyExamineTask.setDeclareId(planDetails.getDeclareRecordId());
                    surveyExamineTask.setPlanDetailsId(planDetails.getId());
                    surveyExamineTask.setDataTaskId(examineTask.getId());
                    surveyExamineTask.setTaskStatus(ProjectStatusEnum.WAIT.getKey());
                    surveyExamineTask.setCreator(commonService.thisUserAccount());
                    surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
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
    public void confirmAssignment(SurveyExamineTaskDto surveyExamineTaskDto) throws Exception {
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
                String phaseKey = AssessPhaseKeyConstant.COMMON_SCENE_EXPLORE_EXAMINE;
                if (ExamineTypeEnum.CASE.getId().equals(surveyExamineTaskDto.getExamineType())) {
                    phaseKey = AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE;
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
    @Transactional(rollbackFor = Exception.class)
    public void deletePlanDetailsAndTask(List<ProjectPlanDetails> list) throws Exception {
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
            //删除查勘过程数据
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(projectDetail.getId());
            if (basicApply != null) {
                basicEstateService.clearInvalidData(basicApply.getId());
                basicBuildingService.clearInvalidData(basicApply.getId());
                basicUnitService.clearInvalidData(basicApply.getId());
                basicHouseService.clearInvalidData(basicApply.getId());
                basicApplyService.deleteBasicApply(basicApply.getId());
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
    public void saveExamineDataInfo(String formData, ProjectPlanDetails projectPlanDetails) throws BusinessException, Exception {
        if (StringUtils.isBlank(formData)) {
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        }
        ExamineTypeEnum examineTypeEnum = ExamineTypeEnum.EXPLORE;
        SurveyCaseStudy surveyCaseStudy = null;
        SurveySceneExplore surveySceneExplore = null;
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE)) {
            examineTypeEnum = ExamineTypeEnum.CASE;
        }
        JSONObject jsonObject = JSONObject.parseObject(formData);
        BasicEstate basicEstate = null;
        BasicEstateLandState basicEstateLandState = null;
        BasicBuilding basicBuilding = null;
        BasicUnit basicUnit = null;
        BasicHouse basicHouse = null;
        BasicHouseTrading basicTrading = null;
        if (StringUtils.isNotEmpty(jsonObject.getString("basicEstate"))) {
            basicEstate = JSONObject.parseObject(jsonObject.getString("basicEstate"), BasicEstate.class);
            if (basicEstate != null) {
                basicEstateService.saveAndUpdateBasicEstate(basicEstate);
            }
        }
        if (StringUtils.isNotEmpty(jsonObject.getString("basicEstateLandState"))) {
            basicEstateLandState = JSONObject.parseObject(jsonObject.getString("basicEstateLandState"), BasicEstateLandState.class);
            if (basicEstateLandState != null) {
                basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState);
            }
        }
        if (StringUtils.isNotEmpty(jsonObject.getString("basicBuilding"))) {
            basicBuilding = JSONObject.parseObject(jsonObject.getString("basicBuilding"), BasicBuilding.class);
            if (basicBuilding != null) {
                basicBuildingService.update(basicBuilding);
            }
        }
        try {
            if (StringUtils.isNotEmpty(jsonObject.getString("basicUnit"))) {
                basicUnit = JSONObject.parseObject(jsonObject.getString("basicUnit"), BasicUnit.class);
                if (basicUnit != null) {
                    basicUnitService.saveAndUpdateBasicUnit(basicUnit);
                }
            }
        } catch (Exception e) {
            //这里是因为basicUnit json 串压根没有 但是还是进入了程序
        }
        if (StringUtils.isNotEmpty(jsonObject.getString("basicHouse"))) {
            basicHouse = JSONObject.parseObject(jsonObject.getString("basicHouse"), BasicHouse.class);
            if (basicHouse != null) {
                try {
                    basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                } catch (Exception e1) {
                    //这里异常原因是  当类型为土地的时候,这个时候basicHouse中只有一个属性有数据 然后发生了sql异常  暂时未处理它
                    String s = e1.getMessage();
                }
            }
        }
        if (StringUtils.isNotBlank(jsonObject.getString("basicTrading"))) {
            basicTrading = JSONObject.parseObject(jsonObject.getString("basicTrading"), BasicHouseTrading.class);
            if (basicTrading != null) {
                basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading);
            }
        }
        if (StringUtils.isNotBlank(jsonObject.getString("basicDamagedDegree"))) {
            List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonObject.getString("basicDamagedDegree"), BasicHouseDamagedDegree.class);
            if (!org.springframework.util.CollectionUtils.isEmpty(damagedDegreeList)) {
                for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                    basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(degree);
                }
            }
            //写入成新率
            if (basicHouse != null) {
                String newDegree = residueRatioService.getObservationalRatio(basicHouse.getId());
                basicHouse.setNewDegree(newDegree);
                basicHouseService.saveAndUpdateBasicHouse(basicHouse);
            }
        }
        String survey = jsonObject.getString("survey");
        if (StringUtils.isNotBlank(survey)) {
            //案例
            if (examineTypeEnum.getId().equals(ExamineTypeEnum.CASE.getId())) {
                surveyCaseStudy = JSONObject.parseObject(survey, SurveyCaseStudy.class);
                surveyCaseStudy.setProcessInsId(projectPlanDetails.getProcessInsId());
                surveyCaseStudyService.updateSurveyCaseStudy(surveyCaseStudy);
            }
            //查勘
            if (examineTypeEnum.getId().equals(ExamineTypeEnum.EXPLORE.getId())) {
                surveySceneExplore = JSONObject.parseObject(survey, SurveySceneExplore.class);
                surveySceneExplore.setProcessInsId(projectPlanDetails.getProcessInsId());
                surveySceneExploreService.updateSurveySceneExplore(surveySceneExplore);
            }
        }
        //需将土地实际用途、房屋实际用途、楼层、房号反写到申报记录表中
        if (projectPlanDetails.getDeclareRecordId() != null) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
            if (declareRecord != null) {
                if (basicEstateLandState != null) {
                    declareRecord.setLandPracticalUse(baseDataDicService.getNameById(basicEstateLandState.getLandUseType()));
                }
                if (basicHouse != null) {
                    declareRecord.setPracticalUse(baseDataDicService.getNameById(basicHouse.getPracticalUse()));
                }
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            }
        }
    }


    //检查是否添加任务
    public boolean checkAssignmentTask(Integer planDetailsId) {
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setPid(planDetailsId);
        List<ProjectPlanDetailsVo> list = projectPlanDetailsService.getProjectDetailsTask(projectPlanDetails);
        if (CollectionUtils.isNotEmpty(list)) {
            return true;
        }
        return false;
    }
}
