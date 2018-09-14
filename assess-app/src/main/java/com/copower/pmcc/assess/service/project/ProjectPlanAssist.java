package com.copower.pmcc.assess.service.project;

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
@WorkFlowAnnotation(desc = "计划编制(默认)")
public class ProjectPlanAssist implements ProjectPlanInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/planIndex", "", 0, "-1", "");
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/planApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/planIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/planApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }
}
