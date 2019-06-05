package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateTool;
import com.copower.pmcc.assess.service.data.DataContractCalculateToolService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/dataContractCalculateTool")
public class DataContractCalculateToolController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataContractCalculateToolService dataContractCalculateToolService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataContractCalculateTool";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/viewonly", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView viewonly() {
        String view = "/data/dataContractCalculateTool";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("readonly", true);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list() {
        return dataContractCalculateToolService.getDataContractCalculateToolList();
    }

    @RequestMapping(value = "/getDataContractCalculateToolById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataContractCalculateTool dataContractCalculateTool = null;
        try {
            if (id != null) {
                dataContractCalculateTool = dataContractCalculateToolService.getByDataContractCalculateToolId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataContractCalculateTool);
    }

    @RequestMapping(value = "/deleteDataContractCalculateToolById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataContractCalculateToolService.deleteDataContractCalculateTool(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataContractCalculateTool", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataContractCalculateTool dataContractCalculateTool) {
        try {
            dataContractCalculateToolService.saveObject(dataContractCalculateTool);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
