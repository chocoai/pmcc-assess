package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ChksCustomerAssessmentPlanDetail;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.ChksCustomerAssessmentPlanDetailService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zch on 2020-1-16.
 * 评估中对考核计划的Controller
 */
@RestController
@RequestMapping(value = "/chksCustomerAssessmentPlanDetail")
public class ChksCustomerAssessmentPlanDetailController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private ChksCustomerAssessmentPlanDetailService chksCustomerAssessmentPlanDetailService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @GetMapping(value = "/apply")
    public ModelAndView apply(){
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/chksCustomize/apply");
        return modelAndView;
    }

    @GetMapping(value = "/approval")
    public ModelAndView approval(){
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/chksCustomize/approval");
        return modelAndView;
    }

    @PostMapping(value = "/saveChksCustomerAssessmentPlanDetailAndUpdate")
    public HttpResult saveChksCustomerAssessmentPlanDetailAndUpdate(String formData, @RequestParam(name = "updateNull", defaultValue = "false") boolean updateNull) {
        try {
            ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = JSONObject.parseObject(formData, ChksCustomerAssessmentPlanDetail.class);
            chksCustomerAssessmentPlanDetailService.saveAndUpdateChksCustomerAssessmentPlanDetail(chksCustomerAssessmentPlanDetail, updateNull);
            return HttpResult.newCorrectResult(200, chksCustomerAssessmentPlanDetail);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteChksCustomerAssessmentPlanDetailById")
    public HttpResult deleteChksCustomerAssessmentPlanDetailById(String id) {
        try {
            chksCustomerAssessmentPlanDetailService.deleteChksCustomerAssessmentPlanDetailById(id);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getChksCustomerAssessmentPlanDetailById")
    public HttpResult getChksCustomerAssessmentPlanDetailById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, chksCustomerAssessmentPlanDetailService.getChksCustomerAssessmentPlanDetailById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(String formData) {
        ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = JSONObject.parseObject(formData, ChksCustomerAssessmentPlanDetail.class);
        return chksCustomerAssessmentPlanDetailService.getBootstrapTableVo(chksCustomerAssessmentPlanDetail);
    }

    @GetMapping(value = "/getChksCustomerAssessmentPlanDetailListByQuery")
    public HttpResult getChksCustomerAssessmentPlanDetailListByQuery(String formData) {
        try {
            ChksCustomerAssessmentPlanDetail chksCustomerAssessmentPlanDetail = JSONObject.parseObject(formData, ChksCustomerAssessmentPlanDetail.class);
            return HttpResult.newCorrectResult(200, chksCustomerAssessmentPlanDetailService.getChksCustomerAssessmentPlanDetailListByQuery(chksCustomerAssessmentPlanDetail));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }
}
