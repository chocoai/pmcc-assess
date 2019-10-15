package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/14 14:35
 * @Description:报告字段(restful风格)
 */
@RequestMapping("/baseReportField")
@RestController
public class BaseReportFieldController {

    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseReportFieldService baseReportFieldService;

    /**
     * 基础数据字典配置页面视图
     *
     * @return
     */
    @GetMapping(value = "/index", name = "视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/baseReportField");
        return modelAndView;
    }

    @GetMapping("/getBaseReportFieldListByPid")
    public BootstrapTableVo getReportFieldListByPid(Integer pid) {
        return baseReportFieldService.getReportFieldListByPidVo(pid);
    }

    @GetMapping("/getBaseReportFieldList")
    public BootstrapTableVo getReportFieldList(String fieldName, String name) {
        return baseReportFieldService.getReportFieldListVo(fieldName, name);
    }

    @PostMapping(value = "/saveBaseReportField", name = "更新或者添加")
    public HttpResult saveReportField(BaseReportField baseReportField) {
        try {
            baseReportFieldService.saveReportField(baseReportField);
            return HttpResult.newCorrectResult(200, baseReportField);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @DeleteMapping(value = "/delBaseReportField", name = "删除数据")
    public HttpResult delReportField(Integer id) {
        try {
            baseReportFieldService.delReportField(id);
            return HttpResult.newCorrectResult(200, id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getBaseReportField")
    public HttpResult getBaseReportField(Integer id) {
        BaseReportField baseReportField = baseReportFieldService.getReportFieldById(id);
        if (baseReportField == null) {
            baseReportField = new BaseReportField();
        }
        return HttpResult.newCorrectResult(200, baseReportField);
    }

    @PostMapping(value = "/getBaseReportFieldLevel", name = "获取字典层级")
    public HttpResult getReportFieldLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, baseReportFieldService.getReportFieldLevel(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/queryBaseReportFieldTree")
    public List<ZtreeDto> queryBaseDicTree(String name) {
        return baseReportFieldService.queryBaseDicTree(name);
    }

    @PostMapping(value = "/getBaseReportFieldTree")
    public List<ZtreeDto> getBaseDicTree(Integer pid) {
        return baseReportFieldService.getBaseDicTree(pid);
    }

    @PostMapping(value = "/getBaseReportFieldByKey")
    public List<ZtreeDto> getBaseDicByKey(String key) {
        return baseReportFieldService.getBaseDicByKey(key);
    }

}
