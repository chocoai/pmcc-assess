package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/18 16:19
 * @Description:案例或者查勘元数据
 */
public class GenerateBaseExamineService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private BasicApplyService basicApplyService;
    private BaseDataDicService baseDataDicService;

    private BasicBuildingService basicBuildingService;
    private BasicBuildingFunctionService basicBuildingFunctionService;
    private BasicBuildingOutfitService basicBuildingOutfitService;
    private BasicBuildingMaintenanceService basicBuildingMaintenanceService;
    private BasicBuildingSurfaceService basicBuildingSurfaceService;

    private BasicHouseService basicHouseService;
    private BasicHouseTradingService basicHouseTradingService;
    private BasicHouseRoomService basicHouseRoomService;
    private BasicHouseEquipmentService basicHouseEquipmentService;
    private BasicHouseIntelligentService basicHouseIntelligentService;
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    private BasicHouseWaterService basicHouseWaterService;
    private BasicHouseWaterDrainService basicHouseWaterDrainService;
    private BasicHouseFaceStreetService basicHouseFaceStreetService;

    private BasicEstateService basicEstateService;
    private BasicEstateNetworkService basicEstateNetworkService;
    private BasicMatchingEducationService basicMatchingEducationService;
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    private BasicMatchingMedicalService basicMatchingMedicalService;
    private BasicMatchingFinanceService basicMatchingFinanceService;
    private BasicEstateSupplyService basicEstateSupplyService;
    private BasicMatchingTrafficService basicMatchingTrafficService;
    private BasicMatchingEnvironmentService basicMatchingEnvironmentService;
    private BasicEstateLandStateService basicEstateLandStateService;
    private BasicEstateParkingService basicEstateParkingService;
    private BasicMatchingMaterialService basicMatchingMaterialService;
    private DataBlockService dataBlockService;


    private BasicUnitService basicUnitService;
    private BasicUnitDecorateService basicUnitDecorateService;
    private BasicUnitElevatorService basicUnitElevatorService;
    private BasicUnitHuxingService basicUnitHuxingService;

    //构造器必须传入的参数
    private Integer planDetailsId;

    //===========================================获取的值===============================
    private BasicApply basicApply;

    /**
     * 取得版块信息
     * @return
     * @throws Exception
     */
    public DataBlockVo getByDataBlock()throws Exception{
        DataBlockVo dataBlockVo = dataBlockService.getDataBlockVo(dataBlockService.getDataBlockById(getEstate().getBlockId()));
        if (dataBlockVo == null){
            dataBlockVo = new DataBlockVo();
        }
        return dataBlockVo;
    }

    public List<BasicEstateNetwork> getBasicEstateNetworkList() throws Exception {
        return basicEstateNetworkService.getBasicEstateNetworkList(getEstate().getId());
    }

    public List<BasicEstateParking> getBasicEstateParkingList() throws Exception {
        BasicEstateParking query = new BasicEstateParking();
        query.setEstateId(getEstate().getId());
        return basicEstateParkingService.basicEstateParkingList(query);
    }

    public List<BasicEstateSupply> getBasicEstateSupplyList() throws Exception {
        return basicEstateSupplyService.getBasicEstateSupplyList(getEstate().getId());
    }

    public List<BasicMatchingEducation> getBasicMatchingEducatioListn() throws Exception {
        return basicMatchingEducationService.getBasicMatchingEducationList(getEstate().getId());
    }

    public List<BasicMatchingEnvironmentVo> getBasicMatchingEnvironmentList() throws Exception {
        return basicMatchingEnvironmentService.getBasicMatchingEnvironmentVos(getEstate().getId());
    }

    public List<BasicMatchingFinanceVo> getBasicMatchingFinanceList() throws Exception {
        List<BasicMatchingFinanceVo> vos = Lists.newArrayList();
        List<BasicMatchingFinance> financeList = basicMatchingFinanceService.getBasicMatchingFinanceList(getEstate().getId());
        if (CollectionUtils.isNotEmpty(financeList)){
            financeList.stream().forEach(basicMatchingFinance -> {
                vos.add(basicMatchingFinanceService.getBasicMatchingFinanceVo(basicMatchingFinance));
            });
        }
        return vos;
    }

    public List<BasicMatchingLeisurePlace> getBasicMatchingLeisurePlaceList() throws Exception {
        return basicMatchingLeisurePlaceService.getBasicMatchingLeisurePlaceList(getEstate().getId());
    }

    public List<BasicMatchingMaterial> getBasicMatchingMaterialList() throws Exception {
        BasicMatchingMaterial query = new BasicMatchingMaterial();
        query.setEstateId(getEstate().getId());
        return basicMatchingMaterialService.basicMatchingMaterialList(query);
    }

    public List<BasicMatchingMedical> getBasicMatchingMedicalList() throws Exception {
        return basicMatchingMedicalService.getBasicMatchingMedicalList(getEstate().getId());
    }

    public List<BasicMatchingTrafficVo> getBasicMatchingTrafficList() throws Exception {
        return basicMatchingTrafficService.getBasicMatchingTrafficVos(getEstate().getId());
    }

    public BasicEstateLandStateVo getBasicEstateLandState() throws Exception {
        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(getEstate().getId());
        if (estateLandState == null) {
            estateLandState = new BasicEstateLandState();
            logger.error("获取数据异常!", new Exception());
        }
        BasicEstateLandStateVo vo = basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState);

        return vo;
    }

    public BasicEstate getEstate() throws Exception {
        BasicEstate examineEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (examineEstate == null) {
            examineEstate = new BasicEstate();
            logger.error("获取数据异常!", new Exception());
        }
        return examineEstate;
    }

    public BasicBuilding getBasicBuilding() {
        BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(getBasicApply().getId());
        if (basicBuilding == null) {
            basicBuilding = new BasicBuilding();
            logger.error("获取数据异常!", new Exception());
        }
        return basicBuilding;
    }

    public List<BasicBuildingFunction> getBasicBuildingFunctionList() {
        return basicBuildingFunctionService.getBasicBuildingFunctionList(getBasicBuilding().getId());
    }

    public List<BasicBuildingMaintenance> getBasicBuildingMaintenanceList() throws Exception {
        BasicBuildingMaintenance query = new BasicBuildingMaintenance();
        query.setBuildingId(getBasicBuilding().getId());
        return basicBuildingMaintenanceService.basicBuildingMaintenanceList(query);
    }

    public List<BasicBuildingOutfitVo> getBasicBuildingOutfitList() {
        return basicBuildingOutfitService.getBasicBuildingOutfitVos(getBasicBuilding().getId());
    }

    public List<BasicBuildingSurface> getBasicBuildingSurfaceList() throws Exception {
        BasicBuildingSurface query = new BasicBuildingSurface();
        query.setBuildingId(getBasicBuilding().getId());
        return basicBuildingSurfaceService.basicBuildingSurfaceList(query);
    }

    public BasicUnit getBasicUnit() throws Exception {
        BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
        if (basicUnit == null) {
            basicUnit = new BasicUnit();
            logger.error("获取数据异常!", new Exception());
        }
        return basicUnit;
    }

    public List<BasicUnitHuxing> getBasicUnitHuxingList() throws Exception {
        BasicUnitHuxing query = new BasicUnitHuxing();
        query.setUnitId(getBasicUnit().getId());
        return basicUnitHuxingService.basicUnitHuxingList(query);
    }

    public List<BasicUnitElevator> getBasicUnitElevatorList() throws Exception {
        return basicUnitElevatorService.getBasicUnitElevatorList(getBasicUnit().getId());
    }

    public List<BasicUnitDecorateVo> getBasicUnitDecorateList() throws Exception {
        return basicUnitDecorateService.getBasicUnitDecorateList(getBasicUnit().getId());
    }

    public BasicHouse getBasicHouse() {
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(getBasicApply().getId());
        if (basicHouse == null) {
            basicHouse = new BasicHouse();
            logger.error("获取数据异常!", new Exception());
        }
        return basicHouse;
    }

    public BasicHouseTrading getBasicTrading() {
        BasicHouseTrading basicHouseTrading = basicHouseTradingService.getTradingByHouseId(getBasicHouse().getId());
        if (basicHouseTrading == null) {
            basicHouseTrading = new BasicHouseTrading();
            logger.error("获取数据异常!", new Exception());
        }
        return basicHouseTrading;
    }

    public List<BasicHouseCorollaryEquipment> getBasicHouseCorollaryEquipmentList() {
        return basicHouseCorollaryEquipmentService.getBasicHouseCorollaryEquipmentList(getBasicHouse().getId());
    }

    public List<BasicHouseRoom> getBasicHouseRoomList() {
        return basicHouseRoomService.getBasicHouseRoomList(getBasicHouse().getId());
    }

    public List<BasicHouseTrading> getBasicHouseTradingList() throws Exception {
        BasicHouseTrading query = new BasicHouseTrading();
        query.setHouseId(getBasicHouse().getId());
        return basicHouseTradingService.basicHouseTradingList(query);
    }

    public List<BasicHouseWater> getBasicHouseWaterList() throws Exception {
        return basicHouseWaterService.getBasicHouseWaterList(getBasicHouse().getId());
    }

    public List<BasicHouseWaterDrain> getBasicHouseWaterDrainList() throws Exception {
        return basicHouseWaterDrainService.getBasicHouseWaterDrainList(getBasicHouse().getId());
    }

    public List<BasicHouseRoomDecorateVo> getBasicHouseRoomDecorateList(Integer roomId) throws Exception {
        return basicHouseRoomDecorateService.getHouseRoomDecorateList(roomId);
    }

    public List<BasicHouseFaceStreetVo> getBasicHouseFaceStreetList() throws Exception {
        List<BasicHouseFaceStreetVo> vos = Lists.newArrayList();
        BasicHouseFaceStreet query = new BasicHouseFaceStreet();
        query.setHouseId(getBasicHouse().getId());
        List<BasicHouseFaceStreet> basicHouseFaceStreetList = basicHouseFaceStreetService.basicHouseFaceStreetList(query);
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetList)) {
            for (BasicHouseFaceStreet basicHouseFaceStreet : basicHouseFaceStreetList) {
                vos.add(basicHouseFaceStreetService.getBasicHouseFaceStreetVo(basicHouseFaceStreet));
            }
        }
        return vos;
    }

    public List<BasicHouseIntelligentVo> getBasicHouseIntelligentList() {
        return basicHouseIntelligentService.getBasicHouseIntelligentVos(getBasicHouse().getId());
    }

    public List<BasicHouseEquipment> getBasicHouseEquipmentList() {
        return basicHouseEquipmentService.getBasicHouseEquipmentList(getBasicHouse().getId());
    }

    public BasicApply getBasicApply() {
        return basicApply;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public GenerateBaseExamineService(Integer planDetailsId) {
        init();
        this.planDetailsId = planDetailsId;
        BasicApply apply = basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId);
        if (apply == null) {
            apply = new BasicApply();
            apply.setPlanDetailsId(planDetailsId);
            logger.error("获取数据异常!", new Exception());
        }
        this.basicApply = apply;
    }

    public GenerateBaseExamineService(BasicApply apply){
        init();
        this.planDetailsId = apply.getPlanDetailsId();
        this.basicApply = apply;
    }

    private void init(){
        this.basicApplyService = SpringContextUtils.getBean(BasicApplyService.class);

        this.basicHouseService = SpringContextUtils.getBean(BasicHouseService.class);
        this.basicHouseTradingService = SpringContextUtils.getBean(BasicHouseTradingService.class);
        this.basicHouseRoomService = SpringContextUtils.getBean(BasicHouseRoomService.class);
        this.basicHouseEquipmentService = SpringContextUtils.getBean(BasicHouseEquipmentService.class);
        this.basicHouseIntelligentService = SpringContextUtils.getBean(BasicHouseIntelligentService.class);
        this.basicHouseRoomDecorateService = SpringContextUtils.getBean(BasicHouseRoomDecorateService.class);
        this.basicHouseCorollaryEquipmentService = SpringContextUtils.getBean(BasicHouseCorollaryEquipmentService.class);
        this.basicHouseWaterService = SpringContextUtils.getBean(BasicHouseWaterService.class);
        this.basicHouseFaceStreetService = SpringContextUtils.getBean(BasicHouseFaceStreetService.class);

        this.basicEstateService = SpringContextUtils.getBean(BasicEstateService.class);
        this.basicMatchingTrafficService = SpringContextUtils.getBean(BasicMatchingTrafficService.class);
        this.basicMatchingEnvironmentService = SpringContextUtils.getBean(BasicMatchingEnvironmentService.class);
        this.basicEstateLandStateService = SpringContextUtils.getBean(BasicEstateLandStateService.class);
        this.basicEstateSupplyService = SpringContextUtils.getBean(BasicEstateSupplyService.class);
        this.basicEstateNetworkService = SpringContextUtils.getBean(BasicEstateNetworkService.class);
        this.basicMatchingEducationService = SpringContextUtils.getBean(BasicMatchingEducationService.class);
        this.basicMatchingLeisurePlaceService = SpringContextUtils.getBean(BasicMatchingLeisurePlaceService.class);
        this.basicMatchingMedicalService = SpringContextUtils.getBean(BasicMatchingMedicalService.class);
        this.basicMatchingFinanceService = SpringContextUtils.getBean(BasicMatchingFinanceService.class);

        this.basicBuildingFunctionService = SpringContextUtils.getBean(BasicBuildingFunctionService.class);
        this.basicBuildingService = SpringContextUtils.getBean(BasicBuildingService.class);
        this.basicBuildingOutfitService = SpringContextUtils.getBean(BasicBuildingOutfitService.class);
        this.basicBuildingMaintenanceService = SpringContextUtils.getBean(BasicBuildingMaintenanceService.class);
        this.basicBuildingSurfaceService = SpringContextUtils.getBean(BasicBuildingSurfaceService.class);

        this.basicUnitHuxingService = SpringContextUtils.getBean(BasicUnitHuxingService.class);
        this.basicUnitService = SpringContextUtils.getBean(BasicUnitService.class);
        this.basicUnitDecorateService = SpringContextUtils.getBean(BasicUnitDecorateService.class);
        this.basicHouseWaterDrainService = SpringContextUtils.getBean(BasicHouseWaterDrainService.class);
        this.basicUnitElevatorService = SpringContextUtils.getBean(BasicUnitElevatorService.class);

        this.basicEstateParkingService = SpringContextUtils.getBean(BasicEstateParkingService.class);
        this.basicMatchingEducationService = SpringContextUtils.getBean(BasicMatchingEducationService.class);
        this.basicMatchingMaterialService = SpringContextUtils.getBean(BasicMatchingMaterialService.class);
        this.dataBlockService = SpringContextUtils.getBean(DataBlockService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
    }

    private GenerateBaseExamineService() {
    }

    public void getCommonSupply(StringBuilder stringBuilder, BasicEstateSupply supply) {
        stringBuilder.append(StringUtils.isEmpty(supply.getName()) ? "" : String.format("供应商名称:%s；", supply.getName()));
        stringBuilder.append(supply.getLineGrade() == null ? "" : String.format("供应保障等级:%s；", baseDataDicService.getNameById(supply.getLineGrade())));
        stringBuilder.append(supply.getReputation() == null ? "" : String.format("供应商信誉:%s；", baseDataDicService.getNameById(supply.getReputation())));
        stringBuilder.append(supply.getGrade() == null ? "" : String.format("供应商等级:%s；", baseDataDicService.getNameById(supply.getGrade())));
        stringBuilder.append(StringUtils.isEmpty(supply.getPower()) ? "" : String.format("供应量或功率:%s；", supply.getPower()));
    }

    public void getCommonBuildingFunction(List<BasicBuildingFunction> buildingFunctions, StringBuilder stringBuilder, BaseDataDic heatPreservationDic) {
        for (BasicBuildingFunction buildingFunction : buildingFunctions) {
            if (buildingFunction.getType() != null && buildingFunction.getType().equals(heatPreservationDic.getId())) {
                stringBuilder.append(buildingFunction.getDecorationPart() == null ? "" : String.format("装修部位:%s；", baseDataDicService.getNameById(buildingFunction.getDecorationPart())));
                stringBuilder.append(buildingFunction.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s；", baseDataDicService.getNameById(buildingFunction.getDecoratingMaterial())));
                stringBuilder.append(buildingFunction.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s；", baseDataDicService.getNameById(buildingFunction.getConstructionTechnology())));
                stringBuilder.append(buildingFunction.getMaterialPrice() == null ? "" : String.format("材料价格区间:%s；", baseDataDicService.getNameById(buildingFunction.getMaterialPrice())));
            }
        }
    }

    public void getEnvironmentString(StringBuilder stringBuilder, BaseDataDic sceneryDic, BasicMatchingEnvironment examineMatchingEnvironment) {
        if (StringUtils.equals(String.valueOf(sceneryDic.getId()), examineMatchingEnvironment.getType())) {
            stringBuilder.append(baseDataDicService.getNameById(examineMatchingEnvironment.getCategory()));
            stringBuilder.append(baseDataDicService.getNameById(examineMatchingEnvironment.getInfluenceDegree()));
            stringBuilder.append("；");
        }
    }
}
