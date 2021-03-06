package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.aspose.words.ControlChar;
import com.copower.pmcc.assess.common.AsposeUtils;
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
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.declare.DeclarePublicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;


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

    public String getLandReportPrinciple(ProjectInfo projectInfo, Integer areaGroupId) {
        List<KeyValueDto> keyValueDtoList = Arrays.asList(new KeyValueDto("text-indent", "2em"),
                new KeyValueDto("font-family", "仿宋_GB2312"),
                new KeyValueDto("font-size", "14pt"),
                new KeyValueDto("line-height", "100%"));
        final int repeat = 1;
        List<DataEvaluationPrinciple> principleList = getPrincipleList(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), projectInfo.getEntrustPurpose());
        if (CollectionUtils.isEmpty(principleList)) return "";
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        StringBuilder stringBuilder = new StringBuilder();

        String title = "根据地价评估的技术规程及估价对象具体状况，在本次估价过程中，我们遵循的主要原则有：";
        stringBuilder.append(AsposeUtils.getWarpCssHtml(title, keyValueDtoList));
        //插入换行符
        stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));

        int count = 0;
        DataEvaluationPrinciple evaluationPrinciple = null;
        List<DataEvaluationPrinciple> evaluationPrincipleList = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.LEGAL_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList)) {
            evaluationPrinciple = evaluationPrincipleList.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }
        List<DataEvaluationPrinciple> evaluationPrincipleList2 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.MARKET_SUPPLY_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList2)) {
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }

        List<DataEvaluationPrinciple> evaluationPrincipleList3 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.MOST_EFFECTIVE_USE_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList3)) {
            evaluationPrinciple = evaluationPrincipleList3.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }

        List<DataEvaluationPrinciple> evaluationPrincipleList4 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.ALTERATION_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList4)) {
            evaluationPrinciple = evaluationPrincipleList4.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }

        //
        Map<Integer, List<Integer>> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (SchemeJudgeObject judgeObject : judgeObjectList) {
                List<Integer> integerList = FormatUtils.transformString2Integer(judgeObject.getJudgeFunction());
                if (CollectionUtils.isEmpty(integerList)) {
                    continue;
                }
                for (DataEvaluationPrinciple principle : principleList) {
                    for (Integer integer : integerList) {
                        if (StringUtils.isBlank(principle.getMethod())) {
                            continue;
                        }
                        if (StringUtils.contains(principle.getMethod(), integer.toString())) {
                            if (map.containsKey(judgeObject.getId())) {
                                map.get(judgeObject.getId()).add(principle.getId());
                            } else {
                                map.put(judgeObject.getId(), Lists.newArrayList(principle.getId()));
                            }
                        }
                    }
                }
            }
        }
        List<DataEvaluationPrinciple> evaluationPrincipleList5 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.REPLACE_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList5) && !map.isEmpty()) {
            evaluationPrinciple = evaluationPrincipleList5.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }

        List<DataEvaluationPrinciple> evaluationPrincipleList6 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.EXPECTED_RETURN_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList6)) {
            evaluationPrinciple = evaluationPrincipleList6.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }

        List<DataEvaluationPrinciple> evaluationPrincipleList7 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.INCREASING_DECREASING_RETURNS_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList7)) {
            evaluationPrinciple = evaluationPrincipleList7.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }

        List<DataEvaluationPrinciple> evaluationPrincipleList8 = LangUtils.filter(principleList, obj -> obj.getFieldName().equals(AssessReportFieldConstant.CAUTIOUS_PRINCIPLE));
        if (CollectionUtils.isNotEmpty(evaluationPrincipleList8)) {
            evaluationPrinciple = evaluationPrincipleList8.get(0);
            String v = String.join("", String.valueOf(++count), ".", evaluationPrinciple.getName());
            stringBuilder.append(AsposeUtils.getWarpCssHtml(v, keyValueDtoList));
            //插入换行符
            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
            stringBuilder.append(AsposeUtils.getWarpCssHtml(evaluationPrinciple.getTemplate(), keyValueDtoList));
        }
        return stringBuilder.toString();
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
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaGroupId);
        for (int i = 0; i < principleList.size(); i++) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
            DataEvaluationPrinciple basis = principleList.get(i);
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", i + 1, basis.getName())));
            stringBuilder.append(generateCommonMethod.getIndentHtml(publicService.tagfilter(basis.getTemplate())));

            //代替原则（房产）
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

            //合法原则（房产）
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

            //最佳使用原则（土地）
            if (AssessReportFieldConstant.BEST_USE_PRINCIPLE.equals(basis.getFieldName())) {
                Map<Integer, String> map = Maps.newHashMap();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    if (judgeObject.getDeclareRecordId() == null) continue;
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
                    StringBuilder builder = new StringBuilder();
                    if (StringUtils.isNotBlank(declareRecord.getLandRightType())) {
                        builder.append(declareRecord.getLandRightType().contains("国") ? "国有" : "集体");
                    }
                    if (StringUtils.isNotBlank(declareRecord.getLandAcquisitionMethod())) {
                        builder.append(declareRecord.getLandAcquisitionMethod());
                    }
                    if (schemeAreaGroup.getBestUse() != null) {
                        DataBestUseDescription bestUseDescription = dataBestUseDescriptionService.getCacheBestUseDescriptionById(schemeAreaGroup.getBestUse());
                        if (bestUseDescription != null) {
                            builder.append(bestUseDescription.getName());
                        }
                    }
                    if (judgeObject.getSetUse() != null) {
                        builder.append(baseDataDicService.getNameById(judgeObject.getSetUse()));
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), builder.toString());
                }
                stringBuilder = publicService.replaceAll(stringBuilder, "#{最佳使用原则}", generateCommonMethod.judgeSummaryDesc(map, "", false));
            }

            //协调原则（土地）
            if (AssessReportFieldConstant.COORDINATION_PRINCIPLE.equals(basis.getFieldName())) {
                String coordinationPrinciple = "需要分析土地是否与所处环境协调；判断土地与其所在的环境是否协调，直接关系到该地块的收益量和价格。";
                Boolean isUseBlockDesc = true;//是否使用板块来描述
                Map<Integer, String> map = Maps.newHashMap();
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(judgeObject.getBasicApplyId());
                    if (basicEstate == null || StringUtils.isBlank(basicEstate.getBlockName())) {
                        isUseBlockDesc = false;
                        break;
                    }
                    map.put(generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber()), basicEstate.getBlockName());
                }
                if(isUseBlockDesc){
                    coordinationPrinciple= String.format("需要分析%s所处环境协调；判断土地与其所在的环境是否协调，直接关系到该地块的收益量和价格。",generateCommonMethod.judgeSummaryDesc(map, "与", false));
                    stringBuilder = publicService.replaceAll(stringBuilder, "#{协调原则}", coordinationPrinciple);
                }else {
                    stringBuilder = publicService.replaceAll(stringBuilder, "#{协调原则}", coordinationPrinciple);
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


