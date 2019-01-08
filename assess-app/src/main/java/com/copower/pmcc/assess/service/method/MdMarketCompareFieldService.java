package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessMarketCompareConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.dao.data.DataPropertyDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseIntelligentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingEnvironmentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SurveyAssetInventoryRightDao surveyAssetInventoryRightDao;
    @Autowired
    private DataPropertyDao dataPropertyDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
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
    private BasicMatchingTrafficService basicMatchingTrafficService;
    @Autowired
    private BasicMatchingEnvironmentService basicMatchingEnvironmentService;
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
    private BasicEstateNetworkService basicEstateNetworkService;
    @Autowired
    private BasicMatchingEducationService basicMatchingEducationService;
    @Autowired
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    @Autowired
    private BasicMatchingMedicalService basicMatchingMedicalService;
    @Autowired
    private BasicMatchingFinanceService basicMatchingFinanceService;
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

    /**
     * 获取市场比较法各个字段对应值
     *
     * @param planDetailsId
     * @param setUseFieldList
     * @return
     */
    public String getJsonContent(ProjectInfo projectInfo, Integer declareId, Integer planDetailsId, List<DataSetUseField> setUseFieldList) {
        try {
            if (CollectionUtils.isEmpty(setUseFieldList)) return null;
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId);
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
            //取得版块信息
            DataBlock block = dataBlockService.getDataBlockById(examineEstate.getBlockId());
            block = block == null ? new DataBlock() : block;
            //取得交易信息
            BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(examineHouse.getId());
            houseTrading = houseTrading == null ? new BasicHouseTrading() : houseTrading;
            //取得申报记录信息
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
            declareRecord = declareRecord == null ? new DeclareRecord() : declareRecord;
            //取得交通信息
            List<BasicMatchingTrafficVo> trafficList = basicMatchingTrafficService.getBasicMatchingTrafficVos(examineEstate.getId());
            //取得环境相关信息
            List<BasicMatchingEnvironmentVo> environmentList = basicMatchingEnvironmentService.getBasicMatchingEnvironmentVos(examineEstate.getId());
            //取得他项权利
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
            List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            ProjectPlanDetails inventoryPlanDetails = planDetailsList.get(0);
            List<SurveyAssetInventoryRight> inventoryRights = surveyAssetInventoryRightDao.getListByPlanDetailsId(inventoryPlanDetails.getId());
            //取得土地实体情况
            BasicEstateLandState landState = basicEstateLandStateService.getLandStateByEstateId(examineEstate.getId());
            landState = landState == null ? new BasicEstateLandState() : landState;
            //取得房间信息
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(examineHouse.getId());
            //取得建筑功能
            List<BasicBuildingFunction> buildingFunctions = basicBuildingFunctionService.getBasicBuildingFunctionList(examineBuilding.getId());
            //取得楼盘供应信息（供水、供电、供热、供气）
            List<BasicEstateSupply> estateSupplyList = basicEstateSupplyService.getBasicEstateSupplyList(examineEstate.getId());
            //取得房间供应信息(供暖、空调、新风)
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
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ESTATE_NAME, examineEstate.getName()));
                            break;
                        case SCOPE_PROPERTY://财产范围
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SCOPE_PROPERTY, baseDataDicService.getNameById(houseTrading.getScopeProperty())));
                            break;
                        case FINANCING_CONDITIONS://融资条件
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.isBlank(houseTrading.getDownPaymentRatio()) ? "" : String.format("首付款比例:%s、", houseTrading.getDownPaymentRatio()));
                            stringBuilder.append(houseTrading.getLendingRate() == null ? "" : String.format("贷款利率:%s、", houseTrading.getLendingRate()));
                            stringBuilder.append(houseTrading.getLoanPeriod() == null ? "" : String.format("贷款期限:%s、", houseTrading.getLoanPeriod()));
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FINANCING_CONDITIONS, stringBuilder.toString()));
                            break;
                        case TAX_BURDEN://税费负担
                            if (houseTrading.getTaxBurden() != null)
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TAX_BURDEN, baseDataDicService.getNameById(houseTrading.getTaxBurden())));
                            break;
                        case PAYMENT_METHOD://付款方式
                            if (houseTrading.getPaymentMethod() == null)
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PAYMENT_METHOD, baseDataDicService.getNameById(houseTrading.getPaymentMethod())));
                            break;
                        case TRADING_PRICE://交易价格
                            if (houseTrading.getTradingUnitPrice() != null)
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TRADING_PRICE, String.valueOf(houseTrading.getTradingUnitPrice())));
                            break;
                        case LOCATION://房地产坐落及方位
                            stringBuilder = new StringBuilder();
                            if (StringUtils.isNotBlank(declareRecord.getSeat()))
                                stringBuilder.append(declareRecord.getSeat()).append("、");

                            if (StringUtils.isNotBlank(block.getPosition()))
                                stringBuilder.append(block.getPosition()).append("、");

                            if (examineEstate.getPosition() == null)
                                stringBuilder.append(baseDataDicService.getNameById(examineEstate.getPosition())).append("、");
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LOCATION, stringBuilder.toString()));
                            break;
                        case OFFICE_CONCENTRATION://办公集聚度
                            //取得交通枢纽信息
                            if (CollectionUtils.isNotEmpty(trafficList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicMatchingTraffic examineMatchingTraffic : trafficList) {
                                    if (StringUtils.equals(examineMatchingTraffic.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())) {
                                        stringBuilder.append(examineMatchingTraffic.getName());
                                        stringBuilder.append(baseDataDicService.getNameById(examineMatchingTraffic.getDistance()));
                                        stringBuilder.append("、");
                                    }
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.OFFICE_CONCENTRATION, stringBuilder.toString()));
                            }
                            break;
                        case FLOOR://楼层
                            if (examineHouse.getFloor() != null)
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR, String.valueOf(examineHouse.getFloor())));
                            break;
                        case ORIENTATION://朝向
                            if (examineHouse.getOrientation() != null)
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ORIENTATION, baseDataDicService.getNameById(examineHouse.getOrientation())));
                            break;
                        case TRAFFIC_CONDITIONS://交通条件
                            if (CollectionUtils.isNotEmpty(trafficList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicMatchingTraffic examineMatchingTraffic : trafficList) {
                                    if (!StringUtils.equals(examineMatchingTraffic.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())) {
                                        stringBuilder.append(examineMatchingTraffic.getName());
                                        stringBuilder.append(baseDataDicService.getNameById(examineMatchingTraffic.getDistance()));
                                        stringBuilder.append("、");
                                    }
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TRAFFIC_CONDITIONS, stringBuilder.toString()));
                            }
                            break;
                        case URBAN_INFRASTRUCTURE://城市基础设施
                            stringBuilder = new StringBuilder();
                            BasicApplyTypeEnum basicApplyTypeEnum = BasicApplyTypeEnum.getEnumById(basicApply.getType());
                            switch (basicApplyTypeEnum) {
                                case INDUSTRY:
                                    //楼盘下供电
                                    if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                        for (BasicEstateSupply supply : estateSupplyList) {
                                            if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName())) {
                                                getCommonSupply(stringBuilder, supply);
                                            }
                                        }
                                    }

                                    //楼盘下供水
                                    if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                        for (BasicEstateSupply supply : estateSupplyList) {
                                            if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName())) {
                                                getCommonSupply(stringBuilder, supply);
                                            }
                                        }
                                    }

                                    //楼盘下排水
                                    if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                        for (BasicEstateSupply supply : estateSupplyList) {
                                            if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName())) {
                                                getCommonSupply(stringBuilder, supply);
                                            }
                                        }
                                    }

                                    //楼盘下采暖供热
                                    if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                        for (BasicEstateSupply supply : estateSupplyList) {
                                            if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName())) {
                                                getCommonSupply(stringBuilder, supply);
                                            }
                                        }
                                    }

                                    //楼盘下供气
                                    if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                        for (BasicEstateSupply supply : estateSupplyList) {
                                            if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName())) {
                                                getCommonSupply(stringBuilder, supply);
                                            }
                                        }
                                    }
                                    break;
                                case RESIDENCE:
                                    stringBuilder.append(examineEstate.getSupplyGas() == null ? "" : String.format("供气:%s、", baseDataDicService.getNameById(examineEstate.getSupplyGas())));
                                    stringBuilder.append(examineEstate.getSupplyPower() == null ? "" : String.format("供电:%s、", baseDataDicService.getNameById(examineEstate.getSupplyPower())));
                                    stringBuilder.append(examineEstate.getSupplyWater() == null ? "" : String.format("供水:%s、", baseDataDicService.getNameById(examineEstate.getSupplyWater())));
                                    stringBuilder.append(examineEstate.getDrainWater() == null ? "" : String.format("排水:%s、", baseDataDicService.getNameById(examineEstate.getDrainWater())));
                                    stringBuilder.append(examineEstate.getSupplyHeating() == null ? "" : String.format("供热:%s、", baseDataDicService.getNameById(examineEstate.getSupplyHeating())));
                                    break;
                            }
                            //楼盘下通信网络
                            List<BasicEstateNetwork> networkList = basicEstateNetworkService.getBasicEstateNetworkList(examineEstate.getId());
                            if (CollectionUtils.isNotEmpty(networkList)) {
                                for (BasicEstateNetwork network : networkList) {
                                    stringBuilder.append(network.getSupplier() == null ? "" : String.format("名称:%s、", baseDataDicService.getNameById(network.getSupplier())));
                                    stringBuilder.append(network.getServiceContent() == null ? "" : String.format("服务内容:%s、", baseDataDicService.getNameById(network.getServiceContent())));
                                    stringBuilder.append(StringUtils.isEmpty(network.getRemark()) ? "" : String.format("描述:%s、", network.getRemark()));
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.URBAN_INFRASTRUCTURE, stringBuilder.toString()));
                            break;
                        case PUBLIC_SERVICE_FACILITIES://公共服务设施
                            stringBuilder = new StringBuilder();
                            List<BasicMatchingEducation> educationList = basicMatchingEducationService.getBasicMatchingEducationList(examineEstate.getId());//教育
                            if (CollectionUtils.isNotEmpty(educationList)) {
                                for (BasicMatchingEducation education : educationList) {
                                    stringBuilder.append(StringUtils.isEmpty(education.getSchoolName()) ? "" : String.format("学校名称:%s、", education.getSchoolName()));
                                    stringBuilder.append(education.getSchoolNature() == null ? "" : String.format("学校性质:%s、", baseDataDicService.getNameById(education.getSchoolNature())));
                                    stringBuilder.append(education.getSchoolGradation() == null ? "" : String.format("学校级次:%s、", baseDataDicService.getNameById(education.getSchoolGradation())));
                                    stringBuilder.append(StringUtils.isBlank(education.getSchoolLevel()) ? "" : String.format("学校等级:%s、", baseDataDicService.getNameById(Integer.valueOf(education.getSchoolLevel()))));
                                    stringBuilder.append(education.getDistance() == null ? "" : String.format("距离:%s、", baseDataDicService.getNameById(education.getDistance())));
                                }
                            }
                            List<BasicMatchingLeisurePlace> leisurePlaceList = basicMatchingLeisurePlaceService.getBasicMatchingLeisurePlaceList(examineEstate.getId());//休闲娱乐
                            if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                    stringBuilder.append(StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s、", leisurePlace.getName()));
                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s、", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s、", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s、", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                }
                            }
                            List<BasicMatchingMedical> medicalList = basicMatchingMedicalService.getBasicMatchingMedicalList(examineEstate.getId());//医养
                            if (CollectionUtils.isNotEmpty(medicalList)) {
                                for (BasicMatchingMedical medical : medicalList) {
                                    stringBuilder.append(StringUtils.isEmpty(medical.getOrganizationName()) ? "" : String.format("机构名称:%s、", medical.getOrganizationName()));
                                    stringBuilder.append(medical.getBedNumber() == null ? "" : String.format("床位数:%s、", baseDataDicService.getNameById(medical.getBedNumber())));
                                    stringBuilder.append(medical.getOrganizationLevel() == null ? "" : String.format("机构等级:%s、", baseDataDicService.getNameById(Integer.valueOf(medical.getOrganizationLevel()))));
                                    stringBuilder.append(medical.getDistance() == null ? "" : String.format("距离:%s、", baseDataDicService.getNameById(medical.getDistance())));
                                }
                            }
                            List<BasicMatchingFinance> financeList = basicMatchingFinanceService.getBasicMatchingFinanceList(examineEstate.getId());//金融
                            if (CollectionUtils.isNotEmpty(financeList)) {
                                for (BasicMatchingFinance finance : financeList) {
                                    stringBuilder.append(StringUtils.isEmpty(finance.getName()) ? "" : String.format("名称:%s、", finance.getName()));
                                    stringBuilder.append(finance.getCategory() == null ? "" : String.format("类别:%s、", baseDataDicService.getNameById(finance.getCategory())));
                                    stringBuilder.append(finance.getNature() == null ? "" : String.format("金融机构性质:%s、", baseDataDicService.getNameById(finance.getNature())));
                                    stringBuilder.append(StringUtils.isEmpty(finance.getName()) ? "" : String.format("服务内容:%s、", finance.getName()));
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PUBLIC_SERVICE_FACILITIES, stringBuilder.toString()));
                            break;
                        case NATURAL://自然环境
                            if (CollectionUtils.isNotEmpty(environmentList)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic naturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_NATURAL);
                                for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                    getEnvironmentString(stringBuilder, naturalDic, examineMatchingEnvironment);
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NATURAL, stringBuilder.toString()));
                            }
                            break;
                        case CULTURAL://人文环境
                            if (CollectionUtils.isNotEmpty(environmentList)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic culturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_CULTURAL);
                                for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                    getEnvironmentString(stringBuilder, culturalDic, examineMatchingEnvironment);
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.CULTURAL, stringBuilder.toString()));
                            }
                            break;
                        case SCENERY://景观
                            if (CollectionUtils.isNotEmpty(environmentList)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                                for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                    getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SCENERY, stringBuilder.toString()));
                            }
                            break;
                        case FEEINTEREST_TYPE://土地权益类型

                            break;
                        case LAND_CONTROL_SITUATION://土地管制情况
                            stringBuilder = new StringBuilder();
                            if (examineHouse.getCertUse() != null) {//证载用途
                                stringBuilder.append(String.format("证载用途:%s、", baseDataDicService.getNameById(examineHouse.getCertUse())));
                            }
                            if (examineEstate.getFloorArea() != null) {
                                stringBuilder.append(String.format("建筑面积:%s、", examineEstate.getFloorArea()));
                            }
                            if (examineEstate.getCoverAnArea() != null) {
                                stringBuilder.append(String.format("占地面积:%s、", examineEstate.getCoverAnArea()));
                            }
                            if (examineEstate.getVolumetricRate() != null) {
                                stringBuilder.append(String.format("容积率:%s、", examineEstate.getVolumetricRate()));
                            }
                            if (examineEstate.getGreeningRate() != null) {
                                stringBuilder.append(String.format("绿化率:%s、", examineEstate.getGreeningRate()));
                            }
                            if (examineBuilding.getBuildingHeight() != null) {
                                stringBuilder.append(String.format("建筑高度:%s、", examineBuilding.getBuildingHeight()));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LAND_CONTROL_SITUATION, stringBuilder.toString()));
                            break;
                        case LAND_RIGHTS://土地他项权利
                            if (CollectionUtils.isNotEmpty(inventoryRights)) {
                                stringBuilder = new StringBuilder();
                                for (SurveyAssetInventoryRight inventoryRight : inventoryRights) {
                                    stringBuilder.append(String.format("%s、", baseDataDicService.getNameById(inventoryRight.getCategory())));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LAND_RIGHTS, stringBuilder.toString()));
                            }
                            break;
                        case HOUSING_OWNERSHIP://房屋所有权

                            break;
                        case LEASEHOLD://租赁情况
                            if (CollectionUtils.isNotEmpty(inventoryRights)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic leaseholdDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_LEASEHOLD);
                                for (SurveyAssetInventoryRight inventoryRight : inventoryRights) {
                                    if (inventoryRight.getCategory().equals(leaseholdDic.getId())) {
                                        stringBuilder.append(String.format("他权证编号:%s、", inventoryRight.getNumber()));
                                        stringBuilder.append(String.format("义务人:%s、", inventoryRight.getObligor()));
                                        stringBuilder.append(String.format("权利人:%s、", inventoryRight.getObligee()));
                                        stringBuilder.append(String.format("登记面积:%s、", inventoryRight.getRegisterArea()));
                                        stringBuilder.append(String.format("他权级次:%s、", inventoryRight.getRightRank()));
                                    }
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LEASEHOLD, stringBuilder.toString()));
                            }
                            break;
                        case PROPERTY_MANAGEMENT://物业管理情况
                            if (examineBuilding.getPropertyType() != null) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(StringUtils.isBlank(examineBuilding.getProperty()) ? "" : examineBuilding.getProperty()).append("、");
                                stringBuilder.append(String.format("物业费:%s、", examineBuilding.getPropertyFee()));
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PROPERTY_MANAGEMENT, stringBuilder.toString()));
                            }
                            break;
                        case OTHER_SPECIAL_SITUATIONS://其它特殊情况
                            SurveyAssetInventory assetInventory = surveyAssetInventoryDao.getDataByPlanDetailsId(inventoryPlanDetails.getId());
                            if (assetInventory != null) {
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.OTHER_SPECIAL_SITUATIONS, assetInventory.getSpecialCase()));
                            }
                            break;
                        case LAND_ENTITY_STATUS://土地实体状况
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(StringUtils.isEmpty(landState.getName()) ? "" : String.format("土地名称:%s、", landState.getName()));
                            stringBuilder.append(landState.getLandUseType() == null ? "" : String.format("土地用途类型:%s、", baseDataDicService.getNameById(landState.getLandUseType())));
                            stringBuilder.append(landState.getLandUseCategory() == null ? "" : String.format("土地用途类别:%s、", baseDataDicService.getNameById(landState.getLandUseCategory())));
                            stringBuilder.append(landState.getLandLevel() == null ? "" : String.format("土地级别:%s、", baseDataDicService.getNameById(landState.getLandLevel())));
                            stringBuilder.append(StringUtils.isEmpty(landState.getEastTo()) ? "" : String.format("东至:%s、", landState.getEastTo()));
                            stringBuilder.append(StringUtils.isEmpty(landState.getSouthTo()) ? "" : String.format("南至:%s、", landState.getSouthTo()));
                            stringBuilder.append(StringUtils.isEmpty(landState.getWestTo()) ? "" : String.format("西至:%s、", landState.getWestTo()));
                            stringBuilder.append(StringUtils.isEmpty(landState.getNorthTo()) ? "" : String.format("北至:%s、", landState.getNorthTo()));
                            stringBuilder.append(landState.getShapeState() == null ? "" : String.format("土地形状:%s、", landState.getShapeState()));
                            stringBuilder.append(StringUtils.isEmpty(landState.getLandArea()) ? "" : String.format("土地面积:%s、", landState.getLandArea()));
                            stringBuilder.append(landState.getPlaneness() == null ? "" : String.format("地形:%s、", baseDataDicService.getNameById(landState.getPlaneness())));
                            stringBuilder.append(landState.getTopographicTerrain() == null ? "" : String.format("地势:%s、", baseDataDicService.getNameById(landState.getTopographicTerrain())));
                            stringBuilder.append(landState.getDevelopmentDegree() == null ? "" : String.format("土地开发程度:%s、", baseDataDicService.getNameById(landState.getDevelopmentDegree())));

                            String restrictiveCondition = String.format("%s%s%s%s",
                                    StringUtils.isBlank(landState.getPlotRatio()) ? "" : String.format("容积率:%s、", landState.getPlotRatio()),
                                    StringUtils.isBlank(landState.getBuildingDensity()) ? "" : String.format("建筑密度:%s、", landState.getBuildingDensity()),
                                    StringUtils.isBlank(landState.getGreenSpaceRate()) ? "" : String.format("绿地率:%s、", landState.getGreenSpaceRate()),
                                    StringUtils.isBlank(landState.getCompatibleRatio()) ? "" : String.format("兼容比例:%s、", landState.getCompatibleRatio()));
                            stringBuilder.append(StringUtils.isEmpty(restrictiveCondition) ? "" : String.format("开发限制条件:(%s)、", restrictiveCondition));

                            String soil = String.format("%s%s%s",
                                    StringUtils.isBlank(landState.getBearingCapacity()) ? "" : String.format("承载力:%s、", landState.getBearingCapacity()),
                                    StringUtils.isBlank(landState.getContaminated()) ? "" : String.format("污染:%s、", landState.getContaminated()),
                                    StringUtils.isBlank(landState.getPh()) ? "" : String.format("酸碱度:%s、", landState.getPh()));
                            stringBuilder.append(StringUtils.isEmpty(soil) ? "" : String.format("土壤:(%s)、", soil));
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LAND_ENTITY_STATUS, stringBuilder.toString()));
                            break;
                        case BUILDING_SCALE://建筑规模

                            break;
                        case BUILDING_AREA://建筑面积（㎡）
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.BUILDING_AREA, String.valueOf(examineEstate.getFloorArea() == null ? "" : examineEstate.getFloorArea())));
                            break;
                        case FLOOR_COUNT://层数
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR_COUNT, String.valueOf(examineBuilding.getFloorCount() == null ? "" : examineBuilding.getFloorCount())));
                            break;
                        case FLOOR_HEIGHT://层高
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR_HEIGHT, String.valueOf(examineBuilding.getFloorHeight() == null ? "" : examineBuilding.getFloorHeight())));
                            break;
                        case NET_HEIGHT://净高
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NET_HEIGHT, String.valueOf(examineBuilding.getNetHeight() == null ? "" : examineBuilding.getNetHeight())));
                            break;
                        case BUILDING_HEIGHT://建筑高度
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.BUILDING_HEIGHT, String.valueOf(examineBuilding.getBuildingHeight() == null ? "" : examineBuilding.getBuildingHeight())));
                            break;
                        case BUILDING_STRUCTURE://建筑结构
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.BUILDING_STRUCTURE, baseDataDicService.getNameById(examineBuilding.getBuildingStructureCategory())));
                            break;
                        case ARCHITECTURAL_OUTFIT://建筑外装
                            List<BasicBuildingOutfit> outfitList = basicBuildingOutfitService.getBasicBuildingOutfitVos(examineBuilding.getId());
                            if (CollectionUtils.isNotEmpty(outfitList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicBuildingOutfit outfit : outfitList) {
                                    stringBuilder.append(StringUtils.isBlank(outfit.getDecorationPart()) ? "" : String.format("装修部位:%s、", outfit.getDecorationPart()));
                                    stringBuilder.append(outfit.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(outfit.getDecoratingMaterial())));
                                    stringBuilder.append(outfit.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(outfit.getConstructionTechnology())));
                                    stringBuilder.append(outfit.getMaterialPrice() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(outfit.getMaterialPrice())));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ARCHITECTURAL_OUTFIT, stringBuilder.toString()));
                            }
                            break;
                        case AERATION://通风
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.isEmpty(room.getAeration()) ? "" : String.format("%s:%s、", baseDataDicService.getNameById(room.getRoomType()), room.getAeration()));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.AERATION, stringBuilder.toString()));
                            }
                            break;
                        case LIGHTING://采光
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.isEmpty(room.getLighting()) ? "" : String.format("%s:%s、", baseDataDicService.getNameById(room.getRoomType()), room.getLighting()));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LIGHTING, stringBuilder.toString()));
                            }
                            break;
                        case SUNSHINE://日照
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.isEmpty(room.getSunshine()) ? "" : String.format("%s:%s、", baseDataDicService.getNameById(room.getRoomType()), room.getSunshine()));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SUNSHINE, stringBuilder.toString()));
                            }
                            break;
                        case SOUND_INSULATION://隔音
                            if (CollectionUtils.isNotEmpty(roomList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseRoom room : roomList) {
                                    stringBuilder.append(StringUtils.isEmpty(room.getSoundInsulation()) ? "" : String.format("%s:%s、", baseDataDicService.getNameById(room.getRoomType()), room.getSoundInsulation()));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SOUND_INSULATION, stringBuilder.toString()));
                            }
                            break;
                        case HEAT_PRESERVATION://保温
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic heatPreservationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_PRESERVATION);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, heatPreservationDic);
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.HEAT_PRESERVATION, stringBuilder.toString()));
                            }
                            break;
                        case HEAT_INSULATION://隔热
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, heatInsulationDic);
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.HEAT_INSULATION, stringBuilder.toString()));
                            }
                            break;
                        case WATERPROOF://防水
                            if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                                stringBuilder = new StringBuilder();
                                BaseDataDic waterproofDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_WATERPROOF);
                                getCommonBuildingFunction(buildingFunctions, stringBuilder, waterproofDic);
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.WATERPROOF, stringBuilder.toString()));
                            }
                            break;
                        case INTELLIGENT_LEVEL://设施设备及智能化程度(空调与新风情况)
                            if (CollectionUtils.isNotEmpty(equipmentList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                    if (StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()) ||
                                            StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                                        stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                        stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("、");
                                    }
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.INTELLIGENT_LEVEL, stringBuilder.toString()));
                            }
                            break;
                        case WATER_SUPPLY_DRAINAGE_MODE://供（排）水方式
                            stringBuilder = new StringBuilder();
                            List<BasicHouseWater> waterList = basicHouseWaterService.getBasicHouseWaterList(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(waterList)) {
                                for (BasicHouseWater houseWater : waterList) {
                                    stringBuilder.append(houseWater.getSupplyMode() == null ? "" : String.format("给水方式:%s、", baseDataDicService.getNameById(houseWater.getSupplyMode())));
                                    stringBuilder.append(houseWater.getPipingLayout() == null ? "" : String.format("给水管道布置:%s、", baseDataDicService.getNameById(houseWater.getPipingLayout())));
                                    stringBuilder.append(houseWater.getPipeMaterial() == null ? "" : String.format("给水管材料:%s、", baseDataDicService.getNameById(houseWater.getPipeMaterial())));
                                    stringBuilder.append(houseWater.getBoosterEquipment() == null ? "" : String.format("给水升压设备:%s、", baseDataDicService.getNameById(houseWater.getBoosterEquipment())));
                                    stringBuilder.append(houseWater.getPretreatedWater() == null ? "" : String.format("前置净水:%s、", baseDataDicService.getNameById(houseWater.getPretreatedWater())));
                                    stringBuilder.append(houseWater.getPurificationEquipmentPrice() == null ? "" : String.format("净水设备价格:%s、", baseDataDicService.getNameById(houseWater.getPurificationEquipmentPrice())));
                                    stringBuilder.append(houseWater.getFireWaterSupply() == null ? "" : String.format("消防给水:%s、", baseDataDicService.getNameById(houseWater.getFireWaterSupply())));
                                }
                            }
                            List<BasicHouseWaterDrain> drainList = basicHouseWaterDrainService.getBasicHouseWaterDrainList(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(drainList)) {
                                for (BasicHouseWaterDrain houseWaterDrain : drainList) {
                                    stringBuilder.append(houseWaterDrain.getDrainSystem() == null ? "" : String.format("排水系统:%s、", baseDataDicService.getNameById(houseWaterDrain.getDrainSystem())));
                                    stringBuilder.append(houseWaterDrain.getType() == null ? "" : String.format("类别:%s、", baseDataDicService.getNameById(houseWaterDrain.getType())));
                                    stringBuilder.append(houseWaterDrain.getProcessingMode() == null ? "" : String.format("排水处理方式:%s、", baseDataDicService.getNameById(houseWaterDrain.getProcessingMode())));
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.WATER_SUPPLY_DRAINAGE_MODE, stringBuilder.toString()));
                            break;
                        case HEATING_MODE://采暖供热方式
                            if (CollectionUtils.isNotEmpty(equipmentList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                    if (StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseHeating.getKey())) {
                                        stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                        stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("、");
                                    }
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.HEATING_MODE, stringBuilder.toString()));
                            }
                            break;
                        case NETWORK://房间电力通信网络
                            List<BasicHouseIntelligentVo> intelligentList = basicHouseIntelligentService.getBasicHouseIntelligentVos(examineHouse.getId());
                            if (CollectionUtils.isNotEmpty(intelligentList)) {
                                stringBuilder = new StringBuilder();
                                for (BasicHouseIntelligentVo intelligent : intelligentList) {
                                    stringBuilder.append(StringUtils.isBlank(intelligent.getSwitchCircuitName()) ? "" : String.format("开关回路:%s、", intelligent.getSwitchCircuitName()));
                                    stringBuilder.append(StringUtils.isBlank(intelligent.getLayingMethodName()) ? "" : String.format("铺设方式:%s、", intelligent.getLayingMethodName()));
                                    stringBuilder.append(StringUtils.isBlank(intelligent.getLampsLanternsName()) ? "" : String.format("灯具:%s、", intelligent.getLampsLanternsName()));
                                    stringBuilder.append(StringUtils.isBlank(intelligent.getIntelligentSystemName()) ? "" : String.format("智能系统:%s、", intelligent.getIntelligentSystemName()));
                                }
                                list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NETWORK, stringBuilder.toString()));
                            }
                            break;
                        case ELEVATOR_HOUSEHOLD_RATIO://电梯梯户比
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ELEVATOR_HOUSEHOLD_RATIO, examineUnit.getElevatorHouseholdRatio()));
                            break;
                        case INTERNAL_ASSEMBLY://内装
                            stringBuilder = new StringBuilder();
                            List<BasicUnitDecorate> decorateList = basicUnitDecorateService.getBasicUnitDecorateList(examineUnit.getId());
                            if (CollectionUtils.isNotEmpty(decorateList)) {
                                for (BasicUnitDecorate unitDecorate : decorateList) {
                                    stringBuilder.append(StringUtils.isBlank(unitDecorate.getDecorationPart()) ? "" : String.format("装修部位:%s、", unitDecorate.getDecorationPart()));
                                    stringBuilder.append(unitDecorate.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(unitDecorate.getDecoratingMaterial())));
                                    stringBuilder.append(unitDecorate.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(unitDecorate.getConstructionTechnology())));
                                    stringBuilder.append(unitDecorate.getMaterialPriceRange() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(unitDecorate.getMaterialPriceRange())));
                                }
                            }
                            if (CollectionUtils.isNotEmpty(roomList)) {//房间内装
                                for (BasicHouseRoom room : roomList) {
                                    List<BasicHouseRoomDecorate> roomDecorateList = basicHouseRoomDecorateService.getHouseRoomDecorateList(room.getId());
                                    if (CollectionUtils.isNotEmpty(roomDecorateList)) {
                                        StringBuilder decorate = new StringBuilder();
                                        for (BasicHouseRoomDecorate roomDecorate : roomDecorateList) {
                                            decorate.append(roomDecorate.getPart() == null ? "" : String.format("装修部位:%s、", baseDataDicService.getNameById(roomDecorate.getPart())));
                                            decorate.append(roomDecorate.getMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(roomDecorate.getMaterial())));
                                            decorate.append(roomDecorate.getMaterialPrice() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(Integer.valueOf(roomDecorate.getMaterialPrice()))));
                                            decorate.append(roomDecorate.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(Integer.valueOf(roomDecorate.getConstructionTechnology()))));
                                        }
                                        if (decorate.length() > 0) {
                                            stringBuilder.append(String.format("%s(%s)、", baseDataDicService.getNameById(room.getRoomType()), declareId));
                                        }
                                    }
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.INTERNAL_ASSEMBLY, stringBuilder.toString()));
                            break;
                        case PLANE_LAYOUT://平面布局

                            break;
                        case NEW_DEGREE://成新度

                            break;
                        case MAINTENANCE_LOSS_STATUS://维护保养和完损状况

                            break;
                        case OTHER://其它

                            break;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new BusinessException(String.format("比较法字段：%s", compareFieldEnum.getName()));
                }
            }
            return JSON.toJSONString(list);
        } catch (Exception e) {
            log.error("市场比较法生成比较数据异常", e);
            return "";
        }
    }

    private void getCommonSupply(StringBuilder stringBuilder, BasicEstateSupply supply) {
        stringBuilder.append(StringUtils.isEmpty(supply.getName()) ? "" : String.format("供应商名称:%s、", supply.getName()));
        stringBuilder.append(supply.getLineGrade() == null ? "" : String.format("供应保障等级:%s、", baseDataDicService.getNameById(supply.getLineGrade())));
        stringBuilder.append(supply.getReputation() == null ? "" : String.format("供应商信誉:%s、", baseDataDicService.getNameById(supply.getReputation())));
        stringBuilder.append(supply.getGrade() == null ? "" : String.format("供应商等级:%s、", baseDataDicService.getNameById(supply.getGrade())));
        stringBuilder.append(StringUtils.isEmpty(supply.getPower()) ? "" : String.format("供应量或功率:%s、", supply.getPower()));
    }

    private void getCommonBuildingFunction(List<BasicBuildingFunction> buildingFunctions, StringBuilder stringBuilder, BaseDataDic heatPreservationDic) {
        for (BasicBuildingFunction buildingFunction : buildingFunctions) {
            if (buildingFunction.getType() != null && buildingFunction.getType().equals(heatPreservationDic.getId())) {
                stringBuilder.append(buildingFunction.getDecorationPart() == null ? "" : String.format("装修部位:%s、", baseDataDicService.getNameById(buildingFunction.getDecorationPart())));
                stringBuilder.append(buildingFunction.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(buildingFunction.getDecoratingMaterial())));
                stringBuilder.append(buildingFunction.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(buildingFunction.getConstructionTechnology())));
                stringBuilder.append(buildingFunction.getMaterialPrice() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(buildingFunction.getMaterialPrice())));
            }
        }
    }

    private void getEnvironmentString(StringBuilder stringBuilder, BaseDataDic sceneryDic, BasicMatchingEnvironment examineMatchingEnvironment) {
        if (StringUtils.equals(String.valueOf(sceneryDic.getId()), examineMatchingEnvironment.getType())) {
            stringBuilder.append(baseDataDicService.getNameById(examineMatchingEnvironment.getCategory()));
            stringBuilder.append(baseDataDicService.getNameById(examineMatchingEnvironment.getInfluenceDegree()));
            stringBuilder.append("、");
        }
    }

    private MarketCompareItemDto getMarketCompareItemDto(String name, String value) {
        MarketCompareItemDto marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName(name);
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        marketCompareItemDto.setValue(StringUtils.isBlank(value) ? "无" : StringUtils.strip(value, "、"));
        return marketCompareItemDto;
    }


}
