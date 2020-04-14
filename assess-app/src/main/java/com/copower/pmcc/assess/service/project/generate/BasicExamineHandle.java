package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019-11-28.
 * 案例或者查勘元数据
 */
public class BasicExamineHandle implements Serializable {
    private BaseService baseService;

    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    private BasicApplyBatchService basicApplyBatchService;
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
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;

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
    private BasicEstateInvestigationService basicEstateInvestigationService;


    private BasicUnitService basicUnitService;
    private BasicUnitDecorateService basicUnitDecorateService;
    private BasicUnitElevatorService basicUnitElevatorService;
    private BasicUnitHuxingService basicUnitHuxingService;
    private BasicHouseTradingLeaseAndSellDtoService basicHouseTradingLeaseAndSellDtoService;
    private BasicBuildingPropertyServiceItemService basicBuildingPropertyServiceItemService;
    private BasicHouseHuxingPriceService basicHouseHuxingPriceService;

    private BasicApplyBatch basicApplyBatch;
    private List<BasicApplyBatchDetail> basicApplyBatchDetailList;

    /**
     * 取得版块信息
     *
     * @return
     * @throws Exception
     */
    public DataBlockVo getByDataBlock() throws Exception {
        DataBlockVo dataBlockVo = dataBlockService.getDataBlockVo(dataBlockService.getDataBlockById(getEstate().getBlockId()));
        if (dataBlockVo == null) {
            dataBlockVo = new DataBlockVo();
        }
        return dataBlockVo;
    }

    public List<BasicEstateNetwork> getBasicEstateNetworkList() {
        return basicEstateNetworkService.getBasicEstateNetworkList(getEstate().getId());
    }

    public List<BasicEstateParking> getBasicEstateParkingList() {
        BasicEstateParking query = new BasicEstateParking();
        query.setEstateId(getEstate().getId());
        return basicEstateParkingService.basicEstateParkingList(query);
    }

    public List<BasicEstateSupply> getBasicEstateSupplyList() {
        return basicEstateSupplyService.getBasicEstateSupplyList(getEstate().getId());
    }

    public List<BasicMatchingEducation> getBasicMatchingEducatioListn() {
        return basicMatchingEducationService.getBasicMatchingEducationList(getEstate().getId());
    }

    public List<BasicMatchingEnvironmentVo> getBasicMatchingEnvironmentList() {
        return basicMatchingEnvironmentService.getBasicMatchingEnvironmentVos(getEstate().getId());
    }

    public List<BasicMatchingFinanceVo> getBasicMatchingFinanceList() {
        List<BasicMatchingFinanceVo> vos = Lists.newArrayList();
        List<BasicMatchingFinance> financeList = basicMatchingFinanceService.getBasicMatchingFinanceList(getEstate().getId());
        if (CollectionUtils.isNotEmpty(financeList)) {
            financeList.stream().forEach(basicMatchingFinance -> {
                vos.add(basicMatchingFinanceService.getBasicMatchingFinanceVo(basicMatchingFinance));
            });
        }
        return vos;
    }

    public List<BasicMatchingLeisurePlace> getBasicMatchingLeisurePlaceList() {
        return basicMatchingLeisurePlaceService.getBasicMatchingLeisurePlaceList(getEstate().getId());
    }

    public List<BasicMatchingMaterial> getBasicMatchingMaterialList() {
        BasicMatchingMaterial query = new BasicMatchingMaterial();
        query.setEstateId(getEstate().getId());
        return basicMatchingMaterialService.basicMatchingMaterialList(query);
    }

    public List<BasicMatchingMedical> getBasicMatchingMedicalList() {
        return basicMatchingMedicalService.getBasicMatchingMedicalList(getEstate().getId());
    }

    public List<BasicMatchingTrafficVo> getBasicMatchingTrafficList() {
        return basicMatchingTrafficService.getBasicMatchingTrafficVos(getEstate().getId());
    }
    //_______________________________________________________||_________________________________________________________

    public List<BasicBuildingFunction> getBasicBuildingFunctionAll() {
        List<BasicBuildingFunction> functionList = new ArrayList<>();
        List<BasicBuilding> basicBuildingList = getBasicBuildingAll();
        if (CollectionUtils.isNotEmpty(basicBuildingList)) {
            for (BasicBuilding target : basicBuildingList) {
                functionList.addAll(basicBuildingFunctionService.getBasicBuildingFunctionList(target.getId()));
            }
        }
        return functionList;
    }

