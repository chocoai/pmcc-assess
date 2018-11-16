package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectInfoDto;
import com.copower.pmcc.assess.dto.input.project.ProjectMemberDto;
import com.copower.pmcc.assess.dto.input.project.initiate.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateContactsVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.ProjectAssignEvent;
import com.copower.pmcc.assess.service.event.project.ProjectInfoEvent;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateContactsService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
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
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Lazy
    @Autowired
    private CrmCustomerService crmCustomerService;
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
    @Lazy
    @Autowired
    private InitiateContactsService initiateContactsService;
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
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
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


    /**
     * 项目立项申请
     *
     * @param projectDto
     */
    public boolean projectApply(InitiateProjectDto projectDto, Boolean bisNextUser) throws BusinessException {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUserAccountManager(projectDto.getProjectInfo().getUserAccountManager());
        projectMember.setUserAccountMember(projectDto.getProjectInfo().getUserAccountMember());
        projectMember.setBisEnable(true);
        return projectApplyChange(projectDto.getConsignor(), projectDto.getUnitinformation(), projectDto.getPossessor(), change(projectMember), projectDto.getProjectInfo(), bisNextUser);
    }

    @Transactional
    public boolean projectApplyChange(InitiateConsignorDto consignorDto, InitiateUnitInformationDto unitInformationDto, InitiatePossessorDto possessorDto, ProjectMemberDto projectMemberDto,
                                      ProjectInfoDto projectInfoDto, Boolean bisNextUser) {
        boolean flag = true;
        try {
            ProjectInfo projectInfo = change(projectInfoDto);
            if (projectInfo.getCreator() == null)
                projectInfo.setCreator(commonService.thisUserAccount());

            int projectId = 0;
            projectId = projectInfoDao.saveProjectInfo_returnID(projectInfo);// save

            consignorDto.setProjectId(projectId);
            int consignorId = consignorService.add(consignorDto);
            unitInformationDto.setProjectId(projectId);
            int unitInformationId = unitInformationService.add(unitInformationDto);
            possessorDto.setProjectId(projectId);
            int possessorId = possessorService.add(possessorDto);
            //更新联系人中的主表id (这根据联系人的标识符(flag)来确定联系人类型)

            initiateContactsService.update(consignorId, InitiateContactsEnum.CONSIGNOR.getId());
            initiateContactsService.update(possessorId, InitiateContactsEnum.POSSESSOR.getId());
            initiateContactsService.update(unitInformationId, InitiateContactsEnum.UNIT_INFORMATION.getId());

            //附件更新
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class), projectId);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(InitiateConsignor.class), consignorId);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(InitiatePossessor.class), possessorId);

            //保存项目成员

            projectMemberDto.setProjectId(projectId);
            projectMemberDto.setCreator(commonService.thisUserAccount());
            projectMemberService.saveReturnId(projectMemberDto);

            //如果没有设置项目经理，则由部门领导分派项目经理
            if (StringUtils.isBlank(projectMemberDto.getUserAccountManager())) {
                //发起流程
                List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectInfo.getProjectTypeId(), true);
                String boxName = baseParameterServcie.getParameterValues(BaseParameterEnum.PROJECT_APPLY_ASSIGN_PROCESS_KEY.getParameterKey());
                Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
                ProcessInfo processInfo = new ProcessInfo();
                processInfo.setProjectId(projectInfo.getId());
                processInfo.setProcessName(boxReDto.getProcessName());
                processInfo.setGroupName(boxReDto.getGroupName());
                processInfo.setFolio(projectInfo.getProjectName());//流程描述
                processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectInfo.class));
                processInfo.setBoxId(boxReDto.getId());
                processInfo.setStartUser(commonService.thisUserAccount());
                processInfo.setWorkStage(projectWorkStages.get(0).getWorkStageName());
                processInfo.setWorkStageId(projectWorkStages.get(0).getId());
                processInfo.setProcessEventExecutorName(ProjectAssignEvent.class.getSimpleName());
                processInfo.setTableId(projectInfo.getId());
                //取审批人
                if (StringUtils.isNotEmpty(projectMemberDto.getUserAccountManager())) {
                    processInfo.setNextUser(Lists.newArrayList(projectMemberDto.getUserAccountManager()));
                } else {
                    Integer departmentId = projectInfo.getDepartmentId();
                    //取部门领导
                    List<String> departmentCE = bpmRpcBoxRoleUserService.getDepartmentCE(departmentId);
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
            } else {
                initProjectInfo(projectInfo);//初始化项目信息
            }
        } catch (Exception e) {
            flag = false;
            logger.error("exception!" + e.getMessage());
        }
        return flag;
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
        projectUpdate(format(formData), projectInfoId);//保存数据
        processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);//提交流程
    }

    /*项目立项修改*/
    @Transactional
    public void projectUpdate(InitiateProjectDto initiateProjectDto, Integer projectInfoId) throws Exception {
        ProjectInfoDto projectInfo = initiateProjectDto.getProjectInfo();
        ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfoId);
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
        consignorService.update(initiateProjectDto.getConsignor());
        possessorService.update(initiateProjectDto.getPossessor());
        projectMemberService.updateProjectMember(projectMemberVo);
        unitInformationService.update(initiateProjectDto.getUnitinformation());
    }


    /**
     * 初始化项目信息
     *
     * @param projectInfo
     */
    @Transactional
    public void initProjectInfo(ProjectInfo projectInfo) throws BusinessException {
        Integer projectTypeId = projectInfo.getProjectTypeId();//项目类别id
        Integer projectCategoryId = projectInfo.getProjectCategoryId();//项目范围id
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectTypeId, true);

        if (CollectionUtils.isEmpty(projectWorkStages)) {
            BaseProjectClassify defaultType = baseProjectClassifyService.getDefaultType();
            if (defaultType != null) {
                projectTypeId = defaultType.getId();
            }
            projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(projectTypeId, true);
        }

        List<ProjectPhase> projectPhaseList = null;
        if (projectCategoryId != null && projectCategoryId > 0) {
            //取一次事项
            projectPhaseList = projectPhaseService.getCacheProjectPhaseByCategoryId(projectCategoryId);
        }

        if (projectCategoryId == null || projectCategoryId <= 0 || CollectionUtils.isEmpty(projectPhaseList)) {
            //取得默认项目范围
            BaseProjectClassify defaultCategory = baseProjectClassifyService.getDefaultCategory(projectTypeId);
            if (defaultCategory != null) {
                projectCategoryId = defaultCategory.getId();
            }
        }

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
            projectPlan.setProjectStatus(ProjectStatusEnum.WAIT.getName());
            projectPlan.setBisRestart(false);
            projectPlan.setStageSort(i);
            projectPlanDao.addProjectPlan(projectPlan);
            i++;//系统对不同项目进行分别排序，你处理某些阶段不需要执行的问题
        }

        ProjectWorkStage projectWorkStage = projectWorkStages.get(0);//取得第一个阶段，即为项目审批立项阶段的审批
        if (StringUtils.isNotBlank(projectWorkStage.getReviewBoxName()))//注意是取复核模型，因为第一个阶段没有工作成果提交，所以取复核较为合理
        {
            ProcessUserDto processUserDto = startProjectProcess(projectInfo, projectWorkStage);
            //发起流程后更新项目的项目id
            projectInfo.setProcessInsId(processUserDto.getProcessInsId());
            projectInfo.setStatus(ProcessStatusEnum.RUN.getValue());
            projectInfoDao.updateProjectInfo(projectInfo);
        } else {//直接进入下一阶段
            projectInfo.setProjectStatus(ProjectStatusEnum.NORMAL.getName());//更新流程状态
            updateProjectInfo(projectInfo);
            List<ProjectPlan> projectPlans = projectPlanService.getProjectplanByProjectId(projectInfo.getId(), "");
            projectPlanService.updatePlanStatus(projectPlans.get(0).getId());
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
        Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectWorkStage.getReviewBoxName());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
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
            processUserDto = processControllerComponent.processStart(processInfo, projectInfo.getCreator(), false);
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
                List<String> departmentCE = bpmRpcBoxRoleUserService.getDepartmentCE(departmentId);
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
        projectPlanList.remove(0);//去除立项阶段
        String viewUrl = String.format("/%s/ProjectPlan/planDetailsById?planId=", applicationConstant.getAppKey());
        List<ProjectPlanVo> projectPlanVos = LangUtils.transform(projectPlanList, projectPlan -> {
            ProjectPlanVo projectPlanVo = new ProjectPlanVo();
            BeanUtils.copyProperties(projectPlan, projectPlanVo);
            try {
                ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.getEnumByName(projectPlan.getProjectStatus());
                switch (projectStatusEnum) {
                    case FINISH:
                    case TASK:
                        projectPlanVo.setPlanDisplayUrl(String.format("%s%s", viewUrl, projectPlan.getId()));
                    case PLAN:
                        //判断有没有发起流程 如果发起了流程 则取待办 没有发起流程 则取任务
                        if (StringUtils.equals(projectPlan.getProcessInsId(), "-1")) {
                            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
                            projectResponsibilityDto.setProjectId(projectId);
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
                                projectPlanVo.setPlanCanExecut(activitiTaskNodeDto.getUsers().contains(commonService.thisUserAccount()));
                                projectPlanVo.setPlanExecutUrl(approvalUrl);
                                projectPlanVo.setPlanDisplayUrl(displayUrl);
                            }
                        }
                        break;
                }

            } catch (Exception e) {
                logger.error("获取任务信息异常", e);
            }
            return projectPlanVo;
        });
        return projectPlanVos;
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
        BeanUtils.copyProperties(projectInfo, projectInfoVo);
        if (!ObjectUtils.isEmpty(projectInfo.getId()) && projectInfo.getId() > 0) {
            ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
            projectInfoVo.setProjectMemberVo(projectMemberVo);
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
        return projectInfoVo;
    }

    public ProjectInfoVo getProjectInfoVoView(ProjectInfo projectInfo) {
        ProjectInfoVo projectInfoVo = getSimpleProjectInfoVo(projectInfo);
        if (StringUtils.isNotBlank(projectInfo.getProvince())) {

            projectInfoVo.setProvinceName(erpAreaService.getSysAreaName(projectInfo.getProvince()));//省
        }
        if (StringUtils.isNotBlank(projectInfo.getCity())) {
            projectInfoVo.setCityName(erpAreaService.getSysAreaName(projectInfo.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(projectInfo.getDistrict())) {
            projectInfoVo.setDistrictName(erpAreaService.getSysAreaName(projectInfo.getDistrict()));//县
        }
        //紧急程度
        if (projectInfo.getUrgency() != null) {
            projectInfoVo.setUrgencyName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getUrgency()).getName());
        }
        if (projectInfo.getDepartmentId() != null) {
            projectInfoVo.setDepartmentName(getDepartmentDto(projectInfo.getDepartmentId()).getName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(projectInfo.getValueType())) {
            //价值类型
            projectInfoVo.setValueTypeName(bidBaseDataDicService.getCacheDataDicById(projectInfo.getValueType()).getName());
        }
        InitiateConsignorVo consignorVo = consignorService.getDataByProjectId(projectInfo.getId());
        projectInfoVo.setConsignorVo(consignorVo);

        InitiatePossessorVo possessorVo = possessorService.getDataByProjectId(projectInfo.getId());
        projectInfoVo.setPossessorVo(possessorVo);

        InitiateUnitInformationVo informationVo = unitInformationService.getDataByProjectId(projectInfo.getId());
        projectInfoVo.setUnitInformationVo(informationVo);
        ProjectMemberVo projectMemberVo = projectMemberService.loadProjectMemberList(projectInfo.getId());
        projectInfoVo.setProjectMemberVo(projectMemberVo);
        return projectInfoVo;
    }

    public List<ProjectInfo> getProjectInfoList(ProjectInfo projectInfo) {
        return projectInfoDao.getProjectInfoList(projectInfo);
    }

    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectInfoDao.updateProjectInfo(projectInfo);
    }

    public CrmCustomerDto getCRM(Integer id) {
        return crmCustomerService.getCustomer(id);
    }

    public SysDepartmentDto getDepartmentDto(Integer id) {
        return erpRpcDepartmentService.getDepartmentById(id);
    }

    /*委托目的*/
    public List<BaseDataDic> list_entrustment_purpose() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        return baseDataDics;
    }

    /*紧急程度*/
    public List<BaseDataDic> project_initiate_urgency() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_INITIATE_URGENCY);
        return baseDataDics;
    }

    /*价值类型*/
    public List<BaseDataDic> value_type() {
        List<BaseDataDic> baseDataDics = bidBaseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE);
        return baseDataDics;
    }

    /*联系人 vo crm*/
    public BootstrapTableVo listContactsVo(Integer customerId, Integer cType, Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InitiateContactsVo> vos = null;
        if (ObjectUtils.isEmpty(pid)) {
            pid = InitiateContactsDto.C_PID;
        }
        if (!ObjectUtils.isEmpty(customerId)) {
            initiateContactsService.writeContacts(customerId, cType, pid);//写入本地
        }
        vos = initiateContactsService.getVoList(pid, cType, customerId);//从本地获取
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<InitiateContactsVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BootstrapTableVo crmContacts(Integer customerId, String search) {
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            vo = crmCustomerService.getCustomerLinkmanPageList(customerId, search);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return vo;
    }

    /*联系人类型*/
    public Map<String, String> getTypeInitiateContactsMap() {
        return initiateContactsService.getTypeMap();
    }

    //添加联系人
    public boolean addContacts(InitiateContactsDto dto) {
        return initiateContactsService.add(dto);
    }

    public void addContacts(List<InitiateContactsDto> dtos) {
        for (InitiateContactsDto dto : dtos) {
            this.addContacts(dto);
        }
    }

    /*联系人删除*/
    public boolean removeContacts(Integer id) {
        return initiateContactsService.remove(id);
    }

    /*联系人修改*/
    public boolean updateContacts(InitiateContactsDto dto) {
        return initiateContactsService.update(dto);
    }

    public InitiateContactsVo getInitiateContacts(Integer id) {
        return initiateContactsService.get(id);
    }

    public ProjectInfo change(ProjectInfoDto dto) {
        ProjectInfo projectInfo = new ProjectInfo();
        BeanUtils.copyProperties(dto, projectInfo);
        return projectInfo;
    }

    public ProjectMemberDto change(ProjectMember projectMember) {
        ProjectMemberDto dto = new ProjectMemberDto();
        BeanUtils.copyProperties(projectMember, dto);
        return dto;
    }

    public InitiateProjectDto format(String val) {
        InitiateProjectDto dto = null;
        if (StringUtils.isNotBlank(val)) {
            dto = JSONObject.parseObject(val, InitiateProjectDto.class);
        }
        return dto;
    }

    /*报告使用单位 获取*/
    public InitiateUnitInformationVo getInitiateUnitInformation(Integer id) {
        return unitInformationService.get(id);
    }

    /*占有人 获取*/
    public InitiatePossessorVo getInitiatePossessor(Integer id) {
        return possessorService.get(id);
    }

    /*委托人 获取*/
    public InitiateConsignorVo getInitiateConsignor(Integer id) {
        return consignorService.get(id);
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
     * 回写到CRM中
     *
     * @param projectID
     * @param cType
     */
    public void writeCrmCustomerDto(Integer projectID, Integer cType) {
        initiateContactsService.writeCrmCustomerDto(projectID, cType);
    }

    public void init() {
        initiateContactsService.remove(0, InitiateContactsEnum.CONSIGNOR.getId());
        initiateContactsService.remove(0, InitiateContactsEnum.POSSESSOR.getId());
        initiateContactsService.remove(0, InitiateContactsEnum.UNIT_INFORMATION.getId());
    }

}
