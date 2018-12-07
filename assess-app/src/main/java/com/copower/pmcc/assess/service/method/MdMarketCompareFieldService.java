package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ExamineEstateSupplyEnumType;
import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessMarketCompareConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataPropertyDao;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.*;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingEnvironmentService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingTrafficService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.utils.LangUtils;
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
public class MdMarketCompareFieldService {
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private ExamineEstateDao examineEstateDao;
    @Autowired
    private ExamineHouseTradingDao examineHouseTradingDao;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ExamineMatchingTrafficService examineMatchingTrafficService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ExamineHouseDao examineHouseDao;
    @Autowired
    private ExamineMatchingEnvironmentService examineMatchingEnvironmentService;
    @Autowired
    private ExamineBuildingDao examineBuildingDao;
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
    private ExamineEstateLandStateDao examineEstateLandStateDao;
    @Autowired
    private ExamineBuildingOutfitDao examineBuildingOutfitDao;
    @Autowired
    private ExamineHouseRoomDao examineHouseRoomDao;
    @Autowired
    private ExamineBuildingFunctionDao examineBuildingFunctionDao;
    @Autowired
    private ExamineEstateSupplyDao examineEstateSupplyDao;
    @Autowired
    private ExamineEstateNetworkDao examineEstateNetworkDao;
    @Autowired
    private ExamineUnitDao examineUnitDao;
    @Autowired
    private ExamineUnitDecorateDao examineUnitDecorateDao;
    @Autowired
    private ExamineHouseRoomDecorateDao examineHouseRoomDecorateDao;
    @Autowired
    private ExamineHouseEquipmentDao examineHouseEquipmentDao;
    @Autowired
    private ExamineHouseIntelligentDao examineHouseIntelligentDao;
    @Autowired
    private ExamineHouseWaterDao examineHouseWaterDao;
    @Autowired
    private ExamineMatchingEducationDao examineMatchingEducationDao;
    @Autowired
    private ExamineMatchingLeisurePlaceDao examineMatchingLeisurePlaceDao;
    @Autowired
    private ExamineMatchingMedicalDao examineMatchingMedicalDao;
    @Autowired
    private ExamineMatchingFinanceDao examineMatchingFinanceDao;

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
            //1.取得楼盘信息
            ExamineEstate examineEstate = examineEstateDao.getEstateByPlanDetailsId(planDetailsId);
            examineEstate = examineEstate == null ? new ExamineEstate() : examineEstate;
            //2.取得交易信息
            ExamineHouseTrading houseTrading = examineHouseTradingDao.getHouseTradingByPlanDetailsId(planDetailsId);
            houseTrading = houseTrading == null ? new ExamineHouseTrading() : houseTrading;
            //3.取得申报记录信息
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
            declareRecord = declareRecord == null ? new DeclareRecord() : declareRecord;
            //4.取得版块信息
            DataBlock block = dataBlockService.getDataBlockById(examineEstate.getBlockId());
            block = block == null ? new DataBlock() : block;
            //5.取得房屋信息
            ExamineHouse examineHouse = examineHouseDao.getHouseByPlanDetailsId(planDetailsId);
            examineHouse = examineHouse == null ? new ExamineHouse() : examineHouse;
            //6.取得交通信息
            ExamineMatchingTraffic whereTraffic = new ExamineMatchingTraffic();
            whereTraffic.setPlanDetailsId(planDetailsId);
            List<ExamineMatchingTraffic> trafficList = examineMatchingTrafficService.getMatchingTrafficList(whereTraffic);
            //7.取得环境相关信息
            ExamineMatchingEnvironment whereEnvironment = new ExamineMatchingEnvironment();
            whereEnvironment.setPlanDetailsId(planDetailsId);
            List<ExamineMatchingEnvironment> environmentList = examineMatchingEnvironmentService.getExamineMatchingEnvironmentList(whereEnvironment);
            //8.取得楼栋信息
            List<ExamineBuilding> buildingList = examineBuildingDao.getByPlanDetailsId(planDetailsId);
            ExamineBuilding examineBuilding = CollectionUtils.isEmpty(buildingList) ? new ExamineBuilding() : buildingList.get(0);
            //9.取得他项权利
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, projectInfo.getProjectCategoryId());
            List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            ProjectPlanDetails inventoryPlanDetails = planDetailsList.get(0);
            List<SurveyAssetInventoryRight> inventoryRights = surveyAssetInventoryRightDao.getListByPlanDetailsId(inventoryPlanDetails.getId());
            //10.取得土地实体情况
            ExamineEstateLandState landState = examineEstateLandStateDao.getEstateLandStateByPlanDetailsId(planDetailsId);
            landState = landState == null ? new ExamineEstateLandState() : landState;
            //11.取得房间信息
            List<ExamineHouseRoom> roomList = examineHouseRoomDao.getHouseRoomList(planDetailsId);
            //12.取得建筑功能
            List<ExamineBuildingFunction> buildingFunctions = examineBuildingFunctionDao.getExamineBuildingFunctionList(planDetailsId);
            //13.取得楼盘供应信息（供水、供电、供热、供气）
            List<ExamineEstateSupply> estateSupplyList = examineEstateSupplyDao.getExamineEstateSupplyList(planDetailsId);
            //14.取得房间供应信息(供暖、空调、新风)
            List<ExamineHouseEquipment> equipmentList = examineHouseEquipmentDao.getHouseEquipmentList(planDetailsId);
            //15.取得房间电力通信网络
            List<ExamineHouseIntelligent> intelligentList = examineHouseIntelligentDao.getHouseIntelligentList(planDetailsId);

            StringBuilder stringBuilder = null;
            BaseDataDic baseDataDic = null;
            List<MarketCompareItemDto> list = Lists.newArrayList();
            for (DataSetUseField dataSetUseField : setUseFieldList) {
                if (StringUtils.isBlank(dataSetUseField.getFieldName())) continue;
                switch (dataSetUseField.getFieldName()) {
                    case AssessMarketCompareConstant.ESTATE_NAME://楼盘名称
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ESTATE_NAME, examineEstate.getName()));
                        break;
                    case AssessMarketCompareConstant.SCOPE_PROPERTY://财产范围
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SCOPE_PROPERTY, houseTrading.getScopeProperty()));
                        break;
                    case AssessMarketCompareConstant.FINANCING_CONDITIONS://融资条件
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FINANCING_CONDITIONS, houseTrading.getFinancingConditions()));
                        break;
                    case AssessMarketCompareConstant.TAX_BURDEN://税费负担
                        if (StringUtils.isNotEmpty(houseTrading.getTaxBurden()))
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TAX_BURDEN, baseDataDicService.getNameById(Integer.valueOf(houseTrading.getTaxBurden()))));
                        break;
                    case AssessMarketCompareConstant.PAYMENT_METHOD://付款方式
                        if (StringUtils.isNotEmpty(houseTrading.getPaymentMethod()))
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PAYMENT_METHOD, baseDataDicService.getNameById(Integer.valueOf(houseTrading.getPaymentMethod()))));
                        break;
                    case AssessMarketCompareConstant.TRADING_PRICE://交易价格
                        if (houseTrading.getTradingPrice() != null)
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TRADING_PRICE, String.valueOf(houseTrading.getTradingPrice())));
                        break;
                    case AssessMarketCompareConstant.LOCATION://房地产坐落及方位
                        stringBuilder = new StringBuilder();
                        if (StringUtils.isNotBlank(declareRecord.getSeat()))
                            stringBuilder.append(declareRecord.getSeat()).append("、");

                        if (StringUtils.isNotBlank(block.getPosition()))
                            stringBuilder.append(block.getPosition()).append("、");

                        if (StringUtils.isNotBlank(examineEstate.getPosition()))
                            stringBuilder.append(examineEstate.getPosition()).append("、");
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LOCATION, stringBuilder.toString()));
                        break;
                    case AssessMarketCompareConstant.OFFICE_CONCENTRATION://办公集聚度
                        //取得交通枢纽信息
                        if (CollectionUtils.isNotEmpty(trafficList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineMatchingTraffic examineMatchingTraffic : trafficList) {
                                if (StringUtils.equals(examineMatchingTraffic.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())) {
                                    stringBuilder.append(examineMatchingTraffic.getName());
                                    stringBuilder.append(baseDataDicService.getNameById(examineMatchingTraffic.getDistance()));
                                    stringBuilder.append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.OFFICE_CONCENTRATION, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.FLOOR://楼层
                        if (examineHouse.getFloor() != null)
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR, String.valueOf(examineHouse.getFloor())));
                        break;
                    case AssessMarketCompareConstant.ORIENTATION://朝向
                        if (StringUtils.isNotBlank(examineHouse.getOrientation()))
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ORIENTATION, examineHouse.getOrientation()));
                        break;
                    case AssessMarketCompareConstant.TRAFFIC_CONDITIONS://交通条件
                        if (CollectionUtils.isNotEmpty(trafficList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineMatchingTraffic examineMatchingTraffic : trafficList) {
                                if (!StringUtils.equals(examineMatchingTraffic.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())) {
                                    stringBuilder.append(examineMatchingTraffic.getName());
                                    stringBuilder.append(baseDataDicService.getNameById(examineMatchingTraffic.getDistance()));
                                    stringBuilder.append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TRAFFIC_CONDITIONS, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.URBAN_INFRASTRUCTURE://城市基础设施
                        stringBuilder = new StringBuilder();
                        //楼盘下供电
                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                            for (ExamineEstateSupply supply : estateSupplyList) {
                                if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATESUPPLYPOWER.getName())) {
                                    getCommonSupply(stringBuilder, supply);
                                }
                            }
                        }

                        //楼盘下供排水
                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                            for (ExamineEstateSupply supply : estateSupplyList) {
                                if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATESUPPLYWATER.getName())) {
                                    getCommonSupply(stringBuilder, supply);
                                }
                            }
                        }

                        //楼盘下采暖供热
                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                            for (ExamineEstateSupply supply : estateSupplyList) {
                                if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATESUPPLYHEATING.getName())) {
                                    getCommonSupply(stringBuilder, supply);
                                }
                            }
                        }

                        //楼盘下供气
                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                            for (ExamineEstateSupply supply : estateSupplyList) {
                                if (StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATESUPPLYGAS.getName())) {
                                    getCommonSupply(stringBuilder, supply);
                                }
                            }
                        }

                        //楼盘下通信网络
                        List<ExamineEstateNetwork> networkList = examineEstateNetworkDao.getEstateNetworkList(planDetailsId);
                        if (CollectionUtils.isNotEmpty(networkList)) {
                            for (ExamineEstateNetwork network : networkList) {
                                stringBuilder.append(StringUtils.isEmpty(network.getName()) ? "" : String.format("名称:%s、", network.getName()));
                                stringBuilder.append(StringUtils.isEmpty(network.getServiceContent()) ? "" : String.format("服务内容:%s、", network.getServiceContent()));
                                stringBuilder.append(StringUtils.isEmpty(network.getIndexParameter()) ? "" : String.format("指标参数:%s、", network.getIndexParameter()));
                            }
                        }
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.URBAN_INFRASTRUCTURE, stringBuilder.toString()));
                        break;
                    case AssessMarketCompareConstant.PUBLIC_SERVICE_FACILITIES://公共服务设施
                        stringBuilder = new StringBuilder();
                        List<ExamineMatchingEducation> educationList = examineMatchingEducationDao.getMatchingEducationList(planDetailsId);//教育
                        if (CollectionUtils.isNotEmpty(educationList)) {
                            for (ExamineMatchingEducation education : educationList) {
                                stringBuilder.append(StringUtils.isEmpty(education.getSchoolName()) ? "" : String.format("学校名称:%s、", education.getSchoolName()));
                                stringBuilder.append(education.getSchoolNature() == null ? "" : String.format("学校性质:%s、", baseDataDicService.getNameById(education.getSchoolNature())));
                                stringBuilder.append(education.getSchoolGradation() == null ? "" : String.format("学校级次:%s、", baseDataDicService.getNameById(education.getSchoolGradation())));
                                stringBuilder.append(StringUtils.isBlank(education.getSchoolLevel()) ? "" : String.format("学校等级:%s、", baseDataDicService.getNameById(Integer.valueOf(education.getSchoolLevel()))));
                                stringBuilder.append(education.getDistance() == null ? "" : String.format("距离:%s、", baseDataDicService.getNameById(education.getDistance())));
                            }
                        }
                        List<ExamineMatchingLeisurePlace> leisurePlaceList = examineMatchingLeisurePlaceDao.getMatchingLeisurePlaceList(planDetailsId);//休闲娱乐
                        if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                            for (ExamineMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                stringBuilder.append(StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s、", leisurePlace.getName()));
                                stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s、", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s、", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s、", baseDataDicService.getNameById(leisurePlace.getDistance())));
                            }
                        }
                        List<ExamineMatchingMedical> medicalList = examineMatchingMedicalDao.getExamineMatchingMedicalList(planDetailsId);//医养
                        if (CollectionUtils.isNotEmpty(medicalList)) {
                            for (ExamineMatchingMedical medical : medicalList) {
                                stringBuilder.append(StringUtils.isEmpty(medical.getOrganizationName()) ? "" : String.format("机构名称:%s、", medical.getOrganizationName()));
                                stringBuilder.append(StringUtils.isEmpty(medical.getBedNumber()) ? "" : String.format("床位数:%s、", medical.getBedNumber()));
                                stringBuilder.append(StringUtils.isBlank(medical.getOrganizationLevel()) ? "" : String.format("机构等级:%s、", baseDataDicService.getNameById(Integer.valueOf(medical.getOrganizationLevel()))));
                                stringBuilder.append(medical.getDistance() == null ? "" : String.format("距离:%s、", baseDataDicService.getNameById(medical.getDistance())));
                            }
                        }
                        List<ExamineMatchingFinance> financeList = examineMatchingFinanceDao.getMatchingFinanceList(planDetailsId);//金融
                        if (CollectionUtils.isNotEmpty(financeList)) {
                            for (ExamineMatchingFinance finance : financeList) {
                                stringBuilder.append(StringUtils.isEmpty(finance.getName()) ? "" : String.format("名称:%s、", finance.getName()));
                                stringBuilder.append(finance.getCategory() == null ? "" : String.format("类别:%s、", baseDataDicService.getNameById(finance.getCategory())));
                                stringBuilder.append(finance.getNature() == null ? "" : String.format("金融机构性质:%s、", baseDataDicService.getNameById(finance.getNature())));
                                stringBuilder.append(StringUtils.isEmpty(finance.getName()) ? "" : String.format("服务内容:%s、", finance.getName()));
                            }
                        }
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PUBLIC_SERVICE_FACILITIES, stringBuilder.toString()));
                        break;
                    case AssessMarketCompareConstant.NATURAL://自然环境
                        if (CollectionUtils.isNotEmpty(environmentList)) {
                            stringBuilder = new StringBuilder();
                            BaseDataDic naturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_NATURAL);
                            for (ExamineMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                getEnvironmentString(stringBuilder, naturalDic, examineMatchingEnvironment);
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NATURAL, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.CULTURAL://人文环境
                        if (CollectionUtils.isNotEmpty(environmentList)) {
                            stringBuilder = new StringBuilder();
                            BaseDataDic culturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_CULTURAL);
                            for (ExamineMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                getEnvironmentString(stringBuilder, culturalDic, examineMatchingEnvironment);
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.CULTURAL, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.SCENERY://景观
                        if (CollectionUtils.isNotEmpty(environmentList)) {
                            stringBuilder = new StringBuilder();
                            BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                            for (ExamineMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SCENERY, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.FEEINTEREST_TYPE://土地权益类型

                        break;
                    case AssessMarketCompareConstant.LAND_CONTROL_SITUATION://土地管制情况
                        stringBuilder = new StringBuilder();
                        if (examineHouse.getCertUse() != null) {//证载用途
                            baseDataDic = baseDataDicService.getDataDicById(examineHouse.getCertUse());
                            if (baseDataDic != null)
                                stringBuilder.append(String.format("证载用途:%s、", baseDataDic.getName()));
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
                    case AssessMarketCompareConstant.LAND_RIGHTS://土地他项权利
                        if (CollectionUtils.isNotEmpty(inventoryRights)) {
                            stringBuilder = new StringBuilder();
                            for (SurveyAssetInventoryRight inventoryRight : inventoryRights) {
                                stringBuilder.append(String.format("%s、", baseDataDicService.getNameById(inventoryRight.getCategory())));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LAND_RIGHTS, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.HOUSING_OWNERSHIP://房屋所有权

                        break;
                    case AssessMarketCompareConstant.LEASEHOLD://租赁情况
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
                    case AssessMarketCompareConstant.PROPERTY_MANAGEMENT://物业管理情况
                        if (examineBuilding.getPropertyId() != null) {
                            stringBuilder = new StringBuilder();
                            DataProperty property = dataPropertyDao.getByDataPropertyId(examineBuilding.getPropertyId());
                            if (property != null) {
                                stringBuilder.append(String.format("物业公司名称:%s、", property.getName()));
                                stringBuilder.append(String.format("公司信誉:%s、", property.getCompanyNature()));
                            }
                            stringBuilder.append(String.format("物业费:%s、", examineBuilding.getPropertyFee()));
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PROPERTY_MANAGEMENT, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.OTHER_SPECIAL_SITUATIONS://其它特殊情况
                        SurveyAssetInventory assetInventory = surveyAssetInventoryDao.getDataByPlanDetailsId(inventoryPlanDetails.getId());
                        if (assetInventory != null) {
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.OTHER_SPECIAL_SITUATIONS, assetInventory.getSpecialCase()));
                        }
                        break;
                    case AssessMarketCompareConstant.LAND_ENTITY_STATUS://土地实体状况
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(StringUtils.isEmpty(landState.getName()) ? "" : String.format("土地名称:%s、", landState.getName()));
                        stringBuilder.append(landState.getLandUseType() == null ? "" : String.format("土地用途类型:%s、", baseDataDicService.getNameById(landState.getLandUseType())));
                        stringBuilder.append(landState.getLandUseCategory() == null ? "" : String.format("土地用途类别:%s、", baseDataDicService.getNameById(landState.getLandUseCategory())));
                        stringBuilder.append(landState.getLandLevel() == null ? "" : String.format("土地级别:%s、", baseDataDicService.getNameById(landState.getLandLevel())));
                        stringBuilder.append(StringUtils.isEmpty(landState.getEastTo()) ? "" : String.format("东至:%s、", landState.getEastTo()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getSouthTo()) ? "" : String.format("南至:%s、", landState.getSouthTo()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getWestTo()) ? "" : String.format("西至:%s、", landState.getWestTo()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getNorthTo()) ? "" : String.format("北至:%s、", landState.getNorthTo()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getShapeState()) ? "" : String.format("土地形状:%s、", landState.getShapeState()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getPlaneness()) ? "" : String.format("地形:%s、", landState.getPlaneness()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getDevelopmentDegree()) ? "" : String.format("土地开发程度:%s、", landState.getDevelopmentDegree()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getRestrictiveCondition()) ? "" : String.format("开发限制条件:%s、", landState.getRestrictiveCondition()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getSoil()) ? "" : String.format("土壤:%s、", landState.getSoil()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getTopographicTerrain()) ? "" : String.format("地势:%s、", landState.getTopographicTerrain()));
                        stringBuilder.append(StringUtils.isEmpty(landState.getLandArea()) ? "" : String.format("土地面积:%s、", landState.getLandArea()));
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LAND_ENTITY_STATUS, stringBuilder.toString()));
                        break;
                    case AssessMarketCompareConstant.BUILDING_SCALE://建筑规模

                        break;
                    case AssessMarketCompareConstant.BUILDING_AREA://建筑面积（㎡）
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.BUILDING_AREA, String.valueOf(examineEstate.getFloorArea() == null ? "" : examineEstate.getFloorArea())));
                        break;
                    case AssessMarketCompareConstant.FLOOR_COUNT://层数
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR_COUNT, String.valueOf(examineBuilding.getFloorCount() == null ? "" : examineBuilding.getFloorCount())));
                        break;
                    case AssessMarketCompareConstant.FLOOR_HEIGHT://层高
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR_HEIGHT, String.valueOf(examineBuilding.getFloorHeight() == null ? "" : examineBuilding.getFloorHeight())));
                        break;
                    case AssessMarketCompareConstant.NET_HEIGHT://净高
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NET_HEIGHT, String.valueOf(examineBuilding.getNetHeight() == null ? "" : examineBuilding.getNetHeight())));
                        break;
                    case AssessMarketCompareConstant.BUILDING_HEIGHT://建筑高度
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.BUILDING_HEIGHT, String.valueOf(examineBuilding.getBuildingHeight() == null ? "" : examineBuilding.getBuildingHeight())));
                        break;
                    case AssessMarketCompareConstant.BUILDING_STRUCTURE://建筑结构
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.BUILDING_STRUCTURE, baseDataDicService.getNameById(examineBuilding.getBuildingStructureCategory())));
                        break;
                    case AssessMarketCompareConstant.ARCHITECTURAL_OUTFIT://建筑外装
                        List<ExamineBuildingOutfit> outfitList = examineBuildingOutfitDao.getBuildingOutfitList(planDetailsId);
                        if (CollectionUtils.isNotEmpty(outfitList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineBuildingOutfit outfit : outfitList) {
                                stringBuilder.append(outfit.getDecorationPart() == null ? "" : String.format("装修部位:%s、", baseDataDicService.getNameById(outfit.getDecorationPart())));
                                stringBuilder.append(outfit.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(outfit.getDecoratingMaterial())));
                                stringBuilder.append(outfit.getMaterialPrice() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(outfit.getMaterialPrice())));
                                stringBuilder.append(outfit.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(outfit.getConstructionTechnology())));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ARCHITECTURAL_OUTFIT, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.AERATION://通风
                        if (CollectionUtils.isNotEmpty(roomList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseRoom room : roomList) {
                                stringBuilder.append(StringUtils.isEmpty(room.getAeration()) ? "" : String.format("%s:%s、", room.getName(), room.getAeration()));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.AERATION, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.LIGHTING://采光
                        if (CollectionUtils.isNotEmpty(roomList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseRoom room : roomList) {
                                stringBuilder.append(StringUtils.isEmpty(room.getLighting()) ? "" : String.format("%s:%s、", room.getName(), room.getLighting()));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.LIGHTING, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.SUNSHINE://日照
                        if (CollectionUtils.isNotEmpty(roomList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseRoom room : roomList) {
                                stringBuilder.append(StringUtils.isEmpty(room.getSunshine()) ? "" : String.format("%s:%s、", room.getName(), room.getSunshine()));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SUNSHINE, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.SOUND_INSULATION://隔音
                        if (CollectionUtils.isNotEmpty(roomList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseRoom room : roomList) {
                                stringBuilder.append(StringUtils.isEmpty(room.getSoundInsulation()) ? "" : String.format("%s:%s、", room.getName(), room.getSoundInsulation()));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.SOUND_INSULATION, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.HEAT_PRESERVATION://保温
                        if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                            stringBuilder = new StringBuilder();
                            BaseDataDic heatPreservationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_PRESERVATION);
                            getCommonBuildingFunction(buildingFunctions, stringBuilder, heatPreservationDic);
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.HEAT_PRESERVATION, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.HEAT_INSULATION://隔热
                        if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                            stringBuilder = new StringBuilder();
                            BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                            getCommonBuildingFunction(buildingFunctions, stringBuilder, heatInsulationDic);
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.HEAT_INSULATION, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.WATERPROOF://防水
                        if (CollectionUtils.isNotEmpty(buildingFunctions)) {
                            stringBuilder = new StringBuilder();
                            BaseDataDic waterproofDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_WATERPROOF);
                            getCommonBuildingFunction(buildingFunctions, stringBuilder, waterproofDic);
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.WATERPROOF, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.INTELLIGENT_LEVEL://设施设备及智能化程度(新风情况)
                        if (CollectionUtils.isNotEmpty(equipmentList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseEquipment examineHouseEquipment : equipmentList) {
                                if (StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseNewWind.getKey())) {
                                    stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                    stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.INTELLIGENT_LEVEL, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.POWER_SUPPLY_MODE://供电方式
                        if (CollectionUtils.isNotEmpty(intelligentList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseIntelligent intelligent : intelligentList) {
                                stringBuilder.append(intelligent.getWireErection() == null ? "" : String.format("电线架设方式:%s、", baseDataDicService.getNameById(intelligent.getWireErection())));
                                stringBuilder.append(intelligent.getWireMaterial() == null ? "" : String.format("电线材质:%s、", baseDataDicService.getNameById(intelligent.getWireMaterial())));
                                stringBuilder.append(intelligent.getSwitchCircuit() == null ? "" : String.format("开关回路:%s、", baseDataDicService.getNameById(intelligent.getSwitchCircuit())));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.POWER_SUPPLY_MODE, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.WATER_SUPPLY_DRAINAGE_MODE://供（排）水方式
                        List<ExamineHouseWater> waterList = examineHouseWaterDao.getHouseWaterList(planDetailsId);
                        if (CollectionUtils.isNotEmpty(intelligentList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseWater houseWater : waterList) {
                                stringBuilder.append(String.format("取水设备：%s", houseWater.getWaterIntakeEquipment())).append("、");
                                stringBuilder.append(String.format("采水点数：%s", houseWater.getIntakePointNumber())).append("、");
                                stringBuilder.append(String.format("自然区间取水点数：%s", houseWater.getNatrueIntakePointNumber())).append("、");
                                stringBuilder.append(houseWater.getSupplyErectionMethod() == null ? "" : String.format("供水管架设方式:%s、", baseDataDicService.getNameById(houseWater.getSupplyErectionMethod())));
                                stringBuilder.append(houseWater.getDrainageCircuit() == null ? "" : String.format("排水回路:%s、", baseDataDicService.getNameById(houseWater.getDrainageCircuit())));
                                stringBuilder.append(houseWater.getPretreatedWater() == null ? "" : String.format("前置净水:%s、", baseDataDicService.getNameById(houseWater.getPretreatedWater())));
                                stringBuilder.append(houseWater.getPurificationEquipmentPrice() == null ? "" : String.format("前置净水设备价格区间:%s、", baseDataDicService.getNameById(Integer.parseInt(houseWater.getPurificationEquipmentPrice()))));
                                stringBuilder.append(houseWater.getWaterIntakeEquipmentPrice() == null ? "" : String.format("取水设备价格区间:%s、", baseDataDicService.getNameById(Integer.parseInt(houseWater.getWaterIntakeEquipmentPrice()))));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.WATER_SUPPLY_DRAINAGE_MODE, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.HEATING_MODE://采暖供热方式
                        if (CollectionUtils.isNotEmpty(equipmentList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseEquipment examineHouseEquipment : equipmentList) {
                                if (StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseHeating.getKey())) {
                                    stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                    stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("、");
                                }
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.HEATING_MODE, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.NETWORK://通讯网络
                        if (CollectionUtils.isNotEmpty(intelligentList)) {
                            stringBuilder = new StringBuilder();
                            for (ExamineHouseIntelligent intelligent : intelligentList) {
                                stringBuilder.append(intelligent.getInternalCommunication() == null ? "" : String.format("%s、", baseDataDicService.getNameById(intelligent.getInternalCommunication())));
                            }
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NETWORK, stringBuilder.toString()));
                        }
                        break;
                    case AssessMarketCompareConstant.ELEVATOR_HOUSEHOLD_RATIO://电梯梯户比
                        ExamineUnit examineUnit = examineUnitDao.getUnitByPlanDetailsId(planDetailsId);
                        if (examineUnit != null) {
                            list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ELEVATOR_HOUSEHOLD_RATIO, examineUnit.getElevatorHouseholdRatio()));
                        }
                        break;
                    case AssessMarketCompareConstant.INTERNAL_ASSEMBLY://内装
                        stringBuilder = new StringBuilder();
                        List<ExamineUnitDecorate> decorateList = examineUnitDecorateDao.getUnitDecorateList(planDetailsId);//楼栋内装
                        if (CollectionUtils.isNotEmpty(decorateList)) {
                            for (ExamineUnitDecorate unitDecorate : decorateList) {
                                stringBuilder.append(unitDecorate.getDecorationPart() == null ? "" : String.format("装修部位:%s、", baseDataDicService.getNameById(unitDecorate.getDecorationPart())));
                                stringBuilder.append(unitDecorate.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(unitDecorate.getDecoratingMaterial())));
                                stringBuilder.append(unitDecorate.getMaterialPriceRange() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(unitDecorate.getMaterialPriceRange())));
                                stringBuilder.append(unitDecorate.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(unitDecorate.getConstructionTechnology())));
                            }
                        }
                        if (CollectionUtils.isNotEmpty(roomList)) {//房间内装
                            List<ExamineHouseRoomDecorate> roomDecorateList = examineHouseRoomDecorateDao.getHouseRoomDecorateList(LangUtils.transform(roomList, o -> o.getId()));
                            if (CollectionUtils.isNotEmpty(roomDecorateList)) {
                                for (ExamineHouseRoomDecorate roomDecorate : roomDecorateList) {
                                    stringBuilder.append(roomDecorate.getPart() == null ? "" : String.format("装修部位:%s、", baseDataDicService.getNameById(roomDecorate.getPart())));
                                    stringBuilder.append(roomDecorate.getMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(roomDecorate.getMaterial())));
                                    stringBuilder.append(StringUtils.isEmpty(roomDecorate.getMaterialPrice()) ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(Integer.valueOf(roomDecorate.getMaterialPrice()))));
                                    stringBuilder.append(StringUtils.isEmpty(roomDecorate.getConstructionTechnology()) ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(Integer.valueOf(roomDecorate.getConstructionTechnology()))));
                                }
                            }
                        }
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.INTERNAL_ASSEMBLY, stringBuilder.toString()));
                        break;
                    case AssessMarketCompareConstant.PLANE_LAYOUT://平面布局

                        break;
                    case AssessMarketCompareConstant.NEW_DEGREE://成新度

                        break;
                    case AssessMarketCompareConstant.MAINTENANCE_LOSS_STATUS://维护保养和完损状况

                        break;
                    case AssessMarketCompareConstant.OTHER://其它

                        break;
                }
            }
            return JSON.toJSONString(list);
        } catch (Exception e) {
            return "";
        }
    }

    private void getCommonSupply(StringBuilder stringBuilder, ExamineEstateSupply supply) {
        stringBuilder.append(StringUtils.isEmpty(supply.getName()) ? "" : String.format("供应商名称:%s、", supply.getName()));
        stringBuilder.append(StringUtils.isEmpty(supply.getReputation()) ? "" : String.format("供应商信誉:%s、", supply.getReputation()));
        stringBuilder.append(StringUtils.isEmpty(supply.getGrade()) ? "" : String.format("供应商等级:%s、", baseDataDicService.getNameById(Integer.valueOf(supply.getGrade()))));
        stringBuilder.append(StringUtils.isEmpty(supply.getLineGrade()) ? "" : String.format("线路供水管等级:%s、", baseDataDicService.getNameById(Integer.valueOf(supply.getLineGrade()))));
        stringBuilder.append(StringUtils.isEmpty(supply.getPower()) ? "" : String.format("供应量或功率:%s、", supply.getPower()));
    }

    private void getCommonBuildingFunction(List<ExamineBuildingFunction> buildingFunctions, StringBuilder stringBuilder, BaseDataDic heatPreservationDic) {
        for (ExamineBuildingFunction buildingFunction : buildingFunctions) {
            if (buildingFunction.getType() != null && buildingFunction.getType().equals(heatPreservationDic.getId())) {
                stringBuilder.append(buildingFunction.getDecorationPart() == null ? "" : String.format("装修部位:%s、", baseDataDicService.getNameById(buildingFunction.getDecorationPart())));
                stringBuilder.append(buildingFunction.getDecoratingMaterial() == null ? "" : String.format("装修材料:%s、", baseDataDicService.getNameById(buildingFunction.getDecoratingMaterial())));
                stringBuilder.append(buildingFunction.getMaterialPrice() == null ? "" : String.format("材料价格区间:%s、", baseDataDicService.getNameById(buildingFunction.getMaterialPrice())));
                stringBuilder.append(buildingFunction.getConstructionTechnology() == null ? "" : String.format("施工工艺:%s、", baseDataDicService.getNameById(buildingFunction.getConstructionTechnology())));
            }
        }
    }

    private void getEnvironmentString(StringBuilder stringBuilder, BaseDataDic sceneryDic, ExamineMatchingEnvironment examineMatchingEnvironment) {
        BaseDataDic baseDataDic;
        if (StringUtils.equals(String.valueOf(sceneryDic.getId()), examineMatchingEnvironment.getType())) {
            baseDataDic = baseDataDicService.getDataDicById(examineMatchingEnvironment.getCategory());
            if (baseDataDic != null)
                stringBuilder.append(baseDataDic.getName());
            baseDataDic = baseDataDicService.getDataDicById(examineMatchingEnvironment.getInfluenceDegree());
            if (baseDataDic != null)
                stringBuilder.append(baseDataDic.getName());
            stringBuilder.append("、");
        }
    }

    private MarketCompareItemDto getMarketCompareItemDto(String name, String value) {
        MarketCompareItemDto marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName(name);
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        marketCompareItemDto.setValue(StringUtils.isBlank(value) ? "" : value);
        return marketCompareItemDto;
    }


}
