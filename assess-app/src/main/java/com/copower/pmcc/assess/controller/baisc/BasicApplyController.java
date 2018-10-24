package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kings on 2018-10-24.
 * 案例基础数据
 */
@Controller
@RequestMapping("/basicApply")
public class BasicApplyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private CrmCustomerService crmCustomerService;
    @Autowired
    private BasicApplyService basicApplyService;


    @RequestMapping(value = "/basicApplyIndex", name = "案例基础数据 初始", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", "0", 0, "0", "");
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        //可选的执业部门
        modelAndView.addObject("departmentAssess", erpRpcDepartmentService.getDepartmentAssess());
        return modelAndView;
    }

    @RequestMapping(value = "/basicApplyEdit", name = "案例基础数据 修改页面")
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @RequestMapping(value = "/basicApplyApproval", name = "案例基础数据 审批页面")
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @RequestMapping(value = "/basicApplyDetails", name = "案例基础数据 详情页面")
    public ModelAndView basicApplyDetails(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyDetails", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }


}
