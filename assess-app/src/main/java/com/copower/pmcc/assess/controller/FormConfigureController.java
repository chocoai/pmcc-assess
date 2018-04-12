package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.entity.BaseFormListField;
import com.copower.pmcc.assess.service.FormConfigureService;
import com.copower.pmcc.erp.api.dto.CustomTableTypeDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
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
 * Created by kings on 2018-3-21.
 */
@Controller
@RequestMapping("/formConfigure")
public class FormConfigureController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormConfigureController.class);
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private FormConfigureService formConfigureService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/base/formConfigure");
        modelAndView.addObject("tableList",formConfigureService.getTableList());
        List<CustomTableTypeDto> fieldTypeList = CustomTableTypeEnum.getCustomSetType();
        modelAndView.addObject("fieldTypeList",fieldTypeList);
        return modelAndView;
    }

    /**
     * 获取表单列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFormList", method = RequestMethod.GET)
    public BootstrapTableVo getFormList() {
        return formConfigureService.getFormList();
    }

    /**
     * 获取表单列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFormModularList", method = RequestMethod.GET)
    public BootstrapTableVo getFormModularList(String formName) {
        return formConfigureService.getFormModularList(formName);
    }

    /**
     * 获取表单列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFormModularFieldList", method = RequestMethod.GET)
    public BootstrapTableVo getFormModularFieldList(Integer formListId) {
        return formConfigureService.getFormModularFieldList(formListId);
    }

    /**
     * 获取字段信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFieldList", method = RequestMethod.POST)
    public HttpResult getFieldList(String tableName) {
        try {
            return HttpResult.newCorrectResult(formConfigureService.getFieldList(tableName));
        } catch (Exception e) {
           return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 保存字段
     *
     * @param hrBaseFormListFieldDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveFormListField", method = RequestMethod.POST)
    public HttpResult saveFormListField(BaseFormListField hrBaseFormListFieldDto) {
        try {
            formConfigureService.saveFormListField(hrBaseFormListFieldDto);
        } catch (Exception e) {
            LOGGER.error("保存字段异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除模板字段
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteFormListField", method = RequestMethod.POST)
    public HttpResult deleteFormListField(Integer id) {
        try {
            formConfigureService.deleteFormListField(id);
        } catch (Exception e) {
            LOGGER.error("删除字段异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取字段信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDynamicFormHtml", method = RequestMethod.POST)
    public HttpResult getDynamicFormHtml(Integer formListId, Boolean readOnly, String jsonValue) {
        try {
            return HttpResult.newCorrectResult(formConfigureService.getDynamicFormHtml(formListId,readOnly,jsonValue));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 保存子表数据
     * @param tableName
     * @param tableId
     * @param formListId
     * @param formData
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveDetailInfo", method = RequestMethod.POST)
    public HttpResult saveDetailInfo(String tableName, Integer tableId, Integer formListId, String formData) {
        try {
            formConfigureService.saveDetailInfo(tableName,tableId,formListId,formData);
        } catch (Exception e) {
            LOGGER.error("保存子表数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除子表数据
     * @param tableName
     * @param tableId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetailInfo", method = RequestMethod.POST)
    public HttpResult deleteDetailInfo(String tableName, Integer tableId) {
        try {
            formConfigureService.deleteDetailInfo(tableName,tableId);
        } catch (Exception e) {
            LOGGER.error("删除子表数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取子表列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDetailInfoList", method = RequestMethod.GET)
    public BootstrapTableVo getDetailInfoList(String tableName, String foreignKeyName, Integer foreignKeyValue, Integer formListId) {
        return formConfigureService.getDetailInfoList(tableName,foreignKeyName,foreignKeyValue,formListId);
    }
}
