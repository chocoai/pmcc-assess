package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.service.basic.BasicBuildingMainService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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
 * @Description:楼栋
 */
@RequestMapping(value = "/basicBuilding")
@Controller
public class BasicBuildingController {
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicBuildingMainService basicBuildingMainService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingById(Integer id){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.getBasicBuildingById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingMainByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingMainByApplyId(Integer applyId){
        try {
            return HttpResult.newCorrectResult(basicBuildingMainService.getBasicBuildingMainByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuilding", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuilding(BasicBuilding basicBuilding){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuilding", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuilding(Integer id){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.deleteBasicBuilding(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicBuilding basicBuilding){
        try {
            return basicBuildingService.getBootstrapTableVo(basicBuilding);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingList(BasicBuilding basicBuilding){
        try {
            List<BasicBuilding> basicBuildingList = basicBuildingService.basicBuildingList(basicBuilding);
            return HttpResult.newCorrectResult(basicBuildingList);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingListByMainId", name = "获取楼栋部分数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingListByMainId(Integer basicBuildingMainId){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.getBasicBuildingListByMainId(basicBuildingMainId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addBuildingMainAndBuilding", name = "同时添加楼栋主数据及楼栋信息", method = {RequestMethod.POST})
    public HttpResult addBuildingMainAndBuilding(String buildingNumber){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.addBuildingMainAndBuilding(buildingNumber));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult("主数据添加异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/appWriteBuilding", name = "过程数据转移", method = {RequestMethod.POST})
    public HttpResult appWriteBuilding(Integer caseMainBuildingId){
        try {
            return HttpResult.newCorrectResult(basicBuildingService.appWriteBuilding(caseMainBuildingId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult("过程数据转移异常");
        }
    }
}
