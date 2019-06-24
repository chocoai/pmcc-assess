package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.common.enums.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingOutfitVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseIntelligentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomDecorateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitDecorateVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateEquityService;
import com.copower.pmcc.assess.service.project.generate.GenerateHouseEntityService;
import com.copower.pmcc.assess.service.project.generate.GenerateLoactionService;
import com.copower.pmcc.assess.service.project.generate.land.GenerateLandFactorService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdMarketCompareFieldService extends BaseService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private BasicEstateSupplyService basicEstateSupplyService;
    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;
    @Autowired
    private BasicHouseIntelligentService basicHouseIntelligentService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicHouseWaterService basicHouseWaterService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicHouseWaterDrainService basicHouseWaterDrainService;
    @Autowired
    private GenerateLoactionService generateLoactionService;
    @Autowired
    private GenerateHouseEntityService generateHouseEntityService;
    @Autowired
    private GenerateEquityService generateEquityService;
    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private GenerateLandFactorService generateLandFactorService;

    /**
     * 获取市场比较法各个字段对应值
     *
     * @param setUseFieldList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String getCompareInfo(SchemeJudgeObject judgeObject, BasicApply basicApply, List<DataSetUseField> setUseFieldList, Boolean isCase) {
        try {
            if (CollectionUtils.isEmpty(setUseFieldList)) return null;
            if (basicApply == null) return null;
            //取得楼盘信息
            BasicEstate examineEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
            examineEstate = examineEstate == null ? new BasicEstate() : examineEstate;
            //取得楼栋信息
            BasicBuilding examineBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            examineBuilding = examineBuilding == null ? new BasicBuilding() : examineBuilding;
            //取得单元信息
            BasicUnit examineUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            examineUnit = examineUnit == null ? new BasicUnit() : examineUnit;
            //取得房屋信息
            BasicHouse examineHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            examineHouse = examineHouse == null ? new BasicHouse() : examineHouse;
            //取得交易信息
            BasicHouseTrading houseTrading = null;
            houseTrading = basicHouseTradingService.getTradingByHouseId(examineHouse.getId());
            houseTrading = houseTrading == null ? new BasicHouseTrading() : houseTrading;
            //取得申报记录信息
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            declareRecord = declareRecord == null ? new DeclareRecord() : declareRecord;
            //取得房间信息
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(examineHouse.getId());
            //取得建筑功能
            List<BasicBuildingFunction> buildingFunctions = basicBuildingFunctionService.getBasicBuildingFunctionList(examineBuilding.getId());
            //取得房间供应信息(供暖；空调；新风)
            List<BasicHouseEquipment> equipmentList = basicHouseEquipmentService.getBasicHouseEquipmentList(examineHouse.getId());

            StringBuilder stringBuilder = null;
            List<MarketCompareItemDto> list = Lists.newArrayList();
            for (DataSetUseField dataSetUseField : setUseFieldList) {
                if (StringUtils.isBlank(dataSetUseField.getFieldName())) continue;
                MethodCompareFieldEnum compareFieldEnum = MethodCompareFieldEnum.getEnumByKey(dataSetUseField.getFieldName());
                if (compareFieldEnum == null) continue;
                try {
                    switch (compareFieldEnum) {
                        case ESTATE_NAME://楼盘名称
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ESTATE_NAME.getKey(), examineEstate.getName()));
                            break;
                        case SCOPE_PROPERTY://财产范围
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SCOPE_PROPERTY.getKey(), baseDataDicService.getNameById(houseTrading.getScopeProperty()), isCase));
                            break;
                        case FINANCING_CONDITIONS://融资条件
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.isBlank(houseTrading.getDownPaymentRatio()) ? "" : String.format("首付款比例%s；", houseTrading.getDownPaymentRatio()));
                            stringBuilder.append(houseTrading.getLendingRate() == null ? "" : String.format("贷款利率%s；", houseTrading.getLendingRate()));
                            stringBuilder.append(houseTrading.getLoanPeriod() == null ? "" : String.format("贷款期限%s；", houseTrading.getLoanPeriod()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.FINANCING_CONDITIONS.getKey(), stringBuilder.toString(), isCase));
                            break;
                        case TAX_BURDEN://税费负担
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TAX_BURDEN.getKey(), baseDataDicService.getNameById(houseTrading.getTaxBurden()), isCase));
                            break;
                        case PAYMENT_METHOD://付款方式
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PAYMENT_METHOD.getKey(), baseDataDicService.getNameById(houseTrading.getPaymentMethod()), isCase));
                            break;
                        case TRADING_TRANSACTION_SITUATION://交易情况
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_TRANSACTION_SITUATION.getKey(), baseDataDicService.getNameById(houseTrading.getTransactionSituation()), isCase));
                            break;
                        case TRADING_TIME://交易时间
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_TIME.getKey(), DateUtils.formatDate(houseTrading.getTradingTime()), isCase));
                            break;
                        case TRADING_PRICE://交易价格
                            if (houseTrading.getTradingUnitPrice() != null && isCase)
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_PRICE.getKey(), String.valueOf(houseTrading.getTradingUnitPrice())));
                            else
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_PRICE.getKey(), null));
                            break;
                        case LOCATION://房地产坐落
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(declareRecord.getStreetNumber());
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LOCATION.getKey(), stringBuilder.toString()));
                            break;
                        case POSITION://房地产方位
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getPosition(examineEstate));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.POSITION.getKey(), stringBuilder.toString()));
                            break;
                        case WITH_IMPORTANT_LOCATION_DISTANCE://与重要场所的距离
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getWithImportantLocationDistance(basicApply));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.WITH_IMPORTANT_LOCATION_DISTANCE.getKey(), stringBuilder.toString()));
                            break;
                        case TEMPORARY_ROAD_CONDITION://临街（路）状况
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getFaceStreet(Lists.newArrayList(judgeObject)));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TEMPORARY_ROAD_CONDITION.getKey(), stringBuilder.toString()));
                            break;
                        case FLOOR://楼栋楼层
                            stringBuilder = new StringBuilder();
                            if (StringUtils.isNotBlank(examineBuilding.getBuildingNumber()))
                                stringBuilder.append(String.format("%s栋", examineBuilding.getBuildingNumber()));
                            if (examineBuilding.getFloorCount() != null)
                                stringBuilder.append(String.format("%s层建筑", examineBuilding.getFloorCount()));
                            if(StringUtils.isNotBlank(examineUnit.getUnitNumber()))
                                stringBuilder.append(String.format("%s单元", examineUnit.getUnitNumber()));
                            if (StringUtils.isNotBlank(examineHouse.getFloor()))
                                stringBuilder.append(String.format("第%s层", examineHouse.getFloor()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.FLOOR.getKey(), stringBuilder.toString()));
                            break;
                        case ORIENTATION://朝向
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ORIENTATION.getKey(), baseDataDicService.getNameById(examineHouse.getOrientation())));
                            break;
                        case TRAFFIC_CONDITIONS://交通条件
                            stringBuilder = new StringBuilder();
                            String roadCondition = generateLoactionService.getRoadCondition(Lists.newArrayList(judgeObject));//道路状况
                            String transport = generateLoactionService.getAccessAvailableMeansTransport(basicApply);//出入可利用的交通工具
                            String trafficControl = generateLoactionService.getTrafficControl(basicApply);//交通管制情况
                            String convenience = generateLoactionService.getParkingConvenience(basicApply);//停车方便度
                            String trafficCharges = generateLoactionService.getTrafficCharges(basicApply);//交通收费情况
                            if (StringUtils.isNotBlank(roadCondition)) {
                                stringBuilder.append(String.format("%s;", roadCondition));
                            }
                            if (StringUtils.isNotBlank(transport)) {
                                stringBuilder.append(String.format("%s;", transport));
                            }
                            if (StringUtils.isNotBlank(trafficControl)) {
                                stringBuilder.append(String.format("%s;", trafficControl));
                            }
                            if (StringUtils.isNotBlank(convenience)) {
                                stringBuilder.append(String.format("%s;", convenience));
                            }
                            if (StringUtils.isNotBlank(trafficCharges)) {
                                stringBuilder.append(String.format("%s", trafficCharges));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRAFFIC_CONDITIONS.getKey(), stringBuilder.toString()));
                            break;
                        case URBAN_INFRASTRUCTURE://外部基础设施
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getExternalInfrastructure(basicApply));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.URBAN_INFRASTRUCTURE.getKey(), stringBuilder.toString()));
                            break;
                        case PUBLIC_SERVICE_FACILITIES://公共服务设施
                            stringBuilder = new StringBuilder();
                            List<String> facilities = generateLoactionService.getExternalPublicServiceFacilities(basicApply, false);
                            if (CollectionUtils.isNotEmpty(facilities)) {
                                for (String facility : facilities) {
                                    stringBuilder.append(facility);
                                }
                            }

                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PUBLIC_SERVICE_FACILITIES.getKey(), stringBuilder.toString()));
                            break;
                        case NATURAL://自然环境
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.NATURAL));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NATURAL.getKey(), stringBuilder.toString()));
                            break;
                        case CULTURAL://人文环境
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.HUMANITY));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.CULTURAL.getKey(), stringBuilder.toString()));
                            break;
                        case SCENERY://景观
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getEnvironmentalScience(basicApply, EnvironmentalScienceEnum.SCENERY));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SCENERY.getKey(), stringBuilder.toString()));
                            break;
                        case LAND_RIGHT_TYPE://土地权利类型
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(declareRecord.getLandRightType());
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_RIGHT_TYPE.getKey(), stringBuilder.toString()));
                            break;
                        case LAND_RIGHT_NATURE://土地权利性质
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(declareRecord.getLandRightNature());
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_RIGHT_NATURE.getKey(), stringBuilder.toString()));
                            break;
                        case HOUSE_NATURE://房屋性质
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HOUSE_NATURE.getKey(), declareRecord.getNature()));
                            break;
                        case HOUSE_CERT_USE://房屋用途
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(declareRecord.getCertUse());
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HOUSE_CERT_USE.getKey(), stringBuilder.toString()));
                            break;
                        case PROPERTY_MANAGEMENT://物业管理情况
                            stringBuilder = new StringBuilder();
                            if (examineBuilding.getProperty() != null) {
                                DataProperty dataProperty = dataPropertyService.getByDataPropertyId(examineBuilding.getProperty());
                                stringBuilder.append(generateEquityService.getProperty(dataProperty));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PROPERTY_MANAGEMENT.getKey(), stringBuilder.toString()));
                            break;
                        case OTHER_SPECIAL_SITUATIONS://其它特殊情况
                            String specialCase = generateEquityService.getTransferLimit(Lists.newArrayList(judgeObject), null);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.OTHER_SPECIAL_SITUATIONS.getKey(), specialCase));
                            break;
                        case BUILDING_AREA://建筑面积（㎡）
                            String buildingArea = null;
                            if (isCase) {
                                buildingArea = examineHouse.getArea() == null ? "" : String.format("%s平方米", examineHouse.getArea());
                            } else {
                                buildingArea = judgeObject.getEvaluationArea() == null ? "" : String.format("%s平方米", judgeObject.getEvaluationArea());
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_AREA.getKey(), buildingArea));
                            break;
                        case FLOOR_HEIGHT://层高
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.FLOOR_HEIGHT.getKey(), String.valueOf(examineBuilding.getFloorHeight() == null ? "" : examineBuilding.getFloorHeight())));
                            break;
                        case NET_HEIGHT://净高
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NET_HEIGHT.getKey(), String.valueOf(examineBuilding.getNetHeight() == null ? "" : examineBuilding.getNetHeight())));
                            break;
                        case BUILDING_SCALE://建筑规模
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateHouseEntityService.getBuildingScale(Lists.newArrayList(judgeObject)));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_SCALE.getKey(), stringBuilder.toString()));
                            break;
                        case BUILDING_STRUCTURE://建筑结构
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_STRUCTURE.getKey(), baseDataDicService.getNameById(examineBuilding.getBuildingStructureCategory())));
                            break;
                        case ARCHITECTURAL_OUTFIT://建筑外装
                            stringBuilder = new StringBuilder();
                            List<BasicBuildingOutfitVo> outfitList = basicBuildingOutfitService.getBasicBuildingOutfitVos(examineBuilding.getId());
                            if (CollectionUtils.isNotEmpty(outfitList)) {
                                for (BasicBuildingOutfitVo outfit : outfitList) {
                                    stringBuilder.append(outfit.getDecorationPart()).append(outfit.getMaterialGradeName()).append(outfit.getDecoratingMaterialName()).append(",");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ARCHITECTURAL_OUTFIT.getKey(), StringUtils.strip(stringBuilder.toString(), ",")));
                            break;
                        case APPEARANCE://外观
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateHouseEntityService.getAppearance(Lists.newArrayList(judgeObject)));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.APPEARANCE.getKey(), stringBuilder.toString()));
                            break;
                        case AERATION://通风
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.defaultString(room.getRoomType())).append(room.getAeration()).append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.AERATION.getKey(), StringUtils.strip(stringBuilder.toString(), "、")));
                            break;
                        case LIGHTING://采光
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.defaultString(room.getRoomType())).append(room.getLighting()).append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LIGHTING.getKey(), StringUtils.strip(stringBuilder.toString(), "、")));
                            break;
                        case SUNSHINE://日照
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.defaultString(room.getRoomType())).append(room.getSunshine()).append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SUNSHINE.getKey(), StringUtils.strip(stringBuilder.toString(), "、")));
                            break;
                        case SOUND_INSULATION://隔音
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.defaultString(room.getRoomType())).append(room.getSoundInsulation()).append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SOUND_INSULATION.getKey(), StringUtils.strip(stringBuilder.toString(), "、")));
                            break;
                        case HEAT_PRESERVATION://保温
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                BaseDataDic heatPreservationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_PRESERVATION);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, heatPreservationDic);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HEAT_PRESERVATION.getKey(), stringBuilder.toString()));
                            break;
                        case HEAT_INSULATION://隔热
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, heatInsulationDic);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HEAT_INSULATION.getKey(), stringBuilder.toString()));
                            break;
                        case WATERPROOF://防水
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                BaseDataDic waterproofDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_WATERPROOF);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, waterproofDic);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.WATERPROOF.getKey(), stringBuilder.toString()));
                            break;
                        case INTELLIGENT_LEVEL://设施设备及智能化程度(空调与新风情况)
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(equipmentList)) {
                                String supplyMode = "";
                                for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                    if (StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()) ||
                                            StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                                        stringBuilder.append(examineHouseEquipment.getEquipment());
                                        stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getGrade())).append("、");
                                        supplyMode = baseDataDicService.getNameById(examineHouseEquipment.getSupplyMode());
                                    }
                                }
                                stringBuilder.append(supplyMode);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.INTELLIGENT_LEVEL.getKey(), StringUtils.strip(stringBuilder.toString(), "、")));
                            break;
                        case WATER_SUPPLY_DRAINAGE_MODE://供（排）水方式
                            stringBuilder = new StringBuilder();
                            List<BasicHouseWater> waterList = basicHouseWaterService.getBasicHouseWaterList(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(waterList)) {
                                for (BasicHouseWater houseWater : waterList) {
                                    stringBuilder.append(houseWater.getGrade() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getGrade())));//档次
                                    stringBuilder.append(houseWater.getBoosterEquipment() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getBoosterEquipment())));//给水升压设备
                                    stringBuilder.append(houseWater.getPipeMaterial() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getPipeMaterial())));//给水管材料
                                    stringBuilder.append(houseWater.getPipingLayout() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getPipingLayout())));//给水管道布置
                                    stringBuilder.append(houseWater.getSupplyMode() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getSupplyMode())));//给水方式
                                    stringBuilder.append(",");
                                    stringBuilder.append(houseWater.getFireWaterSupply() == null ? "" : String.format("消防给水%s；", baseDataDicService.getNameById(houseWater.getFireWaterSupply())));
                                }
                            }
                            List<BasicHouseWaterDrain> drainList = basicHouseWaterDrainService.getBasicHouseWaterDrainList(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(drainList)) {
                                for (BasicHouseWaterDrain houseWaterDrain : drainList) {
                                    stringBuilder.append(houseWaterDrain.getType() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWaterDrain.getType())));//类别
                                    stringBuilder.append(houseWaterDrain.getProcessingMode() == null ? "" : String.format("%s；", baseDataDicService.getNameById(houseWaterDrain.getProcessingMode())));//排水处理方式
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.WATER_SUPPLY_DRAINAGE_MODE.getKey(), stringBuilder.toString()));
                            break;
                        case HEATING_MODE://采暖供热方式
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(equipmentList)) {
                                for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                    if (StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseHeating.getKey())) {
                                        stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                        stringBuilder.append(examineHouseEquipment.getEquipment()).append("、");
                                    }
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HEATING_MODE.getKey(), stringBuilder.toString()));
                            break;
                        case NETWORK://电力通信网络
                            stringBuilder = new StringBuilder();
                            List<BasicHouseIntelligentVo> intelligentList = basicHouseIntelligentService.getBasicHouseIntelligentVos(examineHouse.getId());
                            stringBuilder.append(this.getIntelligentNet(intelligentList));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NETWORK.getKey(), stringBuilder.toString()));
                            break;
                        case ELEVATOR_HOUSEHOLD_RATIO://电梯梯户比
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ELEVATOR_HOUSEHOLD_RATIO.getKey(), examineUnit.getElevatorHouseholdRatio()));
                            break;
                        case INTERNAL_ASSEMBLY://内装
                            stringBuilder = new StringBuilder();
                            List<BasicUnitDecorateVo> decorateList = basicUnitDecorateService.getBasicUnitDecorateList(examineUnit.getId());
                            if (CollectionUtils.isNotEmpty(decorateList)) {
                                for (BasicUnitDecorateVo unitDecorate : decorateList) {
                                    stringBuilder.append(unitDecorate.getDecorationPartName()).append(unitDecorate.getMaterialGradeName()).append(unitDecorate.getDecoratingMaterialName()).append("；");
                                }
                            }
                            if (CollectionUtils.isNotEmpty(roomList)) {//房间内装
                                for (BasicHouseRoom room : roomList) {
                                    List<BasicHouseRoomDecorateVo> roomDecorateList = basicHouseRoomDecorateService.getHouseRoomDecorateList(room.getId());
                                    if (CollectionUtils.isNotEmpty(roomDecorateList)) {
                                        StringBuilder decorate = new StringBuilder();
                                        for (BasicHouseRoomDecorateVo roomDecorate : roomDecorateList) {
                                            decorate.append(roomDecorate.getPartName());
                                            if(StringUtils.isNotBlank(roomDecorate.getRemark())){
                                                decorate.append(roomDecorate.getRemark()).append(",");
                                            }else if(StringUtils.isNotBlank(roomDecorate.getRemark())){
                                                decorate.append(roomDecorate.getMaterialName()).append(",");
                                            }
                                        }
                                        if (decorate.length() > 0) {
                                            stringBuilder.append(String.format("%s:%s；", room.getRoomType(), decorate));
                                        }
                                    }
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.INTERNAL_ASSEMBLY.getKey(), stringBuilder.toString()));
                            break;
                        case PLANE_LAYOUT://平面布局
                            stringBuilder = new StringBuilder();
                            BaseDataDic practicalUseDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_HOUSE_PRACTICAL_USE_HOUSE);
                            if (practicalUseDic != null) {
                                String huxing = StringUtils.isBlank(examineHouse.getNewHuxingName()) ? examineHouse.getHuxingName() : examineHouse.getNewHuxingName();
                                stringBuilder.append(huxing).append("；");
                                if (!practicalUseDic.getId().equals(examineHouse.getPracticalUse())) {
                                    if (CollectionUtils.isNotEmpty(roomList)) {
                                        for (BasicHouseRoom room : roomList) {
                                            stringBuilder.append(baseDataDicService.getNameById(room.getRoomType())).append(String.format("开间:%s米；", room.getOpening())).append(String.format("进深:%s米；", room.getDepth())).append("；");
                                        }
                                    }
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PLANE_LAYOUT.getKey(), stringBuilder.toString()));
                            break;
                        case NEW_DEGREE://成新度
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(examineHouse.getNewDegree());
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NEW_DEGREE.getKey(), stringBuilder.toString()));
                            break;
                        case Other://其它
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateHouseEntityService.getOther(Lists.newArrayList(judgeObject)));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.Other.getKey(), stringBuilder.toString()));
                            break;
                        case BUILDING_YEAR://竣工时间
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_YEAR.getKey(), DateUtils.formatDate(examineBuilding.getBeCompletedTime()), isCase));
                            break;
                        case CONSTRUCTION_QUALITY://工程质量
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.CONSTRUCTION_QUALITY.getKey(), baseDataDicService.getNameById(examineBuilding.getConstructionQuality())));
                            break;
                        case PRICE_CONNOTATION://单价内涵
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PRICE_CONNOTATION.getKey(), baseDataDicService.getNameById(houseTrading.getPriceConnotation())));
                            break;

                        /*-------------------------------------------------------------土地相关*/

                        case LAND_NAME://地块名称
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_NAME.getKey(), examineEstate.getName()));
                            break;
                        case LAND_TRADING_PRICE:
                            if (houseTrading.getTradingUnitPrice() != null && isCase)
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_PRICE.getKey(), String.valueOf(houseTrading.getTradingUnitPrice())));
                            else
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_PRICE.getKey(), null));
                            break;
                        case LAND_AREA_LOCATION://区域位置
                            String areaLocation = generateLandFactorService.getAreaLocation(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_AREA_LOCATION.getKey(), areaLocation));
                            break;
                        case LAND_AGGLOMERATION_DEGREE://产业聚集度
                            String agglomerationDegree = null;
                            try {
                                agglomerationDegree = generateLandFactorService.getAgglomerationDegree(Lists.newArrayList(judgeObject));
                            } catch (Exception ex) {
                                agglomerationDegree = null;
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_AGGLOMERATION_DEGREE.getKey(), agglomerationDegree));
                            break;
                        case LAND_TRAFFIC_CONDITIONS://交通条件
                            String trafficConditions = generateLandFactorService.getTrafficConditions(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRAFFIC_CONDITIONS.getKey(), trafficConditions));
                            break;
                        case LAND_UPPORTING_FACILITY://配套设施条件
                            String upportingFacility = generateLandFactorService.getUpportingFacility(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_UPPORTING_FACILITY.getKey(), upportingFacility));
                            break;
                        case LAND_ENVIRONMENT_CONDITION://环境条件
                            String environmentCondition = generateLandFactorService.getEnvironmentCondition(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_ENVIRONMENT_CONDITION.getKey(), environmentCondition));
                            break;
                        case LAND_PLANNING_CONDITION://规划条件
                            String planningCondition = generateLandFactorService.getPlanningCondition(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_PLANNING_CONDITION.getKey(), planningCondition));
                            break;
                        case LAND_AREA://面积
                            String area = generateLandFactorService.getArea(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_AREA.getKey(), area));
                            break;
                        case LAND_USE://用途
                            String use = generateLandFactorService.getUse(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_USE.getKey(), use));
                            break;
                        case LAND_TEMPORARY_ROAD_CONDITION://临街（路）状况
                            String temporaryRoadCondition = generateLandFactorService.getTemporaryRoadCondition(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TEMPORARY_ROAD_CONDITION.getKey(), temporaryRoadCondition));
                            break;
                        case LAND_TOPOGRAPHY://形状、地质、地形、地势
                            String topography = generateLandFactorService.getTopography(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TOPOGRAPHY.getKey(), topography));
                            break;
                        case LAND_PLOT_RATIO://容积率
                            String plotRatio = generateLandFactorService.getPlotRatio(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_PLOT_RATIO.getKey(), plotRatio));
                            break;
                        case LAND_DEVELOPMENT_LEVEL://宗地开发程度
                            String developmentLevel = generateLandFactorService.getDevelopmentLevel(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_DEVELOPMENT_LEVEL.getKey(), developmentLevel));
                            break;
                        case LAND_USEFUL_LIFE://使用年限
                            String usefulLife = generateLandFactorService.getUsefulLife(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_USEFUL_LIFE.getKey(), usefulLife));
                            break;
                        case LAND_RIGHT_CONDITION://土地权利状况
                            String rightCondition = generateLandFactorService.getRightCondition(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_RIGHT_CONDITION.getKey(), rightCondition));
                            break;
                        case LAND_OTHER://土地其它
                            String other = generateLandFactorService.getOther(Lists.newArrayList(judgeObject));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_OTHER.getKey(), other));
                            break;
                    }
                } catch (Exception e) {
                    log.error(String.format("比较法字段获取数据异常：%s-%s", compareFieldEnum.getName(), e.getMessage()), e);
                }
            }
            return JSON.toJSONString(list);
        } catch (Exception e) {
            log.error("市场比较法生成比较数据异常", e);
            return "";
        }
    }

    private void getCommonBuildingFunction(List<BasicBuildingFunction> buildingFunctions, StringBuilder stringBuilder, BaseDataDic heatPreservationDic) {
        for (BasicBuildingFunction buildingFunction : buildingFunctions) {
            if (buildingFunction.getType() != null && buildingFunction.getType().equals(heatPreservationDic.getId())) {
                stringBuilder.append(baseDataDicService.getNameById(buildingFunction.getDecorationPart()))
                        .append(baseDataDicService.getNameById(buildingFunction.getType()));
            }
        }
    }

    private MarketCompareItemDto getMarketCompareItemDto(String name, String value) {
        return getMarketCompareItemDto(name, value, true);
    }

    private MarketCompareItemDto getMarketCompareItemDto(String name, String value, Boolean isCase) {
        value = StringUtils.isBlank(value) ? (isCase ? "无" : "") : value;
        value = value.replaceAll("^[,，.。;；、]+|[,，.。;；、]+$", "");
        MarketCompareItemDto marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName(name);
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        marketCompareItemDto.setValue(value);
        return marketCompareItemDto;
    }

    /**
     * 获取电力通信网络
     *
     * @param intelligentVoList
     * @return
     */
    public String getIntelligentNet(List<BasicHouseIntelligentVo> intelligentVoList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(intelligentVoList)) {
            int size = intelligentVoList.size();
            for (int j = 0; j < intelligentVoList.size(); j++) {
                stringBuilder.append("电路采用");
                stringBuilder.append(org.apache.commons.lang3.StringUtils.isNotBlank(intelligentVoList.get(j).getGradeName()) ? intelligentVoList.get(j).getGradeName() : "档次无").append("材料");
                stringBuilder.append(org.apache.commons.lang3.StringUtils.isNotBlank(intelligentVoList.get(j).getSwitchCircuitName()) ? intelligentVoList.get(j).getSwitchCircuitName() : "开关回路无");
                stringBuilder.append("铺设方式").append(org.apache.commons.lang3.StringUtils.isNotBlank(intelligentVoList.get(j).getLayingMethodName()) ? intelligentVoList.get(j).getLayingMethodName() : "无");
                if (org.apache.commons.lang3.StringUtils.isNotBlank(intelligentVoList.get(j).getLampsLanternsName())) {
                    stringBuilder.append(",");
                    stringBuilder.append(intelligentVoList.get(j).getLampsLanternsName());
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(intelligentVoList.get(j).getIntelligentSystemName())) {
                    stringBuilder.append(",");
                    stringBuilder.append(intelligentVoList.get(j).getIntelligentSystemName());
                }
                if (j == size - 1 && size != 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append("，");
                }
            }
        }
        return stringBuilder.toString();
    }

}
