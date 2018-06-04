package com.copower.pmcc.assess.service.project.taks.assist.financialClaim;

import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 16:30
 */
@Component
public class financialClaimBaseAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/task/financialClaimIndex", "", 0, "0", "");
        modelAndView.addObject("projectDetailsTask", projectPlanFinancialClaimService.getProjectDetailsTask(projectPlanDetails));
        ProjectPlanDetails projectPlanDetails1 = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("boxCnName", projectPlanDetails1.getProjectPhaseName());
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/task/financialClaimApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("projectDetailsTask", projectPlanFinancialClaimService.getProjectDetailsTask(projectPlanDetails));
        ProjectPlanDetails projectPlanDetails1 = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("boxCnName", projectPlanDetails1.getProjectPhaseName());
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/task/financialClaimIndex", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("projectDetailsTask", projectPlanFinancialClaimService.getProjectDetailsTask(projectPlanDetails));
        ProjectPlanDetails projectPlanDetails1 = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("boxCnName", projectPlanDetails1.getProjectPhaseName());
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/task/financialClaimApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        modelAndView.addObject("projectDetailsTask", projectPlanFinancialClaimService.getProjectDetailsTask(projectPlanDetails));
        ProjectPlanDetails projectPlanDetails1 = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("boxCnName", projectPlanDetails1.getProjectPhaseName());
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
