package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationHypothesisDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.project.generate.BasicExamineHandle;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.*;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 3.1.2.12	评估假设
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationHypothesisService {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationHypothesisDao evaluationHypothesisDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private GenerateReportInfoService generateReportGenerationService;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;


    /**
     * 保存数据
     *
     * @param formData
     */
    public void saveAndUpdate(String formData) {
        DataEvaluationHypothesis evaluationHypothesis = JSON.parseObject(formData, DataEvaluationHypothesis.class);
        if (evaluationHypothesis.getId() != null && evaluationHypothesis.getId() > 0) {
            evaluationHypothesisDao.updateHypothesis(evaluationHypothesis);
        } else {
            evaluationHypothesis.setCreator(commonService.thisUserAccount());
            evaluationHypothesisDao.addHypothesis(evaluationHypothesis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(evaluationHypothesis.getId(), SchemeSupportTypeEnum.HYPOTHESIS.getKey());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeHypothesis(Integer id) {
        return evaluationHypothesisDao.removeHypothesis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataEvaluationHypothesis getHypothesis(Integer id) {
        return evaluationHypothesisDao.getHypothesis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getHypothesisList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataEvaluationHypothesis> hypothesisList = evaluationHypothesisDao.getHypothesisList(name);
        List<DataEvaluationHypothesisVo> vos = LangUtils.transform(hypothesisList, p -> getHypothesisVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据委估目的及评估方法获取数据列表
     *
     * @param purpose
     * @return
     */
    public List<DataEvaluationHypothesis> getHypothesisList(Integer type, Integer category, Integer purpose) {
        String typeStr = new String();
        String categoryStr = new String();
        String methodStr = new String();
        String purposeStr = new String();
        if (type != null && type > 0) {
            typeStr = String.format(",%s,", type);
        }
        if (category != null && category > 0) {
            categoryStr = String.format(",%s,", category);
        }
        if (purpose != null && purpose > 0) {
            purposeStr = String.format(",%s,", purpose);
        }
        return evaluationHypothesisDao.getEnableHypothesisList(typeStr, categoryStr, purposeStr);
    }


    public DataEvaluationHypothesisVo getHypothesisVo(DataEvaluationHypothesis hypothesis) {
        if (hypothesis == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        DataEvaluationHypothesisVo vo = new DataEvaluationHypothesisVo();
        BeanUtils.copyProperties(hypothesis, vo);
        if (StringUtils.isNotBlank(hypothesis.getMethod())) {
            vo.setMethodStr(baseDataDicService.getDataDicName(methodDicList, hypothesis.getMethod()));
        }
        if (StringUtils.isNotBlank(hypothesis.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeStr(baseDataDicService.getDataDicName(purposeDicList, hypothesis.getEntrustmentPurpose()));
        }
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(hypothesis.getType(), hypothesis.getCategory()));
        return vo;
    }

    /**
     * 获取上报告内容
     *
     * @param projectInfo
     * @return
     */
    public String getReportHypothesis(ProjectInfo projectInfo, Integer areaGroupId) throws Exception {
        List<DataEvaluationHypothesis> hypothesisList = this.getHypothesisList(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), projectInfo.getEntrustPurpose());
        if (CollectionUtils.isEmpty(hypothesisList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaGroupId);
        //对应委估对象
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        Integer order = 0;
        Integer order2 = 0;
        Integer order3 = 0;
        for (int i = 0; i < hypothesisList.size(); i++) {
            DataEvaluationHypothesis basis = hypothesisList.get(i);
            //未定事项假设
            if (AssessReportFieldConstant.HYPOTHESIS_UNCERTAIN_MATTER.equals(basis.getFieldName())) {
                List<Integer> actualTimenumbers = new ArrayList<>();

                Set<String> times = new HashSet();

                List<Map<Integer, String>> maps = Lists.newArrayList();
                List<Map<List<Integer>, String>> group = Lists.newArrayList();

                Set<String> completedTime = Sets.newHashSet();
                List<Integer> purposeNumbers = new ArrayList<>();
                StringBuilder actualPurpose = new StringBuilder();
                StringBuilder settingPurpose = new StringBuilder();
                for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                    if (schemeJudgeObject.getDeclareRecordId() == null || schemeJudgeObject.getDeclareRecordId() == 0) {
                        continue;
                    }
                    actualTimenumbers.add(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()));
                    BasicBuilding building = basicBuildingService.getBasicBuildingByApplyId(schemeJudgeObject.getBasicApplyId());
                    if (building != null){
                        //实际调查
                        Integer type = building.getCompletedTimeType();
                        if (type != null && baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.TIME_ACTUAL_SURVEY).getId().equals(type)) {
                            Map<Integer, String> map = Maps.newHashMap();
                            if (building.getBeCompletedTime() != null) {
                                completedTime.add(String.valueOf(DateUtils.getYear(building.getBeCompletedTime())));
                                times.add(String.valueOf(DateUtils.getYear(building.getBeCompletedTime())));
                                map.put(Integer.valueOf(schemeJudgeObject.getNumber()), String.valueOf(DateUtils.getYear(building.getBeCompletedTime())));
                            }
                            maps.add(map);
                        }
                    }

                    //用途
                    Integer purpose = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE).getId();
                    SurveyAssetInventoryContent surveyAsset = new SurveyAssetInventoryContent();
                    surveyAsset.setInventoryContent(purpose);
                    surveyAsset.setProjectId(projectInfo.getId());
                    SurveyAssetInventoryContent singleObject = surveyAssetInventoryContentDao.getSingleObject(surveyAsset);
                    if (StringUtils.isEmpty(singleObject.getRegistration()) || "无".equals(singleObject.getRegistration().trim())) {
                        purposeNumbers.add(generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber()));
                        SchemeJudgeObjectVo schemeJudgeObjectVo = schemeJudgeObjectService.getSchemeJudgeObjectVo(schemeJudgeObject);
                        actualPurpose.append(schemeJudgeObject.getPracticalUse()).append("、");
                        settingPurpose.append(schemeJudgeObjectVo.getSetUseName()).append("、");
                    }
                }

                for (String time : times) {
                    Map<List<Integer>, String> map = Maps.newHashMap();
                    List<Integer> number = new ArrayList<>();
                    for (int k = 0; k < maps.size(); k++) {
                        if (maps.get(k).containsValue(time)) {
                            for (Integer key : maps.get(k).keySet()) {
                                number.add(key);
                            }
                        }
                    }
                    map.put(number, time);
                    group.add(map);
                }

                if (CollectionUtils.isNotEmpty(actualTimenumbers) || CollectionUtils.isNotEmpty(purposeNumbers)) {

                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                    stringBuilder.append(generateCommonMethod.getIndentHtml(basis.getTemplate()));
                }
                if (CollectionUtils.isEmpty(actualTimenumbers) && CollectionUtils.isEmpty(purposeNumbers)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                    stringBuilder.append(generateCommonMethod.getIndentHtml("无未定事项假设。"));
                }
                if (CollectionUtils.isNotEmpty(group)) {
                    if (group.size() == 1) {
                        for (Map.Entry<List<Integer>, String> entry : group.get(0).entrySet()) {
                            DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.TIME_ACTUAL_SURVEY);
                            stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{竣工日期}", entry.getValue()).replace("#{估价对象号}", (generateCommonMethod.convertNumber(entry.getKey()) + "号"))));
                        }
                    } else {
                        DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.TIME_ACTUAL_SURVEY);
                        stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{竣工日期}", String.join("、", completedTime)).replace("#{估价对象号}", (generateCommonMethod.convertNumber(actualTimenumbers) + "号"))));
                    }
                }
                if (CollectionUtils.isNotEmpty(purposeNumbers)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_PURPOSE);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{设定用途}", settingPurpose.deleteCharAt(settingPurpose.length() - 1)).replace("#{实际用途}", actualPurpose.deleteCharAt(actualPurpose.length() - 1)).replace("#{估价对象号}", (generateCommonMethod.convertNumber(purposeNumbers) + "号"))));
                }
            }

            //不相一致假设
            if (AssessReportFieldConstant.HYPOTHESIS_INCONFORMITY.equals(basis.getFieldName())) {
                StringBuilder stringConsistent = new StringBuilder();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    if (judgeObject.getDeclareRecordId() == null || judgeObject.getDeclareRecordId() == 0) {
                        continue;
                    }
                    List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(judgeObject.getDeclareRecordId());
                    if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                        continue;
                    }
                    //对应资产清查内容
                    for (SurveyAssetInventory surveyAssetInventory : surveyAssetInventories) {
                        List<SurveyAssetInventoryContent> SurveyAssetInventoryContents = surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(surveyAssetInventory.getId());
                        if (CollectionUtils.isEmpty(SurveyAssetInventoryContents)) {
                            continue;
                        }
                        for (SurveyAssetInventoryContent item : SurveyAssetInventoryContents) {
                            if (item.getInventoryContent() == null) continue;
                            String fieldName = baseDataDicService.getCacheDataDicById(item.getInventoryContent()).getFieldName();
                            if (StringUtils.isBlank(fieldName)) continue;
                            if ("不一致".equals(item.getAreConsistent())) {
                                DataReportTemplateItem dataReportTemplateByField = null;
                                if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS.equals(fieldName))
                                    dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.LOAD_ADDRESS);
                                if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS.equals(fieldName))
                                    dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.REGISTERED_ADDRESS);
                                if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE.equals(fieldName))
                                    dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.REGISTRATION_PURPOSES);
                                if (dataReportTemplateByField != null) {
                                    String template = dataReportTemplateByField.getTemplate()
                                            .replace("#{委估对象号}", generateCommonMethod.getSchemeJudgeObjectShowName2(judgeObject, judgeObjectList))
                                            .replace("#{登记信息}", StringUtils.defaultString(item.getRegistration()))
                                            .replace("#{实际信息}", StringUtils.defaultString(item.getActual()))
                                            .replace("#{证明人}", StringUtils.defaultString(item.getVoucher()))
                                            .replace("#{证明文件}", StringUtils.defaultString(item.getCredential()))
                                            .replace("#{一致结论}", StringUtils.defaultString(item.getSureConsistent()));
                                    stringConsistent.append(generateCommonMethod.getIndentHtml(template));
                                }
                            }
                        }

                    }
                }
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                if (stringConsistent.length() <= 0) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml("无不相一致假设。"));
                } else {
                    stringBuilder.append(stringConsistent);
                }
            }

            //依据不足假设
            if (AssessReportFieldConstant.HYPOTHESIS_GIST_INSUFFICIENT.equals(basis.getFieldName())) {
                //参考同类（不配合）缴纳情况正常
                StringBuilder paymentNormal = new StringBuilder();
                //参考同类（不配合）缴纳情况不正常
                StringBuilder paymentAbnormality = new StringBuilder();

                for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                    List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                        continue;
                    }
                    BasicHouse basicHouse = basicHouseService.getHouseByApplyId(schemeJudgeObject.getBasicApplyId());
                    //对应资产清查内容
                    SurveyAssetInventory surveyAssetInventory =surveyAssetInventories.get(0);
                    //参考同类（不配合）
                    if (basicHouse != null){
                        Integer type = basicHouse.getResearchType();
                        if (type != null && AssessExamineTaskConstant.EXAMINE_HOUSE_RESEARCH_REFERENCE.equals(baseDataDicService.getCacheDataDicById(type).getFieldName())) {
                            if ("正常".equals(surveyAssetInventory.getPaymentStatus())) {
                                paymentNormal.append(schemeJudgeObject.getNumber()).append(",");
                            } else if ("不正常".equals(surveyAssetInventory.getPaymentStatus())) {
                                paymentAbnormality.append(schemeJudgeObject.getNumber()).append(",");
                            }
                        }
                    }

                }
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                if (StringUtils.isNotBlank(paymentNormal)) {
                    String number = getSubstitutionPrincipleName(paymentNormal.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_GIST_INSUFFICIENT_REFERENCE_NORMAL);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)));
                }
                if (StringUtils.isNotBlank(paymentAbnormality)) {
                    String number = getSubstitutionPrincipleName(paymentAbnormality.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_GIST_INSUFFICIENT_REFERENCE_ABNORMALITY);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)));
                }
                if (StringUtils.isEmpty(paymentNormal) && StringUtils.isEmpty(paymentAbnormality)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml("无依据不足假设"));
                }
            }

            //背离事实假设
            if (AssessReportFieldConstant.HYPOTHESIS_DEPART_FROM_FACT.equals(basis.getFieldName())) {
                //评估基准日
                Date valuationDate = schemeAreaGroup.getValueTimePoint();
                //查勘结束日期
                GenerateReportInfo generateReportInfo = new GenerateReportInfo();
                generateReportInfo.setProjectId(projectInfo.getId());
                generateReportInfo.setAreaGroupId(areaGroupId);
                GenerateReportInfo generation = generateReportGenerationService.getGenerateReportInfo(generateReportInfo);
                Date investigationsEndDate = generation.getInvestigationsEndDate();
                //区位损坏委估对象
                StringBuilder surroundingsDamage = new StringBuilder();

                //实物损坏委估对象
                StringBuilder entityDamage = new StringBuilder();

                //有抵押权委估对象
                StringBuilder havePledge = new StringBuilder();
                String pledgeRemark = new String();
                StringBuilder pledgeContent = new StringBuilder();
                //有其他
                StringBuilder haveOther = new StringBuilder();
                String otherRemark = new String();
                StringBuilder otherContent = new StringBuilder();

                StringBuilder content = new StringBuilder();

                Integer rightId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_PLEDGE).getId();
                Integer otherId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER).getId();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {

                    if (judgeObject.getDeclareRecordId() == null && judgeObject.getDeclareRecordId() == 0) {
                        continue;
                    }
                    List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(judgeObject.getDeclareRecordId());
                    if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                        continue;
                    }
                    //对应资产清查内容
                    for (SurveyAssetInventory surveyAssetInventory : surveyAssetInventories) {
                        if ("不正常".equals(surveyAssetInventory.getRimIsNormal())) {
                            surroundingsDamage.append(judgeObject.getNumber()).append(",");
                        }
                        if ("损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                            entityDamage.append(judgeObject.getNumber()).append(",");
                        }
                    }


                    //对应的他权信息
                    List<SurveyAssetRightItem> rightList = Lists.newArrayList();
                    List<SurveyAssetRightGroup> surveyAssetInventoryRightRecordList = surveyAssetRightGroupService.getSurveyAssetRightGroupByDeclareRecord(judgeObject.getDeclareRecordId(), judgeObject.getProjectId());
                    if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                        Iterator<SurveyAssetRightGroup> iterator = surveyAssetInventoryRightRecordList.iterator();
                        while (iterator.hasNext()) {
                            rightList.addAll(surveyAssetRightGroupService.getSurveyAssetRightItemListByGroupId(iterator.next().getId()));
                        }
                    }
                    if (CollectionUtils.isNotEmpty(rightList)) {
                        Iterator<SurveyAssetRightItem> rightItemIterator = rightList.iterator();
                        while (rightItemIterator.hasNext()) {
                            SurveyAssetRightItem inventoryRight = rightItemIterator.next();
                            if (rightId.equals(inventoryRight.getCategory())) {
                                havePledge.append(judgeObject.getNumber()).append(",");
                                pledgeRemark = inventoryRight.getRemark();
                            }
                            if (otherId.equals(inventoryRight.getCategory())) {
                                haveOther.append(judgeObject.getNumber()).append(",");
                                otherRemark = inventoryRight.getRemark();
                            }
                        }
                    }
                }
                if (valuationDate.compareTo(investigationsEndDate) != 0 || StringUtils.isNotBlank(surroundingsDamage) || StringUtils.isNotBlank(entityDamage)
                        || StringUtils.isNotBlank(havePledge) || StringUtils.isNotBlank(haveOther)) {
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                    stringBuilder.append(generateCommonMethod.getIndentHtml(basis.getTemplate()));
                }
                if (valuationDate.compareTo(investigationsEndDate) != 0) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DATE_ARE_CONSISTENT);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{查勘结束日期}", DateUtils.format(investigationsEndDate, DateUtils.DATE_CHINESE_PATTERN)).replace("#{评估基准日}", DateUtils.format(valuationDate, DateUtils.DATE_CHINESE_PATTERN))));
                }
                if (StringUtils.isNotBlank(surroundingsDamage)) {
                    String number = getSubstitutionPrincipleName(surroundingsDamage.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.SURROUNDINGS_CONDITION);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{评估基准日}", DateUtils.format(valuationDate, DateUtils.DATE_CHINESE_PATTERN))));
                }
                if (StringUtils.isNotBlank(entityDamage)) {
                    String number = getSubstitutionPrincipleName(entityDamage.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ENTITY_CONDITION);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{评估基准日}", DateUtils.format(valuationDate, DateUtils.DATE_CHINESE_PATTERN))));
                }
                if (StringUtils.isNotBlank(havePledge)) {
                    String number = getSubstitutionPrincipleName(havePledge.toString());
                    pledgeContent.append(number).append("委估对象").append(pledgeRemark);
                    if (StringUtils.isNotBlank(haveOther)) {
                        pledgeContent.append("、");
                    }
                }
                if (StringUtils.isNotBlank(haveOther)) {
                    String number = getSubstitutionPrincipleName(haveOther.toString());
                    otherContent.append(number).append("委估对象").append(otherRemark);
                }
                content.append(pledgeContent).append(otherContent);
                if (StringUtils.isNotBlank(content)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_DEPART_FROM_FACT_PLEDGE);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate().replace("#{内容}", content))));
                }

            }

            //一般假设
            if (AssessReportFieldConstant.HYPOTHESIS_COMMON.equals(basis.getFieldName())) {
                StringBuilder allNum = new StringBuilder();
                //有抵押权委估对象
                StringBuilder havePledge = new StringBuilder();
                //无抵押权委估对象
                StringBuilder noPledge = new StringBuilder();
                //有其他
                StringBuilder haveOther = new StringBuilder();
                //无其他
                StringBuilder noOther = new StringBuilder();
                //无建筑面积委估对象
                StringBuilder noBuildingArea = new StringBuilder();
                //无建筑结构委估对象
                StringBuilder noBuildingConstruction = new StringBuilder();
                //无转让限制委估对象
                StringBuilder limit = new StringBuilder();
                //内容
                StringBuilder content = new StringBuilder();
                StringBuilder limitContent = new StringBuilder();

                String pledgeRemark = new String();
                String otherRemark = new String();

                Integer rightId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_PLEDGE).getId();
                Integer otherId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER).getId();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    allNum.append(judgeObject.getNumber()).append(",");

                    if (judgeObject.getDeclareRecordId() == null && judgeObject.getDeclareRecordId() == 0) {
                        continue;
                    }
                    List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(judgeObject.getDeclareRecordId());
                    if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                        continue;
                    }
                    //对应资产清查内容
                    for (SurveyAssetInventory surveyAssetInventory : surveyAssetInventories) {
                        List<SurveyAssetInventoryContent> SurveyAssetInventoryContents = surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(surveyAssetInventory.getId());
                        for (SurveyAssetInventoryContent item : SurveyAssetInventoryContents) {
                            switch (baseDataDicService.getCacheDataDicById(item.getInventoryContent()).getFieldName()) {
                                //建筑面积
                                case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA:
                                    if ("不一致".equals(item.getAreConsistent())) {
                                        noBuildingArea.append(judgeObject.getNumber()).append(",");
                                    }
                                    break;
                                //建筑结构
                                case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE:
                                    if ("不一致".equals(item.getAreConsistent())) {
                                        noBuildingConstruction.append(judgeObject.getNumber()).append(",");
                                    }
                                    break;
                            }
                        }
                        //转让限制
                        if (StringUtils.isNotBlank(surveyAssetInventory.getTransferLimit())) {
                            if (judgeObjectList.size() == 1) {
                                limitContent.append("委估对象,有转让限制").append(surveyAssetInventory.getTransferLimit()).append(";");
                            } else {
                                limitContent.append(generateCommonMethod.getSchemeJudgeObjectShowName2(judgeObject, judgeObjectList)).append("号委估对象,有转让限制").append(surveyAssetInventory.getTransferLimit()).append(";");
                            }
                        } else {
                            limit.append(judgeObject.getNumber()).append(",");
                        }
                    }


                    //对应的他权信息
                    List<SurveyAssetRightItem> rightList = Lists.newArrayList();
                    List<SurveyAssetRightGroup> surveyAssetInventoryRightRecordList = surveyAssetRightGroupService.getSurveyAssetRightGroupByDeclareRecord(judgeObject.getDeclareRecordId(), judgeObject.getProjectId());
                    if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                        Iterator<SurveyAssetRightGroup> iterator = surveyAssetInventoryRightRecordList.iterator();
                        while (iterator.hasNext()) {
                            rightList.addAll(surveyAssetRightGroupService.getSurveyAssetRightItemListByGroupId(iterator.next().getId()));
                        }
                    }
                    if (CollectionUtils.isNotEmpty(rightList)) {
                        Iterator<SurveyAssetRightItem> rightItemIterator = rightList.iterator();
                        while (rightItemIterator.hasNext()) {
                            SurveyAssetRightItem inventoryRight = rightItemIterator.next();
                            if (rightId.equals(inventoryRight.getCategory())) {
                                havePledge.append(judgeObject.getNumber()).append(",");
                                pledgeRemark = inventoryRight.getRemark();
                            }
                            if (otherId.equals(inventoryRight.getCategory())) {
                                haveOther.append(judgeObject.getNumber()).append(",");
                                otherRemark = inventoryRight.getRemark();
                            }
                        }
                    }

                }
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                stringBuilder.append(generateCommonMethod.getIndentHtml(basis.getTemplate()));

                if (StringUtils.isNotBlank(limit)) {
                    String number = getSubstitutionPrincipleName(limit.toString());
                    limitContent.append(number).append("委估对象土地使用权符合法定的转让条件为假设前提");
                }
                noPledge = minus(allNum, havePledge);
                noOther = minus(allNum, haveOther);
                if (StringUtils.isNotBlank(havePledge)) {
                    String number = getSubstitutionPrincipleName(havePledge.toString());
                    content.append(number).append("委估对象").append(pledgeRemark).append(";");
                } else {
                    content.append(judgeObjectList.size() == 1 ? "委估对象无抵押。" : "所有委估对象无抵押。");
                }
                if (StringUtils.isNotBlank(noPledge) && StringUtils.isNotBlank(havePledge)) {
                    String number = getSubstitutionPrincipleName(noPledge.toString());
                    content.append(number).append("委估对象无抵押。");
                }
                if (StringUtils.isNotBlank(haveOther)) {
                    String number = getSubstitutionPrincipleName(haveOther.toString());
                    content.append(number).append("委估对象").append(otherRemark).append(";");
                } else {
                    content.append(judgeObjectList.size() == 1 ? "委估对象无查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷。" :
                            "所有委估对象无查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷。");
                }
                if (StringUtils.isNotBlank(noOther) && StringUtils.isNotBlank(haveOther)) {
                    String number = getSubstitutionPrincipleName(noOther.toString());
                    content.append(number).append("委估对象无查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷。");
                }
                content.append(limitContent);
                //他权
                if (StringUtils.isNotBlank(content)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ALIENI_IURIS);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order) + "）", publicService.tagfilter(dataReportTemplateByField.getTemplate().replace("#{一般假设他权与转让}", content)))));
                }
                //安全质量
                DataReportTemplateItem safetyQuality = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.SAFETY_QUALITY);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order) + "）", publicService.tagfilter(safetyQuality.getTemplate()))));

                //建筑
                if (StringUtils.isNotBlank(noBuildingArea)) {
                    String number = getSubstitutionPrincipleName(noBuildingArea.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.BUILDING_AREA);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order) + "）", publicService.tagfilter(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)))));
                }
                if (StringUtils.isNotBlank(noBuildingConstruction)) {
                    String number = getSubstitutionPrincipleName(noBuildingConstruction.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.BUILDING_CONSTRUCTION);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order) + "）", publicService.tagfilter(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)))));
                }
                //合理价格
                DataReportTemplateItem reasonablePrice = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.REASONABLE_PRICE);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order) + "）", publicService.tagfilter(reasonablePrice.getTemplate()))));

                //公共设施
                DataReportTemplateItem communalFacilities = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.COMMUNAL_FACILITIES);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order) + "）", publicService.tagfilter(communalFacilities.getTemplate()))));
            }

            //评估报告的使用限制
            if (AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION.equals(basis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", ++order2, basis.getName())));
                stringBuilder.append(generateCommonMethod.getIndentHtml(basis.getTemplate()));
                //评估基准日
                Date valuationDate = schemeAreaGroup.getValueTimePoint();
                //现场查勘结束日期
                GenerateReportInfo generateReportGeneration = new GenerateReportInfo();
                generateReportGeneration.setProjectId(projectInfo.getId());
                generateReportGeneration.setAreaGroupId(areaGroupId);
                GenerateReportInfo generation = generateReportGenerationService.getGenerateReportInfo(generateReportGeneration);
                Date investigationsEndDate = generation.getInvestigationsEndDate();
                //抵押评估
                Integer pledgeId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId();

                //估价报告用途
                DataReportTemplateItem purpose = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_PURPOSE);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(purpose.getTemplate()))));

                //相差天数
                long daysBetween = (investigationsEndDate.getTime() - valuationDate.getTime()) / (60 * 60 * 24 * 1000);
                //评估基准日与报告有效期
                if (!pledgeId.equals(projectInfo.getEntrustPurpose())) {
                    DataReportTemplateItem pledge = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_PLEDGE);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(pledge.getName()))));
                    stringBuilder.append(generateCommonMethod.getIndentHtml(pledge.getTemplate()));
                } else if (pledgeId.equals(projectInfo.getEntrustPurpose()) && Math.abs(daysBetween) > 180) {
                    DataReportTemplateItem pledge = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_PLEDGE);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(pledge.getName()))));
                    stringBuilder.append(generateCommonMethod.getIndentHtml(pledge.getTemplate()));
                } else if (pledgeId.equals(projectInfo.getEntrustPurpose()) && Math.abs(daysBetween) <= 180) {
                    DataReportTemplateItem pledge = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_NOT_PLEDGE);
                    stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(pledge.getName()))));
                    stringBuilder.append(generateCommonMethod.getIndentHtml(pledge.getTemplate()));
                }
                //成交价格与报告内容
                DataReportTemplateItem content = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_CONTENT);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(content.getTemplate()))));
                //解释
                DataReportTemplateItem explain = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_EXPLAIN);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(explain.getTemplate()))));
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_USE_RESTRICTION_OTHER);
                stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s%s", "（" + (++order3) + "）", publicService.tagfilter(other.getTemplate()))));
            }


        }
        return stringBuilder.toString();
    }

    public DataEvaluationHypothesis getEvaluationHypothesis(String filedName) {
        DataEvaluationHypothesis data = new DataEvaluationHypothesis();
        data.setFieldName(filedName);
        return evaluationHypothesisDao.getSingleObject(data);
    }

    private List<SysAttachmentDto> getSysAttachmentDtos(Integer id, String tableName) {
        SysAttachmentDto bidSysAttachmentDto = new SysAttachmentDto();
        bidSysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPhase.class));
        bidSysAttachmentDto.setTableName(tableName);
        bidSysAttachmentDto.setTableId(id);
        bidSysAttachmentDto.setAppKey(applicationConstant.getAppKey());
        return baseAttachmentService.getAttachmentList(bidSysAttachmentDto);
    }

    public String getSubstitutionPrincipleName(String str) {
        String[] s = str.toString().split(",");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String item : s) {
            if (!numbers.contains(Integer.valueOf(item))) {
                numbers.add(Integer.valueOf(item));
            }
        }
        return generateCommonMethod.convertNumber(numbers) + "号";
    }

    public StringBuilder minus(StringBuilder all, StringBuilder has) {
        String[] arrAll = all.toString().split(",");
        String[] arrHas = has.toString().split(",");
        List<String> list = Arrays.asList(arrHas);
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : arrAll) {
            if (!list.contains(item)) {
                stringBuilder.append(item).append(",");
            }
        }

        return stringBuilder;
    }


}
