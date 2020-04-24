package com.copower.pmcc.assess.service.project.generate.land;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineEstateSupplyEnumType;
import com.copower.pmcc.assess.common.enums.basic.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingFinanceVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateBaseExamineService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateLandEntityService;
import com.copower.pmcc.assess.service.project.generate.GenerateLoactionService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @deprecated 土地报告获取一些数据
 * Created by kings on 2019-5-17.
 */
@Service
public class GenerateLandFactorService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private GenerateLoactionService generateLoactionService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private GenerateLandEntityService generateLandEntityService;

    /**
     * 获取区域位置
     *
     * @return
     */
    public String getAreaLocation(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService examineService = new GenerateBaseExamineService(basicApply);
            BasicEstate basicEstate = examineService.getEstate();
            String content = String.format("%s%s，%s", erpAreaService.getSysAreaName(basicEstate.getDistrict()), basicEstate.getBlockName(), basicEstate.getLocationDescribe());
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), content);
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取产业聚集度
     *
     * @return
     */
    public String getAgglomerationDegree(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService examineService = new GenerateBaseExamineService(basicApply);
            BasicEstate basicEstate = examineService.getEstate();
            stringBuilder.append(basicEstate.getName());
            List<BasicMatchingLeisurePlace> placeList = examineService.getBasicMatchingLeisurePlaceList();
            if (CollectionUtils.isNotEmpty(placeList)) {//购物、休闲娱乐、餐饮
                stringBuilder.append(generateLoactionService.getMatchingLeisurePlacePrivate(placeList, null, null, true));
            }

            List<BasicMatchingMaterial> materialList = examineService.getBasicMatchingMaterialList();
            if (CollectionUtils.isNotEmpty(materialList)) {//原料供应及销售条件
                integerStringMap.clear();
                listMap.clear();
                for (BasicMatchingMaterial material : materialList) {
                    integerStringMap.put(material.getDistance() == null ? 0 : material.getDistance(), material.getName());
                }
                Map<String, List<Integer>> distance = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!distance.isEmpty()) {
                    distance.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> materialList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    stringBuilder.append(generateLoactionService.getDistanceDec(null, listMap));
                }
            }
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取交通条件
     *
     * @return
     */
    public String getTrafficConditions(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        StringBuilder stringBuilder = new StringBuilder(8);
        List<String> stringList = Lists.newArrayList();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicMatchingTrafficVo> basicMatchingTrafficList = generateBaseExamineService.getBasicMatchingTrafficList();

            //主干道
            if (StringUtils.isNotEmpty(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainRoad, "", false))) {
                stringBuilder.append(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainRoad, "", false)).append(";");
            }

            //交通枢纽
            stringList.add(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.TrafficHub, "", false));
            //主要转换交通
            stringList.add(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainConversion, "", false));
            //停车方便度
            stringList.add(generateLoactionService.getParkingConvenience(generateBaseExamineService.getBasicEstateParkingList()));
            {
                String s = StringUtils.join(stringList.stream().filter(s1 -> StringUtils.isNotEmpty(s1)).collect(Collectors.toSet()), "、");
                if (StringUtils.isNotEmpty(s)) {
                    stringBuilder.append(s).append(";");
                }
            }
            stringList.clear();

            //公交
            stringList.add(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.TRANSIT, "", false));
            //地铁
            stringList.add(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.METRO, "", false));
            {
                String s = StringUtils.join(stringList.stream().filter(s1 -> StringUtils.isNotEmpty(s1)).collect(Collectors.toSet()), "、");
                if (StringUtils.isNotEmpty(s)) {
                    stringBuilder.append(s).append(";");
                }
            }
            stringList.clear();

            if (StringUtils.isNotEmpty(stringBuilder.toString().trim())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            }
            stringBuilder.delete(0, stringBuilder.toString().length());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }


    /**
     * 获取配套设施条件
     *
     * @return
     */
    public String getUpportingFacility(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        List<String> strings = Lists.newArrayList();
        StringBuilder stringBuilder = new StringBuilder(8);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicEstateSupply> basicEstateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
            List<BasicMatchingFinanceVo> basicMatchingFinanceVoList = generateBaseExamineService.getBasicMatchingFinanceList();
            List<BasicMatchingMedical> basicMatchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
            List<BasicMatchingEducation> basicMatchingEducationList = generateBaseExamineService.getBasicMatchingEducatioListn();
            //工业
            if (basicApply.getType().intValue() == BasicApplyTypeEnum.RESIDENCE.getId().intValue()) {
                BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
                if (StringUtils.isNotBlank(landStateVo.getDevelopmentDegreeContentName())) {
                    stringBuilder.append(landStateVo.getDevelopmentDegreeContentName());
                }
            }
            //非工业
            if (basicApply.getType().intValue() == BasicApplyTypeEnum.INDUSTRY.getId().intValue()) {
                boolean estateSupplyGas = false;
                boolean estateSupplyWater = false;
                boolean estateSupplyPower = false;
                if (CollectionUtils.isNotEmpty(basicEstateSupplyList)) {
                    estateSupplyGas = basicEstateSupplyList.stream().anyMatch(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName(), basicEstateSupply.getType()));
                    estateSupplyWater = basicEstateSupplyList.stream().anyMatch(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName(), basicEstateSupply.getType()));
                    estateSupplyPower = basicEstateSupplyList.stream().anyMatch(basicEstateSupply -> Objects.equal(ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName(), basicEstateSupply.getType()));
                }
                if (estateSupplyWater) {
                    strings.add("通水");
                }
                if (estateSupplyPower) {
                    strings.add("通电");
                }
                if (estateSupplyGas) {
                    strings.add("通气");
                }
                if (CollectionUtils.isNotEmpty(strings)) {
                    //三通
                    stringBuilder.append(StringUtils.join(strings, "、"));
                }
                strings.clear();
            }
            {
                String s = generateLoactionService.getFinanceAndMedicalAndEducation(basicMatchingFinanceVoList, null, null, null);
                if (StringUtils.isNotEmpty(s)) {
                    strings.add(s);
                }
            }
            {
                String s = generateLoactionService.getFinanceAndMedicalAndEducation(null, basicMatchingMedicalList, null, null);
                if (StringUtils.isNotEmpty(s)) {
                    strings.add(s);
                }
            }
            {
                String s = generateLoactionService.getFinanceAndMedicalAndEducation(null, null, basicMatchingEducationList, null);
                if (StringUtils.isNotEmpty(s)) {
                    strings.add(s);
                }
            }
            if (CollectionUtils.isNotEmpty(strings)) {
                stringBuilder.append(StringUtils.join(strings, "、"));
                strings.clear();
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString().trim())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            }
            stringBuilder.delete(0, stringBuilder.toString().length());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取环境条件
     *
     * @return
     */
    public String getEnvironmentCondition(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        StringBuilder stringBuilder = new StringBuilder(8);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApplyBatch basicApplyBatch = generateCommonMethod.getBasicApplyBatchBySchemeJudgeObject(schemeJudgeObject);
            if (basicApplyBatch == null || basicApplyBatch.getId() == 0) {
                continue;
            }
            String natural = generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.NATURAL);
            String humanity = generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.HUMANITY);
            String scenery = generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.SCENERY);

            stringBuilder.append(String.format("自然要素:%s", generateCommonMethod.trim(natural)));
            stringBuilder.append(String.format("人文环境要素:%s", generateCommonMethod.trim(humanity)));
            stringBuilder.append(String.format("景观:%s", generateCommonMethod.trim(scenery)));
            if (StringUtils.isNotEmpty(stringBuilder.toString().trim())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            }
            stringBuilder.delete(0, stringBuilder.toString().length());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取规划条件
     *
     * @return
     */
    public String getPlanningCondition(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        StringBuilder stringBuilder = new StringBuilder(8);
        List<String> stringList = Lists.newArrayList();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo landState = generateBaseExamineService.getBasicEstateLandState();
            if (landState == null || landState.getId() == null || landState.getId() == 0) {
                continue;
            }
            if (StringUtils.isNotEmpty(landState.getPlotRatio())) {
                stringList.add(String.format("容积率:%s", landState.getPlotRatio()));
            }
            if (StringUtils.isNotEmpty(landState.getBuildingDensity())) {
                stringList.add(String.format("建筑密度:%s", landState.getBuildingDensity()));
            }
            if (StringUtils.isNotEmpty(landState.getGreenSpaceRate())) {
                stringList.add(String.format("绿地率:%s", landState.getGreenSpaceRate()));
            }
            if (StringUtils.isNotEmpty(landState.getCompatibleRatio())) {
                stringList.add(String.format("兼容比:%s", landState.getCompatibleRatio()));
            }
            if (landState.getBuildingHeightLimit() != null) {
                stringList.add(String.format("建筑高度:%s", landState.getBuildingHeightLimit().toString()));
            }
            if (CollectionUtils.isNotEmpty(stringList)) {
                stringBuilder.append(StringUtils.join(stringList, "、"));
            }
            stringList.clear();
            if (StringUtils.isNotEmpty(stringBuilder.toString().trim())) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            }
            stringBuilder.delete(0, stringBuilder.toString().length());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * -------------------------------------------------------------------------------个别因素
     */

    /**
     * 获取面积
     *
     * @return
     */
    public String getArea(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("证载面积%s平方米，", schemeJudgeObject.getFloorArea()));
            stringBuilder.append(String.format("评估面积%s平方米", schemeJudgeObject.getEvaluationArea()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取用途
     *
     * @return
     */
    public String getUse(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("证载用途%s，", schemeJudgeObject.getLandCertUse()));
            stringBuilder.append(String.format("实际用途%s", schemeJudgeObject.getLandPracticalUse()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取临街（路）状况
     *
     * @return
     */
    public String getTemporaryRoadCondition(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        return generateLoactionService.getFaceStreet(judgeObjectList);
    }

    /**
     * 获取形状、地质、地形、地势
     *
     * @return
     */
    public String getTopography(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService examineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo landState = examineService.getBasicEstateLandState();
            stringBuilder.append(landState.getShapeStateName());
            stringBuilder.append(String.format("土壤%s", landState.getContaminatedName()));
            stringBuilder.append(String.format("、%s，", landState.getPhName()));
            stringBuilder.append(String.format("，稳定性%s", landState.getHoldOnName()));
            stringBuilder.append(String.format("，承载力%s", landState.getBearingCapacityName()));
            stringBuilder.append(String.format("，地形%s", landState.getPlanenessName()));
            stringBuilder.append(String.format("，%s", landState.getTopographicTerrainName()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取容积率
     *
     * @return
     */
    public String getPlotRatio(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            stringBuilder.append(String.format("根据委托方提供的%s显示，", declareRecord.getDataFromType()));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService examineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo landState = examineService.getBasicEstateLandState();
            stringBuilder.append(String.format("其规划容积率", landState.getPlotRatio()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取宗地开发程度
     *
     * @return
     */
    public String getDevelopmentLevel(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            GenerateBaseExamineService examineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo landState = examineService.getBasicEstateLandState();
            stringBuilder.append("截止估价基准日，根据估价人员现场查看，估价对象宗地内，");
            stringBuilder.append(String.format("基础设施%s，", landState.getInfrastructureCompletenessName()));
            stringBuilder.append(String.format("%s", landState.getDevelopmentDegree()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取使用年限
     *
     * @return
     */
    public String getUsefulLife(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            stringBuilder.append(String.format("根据委托方提供的%s原件或复印件记载，", declareRecord.getDataFromType()));
            stringBuilder.append(String.format("土地使用权终止日期为%s，", DateUtils.formatDate(schemeJudgeObject.getLandUseEndDate())));
            stringBuilder.append(String.format("自估价基准日起剩余使用年期为%s年，", schemeJudgeObject.getLandRemainingYear()));
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取土地权利状况
     *
     * @return
     */
    public String getRightCondition(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if ("国用".equals(declareRecord.getLandRightType()))
                stringBuilder.append("国有");
            if ("集用".equals(declareRecord.getLandRightType()))
                stringBuilder.append("集体");
            stringBuilder.append(declareRecord.getLandRightNature()).append(declareRecord.getLandCertUse());
            stringBuilder.append("用途开发用地");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取其它
     *
     * @return
     */
    public String getOther(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("宗地最有效利用方式为");
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if ("国用".equals(declareRecord.getLandRightType()))
                stringBuilder.append("国有");
            if ("集用".equals(declareRecord.getLandRightType()))
                stringBuilder.append("集体");
            if (schemeJudgeObject.getBestUse() != null)
                stringBuilder.append(dataBestUseDescriptionService.getBestUseDescriptionById(schemeJudgeObject.getBestUse()).getName());
            if (schemeJudgeObject.getSetUse() != null)
                stringBuilder.append(dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse()).getName());
            stringBuilder.append("用途开发用地");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }
}
