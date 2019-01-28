package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.service.data.DataQualificationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/dataQualification")
public class DataQualificationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataQualificationService dataQualificationService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataQualificationManage";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        Map<String, String> qualification = new HashMap<>();
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_FINANCE_ZCKJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_FINANCE_ZCKJS.getName());
        modelAndView.addObject("qualificationTypes", qualification);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataQualificationById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataQualificationVo dataQualificationVo = null;
        try {
            if (id != null) {
                dataQualificationVo = dataQualificationService.getByDataQualificationId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataQualificationVo);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataQualificationList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getExamineEstateNetworkList(String type) {
        BootstrapTableVo vo = null;
        try {
            vo = dataQualificationService.getListVos(type);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataQualificationById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataQualificationService.deleteDataQualification(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataQualification", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataQualification dataQualification) {
        try {
            if (dataQualification.getId() == null || dataQualification.getId().equals(0)) {
                dataQualificationService.addDataQualificationReturnId(dataQualification);
            } else {
                dataQualificationService.updateDataQualification(dataQualification);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
