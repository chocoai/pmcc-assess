package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicBuildingService.getBasicBuildingVoById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicBuildingService.getBasicBuildingByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuilding", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuilding(BasicBuilding basicBuilding) {
        try {
            return HttpResult.newCorrectResult(basicBuildingService.saveAndUpdate(basicBuilding, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuilding", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuilding(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicBuildingService.deleteBasicBuilding(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingList(BasicBuilding basicBuilding) {
        try {
            List<BasicBuilding> basicBuildingList = basicBuildingService.getBasicBuildingList(basicBuilding);
            return HttpResult.newCorrectResult(basicBuildingList);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addBuilding", name = "添加楼栋信息", method = {RequestMethod.POST})
    public HttpResult addBuilding(String buildingNumber) {
        try {
            return HttpResult.newCorrectResult(basicBuildingService.addBuilding(buildingNumber));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("主数据添加异常");
        }
    }

    @RequestMapping(value = "/detailView", name = "详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/realEstate/detail/building";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject(StringUtils.uncapitalize(BasicBuilding.class.getSimpleName()), basicBuildingService.getBasicBuildingVoById(id));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingList", method = {RequestMethod.GET}, name = "楼栋--列表")
    public BootstrapTableVo getCaseBuildingList(Integer estateId) throws Exception {
        if (estateId == null) return null;
        BasicBuilding building = new BasicBuilding();
        building.setEstateId(estateId);
        building.setBisCase(true);
        BootstrapTableVo vo = basicBuildingService.getBootstrapTableVo(building);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseBuilding", method = {RequestMethod.GET}, name = "楼栋-- 信息自动补全")
    public HttpResult autoCompleteCaseBuilding(String buildingNumber,String type, Integer applyBatchId) {
        try {
            BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(applyBatchId);
            BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
            if(basicEstate==null){
                return HttpResult.newCorrectResult(null);
            }else {
                List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailDao.getQuoteDataList(basicEstate.getQuoteId(), type, buildingNumber);
                return HttpResult.newCorrectResult(batchDetailList);
            }
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteFromAlternative", name = "引用备选案例数据", method = {RequestMethod.GET})
    public HttpResult quoteFromAlternative(Integer id, Integer tableId) {
        try {
            BasicAlternativeCase alternativeCase = basicAlternativeCaseDao.getBasicAlternativeCaseById(id);
            BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailDao.getInfoById(alternativeCase.getBatchDetailId());
            List<String> ignoreList = Lists.newArrayList("id","estateId","version","bisCase","creator");
            BasicBuilding basicBuilding = (BasicBuilding) basicBuildingService.copyBasicEntityIgnore(applyBatchDetail.getTableId(), tableId, true, ignoreList);
            return HttpResult.newCorrectResult(basicBuilding);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseBuilding", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseBuilding(Integer sourceApplyBatchDetailId, Integer targetId) {
        try {
            BasicApplyBatchDetail batchDetail = basicApplyBatchDetailDao.getInfoById(sourceApplyBatchDetailId);
            List<String> ignoreList = Lists.newArrayList("id","estateId","applyId","version","bisCase","creator");
            BasicBuilding basicBuilding = (BasicBuilding) basicBuildingService.copyBasicEntityIgnore(batchDetail.getTableId(), targetId, true, ignoreList);
            basicBuildingService.saveAndUpdate(basicBuilding, false);
            return HttpResult.newCorrectResult(basicBuilding);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
