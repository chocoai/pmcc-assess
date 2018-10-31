package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingFunction;
import com.copower.pmcc.assess.service.basic.BasicBuildingFunctionService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/10/30 14:24
 * @Description:楼栋建筑功能
 */
@RequestMapping(value = "/basicBuildingFunction")
@Controller
public class BasicBuildingFunctionController {
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingFunctionById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingFunctionById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingFunctionService.getBasicBuildingFunctionById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuildingFunction", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(basicBuildingFunction));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuildingFunction", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuildingFunction(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingFunctionService.deleteBasicBuildingFunction(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicBuildingFunction basicBuildingFunction){
        try {
            return basicBuildingFunctionService.getBootstrapTableVo(basicBuildingFunction);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingFunctionList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingFunctionList(BasicBuildingFunction basicBuildingFunction){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingFunctionService.basicBuildingFunctionList(basicBuildingFunction));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
