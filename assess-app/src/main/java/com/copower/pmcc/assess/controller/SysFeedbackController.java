package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SysFeedback;
import com.copower.pmcc.assess.dto.output.data.SysFeedbackVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.SysFeedbackService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/sysFeedback")
public class SysFeedbackController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysFeedbackService sysFeedbackService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到我的反馈index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/sysFeedback";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> valueTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_INITIATE_URGENCY);
        modelAndView.addObject("valueTypeList", valueTypeList);
        List<BaseDataDic> systemTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_SYSTEM_TYPE);
        modelAndView.addObject("systemTypes", systemTypes);
        return modelAndView;
    }


    @RequestMapping(value = "/sysFeedbackAllIndex", name = "转到所有反馈index页面 ", method = {RequestMethod.GET})
    public ModelAndView sysFeedbackAllIndex() {
        String view = "/data/sysFeedbackAll";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> valueTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_INITIATE_URGENCY);
        modelAndView.addObject("valueTypeList", valueTypeList);
        List<BaseDataDic> systemTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_SYSTEM_TYPE);
        modelAndView.addObject("systemTypes", systemTypes);
        return modelAndView;
    }

    @RequestMapping(value = "/getSysFeedbackById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        SysFeedbackVo sysFeedbackVo = null;
        try {
            if (id != null) {
                sysFeedbackVo = sysFeedbackService.getBySysFeedbackId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(sysFeedbackVo);
    }

    @RequestMapping(value = "/getMySysFeedbackList", method = {RequestMethod.GET}, name = "获取我的列表")
    public BootstrapTableVo getMySysFeedbackList(Integer systemType, String questionTitle, String feedbackPerson, Integer status) {
        BootstrapTableVo vo = null;
        try {
            vo = sysFeedbackService.getListVos(systemType, questionTitle, feedbackPerson, status, processControllerComponent.getThisUser());
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/getAllSysFeedbackList", method = {RequestMethod.GET}, name = "获取所有列表")
    public BootstrapTableVo getAllSysFeedbackList(Integer systemType, String questionTitle, String feedbackPerson, Integer status) {
        BootstrapTableVo vo = null;
        try {
            vo = sysFeedbackService.getListVos(systemType, questionTitle, feedbackPerson, status, null);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/deleteSysFeedbackById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(sysFeedbackService.deleteSysFeedback(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateSysFeedback", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(SysFeedback sysFeedback) {
        try {
            if (sysFeedback.getId() == null || sysFeedback.getId().equals(0)) {
                sysFeedbackService.addSysFeedbackReturnId(sysFeedback);
            } else {
                sysFeedbackService.updateSysFeedback(sysFeedback);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
