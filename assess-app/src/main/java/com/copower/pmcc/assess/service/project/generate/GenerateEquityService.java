package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordCenterService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权益分析信息
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
        int k = 0;
        for (Map.Entry<String, List<SchemeJudgeObject>> entry : listLinkedHashMap.entrySet()) {
            List<DeclareRecord> declareRecordList = this.getDeclareRecordList(entry.getValue());
            if (CollectionUtils.isEmpty(declareRecordList)) {
                continue;
            }
            documentBuilder.writeln(String.format("1、房屋权益状况:%s", getRightsAndInterests(HOUSE, declareRecordList, entry.getValue())));
            documentBuilder.insertHtml(String.format("2、他权类别:%s", getSurveyAssetInventoryRightType(entry.getValue(), projectInfoVo)),true);
            documentBuilder.writeln(String.format("3、特殊情况:%s", getSpecialcase(entry.getValue(),projectInfoVo)));
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
     * 特殊情况
     * @param schemeJudgeObjectList
     * @param projectInfoVo
     * @return
     */
    public String getSpecialcase(List<SchemeJudgeObject> schemeJudgeObjectList, ProjectInfoVo projectInfoVo){
        StringBuilder stringBuilder = new StringBuilder(8);
        Set<String> stringSet = Sets.newHashSet();
        Set<String> stringHashSet = Sets.newHashSet();
        //获得他项权利  和 关联估价对象
        schemeJudgeObjectList.stream().forEach(oo -> {
            if (oo.getDeclareRecordId() != null) {
                List<SurveyAssetInventoryRightRecord> list = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordByDeclareRecord(oo.getDeclareRecordId(), projectInfoVo.getId());
                if (CollectionUtils.isNotEmpty(list)) {
                    list.stream().forEach( po -> {
                        stringHashSet.add(po.getSpecialcase());
                    });
                    stringSet.add(String.format("%s%s",oo.getName(),stringHashSetJoin(stringHashSet)));
                }
            }
        });
        if (CollectionUtils.isNotEmpty(stringSet)){
            appendRightsAndInterest(stringHashSetJoin(stringHashSet), stringBuilder, ";");
        }
        if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
            stringBuilder.append(" ");
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
        //利用他权类别 对 他项权利进行分组
        Map<String, List<SurveyAssetInventoryRightVo>> stringListMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(rightList)) {
            rightList.stream().forEach(oo -> {
                List<SurveyAssetInventoryRightVo> rightVoListA = stringListMap.get(oo.getCategoryName());
                if (CollectionUtils.isEmpty(rightVoListA)) {
                    rightVoListA = Lists.newArrayList();
                }
                rightVoListA.add(oo);
                stringListMap.put(oo.getCategoryName(), rightVoListA);
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
        if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
            stringBuilder.append(" ");
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
        if (Objects.equal(HOUSE, type)) {
            List<Integer> integerList = schemeJudgeObjectList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            stringBuilder.append(generateCommonMethod.convertNumber(integerList)).append(SCHEME_JUDGEOBJECT_NAME);
            Set<String> stringHashSet = Sets.newHashSet();
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

        }
        if (StringUtils.isNotBlank(stringBuilder.toString().trim())) {
            stringBuilder.append(" ");
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
        if (CollectionUtils.isNotEmpty(stringHashSet)) {
            String s = StringUtils.join(stringHashSet, ",");
            stringHashSet.clear();
            if (StringUtils.isNotBlank(s)) {
                return s;
            }
        }
        return "";
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
