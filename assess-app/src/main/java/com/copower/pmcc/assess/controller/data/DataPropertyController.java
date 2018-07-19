package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
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

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:54
 * @Description:物业
 */
@Controller
@RequestMapping(value = "/dataProperty")
public class DataPropertyController {

    @Autowired
    private DataPropertyService dataPropertyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataPropertyView" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPropertyById",method = {RequestMethod.GET},name = "获取开发商")
    public HttpResult getById(Integer id) {
        DataProperty dataProperty = null;
        try {
            if (id!=null){
                dataProperty = dataPropertyService.getByDataPropertyId(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataProperty);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPropertyList",method = {RequestMethod.GET},name = "获取开发商列表")
    public BootstrapTableVo getExamineEstateNetworkList(String name) {
        BootstrapTableVo vo = null;
        try {
            vo = dataPropertyService.getListVos(name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataPropertyById",method = {RequestMethod.POST},name = "删除开发商")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(dataPropertyService.deleteDataProperty(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataProperty",method = {RequestMethod.POST},name = "更新开发商")
    public HttpResult saveAndUpdate(DataProperty dataProperty){
        try {
            if (dataProperty.getId()==null || dataProperty.getId().equals(0)){
                dataPropertyService.addDataPropertyReturnId(dataProperty);
            }else {
                dataPropertyService.updateDataProperty(dataProperty);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
