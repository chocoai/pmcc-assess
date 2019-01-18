package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseIntelligentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingEnvironmentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
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


    private BasicUnitService basicUnitService;
    private BasicUnitDecorateService basicUnitDecorateService;
    private BasicUnitElevatorService basicUnitElevatorService;
    private BasicUnitHuxingService basicUnitHuxingService;

    //构造器必须传入的参数
    private Integer planDetailsId;

    //===========================================获取的值===============================
    private BasicApply basicApply;

    public List<BasicEstateNetwork> getBasicEstateNetworkList()throws Exception {
        return basicEstateNetworkService.getBasicEstateNetworkList(getEstate().getId());
    }

    public List<BasicEstateParking> getBasicEstateParkingList()throws Exception {
        BasicEstateParking query = new BasicEstateParking();
        query.setEstateId(getEstate().getId());
        return basicEstateParkingService.basicEstateParkingList(query);
    }

    public List<BasicEstateSupply> getBasicEstateSupplyList() throws Exception{
        return basicEstateSupplyService.getBasicEstateSupplyList(getEstate().getId());
    }

    public List<BasicMatchingEducation> getBasicMatchingEducatioListn() throws Exception{
        return basicMatchingEducationService.getBasicMatchingEducationList(getEstate().getId());
    }

    public List<BasicMatchingEnvironmentVo> getBasicMatchingEnvironmentList() throws Exception{
        return basicMatchingEnvironmentService.getBasicMatchingEnvironmentVos(getEstate().getId());
    }

    public List<BasicMatchingFinance> getBasicMatchingFinanceList()throws Exception {
        return basicMatchingFinanceService.getBasicMatchingFinanceList(getEstate().getId());
    }

    public List<BasicMatchingLeisurePlace> getBasicMatchingLeisurePlaceList() throws Exception{
        return basicMatchingLeisurePlaceService.getBasicMatchingLeisurePlaceList(getEstate().getId());
    }

    public List<BasicMatchingMaterial> getBasicMatchingMaterialList()throws Exception {
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

    public BasicEstateLandState getBasicEstateLandState() throws Exception {
        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(getEstate().getId());
        if (estateLandState == null) {
            estateLandState = new BasicEstateLandState();
            logger.error("获取数据异常!",new Exception());
        }
        return estateLandState;
    }

    public BasicEstate getEstate() throws Exception {
        BasicEstate examineEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (examineEstate == null) {
            examineEstate = new BasicEstate();
            logger.error("获取数据异常!",new Exception());
        }
        return examineEstate;
    }

    public BasicBuilding getBasicBuilding() {
        BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(getBasicApply().getId());
        if (basicBuilding == null){
            basicBuilding = new BasicBuilding();
            logger.error("获取数据异常!",new Exception());
        }
        return basicBuilding;
    }

    public List<BasicBuildingFunction> getBasicBuildingFunctionList() {
        return basicBuildingFunctionService.getBasicBuildingFunctionList(getBasicBuilding().getId());
    }

    public List<BasicBuildingMaintenance> getBasicBuildingMaintenanceList() throws Exception{
        BasicBuildingMaintenance query = new BasicBuildingMaintenance();
        query.setBuildingId(getBasicBuilding().getId());
        return basicBuildingMaintenanceService.basicBuildingMaintenanceList(query);
    }

    public List<BasicBuildingOutfit> getBasicBuildingOutfitList() {
        return basicBuildingOutfitService.getBasicBuildingOutfitVos(getBasicBuilding().getId());
    }

    public List<BasicBuildingSurface> getBasicBuildingSurfaceList()throws Exception {
        BasicBuildingSurface query = new BasicBuildingSurface();
        query.setBuildingId(getBasicBuilding().getId());
        return basicBuildingSurfaceService.basicBuildingSurfaceList(query);
    }

    public BasicUnit getBasicUnit()throws Exception {
        BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
        if (basicUnit == null){
            basicUnit = new BasicUnit();
            logger.error("获取数据异常!",new Exception());
        }
        return basicUnit;
    }

    public List<BasicUnitHuxing> getBasicUnitHuxingList() throws Exception{
        BasicUnitHuxing query = new BasicUnitHuxing();
        query.setUnitId(getBasicUnit().getId());
        return basicUnitHuxingService.basicUnitHuxingList(query);
    }

    public List<BasicUnitElevator> getBasicUnitElevatorList()throws Exception {
        return basicUnitElevatorService.getBasicUnitElevatorList(getBasicUnit().getId());
    }

    public List<BasicUnitDecorate> getBasicUnitDecorateList()throws Exception {
        return basicUnitDecorateService.getBasicUnitDecorateList(getBasicUnit().getId());
    }

    public BasicHouse getBasicHouse() {
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(getBasicApply().getId());
        if (basicHouse == null){
            basicHouse = new BasicHouse();
            logger.error("获取数据异常!",new Exception());
        }
        return basicHouse;
    }

    public BasicHouseTrading getBasicTrading() {
        BasicHouseTrading basicHouseTrading = basicHouseTradingService.getTradingByHouseId(getBasicHouse().getId());
        if (basicHouseTrading == null){
            basicHouseTrading = new BasicHouseTrading();
            logger.error("获取数据异常!",new Exception());
        }
        return basicHouseTrading;
    }

    public List<BasicHouseCorollaryEquipment> getBasicHouseCorollaryEquipmentList() {
        return basicHouseCorollaryEquipmentService.getBasicHouseCorollaryEquipmentList(getBasicHouse().getId());
    }

    public List<BasicHouseRoom> getBasicHouseRoomList() {
        return basicHouseRoomService.getBasicHouseRoomList(getBasicHouse().getId());
    }

    public List<BasicHouseTrading> getBasicHouseTradingList()throws Exception {
        BasicHouseTrading query = new BasicHouseTrading();
        query.setHouseId(getBasicHouse().getId());
        return basicHouseTradingService.basicHouseTradingList(query);
    }

    public List<BasicHouseWater> getBasicHouseWaterList() throws Exception{
        return basicHouseWaterService.getBasicHouseWaterList(getBasicHouse().getId());
    }

    public List<BasicHouseWaterDrain> getBasicHouseWaterDrainList()throws Exception {
        return basicHouseWaterDrainService.getBasicHouseWaterDrainList(getBasicHouse().getId());
    }

    public List<BasicHouseRoomDecorate> getBasicHouseRoomDecorateList(Integer roomId)throws Exception {
        return basicHouseRoomDecorateService.getHouseRoomDecorateList(roomId);
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
        this.planDetailsId = planDetailsId;
        this.basicApplyService = SpringContextUtils.getBean(BasicApplyService.class);

        this.basicHouseService = SpringContextUtils.getBean(BasicHouseService.class);
        this.basicHouseTradingService = SpringContextUtils.getBean(BasicHouseTradingService.class);
        this.basicHouseRoomService = SpringContextUtils.getBean(BasicHouseRoomService.class);
        this.basicHouseEquipmentService = SpringContextUtils.getBean(BasicHouseEquipmentService.class);
        this.basicHouseIntelligentService = SpringContextUtils.getBean(BasicHouseIntelligentService.class);
        this.basicHouseRoomDecorateService = SpringContextUtils.getBean(BasicHouseRoomDecorateService.class);
        this.basicHouseCorollaryEquipmentService = SpringContextUtils.getBean(BasicHouseCorollaryEquipmentService.class);
        this.basicHouseWaterService = SpringContextUtils.getBean(BasicHouseWaterService.class);

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

        BasicApply apply = basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId);
        if (apply == null) {
            apply = new BasicApply();
            apply.setPlanDetailsId(planDetailsId);
            logger.error("获取数据异常!",new Exception());
        }
        this.basicApply = apply;
    }

    private GenerateBaseExamineService() {
    }
}
