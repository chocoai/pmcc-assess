package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateParking;
import com.copower.pmcc.assess.service.basic.BasicEstateParkingService;
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
 * @Date: 2018/11/6 11:39
 * @Description:
 */
@RequestMapping(value = "/basicEstateParking")
@Controller
public class BasicEstateParkingController {

    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateParkingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateParkingById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateParkingService.getBasicEstateParkingById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateParking", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateParking(BasicEstateParking basicEstateParking){
        try {
            return HttpResult.newCorrectResult(200,basicEstateParkingService.saveAndUpdateBasicEstateParking(basicEstateParking));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateParking", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateParking(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateParkingService.deleteBasicEstateParking(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateParking basicEstateParking){
        try {
            return basicEstateParkingService.getBootstrapTableVo(basicEstateParking);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateParkingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateParkingList(BasicEstateParking basicEstateParking){
        try {
            return HttpResult.newCorrectResult(200,basicEstateParkingService.basicEstateParkingList(basicEstateParking));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
