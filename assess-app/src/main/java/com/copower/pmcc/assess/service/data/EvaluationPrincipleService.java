package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportTemplateItemDao;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationPrincipleDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationPrincipleVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.declare.DeclarePublicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.11	评估原则
 * Created by 13426 on 2018/4/27.
 */
@Service
public class EvaluationPrincipleService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationPrincipleDao evaluationPrincipleDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private MdIncomeDao mdIncomeDao;
    @Autowired
    private DataReportTemplateItemDao dataReportTemplateItemDao;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclarePublicService declarePublicService;


    /**
     * 保存数据
     *
     * @param formData
     */
    public void saveAndUpdate(String formData) {
        DataEvaluationPrinciple evaluationPrinciple = JSON.parseObject(formData, DataEvaluationPrinciple.class);
        if (evaluationPrinciple.getId() != null && evaluationPrinciple.getId() > 0) {
            evaluationPrincipleDao.updatePrinciple(evaluationPrinciple);
        } else {
            evaluationPrinciple.setCreator(commonService.thisUserAccount());
            evaluationPrincipleDao.addPrinciple(evaluationPrinciple);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(evaluationPrinciple.getId(), SchemeSupportTypeEnum.PRINCIPLE.getKey());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removePrinciple(Integer id) {
        return evaluationPrincipleDao.removePrinciple(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataEvaluationPrinciple getPrinciple(Integer id) {
        return evaluationPrincipleDao.getPrinciple(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getPrincipleList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataEvaluationPrinciple> hypothesisList = evaluationPrincipleDao.getPrincipleList(name);
        List<DataEvaluationPrincipleVo> vos = LangUtils.transform(hypothesisList, p -> getPrincipleVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationPrincipleVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据委估目的及评估方法获取数据列表
     *
     * @param purpose
     * @return
     */
    public List<DataEvaluationPrinciple> getPrincipleList(Integer type, Integer category, Integer purpose) {
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
        return evaluationPrincipleDao.getEnablePrincipleList(typeStr, categoryStr, purposeStr);
    }


    public DataEvaluationPrincipleVo getPrincipleVo(DataEvaluationPrinciple evaluationPrinciple) {
        if (evaluationPrinciple == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        DataEvaluationPrincipleVo vo = new DataEvaluationPrincipleVo();
        BeanUtils.copyProperties(evaluationPrinciple, vo);
        if (StringUtils.isNotBlank(evaluationPrinciple.getMethod())) {
            vo.setMethodStr(baseDataDicService.getDataDicName(methodDicList, evaluationPrinciple.getMethod()));
        }
        if (StringUtils.isNotBlank(evaluationPrinciple.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeStr(baseDataDicService.getDataDicName(purposeDicList, evaluationPrinciple.getEntrustmentPurpose()));
        }
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(evaluationPrinciple.getType(), evaluationPrinciple.getCategory()));
        return vo;
    }

    /**
     * 获取上报告内容
     *
     * @param projectInfo
     * @return
     */
    public String getReportPrinciple(ProjectInfo projectInfo, Integer areaGroupId) {
        List<DataEvaluationPrinciple> principleList = getPrincipleList(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), projectInfo.getEntrustPurpose());
        if (CollectionUtils.isEmpty(principleList)) return "";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < principleList.size(); i++) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
            DataEvaluationPrinciple basis = principleList.get(i);
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", i + 1, basis.getName())));
            stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(basis.getTemplate())));

            //代替原则
            if (AssessReportFieldConstant.REPLACE_PRINCIPLE.equals(basis.getFieldName())) {
                //比较法
                StringBuilder compare = new StringBuilder();
                //成本法
                StringBuilder cost = new StringBuilder();
                //假设开发法
                StringBuilder development = new StringBuilder();
                //收益法自营
                StringBuilder autotrophy = new StringBuilder();
                //收益法出租
                StringBuilder rent = new StringBuilder();


                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    List<SchemeJudgeFunction> applicableJudgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctions(judgeObject.getId());
                    if (!CollectionUtils.isNotEmpty(applicableJudgeFunctions)) {
                        applicableJudgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctions(judgeObject.getPid());
                    }
                    for (SchemeJudgeFunction judgeFunction : applicableJudgeFunctions) {
                        BaseDataDic dataDicById = baseDataDicService.getDataDicById(judgeFunction.getMethodType());
                        if (dataDicById == null) {
                            continue;
                        }
                        String fieldName = dataDicById.getFieldName();
                        if (StringUtils.isEmpty(fieldName)) {
                            continue;
                        }
                        switch (fieldName) {
                            case AssessReportFieldConstant.MARKET_COMPARE:
                                compare.append(judgeObject.getNumber()).append(",");
                                break;
                            case AssessReportFieldConstant.COST:
                                cost.append(judgeObject.getNumber()).append(",");
                                break;
                            case AssessReportFieldConstant.DEVELOPMENT:
                                development.append(judgeObject.getNumber()).append(",");
                                break;
                            case AssessReportFieldConstant.INCOME:
                                SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(judgeFunction.getJudgeObjectId(), judgeFunction.getMethodType());
                                if (schemeInfo != null && schemeInfo.getMethodDataId() != null) {
                                    MdIncome incomeById = mdIncomeDao.getIncomeById(schemeInfo.getMethodDataId());
                                    if (incomeById != null && incomeById.getOperationMode() == 0) {
                                        autotrophy.append(judgeObject.getNumber()).append(",");
                                    } else {
                                        rent.append(judgeObject.getNumber()).append(",");
                                    }
                                }
                                break;
                        }

                    }
                }
                if (StringUtils.isNotBlank(compare)) {
                    String substitutionPrincipleName = StringUtils.defaultString(getSubstitutionPrincipleName(compare.toString()));
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_COMPARE);
                    if (dataReportTemplateByField != null)
                        stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate()).replace("#{估价对象号}", substitutionPrincipleName)));
                }
                if (StringUtils.isNotBlank(cost)) {
                    String substitutionPrincipleName = StringUtils.defaultString(getSubstitutionPrincipleName(cost.toString()));
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.COST);
                    if (dataReportTemplateByField != null)
                        stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate()).replace("#{估价对象号}", substitutionPrincipleName)));
                }
                if (StringUtils.isNotBlank(development)) {
                    String substitutionPrincipleName = StringUtils.defaultString(getSubstitutionPrincipleName(development.toString()));
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DEVELOPMENT);
                    if (dataReportTemplateByField != null)
                        stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate()).replace("#{估价对象号}", substitutionPrincipleName)));
                }
                if (StringUtils.isNotBlank(autotrophy)) {
                    String substitutionPrincipleName = StringUtils.defaultString(getSubstitutionPrincipleName(autotrophy.toString()));
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INCOME_AUTOTROPHY);
                    if (dataReportTemplateByField != null)
                        stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate()).replace("#{估价对象号}", substitutionPrincipleName)));
                }
                if (StringUtils.isNotBlank(rent)) {
                    String substitutionPrincipleName = StringUtils.defaultString(getSubstitutionPrincipleName(rent.toString()));
                    DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INCOME_RENT);
                    if (dataReportTemplateByField != null)
                        stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate()).replace("#{估价对象号}", substitutionPrincipleName)));
                }
            }

            //合法原则
            if (AssessReportFieldConstant.LEGAL_PRINCIPLE.equals(basis.getFieldName())) {
                List<DataReportTemplateItem> dataReportTemplateItemList = dataReportTemplateItemDao.getListByMasterId(basis.getId(), SchemeSupportTypeEnum.PRINCIPLE.getKey());
                //委估单位
                DeclareApply declareApplyByProjectId = declarePublicService.getDeclareApplyByProjectId(projectInfo.getId());
                String unit = StringUtils.defaultString(declareApplyByProjectId.getClient());

                for (DataReportTemplateItem templateItem : dataReportTemplateItemList) {
                    switch (templateItem.getFieldName()) {
                        case AssessReportFieldConstant.EVALUATE_RESULTS: //区位
                            DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.EVALUATE_RESULTS);
                            if (dataReportTemplateByField != null)
                                stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(dataReportTemplateByField.getTemplate()).replace("#{委托单位}", unit)));
                            break;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    public String getSubstitutionPrincipleName(String str) {
        String[] s = str.toString().split(",");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String item : s) {
            numbers.add(Integer.valueOf(item));
        }
        return generateCommonMethod.convertNumber(numbers) + "号";
    }
}


