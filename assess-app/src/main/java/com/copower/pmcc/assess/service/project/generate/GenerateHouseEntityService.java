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
import java.util.Date;
import java.util.LinkedHashSet;
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
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;


    /**
     * 获取建造年份
     *
     * @param judgeObjectList
     * @return
     */
    public String getBuildingYear(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            Date beCompletedTime = basicBuilding.getBeCompletedTime();
            int year = DateUtils.getYear(beCompletedTime);
            map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s年", year));
        }
        return generateCommonMethod.judgeEachDesc(map, "", "，", false);
    }

    /**
     * 获取工程质量
     *
     * @param judgeObjectList
     * @return
     */
    public String getConstructionQuality(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            Integer constructionQuality = basicBuilding.getConstructionQuality();
            if (constructionQuality != null && constructionQuality > 0) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), baseDataDicService.getNameById(constructionQuality));
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "", "，", false);
    }

    /**
     * 获取建筑结构
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingStructure(List<SchemeJudgeObject> judgeObjectList) {
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
     * @param judgeObjectList
     * @return
     */
    public String getBuildingScale(List<SchemeJudgeObject> judgeObjectList) {
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
     * @param judgeObjectList
     * @return
     */
    public String getFloorHeight(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BigDecimal floorHeight = basicBuilding.getFloorHeight();
            if (floorHeight != null) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s米", floorHeight));
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "", "，", false);
    }

    /**
     * 获取空间布局
     *
     * @param judgeObjectIds
     * @return
     */
    public String getSpatialDistribution(List<SchemeJudgeObject> judgeObjectList) throws Exception {
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
     * @param judgeObjectList
     * @return
     */
    public String getDecoration(List<SchemeJudgeObject> judgeObjectList) throws Exception {
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
     * @param judgeObjectList
     * @return
     */
    public String getAppearance(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<String, List<Integer>> map = groupByBuilding(judgeObjectList);
        Map<Integer, String> stringMap = Maps.newHashMap();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            stringBuilder.append(String.format("%s风格,", baseDataDicService.getNameById(basicBuilding.getAppearanceStyle())));
            stringBuilder.append(String.format("外观%s;", baseDataDicService.getNameById(basicBuilding.getAppearanceNewAndOld())));
            stringMap.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), stringBuilder.toString());
        }
        return StringUtils.strip(generateCommonMethod.judgeEachDesc(stringMap, "", ";", false), ";");
    }

    /**
     * 其它
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getOther(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<Integer, String> builderMap = Maps.newHashMap();
        Map<Integer, String> developerMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateVo basicEstate = basicEstateService.getBasicEstateVo(generateBaseExamineService.getEstate());
            BasicBuildingVo basicBuilding = basicBuildingService.getBasicBuildingVo(generateBaseExamineService.getBasicBuilding());
            if (basicBuilding != null && basicBuilding.getId() != null) {
                StringBuilder builderString = new StringBuilder();
                StringBuilder developerString = new StringBuilder();
                if (basicBuilding.getDataBuilder() != null) {
                    builderString.append("建造商").append(basicBuilding.getDataBuilder().getName()).append("，")
                            .append(basicBuilding.getDataBuilder().getCompanyNatureName()).append("，");
                    builderString.append(StringUtils.isNotBlank(basicBuilding.getDataBuilder().getSocialPrestigeName()) ? String.format("社会信誉%s;", basicBuilding.getDataBuilder().getSocialPrestigeName()) : "");
                    builderMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), builderString.toString());
                }
                if (basicEstate.getDataDeveloper() != null) {
                    developerString.append("开发商").append(basicEstate.getDataDeveloper().getName()).append("，")
                            .append(basicEstate.getDataDeveloper().getCompanyNatureName()).append("，");
                    developerString.append(StringUtils.isNotBlank(basicEstate.getDataDeveloper().getSocialPrestigeName()) ? String.format("社会信誉%s;", basicEstate.getDataDeveloper().getSocialPrestigeName()) : "");
                    developerMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), developerString.toString());
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generateCommonMethod.judgeEachDesc(builderMap, "", ";", false));
        stringBuilder.append(generateCommonMethod.judgeEachDesc(developerMap, "", ";", false));
        return StringUtils.strip(stringBuilder.toString(), ";");
    }

    /**
     * 较好新旧程度及维护使用情况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getDamagedDegree(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        return getDamagedDegreeBase(judgeObjectList,true) ;
    }

    public String getDamagedDegreeBase(List<SchemeJudgeObject> judgeObjectList, boolean html) throws Exception {
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        //类型一共其实有4个类型也就是4部分
        List<String> typeList = Lists.newArrayList("其它", "设备部分", "装修部分");
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
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
            StringBuilder stringBuilder = new StringBuilder();
            if (!stringListMap.isEmpty()) {
                stringListMap.entrySet().forEach(stringListEntry -> {
                    List<BasicHouseDamagedDegreeVo> damagedDegreeVoList = stringListEntry.getValue();
                    String s = "";
                    if (CollectionUtils.isNotEmpty(damagedDegreeVoList)) {
                        damagedDegreeVoList.stream().forEach(oo -> {
                            if (typeList.contains(oo.getTypeName()) && (oo.getCategoryName().contains("其它") || oo.getCategoryName().contains("特种设备"))) {
                                stringLinkedHashSet.add(String.format("%s%s", oo.getEntityConditionContent(), oo.getEntityConditionName()));
                            } else {
                                stringLinkedHashSet.add(String.format("%s%s", oo.getCategoryName(), oo.getEntityConditionName()));
                            }
                        });
                        s = StringUtils.join(stringLinkedHashSet, "、");
                        stringLinkedHashSet.clear();
                    } else {
                        s = "无";
                    }
                    if (html) {
                        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s: %s。", stringListEntry.getKey(), s)));
                    }else {
                        stringBuilder.append(String.format("%s: %s。", stringListEntry.getKey(), s));
                    }
                });
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
                linkedHashSet.clear();
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";", false);
    }


    /**
     * 建筑实体分析
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getBuildEntityAnalysis(List<SchemeJudgeObject> judgeObjectList, SchemeAreaGroup schemeAreaGroup) throws Exception {
        int size = judgeObjectList.size();
        Map<String, String> map = Maps.newHashMap();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (int i = 0; i < size; i++) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(i);
                if (schemeJudgeObject == null || schemeJudgeObject.getDeclareRecordId() == null) {
                    continue;
                }
                BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
                if (basicApply == null || basicApply.getId() == 0) {
                    continue;
                }
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicBuildingVo basicBuilding = basicBuildingService.getBasicBuildingVo(generateBaseExamineService.getBasicBuilding());
                linkedHashSet.add(String.format("%s%s", basicBuilding.getBuildingStructureTypeName(), basicBuilding.getBuildingStructureCategoryName()));
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
                        linkedHashSet.add(text);
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
                if (StringUtils.isNotBlank(contentA)) {
                    linkedHashSet.add(contentA);
                }
                if (!Objects.equal(basicBuilding.getBuildingStructureCategoryName(), "简易结构")) {
                    if (StringUtils.isNotBlank(contentA)) {
                        switch (contentA) {
                            case "设备设施基础配置不完备":
                                linkedHashSet.add("相对不利于提升房产价值。");
                                break;
                            case "设备设施基础配置相对完备":
                                linkedHashSet.add("相对有利于提升房产价值。");
                                break;
                            case "设备设施基础配置比较完备":
                                linkedHashSet.add("相对比较利于提升房产价值。");
                                break;
                            case "设备设施配置完备":
                                linkedHashSet.add("对于提升房产价值有比较大的影响。");
                                break;
                            case "设备设施配置非常完备":
                                linkedHashSet.add("对于提升房产价值有比较大的影响。");
                                break;
                            default:
                                break;
                        }
                    }
                }
                map.put(StringUtils.join(linkedHashSet.stream().filter(s -> StringUtils.isNotEmpty(s)).collect(Collectors.toList()), "，"), String.format("%s%s", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), "号"));
                linkedHashSet.clear();
            }
        }
        String s = "无";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toList()), "");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), "\r");
            }
        }
        return s;
    }

    /**
     * 房屋配套设备设施工
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getMatchingEquipment(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        judgeObjectList = judgeObjectList.stream().filter(schemeJudgeObject -> {
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
                        linkedHashSet.add(equipmentList.get(j).getName());
                        linkedHashSet.add(baseDataDicService.getNameById(equipmentList.get(j).getEquipmentUse()));
                        linkedHashSet.add(baseDataDicService.getNameById(equipmentList.get(j).getCategory()));
                    }
                }
                if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                    hashSet.add(String.format("%s%s%s", stringEntry.getKey(), "号", StringUtils.join(linkedHashSet, "、")));
                    linkedHashSet.clear();
                }
            }
        } else {
            return "";
        }
        return StringUtils.join(hashSet, ";");
    }

    /**
     * 供水
     *
     * @param judgeObjectList
     * @return
     */
    public String getHouseWater(List<SchemeJudgeObject> judgeObjectList) {
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        Map<String, String> map = Maps.newHashMap();
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
                    List<BasicHouseWater> basicHouseWaterList = Lists.newArrayList();
                    try {
                        basicHouseWaterList = generateBaseExamineService.getBasicHouseWaterList();
                    } catch (Exception e) {
                    }
                    if (CollectionUtils.isNotEmpty(basicHouseWaterList)) {
                        basicHouseWaterList.stream().forEach(basicHouseWater -> {
                            String gradeName = baseDataDicService.getNameById(basicHouseWater.getGrade());
                            int num = 0;
                            if (StringUtils.isEmpty(gradeName)) {
                                num++;
                            }
                            String boosterEquipmentName = baseDataDicService.getNameById(basicHouseWater.getBoosterEquipment());
                            if (StringUtils.isEmpty(boosterEquipmentName)) {
                                num++;
                            }
                            String pipeMaterialName = baseDataDicService.getNameById(basicHouseWater.getPipeMaterial());
                            if (StringUtils.isEmpty(pipeMaterialName)) {
                                num++;
                            }
                            String pipingLayoutName = baseDataDicService.getNameById(basicHouseWater.getPipingLayout());
                            if (StringUtils.isEmpty(pipingLayoutName)) {
                                num++;
                            }
                            String supplyModeName = baseDataDicService.getNameById(basicHouseWater.getSupplyMode());
                            if (StringUtils.isEmpty(supplyModeName)) {
                                num++;
                            }
                            String fireWaterSupplyName = baseDataDicService.getNameById(basicHouseWater.getFireWaterSupply());
                            if (StringUtils.isEmpty(fireWaterSupplyName)) {
                                num++;
                            }
                            if (num == 0) {
                                stringBuilder.append("给水采用")
                                        .append(gradeName)
                                        .append(boosterEquipmentName)
                                        .append(pipeMaterialName)
                                        .append(pipingLayoutName)
                                        .append(supplyModeName)
                                        .append("，")
                                        .append("消防给水")
                                        .append(fireWaterSupplyName);
                            }
                            if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
                                linkedHashSet.add(stringBuilder.toString());
                            }
                            stringBuilder.delete(0, stringBuilder.toString().length());
                        });
                    }
                }
                if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                    if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                        map.put(StringUtils.join(linkedHashSet, "，"), String.format("%s%s", stringEntry.getKey().toString(), "号"));
                    }
                }
                linkedHashSet.clear();
            }
        }
        String s = "";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), "。\r");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), "。\n");
            }
        }
        return s;
    }

    /**
     * 排水
     *
     * @param judgeObjectList
     * @return
     */
    public String getHouseWaterDrain(List<SchemeJudgeObject> judgeObjectList) {
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        Map<String, String> map = Maps.newHashMap();
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
                    List<BasicHouseWaterDrain> voList = Lists.newArrayList();
                    try {
                        voList = generateBaseExamineService.getBasicHouseWaterDrainList();
                    } catch (Exception e) {
                    }
                    if (CollectionUtils.isNotEmpty(voList)) {
                        voList.stream().forEach(basicHouseWaterDrain -> {
                            int number = 0;
                            String typeName = baseDataDicService.getNameById(basicHouseWaterDrain.getType());
                            if (StringUtils.isEmpty(typeName)) {
                                number++;
                            }
                            String processingModeName = baseDataDicService.getNameById(basicHouseWaterDrain.getProcessingMode());
                            if (StringUtils.isEmpty(processingModeName)) {
                                number++;
                            }
                            if (number == 0) {
                                stringBuilder.append(typeName).append("采用")
                                        .append(baseDataDicService.getNameById(basicHouseWaterDrain.getDrainSystem())).append(processingModeName).append("处理");
                            }
                            if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
                                linkedHashSet.add(stringBuilder.toString());
                            }
                            stringBuilder.delete(0, stringBuilder.toString().length());
                        });
                    }
                }
                if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                    if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                        map.put(StringUtils.join(linkedHashSet, "，"), String.format("%s%s", stringEntry.getKey().toString(), "号"));
                    }
                }
                linkedHashSet.clear();
            }
        }
        String s = "";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), "。\r");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), "。\n");
            }
        }
        return s;
    }

    /**
     * 电力通讯网络
     *
     * @param judgeObjectList
     * @return
     */
    public String getIntelligent(List<SchemeJudgeObject> judgeObjectList) {
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> centerList = Sets.newLinkedHashSet();
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        Map<String, String> map = Maps.newHashMap();
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
                    List<BasicHouseIntelligentVo> voList = generateBaseExamineService.getBasicHouseIntelligentList();
                    if (CollectionUtils.isNotEmpty(voList)) {
                        voList.stream().forEach(oo -> {
                            if (StringUtils.isNotBlank(oo.getIntelligentSystemName())) {
                                if (StringUtils.isNotBlank(oo.getGradeName())) {
                                    stringBuilder.append("电路采用");
                                    stringBuilder.append(oo.getGradeName()).append("材料");
                                }
                                if (StringUtils.isNotBlank(oo.getSwitchCircuitName())) {
                                    stringBuilder.append(oo.getSwitchCircuitName());
                                }
                                stringBuilder.append(StringUtils.isNotBlank(oo.getLayingMethodName()) ? oo.getLayingMethodName() : "无").append("铺设");
                                centerList.add(stringBuilder.toString());
                                if (StringUtils.isNotBlank(oo.getLampsLanternsName())) {
                                    centerList.add(String.format("%s%s", "灯具为", oo.getLampsLanternsName()));
                                }
                                String s = oo.getIntelligentSystemName();
                                s = s.replaceAll("；", "");
                                s = s.replaceAll(";", "");
                                s = s.replaceAll("/", "-");
                                centerList.add(s);
                                if (CollectionUtils.isNotEmpty(centerList)) {
                                    linkedHashSet.add(StringUtils.join(centerList, "，"));
                                }
                                centerList.clear();
                                stringBuilder.delete(0, stringBuilder.toString().length());
                            }
                        });
                    }
                }
                if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                    if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                        map.put(StringUtils.join(linkedHashSet, "，"), String.format("%s%s", stringEntry.getKey().toString(), "号"));
                    }
                }
                linkedHashSet.clear();
            }
        }
        String s = "";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), "。\r");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), "。\n");
            }
        }
        return s;
    }

    /**
     * 空调，新风，供暖
     *
     * @param judgeObjectList
     * @param equipmentTypeEnum
     * @return
     * @throws Exception
     */
    public String getHouseEquipment(List<SchemeJudgeObject> judgeObjectList, ExamineHouseEquipmentTypeEnum equipmentTypeEnum) throws Exception {
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
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
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        Map<String, List<Integer>> houseMap = groupByHouse(judgeObjectList);
        Map<String, String> map = Maps.newHashMap();
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
                    List<BasicHouseEquipmentVo> equipmentList = Lists.newArrayList();
                    List<BasicHouseEquipment> equipmentList2 = generateBaseExamineService.getBasicHouseEquipmentList();
                    if (CollectionUtils.isNotEmpty(equipmentList2)) {
                        equipmentList2.stream().forEach(oo -> equipmentList.add(basicHouseEquipmentService.getBasicHouseEquipmentVo(oo)));
                    }
                    if (CollectionUtils.isEmpty(equipmentList)) {
                        continue;
                    }
                    List<BasicHouseEquipmentVo> voList = Lists.newArrayList();
                    if (CollectionUtils.isNotEmpty(equipmentList)) {
                        voList.addAll(equipmentList.stream()
                                .filter(oo -> com.google.common.base.Objects.equal(equipmentTypeEnum.getKey(), oo.getType()))
                                .collect(Collectors.toList()));
                    }
                    if (CollectionUtils.isNotEmpty(voList)) {
                        voList.stream().forEach(oo -> {
                            if (StringUtils.isNotBlank(oo.getEquipment()) && StringUtils.isNotBlank(oo.getCategoryName())) {
                                stringBuilder.append(oo.getEquipment()).append("牌");
                                if (StringUtils.isNotBlank(oo.getGradeName())) {
                                    stringBuilder.append(oo.getGradeName());
                                }
                                stringBuilder.append(oo.getCategoryName());

                                if (StringUtils.isNotBlank(oo.getSupplyModeName())) {
                                    stringBuilder.append(",分房").append(oo.getSupplyModeName());
                                }
                                if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
                                    linkedHashSet.add(stringBuilder.toString());
                                }
                                stringBuilder.delete(0, stringBuilder.toString().length());
                            }
                        });
                    }
                }
                if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                    if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                        map.put(StringUtils.join(linkedHashSet, ";"), String.format("%s%s", stringEntry.getKey().toString(), "号"));
                    }
                }
                linkedHashSet.clear();
            }
        }
        String s = "";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), "。\r");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, null), "。\n");
            }
        }
        return s;
    }


    /**
     * 单元电梯
     *
     * @param judgeObjectList
     * @return
     */
    public String getUnitElevator(List<SchemeJudgeObject> judgeObjectList) throws Exception {
        Map<String, List<Integer>> unitMap = groupByUnit(judgeObjectList);
        LinkedHashSet<String> newLinkedHashSet = Sets.newLinkedHashSet();
        Map<String, String> map = Maps.newHashMap();
        Map<String, List<Integer>> listMap = Maps.newHashMap();
        if (!unitMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> stringEntry : unitMap.entrySet()) {
                List<Integer> integerList = stringEntry.getValue();
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                for (int i = 0; i < integerList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(integerList.get(i));
                    BasicApply basicApply = generateCommonMethod.getBasicApplyBySchemeJudgeObject(schemeJudgeObject);
                    if (basicApply == null) {
                        continue;
                    }
                    GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                    List<BasicUnitElevator> basicUnitElevatorList = generateBaseExamineService.getBasicUnitElevatorList().stream().filter(oo -> oo.getNumber() != null).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(basicUnitElevatorList)) {
                        continue;
                    }
                    if (CollectionUtils.isNotEmpty(basicUnitElevatorList)) {
                        Integer number = 0;
                        if (basicUnitElevatorList.stream().filter(oo -> oo.getNumber() != null).count() >= 1) {
                            number = basicUnitElevatorList.stream().filter(oo -> oo.getNumber() != null).mapToInt(oo -> oo.getNumber()).sum();
                        }
                        if (basicUnitElevatorList.stream().filter(oo -> {
                            if (oo.getType() != null) {
                                return StringUtils.isNotBlank(oo.getBrand());
                            }
                            return false;
                        }).count() >= 1) {
                            basicUnitElevatorList.stream().forEach(oo -> {

                                String s1 = "";
                                if (oo.getQuasiLoadNumber() != null) {
                                    s1 = String.format("%s%s准载人数%s人，",
                                            oo.getBrand(),
                                            baseDataDicService.getNameById(oo.getType()),
                                            oo.getQuasiLoadNumber().toString());
                                }
                                String s2 = "";
                                if (StringUtils.isNotBlank(oo.getQuasiLoadWeight())) {
                                    String s = "";
                                    if (oo.getQuasiLoadNumber() == null) {
                                        s = String.format("%s", oo.getBrand());
                                    }
                                    s2 = String.format("%s准载重量%s", s,
                                            String.format("%s%s", oo.getQuasiLoadWeight(), "kg"));
                                }
                                newLinkedHashSet.add(String.format("%s%s", s1, s2));
                            });
                        }
                        if (CollectionUtils.isNotEmpty(newLinkedHashSet)) {
                            String key = String.format("%d%s%s", number, "部电梯，", StringUtils.join(newLinkedHashSet, "、"));
                            List<Integer> integers = listMap.get(key);
                            if (CollectionUtils.isEmpty(integers)) integers = Lists.newArrayList();
                            integers.add(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()));
                            listMap.put(key, integers);
                        }
                        newLinkedHashSet.clear();
                    }
                }
                if (!listMap.isEmpty()) {
                    if (listMap.entrySet().size() == 1) {
                        map.put(listMap.entrySet().stream().limit(1).findFirst().get().getKey(), String.format("%s单元", stringEntry.getKey()));
                    } else {
                        listMap.entrySet().stream().forEach(stringListEntry -> {
                            String key = String.format("%s%s", generateCommonMethod.convertNumber(stringListEntry.getValue()), stringListEntry.getKey());
                            map.put(key, String.format("%s单元", stringEntry.getKey()));
                        });
                    }
                }
                listMap.clear();
            }
        }
        String s = " ";
        if (!map.isEmpty()) {
            if (map.entrySet().stream().map(entry -> entry.getKey()).distinct().count() == 1) {
                s = StringUtils.join(map.entrySet().stream().map(entry -> entry.getKey()).distinct().collect(Collectors.toList()), "。");
            } else {
                s = StringUtils.join(generateCommonMethod.changeMapToList(map, false, ""), "。");
            }
        }
        return s;
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

    /**
     * 获取建筑功能
     *
     * @param judgeObjectList
     * @return
     */
    public String getBuildingFunction(List<SchemeJudgeObject> judgeObjectList) {
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
                    if (roomList.size() > 1)
                        roomBuilder.append(room.getRoomType());
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
        String roomString = generateCommonMethod.judgeEachDesc(roomMap, "", ";", false);
        StringBuilder resultBuilder = new StringBuilder(functionString);
        if (StringUtils.isNotBlank(roomString))
            resultBuilder.append(";").append(roomString);
        return generateCommonMethod.trim(resultBuilder.toString());
    }

}
