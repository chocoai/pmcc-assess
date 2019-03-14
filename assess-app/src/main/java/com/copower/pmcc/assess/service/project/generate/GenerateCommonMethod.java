package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
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
import java.util.*;
import java.util.List;
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

    public final String SchemeJudgeObjectName = "委估对象";
    public final String errorStr = "无";

    /**
     * 建筑结构类别
     * @param schemeJudgeObjectList
     * @param projectInfo
     * @return
     * @throws Exception
     */
    public String getBuildingStructureCategory(List<SchemeJudgeObject> schemeJudgeObjectList,ProjectInfo projectInfo) throws Exception{
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
                        GenerateBaseExamineService generateBaseExamineService = new  GenerateBaseExamineService(projectPlanDetails.getId());
                        if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                            if (generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory() != null) {
                                String key = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory());
                                this.putStringListMap(stringListMap, schemeJudgeObject, key);
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

    /**
     * 权利人
     * @param schemeJudgeObjectList
     * @return
     */
    public String getPowerPerson(List<SchemeJudgeObject> schemeJudgeObjectList){
        Map<String, List<Integer>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return null;
        }
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> StringUtils.isNotBlank(schemeJudgeObject.getCertUse())).collect(Collectors.toList());
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObjectList.get(i).getDeclareRecordId());
            if (declareRecord != null) {
                this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), declareRecord.getOwnership());
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
                String key = declareRecord.getUseRightType();
                this.putStringListMap(stringListMap, schemeJudgeObject, key);
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
            if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPracticalUse())) {
                this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), baseDataDicService.getNameById(schemeJudgeObjectList.get(i).getPracticalUse()));
            } else {
                this.putStringListMap(stringListMap, schemeJudgeObjectList.get(i), schemeJudgeObjectList.get(i).getPracticalUse());
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
            builder.writeln(content);
        }
        doc.save(localPath);
        return localPath;
    }


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
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(projectInfo.getId());
                    query.setProjectPhaseId(projectPhase.getId());
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (declareRecord != null && declareRecord.getBisPartIn()) {
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = new GenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null) {
                                    String name = generateBaseExamineService.getEstate().getName();
                                    if (StringUtils.isNotBlank(name)) {
                                        name = String.format("%s%s号", name, declareRecord.getStreetNumber());
                                        List<SchemeJudgeObject> judgeObjectList = linkedHashMap.get(name);
                                        if (CollectionUtils.isEmpty(judgeObjectList)) {
                                            judgeObjectList = Lists.newArrayList();
                                        }
                                        judgeObjectList.add(schemeJudgeObject);
                                        linkedHashMap.put(name, judgeObjectList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

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
        }
        return objectList;
    }


    /**
     * 获取重复的集合
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public List<SchemeJudgeObject> unionSchemeJudgeObject(List<SchemeJudgeObject> schemeJudgeObjectList) {
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return new ArrayList<SchemeJudgeObject>(0);
        }
        StringBuilder builder = new StringBuilder(16);
        Map<String, SchemeJudgeObject> schemeJudgeObjectMap = Maps.newHashMap();
        //让重复的变成差集
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                builder.append(declareRecord.getStreetNumber()).append("-").append(declareRecord.getFloor()).append("-");
                builder.append(declareRecord.getUnit()).append("-").append(declareRecord.getBuildingNumber()).append("-");
                builder.append(declareRecord.getAttachedNumber());
                schemeJudgeObjectMap.put(builder.toString(), schemeJudgeObject);
                builder.delete(0, builder.toString().length());
            }
        }
        if (!schemeJudgeObjectMap.isEmpty()) {
            List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
            for (Map.Entry<String, SchemeJudgeObject> objectEntry : schemeJudgeObjectMap.entrySet()) {
                judgeObjectList.add(objectEntry.getValue());
            }
            if (CollectionUtils.isNotEmpty(judgeObjectList) && schemeJudgeObjectList.size() > judgeObjectList.size()) {
                //得到差集
                Collection<SchemeJudgeObject> collection = CollectionUtils.subtract(schemeJudgeObjectList, judgeObjectList);
                if (CollectionUtils.isNotEmpty(collection)) {
                    //重要:得到差集对象  ==> 获取带有特征的估价对象
                    SchemeJudgeObject schemeJudgeObject = collection.stream().findFirst().get();
                    DeclareRecord target = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    if (target != null) {
                        List<SchemeJudgeObject> schemeJudgeObjects = Lists.newArrayList();
                        for (SchemeJudgeObject judgeObject : schemeJudgeObjectList) {
                            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
                            if (declareRecord != null) {
                                boolean streetNumber = com.google.common.base.Objects.equal(target.getStreetNumber(), declareRecord.getStreetNumber());
                                boolean floor = com.google.common.base.Objects.equal(target.getFloor(), declareRecord.getFloor());
                                boolean unit = com.google.common.base.Objects.equal(target.getUnit(), declareRecord.getUnit());
                                boolean buildingNumber = com.google.common.base.Objects.equal(target.getBuildingNumber(), declareRecord.getBuildingNumber());
                                boolean attachedNumber = Objects.equal(target.getAttachedNumber(), declareRecord.getAttachedNumber());
                                if (streetNumber && floor && unit && buildingNumber && attachedNumber) {
                                    schemeJudgeObjects.add(judgeObject);
                                }
                            }
                        }
                        if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
                            return schemeJudgeObjects;
                        }
                    }
                }
            }
        }
        return new ArrayList<SchemeJudgeObject>(0);
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
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        return localPath;
    }

    public String getLocalPath(String title) {
        if (StringUtils.isEmpty(title)) {
            return getLocalPath();
        }
        String localPath = String.format("%s\\" + title + "%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        return localPath;
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
            builder.insertImage(imgPath,
                    sourceImg.getWidth() > 400 ? 400 : sourceImg.getWidth(),
                    sourceImg.getHeight() > 500 ? 500 : sourceImg.getHeight());
            builder.write(" ");
        }
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
        //水平居中
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        //上下居中
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
    }

    public void setDefaultDocumentBuilderSetting(DocumentBuilder builder) throws Exception {
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        builder.getFont().setSize(14.5);
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

}
