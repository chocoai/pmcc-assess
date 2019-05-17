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

import java.math.BigDecimal;
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
    private StringBuffer stringBuffer = new StringBuffer(8);
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
        try {
            Map<Integer, String> map = Maps.newHashMap();
            StringBuilder contentBuilder = new StringBuilder(8);
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply == null || basicApply.getId() == 0) {
                    continue;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();
                if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
                    //主干道
                    List<BasicMatchingTrafficVo> mainRoadList = basicMatchingTrafficList.stream().filter(basicMatchingTrafficVo -> {
                        if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.MainRoad.getName())) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(mainRoadList)) {
                        mainRoadList.stream()
                                .filter(oo -> StringUtils.isNotBlank(oo.getPositionName()) && StringUtils.isNotBlank(oo.getName()))
                                .forEach(oo -> contentBuilder.append(oo.getName()).append(oo.getPositionName()));
                        if (contentBuilder.length() > 0) {
                            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), contentBuilder.toString());
                        }
                    }
                }
                contentBuilder.delete(0, contentBuilder.toString().length());
            }
            String s = generateCommonMethod.judgeEachDesc(map, "", ";", false);
            return StringUtils.strip(s, "，");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return "";
        }
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
        StringBuilder builder = new StringBuilder();
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList = generateBaseExamineService.getBasicMatchingEnvironmentList();
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(basicMatchingEnvironmentVoList)) {
            String a = "无影响";
            String b = "影响一般";
            String c = "影响较大";
            String d = "有重大影响";
            //自然环境要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.NATURAL.getKey())) {
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "空气", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "水体质量", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "美观度", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "静密程度", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "空间辐射", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.NATURAL, a, b, c, d, "废弃物", hashSet, basicMatchingEnvironmentVoList);
                builder.append(StringUtils.join(hashSet, "，"));
                hashSet.clear();
            }
            //人文环境要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.HUMANITY.getKey())) {
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "相邻利用物业状况", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "居民特征", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "治安状况", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "相邻利用物业状况", hashSet, basicMatchingEnvironmentVoList);
                builder.append(StringUtils.join(hashSet, "，"));
                hashSet.clear();
            }
            //景观要素
            if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.SCENERY.getKey())) {
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "海景", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "江景", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "河景", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "湖景", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "山景", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "公园", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "园林", hashSet, basicMatchingEnvironmentVoList);
                environmentalScience(EnvironmentalScienceEnum.SCENERY, a, b, c, d, "中庭景观", hashSet, basicMatchingEnvironmentVoList);
                builder.append(StringUtils.join(hashSet, "，"));
                hashSet.clear();
            }
        }
        return StringUtils.strip(builder.toString(), "，");
    }

    private void environmentalScience(EnvironmentalScienceEnum scienceEnum, String a, String b, String c, String d, String key, LinkedHashSet<String> hashSet, List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList) {
        BasicMatchingEnvironmentVo matchingEnvironmentVo = null;
        if (CollectionUtils.isEmpty(basicMatchingEnvironmentVoList)) return;
        for (BasicMatchingEnvironmentVo basicMatchingEnvironmentVo : basicMatchingEnvironmentVoList) {
            if (basicMatchingEnvironmentVo.getCategoryName().contains(key)) {
                matchingEnvironmentVo = basicMatchingEnvironmentVo;
                break;
            }
        }
        if (matchingEnvironmentVo == null) return;
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
        if (matchingEnvironmentVo != null) {
            hashSet.add(String.format("%s%s", key, matchingEnvironmentVo.getRemark()));
        }
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
                //
                if (a1 > 0 && a2 == 0 && a3 == 0) {
                    v = "道路体系相对完善";
                }
                if (a2 > 0 && a1 == 0 && a3 == 0) {
                    v = "道路体系相对完善";
                }
                if (a3 > 0 && a1 == 0 && a2 == 0) {
                    v = "道路体系相对完善";
                }
                if (Objects.equal("未选定", basicHouseFaceStreetVo.getPositionName())) {
                    v = "无基本的配套道路体系";
                }
                if (StringUtils.isNotBlank(v)) {
                    stringBuilder.append(v).append("，");
                }
            });
        }
        if (basicApply.getType().intValue() == BasicApplyTypeEnum.RESIDENCE.getId().intValue()) {
            BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
            if (StringUtils.isNotBlank(landStateVo.getDevelopmentDegreeContentName())) {
                stringBuilder.append(landStateVo.getDevelopmentDegreeContentName());
            }
        }
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        if (basicApply.getType().intValue() == BasicApplyTypeEnum.INDUSTRY.getId().intValue()) {
            LinkedHashMap<String, Long> map = Maps.newLinkedHashMap();
            long estateNetwork = 0;
            long estateSupplyGas = 0;
            long estateSupplyHeating = 0;
            long estateSupplyWater = 0;
            long estateDrainWater = 0;
            long estateSupplyPower = 0;
            if (CollectionUtils.isNotEmpty(basicEstateNetworkList)) {
                estateNetwork = basicEstateNetworkList.stream().filter(oo -> oo.getSupplier() != null).count() > 0 ? 1 : 0;
            }
            if (CollectionUtils.isNotEmpty(basicEstateSupplyList)) {
                estateSupplyGas = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName(), basicEstateSupply.getType())).count() > 0 ? 1 : 0;
                estateSupplyHeating = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName(), basicEstateSupply.getType())).count() > 0 ? 1 : 0;
                estateSupplyWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName(), basicEstateSupply.getType())).count() > 0 ? 1 : 0;
                estateDrainWater = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName(), basicEstateSupply.getType())).count() > 0 ? 1 : 0;
                estateSupplyPower = basicEstateSupplyList.stream().filter(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName(), basicEstateSupply.getType())).count() > 0 ? 1 : 0;
            }
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName(), estateSupplyGas);
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName(), estateSupplyHeating);
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName(), estateSupplyWater);
            map.put(ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName(), estateDrainWater);
            map.put(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName(), estateSupplyPower);
            map.put("通讯设施", estateNetwork);
            long count = estateSupplyGas + estateSupplyHeating + estateSupplyWater + estateDrainWater + estateSupplyPower + estateNetwork;
            if (count == 6) {
                //all every good
                stringBuilder.append("供电、供气、供热、给水排水、通讯设施完善。");
            }
            if (count != 6) {
                if (!map.isEmpty()) {
                    map.entrySet().stream().forEach(entry -> {
                        if (entry.getValue().intValue() == 1) {
                            linkedHashSet.add(entry.getKey());
                        }
                    });
                    stringBuilder.append(StringUtils.join(linkedHashSet, "、")).append("完善");
                    linkedHashSet.clear();
                    map.entrySet().stream().forEach(entry -> {
                        if (entry.getValue().intValue() == 0) {
                            linkedHashSet.add(entry.getKey());
                        }
                    });
                    stringBuilder.append(StringUtils.join(linkedHashSet, "、")).append("不完善。");
                    linkedHashSet.clear();
                }
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
            if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
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
            }
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.join(hashSet, "；"));
            hashSet.clear();
        }
        String s = generateCommonMethod.judgeEachDesc(map, "", "。", false);
        return StringUtils.strip(s, "。");
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
                        builder.append("自有").append(StringUtils.isNotBlank(basicEstateParking.getName()) ? basicEstateParking.getName() : "").append("停车场").append("有");
                        builder.append(basicEstateParking.getNumber()).append("辆车位");
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
                            builder.append(basicEstateParking.getNumber()).append("辆车位");
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
            basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                builder.append(basicMatchingTrafficVo.getLimitSpeed()).append(basicMatchingTrafficVo.getLimitTime()).append(basicMatchingTrafficVo.getLimitSpeialName()).append(";");
            });
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
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<Integer>> entryMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
            //交通枢纽
            if (basicMatchingTrafficList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())).count() >= 1) {
                basicMatchingTrafficList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingTrafficTypeEnum.TrafficHub.getName())).forEach(oo -> {
                    integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
                });
                entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!entryMap.isEmpty()) {
                    entryMap.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> basicMatchingTrafficList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    stringBuilder.append(this.getDistanceDec("", listMap));
                }
                integerStringMap.clear();
                entryMap.clear();
                listMap.clear();
            }
            //主要转换
            if (basicMatchingTrafficList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingTrafficTypeEnum.MainConversion.getName())).count() >= 1) {
                basicMatchingTrafficList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingTrafficTypeEnum.MainConversion.getName())).forEach(oo -> {
                    integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
                });
                entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!entryMap.isEmpty()) {
                    entryMap.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> basicMatchingTrafficList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    stringBuilder.append(this.getDistanceDec("", listMap));
                }
                integerStringMap.clear();
                entryMap.clear();
                listMap.clear();
            }
        }
        //购物商场
        if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
            if (basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())).count() >= 1) {
                basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())).forEach(oo -> {
                    integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
                });
                entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!entryMap.isEmpty()) {
                    entryMap.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> basicMatchingLeisurePlaceList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    stringBuilder.append(this.getDistanceDec("", listMap));
                }
                integerStringMap.clear();
                entryMap.clear();
                listMap.clear();
            }
        }
        //金融服务
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            basicMatchingFinanceVoList.stream().forEach(oo -> {
                integerStringMap.put(oo.getId(), StringUtils.isNotBlank(oo.getDistance()) ? oo.getDistance() : "0");
            });
            entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> basicMatchingFinanceVoList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                stringBuilder.append(this.getDistanceDec("", listMap));
            }
            integerStringMap.clear();
            entryMap.clear();
            listMap.clear();
        }
        //医疗
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            basicMatchingMedicalList.stream().forEach(oo -> {
                integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
            });
            entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> basicMatchingMedicalList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getOrganizationName()).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                stringBuilder.append(this.getDistanceDec("", listMap));
            }
            integerStringMap.clear();
            entryMap.clear();
            listMap.clear();
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
    public List<String> getExternalPublicServiceFacilities(BasicApply basicApply) throws Exception {
        List<String> stringArrayList = Lists.newArrayList();
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
        List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
        List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<Integer>> entryMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
            //购物商场
            if (basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())).count() >= 1) {
                basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())).forEach(oo -> {
                    integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
                });
                entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!entryMap.isEmpty()) {
                    entryMap.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> basicMatchingLeisurePlaceList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    String s = this.getDistanceDec(String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getName(), ":"), listMap);
                    stringArrayList.add(s);
                }
                integerStringMap.clear();
                entryMap.clear();
                listMap.clear();
            }
            //餐饮
            if (basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())).count() >= 1) {
                basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())).forEach(oo -> {
                    integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
                });
                entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!entryMap.isEmpty()) {
                    entryMap.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> basicMatchingLeisurePlaceList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    String s = this.getDistanceDec(String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getName(), ":"), listMap);
                    stringArrayList.add(s);
                }
                integerStringMap.clear();
                entryMap.clear();
                listMap.clear();
            }
            //休闲娱乐
            if (basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())).count() >= 1) {
                basicMatchingLeisurePlaceList.stream().filter(oo -> Objects.equal(oo.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())).forEach(oo -> {
                    integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
                });
                entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!entryMap.isEmpty()) {
                    entryMap.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> basicMatchingLeisurePlaceList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    String s = this.getDistanceDec(String.format("%s%s", ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getName(), ":"), listMap);
                    stringArrayList.add(s);
                }
                integerStringMap.clear();
                entryMap.clear();
                listMap.clear();
            }
        }
        //金融服务
        if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
            basicMatchingFinanceVoList.stream().forEach(oo -> {
                integerStringMap.put(oo.getId(), StringUtils.isNotBlank(oo.getDistance()) ? oo.getDistance() : "0");
            });
            entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> basicMatchingFinanceVoList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                String s = this.getDistanceDec(String.format("%s%s", "金融服务", ":"), listMap);
                stringArrayList.add(s);
                ;
            }
            integerStringMap.clear();
            entryMap.clear();
            listMap.clear();
        }
        if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
            basicMatchingMedicalList.stream().forEach(oo -> {
                integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
            });
            entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
            if (!entryMap.isEmpty()) {
                entryMap.entrySet().stream().forEach(entry -> {
                    List<String> stringList = entry.getValue().stream().map(po -> basicMatchingMedicalList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getOrganizationName()).collect(Collectors.toList());
                    listMap.put(entry.getKey(), stringList);
                });
            }
            if (!listMap.isEmpty()) {
                String s = this.getDistanceDec(String.format("%s%s", "医疗", ":"), listMap);
                stringArrayList.add(s);
                ;
            }
            integerStringMap.clear();
            entryMap.clear();
            listMap.clear();
        }
        if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
            basicMatchingEducationList.stream().forEach(oo -> {
                integerStringMap.put(oo.getId(), oo.getDistance() != null ? oo.getDistance().toString() : "0");
            });
            entryMap = generateCommonMethod.getGroupByDistance(integerStringMap);
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
                String s = this.getDistanceDec(String.format("%s%s", "教育", ":"), listMap);
                stringArrayList.add(s);
                ;
            }
            integerStringMap.clear();
            entryMap.clear();
            listMap.clear();
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
                    BasicHouse basicHouse = generateBaseExamineService.getBasicHouse();
                    if (basicBuilding == null || basicHouse == null) {
                        continue;
                    }
                    stringBuffer.append(basicBuilding.getBuildingNumber()).append("栋");
                    BigDecimal bigDecimal = new BigDecimal(basicBuilding.getFloorCount() != null ? basicBuilding.getFloorCount() : 0);
                    stringBuffer.append(bigDecimal.toString()).append("层");
                    stringBuffer.append("建筑的第").append(basicHouse.getFloor()).append("层");
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
            stringBuilder.append(title);
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
}
