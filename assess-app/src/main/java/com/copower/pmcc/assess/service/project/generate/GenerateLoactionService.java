package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取区位数据
 */
@Service
public class GenerateLoactionService {
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String error = "无";


    /**
     * 获取坐落信息
     *
     * @param basicEstate
     * @param judgeObjects
     * @return
     * @throws Exception
     */
    public String getSeat(BasicEstate basicEstate, List<SchemeJudgeObject> judgeObjects) throws Exception {
        DeclareRecord record = declareRecordService.getDeclareRecordById(judgeObjects.get(0).getDeclareRecordId());
        return String.format("%s%s", record.getStreetNumber(), basicEstate.getName());
    }


    /**
     * 临街（路）状况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getFaceStreet(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        String value = null;
        try {
            Map<Integer, String> map = Maps.newHashMap();
            StringBuilder contentBuilder = new StringBuilder(8);
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply == null || basicApply.getId() == 0) {
                    continue;
                }
                contentBuilder.append(getFaceStreetExtend(basicApply));
                if (contentBuilder.length() > 0) {
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), contentBuilder.toString());
                }
                contentBuilder.delete(0, contentBuilder.toString().length());
            }
            String s = generateCommonMethod.judgeEachDesc(map, "", ";", false);
            value = StringUtils.strip(s, "，");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return "";
        }
        return StringUtils.isNotBlank(value) ? value : "不临街";
    }

    public String getFaceStreetExtend(BasicApply basicApply) throws Exception {
        if (basicApply == null || basicApply.getId() == 0) {
            return "";
        }
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        if (CollectionUtils.isEmpty(basicHouseFaceStreetVoList)) {
            return "";
        }
        List<String> stringList = Lists.newArrayList();
        basicHouseFaceStreetVoList.forEach(oo -> {
            if (StringUtils.isNotEmpty(oo.getStreetName())) {
                if (StringUtils.isNotEmpty(oo.getPositionName())) {
                    stringList.add(String.format("%s%s", oo.getPositionName(), oo.getStreetName()));
                }
            }
        });
        return StringUtils.join(stringList, ",");
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
        StringBuilder builder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = generateBaseExamineService.getBasicMatchingEnvironmentList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(scienceEnum.getKey());
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            for (BasicMatchingEnvironmentVo oo : basicMatchingEnvironmentVoList) {
                if (Objects.equal(new Integer(oo.getType()), baseDataDic.getId())) {
                    if (StringUtils.isNotBlank(oo.getRemark())) {
                        builder.append(oo.getRemark());
                    } else if (StringUtils.isNotBlank(oo.getHumanImpact())) {
                        String name = baseDataDicService.getNameById(oo.getHumanImpact());
                        if ("无".equals(name)) {
                            builder.append(oo.getHumanImpact()).append(oo.getCategoryName());
                        } else {
                            builder.append(oo.getCategoryName()).append(oo.getHumanImpact());
                        }
                    }
                    if (StringUtils.isNotBlank(builder.toString())) {
                        hashSet.add(builder.toString());
                    }
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        return StringUtils.join(hashSet, ",");
    }


    /**
     * 外部基础设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getExternalInfrastructure(BasicApply basicApply) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        List<BasicEstateSupply> basicEstateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
        List<BasicEstateNetwork> basicEstateNetworkList = generateBaseExamineService.getBasicEstateNetworkList();
        if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
            LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
            String v = null;
            long a1 = 0;
            long a2 = 0;
            long a3 = 0;
            for (BasicHouseFaceStreetVo basicHouseFaceStreetVo : basicHouseFaceStreetVoList) {
                linkedHashSet.add(String.format("%s%s", StringUtils.defaultString(basicHouseFaceStreetVo.getPositionName()), StringUtils.defaultString(basicHouseFaceStreetVo.getStreetName())));
                if (Objects.equal("主干道", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a1++;
                }
                if (Objects.equal("次干道", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a2++;
                }
                if (Objects.equal("支路", basicHouseFaceStreetVo.getStreetLevelName())) {
                    a3++;
                }

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
            if (a1 > 0 && a2 == 0 && a3 == 0) {
                v = "道路体系相对完善";
            }
            if (a2 > 0 && a1 == 0 && a3 == 0) {
                v = "道路体系相对完善";
            }
            if (a3 > 0 && a1 == 0 && a2 == 0) {
                v = "道路体系相对完善";
            }
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                stringBuilder.append(StringUtils.join(linkedHashSet, "，")).append("，");
            }
            if (StringUtils.isNotBlank(v)) {
                stringBuilder.append(v).append("，");
            }
        }
        if (basicApply.getType().intValue() == BasicApplyTypeEnum.RESIDENCE.getId().intValue()) {
            BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
            if (StringUtils.isNotBlank(landStateVo.getDevelopmentDegreeContentName())) {
                stringBuilder.append(landStateVo.getDevelopmentDegreeContentName());
            }
        }
        if (basicApply.getType().intValue() == BasicApplyTypeEnum.INDUSTRY.getId().intValue()) {
            LinkedHashMap<String, Long> map = Maps.newLinkedHashMap();
            long estateNetwork = 0;
            long estateSupplyGas = 0;
            long estateSupplyHeating = 0;
            long estateSupplyWater = 0;
            long estateDrainWater = 0;
            long estateSupplyPower = 0;
            if (CollectionUtils.isNotEmpty(basicEstateNetworkList)) {
                estateNetwork = basicEstateNetworkList.stream().filter(oo -> oo.getSupplier() != null).count();
            }
            if (CollectionUtils.isNotEmpty(basicEstateSupplyList)) {
                estateSupplyGas = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName(), basicEstateSupply.getType())).count();
                estateSupplyHeating = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName(), basicEstateSupply.getType())).count();
                estateSupplyWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName(), basicEstateSupply.getType())).count();
                estateDrainWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName(), basicEstateSupply.getType())).count();
                estateSupplyPower = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName(), basicEstateSupply.getType())).count();
            }
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getDes(), estateSupplyGas);
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getDes(), estateSupplyHeating);
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getDes(), estateSupplyWater);
            map.put(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getDes(), estateDrainWater);
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getDes(), estateSupplyPower);
            map.put("通讯设施", estateNetwork);
            LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
            if (map.entrySet().stream().allMatch(entry -> entry.getValue().longValue() != 0)) {
                map.forEach((s, aLong) -> {
                    linkedHashSet.add(s);
                });
                stringBuilder.append(StringUtils.join(linkedHashSet, "、")).append("完善。");
            } else {
                map.forEach((s, aLong) -> {
                    if (aLong.longValue() != 0) {
                        linkedHashSet.add(s);
                    }
                });
                stringBuilder.append(StringUtils.join(linkedHashSet, "、")).append("不完善，");
                linkedHashSet.clear();
                map.forEach((s, aLong) -> {
                    if (aLong.longValue() == 0) {
                        linkedHashSet.add(s);
                    }
                });
                stringBuilder.append(StringUtils.join(linkedHashSet, "、")).append("完善。");
            }
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }


    /**
     * 道路状况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getRoadCondition(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();

        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            String s = getRoadConditionExtend(basicApply);
            if (StringUtils.isNotBlank(s))
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), s);
        }
        String s = generateCommonMethod.judgeEachDesc(map, "", "。", false);
        return StringUtils.strip(s, "。");
    }

    public String getRoadConditionExtend(BasicApply basicApply) throws Exception {
        if (basicApply == null || basicApply.getId() == 0) {
            return "";
        }
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
        if (CollectionUtils.isEmpty(basicHouseFaceStreetVoList)) {
            return "";
        }
        for (int i = 0; i < basicHouseFaceStreetVoList.size(); i++) {
            BasicHouseFaceStreetVo basicHouseFaceStreetVo = basicHouseFaceStreetVoList.get(i);
            if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getPositionName())) {
                linkedHashSet.add(basicHouseFaceStreetVo.getPositionName());
            }
            if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getStreetLevelName())) {
                linkedHashSet.add(basicHouseFaceStreetVo.getStreetLevelName());
            }
            if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getStreetName())) {
                linkedHashSet.add(basicHouseFaceStreetVo.getStreetName());
            }
            if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getTrafficFlowName())) {
                linkedHashSet.add(String.format("交通流量%s 、", basicHouseFaceStreetVo.getTrafficFlowName()));
            }
            if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getVisitorsFlowrateName())) {
                linkedHashSet.add(String.format("人流量%s", basicHouseFaceStreetVo.getVisitorsFlowrateName()));
            }
            hashSet.add(StringUtils.join(linkedHashSet, ""));
            linkedHashSet.clear();
        }
        return StringUtils.join(hashSet, "；");
    }

    /**
     * 出入可利用的交通工具
     *
     * @return
     * @throws Exception
     */
    public String getAccessAvailableMeansTransport(BasicApply basicApply) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
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
        if (CollectionUtils.isNotEmpty(transitList)) {
            for (int i = 0; i < transitList.size(); i++) {
                BasicMatchingTrafficVo basicMatchingTrafficVo = transitList.get(i);
                String distance = generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName());
                if ("0".equals(distance)) {
                    contentBuilder.append("附近");
                } else {
                    contentBuilder.append("距");
                    contentBuilder.append(basicMatchingTrafficVo.getName());
                    contentBuilder.append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                }
                contentBuilder.append("有").append("线路").append(basicMatchingTrafficVo.getTheLine()).append("；");

            }
        }
        if (CollectionUtils.isNotEmpty(metroList)) {
            for (int i = 0; i < metroList.size(); i++) {
                BasicMatchingTrafficVo basicMatchingTrafficVo = metroList.get(i);
                String distance = generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName());
                if ("0".equals(distance)) {
                    contentBuilder.append("附近");
                } else {
                    contentBuilder.append("距");
                    contentBuilder.append(basicMatchingTrafficVo.getName());
                    contentBuilder.append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                }
                contentBuilder.append("有").append("线路").append(basicMatchingTrafficVo.getTheLine()).append("；");
            }
        }
        return contentBuilder.toString();
    }

    /**
     * 停车方便度
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getParkingConvenience(BasicApply basicApply) throws Exception {
        StringBuilder builder = new StringBuilder(8);
        Set<String> stringSet = Sets.newHashSet();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        //自有停车场有300个车位
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicEstateParking> basicEstateParkingList = generateBaseExamineService.getBasicEstateParkingList();
        final String TYPE = "自有车位";
        if (CollectionUtils.isNotEmpty(basicEstateParkingList)) {
            List<BasicEstateParking> commonParkingList = basicEstateParkingList.stream().filter(basicEstateParking -> !Objects.equal(TYPE, baseDataDicService.getNameById(basicEstateParking.getParkingEstate()))).collect(Collectors.toList());
            List<BasicEstateParking> mySelfParkingList = Lists.newArrayList(CollectionUtils.subtract(basicEstateParkingList, commonParkingList));
            if (CollectionUtils.isNotEmpty(commonParkingList)) {
                commonParkingList.stream().forEach(basicEstateParking -> {
                    if (basicEstateParking.getNumber() != null) {
                        builder.append("自有").append(StringUtils.isNotBlank(basicEstateParking.getName()) ? basicEstateParking.getName() : "").append("停车场");
                        if (basicEstateParking.getNumber() != null)
                            builder.append("有").append(basicEstateParking.getNumber()).append("个车位");
                        stringSet.add(builder.toString());
                        builder.delete(0, builder.toString().length());
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(stringSet)) {
                linkedHashSet.add(StringUtils.join(stringSet, "、"));
            }
            stringSet.clear();
            if (CollectionUtils.isNotEmpty(mySelfParkingList)) {
                mySelfParkingList.stream().forEach(basicEstateParking -> {
                    if (basicEstateParking.getNumber() != null) {
                        String v = baseDataDicService.getNameById(basicEstateParking.getParkingType());
                        if (StringUtils.isNotBlank(v)) {
                            builder.append("在").append(baseDataDicService.getNameById(basicEstateParking.getLocation())).append("位置处");
                            builder.append(v).append("有");
                            builder.append(basicEstateParking.getNumber()).append("个车位");
                            stringSet.add(builder.toString());
                            builder.delete(0, builder.toString().length());
                        }
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(stringSet)) {
                linkedHashSet.add(StringUtils.join(stringSet, "、"));
            }
            stringSet.clear();
        }
        return generateCommonMethod.trim(StringUtils.join(linkedHashSet, "；"));
    }

    /**
     * 交通收费情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficCharges(BasicApply basicApply) throws Exception {
        StringBuilder builder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (StringUtils.isNotBlank(basicMatchingTrafficVo.getCostStandard())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                builder.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getCostStandard());
            });
        }
        return generateCommonMethod.trim(builder.toString());
    }

    /**
     * 交通管制情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficControl(BasicApply basicApply) throws Exception {
        StringBuilder builder = new StringBuilder();
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainRoad.getName())) {
                if (basicMatchingTrafficVo.getFlag() != null) {
                    return basicMatchingTrafficVo.getFlag().booleanValue();
                }
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            builder.append("限行情况:");
            Set<String> stringSet = Sets.newHashSet();
            StringBuffer stringBuffer = new StringBuffer(8);
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                stringBuffer.append(basicMatchingTrafficVo.getLimitSpeed()).append(basicMatchingTrafficVo.getLimitTime()).append(basicMatchingTrafficVo.getLimitSpeialName()).append(";");
                stringSet.add(stringBuffer.toString());
                stringBuffer.delete(0, stringBuffer.toString().length());
            });
            builder.append(StringUtils.join(stringSet, ""));
        }
        return builder.toString();
    }


    /**
     * 与重要场所的距离
     *
     * @return
     */
    public String getWithImportantLocationDistance(BasicApply basicApply) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            //交通枢纽
            stringBuilder.append(StringUtils.defaultString(this.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.TrafficHub, "", false)));
            //主要转换
            stringBuilder.append(StringUtils.defaultString(this.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainConversion, "", false)));
        }
        //购物商场
        if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
            stringBuilder.append(StringUtils.defaultString(this.getMatchingLeisurePlacePrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET, "", false)));
        }
        //金融服务
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            stringBuilder.append(StringUtils.defaultString(getFinanceAndMedicalAndEducation(basicMatchingFinanceVoList, null, null, "")));
        }
        //医疗
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            stringBuilder.append(StringUtils.defaultString(getFinanceAndMedicalAndEducation(null, basicMatchingMedicalList, null, "")));
        }
        return stringBuilder.toString();
    }

    /**
     * 外部公共服务设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public List<String> getExternalPublicServiceFacilities(BasicApply basicApply, Boolean isShowName) throws Exception {
        List<String> stringArrayList = Lists.newArrayList();
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
        if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
            //购物商场
            stringArrayList.add(StringUtils.defaultString(this.getMatchingLeisurePlacePrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET,
                    isShowName ? String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getName(), ":") : "", false)));
            //餐饮
            stringArrayList.add(StringUtils.defaultString(this.getMatchingLeisurePlacePrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT,
                    isShowName ? String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getName(), ":") : "", false)));
            //休闲娱乐
            stringArrayList.add(StringUtils.defaultString(this.getMatchingLeisurePlacePrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION,
                    isShowName ? String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getName(), ":") : "", false)));
        }
        //金融服务
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            stringArrayList.add(StringUtils.defaultString(getFinanceAndMedicalAndEducation(basicMatchingFinanceVoList, null,
                    null, isShowName ? String.format("%s%s", "金融服务", ":") : "")));
        }
        //医疗
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            stringArrayList.add(StringUtils.defaultString(getFinanceAndMedicalAndEducation(null, basicMatchingMedicalList,
                    null, isShowName ? String.format("%s%s", "医疗", ":") : "")));
        }
        //教育
        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
            stringArrayList.add(StringUtils.defaultString(getFinanceAndMedicalAndEducation(null, null,
                    basicMatchingEducationList, isShowName ? String.format("%s%s", "教育", ":") : "")));
        }
        return stringArrayList;
    }

    /**
     * 朝向
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getOrientation(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicHouse basicHouse = generateBaseExamineService.getBasicHouse();
            if (basicHouse != null && basicHouse.getOrientation() != null) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), baseDataDicService.getNameById(basicHouse.getOrientation()));
            }
        }
        String s = generateCommonMethod.judgeEachDesc(map, "", ";", false);
        return StringUtils.strip(s, ";");
    }


    /**
     * 方位
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public String getPosition(BasicEstate basicEstate) throws Exception {
        StringBuffer stringBuffer = new StringBuffer(8);
        stringBuffer.delete(0, stringBuffer.toString().length());
        if (basicEstate != null) {
            stringBuffer.append(erpAreaService.getSysAreaName(basicEstate.getProvince()))
                    .append(erpAreaService.getSysAreaName(basicEstate.getCity()))
                    .append(erpAreaService.getSysAreaName(basicEstate.getDistrict()));
        }
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                String v = baseDataDicService.getNameById(dataBlock.getPosition());
                if (StringUtils.isNotBlank(v)) {
                    stringBuffer.append(v);
                }
                stringBuffer.append(dataBlock.getName());
            }
        }
        stringBuffer.append(baseDataDicService.getNameById(basicEstate.getPosition()));
        return stringBuffer.toString();
    }

    /**
     * 获取楼层信息
     *
     * @param judgeObjectList
     * @return
     */
    public String getFloor(List<SchemeJudgeObject> judgeObjectList) {
        StringBuffer stringBuffer = new StringBuffer(8);
        stringBuffer.delete(0, stringBuffer.toString().length());
        Map<Integer, String> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply != null) {
                    if (judgeObjectList.size() != 1) {
                        stringBuffer.append("位于");
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
                    BasicUnit basicUnit = generateBaseExamineService.getBasicUnit();
                    BasicHouse basicHouse = generateBaseExamineService.getBasicHouse();
                    if (basicBuilding == null || basicUnit == null || basicHouse == null) {
                        continue;
                    }
                    stringBuffer.append(basicBuilding.getBuildingNumber()).append("栋");
                    if (basicBuilding.getFloorCount() != null) {
                        stringBuffer.append(String.format("%s层建筑", basicBuilding.getFloorCount()));
                    }
                    if (StringUtils.isNotBlank(basicUnit.getUnitNumber())) {
                        stringBuffer.append(String.format("%s单元", basicUnit.getUnitNumber()));
                    }
                    if (StringUtils.isNotBlank(basicHouse.getFloor())) {
                        stringBuffer.append("的第").append(basicHouse.getFloor()).append("层");
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.toString().length());
                }
            }
        }
        String s = generateCommonMethod.judgeEachDesc(map, "", ";", false);
        return StringUtils.strip(s, ";");
    }


    /**
     * @param title 类似于(餐饮)
     * @param map
     * @return 餐饮:附近有串串A,串串B;距离大约500米有火锅小郡肝串串,xx火锅;
     */
    public String getDistanceDec(String title, Map<String, List<String>> map) {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (!map.isEmpty()) {
            if (StringUtils.isNotEmpty(title)) {
                stringBuilder.append(title);
            }
            map.entrySet().stream().forEach(entry -> {
                if ("0".equals(entry.getKey())) {
                    stringBuilder.append("附近有");
                } else {
                    stringBuilder.append("大约").append(entry.getKey()).append("米").append("有");
                }
                stringBuilder.append(StringUtils.join(entry.getValue(), "，")).append("；");
            });
        }
        if (StringUtils.isNotEmpty(stringBuilder.toString().trim())) {
            String text = stringBuilder.toString();
            return text;
        }
        return "";
    }

    /**
     * 教育 医疗 金融 距离信息
     *
     * @param basicMatchingFinanceVoList
     * @param basicMatchingMedicalList
     * @param basicMatchingEducationList
     * @return
     */
    public String getFinanceAndMedicalAndEducation(List<BasicMatchingFinanceVo> basicMatchingFinanceVoList, List<BasicMatchingMedical> basicMatchingMedicalList, List<BasicMatchingEducation> basicMatchingEducationList, String title) {
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            basicMatchingFinanceVoList.forEach(oo -> {
                integerStringMap.put(oo.getId(), StringUtils.isNotBlank(oo.getDistance()) ? oo.getDistance() : "0");
            });
            Map<String, List<Integer>> entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> basicMatchingFinanceVoList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                return this.getDistanceDec(title, listMap);
            }
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
            basicMatchingEducationList.forEach(oo -> {
                integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
            });
            Map<String, List<Integer>> entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> {
                        BasicMatchingEducation basicMatchingEducation = basicMatchingEducationList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get();
                        return String.format("%s%s", baseDataDicService.getNameById(basicMatchingEducation.getSchoolNature()), basicMatchingEducation.getSchoolName());
                    }).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                return this.getDistanceDec(title, listMap);
            }
        }
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            basicMatchingMedicalList.forEach(oo -> {
                integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
            });
            Map<String, List<Integer>> entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> basicMatchingMedicalList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getOrganizationName()).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                return this.getDistanceDec(title, listMap);
            }
        }
        return null;
    }

    /**
     * 休闲场所 包含-购物、娱乐、餐饮距离
     *
     * @param notFilter                     是否过滤
     * @param basicMatchingLeisurePlaceList
     * @param matchingLeisurePlaceTypeEnum
     * @return
     */
    public String getMatchingLeisurePlacePrivate(List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum matchingLeisurePlaceTypeEnum, String title, boolean notFilter) {
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        basicMatchingLeisurePlaceList.stream().filter(oo -> {
            if (notFilter) {
                return notFilter;
            }
            return Objects.equal(oo.getType(), matchingLeisurePlaceTypeEnum.getKey());
        }).forEachOrdered(oo -> {
            integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
        });
        Map<String, List<Integer>> entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
        if (!entryMap.isEmpty()) {
            entryMap.entrySet().stream().forEach(entry -> {
                List<String> stringList = entry.getValue().stream().map(po -> basicMatchingLeisurePlaceList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                listMap.put(entry.getKey(), stringList);
            });
        }
        if (!listMap.isEmpty()) {
            return this.getDistanceDec(title, listMap);
        }
        return null;
    }

    /**
     * 交通条件距离
     *
     * @param basicMatchingTrafficList
     * @param examineMatchingTrafficTypeEnum
     * @param notFilter                      是否过滤
     * @return
     */
    public String getTrafficConditionsPrivate(List<BasicMatchingTrafficVo> basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum examineMatchingTrafficTypeEnum, String title, boolean notFilter) {
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (notFilter) {
                return notFilter;
            }
            return Objects.equal(basicMatchingTrafficVo.getType(), examineMatchingTrafficTypeEnum.getName());
        }).forEachOrdered(oo -> {
            integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
        });
        Map<String, List<Integer>> useEntryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
        if (!useEntryMap.isEmpty()) {
            useEntryMap.entrySet().stream().forEach(entry -> {
                List<String> stringList = entry.getValue().stream().map(po -> basicMatchingTrafficList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                listMap.put(entry.getKey(), stringList);
            });
        }
        if (!listMap.isEmpty()) {
            return this.getDistanceDec(title, listMap);
        }
        return null;
    }
}
