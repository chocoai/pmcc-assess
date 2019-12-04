package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectSimpleDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.dto.output.data.DataPropertyServiceItemVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.assess.service.data.DataPropertyServiceItemService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权益分析信息
 *
 * @author zch
 */
@Service
public class GenerateEquityService2 {
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
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private DataPropertyServiceItemService dataPropertyServiceItemService;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    public static final String Land_acquisition_methods = "土地取得方式";
    public static final String Land_use = "土地用途";
    public static final String Stakeholder = "权益人";
    public static final String PLOTRATIO = "容积率";
    public static final String BUILDINGDENSITY = "建筑密度";
    public static final String GREENSPACERATE = "绿地率";
    public static final String PLANNINGCONDITIONS = "规划条件";
    public static final String DEGREEOFLANDDEVELOPMENT = "土地开发程度";

    /**
     * 获取土地权益信息
     *
     * @param basicEstate
     * @param judgeObjects
     * @param key
     * @return
     */
    public  String getLandEquityValue(BasicEstate basicEstate, List<SchemeJudgeObject> judgeObjects, String key) {
        Map<Integer, String> natrueMap = Maps.newHashMap();
        Map<Integer, String> landCertUseMap = Maps.newHashMap();
        Map<Integer, String> ownershipMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
                natrueMap.put(number, declareRecord.getLandRightNature());
                landCertUseMap.put(number, declareRecord.getLandCertUse());
                ownershipMap.put(number, declareRecord.getOwnership());
            }
        }
        String value = "";
        switch (key) {
            case Land_acquisition_methods: {
                if (!natrueMap.isEmpty()) {
                    String natrueString = generateCommonMethod.judgeEachDesc(natrueMap, "", ",", false);
                    value = generateCommonMethod.trim(natrueString);
                }
                break;
            }
            case Land_use: {
                if (!landCertUseMap.isEmpty()) {
                    String landCertUseString = generateCommonMethod.judgeEachDesc(landCertUseMap, "", ",", false);
                    value = generateCommonMethod.trim(landCertUseString);
                }
                break;
            }
            case Stakeholder: {
                if (!ownershipMap.isEmpty()) {
                    String ownershipString = generateCommonMethod.judgeEachDesc(ownershipMap, "", ",", false);
                    value = generateCommonMethod.trim(ownershipString);
                }
                break;
            }
            default:
                break;
        }
        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        if (estateLandState != null) {
            switch (key) {
                case PLOTRATIO: {
                    value = estateLandState.getPlotRatio();//容积率
                    break;
                }
                case BUILDINGDENSITY: {
                    value = estateLandState.getBuildingDensity();//建筑密度
                    break;
                }
                case GREENSPACERATE: {
                    value = estateLandState.getGreenSpaceRate();//绿地率
                    break;
                }
                case PLANNINGCONDITIONS: {
                    //规划用途
                    String plotRatio = estateLandState.getPlotRatio();//容积率
                    String buildingDensity = estateLandState.getBuildingDensity();//建筑密度
                    String greenSpaceRate = estateLandState.getGreenSpaceRate();//绿地率
                    if (StringUtils.isNotBlank(plotRatio) || StringUtils.isNotBlank(buildingDensity) || StringUtils.isNotBlank(greenSpaceRate)) {
                        StringBuilder landStateBuilder = new StringBuilder();
                        if (StringUtils.isNotBlank(plotRatio)) {
                            landStateBuilder.append(String.format("容积率%s，", plotRatio));
                        }
                        if (StringUtils.isNotBlank(buildingDensity)) {
                            landStateBuilder.append(String.format("建筑密度%s，", buildingDensity));
                        }
                        if (StringUtils.isNotBlank(greenSpaceRate)) {
                            landStateBuilder.append(String.format("绿地率%s，", greenSpaceRate));
                        }
                        value = String.join("", "规划条件:"
                                , generateCommonMethod.trim(landStateBuilder.toString()));
                    }
                    break;
                }
                case DEGREEOFLANDDEVELOPMENT: {
                    //土地利用现状
                    if (estateLandState.getDevelopmentDegree() != null) {
                        String degreeName = baseDataDicService.getNameById(estateLandState.getDevelopmentDegree());
                        StringBuilder utilizeBuilder = new StringBuilder();
                        if (degreeName.contains("熟地")) {
                            if (StringUtils.isNotBlank(estateLandState.getDevelopmentDegreeContent())) {
                                List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(estateLandState.getDevelopmentDegreeContent()));
                                integers.forEach(o -> utilizeBuilder.append(baseDataDicService.getNameById(o)).append("、"));
                            }
                        } else {
                            utilizeBuilder.append(estateLandState.getDevelopmentDegreeRemark());
                        }
                        value = generateCommonMethod.getIndentHtml(String.format("土地开发程度:%s", generateCommonMethod.trim(utilizeBuilder.toString())));
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return value;
    }


    /**
     * 获取房屋权益状况
     *
     * @param judgeObjects
     * @return
     */
    public String getHouseEquityValue(List<SchemeJudgeObject> judgeObjects, Integer projectId, String key)  {
        String value = "";
        if (StringUtils.isEmpty(key)) {
            return value;
        }
        Map<Integer, String> propertyMap = Maps.newHashMap();
        Map<Integer, String> natureMap = Maps.newHashMap();
        Map<Integer, String> certUseMap = Maps.newHashMap();
        Map<Integer, String> publicSituationMap = Maps.newHashMap();
        Map<Integer, String> ownershipMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            if (judgeObject.getDeclareRecordId() == null){
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                natureMap.put(number, declareRecord.getNature());
                certUseMap.put(number, declareRecord.getCertUse());
                publicSituationMap.put(number, declareRecord.getPublicSituation());
                ownershipMap.put(number, declareRecord.getOwnership());
            }
            BasicApply exploreBasicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(exploreBasicApply.getId());
            if (basicBuilding.getProperty() != null) {//物业信誉与管理
                DataProperty dataProperty = dataPropertyService.getByDataPropertyId(basicBuilding.getProperty());
                propertyMap.put(number, getProperty(dataProperty));
            }
        }
        switch (key) {
            case "房屋性质": {
                if (!natureMap.isEmpty()) {
                    value = generateCommonMethod.judgeEachDesc(natureMap, "", ",", false);
                }
                break;
            }
            case "规划用途": {
                if (!certUseMap.isEmpty()) {
                    value = generateCommonMethod.judgeEachDesc(certUseMap, "", ",", false);
                }
                break;
            }
            case "共有情况": {
                if (!publicSituationMap.isEmpty()) {
                    value = generateCommonMethod.judgeEachDesc(publicSituationMap, "", ",", false);
                }
                break;
            }
            case "权益人": {
                if (!ownershipMap.isEmpty()) {
                    value = generateCommonMethod.judgeEachDesc(ownershipMap, "", ",", false);
                }
                break;
            }
            case "他项权利": {
                value = this.getRightCategory(projectId, judgeObjects);
                break;
            }
            case "转让限制": {
                value = this.getTransferLimit(judgeObjects, getSchemeJudgeObjectSimpleDto(judgeObjects));
                break;
            }
            case "他权综述": {
                value = this.getRightComprehensiveDesc(projectId, judgeObjects);
                break;
            }
            case "物业": {
                value = generateCommonMethod.judgeEachDesc(propertyMap, "", ",", false);
                break;
            }
            case "综合评价": {
                value = getOverallMerit(projectId, getSchemeJudgeObjectSimpleDto(judgeObjects));
                break;
            }
        }
        return value;
    }

    /**
     * 获取他权类别描述
     *
     * @param projectId
     * @param judgeObjects
     * @return
     */
    private String getRightCategory(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyRightGroupDto> groupDtoList = surveyAssetInventoryRightRecordService.groupRightByCategory(projectId, judgeObjects);
        if (CollectionUtils.isEmpty(groupDtoList)) return null;
        StringBuilder rightBuilder = new StringBuilder();
        for (SurveyRightGroupDto surveyRightGroupDto : groupDtoList) {
            if (groupDtoList.size() > 1) {
                ArrayList<Integer> arrayList = Lists.newArrayList(surveyRightGroupDto.getDeclareRecordIds());
                if (CollectionUtils.isNotEmpty(arrayList) && arrayList.size() > 1) {
                    String judgeNumber = generateCommonMethod.convertNumber(schemeJudgeObjectService.getJudgeNumberByDeclareIds(arrayList));
                    rightBuilder.append(String.format("%s号", judgeNumber));
                }
            }
            if (StringUtils.isNotBlank(surveyRightGroupDto.getRemark()))
                rightBuilder.append(surveyRightGroupDto.getRemark()).append("，");
        }
        return generateCommonMethod.trim(rightBuilder.toString());
    }

    /**
     * 获取转让限制
     *
     * @param judgeObjects
     * @return
     */
    private String getTransferLimit(List<SchemeJudgeObject> judgeObjects, Map<Integer, SchemeJudgeObjectSimpleDto> judgeObjectSimpleDtoMap) {
        Map<Integer, String> judgeObjectMap = FormatUtils.mappingSingleEntity(judgeObjects, o -> o.getId(), o -> o.getNumber());
        List<SurveyAssetInventory> inventoryList = surveyAssetInventoryService.getDataByDeclareIds(LangUtils.transform(judgeObjects, o -> o.getDeclareRecordId()));
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (SurveyAssetInventory surveyAssetInventory : inventoryList) {
            String transferLimit = surveyAssetInventory.getTransferLimit();
            String number = judgeObjectMap.get(surveyAssetInventory.getDeclareRecordId());
            if (StringUtils.isNotBlank(transferLimit) && StringUtils.isNotBlank(number)) {
                resultMap.put(generateCommonMethod.parseIntJudgeNumber(number), transferLimit);
                if (judgeObjectSimpleDtoMap != null && !judgeObjectSimpleDtoMap.isEmpty()) {
                    SchemeJudgeObjectSimpleDto simpleDto = judgeObjectSimpleDtoMap.get(surveyAssetInventory.getDeclareRecordId());
                    simpleDto.setTransferLimit(transferLimit);
                }
            }
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    private Map<Integer, SchemeJudgeObjectSimpleDto> getSchemeJudgeObjectSimpleDto(List<SchemeJudgeObject> judgeObjects) {
        Map<Integer, SchemeJudgeObjectSimpleDto> judgeObjectSimpleDtoMap = Maps.newHashMap();//用于综合评价生成
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            SchemeJudgeObjectSimpleDto simpleDto = new SchemeJudgeObjectSimpleDto();
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            BasicApply exploreBasicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(exploreBasicApply.getId());
            if (basicBuilding.getProperty() != null) {//物业信誉与管理
                DataProperty dataProperty = dataPropertyService.getByDataPropertyId(basicBuilding.getProperty());
                BaseDataDic baseDataDic = baseDataDicService.getDataDicById(dataProperty.getSocialPrestige());
                simpleDto.setSocialPrestige(baseDataDic.getName());
            }
            simpleDto.setDeclareRecordId(judgeObject.getDeclareRecordId());
            simpleDto.setJudgeObjectId(judgeObject.getId());
            simpleDto.setNumber(number);
            judgeObjectSimpleDtoMap.put(judgeObject.getDeclareRecordId(), simpleDto);
        }
        return judgeObjectSimpleDtoMap;
    }


    private String getProperty(DataProperty dataProperty) {
        StringBuilder propertyBuilder = new StringBuilder();
        BaseDataDic baseDataDic = baseDataDicService.getDataDicById(dataProperty.getSocialPrestige());
        propertyBuilder.append(String.format("%s信誉%s，", dataProperty.getName(), baseDataDic.getRemark()));
        List<DataPropertyServiceItemVo> serviceItemVoList = dataPropertyServiceItemService.getListByMasterId(dataProperty.getId());
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
        return propertyBuilder.toString();
    }

    /**
     * 获取他权综合描述
     *
     * @param projectId
     * @param judgeObjects
     * @return
     */
    private String getRightComprehensiveDesc(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyJudgeObjectGroupDto> list = surveyAssetInventoryRightRecordService.groupJudgeObject(projectId, judgeObjects);
        if (CollectionUtils.isEmpty(list)) return null;
        Map<Integer, String> map = Maps.newHashMap();
        for (SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto : list) {
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "强"))
                map.put(generateCommonMethod.parseIntJudgeNumber(surveyJudgeObjectGroupDto.getJudgeObjectNumber()), "产权清晰、权利明确、无特定转让限制。");
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "一般"))
                map.put(generateCommonMethod.parseIntJudgeNumber(surveyJudgeObjectGroupDto.getJudgeObjectNumber()), "产权清晰、权利明确、转让受特定限制");
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "弱"))
                map.put(generateCommonMethod.parseIntJudgeNumber(surveyJudgeObjectGroupDto.getJudgeObjectNumber()), "产权清晰、权利明确、转让受到限制");
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取房产评估综合评价
     *
     * @return
     */
    private String getOverallMerit(Integer projectId, Map<Integer, SchemeJudgeObjectSimpleDto> judgeObjectSimpleDtoMap)  {
        //先找出有他权其它的权证数据
        Integer otherId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER).getId();
        SurveyAssetInventoryRight where = new SurveyAssetInventoryRight();
        where.setProjectId(projectId);
        where.setCategory(otherId);
        List<SurveyAssetInventoryRight> rights = surveyAssetInventoryRightService.getSurveyAssetInventoryRightList(where);
        if (CollectionUtils.isNotEmpty(rights)) {
            for (SurveyAssetInventoryRight right : rights) {
                SurveyAssetInventoryRightRecord rightRecord = surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordById(right.getInventoryRightRecordId());
                for (Integer integer : FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(rightRecord.getRecordIds()))) {
                    SchemeJudgeObjectSimpleDto simpleDto = judgeObjectSimpleDtoMap.get(integer);
                    if (simpleDto != null)
                        simpleDto.setRightOther(right.getRemark());
                }
            }
        }
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<Integer, SchemeJudgeObjectSimpleDto> simpleDtoEntry : judgeObjectSimpleDtoMap.entrySet()) {
            SchemeJudgeObjectSimpleDto judgeObjectSimpleDto = simpleDtoEntry.getValue();
            Boolean isRightEmpty = StringUtils.isBlank(judgeObjectSimpleDto.getRightOther());
            Boolean isTransferLimit = StringUtils.isBlank(judgeObjectSimpleDto.getTransferLimit());
            String socialPrestige = StringUtils.isBlank(judgeObjectSimpleDto.getSocialPrestige()) ? "优" : judgeObjectSimpleDto.getSocialPrestige();
            String msg = "";
            if (isRightEmpty && isTransferLimit) {
                switch (socialPrestige) {
                    case "优":
                        msg = "对提升房产价值有较大影响。";
                        break;
                    case "良":
                        msg = "对提升房产价值有一定影响。";
                        break;
                    case "中":
                        msg = "对提升房产价值影响一般。";
                        break;
                    case "差":
                        msg = "对提升房产价值有一定的负面影响。";
                        break;
                }
            }
            if ((isRightEmpty && !isTransferLimit) || (!isRightEmpty && isTransferLimit)) {
                switch (socialPrestige) {
                    case "优":
                        msg = "对提升房产价值受到一定限制。";
                        break;
                    case "良":
                        msg = "对提升房产价值受到较大限限制。";
                        break;
                    case "中":
                        msg = "对提升房产价值受到比较大的限限制。";
                        break;
                    case "差":
                        msg = "对提升房产价值受到相当大的限限制。";
                        break;
                }
            }
            if (!isRightEmpty && !isTransferLimit) {
                switch (socialPrestige) {
                    case "优":
                        msg = "对提升房产价值受到比较大的限制。";
                        break;
                    case "良":
                        msg = "对提升房产价值受到相当大的限制。";
                        break;
                    case "中":
                        msg = "对提升房产价值受到严得限制。";
                        break;
                    case "差":
                        msg = "对提升房产价值受到重大限制。";
                        break;
                }
            }
            resultMap.put(judgeObjectSimpleDto.getNumber(), msg);
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", ".", false);
    }

}
