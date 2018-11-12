package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseCorollaryEquipment;
import com.copower.pmcc.assess.service.basic.BasicHouseCorollaryEquipmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * @Date: 2018/11/6 12:01
 * @Description:
 */
@RequestMapping(value = "/basicHouseCorollaryEquipment")
@Controller
public class BasicHouseCorollaryEquipmentController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseCorollaryEquipmentById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseCorollaryEquipmentById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseCorollaryEquipmentService.getBasicHouseCorollaryEquipmentById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseCorollaryEquipment", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment){
        try {
            return HttpResult.newCorrectResult(200,basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(basicHouseCorollaryEquipment));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseCorollaryEquipment", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseCorollaryEquipment(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseCorollaryEquipmentService.deleteBasicHouseCorollaryEquipment(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment){
        try {
            if (basicHouseCorollaryEquipment != null){
                basicHouseCorollaryEquipment.setCreator(commonService.thisUserAccount());
            }
            return basicHouseCorollaryEquipmentService.getBootstrapTableVo(basicHouseCorollaryEquipment);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseCorollaryEquipmentList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseCorollaryEquipmentList(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment){
        try {
            return HttpResult.newCorrectResult(200,basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(basicHouseCorollaryEquipment));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
