package com.copower.pmcc.assess.service.project.generate.land;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
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
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kings on 2019-5-17.
 */
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
                integerStringMap.clear();
                listMap.clear();
                for (BasicMatchingLeisurePlace place : placeList) {
                    integerStringMap.put(place.getDistance() == null ? 0 : place.getDistance(), place.getName());
                }
                Map<String, List<Integer>> distance = generateCommonMethod.getGroupByDistance(integerStringMap);
                if (!distance.isEmpty()) {
                    distance.entrySet().stream().forEach(entry -> {
                        List<String> stringList = entry.getValue().stream().map(po -> placeList.stream().filter(oo -> po.intValue() == oo.getId().intValue()).findFirst().get().getName()).collect(Collectors.toList());
                        listMap.put(entry.getKey(), stringList);
                    });
                }
                if (!listMap.isEmpty()) {
                    stringBuilder.append(generateLoactionService.getDistanceDec(null, listMap));
                }
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
    public String getTrafficConditions(List<SchemeJudgeObject> judgeObjectList) {

        return null;
    }

    /**
     * 获取配套设施条件
     *
     * @return
     */
    public String getUpportingFacility(List<SchemeJudgeObject> judgeObjectList) {
        return null;
    }

    /**
     * 获取环境条件
     *
     * @return
     */
    public String getEnvironmentCondition(List<SchemeJudgeObject> judgeObjectList) {
        return null;
    }

    /**
     * 获取规划条件
     *
     * @return
     */
    public String getPlanningCondition(List<SchemeJudgeObject> judgeObjectList) {
        return null;
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
    public String getTemporaryRoadCondition(List<SchemeJudgeObject> judgeObjectList) {

        return null;
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
            stringBuilder.append(String.format("土地使用权终止日期为%s，", schemeJudgeObject.getLandUseEndDate()));
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
            stringBuilder.append(dataBestUseDescriptionService.getBestUseDescriptionById(schemeJudgeObject.getBestUse()).getName());
            stringBuilder.append(dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse()).getName());
            stringBuilder.append("用途开发用地");
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }
}
