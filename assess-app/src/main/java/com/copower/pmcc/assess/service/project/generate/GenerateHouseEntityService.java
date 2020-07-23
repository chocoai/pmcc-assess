package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
 * 房屋实体信息
 */
@Service
public class GenerateHouseEntityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicUnitElevatorService basicUnitElevatorService;


    /**
     * 获取建造年份
     *
     * @param judgeObjectList
     * @return
     */
    public String getBuildingYear(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(schemeJudgeObject.getBasicApplyId());
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
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(schemeJudgeObject.getBasicApplyId());
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
     * @param judgeObjectList
     * @return
     */
    public String getBuildingStructure(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder builder = new StringBuilder(8);
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(schemeJudgeObject.getBasicApplyId());
            String v1 = baseDataDicService.getNameById(basicBuilding.getBuildingStructureType());
            String v2 = baseDataDicService.getNameById(basicBuilding.getBuildingStructureCategory());
            if (basicBuilding.getBuildingStructureType() != null && basicBuilding.getBuildingStructureCategory() != null) {
                builder.append("按").append(v1).append("划分为").append(v2);
            } else if (basicBuilding.getBuildingStructureType() != null) {
                builder.append(v1);
            } else if (basicBuilding.getBuildingStructureCategory() != null) {
                builder.append(v2);
            }
            if (builder != null && builder.length() > 0) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), builder.toString());
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
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            String s = getBuildingScaleExtend(basicApply);
            if (StringUtils.isNotBlank(s))
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), s);
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";", false);
    }

    public String getBuildingScaleExtend(BasicApply basicApply) {
        if (basicApply == null) return "";
        BasicBuildingVo basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
        StringBuilder builder = new StringBuilder();
        if (basicBuilding.getBuildingArea() != null)
            builder.append(String.format("建筑面积%s平方米，", basicBuilding.getBuildingArea()));
        if (StringUtils.isNotBlank(basicBuilding.getInJacketArea()))
            builder.append(String.format("套内面积%s平方米，", basicBuilding.getInJacketArea()));
        if (StringUtils.isNotBlank(basicBuilding.getUseArea()))
            builder.append(String.format("使用面积%s平方米，", basicBuilding.getUseArea()));

        List<BasicBuildingVo> buildingDiffVoList = basicBuilding.getBasicBuildingDifferences();
        if (StringUtils.isNotBlank(basicBuilding.getBuildingHeight()))
            builder.append(String.format("建筑高度%s米，", basicBuilding.getBuildingHeight()));
        if (CollectionUtils.isNotEmpty(buildingDiffVoList)) {
            for (BasicBuildingVo vo : buildingDiffVoList) {
                if (vo.getFirstFloor() != null)
                    builder.append(String.format("首层%s至", vo.getFirstFloor()));
                if (vo.getMaxFloor() != null)
                    builder.append(String.format("最高层%s是", vo.getMaxFloor()));
                if (vo.getPropertyType() != null)
                    builder.append(String.format("%s，", baseDataDicService.getNameById(vo.getPropertyType())));
                if (StringUtils.isNotBlank(vo.getFloorHeight()))
                    builder.append(String.format("层高%s米", vo.getFloorHeight()));
                builder.append(";");
            }
        } else {
            if (basicBuilding.getFirstFloor() != null)
                builder.append(String.format("首层%s至", basicBuilding.getFirstFloor()));
            if (basicBuilding.getMaxFloor() != null)
                builder.append(String.format("最高层%s是", basicBuilding.getMaxFloor()));
            if (basicBuilding.getPropertyType() != null)
                builder.append(String.format("%s，", baseDataDicService.getNameById(basicBuilding.getPropertyType())));
            if (StringUtils.isNotBlank(basicBuilding.getFloorHeight()))
                builder.append(String.format("层高%s米", basicBuilding.getFloorHeight()));
        }
        return builder.toString();
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
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(schemeJudgeObject.getBasicApplyId());
            List<BasicHouseRoom> houseRoomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
            if (CollectionUtils.isNotEmpty(houseRoomList)) {
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s米", houseRoomList.get(0).getLayerHeight()));
            }
        }
        return generateCommonMethod.judgeEachDesc(map, "", "，", false);
    }

    /**
     * 获取空间布局
     *
     * @param judgeObjectList
     * @return
     */
    public String getSpatialDistribution(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            StringBuilder stringBuilder = new StringBuilder();
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicUnit unit = generateBaseExamineService.getBasicUnit();
            BasicUnitHuxingVo huxing = generateBaseExamineService.getBasicUnitHuxing();
            if (unit != null && StringUtils.isNotBlank(unit.getElevatorHouseholdRatio())) {
                stringBuilder.append("梯户比").append(unit.getElevatorHouseholdRatio()).append(",");
            }
            if (huxing != null && StringUtils.isNotBlank(huxing.getName())) {
                stringBuilder.append(huxing.getName()).append(",");
            }
            if (huxing != null && StringUtils.isNotBlank(huxing.getSpatialDistributionName())) {
                stringBuilder.append(huxing.getSpatialDistributionName());
            }
            if (StringUtils.isNotBlank(stringBuilder.toString())) {
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
    public String getDecoration(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> outfitMap = Maps.newHashMap();
        Map<Integer, String> unitDecorateMap = Maps.newHashMap();
        Map<Integer, String> roomDecorateMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null || schemeJudgeObject.getDeclareRecordId() == 0) {
                continue;
            }
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            //楼栋外装
            List<BasicBuildingOutfitVo> outfitVos = basicBuildingOutfitService.getBasicBuildingOutfitVos(generateBaseExamineService.getBasicBuilding().getId());
            StringBuilder outfitBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(outfitVos)) {
                outfitVos.forEach(o -> {
                    outfitBuilder.append(o.getDecorationPart()).append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
                outfitMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s;", StringUtils.strip(outfitBuilder.toString(), ",")));
            }
            //楼栋内装
            List<BasicUnitDecorateVo> unitDecorates = basicUnitDecorateService.getBasicUnitDecorateList(generateBaseExamineService.getBasicUnit().getId());
            StringBuilder unitDecorateBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(unitDecorates)) {
                unitDecorates.forEach(o -> {
                    unitDecorateBuilder.append(o.getDecorationPartName()).append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
                unitDecorateMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), String.format("%s;", StringUtils.strip(unitDecorateBuilder.toString(), ",")));
            }
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(schemeJudgeObject.getBasicApplyId());
            List<BasicHouseRoomDecorateVo> roomDecorateList = basicHouseRoomDecorateService.getHouseRoomDecorateList(basicHouse.getId());
            if (CollectionUtils.isNotEmpty(roomDecorateList)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (BasicHouseRoomDecorateVo roomDecorate : roomDecorateList) {
                    if (org.apache.commons.lang.StringUtils.isNotBlank(roomDecorate.getLocation()))
                        stringBuilder.append(roomDecorate.getLocation()).append("的");
                    if (org.apache.commons.lang.StringUtils.isNotBlank(roomDecorate.getPartName()))
                        stringBuilder.append(roomDecorate.getPartName()).append("为");
                    if (org.apache.commons.lang.StringUtils.isNotBlank(roomDecorate.getRemark())) {
                        stringBuilder.append(roomDecorate.getRemark()).append(";");
                    } else if (org.apache.commons.lang.StringUtils.isNotBlank(roomDecorate.getMaterialName())) {
                        stringBuilder.append(roomDecorate.getMaterialName()).append(";");
                    }
                }
                roomDecorateMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), stringBuilder.toString());
            }
        }
        String outfitString = generateCommonMethod.judgeEachDesc(outfitMap, "", ";", false);
        String unitDecorateString = generateCommonMethod.judgeEachDesc(unitDecorateMap, "", ";", false);
        String roomDecorateString = generateCommonMethod.judgeEachDesc(roomDecorateMap, "", ";", false);
        StringBuilder resultBuilder = new StringBuilder(outfitString);
        if (StringUtils.isNotBlank(unitDecorateString))
            resultBuilder.append(";").append(unitDecorateString);
        if (StringUtils.isNotBlank(roomDecorateString))
            resultBuilder.append(";").append(roomDecorateString);
        return generateCommonMethod.trim(resultBuilder.toString());
    }

    /**
     * 获取外观
     *
     * @param judgeObjectList
     * @return
     */
    public String getAppearance(List<SchemeJudgeObject> judgeObjectList) {
        Map<String, List<Integer>> map = groupByBuilding(judgeObjectList);
        Map<Integer, String> stringMap = Maps.newHashMap();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            String s = getAppearanceExtend(basicApply);
            if (StringUtils.isNotBlank(s))
                stringMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), s);
        }
        return StringUtils.strip(generateCommonMethod.judgeEachDesc(stringMap, "", ";", false), ";");
    }

    public String getAppearanceExtend(BasicApply basicApply) {
        if (basicApply == null) return "";
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicBuildingVo basicBuilding = generateBaseExamineService.getBasicBuilding();
        Set<String> stringSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        if (basicBuilding.getAppearanceStyle() != null)
            stringBuilder.append(String.format("%s风格,", baseDataDicService.getNameById(basicBuilding.getAppearanceStyle())));
        if (basicBuilding.getAppearanceNewAndOld() != null)
            stringBuilder.append(String.format("外观%s;", baseDataDicService.getNameById(basicBuilding.getAppearanceNewAndOld())));
        stringSet.add(stringBuilder.toString());
        if (CollectionUtils.isNotEmpty(stringSet)) {
            return stringSet.stream().findFirst().get();
        }
        return "";
    }

    /**
     * 其它
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getOther(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            String s = getOtherExtend(basicApply);
            if (StringUtils.isNotBlank(s))
                map.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), s);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generateCommonMethod.judgeEachDesc(map, "", ";", false));
        return StringUtils.strip(stringBuilder.toString(), ";");
    }

    public String getOtherExtend(BasicApply basicApply) {
        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
        BasicEstateVo basicEstate = basicEstateService.getBasicEstateVo(generateBaseExamineService.getEstate());
        BasicBuildingVo basicBuilding = basicBuildingService.getBasicBuildingVo(generateBaseExamineService.getBasicBuilding());
        StringBuilder builder = new StringBuilder();
        if (basicBuilding != null && basicBuilding.getDataBuilder() != null) {
            builder.append("建造商").append(basicBuilding.getDataBuilder().getName()).append("，")
                    .append(basicBuilding.getDataBuilder().getCompanyNatureName()).append("，");
            builder.append(StringUtils.isNotBlank(basicBuilding.getDataBuilder().getSocialPrestigeName()) ? String.format("社会信誉%s;", basicBuilding.getDataBuilder().getSocialPrestigeName()) : ";");
        }

        if (basicEstate != null && basicEstate.getDataDeveloper() != null) {
            builder.append("开发商").append(basicEstate.getDataDeveloper().getName()).append("，")
                    .append(basicEstate.getDataDeveloper().getCompanyNatureName()).append("，");
            builder.append(StringUtils.isNotBlank(basicEstate.getDataDeveloper().getSocialPrestigeName()) ? String.format("社会信誉%s;", basicEstate.getDataDeveloper().getSocialPrestigeName()) : "");
        }
        return builder.toString();
    }


    /**
     * 较好新旧程度及维护使用情况
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getDamagedDegree(List<SchemeJudgeObject> judgeObjectList) {
        return getDamagedDegreeBase(judgeObjectList, true);
    }

    public String getDamagedDegreeBase(List<SchemeJudgeObject> judgeObjectList, boolean html) {
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> stringLinkedHashSet = Sets.newLinkedHashSet();
        //类型一共其实有4个类型也就是4部分
        List<String> typeList = Lists.newArrayList("其它", "设备部分", "装修部分");
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply == null || basicApply.getId() == 0) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseDamagedDegreeVo> degreeVoList = generateBaseExamineService.getDamagedDegreeVoList();
            Map<String, List<BasicHouseDamagedDegreeVo>> stringListMap = Maps.newLinkedHashMap();
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
                            String linkString = null;
                            if (StringUtils.isNotBlank(oo.getCategoryName()) && StringUtils.isNotBlank(oo.getEntityConditionName()) && StringUtils.isNotBlank(oo.getEntityConditionContent())) {
                                if (typeList.contains(oo.getTypeName()) && (oo.getCategoryName().contains("其它") || oo.getCategoryName().contains("特种设备"))) {
                                    String conditionName = oo.getEntityConditionContent().contains(oo.getEntityConditionName()) ? "" : oo.getEntityConditionName();
                                    linkString = String.format("%s%s", oo.getEntityConditionContent(), conditionName);
                                } else {
                                    linkString = String.format("%s%s", oo.getCategoryName(), oo.getEntityConditionName());
                                }
                            }
                            if (StringUtils.isNotBlank(linkString))
                                stringLinkedHashSet.add(linkString);
                        });
                        s = StringUtils.join(stringLinkedHashSet, "、");
                        stringLinkedHashSet.clear();
                    }
                    s = StringUtils.defaultIfBlank(s, "无");
                    if (html) {
                        stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s: %s。", stringListEntry.getKey(), s)));
                    } else {
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
    public String getBuildEntityAnalysis(List<SchemeJudgeObject> judgeObjectList, SchemeAreaGroup schemeAreaGroup) {
        int size = judgeObjectList.size();
        Map<String, String> map = Maps.newHashMap();
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (int i = 0; i < size; i++) {
                SchemeJudgeObject schemeJudgeObject = judgeObjectList.get(i);
                BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicBuildingVo basicBuilding = basicBuildingService.getBasicBuildingByApplyId(schemeJudgeObject.getBasicApplyId());
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
                //供水
                List<BasicHouseWater> basicHouseWaterList = new ArrayList<>();
                //排水
                List<BasicHouseWaterDrain> houseWaterDrainList = new ArrayList<>();
                //电梯
                List<BasicUnitElevator> unitElevatorList = new ArrayList<>();
                //电力通讯网络
                List<BasicHouseIntelligentVo> intelligentVoList = new ArrayList<>();
                List<BasicHouseWater> basicHouseWaterList2 = generateBaseExamineService.getBasicHouseWaterList();
                basicHouseWaterList.addAll(basicHouseWaterList2);
                List<BasicHouseWaterDrain> houseWaterDrainList2 = generateBaseExamineService.getBasicHouseWaterDrainList();
                houseWaterDrainList.addAll(houseWaterDrainList2);
                List<BasicHouseIntelligentVo> intelligentVoList2 = generateBaseExamineService.getBasicHouseIntelligentList();
                intelligentVoList.addAll(intelligentVoList2);

                List<BasicUnitElevator> unitElevatorList2 = generateBaseExamineService.getBasicUnitElevatorList();
                if (CollectionUtils.isNotEmpty(unitElevatorList2)) {
                    unitElevatorList.addAll(unitElevatorList2);
                }
                List<BasicHouseEquipmentVo> houseAirConditioner = generateBaseExamineService.getBasicHouseEquipmentList(ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey());
                List<BasicHouseEquipmentVo> houseNewWind = generateBaseExamineService.getBasicHouseEquipmentList(ExamineHouseEquipmentTypeEnum.houseNewWind.getKey());
                List<BasicHouseEquipmentVo> houseHeating = generateBaseExamineService.getBasicHouseEquipmentList(ExamineHouseEquipmentTypeEnum.houseHeating.getKey());
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
     * 房屋配套设备设施
     *
     * @param judgeObjectList
     * @return
     * @throws Exception
     */
    public String getMatchingEquipment(List<SchemeJudgeObject> judgeObjectList) {
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            return "";
        }
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> hashSet = Sets.newLinkedHashSet();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
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
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                hashSet.add(String.format("%s%s%s", schemeJudgeObject.getNumber(), "号", StringUtils.join(linkedHashSet, "、")));
                linkedHashSet.clear();
            }
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
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            return "";
        }
        Map<String, String> map = Maps.newHashMap();
        StringBuilder stringBuilder = new StringBuilder(8);
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseWater> basicHouseWaterList = generateBaseExamineService.getBasicHouseWaterList();
            if (CollectionUtils.isNotEmpty(basicHouseWaterList)) {
                basicHouseWaterList.stream().forEach(basicHouseWater -> {
                    stringBuilder.append("给水采用");
                    String gradeName = baseDataDicService.getNameById(basicHouseWater.getGrade());
                    if (StringUtils.isNotBlank(gradeName)) {
                        stringBuilder.append(gradeName);
                    }
                    String boosterEquipmentName = baseDataDicService.getNameById(basicHouseWater.getBoosterEquipment());
                    if (StringUtils.isNotBlank(boosterEquipmentName)) {
                        stringBuilder.append(boosterEquipmentName);
                    }
                    String pipeMaterialName = baseDataDicService.getNameById(basicHouseWater.getPipeMaterial());
                    if (StringUtils.isNotBlank(pipeMaterialName)) {
                        stringBuilder.append(pipeMaterialName);
                    }
                    String pipingLayoutName = baseDataDicService.getNameById(basicHouseWater.getPipingLayout());
                    if (StringUtils.isNotBlank(pipingLayoutName)) {
                        stringBuilder.append(pipingLayoutName);
                    }
                    String supplyModeName = baseDataDicService.getNameById(basicHouseWater.getSupplyMode());
                    if (StringUtils.isNotBlank(supplyModeName)) {
                        stringBuilder.append(supplyModeName);
                    }
                    if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
                        linkedHashSet.add(stringBuilder.toString());
                    }
                    stringBuilder.delete(0, stringBuilder.toString().length());
                });
            }
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                map.put(StringUtils.join(linkedHashSet, "，"), String.format("%s%s", schemeJudgeObject.getNumber(), "号"));
            }
            linkedHashSet.clear();
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
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        Map<String, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseWaterDrain> voList = generateBaseExamineService.getBasicHouseWaterDrainList();
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
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                map.put(StringUtils.join(linkedHashSet, "，"), String.format("%s%s", schemeJudgeObject.getNumber(), "号"));
            }
            linkedHashSet.clear();
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
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        LinkedHashSet<String> centerList = Sets.newLinkedHashSet();
        Map<String, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseIntelligentVo> voList = generateBaseExamineService.getBasicHouseIntelligentList();
            if (CollectionUtils.isNotEmpty(voList)) {
                voList.stream().forEach(oo -> {
                    if (StringUtils.isNotBlank(oo.getGradeName())) {
                        stringBuilder.append("电路采用");
                        stringBuilder.append(oo.getGradeName()).append("材料");
                    }
                    if (StringUtils.isNotBlank(oo.getSwitchCircuitName())) {
                        stringBuilder.append(oo.getSwitchCircuitName());
                    }
                    stringBuilder.append(StringUtils.isNotBlank(oo.getLayingMethodName()) ? oo.getLayingMethodName() : "无").append("铺设，");
                    centerList.add(stringBuilder.toString());
                    if (StringUtils.isNotBlank(oo.getLampsLanternsName())) {
                        centerList.add(String.format("%s%s", "灯具为", oo.getLampsLanternsName()));
                    }
                    if (StringUtils.isNotBlank(oo.getIntelligentSystemName())) {
                        centerList.add(oo.getIntelligentSystemName());
                    }
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        linkedHashSet.add(StringUtils.join(centerList, "，"));
                    }
                    centerList.clear();
                    stringBuilder.delete(0, stringBuilder.toString().length());
                });
            }
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                map.put(StringUtils.join(linkedHashSet, "，"), String.format("%s%s", schemeJudgeObject.getNumber(), "号"));
            }
            linkedHashSet.clear();
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
    public String getHouseEquipment(List<SchemeJudgeObject> judgeObjectList, ExamineHouseEquipmentTypeEnum equipmentTypeEnum) {
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        StringBuilder stringBuilder = new StringBuilder(8);
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        Map<String, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            List<BasicHouseEquipmentVo> equipmentList = generateBaseExamineService.getBasicHouseEquipmentList(equipmentTypeEnum.getKey());
            if (CollectionUtils.isEmpty(equipmentList)) {
                continue;
            }
            if (CollectionUtils.isNotEmpty(equipmentList)) {
                equipmentList.stream().forEach(oo -> {
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
            if (CollectionUtils.isNotEmpty(linkedHashSet)) {
                map.put(StringUtils.join(linkedHashSet, ";"), String.format("%s%s", generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), "号"));
            }
            linkedHashSet.clear();
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
    public String getUnitElevator(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, List<String>> unitMap = groupByUnit(judgeObjectList);
        StringBuilder stringBuilder = new StringBuilder();
        if (!unitMap.isEmpty()) {
            for (Map.Entry<Integer, List<String>> entry : unitMap.entrySet()) {
                List<BasicUnitElevator> elevatorList = basicUnitElevatorService.getBasicUnitElevatorList(entry.getKey());
                if (CollectionUtils.isEmpty(elevatorList)) continue;
                if (unitMap.size() > 1 || entry.getValue().size() > 1) {
                    stringBuilder.append(FormatUtils.transformListString(entry.getValue())).append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
                }
                for (BasicUnitElevator basicUnitElevator : elevatorList) {
                    if (basicUnitElevator.getNumber() != null)
                        stringBuilder.append(basicUnitElevator.getNumber()).append("部");
                    stringBuilder.append(basicUnitElevator.getBrand());
                    if (basicUnitElevator.getType() != null)
                        stringBuilder.append(baseDataDicService.getNameById(basicUnitElevator.getType()));
                    stringBuilder.append(",");
                    if (basicUnitElevator.getQuasiLoadNumber() != null) {
                        stringBuilder.append("准载人数").append(basicUnitElevator.getQuasiLoadNumber()).append("人，");
                    }
                    if (basicUnitElevator.getQuasiLoadWeight() != null) {
                        stringBuilder.append("准载重量").append(basicUnitElevator.getQuasiLoadWeight()).append("kg");
                    }
                }
            }
        }
        return stringBuilder.toString();
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
            BasicApplyBatch basicApplyBatch = surveyCommonService.getBasicApplyBatchByApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApplyBatch == null || basicApplyBatch.getId() == 0) {
                continue;
            }
            BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApplyBatch);
            if (basicExamineHandle == null) {
                continue;
            }
            List<BasicBuilding> basicBuildingList = basicExamineHandle.getBasicBuildingAll();
            if (CollectionUtils.isEmpty(basicBuildingList)) {
                continue;
            }
            Iterator<BasicBuilding> iterator = basicBuildingList.iterator();
            while (iterator.hasNext()) {
                BasicBuilding basicBuilding = iterator.next();
                String key = basicBuilding.getBuildingNumber();
                if (map.containsKey(key)) {
                    List<Integer> list = map.get(key);
                    list.add(schemeJudgeObject.getId());
                } else {
                    map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
                }
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
    private Map<Integer, List<String>> groupByUnit(List<SchemeJudgeObject> judgeObjectList) {
        Map<Integer, List<String>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            List<KeyValueDto> keyValueDtos = JSON.parseArray(basicApply.getStructuralInfo(), KeyValueDto.class);
            if (CollectionUtils.isEmpty(keyValueDtos)) continue;
            for (KeyValueDto keyValueDto : keyValueDtos) {
                if (keyValueDto.getKey().startsWith(BasicFormClassifyEnum.UNIT.getKey())) {
                    List<String> list = map.get(keyValueDto.getValue());
                    if (CollectionUtils.isEmpty(list)) {
                        map.put(Integer.valueOf(keyValueDto.getValue()), Lists.newArrayList(schemeJudgeObject.getNumber()));
                    } else {
                        list.add(schemeJudgeObject.getNumber());
                        map.put(Integer.valueOf(keyValueDto.getValue()), list);
                    }
                }
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
            BasicApplyBatch basicApplyBatch = surveyCommonService.getBasicApplyBatchByApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApplyBatch == null || basicApplyBatch.getId() == 0) {
                continue;
            }
            BasicExamineHandle basicExamineHandle = new BasicExamineHandle(basicApplyBatch);
            if (basicExamineHandle == null) {
                continue;
            }
            List<BasicBuilding> basicBuildingList = basicExamineHandle.getBasicBuildingAll();
            if (CollectionUtils.isEmpty(basicBuildingList)) {
                continue;
            }
            Iterator<BasicBuilding> iterator = basicBuildingList.iterator();
            while (iterator.hasNext()) {
                BasicBuilding basicBuilding = iterator.next();
                List<BasicBuildingFunction> functionList = basicExamineHandle.getBasicBuildingFunctionList(basicBuilding.getId());
                StringBuilder functionBuilder = new StringBuilder();
                if (CollectionUtils.isNotEmpty(functionList)) {
                    functionList.forEach(o -> {
                        functionBuilder.append(baseDataDicService.getNameById(o.getDecorationPart())).append(baseDataDicService.getNameById(o.getType())).append(",");
                    });
                    functionMap.put(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()), StringUtils.strip(functionBuilder.toString(), ","));
                }
            }
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(schemeJudgeObject.getBasicApplyId());
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
            if (CollectionUtils.isNotEmpty(roomList)) {
                StringBuilder roomBuilder = new StringBuilder();
                for (BasicHouseRoom room : roomList) {
                    if (roomList.size() > 1)
                        roomBuilder.append(room.getName());
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
