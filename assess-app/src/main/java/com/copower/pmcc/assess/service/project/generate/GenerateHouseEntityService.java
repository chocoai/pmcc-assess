package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 房屋实体信息
 */
@Service
public class GenerateHouseEntityService {
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BasicBuildingSurfaceService basicBuildingSurfaceService;
    @Autowired
    private BasicBuildingMaintenanceService basicBuildingMaintenanceService;

    //物业这块无需处理


    /**
     * 获取建造年份
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingYear(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            Date beCompletedTime = basicBuilding.getBeCompletedTime();
            int year = DateUtils.getYear(beCompletedTime);
            map.put(schemeJudgeObject.getId(), String.format("%s年", year));
        }
        return generateCommonMethod.judgeSummaryDesc(map, "分别为");
    }

    /**
     * 获取工程质量
     *
     * @param judgeObjectIds
     * @return
     */
    public String getConstructionQuality(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            Integer constructionQuality = basicBuilding.getConstructionQuality();
            if (constructionQuality != null && constructionQuality > 0) {
                map.put(schemeJudgeObject.getId(), baseDataDicService.getNameById(constructionQuality));
            }
        }
        return generateCommonMethod.judgeSummaryDesc(map, "分别为");
    }

    /**
     * 获取建筑结构
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingStructure(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        StringBuilder builder = new StringBuilder(8);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            if (basicBuilding != null) {
                if (basicBuilding.getBuildingStructureType() != null) {
                    builder.append(String.format("%s", baseDataDicService.getNameById(basicBuilding.getBuildingStructureType())));
                }
                if (basicBuilding.getBuildingStructureCategory() != null) {
                    builder.append(String.format("%s", baseDataDicService.getNameById(basicBuilding.getBuildingStructureCategory())));
                }
                map.put(schemeJudgeObject.getId(), builder.toString());
                builder.delete(0, builder.toString().length());
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";");
    }

    /**
     * 获取建筑规模
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingScale(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            StringBuilder builder = new StringBuilder();
            if (basicBuilding.getBuildingArea() != null)
                builder.append(String.format("建筑面积%s平方米，", basicBuilding.getBuildingArea()));
            if (basicBuilding.getInJacketArea() != null)
                builder.append(String.format("套内面积%s平方米，", basicBuilding.getInJacketArea()));
            if (basicBuilding.getUseArea() != null)
                builder.append(String.format("使用面积%s平方米，", basicBuilding.getUseArea()));
            if (basicBuilding.getBuildingHeight() != null)
                builder.append(String.format("建筑高度%s米，", basicBuilding.getBuildingHeight()));
            if (basicBuilding.getFirstFloor() != null)
                builder.append(String.format("首层%s至", basicBuilding.getFirstFloor()));
            if (basicBuilding.getMaxFloor() != null)
                builder.append(String.format("最高层%s是", basicBuilding.getMaxFloor()));
            if (basicBuilding.getPropertyType() != null)
                builder.append(String.format("%s，", baseDataDicService.getNameById(basicBuilding.getPropertyType())));
            if (basicBuilding.getFloorHeight() != null)
                builder.append(String.format("层高%s米", basicBuilding.getFloorHeight()));
            map.put(schemeJudgeObject.getId(), builder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";");
    }

    /**
     * 获取层高
     *
     * @param judgeObjectIds
     * @return
     */
    public String getFloorHeight(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BigDecimal floorHeight = basicBuilding.getFloorHeight();
            if (floorHeight != null) {
                map.put(schemeJudgeObject.getId(), String.format("%s米", floorHeight));
            }
        }
        return generateCommonMethod.judgeSummaryDesc(map, "分别为");
    }

    /**
     * 获取空间布局
     *
     * @param judgeObjectIds
     * @return
     */
    public String getSpatialDistribution(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        BaseDataDic production = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_UNIT_HUXING_TYPE_PRODUCTION);
        BaseDataDic office = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_UNIT_HUXING_TYPE_OFFICE);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            if (basicHouse != null && basicHouse.getHuxingId() != null && basicHouse.getHuxingId() > 0) {
                BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
                BasicUnitHuxing basicUnitHuxing = basicUnitHuxingService.getBasicUnitHuxingById(basicHouse.getHuxingId());
                if (basicUnitHuxing == null) continue;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.format("梯户比%s,", basicUnit.getElevatorHouseholdRatio()));
                if (basicUnitHuxing.getType().equals(production.getId())) {//办公商业取开间进深
                    stringBuilder.append(String.format("开间%s米,", basicUnitHuxing.getBay()));
                    stringBuilder.append(String.format("进深%s米,", basicUnitHuxing.getDeep()));
                } else if (basicUnitHuxing.getType().equals(office.getId())) {//工业仓储取跨长跨宽
                    stringBuilder.append(String.format("跨长%s米,", basicUnitHuxing.getSpanLength()));
                    stringBuilder.append(String.format("跨宽%s米,", basicUnitHuxing.getSpanWidth()));
                    stringBuilder.append(String.format("跨数%s米,", basicUnitHuxing.getSpanNumber()));
                } else {
                    stringBuilder.append(String.format("%s,", basicUnitHuxing.getName()));
                }
                if (StringUtils.isNotBlank(basicUnitHuxing.getDescription())) {
                    stringBuilder.append(basicUnitHuxing.getDescription());
                }
            }
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(map, "", ";"));
    }

    /**
     * 获取装饰装修
     *
     * @param judgeObjectIds
     * @return
     */
    public String getDecoration(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, List<Integer>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            String key = String.format("%s&%s", basicBuilding.getBuildingNumber(), basicUnit.getUnitNumber());
            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                list.add(schemeJudgeObject.getId());
            } else {
                map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            List<BasicBuildingOutfitVo> outfitVos = basicBuildingOutfitService.getBasicBuildingOutfitVos(basicBuilding.getId());
            StringBuilder outfitBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(outfitVos)) {
                outfitVos.forEach(o -> {
                    outfitBuilder.append(o.getDecorationPart())
                            .append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
            }
            stringBuilder.append(String.format("楼栋外装%s;", outfitBuilder));
            List<BasicUnitDecorateVo> unitDecorates = basicUnitDecorateService.getBasicUnitDecorateList(basicUnit.getId());
            StringBuilder unitDecorateBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(unitDecorates)) {
                unitDecorates.forEach(o -> {
                    unitDecorateBuilder.append(o.getDecorationPartName())
                            .append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
            }
            stringBuilder.append(String.format("楼栋内装%s;", outfitBuilder));
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 获取外观
     *
     * @param judgeObjectIds
     * @return
     */
    public String getAppearance(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, List<Integer>> map = groupByBuilding(judgeObjectList);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            stringBuilder.append(String.format("外观风格%s,", baseDataDicService.getNameById(basicBuilding.getAppearanceStyle())));
            stringBuilder.append(String.format("外观%s;", baseDataDicService.getNameById(basicBuilding.getAppearanceNewAndOld())));
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 其它
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getOther(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, List<Integer>> buildMap = groupByBuilding(judgeObjectList);
        if (!buildMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : buildMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                stringBuilder.append(stringEntry.getKey()).append("栋");
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                        continue;
                    }
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
                    if (basicBuilding != null && basicBuilding.getId() != null) {
                        BasicBuildingVo oo = basicBuildingService.getBasicBuildingVo(basicBuilding);
                        if (oo.getDataBuilder() != null) {
                            stringBuilder.append(oo.getDataBuilder().getName())
                                    .append(baseDataDicService.getNameById(oo.getDataBuilder().getCompanyNature()))
                                    .append(baseDataDicService.getNameById(oo.getDataBuilder().getQualificationGrade()))
                                    .append(baseDataDicService.getNameById(oo.getDataBuilder().getSocialPrestige()));
                        }
                        if (oo.getDataProperty() != null) {
                            stringBuilder.append(oo.getDataProperty().getName())
                                    .append(baseDataDicService.getNameById(oo.getDataProperty().getCompanyNature()))
                                    .append(baseDataDicService.getNameById(oo.getDataProperty().getSocialPrestige()));
                        }
                    }
                    if (i == integerList.size() - 1 && integerList.size() != 1) {
                        stringBuilder.append(";");
                    } else {
                        stringBuilder.append(",");
                    }
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无数据");
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 较好新旧程度及维护使用情况
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getThirteen(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, List<Integer>> buildMap = groupByBuilding(judgeObjectList);
        if (!buildMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : buildMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                stringBuilder.append(stringEntry.getKey()).append("栋");
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                        continue;
                    }
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicBuildingSurfaceVo> surfaceVoList = Lists.newArrayList();
                    List<BasicBuildingMaintenanceVo> maintenanceVoList = Lists.newArrayList();
                    List<BasicBuildingFunctionVo> functionVoList = Lists.newArrayList();
                    List<BasicBuildingOutfitVo> outfitVoList = generateBaseExamineService.getBasicBuildingOutfitList();
                    try {
                        surfaceVoList = generateBaseExamineService.getBasicBuildingSurfaceList().stream().map(oo -> basicBuildingSurfaceService.getBasicBuildingSurfaceVo(oo)).collect(Collectors.toList());
                        maintenanceVoList = generateBaseExamineService.getBasicBuildingMaintenanceList().stream().map(oo -> basicBuildingMaintenanceService.getBasicBuildingMaintenanceVo(oo)).collect(Collectors.toList());
                        functionVoList = generateBaseExamineService.getBasicBuildingFunctionList().stream().map(oo -> basicBuildingFunctionService.getBasicBuildingFunctionVo(oo)).collect(Collectors.toList());
                    } catch (Exception e) {
                    }
                    if (CollectionUtils.isNotEmpty(surfaceVoList)) {
                        for (int j = 0; j < surfaceVoList.size(); j++) {
                            BasicBuildingSurfaceVo oo = surfaceVoList.get(j);
                            if (StringUtils.isEmpty(oo.getStructureName()) || StringUtils.isEmpty(oo.getDescription())) {
                                continue;
                            }
                            stringBuilder.append(oo.getStructureName()).append(oo.getDescription());
                            if (j == surfaceVoList.size() - 1 && surfaceVoList.size() != 1) {
                                stringBuilder.append(";");
                            } else {
                                stringBuilder.append(",");
                            }
                        }
                    }
                    if (CollectionUtils.isNotEmpty(maintenanceVoList)) {
                        for (int j = 0; j < maintenanceVoList.size(); j++) {
                            BasicBuildingMaintenanceVo oo = maintenanceVoList.get(j);
                            if (StringUtils.isEmpty(oo.getTypeName()) || StringUtils.isEmpty(oo.getCategoryName())) {
                                continue;
                            }
                            if (StringUtils.isEmpty(oo.getMaterialQualityName())) {
                                continue;
                            }
                            stringBuilder.append(oo.getTypeName()).append(oo.getCategoryName()).append(oo.getMaterialQualityName());
                            if (j == maintenanceVoList.size() - 1 && maintenanceVoList.size() != 1) {
                                stringBuilder.append(";");
                            } else {
                                stringBuilder.append(",");
                            }
                        }
                    }
                    if (CollectionUtils.isNotEmpty(outfitVoList)) {
                        for (int j = 0; j < outfitVoList.size(); j++) {
                            BasicBuildingOutfitVo oo = outfitVoList.get(j);
                            if (StringUtils.isEmpty(oo.getDecorationPartName()) || StringUtils.isEmpty(oo.getDecoratingMaterialName())) {
                                continue;
                            }
                            if (StringUtils.isEmpty(oo.getConstructionTechnologyName())) {
                                continue;
                            }
                            stringBuilder.append(oo.getDecorationPartName())
                                    .append(oo.getDecoratingMaterialName())
                                    .append(oo.getMaterialPriceName())
                                    .append(oo.getConstructionTechnologyName());
                            if (j == outfitVoList.size() - 1 && outfitVoList.size() != 1) {
                                stringBuilder.append(";");
                            } else {
                                stringBuilder.append(",");
                            }
                        }
                    }
                }
                stringBuilder.append("。");
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("较好新旧程度及维护使用情况无数据");
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 建筑实体分析
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getContent(List<Integer> judgeObjectIds, SchemeAreaGroup schemeAreaGroup) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        StringBuilder stringBuilder = new StringBuilder(8);
        int size = judgeObjectList.size();
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (int i = 0; i < size; i++) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(i);
                if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                    continue;
                }
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply == null) {
                    continue;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                stringBuilder.append(schemeJudgeObject.getId()).append("号");
                BasicBuildingVo basicBuilding = basicBuildingService.getBasicBuildingVo(generateBaseExamineService.getBasicBuilding());
                stringBuilder
                        .append(basicBuilding.getBuildingStructureTypeName())
                        .append(basicBuilding.getBuildingStructureCategoryName());
                //建筑使用年限
                String value = null;
                if (StringUtils.isNotBlank(basicBuilding.getIndustryUseYearName())) {
                    value = basicBuilding.getIndustryUseYearName();
                }
                if (StringUtils.isNotBlank(basicBuilding.getResidenceUseYearName())) {
                    value = basicBuilding.getResidenceUseYearName();
                }
                if (StringUtils.isNotBlank(value) && schemeAreaGroup.getValueTimePoint() != null && basicBuilding.getBeCompletedTime() != null) {
                    //已使用年限 建筑使用年限
                    Date useDate = DateUtils.addYear(schemeAreaGroup.getValueTimePoint(), -DateUtils.getYear(basicBuilding.getBeCompletedTime()));
                    //经济耐用年限
                    double year = Double.parseDouble(generateCommonMethod.getNumber(value));
                    //已使用年限 建筑使用年限
                    double use = (double) DateUtils.getYear(useDate);
                    String text = "";
                    if (Objects.equal(basicBuilding.getBuildingStructureCategoryName(), "简易结构")) {
                        if (use <= year * 0.1) {
                            text = "使用时间短，";
                        }
                        if (use > year * 0.1 && use <= year * 0.2) {
                            text = "使用时间较短";
                        }
                        if (use <= year * 0.3 && use > year * 0.2) {
                            text = "使用时间较长";
                        }
                        if (use <= year * 0.5 && use > year * 0.3) {
                            text = "使用时间较长";
                        }
                        if (use > year * 0.5) {
                            text = "使用时间很长";
                        }
                    }
                    if (!Objects.equal(basicBuilding.getBuildingStructureCategoryName(), "简易结构")) {
                        if (use <= 2) {
                            text = "使用时间短";
                        }
                        if (use > 2 && use < 5) {
                            text = "使用时间不长";
                        }
                        if (use >= 5) {
                            text = "使用时间不长";
                        }
                    }
                    if (StringUtils.isNotBlank(text)) {
                        stringBuilder.append(text).append(",");
                    }
                }
                String contentA = null;
                List<BasicHouseEquipment> houseEquipmentList = generateBaseExamineService.getBasicHouseEquipmentList();
                //供水
                List<BasicHouseWater> basicHouseWaterList = generateBaseExamineService.getBasicHouseWaterList();
                //排水
                List<BasicHouseWaterDrain> houseWaterDrainList = generateBaseExamineService.getBasicHouseWaterDrainList();
                //电梯
                List<BasicUnitElevator> unitElevatorList = generateBaseExamineService.getBasicUnitElevatorList();
                //电力通讯网络
                List<BasicHouseIntelligentVo> intelligentVoList = generateBaseExamineService.getBasicHouseIntelligentList();
                List<BasicHouseEquipment> houseAirConditioner = Lists.newArrayList();
                List<BasicHouseEquipment> houseNewWind = Lists.newArrayList();
                List<BasicHouseEquipment> houseHeating = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(houseEquipmentList)) {
                    houseAirConditioner = houseEquipmentList.stream().filter(oo -> com.google.common.base.Objects.equal(ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey(), oo.getType())).collect(Collectors.toList());
                    houseNewWind = houseEquipmentList.stream().filter(oo -> com.google.common.base.Objects.equal(ExamineHouseEquipmentTypeEnum.houseNewWind.getKey(), oo.getType())).collect(Collectors.toList());
                    houseHeating = houseEquipmentList.stream().filter(oo -> com.google.common.base.Objects.equal(ExamineHouseEquipmentTypeEnum.houseHeating.getKey(), oo.getType())).collect(Collectors.toList());
                }
                //设备设施判断依据
                boolean elevator = unitElevatorList.size() == 0 ? false : true;
                boolean waterDrain = houseWaterDrainList.size() == 0 ? false : true;
                boolean water = basicHouseWaterList.size() == 0 ? false : true;
                boolean airConditioner = houseAirConditioner.size() == 0 ? false : true;
                boolean intelligent = intelligentVoList.size() == 0 ? false : true;
                boolean newWind = houseNewWind.size() == 0 ? false : true;
                boolean heating = houseHeating.size() == 0 ? false : true;
                if (!waterDrain || !water) {
                    contentA = "设备设施基础配置不完备";
                }
                if (water && waterDrain && elevator) {
                    contentA = "设备设施基础配置相对完备";
                }
                if (water && waterDrain && elevator && airConditioner) {
                    contentA = "设备设施基础配置比较完备";
                }
                if (water && waterDrain && elevator && airConditioner) {
                    if (intelligent || newWind || heating) {
                        contentA = "设备设施配置完备";
                    }
                }
                List<Boolean> booleans = Lists.newArrayList(intelligent, newWind, heating);
                if (booleans.stream().filter(b -> b.booleanValue()).count() >= 2) {
                    contentA = "设备设施配置非常完备";
                }
                //维护使用情况
                stringBuilder.append("维护使用情况无,");
                if (StringUtils.isNotBlank(contentA)) {
                    stringBuilder.append(contentA).append("。");
                }
                if (!Objects.equal(basicBuilding.getBuildingStructureCategoryName(), "简易结构")){
                    if (StringUtils.isNotBlank(contentA)) {
                        switch (contentA) {
                            case "设备设施基础配置不完备":
                                stringBuilder.append("相对不利于提升房产价值").append("。");
                                break;
                            case "设备设施基础配置相对完备":
                                stringBuilder.append("相对有利于提升房产价值").append("。");
                                break;
                            case "设备设施基础配置比较完备":
                                stringBuilder.append("相对比较利于提升房产价值").append("。");
                                break;
                            case "设备设施配置完备":
                                stringBuilder.append("对于提升房产价值有比较大的影响").append("。");
                                break;
                            case "设备设施配置非常完备":
                                stringBuilder.append("对于提升房产价值有比较大的影响").append("。");
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (i == size - 1 && size != 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        }

        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无数据");
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 房屋配套设备设施工
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getTenPointThree(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        if (!houseMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : houseMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                stringBuilder.append(stringEntry.getKey()).append("房号");
                int start = stringBuilder.toString().length();
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                        continue;
                    }
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    if (basicApply == null || basicApply.getType() == 0) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicHouseCorollaryEquipment> equipmentList = generateBaseExamineService.getBasicHouseCorollaryEquipmentList();
                    if (CollectionUtils.isEmpty(equipmentList)) {
                        continue;
                    }
                    int size = equipmentList.size();
                    for (int j = 0; j < size; j++) {
                        stringBuilder.append(equipmentList.get(j).getName());
                        stringBuilder.append(baseDataDicService.getNameById(equipmentList.get(j).getEquipmentUse()));
                        stringBuilder.append(baseDataDicService.getNameById(equipmentList.get(j).getCategory()));
                        if (j == size - 1 && size != 1) {
                            stringBuilder.append(";");
                        } else {
                            stringBuilder.append(",");
                        }
                    }
                }
                int end = stringBuilder.toString().length();
                if (start == end) {
                    stringBuilder.append("房屋配套设备设施无");
                }
                stringBuilder.append("。");
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("房屋配套设备设施无数据");
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 非工业与仓储的其他设施
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getTenPointTwo(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        if (!houseMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : houseMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                stringBuilder.append(stringEntry.getKey()).append("房号");
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                        continue;
                    }
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicHouseEquipment> equipmentList = generateBaseExamineService.getBasicHouseEquipmentList();
                    List<BasicHouseIntelligentVo> intelligentVoList = generateBaseExamineService.getBasicHouseIntelligentList();
                    //供水
                    List<BasicHouseWater> basicHouseWaterList = generateBaseExamineService.getBasicHouseWaterList();
                    //排水
                    List<BasicHouseWaterDrain> houseWaterDrainList = generateBaseExamineService.getBasicHouseWaterDrainList();
                    if (CollectionUtils.isEmpty(equipmentList) && CollectionUtils.isEmpty(intelligentVoList)
                            && CollectionUtils.isEmpty(basicHouseWaterList) && CollectionUtils.isEmpty(houseWaterDrainList)) {
                        continue;
                    }
                    if (CollectionUtils.isNotEmpty(equipmentList)) {
                        List<BasicHouseEquipment> houseAirConditioner = equipmentList.stream().filter(oo -> com.google.common.base.Objects.equal(ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey(), oo.getType())).collect(Collectors.toList());
                        List<BasicHouseEquipment> houseNewWind = equipmentList.stream().filter(oo -> com.google.common.base.Objects.equal(ExamineHouseEquipmentTypeEnum.houseNewWind.getKey(), oo.getType())).collect(Collectors.toList());
                        List<BasicHouseEquipment> houseHeating = equipmentList.stream().filter(oo -> com.google.common.base.Objects.equal(ExamineHouseEquipmentTypeEnum.houseHeating.getKey(), oo.getType())).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(houseAirConditioner)) {
                            int size = houseAirConditioner.size();
                            for (int j = 0; j < houseAirConditioner.size(); j++) {
                                BasicHouseEquipment oo = houseAirConditioner.get(j);
                                String categoryName = baseDataDicService.getNameById(oo.getCategory());
                                String gradeName = baseDataDicService.getNameById(oo.getGrade());
                                if (StringUtils.isEmpty(gradeName)) {
                                    gradeName = "档次无";
                                }
                                if (StringUtils.isEmpty(categoryName)) {
                                    categoryName = "类别无";
                                }
                                stringBuilder.append(StringUtils.isNotBlank(oo.getEquipment()) ? oo.getEquipment() : "无品牌");
                                stringBuilder.append(gradeName);
                                stringBuilder.append(categoryName);
                                stringBuilder.append(StringUtils.isNotBlank(oo.getSupplyWeast()) ? oo.getSupplyWeast() : "供应方式无");
                                if (j == size - 1 && size != 1) {
                                    stringBuilder.append(";");
                                } else {
                                    stringBuilder.append(",");
                                }
                            }
                        }
                        //
                        if (CollectionUtils.isNotEmpty(houseNewWind)) {
                            int size = houseNewWind.size();
                            for (int j = 0; j < houseNewWind.size(); j++) {
                                BasicHouseEquipment oo = houseNewWind.get(j);
                                String categoryName = baseDataDicService.getNameById(oo.getCategory());
                                String gradeName = baseDataDicService.getNameById(oo.getGrade());
                                if (StringUtils.isEmpty(gradeName)) {
                                    gradeName = "档次无";
                                }
                                if (StringUtils.isEmpty(categoryName)) {
                                    categoryName = "类别无";
                                }
                                stringBuilder.append(StringUtils.isNotBlank(oo.getEquipment()) ? oo.getEquipment() : "无品牌");
                                stringBuilder.append(gradeName);
                                stringBuilder.append(categoryName);
                                stringBuilder.append(StringUtils.isNotBlank(oo.getSupplyWeast()) ? oo.getSupplyWeast() : "供应方式无");
                                if (j == size - 1 && size != 1) {
                                    stringBuilder.append(";");
                                } else {
                                    stringBuilder.append(",");
                                }
                            }
                        }
                        //
                        if (CollectionUtils.isNotEmpty(houseHeating)) {
                            int size = houseHeating.size();
                            for (int j = 0; j < houseHeating.size(); j++) {
                                BasicHouseEquipment oo = houseHeating.get(j);
                                String categoryName = baseDataDicService.getNameById(oo.getCategory());
                                String gradeName = baseDataDicService.getNameById(oo.getGrade());
                                if (StringUtils.isEmpty(gradeName)) {
                                    gradeName = "档次无";
                                }
                                if (StringUtils.isEmpty(categoryName)) {
                                    categoryName = "类别无";
                                }
                                stringBuilder.append(StringUtils.isNotBlank(oo.getEquipment()) ? oo.getEquipment() : "无品牌");
                                stringBuilder.append(gradeName);
                                stringBuilder.append(categoryName);
                                stringBuilder.append(StringUtils.isNotBlank(oo.getSupplyWeast()) ? oo.getSupplyWeast() : "供应方式无");
                                if (j == size - 1 && size != 1) {
                                    stringBuilder.append(";");
                                } else {
                                    stringBuilder.append(",");
                                }
                            }
                        }
                        //电力通讯网络
                        if (CollectionUtils.isNotEmpty(intelligentVoList)) {
                            int size = intelligentVoList.size();
                            for (int j = 0; j < intelligentVoList.size(); j++) {
                                stringBuilder.append("电路采用");
                                stringBuilder.append(StringUtils.isNotBlank(intelligentVoList.get(j).getGradeName()) ? intelligentVoList.get(j).getGradeName() : "档次无").append("材料");
                                stringBuilder.append(StringUtils.isNotBlank(intelligentVoList.get(j).getSwitchCircuitName()) ? intelligentVoList.get(j).getSwitchCircuitName() : "开关回路无");
                                stringBuilder.append("铺设方式").append(StringUtils.isNotBlank(intelligentVoList.get(j).getLayingMethodName()) ? intelligentVoList.get(j).getLayingMethodName() : "无");
                                if (StringUtils.isNotBlank(intelligentVoList.get(j).getLampsLanternsName())) {
                                    stringBuilder.append(",");
                                    stringBuilder.append(intelligentVoList.get(j).getLampsLanternsName());
                                }
                                if (StringUtils.isNotBlank(intelligentVoList.get(j).getIntelligentSystemName())) {
                                    stringBuilder.append(",");
                                    stringBuilder.append(intelligentVoList.get(j).getIntelligentSystemName());
                                }
                                if (j == size - 1 && size != 1) {
                                    stringBuilder.append(";");
                                } else {
                                    stringBuilder.append("，");
                                }
                            }
                        }
                        if (CollectionUtils.isNotEmpty(basicHouseWaterList)) {
                            int size = basicHouseWaterList.size();
                            for (int j = 0; j < size; j++) {
                                BasicHouseWater basicHouseWater = basicHouseWaterList.get(j);
                                String gradeName = baseDataDicService.getNameById(basicHouseWater.getGrade());
                                if (StringUtils.isEmpty(gradeName)) {
                                    gradeName = "档次无";
                                }
                                String boosterEquipmentName = baseDataDicService.getNameById(basicHouseWater.getBoosterEquipment());
                                if (StringUtils.isEmpty(boosterEquipmentName)) {
                                    boosterEquipmentName = "给水升压设备无";
                                }
                                String pipeMaterialName = baseDataDicService.getNameById(basicHouseWater.getPipeMaterial());
                                if (StringUtils.isEmpty(pipeMaterialName)) {
                                    pipeMaterialName = "给水管材料无";
                                }
                                String pipingLayoutName = baseDataDicService.getNameById(basicHouseWater.getPipingLayout());
                                if (StringUtils.isEmpty(pipingLayoutName)) {
                                    pipingLayoutName = "给水管道布置无";
                                }
                                String supplyModeName = baseDataDicService.getNameById(basicHouseWater.getSupplyMode());
                                if (StringUtils.isEmpty(supplyModeName)) {
                                    supplyModeName = "给水方式无";
                                }
                                String fireWaterSupplyName = baseDataDicService.getNameById(basicHouseWater.getFireWaterSupply());
                                if (StringUtils.isEmpty(fireWaterSupplyName)) {
                                    fireWaterSupplyName = "给水方式无";
                                }
                                stringBuilder.append("给水采用")
                                        .append(gradeName)
                                        .append(boosterEquipmentName)
                                        .append(pipeMaterialName)
                                        .append(pipingLayoutName)
                                        .append(supplyModeName)
                                        .append(",")
                                        .append("消防给水")
                                        .append(fireWaterSupplyName);
                                if (j == size - 1 && size != 1) {
                                    stringBuilder.append(";");
                                } else {
                                    stringBuilder.append("，");
                                }
                            }
                        }
                        if (CollectionUtils.isNotEmpty(houseWaterDrainList)) {
                            int size = houseWaterDrainList.size();
                            for (int j = 0; j < size; j++) {
                                BasicHouseWaterDrain basicHouseWaterDrain = houseWaterDrainList.get(j);
                                String typeName = baseDataDicService.getNameById(basicHouseWaterDrain.getType());
                                if (StringUtils.isEmpty(typeName)) {
                                    typeName = "类别无";
                                }
                                String processingModeName = baseDataDicService.getNameById(basicHouseWaterDrain.getProcessingMode());
                                if (StringUtils.isEmpty(processingModeName)) {
                                    processingModeName = "排水处理方式无";
                                }
                                stringBuilder.append(typeName)
                                        .append(processingModeName)
                                        .append(",").append(StringUtils.isNotBlank(basicHouseWaterDrain.getEvaluate()) ? basicHouseWaterDrain.getEvaluate() : "无");
                                if (basicApply.getType() == 0) {
                                    String organizationName = baseDataDicService.getNameById(basicHouseWaterDrain.getOrganization());
                                    if (StringUtils.isEmpty(organizationName)) {
                                        organizationName = "体系无";
                                    }
                                    stringBuilder.append(organizationName);
                                }
                                if (j == size - 1 && size != 1) {
                                    stringBuilder.append(";");
                                } else {
                                    stringBuilder.append("，");
                                }
                            }
                        }
                    }
                }
                stringBuilder.append("。");
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无数据");
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 单元电梯
     *
     * @param judgeObjectIds
     * @return
     */
    public String getUnitElevator(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        StringBuilder stringBuilder = new StringBuilder(8);
        Map<String, List<Integer>> unitMap = groupByUnit(judgeObjectList);
        if (!unitMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : unitMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                stringBuilder.append(stringEntry.getKey()).append("单元");
                int start = stringBuilder.toString().length();
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                        continue;
                    }
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicUnitElevator> basicUnitElevatorList = generateBaseExamineService.getBasicUnitElevatorList().stream().filter(oo -> oo.getNumber() != null).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(basicUnitElevatorList)) {
                        continue;
                    }
                    for (int j = 0; j < basicUnitElevatorList.size(); j++) {
                        stringBuilder.append(basicUnitElevatorList.get(i).getNumber()).append("部");
                        String type = baseDataDicService.getNameById(basicUnitElevatorList.get(i).getType());
                        if (StringUtils.isEmpty(type)) {
                            type = "未知类型";
                        }
                        stringBuilder.append(type).append("电梯");
                        if (basicUnitElevatorList.get(i).getQuasiLoadNumber() != null) {
                            stringBuilder.append("准载人数").append(basicUnitElevatorList.get(i).getQuasiLoadNumber()).append("人");
                        }
                        if (StringUtils.isNotBlank(basicUnitElevatorList.get(i).getQuasiLoadWeight())) {
                            if (basicUnitElevatorList.get(i).getQuasiLoadNumber() != null) {
                                stringBuilder.append("、");
                            }
                            stringBuilder.append("准载重量").append(basicUnitElevatorList.get(i).getQuasiLoadWeight()).append("kg");
                        }
                        if (j == basicUnitElevatorList.size() - 1) {
                            if (j != 1) {
                                stringBuilder.append(";");
                            }
                        } else {
                            stringBuilder.append(",");
                        }
                    }
                }
                int end = stringBuilder.toString().length();
                if (start == end) {
                    stringBuilder.append("无电梯数据");
                }
                stringBuilder.append("。");
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无数据");
        }
        return generateCommonMethod.trim(stringBuilder.toString());
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
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
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
            String key = oo.getHouseNumber();
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
                String key = oo.getUnitNumber();
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
     * 获取建筑功能
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingFunction(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, List<Integer>> map = groupByBuilding(judgeObjectList);
        StringBuilder stringBuilder = new StringBuilder();
        //楼栋中的建筑功能按楼栋描述
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            List<BasicBuildingFunction> functionList = basicBuildingFunctionService.getBasicBuildingFunctionList(basicBuilding.getId());
            if (CollectionUtils.isNotEmpty(functionList)) {
                stringBuilder.append(basicBuilding.getBuildingName());
                for (int i = 0; i < functionList.size(); i++) {
                    BasicBuildingFunction function = functionList.get(i);
                    stringBuilder.append(baseDataDicService.getNameById(function.getDecorationPart()))
                            .append(baseDataDicService.getNameById(function.getType()));
                    stringBuilder.append(i == (functionList.size() - 1) ? "；" : "，");
                }
            }
        }
        //房间中采光等信息按估价对象分别描述
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            stringBuilder.append(String.format("%s%s", judgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());

            for (BasicHouseRoom room : roomList) {
                stringBuilder.append(baseDataDicService.getNameById(room.getRoomType()));
                Map<String, String> stringMap = Maps.newHashMap();
                stringMap.put("通风", room.getAeration());
                stringMap.put("日照", room.getSunshine());
                stringMap.put("采光", room.getLighting());
                stringMap.put("隔音", room.getSoundInsulation());
                stringBuilder.append(generateCommonMethod.stringSummaryDesc(stringMap, "", "，"));
            }
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

}
