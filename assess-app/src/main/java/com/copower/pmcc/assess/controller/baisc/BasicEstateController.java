package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
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
 * @Description:楼盘
 */
@RequestMapping(value = "/basicEstate")
@Controller
public class BasicEstateController {
    @Autowired
    private BasicEstateService basicEstateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstate", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstate(BasicEstate basicEstate){
        try {
            return HttpResult.newCorrectResult(basicEstateService.saveAndUpdateBasicEstate(basicEstate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstate", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstate(Integer id){
        try {
            return HttpResult.newCorrectResult(basicEstateService.deleteBasicEstate(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate){
        try {
            return basicEstateService.getBootstrapTableVo(basicEstate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateList(BasicEstate basicEstate){
        try {
            return HttpResult.newCorrectResult(basicEstateService.basicEstateList(basicEstate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

}
