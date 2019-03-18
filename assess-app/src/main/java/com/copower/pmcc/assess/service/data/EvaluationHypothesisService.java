package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationHypothesisDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
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
import java.util.List;

/**
 * 3.1.2.12	评估假设
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationHypothesisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdIncomeDao mdIncomeDao;



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
    public String getReportHypothesis(ProjectInfo projectInfo, Integer areaGroupId) {
        List<DataEvaluationHypothesis> hypothesisList = this.getHypothesisList(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), projectInfo.getEntrustPurpose());
        if (CollectionUtils.isEmpty(hypothesisList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        for (int i = 0; i < hypothesisList.size(); i++) {
            DataEvaluationHypothesis basis = hypothesisList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", i + 1, basis.getName())).append("</p>");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(basis.getTemplate()).append("</p>");

            if (AssessReportFieldConstant.HYPOTHESIS_UNCERTAIN_MATTER.equals(basis.getFieldName())) {
                SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
                schemeJudgeObject.setProjectId(projectInfo.getId());
                schemeJudgeObject.setBisEnable(true);
                schemeJudgeObject.setBisMerge(false);
                schemeJudgeObject.setAreaGroupId(areaGroupId);
                List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);

                List<Integer> actualTimenumbers = new ArrayList<>();
                StringBuilder completedTime = new StringBuilder();

                List<Integer> purposeNumbers = new ArrayList<>();
                StringBuilder actualPurpose = new StringBuilder();
                StringBuilder settingPurpose = new StringBuilder();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    //未定事项假设(实际调查)
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicBuilding building = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
                    Integer type = building.getCompletedTimeType();
                    if (type != null && baseDataDicService.getCacheDataDicByFieldName(AssessReportFieldConstant.TIME_ACTUAL_SURVEY).getId() == type) {
                        actualTimenumbers.add(Integer.valueOf(judgeObject.getNumber()));
                        completedTime.append(sdf.format(building.getBeCompletedTime())).append("、");
                    }
                    //未定事项假设(用途)
                    Integer purpose = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE).getId();
                    SurveyAssetInventoryContent surveyAsset = new SurveyAssetInventoryContent();
                    surveyAsset.setInventoryContent(purpose);
                    surveyAsset.setProjectId(projectInfo.getId());
                    SurveyAssetInventoryContent singleObject = surveyAssetInventoryContentDao.getSingleObject(surveyAsset);
                    if(StringUtils.isEmpty(singleObject.getRegistration()) || "无".equals(singleObject.getRegistration().trim())){
                        purposeNumbers.add(Integer.valueOf(judgeObject.getNumber()));
                        SchemeJudgeObjectVo schemeJudgeObjectVo = schemeJudgeObjectService.getSchemeJudgeObjectVo(judgeObject);
                        actualPurpose.append(judgeObject.getPracticalUse()).append("、");
                        settingPurpose.append(schemeJudgeObjectVo.getSetUseName()).append("、");
                    }
                }
                if(CollectionUtils.isNotEmpty(actualTimenumbers)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.TIME_ACTUAL_SURVEY);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{竣工日期}", completedTime.deleteCharAt(completedTime.length() - 1)).replace("#{估价对象号}", (generateCommonMethod.convertNumber(actualTimenumbers) + "号"))).append("</p>");
                }
                if(CollectionUtils.isNotEmpty(purposeNumbers)) {
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.HYPOTHESIS_PURPOSE);
                    stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{设定用途}", settingPurpose.deleteCharAt(settingPurpose.length() - 1)).replace("#{实际用途}", actualPurpose.deleteCharAt(actualPurpose.length() - 1)).replace("#{估价对象号}", (generateCommonMethod.convertNumber(purposeNumbers) + "号"))).append("</p>");
                }
            }

        }
        return stringBuilder.toString();
    }


}
