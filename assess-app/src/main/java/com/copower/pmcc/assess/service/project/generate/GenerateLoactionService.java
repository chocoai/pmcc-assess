package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.MyEntry;
import com.copower.pmcc.assess.common.enums.basic.*;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
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
    @Autowired
    private BasicApplyService basicApplyService;

    private static final String error = "无";
    @Autowired
    private BaseService baseService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;


    /**
     * 获取坐落信息
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public String getSeat(BasicEstate basicEstate) {
        if (basicEstate == null) return null;
        String value = String.format("%s%s", basicEstate.getStreetNumber(), basicEstate.getName());
        return value;
    }


    /**
     * 临街（路）状况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getFaceStreet(List<SchemeJudgeObject> judgeObjectList) {
        String value = null;
        try {
            Map<Integer, String> map = Maps.newHashMap();
            StringBuilder contentBuilder = new StringBuilder(8);
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
                if (basicApply == null || basicApply.getId() == 0) {
                    continue;
                }
                contentBuilder.append(getFaceStreetExtend(basicApply));
                if (StringUtils.isNotBlank(contentBuilder.toString())) {
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), contentBuilder.toString());
                }
                contentBuilder.delete(0, contentBuilder.toString().length());
            }
            String s = generateCommonMethod.judgeEachDesc2(map, "", ";", false);
            value = StringUtils.strip(s, "，");
        } catch (Exception ex) {
            baseService.writeExceptionInfo(ex);
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
        return StringUtils.join(stringList.stream().distinct().collect(Collectors.toList()), ",");
    }


    /**
     * 环境
     *
     * @param basicApply
     * @param scienceEnum
     * @return
     * @throws Exception
     */
    public String getEnvironmentalScience(BasicApplyBatch basicApply, EnvironmentalScienceEnum scienceEnum) {
        StringBuilder builder = new StringBuilder(8);
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = basicExamineHandle.getBasicMatchingEnvironmentList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(scienceEnum.getKey());
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            List<BasicMatchingEnvironmentVo> collect = basicMatchingEnvironmentVoList.stream().filter(basicMatchingEnvironmentVo -> Objects.equal(basicMatchingEnvironmentVo.getType(), baseDataDic.getId().toString())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect)) {
                basicMatchingEnvironmentVoList = Arrays.asList(collect.get(0));
            } else {
                basicMatchingEnvironmentVoList.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            for (BasicMatchingEnvironmentVo oo : basicMatchingEnvironmentVoList) {
                if (StringUtils.isNotBlank(oo.getRemark())) {
                    builder.append(oo.getRemark());
                } else if (StringUtils.isNotBlank(oo.getHumanImpactName())) {
                    String name = oo.getHumanImpactName();
                    if ("无".equals(name)) {
                        builder.append("无").append(oo.getCategoryName());
                    } else {
                        builder.append(oo.getCategoryName()).append(name);
                    }
                }
                if (StringUtils.isNotBlank(builder.toString())) {
                    hashSet.add(builder.toString());
                }
                builder.delete(0, builder.toString().length());
            }
        }
        String value = StringUtils.join(hashSet, ",");
        return value;
    }

    /**
     * 外部基础设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getExternalInfrastructure(BasicApplyBatch basicApply) {
        StringBuilder stringBuilder = new StringBuilder(8);
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = basicExamineHandle.getBasicHouseFaceStreetAll();
        List<BasicEstateSupply> basicEstateSupplyList = basicExamineHandle.getBasicEstateSupplyList();
        List<BasicEstateNetwork> basicEstateNetworkList = basicExamineHandle.getBasicEstateNetworkList();
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
            BasicEstateVo basicEstateVo = basicExamineHandle.getEstate();
            if (basicEstateVo != null && StringUtils.isNotBlank(basicEstateVo.getInfrastructureName())) {
                stringBuilder.append(basicEstateVo.getInfrastructureName());
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
        String value = StringUtils.strip(stringBuilder.toString(), "。");
        return value;
    }

    /**
     * 道路状况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getRoadConditionNew(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();

        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApplyBatch basicApplyBatch = surveyCommonService.getBasicApplyBatchByApplyId(schemeJudgeObject.getBasicApplyId());
            String s = getRoadConditionExtend(basicApplyBatch);
            if (StringUtils.isNotBlank(s))
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), s);
        }
        String s = generateCommonMethod.judgeEachDesc(map, "", "。", false);
        String value = StringUtils.strip(s, "。");
        return value;
    }

    //道路状况
    public String getRoadConditionExtend(BasicApplyBatch basicApply) {
        if (basicApply == null || basicApply.getId() == 0) {
            return "";
        }
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicExamineHandle.getBasicMatchingTrafficList();
        List<BasicMatchingTrafficVo> metroList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainRoad.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(metroList)) {
            for (int i = 0; i < metroList.size(); i++) {
                BasicMatchingTrafficVo trafficVo = metroList.get(i);
                if (StringUtils.isNotBlank(trafficVo.getName())) {
                    linkedHashSet.add(trafficVo.getName());
                }
                if (StringUtils.isNotBlank(trafficVo.getPositionName())) {
                    linkedHashSet.add(trafficVo.getPositionName());
                }
                if (StringUtils.isNotBlank(trafficVo.getTrafficFlowName())) {
                    linkedHashSet.add(String.format("交通流量%s 、", trafficVo.getTrafficFlowName()));
                }
                if (StringUtils.isNotBlank(trafficVo.getVisitorsFlowrateName())) {
                    linkedHashSet.add(String.format("人流量%s", trafficVo.getVisitorsFlowrateName()));
                }
                hashSet.add(StringUtils.join(linkedHashSet, ""));
                linkedHashSet.clear();
            }
        }
        return StringUtils.join(hashSet, "；");
    }

    /**
     * 出入可利用的交通工具
     *
     * @return
     * @throws Exception
     */
    public String getAccessAvailableMeansTransport(BasicApplyBatch basicApply) {
        if (basicApply == null) {
            return null;
        }
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicExamineHandle.getBasicMatchingTrafficList();
        List<MyEntry<String, Integer>> transitEntry = new ArrayList<>();
        List<MyEntry<String, Integer>> metroEntry = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            Iterator<BasicMatchingTrafficVo> iterator = basicMatchingTrafficList.iterator();
            while (iterator.hasNext()) {
                BasicMatchingTrafficVo basicMatchingTrafficVo = iterator.next();
                //公交
                if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TRANSIT.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                    transitEntry.add(new MyEntry<>(String.format("线路%s", basicMatchingTrafficVo.getTheLine()), basicMatchingTrafficVo.getDistance()));
                }
                //地铁
                if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.METRO.getName()) && basicMatchingTrafficVo.getDistance() != null) {
                    metroEntry.add(new MyEntry<>(String.format("线路%s", basicMatchingTrafficVo.getTheLine()), basicMatchingTrafficVo.getDistance()));
                }
            }
        }
        LinkedHashMap<String, List<String>> transitMap = assembleDistanceMap(transitEntry);
        LinkedHashMap<String, List<String>> metroMap = assembleDistanceMap(metroEntry);
        if (!metroMap.isEmpty()) {
            metroMap.forEach((s, stringList) -> {
                if (transitMap.containsKey(s)) {
                    transitMap.get(s).addAll(stringList);
                } else {
                    transitMap.put(s,CollectionUtils.isNotEmpty( stringList)? stringList:new ArrayList<>());
                }
                transitMap.put(s, stringList) ;
            });
        }
        String value = getDistanceDec("", transitMap);
        return value;
    }

    /**
     * 停车方便度
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getParkingConvenience(BasicApplyBatch basicApply) {
        if (basicApply == null) {
            return null;
        }
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicEstateParking> basicEstateParkingList = basicExamineHandle.getBasicEstateParkingList();
        return getParkingConvenience(basicEstateParkingList);
    }

    public String getParkingConvenience(List<BasicEstateParking> basicEstateParkingList) {
        StringBuilder builder = new StringBuilder(8);
        Set<String> stringSet = Sets.newHashSet();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        final String TYPE = "自有车位";
        if (CollectionUtils.isNotEmpty(basicEstateParkingList)) {
            List<BasicEstateParking> commonParkingList = basicEstateParkingList.stream().filter(basicEstateParking -> !Objects.equal(TYPE, baseDataDicService.getNameById(basicEstateParking.getParkingEstate()))).collect(Collectors.toList());
            List<BasicEstateParking> mySelfParkingList = Lists.newArrayList(CollectionUtils.subtract(basicEstateParkingList, commonParkingList));
            if (CollectionUtils.isNotEmpty(commonParkingList)) {
                commonParkingList = Arrays.asList(commonParkingList.get(0));
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
                mySelfParkingList = Arrays.asList(mySelfParkingList.get(0));
                mySelfParkingList.stream().forEach(basicEstateParking -> {
                    if (basicEstateParking.getNumber() != null) {
                        String v = baseDataDicService.getNameById(basicEstateParking.getParkingType());
                        if (StringUtils.isNotBlank(v)) {
                            builder.append(baseDataDicService.getNameById(basicEstateParking.getParkingEstate()));
                            builder.append(baseDataDicService.getNameById(basicEstateParking.getLocation()));
                            builder.append(v);
                            builder.append(basicEstateParking.getNumber()).append("个");
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
        String value = generateCommonMethod.trim(StringUtils.join(linkedHashSet, "；"));
        return value;
    }

    /**
     * 交通收费情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficCharges(BasicApplyBatch basicApply) {
        if (basicApply == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(8);
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicExamineHandle.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (StringUtils.isNotBlank(basicMatchingTrafficVo.getCostStandard())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            basicMatchingTrafficList = Arrays.asList(basicMatchingTrafficList.get(0));
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                builder.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getCostStandard());
            });
        }
        String value = generateCommonMethod.trim(builder.toString());
        return value;
    }

    /**
     * 交通管制情况
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public String getTrafficControl(BasicApplyBatch basicApply) {
        if (basicApply == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicExamineHandle.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
            if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainRoad.getName())) {
                if (basicMatchingTrafficVo.getFlag() != null) {
                    return basicMatchingTrafficVo.getFlag().booleanValue();
                }
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            basicMatchingTrafficList = Arrays.asList(basicMatchingTrafficList.get(0));
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
        String value = builder.toString();
        return value;
    }

    /**
     * 与重要场所的距离
     * 最新调整 所有都取一个
     *
     * @return
     */
    public String getWithImportantLocationDistance(BasicApplyBatch basicApply) {
        if (basicApply == null) {
            return null;
        }
        BiConsumer<LinkedHashMap<String, List<String>>, LinkedHashMap<String, List<String>>> biConsumer = (((linkedHashMap, hashMap) -> {
            if (hashMap == null || hashMap.isEmpty()) {
                return;
            }
            Iterator<Map.Entry<String, List<String>>> iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> entry = iterator.next();
                if (linkedHashMap.containsKey(entry.getKey())) {
                    linkedHashMap.get(entry.getKey()).addAll(entry.getValue());
                } else {
                    linkedHashMap.put(entry.getKey(),CollectionUtils.isNotEmpty( entry.getValue())? entry.getValue():new ArrayList<>());
                }
            }
        }));
        StringBuilder stringBuilder = new StringBuilder(8);
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicExamineHandle.getBasicMatchingTrafficList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = basicExamineHandle.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = basicExamineHandle.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = basicExamineHandle.getBasicMatchingMedicalList();

        LinkedHashMap<String, List<String>> listMap = Maps.newLinkedHashMap();

        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            //交通枢纽
            List<BasicMatchingTrafficVo> trafficVoList1 = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(trafficVoList1)) {
                LinkedHashMap<String, List<String>> linkedHashMap = getTrafficConditionsCommonPrivate(trafficVoList1, ExamineMatchingTrafficTypeEnum.TrafficHub, "", false);
//                stringBuilder.append(StringUtils.defaultString(this.getDistanceDec("", linkedHashMap)));
                biConsumer.accept(listMap, linkedHashMap);
            }
            //主要转换
            List<BasicMatchingTrafficVo> trafficVoList2 = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainConversion.getName())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(trafficVoList2)) {
                LinkedHashMap<String, List<String>> linkedHashMap = getTrafficConditionsCommonPrivate(trafficVoList2, ExamineMatchingTrafficTypeEnum.MainConversion, "", false);
//                stringBuilder.append(StringUtils.defaultString(this.getDistanceDec("", linkedHashMap)));
                biConsumer.accept(listMap, linkedHashMap);
            }
        }
        //购物商场
        if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
            List<BasicMatchingLeisurePlace> matchingLeisurePlaceList = basicMatchingLeisurePlaceList.stream().filter(basicMatchingLeisurePlace -> Objects.equal(basicMatchingLeisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(matchingLeisurePlaceList)) {
                LinkedHashMap<String, List<String>> linkedHashMap = getMatchingLeisurePlaceCommonPrivate(Arrays.asList(matchingLeisurePlaceList.get(0)), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET, "", false);
//                stringBuilder.append(StringUtils.defaultString(this.getDistanceDec("", linkedHashMap)));
//                biConsumer.accept(listMap, linkedHashMap);
            }
        }
        //金融服务
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            LinkedHashMap<String, List<String>> linkedHashMap = getFinanceAndMedicalAndEducationCommonPrivate(Arrays.asList(basicMatchingFinanceVoList.get(0)), null, null, "");
//            stringBuilder.append(StringUtils.defaultString(this.getDistanceDec("", linkedHashMap)));
//            biConsumer.accept(listMap, linkedHashMap);
        }
        //医疗
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            LinkedHashMap<String, List<String>> linkedHashMap = getFinanceAndMedicalAndEducationCommonPrivate(null, Arrays.asList(basicMatchingMedicalList.get(0)), null, "");
//            stringBuilder.append(StringUtils.defaultString(this.getDistanceDec("", linkedHashMap)));
//            biConsumer.accept(listMap, linkedHashMap);
        }
