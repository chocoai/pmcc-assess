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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
     * @param judgeObjectIds
     * @return
     */
    public String getSeat(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Set<String> stringSet = Sets.newHashSet();
        List<Integer> integerArrayList = Lists.newArrayList(judgeObjectList.stream().map(oo -> generateCommonMethod.parseIntJudgeNumber(oo.getNumber())).collect(Collectors.toList()));
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                stringSet.add(declareRecord.getStreetNumber());
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            String s = "";
            if (stringSet.stream().distinct().count() > 1) {
                s = String.format("%s号", generateCommonMethod.convertNumber(integerArrayList));
            }
            return String.format("%s%s", s, StringUtils.join(stringSet, ","));
        }
        return error;
    }


    /**
     * 临街（路）状况
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getFaceStreet(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        Map<String, String> map = Maps.newHashMap();
        if (!houseMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : houseMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                for (int i = 0; i < integerList.size(); i++) {
                    final int intValue = integerList.get(i).intValue();
                    SchemeJudgeObject schemeJudgeObject = judgeObjectList.stream().filter(oo -> oo.getId().intValue() == intValue).findFirst().get();
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    if (basicApply == null || basicApply.getId() == 0) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicHouseFaceStreetVo> basicHouseFaceStreetVoList = generateBaseExamineService.getBasicHouseFaceStreetList();
                    if (CollectionUtils.isNotEmpty(basicHouseFaceStreetVoList)) {
                        basicHouseFaceStreetVoList.stream().forEach(basicHouseFaceStreetVo -> {
                            if (StringUtils.isNotEmpty(basicHouseFaceStreetVo.getPositionName())) {
                                if (StringUtils.isNotEmpty(basicHouseFaceStreetVo.getStreetName())) {
                                    stringLinkedHashSet.add(basicHouseFaceStreetVo.getPositionName());
                                    stringLinkedHashSet.add(basicHouseFaceStreetVo.getStreetName());
                                }
                            }
                        });
                    }
                }
                if (CollectionUtils.isNotEmpty(stringLinkedHashSet)) {
                    map.put(StringUtils.join(stringLinkedHashSet, "、"), String.format("%s号", stringEntry.getKey()));
                }
                stringLinkedHashSet.clear();
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\n";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            return s;
        }
        return error;
    }

    /**
     * 外部公共服务设施
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getExternalPublicServiceFacilities(List<Integer> judgeObjectIds) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstate basicEstate = generateBaseExamineService.getEstate();
            List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
            List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
            List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
            List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
            LinkedHashSet<String> linkedHashSetA = Sets.newLinkedHashSet();
            LinkedHashSet<String> linkedHashSetB = Sets.newLinkedHashSet();
            LinkedHashSet<String> linkedHashSetC = Sets.newLinkedHashSet();
            LinkedHashSet<String> linkedHashSetD = Sets.newLinkedHashSet();
            LinkedHashSet<String> linkedHashSetE = Sets.newLinkedHashSet();
            LinkedHashSet<String> linkedHashSetF = Sets.newLinkedHashSet();
            LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
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
                for (int i = 0; i < matchingMarketList.size(); i++) {
                    BasicMatchingLeisurePlace leisurePlace = matchingMarketList.get(i);
                    stringBuilder.append("购物商场距离").append(getExternalPublicServiceFacilitiesDistance(String.format("%s%s", baseDataDicService.getNameById(leisurePlace.getCategory()), leisurePlace.getName()), baseDataDicService.getNameById(leisurePlace.getDistance())));
                    linkedHashSetA.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
            if (CollectionUtils.isNotEmpty(matchingRestaurantList)) {
                for (int i = 0; i < matchingRestaurantList.size(); i++) {
                    BasicMatchingLeisurePlace leisurePlace = matchingRestaurantList.get(i);
                    stringBuilder.append("餐饮距离").append(getExternalPublicServiceFacilitiesDistance(String.format("%s%s", baseDataDicService.getNameById(leisurePlace.getCategory()), leisurePlace.getName()), baseDataDicService.getNameById(leisurePlace.getDistance())));
                    linkedHashSetB.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
            if (CollectionUtils.isNotEmpty(matchingRecreationList)) {
                for (int i = 0; i < matchingRecreationList.size(); i++) {
                    BasicMatchingLeisurePlace leisurePlace = matchingRecreationList.get(i);
                    stringBuilder.append("休闲娱乐距离").append(getExternalPublicServiceFacilitiesDistance(String.format("%s%s", baseDataDicService.getNameById(leisurePlace.getCategory()), leisurePlace.getName()), baseDataDicService.getNameById(leisurePlace.getDistance())));
                    linkedHashSetC.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
            if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
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
                    stringBuilder.append("金融距离").append(getExternalPublicServiceFacilitiesDistance(String.format("%s%s", baseDataDicService.getNameById(basicMatchingFinanceVo.getCategory()), basicMatchingFinanceVo.getName()), v));
                    linkedHashSetD.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
            if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
                for (int i = 0; i < basicMatchingMedicalList.size(); i++) {
                    BasicMatchingMedical basicMatchingMedical = basicMatchingMedicalList.get(i);
                    stringBuilder.append("医疗距离").append(getExternalPublicServiceFacilitiesDistance(String.format("%s%s", baseDataDicService.getNameById(basicMatchingMedical.getOrganizationLevel()), basicMatchingMedical.getOrganizationName()), baseDataDicService.getNameById(basicMatchingMedical.getDistance())));
                    linkedHashSetE.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
            if (CollectionUtils.isNotEmpty(basicMatchingEducationList)) {
                for (int i = 0; i < basicMatchingEducationList.size(); i++) {
                    BasicMatchingEducation basicMatchingEducation = basicMatchingEducationList.get(i);
                    stringBuilder.append("教育距离").append(getExternalPublicServiceFacilitiesDistance(String.format("%s%s", baseDataDicService.getNameById(basicMatchingEducation.getSchoolNature()), basicMatchingEducation.getSchoolName()), baseDataDicService.getNameById(basicMatchingEducation.getDistance())));
                    linkedHashSetF.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
            linkedHashSet.add(String.format("%s%s", basicEstate.getName(), StringUtils.join(linkedHashSetA, "，")));
            linkedHashSet.add(String.format("%s%s", basicEstate.getName(), StringUtils.join(linkedHashSetB, "，")));
            linkedHashSet.add(String.format("%s%s", basicEstate.getName(), StringUtils.join(linkedHashSetC, "，")));
            linkedHashSet.add(String.format("%s%s", basicEstate.getName(), StringUtils.join(linkedHashSetD, "，")));
            linkedHashSet.add(String.format("%s%s", basicEstate.getName(), StringUtils.join(linkedHashSetE, "，")));
            linkedHashSet.add(String.format("%s%s", basicEstate.getName(), StringUtils.join(linkedHashSetF, "，")));
            linkedHashSetA.clear();
            linkedHashSetB.clear();
            linkedHashSetC.clear();
            linkedHashSetD.clear();
            linkedHashSetE.clear();
            linkedHashSetF.clear();
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                map.put(StringUtils.join(linkedHashSet, "；"), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "。\r";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return error;
    }

    private String getExternalPublicServiceFacilitiesDistance(String name, String number) {
        number = generateCommonMethod.getNumber(number);
        if ("0".equals(number)) {
            return String.format("附件有%s", name);
        } else {
            return String.format("大约%s米有%s", number, name);
        }
    }

    /**
     * 环境
     *
     * @param judgeObjectIds
     * @param scienceEnum
     * @return
     * @throws Exception
     */
    public String getEnvironmentalScience(List<Integer> judgeObjectIds, EnvironmentalScienceEnum scienceEnum) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
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
                    stringBuilder.append(StringUtils.join(hashSet, "，"));
                    hashSet.clear();
                }
                //人文环境要素
                if (Objects.equal(scienceEnum.getKey(), EnvironmentalScienceEnum.HUMANITY.getKey())) {
                    environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "相邻利用物业状况", hashSet, basicMatchingEnvironmentVoList);
                    environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "居民特征", hashSet, basicMatchingEnvironmentVoList);
                    environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "治安状况", hashSet, basicMatchingEnvironmentVoList);
                    environmentalScience(EnvironmentalScienceEnum.HUMANITY, a, b, c, d, "相邻利用物业状况", hashSet, basicMatchingEnvironmentVoList);
                    stringBuilder.append(StringUtils.join(hashSet, "，"));
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
                    stringBuilder.append(StringUtils.join(hashSet, "，"));
                    hashSet.clear();
                }
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString().trim())) {
                map.put(stringBuilder.toString(), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
                stringBuilder.delete(0, stringBuilder.toString().length());
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\r";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return "";
    }

    private void environmentalScience(EnvironmentalScienceEnum scienceEnum, String a, String b, String c, String d, String key, LinkedHashSet<String> hashSet, List<BasicMatchingEnvironmentVo> basicMatchingEnvironmentVoList) {
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
        } else {
            hashSet.add(String.format("%s%s", key, matchingEnvironmentVo.getRemark()));
        }
    }

    /**
     * 综述
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String content(List<Integer> judgeObjectIds) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstate basicEstate = generateBaseExamineService.getEstate();
            String s = "";
            if (basicEstate != null) {
                DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
                if (dataBlock != null && StringUtils.isNotBlank(dataBlock.getRemark())) {
                    s = dataBlock.getRemark();
                }
            }
            if (StringUtils.isEmpty(s.trim())) {
                s = "区域内交通便捷度高、基础设施完备、属于成熟的区级中心商业区、医疗、教育、购物等公共配套设施完善，环境较好，" +
                        "区域内人流量大，居家生活便捷度高、商业聚集程度较高";
            }
            map.put(s, String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\r";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return error;
    }

    /**
     * 外部基础设施
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getExternalInfrastructure(List<Integer> judgeObjectIds) throws Exception {
        StringBuilder stringBuffer = new StringBuilder(8);
        stringBuffer.delete(0, stringBuffer.toString().length());
        Map<String, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
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
                        stringBuffer.append(v);
                    }
                });
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

                } else {
                    stringBuffer.append(stringBuilder.toString());
                }
            }
            map.put(stringBuffer.toString(), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
            stringBuffer.delete(0, stringBuffer.toString().length());
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\r";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return error;
    }


    /**
     * 道路状况
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getRoadCondition(List<Integer> judgeObjectIds) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        Map<String, String> map = Maps.newHashMap();
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
                        stringLinkedHashSet.add(basicHouseFaceStreetVo.getPositionName());
                    }
                    if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getStreetLevelName())) {
                        stringLinkedHashSet.add(basicHouseFaceStreetVo.getStreetLevelName());
                    }
                    if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getStreetName())) {
                        stringLinkedHashSet.add(basicHouseFaceStreetVo.getStreetName());
                    }
                    if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getTrafficFlowName())) {
                        linkedHashSet.add(String.format("交通流量%s", basicHouseFaceStreetVo.getTrafficFlowName()));
                    }
                    if (StringUtils.isNotBlank(basicHouseFaceStreetVo.getVisitorsFlowrateName())) {
                        linkedHashSet.add(String.format("人流量%s", basicHouseFaceStreetVo.getVisitorsFlowrateName()));
                    }
                    if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                        List<String> stringList = Lists.newArrayList(StringUtils.join(linkedHashSet, "、"), StringUtils.join(stringLinkedHashSet, "、"));
                        map.put(StringUtils.join(stringList, ","), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
                        linkedHashSet.clear();
                        stringLinkedHashSet.clear();
                    }
                }
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "。   ";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return error;
    }

    /**
     * 出入可利用的交通工具
     *
     * @return
     * @throws Exception
     */
    public String getAccessAvailableMeansTransport(List<Integer> judgeObjectIds) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        Map<String, String> map = Maps.newHashMap();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
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
                        stringBuffer.append("附近");
                    } else {
                        stringBuffer.append("距");
                        stringBuffer.append(basicMatchingTrafficVo.getName());
                        stringBuffer.append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                    }
                    stringBuffer.append("有").append("线路").append(basicMatchingTrafficVo.getTheLine());
                    linkedHashSet.add(stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.toString().length());
                }
            }
            if (CollectionUtils.isNotEmpty(metroList)) {
                for (int i = 0; i < metroList.size(); i++) {
                    BasicMatchingTrafficVo basicMatchingTrafficVo = metroList.get(i);
                    String distance = generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName());
                    if ("0".equals(distance)) {
                        stringBuffer.append("附近");
                    } else {
                        stringBuffer.append("距");
                        stringBuffer.append(basicMatchingTrafficVo.getName());
                        stringBuffer.append(String.format("大约%s米", generateCommonMethod.getNumber(basicMatchingTrafficVo.getDistanceName())));
                    }
                    stringBuffer.append("有").append("线路").append(basicMatchingTrafficVo.getTheLine());
                    stringLinkedHashSet.add(stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.toString().length());
                }
            }
            List<String> stringList = Lists.newArrayList(StringUtils.join(linkedHashSet, "、"), StringUtils.join(stringLinkedHashSet, "、"));
            if (CollectionUtils.isNotEmpty(stringList)) {
                if (stringList.stream().filter(s -> StringUtils.isNotEmpty(s)).count() >= 1) {
                    map.put(StringUtils.join(stringList, "。\r"), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
                }
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "\r";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return "";
    }

    /**
     * 停车方便度
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getParkingConvenience(List<Integer> judgeObjectIds) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        Map<String, String> map = Maps.newHashMap();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicEstateParking> basicEstateParkingList = generateBaseExamineService.getBasicEstateParkingList();
            if (CollectionUtils.isNotEmpty(basicEstateParkingList)) {
                List<BasicEstateParking> commonParkingList = basicEstateParkingList.stream().filter(basicEstateParking -> !Objects.equal("自有车位", baseDataDicService.getNameById(basicEstateParking.getParkingEstate()))).collect(Collectors.toList());
                List<BasicEstateParking> mySelfParkingList = Lists.newArrayList(CollectionUtils.subtract(basicEstateParkingList, commonParkingList));
                if (CollectionUtils.isNotEmpty(commonParkingList)) {
                    commonParkingList.stream().forEach(basicEstateParking -> {
                        if (basicEstateParking.getNumber() != null) {
                            stringBuffer.append(StringUtils.isNotBlank(basicEstateParking.getName()) ? basicEstateParking.getName() : "").append("有");
                            stringBuffer.append(basicEstateParking.getNumber()).append("辆车位");
                            linkedHashSet.add(stringBuffer.toString());
                            stringBuffer.delete(0, stringBuffer.toString().length());
                        }
                    });
                }
                if (CollectionUtils.isNotEmpty(mySelfParkingList)) {
                    mySelfParkingList.stream().forEach(basicEstateParking -> {
                        if (basicEstateParking.getNumber() != null) {
                            stringBuffer.append(baseDataDicService.getNameById(basicEstateParking.getLocation())).append("位置处");
                            String v = baseDataDicService.getNameById(basicEstateParking.getParkingType());
                            stringBuffer.append(StringUtils.isNotBlank(v) ? v : "停车场类型无").append("有");
                            stringBuffer.append(basicEstateParking.getNumber()).append("辆车位");
                            stringLinkedHashSet.add(stringBuffer.toString());
                            stringBuffer.delete(0, stringBuffer.toString().length());
                        }
                    });
                }
            }
            List<String> stringList = Lists.newArrayList(StringUtils.join(stringLinkedHashSet, ";"), StringUtils.join(linkedHashSet, ";"));
            if (CollectionUtils.isNotEmpty(stringList)) {
                if (stringList.stream().filter(s -> StringUtils.isNotEmpty(s)).count() >= 1) {
                    map.put(StringUtils.join(stringList.stream().filter(s -> StringUtils.isNotEmpty(s)).collect(Collectors.toList()), "")
                            ,
                            String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
                }
            }
            stringLinkedHashSet.clear();
            linkedHashSet.clear();
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\n";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return error;
    }

    /**
     * 交通收费情况
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getTrafficCharges(List<Integer> judgeObjectIds) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        Map<String, String> map = Maps.newHashMap();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList().stream().filter(basicMatchingTrafficVo -> {
                if (StringUtils.isNotBlank(basicMatchingTrafficVo.getCostStandard())) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(basicMatchingTrafficList)) {
                basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                    stringBuffer.append(basicMatchingTrafficVo.getName()).append(basicMatchingTrafficVo.getCostStandard());
                    linkedHashSet.add(stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.toString().length());
                });
            }
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                map.put(StringUtils.join(linkedHashSet, "、"), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
                linkedHashSet.clear();
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\r";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return "";
    }

    /**
     * 交通管制情况
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getTrafficControl(List<Integer> judgeObjectIds) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        Map<String, String> map = Maps.newHashMap();
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
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
                stringBuffer.append("限行情况:");
                basicMatchingTrafficList.stream().forEach(basicMatchingTrafficVo -> {
                    stringBuffer.append(basicMatchingTrafficVo.getLimitSpeed()).append(basicMatchingTrafficVo.getLimitTime()).append(basicMatchingTrafficVo.getLimitSpeialName()).append(";");
                });
                map.put(stringBuffer.toString(), String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
                stringBuffer.delete(0, stringBuffer.toString().length());
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；\n";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return "";
    }


    /**
     * 与重要场所的距离
     *
     * @return
     */
    public String getWithImportantLocationDistance(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        List<String> centerSet = Lists.newArrayList();
        LinkedHashSet<String> setMedical = Sets.newLinkedHashSet();
        LinkedHashSet<String> setTrafficHub = Sets.newLinkedHashSet();
        LinkedHashSet<String> setMatchingTrafficVo = Sets.newLinkedHashSet();
        LinkedHashSet<String> setLeisurePlace = Sets.newLinkedHashSet();
        LinkedHashSet<String> setFinanceVo = Sets.newLinkedHashSet();
        Map<String, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
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
                    return true;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(trafficHubList)) {
                    for (int i = 0; i < trafficHubList.size(); i++) {
                        BasicMatchingTrafficVo basicMatchingTrafficVo = trafficHubList.get(i);
                        setTrafficHub.add(String.format("%s", getDistance(basicMatchingTrafficVo.getName(), basicMatchingTrafficVo.getDistanceName()), ""));
                    }
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
                        setMatchingTrafficVo.add(String.format("%s", getDistance(basicMatchingTrafficVo.getName(), basicMatchingTrafficVo.getDistanceName()), ""));
                    }
                }
            }
            //购物商场
            if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
                basicMatchingLeisurePlaceList = basicMatchingLeisurePlaceList.stream().filter(leisurePlace -> {
                    return true;
                }).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(basicMatchingLeisurePlaceList)) {
                    for (int i = 0; i < basicMatchingLeisurePlaceList.size(); i++) {
                        BasicMatchingLeisurePlace leisurePlace = basicMatchingLeisurePlaceList.get(i);
                        if (leisurePlace.getDistance() != null) {
                            setLeisurePlace.add(String.format("%s", getDistance(leisurePlace.getName(), baseDataDicService.getNameById(leisurePlace.getDistance())), ""));
                        }
                    }
                }
            }
            //金融服务
            if (CollectionUtils.isNotEmpty(basicMatchingFinanceVoList)) {
                for (int i = 0; i < basicMatchingFinanceVoList.size(); i++) {
                    BasicMatchingFinanceVo basicMatchingFinanceVo = basicMatchingFinanceVoList.get(i);
                    setFinanceVo.add(String.format("%s", getDistance(basicMatchingFinanceVo.getName(), basicMatchingFinanceVo.getDistanceName()), ""));
                }
            }
            //医疗
            if (CollectionUtils.isNotEmpty(basicMatchingMedicalList)) {
                for (int i = 0; i < basicMatchingMedicalList.size(); i++) {
                    BasicMatchingMedical basicMatchingMedical = basicMatchingMedicalList.get(i);
                    if (basicMatchingMedical.getDistance() != null) {
                        setMedical.add(String.format("%s", getDistance(basicMatchingMedical.getOrganizationName(), baseDataDicService.getNameById(basicMatchingMedical.getDistance())), ""));
                    }
                }
            }
            centerSet.add(StringUtils.join(setTrafficHub, ","));
            centerSet.add(StringUtils.join(setFinanceVo, ","));
            centerSet.add(StringUtils.join(setMatchingTrafficVo, ","));
            centerSet.add(StringUtils.join(setLeisurePlace, ","));
            centerSet.add(StringUtils.join(setMedical, ","));
            setTrafficHub.clear();
            setFinanceVo.clear();
            setMatchingTrafficVo.clear();
            setLeisurePlace.clear();
            setMedical.clear();
            centerSet = centerSet.stream().filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(centerSet)) {
                String s = String.format("%s%s", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), "号");
                map.put(StringUtils.join(centerSet, ";"), s);
            }
            centerSet.clear();
        }
        String s = " ";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), "。\r");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, ""), "。\r");
            }
            s = generateCommonMethod.trim(s);
            return s;
        }
        return error;
    }


    /**
     * 朝向
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getOrientation(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            String s = "";
            try {
                s = baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getOrientation());
            } catch (Exception e1) {
                logger.error("异常 com.copower.pmcc.assess.service.project.generate.GenerateLoactionService.getOrientation", e1);
            }
            if (StringUtils.isNotBlank(s)) {
                map.put(s, String.format("%s号", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())));
            }
        }
        if (!map.isEmpty()) {
            String s = "";
            String separator = "；";
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), separator);
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), separator);
            }
            if (StringUtils.isNotEmpty(s.trim())) {
                return s;
            }
        }
        return error;
    }


    /**
     * 方位
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getPosition(List<Integer> judgeObjectIds) throws Exception {
        stringBuffer.delete(0, stringBuffer.toString().length());
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Set<String> stringSet = Sets.newHashSet();
        List<Integer> integerArrayList = Lists.newArrayList(judgeObjectList.stream().map(oo -> generateCommonMethod.parseIntJudgeNumber(oo.getNumber())).collect(Collectors.toList()));
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateVo basicEstate = basicEstateService.getBasicEstateVo(generateBaseExamineService.getEstate());
            if (basicEstate != null) {
                stringBuffer.append(basicEstate.getProvinceName()).append(basicEstate.getCityName()).append(basicEstate.getDistrictName());
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
            stringSet.add(stringBuffer.toString());
            stringBuffer.delete(0, stringBuffer.toString().length());
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            String s = "";
            if (stringSet.size() > 1) {
                s = String.format("%s号", generateCommonMethod.convertNumber(integerArrayList));
            }
            return String.format("%s%s", s, StringUtils.join(stringSet, ","));
        }
        return error;
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (int i = 0; i < judgeObjectList.size(); i++) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(i);
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply != null) {
                    if (judgeObjectList.size() != 1) {
                        stringBuffer.append(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()));
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
                    linkedHashSet.add(stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.toString().length());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(linkedHashSet)) {
            return StringUtils.join(linkedHashSet, "。");
        }
        return error;
    }

    private String getDistance(String name, String number) {
        number = generateCommonMethod.getNumber(number);
        if ("0".equals(number)) {
            return String.format("附近有%s", name);
        } else {
            return String.format("距%s大约%s米", name, number);
        }
    }

    /**
     * 估价对象按楼栋分组
     *
     * @param judgeObjectList
     * @return
     */
    private Map<String, List<Integer>> groupByBuilding(List<SchemeJudgeObject> judgeObjectList) {
        Map<String, List<Integer>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
            String key = basicBuilding.getBuildingNumber();
            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                list.add(schemeJudgeObject.getId());
            } else {
                map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
            }
        }
        return map;
    }

    /**
     * 估价对象按房屋分组
     *
     * @param judgeObjectList
     * @return
     */
    private Map<String, List<Integer>> groupByHouse(List<SchemeJudgeObject> judgeObjectList) {
        Map<String, List<Integer>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicHouse oo = generateBaseExamineService.getBasicHouse();
            if (oo == null) {
                continue;
            }
            try {
                String key = String.format("%s栋%s单元%s", generateBaseExamineService.getBasicBuilding().getBuildingNumber(), generateBaseExamineService.getBasicUnit().getUnitNumber(), oo.getHouseNumber());
                if (map.containsKey(key)) {
                    List<Integer> list = map.get(key);
                    list.add(schemeJudgeObject.getId());
                } else {
                    map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
                }
            } catch (Exception e) {
            }
        }
        return map;
    }

    /**
     * 估价对象按单元分组
     *
     * @param judgeObjectList
     * @return
     */
    private Map<String, List<Integer>> groupByUnit(List<SchemeJudgeObject> judgeObjectList) {
        Map<String, List<Integer>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            try {
                BasicUnit oo = generateBaseExamineService.getBasicUnit();
                if (oo == null) {
                    continue;
                }
                String key = String.format("%s栋%s", generateBaseExamineService.getBasicBuilding().getBuildingNumber(), generateBaseExamineService.getBasicUnit().getUnitNumber());
                if (map.containsKey(key)) {
                    List<Integer> list = map.get(key);
                    list.add(schemeJudgeObject.getId());
                } else {
                    map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
                }
            } catch (Exception e) {
            }
        }
        return map;
    }
}
