package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.service.basic.BasicHouseRoomDecorateService;
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
@RequestMapping(value = "/basicHouseRoomDecorate")
@Controller
public class BasicHouseRoomDecorateController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseRoomDecorateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseRoomDecorateById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseRoomDecorateService.getBasicHouseRoomDecorateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseRoomDecorate", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate){
        try {
            return HttpResult.newCorrectResult(200,basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate,true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseRoomDecorate", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseRoomDecorate(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicHouseRoomDecorateService.deleteBasicHouseRoomDecorate(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseRoomDecorate basicHouseRoomDecorate){
        try {
            return basicHouseRoomDecorateService.getBootstrapTableVo(basicHouseRoomDecorate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseRoomDecorateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseRoomDecorateList(BasicHouseRoomDecorate basicHouseRoomDecorate){
        try {
            return HttpResult.newCorrectResult(200,basicHouseRoomDecorateService.basicHouseRoomDecorateList(basicHouseRoomDecorate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

}
