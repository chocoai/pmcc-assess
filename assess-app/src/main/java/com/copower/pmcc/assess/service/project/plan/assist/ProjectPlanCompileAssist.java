package com.copower.pmcc.assess.service.project.plan.assist;

import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanCompileService;
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
@WorkFlowAnnotation(desc = "报告编写计划")
public class ProjectPlanCompileAssist implements ProjectPlanInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanCompileService projectPlanComplieService;


    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/compile/planCompileIndex", "", 0, "-1", "");
//        projectPlanSurveyService.getProjectPlanSurvey(modelAndView,projectPlan);
        projectPlanComplieService.getInitialize(modelAndView, projectPlan);
        return modelAndView;
    }


    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/compile/planCompileApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/compile/planCompileIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/plan/compile/planCompileApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

}
