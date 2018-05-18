package com.copower.pmcc.assess.service.project.plan.assist;

import com.copower.pmcc.assess.common.DeclareRecordItems;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.proxy.face.ProjectPlanInterface;
import com.copower.pmcc.assess.service.project.SchemeAssistService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    private SchemeAssistService schemeAssistService;
    @Autowired
    private ControllerComponent serviceComponent;
    @Override
    public ModelAndView applyView(ProjectPlan projectPlan) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSchemeIndex", "", 0, "-1", "");
        modelAndView.addObject("bestusedescriptionList",schemeAssistService.dataBestUseDescriptionList());
        modelAndView.addObject("dataList",schemeAssistService.schemeareagroupauxiliary(projectPlan.getProjectId()+""));
        modelAndView.addObject("dataEvaluationMethod",schemeAssistService.evaluationmethod());
        modelAndView.addObject("dataEvaluationThink",schemeAssistService.thinkingList());
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSchemeApproval", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSchemeIndex", projectPlan.getProcessInsId(), boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/plan/planSchemeApproval", projectPlan.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }
}
