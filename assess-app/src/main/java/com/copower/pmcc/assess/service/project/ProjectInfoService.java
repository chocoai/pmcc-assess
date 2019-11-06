package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectInfoDto;
import com.copower.pmcc.assess.dto.input.project.initiate.InitiateProjectDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.ProjectAssignEvent;
import com.copower.pmcc.assess.service.event.project.ProjectInfoEvent;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * 描述:项目基础信息
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:05
 */
@Service
public class ProjectInfoService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Lazy
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private BaseDataDicService bidBaseDataDicService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private InitiateConsignorService consignorService;
    @Autowired
    private InitiateUnitInformationService unitInformationService;
    @Autowired
    private InitiatePossessorService possessorService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private BaseParameterService baseParameterServcie;
    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseParameterService baseParameterService;


    /**
     * 项目立项申请
     *
     * @param projectDto
     * @param init       是否立项
     */
    public boolean projectApply(InitiateProjectDto projectDto, boolean init, boolean mustUseBox) throws BusinessException {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUserAccountManager(projectDto.getProjectInfo().getUserAccountManager());
        projectMember.setUserAccountMember(projectDto.getProjectInfo().getUserAccountMember());
        projectMember.setBisEnable(true);
        return projectApplyChange(projectDto.getConsignor(), projectDto.getUnitinformation(), projectDto.getPossessor(), projectMember, projectDto.getProjectInfo(), init, mustUseBox);
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean projectApplyChange(InitiateConsignor consignor, InitiateUnitInformation unitInformation, InitiatePossessor possessor, ProjectMember projectMember,
                                      ProjectInfoDto projectInfoDto, boolean init, boolean mustUseBox) {
        boolean flag = true;
        try {
            ProjectInfo projectInfo = change(projectInfoDto);
            if (org.apache.commons.lang3.StringUtils.isEmpty(projectInfo.getCreator())) {
                projectInfo.setCreator(commonService.thisUserAccount());
            }
            if (!init) {
                projectInfo.setStatus(ProjectStatusEnum.DRAFT.getKey());
                projectInfo.setProjectStatus(ProjectStatusEnum.DRAFT.getKey());
            }
            int projectId = saveProjectInfo(projectInfo);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class), projectId);
            if (consignor != null) {
                consignor.setProjectId(projectId);
                consignorService.saveAndUpdateInitiateConsignor(consignor);
            }
            if (unitInformation != null) {
                unitInformation.setProjectId(projectId);
                unitInformationService.saveAndUpdate(unitInformation);
            }
            if (possessor != null) {
                possessor.setProjectId(projectId);
                possessorService.saveAndUpdate(possessor);
            }
            if (projectMember != null) {//保存项目成员
                projectMember.setProjectId(projectId);
                projectMember.setCreator(commonService.thisUserAccount());
                projectMemberService.saveProjectMemeber(projectMember);
            }

            //发起项目
            if (init) {
                //初始化项目信息
                initProjectInfo(projectInfo, mustUseBox);
                publicService.writeToErpProject(projectInfo);
                unitInformationService.roundWrite(unitInformation);
            }
        } catch (Exception e) {
            flag = false;
            logger.error("exception!" + e.getMessage());
        }
        return flag;
    }

    /**
     * 分派项目经理
     *
     * @param projectMember
     * @param projectInfo
     */
    private void allocateProjectManager(ProjectMember projectMember, ProjectInfo projectInfo) {
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectInfo.getProjectTypeId(), true);
        String boxName = baseParameterServcie.getParameterValues(BaseParameterEnum.PROJECT_APPLY_ASSIGN_PROCESS_KEY.getParameterKey());
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectInfo.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        //流程描述
        processInfo.setFolio(projectInfo.getProjectName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setStartUser(commonService.thisUserAccount());
        processInfo.setWorkStage(projectWorkStages.get(0).getWorkStageName());
        processInfo.setWorkStageId(projectWorkStages.get(0).getId());
        processInfo.setProcessEventExecutorName(ProjectAssignEvent.class.getSimpleName());
        processInfo.setTableId(projectInfo.getId());
        //取审批人
        if (StringUtils.isNotEmpty(projectMember.getUserAccountManager())) {
            processInfo.setNextUser(Lists.newArrayList(projectMember.getUserAccountManager()));
        } else {
            Integer departmentId = projectInfo.getDepartmentId();
            //取部门领导
            List<String> departmentCE = bpmRpcBoxRoleUserService.getDepartmentBmfzr(departmentId);
            processInfo.setNextUser(departmentCE);
        }
        try {
            String processInsId = processControllerComponent.processStartPending(processInfo);
            projectInfo.setAssignStatus(ProcessStatusEnum.RUN.getValue());
            projectInfo.setAssignProcessInsId(processInsId);
            projectInfoDao.updateProjectInfo(projectInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 项目立项返回修改
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void projectEditApproval(ApprovalModelDto approvalModelDto, String formData, Integer projectInfoId) throws Exception {
        //保存数据
        projectUpdate(format(formData), projectInfoId);
        //提交流程
        processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
    }

    /*项目立项修改*/
    @Transactional(rollbackFor = {Exception.class})
    public void projectUpdate(InitiateProjectDto initiateProjectDto, Integer projectInfoId) throws Exception {
        ProjectInfoDto projectInfo = initiateProjectDto.getProjectInfo();
        ProjectMemberVo projectMemberVo = projectMemberService.getProjectMember(projectInfoId);
        projectInfo.setId(projectInfoId);
        String userAccountManager = projectInfo.getUserAccountManager();
        String userAccountMember = projectInfo.getUserAccountMember();
        if (!org.springframework.util.StringUtils.isEmpty(userAccountManager)) {
            projectMemberVo.setUserAccountManager(userAccountManager);
        }
        if (!org.springframework.util.StringUtils.isEmpty(userAccountMember)) {
            projectMemberVo.setUserAccountMember(userAccountMember);
        }
        projectInfoDao.updateProjectInfo(change(projectInfo));
        consignorService.saveAndUpdateInitiateConsignor(initiateProjectDto.getConsignor());
        possessorService.saveAndUpdate(initiateProjectDto.getPossessor());
        unitInformationService.saveAndUpdate(initiateProjectDto.getUnitinformation());
        projectMemberService.updateProjectMember(projectMemberVo);
        ProjectPlan projectPlan = new ProjectPlan();
        projectPlan.setProjectId(projectInfoId);

        List<ProjectPlan> projectPlans = projectPlanService.getProjectPlanList(projectInfoId);
        if (CollectionUtils.isNotEmpty(projectPlans)) {
            unitInformationService.roundWrite(initiateProjectDto.getUnitinformation());
        }
    }


    /**
     * 初始化项目信息
     * @param projectInfo
     * @param mustUseBox 当为true的时候发起审批流程,当为false直接进入下一个阶段
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void initProjectInfo(ProjectInfo projectInfo, boolean mustUseBox) throws Exception {
        Integer projectTypeId = projectInfo.getProjectTypeId();//项目类别id
        Integer projectCategoryId = projectInfo.getProjectCategoryId();//项目范围id
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectTypeId, true);
        if (CollectionUtils.isEmpty(projectWorkStages))
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        int i = 1;
        for (ProjectWorkStage item : projectWorkStages) {
            ProjectPlan projectPlan = new ProjectPlan();
            projectPlan.setProjectId(projectInfo.getId());
            projectPlan.setProcessInsId("-1");
            projectPlan.setWorkStageId(item.getId());
            projectPlan.setCategoryId(projectCategoryId);
            projectPlan.setPlanName(item.getWorkStageName());
            projectPlan.setCreator(processControllerComponent.getThisUser());
            projectPlan.setCreated(new Date());
            projectPlan.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            projectPlan.setProjectStatus(ProjectStatusEnum.WAIT.getKey());
            projectPlan.setBisRestart(false);
            projectPlan.setStageSort(i);
            projectPlan.setSpecificGravity(item.getSpecificGravity());
            projectPlanDao.addProjectPlan(projectPlan);
            i++;//系统对不同项目进行分别排序，你处理某些阶段不需要执行的问题
        }

        //发起流程,注意以前是取得阶段中的复核模型,现在改为参数直接获取复核模型
        if (mustUseBox) {
            ProjectWorkStage projectWorkStage = projectWorkStages.get(0);//取得第一个阶段，即为项目审批立项阶段的审批
            ProcessUserDto processUserDto = startProjectProcess(projectInfo, projectWorkStage);
            //发起流程后更新项目的项目id
            projectInfo.setProcessInsId(processUserDto.getProcessInsId());
            projectInfo.setStatus(ProcessStatusEnum.RUN.getValue());
            projectInfoDao.updateProjectInfo(projectInfo);
        }
        //直接进入下一阶段
        if (!mustUseBox) {
            projectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getKey());//更新项目状态
            updateProjectInfo(projectInfo);
            List<ProjectPlan> projectPlans = projectPlanService.getProjectplanByProjectId(projectInfo.getId(), "");
            projectPlanService.enterNextStage(projectPlans.get(0).getId());
        }
    }

    /**
     * 发起立项流程
     *
     * @param projectInfo
     * @param projectWorkStage
     * @return
     * @throws BusinessException
     */
    private ProcessUserDto startProjectProcess(ProjectInfo projectInfo, ProjectWorkStage projectWorkStage) throws BusinessException {
        ProcessUserDto processUserDto = null;
        //发起相应的流程
        String folio = "【立项审批】" + projectInfo.getProjectName();
        //取得复核模型
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT__FORM__TASK__PROCESS__KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setStartUser(projectInfo.getCreator());
        processInfo.setProjectId(projectInfo.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        processInfo.setTableId(projectInfo.getId());
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setWorkStage(projectWorkStage.getWorkStageName());
        processInfo.setProcessEventExecutorName(ProjectInfoEvent.class.getSimpleName());
        processInfo.setWorkStageId(projectWorkStage.getId());
        processInfo.setAppKey(applicationConstant.getAppKey());
        try {
            processUserDto = processControllerComponent.processStart(projectInfo.getCreator(), processInfo, projectInfo.getCreator(), false);
        } catch (BpmException e) {
            logger.info(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
        return processUserDto;
    }

    /**
     * 立项审批
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void projectApproval(ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        ProjectInfo projectInfo = projectInfoDao.getProjectInfoById(approvalModelDto.getProjectId());
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectInfo.getProjectTypeId(), true);
        ProjectWorkStage projectWorkStage = projectWorkStages.get(0);//取得第一个阶段，即为项目审批立项阶段的审批
        approvalModelDto.setWorkStageId(projectWorkStage.getId());
        approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    /**
     * 分派项目经理审批
     *
     * @param approvalModelDto
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional(rollbackFor = Exception.class)
    public void projectAssignApproval(ApprovalModelDto approvalModelDto) throws BusinessException, BpmException {
        //如果选择了项目经理，则更新项目经理，如果没有选择项目经理并且该项目还为设置项目经理则取该部门领导作为项目经理
        ProjectMember projectMember = projectMemberService.get(approvalModelDto.getProjectId());
        if (StringUtils.isNotEmpty(approvalModelDto.getAppointUserAccount())) {
            approvalModelDto.setNextApproval(Lists.newArrayList(approvalModelDto.getAppointUserAccount()));
            projectMember.setUserAccountManager(approvalModelDto.getAppointUserAccount());
            projectMemberDao.updateProjectMember(projectMember);
        } else {
            if (StringUtils.isBlank(projectMember.getUserAccountManager())) {
                ProjectInfo projectInfo = getProjectInfoById(approvalModelDto.getProjectId());
                Integer departmentId = projectInfo.getDepartmentId();
                List<String> departmentCE = bpmRpcBoxRoleUserService.getDepartmentBmfzr(departmentId);
                if (CollectionUtils.isEmpty(departmentCE))
                    throw new BusinessException("未找到对应的部门领导");
                projectMember.setUserAccountManager(departmentCE.get(0));
                projectMemberDao.updateProjectMember(projectMember);
            }
        }
        processControllerComponent.processSubmitPendingTaskNodeArg(approvalModelDto);
    }

    /**
     * 获取项目详情显示的项目阶段
     *
     * @param projectId
     * @return
     */
    public List<ProjectPlanVo> getProjectPlanList(Integer projectId) {
        List<ProjectPlan> projectPlanList = projectPlanDao.getProjectPlanList(projectId);
        List<ProjectPlanVo> projectPlanVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(projectPlanList)) {
            projectPlanList.remove(0);//去除立项阶段
            projectPlanVos = LangUtils.transform(projectPlanList, projectPlan -> getProjectPlanVo(projectPlan));
        }
        return projectPlanVos;
    }

    /**
     * 获取项目详情显示的项目阶段
     *
     * @param planId
     * @return
     */
    public ProjectPlanVo getProjectPlanItem(Integer planId) {
        ProjectPlan projectPlan = projectPlanDao.getProjectPlanById(planId);
        if (projectPlan == null) return null;
        return getProjectPlanVo(projectPlan);
    }

    public ProjectPlanVo getProjectPlanVo(ProjectPlan projectPlan) {
        ProjectPlanVo projectPlanVo = new ProjectPlanVo();
        BeanUtils.copyProperties(projectPlan, projectPlanVo);
        String viewUrl = String.format("/%s/ProjectPlan/planDetailsById?planId=", applicationConstant.getAppKey());
        try {
            ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.getEnumByKey(projectPlan.getProjectStatus());
            switch (projectStatusEnum) {
                case FINISH:
                case TASK:
                    projectPlanVo.setPlanDisplayUrl(String.format("%s%s", viewUrl, projectPlan.getId()));
                case PLAN:
                    //判断有没有发起流程 如果发起了流程 则取待办 没有发起流程 则取任务
                    if (StringUtils.equals(projectPlan.getProcessInsId(), "-1")) {
                        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
                        projectResponsibilityDto.setProjectId(projectPlan.getProjectId());
                        projectResponsibilityDto.setPlanId(projectPlan.getId());
                        projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
                        projectResponsibilityDto.setModel(ResponsibileModelEnum.NEWPLAN.getId());
                        ProjectResponsibilityDto projectTask = bpmRpcProjectTaskService.getProjectTask(projectResponsibilityDto);
                        if (projectTask != null) {
                            projectPlanVo.setPlanExecutor(publicService.getUserNameByAccount(projectTask.getUserAccount()));
                            projectPlanVo.setPlanCanExecut(StringUtils.equals(commonService.thisUserAccount(), projectTask.getUserAccount()));
                            projectPlanVo.setPlanExecutUrl(projectTask.getUrl());
                            projectPlanVo.setPlanDisplayUrl(String.format("%s%s", viewUrl, projectPlan.getId()));
                        }
                    } else {
                        List<ActivitiTaskNodeDto> activitiTaskNodeDtos = null;
                        try {
                            activitiTaskNodeDtos = bpmRpcActivitiProcessManageService.queryProcessCurrentTask(projectPlan.getProcessInsId());
                        } catch (BpmException e) {
                            logger.error("计划流程查询异常", e);
                        }
                        if (CollectionUtils.isNotEmpty(activitiTaskNodeDtos)) {
                            ActivitiTaskNodeDto activitiTaskNodeDto = activitiTaskNodeDtos.get(0);
                            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(Integer.parseInt(activitiTaskNodeDto.getBusinessKey()));
                            String approvalUrl = boxReDto.getProcessApprovalUrl();
                            if (StringUtils.equals(ProcessActivityEnum.EDIT.getValue(), activitiTaskNodeDto.getTaskKey())) {
                                approvalUrl = boxReDto.getProcessEditUrl();
                            }
                            approvalUrl = String.format("/%s%s?boxId=%s&processInsId=%s&taskId=%s", boxReDto.getGroupName(), approvalUrl, boxReDto.getId(), activitiTaskNodeDto.getProcessInstanceId(), activitiTaskNodeDto.getTaskId());
                            String displayUrl = String.format("/%s%s?boxId=%s&processInsId=%s&taskId=%s", boxReDto.getGroupName(), boxReDto.getProcessDisplayUrl(), boxReDto.getId(), activitiTaskNodeDto.getProcessInstanceId(), activitiTaskNodeDto.getTaskId());
                            projectPlanVo.setPlanExecutor(publicService.getUserNameByAccountList(activitiTaskNodeDto.getUsers()));
                            if (activitiTaskNodeDto.getUsers().contains(commonService.thisUserAccount())) {
                                projectPlanVo.setPlanCanExecut(true);
                                projectPlanVo.setPlanExecutUrl(approvalUrl);
                            }
                            projectPlanVo.setPlanDisplayUrl(displayUrl);
                        }
                    }
                    break;
            }

        } catch (Exception e) {
            logger.error("获取任务信息异常", e);
        }
        return projectPlanVo;
    }


    public ProjectInfo getProjectInfoByProcessInsId(String processInsId) {
        return projectInfoDao.getProjectInfoByProcessInsId(processInsId);
    }

    public ProjectInfo getProjectInfoById(Integer projectId) {
        return projectInfoDao.getProjectInfoById(projectId);
    }

    public String getProjectName(List<ProjectInfo> projectInfos) {
        if (CollectionUtils.isEmpty(projectInfos))
            return "";
        List<String> stringList = LangUtils.transform(projectInfos, input -> input.getProjectName());
        return FormatUtils.transformListString(stringList);
    }

    public List<ProjectInfo> getProjectInfoByProjectIds(List<Integer> projectIds) {
        return projectInfoDao.getProjectInfoByProjectIds(projectIds);
    }

    public ProjectInfoVo getSimpleProjectInfoVo(ProjectInfo projectInfo) {
        ProjectInfoVo projectInfoVo = new ProjectInfoVo();
        if (projectInfo == null) {
            return projectInfoVo;
        }
        BeanUtils.copyProperties(projectInfo, projectInfoVo);
        projectInfoVo.setServiceComeFromName(bidBaseDataDicService.getNameById(projectInfoVo.getServiceComeFrom()));
        if (StringUtils.isNotEmpty(projectInfo.getContractId()) && StringUtils.isNotEmpty(projectInfo.getContractId())) {
            List<String> contractIds = FormatUtils.transformString2List(projectInfo.getContractId());
            List<String> contractName = FormatUtils.transformString2List(projectInfo.getContractName());
            Integer size = Stream.of(contractIds.size(), contractName.size()).mapToInt(Integer::intValue).min().getAsInt();
            for (int i = 0; i < size; i++) {
                projectInfoVo.getContractList().add(new KeyValueDto(contractIds.get(i), contractName.get(i)));
            }
        }
        //项目类型
        if (projectInfo.getProjectClassId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectClassId());
            if (projectClassify != null) {
                projectInfoVo.setProjectClassName(projectClassify.getName());
            }
        }
        //项目类别
        if (projectInfo.getProjectTypeId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectTypeId());
            if (projectClassify != null) {
                projectInfoVo.setProjectTypeName(projectClassify.getName());
            }
        }
        //项目范围
        if (projectInfo.getProjectCategoryId() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectInfo.getProjectCategoryId());
            if (projectClassify != null) {
                projectInfoVo.setProjectCategoryName(projectClassify.getName());
            }
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getEntrustPurpose())) {
            //委托目的
            projectInfoVo.setEntrustPurposeName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getEntrustPurpose()).getName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getEntrustAimType())) {
            //委托目的类别
            projectInfoVo.setEntrustAimTypeName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getEntrustAimType()).getName());
        }
        if (StringUtils.isNotBlank(projectInfo.getProvince())) {
            //省
            projectInfoVo.setProvinceName(erpAreaService.getSysAreaName(projectInfo.getProvince()));
        }
        if (StringUtils.isNotBlank(projectInfo.getCity())) {
            //市
            projectInfoVo.setCityName(erpAreaService.getSysAreaName(projectInfo.getCity()));
        }
        if (StringUtils.isNotBlank(projectInfo.getDistrict())) {
            //县
            projectInfoVo.setDistrictName(erpAreaService.getSysAreaName(projectInfo.getDistrict()));
        }
        //紧急程度
        if (projectInfo.getUrgency() != null) {
            projectInfoVo.setUrgencyName(bidBaseDataDicService.getNameById(projectInfo.getUrgency()));
        }
        projectInfoVo.setLoanTypeName(bidBaseDataDicService.getNameById(projectInfo.getLoanType()));
        if (projectInfo.getDepartmentId() != null) {
            projectInfoVo.setDepartmentName(getDepartmentDto(projectInfo.getDepartmentId()).getName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getValueType())) {
            //价值类型
            projectInfoVo.setValueTypeName(bidBaseDataDicService.getNameById(projectInfo.getValueType()));
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getPropertyScope())) {
            projectInfoVo.setPropertyScopeName(bidBaseDataDicService.getNameById(projectInfo.getPropertyScope()));
        }

        if (projectInfo.getId() != null && projectInfo.getId().intValue() > 0) {
            InitiateConsignorVo consignorVo = consignorService.getDataByProjectId(projectInfo.getId());
            if (consignorVo != null) {
                projectInfoVo.setConsignorVo(consignorVo);
            }
        }

        if (projectInfo.getId() != null && projectInfo.getId().intValue() > 0) {
            InitiatePossessorVo possessorVo = possessorService.getDataByProjectId(projectInfo.getId());
            if (possessorVo != null) {
                projectInfoVo.setPossessorVo(possessorVo);
            }
        }

        if (projectInfo.getId() != null && projectInfo.getId().intValue() > 0) {
            InitiateUnitInformationVo informationVo = unitInformationService.getDataByProjectId(projectInfo.getId());
            if (informationVo != null) {
                projectInfoVo.setUnitInformationVo(informationVo);
            }
        }
        if (projectInfo.getId() != null && projectInfo.getId().intValue() > 0) {
            ProjectMemberVo projectMemberVo = projectMemberService.getProjectMember(projectInfo.getId());
            if (projectMemberVo != null) {
                projectInfoVo.setProjectMemberVo(projectMemberVo);
            }
        }
        return projectInfoVo;
    }

    public List<ProjectInfo> getProjectInfoList(ProjectInfo projectInfo) {
        return projectInfoDao.getProjectInfoList(projectInfo);
    }

    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectInfoDao.updateProjectInfo(projectInfo);
    }


    public SysDepartmentDto getDepartmentDto(Integer id) {
        return erpRpcDepartmentService.getDepartmentById(id);
    }

    public ProjectInfo change(ProjectInfoDto dto) {
        ProjectInfo projectInfo = new ProjectInfo();
        BeanUtils.copyProperties(dto, projectInfo);
        return projectInfo;
    }


    public InitiateProjectDto format(String val) {
        InitiateProjectDto dto = null;
        if (StringUtils.isNotBlank(val)) {
            dto = JSONObject.parseObject(val, InitiateProjectDto.class);
        }
        return dto;
    }


    public int saveProjectInfo(ProjectInfo projectInfo) {
        if (projectInfo.getId() == null || projectInfo.getId() == 0) {
            projectInfo.setCreator(commonService.thisUserAccount());
            projectInfoDao.saveProjectInfo_returnID(projectInfo);
        } else {
            projectInfoDao.updateProjectInfo(projectInfo);
        }
        return projectInfo.getId();
    }

    /**
     * 单位性质 crm
     *
     * @return
     */
    public List<CrmBaseDataDicDto> getUnitPropertiesList() {
        List<CrmBaseDataDicDto> crmBaseDataDicDtos = crmRpcBaseDataDicService.getUnitPropertiesList();
        return crmBaseDataDicDtos;
    }

    /**
     * 仅仅是联系人清楚
     */
    public void clear() {
        consignorService.clear();
        possessorService.clear();
        unitInformationService.clear();
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachmentByDto(attachmentDto);
            }
        }
    }

    public void projectClearData(Integer id) {
        if (id == null) {
            return;
        }
        ProjectInfo projectInfo = getProjectInfoById(id);
        if (projectInfo == null) {
            return;
        }
        ProjectInfoVo projectInfoVo = getSimpleProjectInfoVo(projectInfo);
        projectInfoDao.deleteByProjectInfoId(id);
        if (projectInfoVo.getConsignorVo() != null) {
            if (projectInfoVo.getConsignorVo().getId() != null) {
                consignorService.remove(projectInfoVo.getConsignorVo().getId());
            }
        }
        if (projectInfoVo.getUnitInformationVo() != null) {
            if (projectInfoVo.getUnitInformationVo().getId() != null) {
                unitInformationService.remove(projectInfoVo.getUnitInformationVo().getId());
            }
        }
        if (projectInfoVo.getPossessorVo() != null) {
            if (projectInfoVo.getPossessorVo().getId() != null) {
                possessorService.remove(projectInfoVo.getPossessorVo().getId());
            }
        }
        if (projectInfoVo.getProjectMemberVo() != null) {
            if (projectInfoVo.getProjectMemberVo().getId() != null) {
                projectMemberService.deleteProjectMemberById(projectInfoVo.getProjectMemberVo().getId());
            }
        }
    }

    /**
     * 项目是否可操作
     *
     * @param projectId
     * @return
     */
    public boolean isProjectOperable(Integer projectId) {
        ProjectInfo projectInfo = getProjectInfoById(projectId);
        if (projectInfo == null) return false;
        if (ProjectStatusEnum.FINISH.getKey().equals(projectInfo.getProjectStatus()))
            return false;
        if (ProjectStatusEnum.CLOSE.getKey().equals(projectInfo.getProjectStatus()))
            return false;
        if (ProjectStatusEnum.PAUSE.getKey().equals(projectInfo.getProjectStatus()))
            return false;
        return true;
    }

    /**
     * 完成项目
     *
     * @param projectId
     */
    public void finishProject(Integer projectId) throws BusinessException {
        //检查该项目是否还有待提交或待审批的任务
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(applicationConstant.getAppKey(), Lists.newArrayList(projectId));
        if (CollectionUtils.isNotEmpty(projectTaskList))
            throw new BusinessException("项目中还有待提交的任务");
        ProjectInfo projectInfo = getProjectInfoById(projectId);
        projectInfo.setProjectStatus(ProjectStatusEnum.FINISH.getKey());
        updateProjectInfo(projectInfo);
    }

    /**
     * 进入项目下个阶段
     *
     * @param projectId
     */
    public void enterNextStage(Integer projectId) throws BusinessException {
        List<ProjectPlan> planList = projectPlanService.getCurrentProjectPlans(projectId);
        if (CollectionUtils.isEmpty(planList))
            throw new BusinessException("未找到进行中的阶段");
        Integer planId = planList.get(0).getId();
        Integer totalPlanDetails = projectPlanDetailsService.getTotalPlanDetails(planId);//查看该阶段是否有任务
        if (totalPlanDetails <= 0)
            throw new BusinessException("请先为该阶段添加相关任务");
        boolean isAllFinish = projectPlanDetailsService.isAllPlanDetailsFinish(planId); //判断当前阶段是否任务是否完成
        if (!isAllFinish) {
            throw new BusinessException("还有未完成的任务，请先完成该阶段所有任务");
        }
        projectPlanService.enterNextStage(planId);
    }

    /**
     * 获取当前项目的项目类型
     *
     * @param projectCategoryId
     * @return
     */
    public AssessProjectTypeEnum getAssessProjectType(Integer projectCategoryId) {
        BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(projectCategoryId);
        if (baseProjectClassify != null) {
            if (AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE.equals(baseProjectClassify.getFieldName()))
                return AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE;
            if (AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE.equals(baseProjectClassify.getFieldName()))
                return AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND;
            if (AssessProjectClassifyConstant.COMPREHENSIVE_ASSETS_TYPE.equals(baseProjectClassify.getFieldName()))
                return AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_ASSETS;
        }
        return null;
    }
}
