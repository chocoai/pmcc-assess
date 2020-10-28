package com.copower.pmcc.assess.controller;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateWithBLOBs;
import com.copower.pmcc.assess.service.ScriptTemplateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/scriptTemplate")
@RestController
public class ScriptTemplateController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ScriptTemplateService scriptTemplateService;

    @RequestMapping(value = "/templateIndex")
    public ModelAndView templateIndex() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/scriptTemplate/templateIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/templateEdit/{id}", method = {RequestMethod.GET})
    public ModelAndView templateEdit(@PathVariable Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/scriptTemplate/templateEdit");
        modelAndView.addObject("template", scriptTemplateService.getScriptTemplateById(id));
        return modelAndView;
    }

    @PostMapping(value = "/previewScriptTemplate", name = "预览脚本模板")
    public HttpResult previewScriptTemplate(ScriptTemplateWithBLOBs scriptTemplate, String paramJsonStr) {
        try {
            //paramJson 实际是一个key-value map
            JSONObject paramJson = null;
            if (StringUtils.isNotBlank(paramJsonStr)) {
                paramJson = JSONObject.parseObject(paramJsonStr);
            }
            String result = scriptTemplateService.executeScriptTemplate(scriptTemplate, paramJson);
            return HttpResult.newCorrectResult(200, result);
        } catch (Exception e) {
            logger.error("预览脚本模板", e);
            return HttpResult.newErrorResult(500, e);
        }

    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(String templateName, String templateKey) {
        return scriptTemplateService.getScriptTemplateDataList(templateName, templateKey);
    }

    @PostMapping(value = "/saveAndUpdateScriptTemplate")
    public HttpResult saveAndUpdateScriptTemplate(String formData) {
        try {
            ScriptTemplateWithBLOBs template = JSONObject.parseObject(formData, ScriptTemplateWithBLOBs.class);
            scriptTemplateService.saveAndUpdateScriptTemplate(template);
            return HttpResult.newCorrectResult(200, template);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/deleteScriptTemplateByIds")
    public HttpResult deleteScriptTemplateByIds(String id) {
        try {
            scriptTemplateService.deleteScriptTemplateByIds(FormatUtils.transformString2Integer(id));
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
