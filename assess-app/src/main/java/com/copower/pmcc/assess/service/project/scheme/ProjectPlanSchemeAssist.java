package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:45
 */
@Component
@WorkFlowAnnotation(desc = "评估方案计划")
public class ProjectPlanSchemeAssist implements ProjectPlanInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/planSchemeIndex", "", 0, "-1", "");
        modelAndView.addObject("projectPlan", projectPlan);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/planSchemeApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/planSchemeIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/planSchemeApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }
}
