package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectSimpleDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyRightGroupDto;
import com.copower.pmcc.assess.dto.output.data.DataPropertyServiceItemVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
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
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取土地权益信息
     *
     * @param judgeObjects
     * @return
     */
    public String getLandEquity(BasicEstate basicEstate, List<SchemeJudgeObject> judgeObjects) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
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
        String natrueString = generateCommonMethod.judgeEachDesc(natrueMap, "", ",", false);
        String landCertUseString = generateCommonMethod.judgeEachDesc(landCertUseMap, "", ",", false);
        String ownershipString = generateCommonMethod.judgeEachDesc(ownershipMap, "", ",", false);
        if (StringUtils.isNotBlank(natrueString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("土地取得方式:%s", generateCommonMethod.trim(natrueString))));
        }
        if (StringUtils.isNotBlank(landCertUseString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("土地用途:%s", generateCommonMethod.trim(landCertUseString))));
        }
        if (StringUtils.isNotBlank(ownershipString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("权益人:%s", generateCommonMethod.trim(ownershipString))));
        }

        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        if (estateLandState != null) {
            //规划用途
            String plotRatio = estateLandState.getPlotRatio();//容积率
            String buildingDensity = estateLandState.getBuildingDensity();//建筑密度
            String greenSpaceRate = estateLandState.getGreenSpaceRate();//绿地率
            if (StringUtils.isNotBlank(plotRatio) || StringUtils.isNotBlank(buildingDensity) || StringUtils.isNotBlank(greenSpaceRate)) {
                StringBuilder landStateBuilder = new StringBuilder();
                if (StringUtils.isNotBlank(plotRatio))
                    landStateBuilder.append(String.format("容积率%s，", plotRatio));
                if (StringUtils.isNotBlank(buildingDensity))
                    landStateBuilder.append(String.format("建筑密度%s，", buildingDensity));
                if (StringUtils.isNotBlank(greenSpaceRate))
                    landStateBuilder.append(String.format("绿地率%s，", greenSpaceRate));
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("规划条件:%s", generateCommonMethod.trim(landStateBuilder.toString()))));
            }

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
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("土地开发程度:%s", generateCommonMethod.trim(utilizeBuilder.toString()))));
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
        //转让限制
        String stringSpecialcase = this.getTransferLimit(judgeObjects, null);
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
     * 获取转让限制
     *
     * @param judgeObjects
     * @return
     */
    public String getTransferLimit(List<SchemeJudgeObject> judgeObjects, Map<Integer, SchemeJudgeObjectSimpleDto> judgeObjectSimpleDtoMap) {
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

    /**
     * 获取房屋权益状况
     *
     * @param judgeObjects
     * @return
     */
    public String getHouseEquity(List<SchemeJudgeObject> judgeObjects, Integer projectId) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> propertyMap = Maps.newHashMap();
        Map<Integer, String> natureMap = Maps.newHashMap();
        Map<Integer, String> certUseMap = Maps.newHashMap();
        Map<Integer, String> publicSituationMap = Maps.newHashMap();
        Map<Integer, String> ownershipMap = Maps.newHashMap();
        Map<Integer, SchemeJudgeObjectSimpleDto> judgeObjectSimpleDtoMap = Maps.newHashMap();//用于综合评价生成
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            SchemeJudgeObjectSimpleDto simpleDto = new SchemeJudgeObjectSimpleDto();
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
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
                BaseDataDic baseDataDic = baseDataDicService.getDataDicById(dataProperty.getSocialPrestige());
                simpleDto.setSocialPrestige(baseDataDic.getName());
                propertyMap.put(number, getProperty(dataProperty));
            }
            simpleDto.setDeclareRecordId(judgeObject.getDeclareRecordId());
            simpleDto.setJudgeObjectId(judgeObject.getId());
            simpleDto.setNumber(number);
            judgeObjectSimpleDtoMap.put(judgeObject.getDeclareRecordId(), simpleDto);
        }
        String natureString = generateCommonMethod.judgeEachDesc(natureMap, "", ",", false);
        if (StringUtils.isNotBlank(natureString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("房屋性质:%s", generateCommonMethod.trim(natureString))));
        }
        String certUseString = generateCommonMethod.judgeEachDesc(certUseMap, "", ",", false);
        if (StringUtils.isNotBlank(certUseString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("规划用途:%s", generateCommonMethod.trim(certUseString))));
        }
        String publicSituationString = generateCommonMethod.judgeEachDesc(publicSituationMap, "", ",", false);
        if (StringUtils.isNotBlank(publicSituationString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("共有情况:%s", generateCommonMethod.trim(publicSituationString))));
        }
        String ownershipString = generateCommonMethod.judgeEachDesc(ownershipMap, "", ",", false);
        if (StringUtils.isNotBlank(ownershipString)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("权益人:%s", generateCommonMethod.trim(ownershipString))));
        }
        //他项权利类别
        String stringRightCategory = this.getRightCategory(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringRightCategory))
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("他项权利:%s", generateCommonMethod.trim(stringRightCategory))));
        //转让限制
        String stringTransferLimit = this.getTransferLimit(judgeObjects, judgeObjectSimpleDtoMap);
        if (StringUtils.isNotBlank(stringTransferLimit))
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("转让限制:%s", generateCommonMethod.trim(stringTransferLimit))));

        //他权综合描述
        String stringRightComprehensiveDesc = this.getRightComprehensiveDesc(projectId, judgeObjects);
        if (StringUtils.isNotBlank(stringRightComprehensiveDesc))
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("他权综述:%s", generateCommonMethod.trim(stringRightComprehensiveDesc))));

        //物业信誉与管理
        String propertyDesc = generateCommonMethod.judgeEachDesc(propertyMap, "", ",", false);
        if (StringUtils.isNotBlank(propertyDesc)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("物业:%s", generateCommonMethod.trim(propertyDesc))));
        }

        //房产评估综合评价
        String stringOverallMerit = getOverallMerit(projectId, judgeObjectSimpleDtoMap);
        if (StringUtils.isNotBlank(stringOverallMerit)) {
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("综合评价:%s", generateCommonMethod.trim(stringOverallMerit))));
        }
        return stringBuilder.toString();
    }

    public String getProperty(DataProperty dataProperty) {
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
    public String getRightComprehensiveDesc(Integer projectId, List<SchemeJudgeObject> judgeObjects) {
        List<SurveyJudgeObjectGroupDto> list = surveyAssetInventoryRightRecordService.groupJudgeObject(projectId, judgeObjects);
        if (CollectionUtils.isEmpty(list)) return null;
        Map<Integer, String> map = Maps.newHashMap();
        for (SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto : list) {
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "强"))
                map.put(generateCommonMethod.parseIntJudgeNumber(surveyJudgeObjectGroupDto.getJudgeObjectNumber()), "产权清晰、权力明确、无特定转让限制。");
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "一般"))
                map.put(generateCommonMethod.parseIntJudgeNumber(surveyJudgeObjectGroupDto.getJudgeObjectNumber()), "产权清晰、权力明确、转让受特定限制");
            if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "弱"))
                map.put(generateCommonMethod.parseIntJudgeNumber(surveyJudgeObjectGroupDto.getJudgeObjectNumber()), "产权清晰、权力明确、转让受到限制");
        }
        return generateCommonMethod.judgeEachDesc(map, "", "。", false);
    }

    /**
     * 获取房产评估综合评价
     *
     * @return
     */
    public String getOverallMerit(Integer projectId, Map<Integer, SchemeJudgeObjectSimpleDto> judgeObjectSimpleDtoMap) throws Exception {
        //先找出有他权其它的权证数据
        Integer otherId = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_TASKRIGHT_OTHER).getId();
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
            if (isRightEmpty && !isTransferLimit) {
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
