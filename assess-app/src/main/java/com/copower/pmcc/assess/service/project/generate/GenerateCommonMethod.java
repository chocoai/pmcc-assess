package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.*;
import com.copower.pmcc.assess.common.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.method.MdBaseLandPriceService;
import com.copower.pmcc.assess.service.method.MdCostApproachService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.*;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.utils.*;
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
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private SchemeReportFileService schemeReportFileService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private GenerateReportGroupService generateReportGroupService;
    @Autowired
    private SurveyAssetInfoGroupService surveyAssetInfoGroupService;
    @Autowired
    private MdMarketCostService mdMarketCostService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;
    @Autowired
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private MdCostApproachService mdCostApproachService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;


    public static final String SchemeJudgeObjectName = "委估对象";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public BasicEstateLandCategoryInfoService getBasicEstateLandCategoryInfoService(){
        return basicEstateLandCategoryInfoService;
    }
    public MdCostApproachService getMdCostApproachService() {
        return mdCostApproachService;
    }

    public MdBaseLandPriceService getMdBaseLandPriceService() {
        return mdBaseLandPriceService;
    }

    public MdMarketCostService getMdMarketCostService() {
        return mdMarketCostService;
    }

    public MdDevelopmentService getMdDevelopmentService() {
        return mdDevelopmentService;
    }

    public SurveyAssetInfoGroupService getSurveyAssetInfoGroupService() {
        return surveyAssetInfoGroupService;
    }

    public SurveyAssetInfoItemService getSurveyAssetInfoItemService() {
        return surveyAssetInfoItemService;
    }

    public SurveyAssetInventoryContentService getSurveyAssetInventoryContentService() {
        return surveyAssetInventoryContentService;
    }


    //房地产总价
    public BigDecimal getTotalRealEstate(Integer areId) {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areId);
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                BigDecimal evaluationArea = schemeJudgeObjectService.getEvaluationAreaOrNumber(schemeJudgeObject);
                if (evaluationArea != null && schemeJudgeObject.getPrice() != null) {
                    BigDecimal bigDecimal = evaluationArea.multiply(schemeJudgeObject.getPrice());
                    temp = temp.add(bigDecimal);
                }
            }
        }
        return temp;
    }

    /**
     * 资产清查 提取的公共方法
     *
     * @param fieldName
     * @param type
     * @throws Exception
     */
    public String getAssetInventoryCommon(String fieldName, BaseDataDic type, Integer declareRecordId, ProjectInfo projectInfo) throws Exception {
        Set<SurveyAssetInventoryContent> surveyAssetInventoryContentSet = Sets.newHashSet();
        Set<String> stringSet = Sets.newHashSet();
        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemByDeclareId(declareRecordId);
        if (assetInfoItem == null) return null;
        List<SurveyAssetInventoryContent> contentList = new ArrayList<>();
        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentService.getInventoryContentListByItemId(assetInfoItem.getId());
        if (CollectionUtils.isEmpty(surveyAssetInventoryContents)) {
            return null;
        }
        contentList.addAll(surveyAssetInventoryContents);
        if (CollectionUtils.isNotEmpty(contentList)) {
            for (SurveyAssetInventoryContent o : contentList) {
                if (com.google.common.base.Objects.equal("不一致", o.getAreConsistent()) && com.google.common.base.Objects.equal(type.getId(), o.getInventoryContent()))
                    surveyAssetInventoryContentSet.add(o);
            }
        }
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentSet)) {
            for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentSet) {
                String value = (String) Reflections.getFieldValue(surveyAssetInventoryContent, fieldName);
                if (StringUtils.isNotEmpty(value)) {
                    stringSet.add(value);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            return StringUtils.join(stringSet, "");
        } else {
            return null;
        }
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
     * 获取区域下楼盘的分组
     *
     * @param areaId
     * @return
     */
    public LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> getEstateGroupByAreaId(Integer areaId) throws Exception {
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> listLinkedHashMap = Maps.newLinkedHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaId);
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) return listLinkedHashMap;
        List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByIds(LangUtils.transform(schemeJudgeObjectList, o -> o.getBasicApplyId()));
        if (CollectionUtils.isEmpty(basicApplyList)) return listLinkedHashMap;
        List<Integer> batchDetailIds = LangUtils.transform(basicApplyList, o -> o.getBatchDetailId());
        List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBatchDetailListByIds(batchDetailIds);
        Map<Integer, BasicApplyBatchDetail> mappingSingleEntity = FormatUtils.mappingSingleEntity(batchDetailList, o -> o.getApplyBatchId());
        for (Map.Entry<Integer, BasicApplyBatchDetail> entry : mappingSingleEntity.entrySet()) {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(entry.getKey());
            BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
            List<BasicApplyBatchDetail> batchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailListByType(BasicFormClassifyEnum.HOUSE.getKey(), entry.getValue().getApplyBatchId(), null, false);
            if (CollectionUtils.isEmpty(batchDetails)) continue;
            List<BasicApply> applyList = basicApplyService.getBasicApplysByBatchDetailIds(LangUtils.transform(batchDetails, o -> o.getId()));
            if (CollectionUtils.isEmpty(applyList)) continue;
            List<Integer> applyIds = LangUtils.transform(applyList, o -> o.getId());
            List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (applyIds.contains(schemeJudgeObject.getBasicApplyId()))
                    judgeObjectList.add(schemeJudgeObject);
            }
            listLinkedHashMap.put(basicEstate, judgeObjectList);
        }
        return listLinkedHashMap;
    }

    /**
     * 获取区域下楼盘的分组
     *
     * @param reportGroup
     * @return
     */
    public LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> getEstateGroupByGroupId(GenerateReportGroup reportGroup) throws Exception {
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> listLinkedHashMap = Maps.newLinkedHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = generateReportGroupService.getSchemeJudgeObjectByGroupId(reportGroup.getId());
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) return listLinkedHashMap;
        List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByIds(LangUtils.transform(schemeJudgeObjectList, o -> o.getBasicApplyId()));
        if (CollectionUtils.isEmpty(basicApplyList)) return listLinkedHashMap;
        //去除重复
        basicApplyList = basicApplyList.stream().filter(StreamUtils.distinctByKey(o -> o.getId())).collect(Collectors.toList());
        LinkedHashMap<Integer, BasicEstate> linkedHashMap = new LinkedHashMap<>();
        for (BasicApply basicApply : basicApplyList) {
            linkedHashMap.put(basicApply.getId(), getBasicEstateByBasicApply(basicApply));
        }
        List<BasicEstate> basicEstateList = linkedHashMap.values().stream().collect(Collectors.toList());
        //将LinkedHashMap<BasicApply, BasicEstate> 变为 LinkedHashMap<BasicEstate, List<BasicApply>>
        LinkedHashMap<Integer, List<Integer>> estateListLinkedHashMap = new LinkedHashMap<>();
        if (!linkedHashMap.isEmpty()) {
            Iterator<Map.Entry<Integer, BasicEstate>> iterator = linkedHashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, BasicEstate> estateEntry = iterator.next();
                if (estateEntry.getValue() == null) {
                    continue;
                }
                if (estateListLinkedHashMap.containsKey(estateEntry.getValue().getId())) {
                    estateListLinkedHashMap.get(estateEntry.getValue().getId()).add(estateEntry.getKey());
                } else {
                    estateListLinkedHashMap.put(estateEntry.getValue().getId(), Lists.newArrayList(estateEntry.getKey()));
                }
            }
        }
        LinkedHashMap<Integer, List<SchemeJudgeObject>> listLinkedHashMap2 = Maps.newLinkedHashMap();
        //将LinkedHashMap<BasicEstate, List<BasicApply>> 变为 LinkedHashMap<BasicEstate, List<SchemeJudgeObject>>
        if (!estateListLinkedHashMap.isEmpty()) {
            Iterator<Map.Entry<Integer, List<Integer>>> iterator = estateListLinkedHashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, List<Integer>> estateListEntry = iterator.next();
                if (CollectionUtils.isEmpty(estateListEntry.getValue())) {
                    continue;
                }
                List<SchemeJudgeObject> judgeObjectList = new ArrayList<>();
                Iterator<SchemeJudgeObject> objectIterator = schemeJudgeObjectList.iterator();
                while (objectIterator.hasNext()) {
                    SchemeJudgeObject judgeObject = objectIterator.next();
                    if (judgeObject.getBasicApplyId() == null || judgeObject.getBasicApplyId() == 0) {
                        continue;
                    }
                    if (estateListEntry.getValue().contains(judgeObject.getBasicApplyId())) {
                        judgeObjectList.add(judgeObject);
                    }
                }
                if (CollectionUtils.isEmpty(judgeObjectList)) {
                    continue;
                }
                if (listLinkedHashMap2.containsKey(estateListEntry.getKey())) {
                    listLinkedHashMap2.get(estateListEntry.getKey()).addAll(judgeObjectList);
                } else {
                    listLinkedHashMap2.put(estateListEntry.getKey(), judgeObjectList);
                }
            }
        }
        if (!listLinkedHashMap2.isEmpty()) {
            Iterator<Map.Entry<Integer, List<SchemeJudgeObject>>> iterator = listLinkedHashMap2.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, List<SchemeJudgeObject>> entry = iterator.next();
                listLinkedHashMap.put(basicEstateList.stream().filter(basicEstate -> Objects.equal(basicEstate.getId(), entry.getKey())).findFirst().get(), entry.getValue());
            }
        }
        return listLinkedHashMap;
    }

    public BasicEstate getBasicEstateByBasicApply(BasicApply basicApply) {
        BasicEstate basicEstate = null;
        if (basicApply.getBasicEstateId() != null && basicApply.getBasicEstateId() != 0) {
            basicEstate = basicEstateService.getBasicEstateById(basicApply.getBasicEstateId());
        }
        if (basicApply.getApplyBatchId() != null && basicApply.getApplyBatchId() != 0) {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApply.getApplyBatchId());
            if (basicApplyBatch != null && basicApplyBatch.getEstateId() != null && basicApplyBatch.getEstateId() != 0) {
                basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
            }
        }
        if (basicApply.getBatchDetailId() != null && basicApply.getBatchDetailId() != 0) {
            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(basicApply.getBatchDetailId());
            if (basicApplyBatchDetail != null) {
                if (Objects.equal(basicApplyBatchDetail.getTableName(), FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
                    basicEstate = basicEstateService.getBasicEstateById(basicApplyBatchDetail.getTableId());
                }
                if (basicApplyBatchDetail.getApplyBatchId() != null && basicApplyBatchDetail.getApplyBatchId() != 0) {
                    BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
                    if (basicApplyBatch != null && basicApplyBatch.getEstateId() != null && basicApplyBatch.getEstateId() != 0) {
                        basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
                    }
                }
                //如果 basicEstate 还为null
                if (basicEstate == null && basicApplyBatchDetail.getApplyBatchId() != null && basicApplyBatchDetail.getApplyBatchId() != 0) {
                    BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
                    if (basicApplyBatch != null && basicApplyBatch.getEstateId() != null && basicApplyBatch.getEstateId() != 0) {
                        basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
                    }
                }
            }
        }

        return basicEstate;
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
                Integer a = parseIntJudgeNumber(o1.getNumber());
                Integer b = parseIntJudgeNumber(o2.getNumber());
                return a.compareTo(b);
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
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getId().toString()));
                } else {
                    integerList = Lists.newArrayList();
                    integerList.add(NumberUtils.toInt(schemeJudgeObject.getId().toString()));
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
        String spliter = "-";
        StringBuilder stringBuilder = new StringBuilder(8);
        String baseName = "号";
        if (schemeJudgeObject == null) {
            return "";
        }
        List<String> stringList = FormatUtils.transformString2List(schemeJudgeObject.getNumber(), ",");
        //单独一个估价对象  拆分情况
        if (stringList.size() == 1 && schemeJudgeObject.getSplitNumber() != null) {
            stringList.clear();
            stringList.add(String.join(spliter, schemeJudgeObject.getNumber(), schemeJudgeObject.getSplitNumber().toString()));
        }
        //1:如果全部是数字并且是连续的数字那么我们合并描述

        //检测是否全部是数字
        if (StreamUtils.checkNumberList(stringList)) {
            List<Integer> integerList = LangUtils.transform(stringList, (s) -> Integer.valueOf(s));
            //检测是否连续
            if (StreamUtils.continuous(integerList)) {
                stringBuilder.append(convertNumber(integerList)).append(baseName);
            } else {
                //全是数字,但是不是全部连续,我们暂时用同样的方式处理  , 以后这里可能会变化
                stringBuilder.append(convertNumber(integerList)).append(baseName);
            }
            return stringBuilder.toString();
        }
        //字符串存在非数字 , 可能存在拆分后合并情况
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (String number : stringList) {
            List<String> string2List = FormatUtils.transformString2List(number, spliter);
            LinkedList<String> splits = new LinkedList<>();
            for (int i = 0; i < string2List.size(); i++) {
                if (i == 0) {
                    if (NumberUtils.isNumber(string2List.get(0))) {
                        splits.add(convertNumber(Lists.newArrayList(Integer.parseInt(string2List.get(0)))));
                    } else {
                        splits.add(string2List.get(0));
                    }
                } else {
                    splits.add(string2List.get(i));
                }
            }
            stringLinkedList.add(StringUtils.join(splits, spliter));
        }
        stringBuilder.append(StringUtils.join(stringLinkedList, ",")).append(baseName);
        return stringBuilder.toString();
    }

    /**
     * 类似于⑲号或者21号
     *
     * @param schemeJudgeObject
     * @param schemeJudgeObjectList
     * @return
     */
    public String getSchemeJudgeObjectShowName(SchemeJudgeObject schemeJudgeObject, List<SchemeJudgeObject> schemeJudgeObjectList) {
        List<Integer> integerList = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject object = schemeJudgeObjectList.get(i);
            integerList.add(parseIntJudgeNumber(object.getNumber()));
        }
        return getSchemeJudgeObjectShowHandleName(schemeJudgeObject, integerList);
    }


    /**
     * 类似于⑲或者21
     *
     * @param schemeJudgeObject
     * @param schemeJudgeObjectList
     * @return
     */
    public String getSchemeJudgeObjectShowName2(SchemeJudgeObject schemeJudgeObject, List<SchemeJudgeObject> schemeJudgeObjectList) {
        List<Integer> integerList = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject object = schemeJudgeObjectList.get(i);
            integerList.add(parseIntJudgeNumber(object.getNumber()));
        }
        return StringUtils.remove(getSchemeJudgeObjectShowHandleName(schemeJudgeObject, integerList), "号");
    }

    /**
     * 类似于⑲号或者21号
     *
     * @param schemeJudgeObject
     * @param integerList
     * @return
     */
    public String getSchemeJudgeObjectShowHandleName(SchemeJudgeObject schemeJudgeObject, List<Integer> integerList) {
        if (checkSchemeJudgeObjectNumberOverloadTwenty(integerList)) {
            return getSchemeJudgeObjectShowName(schemeJudgeObject);
        } else {
            return schemeJudgeObject.getName();
        }
    }

    /**
     * 校验是否超过word 的编号
     *
     * @param integerList
     * @return
     */
    public boolean checkSchemeJudgeObjectNumberOverloadTwenty(List<Integer> integerList) {
        final int twenty = 20;
        boolean flag = true;
        if (CollectionUtils.isNotEmpty(integerList)) {
            integerList = integerList.stream().distinct().collect(Collectors.toList());
            if (integerList.stream().reduce(Integer::max).get() > twenty || integerList.size() > twenty) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean checkSchemeJudgeObjectNumberOverloadTwenty2(List<SchemeJudgeObject> schemeJudgeObjectList) {
        List<Integer> integerList = Lists.newArrayList();
        for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
            SchemeJudgeObject object = schemeJudgeObjectList.get(i);
            integerList.add(parseIntJudgeNumber(object.getNumber()));
        }
        return checkSchemeJudgeObjectNumberOverloadTwenty(integerList);
    }

    public String getPercentileSystem(BigDecimal bigDecimal) {
        return ArithmeticUtils.getPercentileSystem(bigDecimal, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取数值的四舍五入的绝对值，并且考虑是否除以10000
     *
     * @param bigDecimal
     * @return
     */
    public String getBigDecimalRound(final BigDecimal bigDecimal, boolean tenThousand) {
        return getBigDecimalRound(bigDecimal, 0, tenThousand);
    }

    public String getBigDecimalRound(final BigDecimal bigDecimal, int newScale, boolean tenThousand) {
        if (tenThousand) {
            return getBigDecimalRound(bigDecimal, newScale, new Double(10000));
        } else {
            return getBigDecimalRound(bigDecimal, newScale, null);
        }
    }

    public String getBigDecimalRound(final BigDecimal bigDecimal, int newScale, Double d) {
        if (bigDecimal == null) {
            return "";
        }
        BigDecimal setScale = new BigDecimal(0);
        if (d != null) {
            setScale = ArithmeticUtils.divModel(bigDecimal, new BigDecimal(d), RoundingMode.HALF_UP);
        }
        if (d == null) {
            setScale = new BigDecimal(bigDecimal.toString());
        }
        //四舍五入,并且取到约定的位数
        return ArithmeticUtils.getBigDecimalString(setScale.abs().setScale(newScale, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 将非整数在约定的位数下取整 如 1515.45 取10 变为 1510
     *
     * @param bigDecimal
     * @param number     必须是10的正次方的结果 如10,100,1000,1000
     * @return
     */
    public String getBigDecimalToInteger(final BigDecimal bigDecimal, int number) {
        return ArithmeticUtils.getBigDecimalToInteger(bigDecimal, number);
    }

    /**
     * 判断是否为整数 仅仅判断是否是整数
     *
     * @param bigDecimal
     * @return
     */
    public boolean isInteger(BigDecimal bigDecimal) {
        return ArithmeticUtils.isInteger(bigDecimal);
    }


    /**
     * 类似于这样的1=2-7-20-32-1-fhd-6364转换为 List形式并且里面的数字如20和32等会是一个元素而像'='或者'-'或者'f'等这样的非数字则单独是一个元素
     *
     * @param text
     * @return
     */
    public List<String> convertNumberHelp(String text) {
        List<String> stringList = Lists.newArrayList();
        if (StringUtils.isEmpty(text.trim())) {
            return stringList;
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append(text);
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        while (m.find()) {
            String string = stringBuilder.toString();
            int index = string.indexOf(m.group());
            String number = string.substring(0, index);
            if (NumberUtils.isNumber(number)) {
                stringList.add(number);
            }
            stringList.add(m.group());
            string = string.substring(index + 1, stringBuilder.toString().length());
            stringBuilder.delete(0, stringBuilder.toString().length());
            stringBuilder.append(string);
        }
        if (NumberUtils.isNumber(stringBuilder.toString())) {
            stringList.add(stringBuilder.toString());
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            //去重复 (不改变顺序)
            stringList = stringList.stream().distinct().collect(Collectors.toList());
        }
        return stringList;
    }

    /**
     * 数字转换
     *
     * @param numbers
     * @return
     */
    public String convertNumber(List<Integer> numbers) {
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(numbers)) {
            numbers = numbers.stream().distinct().sorted().collect(Collectors.toList());
            Integer[] ints = new Integer[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                ints[i] = numbers.get(i);
            }
            String text = null;
            if (ints.length > 1) {
                text = this.convert(ints, 0);
                LinkedList<String> stringList = Lists.newLinkedList();
                for (char c : text.toCharArray()) {
                    stringList.add(String.valueOf(c));
                }
                for (int i = 0; i < stringList.size(); i++) {
                    if (i == stringList.size() - 1) {
                        if (NumberUtils.isNumber(stringList.get(i))) {
                            stringBuilder.append(stringList.get(i));
                        }
                    } else {
                        stringBuilder.append(stringList.get(i));
                    }
                }
                text = stringBuilder.toString();
                stringBuilder.delete(0, stringBuilder.toString().length());
            } else {
                text = ints[0].toString();
            }
            List<String> stringList = convertNumberHelp(text);
            if (!checkSchemeJudgeObjectNumberOverloadTwenty(numbers)) {
                return StringUtils.join(stringList, "");
            }
            if (CollectionUtils.isNotEmpty(stringList)) {
                stringList.stream().forEach(s -> {
                    if (NumberUtils.isNumber(s)) {
                        stringBuilder.append(this.parseToCircleNumber(Integer.parseInt(s)));
                    } else {
                        stringBuilder.append(s);
                    }
                });
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                text = stringBuilder.toString();
            }
            return text;
        }
        return "";
    }

    /**
     * 去掉html
     *
     * @param htmlStr
     * @return
     */
    public String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签
        return htmlStr; //返回文本字符串
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

    public String getLocalPath() {
        return getLocalPath(null);
    }

    public String getLocalPath(String title) {
        return getLocalPath(title, "doc");
    }

    public String getLocalPath(String title, String suffix) {
        if (StringUtils.isEmpty(title)) {
            title = String.format("%s%s", "reportTemplate", DateUtils.format(new Date(), DateUtils.DATE_PATTERN));
        }
        return String.format("%s\\%s%s%s%s", baseAttachmentService.createTempDirPath(), title, UUID.randomUUID().toString(), ".", suffix);
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
        if (value == null) {
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

    public static List<String> specialTreatment(List<String> strings) {
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
     * 设置表格属性
     *
     * @param builder
     * @throws Exception
     */
    public void settingBuildingTable(DocumentBuilder builder) throws Exception {
        builder.getFont().setSize(9);
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        AsposeUtils.setDefaultTable(builder);

    }


    public void setDefaultDocumentBuilderSetting(DocumentBuilder builder) throws Exception {
        builder.getFont().setName(AsposeUtils.ImitationSongGB2312FontName);
        builder.getFont().setSize(14);
    }

    /**
     * 合并表格
     *
     * @param mergeCellModelList
     * @param table
     */
    public void mergeCellTable(Set<MergeCellModel> mergeCellModelList, Table table) {
        AsposeUtils.mergeCellTable(mergeCellModelList, table);
    }

    /**
     * 获取包装后的html，与当前word字体格式一致
     *
     * @param html
     * @return
     */
    public String getWarpCssHtml(String html) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
        keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
        keyValueDtoList.add(new KeyValueDto("font-size", "14.0pt"));
        keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
        return AsposeUtils.getWarpCssHtml(html, keyValueDtoList);
    }

    public String getSongWarpCssHtml(String html) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(3);
        keyValueDtoList.add(new KeyValueDto("font-family", "宋体"));
        keyValueDtoList.add(new KeyValueDto("font-size", "10.0pt"));
        keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
        return AsposeUtils.getWarpCssHtml(html, keyValueDtoList);
    }

    public String getSongWarpCssHtml2(String html) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(5);
        keyValueDtoList.add(new KeyValueDto("font-family", "宋体"));
        keyValueDtoList.add(new KeyValueDto("font-size", "12.0pt"));
        keyValueDtoList.add(new KeyValueDto("text-align", "right"));
        keyValueDtoList.add(new KeyValueDto("float", "right"));
        keyValueDtoList.add(new KeyValueDto("width", "150px"));
        return AsposeUtils.getWarpCssHtml(html, keyValueDtoList);
    }

    public String getSongWarpCssHtml3(String html) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(4);
        keyValueDtoList.add(new KeyValueDto("font-family", "仿宋_GB2312"));
        keyValueDtoList.add(new KeyValueDto("font-size", "14.0pt"));
        keyValueDtoList.add(new KeyValueDto("text-align", "center"));
        keyValueDtoList.add(new KeyValueDto("line-height", "150%"));
        return AsposeUtils.getWarpCssHtml(html, keyValueDtoList);
    }

    /**
     * 获取缩进后html
     *
     * @param html
     * @return
     */
    public String getIndentHtml(String html) {
        return AsposeUtils.getWarpCssHtml(html, "text-indent", "2em");
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
        //map按key值排序
        Map<Integer, String> sortMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sortMap.putAll(map);
        Map<String, List<Integer>> listMap = getStringListMap(sortMap);
        StringBuilder judgeBuilder = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            judgeBuilder.append(convertNumber(stringListEntry.getValue())).append("、");
            contentBuilder.append(stringListEntry.getKey()).append("、");
        }
        String judgeString = StringUtils.strip(judgeBuilder.toString(), "、");
        String contentStrig = StringUtils.strip(contentBuilder.toString().replaceAll("^<[^>]+>|<[^>]+>$", ""), "、");
        if (listMap.size() <= 1 && isShowNumber == Boolean.FALSE) {
            return explain + contentStrig;
        }
        return String.format("%s号%s%s", judgeString, StringUtils.defaultString(explain), contentStrig);
    }

    private Map<String, List<Integer>> getStringListMap(Map<Integer, String> map) {
        Map<String, List<Integer>> listMap = Maps.newLinkedHashMap();
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
    public String judgeEachDesc(Map<Integer, String> map, String explain, String symbol, Boolean isShowJudgeNumber) {
        if (map == null || map.size() <= 0) return "";
        //map按key值排序
        Map<Integer, String> sortMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sortMap.putAll(map);
        Map<String, List<Integer>> listMap = getStringListMap(sortMap);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            if (StringUtils.isBlank(stringListEntry.getKey())) continue;
            String content = stringListEntry.getKey().replaceAll("^<[^>]+>|<[^>]+>$", "");
            if (listMap.size() <= 1 && isShowJudgeNumber == Boolean.FALSE) {
                return explain + content;
            }
            builder.append(String.format("%s号", convertNumber(stringListEntry.getValue()))).append(StringUtils.defaultString(explain)).append(content).append(symbol);
        }
        return builder.toString();
    }

    /**
     * 当只有一个估价对象的时候不会分别描述
     * 具体看 judgeEachDesc
     *
     * @param map
     * @param explain
     * @param symbol
     * @param isShowJudgeNumber
     * @return
     */
    public String judgeEachDesc2(Map<Integer, String> map, String explain, String symbol, Boolean isShowJudgeNumber) {
        if (map == null || map.size() <= 0) {
            return "";
        }
        boolean one = false;
        if (map.size() == 1) {
            one = true;
        }
        if (!one) {
            Set<String> stringSet = Sets.newHashSet();
            map.entrySet().forEach(integerStringEntry -> stringSet.add(integerStringEntry.getValue()));
            if (stringSet.size() == 1) {
                one = true;
            }
        }
        if (one) {
            StringBuilder stringBuilder = new StringBuilder(8);
            if (isShowJudgeNumber.equals(Boolean.FALSE)) {
                stringBuilder.append(StringUtils.defaultString(explain)).append(map.entrySet().stream().findFirst().get().getValue());
            } else {
                stringBuilder.append(StringUtils.defaultString(explain)).append(map.entrySet().stream().findFirst().get().getValue()).append(symbol);
            }
            return stringBuilder.toString();
        } else {
            return judgeEachDesc(map, explain, symbol, isShowJudgeNumber);
        }
    }

    /**
     * 估价对象分别描述
     *
     * @param map               合并拆分对象map
     * @param explain
     * @param symbol
     * @param isShowJudgeNumner
     * @return
     */
    public String judgeEachDescExtend(Map<String, String> map, String explain, String symbol, Boolean isShowJudgeNumner) {
        //先处理map key,先处理连续未拆分的估价对象，再将拆分的估价对象搁置一边，最后将拆分的对象插入到描述一致的分组中
        if (map == null || map.size() <= 0) return "";
        Map<Integer, String> standardMap = Maps.newHashMap();//标准值map
        Map<String, String> splitMap = Maps.newHashMap();//拆分对象map
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            for (String s : StringUtils.split(key, ",")) {
                if (s.contains("-")) {
                    splitMap.put(s, entry.getValue());
                } else {
                    standardMap.put(Integer.valueOf(s), entry.getValue());
                }
            }
        }
        Map<Integer, String> sortMap = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
        sortMap.putAll(standardMap);
        Map<String, List<Integer>> listMap = getStringListMap(sortMap);

        List<String> removeKeys = Lists.newArrayList();
        Map<String, String> resultMap = Maps.newHashMap();
        for (Map.Entry<String, List<Integer>> stringListEntry : listMap.entrySet()) {
            StringBuilder numberBuilder = new StringBuilder(convertNumber(stringListEntry.getValue()));
            for (Map.Entry<String, String> splitEntry : splitMap.entrySet()) {
                if (StringUtils.equals(splitEntry.getValue(), stringListEntry.getKey())) {
                    numberBuilder.append(",").append(splitEntry.getKey());
                    removeKeys.add(splitEntry.getKey());
                }
            }
            resultMap.put(StringUtils.strip(numberBuilder.toString(), ","), stringListEntry.getKey());
        }
        removeKeys.forEach(o -> splitMap.remove(o));
        if (!splitMap.isEmpty()) {
            resultMap.putAll(splitMap);
        }
        StringBuilder builder = new StringBuilder();
        if (!resultMap.isEmpty()) {
            for (Map.Entry<String, String> resultEntry : resultMap.entrySet()) {
                String content = resultEntry.getValue().replaceAll("^<[^>]+>|<[^>]+>$", "");
                if (resultMap.size() <= 1 && isShowJudgeNumner == Boolean.FALSE) {
                    return explain + content;
                }
                builder.append(String.format("%s号", resultEntry.getKey())).append(StringUtils.defaultString(explain)).append(content).append(symbol);
            }
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
            String content = stringListEntry.getKey().replaceAll("^<[^>]+>|<[^>]+>$", "");
            stringBuilder.append(explain).append(content).append(symbol);
        }
        return stringBuilder.toString();
    }

    /**
     * 处理字符串中错误的标点符号
     *
     * @param str
     * @return
     */
    public String trimText(String str) {
        return trim(str, true, false);
    }

    public String trim(String str) {
        return trim(str, true);
    }

    public String trim(String str, Boolean removeTag) {
        return trim(str, removeTag, true);
    }

    /**
     * 替换为标准格式
     *
     * @param str
     * @param removeTag       是否移除html标签
     * @param containFullStop 末尾是否包含句号
     * @return
     */
    public String trim(String str, Boolean removeTag, Boolean containFullStop) {
        return AsposeUtils.trim(str, removeTag, containFullStop);
    }


    /**
     * 提取数字
     *
     * @param text
     * @return
     */
    public String getNumber(String text) {
        return StreamUtils.getNumber(text);
    }

    /**
     * 估价对象编号zhuanintege
     *
     * @param number
     * @return
     */
    public Integer parseIntJudgeNumber(String number) {
        final int ten = 10;
        if (StringUtils.isBlank(number)) {
            return null;
        }
        if (number.contains(",")) {
            number = number.split(",")[0];
        }
        if (number.contains("-")) {
            number = number.split("-")[0];
        }
        if (number.length() < ten) {
            return Integer.valueOf(number);
        } else {
            return Integer.valueOf(number.substring(0, ten - 1));
        }
    }

    public List<Integer> splitIntegerListJudgeNumber(String number) {
        List<Integer> integerList = new ArrayList<>();
        if (StringUtils.isEmpty(number)) {
            return integerList;
        }
        List<String> string2List = FormatUtils.transformString2List(number, ",");
        if (CollectionUtils.isEmpty(string2List)) {
            string2List = FormatUtils.transformString2List(number, "-");
        }
        if (CollectionUtils.isEmpty(string2List)) {
            string2List = FormatUtils.transformString2List(number, "_");
        }
        if (CollectionUtils.isNotEmpty(string2List)) {
            for (String num : string2List) {
                if (NumberUtils.isNumber(num)) {
                    integerList.add(NumberUtils.createInteger(num));
                }
            }
        }
        return integerList;
    }


    public List<String> changeMapToList(Map<String, String> map, boolean flag, String suffix) {
        List<String> stringList = Lists.newArrayList();
        StringBuilder stringBuilder = new StringBuilder(8);
        if (!map.isEmpty()) {
            map.entrySet().stream().forEach(entry -> {
                if (flag) {
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append(entry.getValue());
                } else {
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(entry.getKey());
                }
                if (StringUtils.isNotEmpty(suffix)) {
                    stringBuilder.append(suffix);
                    stringList.add(stringBuilder.toString());
                } else {
                    stringList.add(stringBuilder.toString());
                }
                stringBuilder.delete(0, stringBuilder.toString().length());
            });
        }
        return stringList;
    }

    /**
     * 数字转换为带圆圈的数字 (由于word目前标准子集中只有20,所以暂时只有20)
     *
     * @param number
     * @return
     */
    final public String parseToCircleNumber(final Integer number) {
        if (number == null) {
            return null;
        }
        final int max = 20;
        final Map<Integer, String> map = new HashMap<Integer, String>(max);
        for (int i = 1; i <= max; i++) {
            switch (i) {// ①,②,③,④,⑤,⑥,⑦,⑧,⑨,⑩,⑪,⑫,⑬,⑭,⑮,⑯,⑰,⑱,⑲,⑳  ①②③④⑤⑥⑦⑧⑨⑩⑪⑫⑬⑭⑮⑯⑰⑱⑲⑳
                case 1:
                    map.put(i, "①");
                    break;
                case 2:
                    map.put(i, "②");
                    break;
                case 3:
                    map.put(i, "③");
                    break;
                case 4:
                    map.put(i, "④");
                    break;
                case 5:
                    map.put(i, "⑤");
                    break;
                case 6:
                    map.put(i, "⑥");
                    break;
                case 7:
                    map.put(i, "⑦");
                    break;
                case 8:
                    map.put(i, "⑧");
                    break;
                case 9:
                    map.put(i, "⑨");
                    break;
                case 10:
                    map.put(i, "⑩");
                    break;
                case 11:
                    map.put(i, "⑪");
                    break;
                case 12:
                    map.put(i, "⑫");
                    break;
                case 13:
                    map.put(i, "⑬");
                    break;
                case 14:
                    map.put(i, "⑭");
                    break;
                case 15:
                    map.put(i, "⑮");
                    break;
                case 16:
                    map.put(i, "⑯");
                    break;
                case 17:
                    map.put(i, "⑰");
                    break;
                case 18:
                    map.put(i, "⑱");
                    break;
                case 19:
                    map.put(i, "⑲");
                    break;
                case 20:
                    map.put(i, "⑳");
                    break;
                default:
                    break;
            }
        }
        if (!map.containsKey(number)) {
            return number.toString();
        }
        return map.get(number);
    }

    /**
     * 根据距离分组
     *
     * @param map
     * @return (距离, List < id >)
     */
    public LinkedHashMap<String, List<Integer>> getGroupByDistance(Map<Integer, String> map) {
        AssembleGroupByDistance<Integer> assembleGroupByDistance = new AssembleGroupByDistance<>();
        LinkedHashMap<String, List<Integer>> listMap = assembleGroupByDistance.getGroupByDistance(map, baseDataDicService);
        return listMap;
    }

    /**
     * 根据距离分组
     *
     * @param map
     * @return (距离, List < name >)
     */
    public LinkedHashMap<String, List<String>> getGroupByDistanceToString(Map<String, String> map) {
        AssembleGroupByDistance<String> assembleGroupByDistance = new AssembleGroupByDistance<>();
        LinkedHashMap<String, List<String>> listMap = assembleGroupByDistance.getGroupByDistance(map, baseDataDicService);
        return listMap;
    }

    public static class AssembleGroupByDistance<T> {
        private T t;

        public LinkedHashMap<String, List<T>> getGroupByDistance(Map<T, String> map, BaseDataDicService baseDataDicService) {
            LinkedHashMap<String, List<T>> listMap = Maps.newLinkedHashMap();
            if (!map.isEmpty()) {
                map.entrySet().stream().forEach(entry -> {
                    String key = entry.getValue();
                    if (NumberUtils.isNumber(entry.getValue())) {
                        key = baseDataDicService.getNameById(entry.getValue());
                    }
                    key = StreamUtils.getNumber(key);
                    List<T> integerList = listMap.get(key);
                    if (CollectionUtils.isEmpty(integerList)) {
                        integerList = Lists.newArrayList();
                    }
                    integerList.add(entry.getKey());
                    if (StringUtils.isNumeric(key)) {
                        listMap.put(key, integerList);
                    }
                });
            }
            return listMap;
        }
    }

    /**
     * 根据估价对象获取 BasicApply
     *
     * @param schemeJudgeObject
     * @return
     */
    public BasicApply getBasicApplyBySchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject.getDeclareRecordId() == null) {
            return null;
        }
        return basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
    }

    public BasicApplyBatch getBasicApplyBatchBySchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) {
            return null;
        }
        BasicApplyBatch basicApplyBatch = surveyCommonService.getBasicApplyBatchByApplyId(schemeJudgeObject.getBasicApplyId());
        if (basicApplyBatch == null) {
            return null;
        }
        return basicApplyBatch;
    }

    /**
     * 计算数值差异
     *
     * @param var1
     * @param var2
     * @return 返回10则有10%的差异
     */
    public int computeDifference(BigDecimal var1, BigDecimal var2) {
        return publicService.computeDifference(var1, var2);
    }

    public void writeWordTitle(DocumentBuilder builder, LinkedList<String> titles) throws Exception {
        AsposeUtils.writeWordTitle(builder, titles);
    }

    public void writeWordTitle(DocumentBuilder builder, LinkedList<Double> doubleLinkedList, LinkedList<String> linkedLists) throws Exception {
        AsposeUtils.writeWordTitle(builder, doubleLinkedList, linkedLists);
    }

    /**
     * 获取报告附件关联fieldsName
     *
     * @param reportType
     * @param reportGroup
     * @return
     */
    public String getReportFieldsName(String reportType, GenerateReportGroup reportGroup) {
        if (StringUtils.isBlank(reportType)) return null;
        List<String> stringArrayList = Lists.newArrayList();
        for (String s : reportType.split("\\.")) {
            stringArrayList.add(s.toUpperCase());
        }
        if (reportGroup == null) {
            return StringUtils.join(stringArrayList, "_");
        } else {
            return String.format("%s%d%d", StringUtils.join(stringArrayList, "_"), reportGroup.getAreaGroupId(), reportGroup.getId());
        }
    }

    /**
     * 获取报告附件模板关联fieldsName
     *
     * @param reportType
     * @param reportGroup
     * @return
     */
    public String getReportFooterFieldsName(String reportType, GenerateReportGroup reportGroup) {
        if (StringUtils.isBlank(reportType)) return null;
        List<String> fieldsName = Lists.newArrayList();
        for (String s : reportType.split("\\.")) {
            fieldsName.add(s.toUpperCase());
        }
        fieldsName.add("Attachment");
        return String.format("%s%d%d", StringUtils.join(fieldsName, "_"), reportGroup.getAreaGroupId(), reportGroup.getId());
    }

    /**
     * 实况照片 插入 以表格方式插入
     * 以前方法名称叫imageInsertToWrod3
     * zch 重新构造 目的是不能因为某张图片 无效而影响到报告并且当单张图片出问题其余图片可以正常显示
     * 插入思路是  先插入相同列的图片，然后再插入相同列的名称  这样就做到了图片和列相同  使得名称看起来在图片下面  起到美观作用
     *
     * @param schemeReportFileList
     * @param colCount
     * @param builder
     * @throws Exception
     */
    public void InsertSchemeReportFileItemImageToWord(List<SchemeReportFileItem> schemeReportFileList, Integer colCount, DocumentBuilder builder) throws Exception {
        //根据不同列数设置 表格与图片的宽度 总宽度为560
        int maxWidth = 325;
        int cellWidth = maxWidth / colCount;
        int width = maxWidth / colCount;
        int height = maxWidth / colCount;
        if (schemeReportFileList.size() == 1) {
            height = 145;
        }
        if (CollectionUtils.isEmpty(schemeReportFileList)) throw new RuntimeException("imgPathList empty");
        if (colCount == null || colCount <= 0) throw new RuntimeException("colCount empty");
        if (builder == null) throw new RuntimeException("builder empty");
        SysAttachmentDto baseQuery = new SysAttachmentDto();
        baseQuery.setFieldsName("live_situation_select_supplement");
        baseQuery.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
        TreeMap<SchemeReportFileItem, List<String>> map = schemeReportFileService.transform(schemeReportFileList, baseQuery);
        if (map.isEmpty()) {
            try {
                throw new Exception("没有正确的获取到图片");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return;
        }
        //设置样式
        builder.getFont().setName("宋体");
        builder.getFont().setSize(10.5);
        builder.getCellFormat().getBorders().setColor(Color.white);
        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
        builder.getCellFormat().setWidth(cellWidth);
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        Set<SchemeReportFileItem> itemSet = map.keySet();
        List<List<SchemeReportFileItem>> splitsList = new ExcelImportUtils.SplitsList<SchemeReportFileItem>().splitsList(Lists.newArrayList(itemSet), colCount);
        builder.startTable();
        for (List<SchemeReportFileItem> list : splitsList) {
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            List<String> names = new ArrayList<>(list.size());
            //传入图片
            for (SchemeReportFileItem schemeReportFileItem : list) {
                builder.insertCell();
                List<Map.Entry<SchemeReportFileItem, List<String>>> filter = LangUtils.filter(map.entrySet(), obj -> obj.getKey().getId().equals(schemeReportFileItem.getId()));
                if (CollectionUtils.isEmpty(filter)) {
                    continue;
                }
                List<String> paths = filter.get(0).getValue();//刚好一个所以取0
                String imgPath = null;
                try {
                    imgPath = FileUtils.getCombinationOfhead(paths);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    continue;
                }
                if (StringUtils.isNotBlank(imgPath)) {
                    builder.insertImage(imgPath, RelativeHorizontalPosition.MARGIN, 0,
                            RelativeVerticalPosition.MARGIN, 0, width, height, WrapType.INLINE);
                }
                names.add(schemeReportFileItem.getFileName());
            }
            builder.endRow();
            //在图片下面一行继续插入一行作为显示图片的名称
            if (CollectionUtils.isEmpty(names)) {
                continue;
            }
            for (String name : names) {
                builder.insertCell();
                builder.writeln(AsposeUtils.getValue(name));
            }
            builder.endRow();
        }
        builder.endTable();
    }

}
