package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/3/7 16:32
 * @description:提取一些方法(报告生成)
 */
@Component
public class GenerateCommonMethod {

    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyCommonService surveyCommonService;

    public final String SchemeJudgeObjectName = "委估对象";
    public final String errorStr = "无";

    /**
     * 建筑结构类别
     *
     * @param schemeJudgeObjectList
     * @param projectInfo
     * @return
     * @throws Exception
     */
    public String getBuildingStructureCategory(List<SchemeJudgeObject> schemeJudgeObjectList, ProjectInfo projectInfo) throws Exception {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        for (ProjectPhase projectPhase : projectPhases) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectInfo.getId());
                query.setProjectPhaseId(projectPhase.getId());
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(projectPlanDetails.getId());
                        if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                            if (generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory() != null) {
                                String key = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory());
                                if (StringUtils.isNotBlank(key)) {
                                    this.putStringListMap(stringListMap, schemeJudgeObject, key);
                                }
                            }
                        }
                    }
                }
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    //房地产总价
    public BigDecimal getTotalRealEstate(Integer areId) {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areId);
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimal = schemeJudgeObject.getEvaluationArea().multiply(schemeJudgeObject.getPrice());
                        temp = temp.add(bigDecimal);
                    }
                }
            }
        }
        return temp;
    }

    /**
     * 权利人
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPowerPerson(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getCertUse())).collect(Collectors.toList());
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObjectList.get(i).getDeclareRecordId());
            if (declareRecord != null) {
                if (StringUtils.isNotBlank(declareRecord.getOwnership())) {
                    this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), declareRecord.getOwnership());
                }
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 使用权类型
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getUseRightType(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                String key = declareRecord.getLandRightNature();
                if (StringUtils.isNotBlank(key)) {
                    this.putStringListMap(stringListMap, schemeJudgeObject, key);
                }
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, null);
        if (StringUtils.isEmpty(s.trim())) {
            s = errorStr;
        }
        return s;
    }

    /**
     * 建筑面积及评估面积
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getBuildingAndAssessArea(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getEvaluationArea() != null).collect(Collectors.toList());
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            if (schemeJudgeObjectList.get(i).getEvaluationArea() != null) {
                this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), schemeJudgeObjectList.get(i).getEvaluationArea().toString());
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, "㎡");
        return s;
    }

    /**
     * 实际用途
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPracticalUse(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getPracticalUse())).collect(Collectors.toList());
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            String value = null;
            if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPracticalUse())) {
                value = baseDataDicService.getNameById(schemeJudgeObjectList.get(i).getPracticalUse());
            } else {
                value = schemeJudgeObjectList.get(i).getPracticalUse();
            }
            if (StringUtils.isNotBlank(value)) {
                this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), value);
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, null);
        return s;
    }

    /**
     * 设定用途
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public String getSetUses(List<SchemeJudgeObject> schemeJudgeObjectList) {
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getSetUse() != null).collect(Collectors.toList());
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            if (schemeJudgeObjectList.get(i).getSetUse() != null) {
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObjectList.get(i).getSetUse());
                if (dataSetUseField != null) {
                    this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), dataSetUseField.getName());
                }
            }
        }
        String s = this.getSchemeJudgeObjectListShowName(stringListMap, null);
        return s;
    }

    public String writeStringInDocument(String content) throws Exception {
        String localPath = getLocalPath();
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        this.settingBuildingTable(builder);
        if (StringUtils.isNotBlank(content)) {
            builder.writeln(getWarpCssHtml(content));
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 获取 (楼盘名称和街道号) 的估价对象集合
     *
     * @param schemeJudgeObjectList
     * @param projectInfo
     * @return
     * @throws Exception
     */
    public LinkedHashMap<String, List<SchemeJudgeObject>> getSchemeJudgeObjectLinkedHashMap(List<SchemeJudgeObject> schemeJudgeObjectList, ProjectInfo projectInfo) throws Exception {
        LinkedHashMap<String, List<SchemeJudgeObject>> linkedHashMap = Maps.newLinkedHashMap();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return false;
                    }
                    return false;
                }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(schemeJudgeObjectList) || CollectionUtils.isEmpty(projectPhases)) {
            return linkedHashMap;
        }
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            //是否可以出局报告
            if (declareRecord.getBisPartIn()) {
                String name = declareRecord.getStreetNumber();
                if (StringUtils.isNotBlank(name)) {
                    List<SchemeJudgeObject> judgeObjectList = linkedHashMap.get(name);
                    if (CollectionUtils.isEmpty(judgeObjectList)) {
                        judgeObjectList = Lists.newArrayList();
                    }
                    judgeObjectList.add(schemeJudgeObject);
                    judgeObjectList = this.removeDuplicate(judgeObjectList);
                    linkedHashMap.put(name, judgeObjectList);
                }
            }
        }
        //再次重新拼接
        LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap = Maps.newLinkedHashMap();
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry<String, List<SchemeJudgeObject>> entry : linkedHashMap.entrySet()) {
                String name = null;
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(projectInfo.getId());
                query.setProjectPhaseId(projectPhases.get(0).getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        //只找一个楼盘
                        try {
                            name = new GenerateBaseExamineService(projectPlanDetails.getId()).getEstate().getName();
                        } catch (Exception e) {
                            name = null;
                        }
                        if (StringUtils.isNotBlank(name)) {
                            break;
                        }
                    }
                }
                if (StringUtils.isNotBlank(name)) {
                    name = String.format("%s号%s", entry.getKey(), name);
                    listLinkedHashMap.put(name, entry.getValue());
                } else {
                    continue;
                }
            }
        }
        return listLinkedHashMap;
    }

    /**
     * 相同楼盘名称的估价对象集合
     *
     * @return
     * @throws Exception
     */
    public LinkedHashMap<String, List<SchemeJudgeObject>> getLinkedHashMapEstateNameSchemeJudgeObjectList(Integer areaId) throws Exception {
        LinkedHashMap<String, List<SchemeJudgeObject>> listLinkedHashMap = Maps.newLinkedHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            if (basicApply == null || basicApply.getId() == null) {
                continue;
            }
            GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(basicApply);
            BasicEstate basicEstate = generateBaseExamineService.getEstate();
            if (basicEstate == null || StringUtils.isEmpty(basicEstate.getName())) {
                continue;
            }
            List<SchemeJudgeObject> schemeJudgeObjects = listLinkedHashMap.get(basicEstate.getName());
            if (CollectionUtils.isEmpty(schemeJudgeObjects)) {
                schemeJudgeObjects = Lists.newArrayList();
            }
            schemeJudgeObjects.add(schemeJudgeObject);
            listLinkedHashMap.put(basicEstate.getName(), schemeJudgeObjects);
        }
        return listLinkedHashMap;
    }

    /**
     * 基本排序
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public List<SchemeJudgeObject> getSortSchemeJudgeObject(List<SchemeJudgeObject> schemeJudgeObjectList) {
        Ordering<SchemeJudgeObject> ordering = Ordering.from(new Comparator<SchemeJudgeObject>() {
            @Override
            public int compare(SchemeJudgeObject o1, SchemeJudgeObject o2) {
                Integer a = Integer.parseInt(o1.getNumber().substring(0, 1));
                Integer b = Integer.parseInt(o2.getNumber().substring(0, 1));
                int num = a.compareTo(b);
                return num;
            }
        });
        schemeJudgeObjectList.sort(ordering);
        return schemeJudgeObjectList;
    }

    /**
     * 填充
     *
     * @param stringListMap
     * @param schemeJudgeObject
     * @param key
     */
    public void putStringListMap(Map<String, List<Integer>> stringListMap, SchemeJudgeObject schemeJudgeObject, String key) {
        if (StringUtils.isNotBlank(key)) {
            if (NumberUtils.isNumber(schemeJudgeObject.getNumber())) {
                List<Integer> integerList = stringListMap.get(key);
                if (CollectionUtils.isNotEmpty(integerList)) {
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                } else {
                    integerList = Lists.newArrayList();
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getNumber()));
                }
                stringListMap.put(key, integerList);
            }
        }
    }

    public String getSchemeJudgeObjectListShowName(Map<String, List<Integer>> stringListMap, String suffix) {
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder stringBuilder = new StringBuilder(8);
        Set<String> stringSetTemp = Sets.newHashSet();
        if (!stringListMap.isEmpty()) {
            for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
                if (entry.getValue().size() > 0) {
                    stringSet.add(this.convertNumber(entry.getValue()));
                    stringSetTemp.add(entry.getKey());
                }
            }
            List<String> stringList = Lists.newArrayList(stringSet);
            //排一次序
            Ordering<String> ordering = Ordering.from((o1, o2) -> {
                int x = Integer.parseInt(o1.substring(0, 1));
                int y = Integer.parseInt(o2.substring(0, 1));
                return (x > y) ? -1 : ((x == y) ? 0 : 1);
            });
            stringList.sort(ordering);
            stringSet = Sets.newHashSet(stringList);
            if (stringList.size() > 1) {
                stringBuilder.append(this.toSetStringMerge(stringSet, ","));
                stringBuilder.append(this.SchemeJudgeObjectName);
            }
            stringBuilder.append(this.toSetStringSplitCommaSuffix(stringSetTemp, ",", suffix));
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public String getSchemeJudgeObjectShowName(SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder(24);
        if (schemeJudgeObject == null) {
            return "";
        }
        if (StringUtils.isNotBlank(schemeJudgeObject.getNumber())) {
            String[] strings = schemeJudgeObject.getNumber().split(",");
            //显示委估对象最多3个
            final int max = 3;
            if (strings.length > 3) {
                //合并委估对象大于了3个以上的情况
                for (int i = 0; i < max; i++) {
                    stringBuilder.append(strings[i]);
                    if (i != max - 1) {
                        stringBuilder.append(",");
                    }
                }
            } else {
                stringBuilder.append(schemeJudgeObject.getNumber());
                //拆分情况
                if (schemeJudgeObject.getSplitNumber() != null) {
                    stringBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
                }
            }
            stringBuilder.append("号");
            if (strings.length > 3) {
                stringBuilder.append("等");
            }
            if (false) {
                stringBuilder.append(SchemeJudgeObjectName);
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            if (StringUtils.isNotBlank(schemeJudgeObject.getName())) {
                stringBuilder.append(schemeJudgeObject.getName());
            } else {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public String getPercentileSystem(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        bigDecimal = bigDecimal.multiply(new BigDecimal(100));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return String.format("%s%s", bigDecimal.toString(), "%");
    }

    /**
     * 按照报告类型
     *
     * @param bigDecimal
     * @return
     */
    public String getBigDecimalRound(BigDecimal bigDecimal, boolean tenThousand) {
        //四舍五入,并且取到0
        bigDecimal = bigDecimal.setScale(0, BigDecimal.ROUND_UP);
        if (tenThousand) {
            bigDecimal = bigDecimal.divide(new BigDecimal(10000));
        }
        if (bigDecimal.doubleValue() < 0) {
            //取绝对值
            bigDecimal = new BigDecimal(Math.abs(bigDecimal.doubleValue()));
            String s = bigDecimal.toString();
            s = s.substring(1, bigDecimal.toString().length());
//            bigDecimal = new BigDecimal(s);
        }
        return bigDecimal.toString();
    }

    /**
     * 获取合并的估价对象
     *
     * @param schemeJudgeObjectsA
     * @return
     */
    public List<SchemeJudgeObject> getByRootAndChildSchemeJudgeObjectList(List<SchemeJudgeObject> schemeJudgeObjectsA, boolean declareRecordFilter) {
        Set<SchemeJudgeObject> schemeJudgeObjectHashSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectsA)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectsA) {
                if (schemeJudgeObject.getBisMerge()) {
                    List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
                        schemeJudgeObjectHashSet.addAll(schemeJudgeObjects);
                    }
                }
                schemeJudgeObjectHashSet.add(schemeJudgeObject);
            }
        }
        List<SchemeJudgeObject> objectList = Lists.newArrayList(schemeJudgeObjectHashSet);
        if (declareRecordFilter) {
            objectList = objectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getDeclareRecordId() != null).collect(Collectors.toList());
        }
        if (CollectionUtils.isNotEmpty(objectList)) {
            objectList = getSortSchemeJudgeObject(objectList);
            objectList = this.removeDuplicate(objectList);
        }
        return objectList;
    }


    /**
     * 数字转换
     *
     * @param numbers
     * @return
     */
    public String convertNumber(List<Integer> numbers) {
        if (CollectionUtils.isNotEmpty(numbers)) {
            Collections.sort(numbers);
            Integer[] ints = new Integer[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                ints[i] = numbers.get(i);
            }
            String text = this.convert(ints, 0);
            text = text.substring(0, text.length() - 1);
            return text;
        }
        return " ";
    }

    /**
     * 获取连续的数字组合
     *
     * @param ints
     * @param index
     * @return
     */
    public String convert(Integer[] ints, int index) {
        int end = index;
        //结束条件，遍历完数组
        if (ints.length == index) {
            return "";
        } else {
            for (int i = index; i < ints.length; i++) {
                if (i < ints.length - 1) {
                    if (ints[i] + 1 == ints[i + 1]) {
                        end = i;
                    } else {
                        if (i > index)
                            end = end + 1;
                        break;
                    }
                } else {
                    if (end == ints.length - 2) {
                        end = ints.length - 1;
                        break;
                    }
                }
            }
            //相等说明不连续
            if (index == end)
                return ints[index] + "," + convert(ints, end + 1);
                //连续
            else
                return ints[index] + "-" + ints[end] + "," + convert(ints, end + 1);

        }
    }

    public String toSetStringSplitSpace(Set<String> stringSet) {
        return toSetStringMerge(stringSet, " ");
    }

    public String toSetStringSplitComma(Set<String> stringSet) {
        return toSetStringMerge(stringSet, ",");
    }

    public String toSetStringSplitNull(Set<String> stringSet) {
        return toSetStringMerge(stringSet, null);
    }

    public String toSetStringMerge(Set<String> stringSet, String split) {
        return this.toSetStringSplitCommaSuffix(stringSet, split, null);
    }

    public String toSetStringSplitCommaSuffix(Set<String> stringSet, String split, String suffix) {
        StringBuilder builder = new StringBuilder(16);
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (StringUtils.isNotBlank(suffix)) {
                        builder.append(suffix);
                    }
                    if (i != stringList.size() - 1 && stringList.size() != 1) {
                        if (StringUtils.isNotBlank(split)) {
                            builder.append(split);
                        }
                    }
                }
            }
        } else {
            return " ";
        }
        return builder.toString();
    }


    public String getListByIndex(Set<String> stringSet, int index) {
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            if (stringList.size() - 1 >= index) {
                return stringList.get(index);
            }
        }
        return null;
    }

    public String getLocalPath() {
        return getLocalPath(null);
    }

    public String getLocalPath(String title) {
        if (StringUtils.isEmpty(title)) {
            title = String.format("%s%s", "报告模板", DateUtils.format(new Date(), DateUtils.DATE_CHINESE_PATTERN));
        }
        return String.format("%s\\%s%s%s", baseAttachmentService.createTempDirPath(), title, UUID.randomUUID().toString(), ".doc");
    }

    /**
     * 填充值
     *
     * @param text
     * @param bookmark
     * @param textMap
     * @param bookmarkMap
     * @param key
     * @param value
     */
    public void putValue(boolean text, boolean bookmark, boolean fileFlag, Map<String, String> textMap, Map<String, String> bookmarkMap, Map<String, String> fileMap, String key, String value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (text) {
            textMap.put(String.format("${%s}", key), value);
        }
        if (bookmark) {
            bookmarkMap.put(key, value);
        }
        if (fileFlag) {
            fileMap.put(String.format("${%s}", key), value);
        }
    }

    public List<String> specialTreatment(List<String> strings) {
        List<String> stringList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(strings)) {
            for (String s : strings) {
                String temp = s.substring(2, s.length() - 1);
                stringList.add(temp);
            }
        }
        return stringList;
    }

    /**
     * list去重复元素
     *
     * @param list
     * @return
     */
    public List removeDuplicate(List list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 为图片设置间隔
     *
     * @param builder
     * @param imgPath
     * @throws Exception
     */
    public void builderInsertImage(DocumentBuilder builder, String imgPath) throws Exception {
        if (StringUtils.isNotBlank(imgPath) && FileUtils.checkImgSuffix(imgPath)) {
            File file = new File(imgPath);
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
            int targetWidth = sourceImg.getWidth() > 400 ? 400 : sourceImg.getWidth();
            builder.insertImage(imgPath, targetWidth, getImageTargeHeight(sourceImg.getWidth(), targetWidth, sourceImg.getHeight()));
        }
    }

    public int getImageTargeHeight(int sourceWidth, int targeWidth, int sourceHeight) {
        int targetHeight = sourceHeight / (sourceWidth / targeWidth);
        return targetHeight;
    }

    /**
     * 设置表格属性
     *
     * @param builder
     * @throws Exception
     */
    public void settingBuildingTable(DocumentBuilder builder) throws Exception {
        builder.getFont().setSize(9);
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        //设置表格边框的宽度
        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
        //设置具体宽度
        builder.getCellFormat().setWidth(100);
        Style style = builder.getParagraphFormat().getStyle();
        style.setName(AsposeUtils.ImitationSongGB2312FontName);
//        builder.getParagraphFormat().setStyle(style);
        //水平居中
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);

    }


    public void setDefaultDocumentBuilderSetting(DocumentBuilder builder) throws Exception {
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        builder.getFont().setSize(14.5);
        //
//        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
    }

    /**
     * 合并表格
     *
     * @param mergeCellModelList
     * @param table
     */
    public void mergeCellTable(Set<MergeCellModel> mergeCellModelList, Table table) {
        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
            }
        }
    }

    /**
     * 获取包装后的html，与当前word字体格式一致
     *
     * @param html
     * @return
     */
    public String getWarpCssHtml(String html) {
        return String.format("<div style='font-family:仿宋_GB2312;line-height:150%%;font-size:14.0pt'>%s</div>", html);
    }

    /**
     * 获取缩进后html
     * @param html
     * @return
     */
    public String getIndentHtml(String html){
        return String.format("<p style=\"text-indent:2em\">%s</p>",html);
    }

    /**
     * 估价对象合并描述
     * map 1:商业  2:商业  3:商业  4:住宅  explain:证载用途为
     * 1-3、4证载用途为商业、住宅
     *
     * @param map
     * @return
     */
    public String judgeSummaryDesc(Map<Integer, String> map, String explain, Boolean isShowNumber) {
        if (map == null || map.size() <= 0) return "";
        Map<String, List<Integer>> listMap = getStringListMap(map);
        StringBuilder judgeBuilder = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            judgeBuilder.append(convertNumber(stringListEntry.getValue())).append("、");
            contentBuilder.append(stringListEntry.getKey()).append("、");
        }
        String judgeString = StringUtils.strip(judgeBuilder.toString(), "、");
        String contentStrig = StringUtils.strip(contentBuilder.toString(), "、");
        if (listMap.size() <= 1 && isShowNumber == Boolean.FALSE) {
            return contentStrig;
        }
        return String.format("%s%s%s", judgeString, StringUtils.defaultString(explain), contentStrig);
    }

    private Map<String, List<Integer>> getStringListMap(Map<Integer, String> map) {
        Map<String, List<Integer>> listMap = Maps.newHashMap();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (listMap.containsKey(entry.getValue())) {
                List<Integer> list = listMap.get(entry.getValue());
                list.add(entry.getKey());
            } else {
                listMap.put(entry.getValue(), Lists.newArrayList(entry.getKey()));
            }
        }
        return listMap;
    }

    /**
     * 估价对象分别描述
     * map 1:商业  2:商业  3:商业  4:住宅  explain:证载用途为 symbol:,
     * 1号估价对象证载用途为商业,2号估价对象证载用途为商业,3号估价对象证载用途为商业,4号估价对象证载用途为住宅
     *
     * @param map
     * @return
     */
    public String judgeEachDesc(Map<Integer, String> map, String explain, String symbol, Boolean isShowJudgeNumner) {
        if (map == null || map.size() <= 0) return "";
        Map<String, List<Integer>> listMap = getStringListMap(map);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            if (listMap.size() <= 1 && isShowJudgeNumner == Boolean.FALSE) {
                return stringListEntry.getKey();
            }
            builder.append(convertNumber(stringListEntry.getValue())).append(StringUtils.defaultString(explain)).append(stringListEntry.getKey()).append(symbol);
        }
        return builder.toString();
    }

    /**
     * 字符串合并描述
     * 日照：好 采光：好 通风：差 隔音：差  explain:效果 symbol：，
     * 日照、采光效果好，通风、隔音效果差
     *
     * @param map
     * @param explain
     * @return
     */
    public String stringSummaryDesc(Map<String, String> map, String explain, String symbol) {
        if (map == null || map.size() <= 0) return "";
        Map<String, List<String>> listMap = Maps.newHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (listMap.containsKey(entry.getValue())) {
                List<String> list = listMap.get(entry.getValue());
                list.add(entry.getKey());
            } else {
                listMap.put(entry.getValue(), Lists.newArrayList(entry.getKey()));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<String>> stringListEntry : listMap.entrySet()) {
            List<String> strings = stringListEntry.getValue();
            for (int i = 0; i < strings.size(); i++) {
                stringBuilder.append(strings.get(i));
                if (strings.size() - 1 > i)
                    stringBuilder.append("、");
            }
            stringBuilder.append(explain).append(stringListEntry.getKey()).append(symbol);
        }
        return stringBuilder.toString();
    }

    /**
     * 处理字符串中错误的标点符号
     *
     * @param str
     * @return
     */
    public String trim(String str) {
        if (StringUtils.isBlank(str)) return str;
        str = str.replaceAll(",+", ",").replaceAll(";+", ";")
                .replaceAll("，+", "，").replaceAll("、+", "、")
                .replaceAll("。+", "。").replaceAll("；+", "；")
                .replaceAll("[,|，|、|;|；|.|。]+$", "。");
        return str;
    }

    /**
     * join 连接Set
     *
     * @param stringHashSet
     * @return
     */
    public String stringHashSetJoin(Set<String> stringHashSet, String separator) {
        if (CollectionUtils.isNotEmpty(stringHashSet)) {
            String s = StringUtils.join(stringHashSet, separator);
            stringHashSet.clear();
            if (StringUtils.isNotBlank(s)) {
                return s;
            }
        }
        return "";
    }

    /**
     * 提取数字
     *
     * @param text
     * @return
     */
    public String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return "0";
        }
        if (NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : "0";
    }

    /**
     * 估价对象编号zhuanintege
     *
     * @param number
     * @return
     */
    public Integer parseIntJudgeNumber(String number) {
        if (StringUtils.isBlank(number)) return null;
        if (number.contains(","))
            number = number.split(",")[0];
        if (number.contains("-"))
            number = number.split("-")[0];
        Integer i = Integer.valueOf(number);
        return i;
    }

    public List<Integer> getJudgeNumberByIds(List<Integer> judgeObjectIds) {
        if (CollectionUtils.isEmpty(judgeObjectIds)) return null;
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getListByIds(judgeObjectIds);
        return LangUtils.transform(judgeObjectList, o -> parseIntJudgeNumber(o.getNumber()));
    }
}
