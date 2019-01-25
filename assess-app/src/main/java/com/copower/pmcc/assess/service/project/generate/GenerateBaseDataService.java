package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.CnNumberUtils;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.*;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseFaceStreetVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingEnvironmentVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingFinanceVo;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.compile.CompileReportService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.*;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2019-1-16.
 */
public class GenerateBaseDataService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    protected final String errorStr = "暂无数据";

    //spring bean
    private SchemeJudgeObjectService schemeJudgeObjectService;
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    private SchemeAreaGroupService schemeAreaGroupService;
    private ProjectNumberRecordService projectNumberRecordService;
    private BaseReportService baseReportService;
    private ProjectInfoService projectInfoService;
    private BaseDataDicService baseDataDicService;
    private BaseAttachmentService baseAttachmentService;
    private ProjectPlanDetailsService projectPlanDetailsService;
    private DataSetUseFieldService dataSetUseFieldService;
    private ProjectPhaseService projectPhaseService;
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    private DeclareRecordService declareRecordService;
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    private SchemeSurePriceService schemeSurePriceService;
    private SchemeReimbursementService schemeReimbursementService;
    private com.copower.pmcc.assess.service.AdRpcQualificationsAppService adRpcQualificationsService;
    private PublicService publicService;
    private SchemeSupportInfoService schemeSupportInfoService;
    private CompileReportService compileReportService;
    private SchemeReportFileService schemeReportFileService;

    //构造器必须传入的参数
    private Integer projectId;
    private Integer areaId;
    private Integer baseReportTemplateId;
    private ProjectPlan projectPlan;

    //中间变量
    private SchemeAreaGroup schemeAreaGroup = null;
    private BaseReportTemplate baseReportTemplate;
    private ProjectInfoVo projectInfo = null;
    private List<SchemeJudgeObject> schemeJudgeObjectList = null;

    //===========================================获取的值===============================
    //文号
    private String wordNumber;
    //委托人
    private String principal;
    //评估面积
    private BigDecimal assessArea = null;
    //评估方法
    private String evaluationMethod;
    //委托目的表述
    private String statementPurposeEntrustment;
    //价值类型
    private String valueType;
    //价值定义
    private String definitionValue;
    //价值含义
    private String valueImplication;
    //权利人(区位)
    private String powerPerson;
    //非权利人(区位)
    private String notPowerPerson;
    //设定用途
    private String setUse;
    //土地实际用途
    private String landPracticalUse;
    //使用权类型
    private String useRightType;

    //估价对象区位状况表
    private String judgeObjectAreaStatusSheet;

    //估价土地实体状况表
    private String judgeObjectLandStateSheet;

    //估价对象建筑实体状况表
    private String judgeBuildLandStateSheet;

    //报告出具日期
    private Date reportIssuanceDate = new Date();


    /**
     * 获取区域信息(组)
     *
     * @return
     */
    public SchemeAreaGroup getSchemeAreaGroup() {
        if (schemeAreaGroup != null) {
            return schemeAreaGroup;
        }
        if (this.schemeAreaGroup != null) {
            return this.schemeAreaGroup;
        } else {
            SchemeAreaGroup areaGroup = schemeAreaGroupService.get(getAreaId());
            if (areaGroup == null) {
                areaGroup = new SchemeAreaGroup();
            }
            this.schemeAreaGroup = areaGroup;
        }
        return schemeAreaGroup;
    }

    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    /**
     * 获取文号
     *
     * @return
     */
    public String getWordNumber() {
        if (StringUtils.isEmpty(wordNumber)) {
            try {
                String number = projectNumberRecordService.getReportNumber(this.getProjectId(), areaId, this.getBaseReportTemplate().getReportType());
                this.wordNumber = number;
            } catch (BusinessException e) {
                logger.error("获取文号异常", e);
            }

        }
        if (StringUtils.isNotBlank(this.wordNumber)) {
            return wordNumber;
        } else {
            return errorStr;
        }
    }

    public BaseReportTemplate getBaseReportTemplate() {
        if (baseReportTemplate == null) {
            BaseReportTemplate reportTemplate = baseReportService.getBaseReportTemplateById(baseReportTemplateId);
            this.baseReportTemplate = reportTemplate;
        }
        return baseReportTemplate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public ProjectPlan getProjectPlan() {
        return projectPlan;
    }

    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 委托人
     *
     * @return
     */
    public String getPrincipal() {
        if (StringUtils.isBlank(principal)) {
            String principalStr = StringUtils.isNotBlank(getProjectInfo().getConsignorVo().getCsName()) ? getProjectInfo().getConsignorVo().getCsName() : getProjectInfo().getConsignorVo().getCsEntrustmentUnit();
            this.principal = principalStr;
        }
        if (StringUtils.isNotBlank(this.principal)) {
            return principal;
        } else {
            return errorStr;
        }
    }

    /**
     * 获取项目info
     *
     * @return
     */
    public ProjectInfoVo getProjectInfo() {
        if (this.projectInfo != null) {
            return this.projectInfo;
        } else {
            if (projectInfo == null) {
                ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(getProjectId()));
                this.projectInfo = projectInfoVo;
            }
        }
        return projectInfo;
    }

    public BigDecimal getAssessArea() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BigDecimal bigDecimal = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                bigDecimal = bigDecimal.add(schemeJudgeObject.getEvaluationArea());
            }
            assessArea = bigDecimal;
        } else {
            return new BigDecimal(0.0d);
        }
        return assessArea;
    }

    /**
     * 选择估价方法
     *
     * @return
     */
    public String getSelectionValuationMethod() {
        StringBuilder builder = new StringBuilder("");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (schemeJudgeFunction.getBisApplicable()) {
                        builder.append(schemeJudgeFunction.getName());
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 大写金额
     *
     * @return
     */
    public String getCapitalizationAmount() {
        String price = getTotalRealEstatePrice();
        if (NumberUtils.isNumber(price)) {
            BigDecimal bg = new BigDecimal(Double.parseDouble(price));
            //强制保留2位
            double d = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return CnNumberUtils.toUppercase(d);
        }
        return errorStr;
    }

    /**
     * 估价项目名称
     *
     * @return
     */
    public String getValuationProjectName() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            if (schemeJudgeObjectList.size() == 1) {
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                builder.append(schemeJudgeObject.getSeat()).append(";");
                if (schemeJudgeObject.getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null) {
                        builder.append(dataSetUseField.getName()).append(";");
                    }
                }
                builder.append(getValueImplication()).append(";");
                builder.append(schemeJudgeObject.getEvaluationArea().toString());
            } else {
                int num = schemeJudgeObjectList.size() > 2 ? 2 : schemeJudgeObjectList.size();
                String temp = "";
                BigDecimal evaluationArea = null;
                for (int i = 0; i < num; i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                    builder.append(schemeJudgeObject.getSeat()).append(";");
                    if (schemeJudgeObject.getSetUse() != null) {
                        DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                        if (dataSetUseField != null) {
                            builder.append(dataSetUseField.getName()).append(";");
                        }
                        if (i == num - 1) {
                            builder.append("等").append(schemeJudgeObject.getNumber());
                            temp = schemeJudgeObject.getCertUse();
                            evaluationArea = schemeJudgeObject.getEvaluationArea();
                        }
                    }
                }
                builder.append(getValueImplication()).append(";");
                builder.append(temp);
                if (getSchemeAreaGroup().getEntrustPurpose() != null) {
                    builder.append(baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose()));
                }
                builder.append("委估面积:");
                builder.append(evaluationArea.toString());
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }


    /**
     * 分类评估单价
     *
     * @return
     */
    public String getEvaluationPriceCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        builder.append(schemeJudgeObject.getPrice().toString()).append(",");
                    }
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * 分类评估面积
     *
     * @return
     */
    public String getEvaluationAreaCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString())) {
                        builder.append(schemeJudgeObject.getEvaluationArea().toString()).append(",");
                    }
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }


    /**
     * 分类评估总价
     *
     * @return
     */
    public String getEvaluationPriceCateGoryTotalOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimalA = new BigDecimal(schemeJudgeObject.getEvaluationArea().toString());
                        BigDecimal bigDecimalB = new BigDecimal(schemeJudgeObject.getPrice().toString());
                        BigDecimal bigDecimal = bigDecimalA.multiply(bigDecimalB);
                        builder.append(bigDecimal.toString()).append(",");
                    }
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }


    /**
     * 分类评估单价计算试
     *
     * @return
     */
    public String getEvaluationExpression() {
        return "市场比较法价格*权重+收益法价格*权重";
    }

    /**
     * 分类评估方法结果
     *
     * @return
     */
    public String getEvaluationMethodResult(String report_type) throws Exception {
        if (Objects.equal(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT, report_type)) {
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            BigDecimal price = new BigDecimal(0);
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    if (schemeJudgeObject.getPrice() != null) {
                        if (NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                            price.add(schemeJudgeObject.getPrice());
                        }
                    }
                }
            }
            return String.format("最终单价:%s", price.toString());
        }

        if (Objects.equal(AssessDataDicKeyConstant.REPORT_TYPE_RESULT, report_type)) {
            return getjudgeBuildResultSurveySheet();
        }

        return errorStr;
    }

    /**
     * 权重说明
     *
     * @return
     */
    public String getWeightSpecification() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setDeclareRecordId(schemeJudgeObjectList.get(i).getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(schemeJudgeObjectList.get(i).getDeclareRecordId());
                    if (schemeSurePrice != null) {
                        builder.append(schemeSurePrice.getWeightExplain());
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 房地产总价
     *
     * @return
     */
    public String getTotalRealEstatePrice() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BigDecimal area = new BigDecimal(0);
        BigDecimal price = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        area = area.add(schemeJudgeObject.getEvaluationArea());
                        price = price.add(schemeJudgeObject.getPrice());
                    }
                }
            }
        }
        if (area.doubleValue() > 0 && price.doubleValue() > 0) {
            BigDecimal decimal = area.multiply(price);
            return decimal.toString();
        }
        return errorStr;
    }

    /**
     * 法定优选受偿款
     *
     * @return
     */
    public String getStatutoryOptimumReimbursement() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setDeclareRecordId(schemeJudgeObjectList.get(i).getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(projectPlanDetailsList.get(0).getId());
                    if (schemeReimbursement != null) {
                        builder.append(schemeReimbursement.getNotSetUpTotalPrice().toString());
                        builder.append(";");
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 价值表达结果
     *
     * @return
     */
    public String getValueExpressionResult() {
        StringBuilder builder = new StringBuilder();
        return "抵押价值特殊处理";
    }

    /**
     * 评估方法
     *
     * @return
     */
    public String getEvaluationMethod() {
        StringBuilder builder = new StringBuilder("");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    builder.append(schemeJudgeFunction.getName());
                }
            }
        }
        this.evaluationMethod = moreJudgeObject(builder.toString(), builder.toString());
        if (StringUtils.isNotBlank(this.evaluationMethod)) {
            return evaluationMethod;
        } else {
            return errorStr;
        }
    }

    /**
     * 估价技术思路
     *
     * @return
     */
    public String getEvaluationThink() {
        StringBuilder builder = new StringBuilder("");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    builder.append(schemeJudgeFunction.getThinking());
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 委托目的表述
     *
     * @return
     */
    public String getStatementPurposeEntrustment() {
        if (getProjectInfo() != null) {
            this.statementPurposeEntrustment = getProjectInfo().getRemarkEntrustPurpose();
        }
        if (StringUtils.isNotBlank(this.statementPurposeEntrustment)) {
            return statementPurposeEntrustment;
        } else {
            return errorStr;
        }
    }

    /**
     * get 价值类型
     *
     * @return
     */
    public String getValueType() {
        if (getSchemeAreaGroup() != null) {
            String value = baseDataDicService.getNameById(getSchemeAreaGroup().getValueDefinition());
            if (StringUtils.isNotBlank(value)) {
                return value;
            }
        }
        return errorStr;
    }

    /**
     * get 价值定义
     *
     * @return
     */
    public String getDefinitionValue() {
        if (getSchemeAreaGroup() != null) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(getSchemeAreaGroup().getValueDefinition());
            if (baseDataDic != null) {
                if (StringUtils.isNotBlank(baseDataDic.getRemark())) {
                    return baseDataDic.getRemark();
                }
            }
        }
        return errorStr;
    }

    /**
     * 注册房产估价师
     *
     * @param account
     * @return
     */
    public String getRegisteredRealEstateValuer(String account) {
        String temp = publicService.getUserNameByAccount(account);
        return temp;
    }

    /**
     * 注册房产估价师 编号
     *
     * @param account
     * @return
     * @throws Exception
     */
    public String getRegistrationNumber(String account) throws Exception {
        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
            return adPersonalQualificationDtoList.get(0).getCertificateNo();
        }
        return errorStr;
    }

    /**
     * 根据公司编号取得公司营业执照
     *
     * @return
     */
    public AdCompanyQualificationDto getCompanyQualificationForLicense() {
        return adRpcQualificationsService.getCompanyQualificationForLicense(publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 根据公司编号取得公司执业资质
     *
     * @return
     */
    public AdCompanyQualificationDto getCompanyQualificationForPractising() {
        return adRpcQualificationsService.getCompanyQualificationForPractising(publicService.getCurrentCompany().getCompanyId());
    }

    /**
     * 现场查勘期
     *
     * @param start
     * @param end
     * @return
     */
    public String getSurveyExamineDate(Date start, Date end) {
        if (start == null) {
            start = new Date();
        }
        if (end == null) {
            end = new Date();
        }
        return String.format("%s%s", DateUtils.format(start, DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN));
    }

    /**
     * 作业结束时间
     *
     * @param end
     * @return
     */
    public String getHomeWorkEndTime(Date end) {
        if (end == null) {
            end = new Date();
        }
        return DateUtils.format(end, DateUtils.DATE_CHINESE_PATTERN);
    }

    /**
     * 作业开始时间
     *
     * @return
     */
    public String getHomeWorkStartTime() {
        return DateUtils.format(getProjectInfo().getGmtCreated(), DateUtils.DATE_CHINESE_PATTERN);
    }

    /**
     * 评估假设
     *
     * @return
     */
    public String getEVALUATION_HYPOTHESIS() {
        StringBuilder builder = new StringBuilder();
        List<SchemeSupportInfo> schemeSupportInfoList = schemeSupportInfoService.getSupportInfoListByAreaId(getAreaId(), SchemeSupportTypeEnum.HYPOTHESIS);
        if (CollectionUtils.isNotEmpty(schemeSupportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : schemeSupportInfoList) {
                if (StringUtils.isNotBlank(schemeSupportInfo.getTemplate())) {
                    builder.append(schemeSupportInfo.getTemplate());
                    builder.append("\r\n");
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 评估依据
     *
     * @return
     */
    public String getEVALUATION_BASIS() {
        StringBuilder builder = new StringBuilder();
        List<SchemeSupportInfo> schemeSupportInfoList = schemeSupportInfoService.getSupportInfoListByAreaId(getAreaId(), SchemeSupportTypeEnum.BASIS);
        if (CollectionUtils.isNotEmpty(schemeSupportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : schemeSupportInfoList) {
                if (StringUtils.isNotBlank(schemeSupportInfo.getTemplate())) {
                    builder.append(schemeSupportInfo.getTemplate());
                    builder.append("\r\n");
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 评估原则
     *
     * @return
     */
    public String getEVALUATION_PRINCIPLE() {
        StringBuilder builder = new StringBuilder();
        List<SchemeSupportInfo> schemeSupportInfoList = schemeSupportInfoService.getSupportInfoListByAreaId(getAreaId(), SchemeSupportTypeEnum.PRINCIPLE);
        if (CollectionUtils.isNotEmpty(schemeSupportInfoList)) {
            for (SchemeSupportInfo schemeSupportInfo : schemeSupportInfoList) {
                if (StringUtils.isNotBlank(schemeSupportInfo.getTemplate())) {
                    builder.append(schemeSupportInfo.getTemplate());
                    builder.append("\r\n");
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 报告分析
     *
     * @return
     */
    public String getReportAnalysis() {
        StringBuilder builder = new StringBuilder();
        String[] strings = new String[]{AssessPhaseKeyConstant.LIQUIDATION_ANALYSIS, AssessPhaseKeyConstant.REIMBURSEMENT};
        List<CompileReportDetail> compileReportDetailList = Lists.newArrayList();
        for (String s : strings) {
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(s);
            if (baseDataDic != null) {
                List<CompileReportDetail> compileReportDetails = compileReportService.getCompileReportDetailList(getAreaId(), baseDataDic.getId());
                if (CollectionUtils.isNotEmpty(compileReportDetails)) {
                    compileReportDetailList.addAll(compileReportDetails);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(compileReportDetailList)) {
            for (CompileReportDetail compileReportDetail : compileReportDetailList) {
                builder.append(compileReportDetail.getTemplate());
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 协助工作人员
     *
     * @return
     */
    public String getAssistanceStaff(String account) {
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setProjectPhaseId(projectPhaseScene.getId());
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    projectPlanDetailsList.parallelStream().forEach(projectPlanDetails -> {
                        if (StringUtils.isNotBlank(projectPlanDetails.getExecuteUserAccount())) {
                            if (Objects.equal(account, projectPlanDetails.getExecuteUserAccount())) {
                            } else {
                                builder.append(publicService.getUserNameByAccount(projectPlanDetails.getExecuteUserAccount()));
                            }
                        }
                    });
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * get 价值含义
     *
     * @return
     */
    public String getValueImplication() {
        String temp = String.format("%s%s%s", getValueType(), getDefinitionValue(), getSchemeAreaGroup().getValueConnotation());
        if (StringUtils.isNotBlank(temp)) {
            return temp;
        }
        return errorStr;
    }

    public String getPowerPerson() {
        StringBuilder builder = new StringBuilder(1024);
        try {
            SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                String text = String.format("%s%s", schemeJudgeObjectList.get(0).getSeat(), "");
                Matcher m = Pattern.compile("[\\d]+号").matcher(text);
                while (m.find()) {
                    builder.append(text.substring(0, text.lastIndexOf(m.group())));
                }
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                    m = Pattern.compile("[\\d]+号").matcher(schemeJudgeObject.getSeat());
                    while (m.find()) {
                        if (i == schemeJudgeObjectList.size() - 1) {
                            builder.append(m.group());
                        } else {
                            builder.append(m.group()).append(",");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error("权利人(区位)拼接异常!");
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    public String getNotPowerPerson() {
        StringBuilder builder = new StringBuilder(1024);
        try {
            SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                String text = String.format("%s%s", schemeJudgeObjectList.get(0).getSeat(), "");
                Matcher m = Pattern.compile("[\\d]+楼[\\d]+号").matcher(text);
                while (m.find()) {
                    builder.append(text.substring(0, text.lastIndexOf(m.group())));
                }
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                    m = Pattern.compile("[\\d]+楼[\\d]+号").matcher(schemeJudgeObject.getSeat());
                    while (m.find()) {
                        if (i == schemeJudgeObjectList.size() - 1) {
                            builder.append(m.group());
                        } else {
                            builder.append(m.group()).append(",");
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error("不是权利人(区位)拼接异常!");
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        } else {
            return errorStr;
        }
    }

    /**
     * 设定用途
     *
     * @return
     */
    public String getSetUse() {
        StringBuilder stringBuilder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getSetUse() != null) {
                    DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                    if (dataSetUseField != null) {
                        stringBuilder.append(dataSetUseField.getName());
                    }
                }
            }
            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                return moreJudgeObject(stringBuilder.toString(), stringBuilder.toString());
            }
        }
        return errorStr;
    }

    /**
     * 多个委估对象 显示
     *
     * @param str
     * @return
     */
    public String moreJudgeObject(String str, String s) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(s)) {
            return "";
        } else {
            return String.format("1,3号委估对象%s2号委估对象%s", str, s);
        }
    }

    /**
     * 土地实际用途
     *
     * @return
     */
    public String getLandPracticalUse() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList.parallelStream().forEach(schemeJudgeObject -> {
                if (NumberUtils.isNumber(schemeJudgeObject.getCertUse())) {
                    builder.append(baseDataDicService.getNameById(schemeJudgeObject.getCertUse()));
                } else {
                    builder.append(schemeJudgeObject.getCertUse());
                }
            });
            try {
                if (StringUtils.isNotBlank(builder.toString())) {
                    return moreJudgeObject(builder.toString(), builder.toString());
                }
            } catch (Exception e) {

            }
        }
        return errorStr;
    }

    /**
     * 使用权类型
     *
     * @return
     */
    public String getUseRightType() {
        String value = getLandPracticalUse();
        if (StringUtils.isNotBlank(value)) {
            return value;
        } else {
            return errorStr;
        }
    }

    /**
     * 价值时点
     *
     * @return
     */
    public String getValueTimePoint() {
        if (getSchemeAreaGroup().getValueTimePoint() != null) {
            return DateUtils.format(getSchemeAreaGroup().getValueTimePoint());
        }
        return errorStr;
    }

    /**
     * 价值时点说明
     *
     * @return
     */
    public String getValueTimePointRemark() {
        if (StringUtils.isNotBlank(getSchemeAreaGroup().getTimePointExplain())) {
            return getSchemeAreaGroup().getTimePointExplain();
        }
        return errorStr;
    }

    /**
     * 土地使用管制
     *
     * @return
     */
    public String getLandUseControl() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (StringUtils.isNotBlank(schemeJudgeObject.getCertUse())) {
                    stringBuilder.append(schemeJudgeObject.getCertUse());
                    stringBuilder.append(";");
                }
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(stringBuilder.toString())) {
            return stringBuilder.toString();
        }
        return errorStr;
    }

    /**
     * 出租或占用情况
     *
     * @return
     */
    public String getRentalPossessionDesc() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (StringUtils.isNotBlank(schemeJudgeObject.getRentalPossessionDesc())) {
                    stringBuilder.append(schemeJudgeObject.getRentalPossessionDesc());
                    stringBuilder.append(";");
                }
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(stringBuilder.toString())) {
            return stringBuilder.toString();
        }
        return errorStr;
    }

    /**
     * 土地他项权利
     *
     * @return
     */
    public String getInventoryRight() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (projectPhase != null) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                            for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                stringBuilder.append(String.format("%s；", baseDataDicService.getNameById(inventoryRight.getCategory())));
                            }
                        }
                    }
                }
            }
        } catch (Exception e1) {

        }
        if (StringUtils.isNotBlank(stringBuilder.toString())) {
            return stringBuilder.toString();
        }
        return errorStr;
    }

    /**
     * 假设开发法适用原因
     *
     * @return
     */
    public String getDevelopmentAssistApplyReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 假设开发法不适用原因
     *
     * @return
     */
    public String getDevelopmentAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (!schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getNotApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 假设开发法不适用原因
     *
     * @return
     */
    public String getDevelopmentAssistThink() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT);
                    if (baseDataDic != null) {
                        if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                            builder.append(schemeJudgeFunction.getThinking()).append(";");
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }


    /**
     * 收益法适用原因
     *
     * @return
     */
    public String getIncomeAssistApplyReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 收益法不适用原因
     *
     * @return
     */
    public String getIncomeAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (!schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getNotApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 收益法评估思路
     *
     * @return
     */
    public String getIncomeAssistThink() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_INCOME);
                    if (baseDataDic != null) {
                        if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                            builder.append(schemeJudgeFunction.getThinking()).append(";");
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 市场比较法适用原因
     *
     * @return
     */
    public String getCompareAssistApplyReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 市场比较法不适用原因
     *
     * @return
     */
    public String getCompareAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (!schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getNotApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 市场比较法评估思路
     *
     * @return
     */
    public String getCompareAssistThink() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
                    if (baseDataDic != null) {
                        if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                            builder.append(schemeJudgeFunction.getThinking()).append(";");
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 成本法适用原因
     *
     * @return
     */
    public String getCostAssistApplyReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 成本法不适用原因
     *
     * @return
     */
    public String getCostAssistNotApplicableReason() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    if (!schemeJudgeFunction.getBisApplicable()) {
                        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
                        if (baseDataDic != null) {
                            if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                                builder.append(schemeJudgeFunction.getNotApplicableReason()).append(";");
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 成本法评估思路
     *
     * @return
     */
    public String getCostAssistThink() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_COST);
                    if (baseDataDic != null) {
                        if (Objects.equal(baseDataDic.getId(), schemeJudgeFunction.getMethodType())) {
                            builder.append(schemeJudgeFunction.getThinking()).append(";");
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }


    /**
     * 计算过程
     *
     * @return
     * @throws Exception
     */
    public String getComputationProcess(SysAttachmentDto sysAttachmentDto) throws Exception {
        String tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        return tempDir;
    }

    /**
     * 参数选取与应用
     *
     * @return
     * @throws Exception
     */
    public String getSelectionApplicationParameters(SysAttachmentDto sysAttachmentDto) throws Exception {
        String tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        return tempDir;
    }

    /**
     * 建筑物权益状况
     *
     * @return
     * @throws Exception
     */
    public String getStatusBuildingRightsInterests(SysAttachmentDto sysAttachmentDto) throws Exception {
        String tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        return tempDir;
    }

    /**
     * 本次估价的总体思路和评估方法的选取
     *
     * @return
     * @throws Exception
     */
    public String gettheGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods(SysAttachmentDto sysAttachmentDto) throws Exception {
        String tempDir = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
        return tempDir;
    }

    /**
     * 估价结果一览表
     *
     * @return
     * @throws Exception
     */
    public String getjudgeBuildResultSurveySheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        builder.writeln("估价结果一览表");

        int i = 0;
        while (i < schemeJudgeObjectList.size() + 1) {
            Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
            Table table = builder.startTable();
            switch (i) {
                case 1:
                    for (int k = 0; k < 9; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.writeln("产权证号");
                                break;
                            case 1:
                                builder.insertCell();
                                builder.writeln("业务件号");
                                break;
                            case 2:
                                builder.insertCell();
                                builder.writeln("房屋座落");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln("权利人");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.writeln("共有方式");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.writeln("规划用途");
                                break;
                            case 6:
                                builder.insertCell();
                                builder.writeln("所在层数");
                                break;
                            case 7:
                                builder.insertCell();
                                builder.writeln("建筑面积");
                                break;
                            case 8:
                                builder.insertCell();
                                builder.writeln("房地产单价");
                                break;
                            case 9:
                                builder.insertCell();
                                builder.writeln("房地产价值");
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 2:
                    this.judgeBuildResultSurveySheet2(builder, schemeJudgeObjectList.get(i - 1), 9);
                    break;
                case 3:
                    this.judgeBuildResultSurveySheet2(builder, schemeJudgeObjectList.get(i - 1), 9);
                    break;
                default:
                    break;
            }

            i++;
            //设置表格边框的宽度
            builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
            builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
            builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
            builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
            //设置具体宽度
            builder.getCellFormat().setWidth(100);
            //水平居中
            builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
            //上下居中
            builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
            builder.endTable();
        }

        doc.save(localPath);
        return localPath;
    }

    private void judgeBuildResultSurveySheet2(DocumentBuilder builder, SchemeJudgeObject schemeJudgeObject, int num) throws Exception {
        for (int k = 0; k < num; k++) {
            switch (k) {
                case 0:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getName());
                    break;
                case 1:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getName());
                    break;
                case 2:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getSeat());
                    break;
                case 3:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getOwnership());
                    break;
                case 4:
                    builder.insertCell();
                    builder.writeln(errorStr);
                    break;
                case 5:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getCertUse());
                    break;
                case 6:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getName());
                    break;
                case 7:
                    builder.insertCell();
                    builder.writeln(schemeJudgeObject.getEvaluationArea().toString());
                    break;
                case 8:
                    builder.insertCell();
                    builder.writeln(errorStr);
                    break;
                case 9:
                    builder.insertCell();
                    builder.writeln(errorStr);
                    break;
                default:
                    builder.insertCell();
                    break;
            }
        }
        builder.endRow();
    }

    /**
     * 房屋所有权登记状况表
     *
     * @return
     * @throws Exception
     */
    public String getHousingOwnershipRegistrationStatementSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        builder.writeln("房屋所有权登记状况表");
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {

            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setDeclareRecordId(schemeJudgeObjectList.get(i).getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                    GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObjectList.get(i).getDeclareRecordId());
                    declareRecord = declareRecord == null ? new DeclareRecord() : declareRecord;
                    builder.writeln(schemeJudgeObjectList.get(i).getName());
                    Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                    Table table = builder.startTable();
                    for (int j = 0; j < 7; j++) {
                        switch (j) {
                            case 0:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 0:
                                            builder.insertCell();
                                            builder.writeln("权益状况");
                                            break;
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("土地权益类型");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            builder.writeln(errorStr);
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            case 1:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("土地管制情况");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            if (true) {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                //证载用途
                                                if (generateBaseExamineService.getBasicHouse().getCertUse() != null) {
                                                    stringBuilder.append(String.format("证载用途:%s；", baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getCertUse())));
                                                }
                                                if (generateBaseExamineService.getEstate().getFloorArea() != null) {
                                                    stringBuilder.append(String.format("建筑面积:%s；", generateBaseExamineService.getEstate().getFloorArea()));
                                                }
                                                if (generateBaseExamineService.getEstate().getCoverAnArea() != null) {
                                                    stringBuilder.append(String.format("占地面积:%s；", generateBaseExamineService.getEstate().getCoverAnArea()));
                                                }
                                                if (generateBaseExamineService.getEstate().getVolumetricRate() != null) {
                                                    stringBuilder.append(String.format("容积率:%s；", generateBaseExamineService.getEstate().getVolumetricRate()));
                                                }
                                                if (generateBaseExamineService.getEstate().getGreeningRate() != null) {
                                                    stringBuilder.append(String.format("绿化率:%s；", generateBaseExamineService.getEstate().getGreeningRate()));
                                                }
                                                if (generateBaseExamineService.getBasicBuilding().getBuildingHeight() != null) {
                                                    stringBuilder.append(String.format("建筑高度:%s；", generateBaseExamineService.getBasicBuilding().getBuildingHeight()));
                                                }
                                                builder.writeln(stringBuilder.toString());
                                            }
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            case 2:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("土地他项权力");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            builder.writeln(getInventoryRight());
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            case 3:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("其他特殊情况");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            if (true) {
                                                SurveyAssetInventory assetInventory = surveyAssetInventoryDao.getDataByPlanDetailsId(projectPlanDetails.getId());
                                                if (assetInventory == null) {
                                                    assetInventory = new SurveyAssetInventory();
                                                }
                                                builder.writeln(StringUtils.isNotBlank(assetInventory.getSpecialCase()) ? assetInventory.getSpecialCase() : errorStr);
                                            }
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            case 4:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("房屋所有权");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            builder.writeln(StringUtils.isNotBlank(declareRecord.getPublicSituation()) ? declareRecord.getPublicSituation() : errorStr);
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            case 5:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("出租或占用情况");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            builder.writeln(StringUtils.isNotBlank(schemeJudgeObjectList.get(i).getRentalPossessionDesc()) ? schemeJudgeObjectList.get(i).getRentalPossessionDesc() : errorStr);
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            case 6:
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 1:
                                            builder.insertCell();
                                            builder.writeln("物业管理情况");
                                            break;
                                        case 3:
                                            builder.insertCell();
                                            if (true) {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                if (generateBaseExamineService.getBasicBuilding().getPropertyType() != null) {
                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(generateBaseExamineService.getBasicBuilding().getProperty()) ? errorStr : generateBaseExamineService.getBasicBuilding().getProperty()).append("；");
                                                    stringBuilder.append(String.format("物业费:%s；", generateBaseExamineService.getBasicBuilding().getPropertyFee()));
                                                }
                                                builder.writeln(stringBuilder.toString());
                                            }
                                            break;
                                        default:
                                            builder.insertCell();
                                            break;
                                    }
                                }
                                builder.endRow();
                                break;
                            default:
                                break;
                        }
                        mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                        mergeCellModelList.add(new MergeCellModel(0, 0, 6, 0));
                    }
                    if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                        for (MergeCellModel mergeCellModel : mergeCellModelList) {
                            Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                            Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                        }
                    }
                    builder.endTable();
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象区位状况表
     *
     * @return
     */
    public String getJudgeObjectAreaStatusSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");

        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                        List<DataSetUseField> setUseFields = Lists.newArrayList();
                        if (dataSetUseField != null) {
                            setUseFields.add(dataSetUseField);
                        }
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        builder.writeln(schemeJudgeObject.getName());
                        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                        Table table = builder.startTable();
                        //行
                        for (int j = 0; j < 20; j++) {
                            if (0 <= j && j <= 5) {
                                //列
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 0) {
                                        builder.insertCell();
                                        builder.writeln("区域位置");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 0:
                                                    builder.writeln("座落");
                                                    break;
                                                case 1:
                                                    builder.writeln("方位");
                                                    break;
                                                case 2:
                                                    builder.writeln("商业繁华程度");
                                                    break;
                                                case 3:
                                                    builder.writeln("临街（路状况）");
                                                    break;
                                                case 4:
                                                    builder.writeln("楼层");
                                                    break;
                                                case 5:
                                                    builder.writeln("人文景观");
                                                    break;
                                                default:
                                                    builder.writeln(String.format("%d-%d", j, k));
                                                    break;
                                            }
                                        }
                                        if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                case 0:
                                                    builder.writeln(String.format("%s%s", getSchemeAreaGroup().getAreaName(), schemeJudgeObject.getSeat()));
                                                    break;
                                                case 1:
                                                    builder.writeln(StringUtils.isNotBlank(generateBaseExamineService.getByDataBlock().getPosition()) ? generateBaseExamineService.getByDataBlock().getPosition() : "方位：暂无");
                                                    break;
                                                case 2:
                                                    builder.writeln("估价对象所在区域为规划新城区，区域商业待发展，目前以超市、零售商店为主，商业繁华度一般");
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        List<BasicHouseFaceStreetVo> list = generateBaseExamineService.getBasicHouseFaceStreetList();
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        if (CollectionUtils.isNotEmpty(list)) {
                                                            for (BasicHouseFaceStreetVo faceStreet : list) {
                                                                stringBuilder.append(faceStreet.getStreetName())
                                                                        .append(faceStreet.getStreetLevelName())
                                                                        .append(faceStreet.getTrafficFlowName()).append(faceStreet.getVisitorsFlowrateName());
                                                                stringBuilder.append("\r\n");
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 4:
                                                    builder.writeln(generateBaseExamineService.getBasicHouse().getFloor());
                                                    break;
                                                case 5:
                                                    builder.writeln("人文景观：分布有汇融悉尼湾达令港、盛世名城、炬星幸福里等众多住宅区，居民多为中档收入人群、素质较高，社会治安良好。");
                                                    break;
                                                default:
                                                    builder.writeln("");
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(0, 0, 5, 0));
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                            }
                            if (5 < j && j <= 10) {
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 6) {
                                        builder.insertCell();
                                        builder.writeln("交通状况");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 6:
                                                    builder.writeln("道路状况");
                                                    break;
                                                case 7:
                                                    builder.writeln("公共交通");
                                                    break;
                                                case 8:
                                                    builder.writeln("对外交通");
                                                    break;
                                                case 9:
                                                    builder.writeln("交通管制情况");
                                                    break;
                                                case 10:
                                                    builder.writeln("停车方便度");
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                case 6:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingTrafficVo> voList = generateBaseExamineService.getBasicMatchingTrafficList();
                                                        if (CollectionUtils.isNotEmpty(voList)) {
                                                            voList.parallelStream().forEach(basicMatchingTrafficVo -> {
                                                                stringBuilder.append(basicMatchingTrafficVo.getName())
                                                                        .append(basicMatchingTrafficVo.getNatureName())
                                                                        .append(basicMatchingTrafficVo.getDistanceName());
                                                                stringBuilder.append("\r\n");
                                                            });
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 7:
                                                    builder.writeln("暂无");
                                                    break;
                                                case 8:
                                                    builder.writeln("暂无");
                                                    break;
                                                case 9:
                                                    builder.writeln("暂无");
                                                    break;
                                                case 10:
                                                    builder.writeln("暂无");
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(6, 0, 10, 0));
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                            }
                            if (10 < j && j <= 15) {
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 11) {
                                        builder.insertCell();
                                        builder.writeln("外部配套设施");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 11:
                                                    builder.writeln("城市基础设施");
                                                    break;
                                                case 12:
                                                    builder.writeln("公共服务设施");
                                                    break;
                                            }
                                        } else if (k == 2) {
                                            switch (j) {
                                                case 12:
                                                    builder.writeln("银行");
                                                    break;
                                                case 13:
                                                    builder.writeln("购物");
                                                    break;
                                                case 14:
                                                    builder.writeln("教育");
                                                    break;
                                                case 15:
                                                    builder.writeln("娱乐休闲");
                                                    break;
                                            }
                                        } else if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                //城市基础设施
                                                case 11:
                                                    List<BasicEstateSupply> estateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(128);
                                                        //楼盘下供电
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_POWER.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }

                                                        //楼盘下供水
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_WATER.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }

                                                        //楼盘下排水
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_DRAIN_WATER.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }

                                                        //楼盘下采暖供热
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_HEATING.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }

                                                        //楼盘下供气
                                                        if (CollectionUtils.isNotEmpty(estateSupplyList)) {
                                                            for (BasicEstateSupply supply : estateSupplyList) {
                                                                if (org.apache.commons.lang.StringUtils.equals(supply.getType(), ExamineEstateSupplyEnumType.ESTATE_SUPPLY_GAS.getName())) {
                                                                    generateBaseExamineService.getCommonSupply(stringBuilder, supply);
                                                                }
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 12:
                                                    if (true) {//金融机构
                                                        if (true) {
                                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                                            List<BasicMatchingFinanceVo> financeList = generateBaseExamineService.getBasicMatchingFinanceList();
                                                            if (CollectionUtils.isNotEmpty(financeList)) {
                                                                for (BasicMatchingFinance finance : financeList) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(finance.getName()) ? "" : String.format("名称:%s；", finance.getName()));
                                                                    stringBuilder.append(finance.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(finance.getCategory())));
                                                                    stringBuilder.append(finance.getNature() == null ? "" : String.format("金融机构性质:%s；", baseDataDicService.getNameById(finance.getNature())));
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(finance.getName()) ? "" : String.format("服务内容:%s；", finance.getName()));
                                                                }
                                                            }
                                                            builder.writeln(stringBuilder.toString());
                                                        }
                                                    }
                                                    break;

                                                case 13: //购物
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingLeisurePlace> leisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                                                        if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                                            for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                                if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                                }
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 14://教育
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingEducation> educationList = generateBaseExamineService.getBasicMatchingEducatioListn();
                                                        if (CollectionUtils.isNotEmpty(educationList)) {
                                                            for (BasicMatchingEducation education : educationList) {
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(education.getSchoolName()) ? "" : String.format("学校名称:%s；", education.getSchoolName()));
                                                                stringBuilder.append(education.getSchoolNature() == null ? "" : String.format("学校性质:%s；", baseDataDicService.getNameById(education.getSchoolNature())));
                                                                stringBuilder.append(education.getSchoolGradation() == null ? "" : String.format("学校级次:%s；", baseDataDicService.getNameById(education.getSchoolGradation())));
                                                                stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(education.getSchoolLevel()) ? "" : String.format("学校等级:%s；", baseDataDicService.getNameById(Integer.valueOf(education.getSchoolLevel()))));
                                                                stringBuilder.append(education.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(education.getDistance())));
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 15://娱乐休闲
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingLeisurePlace> leisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                                                        if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                                            for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                                if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {
                                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                                    stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                                    stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                                    stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                                }
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(11, 0, 15, 0));
                                mergeCellModelList.add(new MergeCellModel(12, 1, 15, 1));
                                mergeCellModelList.add(new MergeCellModel(11, 1, 11, 2));
                            }
                            if (15 < j && j <= 18) {
                                for (int k = 0; k < 5; k++) {
                                    if (k == 0 && j == 16) {
                                        builder.insertCell();
                                        builder.writeln("周围环境和景观");
                                    } else {
                                        builder.insertCell();
                                        if (k == 1) {
                                            switch (j) {
                                                case 16:
                                                    builder.writeln("自然环境");
                                                    break;
                                                case 17:
                                                    builder.writeln("人文环境");
                                                    break;
                                                case 18:
                                                    builder.writeln("景观");
                                                    break;
                                            }
                                        }
                                        if (k == 3) {
                                            mergeCellModelList.add(new MergeCellModel(j, k, j, k + 1));
                                            switch (j) {
                                                case 16:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic naturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_NATURAL);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, naturalDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 17:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic culturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_CULTURAL);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, culturalDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                case 18:
                                                    if (true) {
                                                        List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                                        StringBuilder stringBuilder = new StringBuilder(1024);
                                                        if (CollectionUtils.isNotEmpty(environmentList)) {
                                                            BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                                                            for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                                generateBaseExamineService.getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                                                            }
                                                        }
                                                        builder.writeln(stringBuilder.toString());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                }
                                builder.endRow();
                                mergeCellModelList.add(new MergeCellModel(16, 0, 18, 0));
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                            }
                        }
                        for (MergeCellModel mergeCellModel : mergeCellModelList) {
                            Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                            Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                        }
                        builder.endTable();
                    }
                }
            }
        }
        doc.save(localPath);
        this.judgeObjectAreaStatusSheet = localPath;
        return judgeObjectAreaStatusSheet;
    }

    /**
     * 估价土地实体状况表
     *
     * @return
     */
    public String getJudgeObjectLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");

        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        builder.writeln("估价土地实体状况表");
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                        Table table = builder.startTable();
                        //行
                        for (int j = 0; j < 8; j++) {
                            switch (j) {
                                case 0:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("估价对象名称");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(schemeJudgeObject.getName());
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln("备注");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 1:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("用途及级别");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(10);
                                                    stringBuilder.append("土地类型:");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName()) ? generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName() : errorStr);
                                                    stringBuilder.append(";");
                                                    stringBuilder.append("土地级别:");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandLevelName()) ? generateBaseExamineService.getBasicEstateLandState().getLandLevelName() : errorStr);
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 2:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("东至,南至,西至,北至");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(10);
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getEastTo()) ? generateBaseExamineService.getBasicEstateLandState().getEastTo() : errorStr);
                                                    stringBuilder.append(",");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getSouthTo()) ? generateBaseExamineService.getBasicEstateLandState().getSouthTo() : errorStr);
                                                    stringBuilder.append(",");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getWestTo()) ? generateBaseExamineService.getBasicEstateLandState().getWestTo() : errorStr);
                                                    stringBuilder.append(",");
                                                    stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getNorthTo()) ? generateBaseExamineService.getBasicEstateLandState().getNorthTo() : errorStr);
                                                    stringBuilder.append(",");
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 3:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("面积");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandArea())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getLandArea());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 4:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("形状");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getShapeStateName())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getShapeStateName());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getShapeStateRemark())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getShapeStateRemark());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 5:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("地形、地势、工程地质");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    stringBuilder.append("地形:");
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getPlanenessName())) {
                                                        stringBuilder.append(generateBaseExamineService.getBasicEstateLandState().getPlanenessName());

                                                    } else {
                                                        stringBuilder.append(errorStr);
                                                    }
                                                    stringBuilder.append(";");
                                                    stringBuilder.append("地势:");
                                                    if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName())) {
                                                        stringBuilder.append(generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName());
                                                    } else {
                                                        stringBuilder.append(errorStr);
                                                    }
                                                    stringBuilder.append(";");
                                                    stringBuilder.append("工程地质:");
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 6:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("开发程度");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName())) {
                                                    builder.writeln(generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName());
                                                } else {
                                                    builder.writeln(errorStr);
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 7:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("其它");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                default:
                                    break;
                            }
                            mergeCellModelList.add(new MergeCellModel(j, 1, j, 3));
                        }
                        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                            }
                        }
                        builder.endTable();
                        builder.writeln("");
                    }
                }
            }
        }
        doc.save(localPath);
        this.judgeObjectLandStateSheet = localPath;
        return judgeObjectLandStateSheet;
    }

    /**
     * 估价对象建筑实体状况表
     *
     * @return
     */
    public String getJudgeBuildLandStateSheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");

        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null);
        ProjectPhase projectPhaseScene = null;
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
            for (ProjectPhaseVo projectPhaseVo : projectPhaseVos) {
                if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                    projectPhaseScene = projectPhaseVo;
                }
            }
        }
        builder.writeln("估价对象建筑实体状况表");
        if (projectPhaseScene != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhaseScene.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
                        Table table = builder.startTable();
                        //行
                        for (int j = 0; j < 18; j++) {
                            switch (j) {
                                case 0:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("估价对象名称");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(schemeJudgeObject.getName());
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln("备注");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 1:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("建筑规模");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 2:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("建筑功能");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    List<BasicBuildingFunction> functionList = generateBaseExamineService.getBasicBuildingFunctionList();
                                                    if (CollectionUtils.isNotEmpty(functionList)) {
                                                        functionList.parallelStream().forEach(basicBuildingFunction -> {
                                                            stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getType()));
                                                            stringBuilder.append(",");
                                                            stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getDecorationPart()));
                                                        });
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 3:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("结构");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                String temp = baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory());
                                                builder.writeln(StringUtils.isNotBlank(temp) ? temp : temp);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 4:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("层数");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(String.valueOf(generateBaseExamineService.getBasicBuilding().getFloorCount() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getFloorCount()));
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 5:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("外观");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    List<BasicBuildingOutfit> outfitList = generateBaseExamineService.getBasicBuildingOutfitList();
                                                    if (CollectionUtils.isNotEmpty(outfitList)) {
                                                        for (BasicBuildingOutfit outfit : outfitList) {
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(outfit.getDecorationPart()) ? errorStr : String.format("装修部位:%s；", outfit.getDecorationPart()));
                                                            stringBuilder.append(outfit.getDecoratingMaterial() == null ? errorStr : String.format("装修材料:%s；", baseDataDicService.getNameById(outfit.getDecoratingMaterial())));
                                                            stringBuilder.append(outfit.getConstructionTechnology() == null ? errorStr : String.format("施工工艺:%s；", baseDataDicService.getNameById(outfit.getConstructionTechnology())));
                                                            stringBuilder.append(outfit.getMaterialPrice() == null ? errorStr : String.format("材料价格区间:%s；", baseDataDicService.getNameById(outfit.getMaterialPrice())));
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 6:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("室内净高或层高");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder(128);
                                                    stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getFloorHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getFloorHeight()));
                                                    stringBuilder.append(",");
                                                    stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getNetHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getNetHeight()));
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 7:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("装饰装修");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicUnitDecorate> decorateList = generateBaseExamineService.getBasicUnitDecorateList();
                                                    if (CollectionUtils.isNotEmpty(decorateList)) {
                                                        for (BasicUnitDecorate unitDecorate : decorateList) {
                                                            stringBuilder.append(unitDecorate.getDecorationPart() == null ? errorStr : String.format("装修部位:%s；", baseDataDicService.getNameById(unitDecorate.getDecorationPart())));
                                                            stringBuilder.append(unitDecorate.getDecoratingMaterial() == null ? errorStr : String.format("装修材料:%s；", baseDataDicService.getNameById(unitDecorate.getDecoratingMaterial())));
                                                            stringBuilder.append(unitDecorate.getConstructionTechnology() == null ? errorStr : String.format("施工工艺:%s；", baseDataDicService.getNameById(unitDecorate.getConstructionTechnology())));
                                                            stringBuilder.append(unitDecorate.getMaterialPriceRange() == null ? errorStr : String.format("材料价格区间:%s；", baseDataDicService.getNameById(unitDecorate.getMaterialPriceRange())));
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 8:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("设施设备");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicHouseEquipment> equipmentList = generateBaseExamineService.getBasicHouseEquipmentList();
                                                    if (CollectionUtils.isNotEmpty(equipmentList)) {
                                                        stringBuilder = new StringBuilder();
                                                        for (BasicHouseEquipment examineHouseEquipment : equipmentList) {
                                                            if (org.apache.commons.lang.StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()) ||
                                                                    org.apache.commons.lang.StringUtils.equals(examineHouseEquipment.getType(), ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                                                                stringBuilder.append(baseDataDicService.getNameById(examineHouseEquipment.getCategory()));
                                                                stringBuilder.append(examineHouseEquipment.getEquipment()).append(examineHouseEquipment.getEquipmentPrice()).append("；");
                                                            }
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 9:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("空间布局");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    BaseDataDic practicalUseDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_HOUSE_PRACTICAL_USE_HOUSE);
                                                    if (practicalUseDic != null) {
                                                        String huxing = org.apache.commons.lang.StringUtils.isBlank(generateBaseExamineService.getBasicHouse().getNewHuxingName()) ? generateBaseExamineService.getBasicHouse().getHuxingName() : generateBaseExamineService.getBasicHouse().getNewHuxingName();
                                                        stringBuilder.append(huxing).append("；");
                                                        if (!practicalUseDic.getId().equals(generateBaseExamineService.getBasicHouse().getPracticalUse())) {
                                                            List<BasicHouseRoom> roomList = generateBaseExamineService.getBasicHouseRoomList();
                                                            if (CollectionUtils.isNotEmpty(roomList)) {
                                                                for (BasicHouseRoom room : roomList) {
                                                                    stringBuilder.append(baseDataDicService.getNameById(room.getRoomType()))
                                                                            .append(String.format("开间:%s米；", room.getOpening())).append(String.format("进深:%s米；", room.getDepth())).append("；");
                                                                }
                                                            }
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 10:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("竣工日期和设计使用年限");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    builder.writeln(DateUtils.format(generateBaseExamineService.getBasicBuilding().getBeCompletedTime()));
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 11:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("维护保养和完损状况");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicUnitElevator> elevatorList = generateBaseExamineService.getBasicUnitElevatorList();
                                                    if (CollectionUtils.isNotEmpty(elevatorList)) {
                                                        for (BasicUnitElevator elevator : elevatorList) {
                                                            stringBuilder.append(elevator.getMaintenance() == null ? errorStr : String.format("电梯维护情况:%s；", baseDataDicService.getNameById(elevator.getMaintenance())));
                                                            stringBuilder.append(elevator.getType() == null ? errorStr : String.format("电梯类型:%s；", baseDataDicService.getNameById(elevator.getType())));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(elevator.getBrand()) ? errorStr : String.format("电梯品牌:%s；", elevator.getBrand()));
                                                            stringBuilder.append(elevator.getNumber() == null ? errorStr : String.format("电梯数量:%s；", elevator.getNumber()));
                                                            stringBuilder.append(elevator.getQuasiLoadNumber() == null ? errorStr : String.format("准载人数:%s；", elevator.getQuasiLoadNumber()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(elevator.getQuasiLoadWeight()) ? "" : String.format("准载重量:%s；", elevator.getQuasiLoadWeight()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(elevator.getRunningSpeed()) ? "" : String.format("运行速度:%s；", elevator.getRunningSpeed()));
                                                        }
                                                    }
                                                    stringBuilder.append("\r\n");
                                                    List<BasicHouseCorollaryEquipment> corollaryEquipmentList = generateBaseExamineService.getBasicHouseCorollaryEquipmentList();
                                                    if (CollectionUtils.isNotEmpty(corollaryEquipmentList)) {
                                                        for (BasicHouseCorollaryEquipment equipment : corollaryEquipmentList) {
                                                            stringBuilder.append(equipment.getType() == null ? errorStr : String.format("类型:%s；", baseDataDicService.getNameById(equipment.getType())));
                                                            stringBuilder.append(equipment.getCategory() == null ? errorStr : String.format("类别:%s；", baseDataDicService.getNameById(equipment.getCategory())));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getName()) ? errorStr : String.format("名称:%s；", equipment.getName()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getParameterIndex()) ? errorStr : String.format("参数指标:%s；", equipment.getParameterIndex()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getMaintenanceStatus()) ? errorStr : String.format("维护状况:%s；", equipment.getMaintenanceStatus()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getEquipmentUse()) ? errorStr : String.format("设备用途:%s；", equipment.getEquipmentUse()));
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(equipment.getPrice()) ? errorStr : String.format("价格:%s；", equipment.getPrice()));
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 12:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("工程质量");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln("");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 13:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("日照、采光、通风、保温、隔热、隔声、防水");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                if (true) {
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    List<BasicHouseRoom> roomList = generateBaseExamineService.getBasicHouseRoomList();
                                                    List<BasicBuildingFunction> functionList = generateBaseExamineService.getBasicBuildingFunctionList();
                                                    if (CollectionUtils.isNotEmpty(roomList)) {
                                                        for (BasicHouseRoom room : roomList) {
                                                            //日照
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getSunshine()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getSunshine()));
                                                            //采光
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getLighting()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getLighting()));
                                                            //通风
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getAeration()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getAeration()));
                                                            //隔音
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(room.getSoundInsulation()) ? "" : String.format("%s:%s；", baseDataDicService.getNameById(room.getRoomType()), room.getSoundInsulation()));
                                                        }
                                                        //保温
                                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                                            BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                                            generateBaseExamineService.getCommonBuildingFunction(functionList, stringBuilder, heatInsulationDic);
                                                        }
                                                        //隔热
                                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                                            BaseDataDic heatInsulationDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_HEAT_INSULATION);
                                                            generateBaseExamineService.getCommonBuildingFunction(functionList, stringBuilder, heatInsulationDic);
                                                        }
                                                        //防水
                                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                                            BaseDataDic waterproofDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_BUILDING_FUNCTION_TYPE_WATERPROOF);
                                                            generateBaseExamineService.getCommonBuildingFunction(functionList, stringBuilder, waterproofDic);
                                                        }
                                                    }
                                                    builder.writeln(stringBuilder.toString());
                                                }
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 14:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("其它");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            case 4:
                                                builder.insertCell();
                                                builder.writeln(errorStr);
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 15:
                                    for (int k = 0; k < 5; k++) {
                                        builder.insertCell();
                                    }
                                    builder.endRow();
                                    break;
                                case 16:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("委估对象序号");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln(schemeJudgeObject.getNumber());
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                case 17:
                                    for (int k = 0; k < 5; k++) {
                                        switch (k) {
                                            case 0:
                                                builder.insertCell();
                                                builder.writeln("建筑实体分析");
                                                break;
                                            case 1:
                                                builder.insertCell();
                                                builder.writeln("综上所述，影响估价对象商业用房地产价值的实体状况中，其建筑物较新，相关配套设施齐全，维护保养较好，商铺开间、进深比一般，对估价对象商业用房地产价值的保值有积极的影响。");
                                                break;
                                            default:
                                                builder.insertCell();
                                                break;
                                        }
                                    }
                                    builder.endRow();
                                    break;
                                default:
                                    break;
                            }
                            if (j == 17 || j == 16) {
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 4));
                            } else if (j == 15) {
                                mergeCellModelList.add(new MergeCellModel(j, 0, j, 4));
                            } else {
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 3));
                            }
                        }
                        if (CollectionUtils.isNotEmpty(mergeCellModelList)) {
                            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                                Cell cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                                Cell cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                                AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                            }
                        }
                        builder.endTable();
                        builder.writeln("");
                    }
                }
            }
        }
        doc.save(localPath);
        this.judgeBuildLandStateSheet = localPath;
        return judgeBuildLandStateSheet;
    }

    /**
     * 估价委托书复印件
     *
     * @return
     * @throws Exception
     */
    public String getJUDGEOBJECTPRINCIPALCOPYSHEET() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getProjectProxyFileList(getProjectId());
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDtoList.get(0).getId());
            if (FileUtils.checkImgSuffix(imgPath)) {
                builder.insertImage(imgPath,
                        RelativeHorizontalPosition.MARGIN,
                        GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                        RelativeVerticalPosition.MARGIN,
                        GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                        GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                        GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                        WrapType.SQUARE);
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估计对象位置示意图
     *
     * @return
     */
    public String getEstimatedObjectLocationDiagram() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getJudgeObjectPositionFileList(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                builder.insertImage(imgPath,
                                        RelativeHorizontalPosition.MARGIN,
                                        GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                        RelativeVerticalPosition.MARGIN,
                                        GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                        GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                        GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                        WrapType.SQUARE);
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象实况照片
     *
     * @return
     */
    public String getValuation_Target_Live_Photos() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeReportFileItem> sysAttachmentDtoList = schemeReportFileService.getLiveSituationSelect(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    for (SchemeReportFileItem sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getAttachmentId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                builder.insertImage(imgPath,
                                        RelativeHorizontalPosition.MARGIN,
                                        GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                        RelativeVerticalPosition.MARGIN,
                                        GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                        GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                        GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                        WrapType.SQUARE);
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价对象权属证明复印件
     *
     * @return
     */
    public String getCopies_the_Ownership_Certificate_the_Valuation_Object() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getOwnershipCertFileList(getAreaId());
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                if (StringUtils.isNotBlank(imgPath)) {
                    if (FileUtils.checkImgSuffix(imgPath)) {
                        builder.insertImage(imgPath,
                                RelativeHorizontalPosition.MARGIN,
                                GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                RelativeVerticalPosition.MARGIN,
                                GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                WrapType.SQUARE);
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价中引用的专用文件资料
     *
     * @return
     * @throws Exception
     */
    public String getSpecial_documentation_referenced_in_valuation() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SysAttachmentDto> sysAttachmentDtoList = Lists.newArrayList();
        List<SysAttachmentDto> sysAttachmentDtoList1 = schemeReportFileService.getInventoryAddressFileList(getAreaId());
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList1)) {
            sysAttachmentDtoList.addAll(sysAttachmentDtoList1);
        }
        List<SysAttachmentDto> sysAttachmentDtoList2 = schemeReportFileService.getOwnershipCertFileList(getAreaId());
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList2)) {
            sysAttachmentDtoList.addAll(sysAttachmentDtoList2);
        }
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                if (StringUtils.isNotBlank(imgPath)) {
                    if (FileUtils.checkImgSuffix(imgPath)) {
                        builder.insertImage(imgPath,
                                RelativeHorizontalPosition.MARGIN,
                                GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                RelativeVerticalPosition.MARGIN,
                                GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                WrapType.SQUARE);
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 房地产估价机构营业执照复印件
     *
     * @return
     * @throws Exception
     */
    public String getCopyBusinessLicenseRealEstateValuationAgency() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForLicense();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    SysAttachmentDto sysAttachmentDto = attachmentDtoList.get(0);
                    String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                    if (StringUtils.isNotBlank(imgPath)) {
                        if (FileUtils.checkImgSuffix(imgPath)) {
                            builder.insertImage(imgPath,
                                    RelativeHorizontalPosition.MARGIN,
                                    GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                    RelativeVerticalPosition.MARGIN,
                                    GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                    GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                    GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                    WrapType.SQUARE);
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * '房地产估价机构资质证书复印件
     *
     * @return
     * @throws Exception
     */
    public String getCopyQualificationCertificateRealEstateValuationInstitution() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForPractising();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    SysAttachmentDto sysAttachmentDto = attachmentDtoList.get(0);
                    String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                    if (StringUtils.isNotBlank(imgPath)) {
                        if (FileUtils.checkImgSuffix(imgPath)) {
                            builder.insertImage(imgPath,
                                    RelativeHorizontalPosition.MARGIN,
                                    GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                    RelativeVerticalPosition.MARGIN,
                                    GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                    GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                    GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                    WrapType.SQUARE);
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 注册房地产估价师注册证书复印件
     *
     * @param account
     * @return
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerValuationInstitution(String account) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
            AdPersonalQualificationDto adCompanyQualificationDto = adPersonalQualificationDtoList.get(0);
            if (adCompanyQualificationDto != null) {
                if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                    List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                    if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                        SysAttachmentDto sysAttachmentDto = attachmentDtoList.get(0);
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                builder.insertImage(imgPath,
                                        RelativeHorizontalPosition.MARGIN,
                                        GenerateReportEnum.JUDGEOBJECTIMG.getLeft(),
                                        RelativeVerticalPosition.MARGIN,
                                        GenerateReportEnum.JUDGEOBJECTIMG.getTop(),
                                        GenerateReportEnum.JUDGEOBJECTIMG.getWidth(),
                                        GenerateReportEnum.JUDGEOBJECTIMG.getHeight(),
                                        WrapType.SQUARE);
                            }
                        }
                    }
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }


    public List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        if (!CollectionUtils.isNotEmpty(this.schemeJudgeObjectList)) {
            this.schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(this.getSchemeAreaGroup().getId());
        }
        return this.schemeJudgeObjectList;
    }

    public GenerateBaseExamineService getGenerateBaseExamineService(Integer planDetailsId) {
        return new GenerateBaseExamineService(planDetailsId);
    }

    private GenerateBaseDataService() {
    }

    public GenerateBaseDataService(Integer projectId, Integer areaId, Integer baseReportTemplateId, ProjectPlan projectPlan) {
        this.projectPlan = projectPlan;
        this.projectId = projectId;
        this.areaId = areaId;
        this.baseReportTemplateId = baseReportTemplateId;
        this.schemeJudgeObjectService = SpringContextUtils.getBean(SchemeJudgeObjectService.class);
        this.schemeAreaGroupService = SpringContextUtils.getBean(SchemeAreaGroupService.class);
        this.projectNumberRecordService = SpringContextUtils.getBean(ProjectNumberRecordService.class);
        this.baseReportService = SpringContextUtils.getBean(BaseReportService.class);
        this.projectInfoService = SpringContextUtils.getBean(ProjectInfoService.class);
        this.baseDataDicService = SpringContextUtils.getBean(BaseDataDicService.class);
        this.schemeJudgeFunctionService = SpringContextUtils.getBean(SchemeJudgeFunctionService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.projectPlanDetailsService = SpringContextUtils.getBean(ProjectPlanDetailsService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        this.projectPhaseService = SpringContextUtils.getBean(ProjectPhaseService.class);
        this.surveyAssetInventoryRightService = SpringContextUtils.getBean(SurveyAssetInventoryRightService.class);
        this.declareRecordService = SpringContextUtils.getBean(DeclareRecordService.class);
        this.surveyAssetInventoryDao = SpringContextUtils.getBean(SurveyAssetInventoryDao.class);
        this.schemeSurePriceService = SpringContextUtils.getBean(SchemeSurePriceService.class);
        this.schemeReimbursementService = SpringContextUtils.getBean(SchemeReimbursementService.class);
        this.publicService = SpringContextUtils.getBean(PublicService.class);
        this.schemeSupportInfoService = SpringContextUtils.getBean(SchemeSupportInfoService.class);
        this.adRpcQualificationsService = SpringContextUtils.getBean(com.copower.pmcc.assess.service.AdRpcQualificationsAppService.class);
        this.compileReportService = SpringContextUtils.getBean(CompileReportService.class);
        this.schemeReportFileService = SpringContextUtils.getBean(SchemeReportFileService.class);
    }


}
