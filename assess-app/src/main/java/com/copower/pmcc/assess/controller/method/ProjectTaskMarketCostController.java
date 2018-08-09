package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/8 11:38
 * @Description:市场成本法临时控制器
 */
@RequestMapping(value = "/projectTaskMarketCost")
@Controller
public class ProjectTaskMarketCostController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Autowired
    private MdMarketCostService mdMarketCostService;

    @RequestMapping(value = "/marketCostIndex", name = "成本法", method = RequestMethod.GET)
    public ModelAndView view() {
        String view = "/task/scheme/taskCostIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        final int projectPlanDetailId = 9121;
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanDetailId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        projectPlanDetails.setProjectPhaseName("成本法");
        modelAndView.addObject("projectInfo", projectInfoService.getProjectInfoVoView(projectInfo));
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getBaseDicTree", name = "获取", method = RequestMethod.GET)
    public BootstrapTableVo getBaseDicTree() {
        BootstrapTableVo vo = mdMarketCostService.getBaseDicTree();
        return vo;
    }
}
