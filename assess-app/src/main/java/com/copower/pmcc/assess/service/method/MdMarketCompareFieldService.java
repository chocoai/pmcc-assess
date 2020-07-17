package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateEquityService;
import com.copower.pmcc.assess.service.project.generate.GenerateHouseEntityService;
import com.copower.pmcc.assess.service.project.generate.GenerateLoactionService;
import com.copower.pmcc.assess.service.project.generate.land.GenerateLandFactorService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdMarketCompareFieldService extends BaseService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private PublicService publicService;
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
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private GenerateLandFactorService generateLandFactorService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;

    /**
     * 获取市场比较法各个字段对应值
     *
     * @param setUseFieldList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String getCompareInfo(SchemeAreaGroup areaGroup, SchemeJudgeObject judgeObject, BasicApply basicApply, List<DataSetUseField> setUseFieldList, Boolean isCase) {
        try {
            if (CollectionUtils.isEmpty(setUseFieldList)) return null;
            if (basicApply == null) return null;
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApply.getApplyBatchId());
            //取得楼盘信息
            BasicEstate examineEstate = basicEstateService.getBasicEstateByBasicApply(basicApply);
            if (examineEstate == null) {
                examineEstate = new BasicEstate();
                examineEstate.setId(0);
            }
            //取得楼栋信息
            BasicBuildingVo examineBuilding = basicBuildingService.getBasicBuildingByBasicApply(basicApply);
            if (examineBuilding == null) {
                examineBuilding = new BasicBuildingVo();
                examineBuilding.setId(0);
            }
            //取得单元信息
            BasicUnit examineUnit = basicUnitService.getBasicUnitByBasicApply(basicApply);
            if (examineUnit == null) {
                examineUnit = new BasicUnit();
                examineUnit.setId(0);
            }
            //取得房屋信息
            BasicHouse examineHouse = basicHouseService.getHouseByBasicApply(basicApply);
            if (examineHouse == null) {
                examineHouse = new BasicHouse();
                examineHouse.setId(0);
            }
            //取得交易信息
            BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(examineHouse.getId());
            if (houseTrading == null) {
                houseTrading = new BasicHouseTrading();
                houseTrading.setId(0);
            }
            //取得申报记录信息
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            //取得房间信息
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(examineHouse.getId());
            //取得户型信息
            BasicUnitHuxing unitHuxing = basicUnitHuxingService.getBasicUnitHuxingById(examineHouse.getHuxingId());
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
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ESTATE_NAME.getKey(), examineEstate.getName(), dataSetUseField));
                            break;
                        case SCOPE_PROPERTY://财产范围
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SCOPE_PROPERTY.getKey(), baseDataDicService.getNameById(houseTrading.getScopeProperty()), dataSetUseField, isCase));
                            break;
                        case FINANCING_CONDITIONS://融资条件
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.isBlank(houseTrading.getDownPaymentRatio()) ? "" : String.format("首付款比例%s；", houseTrading.getDownPaymentRatio()));
                            stringBuilder.append(houseTrading.getLendingRate() == null ? "" : String.format("贷款利率%s；", houseTrading.getLendingRate()));
                            stringBuilder.append(houseTrading.getLoanPeriod() == null ? "" : String.format("贷款期限%s；", houseTrading.getLoanPeriod()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.FINANCING_CONDITIONS.getKey(), stringBuilder.toString(), dataSetUseField, isCase));
                            break;
                        case TAX_BURDEN://税费负担
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TAX_BURDEN.getKey(), baseDataDicService.getNameById(houseTrading.getTaxBurden()), dataSetUseField, isCase));
                            break;
                        case PAYMENT_METHOD://付款方式
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PAYMENT_METHOD.getKey(), baseDataDicService.getNameById(houseTrading.getPaymentMethod()), dataSetUseField, isCase));
                            break;
                        case TRADING_TRANSACTION_SITUATION://交易情况
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_TRANSACTION_SITUATION.getKey(), baseDataDicService.getNameById(houseTrading.getTransactionSituation()), dataSetUseField, isCase));
                            break;
                        case TRADING_PRICE://交易价格
                            if (houseTrading.getTradingUnitPrice() != null && isCase)
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_PRICE.getKey(), String.valueOf(houseTrading.getTradingUnitPrice()), dataSetUseField));
                            else
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_PRICE.getKey(), null, dataSetUseField));
                            break;
                        case TRADING_TIME://交易时间|市场状况
                            if (isCase)
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_TIME.getKey(), DateUtils.formatDate(houseTrading.getTradingTime()), dataSetUseField, isCase));
                            else
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRADING_TIME.getKey(), DateUtils.formatDate(areaGroup.getValueTimePoint()), dataSetUseField, isCase));
                            break;
                        case PRICE_CONNOTATION://单价内涵
                            String priceConnotationName = baseDataDicService.getNameById(houseTrading.getPriceConnotation());
                            if ("其它".equals(priceConnotationName))
                                priceConnotationName = houseTrading.getPriceConnotationUnit();
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PRICE_CONNOTATION.getKey(), priceConnotationName, dataSetUseField));
                            break;
                        case LOCATION://房地产坐落
                            stringBuilder = new StringBuilder();
                            if (isCase) {
                                stringBuilder.append(basicApply.getName());
                            } else {
                                stringBuilder.append(declareRecord.getSeat());
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LOCATION.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case POSITION://房地产方位
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getPosition(examineEstate));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.POSITION.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case WITH_IMPORTANT_LOCATION_DISTANCE://与重要场所的距离
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getWithImportantLocationDistance(basicApplyBatch));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.WITH_IMPORTANT_LOCATION_DISTANCE.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case TEMPORARY_ROAD_CONDITION://临街（路）状况
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getFaceStreetExtend(basicApply));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TEMPORARY_ROAD_CONDITION.getKey(), stringBuilder.length() <= 0 ? "不临街" : stringBuilder.toString(), dataSetUseField));
                            break;
                        case FLOOR://楼栋楼层
                            stringBuilder = new StringBuilder();
                            if (StringUtils.isNotBlank(examineBuilding.getBuildingNumber()))
                                stringBuilder.append(examineBuilding.getBuildingNumber());
                            if (examineBuilding.getFloorCount() != null)
                                stringBuilder.append(String.format("%s层建筑", examineBuilding.getFloorCount()));
                            if (StringUtils.isNotBlank(examineHouse.getFloor()))
                                stringBuilder.append(String.format("第%s层", examineHouse.getFloor()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.FLOOR.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case ORIENTATION://朝向
                            if (unitHuxing == null) {
                                unitHuxing = new BasicUnitHuxing();
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ORIENTATION.getKey(), baseDataDicService.getNameById(unitHuxing.getOrientation()), dataSetUseField));
                            break;
                        case TRAFFIC_CONDITIONS://交通条件
                            stringBuilder = new StringBuilder();
                            String roadCondition = generateLoactionService.getRoadConditionExtend(basicApplyBatch);//道路状况
                            String transport = generateLoactionService.getAccessAvailableMeansTransport(basicApplyBatch);//出入可利用的交通工具
                            String trafficControl = generateLoactionService.getTrafficControl(basicApplyBatch);//交通管制情况
                            String convenience = generateLoactionService.getParkingConvenience(basicApplyBatch);//停车方便度
                            String trafficCharges = generateLoactionService.getTrafficCharges(basicApplyBatch);//交通收费情况
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
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.TRAFFIC_CONDITIONS.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case URBAN_INFRASTRUCTURE://外部基础设施
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getExternalInfrastructure(basicApplyBatch));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.URBAN_INFRASTRUCTURE.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case PUBLIC_SERVICE_FACILITIES://公共服务设施
                            stringBuilder = new StringBuilder();
                            List<String> facilities = generateLoactionService.getExternalPublicServiceFacilities(basicApplyBatch, false);
                            if (CollectionUtils.isNotEmpty(facilities)) {
                                for (String facility : facilities) {
                                    stringBuilder.append(facility);
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PUBLIC_SERVICE_FACILITIES.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case NATURAL://自然环境
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.NATURAL));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NATURAL.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case CULTURAL://人文环境
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.HUMANITY));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.CULTURAL.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case SCENERY://景观
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.SCENERY));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SCENERY.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case LAND_RIGHT_TYPE://土地权利类型
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.defaultString(declareRecord.getLandRightType()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_RIGHT_TYPE.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case LAND_RIGHT_NATURE://土地权利性质
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.defaultString(declareRecord.getLandRightNature()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_RIGHT_NATURE.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case HOUSE_NATURE://房屋性质
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HOUSE_NATURE.getKey(), StringUtils.defaultString(declareRecord.getNature()), dataSetUseField));
                            break;
                        case HOUSE_CERT_USE://房屋用途
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.defaultString(declareRecord.getCertUse()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HOUSE_CERT_USE.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case PROPERTY_MANAGEMENT://物业管理情况
                            stringBuilder = new StringBuilder();
                            if (examineBuilding.getPropertyName() != null) {
                                stringBuilder.append(examineBuilding.getPropertyName());
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PROPERTY_MANAGEMENT.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case OTHER_SPECIAL_SITUATIONS://其它特殊情况
                            String specialCase = generateEquityService.getTransferLimit(Lists.newArrayList(judgeObject), null);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.OTHER_SPECIAL_SITUATIONS.getKey(), generateCommonMethod.trimText(specialCase), dataSetUseField));
                            break;
                        case BUILDING_AREA://建筑面积（㎡）
                            String buildingArea = null;
                            if (isCase) {
                                buildingArea = examineHouse.getArea() == null ? "" : String.format("%s平方米", examineHouse.getArea());
                            } else {
                                buildingArea = judgeObject.getEvaluationArea() == null ? "" : String.format("%s平方米", judgeObject.getEvaluationArea());
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_AREA.getKey(), buildingArea, dataSetUseField));
                            break;
                        case FLOOR_HEIGHT://层高
                            String floorHeight = examineBuilding.getFloorHeight();
                            if (examineBuilding.getCurrBuildingDifference() != null) {
                                floorHeight = examineBuilding.getCurrBuildingDifference().getFloorHeight();
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.FLOOR_HEIGHT.getKey(), generateCommonMethod.trimText(floorHeight), dataSetUseField));
                            break;
                        case NET_HEIGHT://净高
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                Map<BigDecimal, String> map = Maps.newHashMap();
                                for (BasicHouseRoom o : roomList) {
                                    if (o.getClearHeight() == null) continue;
                                    if (map.containsKey(o.getClearHeight())) {
                                        map.put(o.getClearHeight(), map.get(o.getClearHeight()) + "," + o.getName());
                                    } else {
                                        map.put(o.getClearHeight(), o.getName());
                                    }
                                }
                                if (!map.isEmpty()) {
                                    for (Map.Entry<BigDecimal, String> entry : map.entrySet()) {
                                        if (map.size() > 1) stringBuilder.append(entry.getValue());
                                        stringBuilder.append(entry.getKey()).append("米");
                                    }
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NET_HEIGHT.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case BUILDING_SCALE://建筑规模
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateHouseEntityService.getBuildingScaleExtend(basicApply));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_SCALE.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case BUILDING_STRUCTURE://建筑结构
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_STRUCTURE.getKey(), baseDataDicService.getNameById(examineBuilding.getBuildingStructureCategory()), dataSetUseField));
                            break;
                        case ARCHITECTURAL_OUTFIT://建筑外装
                            stringBuilder = new StringBuilder();
                            Integer buildingId = examineBuilding.getId();
                            if (examineBuilding.getCurrBuildingDifference() != null) {
                                buildingId = examineBuilding.getCurrBuildingDifference().getId();
                            }
                            List<BasicBuildingOutfitVo> outfitList = basicBuildingOutfitService.getBasicBuildingOutfitVos(buildingId);
                            if (CollectionUtils.isNotEmpty(outfitList)) {
                                for (BasicBuildingOutfitVo outfit : outfitList) {
                                    stringBuilder.append(outfit.getDecorationPart()).append(outfit.getMaterialGradeName()).append(outfit.getDecoratingMaterialName()).append(",");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ARCHITECTURAL_OUTFIT.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case APPEARANCE://外观
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateHouseEntityService.getAppearanceExtend(basicApply));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.APPEARANCE.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case AERATION://通风
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                Map<String, String> map = Maps.newHashMap();
                                for (BasicHouseRoom basicHouseRoom : roomList) {
                                    if (StringUtils.isNotBlank(basicHouseRoom.getAeration())) {
                                        map.put(basicHouseRoom.getName(), basicHouseRoom.getAeration());
                                    }
                                }
                                stringBuilder.append(generateCommonMethod.stringSummaryDesc(map, "", ","));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.AERATION.getKey(), StringUtils.strip(stringBuilder.toString(), "、"), dataSetUseField));
                            break;
                        case LIGHTING://采光
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                Map<String, String> map = Maps.newHashMap();
                                for (BasicHouseRoom basicHouseRoom : roomList) {
                                    if (StringUtils.isNotBlank(basicHouseRoom.getLighting())) {
                                        map.put(basicHouseRoom.getName(), basicHouseRoom.getLighting());
                                    }
                                }
                                stringBuilder.append(generateCommonMethod.stringSummaryDesc(map, "", ","));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LIGHTING.getKey(), StringUtils.strip(stringBuilder.toString(), "、"), dataSetUseField));
                            break;
                        case SUNSHINE://日照
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                Map<String, String> map = Maps.newHashMap();
                                for (BasicHouseRoom basicHouseRoom : roomList) {
                                    if (StringUtils.isNotBlank(basicHouseRoom.getSunshine())) {
                                        map.put(basicHouseRoom.getName(), basicHouseRoom.getSunshine());
                                    }
                                }
                                stringBuilder.append(generateCommonMethod.stringSummaryDesc(map, "", ","));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SUNSHINE.getKey(), StringUtils.strip(stringBuilder.toString(), "、"), dataSetUseField));
                            break;
                        case SOUND_INSULATION://隔音
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                Map<String, String> map = Maps.newHashMap();
                                for (BasicHouseRoom basicHouseRoom : roomList) {
                                    if (StringUtils.isNotBlank(basicHouseRoom.getSoundInsulation())) {
                                        map.put(basicHouseRoom.getName(), basicHouseRoom.getSoundInsulation());
                                    }
                                }
                                stringBuilder.append(generateCommonMethod.stringSummaryDesc(map, "", ","));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.SOUND_INSULATION.getKey(), StringUtils.strip(stringBuilder.toString(), "、"), dataSetUseField));
                            break;
                        case HEAT_PRESERVATION://保温
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                BaseDataDic heatPreservationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_PRESERVATION);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, heatPreservationDic);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HEAT_PRESERVATION.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case HEAT_INSULATION://隔热
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, heatInsulationDic);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HEAT_INSULATION.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case WATERPROOF://防水
                            stringBuilder = new StringBuilder();
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                BaseDataDic waterproofDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_WATERPROOF);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, waterproofDic);
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.WATERPROOF.getKey(), stringBuilder.toString(), dataSetUseField));
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
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.INTELLIGENT_LEVEL.getKey(), StringUtils.strip(stringBuilder.toString(), "、"), dataSetUseField));
                            break;
                        case WATER_SUPPLY_DRAINAGE_MODE://供（排）水方式
                            stringBuilder = new StringBuilder();
                            List<BasicHouseWater> waterList = basicHouseWaterService.getBasicHouseWaterList(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(waterList)) {
                                for (BasicHouseWater houseWater : waterList) {
                                    stringBuilder.append("采用");
                                    stringBuilder.append(houseWater.getGrade() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getGrade())));//档次
                                    stringBuilder.append(houseWater.getPipeMaterial() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getPipeMaterial())));//给水管材料
                                    stringBuilder.append(houseWater.getPipingLayout() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getPipingLayout())));//给水管道布置
                                    stringBuilder.append(houseWater.getSupplyMode() == null ? "" : String.format("%s", baseDataDicService.getNameById(houseWater.getSupplyMode())));//给水方式
                                    stringBuilder.append(";");
                                }
                            }
                            List<BasicHouseWaterDrain> drainList = basicHouseWaterDrainService.getBasicHouseWaterDrainList(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(drainList)) {
                                for (BasicHouseWaterDrain houseWaterDrain : drainList) {
                                    stringBuilder.append(houseWaterDrain.getType() == null ? "" : String.format("%s采用", baseDataDicService.getNameById(houseWaterDrain.getType())));//类别
                                    stringBuilder.append(houseWaterDrain.getDrainSystem() == null ? "" : baseDataDicService.getNameById(houseWaterDrain.getDrainSystem()));
                                    stringBuilder.append(houseWaterDrain.getProcessingMode() == null ? "" : String.format("%s处理；", baseDataDicService.getNameById(houseWaterDrain.getProcessingMode())));//排水处理方式
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.WATER_SUPPLY_DRAINAGE_MODE.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
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
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.HEATING_MODE.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case NETWORK://电力通信网络
                            stringBuilder = new StringBuilder();
                            List<BasicHouseIntelligentVo> intelligentList = basicHouseIntelligentService.getBasicHouseIntelligentVos(examineHouse.getId());
                            stringBuilder.append(this.getIntelligentNet(intelligentList));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NETWORK.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case ELEVATOR_HOUSEHOLD_RATIO://电梯梯户比
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.ELEVATOR_HOUSEHOLD_RATIO.getKey(), examineUnit.getElevatorHouseholdRatio(), dataSetUseField));
                            break;
                        case INTERNAL_ASSEMBLY://内装
                            stringBuilder = new StringBuilder();
                            List<BasicUnitDecorateVo> decorateList = basicUnitDecorateService.getBasicUnitDecorateList(examineUnit.getId());
                            if (CollectionUtils.isNotEmpty(decorateList)) {
                                for (BasicUnitDecorateVo unitDecorate : decorateList) {
                                    if (StringUtils.isNotBlank(unitDecorate.getUnitCommonPart()))
                                        stringBuilder.append(unitDecorate.getUnitCommonPart()).append("的");
                                    if (StringUtils.isNotBlank(unitDecorate.getUnitCommonPart()))
                                        stringBuilder.append(unitDecorate.getDecorationPartName()).append("为");
                                    stringBuilder.append(unitDecorate.getMaterialGradeName()).append(unitDecorate.getDecoratingMaterialName()).append(";");
                                }
                            }
                            if (CollectionUtils.isNotEmpty(roomList)) {//房间内装
                                List<BasicHouseRoomDecorateVo> roomDecorateList = basicHouseRoomDecorateService.getHouseRoomDecorateList(examineHouse.getId());
                                if (CollectionUtils.isNotEmpty(roomDecorateList)) {
                                    for (BasicHouseRoomDecorateVo roomDecorate : roomDecorateList) {
                                        if (StringUtils.isNotBlank(roomDecorate.getLocation()))
                                            stringBuilder.append(roomDecorate.getLocation()).append("的");
                                        if (StringUtils.isNotBlank(roomDecorate.getPartName()))
                                            stringBuilder.append(roomDecorate.getPartName()).append("为");
                                        if (StringUtils.isNotBlank(roomDecorate.getRemark())) {
                                            stringBuilder.append(roomDecorate.getRemark()).append(";");
                                        } else if (StringUtils.isNotBlank(roomDecorate.getMaterialName())) {
                                            stringBuilder.append(roomDecorate.getMaterialName()).append(";");
                                        }
                                    }
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.INTERNAL_ASSEMBLY.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case PLANE_LAYOUT://平面布局
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(unitHuxing.getName()).append(";");
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(room.getName());
                                    if (StringUtils.isNotBlank(room.getOpening()))
                                        stringBuilder.append(String.format("开间:%s米,", room.getOpening()));
                                    if (StringUtils.isNotBlank(room.getDepth()))
                                        stringBuilder.append(String.format("进深:%s米,", room.getDepth()));
                                    if (StringUtils.isNotBlank(room.getLength()))
                                        stringBuilder.append(String.format("长:%s米,", room.getLength()));
                                    if (StringUtils.isNotBlank(room.getWidth()))
                                        stringBuilder.append(String.format("宽:%s米", room.getWidth()));
                                    stringBuilder.append(";");
                                }
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.PLANE_LAYOUT.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case NEW_DEGREE://成新度
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(examineHouse.getNewDegree());
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.NEW_DEGREE.getKey(), stringBuilder.toString(), dataSetUseField));
                            break;
                        case Other://其它
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(generateHouseEntityService.getOtherExtend(basicApply));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.Other.getKey(), generateCommonMethod.trimText(stringBuilder.toString()), dataSetUseField));
                            break;
                        case BUILDING_YEAR://竣工时间
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.BUILDING_YEAR.getKey(), DateUtils.formatDate(examineBuilding.getBeCompletedTime(), DateUtils.DATE_YYYY_MM), dataSetUseField, isCase));
                            break;
                        case CONSTRUCTION_QUALITY://工程质量
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.CONSTRUCTION_QUALITY.getKey(), baseDataDicService.getNameById(examineBuilding.getConstructionQuality()), dataSetUseField));
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

    /**
     * 获取土地比较法各个字段对应值
     *
     * @param setUseFieldList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String getLandCompareInfo(SchemeAreaGroup areaGroup, SchemeJudgeObject judgeObject, BasicApply basicApply, List<DataSetUseField> setUseFieldList, Boolean isCase) {
        try {
            if (CollectionUtils.isEmpty(setUseFieldList)) return null;
            if (basicApply == null) return null;
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApply.getApplyBatchId());
            //取得楼盘信息
            BasicEstate examineEstate = basicEstateService.getBasicEstateByBasicApply(basicApply);
            if (examineEstate == null) {
                examineEstate = new BasicEstate();
                examineEstate.setId(0);
            }
            //取得房屋信息
            BasicHouse examineHouse = basicHouseService.getHouseByBasicApply(basicApply);
            if (examineHouse == null) {
                examineHouse = new BasicHouse();
                examineHouse.setId(0);
            }
            //取得土地类型相关信息
            BasicEstateLandCategoryInfo categoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoById(basicApply.getLandCategoryId());
            if (categoryInfo == null) {
                categoryInfo = new BasicEstateLandCategoryInfo();
                categoryInfo.setId(0);
            }
            //取得交易信息
            BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(examineHouse.getId());
            if (houseTrading == null) {
                houseTrading = new BasicHouseTrading();
                houseTrading.setId(0);
            }

            StringBuilder stringBuilder = null;
            List<MarketCompareItemDto> list = Lists.newArrayList();
            for (DataSetUseField dataSetUseField : setUseFieldList) {
                if (StringUtils.isBlank(dataSetUseField.getFieldName())) continue;
                MethodCompareFieldEnum compareFieldEnum = MethodCompareFieldEnum.getEnumByKey(dataSetUseField.getFieldName());
                if (compareFieldEnum == null) continue;
                try {
                    switch (compareFieldEnum) {
                        case LAND_NAME://地块名称
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_NAME.getKey(), examineEstate.getName(), dataSetUseField));
                            break;
                        case LAND_SCOPE_PROPERTY://财产范围
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_SCOPE_PROPERTY.getKey(), baseDataDicService.getNameById(houseTrading.getScopeProperty()), dataSetUseField, isCase));
                            break;
                        case LAND_FINANCING_CONDITIONS://融资条件
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.isBlank(houseTrading.getDownPaymentRatio()) ? "" : String.format("首付款比例%s；", houseTrading.getDownPaymentRatio()));
                            stringBuilder.append(houseTrading.getLendingRate() == null ? "" : String.format("贷款利率%s；", houseTrading.getLendingRate()));
                            stringBuilder.append(houseTrading.getLoanPeriod() == null ? "" : String.format("贷款期限%s；", houseTrading.getLoanPeriod()));
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_FINANCING_CONDITIONS.getKey(), stringBuilder.toString(), dataSetUseField, isCase));
                            break;
                        case LAND_TAX_BURDEN://税费负担
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TAX_BURDEN.getKey(), baseDataDicService.getNameById(houseTrading.getTaxBurden()), dataSetUseField, isCase));
                            break;
                        case LAND_PAYMENT_METHOD://付款方式
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_PAYMENT_METHOD.getKey(), baseDataDicService.getNameById(houseTrading.getPaymentMethod()), dataSetUseField, isCase));
                            break;
                        case LAND_TRADING_PRICE://交易单价
                            if (houseTrading.getTradingUnitPrice() != null && isCase)
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_PRICE.getKey(), String.valueOf(houseTrading.getTradingUnitPrice()), dataSetUseField));
                            else
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_PRICE.getKey(), null, dataSetUseField));
                            break;
                        case LAND_TRADING_TRANSACTION_SITUATION://交易情况
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_TRANSACTION_SITUATION.getKey(), baseDataDicService.getNameById(houseTrading.getTransactionSituation()), dataSetUseField, isCase));
                            break;
                        case LAND_TRADING_TIME://交易时间|市场状况
                            if (isCase)
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_TIME.getKey(), DateUtils.formatDate(houseTrading.getTradingTime()), dataSetUseField, isCase));
                            else
                                list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRADING_TIME.getKey(), DateUtils.formatDate(areaGroup.getValueTimePoint()), dataSetUseField, isCase));
                            break;
                        case LAND_PRICE_CONNOTATION://单价内涵
                            String priceConnotationName = baseDataDicService.getNameById(houseTrading.getPriceConnotation());
                            if ("其它".equals(priceConnotationName))
                                priceConnotationName = houseTrading.getPriceConnotationUnit();
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_PRICE_CONNOTATION.getKey(), priceConnotationName, dataSetUseField));
                            break;
                        case LAND_AREA_LOCATION://区域位置
                            String areaLocation = generateLandFactorService.getAreaLocation(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_AREA_LOCATION.getKey(), generateCommonMethod.trimText(areaLocation), dataSetUseField));
                            break;
                        case LAND_AGGLOMERATION_DEGREE://产业聚集度
                            String agglomerationDegree = generateLandFactorService.getAgglomerationDegree(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_AGGLOMERATION_DEGREE.getKey(), generateCommonMethod.trimText(agglomerationDegree), dataSetUseField));
                            break;
                        case LAND_TRAFFIC_CONDITIONS://交通条件
                            String trafficConditions = generateLandFactorService.getTrafficConditions(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TRAFFIC_CONDITIONS.getKey(), generateCommonMethod.trimText(trafficConditions), dataSetUseField));
                            break;
                        case LAND_UPPORTING_FACILITY://配套设施条件
                            String upportingFacility = generateLandFactorService.getUpportingFacility(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_UPPORTING_FACILITY.getKey(), generateCommonMethod.trimText(upportingFacility), dataSetUseField));
                            break;
                        case LAND_ENVIRONMENT_CONDITION://环境条件
                            String environmentCondition = generateLandFactorService.getEnvironmentCondition(basicApplyBatch);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_ENVIRONMENT_CONDITION.getKey(), generateCommonMethod.trimText(environmentCondition), dataSetUseField));
                            break;
                        case LAND_PLANNING_CONDITION://规划条件
                            String planningCondition = generateLandFactorService.getPlanningCondition(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_PLANNING_CONDITION.getKey(), generateCommonMethod.trimText(planningCondition), dataSetUseField));
                            break;
                        case LAND_AREA://面积
                            String area = "";
                            if (isCase) {
                                area = String.format("占地面积%s平方米，", examineEstate.getCoverAnArea());
                            } else {
                                area = String.format("评估面积%s平方米", judgeObject.getEvaluationArea());
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_AREA.getKey(), area, dataSetUseField));
                            break;
                        case LAND_USE://用途
                            String use = "";
                            if (isCase) {
                                use = categoryInfo.getLandUseType();
                            } else {
                                use = dataSetUseFieldService.getCacheSetUseFieldById(judgeObject.getSetUse()).getName();
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_USE.getKey(), use, dataSetUseField));
                            break;
                        case LAND_TEMPORARY_ROAD_CONDITION://临街（路）状况
                            String temporaryRoadCondition = null;
                            if (isCase) {
                                temporaryRoadCondition = generateLoactionService.getFaceStreetExtend(basicApply);
                            } else {
                                temporaryRoadCondition = generateLandFactorService.getTemporaryRoadCondition(Lists.newArrayList(judgeObject));
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TEMPORARY_ROAD_CONDITION.getKey(), generateCommonMethod.trimText(temporaryRoadCondition), dataSetUseField));
                            break;
                        case LAND_SHAPE://形状
                            String shape = categoryInfo.getLandShape();
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_SHAPE.getKey(), generateCommonMethod.trimText(shape), dataSetUseField));
                            break;
                        case LAND_GEOLOGY://地质
                            String geology=generateLandFactorService.getGeology(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_GEOLOGY.getKey(), generateCommonMethod.trimText(geology), dataSetUseField));
                            break;
                        case LAND_TOPOGRAPHY://地形、地势
                            String topography = generateLandFactorService.getTopography(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_TOPOGRAPHY.getKey(), generateCommonMethod.trimText(topography), dataSetUseField));
                            break;
                        case LAND_PLOT_RATIO://容积率
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_PLOT_RATIO.getKey(), generateCommonMethod.trimText(categoryInfo.getPlotRatio() == null ? "" : categoryInfo.getPlotRatio().toString()), dataSetUseField));
                            break;
                        case LAND_DEVELOPMENT_LEVEL://宗地开发程度
                            String developmentLevel = generateLandFactorService.getDevelopmentLevel(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_DEVELOPMENT_LEVEL.getKey(), generateCommonMethod.trimText(developmentLevel), dataSetUseField));
                            break;
                        case LAND_USEFUL_LIFE://剩余使用年限
                            String usefulLife = null;
                            if (isCase) {
                                if (categoryInfo.getTerminationData() != null && categoryInfo.getAcquisitionTime() != null)
                                    usefulLife = publicService.diffDateYear(categoryInfo.getTerminationData(), categoryInfo.getAcquisitionTime()).toString();
                            } else {
                                if (categoryInfo.getTerminationData() != null && areaGroup.getValueTimePoint() != null)
                                    usefulLife = publicService.diffDateYear(categoryInfo.getTerminationData(), areaGroup.getValueTimePoint()).toString();
                            }
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_USEFUL_LIFE.getKey(), generateCommonMethod.trimText(usefulLife), dataSetUseField));
                            break;
                        case LAND_RIGHT_CONDITION://土地权利状况
                            String rightCondition = generateLandFactorService.getRightCondition(examineEstate);
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_RIGHT_CONDITION.getKey(), generateCommonMethod.trimText(rightCondition), dataSetUseField));
                            break;
                        case LAND_OTHER://土地其它
                            String other = "无";
                            list.add(getMarketCompareItemDto(MethodCompareFieldEnum.LAND_OTHER.getKey(), generateCommonMethod.trimText(other), dataSetUseField));
                            break;
                        case Land_Annual_Coefficient:
                            break;
                        case Land_VolumeRatio_Coefficient:
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

    private MarketCompareItemDto getMarketCompareItemDto(String name, String value, DataSetUseField dataSetUseField) {
        return getMarketCompareItemDto(name, value, dataSetUseField, true);
    }

    private MarketCompareItemDto getMarketCompareItemDto(String name, String value, DataSetUseField dataSetUseField, Boolean isCase) {
        value = StringUtils.isBlank(value) ? (isCase ? "无" : "") : value;
        MarketCompareItemDto marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName(name);
        marketCompareItemDto.setDesc(dataSetUseField.getName());
        marketCompareItemDto.setScore(new BigDecimal(100));
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        marketCompareItemDto.setValue(generateCommonMethod.trimText(value));
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
                if (StringUtils.isNotBlank(intelligentVoList.get(j).getGradeName())) {
                    stringBuilder.append(intelligentVoList.get(j).getGradeName());
                }
                if (StringUtils.isNotBlank(intelligentVoList.get(j).getSwitchCircuitName())) {
                    stringBuilder.append(intelligentVoList.get(j).getSwitchCircuitName());
                }
                if (StringUtils.isNotBlank(intelligentVoList.get(j).getLayingMethodName())) {
                    stringBuilder.append(intelligentVoList.get(j).getLayingMethodName()).append("铺设");
                }
                if (StringUtils.isNotBlank(intelligentVoList.get(j).getLampsLanternsName())) {
                    stringBuilder.append(",");
                    stringBuilder.append("灯具为");
                    stringBuilder.append(intelligentVoList.get(j).getLampsLanternsName());
                }
                if (StringUtils.isNotBlank(intelligentVoList.get(j).getIntelligentSystemName())) {
                    stringBuilder.append(",");
                    stringBuilder.append("智能系统中");
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
