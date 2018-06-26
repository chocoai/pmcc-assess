package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.entity.BaseForm;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.entity.BaseFormModuleField;
import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dto.output.BaseFormModuleVo;
import com.copower.pmcc.assess.dto.output.FormConfigureFieldVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.CustomTableTypeDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/formConfigure");
        modelAndView.addObject("tableList", formConfigureService.getTableList());
        List<CustomTableTypeDto> fieldTypeList = CustomTableTypeEnum.getCustomSetType();
        modelAndView.addObject("fieldTypeList", fieldTypeList);
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
     * 保存表单
     *
     * @param baseForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveForm", method = RequestMethod.POST)
    public HttpResult saveForm(BaseForm baseForm) {
        try {
            formConfigureService.saveForm(baseForm);
        } catch (Exception e) {
            LOGGER.error("保存表单异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除表单字段
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteForm", method = RequestMethod.POST)
    public HttpResult deleteForm(Integer id) {
        try {
            formConfigureService.deleteForm(id);
        } catch (Exception e) {
            LOGGER.error("删除表单异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取表单模块列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFormModuleList", method = RequestMethod.GET)
    public BootstrapTableVo getFormModuleList(Integer formId) {
        return formConfigureService.getFormModuleList(formId);
    }

    /**
     * 获取表单模块列表数据
     *
     * @param formId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFormModules", method = RequestMethod.GET)
    public HttpResult getFormModules(Integer formId) {
        return HttpResult.newCorrectResult(formConfigureService.getFormModules(formId));
    }

    /**
     * 保存表单模块
     *
     * @param baseFormModule
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveFormModule", method = RequestMethod.POST)
    public HttpResult saveFormModule(BaseFormModule baseFormModule) {
        try {
            formConfigureService.saveFormModule(baseFormModule);
        } catch (Exception e) {
            LOGGER.error("保存表单模块异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除表单模块
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteFormModule", method = RequestMethod.POST)
    public HttpResult deleteFormModule(Integer id) {
        try {
            formConfigureService.deleteFormModule(id);
        } catch (Exception e) {
            LOGGER.error("删除表单模块异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取表单列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFormModuleFieldList", method = RequestMethod.GET)
    public BootstrapTableVo getFormModuleFieldList(Integer formModuleId) {
        return formConfigureService.getFormModuleFieldList(formModuleId);
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
     * @param baseFormModuleField
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveFormListField", method = RequestMethod.POST)
    public HttpResult saveFormListField(BaseFormModuleField baseFormModuleField) {
        try {
            formConfigureService.saveFormModuleField(baseFormModuleField);
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
            formConfigureService.deleteFormModuleField(id);
        } catch (Exception e) {
            LOGGER.error("删除字段异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取动态html
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDynamicFormHtml", method = RequestMethod.POST)
    public HttpResult getDynamicFormHtml(Integer formModuleId, Boolean readOnly, String jsonValue) {
        try {
            return HttpResult.newCorrectResult(formConfigureService.getDynamicFormHtml(formModuleId, readOnly, jsonValue));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 获取字段Json数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFieldJsonString", method = RequestMethod.POST)
    public HttpResult getFieldJsonString(Integer formModuleId, Integer tableId, String tableName) {
        try {
            List<FormConfigureFieldVo> fieldVos = formConfigureService.getListShowFields(formModuleId);
            return HttpResult.newCorrectResult(fieldVos);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 保存子表数据
     *
     * @param tableName
     * @param tableId
     * @param formData
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveDetailInfo", method = RequestMethod.POST)
    public HttpResult saveDetailInfo(String tableName, Integer tableId, Integer formModuleId, String formData) {
        try {
            formConfigureService.saveDetailInfo(tableName, tableId, formModuleId, formData);
        } catch (Exception e) {
            LOGGER.error("保存子表数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除子表数据
     *
     * @param tableName
     * @param tableId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetailInfo", method = RequestMethod.POST)
    public HttpResult deleteDetailInfo(String tableName, Integer tableId) {
        try {
            formConfigureService.deleteDetailInfo(tableName, tableId);
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
    public BootstrapTableVo getDetailInfoList(String tableName, String foreignKeyName, Integer foreignKeyValue, Integer formModuleId) {
        return formConfigureService.getDetailInfoList(tableName, foreignKeyName, foreignKeyValue, formModuleId);
    }


    /**
     * 获取项目分类中的动态表单信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectClassifyFormInfo", method = RequestMethod.POST)
    public HttpResult getProjectClassifyFormInfo(Integer id) {
        try {
            BaseFormModuleVo baseFormModuleVo = new BaseFormModuleVo();
            BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(id);
            if (baseProjectClassify != null && baseProjectClassify.getFormModuleId() != null) {
                BaseFormModule baseFormModule = formConfigureService.getBaseFormModuleById(baseProjectClassify.getFormModuleId());
                BeanUtils.copyProperties(baseFormModule,baseFormModuleVo);
                baseFormModuleVo.setListShowFields(formConfigureService.getListShowFields(baseFormModule.getId()));
            }
            return HttpResult.newCorrectResult(baseFormModuleVo);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
