package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.data.DataPropertyServiceItemVo;
import com.copower.pmcc.assess.dto.output.data.DataPropertyVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightRecordVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.data.DataPropertyServiceItemService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权益分析信息
 * @author zch
 */
@Service
public class GenerateEquityService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private DataPropertyServiceItemService dataPropertyServiceItemService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String HOUSE = "房产";
    private final String LAND = "土地";
    private final String SCHEME_JUDGEOBJECT_NAME = "号";
    private final String errorStr = "无";

    //1.土地权益状况
    //权证权益
    //规划条件
    //土地利用状况
    //他权类别
    //他权特殊情况


    //2.房屋权益状况
    //权证权益
    //他权类别
    //他权特殊情况
    //物业服务
    //物业公司
    //房产评估价益综合评价

    public void writeText(Integer areaId, ProjectInfoVo projectInfoVo, DocumentBuilder builder) throws Exception {
        LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap = generateCommonMethod.getLinkedHashMapEstateNameSchemeJudgeObjectList(areaId);
        if (projectInfoVo.getProjectCategoryName().indexOf(LAND) != -1) {
            if (!listLinkedHashMap.isEmpty()) {
                this.writeLand(areaId, projectInfoVo, listLinkedHashMap, builder);
            }
        }
        if (projectInfoVo.getProjectCategoryName().indexOf(HOUSE) != -1) {
            if (!listLinkedHashMap.isEmpty()) {
                this.writeHouse(areaId, projectInfoVo, listLinkedHashMap, builder);
            }
        }
    }

    /**
     * 土地
     *
     * @param areaId
     * @param projectInfoVo
     * @param listLinkedHashMap
     * @param documentBuilder
     * @throws Exception
     */
    private void writeLand(Integer areaId, ProjectInfoVo projectInfoVo, LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap, DocumentBuilder documentBuilder) throws Exception {
        for (Map.Entry<String, List<SchemeJudgeObject>> entry : listLinkedHashMap.entrySet()) {
            List<DeclareRecord> declareRecordList = this.getDeclareRecordList(entry.getValue());
            if (CollectionUtils.isEmpty(declareRecordList)) {
                continue;
            }
            documentBuilder.writeln(String.format("1、土地权益状况:%s", getRightsAndInterests(LAND, declareRecordList, entry.getValue())));
            documentBuilder.insertHtml(String.format("2、他权类别:%s", getSurveyAssetInventoryRightType(entry.getValue(), projectInfoVo)), true);
            documentBuilder.writeln(String.format("3、特殊情况:%s", getSpecialcase(entry.getValue(), projectInfoVo, LAND)));
            documentBuilder.writeln(String.format("4、规划条件:%s", getPlanningConditions(entry.getValue())));
            documentBuilder.writeln();
        }
    }

    /**
     * 房产
     *
     * @param areaId
     * @param projectInfoVo
     * @param listLinkedHashMap
     * @param documentBuilder
     * @throws Exception
     */
    private void writeHouse(Integer areaId, ProjectInfoVo projectInfoVo, LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap, DocumentBuilder documentBuilder) throws Exception {
        for (Map.Entry<String, List<SchemeJudgeObject>> entry : listLinkedHashMap.entrySet()) {
            List<DeclareRecord> declareRecordList = this.getDeclareRecordList(entry.getValue());
            if (CollectionUtils.isEmpty(declareRecordList)) {
                continue;
            }
            if (CollectionUtils.isEmpty(entry.getValue()) || projectInfoVo == null) {
                continue;
            }
            String one = getRightsAndInterests(HOUSE, declareRecordList, entry.getValue()) ;
            String two = getSurveyAssetInventoryRightType(entry.getValue(), projectInfoVo) ;
            String three = getSpecialcase(entry.getValue(), projectInfoVo, HOUSE) ;
            String four = getPropertyService(entry.getValue());
            String five = getPropertyCompany(entry.getValue());
            String six = getEstateManagement(entry.getValue());
            String text =  getHouseContent(entry.getValue(), projectInfoVo);
            documentBuilder.writeln(String.format("1、房屋权益状况:%s", one));
            documentBuilder.insertHtml(String.format("2、他权类别:%s", two),true);
            documentBuilder.writeln(String.format("3、特殊情况:%s",three ));
            documentBuilder.writeln(String.format("4、物业服务:%s",four ));
            documentBuilder.writeln(String.format("5、物业公司:%s", five));
            documentBuilder.writeln(String.format("6、物业管理:%s", six));
            documentBuilder.writeln(String.format("7、房产评估综合评价:%s",text));
            documentBuilder.writeln();
        }
    }

    private List<DeclareRecord> getDeclareRecordList(List<SchemeJudgeObject> schemeJudgeObjectList) {
        List<DeclareRecord> declareRecordList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList.stream().forEach(oo -> {
                if (oo.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(oo.getDeclareRecordId());
                    if (declareRecord != null) {
                        declareRecordList.add(declareRecord);
                    }
                }
            });
        }
        return declareRecordList;
    }

    /**
     * 规划条件
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPlanningConditions(List<SchemeJudgeObject> schemeJudgeObjectList) throws Exception {
        HashSet<String> stringHashSet = Sets.newHashSet();
        Set<String> stringSet = Sets.newHashSet();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstateLandStateVo landStateVo = generateBaseExamineService.getBasicEstateLandState();
            if (landStateVo == null) {
                continue;
            }
            if (StringUtils.isEmpty(landStateVo.getPlotRatio())
                    && StringUtils.isEmpty(landStateVo.getBuildingDensity())
                    && StringUtils.isEmpty(landStateVo.getGreenSpaceRate())) {
                continue;
            }
            if (StringUtils.isNotBlank(landStateVo.getPlotRatio())) {
                stringHashSet.add(landStateVo.getPlotRatio());
            }
            if (StringUtils.isNotBlank(landStateVo.getBuildingDensity())) {
                stringHashSet.add(landStateVo.getBuildingDensity());
            }
            if (StringUtils.isNotBlank(landStateVo.getGreenSpaceRate())) {
                stringHashSet.add(landStateVo.getGreenSpaceRate());
            }
            stringSet.add(String.format("%d号%s。", schemeJudgeObject.getId().intValue(), StringUtils.join(stringHashSet, "、")));
            stringHashSet.clear();
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            return String.format("%s 。", StringUtils.join(stringSet, ","));
        } else {
            return " ";
        }
    }

    /**
     * 物业管理
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getEstateManagement(List<SchemeJudgeObject> schemeJudgeObjectList) {
        StringBuilder stringBuilder = new StringBuilder(8);
        List<Integer> integerList = schemeJudgeObjectList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
        stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append("号");
        List<DataPropertyServiceItemVo> dataPropertyServiceItemVoList = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
            if (basicBuilding.getId() == null) {
                continue;
            }
            BasicBuildingVo oo = basicBuildingService.getBasicBuildingVo(basicBuilding);
            if (oo.getDataProperty() != null) {
                List<DataPropertyServiceItemVo> voAs = dataPropertyServiceItemService.getListByMasterId(oo.getDataProperty().getId());
                if (CollectionUtils.isNotEmpty(voAs)) {
                    dataPropertyServiceItemVoList.addAll(voAs);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(dataPropertyServiceItemVoList)) {
            List<DataPropertyServiceItemVo> listA = dataPropertyServiceItemVoList.stream().filter(po -> Objects.equal("基本服务", po.getServiceTypeName())).collect(Collectors.toList());
            //附加服务
            List<DataPropertyServiceItemVo> listB = Lists.newArrayList();
            if (CollectionUtils.isEmpty(listA)) {
                listB = dataPropertyServiceItemVoList;
            } else {
                listB = Lists.newArrayList(CollectionUtils.subtract(dataPropertyServiceItemVoList, listA));
            }
            Set<String> stringSetA = Sets.newHashSet();
            Set<String> stringSetB = Sets.newHashSet();
            Set<String> stringSetFinal = Sets.newHashSet();
            if (CollectionUtils.isNotEmpty(listA)) {
                listA.stream().forEach(oo -> {
                    stringSetFinal.add(StringUtils.isNotBlank(oo.getServiceContentName()) ? oo.getServiceContentName() : "空服务内容");
                    stringSetFinal.add(StringUtils.isNotBlank(oo.getServiceTime()) ? oo.getServiceTime() : "无时间");
                    stringSetFinal.add(StringUtils.isNotBlank(oo.getGradeEvaluationName()) ? oo.getGradeEvaluationName() : "无等级");
                    stringSetA.add(StringUtils.join(stringSetFinal, ""));
                    stringSetFinal.clear();
                });
                stringBuilder.append(StringUtils.join(stringSetA, ",")).append(";");
            }
            if (CollectionUtils.isNotEmpty(listB)) {
                listB.stream().forEach(oo -> {
                    stringSetFinal.add(StringUtils.isNotBlank(oo.getServiceContentName()) ? oo.getServiceContentName() : "空服务内容");
                    stringSetFinal.add(StringUtils.isNotBlank(oo.getServiceTime()) ? oo.getServiceTime() : "无时间");
                    stringSetFinal.add(StringUtils.isNotBlank(oo.getGradeEvaluationName()) ? oo.getGradeEvaluationName() : "无等级");
                    stringSetB.add(StringUtils.join(stringSetFinal, ""));
                    stringSetFinal.clear();
                });
                stringBuilder.append(StringUtils.join(stringSetB, ",")).append(";");
            }
        } else {
            stringBuilder.append("基本服务无,").append("附加服务无;");
        }
        return stringBuilder.toString();
    }

    /**
     * 房产评估综合评价
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getHouseContent(List<SchemeJudgeObject> schemeJudgeObjectList, ProjectInfoVo projectInfoVo) {
        StringBuilder stringBuilder = new StringBuilder(8);
        Set<DataPropertyVo> dataPropertyVos = Sets.newHashSet();
        Set<String> stringSet = Sets.newLinkedHashSet();
        List<SurveyAssetInventoryRightVo> surveyAssetInventoryRightList = Lists.newArrayList();
        List<SurveyAssetInventoryRightRecord> rightRecordList = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
            List<SurveyAssetInventoryRightRecord> list = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(schemeJudgeObject.getDeclareRecordId(), projectInfoVo.getId());
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply != null) {
                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
                BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
                if (basicBuilding.getId() != null) {
                    BasicBuildingVo oo = basicBuildingService.getBasicBuildingVo(basicBuilding);
                    if (oo.getDataProperty() != null) {
                        dataPropertyVos.add(oo.getDataProperty());
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(list)) {
                rightRecordList.addAll(list);
                list.stream().forEach(po -> {
                    List<SurveyAssetInventoryRight> rightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(po.getId());
                    if (CollectionUtils.isNotEmpty(rightList)) {
                        rightList.stream().forEach(right -> {
                            surveyAssetInventoryRightList.add(surveyAssetInventoryRightService.getSurveyAssetInventoryRightVo(right));
                        });
                    }
                });
            }
        }
        if (CollectionUtils.isNotEmpty(dataPropertyVos)) {
            dataPropertyVos.stream().forEach(dataPropertyVo -> {
                stringSet.add(dataPropertyVo.getSocialPrestigeName());
            });
        }
        String[] strings = this.sortMaxBetWeen(stringSet);
        String type = null;
        if (CollectionUtils.isNotEmpty(stringSet)) {
            type = stringSet.stream().findFirst().get();
        }
        //其他
        boolean other = true;
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
            other = surveyAssetInventoryRightList.stream().filter(oo -> Objects.equal("其它", oo.getCategoryName())).count() >= 1;
        } else {
            other = false;
        }
        //特殊情况是否存在
        boolean specialcase = true;
        if (CollectionUtils.isNotEmpty(rightRecordList)) {
            specialcase = rightRecordList.stream().filter(oo -> StringUtils.isNotBlank(oo.getSpecialcase())).count() >= 1;
        } else {
            specialcase = false;
        }
        //他项权利——其他，特殊情况为均空时
        if (!other && !specialcase) {
            if (StringUtils.isNotBlank(type)) {
                if (Objects.equal(type, strings[0])) {
                    stringBuilder.append("对提升房产价值有一定的负面影响 。");
                }
                if (Objects.equal(type, strings[1])) {
                    stringBuilder.append("对提升房产价值影响一般 。");
                }
                if (Objects.equal(type, strings[2])) {
                    stringBuilder.append("对提升房产价值有一定影响 。");
                }
                if (Objects.equal(type, strings[3])) {
                    stringBuilder.append("对提升房产价值有较大影响 。");
                }
            }
        }
        //他项权利——其他为空时，有特殊情况不为空
        if (!other && specialcase) {
            if (StringUtils.isNotBlank(type)) {
                if (Objects.equal(type, strings[0])) {
                    stringBuilder.append("对提升房产价值受到相当大的限制 。");
                }
                if (Objects.equal(type, strings[1])) {
                    stringBuilder.append("对提升房产价值受到比较大的限制 。");
                }
                if (Objects.equal(type, strings[2])) {
                    stringBuilder.append("对提升房产价值受到较大限限制 。");
                }
                if (Objects.equal(type, strings[3])) {
                    stringBuilder.append("对提升房产价值受到一定限制 。");
                }
            }
        }
        //他项权利——其他、特殊情况均不为空时
        if (other && specialcase) {
            if (StringUtils.isNotBlank(type)) {
                if (Objects.equal(type, strings[0])) {
                    stringBuilder.append("对提升房产价值受到重大限制 。");
                }
                if (Objects.equal(type, strings[1])) {
                    stringBuilder.append("对提升房产价值受到严得限制 。");
                }
                if (Objects.equal(type, strings[2])) {
                    stringBuilder.append("对提升房产价值受到相当大的限制 。");
                }
                if (Objects.equal(type, strings[3])) {
                    stringBuilder.append("对提升房产价值受到比较大的限制 。");
                }
            }
        }
        if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
            stringBuilder.append("无");
        }
        return stringBuilder.toString();
    }


    /**
     * 物业公司
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPropertyCompany(List<SchemeJudgeObject> schemeJudgeObjectList) {
        List<DataPropertyVo> dataPropertyVos = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
            if (basicBuilding.getId() == null) {
                continue;
            }
            BasicBuildingVo oo = basicBuildingService.getBasicBuildingVo(basicBuilding);
            if (oo.getDataProperty() != null) {
                dataPropertyVos.add(oo.getDataProperty());
            }
        }
        Set<String> stringSet = Sets.newLinkedHashSet();
        if (CollectionUtils.isNotEmpty(dataPropertyVos)) {
            dataPropertyVos.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getSocialPrestigeName())) {
                    stringSet.add(oo.getSocialPrestigeName());
                }
            });
        }
        String[] strings = this.sortMaxBetWeen(null);
        if (CollectionUtils.isNotEmpty(stringSet)) {
            if (stringSet.stream().filter(oo -> Objects.equal(strings[0], oo)).count() >= 1) {
                return String.format("物业公司信誉%s", "差");
            }
            if (stringSet.stream().filter(oo -> Objects.equal(strings[1], oo)).count() >= 1) {
                return String.format("物业公司信誉%s", "一般");
            }
            if (stringSet.stream().filter(oo -> Objects.equal(strings[2], oo)).count() >= 1) {
                return String.format("物业公司信誉%s", "良好");
            }
            if (stringSet.stream().filter(oo -> Objects.equal(strings[3], oo)).count() >= 1) {
                return String.format("物业公司信誉%s", "优秀");
            }
        }
        return "无";
    }

    /**
     * 物业服务
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPropertyService(List<SchemeJudgeObject> schemeJudgeObjectList) {
        List<DataPropertyServiceItemVo> dataPropertyServiceItemVoList = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicBuilding basicBuilding = generateBaseExamineService.getBasicBuilding();
            if (basicBuilding.getId() == null) {
                continue;
            }
            BasicBuildingVo oo = basicBuildingService.getBasicBuildingVo(basicBuilding);
            if (oo.getDataProperty() != null) {
                List<DataPropertyServiceItemVo> voAs = dataPropertyServiceItemService.getListByMasterId(oo.getDataProperty().getId());
                if (CollectionUtils.isNotEmpty(voAs)) {
                    dataPropertyServiceItemVoList.addAll(voAs);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(dataPropertyServiceItemVoList)) {
            List<DataPropertyServiceItemVo> listA = dataPropertyServiceItemVoList.stream().filter(po -> Objects.equal("基本服务", po.getServiceTypeName())).collect(Collectors.toList());
            //附加服务
            List<DataPropertyServiceItemVo> listB = Lists.newArrayList();
            if (CollectionUtils.isEmpty(listA)) {
                listB = dataPropertyServiceItemVoList;
            } else {
                listB = Lists.newArrayList(CollectionUtils.subtract(dataPropertyServiceItemVoList, listA));
            }
            Set<String> stringSetA = Sets.newHashSet();
            Set<String> stringSetB = Sets.newHashSet();
            if (CollectionUtils.isNotEmpty(listA)) {
                listA.stream().forEach(oo -> {
                    stringSetA.add(oo.getGradeEvaluationName());
                });
            }
            if (CollectionUtils.isNotEmpty(listB)) {
                listB.stream().forEach(oo -> {
                    stringSetB.add(oo.getGradeEvaluationName());
                });
            }
            String typeA = null;
            String typeB = null;
            if (CollectionUtils.isNotEmpty(stringSetA)) {
                this.sortMaxBetWeen(stringSetA);
                typeA = stringSetA.stream().findFirst().get();
            }
            if (CollectionUtils.isNotEmpty(stringSetB)) {
                this.sortMaxBetWeen(stringSetB);
                typeB = stringSetB.stream().findFirst().get();
            }
            Set<String> stringSet = Sets.newLinkedHashSet();
            if (StringUtils.isNotBlank(typeA)) {
                stringSet.add(typeA);
            }
            if (StringUtils.isNotBlank(typeB)) {
                stringSet.add(typeB);
            }
            this.sortMaxBetWeen(stringSet);
            String v = stringSet.stream().findFirst().get();
            String[] strings = this.sortMaxBetWeen(null);
            if (Objects.equal(v, strings[0])) {
                return "物业管理差";
            }
            if (Objects.equal(v, strings[1])) {
                return "物业管理一般";
            }
            if (Objects.equal(v, strings[2])) {
                return "物业管理良好";
            }
            if (Objects.equal(v, strings[3])) {
                return "物业管理优秀";
            }
        }
        return "无";
    }

    /**
     * 优良中差
     *
     * @param stringSet
     */
    private String[] sortMaxBetWeen(Set<String> stringSet) {
        String[] strings = new String[]{"差", "中", "良", "优"};
        if (CollectionUtils.isNotEmpty(stringSet)) {
            Ordering<String> ordering = Ordering.from(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    Integer a = 0;
                    Integer b = 0;
                    for (int i = 0; i < strings.length; i++) {
                        if (Objects.equal(o1, strings[i])) {
                            a = i;
                        }
                        if (Objects.equal(o2, strings[i])) {
                            b = i;
                        }
                    }
                    return Integer.compare(a, b);
                }
            });
            stringSet.stream().sorted(ordering);
        }
        return strings;
    }

    /**
     * 特殊情况
     *
     * @param schemeJudgeObjectList
     * @param projectInfoVo
     * @return
     */
    public String getSpecialcase(List<SchemeJudgeObject> schemeJudgeObjectList, ProjectInfoVo projectInfoVo, String type) {
        StringBuilder stringBuilder = new StringBuilder(8);
        Set<String> stringSet = Sets.newHashSet();
        if (Objects.equal(type, HOUSE)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getDeclareRecordId() == null) {
                    continue;
                }
                List<SurveyAssetInventoryRightRecord> list = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(schemeJudgeObject.getDeclareRecordId(), projectInfoVo.getId());
                if (CollectionUtils.isNotEmpty(list)) {
                    List<SurveyAssetInventoryRightRecordVo> rightRecordVos = list.stream().map(oo -> surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordVo(oo)).collect(Collectors.toList());
                    List<SurveyAssetInventoryRightVo> surveyAssetInventoryRightList = Lists.newArrayList();
                    rightRecordVos.stream().forEach(po -> {
                        List<SurveyAssetInventoryRight> rightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(po.getId());
                        if (CollectionUtils.isNotEmpty(rightList)) {
                            rightList.stream().forEach(oo -> {
                                surveyAssetInventoryRightList.add(surveyAssetInventoryRightService.getSurveyAssetInventoryRightVo(oo));
                            });
                        }
                    });
                    //特殊情况是否存在
                    boolean flag = true;
                    if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                        flag = surveyAssetInventoryRightList.stream().filter(oo -> Objects.equal("其它", oo.getCategoryName())).count() >= 1;
                    } else {
                        flag = false;
                    }
                    boolean specialcase = rightRecordVos.stream().filter(oo -> StringUtils.isNotBlank(oo.getSpecialcase())).count() >= 1;
                    if (!flag && !specialcase) {
                        stringSet.add(String.format("%d估价对象对产权清晰、权力明确、无特定转让限制", schemeJudgeObject.getId().intValue()));
                    }
                    if (!flag && specialcase) {
                        stringSet.add(String.format("%d估价对象对产权清晰、权力明确、转让受特定限制", schemeJudgeObject.getId().intValue()));
                    }
                    if (flag && specialcase) {
                        stringSet.add(String.format("%d估价对象对产权清晰、权力明确、转让受到限制", schemeJudgeObject.getId().intValue()));
                    }
                }
            }
        }
        if (Objects.equal(type, LAND)) {
            Set<String> stringHashSet = Sets.newHashSet();
            //获得他项权利  和 关联估价对象
            schemeJudgeObjectList.stream().forEach(oo -> {
                if (oo.getDeclareRecordId() != null) {
                    List<SurveyAssetInventoryRightRecord> list = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(oo.getDeclareRecordId(), projectInfoVo.getId());
                    if (CollectionUtils.isNotEmpty(list)) {
                        list.stream().forEach(po -> {
                            stringHashSet.add(po.getSpecialcase());
                        });
                        stringSet.add(String.format("%s%s%s",
                                oo.getId().toString(),
                                SCHEME_JUDGEOBJECT_NAME,
                                stringHashSetJoin(stringHashSet)));
                    }
                }
            });
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            appendRightsAndInterest(stringHashSetJoin(stringSet), stringBuilder, ";");
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无");
        }
        return stringBuilder.toString();
    }

    /**
     * 他权类别
     * (根据选定他项权利土地类型下选定的类别及已经关联的权证所对应的估价对象号进行描述)
     *
     * @return
     */
    public String getSurveyAssetInventoryRightType(List<SchemeJudgeObject> schemeJudgeObjectList, ProjectInfoVo projectInfoVo) {
        StringBuilder stringBuilder = new StringBuilder(8);
        List<SurveyAssetInventoryRightVo> rightList = Lists.newArrayList();
        Map<SurveyAssetInventoryRightVo, SchemeJudgeObject> rightVoSchemeJudgeObjectMap = Maps.newHashMap();
        //获得他项权利  和 关联估价对象
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && projectInfoVo != null) {
            schemeJudgeObjectList.stream().forEach(oo -> {
                if (oo.getDeclareRecordId() != null) {
                    List<SurveyAssetInventoryRightRecord> list = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(oo.getDeclareRecordId(), projectInfoVo.getId());
                    if (CollectionUtils.isNotEmpty(list)) {
                        list.stream().forEach(po -> {
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightBy(po.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                List<SurveyAssetInventoryRightVo> voList = surveyAssetInventoryRightService.getVoList(surveyAssetInventoryRightList);
                                voList.stream().forEach(surveyAssetInventoryRightVo -> rightVoSchemeJudgeObjectMap.put(surveyAssetInventoryRightVo, oo));
                                rightList.addAll(voList);
                            }
                        });
                    }
                }
            });
        }
        //利用他权类别 对 他项权利进行分组
        Map<String, List<SurveyAssetInventoryRightVo>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(rightList)) {
            rightList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getCategoryName())){
                    List<SurveyAssetInventoryRightVo> rightVoListA = stringListMap.get(oo.getCategoryName());
                    if (CollectionUtils.isEmpty(rightVoListA)) {
                        rightVoListA = Lists.newArrayList();
                    }
                    rightVoListA.add(oo);
                    stringListMap.put(oo.getCategoryName(), rightVoListA);
                }
            });
        }
        if (!stringListMap.isEmpty() && !rightVoSchemeJudgeObjectMap.isEmpty()) {
            for (Map.Entry<String, List<SurveyAssetInventoryRightVo>> entry : stringListMap.entrySet()) {
                List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
                List<SurveyAssetInventoryRightVo> rightVoList = entry.getValue();
                Set<String> stringHashSet = Sets.newHashSet();
                rightVoList = generateCommonMethod.removeDuplicate(rightVoList);
                for (SurveyAssetInventoryRightVo surveyAssetInventoryRightVo : rightVoList) {
                    SchemeJudgeObject schemeJudgeObject = rightVoSchemeJudgeObjectMap.get(surveyAssetInventoryRightVo);
                    if (schemeJudgeObject != null) {
                        stringHashSet.add(surveyAssetInventoryRightVo.getRemark());
                        judgeObjectList.add(schemeJudgeObject);
                    }
                }
                if (CollectionUtils.isEmpty(judgeObjectList)) {
                    continue;
                } else {
                    judgeObjectList = generateCommonMethod.removeDuplicate(judgeObjectList);
                }
                List<Integer> integerList = judgeObjectList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
                stringBuilder.append("<p style=\"text-indent:2em\">")
                        .append(entry.getKey())
                        .append(generateCommonMethod.convertNumber(integerList))
                        .append(SCHEME_JUDGEOBJECT_NAME);
                appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, "。");
                stringBuilder.append("</p>");
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("<p style=\"text-indent:2em\">");
            stringBuilder.append("无");
            stringBuilder.append("</p>");
        }
        return stringBuilder.toString();
    }

    /**
     * 权益状况
     *
     * @param type
     * @param declareRecordList
     * @param schemeJudgeObjectList
     * @return
     * @throws Exception
     */
    private String getRightsAndInterests(String type, List<DeclareRecord> declareRecordList, List<SchemeJudgeObject> schemeJudgeObjectList) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        List<Integer> integerList = schemeJudgeObjectList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
        Set<String> stringHashSet = Sets.newHashSet();
        if (Objects.equal(HOUSE, type)) {
            stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append(SCHEME_JUDGEOBJECT_NAME);
            //房屋性质
            declareRecordList.stream().forEach(oo -> {
                if (oo.getNature() != null) {
                    String v = oo.getNature().toString();
                    stringHashSet.add(baseDataDicService.getNameById(v));
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, ";");
            //规划用途 (证载用途)
            declareRecordList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getCertUse())) {
                    if (NumberUtils.isNumber(oo.getCertUse())) {
                        stringHashSet.add(baseDataDicService.getNameById(oo.getCertUse()));
                    } else {
                        stringHashSet.add(oo.getCertUse());
                    }
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, ";");
            //共有情况
            declareRecordList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getPublicSituation())) {
                    if (NumberUtils.isNumber(oo.getPublicSituation())) {
                        stringHashSet.add(baseDataDicService.getNameById(oo.getPublicSituation()));
                    } else {
                        stringHashSet.add(oo.getPublicSituation());
                    }
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, ";");
            //权益
            declareRecordList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getOwnership())) {
                    stringHashSet.add(oo.getOwnership());
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, "。");
        }
        if (Objects.equal(LAND, type)) {
            stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append(SCHEME_JUDGEOBJECT_NAME);
            //类型 (土地证)
            declareRecordList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getLandCertUse())) {
                    String v = oo.getLandCertUse();
                    if (NumberUtils.isNumber(oo.getLandCertUse())) {
                        v = baseDataDicService.getNameById(v);
                        if (StringUtils.isNotBlank(v)) {
                            v = oo.getLandCertUse();
                        }
                    } else {
                        v = oo.getLandCertUse();
                    }
                    stringHashSet.add(v);
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, ";");
            //使用权类型
            declareRecordList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getLandPracticalUse())) {
                    stringHashSet.add(oo.getLandPracticalUse());
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, "；");
            //权益
            declareRecordList.stream().forEach(oo -> {
                if (StringUtils.isNotBlank(oo.getOwnership())) {
                    stringHashSet.add(oo.getOwnership());
                }
            });
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, "。");
        }
        if (StringUtils.isEmpty(stringBuilder.toString().trim())) {
            stringBuilder.append("无");
        }
        return stringBuilder.toString();
    }

    /**
     * join 连接Set
     *
     * @param stringHashSet
     * @return
     */
    private String stringHashSetJoin(Set<String> stringHashSet) {
        return generateCommonMethod.stringHashSetJoin(stringHashSet, ",");
    }

    /**
     * 添加数据
     *
     * @param string
     * @param stringBuilder
     */
    private void appendRightsAndInterest(String string, StringBuilder stringBuilder, String suffix) {
        if (StringUtils.isNotBlank(string)) {
            if (StringUtils.isNotBlank(string.trim())) {
                stringBuilder.append(string);
                if (StringUtils.isNotBlank(suffix)) {
                    stringBuilder.append(suffix);
                }
            }
        }
    }
}