    public List<BasicBuildingFunction> getBasicBuildingFunctionList(Integer buildingId) {
        return basicBuildingFunctionService.getBasicBuildingFunctionList(buildingId);
    }

    public List<BasicBuildingMaintenance> getBasicBuildingMaintenanceAll() throws Exception {
        List<BasicBuildingMaintenance> maintenanceList = new ArrayList<>();
        List<BasicBuilding> basicBuildingList = getBasicBuildingAll();
        if (CollectionUtils.isNotEmpty(basicBuildingList)) {
            for (BasicBuilding target : basicBuildingList) {
                BasicBuildingMaintenance query = new BasicBuildingMaintenance();
                query.setBuildingId(target.getId());
                maintenanceList.addAll(basicBuildingMaintenanceService.basicBuildingMaintenanceList(query));
            }
        }
        return maintenanceList;
    }

    public List<BasicBuildingOutfitVo> getBasicBuildingOutfitAll() {
        List<BasicBuildingOutfitVo> outfitVoList = new ArrayList<>();
        List<BasicBuilding> basicBuildingList = getBasicBuildingAll();
        if (CollectionUtils.isNotEmpty(basicBuildingList)) {
            for (BasicBuilding target : basicBuildingList) {
                outfitVoList.addAll(basicBuildingOutfitService.getBasicBuildingOutfitVos(target.getId()));
            }
        }
        return outfitVoList;
    }

    public List<BasicBuildingSurface> getBasicBuildingSurfaceAll() {
        List<BasicBuildingSurface> surfaceList = new ArrayList<>();
        List<BasicBuilding> basicBuildingList = getBasicBuildingAll();
        if (CollectionUtils.isNotEmpty(basicBuildingList)) {
            for (BasicBuilding target : basicBuildingList) {
                BasicBuildingSurface query = new BasicBuildingSurface();
                query.setBuildingId(target.getId());
                surfaceList.addAll(basicBuildingSurfaceService.basicBuildingSurfaceList(query));
            }
        }
        return surfaceList;
    }


    //____________________________________||_____________________________________________________


    public List<BasicUnitHuxing> getBasicUnitHuxingAll() {
        List<BasicUnitHuxing> unitHuxingList = new ArrayList<>();
        List<BasicUnit> unitList = getBasicUnitAll();
        if (CollectionUtils.isNotEmpty(unitList)) {
            for (BasicUnit target : unitList) {
                BasicUnitHuxing query = new BasicUnitHuxing();
                query.setUnitId(target.getId());
                unitHuxingList.addAll(basicUnitHuxingService.basicUnitHuxingList(query));
            }
        }
        return unitHuxingList;
    }

    public List<BasicUnitElevator> getBasicUnitElevatorAll() {
        List<BasicUnitElevator> unitElevatorList = new ArrayList<>();
        List<BasicUnit> unitList = getBasicUnitAll();
        if (CollectionUtils.isNotEmpty(unitList)) {
            for (BasicUnit target : unitList) {
                unitElevatorList.addAll(basicUnitElevatorService.getBasicUnitElevatorList(target.getId()));
            }
        }
        return unitElevatorList;
    }

    public List<BasicUnitElevator> getBasicUnitElevatorList(Integer unitId) {
        List<BasicUnitElevator> unitElevatorList = getBasicUnitElevatorAll();
        if (CollectionUtils.isNotEmpty(unitElevatorList)) {
            Iterator<BasicUnitElevator> iterator = unitElevatorList.iterator();
            while (iterator.hasNext()) {
                BasicUnitElevator unitElevator = iterator.next();
                if (!Objects.equal(unitElevator.getUnitId(), unitId)) {
                    iterator.remove();
                }
            }
        }
        return unitElevatorList;
    }

    public List<BasicUnitDecorateVo> getBasicUnitDecorateAll() {
        List<BasicUnitDecorateVo> unitDecorateVoList = new ArrayList<>();
        List<BasicUnit> unitList = getBasicUnitAll();
        if (CollectionUtils.isNotEmpty(unitList)) {
            for (BasicUnit target : unitList) {
                unitDecorateVoList.addAll(basicUnitDecorateService.getBasicUnitDecorateList(target.getId()));
            }
        }
        return unitDecorateVoList;
    }


