package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:房屋
 */
@RequestMapping(value = "/basicHouse")
@Controller
public class BasicHouseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/realEstate/detail/house";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(id);
        if (basicHouseVo != null) {
            modelAndView.addObject(StringUtils.uncapitalize(BasicHouse.class.getSimpleName()), basicHouseVo);
            BasicHouseTradingVo basicHouseTradingVo = basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(basicHouseVo.getId()));
            modelAndView.addObject(StringUtils.uncapitalize(BasicHouseTrading.class.getSimpleName()), basicHouseTradingVo);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouse", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouse(BasicHouse basicHouse) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.saveAndUpdate(basicHouse, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouse", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouse(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.deleteBasicHouse(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseList(BasicHouse basicHouse) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseList(basicHouse));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addHouseAndTrading", name = "添加房屋及交易信息", method = {RequestMethod.POST})
    public HttpResult addHouseAndTrading(String houseNumber, Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.addHouseAndTrading(houseNumber, applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("添加房屋及交易信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/copyHuxingPlan", name = "拷贝户型图", method = {RequestMethod.POST})
    public HttpResult copyHuxingPlan(Integer sourceTableId, String sourceTableName, Integer targetTableId, String fieldsName) {
        try {
            basicHouseService.copyHuxingPlan(sourceTableId, sourceTableName, targetTableId, fieldsName);
            return HttpResult.newCorrectResult(null);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("拷贝户型图异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseMapById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseMapById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseMapById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseList", method = {RequestMethod.GET}, name = "获取案例 房屋列表")
    public BootstrapTableVo getCaseHouseList(Integer unitId) {
        if (unitId == null) return null;
        BasicHouse basicHouse = new BasicHouse();
        basicHouse.setUnitId(unitId);
        basicHouse.setBisCase(true);
        BootstrapTableVo vo = basicHouseService.getBootstrapTableVo(basicHouse);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseHouse", method = {RequestMethod.GET}, name = "房屋-- 信息自动补全")
    public HttpResult autoCompleteCaseHouse(String houseNumber, Integer unitId) {
        try {
            List<CustomCaseEntity> caseEntities = basicHouseService.autoCompleteCaseHouse(houseNumber, unitId);
            return HttpResult.newCorrectResult(caseEntities);
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
            List<String> ignoreList = Lists.newArrayList("estateId", "buildingId", "unitId");
            BasicHouse basicHouse = (BasicHouse) basicHouseService.copyBasicEntityIgnore(applyBatchDetail.getTableId(), tableId, true, ignoreList);
            Map<String, Object> objectMap = basicHouseService.getBasicHouseMapById(basicHouse.getId());
            return HttpResult.newCorrectResult(objectMap);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseHouse", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseHouse(Integer sourceId, Integer targetId) {
        try {
            List<String> ignoreList = Lists.newArrayList("estateId", "buildingId", "unitId");
            BasicHouse basicHouse = (BasicHouse) basicHouseService.copyBasicEntityIgnore(sourceId, targetId, true, ignoreList);
            basicHouse.setQuoteId(sourceId);
            basicHouse.setBisCase(false);
            basicHouseService.saveAndUpdate(basicHouse, false);
            return HttpResult.newCorrectResult(basicHouse);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
