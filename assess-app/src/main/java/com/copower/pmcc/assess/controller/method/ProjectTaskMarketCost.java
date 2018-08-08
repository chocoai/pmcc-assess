package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/8/8 11:38
 * @Description:市场成本法临时控制器
 */
@RequestMapping(value = "/projectTaskMarketCost")
@Controller
public class ProjectTaskMarketCost {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @RequestMapping(value = "/marketCostIndex", name = "成本法", method = RequestMethod.GET)
    public ModelAndView view() {
        String view = "/task/scheme/taskCostIndex" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        final int projectPlanDetailId = 9121;
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetailId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        projectPlanDetails.setProjectPhaseName("成本法");
        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoVoView(projectInfo));
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        return modelAndView;
    }
}
