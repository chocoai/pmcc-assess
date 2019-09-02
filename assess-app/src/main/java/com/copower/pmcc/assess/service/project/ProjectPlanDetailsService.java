package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectTaskReturnRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicApplyTransferService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineInfoService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService;
import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.enums.SysProjectEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:09
 */
@Service
public class ProjectPlanDetailsService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicApplyTransferService basicApplyTransferService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private SchemeSurePriceRecordDao schemeSurePriceRecordDao;
    @Autowired
    private ProjectTaskReturnRecordDao projectTaskReturnRecordDao;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;

    public ProjectPlanDetails getProjectPlanDetailsById(Integer id) {
        return projectPlanDetailsDao.getProjectPlanDetailsById(id);
    }

    public ProjectPlanDetails getProjectPlanDetailsByProcessInsId(String processInsId) {
        return projectPlanDetailsDao.getProjectPlanDetailsItemByProcessInsId(processInsId);
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsByPlanId(Integer planId) {
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
        return getProjectPlanDetailsVos(projectPlanDetails, true);
    }

    public List<ProjectPlanDetails> getPlanDetailsByPlanId(Integer planId) {
        return projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
    }

    public ProjectPlanDetails getProjectPlanDetails(Integer declareId, Integer projectPhaseId) {
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setDeclareRecordId(declareId);
        projectPlanDetails.setProjectPhaseId(projectPhaseId);
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetails);
        if (CollectionUtils.isEmpty(planDetailsList)) return null;
        return planDetailsList.get(0);
    }

    /**
     * 获取权证下的调查信息内容
     *
     * @param declareId
     * @return
     */
    public List<ProjectPlanDetailsVo> getPlanDetailsByDeclareId(Integer declareId) {
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setDeclareRecordId(declareId);
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetails);
        if (CollectionUtils.isEmpty(planDetailsList)) return Lists.newArrayList();
        List<ProjectPlanDetailsVo> planDetailsVoList = LangUtils.transform(planDetailsList, o -> getProjectPlanDetailsVo(o));
        if (CollectionUtils.isNotEmpty(planDetailsVoList)) {
            String viewUrl = String.format("/%s/ProjectTask/projectTaskDetailsById?planDetailsId=", applicationConstant.getAppKey());
            for (ProjectPlanDetailsVo projectPlanDetailsVo : planDetailsVoList) {
                //设置查看url
                if (projectPlanDetailsVo.getBisStart()) {
                    projectPlanDetailsVo.setDisplayUrl(String.format("%s%s", viewUrl, projectPlanDetailsVo.getId()));
                }
            }
        }
        return planDetailsVoList;
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsByPlanApply(Integer planId) {
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
        return getProjectPlanDetailsVos(projectPlanDetails, false);
    }

    /**
     * 保存计划详情数据
     *
     * @param projectPlanDetails
     */
    public void saveProjectPlanDetails(ProjectPlanDetails projectPlanDetails) throws BusinessException {
        if (projectPlanDetails == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (projectPlanDetails.getId() != null && projectPlanDetails.getId() > 0) {
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
        } else {
            projectPlanDetails.setCreator(commonService.thisUserAccount());
            projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
        }
    }

    /**
     * 项目详情阶段任务信息
     *
     * @param projectId
     * @return
     */
    public BootstrapTableVo getPlanDetailListByPlanId(Integer projectId, Integer planId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
        if (CollectionUtils.isEmpty(projectPlanDetails)) return bootstrapTableVo;
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = getProjectPlanDetailsVos(projectPlanDetails, false);

        //获取当前人该阶段下待处理的任务
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProjectId(projectId);
        projectResponsibilityDto.setPlanId(planId);
        projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
        projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getAgentProjectTaskList(projectResponsibilityDto);

        //获取该阶段下正在运行的待审批
        List<String> processInsIds = Lists.newArrayList();
        for (ProjectPlanDetails projectPlanDetail : projectPlanDetails) {
            if (StringUtils.equals(projectPlanDetail.getStatus(), SysProjectEnum.RUNING.getValue())) {
                if (!StringUtils.equals(projectPlanDetail.getProcessInsId(), "0")) {
                    processInsIds.add(projectPlanDetail.getProcessInsId());
                }
            }
        }
        List<ActivitiTaskNodeDto> activitiTaskNodeDtos = null;
        try {
            activitiTaskNodeDtos = bpmRpcActivitiProcessManageService.queryProcessCurrentTask(processInsIds.toArray(new String[processInsIds.size()]));
        } catch (BpmException e) {
            logger.error("计划任务获取流程任务异常", e);
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectPhase sceneExplorePhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
        ProjectPhase caseStudyChildPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
        List<Integer> phaseIds = Lists.newArrayList(sceneExplorePhase.getId(), caseStudyChildPhase.getId());
        List<Integer> phaseFullIds = Lists.newArrayList(phaseIds);
        String viewUrl = String.format("/%s/ProjectTask/projectTaskDetailsById?planDetailsId=", applicationConstant.getAppKey());
        //判断任务是否结束，如果结束只能查看详情
        for (ProjectPlanDetailsVo projectPlanDetailsVo : projectPlanDetailsVos) {
            //任务在进行中，则需判断任务是在提交还是审批的状态
            // 如果任务是在审批或完成状态可查看详情
            //如果为待提交状态 当前人与任务执行人相同 可提交任务
            //如果为待审批状态 当前人与审批人相同 可审批该任务
            SysProjectEnum sysProjectEnum = SysProjectEnum.getEnumByName(SysProjectEnum.getNameByKey(projectPlanDetailsVo.getStatus()));
            switch (sysProjectEnum) {
                case NONE:
                case CLOSE://业务异常关闭流程错误更新状态的数据
                case RUNING:
                    if (StringUtils.equals(projectPlanDetailsVo.getProcessInsId(), "0")) {
                        if (CollectionUtils.isNotEmpty(projectTaskList)) {
                            for (ProjectResponsibilityDto responsibilityDto : projectTaskList) {
                                if (projectPlanDetailsVo.getId().intValue() == responsibilityDto.getPlanDetailsId().intValue()) {
                                    String executeUrl = String.format(responsibilityDto.getUrl().contains("?") ? "%s&responsibilityId=%s" : "%s?responsibilityId=%s", responsibilityDto.getUrl(), responsibilityDto.getId());
                                    projectPlanDetailsVo.setExcuteUrl(executeUrl);
                                    //设置粘贴
                                    if (phaseFullIds.contains(projectPlanDetailsVo.getProjectPhaseId())) {
                                        projectPlanDetailsVo.setCanPaste(true);
                                    }
                                }
                            }
                        }
                    } else {
                        if (CollectionUtils.isNotEmpty(activitiTaskNodeDtos)) {
                            String processInsId = projectPlanDetailsVo.getProcessInsId();
                            String taskId = new String();
                            //根据情况获取对应的审批节点数据activitiTaskNodeDto
                            ActivitiTaskNodeDto activitiTaskNodeDto = null;
                            for (ActivitiTaskNodeDto taskNodeDto : activitiTaskNodeDtos) {
                                if (StringUtils.equals(taskNodeDto.getProcessInstanceId(), processInsId)) {
                                    activitiTaskNodeDto = taskNodeDto;
                                    taskId = taskNodeDto.getTaskId();
                                }
                            }
                            if (activitiTaskNodeDto != null) {
                                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(Integer.parseInt(activitiTaskNodeDto.getBusinessKey()));
                                String approvalUrl = boxReDto.getProcessApprovalUrl();
                                if (StringUtils.equals(ProcessActivityEnum.EDIT.getValue(), activitiTaskNodeDto.getTaskKey())) {
                                    approvalUrl = boxReDto.getProcessEditUrl();
                                }
                                approvalUrl = String.format("/%s%s?boxId=%s&processInsId=%s&taskId=%s", boxReDto.getGroupName(), approvalUrl, boxReDto.getId(), processInsId, taskId);
                                if (activitiTaskNodeDto.getUsers().contains(commonService.thisUserAccount())) {
                                    projectPlanDetailsVo.setExcuteUrl(approvalUrl);
                                }
                            }
                        }
                    }
                    break;
            }
            if (projectPlanDetailsVo.getBisLastLayer() == Boolean.TRUE)
                projectPlanDetailsVo.setDisplayUrl(String.format("%s%s", viewUrl, projectPlanDetailsVo.getId()));
            //设置复制
            if (projectPlanDetailsVo.getBisLastLayer() == Boolean.TRUE && phaseIds.contains(projectPlanDetailsVo.getProjectPhaseId())) {
                projectPlanDetailsVo.setCanCopy(true);
            }
            boolean isMember = projectMemberService.isProjectMember(projectId, commonService.thisUserAccount());
            boolean isOperable = projectInfoService.isProjectOperable(projectId);

            if (isMember && isOperable) {
                if (StringUtils.isNotBlank(projectPlanDetailsVo.getExecuteUserAccount()) && projectPlanDetailsVo.getBisStart()) {
                    ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetailsVo.getProjectPhaseId());
                    if (projectPhase != null) {
                        projectPlanDetailsVo.setCanReplay(projectPhase.getBisCanReturn());
                    }
                }
            }
        }
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos);
        return bootstrapTableVo;
    }


    public ProjectPlanDetailsVo getPlanDetailListByProjectPlanDetailId(Integer projectPlanDetailId) {
        ProjectPlanDetails projectPlanDetailsById = projectPlanDetailsDao.getProjectPlanDetailsById(projectPlanDetailId);
        List<ProjectPlanDetails> projectPlanDetails = Lists.newArrayList();
        projectPlanDetails.add(projectPlanDetailsById);
        if (CollectionUtils.isEmpty(projectPlanDetails)) return null;
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = getProjectPlanDetailsVos(projectPlanDetails, false);

        //获取当前人该阶段下待处理的任务
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProjectId(projectPlanDetailsById.getProjectId());
        projectResponsibilityDto.setPlanId(projectPlanDetailsById.getPlanId());
        projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
        projectResponsibilityDto.setUserAccount(processControllerComponent.getThisUser());
        List<ProjectResponsibilityDto> projectTaskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);

        //获取该阶段下正在运行的待审批任务
        List<String> processInsIds = Lists.newArrayList();
        for (ProjectPlanDetails projectPlanDetail : projectPlanDetails) {
            if (StringUtils.equals(projectPlanDetail.getStatus(), SysProjectEnum.RUNING.getValue())) {
                if (!StringUtils.equals(projectPlanDetail.getProcessInsId(), "0")) {
                    processInsIds.add(projectPlanDetail.getProcessInsId());
                }
            }
        }
        List<ActivitiTaskNodeDto> activitiTaskNodeDtos = null;
        try {
            activitiTaskNodeDtos = bpmRpcActivitiProcessManageService.queryProcessCurrentTask(processInsIds.toArray(new String[processInsIds.size()]));
        } catch (BpmException e) {
            logger.error("计划任务获取流程任务异常", e);
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetailsById.getProjectId());
        ProjectPhase sceneExplorePhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
        ProjectPhase sceneExploreChildPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_SCENE_EXPLORE_EXAMINE);
        ProjectPhase caseStudyChildPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
        List<Integer> phaseIds = Lists.newArrayList(sceneExploreChildPhase.getId(), caseStudyChildPhase.getId());
        List<Integer> phaseFullIds = Lists.newArrayList(phaseIds);
        if (sceneExplorePhase != null)
            phaseFullIds.add(sceneExplorePhase.getId());
        String viewUrl = String.format("/%s/ProjectTask/projectTaskDetailsById?planDetailsId=", applicationConstant.getAppKey());
        //判断任务是否结束，如果结束只能查看详情
        for (ProjectPlanDetailsVo projectPlanDetailsVo : projectPlanDetailsVos) {
            //任务在进行中，则需判断任务是在提交还是审批的状态
            // 如果任务是在审批或完成状态可查看详情
            //如果为待提交状态 当前人与任务执行人相同 可提交任务
            //如果为待审批状态 当前人与审批人相同 可审批该任务
            SysProjectEnum sysProjectEnum = SysProjectEnum.getEnumByName(SysProjectEnum.getNameByKey(projectPlanDetailsVo.getStatus()));
            switch (sysProjectEnum) {
                case NONE:
                case CLOSE://业务异常关闭流程错误更新状态的数据
                case RUNING:
                    if (StringUtils.equals(projectPlanDetailsVo.getProcessInsId(), "0")) {
                        if (CollectionUtils.isNotEmpty(projectTaskList)) {
                            for (ProjectResponsibilityDto responsibilityDto : projectTaskList) {
                                if (projectPlanDetailsVo.getId().intValue() == responsibilityDto.getPlanDetailsId().intValue()) {
                                    if (responsibilityDto.getUserAccount().contains(commonService.thisUserAccount())) {
                                        String executeUrl = String.format(responsibilityDto.getUrl().contains("?") ? "%s&responsibilityId=%s" : "%s?responsibilityId=%s", responsibilityDto.getUrl(), responsibilityDto.getId());
                                        projectPlanDetailsVo.setExcuteUrl(executeUrl);

                                        //设置粘贴
                                        if (phaseFullIds.contains(projectPlanDetailsVo.getProjectPhaseId())) {
                                            projectPlanDetailsVo.setCanPaste(true);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (CollectionUtils.isNotEmpty(activitiTaskNodeDtos)) {
                            String processInsId = projectPlanDetailsVo.getProcessInsId();
                            String taskId = new String();
                            //根据情况获取对应的审批节点数据activitiTaskNodeDto
                            ActivitiTaskNodeDto activitiTaskNodeDto = null;
                            for (ActivitiTaskNodeDto taskNodeDto : activitiTaskNodeDtos) {
                                if (StringUtils.equals(taskNodeDto.getProcessInstanceId(), processInsId)) {
                                    activitiTaskNodeDto = taskNodeDto;
                                    taskId = taskNodeDto.getTaskId();
                                }
                            }
                            if (activitiTaskNodeDto != null) {
                                BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(Integer.parseInt(activitiTaskNodeDto.getBusinessKey()));
                                String approvalUrl = boxReDto.getProcessApprovalUrl();
                                if (StringUtils.equals(ProcessActivityEnum.EDIT.getValue(), activitiTaskNodeDto.getTaskKey())) {
                                    approvalUrl = boxReDto.getProcessEditUrl();
                                }
                                approvalUrl = String.format("/%s%s?boxId=%s&processInsId=%s&taskId=%s", boxReDto.getGroupName(), approvalUrl, boxReDto.getId(), processInsId, taskId);
                                if (activitiTaskNodeDto.getUsers().contains(commonService.thisUserAccount())) {
                                    projectPlanDetailsVo.setExcuteUrl(approvalUrl);
                                }
                            }
                        }
                    }
                    break;
            }
            if (projectPlanDetailsVo.getBisLastLayer() == Boolean.TRUE)
                projectPlanDetailsVo.setDisplayUrl(String.format("%s%s", viewUrl, projectPlanDetailsVo.getId()));
            //设置复制
            if (projectPlanDetailsVo.getBisLastLayer() == Boolean.TRUE && phaseIds.contains(projectPlanDetailsVo.getProjectPhaseId())) {
                projectPlanDetailsVo.setCanCopy(true);
            }
            boolean isMember = projectMemberService.isProjectMember(projectPlanDetailsById.getProjectId(), commonService.thisUserAccount());
            boolean isOperable = projectInfoService.isProjectOperable(projectPlanDetailsById.getProjectId());

            if (isMember && isOperable) {
                if (StringUtils.isNotBlank(projectPlanDetailsVo.getExecuteUserAccount()) && projectPlanDetailsVo.getBisStart()) {
                    ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetailsVo.getProjectPhaseId());
                    if (projectPhase != null) {
                        projectPlanDetailsVo.setCanReplay(projectPhase.getBisCanReturn());
                    }
                }
            }
        }

        return projectPlanDetailsVos.get(0);

    }

    /**
     * 是否所有计划明细任务都已完成
     *
     * @param planId
     * @return
     */
    public boolean isAllPlanDetailsFinish(Integer planId) {
        ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
        projectPlanDetailsWhere.setPlanId(planId);
        projectPlanDetailsWhere.setStatus(ProcessStatusEnum.RUN.getValue());
        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
        return CollectionUtils.isEmpty(projectPlanDetailsList);
    }

    /**
     * 更新
     *
     * @param projectPlanDetails
     * @return
     */
    public boolean updateProjectPlanDetails(ProjectPlanDetails projectPlanDetails) {
        return projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
    }

    public ProjectPlanDetailsVo getProjectPlanDetailsVo(ProjectPlanDetails projectPlanDetails) {
        ProjectPlanDetailsVo projectPlanDetailsVo = new ProjectPlanDetailsVo();
        BeanUtils.copyProperties(projectPlanDetails, projectPlanDetailsVo);
        if (StringUtils.isNotBlank(projectPlanDetails.getExecuteUserAccount())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(projectPlanDetails.getExecuteUserAccount());
            projectPlanDetailsVo.setExecuteUserName(sysUser == null ? "" : sysUser.getUserName());
            projectPlanDetailsVo.setNodeName(String.format("%s(%s)",projectPlanDetails.getProjectPhaseName(),sysUser.getUserName()));
        }else{
            projectPlanDetailsVo.setNodeName(projectPlanDetails.getProjectPhaseName());
        }
        if (projectPlanDetails.getExecuteDepartmentId() != null) {
            SysDepartmentDto departmentById = erpRpcDepartmentService.getDepartmentById(projectPlanDetails.getExecuteDepartmentId());
            projectPlanDetailsVo.setExecuteDepartmentName(departmentById == null ? "" : departmentById.getName());
        }
        if (projectPlanDetails.getPid() > 0) {
            projectPlanDetailsVo.setSorting(projectPlanDetails.getPid() * 100 + projectPlanDetails.getSorting());
            projectPlanDetailsVo.set_parentId(projectPlanDetails.getPid().toString());
        } else {
            projectPlanDetailsVo.setSorting(1000 + projectPlanDetails.getSorting());
        }

        return projectPlanDetailsVo;
    }

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsVos(List<ProjectPlanDetails> projectPlanDetails, Boolean bisAttachment) {
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
            List<SysAttachmentDto> bidSysAttachmentDtos = new ArrayList<>();
            if (bisAttachment) {
                List<Integer> detailsIds = LangUtils.transform(projectPlanDetails, o -> o.getId());
                bidSysAttachmentDtos = baseAttachmentService.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(ProjectPlanDetails.class), detailsIds);
            }
            for (ProjectPlanDetails item : projectPlanDetails) {
                if (item.getBisEnable() == false) {
                    continue;
                }
                ProjectPlanDetailsVo projectPlanDetailsVo = getProjectPlanDetailsVo(item);
                if (bidSysAttachmentDtos != null && bidSysAttachmentDtos.size() > 0) {
                    List<SysAttachmentDto> filter = LangUtils.filter(bidSysAttachmentDtos, o -> {
                        return o.getTableId().intValue() == item.getId().intValue();
                    });
                    if (CollectionUtils.isNotEmpty(filter)) {
                        List<KeyValueDto> transform = LangUtils.transform(filter, o -> {
                            KeyValueDto keyValueDto = new KeyValueDto();
                            keyValueDto.setKey(o.getId().toString());
                            keyValueDto.setValue(String.format("%s(%s)", o.getFileName(), o.getFileSize()));
                            keyValueDto.setExplain(o.getFileExtension());
                            return keyValueDto;
                        });
                        projectPlanDetailsVo.setTasks(transform);
                    }
                }
                projectPlanDetailsVos.add(projectPlanDetailsVo);
            }
        }
        return projectPlanDetailsVos;
    }

    public List<ProjectPlanDetailsVo> getProjectDetailsTask(ProjectPlanDetails projectPlanDetails) {
        ProjectPlanDetails projectPlanDetailsWhere = new ProjectPlanDetails();
        projectPlanDetailsWhere.setPid(projectPlanDetails.getPid());
        projectPlanDetailsWhere.setExecuteUserAccount(projectPlanDetails.getExecuteUserAccount());
        List<ProjectPlanDetails> listObject = projectPlanDetailsDao.getListObject(projectPlanDetailsWhere);
        List<ProjectPlanDetailsVo> transform = LangUtils.transform(listObject, o -> getProjectPlanDetailsVo(o));
        return transform;
    }

    public List<ProjectPlanDetails> getProjectDetails(ProjectPlanDetails projectPlanDetails) {
        List<ProjectPlanDetails> listObject = projectPlanDetailsDao.getListObject(projectPlanDetails);
        return listObject;
    }

    public void deleteProjectPlanDetails(Integer id) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(id);
        deleteProjectPlanDetails(projectPlanDetails);
    }

    public void deleteProjectPlanDetails(ProjectPlanDetails projectPlanDetails) {
        if (projectPlanDetails == null) return;
        try {
            if (ProcessStatusEnum.RUN.getValue().equals(projectPlanDetails.getStatus())) {
                bpmRpcProjectTaskService.deleteProjectTaskByPlanDetailsId(applicationConstant.getAppKey(), projectPlanDetails.getId());
                if (StringUtils.isNotBlank(projectPlanDetails.getProcessInsId()) && !projectPlanDetails.getProcessInsId().equals("0"))
                    bpmRpcActivitiProcessManageService.closeProcess(projectPlanDetails.getProcessInsId());
            }
            projectPlanDetailsDao.deleteProjectPlanDetails(projectPlanDetails.getId());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 获取任务及子项任务数据
     *
     * @param planDetailsId
     * @return
     */
    public List<ProjectPlanDetails> getPlanDetailsListRecursion(Integer planDetailsId, boolean containThis) {
        List<ProjectPlanDetails> list = Lists.newArrayList();
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(planDetailsId);
        if (projectPlanDetails != null) {
            if (containThis)
                list.add(projectPlanDetails);
            getPlanDetailsSubList(projectPlanDetails.getId(), list);
        }
        return list;
    }

    /**
     * 递归获取子项任务
     *
     * @param pid
     * @param list
     */
    private void getPlanDetailsSubList(Integer pid, List<ProjectPlanDetails> list) {
        if (pid == null) return;
        if (list == null) return;
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsDao.getProjectPlanDetailsByPid(pid);
        if (CollectionUtils.isEmpty(planDetailsList)) return;
        for (ProjectPlanDetails projectPlanDetails : planDetailsList) {
            list.add(projectPlanDetails);
            getPlanDetailsSubList(projectPlanDetails.getId(), list);
        }
    }

    /**
     * 判断该计划任务下的所有任务是否都已完成
     *
     * @param planDetailsId
     * @return
     */
    public boolean isAllFinish(Integer planDetailsId) {
        List<ProjectPlanDetails> planDetailsList = getPlanDetailsListRecursion(planDetailsId, false);
        if (CollectionUtils.isEmpty(planDetailsList)) return true;
        for (ProjectPlanDetails projectPlanDetails : planDetailsList) {
            if (projectPlanDetails.getBisLastLayer() == Boolean.TRUE && !StringUtils.equals(projectPlanDetails.getStatus(), ProcessStatusEnum.FINISH.getValue()))
                return false;
        }
        return true;
    }

    /**
     * 获取子项计划任务
     *
     * @param planDetailsId
     * @return
     */
    public List<ProjectPlanDetails> getChildrenPlanDetailsList(Integer planDetailsId) {
        return projectPlanDetailsDao.getProjectPlanDetailsByPid(planDetailsId);
    }

    public List<ProjectPlanDetails> getProjectPlanDetailsByIds(List<Integer> ids) {
        return projectPlanDetailsDao.getProjectPlanDetailsByIds(ids);
    }

    /**
     * 删除阶段下的所有任务
     *
     * @param planId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePlanDetailsByPlanId(Integer planId) {
        //删除任务的同时，需删除待提交任务，需删除待提交的流程
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
        if (CollectionUtils.isNotEmpty(planDetailsList)) {
            for (ProjectPlanDetails projectPlanDetails : planDetailsList) {
                deleteProjectPlanDetails(projectPlanDetails);
            }
        }
    }

    /**
     * 重启任务
     *
     * @param planDetailsId
     * @param formData
     */
    @Transactional(rollbackFor = Exception.class)
    public ProjectPlanDetailsVo replyProjectPlanDetails(Integer planDetailsId, String formData) throws BusinessException, BpmException {
        //1.更新任务状态 记录重启原因 2.添加新的待提交任务
        ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsById(planDetailsId);
        if (projectPlanDetails == null) return null;
        if (!StringUtils.equals(projectPlanDetails.getStatus(), ProcessStatusEnum.FINISH.getValue()))
            throw new BusinessException("已完成的任务才允许重启");

        projectPlanDetails.setStatus(ProcessStatusEnum.RUN.getValue());
        projectPlanDetails.setTaskSubmitTime(null);
        projectPlanDetails.setBisStart(false);
        projectPlanDetails.setProcessInsId("0");
        projectPlanDetails.setActualHours(null);
        projectPlanDetails.setBisRestart(true);
        projectPlanDetailsDao.updateProjectPlanDetailsAndNull(projectPlanDetails);

        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId());
        projectPlanService.saveProjectPlanDetailsResponsibility(projectPlanDetails, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);

        //新增一条重启记录
        ProjectTaskReturnRecord projectTaskReturnRecord = JSON.parseObject(formData, ProjectTaskReturnRecord.class);
        projectTaskReturnRecord.setProjectId(projectPlanDetails.getProjectId());
        projectTaskReturnRecord.setPlanDetailsId(planDetailsId);
        projectTaskReturnRecord.setCreator(commonService.thisUserAccount());
        projectTaskReturnRecord.setReturnPerson(commonService.thisUserAccount());
        projectTaskReturnRecord.setReturnTime(new Date());
        Integer returnId = projectTaskReturnRecordDao.addProjectTaskReturnRecord(projectTaskReturnRecord);
        //修改附件tableId
        SysAttachmentDto queryParam = new SysAttachmentDto();
        queryParam.setTableName("tb_project_task_return_record");
        queryParam.setTableId(0);
        queryParam.setFieldsName(String.valueOf(planDetailsId));
        queryParam.setAppKey(applicationConstant.getAppKey());
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(returnId);
        erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
        return getProjectPlanDetailsVo(projectPlanDetails);
    }

    /**
     * 调整任务责任人
     *
     * @param planDetailsId
     * @param newExecuteUser
     */
    @Transactional(rollbackFor = Exception.class)
    public ProjectPlanDetailsVo updateExecuteUser(Integer planDetailsId, String newExecuteUser) throws BusinessException {
        ProjectPlanDetails projectPlanDetails = getProjectPlanDetailsById(planDetailsId);
        if (projectPlanDetails == null) return null;
        if (StringUtils.isBlank(newExecuteUser))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (projectPlanDetails.getBisStart())
            throw new BusinessException("只允许未提交的数据变更责任人");
        projectPlanDetails.setExecuteUserAccount(newExecuteUser);
        SysUserDto sysUser = erpRpcUserService.getSysUser(newExecuteUser);
        projectPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
        projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);

        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
        projectResponsibilityDto.setProjectId(projectPlanDetails.getProjectId());
        projectResponsibilityDto.setPlanId(projectPlanDetails.getPlanId());
        projectResponsibilityDto.setPlanDetailsId(projectPlanDetails.getId());
        ProjectResponsibilityDto projectTask = bpmRpcProjectTaskService.getProjectTask(projectResponsibilityDto);
        if (projectTask != null) {
            projectTask.setUserAccount(newExecuteUser);
            bpmRpcProjectTaskService.updateProjectTask(projectTask);
        }


        //当任务为现场查勘或案例调查时特殊处理
        ProjectPhase projectPhaseExplore = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_SCENE_EXPLORE_EXAMINE);
        ProjectPhase projectPhaseStudy = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
        if (projectPlanDetails.getProjectPhaseId().equals(projectPhaseExplore.getId()) || projectPlanDetails.getProjectPhaseId().equals(projectPhaseStudy.getId())) {
            //将task任务也移交过去
            ProjectPlanDetails parentDetail = projectPlanDetailsDao.getProjectPlanDetailsById(projectPlanDetails.getPid());
            if (parentDetail != null) {
                parentDetail.setExecuteUserAccount(newExecuteUser);
                parentDetail.setExecuteDepartmentId(sysUser.getDepartmentId());
                projectPlanDetailsDao.updateProjectPlanDetails(parentDetail);
            }
            List<SurveyExamineTask> taskList = surveyExamineTaskService.getTaskListByPlanDetailsId(projectPlanDetails.getPid());
            if (CollectionUtils.isNotEmpty(taskList)) {
                for (SurveyExamineTask surveyExamineTask : taskList) {
                    surveyExamineTask.setUserAccount(newExecuteUser);
                    surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
                }
            }
        }
        return getProjectPlanDetailsVo(projectPlanDetails);
    }

    /**
     * 批量调整任务责任人
     *
     * @param planDetailsIds
     * @param newExecuteUser
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateExecuteUser(List<Integer> planDetailsIds, String newExecuteUser) throws BusinessException {
        List<ProjectPlanDetails> projectPlanDetailsByIds = this.getProjectPlanDetailsByIds(planDetailsIds);
        for (ProjectPlanDetails projectPlanDetails: projectPlanDetailsByIds) {
            if (projectPlanDetails == null) return;
            if (StringUtils.isBlank(newExecuteUser))
                throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
            if (projectPlanDetails.getBisStart())
                continue;
            projectPlanDetails.setExecuteUserAccount(newExecuteUser);
            SysUserDto sysUser = erpRpcUserService.getSysUser(newExecuteUser);
            projectPlanDetails.setExecuteDepartmentId(sysUser.getDepartmentId());
            projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);

            ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
            projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
            projectResponsibilityDto.setProjectId(projectPlanDetails.getProjectId());
            projectResponsibilityDto.setPlanId(projectPlanDetails.getPlanId());
            projectResponsibilityDto.setPlanDetailsId(projectPlanDetails.getId());
            ProjectResponsibilityDto projectTask = bpmRpcProjectTaskService.getProjectTask(projectResponsibilityDto);
            if (projectTask != null) {
                projectTask.setUserAccount(newExecuteUser);
                bpmRpcProjectTaskService.updateProjectTask(projectTask);
            }


            //当任务为现场查勘或案例调查时特殊处理
            ProjectPhase projectPhaseExplore = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_SCENE_EXPLORE_EXAMINE);
            ProjectPhase projectPhaseStudy = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
            if (projectPlanDetails.getProjectPhaseId().equals(projectPhaseExplore.getId()) || projectPlanDetails.getProjectPhaseId().equals(projectPhaseStudy.getId())) {
                //将task任务也移交过去
                ProjectPlanDetails parentDetail = projectPlanDetailsDao.getProjectPlanDetailsById(projectPlanDetails.getPid());
                if (parentDetail != null) {
                    parentDetail.setExecuteUserAccount(newExecuteUser);
                    parentDetail.setExecuteDepartmentId(sysUser.getDepartmentId());
                    projectPlanDetailsDao.updateProjectPlanDetails(parentDetail);
                }
                List<SurveyExamineTask> taskList = surveyExamineTaskService.getTaskListByPlanDetailsId(projectPlanDetails.getPid());
                if (CollectionUtils.isNotEmpty(taskList)) {
                    for (SurveyExamineTask surveyExamineTask : taskList) {
                        surveyExamineTask.setUserAccount(newExecuteUser);
                        surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
                    }
                }
            }
        }

    }

    /**
     * 任务粘贴
     *
     * @param copyPlanDetailsId
     * @param pastePlanDetailsId
     */
    public void taskPaste(Integer copyPlanDetailsId, Integer pastePlanDetailsId) throws Exception {
        //1.被复制的任务必须是叶子节点 2.目前只支持资产清查，现场查勘案例调查可被复制
        //3.被粘贴的任务必须是还未开始的任务 4.被粘贴的任务必须与被复制数据的工作事项一致
        //5.被粘贴的任务也必须是叶子节点
        if (copyPlanDetailsId == null || pastePlanDetailsId == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ProjectPlanDetails copyPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(copyPlanDetailsId);
        ProjectPlanDetails pastePlanDetails = projectPlanDetailsDao.getProjectPlanDetailsById(pastePlanDetailsId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(copyPlanDetails.getProjectId());
        ProjectPhase sceneExplorePhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, projectInfo.getProjectCategoryId());
        ProjectPhase caseStudyChildPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.COMMON_CASE_STUDY_EXAMINE);
        List<Integer> commonPhaseIds = Lists.newArrayList(sceneExplorePhase.getId(), caseStudyChildPhase.getId());
        //現場查勘案例調查
        if (commonPhaseIds.contains(copyPlanDetails.getProjectPhaseId())) {
            if (commonPhaseIds.contains(pastePlanDetails.getProjectPhaseId())) {
                basicApplyTransferService.copyForExamine(copyPlanDetails.getId(), pastePlanDetails.getId());
            }
        }
    }

    /**
     * 根据计划编号，取得计划所有的上子级计划总数
     *
     * @param planId
     * @return
     */
    public Integer getTotalPlans(Integer planId) {
        return projectPlanDetailsDao.getTotalPlans(planId);
    }
}
