package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    private ErpAreaService erpAreaService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private PublicBasicService publicBasicService;
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

    @ResponseBody
    @RequestMapping(value = "/basicApplySubmit", name = "案例基础数据 提交", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(String formData, Boolean bisNextUser) {
        try {
            publicBasicService.saveBasic(formData,bisNextUser);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/basicApplyEdit", name = "案例基础数据 修改页面")
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @RequestMapping(value = "/basicApplyApproval", name = "案例基础数据 审批页面",method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        try {
            this.setViewParam(processInsId,modelAndView);
        } catch (Exception e1) {
            logger.error(e1.getMessage(),e1);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/basicApplyDetails", name = "案例基础数据 详情页面",method = RequestMethod.GET)
    public ModelAndView basicApplyDetails(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicApplyDetails", processInsId, boxId, taskId, agentUserAccount);
        try {
            this.setViewParam(processInsId,modelAndView);
        } catch (Exception e1) {
            logger.error(e1.getMessage(),e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/projectApprovalSubmit", name = "审批页面 提交")
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto){
        try {
            publicBasicService.approvalAndWrite(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(),e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    /**
     * 设置参数
     * @param processInsId
     * @param modelAndView
     * @throws Exception
     */
    private void setViewParam(String processInsId,ModelAndView modelAndView)throws Exception{
        modelAndView.addObject("processInsId",processInsId);
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
        if (basicApply != null){
            modelAndView.addObject("basicApply",basicApply);
            modelAndView.addObject("basicEstate",publicBasicService.getByAppIdBasicEstate(basicApply.getId()));
        }
    }


}
