package com.copower.pmcc.assess.service.project.taks.assist.financialClaim;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
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
@WorkFlowAnnotation(desc = "债权评估综合表单")
public class financialClaimBaseAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/financialClaim/financialClaimIndex", "", 0, "0", "");
        modelAndView.addObject("projectDetailsTask", projectPlanFinancialClaimService.getProjectDetailsTask(projectPlanDetails));
        ProjectPlanDetails planDetailsParent = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("planDetailsParent", planDetailsParent);
        modelAndView.addObject("boxCnName", planDetailsParent.getProjectPhaseName());
        modelAndView.addObject("currUserAccount", processControllerComponent.getThisUser());
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/financialClaim/financialClaimIndexApproval", processInsId, boxId, taskId, agentUserAccount);
        ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getProjectPlanDetailsVo(projectPlanDetails);
        modelAndView.addObject("projectPlanDetailsVo", projectPlanDetailsVo);
        ProjectPlanDetails planDetailsParent = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("planDetailsParent", planDetailsParent);
        modelAndView.addObject("boxCnName", planDetailsParent.getProjectPhaseName());
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/financialClaim/financialClaimEdit", processInsId, boxId, taskId, agentUserAccount);
        ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getProjectPlanDetailsVo(projectPlanDetails);
        modelAndView.addObject("projectPlanDetailsVo", projectPlanDetailsVo);
        ProjectPlanDetails planDetailsParent = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("planDetailsParent", planDetailsParent);
        modelAndView.addObject("boxCnName", planDetailsParent.getProjectPhaseName());
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/financialClaim/financialClaimIndexApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        ProjectPlanDetailsVo projectPlanDetailsVo = projectPlanDetailsService.getProjectPlanDetailsVo(projectPlanDetails);
        modelAndView.addObject("projectPlanDetailsVo", projectPlanDetailsVo);
        ProjectPlanDetails planDetailsParent = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetails.getPid());
        modelAndView.addObject("planDetailsParent", planDetailsParent);
        modelAndView.addObject("boxCnName", planDetailsParent.getProjectPhaseName());
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
