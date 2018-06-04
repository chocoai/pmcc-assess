package com.copower.pmcc.assess.controller.report;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ReportTemplate;
import com.copower.pmcc.assess.dal.entity.ReportTemplateBookmark;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.ReportTemplateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * Created by kings on 2018-3-5.
 */
@Controller
@RequestMapping("/reportTemplate")
public class ReportTemplateController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReportTemplateService reportTemplateService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/reportTemplate");
        List<BaseDataDic> reportTemplateTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TEMPLATE_TYPE);
        modelAndView.addObject("reportTemplateTypeList",reportTemplateTypeList);
        modelAndView.addObject("currUserAccount",commonService.thisUserAccount());
        return modelAndView;
    }

    /**
     * 获取模板列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTemplateList", method = RequestMethod.GET)
    public BootstrapTableVo getTemplateList(Integer type, Integer category) {
        return reportTemplateService.getTemplateList(type, category);
    }

    /**
     * 保存模板
     *
     * @param reportTemplate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
    public HttpResult saveTemplate(ReportTemplate reportTemplate) {
        try {
            reportTemplateService.saveTemplate(reportTemplate);
        } catch (Exception e) {
            LOGGER.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除合同模板
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTemplate", method = RequestMethod.POST)
    public HttpResult deleteTemplate(Integer id) {
        try {
            reportTemplateService.deleteTemplate(id);
        } catch (Exception e) {
            LOGGER.error("删除模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取模板书签列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTemplateBookmarkList", method = RequestMethod.GET)
    public BootstrapTableVo getTemplateBookmarkList(Integer templateId) {
        return reportTemplateService.getTemplateBookmarkList(templateId);
    }

    /**
     * 保存模板书签
     *
     * @param reportTemplateBookmark
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveTemplateBookmark", method = RequestMethod.POST)
    public HttpResult saveTemplateBookmark(ReportTemplateBookmark reportTemplateBookmark) {
        try {
            reportTemplateService.saveTemplateBookmark(reportTemplateBookmark);
        } catch (Exception e) {
            LOGGER.error("保存模板书签异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除模板书签
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTemplateBookmark", method = RequestMethod.POST)
    public HttpResult deleteTemplateBookmark(Integer id) {
        try {
            reportTemplateService.deleteTemplateBookmark(id);
        } catch (Exception e) {
            LOGGER.error("删除模板书签异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


}
