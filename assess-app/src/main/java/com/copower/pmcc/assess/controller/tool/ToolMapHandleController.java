package com.copower.pmcc.assess.controller.tool;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ToolMapHandle;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.tool.ToolMapHandleService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by zch on 2019-10-8.
 * 地图插件
 */
@RestController
@RequestMapping(value = "/toolMapHandle")
public class ToolMapHandleController {


    private final String errorName = "地图插件" ;

    @Autowired
    private ToolMapHandleService toolMapHandleService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/toolMapHandleView")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/tool/toolMapHandleView");
        settingParams(modelAndView) ;
        return modelAndView;
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(ToolMapHandle oo){
        return toolMapHandleService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/saveToolMapHandle")
    public HttpResult saveToolMapHandle(String formData) {
        try {
            ToolMapHandle toolMapHandle = JSONObject.parseObject(formData, ToolMapHandle.class);
            toolMapHandleService.saveToolMapHandle(toolMapHandle);
            return HttpResult.newCorrectResult(200, toolMapHandle);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @GetMapping(value = "/getToolMapHandleById")
    public HttpResult getToolMapHandle(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, toolMapHandleService.getToolMapHandleById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @DeleteMapping(value = "/deleteToolMapHandleById/{id}")
    public HttpResult deleteToolMapHandleById(@PathVariable String id) {
        try {
            toolMapHandleService.deleteToolMapHandle(id) ;
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    private void settingParams(ModelAndView modelAndView){

    }

}