    //________________________________________________________||________________________________________________________

    public List<BasicHouseCorollaryEquipment> getBasicHouseCorollaryEquipmentAll() {
        List<BasicHouseCorollaryEquipment> corollaryEquipmentList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                corollaryEquipmentList.addAll(basicHouseCorollaryEquipmentService.getBasicHouseCorollaryEquipmentList(target.getId()));
            }
        }
        return corollaryEquipmentList;
    }

    public List<BasicHouseRoom> getBasicHouseRoomAll() {
        List<BasicHouseRoom> houseRoomList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                houseRoomList.addAll(basicHouseRoomService.getBasicHouseRoomList(target.getId()));
            }
        }
        return houseRoomList;
    }

    public List<BasicHouseDamagedDegreeVo> getDamagedDegreeVoAll() {
        List<BasicHouseDamagedDegreeVo> degreeList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                degreeList.addAll(basicHouseDamagedDegreeService.getDamagedDegreeVoList(target.getId()));
            }
        }
        return degreeList;
    }


    public List<BasicHouseWater> getBasicHouseWaterAll() {
        List<BasicHouseWater> waterList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                waterList.addAll(basicHouseWaterService.getBasicHouseWaterList(target.getId()));
            }
        }
        return waterList;
    }

    public List<BasicHouseWater> getBasicHouseWaterList(Integer houseId) {
        List<BasicHouseWater> waterList = getBasicHouseWaterAll();
        if (CollectionUtils.isNotEmpty(waterList)) {
            return waterList.stream().filter(oo -> Objects.equal(oo.getHouseId(), houseId)).collect(Collectors.toList());
        }
        return waterList;
    }

    public List<BasicHouseWaterDrain> getBasicHouseWaterDrainAll() {
        List<BasicHouseWaterDrain> waterDrainList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                waterDrainList.addAll(basicHouseWaterDrainService.getBasicHouseWaterDrainList(target.getId()));
            }
        }
        return waterDrainList;
    }

    public List<BasicHouseWaterDrain> getBasicHouseWaterDrainList(Integer houseId) {
        List<BasicHouseWaterDrain> waterDrainList = getBasicHouseWaterDrainAll();
        if (CollectionUtils.isNotEmpty(waterDrainList)) {
            return waterDrainList.stream().filter(oo -> Objects.equal(oo.getHouseId(), houseId)).collect(Collectors.toList());
        }
        return waterDrainList;
    }

    public List<BasicHouseRoomDecorateVo> getHouseRoomDecorateList(Integer roomId) {
        return basicHouseRoomDecorateService.getHouseRoomDecorateList(roomId);
    }

    public List<BasicHouseFaceStreetVo> getBasicHouseFaceStreetAll() {
        List<BasicHouseFaceStreetVo> vos = Lists.newArrayList();
        List<BasicHouseFaceStreet> basicHouseFaceStreetList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                BasicHouseFaceStreet query = new BasicHouseFaceStreet();
                query.setHouseId(target.getId());
                basicHouseFaceStreetList.addAll(basicHouseFaceStreetService.basicHouseFaceStreetList(query));
            }
        }
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetList)) {
            for (BasicHouseFaceStreet basicHouseFaceStreet : basicHouseFaceStreetList) {
                vos.add(basicHouseFaceStreetService.getBasicHouseFaceStreetVo(basicHouseFaceStreet));
            }
        }
        return vos;
    }

    public List<BasicHouseIntelligentVo> getBasicHouseIntelligentAll() {
        List<BasicHouseIntelligentVo> intelligentVoList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                intelligentVoList.addAll(basicHouseIntelligentService.getBasicHouseIntelligentVos(target.getId()));
            }
        }
        return intelligentVoList;
    }

    public List<BasicHouseIntelligentVo> getBasicHouseIntelligentList(Integer houseId) {
        List<BasicHouseIntelligentVo> intelligentVoList = getBasicHouseIntelligentAll() ;
        if (CollectionUtils.isNotEmpty(intelligentVoList)) {
           return intelligentVoList.stream().filter(oo -> Objects.equal(oo.getHouseId(),houseId)).collect(Collectors.toList());
        }
        return intelligentVoList;
    }

    public List<BasicHouseHuxingPrice> getBasicHouseHuxingPriceList(Integer houseId){
        List<BasicHouseHuxingPrice> houseHuxingPriceList = getBasicHouseHuxingPriceAll() ;
        Iterator<BasicHouseHuxingPrice> iterator = houseHuxingPriceList.iterator();
        while (iterator.hasNext()){
            BasicHouseHuxingPrice next = iterator.next();
            if (! Objects.equal(next.getHouseId(),houseId)){
                iterator.remove();
            }
        }
        return houseHuxingPriceList;
    }

    public List<BasicHouseHuxingPrice> getBasicHouseHuxingPriceAll(){
        List<BasicHouseHuxingPrice> houseHuxingPriceList = new ArrayList<>() ;
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                List<BasicHouseHuxingPrice> houseHuxingPrices = basicHouseHuxingPriceService.getBasicHouseHuxingPriceList(target.getId());
                if (CollectionUtils.isEmpty(houseHuxingPrices)){
                    continue;
                }
                houseHuxingPriceList.addAll(houseHuxingPrices) ;
            }
        }
        return houseHuxingPriceList ;
    }


    public List<BasicHouseEquipment> getBasicHouseEquipmentAll() {
        List<BasicHouseEquipment> basicHouseEquipmentList = new ArrayList<>();
        List<BasicHouse> basicHouseList = getBasicHouseAll();
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse target : basicHouseList) {
                basicHouseEquipmentList.addAll(basicHouseEquipmentService.getBasicHouseEquipmentList(target.getId()));
            }
        }
        return basicHouseEquipmentList;
    }

    public List<BasicHouseEquipment> getBasicHouseEquipmentList(Integer houseId) {
        List<BasicHouseEquipment> basicHouseEquipmentList = getBasicHouseEquipmentAll();
        if (CollectionUtils.isNotEmpty(basicHouseEquipmentList)) {
            return basicHouseEquipmentList.stream().filter(oo -> Objects.equal(oo.getHouseId(), houseId)).collect(Collectors.toList());
        }
        return basicHouseEquipmentList;
    }


    public BasicEstateVoAndLandStateVo getBasicEstateVoAndLandStateVo() {
        BasicEstate estate = null;
        BasicEstateLandState land = null;
        BasicApplyBatch basicApplyBatch = this.basicApplyBatch;
        if (basicApplyBatch != null && basicApplyBatch.getEstateId() != null) {
            estate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
        }
        if (estate == null) {
            estate = new BasicEstate();
            baseService.writeInfo(BaseService.Type.Throw, "获取数据异常!");
        }
        if (estate != null && estate.getId() != null) {
            land = basicEstateLandStateService.getLandStateByEstateId(estate.getId());
        }
        if (land == null) {
            land = new BasicEstateLandState();
            baseService.writeInfo(BaseService.Type.Throw, "获取数据异常!");
        }
        BasicEstateVoAndLandStateVo target = new BasicEstateVoAndLandStateVo(basicEstateService.getBasicEstateVo(estate));
        BasicEstateLandStateVo vo = basicEstateLandStateService.getBasicEstateLandStateVo(land);
        target.setBasicEstateLandStateVo(vo);
        return target;
    }

    public BasicEstateLandStateVo getBasicEstateLandState() {
        return getBasicEstateVoAndLandStateVo().getBasicEstateLandStateVo();
    }

    public BasicEstateVo getEstate() {
        return getBasicEstateVoAndLandStateVo().getBasicEstateVo();
    }

    public List<BasicBuilding> getBasicBuildingAll() {
        List<BasicBuilding> basicBuildingList = new ArrayList<>(1);
        List<BasicApplyBatchDetail> buildTree = getBasicApplyBatchDetailList().stream().filter(oo -> Objects.equal(oo.getTableName(), FormatUtils.entityNameConvertToTableName(BasicBuilding.class))).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(buildTree)) {
            List<BasicBuilding> basicBuildingList2 = basicBuildingService.getBasicBuildingIds(buildTree.stream().map(oo -> oo.getTableId()).collect(Collectors.toList()));
            basicBuildingList.addAll(basicBuildingList2);
        }
        return basicBuildingList;
    }

    public List<BasicBuilding> getBasicBuildingList(Integer estateId) {
        List<BasicBuilding> basicBuildingList = getBasicBuildingAll();
        if (CollectionUtils.isNotEmpty(basicBuildingList)) {
            Iterator<BasicBuilding> it = basicBuildingList.iterator();
            while (it.hasNext()) {
                BasicBuilding basicBuilding = it.next();
                if (!Objects.equal(basicBuilding.getEstateId(), estateId)) {
                    it.remove();
                }
            }
        }
        return basicBuildingList;
    }

    public List<BasicUnit> getBasicUnitAll() {
        List<BasicUnit> basicUnitList = new ArrayList<>(1);
        List<BasicApplyBatchDetail> unitTree = getBasicApplyBatchDetailList().stream().filter(oo -> Objects.equal(oo.getTableName(), FormatUtils.entityNameConvertToTableName(BasicUnit.class))).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(unitTree)) {
            List<BasicUnit> basicUnitList2 = basicUnitService.getBasicUnitListByIds(unitTree.stream().map(oo -> oo.getTableId()).collect(Collectors.toList()));
            basicUnitList.addAll(basicUnitList2);
        }
        return basicUnitList;
    }

    public List<BasicUnit> getBasicUnitList(Integer buildingId) {
        List<BasicUnit> basicUnitList = getBasicUnitAll();
        if (CollectionUtils.isNotEmpty(basicUnitList)) {
            Iterator<BasicUnit> it = basicUnitList.iterator();
            while (it.hasNext()) {
                BasicUnit basicUnit = it.next();
                if (!Objects.equal(basicUnit.getBuildingId(), buildingId)) {
                    it.remove();
                }
            }
        }
        return basicUnitList;
    }

    public List<BasicHouse> getBasicHouseAll() {
        return getBasicHouseAndTradingAll().stream().map(oo -> oo.getHouse()).collect(Collectors.toList());
    }


    public List<BasicHouse> getBasicHouseList(Integer unitId) {
        return getBasicHouseAndTradingList(unitId).stream().map(oo -> oo.getHouse()).collect(Collectors.toList());
    }

    public List<BasicHouseTrading> getBasicHouseTradingList(Integer unitId) {
        return getBasicHouseAndTradingList(unitId).stream().map(oo -> oo.getTrading()).collect(Collectors.toList());
    }

    public List<BasicHouseTrading> getBasicHouseTradingAll() {
        return getBasicHouseAndTradingAll().stream().map(oo -> oo.getTrading()).collect(Collectors.toList());
    }

    public List<BasicHouseAndTrading> getBasicHouseAndTradingList(Integer unitId) {
        List<BasicHouseAndTrading> list = getBasicHouseAndTradingAll();
        if (CollectionUtils.isNotEmpty(list)) {
            Iterator<BasicHouseAndTrading> it = list.iterator();
            while (it.hasNext()) {
                BasicHouseAndTrading target = it.next();
                if (!Objects.equal(unitId, target.getHouse().getUnitId())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    public List<BasicHouseAndTrading> getBasicHouseAndTradingAll() {
        List<BasicHouseAndTrading> list = new ArrayList<>(1);
        List<BasicHouse> basicHouseList = new ArrayList<>(1);
        List<BasicApplyBatchDetail> basicApplyBatchDetails = getBasicApplyBatchDetailList();
        List<BasicApplyBatchDetail> houseTree = basicApplyBatchDetails.stream().filter(oo -> Objects.equal(oo.getTableName(), FormatUtils.entityNameConvertToTableName(BasicHouse.class))).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(houseTree)) {
            List<BasicHouse> basicHouseList2 = basicHouseService.getBasicHouseIds(houseTree.stream().map(oo -> oo.getTableId()).collect(Collectors.toList()));
            basicHouseList.addAll(basicHouseList2);
        }
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            Iterator<BasicHouse> it = basicHouseList.iterator();
            while (it.hasNext()) {
                BasicHouseAndTrading target = new BasicHouseAndTrading(it.next());
                BasicHouseTrading basicHouseTrading = basicHouseTradingService.getTradingByHouseId(target.getHouse().getId());
                if (basicHouseTrading == null) {
                    basicHouseTrading = new BasicHouseTrading();
                    baseService.writeInfo(BaseService.Type.Throw, "获取数据异常!");
                }
                target.setTrading(basicHouseTrading);
                list.add(target);
            }
        }
        return list;
    }

    public class BasicEstateVoAndLandStateVo implements Serializable {
        private BasicEstateVo basicEstateVo;
        private BasicEstateLandStateVo basicEstateLandStateVo;

        public BasicEstateVoAndLandStateVo(BasicEstateVo basicEstateVo) {
            this.basicEstateVo = basicEstateVo;
        }

        public BasicEstateVo getBasicEstateVo() {
            return basicEstateVo;
        }

        public BasicEstateLandStateVo getBasicEstateLandStateVo() {
            return basicEstateLandStateVo;
        }

        public void setBasicEstateLandStateVo(BasicEstateLandStateVo basicEstateLandStateVo) {
            this.basicEstateLandStateVo = basicEstateLandStateVo;
        }
    }

    public class BasicHouseAndTrading implements Serializable {
        private BasicHouse house;
        private BasicHouseTrading trading;

        public BasicHouseAndTrading(BasicHouse basicHouse) {
            this.house = basicHouse;
        }

        public void setTrading(BasicHouseTrading trading) {
            this.trading = trading;
        }

        public BasicHouseTrading getTrading() {
            return trading;
        }

        public BasicHouse getHouse() {
            return house;
        }
    }


    public BasicExamineHandle(Integer applyBatchId) {
        beanInit();
        this.basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
    }

    public BasicExamineHandle(BasicApplyBatch applyBatch) {
        beanInit();
        this.basicApplyBatch = applyBatch;
    }

    private BasicExamineHandle() {
    }

    public static BasicExamineHandle getBasicExamineHandle(Integer estateId) {
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle();
        basicExamineHandle.beanInit();
        basicExamineHandle.basicApplyBatch = basicExamineHandle.basicApplyBatchService.getBasicApplyBatchByEstateId(estateId);
        return basicExamineHandle;
    }

    private void beanInit() {
        this.baseService = SpringContextUtils.getBean(BaseService.class);
        this.basicApplyBatchDetailService = SpringContextUtils.getBean(BasicApplyBatchDetailService.class);
        this.basicApplyBatchService = SpringContextUtils.getBean(BasicApplyBatchService.class);
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
        this.basicHouseDamagedDegreeService = SpringContextUtils.getBean(BasicHouseDamagedDegreeService.class);

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
        this.basicHouseTradingLeaseAndSellDtoService = SpringContextUtils.getBean(BasicHouseTradingLeaseAndSellDtoService.class);
        this.basicEstateInvestigationService = SpringContextUtils.getBean(BasicEstateInvestigationService.class);
        this.basicBuildingPropertyServiceItemService = SpringContextUtils.getBean(BasicBuildingPropertyServiceItemService.class);
        this.basicHouseHuxingPriceService = SpringContextUtils.getBean(BasicHouseHuxingPriceService.class);
    }

    private List<BasicApplyBatchDetail> getBasicApplyBatchDetailList() {
        if (CollectionUtils.isEmpty(basicApplyBatchDetailList) && this.basicApplyBatch != null) {
            basicApplyBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(this.basicApplyBatch.getId());
        }
        return basicApplyBatchDetailList;
    }

    public static class BasicVo implements Serializable {
        private String name;
        private String parentName;
        private String type;
        private String value;
        private LinkedHashSet<BasicVo> basicVoLinkedHashSet = new LinkedHashSet<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public LinkedHashSet<BasicVo> getBasicVoLinkedHashSet() {
            return basicVoLinkedHashSet;
        }

        public void setBasicVoLinkedHashSet(LinkedHashSet<BasicVo> basicVoLinkedHashSet) {
            this.basicVoLinkedHashSet = basicVoLinkedHashSet;
        }

        public BasicVo(String type) {
            this.type = type;
        }

        public BasicVo(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public BasicVo() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BasicVo basicVo = (BasicVo) o;
            return java.util.Objects.equals(name, basicVo.name) &&
                    java.util.Objects.equals(parentName, basicVo.parentName) &&
                    java.util.Objects.equals(type, basicVo.type) &&
                    java.util.Objects.equals(value, basicVo.value);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, parentName, type, value);
        }

        @Override
        public String toString() {
            return "BasicVo{" +
                    "name='" + name + '\'' +
                    ", parentName='" + parentName + '\'' +
                    ", type='" + type + '\'' +
                    ", value='" + value + '\'' +
                    ", basicVoLinkedHashSet=" + basicVoLinkedHashSet +
                    '}';
        }
    }


}
