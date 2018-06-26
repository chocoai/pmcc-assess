package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanTaskAllDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectTaskAllBackDto;
import com.copower.pmcc.assess.service.base.BaseParameterServcie;
import com.copower.pmcc.assess.service.event.project.ProjectPlanTaskAllEvent;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 16:32
 */
@Service
public class ProjectTaskAllService {
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanTaskAllDao projectPlanTaskAllDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private BaseParameterServcie baseParameterServcie;

    @Transactional(rollbackFor = Exception.class)
    public void startTaskAllApproval(String conclusion, Integer planId, List<ProjectTaskAllBackDto> taskAllBackDtos, String appointUserAccount) throws BusinessException {
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        if (conclusion.toLowerCase().equals(TaskHandleStateEnum.AGREE.getValue().toLowerCase())) {
            //发起流程
            String reviewBoxName = projectWorkStage.getReviewBoxName();//复核模型
            ProjectPlanTaskAll projectPlanTaskAll = new ProjectPlanTaskAll();
            projectPlanTaskAll.setCreator(processControllerComponent.getThisUser());
            projectPlanTaskAll.setProjectId(projectPlan.getProjectId());
            projectPlanTaskAll.setProjectPlanId(projectPlan.getId());
            projectPlanTaskAll.setProjectWorkStageId(projectPlan.getWorkStageId());
            projectPlanTaskAllDao.addObject(projectPlanTaskAll);
            String folio = String.format("%s【整体复核】%s", projectWorkStage.getWorkStageName(), projectInfo.getProjectName());
            //发起流程
            Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(reviewBoxName);
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);

            processInfo.setProjectId(projectPlan.getProjectId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName("tb_project_plan_task_all");
            processInfo.setTableId(projectPlanTaskAll.getId());
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setWorkStage(projectWorkStage.getWorkStageName());
            processInfo.setProcessEventExecutorName(ProjectPlanTaskAllEvent.class.getSimpleName());
            processInfo.setWorkStageId(projectWorkStage.getId());
            try {
                processUserDto = processControllerComponent.processStart(processInfo, appointUserAccount, false);//发起流程，并返回流程实例编号
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }
            projectPlanTaskAll.setStatus(ProcessStatusEnum.RUN.getValue());
            projectPlanTaskAll.setProcessInsId(processUserDto.getProcessInsId());
            projectPlanTaskAllDao.updateObject(projectPlanTaskAll);
            //更新任务标识
            bpmRpcProjectTaskService.deleteProjectTaskByPlanId(planId);
            if (CollectionUtils.isNotEmpty(processUserDto.getSkipActivity())) {
                try {
                    processControllerComponent.autoProcessSubmitLoopTaskNodeArg(processInfo, processUserDto);
                } catch (BpmException e) {
                    throw new BusinessException("跳过节点自动提交失败");
                }
            }
        } else {
            //退回相应责任人，重新提交成果

            if (CollectionUtils.isEmpty(taskAllBackDtos)) {
                throw new BusinessException("至少选择一个要重新提交成果的事项");
            }
            //更新任务标识

            bpmRpcProjectTaskService.deleteProjectTaskByPlanId(planId);

            List<Integer> integers = LangUtils.transform(taskAllBackDtos, o -> o.getDetailsId());

            List<ProjectPlanDetails> projectPlanDetailsByPlanId = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
            List<ProjectPlanDetails> projectPlanDetails = LangUtils.filter(projectPlanDetailsByPlanId, o -> {
                return integers.contains(o.getId());
            });

            //加入新任务
            for (ProjectPlanDetails item : projectPlanDetails) {

                List<ProjectTaskAllBackDto> filter = LangUtils.filter(taskAllBackDtos, o -> {
                    return o.getDetailsId().intValue() == item.getId().intValue();
                });

                if (CollectionUtils.isNotEmpty(filter)) {
                    item.setReturnDetailsReason(filter.get(0).getBackText());
                    projectPlanDetailsDao.updateProjectPlanDetails(item);
                }
                item.setReturnDetailsId(item.getId());
                item.setStatus(ProcessStatusEnum.RUN.getValue());
                projectPlanDetailsDao.addProjectPlanDetails(item);
                projectPlanService.saveProjectPlanDetailsResponsibility(item, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            }

        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void projectTaskAllEditSubmit(ApprovalModelDto approvalModelDto, String conclusion, Integer planId, List<ProjectTaskAllBackDto> taskAllBackDtos) throws BusinessException {
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
        if (conclusion.toLowerCase().equals(TaskHandleStateEnum.AGREE.getValue().toLowerCase())) {
            //如果同意，则提交流程
            approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
            approvalModelDto.setWorkStageId(projectPlan.getWorkStageId());
            approvalModelDto.setWorkPhaseId(0);
            approvalModelDto.setOpinions("返回修改");
            approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
            approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
            approvalModelDto.setCurrentStep(-1);
            try {
                processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            } catch (BpmException e) {
                throw new BusinessException(e.getMessage());
            }
        } else {
            //退回相应责任人，并关闭流程

            if (CollectionUtils.isEmpty(taskAllBackDtos)) {
                throw new BusinessException("至少选择一个要重新提交成果的事项");
            }

            //更新任务标识
            bpmRpcProjectTaskService.deleteProjectTaskByPlanId(planId);

            List<Integer> integers = LangUtils.transform(taskAllBackDtos, o -> o.getDetailsId());

            List<ProjectPlanDetails> projectPlanDetailsByPlanId = projectPlanDetailsDao.getProjectPlanDetailsByPlanId(planId);
            List<ProjectPlanDetails> projectPlanDetails = LangUtils.filter(projectPlanDetailsByPlanId, o -> {
                return integers.contains(o.getId());
            });

            //加入新任务
            for (ProjectPlanDetails item : projectPlanDetails) {
                item.setStatus(ProcessStatusEnum.RUN.getValue());
                item.setProcessInsId("0");
                item.setReturnDetailsId(item.getId());
                List<ProjectTaskAllBackDto> filter = LangUtils.filter(taskAllBackDtos, o -> {
                    return o.getDetailsId().intValue() == item.getId().intValue();
                });
                if (CollectionUtils.isNotEmpty(filter)) {
                    item.setReturnDetailsReason(filter.get(0).getBackText());
                }

                projectPlanDetailsDao.addProjectPlanDetails(item);
                projectPlanService.saveProjectPlanDetailsResponsibility(item, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
            }
            //关闭现有流程
            bpmRpcActivitiProcessManageService.closeProcess(approvalModelDto.getProcessInsId());
        }
    }

    public void projectTaskAllApprovalSubmit(ApprovalModelDto approvalModelDto) throws BusinessException {
        ProjectPlanTaskAll ProjectPlanTaskAll = getObjectByProcessInsId(approvalModelDto.getProcessInsId());
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(ProjectPlanTaskAll.getProjectPlanId());
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        approvalModelDto.setWorkStageId(projectWorkStage.getId());
        approvalModelDto.setWorkPhaseId(0);
        approvalModelDto.setWorkStage(projectWorkStage.getWorkStageName());
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    public ProjectPlanTaskAll getObjectByProcessInsId(String processInsId) {
        return projectPlanTaskAllDao.getObjectByProcessInsId(processInsId);
    }

    public ProjectPlanTaskAll getObjectById(Integer id) {
        return projectPlanTaskAllDao.getObjectById(id);
    }

    public void startTaskAllApproval(Integer planId) {
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());

        //阶段重启后的执行规则
        String parameterValues = baseParameterServcie.getParameterValues(AssessCacheConstant.PHASE_RESTART_EXECUTION_RULE);
        //如果阶段是重启，并且配置为重启的阶段不执行后续任务，则直接返回
        if (projectPlan.getBisRestart() && StringUtils.equals("0", parameterValues)) {
            return;
        }

        if (StringUtils.isNotBlank(projectWorkStage.getReviewBoxName())) {
            String workStageUserAccounts = projectWorkStageService.getWorkStageUserAccounts(projectWorkStage.getId(), projectPlan.getProjectId());
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
            projectPlanService.saveProjectPlanResponsibility(projectPlan, workStageUserAccounts, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.ALLTASK);
        } else {
            //将下阶段设置为可编辑计划
            try {
                projectPlanService.updatePlanStatus(planId);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
    }
}
