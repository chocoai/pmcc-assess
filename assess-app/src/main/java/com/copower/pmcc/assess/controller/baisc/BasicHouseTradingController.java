package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:
 */
@RequestMapping(value = "/basicHouseTrading")
@Controller
public class BasicHouseTradingController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseTradingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseTradingById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicHouseTradingService.getBasicHouseTradingById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseTrading", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseTrading(String formData){
        try {
            BasicHouseTrading basicHouseTrading = JSONObject.parseObject(formData,BasicHouseTrading.class) ;
            return HttpResult.newCorrectResult(basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading,true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseTrading", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseTrading(Integer id){
        try {
            return HttpResult.newCorrectResult(basicHouseTradingService.deleteBasicHouseTrading(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseTrading basicHouseTrading, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            return basicHouseTradingService.getBootstrapTableVo(basicHouseTrading);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseTradingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseTradingList(BasicHouseTrading basicHouseTrading){
        try {
            return HttpResult.newCorrectResult(basicHouseTradingService.basicHouseTradingVoList(basicHouseTrading));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateBisMark", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult updateBisMark(String formData){
        try {
            BasicHouseTrading basicHouseTrading = JSONObject.parseObject(formData,BasicHouseTrading.class) ;
            basicHouseTradingService.updateBisMark(basicHouseTrading);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
}
