package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurvey;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLocaleSurveyService;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 10:00
 * @Description:现场查勘图片配置
 */
@RequestMapping(value = "/dataLocaleSurvey")
@Controller
public class DataLocaleSurveyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLocaleSurveyService dataLocaleSurveyService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataLocaleSurveyView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> pictureTemplates = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOCALE_SURVEY_PICTURE_TEMPLATE);
        modelAndView.addObject("pictureTemplates", pictureTemplates);
        //超级管理员
        String account = processControllerComponent.getThisUser();
        if (processControllerComponent.userIsAdmin(account)) {//失效的方法
            modelAndView.addObject("permission", "admin");//不设置 processControllerComponent.getThisUser(),防止超级管理员变更或者直接变为一组
        } else {
            modelAndView.addObject("permission", account);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLocaleSurveyById", method = {RequestMethod.GET}, name = "获取现场查勘图片配置")
    public HttpResult getById(Integer id) {
        DataLocaleSurvey dataLocaleSurvey = null;
        try {
            if (id != null) {
                dataLocaleSurvey = dataLocaleSurveyService.getDataLocaleSurveyById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataLocaleSurvey);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLocaleSurveyList", method = {RequestMethod.GET}, name = "获取现场查勘图片配置列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer type,String name) {
        BootstrapTableVo vo = null;
        try {
            vo = dataLocaleSurveyService.getDataLocaleSurveyListVos(type,name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLocaleSurveyListBySurvey", method = {RequestMethod.GET}, name = "获取现场查勘图片配置列表")
    public BootstrapTableVo getDataLocaleSurveyListBySurvey(Integer type,String name) {
        BootstrapTableVo vo = null;
        try {
            vo = dataLocaleSurveyService.getDataLocaleSurveyListBySurvey(type,name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataLocaleSurveyById", method = {RequestMethod.POST}, name = "删除现场查勘图片配置")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DataLocaleSurvey dataLocaleSurvey = new DataLocaleSurvey();
                dataLocaleSurvey.setId(id);
                dataLocaleSurveyService.removeDataLocaleSurvey(dataLocaleSurvey);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataLocaleSurvey", method = {RequestMethod.POST}, name = "更新现场查勘图片配置")
    public HttpResult saveAndUpdate(DataLocaleSurvey dataLocaleSurvey) {
        try {
            dataLocaleSurveyService.saveAndUpdateDataLocaleSurvey(dataLocaleSurvey);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
