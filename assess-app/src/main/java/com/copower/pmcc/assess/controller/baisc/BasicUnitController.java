package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:
 */
@RequestMapping(value = "/basicUnit")
@Controller
public class BasicUnitController {
    @Autowired
    private BasicUnitService basicUnitService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnit", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnit(BasicUnit basicUnit){
        try {
            return HttpResult.newCorrectResult(basicUnitService.saveAndUpdateBasicUnit(basicUnit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnit", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnit(Integer id){
        try {
            return HttpResult.newCorrectResult(basicUnitService.deleteBasicUnit(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit){
        try {
            return basicUnitService.getBootstrapTableVo(basicUnit);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitList(BasicUnit basicUnit){
        try {
            return HttpResult.newCorrectResult(basicUnitService.basicUnitList(basicUnit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

}
