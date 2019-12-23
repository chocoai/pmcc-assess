package com.copower.pmcc.assess.controller.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.cases.CaseHouseTradingLeaseAndSellDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicHouseTradingService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/9/11 18:16
 * @Description:
 */
@RequestMapping(value = "/caseHouse")
@Controller
public class CaseHouseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseHouseTradingLeaseAndSellDtoService caseHouseTradingLeaseAndSellDtoService;
    @Autowired
    private CaseHouseFaceStreetService caseHouseFaceStreetService;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseIntelligentService caseHouseIntelligentService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseWaterService caseHouseWaterService;
    @Autowired
    private CaseHouseEquipmentService caseHouseEquipmentService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private CaseHouseWaterDrainService caseHouseWaterDrainService;
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;


    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/house/detail/house";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseHouse caseHouse = caseHouseService.getCaseHouseById(id);
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseHouse.getUnitId());
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
        if (caseEstate.getType() != null) {
            modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(caseEstate.getType()).getKey());
        }

        BasicHouse house = new BasicHouse();
        house.setDisplayCaseId(id);
        List<BasicHouse> houses = basicHouseDao.basicHouseList(house);
        if (CollectionUtils.isNotEmpty(houses)) {
            house = houses.get(0);
        } else {
            basicHouseDao.addBasicHouse(house);
        }
        caseHouseService.quoteCaseHouseToBasic(id, house.getId());
        house.setDisplayCaseId(id);
        basicHouseDao.updateBasicHouse(house, false);

        BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(house.getId());
        modelAndView.addObject(org.springframework.util.StringUtils.uncapitalize(BasicHouse.class.getSimpleName()), basicHouseVo);
        modelAndView.addObject(org.springframework.util.StringUtils.uncapitalize(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(basicHouseVo.getId())));
        return modelAndView;
    }

    /**
     * 设置房屋中部分元素是否渲染
     *
     * @param caseHouseTradingVo
     * @param modelAndView
     */
    private void setHouseElementRender(CaseHouseTradingVo caseHouseTradingVo, ModelAndView modelAndView) {
        if (caseHouseTradingVo == null) return;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(caseHouseTradingVo.getTransactionSituation());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseAbnormal", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_SITUATION_ABNORMAL));
        }
        baseDataDic = baseDataDicService.getCacheDataDicById(caseHouseTradingVo.getPaymentMethod());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseInstallment", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_PAYMENT_METHOD_INSTALLMENT));
        }
        baseDataDic = baseDataDicService.getCacheDataDicById(caseHouseTradingVo.getInformationType());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseInfomationOpen", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_INFORMATION_SOURCE_TYPE_OPEN));
        }
        baseDataDic = baseDataDicService.getCacheDataDicById(caseHouseTradingVo.getTradingType());
        if (baseDataDic != null && StringUtils.isNotBlank(baseDataDic.getFieldName())) {
            modelAndView.addObject("isHouseSell", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_SELL));
            modelAndView.addObject("isHouseLease", baseDataDic.getFieldName().equals(AssessExamineTaskConstant.EXAMINE_HOUSE_TRANSACTION_TYPE_LEASE));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseById", method = {RequestMethod.GET}, name = "获取案例 房屋")
    public HttpResult getCaseHouseById(Integer id) {
        CaseHouse caseHouse = null;
        try {
            if (id != null) {
                caseHouse = caseHouseService.getCaseHouseById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseHouse);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseByIdAndTrading", method = {RequestMethod.GET}, name = "获取案例 房屋")
    public HttpResult getCaseHouseByIdAndTrading(Integer id) {
        CaseHouse caseHouse = null;
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        try {
            if (id != null) {
                caseHouse = caseHouseService.getCaseHouseById(id);
                CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
                caseHouseTrading.setHouseId(id);
                List<CaseHouseTradingVo> houseTradings = caseHouseTradingService.caseHouseTradingVoList(caseHouseTrading);
                if (!ObjectUtils.isEmpty(houseTradings)) {
                    objectMap.put(CaseHouseTrading.class.getSimpleName(), houseTradings.get(0));
                }
                objectMap.put(CaseHouse.class.getSimpleName(), caseHouse);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(objectMap);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseList", method = {RequestMethod.GET}, name = "获取案例 房屋列表")
    public BootstrapTableVo getCaseHouseList(Integer unitId) {
        CaseHouse caseHouse = new CaseHouse();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (unitId != null) {
                caseHouse.setUnitId(unitId);
                vo = caseHouseService.getCaseHouseListVos(caseHouse);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseHouseById", method = {RequestMethod.POST}, name = "删除案例 房屋")
    public HttpResult deleteCaseHouseById(Integer id) {
        CaseHouse caseHouse = null;
        try {
            if (id != null) {
                CaseHouseTrading caseHouseTrading = new CaseHouseTrading();
                caseHouseTrading.setHouseId(id);
                caseHouseTradingService.deleteCaseHouseTrading(caseHouseTrading);
                caseHouse = caseHouseService.getCaseHouseById(id);
                caseHouseService.deleteCaseHouse(id);
                return HttpResult.newCorrectResult(caseHouse.getUnitId());
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseHouse", method = {RequestMethod.POST}, name = "更新案例 房屋")
    public HttpResult saveAndUpdateCaseHouse(String formData) {
        JSONObject jsonObject = JSON.parseObject(formData);
        CaseHouse caseHouse = null;
        CaseHouseTrading caseHouseTrading = null;
        String jsonContent = null;
        try {
            try {
                jsonContent = jsonObject.getString("house");
                caseHouse = JSONObject.parseObject(jsonContent, CaseHouse.class);
                jsonContent = null;
                jsonContent = jsonObject.getString("trading");
                caseHouseTrading = JSONObject.parseObject(jsonContent, CaseHouseTrading.class);
            } catch (Exception e1) {
                logger.error(String.format("exception: %s", e1.getMessage()), e1);
                return HttpResult.newErrorResult("解析异常");
            }
            Integer id = caseHouseService.saveAndUpdateCaseHouse(caseHouse);
            if (id != null) {
                caseHouseService.initAndUpdateSon(0, id);
            }
            if (caseHouseTrading != null) {
                caseHouseTrading.setHouseId(id);
            }
            caseHouseTradingService.saveAndUpdateCaseHouseTrading(caseHouseTrading);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseHouseTradingLeaseAndSellDtoVos", method = {RequestMethod.GET}, name = "获取案例 房屋 出租或者出售 列表")
    public BootstrapTableVo getCaseHouseTradingLeaseAndSellDtoVos(String type, CaseHouseTradingLease caseHouseTradingLease, CaseHouseTradingSell caseHouseTradingSell) {
        if (caseHouseTradingLease == null) {
            caseHouseTradingLease = new CaseHouseTradingLease();
            caseHouseTradingLease.setHouseId(0);
        }
        if (caseHouseTradingSell == null) {
            caseHouseTradingSell = new CaseHouseTradingSell();
            caseHouseTradingSell.setHouseId(0);
        }
        BootstrapTableVo vo = null;
        vo = caseHouseTradingLeaseAndSellDtoService.getVoList(type, caseHouseTradingLease, caseHouseTradingSell);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveCaseHouseTradingLeaseAndSellDto", method = {RequestMethod.POST}, name = "更新案例 房屋 出租或者出售")
    public HttpResult saveCaseHouseTradingLeaseAndSellDto(CaseHouseTradingLeaseAndSellDto caseHouseTradingLeaseAndSellDto) {
        try {
            caseHouseTradingLeaseAndSellDtoService.saveCaseHouseTradingLeaseAndSellDto(caseHouseTradingLeaseAndSellDto);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeCaseHouseTradingLeaseAndSellDto", method = {RequestMethod.POST}, name = "删除案例 房屋 出租或者出售")
    public HttpResult removeCaseHouseTradingLeaseAndSellDto(Integer id, String type) {
        try {
            caseHouseTradingLeaseAndSellDtoService.remove(type, id);
            return HttpResult.newCorrectResult("success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initAndUpdateSon", method = {RequestMethod.POST}, name = "初始化子类")
    public HttpResult initAndUpdateSon() {
        try {
            caseHouseService.initAndUpdateSon(0, null);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseHouse", method = {RequestMethod.GET}, name = "房屋-- 信息自动补全")
    public HttpResult autoCompleteCaseHouse(String houseNumber, Integer unitId) {
        try {
            List<CustomCaseEntity> caseEntities = caseHouseService.autoCompleteCaseHouse(houseNumber, unitId);
            return HttpResult.newCorrectResult(caseEntities);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseHouseToBasic", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseHouseToBasic(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(caseHouseService.quoteCaseHouseToBasic(id, tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
