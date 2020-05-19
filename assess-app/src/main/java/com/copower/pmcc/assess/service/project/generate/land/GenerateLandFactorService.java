package com.copower.pmcc.assess.service.project.generate.land;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.EnvironmentalScienceEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineEstateSupplyEnumType;
import com.copower.pmcc.assess.common.enums.basic.ExamineMatchingTrafficTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVillageVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingFinanceVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
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

import javax.websocket.RemoteEndpoint;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private BasicEstateVillageService basicEstateVillageService;
    @Autowired
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    @Autowired
    private BasicMatchingMaterialService basicMatchingMaterialService;
    @Autowired
    private BasicMatchingTrafficService basicMatchingTrafficService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicMatchingFinanceService basicMatchingFinanceService;
    @Autowired
    private BasicMatchingMedicalService basicMatchingMedicalService;
    @Autowired
    private BasicMatchingEducationService basicMatchingEducationService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;

    /**
     * 获取区域位置
     *
     * @return
     */
    public String getAreaLocation(BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        List<BasicEstateVillageVo> villageVoList = basicEstateVillageService.getVillageListByEstateId(basicEstate.getId());
        if (CollectionUtils.isNotEmpty(villageVoList)) {
            for (BasicEstateVillageVo basicEstateVillageVo : villageVoList) {
                if (StringUtils.isNotBlank(basicEstateVillageVo.getDistrictName()))
                    stringBuilder.append(basicEstateVillageVo.getDistrictName()).append(",");
            }
        }
        if (StringUtils.isNotBlank(basicEstate.getBlockName())) {
            stringBuilder.append(basicEstate.getBlockName()).append(";");
        }
        if (StringUtils.isNotBlank(basicEstate.getLocationDescribe())) {
            stringBuilder.append(basicEstate.getLocationDescribe());
        }
        return stringBuilder.toString();
    }

    /**
     * 获取产业聚集度
     *
     * @return
     */
    public String getAgglomerationDegree(BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        List<BasicMatchingLeisurePlace> placeList = basicMatchingLeisurePlaceService.getBasicMatchingLeisurePlaceList(basicEstate.getId());
        if (CollectionUtils.isNotEmpty(placeList)) {//购物、休闲娱乐、餐饮
            stringBuilder.append(generateLoactionService.getMatchingLeisurePlacePrivate(placeList, null, null, true));
        }
        Map<Integer, String> integerStringMap = Maps.newHashMap();
        Map<String, List<String>> listMap = Maps.newHashMap();
        List<BasicMatchingMaterial> materialList = basicMatchingMaterialService.getListByEstateId(basicEstate.getId());
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
        return stringBuilder.toString();
    }

    /**
     * 获取交通条件
     *
     * @return
     */
    public String getTrafficConditions(BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        List<BasicMatchingTrafficVo> basicMatchingTrafficList = basicMatchingTrafficService.getBasicMatchingTrafficVos(basicEstate.getId());
        //主干道
        if (StringUtils.isNotEmpty(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainRoad, "", false))) {
            stringBuilder.append(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainRoad, "", false)).append(";");
        }
        //交通枢纽
        stringBuilder.append(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.TrafficHub, "", false));
        //主要转换交通
        stringBuilder.append(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.MainConversion, "", false));
        //停车方便度
        stringBuilder.append(generateLoactionService.getParkingConvenience(basicEstateParkingService.getListByEstateId(basicEstate.getId())));
        //公交
        stringBuilder.append(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.TRANSIT, "", false));
        //地铁
        stringBuilder.append(generateLoactionService.getTrafficConditionsPrivate(basicMatchingTrafficList, ExamineMatchingTrafficTypeEnum.METRO, "", false));
        return stringBuilder.toString();
    }


    /**
     * 获取配套设施条件
     *
     * @return
     */
    public String getUpportingFacility(BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        if (StringUtils.isNotBlank(estateLandState.getDevelopmentDegreeContent())) {
            stringBuilder.append(basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState).getDevelopmentDegreeContentName());
        }
        String s = generateLoactionService.getFinanceAndMedicalAndEducation(basicMatchingFinanceService.getBasicMatchingFinanceList(basicEstate.getId()), null, null, null);
        if (StringUtils.isNotEmpty(s)) {
            stringBuilder.append(s).append(";");
        }
        s = generateLoactionService.getFinanceAndMedicalAndEducation(null, basicMatchingMedicalService.getBasicMatchingMedicalList(basicEstate.getId()), null, null);
        if (StringUtils.isNotEmpty(s)) {
            stringBuilder.append(s).append(";");
        }
        s = generateLoactionService.getFinanceAndMedicalAndEducation(null, null, basicMatchingEducationService.getBasicMatchingEducationList(basicEstate.getId()), null);
        if (StringUtils.isNotEmpty(s)) {
            stringBuilder.append(s).append(";");
        }
        return stringBuilder.toString();
    }

    /**
     * 获取环境条件
     *
     * @return
     */
    public String getEnvironmentCondition(BasicApplyBatch basicApplyBatch) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        String natural = generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.NATURAL);
        String humanity = generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.HUMANITY);
        String scenery = generateLoactionService.getEnvironmentalScience(basicApplyBatch, EnvironmentalScienceEnum.SCENERY);
        if (StringUtils.isNotBlank(natural))
            stringBuilder.append(String.format("自然要素:%s", generateCommonMethod.trim(natural)));
        if (StringUtils.isNotBlank(humanity))
            stringBuilder.append(String.format("人文环境要素:%s", generateCommonMethod.trim(humanity)));
        if (StringUtils.isNotBlank(scenery))
            stringBuilder.append(String.format("景观:%s", generateCommonMethod.trim(scenery)));
        return stringBuilder.toString();
    }

    /**
     * 获取规划条件
     *
     * @return
     */
    public String getPlanningCondition(BasicEstateLandCategoryInfo categoryInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        if (categoryInfo == null) return "";
        if (StringUtils.isNotEmpty(categoryInfo.getPlotRatio())) {
            stringBuilder.append(String.format("容积率:%s,", categoryInfo.getPlotRatio()));
        }
        if (StringUtils.isNotEmpty(categoryInfo.getBuildingDensity())) {
            stringBuilder.append(String.format("建筑密度:%s,", categoryInfo.getBuildingDensity()));
        }
        if (StringUtils.isNotEmpty(categoryInfo.getGreeningRate())) {
            stringBuilder.append(String.format("绿地率:%s,", categoryInfo.getGreeningRate()));
        }
        if (StringUtils.isNotEmpty(categoryInfo.getCompatibilityRate())) {
            stringBuilder.append(String.format("兼容比:%s,", categoryInfo.getCompatibilityRate()));
        }
        if (categoryInfo.getHeightPermitted() != null) {
            stringBuilder.append(String.format("建筑高度:%s", categoryInfo.getHeightPermitted()));
        }
        return stringBuilder.toString();
    }

    /**
     * -------------------------------------------------------------------------------个别因素
     */

    /**
     * 获取面积
     *
     * @return
     */
    public String getArea(BasicEstate basicEstate, SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder();
        if (basicEstate != null && basicEstate.getCoverAnArea() != null) {
            stringBuilder.append(String.format("证载面积%s平方米，", basicEstate.getCoverAnArea()));
        }
        if (schemeJudgeObject != null && schemeJudgeObject.getEvaluationArea() != null) {
            stringBuilder.append(String.format("评估面积%s平方米", schemeJudgeObject.getEvaluationArea()));
        }
        return StringUtils.strip(stringBuilder.toString(), ",");
    }

    /**
     * 获取用途
     *
     * @return
     */
    public String getUse(BasicEstateLandCategoryInfo categoryInfo, SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder();
        if (categoryInfo != null && categoryInfo.getLandUseType() != null) {
            stringBuilder.append(String.format("%s，", categoryInfo.getLandUseType()));
        }
        if (schemeJudgeObject != null && schemeJudgeObject.getSetUse() != null) {
            stringBuilder.append(String.format("%s,", dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse()).getName()));
        }
        return StringUtils.strip(stringBuilder.toString(), ",");
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
    public String getTopography(BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BasicEstateLandStateVo landState = basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId()));
        if (StringUtils.isNotBlank(landState.getShapeStateName()))
            stringBuilder.append(landState.getShapeStateName());
        if (StringUtils.isNotBlank(landState.getContaminatedName()))
            stringBuilder.append(String.format("土壤%s,", landState.getContaminatedName()));
        if (StringUtils.isNotBlank(landState.getPhName()))
            stringBuilder.append(String.format("%s，", landState.getPhName()));
        if (StringUtils.isNotBlank(landState.getHoldOnName()))
            stringBuilder.append(String.format("稳定性%s,", landState.getHoldOnName()));
        if (StringUtils.isNotBlank(landState.getBearingCapacityName()))
            stringBuilder.append(String.format("承载力%s,", landState.getBearingCapacityName()));
        if (StringUtils.isNotBlank(landState.getPlanenessName()))
            stringBuilder.append(String.format("地形%s,", landState.getPlanenessName()));
        if (StringUtils.isNotBlank(landState.getTopographicTerrainName()))
            stringBuilder.append(String.format("%s,", landState.getTopographicTerrainName()));
        return stringBuilder.toString();
    }

    /**
     * 获取容积率
     *
     * @return
     */
    public String getPlotRatio(SchemeJudgeObject schemeJudgeObject, BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getDataFromType()))
            stringBuilder.append(String.format("根据委托方提供的%s显示，", declareRecord.getDataFromType()));
        List<BasicEstateLandCategoryInfo> categoryInfos = basicEstateLandCategoryInfoService.getListByEstateId(basicEstate.getId());
        if (CollectionUtils.isNotEmpty(categoryInfos)) {
            for (BasicEstateLandCategoryInfo categoryInfo : categoryInfos) {
                if (StringUtils.isNotBlank(categoryInfo.getLandUseCategory()))
                    stringBuilder.append(String.format("%s其规划容积率%s,", categoryInfo.getLandUseCategory(), null));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取宗地开发程度
     *
     * @return
     */
    public String getDevelopmentLevel(BasicEstate basicEstate) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BasicEstateLandStateVo landState = basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId()));
        if (landState == null) return "";
        if (StringUtils.isNotBlank(landState.getInfrastructureCompletenessName()))
            stringBuilder.append(String.format("基础设施%s，", landState.getInfrastructureCompletenessName()));
        if (StringUtils.isNotBlank(landState.getDevelopmentDegreeName()))
            stringBuilder.append(String.format("%s", landState.getDevelopmentDegreeName()));
        return StringUtils.strip(stringBuilder.toString(), ",");
    }

    /**
     * 获取使用年限
     *
     * @return
     */
    public String getUsefulLife(SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder();
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        if (StringUtils.isNotBlank(declareRecord.getDataFromType()))
            stringBuilder.append(String.format("根据委托方提供的%s原件或复印件记载，", declareRecord.getDataFromType()));
        if (schemeJudgeObject.getLandUseEndDate() != null)
            stringBuilder.append(String.format("土地使用权终止日期为%s，", DateUtils.formatDate(schemeJudgeObject.getLandUseEndDate())));
        if (schemeJudgeObject.getLandRemainingYear() != null)
            stringBuilder.append(String.format("自估价基准日起剩余使用年期为%s年，", schemeJudgeObject.getLandRemainingYear()));
        return stringBuilder.toString();
    }

    /**
     * 获取土地权利状况
     *
     * @return
     */
    public String getRightCondition(SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder();
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        if (StringUtils.isNotBlank(declareRecord.getLandRightType()))
            stringBuilder.append(declareRecord.getLandRightType()).append(",");
        stringBuilder.append(declareRecord.getLandRightNature()).append(declareRecord.getLandCertUse());
        stringBuilder.append("用途开发用地");
        return stringBuilder.toString();
    }

    /**
     * 获取其它
     *
     * @return
     */
    public String getOther(SchemeJudgeObject schemeJudgeObject) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("宗地最有效利用方式为");
        if (schemeJudgeObject.getBestUse() != null)
            stringBuilder.append(dataBestUseDescriptionService.getBestUseDescriptionById(schemeJudgeObject.getBestUse()).getName());
        return stringBuilder.toString();
    }
}
