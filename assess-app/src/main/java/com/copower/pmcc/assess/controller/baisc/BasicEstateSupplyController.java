package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateSupply;
import com.copower.pmcc.assess.service.basic.BasicEstateSupplyService;
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
 * @Date: 2018/11/6 11:41
 * @Description:
 */
@RequestMapping(value = "/basicEstateSupply")
@Controller
public class BasicEstateSupplyController {

    @Autowired
    private BasicEstateSupplyService basicEstateSupplyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateSupplyById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateSupplyById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateSupplyService.getBasicEstateSupplyById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateSupply", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateSupply(BasicEstateSupply basicEstateSupply){
        try {
            return HttpResult.newCorrectResult(200,basicEstateSupplyService.saveAndUpdateBasicEstateSupply(basicEstateSupply));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateSupply", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateSupply(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateSupplyService.deleteBasicEstateSupply(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateSupply basicEstateSupply){
        try {
            return basicEstateSupplyService.getBootstrapTableVo(basicEstateSupply);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateSupplyList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateSupplyList(BasicEstateSupply basicEstateSupply){
        try {
            return HttpResult.newCorrectResult(200,basicEstateSupplyService.basicEstateSupplyList(basicEstateSupply));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
