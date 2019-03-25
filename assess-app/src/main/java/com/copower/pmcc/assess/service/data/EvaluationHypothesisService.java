package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportTemplateItemDao;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationHypothesisDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGenerationService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 3.1.2.12	评估假设
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationHypothesisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
    private DataReportTemplateItemDao dataReportTemplateItemDao;
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
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdIncomeDao mdIncomeDao;
    @Autowired
    private GenerateReportGenerationService generateReportGenerationService;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;


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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        //对应委估对象
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        for (int i = 0; i < hypothesisList.size(); i++) {
            DataEvaluationHypothesis basis = hypothesisList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", i + 1, basis.getName())).append("</p>");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(basis.getTemplate()).append("</p>");

            //未定事项假设
            if (AssessReportFieldConstant.HYPOTHESIS_UNCERTAIN_MATTER.equals(basis.getFieldName())) {
                List<Integer> actualTimenumbers = new ArrayList<>();
                StringBuilder completedTime = new StringBuilder();

                List<Integer> purposeNumbers = new ArrayList<>();
                StringBuilder actualPurpose = new StringBuilder();
                StringBuilder settingPurpose = new StringBuilder();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //实际调查
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicBuilding building = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
                    Integer type = building.getCompletedTimeType();
                    if (type != null && baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.TIME_ACTUAL_SURVEY).getId().equals(type)) {
                        actualTimenumbers.add(Integer.valueOf(judgeObject.getNumber()));
                        completedTime.append(sdf.format(building.getBeCompletedTime())).append("、");
                    }
                    //用途
                    Integer purpose = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE).getId();
                    SurveyAssetInventoryContent surveyAsset = new SurveyAssetInventoryContent();
                    surveyAsset.setInventoryContent(purpose);
                    surveyAsset.setProjectId(projectInfo.getId());
                    SurveyAssetInventoryContent singleObject = surveyAssetInventoryContentDao.getSingleObject(surveyAsset);
                    if (StringUtils.isEmpty(singleObject.getRegistration()) || "无".equals(singleObject.getRegistration().trim())) {
                        purposeNumbers.add(Integer.valueOf(judgeObject.getNumber()));
                        SchemeJudgeObjectVo schemeJudgeObjectVo = schemeJudgeObjectService.getSchemeJudgeObjectVo(judgeObject);
                        actualPurpose.append(judgeObject.getPracticalUse()).append("、");
                        settingPurpose.append(schemeJudgeObjectVo.getSetUseName()).append("、");
                    }
                }
                if (CollectionUtils.isNotEmpty(actualTimenumbers)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.TIME_ACTUAL_SURVEY);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{竣工日期}", completedTime.deleteCharAt(completedTime.length() - 1)).replace("#{估价对象号}", (generateCommonMethod.convertNumber(actualTimenumbers) + "号"))).append("</p>");
                }
                if (CollectionUtils.isNotEmpty(purposeNumbers)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_PURPOSE);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{设定用途}", settingPurpose.deleteCharAt(settingPurpose.length() - 1)).replace("#{实际用途}", actualPurpose.deleteCharAt(actualPurpose.length() - 1)).replace("#{估价对象号}", (generateCommonMethod.convertNumber(purposeNumbers) + "号"))).append("</p>");
                }
            }

            //不相一致假设
            if (AssessReportFieldConstant.HYPOTHESIS_INCONFORMITY.equals(basis.getFieldName())) {
                //证载地址委估对象号
                StringBuilder loadAddressNum = new StringBuilder();
                //证载地址登记内容
                StringBuilder loadRegistrationContent = new StringBuilder();
                //证载地址实际内容
                StringBuilder loadActualContent = new StringBuilder();
                //证载地址说明
                StringBuilder loadRemark = new StringBuilder();

                //登记地址委估对象号
                StringBuilder registeredAddressNum = new StringBuilder();
                //登记地址登记内容
                StringBuilder registeredRegistrationContent = new StringBuilder();
                //登记地址实际内容
                StringBuilder registeredActualContent = new StringBuilder();
                //登记地址说明
                StringBuilder registeredRemark = new StringBuilder();

                //登记用途委估对象号
                StringBuilder registrationPurposesNum = new StringBuilder();
                //登记用途登记内容
                StringBuilder purposesRegistrationContent = new StringBuilder();
                //登记用途实际内容
                StringBuilder purposesActualContent = new StringBuilder();
                //登记用途址说明
                StringBuilder purposesRemark = new StringBuilder();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //对应资产清查内容
                    SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
                    List<SurveyAssetInventoryContent> SurveyAssetInventoryContents = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());

                    for (SurveyAssetInventoryContent item : SurveyAssetInventoryContents) {
                        switch (baseDataDicService.getCacheDataDicById(item.getInventoryContent()).getFieldName()) {
                            //证载地址
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS:
                                if ("不一致".equals(item.getAreConsistent())) {
                                    splicingContent(loadAddressNum, loadRegistrationContent, loadActualContent, loadRemark, judgeObject, item);
                                }
                                break;
                            //登记地址
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS:
                                if ("不一致".equals(item.getAreConsistent())) {
                                    splicingContent(registeredAddressNum, registeredRegistrationContent, registeredActualContent, registeredRemark, judgeObject, item);
                                }
                                break;
                            //登记用途
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE:
                                if ("不一致".equals(item.getAreConsistent())) {
                                    splicingContent(registrationPurposesNum, purposesRegistrationContent, purposesActualContent, purposesRemark, judgeObject, item);
                                }
                                break;
                        }
                    }
                }
                if (StringUtils.isNotBlank(loadAddressNum)) {
                    String number = getSubstitutionPrincipleName(loadAddressNum.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.LOAD_ADDRESS);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)
                            .replace("#{房产证登记地址}", loadRegistrationContent.deleteCharAt(loadRegistrationContent.length() - 1))
                            .replace("#{土地证登记地址}", loadActualContent.deleteCharAt(loadActualContent.length() - 1))
                            .replace("#{证明文件名称}#{证明文件说明内容}", loadRemark.deleteCharAt(loadRemark.length() - 1))).append("</p>");
                }
                if (StringUtils.isNotBlank(registeredAddressNum)) {
                    String number = getSubstitutionPrincipleName(registeredAddressNum.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.REGISTERED_ADDRESS);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)
                            .replace("#{登记地址}", registeredRegistrationContent.deleteCharAt(registeredRegistrationContent.length() - 1))
                            .replace("#{实际地址}", registeredActualContent.deleteCharAt(registeredActualContent.length() - 1))
                            .replace("#{证明文件名称}#{证明文件说明内容}", registeredRemark.deleteCharAt(registeredRemark.length() - 1))).append("</p>");
                }
                if (StringUtils.isNotBlank(registrationPurposesNum)) {
                    String number = getSubstitutionPrincipleName(registrationPurposesNum.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.REGISTRATION_PURPOSES);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)
                            .replace("#{登记用途}", purposesRegistrationContent.deleteCharAt(purposesRegistrationContent.length() - 1))
                            .replace("#{实际用途}", purposesActualContent.deleteCharAt(purposesActualContent.length() - 1))
                            .replace("#{证明文件名称}#{证明文件说明内容}", purposesRemark.deleteCharAt(purposesRemark.length() - 1))).append("</p>");
                }
            }

            //依据不足假设
            if (AssessReportFieldConstant.HYPOTHESIS_GIST_INSUFFICIENT.equals(basis.getFieldName())) {
                //参考同类委估对象号
                StringBuilder referenceNum = new StringBuilder();
                //入户调查估对象号
                StringBuilder assetCheckNum = new StringBuilder();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
                    //参考同类
                    if (AssessExamineTaskConstant.EXAMINE_HOUSE_RESEARCH_REFERENCE.equals(baseDataDicService.getCacheDataDicById(basicHouse.getResearchType()).getFieldName())) {
                        referenceNum.append(judgeObject.getNumber()).append(",");
                    }
                    //入户调查
                    if (AssessExamineTaskConstant.EXAMINE_HOUSE_RESEARCH_INVESTIGATE.equals(baseDataDicService.getCacheDataDicById(basicHouse.getResearchType()).getFieldName())) {
                        assetCheckNum.append(judgeObject.getNumber()).append(",");
                    }

                }
                if (StringUtils.isNotBlank(referenceNum)) {
                    String number = getSubstitutionPrincipleName(referenceNum.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.REFERENCE_SAME);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)).append("</p>");
                }
              /*  if (StringUtils.isNotBlank(assetCheckNum)) {
                    String number = getSubstitutionPrincipleName(assetCheckNum.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ASSET_CHECK);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{委估对象号}", number)).append("</p>");
                }*/
            }

            //背离事实假设
            if (AssessReportFieldConstant.HYPOTHESIS_DEPART_FROM_FACT.equals(basis.getFieldName())) {
                //评估基准日
                Date valuationDate = projectInfo.getValuationDate();
                //查勘结束日期
                GenerateReportGeneration generateReportGeneration = new GenerateReportGeneration();
                generateReportGeneration.setProjectId(projectInfo.getId());
                generateReportGeneration.setAreaGroupId(areaGroupId);
                GenerateReportGeneration generation = generateReportGenerationService.getGenerateReportGeneration(generateReportGeneration);
                Date investigationsEndDate = generation.getInvestigationsEndDate();
                //区位损坏委估对象
                StringBuilder surroundingsDamage = new StringBuilder();

                //实物损坏委估对象
                StringBuilder entityDamage = new StringBuilder();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //对应资产清查内容
                    SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
                    if ("不正常".equals(surveyAssetInventory.getRimIsNormal())) {
                        surroundingsDamage.append(judgeObject.getNumber()).append(",");
                    }
                    if ("损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                        entityDamage.append(judgeObject.getNumber()).append(",");
                    }
                }
                if(valuationDate.compareTo(investigationsEndDate)!=0){
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DATE_ARE_CONSISTENT);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{查勘结束日期}", sdf2.format(investigationsEndDate)).replace("#{评估基准日}",sdf2.format(valuationDate))).append("</p>");
                }
                if (StringUtils.isNotBlank(surroundingsDamage)) {
                    String number = getSubstitutionPrincipleName(surroundingsDamage.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.SURROUNDINGS_CONDITION);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{评估基准日}",sdf2.format(valuationDate))).append("</p>");
                }
                if (StringUtils.isNotBlank(entityDamage)) {
                    String number = getSubstitutionPrincipleName(entityDamage.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ENTITY_CONDITION);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number).replace("#{评估基准日}",sdf2.format(valuationDate))).append("</p>");
                }

            }

            //一般假设
            if (AssessReportFieldConstant.HYPOTHESIS_COMMON.equals(basis.getFieldName())) {
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


                Integer rightId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_PLEDGE).getId();
                Integer otherId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_OTHER).getId();

                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //对应资产清查内容
                    SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryService.getDataByDeclareId(judgeObject.getDeclareRecordId());
                    List<SurveyAssetInventoryContent> SurveyAssetInventoryContents = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());
                    for (SurveyAssetInventoryContent item : SurveyAssetInventoryContents) {
                        switch (baseDataDicService.getCacheDataDicById(item.getInventoryContent()).getFieldName()) {
                            //建筑面积
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA:
                                if (StringUtils.isEmpty(item.getRegistration()) || "无".equals(item.getRegistration().trim())) {
                                    noBuildingArea.append(judgeObject.getNumber()).append(",");
                                }
                                break;
                            //建筑结构
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE:
                                if (StringUtils.isEmpty(item.getRegistration()) || "无".equals(item.getRegistration().trim())) {
                                    noBuildingConstruction.append(judgeObject.getNumber()).append(",");
                                }
                                break;
                        }
                    }
                    //对应的他权信息
                    List<SurveyAssetInventoryRight> rightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(surveyAssetInventory.getPlanDetailId());
                     for (SurveyAssetInventoryRight inventoryRight: rightList) {
                        if(rightId.equals(inventoryRight.getCategory())){
                            havePledge.append(judgeObject.getNumber()).append(",");
                        }else {
                            noPledge.append(judgeObject.getNumber()).append(",");
                        }
                        if(otherId.equals(inventoryRight.getCategory())){
                            haveOther.append(judgeObject.getNumber()).append(",");
                        }else {
                            noOther.append(judgeObject.getNumber()).append(",");
                        }
                    }
                    //转让限制
                    if(StringUtils.isNotBlank(surveyAssetInventory.getTransferLimit())){
                        limitContent.append(judgeObject.getNumber()).append("号委估对象,有转让限制").append(surveyAssetInventory.getTransferLimit()).append(";");
                    }else {
                        limit.append(judgeObject.getNumber()).append(",");
                    }

                }
                if (StringUtils.isNotBlank(limit)) {
                    String number = getSubstitutionPrincipleName(limit.toString());
                    limitContent.append(number).append("委估对象土地使用权符合法定的转让条件为假设前提");
                }
                if (StringUtils.isNotBlank(havePledge)) {
                    String number = getSubstitutionPrincipleName(havePledge.toString());
                    content.append(number).append("委估对象有抵押;");
                }
                if (StringUtils.isNotBlank(noPledge)) {
                    String number = getSubstitutionPrincipleName(noPledge.toString());
                    content.append(number).append("委估对象无抵押;");
                }
                if (StringUtils.isNotBlank(haveOther)) {
                    String number = getSubstitutionPrincipleName(haveOther.toString());
                    content.append(number).append("委估对象有查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷;");
                }
                if (StringUtils.isNotBlank(noOther)) {
                    String number = getSubstitutionPrincipleName(noOther.toString());
                    content.append(number).append("委估对象无查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷;");
                }
                content.append(limitContent);
                if (StringUtils.isNotBlank(content)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ALIENI_IURIS);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{一般假设他权与转让}", content)).append("</p>");
                }

                if (StringUtils.isNotBlank(noBuildingArea)) {
                    String number = getSubstitutionPrincipleName(noBuildingArea.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.BUILDING_AREA);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)).append("</p>");
                }
                if (StringUtils.isNotBlank(noBuildingConstruction)) {
                    String number = getSubstitutionPrincipleName(noBuildingConstruction.toString());
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.BUILDING_CONSTRUCTION);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", number)).append("</p>");
                }
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
            numbers.add(Integer.valueOf(item));
        }
        return generateCommonMethod.convertNumber(numbers) + "号";
    }

    public void splicingContent(StringBuilder num, StringBuilder registrationContent, StringBuilder actualContent,
                                StringBuilder remark, SchemeJudgeObject judgeObject, SurveyAssetInventoryContent item) {
        num.append(judgeObject.getNumber()).append(",");
        registrationContent.append(item.getRegistration()).append("、");
        actualContent.append(item.getActual()).append("、");
        //相关附件
        StringBuilder fileNames = new StringBuilder();
        List<SysAttachmentDto> sysAttachmentDtos = this.getSysAttachmentDtos(item.getId(), FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class));
        if (CollectionUtils.isNotEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto file : sysAttachmentDtos) {
                fileNames.append(file.getFileName()).append("、");
            }
            remark.append(fileNames.deleteCharAt(fileNames.length() - 1));
        }
        if (StringUtils.isNotBlank(item.getCredential())) {
            remark.append("文件说明").append(item.getCredential()).append(";");
        }
    }
}