//        String value = stringBuilder.toString();
        String value = StringUtils.defaultString(this.getDistanceDec("", listMap));
        return value;
    }

    /**
     * 外部公共服务设施
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public List<String> getExternalPublicServiceFacilities(BasicApplyBatch basicApply, Boolean isShowName) {
        if (basicApply == null) {
            return null;
        }
        BiConsumer<LinkedHashMap<String, List<String>>, LinkedHashMap<String, List<String>>> biConsumer = (((linkedHashMap, hashMap) -> {
            if (hashMap == null || hashMap.isEmpty()) {
                return;
            }
            Iterator<Map.Entry<String, List<String>>> iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> entry = iterator.next();
                if (linkedHashMap.containsKey(entry.getKey())) {
                    linkedHashMap.get(entry.getKey()).addAll(entry.getValue());
                } else {
                    linkedHashMap.put(entry.getKey(),CollectionUtils.isNotEmpty( entry.getValue())? entry.getValue():new ArrayList<>());
                }
            }
        }));
        LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
        BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApply);
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = basicExamineHandle.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = basicExamineHandle.getBasicMatchingMedicalList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = basicExamineHandle.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingEducation> basicMatchingEducationList = basicExamineHandle.getBasicMatchingEducatioListn();
        if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
            //购物商场
            LinkedHashMap<String, List<String>> leisurePlaceMapA = getMatchingLeisurePlaceCommonPrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET, isShowName ? String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getName(), ":") : "", false);
            biConsumer.accept(linkedHashMap, leisurePlaceMapA);
            //餐饮
            LinkedHashMap<String, List<String>> leisurePlaceMapB = getMatchingLeisurePlaceCommonPrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT, isShowName ? String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getName(), ":") : "", false);
            biConsumer.accept(linkedHashMap, leisurePlaceMapB);
            //休闲娱乐
            LinkedHashMap<String, List<String>> leisurePlaceMapC = getMatchingLeisurePlaceCommonPrivate(basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION, isShowName ? String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getName(), ":") : "", false);
            biConsumer.accept(linkedHashMap, leisurePlaceMapC);
        }

        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {//金融服务
            LinkedHashMap<String, List<String>> stringListLinkedHashMap = getFinanceAndMedicalAndEducationCommonPrivate(basicMatchingFinanceVoList, null, null, isShowName ? String.format("%s%s", "金融服务", ":") : "");
            biConsumer.accept(linkedHashMap, stringListLinkedHashMap);
        }
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {//医疗
            LinkedHashMap<String, List<String>> listLinkedHashMap = getFinanceAndMedicalAndEducationCommonPrivate(null, basicMatchingMedicalList, null, isShowName ? String.format("%s%s", "医疗", ":") : "");
            biConsumer.accept(linkedHashMap, listLinkedHashMap);
        }

        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {//教育
            LinkedHashMap<String, List<String>> stringListLinkedHashMap = getFinanceAndMedicalAndEducationCommonPrivate(null, null, basicMatchingEducationList, isShowName ? String.format("%s%s", "教育", ":") : "");
            biConsumer.accept(linkedHashMap, stringListLinkedHashMap);
        }
        return Arrays.asList(getDistanceDec("", linkedHashMap));
    }


    /**
     * 朝向
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getOrientation(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            if (schemeJudgeObject.getDeclareRecordId() == null || schemeJudgeObject.getDeclareRecordId() == 0) {
                continue;
            }
            BasicHouseVo basicHouseVo = generateBaseExamineService.getBasicHouse();
            BasicUnitHuxing basicUnitHuxing = generateBaseExamineService.getBasicUnitHuxing();
            Map<String, String> stringMap = new HashMap<>();
            if (basicUnitHuxing != null) {
                stringMap.put(baseDataDicService.getNameById(basicUnitHuxing.getOrientation()), basicHouseVo.getHouseNumber());
            }
            if (!stringMap.isEmpty()) {
                if (stringMap.size() == 1) {
                    Set<String> stringSet = stringMap.keySet();
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringSet, ""));
                } else {
                    Set<String> stringSet = new HashSet<>();
                    Iterator<Map.Entry<String, String>> entryIterator = stringMap.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<String, String> stringEntry = entryIterator.next();
                        stringSet.add(String.join(":", stringEntry.getKey(), stringEntry.getValue()));
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(stringSet, " "));
                }
            }
        }
        String s = generateCommonMethod.judgeEachDesc(map, "", ";", false);
        String value = StringUtils.strip(s, ";");
        return value;
    }


    /**
     * 方位
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public String getPosition(BasicEstate basicEstate) {
        StringBuffer stringBuffer = new StringBuffer(8);
        if (basicEstate == null) {
            return stringBuffer.toString();
        }
        stringBuffer.append(erpAreaService.getSysAreaName(basicEstate.getProvince()))
                .append(erpAreaService.getSysAreaName(basicEstate.getCity()))
                .append(erpAreaService.getSysAreaName(basicEstate.getDistrict()));
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                String v = baseDataDicService.getNameById(dataBlock.getPosition());
                if (StringUtils.isNotBlank(v)) {
                    stringBuffer.append(v);
                }
                stringBuffer.append(dataBlock.getName());
            }
        } else {
            stringBuffer.append(basicEstate.getBlockName());
        }
        stringBuffer.append(baseDataDicService.getNameById(basicEstate.getPosition()));
        String value = stringBuffer.toString();
        return value;
    }

    /**
     * 获取楼层信息
     *
     * @param judgeObjectList
     * @return
     */
    public String getFloor(List<SchemeJudgeObject> judgeObjectList) {
        StringBuilder stringBuffer = new StringBuilder(8);
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
                    stringBuffer.append(basicBuilding.getBuildingNumber());
                    if (basicBuilding.getFloorCount() != null) {
                        stringBuffer.append(String.format("%s层建筑", basicBuilding.getFloorCount()));
                    }
                    if (StringUtils.isNotBlank(basicUnit.getUnitNumber())) {
                        stringBuffer.append(String.format("%s", basicUnit.getUnitNumber()));
                    }
                    if (StringUtils.isNotBlank(basicHouse.getFloor())) {
                        stringBuffer.append("的第").append(basicHouse.getFloor()).append("层");
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuffer.toString().replaceAll("层+", "层"));
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
        LinkedHashMap<String, List<String>> listMap = getFinanceAndMedicalAndEducationCommonPrivate(basicMatchingFinanceVoList, basicMatchingMedicalList, basicMatchingEducationList, title);
        if (!listMap.isEmpty()) {
            return this.getDistanceDec(title, listMap);
        }
        return null;
    }

    /**
     * 教育 医疗 金融 距离信息
     *
     * @param basicMatchingFinanceVoList
     * @param basicMatchingMedicalList
     * @param basicMatchingEducationList
     * @return
     */
    public LinkedHashMap<String, List<String>> getFinanceAndMedicalAndEducationCommonPrivate(List<BasicMatchingFinanceVo> basicMatchingFinanceVoList, List<BasicMatchingMedical> basicMatchingMedicalList, List<BasicMatchingEducation> basicMatchingEducationList, String title) {
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        LinkedHashMap<String, List<String>> listMap = Maps.newLinkedHashMap();
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
                return listMap;
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
                return listMap;
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
                return listMap;
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
        LinkedHashMap<String, List<String>> listMap = getMatchingLeisurePlaceCommonPrivate(basicMatchingLeisurePlaceList, matchingLeisurePlaceTypeEnum, title, notFilter);
        if (!listMap.isEmpty()) {
            return this.getDistanceDec(title, listMap);
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
    public LinkedHashMap<String, List<String>> getMatchingLeisurePlaceCommonPrivate(List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList, ExamineMatchingLeisurePlaceTypeEnum matchingLeisurePlaceTypeEnum, String title, boolean notFilter) {
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        LinkedHashMap<String, List<String>> listMap = Maps.newLinkedHashMap();
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
        return listMap;
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
        LinkedHashMap<String, List<String>> listMap = getTrafficConditionsCommonPrivate(basicMatchingTrafficList, examineMatchingTrafficTypeEnum, title, notFilter);
        if (!listMap.isEmpty()) {
            return this.getDistanceDec(title, listMap);
        }
        return "";
    }

    /**
     * 交通条件距离
     *
     * @param basicMatchingTrafficList
     * @param examineMatchingTrafficTypeEnum
     * @param notFilter                      是否过滤
     * @return
     */
    public LinkedHashMap<String, List<String>> getTrafficConditionsCommonPrivate(List<BasicMatchingTrafficVo> basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum examineMatchingTrafficTypeEnum, String title, boolean notFilter) {
        List<MyEntry<String, Integer>> myEntryList = new ArrayList<>();
        basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
            if (notFilter) {
                return notFilter;
            }
            return Objects.equal(basicMatchingTrafficVo.getType(), examineMatchingTrafficTypeEnum.getName());
        }).forEachOrdered(oo -> {
            myEntryList.add(new MyEntry<>(oo.getName(), oo.getDistance()));
        });
        return assembleDistanceMap(myEntryList);
    }

    public LinkedHashMap<String, List<String>> assembleDistanceMap(List<MyEntry<String, Integer>> myEntryList) {
        LinkedHashMap<String, String> stringMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(myEntryList)) {
            Iterator<MyEntry<String, Integer>> iterator = myEntryList.iterator();
            while (iterator.hasNext()) {
                MyEntry<String, Integer> myEntry = iterator.next();
                stringMap.put(myEntry.getKey(), myEntry.getValue() != null ? myEntry.getValue().toString() : "0");
            }
        }
        LinkedHashMap<String, List<String>> useEntryMap = generateCommonMethod.getGroupByDistanceToString(stringMap);
        return useEntryMap;
    }

    public String assembleMapToValue(List<MyEntry<String, Integer>> myEntryList, String title) {
        Map<String, List<String>> useEntryMap = assembleDistanceMap(myEntryList);
        if (!useEntryMap.isEmpty()) {
            return getDistanceDec(title, useEntryMap);
        }
        return "";
    }
}
