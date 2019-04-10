package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
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
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
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
    @Autowired
    private BasicEstateService basicEstateService;

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
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s年", year));
        }
        return generateCommonMethod.judgeEachDesc(map, "","，", false);
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
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), baseDataDicService.getNameById(constructionQuality));
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "","，", false);
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
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), builder.toString());
                builder.delete(0, builder.toString().length());
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";", false);
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
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), builder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";", false);
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
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s米", floorHeight));
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "","，", false);
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
                stringBuilder.append(StringUtils.isBlank(basicUnit.getElevatorHouseholdRatio()) ? "" : String.format("梯户比%s,", basicUnit.getElevatorHouseholdRatio()));
                if (basicUnitHuxing.getType().equals(production.getId())) {//办公商业取开间进深
                    stringBuilder.append(basicUnitHuxing.getBay() != null ? "" : String.format("开间%s米,", basicUnitHuxing.getBay()));
                    stringBuilder.append(basicUnitHuxing.getDeep() != null ? "" : String.format("进深%s米,", basicUnitHuxing.getDeep()));
                } else if (basicUnitHuxing.getType().equals(office.getId())) {//工业仓储取跨长跨宽
                    stringBuilder.append(basicUnitHuxing.getSpanLength() != null ? "" : String.format("跨长%s米,", basicUnitHuxing.getSpanLength()));
                    stringBuilder.append(basicUnitHuxing.getSpanWidth() != null ? "" : String.format("跨宽%s米,", basicUnitHuxing.getSpanWidth()));
                    stringBuilder.append(basicUnitHuxing.getSpanNumber() != null ? "" : String.format("跨数%s米,", basicUnitHuxing.getSpanNumber()));
                } else {
                    stringBuilder.append(String.format("%s,", basicUnitHuxing.getName()));
                }
                if (StringUtils.isNotBlank(basicUnitHuxing.getDescription())) {
                    stringBuilder.append(basicUnitHuxing.getDescription());
                }
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            }
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(map, "", ";", false));
    }

    /**
     * 获取装饰装修
     *
     * @param judgeObjectIds
     * @return
     */
    public String getDecoration(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> outfitMap = Maps.newHashMap();
        Map<Integer, String> unitDecorateMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            //楼栋外装
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            List<BasicBuildingOutfitVo> outfitVos = basicBuildingOutfitService.getBasicBuildingOutfitVos(basicBuilding.getId());
            StringBuilder outfitBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(outfitVos)) {
                outfitVos.forEach(o -> {
                    outfitBuilder.append(o.getDecorationPart()).append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
                outfitMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("楼栋外装%s;", StringUtils.strip(outfitBuilder.toString(), ",")));
            }


            //楼栋内装
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            List<BasicUnitDecorateVo> unitDecorates = basicUnitDecorateService.getBasicUnitDecorateList(basicUnit.getId());
            StringBuilder unitDecorateBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(unitDecorates)) {
                unitDecorates.forEach(o -> {
                    unitDecorateBuilder.append(o.getDecorationPartName()).append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
                unitDecorateMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("楼栋内装%s;", StringUtils.strip(unitDecorateBuilder.toString(), ",")));
            }
        }
        String outfitString = generateCommonMethod.judgeEachDesc(outfitMap, "", ";", false);
        String unitDecorateString = generateCommonMethod.judgeEachDesc(unitDecorateMap, "", ";", false);
        StringBuilder resultBuilder = new StringBuilder(outfitString);
        if (StringUtils.isNotBlank(unitDecorateString))
            resultBuilder.append(";").append(unitDecorateString);
        return generateCommonMethod.trim(resultBuilder.toString());
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
        Map<Integer, String> stringMap = Maps.newHashMap();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            stringBuilder.append(String.format("外观风格%s,", baseDataDicService.getNameById(basicBuilding.getAppearanceStyle())));
            stringBuilder.append(String.format("外观%s;", baseDataDicService.getNameById(basicBuilding.getAppearanceNewAndOld())));
            stringMap.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), stringBuilder.toString());
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(stringMap, "号", ";", false));
    }

    /**
     * 其它
     *
     * @param judgeObjectIds
     * @return
     * @throws Exception
     */
    public String getOther(List<Integer> judgeObjectIds) throws Exception {
        LinkedHashSet<String> stringSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringList = Sets.newLinkedHashSet();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder(8);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
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
                    BasicEstateVo basicEstate = basicEstateService.getBasicEstateVo(generateBaseExamineService.getEstate());
                    if (basicBuilding != null && basicBuilding.getId() != null) {
                        BasicBuildingVo oo = basicBuildingService.getBasicBuildingVo(basicBuilding);
                        if (oo.getDataBuilder() != null) {
                            stringList.add(oo.getDataBuilder().getName());
                            stringList.add(oo.getDataBuilder().getCompanyNatureName());
                            stringList.add(StringUtils.isNotBlank(oo.getDataBuilder().getSocialPrestigeName()) ? String.format("社会信誉%s", oo.getDataBuilder().getSocialPrestigeName()) : "");
                            if (stringList.stream().filter(s -> StringUtils.isNotBlank(s)).count() == 3) {
                                stringBuffer.append(StringUtils.join(stringList, ""));
                            }
                            stringList.clear();
                        }
                    }
                    if (basicEstate != null && basicEstate.getId() != null) {
                        if (basicEstate.getDataDeveloper() != null) {
                            stringList.add(basicEstate.getDataDeveloper().getName());
                            stringList.add(basicEstate.getDataDeveloper().getCompanyNatureName());
                            stringList.add(StringUtils.isNotBlank(basicEstate.getDataDeveloper().getSocialPrestigeName()) ? String.format("社会信誉%s", basicEstate.getDataDeveloper().getSocialPrestigeName()) : "");
                            if (stringList.stream().filter(s -> StringUtils.isNotBlank(s)).count() == 3) {
                                stringBuffer.append(StringUtils.join(stringList, ""));
                            }
                            stringList.clear();
                        }
                    }
                    stringSet.add(stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.toString().length());
                }
                if (CollectionUtils.isNotEmpty(stringSet)) {
                    stringBuilder.append(StringUtils.join(stringSet, ","));
                    stringBuilder.append(";");
                    stringSet.clear();
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无");
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        Map<String, List<Integer>> buildMap = groupByHouse(judgeObjectList);
        if (!buildMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : buildMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                stringBuilder.append(stringEntry.getKey()).append("房:");
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
                    List<BasicHouseDamagedDegreeVo> degreeVoList = generateBaseExamineService.getDamagedDegreeVoList();
                    Map<String, List<BasicHouseDamagedDegreeVo>> stringListMap = Maps.newHashMap();
                    if (CollectionUtils.isNotEmpty(degreeVoList)) {
                        degreeVoList.stream().forEach(oo -> {
                            List<BasicHouseDamagedDegreeVo> damagedDegreeVoList = stringListMap.get(oo.getTypeName());
                            if (CollectionUtils.isEmpty(damagedDegreeVoList)) {
                                damagedDegreeVoList = Lists.newArrayList();
                            }
                            damagedDegreeVoList.add(oo);
                            stringListMap.put(oo.getTypeName(), damagedDegreeVoList);
                        });
                    }
                    if (!stringListMap.isEmpty()) {
                        stringListMap.entrySet().stream().forEach(stringListEntry -> {
                            List<BasicHouseDamagedDegreeVo> damagedDegreeVoList = stringListEntry.getValue();
                            String s = "";
                            if (CollectionUtils.isNotEmpty(damagedDegreeVoList)) {
                                damagedDegreeVoList.stream().forEach(oo -> {
                                    stringLinkedHashSet.add(String.format("%s%s", oo.getCategoryName(), oo.getEntityConditionName()));
                                });
                                s = StringUtils.join(stringLinkedHashSet, "、");
                                stringLinkedHashSet.clear();
                            } else {
                                s = "无";
                            }
                            linkedHashSet.add(String.format("%s：%s%s", stringListEntry.getKey(), s, "。"));
                        });
                        stringBuilder.append(StringUtils.join(linkedHashSet, "\r"));
                        linkedHashSet.clear();
                    }
                }
                stringBuilder.append("").append("\r");
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("楼栋新旧程度及维护使用情况无数据");
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
                stringBuilder.append(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber())).append("号");
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
                if (!Objects.equal(basicBuilding.getBuildingStructureCategoryName(), "简易结构")) {
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
        judgeObjectList= judgeObjectList.stream().filter(schemeJudgeObject -> {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                return false;
            }
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getType() == 0) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        if (!houseMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : houseMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicHouseCorollaryEquipment> equipmentList = generateBaseExamineService.getBasicHouseCorollaryEquipmentList();
                    if (CollectionUtils.isEmpty(equipmentList)) {
                        continue;
                    }
                    int size = equipmentList.size();
                    for (int j = 0; j < size; j++) {
                        linkedHashSet.add(equipmentList.get(j).getName());
                        linkedHashSet.add(baseDataDicService.getNameById(equipmentList.get(j).getEquipmentUse()));
                        linkedHashSet.add(baseDataDicService.getNameById(equipmentList.get(j).getCategory()));
                    }
                }
                if (CollectionUtils.isNotEmpty(linkedHashSet)){
                    hashSet.add(String.format("%s%s%s",stringEntry.getKey(),"房号",StringUtils.join(linkedHashSet,"、")));
                    linkedHashSet.clear();
                }
            }
        }else {
            return "" ;
        }
        return StringUtils.join(hashSet,";");
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
        if (CollectionUtils.isNotEmpty(judgeObjectList)){
            judgeObjectList = judgeObjectList.stream().filter(schemeJudgeObject -> {
                if (schemeJudgeObject.getDeclareRecordId() == null) {
                    return false;
                }
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply == null || basicApply.getType() == 1) {
                    return false;
                }
                return true;
            }).collect(Collectors.toList());
        }
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
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
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
                        stringBuilder.append(getIntelligentNet(intelligentVoList));
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
     * 获取电力通信网络
     *
     * @param intelligentVoList
     * @return
     */
    public String getIntelligentNet(List<BasicHouseIntelligentVo> intelligentVoList) {
        StringBuilder stringBuilder = new StringBuilder();
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
        return stringBuilder.toString();
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
                    if (CollectionUtils.isNotEmpty(basicUnitElevatorList)) {
                        for (int j = 0; j < basicUnitElevatorList.size(); j++) {
                            stringBuilder.append(basicUnitElevatorList.get(j).getNumber()).append("部");
                            String type = baseDataDicService.getNameById(basicUnitElevatorList.get(j).getType());
                            if (StringUtils.isEmpty(type)) {
                                type = "未知类型";
                            }
                            stringBuilder.append(type).append("电梯");
                            if (basicUnitElevatorList.get(j).getQuasiLoadNumber() != null) {
                                stringBuilder.append("准载人数").append(basicUnitElevatorList.get(j).getQuasiLoadNumber()).append("人");
                            }
                            if (StringUtils.isNotBlank(basicUnitElevatorList.get(j).getQuasiLoadWeight())) {
                                if (basicUnitElevatorList.get(j).getQuasiLoadNumber() != null) {
                                    stringBuilder.append("、");
                                }
                                stringBuilder.append("准载重量").append(basicUnitElevatorList.get(j).getQuasiLoadWeight()).append("kg");
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
        Map<Integer, String> functionMap = Maps.newHashMap();
        Map<Integer, String> roomMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            List<BasicBuildingFunction> functionList = basicBuildingFunctionService.getBasicBuildingFunctionList(basicBuilding.getId());
            StringBuilder functionBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(functionList)) {
                functionList.forEach(o -> {
                    functionBuilder.append(baseDataDicService.getNameById(o.getDecorationPart())).append(baseDataDicService.getNameById(o.getType())).append(",");
                });
                functionMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.strip(functionBuilder.toString(), ","));
            }

            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
            if (CollectionUtils.isNotEmpty(roomList)) {
                StringBuilder roomBuilder = new StringBuilder();
                for (BasicHouseRoom room : roomList) {
                    roomBuilder.append(baseDataDicService.getNameById(room.getRoomType()));
                    Map<String, String> stringMap = Maps.newHashMap();
                    if (StringUtils.isNotBlank(room.getAeration()))
                        stringMap.put("通风", room.getAeration());
                    if (StringUtils.isNotBlank(room.getSunshine()))
                        stringMap.put("日照", room.getSunshine());
                    if (StringUtils.isNotBlank(room.getLighting()))
                        stringMap.put("采光", room.getLighting());
                    if (StringUtils.isNotBlank(room.getSoundInsulation()))
                        stringMap.put("隔音", room.getSoundInsulation());
                    roomBuilder.append(generateCommonMethod.stringSummaryDesc(stringMap, "", "，"));
                }
                roomMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.strip(roomBuilder.toString(), "，"));
            }
        }
        String functionString = generateCommonMethod.judgeEachDesc(functionMap, "", ";", false);
        String roomString = generateCommonMethod.judgeEachDesc(roomMap, "号", ";", true);
        StringBuilder resultBuilder = new StringBuilder(functionString);
        if (StringUtils.isNotBlank(roomString))
            resultBuilder.append(";").append(roomString);
        return generateCommonMethod.trim(resultBuilder.toString());


//        //房间中采光等信息按估价对象分别描述
//        for (SchemeJudgeObject judgeObject : judgeObjectList) {
//            stringBuilder.append(String.format("%s%s", judgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
//            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
//            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
//            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
//
//            for (BasicHouseRoom room : roomList) {
//                stringBuilder.append(baseDataDicService.getNameById(room.getRoomType()));
//                Map<String, String> stringMap = Maps.newHashMap();
//                stringMap.put("通风", room.getAeration());
//                stringMap.put("日照", room.getSunshine());
//                stringMap.put("采光", room.getLighting());
//                stringMap.put("隔音", room.getSoundInsulation());
//                stringBuilder.append(generateCommonMethod.stringSummaryDesc(stringMap, "", "，"));
//            }
//        }
//        return generateCommonMethod.trim(stringBuilder.toString());
    }

}
