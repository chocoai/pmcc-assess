package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseEquipment;
import com.copower.pmcc.assess.service.basic.BasicHouseEquipmentService;
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
 * @Date: 2018/11/6 12:00
 * @Description:
 */
@RequestMapping(value = "/basicHouseEquipment")
@Controller
public class BasicHouseEquipmentController {

    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseEquipmentById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseEquipmentById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseEquipmentService.getBasicHouseEquipmentById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseEquipment", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment){
        try {
            return HttpResult.newCorrectResult(200,basicHouseEquipmentService.saveAndUpdateBasicHouseEquipment(basicHouseEquipment));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseEquipment", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseEquipment(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseEquipmentService.deleteBasicHouseEquipment(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseEquipment basicHouseEquipment){
        try {
            return basicHouseEquipmentService.getBootstrapTableVo(basicHouseEquipment);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseEquipmentList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseEquipmentList(BasicHouseEquipment basicHouseEquipment){
        try {
            return HttpResult.newCorrectResult(200,basicHouseEquipmentService.basicHouseEquipmentList(basicHouseEquipment));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
