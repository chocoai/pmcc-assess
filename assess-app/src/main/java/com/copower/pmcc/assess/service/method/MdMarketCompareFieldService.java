package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessMarketCompareConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineEstateDao;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseDao;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseTradingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingEnvironmentService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingTrafficService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
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

    /**
     * 获取市场比较法各个字段对应值
     *
     * @param planDetailsId
     * @param setUseFieldList
     * @return
     */
    public String getJsonContent(Integer planDetailsId, Integer declareId, List<DataSetUseField> setUseFieldList) {
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

        StringBuilder stringBuilder = null;
        BaseDataDic baseDataDic = null;
        List<MarketCompareItemDto> list = Lists.newArrayList();
        for (DataSetUseField dataSetUseField : setUseFieldList) {
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
                    list.add(getMarketCompareItemDto(AssessMarketCompareConstant.TAX_BURDEN, houseTrading.getTaxBurden()));
                    break;
                case AssessMarketCompareConstant.PAYMENT_METHOD://付款方式
                    list.add(getMarketCompareItemDto(AssessMarketCompareConstant.PAYMENT_METHOD, houseTrading.getPaymentMethod()));
                    break;
                case AssessMarketCompareConstant.TRADING_PRICE://交易价格
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
                                baseDataDic = baseDataDicService.getDataDicById(examineMatchingTraffic.getDistance());
                                if (baseDataDic != null)
                                    stringBuilder.append(baseDataDic.getName());
                                stringBuilder.append("、");
                            }
                        }
                    }
                    list.add(getMarketCompareItemDto(AssessMarketCompareConstant.OFFICE_CONCENTRATION, stringBuilder.toString()));
                    break;
                case AssessMarketCompareConstant.FLOOR://楼层
                    list.add(getMarketCompareItemDto(AssessMarketCompareConstant.FLOOR, String.valueOf(examineHouse.getFloor())));
                    break;
                case AssessMarketCompareConstant.ORIENTATION://朝向
                    list.add(getMarketCompareItemDto(AssessMarketCompareConstant.ORIENTATION, examineHouse.getOrientation()));
                    break;
                case AssessMarketCompareConstant.TRAFFIC_CONDITIONS://交通条件
                    if (CollectionUtils.isNotEmpty(trafficList)) {
                        stringBuilder = new StringBuilder();
                        for (ExamineMatchingTraffic examineMatchingTraffic : trafficList) {
                            if (!StringUtils.equals(examineMatchingTraffic.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())) {
                                stringBuilder.append(examineMatchingTraffic.getName());
                                baseDataDic = baseDataDicService.getDataDicById(examineMatchingTraffic.getDistance());
                                if (baseDataDic != null)
                                    stringBuilder.append(baseDataDic.getName());
                                stringBuilder.append("、");
                            }
                        }
                    }
                    list.add(getMarketCompareItemDto(AssessMarketCompareConstant.OFFICE_CONCENTRATION, stringBuilder.toString()));
                    break;
                case AssessMarketCompareConstant.URBAN_INFRASTRUCTURE://城市基础设施

                    break;
                case AssessMarketCompareConstant.PUBLIC_SERVICE_FACILITIES://公共服务设施

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
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NATURAL, stringBuilder.toString()));
                    }
                    break;
                case AssessMarketCompareConstant.SCENERY://景观
                    if (CollectionUtils.isNotEmpty(environmentList)) {
                        stringBuilder = new StringBuilder();
                        BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                        for (ExamineMatchingEnvironment examineMatchingEnvironment : environmentList) {
                            getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                        }
                        list.add(getMarketCompareItemDto(AssessMarketCompareConstant.NATURAL, stringBuilder.toString()));
                    }
                    break;
                case AssessMarketCompareConstant.FEEINTEREST_TYPE://土地权益类型

                    break;
                case AssessMarketCompareConstant.LAND_CONTROL_SITUATION://土地管制情况

                    break;
                case AssessMarketCompareConstant.LAND_RIGHTS://土地他项权利

                    break;
                case AssessMarketCompareConstant.HOUSING_OWNERSHIP://房屋所有权

                    break;
                case AssessMarketCompareConstant.LEASEHOLD://租赁情况

                    break;
                case AssessMarketCompareConstant.PROPERTY_MANAGEMENT://物业管理情况

                    break;
                case AssessMarketCompareConstant.OTHER_SPECIAL_SITUATIONS://其它特殊情况

                    break;
                case AssessMarketCompareConstant.LAND_ENTITY_STATUS://土地实体状况

                    break;
                case AssessMarketCompareConstant.BUILDING_SCALE://建筑规模

                    break;
                case AssessMarketCompareConstant.BUILDING_AREA://建筑面积（㎡）

                    break;
                case AssessMarketCompareConstant.FLOOR_COUNT://层数

                    break;
                case AssessMarketCompareConstant.FLOOR_HEIGHT://层高

                    break;
                case AssessMarketCompareConstant.NET_HEIGHT://净高

                    break;
                case AssessMarketCompareConstant.BUILDING_HEIGHT://建筑高度

                    break;
                case AssessMarketCompareConstant.BUILDING_STRUCTURE://建筑结构

                    break;
                case AssessMarketCompareConstant.ARCHITECTURAL_OUTFIT://建筑外装

                    break;
                case AssessMarketCompareConstant.AERATION://通风

                    break;
                case AssessMarketCompareConstant.LIGHTING://采光

                    break;
                case AssessMarketCompareConstant.SUNSHINE://日照

                    break;
                case AssessMarketCompareConstant.SOUND_INSULATION://隔音

                    break;
                case AssessMarketCompareConstant.HEAT_PRESERVATION://保温

                    break;
                case AssessMarketCompareConstant.HEAT_INSULATION://隔热

                    break;
                case AssessMarketCompareConstant.WATERPROOF://防水

                    break;
                case AssessMarketCompareConstant.INTELLIGENT_LEVEL://设施设备及智能化程度

                    break;
                case AssessMarketCompareConstant.POWER_SUPPLY_MODE://供电方式

                    break;
                case AssessMarketCompareConstant.WATER_SUPPLY_DRAINAGE_MODE://供（排）水方式

                    break;
                case AssessMarketCompareConstant.HEATING_MODE://采暖供热方式

                    break;
                case AssessMarketCompareConstant.GAS_SUPPLY_MODE://供气方式

                    break;
                case AssessMarketCompareConstant.NETWORK://通讯网络

                    break;
                case AssessMarketCompareConstant.ELEVATOR_HOUSEHOLD_RATIO://电梯梯户比

                    break;
                case AssessMarketCompareConstant.INTERNAL_ASSEMBLY://内装

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
    }

    private void getEnvironmentString(StringBuilder stringBuilder, BaseDataDic sceneryDic, ExamineMatchingEnvironment examineMatchingEnvironment) {
        BaseDataDic baseDataDic;
        if (sceneryDic.getId().equals(examineMatchingEnvironment.getType())) {
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
        marketCompareItemDto.setValue(value);
        return marketCompareItemDto;
    }

    public void getInfo(Integer judgeObjectId, List<Integer> casePlanDetailsIdList) {
        //根据委估对象id找出相应的查勘信息，如果委估对象是由多个权证合并则找第一个权证的相关信息即可
        //1.找出当前权证的相关信息，根据设定用途确定需要对比的字段信息
        //2.找出各字段的值，案例类似处理
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        DataSetUseField dataSetUseField = dataSetUseFieldService.getSetUseFieldByType(judgeObject.getSetUse());
    }

    /**
     *
     */
    private void initField() {

    }

}
