package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataCustomerField;
import com.copower.pmcc.assess.service.data.DataCustomerFieldService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
 * @Date: 2018/9/11 18:16
 * @Description:
 */
@RequestMapping(value = "/dataCustomerField")
@Controller
public class DataCustomerFieldController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataCustomerFieldService dataCustomerFieldService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;


    @RequestMapping(value = "/index", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "/data/customerField";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    /**
     * 获取列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataCustomerFieldList", method = RequestMethod.GET)
    public BootstrapTableVo getDataCustomerFieldList(String queryName) {
        return dataCustomerFieldService.getDataCustomerFieldList(queryName);
    }


    /**
     * 保存信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCustomerField", method = RequestMethod.POST)
    public HttpResult save(DataCustomerField dataCustomerField) {
        try {
            dataCustomerFieldService.saveDataCustomerField(dataCustomerField);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCustomerField", name = "删除一条信息", method = RequestMethod.POST)
    public HttpResult deleteCustomerField(Integer id) {
        try {
            dataCustomerFieldService.deleteDataCustomerField(id);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomerFieldById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingLeisurePlaceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataCustomerFieldService.getCustomerFieldById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomerFieldList", name = "获取查询数据", method = {RequestMethod.GET})
    public HttpResult getCustomerField(DataCustomerField dataCustomerField) {
        try {
            return HttpResult.newCorrectResult(200, dataCustomerFieldService.getCustomerField(dataCustomerField));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
