package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.dto.output.data.DataPropertyServiceItemVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.assess.service.data.DataPropertyServiceItemService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 权益分析信息
 *
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
    private DataPropertyService dataPropertyService;
    @Autowired
    private DataPropertyServiceItemService dataPropertyServiceItemService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取土地权益信息
     *
     * @param judgeObjects
     * @return
     */
    public String getLandEquity(BasicEstate basicEstate, List<SchemeJudgeObject> judgeObjects) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> declareMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                StringBuilder declareBuilder = new StringBuilder();
                if (StringUtils.equals(declareRecord.getLandRightType(), "国用"))
                    declareBuilder.append("国有土地使用权");
                if (StringUtils.equals(declareRecord.getLandRightType(), "集用"))
                    declareBuilder.append("集体土地所有权");
                declareBuilder.append(declareRecord.getLandRightNature()).append("，")
                        .append(declareRecord.getLandCertUse()).append("，").append(String.format("权益人%s", declareRecord.getOwnership()));
                declareMap.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), declareBuilder.toString());
            }
        }
        String declareDesc = generateCommonMethod.judgeEachDesc(declareMap, "", ",", false);
        if (StringUtils.isNotBlank(declareDesc)) {
            stringBuilder.append(generateCommonMethod.trim(declareDesc));
        }

        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        if (estateLandState != null) {
            //规划用途
            String plotRatio = estateLandState.getPlotRatio();//容积率
            String buildingDensity = estateLandState.getBuildingDensity();//建筑密度
            String greenSpaceRate = estateLandState.getGreenSpaceRate();//绿地率
            if (StringUtils.isNotBlank(plotRatio) || StringUtils.isNotBlank(buildingDensity) || StringUtils.isNotBlank(greenSpaceRate)) {
                StringBuilder landStateBuilder = new StringBuilder("规划条件");
                if (StringUtils.isNotBlank(plotRatio))
                    landStateBuilder.append(String.format("容积率%s，", plotRatio));
                if (StringUtils.isNotBlank(buildingDensity))
                    landStateBuilder.append(String.format("建筑密度%s，", buildingDensity));
                if (StringUtils.isNotBlank(greenSpaceRate))
                    landStateBuilder.append(String.format("绿地率%s，", greenSpaceRate));
                stringBuilder.append(generateCommonMethod.trim(landStateBuilder.toString()));
            }

            //土地利用现状
            if (estateLandState.getDevelopmentDegree() != null) {
                String degreeName = baseDataDicService.getNameById(estateLandState.getDevelopmentDegree());
                StringBuilder utilizeBuilder = new StringBuilder(degreeName);
                if (degreeName.contains("熟地")) {
                    if (StringUtils.isNotBlank(estateLandState.getDevelopmentDegreeContent())) {
                        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(estateLandState.getDevelopmentDegreeContent()));
                        integers.forEach(o -> utilizeBuilder.append(baseDataDicService.getNameById(o)).append("、"));
                    }
                } else {
                    utilizeBuilder.append(estateLandState.getDevelopmentDegreeRemark());
                }
                stringBuilder.append(generateCommonMethod.trim(utilizeBuilder.toString()));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取土地权益完整信息
     *
     * @param judgeObjects
     * @return
     */
    public String getLandEquityFull(BasicEstate basicEstate, List<SchemeJudgeObject> judgeObjects, Integer projectId) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(this.getLandEquity(basicEstate, judgeObjects));
        //他项权利类别
        String stringRightCategory = this.getRightCategory(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringRightCategory))
            stringBuilder.append(stringRightCategory);
        //特殊情况
        String stringSpecialcase = this.getSpecialcase(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringSpecialcase))
            stringBuilder.append(stringSpecialcase);
        return stringBuilder.toString();
    }

    /**
     * 获取他权类别描述
     *
     * @param projectId
     * @param judgeObjects
     * @return
     */
    public String getRightCategory(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyRightGroupDto> groupDtoList = surveyAssetInventoryRightRecordService.groupRightByCategory(projectId, judgeObjects);
        if (CollectionUtils.isEmpty(groupDtoList)) return null;
        StringBuilder rightBuilder = new StringBuilder();
        for (SurveyRightGroupDto surveyRightGroupDto : groupDtoList) {
            if (groupDtoList.size() > 1) {
                String judgeNumber = generateCommonMethod.convertNumber(schemeJudgeObjectService.getJudgeNumberByDeclareIds(Lists.newArrayList(surveyRightGroupDto.getDeclareRecordIds())));
                rightBuilder.append(String.format("%s号", judgeNumber));
            }
            rightBuilder.append(surveyRightGroupDto.getRemark()).append("，");
        }
        return generateCommonMethod.trim(rightBuilder.toString());
    }

    /**
     * 获取特殊情况
     *
     * @param projectId
     * @param judgeObjects
     * @return
     */
    public String getSpecialcase(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        Map<String, List<Integer>> map = surveyAssetInventoryRightRecordService.groupSpecialcase(projectId, judgeObjects);
        if (map.isEmpty()) return null;
        StringBuilder specialcaseBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> stringListEntry : map.entrySet()) {
            if (map.size() > 1) {
                String judgeNumber = generateCommonMethod.convertNumber(schemeJudgeObjectService.getJudgeNumberByDeclareIds(Lists.newArrayList(stringListEntry.getValue())));
                specialcaseBuilder.append(String.format("%s号", judgeNumber));
            }
            specialcaseBuilder.append(stringListEntry.getKey()).append("，");
        }
        return generateCommonMethod.trim(specialcaseBuilder.toString());
    }

    /**
     * 获取房屋权益状况
     *
     * @param judgeObjects
     * @return
     */
    public String getHouseEquity(List<SchemeJudgeObject> judgeObjects, Integer projectId) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> declareMap = Maps.newHashMap();
        Map<Integer, String> propertyMap = Maps.newHashMap();
        String socialPrestige = null;
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                StringBuilder declareBuilder = new StringBuilder();
                if (StringUtils.isNotBlank(declareRecord.getNature()))
                    declareBuilder.append(String.format("房屋性质%s、", declareRecord.getNature()));
                if (StringUtils.isNotBlank(declareRecord.getCertUse()))
                    declareBuilder.append(String.format("规划用途%s、", declareRecord.getCertUse()));
                if (StringUtils.isNotBlank(declareRecord.getPublicSituation()))
                    declareBuilder.append(String.format("共有情况%s、", declareRecord.getPublicSituation()));
                if (StringUtils.isNotBlank(declareRecord.getOwnership()))
                    declareBuilder.append(String.format("权益人%s、", declareRecord.getOwnership()));
                declareMap.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), declareBuilder.toString());
            }

            BasicApply exploreBasicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(exploreBasicApply.getId());
            if (StringUtils.isNotBlank(basicBuilding.getProperty())) {//物业信誉与管理
                StringBuilder propertyBuilder = new StringBuilder();
                DataProperty dataProperty = dataPropertyService.getByDataPropertyId(Integer.valueOf(basicBuilding.getProperty()));
                BaseDataDic baseDataDic = baseDataDicService.getDataDicById(dataProperty.getSocialPrestige());
                socialPrestige = baseDataDic.getName();
                propertyBuilder.append(String.format("%s信誉%s，", dataProperty.getName(), baseDataDic.getRemark()));
                List<DataPropertyServiceItemVo> serviceItemVoList = dataPropertyServiceItemService.getListByMasterId(Integer.valueOf(basicBuilding.getProperty()));
                if (CollectionUtils.isNotEmpty(serviceItemVoList)) {
                    List<BaseDataDic> reputationList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_COMPANY_REPUTATION);
                    reputationList.sort((o1, o2) -> o2.getSorting().compareTo(o1.getSorting()));
                    List<Integer> gradeEvaluations = LangUtils.transform(serviceItemVoList, o -> o.getGradeEvaluation());
                    for (BaseDataDic dataDic : reputationList) {
                        if (gradeEvaluations.contains(dataDic.getId())) {
                            propertyBuilder.append(String.format("管理%s，", dataDic.getRemark()));
                            break;
                        }
                    }
                }
                propertyMap.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), propertyBuilder.toString());
            }
        }
        String declareDesc = generateCommonMethod.judgeEachDesc(declareMap, "", ",", false);
        if (StringUtils.isNotBlank(declareDesc)) {
            stringBuilder.append(generateCommonMethod.trim(declareDesc));
        }

        //他项权利类别
        String stringRightCategory = this.getRightCategory(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringRightCategory))
            stringBuilder.append(generateCommonMethod.trim(stringRightCategory));
        //特殊情况
        String stringSpecialcase = this.getSpecialcase(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringSpecialcase))
            stringBuilder.append(generateCommonMethod.trim(stringSpecialcase));

        //他权综合描述
        String stringRightComprehensiveDesc = this.getRightComprehensiveDesc(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringRightComprehensiveDesc))
            stringBuilder.append(generateCommonMethod.trim(stringRightComprehensiveDesc));

        //物业信誉与管理
        String propertyDesc = generateCommonMethod.judgeEachDesc(propertyMap, "", ",", false);
        if (StringUtils.isNotBlank(propertyDesc)) {
            stringBuilder.append(generateCommonMethod.trim(propertyDesc));
        }

        //房产评估综合评价
        String stringOverallMerit = getOverallMerit(StringUtils.isEmpty(stringRightCategory), StringUtils.isEmpty(stringSpecialcase), socialPrestige);
        if (StringUtils.isNotBlank(stringOverallMerit)) {
            stringBuilder.append(generateCommonMethod.trim(stringOverallMerit));
        }
        return stringBuilder.toString();
    }

    /**
     * 获取他权综合描述
     *
     * @param projectId
     * @param judgeObjects
     * @return
     */
    public String getRightComprehensiveDesc(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyJudgeObjectGroupDto> list = surveyAssetInventoryRightRecordService.groupJudgeObject(projectId, judgeObjects);
        if (CollectionUtils.isEmpty(list)) return null;
        Map<Integer, String> map = Maps.newHashMap();
        for (SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto : list) {
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "强"))
                map.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "对产权清晰、权力明确、无特定转让限制。");
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "一般"))
                map.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "对产权清晰、权力明确、转让受特定限制");
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "弱"))
                map.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "对产权清晰、权力明确、转让受到限制");
        }
        return generateCommonMethod.judgeSummaryDesc(map, "", false);
    }

    /**
     * 获取房产评估综合评价
     *
     * @param isRightEmpty
     * @param isSpecialcaseEmpty
     * @param socialPrestige
     * @return
     */
    public String getOverallMerit(Boolean isRightEmpty, Boolean isSpecialcaseEmpty, String socialPrestige) {
        if (StringUtils.isBlank(socialPrestige)) return null;
        if (isRightEmpty && isSpecialcaseEmpty) {
            switch (socialPrestige) {
                case "优":
                    return "对提升房产价值有较大影响。";
                case "良":
                    return "对提升房产价值有一定影响。";
                case "中":
                    return "对提升房产价值影响一般。";
                case "差":
                    return "对提升房产价值有一定的负面影响。";
            }
        }
        if (isRightEmpty && !isSpecialcaseEmpty) {
            switch (socialPrestige) {
                case "优":
                    return "对提升房产价值受到一定限制。";
                case "良":
                    return "对提升房产价值受到较大限限制。";
                case "中":
                    return "对提升房产价值受到比较大的限限制。";
                case "差":
                    return "对提升房产价值受到相当大的限限制。";
            }
        }
        if (!isRightEmpty && !isSpecialcaseEmpty) {
            switch (socialPrestige) {
                case "优":
                    return "对提升房产价值受到比较大的限制。";
                case "良":
                    return "对提升房产价值受到相当大的限制。";
                case "中":
                    return "对提升房产价值受到严得限制。";
                case "差":
                    return "对提升房产价值受到重大限制。";
            }
        }
        return null;
    }

}
