package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseFileTemplate;
import com.copower.pmcc.assess.service.base.BaseFileTemplateService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
@RequestMapping("/baseFileTemplate")
public class BaseFileTemplateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseFileTemplateController.class);
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseFileTemplateService baseFileTemplateService;
    @Autowired
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/fileTemplateIndex");
        modelAndView.addObject("currUserAccount",processControllerComponent.getThisUser());
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(value = "/getFileTemplateList",name = "获取数据列表")
    public BootstrapTableVo getDataDicList(String name, String remark) {
        return baseFileTemplateService.getFileTemplateList(name, remark);
    }


    @ResponseBody
    @GetMapping(value = "/getAttachmentId",name = "获取附件id")
    public HttpResult getAttachmentId(String name) {
        try {
            Integer attachmentId = baseFileTemplateService.getAttachmentId(name);
            return HttpResult.newCorrectResult(attachmentId);
        } catch (Exception e) {
            LOGGER.error("获取附件id异常", e);
            return HttpResult.newErrorResult("获取附件id异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveFileTemplate",name = "保存文件模板", method = RequestMethod.POST)
    public HttpResult saveFileTemplate(BaseFileTemplate baseFileTemplate) {
        try {
            baseFileTemplateService.saveFileTemplate(baseFileTemplate);
        } catch (Exception e) {
            LOGGER.error("保存文件模板", e);
            return HttpResult.newErrorResult("保存文件模板");
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFileTemplate",name = "删除文件模板", method = RequestMethod.POST)
    public HttpResult delDataDic(Integer id) {
        try {
            baseFileTemplateService.deleteFileTemplate(id);
        } catch (Exception e) {
            LOGGER.error("删除文件模板", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }



    @ResponseBody
    @RequestMapping(value = "/adjustLiquidationAnalysis",name = "调整变现分析税费", method = RequestMethod.POST)
    public HttpResult adjustLiquidationAnalysis(Integer id) {
        try {
            schemeLiquidationAnalysisService.adjustLiquidationAnalysis();
        } catch (Exception e) {
            LOGGER.error("调整变现分析税费异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
