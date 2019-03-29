package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.ExamineEstateSupplyEnumType;
import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取区位数据
 */
@Service
public class GenerateLoactionService {
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicApplyService basicApplyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private StringBuffer stringBuffer = new StringBuffer(8);
    private final String error = "无";


    /**
     * 获取坐落信息
     *
     * @param declareId
     * @param applyId
     * @return
     */
    public String getSeat(Integer declareId, Integer applyId) throws Exception {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
        BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(applyId);
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (declareRecord == null || StringUtils.isEmpty(declareRecord.getSeat())) {
            stringBuffer.append(error);
        } else {
            stringBuffer.append(declareRecord.getSeat());
        }
        if (basicEstate == null || StringUtils.isEmpty(basicEstate.getName())) {
            stringBuffer.append(error);
        } else {
            stringBuffer.append(basicEstate.getName());
        }
        return stringBuffer.toString();
    }

    /**
     * 临街（路）状况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getFaceStreet(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            basicHouseFaceStreetVoList.stream().forEach(basicHouseFaceStreetVo -> {
                if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getPositionName())) {
                    stringBuffer
                            .append(String.format("%s", StringUtils.isNotBlank(basicHouseFaceStreetVo.getPositionName()) ? basicHouseFaceStreetVo.getPositionName() : error))
                            .append(basicHouseFaceStreetVo.getStreetName()).append("\r");
                }
            });
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 外部公共服务设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getExternalPublicServiceFacilities(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
        //购物商场
        List<BasicMatchingLeisurePlace> matchingMarketList = basicMatchingLeisurePlaceList.stream()
                .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey(), basicMatchingLeisurePlace.getType()))
                .collect(Collectors.toList());
        //餐饮
        List<BasicMatchingLeisurePlace> matchingRestaurantList = basicMatchingLeisurePlaceList.stream()
                .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey(), basicMatchingLeisurePlace.getType()))
                .collect(Collectors.toList());
        //休闲娱乐
        List<BasicMatchingLeisurePlace> matchingRecreationList = basicMatchingLeisurePlaceList.stream()
                .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey(), basicMatchingLeisurePlace.getType()))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(matchingMarketList)) {
            stringBuffer.append(basicEstate.getName());
            for (int i = 0; i < matchingMarketList.size(); i++) {
                BasicMatchingLeisurePlace leisurePlace = matchingMarketList.get(i);
                stringBuffer.append("购物商场距离")
                        .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(leisurePlace.getDistance()))))
                        .append("有")
                        .append(baseDataDicService.getNameById(leisurePlace.getCategory()))
                        .append(leisurePlace.getName());
                if (i == matchingMarketList.size() - 1) {
                    stringBuffer.append(";");
                } else {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("\r");
        }
        if (CollectionUtils.isNotEmpty(matchingRestaurantList)) {
            stringBuffer.append(basicEstate.getName());
            for (int i = 0; i < matchingRestaurantList.size(); i++) {
                BasicMatchingLeisurePlace leisurePlace = matchingRestaurantList.get(i);
                stringBuffer.append("餐饮距离")
                        .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(leisurePlace.getDistance()))))
                        .append("有")
                        .append(baseDataDicService.getNameById(leisurePlace.getCategory()))
                        .append(leisurePlace.getName());
                if (i == matchingRestaurantList.size() - 1) {
                    stringBuffer.append(";");
                } else {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("\r");
        }
        if (CollectionUtils.isNotEmpty(matchingRecreationList)) {
            stringBuffer.append(basicEstate.getName());
            for (int i = 0; i < matchingRecreationList.size(); i++) {
                BasicMatchingLeisurePlace leisurePlace = matchingRecreationList.get(i);
                stringBuffer.append("休闲娱乐距离")
                        .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(leisurePlace.getDistance()))))
                        .append("有")
                        .append(baseDataDicService.getNameById(leisurePlace.getCategory()))
                        .append(leisurePlace.getName());
                if (i == matchingRecreationList.size() - 1) {
                    stringBuffer.append(";");
                } else {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("\r");
        }
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            stringBuffer.append(basicEstate.getName());
            for (int i = 0; i < basicMatchingFinanceVoList.size(); i++) {
                BasicMatchingFinanceVo basicMatchingFinanceVo = basicMatchingFinanceVoList.get(i);
                String v = null;
                if (NumberUtils.isNumber(basicMatchingFinanceVo.getDistance())) {
                    v = baseDataDicService.getNameById(basicMatchingFinanceVo.getDistance());
                    if (StringUtils.isEmpty(v)) {
                        v = basicMatchingFinanceVo.getDistance();
                    }
                } else {
                    v = basicMatchingFinanceVo.getDistance();
                }
                stringBuffer.append("金融距离")
                        .append(String.format("大约%s米", generateCommonMethod.getNumber(v)))
                        .append("有")
                        .append(baseDataDicService.getNameById(basicMatchingFinanceVo.getCategory()))
                        .append(basicMatchingFinanceVo.getName());
                if (i == basicMatchingFinanceVoList.size() - 1) {
                    stringBuffer.append(";");
                } else {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("\r");
        }
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            stringBuffer.append(basicEstate.getName());
            for (int i = 0; i < basicMatchingMedicalList.size(); i++) {
                BasicMatchingMedical basicMatchingMedical = basicMatchingMedicalList.get(i);
                stringBuffer.append("医疗距离")
                        .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(basicMatchingMedical.getDistance()))))
                        .append("有")
                        .append(baseDataDicService.getNameById(basicMatchingMedical.getOrganizationLevel()))
                        .append(basicMatchingMedical.getOrganizationName());
                if (i == basicMatchingMedicalList.size() - 1 || basicMatchingMedicalList.size() == 1) {
                    stringBuffer.append(";");
                } else {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("\r");
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
            stringBuffer.append(basicEstate.getName());
            for (int i = 0; i < basicMatchingEducationList.size(); i++) {
                BasicMatchingEducation basicMatchingEducation = basicMatchingEducationList.get(i);
                stringBuffer.append("教育距离")
                        .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(basicMatchingEducation.getDistance()))))
                        .append("有")
                        .append(baseDataDicService.getNameById(basicMatchingEducation.getSchoolNature()))
                        .append(basicMatchingEducation.getSchoolName());
                if (i == basicMatchingEducationList.size() - 1 || basicMatchingEducationList.size() == 1) {
                    stringBuffer.append("。");
                } else {
                    stringBuffer.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 环境
     *
     * @param basicApply
     * @param scienceEnum
     * @return
     * @throws Exception
     */
    public String getEnvironmentalScience(BasicApply basicApply, EnvironmentalScienceEnum scienceEnum) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = generateBaseExamineService.getBasicMatchingEnvironmentList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(scienceEnum.getKey());
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            String a = "无影响";
            String b = "影响一般";
            String c = "影响较大";
            String d = "有重大影响";
            //自然环境要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.NATURAL.getKey())) {
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "空气", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "水体质量", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "美观度", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "静密程度", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "空间辐射", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "废弃物", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("。");
            }
            //人文环境要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.HUMANITY.getKey())) {
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "相邻利用物业状况", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "居民特征", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "治安状况", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "相邻利用物业状况", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("。");
            }
            //景观要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.SCENERY.getKey())) {
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "海景", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "江景", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "河景", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "湖景", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "山景", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "公园", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "园林", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("，");
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "中庭景观", stringBuffer, basicMatchingEnvironmentVoList);
                stringBuffer.append("。");
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    private void environmentalScience(EnvironmentalScienceEnum scienceEnum, String a, String b, String c, String d, String key, StringBuffer stringBuffer, List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList) {
        BasicMatchingEnvironmentVo matchingEnvironmentVo = null;
        try {
            matchingEnvironmentVo = basicMatchingEnvironmentVoList.stream().filter(basicMatchingEnvironmentVo -> {
                if (basicMatchingEnvironmentVo.getCategory() != null && StringUtils.isNotBlank(basicMatchingEnvironmentVo.getCategoryName())) {
                    if (basicMatchingEnvironmentVo.getCategoryName().indexOf(key) != -1) {
                        return true;
                    }
                }
                return false;
            }).findFirst().get();
        } catch (Exception e) {
            //经常出现null parsent value 异常
            logger.error(String.format("异常 com.copower.pmcc.assess.service.project.generate.GenerateLoactionService.environmentalScience %s", e.getMessage()), e);
        }
        if (!Objects.equal(EnvironmentalScienceEnum.SCENERY.getKey(), scienceEnum.getKey())) {
            if (matchingEnvironmentVo != null) {
                if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), a)) {
                    matchingEnvironmentVo.setRemark("优良");
                }
                if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), b)) {
                    matchingEnvironmentVo.setRemark("一般");
                }
                if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), c)) {
                    matchingEnvironmentVo.setRemark("差");
                }
                if (Objects.equal(matchingEnvironmentVo.getInfluenceDegreeName(), d)) {
                    matchingEnvironmentVo.setRemark("非常差");
                }
            }
        }
        if (matchingEnvironmentVo == null) {
            matchingEnvironmentVo = new BasicMatchingEnvironmentVo();
            matchingEnvironmentVo.setRemark(String.format("无%s信息", key));
        }
        stringBuffer.append(key).append(matchingEnvironmentVo.getRemark());
        if (StringUtils.isNotBlank(stringBuffer.toString().trim())) {

        }
    }

    /**
     * 综述
     *
     * @param schemeJudgeObject
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String content(SchemeJudgeObject schemeJudgeObject, BasicApply basicApply) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        if (basicEstate != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null && StringUtils.isNotBlank(dataBlock.getRemark())) {
                stringBuilder.append(dataBlock.getRemark());
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("区域内交通便捷度高、基础设施完备、属于成熟的区级中心商业区、医疗、教育、购物等公共配套设施完善，环境较好，" +
                    "区域内人流量大，居家生活便捷度高、商业聚集程度较高");
        }
        return stringBuilder.toString();
    }

    /**
     * 外部基础设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getExternalInfrastructure(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        List<BasicEstateSupply> basicEstateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            basicHouseFaceStreetVoList.stream().forEach(basicHouseFaceStreetVo -> {
                String v = null;
                long a1 = 0;
                long a2 = 0;
                long a3 = 0;
                if (Objects.equal("主干道", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a1++;
                }
                if (Objects.equal("次干道", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a2++;
                }
                if (Objects.equal("支路", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a3++;
                }
                if (a1 > 0 && a2 > 0) {
                    //支路不必判断
                    v = "道路体系完善";
                }
                if (a1 == 0 && a2 > 0 && a3 > 0) {
                    v = "道路体系相对完善";
                }
                if (a1 == 0 && a2 == 0 && a3 > 0) {
                    v = "道路体系不完善";
                }
                if (a1 == 0 && a2 == 0 && a3 == 0) {
                    v = "无基本的配套道路体系";
                }
                if (Objects.equal("未选定", basicHouseFaceStreetVo.getPositionName())) {
                    v = "无基本的配套道路体系";
                }
                if (StringUtils.isNotBlank(v)) {
                    stringBuffer.append(v);
                }
            });
        } else {
            stringBuffer.append("配套道路体系信息暂时无");
        }
        if (CollectionUtils.isNotEmpty(basicEstateSupplyList)) {
            StringBuilder stringBuilder = new StringBuilder(8);
            long estateSupplyGas = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName(), basicEstateSupply.getType())).count();
            long estateSupplyHeating = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName(), basicEstateSupply.getType())).count();
            long estateSupplyWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName(), basicEstateSupply.getType())).count();
            long estateDrainWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName(), basicEstateSupply.getType())).count();
            long estateSupplyPower = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName(), basicEstateSupply.getType())).count();
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName());
            if (estateSupplyGas == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName());
            if (estateSupplyHeating == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName());
            if (estateSupplyWater == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName());
            if (estateDrainWater == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            stringBuilder.append(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName());
            if (estateSupplyPower == 0) {
                stringBuilder.append("无");
            } else {
                stringBuilder.append("、");
            }
            long count = estateSupplyGas + estateSupplyHeating + estateSupplyWater + estateDrainWater + estateSupplyPower;
            if (count == 0) {
                stringBuffer.append("无供应信息");
            } else {
                stringBuffer.append(stringBuilder.toString());
            }
        } else {
            stringBuffer.append("配套供应信息暂时无");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 道路状况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getRoadCondition(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        stringBuffer.append(basicEstate.getName());
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            for (int i = 0; i < basicHouseFaceStreetVoList.size(); i++) {
                BasicHouseFaceStreetVo basicHouseFaceStreetVo = basicHouseFaceStreetVoList.get(i);
                stringBuffer.append(StringUtils.isNotBlank(basicHouseFaceStreetVo.getPositionName()) ? basicHouseFaceStreetVo.getPositionName() : "方位无")
                        .append(StringUtils.isNotBlank(basicHouseFaceStreetVo.getStreetLevelName()) ? basicHouseFaceStreetVo.getStreetLevelName() : "道路等级无");
                stringBuffer.append(StringUtils.isNotBlank(basicHouseFaceStreetVo.getStreetName()) ? basicHouseFaceStreetVo.getStreetName() : "街道名称无");
                stringBuffer.append(",")
                        .append(StringUtils.isNotBlank(basicHouseFaceStreetVo.getTrafficFlowName()) ? String.format("交通流量%s", basicHouseFaceStreetVo.getTrafficFlowName()) : "交通流量无").append("、")
                        .append(StringUtils.isNotBlank(basicHouseFaceStreetVo.getVisitorsFlowrateName()) ? String.format("人流量%s", basicHouseFaceStreetVo.getVisitorsFlowrateName()) : "人流量无。");
                if (i != basicHouseFaceStreetVoList.size() - 1) {
                    stringBuffer.append("\r");
                }
            }
        } else {
            stringBuffer.append(error);
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 出入可利用的交通工具
     *
     * @return
     * @throws Exception
     */
    public String getAccessAvailableMeansTransport(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
        //公交
        List<BasicMatchingTrafficVo> transitList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TRANSIT.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        //地铁
        List<BasicMatchingTrafficVo> metroList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.METRO.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        if (CollectionUtils.isNotEmpty(transitList)) {
            for (int i = 0; i < transitList.size(); i++) {
                BasicMatchingTrafficVo basicMatchingTrafficVo = transitList.get(i);
                stringBuffer.append("距");
                stringBuffer.append(basicMatchingTrafficVo.getName());
                stringBuffer.append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                stringBuffer.append("有").append("线路").append(basicMatchingTrafficVo.getTheLine());
                if (i == transitList.size() - 1) {
                    stringBuffer.append("。");
                } else {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("。\r");
        }
        if (CollectionUtils.isNotEmpty(metroList)) {
            for (int i = 0; i < metroList.size(); i++) {
                BasicMatchingTrafficVo basicMatchingTrafficVo = metroList.get(i);
                stringBuffer.append("距");
                stringBuffer.append(basicMatchingTrafficVo.getName());
                stringBuffer.append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                stringBuffer.append("有").append("线路").append(basicMatchingTrafficVo.getTheLine());
                if (i == metroList.size() - 1) {
                    stringBuffer.append("。");
                } else {
                    stringBuffer.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 停车方便度
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getParkingConvenience(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicEstateParking> basicEstateParkingList = generateBaseExamineService.getBasicEstateParkingList();
        if (CollectionUtils.isNotEmpty(basicEstateParkingList)) {
            List<BasicEstateParking> commonParkingList = basicEstateParkingList.stream().filter(basicEstateParking -> !Objects.equal("自有车位", baseDataDicService.getNameById(basicEstateParking.getParkingEstate()))).collect(Collectors.toList());
            List<BasicEstateParking> mySelfParkingList = Lists.newArrayList(CollectionUtils.subtract(basicEstateParkingList, commonParkingList));
            if (CollectionUtils.isNotEmpty(commonParkingList)) {
                commonParkingList.stream().forEach(basicEstateParking -> {
                    stringBuffer.append(StringUtils.isNotBlank(basicEstateParking.getName()) ? basicEstateParking.getName() : "名称无").append("有");
                    if (basicEstateParking.getNumber() != null) {
                        stringBuffer.append(basicEstateParking.getNumber()).append("辆车位");
                    } else {
                        stringBuffer.append("0辆车位");
                    }
                    stringBuffer.append(";");
                });
                if (CollectionUtils.isNotEmpty(mySelfParkingList)) {
                    stringBuffer.append("\r");
                }
            }
            if (CollectionUtils.isNotEmpty(mySelfParkingList)) {
                mySelfParkingList.stream().forEach(basicEstateParking -> {
                    stringBuffer.append(baseDataDicService.getNameById(basicEstateParking.getLocation())).append("位置处");
                    String v = baseDataDicService.getNameById(basicEstateParking.getParkingType());
                    stringBuffer.append(StringUtils.isNotBlank(v) ? v : "停车场类型无").append("有");
                    if (basicEstateParking.getNumber() != null) {
                        stringBuffer.append(basicEstateParking.getNumber()).append("辆车位");
                    } else {
                        stringBuffer.append("0辆车位");
                    }
                    stringBuffer.append(";");
                });
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 交通收费情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficCharges(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstate basicEstate = generateBaseExamineService.getEstate();
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (StringUtils.isNotBlank(basicMatchingTrafficVo.getCostStandard())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            stringBuffer.append(basicEstate.getName());
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getCostStandard()).append(";");
            });
            stringBuffer.append("。");
        } else {
            stringBuffer.append("收费标准无");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 交通管制情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficControl(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainRoad.getName()) && basicMatchingTrafficVo.getFlag() != null) {
                if (basicMatchingTrafficVo.getFlag().booleanValue()) {
                    return basicMatchingTrafficVo.getFlag().booleanValue();
                }
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            BasicEstate basicEstate = generateBaseExamineService.getEstate();
            stringBuffer.append(basicEstate.getName());
            stringBuffer.append("限行情况:");
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getLimitSpeed()).append(basicMatchingTrafficVo.getLimitTime()).append(basicMatchingTrafficVo.getLimitSpeialName()).append(";");
            });
            stringBuffer.append("。");
            stringBuffer.append("\r");
        } else {
            stringBuffer.append("交通管制限制无");
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 与重要场所的距离
     *
     * @return
     */
    public String getWithImportantLocationDistance(Integer applyId) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
        if (basicApply != null) {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
            List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList().stream()
                    .filter(basicMatchingLeisurePlace -> Objects.equal(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey(), basicMatchingLeisurePlace.getType()))
                    .collect(Collectors.toList());
            List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
            List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
            if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
                //交通枢纽
                List<BasicMatchingTrafficVo> trafficHubList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
                    if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(trafficHubList)) {
                    for (int i = 0; i < trafficHubList.size(); i++) {
                        BasicMatchingTrafficVo basicMatchingTrafficVo = trafficHubList.get(i);
                        stringBuffer.append("距").append(basicMatchingTrafficVo.getName())
                                .append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                        if (i == trafficHubList.size() - 1) {
                            stringBuffer.append("。");
                        } else {
                            stringBuffer.append(",");
                        }
                    }
                    stringBuffer.append("\r");
                }
                //主要转换
                List<BasicMatchingTrafficVo> mainConversionList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
                    if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainConversion.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(mainConversionList)) {
                    for (int i = 0; i < mainConversionList.size(); i++) {
                        BasicMatchingTrafficVo basicMatchingTrafficVo = mainConversionList.get(i);
                        stringBuffer.append("距").append(basicMatchingTrafficVo.getName())
                                .append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                        if (i == mainConversionList.size() - 1) {
                            stringBuffer.append("。");
                        } else {
                            stringBuffer.append(",");
                        }
                    }
                    stringBuffer.append("\r");
                }
            }
            //购物商场
            if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
                for (int i = 0; i < basicMatchingLeisurePlaceList.size(); i++) {
                    BasicMatchingLeisurePlace leisurePlace = basicMatchingLeisurePlaceList.get(i);
                    if (leisurePlace.getDistance() != null) {
                        stringBuffer.append("距").append(leisurePlace.getName())
                                .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(leisurePlace.getDistance()))));
                        if (i == basicMatchingLeisurePlaceList.size() - 1) {
                            stringBuffer.append("。");
                        } else {
                            stringBuffer.append(",");
                        }
                    }
                }
                stringBuffer.append("\r");
            }
            //金融服务
            if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
                for (int i = 0; i < basicMatchingFinanceVoList.size(); i++) {
                    BasicMatchingFinanceVo basicMatchingFinanceVo = basicMatchingFinanceVoList.get(i);
                    String value = null;
                    if (NumberUtils.isNumber(basicMatchingFinanceVo.getDistance())) {
                        value = baseDataDicService.getNameById(basicMatchingFinanceVo.getDistance());
                        if (StringUtils.isEmpty(value)) {
                            value = basicMatchingFinanceVo.getDistance();
                        }
                    } else {
                        value = basicMatchingFinanceVo.getDistance();
                    }
                    if (StringUtils.isEmpty(value)) {
                        value = "0";
                    }
                    stringBuffer.append("距").append(basicMatchingFinanceVo.getName()).append("大约").append(value).append("米");
                    if (i == basicMatchingFinanceVoList.size() - 1) {
                        stringBuffer.append("。");
                    } else {
                        stringBuffer.append(",");
                    }
                }
                stringBuffer.append("\r");
            }
            //医疗
            if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
                for (int i = 0; i < basicMatchingMedicalList.size(); i++) {
                    BasicMatchingMedical basicMatchingMedical = basicMatchingMedicalList.get(i);
                    if (basicMatchingMedical.getDistance() != null) {
                        stringBuffer.append("距").append(basicMatchingMedical.getOrganizationName())
                                .append(String.format("大约%s米", generateCommonMethod.getNumber(baseDataDicService.getNameById(basicMatchingMedical.getDistance()))));
                        if (i == basicMatchingMedicalList.size() - 1) {
                            stringBuffer.append("。");
                        } else {
                            stringBuffer.append(",");
                        }
                    }
                }
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 朝向
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getOrientation(BasicApply basicApply) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        try {
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            stringBuffer.append(baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getOrientation()));
        } catch (Exception e1) {
            logger.error("异常 com.copower.pmcc.assess.service.project.generate.GenerateLoactionService.getOrientation", e1);
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 方位
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public String getPosition(Integer applyId) throws Exception {
        BasicEstateVo basicEstate = basicEstateService.getBasicEstateVo(basicEstateService.getBasicEstateByApplyId(applyId));
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (basicEstate != null) {
            stringBuffer.append(basicEstate.getProvinceName()).append(basicEstate.getCityName()).append(basicEstate.getDistrictName());
        } else {
            stringBuffer.append(error);
        }
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                stringBuffer.append(baseDataDicService.getNameById(dataBlock.getPosition()));
                stringBuffer.append(dataBlock.getName());
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }

    /**
     * 获取楼层信息
     *
     * @param judgeObjectIds
     * @return
     */
    public String getFloor(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (int i = 0; i < judgeObjectList.size(); i++) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(i);
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply != null) {
                    if (judgeObjectList.size() != 1) {
                        stringBuffer.append(schemeJudgeObject.getNumber());
                        stringBuffer.append("号位于");
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
                    BasicHouse basicHouse = generateBaseExamineService.getBasicHouse();
                    if (basicBuilding == null || basicHouse == null) {
                        continue;
                    }
                    stringBuffer.append(basicBuilding.getBuildingNumber()).append("栋");
                    BigDecimal bigDecimal = basicBuilding.getFloorHeight().setScale(0, BigDecimal.ROUND_UP);
                    stringBuffer.append(bigDecimal.toString()).append("层");
                    stringBuffer.append("建筑的第").append(basicHouse.getFloor()).append("层");
                }
            }
        }
        if (StringUtils.isEmpty(stringBuffer.toString().trim())) {
            stringBuffer.append(error);
        }
        return stringBuffer.toString();
    }
}
