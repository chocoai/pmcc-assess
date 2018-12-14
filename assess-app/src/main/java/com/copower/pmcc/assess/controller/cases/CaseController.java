package com.copower.pmcc.assess.controller.cases;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingVo;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/case", name = "案例 基础")
public class CaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBaseHouseService caseBaseHouseService;

    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
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
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseUnitDecorateService caseUnitDecorateService;
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;

    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseBuildingService caseBuildingService;

    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;

    @RequestMapping(value = "/areaCaseMap", name = "区域楼盘案例", method = {RequestMethod.GET})
    public ModelAndView areaEstateCaseMap() {
        String view = "/case/areaCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            modelAndView.addObject("mapList", JSON.toJSONString(caseEstateTaggingService.mapDtoList(null, EstateTaggingTypeEnum.ESTATE.getKey())));
        } catch (Exception e1) {
            logger.error("区域楼盘案例获取经度和纬度出错!", e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/estateCaseMap", name = "区域楼盘案例-", method = {RequestMethod.GET})
    public ModelAndView estateCaseMap(Integer estateId) {
        String view = "/case/estateCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            if (estateId != null) {
                modelAndView.addObject("mapTree", JSON.toJSONString(caseEstateTaggingService.getCaseEstateTaggingDto(estateId)));
                modelAndView.addObject("caseEstate",caseEstateService.getCaseEstateById(estateId));
            }
        } catch (Exception e1) {
            logger.error("区域楼盘案例获取经度和纬度出错!", e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/estateSearch", name = "楼盘案例查询", method = {RequestMethod.GET})
    public ModelAndView estateSearch() {
        String view = "/case/estateSearch";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/houseSearch", name = "房屋案例查询", method = {RequestMethod.GET})
    public ModelAndView houseSearch() {
        String view = "/case/houseSearch";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/findHouseView", name = "房屋查询到的详情", method = {RequestMethod.GET})
    public ModelAndView findHouse(Integer id) {
        String view = "/case/searchHouseDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id != null) {
            CaseBaseHouse caseBaseHouse = caseBaseHouseService.getBaseHouseById(id);
            if (caseBaseHouse != null) {
                modelAndView.addObject("caseBaseHouse", caseBaseHouse);
                if (caseBaseHouse.getCaseHouseId() != null) {
                    CaseHouse caseHouse = caseHouseService.getCaseHouseById(caseBaseHouse.getCaseHouseId());
                    if (caseHouse != null) {
                        modelAndView.addObject("caseHouse", caseHouseService.getCaseHouseVo(caseHouse));
                        CaseHouseTrading query = new CaseHouseTrading();
                        query.setHouseId(caseHouse.getId());
                        List<CaseHouseTrading> caseHouseTradingList = caseHouseTradingService.caseHouseTradingList(query);
                        if (!org.springframework.util.ObjectUtils.isEmpty(caseHouseTradingList)) {
                            modelAndView.addObject("caseHouseTrading", caseHouseTradingService.getCaseHouseTradingVo(caseHouseTradingList.get(0)));
                        }
                        if (caseHouse.getUnitId() != null) {
                            CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseHouse.getUnitId());
                            if (caseUnit != null) {
                                modelAndView.addObject("caseUnit", caseUnit);
                                CaseBuildingMain caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(caseUnit.getBuildingMainId());
                                if (caseBuildingMain != null) {
                                    modelAndView.addObject("caseBuildingMain", caseBuildingMain);
                                }
                            }
                        }
                    }
                }
                modelAndView.addObject("hasHouseFaceStreetData", caseHouseFaceStreetService.hasHouseFaceStreetData(id));
                modelAndView.addObject("hasHouseCorollaryEquipmentData", caseHouseCorollaryEquipmentService.hasHouseCorollaryEquipmentData(id));
                modelAndView.addObject("hasHouseIntelligentData", caseHouseIntelligentService.hasHouseIntelligentData(id));
                modelAndView.addObject("hasHouseRoomData", caseHouseRoomService.hasHouseRoomData(id));
                modelAndView.addObject("hasHouseWaterData", caseHouseWaterService.hasHouseWaterData(id));
                modelAndView.addObject("hasHouseEquipmentAirConditioner", caseHouseEquipmentService.hasHouseEquipmentData(id, ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey()));
                modelAndView.addObject("hasHouseEquipmentHeating", caseHouseEquipmentService.hasHouseEquipmentData(id, ExamineHouseEquipmentTypeEnum.houseHeating.getKey()));
                modelAndView.addObject("hasHouseEquipmentNewWind", caseHouseEquipmentService.hasHouseEquipmentData(id, ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()));
            }
        }
        return modelAndView;
    }


    @RequestMapping(value = "/findUnitView", name = "单元查询到的详情", method = {RequestMethod.GET})
    public ModelAndView findUnitView(Integer id) {
        String view = "/case/searchUnitDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseUnit caseUnit = null;
        caseUnit = caseUnitService.getCaseUnitById(id);
        modelAndView.addObject("caseUnit", caseUnit);
        modelAndView.addObject("hasUnitDecorateData", caseUnitDecorateService.hasUnitDecorateData(id));
        modelAndView.addObject("hasUnitElevatorData", caseUnitElevatorService.hasUnitElevatorData(id));
        modelAndView.addObject("hasUnitHuxingData", caseUnitHuxingService.hasUnitHuxingData(id));
        return modelAndView;
    }

    @RequestMapping(value = "/findBuildView", name = "楼栋查询到的详情", method = {RequestMethod.GET})
    public ModelAndView findBuildView(Integer id) {
        String view = "/case/searchBuildDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseBuildingMain caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(id);
        modelAndView.addObject("caseBuildingMain", caseBuildingMain);
        if (caseBuildingMain != null) {
            List<CaseBuildingVo> caseBuildingList = caseBuildingService.getCaseBuildingListByMainId(caseBuildingMain.getId());
            if (CollectionUtils.isEmpty(caseBuildingList)) {
                modelAndView.addObject("caseBuilding", new CaseBuildingVo());
            } else {
                modelAndView.addObject("caseBuilding", caseBuildingList.get(0));
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/findEstate", name = "楼盘查询到的详情", method = {RequestMethod.GET})
    public ModelAndView findEstate(Integer id) {
        String view = "/case/searchEstateDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id != null && id.intValue() != 0) {
            //楼盘 土地实体情况
            CaseEstateLandState caseEstateLandState = new CaseEstateLandState();
            caseEstateLandState.setEstateId(id);
            List<CaseEstateLandState> caseEstateLandStateList = caseEstateLandStateService.getCaseEstateLandStateList(caseEstateLandState);
            if (!ObjectUtils.isEmpty(caseEstateLandStateList)) {
                modelAndView.addObject("caseEstateLandState", caseEstateLandStateService.getCaseEstateLandStateVo(caseEstateLandStateList.get(0)));
            }
            //楼盘 基本信息
            CaseEstate caseEstate = caseEstateService.getCaseEstateById(id);
            modelAndView.addObject("caseEstate", caseEstateService.getCaseEstateVo(caseEstate));
        }
        modelAndView.addObject("hasEstateNetworkData", caseEstateNetworkService.hasEstateNetworkData(id));
        modelAndView.addObject("hasEstateParkingData", caseEstateParkingService.hasEstateParkingData(id));
        modelAndView.addObject("hasEstateSupplyGas", caseEstateSupplyService.hasEstateSupplyData(id, ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName()));
        modelAndView.addObject("hasEstateSupplyHeating", caseEstateSupplyService.hasEstateSupplyData(id, ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName()));
        modelAndView.addObject("hasEstateSupplyPower", caseEstateSupplyService.hasEstateSupplyData(id, ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName()));
        modelAndView.addObject("hasEstateSupplyWater", caseEstateSupplyService.hasEstateSupplyData(id, ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName()));
        modelAndView.addObject("hasEstateDrainWater", caseEstateSupplyService.hasEstateSupplyData(id, ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName()));

        modelAndView.addObject("hasMatchingEducationData", caseMatchingEducationService.hasMatchingEducationData(id));
        modelAndView.addObject("hasMatchingEnvironmentData", caseMatchingEnvironmentService.hasMatchingEnvironmentData(id));
        modelAndView.addObject("hasMatchingFinanceData", caseMatchingFinanceService.hasMatchingFinanceData(id));
        modelAndView.addObject("hasMatchingLeisurePlaceMarket", caseMatchingLeisurePlaceService.hasMatchingLeisurePlaceData(id, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey()));
        modelAndView.addObject("hasMatchingLeisurePlaceRecreation", caseMatchingLeisurePlaceService.hasMatchingLeisurePlaceData(id, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey()));
        modelAndView.addObject("hasMatchingLeisurePlaceRestaurant", caseMatchingLeisurePlaceService.hasMatchingLeisurePlaceData(id, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey()));
        modelAndView.addObject("hasMatchingMaterialData", caseMatchingMaterialService.hasMatchingMaterialData(id));
        modelAndView.addObject("hasMatchingMedicalData", caseMatchingMedicalService.hasMatchingMedicalData(id));

        modelAndView.addObject("hasMatchingTrafficTransit", caseMatchingTrafficService.hasMatchingTrafficData(id, ExamineMatchingTrafficTypeEnum.TRANSIT.getName()));
        modelAndView.addObject("hasMatchingTrafficMetro", caseMatchingTrafficService.hasMatchingTrafficData(id, ExamineMatchingTrafficTypeEnum.METRO.getName()));
        modelAndView.addObject("hasMatchingTrafficMainRoad", caseMatchingTrafficService.hasMatchingTrafficData(id, ExamineMatchingTrafficTypeEnum.MainRoad.getName()));
        modelAndView.addObject("hasMatchingTrafficMainConversion", caseMatchingTrafficService.hasMatchingTrafficData(id, ExamineMatchingTrafficTypeEnum.MainConversion.getName()));
        modelAndView.addObject("hasMatchingTrafficTrafficHub", caseMatchingTrafficService.hasMatchingTrafficData(id, ExamineMatchingTrafficTypeEnum.TrafficHub.getName()));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableCaseBaseHouseVo", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getBootstrapTableVo(BigDecimal tradingUnitPriceStart, BigDecimal tradingUnitPriceEnd, String tradingTimeStart, String tradingTimeEnd, CaseBaseHouse caseBaseHouse) {
        BootstrapTableVo vo = null;
        try {
            Date dateA = null;
            Date dateB = null;
            if (StringUtils.isNotBlank(tradingTimeStart)) {
                dateA = DateUtils.parse(tradingTimeStart);
            }
            if (StringUtils.isNotBlank(tradingTimeEnd)) {
                dateB = DateUtils.parse(tradingTimeEnd);
            }
            vo = caseBaseHouseService.getBootstrapTableVo(tradingUnitPriceStart, tradingUnitPriceEnd, dateA, dateB, caseBaseHouse);
        } catch (Exception e1) {
            logger.error("", e1);
        }
        return vo;
    }
}
