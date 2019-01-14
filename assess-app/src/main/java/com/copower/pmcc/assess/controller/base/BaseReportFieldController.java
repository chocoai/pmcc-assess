package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.assess.common.enums.BaseReportFieldReplaceEnum;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseReportFieldController.class);

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseReportFieldService baseDataDicService;
    /**
     * 基础数据字典配置页面视图
     *
     * @return
     */
    @GetMapping(value = "/index", name = "视图")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/baseReportField");
       modelAndView.addObject("replaceType", BaseReportFieldReplaceEnum.getBaseReportFieldReplaceEnumList());
        return modelAndView;
    }

    @GetMapping("/getBaseReportFieldListByPid")
    public BootstrapTableVo getDataDicListByPid(Integer pid) {
        return baseDataDicService.getDataDicListByPidVo(pid);
    }

    @GetMapping("/getBaseReportFieldList")
    public BootstrapTableVo getDataDicList(String fieldName, String name) {
        return baseDataDicService.getDataDicListVo(fieldName, name);
    }

    @PostMapping(value = "/saveBaseReportField",name = "更新或者添加")
    public HttpResult saveDataDic(BaseReportField baseReportField) {
        try {
            baseDataDicService.saveDataDic(baseReportField);
        } catch (Exception e) {
            LOGGER.error("保存数据字典异常", e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult(200, baseReportField);
    }

    @DeleteMapping(value = "/delBaseReportField", name = "删除数据")
    public HttpResult delDataDic(Integer id) {
        try {
            baseDataDicService.delDataDic(id);
        } catch (Exception e) {
            LOGGER.error("删除数据字典异常", e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult(200, id);
    }

    @GetMapping(value = "/getBaseReportField")
    public HttpResult getBaseReportField(Integer id) {
        BaseReportField baseReportField = baseDataDicService.getDataDicById(id);
        if (baseReportField == null) {
            baseReportField = new BaseReportField();
        }
        return HttpResult.newCorrectResult(200,baseReportField);
    }

    @PostMapping(value = "/getBaseReportFieldLevel",name = "获取字典层级")
    public HttpResult getDataDicLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(200,baseDataDicService.getDataDicLevel(id));
        } catch (Exception e) {
            LOGGER.error("获取字典层级异常", e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @PostMapping(value = "/queryBaseReportFieldTree")
    public List<ZtreeDto> queryBaseDicTree(String name) {
        return baseDataDicService.queryBaseDicTree(name);
    }

    @PostMapping(value = "/getBaseReportFieldTree")
    public List<ZtreeDto> getBaseDicTree(Integer pid) {
        return baseDataDicService.getBaseDicTree(pid);
    }

    @PostMapping(value = "/getBaseReportFieldByKey")
    public List<ZtreeDto> getBaseDicByKey(String key) {
        return baseDataDicService.getBaseDicByKey(key);
    }

}
