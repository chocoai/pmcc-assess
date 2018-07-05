package com.copower.pmcc.assess.service.project.plan.assist;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/30
 * @time: 18:26
 */
@Component
@WorkFlowAnnotation(desc = "债权工作实施计划")
public class ProjectPlanFinancialClaimAssist implements ProjectPlanInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/financialClaim/planIndex", "", 0, "-1", "");
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/financialClaim/planApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/financialClaim/planIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/financialClaim/planApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }
}
