package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWater;
import com.copower.pmcc.assess.service.basic.BasicHouseWaterService;
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
 * @Date: 2018/11/6 11:57
 * @Description:
 */
@RequestMapping(value = "/basicHouseWater")
@Controller
public class BasicHouseWaterController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseWaterService basicHouseWaterService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseWaterById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseWaterById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseWaterService.getBasicHouseWaterById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseWater", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseWater(BasicHouseWater basicHouseWater){
        try {
            return HttpResult.newCorrectResult(200,basicHouseWaterService.saveAndUpdateBasicHouseWater(basicHouseWater));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseWater", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseWater(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseWaterService.deleteBasicHouseWater(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseWater basicHouseWater, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicHouseWater != null){
                if (!approval) {
                    basicHouseWater.setCreator(commonService.thisUserAccount());
                }
            }
            return basicHouseWaterService.getBootstrapTableVo(basicHouseWater);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseWaterList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseWaterList(BasicHouseWater basicHouseWater){
        try {
            return HttpResult.newCorrectResult(200,basicHouseWaterService.basicHouseWaterList(basicHouseWater));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
