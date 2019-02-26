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
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.MergeCellModel;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseReportService;
import com.copower.pmcc.assess.service.data.*;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.compile.CompileReportService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.*;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    private DataQualificationService dataQualificationService;
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    private SchemeInfoService schemeInfoService;
    private EvaluationMethodService evaluationMethodService;
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    private MdIncomeService mdIncomeService;
    private MdMarketCompareService mdMarketCompareService;
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    private DataHisRightInfoPublicityService dataHisRightInfoPublicityService;
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    private DataMethodFormulaService dataMethodFormulaService;

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
     * 估价委托人信息
     *
     * @return
     */
    public String getPrincipalInfo() {
        StringBuilder stringBuilder = new StringBuilder(24);
        if (Objects.equal(getProjectInfo().getConsignorVo().getCsType(), InitiateContactsEnum.legalPerson.getId())) {
            stringBuilder.append("名称:").append(getProjectInfo().getConsignorVo().getCsEntrustmentUnitName()).append(";");
            stringBuilder.append("统一社会信用代码:").append(getProjectInfo().getConsignorVo().getCsSociologyCode()).append(";");
            stringBuilder.append("住所:").append(getProjectInfo().getConsignorVo().getCsAddress()).append("");
            stringBuilder.append("法定代表人:").append(getProjectInfo().getConsignorVo().getCsLegalRepresentative()).append("");
        }
        if (Objects.equal(getProjectInfo().getConsignorVo().getCsType(), InitiateContactsEnum.naturalPerson.getId())) {
            stringBuilder.append("姓名:").append(getProjectInfo().getConsignorVo().getCsName()).append(";");
            stringBuilder.append("身份证号:").append(getProjectInfo().getConsignorVo().getCsIdcard()).append(";");
            stringBuilder.append("地址:").append(getProjectInfo().getConsignorVo().getCsAddress()).append("");
        }
        return stringBuilder.toString();
    }

    /**
     * 财产范围说明
     *
     * @return
     * @throws Exception
     */
    public String getScopePropertyExplain() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(projectPhases) && CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setProjectPhaseId(projectPhase.getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                        if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                            if (generateBaseExamineService.getBasicTrading() != null && StringUtils.isNotBlank(generateBaseExamineService.getBasicTrading().getScopePropertyExplain())) {
                                stringSet.add(generateBaseExamineService.getBasicTrading().getScopePropertyExplain());
                            }
                        }
                    }
                }
            }
        }
        stringBuilder.append(" ");
        if (CollectionUtils.isNotEmpty(stringSet)) {
            stringSet.stream().forEach(s -> stringBuilder.append(s).append(","));
            stringBuilder.append(".");
        }
        return stringBuilder.toString();
    }

    /**
     * 建筑面积及评估面积
     *
     * @return
     */
    public String getBuildingAndAssessArea() {
        StringBuilder stringBuilder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getEvaluationArea() != null) {
                    stringBuilder.append(schemeJudgeObjectList.get(i).getEvaluationArea().toString()).append("㎡");
                    if (i == schemeJudgeObjectList.size() - 1) {
                        stringBuilder.append(";");
                    } else {
                        stringBuilder.append(",");
                    }
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 座落
     *
     * @return
     * @throws Exception
     */
    public String getSeat() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(projectPhases) && CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                BasicEstate basicEstate = generateBaseExamineService.getEstate();
                                if (basicEstate != null) {
                                    if (StringUtils.isNotBlank(basicEstate.getStreet()) && StringUtils.isNotBlank(basicEstate.getName())) {
                                        stringBuilder.append(schemeJudgeObject.getNumber());
                                        if (schemeJudgeObject.getSplitNumber() != null) {
                                            stringBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
                                        }
                                        stringBuilder.append("号");
                                        stringBuilder.append(",");
                                        stringSet.add(String.format("%s%s", basicEstate.getStreet(), basicEstate.getName()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(stringBuilder.toString())) {
            stringBuilder.append("委估对象");
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    stringBuilder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        stringBuilder.append(";");
                    } else {
                        stringBuilder.append(",");
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 委托人地址
     *
     * @return
     */
    public String getPrincipalAddress() {
        String str = getProjectInfo().getConsignorVo().getCsAddress();
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return "";
        }
    }

    /**
     * 委托人法定代表人
     *
     * @return
     */
    public String getPrincipalLegalRepresentative() {
        String str = getProjectInfo().getConsignorVo().getCsLegalRepresentative();
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return "";
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
     * 大写金额
     *
     * @return
     */
    public String getCapitalizationAmount() {
        String price = getTotalRealEstatePrice();
        if (NumberUtils.isNumber(price)) {
            return CnNumberUtils.toUppercaseSubstring(price);
        } else {
            return "";
        }
    }

    /**
     * 外聘专家工作概况
     *
     * @return
     */
    public String getExpertWorkOverview() {
        String s = "茅以升1916年毕业于西南交通大学（时称交通部唐山工业专门学校英文名称Tangshan Engineering College），1917年获美国康乃尔大学硕士学位，1919年获美国卡耐基理工学院（先卡耐基梅隆大学）博士学位 [1]  ，回国后历任交通大学唐山工学院教授、国立东南大学（1928年更名为国立中央大学，1949年更名为南京大学）教授、工科主任、国立河海工科大学校长、交通部唐山大学校长（今西南交通大学）、北洋工学院院长、江苏省水利厅厅长、钱塘江大桥工程处处长、交通大学唐山工学院代院长、院长、中国桥梁公司总经理、北洋大学校长、中国/北方交通大学（时含今西南交通大学和今北京交通大学）校长、铁道科学研究院院长等职。1955年选聘为中国科学院院士（学部委员）。";
        return s;
    }

    /**
     * 共有权情况
     *
     * @return
     * @throws Exception
     */
    public String getCo_ownership() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(getProjectId());
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCertList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            List<DeclareRecord> declareRecords = declareRecordList.stream().filter(declareRecord -> {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(declareRecords)) {
                declareRecords.stream().forEach(declareRecord -> {
                    if (declareRecord.getDataTableId() != null) {
                        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                        if (declareRealtyRealEstateCert != null) {
                            declareRealtyRealEstateCertList.add(declareRealtyRealEstateCert);
                        }
                    }
                });
            }
        }
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertList)) {
            declareRealtyRealEstateCertList.stream().forEach(declareRealtyRealEstateCert -> {
                if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getPublicSituation())) {
                    stringSet.add(declareRealtyRealEstateCert.getPublicSituation());
                }
            });
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 抵押价值总金额
     *
     * @return
     */
    public String getTotalAmountMortgageValue() {
        /**
         * 慢揾英雄泪，相离处世家 ....
         * 没缘法转眼分离栅
         */
        StringBuilder builder = new StringBuilder(24);
        Set<BigDecimal> bigDecimalSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setProjectPhaseId(projectPhase.getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(projectPlanDetails.getId());
                        if (schemeReimbursement != null) {
                            if (schemeReimbursement.getMortgageTotalPrice() != null) {
                                bigDecimalSet.add(schemeReimbursement.getMortgageTotalPrice());
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(bigDecimalSet)) {
            List<BigDecimal> bigDecimalList = Lists.newArrayList(bigDecimalSet);
            for (int i = 0; i < bigDecimalList.size(); i++) {
                builder.append(bigDecimalList.get(i).toString());
                if (i != bigDecimalList.size() - 1) {
                    builder.append(",");
                }
            }
        } else {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 抵押价值总金额大写
     *
     * @return
     */
    public String getTotalAmountMortgageValueCapitalization() {
        StringBuilder builder = new StringBuilder(24);
        Set<BigDecimal> bigDecimalSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setProjectPhaseId(projectPhase.getId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(projectPlanDetails.getId());
                        if (schemeReimbursement != null) {
                            if (schemeReimbursement.getMortgageTotalPrice() != null) {
                                bigDecimalSet.add(schemeReimbursement.getMortgageTotalPrice());
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(bigDecimalSet)) {
            List<BigDecimal> bigDecimalList = Lists.newArrayList(bigDecimalSet);
            for (int i = 0; i < bigDecimalList.size(); i++) {
                builder.append(CnNumberUtils.toUppercaseSubstring(bigDecimalList.get(i).toString()));
                if (i != bigDecimalList.size() - 1) {
                    builder.append(",");
                }
            }
        } else {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 估价项目名称
     *
     * @return
     */
    public String getValuationProjectName() throws Exception {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            ProjectPhase projectPhaseScene = projectPhases.get(0);
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            ProjectPlanDetails query = new ProjectPlanDetails();
            query.setProjectId(getProjectId());
            query.setProjectPhaseId(projectPhaseScene.getId());
            query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
            List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(0);
                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                builder.append(generateBaseExamineService.getEstate().getName());
            }
            if (StringUtils.isNotBlank(schemeJudgeObject.getSeat())) {
                builder.append(schemeJudgeObject.getSeat()).append(";");
            }
            if (schemeJudgeObject.getSetUse() != null) {
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                if (dataSetUseField != null) {
                    builder.append(dataSetUseField.getName()).append(";");
                }
            }
            if (getSchemeAreaGroup().getEntrustPurpose() != null) {
                builder.append(baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose())).append(";");
            }
            if (schemeJudgeObjectList.size() > 1) {
                builder.append("等");
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 证载用途总括
     *
     * @return
     * @throws Exception
     */
    public String getSummaryCertificateUses() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            if (StringUtils.isNotBlank(schemeJudgeObjectList.get(0).getCertUse())) {
                builder.append(schemeJudgeObjectList.get(0).getCertUse());
            }
            if (schemeJudgeObjectList.size() > 1) {
                builder.append("等");
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
    }

    /**
     * 证载用途分述
     *
     * @return
     * @throws Exception
     */
    public String getSeparationCertificateUses() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (StringUtils.isNotBlank(schemeJudgeObjectList.get(i).getCertUse())) {
                    builder.append(schemeJudgeObjectList.get(i).getName()).append(schemeJudgeObjectList.get(i).getCertUse());
                    if (i == schemeJudgeObjectList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        }
        return builder.toString();
    }


    /**
     * 分类评估单价
     *
     * @return
     */
    public String getEvaluationPriceCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPrice().toString())) {
                        stringSet.add(String.format("%s:%s", schemeJudgeObjectList.get(i).getName(), schemeJudgeObjectList.get(i).getPrice().toString()));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                builder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 功能描述: 估价对象的单价
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 17:15
     */
    public String getUnitPriceValuator() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                    stringBuilder.append(schemeJudgeObject.getName()).append("的单价=").append(getEvaluationExpression());
                    for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                        if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdCompare.getName())) {
                            if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                                stringBuilder.append("+");
                            }
                        }
                        if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdIncome.getName())) {
                            stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                        }
                    }
                }
            }
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 分类评估面积
     *
     * @return
     */
    public String getEvaluationAreaCateGoryOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getEvaluationArea().toString())) {
                        stringSet.add(String.format("%s:%s", schemeJudgeObjectList.get(i).getName(), schemeJudgeObjectList.get(i).getEvaluationArea().toString()));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                builder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 估价对象的总价
     *
     * @return
     */
    public String getTotalValueValuationObject() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BigDecimal temp = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getEvaluationArea() != null && schemeJudgeObject.getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObject.getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObject.getPrice().toString())) {
                        BigDecimal bigDecimalA = new BigDecimal(schemeJudgeObject.getEvaluationArea().toString());
                        BigDecimal bigDecimalB = new BigDecimal(schemeJudgeObject.getPrice().toString());
                        BigDecimal bigDecimal = bigDecimalA.multiply(bigDecimalB);
                        temp = bigDecimal.add(temp);
                    }
                }
            }
        }
        return temp.toString();
    }


    /**
     * 分类评估总价
     *
     * @return
     */
    public String getEvaluationPriceCateGoryTotalOne() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (schemeJudgeObjectList.get(i).getEvaluationArea() != null && schemeJudgeObjectList.get(i).getPrice() != null) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getEvaluationArea().toString()) && NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPrice().toString())) {
                        BigDecimal bigDecimalA = new BigDecimal(schemeJudgeObjectList.get(i).getEvaluationArea().toString());
                        BigDecimal bigDecimalB = new BigDecimal(schemeJudgeObjectList.get(i).getPrice().toString());
                        BigDecimal bigDecimal = bigDecimalA.multiply(bigDecimalB);
                        builder.append(schemeJudgeObjectList.get(i).getName()).append(":");
                        builder.append(bigDecimal.toString());
                        if (i == schemeJudgeObjectList.size() - 1) {
                            builder.append(";");
                        } else {
                            builder.append(",");
                        }
                    }
                }
            }
        }
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
     * 单价调整表
     *
     * @return
     * @throws Exception
     */
    public String getUnitPriceAdjustmentTable() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        List<SchemeJudgeObjectVo> schemeJudgeObjectList = getSchemeJudgeObjectList().stream().map(schemeJudgeObject -> {
            return schemeJudgeObjectService.getSchemeJudgeObjectVo(schemeJudgeObject);
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Table table = builder.startTable();
            for (int i = 0; i < 5; i++) {
                builder.insertCell();
                if (i == 0) builder.writeln("权证号");
                if (i == 1) builder.writeln("楼层");
                if (i == 2) builder.writeln("房号");
                if (i == 3) builder.writeln("价格");
                if (i == 4) builder.writeln("因素");
            }
            builder.endRow();
            for (SchemeJudgeObjectVo schemeJudgeObject : schemeJudgeObjectList) {
                for (int i = 0; i < 5; i++) {
                    builder.insertCell();
                    if (i == 0) {
                        if (StringUtils.isNotBlank(schemeJudgeObject.getName())) {
                            builder.writeln(schemeJudgeObject.getName());
                        }
                    }
                    if (i == 1) {
                        if (StringUtils.isNotBlank(schemeJudgeObject.getFloor())) {
                            builder.writeln(schemeJudgeObject.getFloor());
                        }
                    }
                    if (i == 2) {
                        if (StringUtils.isNotBlank(schemeJudgeObject.getRoomNumber())) {
                            builder.writeln(schemeJudgeObject.getRoomNumber());
                        }
                    }
                    if (i == 3) {
                        if (schemeJudgeObject.getPrice() != null) {
                            builder.writeln(schemeJudgeObject.getPrice().toString());
                        }
                    }
                    if (i == 4) {
                        if (StringUtils.isNotBlank(schemeJudgeObject.getCoefficient())) {
                            builder.writeln(schemeJudgeObject.getCoefficient());
                        }
                    }
                }
                builder.endRow();
            }
            builder.endTable();
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 分类评估方法结果
     *
     * @return
     */
    public String getEvaluationMethodResult() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                int row = 0;
                builder.writeln(schemeJudgeObject.getName());
                Table table = builder.startTable();
                for (int i = 0; i < 3; i++) {
                    builder.insertCell();
                    if (i == 0) builder.writeln("方法名称");
                    if (i == 1) builder.writeln("试算价格");
                    if (i == 2) builder.writeln("权重");
                }
                builder.endRow();
                row++;
                List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                    for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                        for (int i = 0; i < 3; i++) {
                            builder.insertCell();
                            if (i == 0) {
                                if (StringUtils.isNotBlank(schemeSurePriceItem.getMethodName())) {
                                    builder.writeln(schemeSurePriceItem.getMethodName());
                                }
                            }
                            if (i == 1) {
                                if (schemeSurePriceItem.getTrialPrice() != null) {
                                    builder.writeln(schemeSurePriceItem.getTrialPrice().toString());
                                }
                            }
                            if (i == 2) {
                                if (schemeSurePriceItem.getWeight() != null) {
                                    builder.writeln(schemeSurePriceItem.getWeight().toString());
                                }
                            }
                        }
                        builder.endRow();
                        row++;
                    }
                }
                SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSchemeSurePriceBySchemeJudgeObjectId(schemeJudgeObject.getId());
                if (schemeSurePrice != null) {
                    for (int i = 0; i < 3; i++) {
                        builder.insertCell();
                        if (StringUtils.isNotBlank(schemeSurePrice.getWeightExplain())) {
                            if (i == 0) {
                                builder.writeln("权重说明");
                            }
                            if (i == 1) {
                                builder.writeln(schemeSurePrice.getWeightExplain());
                            }
                        }
                        mergeCellModelList.add(new MergeCellModel(row, 1, row, 2));
                    }
                    builder.endRow();
                    row++;
                    for (int i = 0; i < 3; i++) {
                        builder.insertCell();
                        if (schemeSurePrice.getPrice() != null) {
                            if (i == 0) {
                                builder.writeln("最终单价");
                            }
                            if (i == 1) {
                                builder.writeln(schemeSurePrice.getPrice().toString());
                            }
                        }
                        mergeCellModelList.add(new MergeCellModel(row, 1, row, 2));
                    }
                    builder.endRow();
                    row++;
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
        doc.save(localPath);
        return localPath;
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
                    SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetailsList.get(0).getId());
                    if (schemeSurePrice != null) {
                        builder.append(schemeSurePrice.getWeightExplain());
                        if (i == schemeJudgeObjectList.size() - 1) {
                            builder.append(";");
                        } else {
                            builder.append(",");
                        }
                    }
                }
            }
        }
        return builder.toString();
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
            return String.format("%s", decimal.toString());
        }
        return errorStr;
    }

    /**
     * 法定优先受偿款
     *
     * @return
     */
    public String getStatutoryOptimumReimbursement() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                builder.append(schemeJudgeObjectList.get(i).getName()).append(schemeReimbursementService.getFullDescription(schemeJudgeObjectList.get(i).getId()));
                if (i == schemeJudgeObjectList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        return builder.toString();
    }

    /**
     * 变现分析表
     *
     * @param title 标题
     * @return
     */
    public String getLiquidationAnalysis(String title) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\" + title + "%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        for (SchemeJudgeObject judgeObject : this.getSchemeJudgeObjectList()) {
            createLiquidationAnalysisTable(builder, judgeObject);
        }
        doc.save(localPath);
        return localPath;
    }

    public void createLiquidationAnalysisTable(DocumentBuilder builder, SchemeJudgeObject judgeObject) throws Exception {
        builder.writeln(judgeObject.getName());
        //表头
        builder.insertCell();
        builder.writeln("物业类型");
        builder.insertCell();
        builder.writeln("税率");
        builder.insertCell();
        builder.writeln("备注");
        builder.insertCell();
        builder.writeln("商业");
        builder.endRow();

        builder.insertCell();
        builder.writeln("面积");
        builder.insertCell();
        builder.insertCell();
        builder.insertCell();
        builder.writeln(judgeObject.getEvaluationArea().toString());
        builder.endRow();

        builder.insertCell();
        builder.writeln("评估价");
        builder.insertCell();
        builder.insertCell();
        builder.insertCell();
        builder.writeln(judgeObject.getPrice().toString());
        builder.endRow();
        SchemeLiquidationAnalysis object = new SchemeLiquidationAnalysis();
        object.setJudgeObjectId(judgeObject.getId());
        SchemeLiquidationAnalysis schemeLiquidationAnalysis = schemeLiquidationAnalysisService.getDataByJudgeObjectId(judgeObject.getId());
        List<SchemeLiquidationAnalysisItem> itemList = schemeLiquidationAnalysisService.getAnalysisItemList(schemeLiquidationAnalysis.getPlanDetailsId());
        for (SchemeLiquidationAnalysisItem item : itemList) {
            builder.insertCell();
            if (!StringUtils.isEmpty(item.getTaxRateName())) {
                builder.writeln(item.getTaxRateName());
            } else {
                builder.writeln("空");
            }
            builder.insertCell();
            if (item.getCalculationMethod() == 1 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                builder.writeln(new BigDecimal(item.getTaxRateValue()).multiply(new BigDecimal("100")).stripTrailingZeros().toString() + "%");
            } else if (item.getCalculationMethod() == 0 && !StringUtils.isEmpty(item.getTaxRateValue())) {
                builder.writeln(item.getTaxRateValue() + "元/㎡");
            } else {
                builder.writeln("空");
            }
            builder.insertCell();
            if (!StringUtils.isEmpty(item.getRemark())) {
                builder.writeln(item.getRemark());
            } else {
                builder.writeln("空");
            }
            builder.insertCell();
            if (!StringUtils.isEmpty(item.getPrice().toString())) {
                builder.writeln(item.getPrice().toString());
            } else {
                builder.writeln("空");
            }
            builder.endRow();
        }
        builder.insertCell();
        builder.writeln("合计费用");
        builder.insertCell();
        builder.insertCell();
        builder.insertCell();
        builder.writeln(schemeLiquidationAnalysis.getTotal().toString());
        builder.endRow();
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
     * 评估方法分述
     *
     * @return
     */
    public String getAssessmentMethods() {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        stringSet.add(String.format("%s", schemeJudgeFunction.getName()));
                    }
                }
            }
        }
        List<String> stringList = Lists.newArrayList(stringSet);
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (int i = 0; i < stringList.size(); i++) {
                builder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 估价对象评估方法
     *
     * @return
     * @throws Exception
     */
    public String getEvaluationMethodValuationObject() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        stringSet.add(String.format("%s%s", schemeJudgeObject.getName(), schemeJudgeFunction.getName()));
                    }
                }
            }
        }
        List<String> stringList = Lists.newArrayList(stringSet);
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (int i = 0; i < stringList.size(); i++) {
                builder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 功能描述: 技术思路通用模板
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 15:20
     */
    public String getGeneralTemplateTechnicalIdea() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(schemeJudgeObject.getName()).append("");
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (StringUtils.isNotBlank(schemeJudgeFunction.getThinking()) && schemeJudgeFunction.getBisApplicable()) {
                            builder.append(schemeJudgeFunction.getName()).append("思路:");
                            builder.append(schemeJudgeFunction.getThinking()).append(";");
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        List<String> stringList = Lists.newArrayList(stringSet);
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (int i = 0; i < stringList.size(); i++) {
                builder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 估价对象选择估价方法
     *
     * @return
     */
    public String getSelectionValuationMethod() {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.append(schemeJudgeObject.getName()).append("");
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (StringUtils.isNotBlank(schemeJudgeFunction.getApplicableReason())) {
                            builder.append(schemeJudgeFunction.getName()).append("估价方法:");
                            builder.append(schemeJudgeFunction.getApplicableReason()).append(";");
                        }
                    }
                    stringSet.add(builder.toString());
                    builder.delete(0, builder.toString().length());
                }
            }
        }
        List<String> stringList = Lists.newArrayList(stringSet);
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (int i = 0; i < stringList.size(); i++) {
                builder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    builder.append(";");
                } else {
                    builder.append(",");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 评估方法总括
     *
     * @return
     */
    public String getSummaryEvaluationMethod() {
        StringBuilder builder = new StringBuilder(128);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                    builder.append(schemeJudgeFunction.getName());
                }
            }
            if (schemeJudgeFunctionList.size() > 1) {
                builder.append("等");
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return errorStr;
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
            this.statementPurposeEntrustment = getSchemeAreaGroup().getRemarkEntrustPurpose();
        }
        if (StringUtils.isNotBlank(this.statementPurposeEntrustment)) {
            return statementPurposeEntrustment;
        } else {
            return "";
        }
    }

    //委托目的
    public String getDelegatePurpose() {
        String str = baseDataDicService.getNameById(getSchemeAreaGroup().getEntrustPurpose());
        if (StringUtils.isNotBlank(str)) {
            return str;
        }
        return "";
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
        return "";
    }

    //价值类型描述
    public String getValueTypeDesc() {
        return getSchemeAreaGroup().getValueDefinitionDesc();
    }


    /**
     * 注册房产估价师
     *
     * @param str
     * @return
     */
    public String getRegisteredRealEstateValuer(String str) {
        String[] strings = str.split(",");
        StringBuilder builder = new StringBuilder();
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccountName())) {
                    builder.append(dataQualificationVo.getUserAccountName()).append(" ");
                }
            }
        }
        if (StringUtils.isEmpty(builder.toString())) {
            return "";
        }
        return builder.toString();
    }

    /**
     * 注册房产估价师 编号
     *
     * @param str
     * @return
     * @throws Exception
     */
    public String getRegistrationNumber(String str) throws Exception {
        StringBuilder builder = new StringBuilder(24);
        String[] strings = str.split(",");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                    for (String account : dataQualificationVo.getUserAccount().split(",")) {
                        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                            builder.append(adPersonalQualificationDtoList.get(0).getCertificateNo()).append(" ");
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return "";
    }

    /**
     * 房地产估价机构信息
     *
     * @return
     * @throws Exception
     */
    public String getXIEHE_organizationInfo(AdCompanyQualificationDto qualificationDto) throws Exception {
        StringBuilder builder = new StringBuilder(24);
        builder.append("机构名称:").append(qualificationDto.getOrganizationName()).append(";");
        builder.append("住所:").append(qualificationDto.getOrganizationAddress()).append(";");
        builder.append("法定代表人:").append(qualificationDto.getLegalRepresentative()).append(";");
        builder.append("工商注册号:").append(qualificationDto.getRegisteredNo()).append(";");
        builder.append("资质等级:").append(qualificationDto.getOrganizationRank()).append(";");
        builder.append("资质证书编号:").append(qualificationDto.getCertificateNo()).append(";");
        builder.append("资质证书有效期:").append(qualificationDto.getCertificateEffectiveDate()).append(";");
        builder.append("经营范围:").append("评估房产").append("");
        return builder.toString();
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
     * 经营范围
     *
     * @param qualificationDto
     * @return
     * @throws Exception
     */
    public String getBusinessScope(AdCompanyQualificationDto qualificationDto) throws Exception {
        return "评估房地产";
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
     * 现场查勘人员
     *
     * @return
     * @throws Exception
     */
    public String getSurveyExamineCreate() throws Exception {
        List<ProjectPhaseVo> projectPhaseVoList = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            //查勘通过
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            //案例同样通过
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<ProjectPlanDetails> projectPlanDetailsList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(projectPhaseVoList)) {
            projectPhaseVoList.stream().forEach(projectPhaseVo -> {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setProjectPhaseId(projectPhaseVo.getId());
                List<ProjectPlanDetails> projectPlanDetailss = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailss)) {
                    projectPlanDetailsList.addAll(projectPlanDetailss);
                }
            });
        }
        Set<String> stringSet = Sets.newHashSet();
        StringBuilder builder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
            projectPlanDetailsList.stream().forEach(projectPlanDetails -> stringSet.add(projectPlanDetails.getExecuteUserAccount()));
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(publicService.getUserNameByAccount(stringList.get(i)));
                    if (i == stringList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        }
        return builder.toString();
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
     * 评估依据假设原则
     *
     * @param schemeSupportTypeEnum
     * @return
     * @throws Exception
     */
    public String getPrincipleBasisHypothesis(SchemeSupportTypeEnum schemeSupportTypeEnum) throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        List<SchemeSupportInfo> schemeSupportInfoList = schemeSupportInfoService.getSupportInfoListByAreaId(getAreaId(), schemeSupportTypeEnum);
        if (CollectionUtils.isNotEmpty(schemeSupportInfoList)) {
            int i = 1;
            for (SchemeSupportInfo schemeSupportInfo : schemeSupportInfoList) {
                if (StringUtils.isNotBlank(schemeSupportInfo.getContent())) {
                    builder.insertHtml(String.format("<h5>%s.%s</h5>", i, schemeSupportInfo.getName()));
                    builder.writeln(schemeSupportInfo.getContent());
                    i++;
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 报告分析
     *
     * @return
     */
    public String getReportAnalysis(String type) throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(type);
        List<CompileReportDetail> compileReportDetailList = compileReportService.getCompileReportDetailList(getAreaId(), baseDataDic.getId());
        if (CollectionUtils.isNotEmpty(compileReportDetailList)) {
            int i = 1;
            for (CompileReportDetail compileReportDetail : compileReportDetailList) {
                if (StringUtils.isNotBlank(compileReportDetail.getContent())) {
                    builder.insertHtml(String.format("<h5>%s.%s</h5>", i, compileReportDetail.getName()));
                    builder.writeln(compileReportDetail.getContent());
                    i++;
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 协助工作人员
     *
     * @return
     */
    public String getAssistanceStaff(String str) {
        String[] ids = str.split(",");
        List<ProjectPhaseVo> projectPhaseVos = projectPhaseService.queryProjectPhaseByCategory(getProjectInfo().getProjectTypeId(),
                getProjectInfo().getProjectCategoryId(), null).stream().filter(projectPhaseVo -> {
            if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> stringSet = Sets.newHashSet();
        for (String id : ids) {
            if (CollectionUtils.isNotEmpty(projectPhaseVos)) {
                projectPhaseVos.stream().forEach(projectPhaseScene -> {
                    DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                                if (StringUtils.isNotBlank(projectPlanDetails.getExecuteUserAccount())) {
                                    if (dataQualificationVo != null) {
                                        if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                                            for (String account : dataQualificationVo.getUserAccount().split(",")) {
                                                if (Objects.equal(account, projectPlanDetails.getExecuteUserAccount())) {

                                                } else {
                                                    stringSet.add(publicService.getUserNameByAccount(projectPlanDetails.getExecuteUserAccount()));
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
        StringBuilder builder = new StringBuilder(24);
        if (CollectionUtils.isEmpty(stringSet)) {
            builder.append(" ");
        } else {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * get 价值内涵
     *
     * @return
     */
    public String getValueImplication() {
        List<String> list = JSON.parseArray(getSchemeAreaGroup().getValueConnotation(), String.class);
        if (CollectionUtils.isEmpty(list)) return null;
        List<Integer> integerList = FormatUtils.ListStringToListInteger(list);
        String result = new String();
        for (String s : list) {
            result += baseDataDicService.getNameById(s) + ",";
        }
        return StringUtils.strip(result, ",");
    }

    //价值内涵描述
    public String getValueImplicationDesc() {
        return getSchemeAreaGroup().getValueConnotationDesc();
    }

    /**
     * 权利人
     *
     * @return
     */
    public String getPowerPerson() {
        StringBuilder builder = new StringBuilder(24);
        try {
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                    if (schemeJudgeObjectList.get(i).getDeclareRecordId() != null) {
                        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObjectList.get(i).getDeclareRecordId());
                        if (declareRecord != null && StringUtils.isNotBlank(declareRecord.getOwnership())) {
                            builder.append(declareRecord.getOwnership());
                            if (schemeJudgeObjectList.size() - 1 == i) {
                                builder.append(";");
                            } else {
                                builder.append(",");
                            }
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error("权利人拼接异常!");
        }
        return builder.toString();
    }

    /**
     * 区位(2019-01-28 修改之后)
     *
     * @return
     * @throws Exception
     */
    public String getLocation_() throws Exception {
        StringBuilder builder = new StringBuilder(1024);
        try {
            SchemeAreaGroup schemeAreaGroup = this.getSchemeAreaGroup();
            builder.append(schemeAreaGroup.getAreaName());
            List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
                builder.append(schemeJudgeObject.getSeat());
                if (schemeJudgeObjectList.size() > 1) {
                    builder.append("等");
                }
            }
        } catch (Exception e1) {
            logger.error("(区位)拼接异常!");
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            return builder.toString();
        }
        return "";
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
            return "";
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
            SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(0);
            if (schemeJudgeObject.getSetUse() != null) {
                DataSetUseField dataSetUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
                if (dataSetUseField != null) {
                    stringBuilder.append(dataSetUseField.getName());
                }
            }
            if (schemeJudgeObjectList.size() > 1) {
                stringBuilder.append("等");
            }
            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                return stringBuilder.toString();
            }
        }
        return "";
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
     * 实际用途
     *
     * @return
     */
    public String getPracticalUse() {
        StringBuilder builder = new StringBuilder();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (StringUtils.isNotBlank(schemeJudgeObjectList.get(i).getPracticalUse())) {
                    if (NumberUtils.isNumber(schemeJudgeObjectList.get(i).getPracticalUse())) {
                        builder.append(baseDataDicService.getNameById(schemeJudgeObjectList.get(i).getPracticalUse()));
                    } else {
                        builder.append(schemeJudgeObjectList.get(i).getPracticalUse());
                    }
                    if (i != schemeJudgeObjectList.size() - 1) {
                        builder.append(",");
                    } else {
                        builder.append(";");
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 建筑结构类别
     *
     * @return
     * @throws Exception
     */
    public String getBuildingStructureCategory() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory() != null) {
                                    stringSet.add(baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory()));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        } else {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 税费负担
     *
     * @return
     * @throws Exception
     */
    public String getTaxBurden() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicTrading().getTaxBurden() != null) {
                                    stringSet.add(baseDataDicService.getNameById(generateBaseExamineService.getBasicTrading().getTaxBurden()));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        } else {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * 付款方式
     *
     * @return
     * @throws Exception
     */
    public String getPaymentMethod() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList) && CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                if (generateBaseExamineService.getBasicTrading().getPaymentMethod() != null) {
                                    stringSet.add(baseDataDicService.getNameById(generateBaseExamineService.getBasicTrading().getPaymentMethod()));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        } else {
            builder.append(" ");
        }
        return builder.toString();
    }


    /**
     * 使用权类型
     *
     * @return
     */
    public String getUseRightType() {
        String value = getPracticalUse();
        if (StringUtils.isNotBlank(value)) {
            return value;
        } else {
            return "";
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
        return "";
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
        return "";
    }

    /**
     * 土地使用管制
     *
     * @return
     */
    public String getLandUseControl() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder stringBuilder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (StringUtils.isNotBlank(schemeJudgeObjectList.get(i).getCertUse())) {
                    stringBuilder.append(schemeJudgeObjectList.get(i).getName());
                    stringBuilder.append(schemeJudgeObjectList.get(i).getCertUse());
                    if (i != schemeJudgeObjectList.size() - 1) {
                        stringBuilder.append(",");
                    } else {
                        stringBuilder.append(";");
                    }
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 出租或占用情况
     *
     * @return
     */
    public String getRentalPossessionDesc() {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        StringBuilder stringBuilder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                if (StringUtils.isNotBlank(schemeJudgeObjectList.get(i).getRentalPossessionDesc())) {
                    stringBuilder.append(schemeJudgeObjectList.get(i).getName());
                    stringBuilder.append(schemeJudgeObjectList.get(i).getRentalPossessionDesc());
                    if (i != schemeJudgeObjectList.size() - 1) {
                        stringBuilder.append(",");
                    } else {
                        stringBuilder.append(";");
                    }
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
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
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setProjectPhaseId(projectPhase.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                                ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                                List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                    for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                        stringBuilder.append(schemeJudgeObject.getName()).append(":");
                                        stringBuilder.append(String.format("%s；", baseDataDicService.getNameById(inventoryRight.getCategory())));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        if (StringUtils.isNotBlank(stringBuilder.toString())) {
            return stringBuilder.toString();
        }
        return "";
    }

    /**
     * 他权有无租赁权
     *
     * @return
     * @throws Exception
     */
    public String getHisRightHasLease() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        final String temp = "他权有无租赁权:";
        stringBuilder.append(temp);
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE_HOUSE_LEASEHOLD);
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                inner:
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (Objects.equal(inventoryRight.getCategory(), baseDataDic.getId())) {
                                        stringBuilder.append("有");
                                        break inner;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Objects.equal(stringBuilder.toString(), temp)) {
            stringBuilder.append("无");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * 功能描述: 有无他项权
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 15:10
     */
    public String getThereAnyOtherRight() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    boolean flag = false;
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (StringUtils.isNotBlank(inventoryRight.getNumber())) {
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }
                    if (flag) {
                        stringSet.add(String.format("%s%s", schemeJudgeObject.getName(), ":有他项权"));
                    } else {
                        stringSet.add(String.format("%s%s", schemeJudgeObject.getName(), ":无他项权"));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 他权类别
     *
     * @return
     * @throws Exception
     */
    public String getHisRightType() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (inventoryRight.getCategory() != null) {
                                        stringSet.add(baseDataDicService.getNameById(inventoryRight.getCategory()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 他权其它
     *
     * @return
     * @throws Exception
     */
    public String getRightOther() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsList.get(j);
                            List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.surveyAssetInventoryRights(projectPlanDetails.getId());
                            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
                                for (SurveyAssetInventoryRight inventoryRight : surveyAssetInventoryRightList) {
                                    if (inventoryRight.getCategory() != null) {
                                        stringSet.add(baseDataDicService.getNameById(inventoryRight.getCategory()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                if (Objects.equal("其它", stringList.get(i))) {
                    stringBuilder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        stringBuilder.append(";");
                    } else {
                        stringBuilder.append(",");
                    }
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 功能描述: 资产清查实际地址
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:02
     */
    public String getActualAddressAssetInventory() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                List<SurveyAssetInventoryContent> surveyAssetInventoryContentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentList)) {
                                    for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentList) {
                                        if (Objects.equal("不一致", surveyAssetInventoryContent.getAreConsistent())) {
                                            stringSet.add(surveyAssetInventoryContent.getActual());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 功能描述: 资产清查证明人
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:10
     */
    public String getCertificateAssetInventory() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                List<SurveyAssetInventoryContent> surveyAssetInventoryContentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentList)) {
                                    for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentList) {
                                        if (Objects.equal("不一致", surveyAssetInventoryContent.getAreConsistent())) {
                                            stringSet.add(surveyAssetInventoryContent.getVoucher());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 功能描述: 资产清查确认一致
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:16
     */
    public String getAssetInventoryConfirmConsistency() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                List<SurveyAssetInventoryContent> surveyAssetInventoryContentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentList)) {
                                    for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentList) {
                                        if (Objects.equal("不一致", surveyAssetInventoryContent.getAreConsistent())) {
                                            stringSet.add(surveyAssetInventoryContent.getSureConsistent());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 功能描述: 资产清查一致说明
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 14:22
     */
    public String getAssetInventoryAgreement() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.ASSET_INVENTORY, getProjectInfo().getProjectCategoryId());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (projectPhase != null) {
            if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setProjectPhaseId(projectPhase.getId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (int j = 0; j < projectPlanDetailsList.size(); j++) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                List<SurveyAssetInventoryContent> surveyAssetInventoryContentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(projectPlanDetails.getId());
                                if (CollectionUtils.isNotEmpty(surveyAssetInventoryContentList)) {
                                    for (SurveyAssetInventoryContent surveyAssetInventoryContent : surveyAssetInventoryContentList) {
                                        if (Objects.equal("不一致", surveyAssetInventoryContent.getAreConsistent())) {
                                            stringSet.add(surveyAssetInventoryContent.getDifferenceReason());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 担保物权设立情况
     *
     * @return
     * @throws Exception
     */
    public String getCollateralFound() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (StringUtils.isNotBlank(schemeJudgeObject.getCollateralFound())) {
                    stringSet.add(schemeJudgeObject.getCollateralFound());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 最佳利用描述
     *
     * @return
     * @throws Exception
     */
    public String getOptimumUtilizationDescription() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                if (schemeJudgeObject.getBestUse() != null) {
                    stringSet.add(baseDataDicService.getNameById(schemeJudgeObject.getBestUse()));
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 法定优先受偿款总金额
     *
     * @return
     * @throws Exception
     */
    public String getStatutoryPriorityAmountTotal() throws Exception {
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, Integer> judgeObjectIntegerMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        query.setProjectPhaseId(projectPhase.getId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                            }
                        }
                    }
                }
            }
        }
        java.math.BigDecimal bigDecimal = new BigDecimal(0);
        if (!judgeObjectIntegerMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, Integer> integerEntry : judgeObjectIntegerMap.entrySet()) {
                SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(integerEntry.getValue());
                if (schemeReimbursement != null && schemeReimbursement.getKnowTotalPrice() != null) {
                    bigDecimal = bigDecimal.add(schemeReimbursement.getKnowTotalPrice());
                }
            }
        }
        return bigDecimal.toString();
    }


    /**
     * 法定优先受偿款金额
     *
     * @return
     * @throws Exception
     */
    public String getStatutoryPriorityAmount() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(24);
        Set<String> stringSet = Sets.newHashSet();
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, Integer> judgeObjectIntegerMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        query.setProjectPhaseId(projectPhase.getId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                            }
                        }
                    }
                }
            }
        }
        if (!judgeObjectIntegerMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, Integer> integerEntry : judgeObjectIntegerMap.entrySet()) {
                SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(integerEntry.getValue());
                if (schemeReimbursement != null && schemeReimbursement.getKnowTotalPrice() != null) {
                    stringSet.add(String.format("%s:%s、", integerEntry.getKey().getName(), schemeReimbursement.getKnowTotalPrice().toString()));
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringSet)) {
            List<String> stringList = Lists.newArrayList(stringSet);
            for (int i = 0; i < stringList.size(); i++) {
                stringBuilder.append(stringList.get(i));
                if (i == stringList.size() - 1) {
                    stringBuilder.append(";");
                } else {
                    stringBuilder.append(",");
                }
            }
        } else {
            stringBuilder.append("0");
        }
        return stringBuilder.toString();
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
        return "";
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
     * 功能描述: 估价对象市场价值的确定
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/26 16:25
     */
    public String getDeterminationMarketValueValuationObject() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    builder.writeln(String.format("%s:", schemeJudgeObject.getName()));
                    StringBuilder stringBuilder = new StringBuilder(24);
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("与");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("测算结果相近，通过对该区域的调查，考虑估价对象在该区域内的具体位置等因素，");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("的试算结果与");
                    stringBuilder.append(schemeJudgeFunctionList.stream().filter(schemeJudgeFunction -> Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), schemeJudgeFunction.getName())).findFirst().get().getName());
                    stringBuilder.append("试算结果均能反映估价对象市场价值，");
                    //权重说明
                    ProjectPlanDetails query = new ProjectPlanDetails();
                    query.setProjectId(getProjectId());
                    query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                            SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
                            if (schemeSurePrice != null) {
                                stringBuilder.append(schemeSurePrice.getWeightExplain());
                            }
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());

                    stringBuilder.append(schemeJudgeObject.getName()).append("的单价=").append(getEvaluationExpression());
                    List<SchemeSurePriceItem> schemeSurePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(schemeJudgeObject.getId(), false);
                    if (CollectionUtils.isNotEmpty(schemeSurePriceItemList)) {
                        for (SchemeSurePriceItem schemeSurePriceItem : schemeSurePriceItemList) {
                            if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdCompare.getName())) {
                                if (schemeSurePriceItem.getTrialPrice() != null && schemeSurePriceItem.getWeight() != null) {
                                    stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                                    stringBuilder.append("+");
                                }
                            }
                            if (Objects.equal(schemeSurePriceItem.getMethodName(), CalculationMethodNameEnum.MdIncome.getName())) {
                                stringBuilder.append(schemeSurePriceItem.getTrialPrice().toString()).append("*").append(schemeSurePriceItem.getWeight().toString());
                            }
                        }
                    }
                    builder.writeln(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.toString().length());
                }
            }
        }
        doc.save(localPath);
        return localPath;
    }

    /**
     * 相关参数选取与应用
     *
     * @return
     * @throws Exception
     */
    public String getSelectionApplicationParameters() throws Exception {
        StringBuilder builder = new StringBuilder(24);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        //市场比较法
        Set<SchemeJudgeObject> mdCompare = Sets.newHashSet();
        //收益法
        Set<SchemeJudgeObject> mdIncome = Sets.newHashSet();
        //假设开发法
        Set<SchemeJudgeObject> mdDevelopment = Sets.newHashSet();
        //成本法
        Set<SchemeJudgeObject> mdCost = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SchemeJudgeFunction> schemeJudgeFunctionList = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(schemeJudgeFunctionList)) {
                    for (SchemeJudgeFunction schemeJudgeFunction : schemeJudgeFunctionList) {
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdCompare.getName())) {
                            mdCompare.add(schemeJudgeObject);
                        }
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdIncome.getName())) {
                            mdIncome.add(schemeJudgeObject);
                        }
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdDevelopment.getName())) {
                            mdDevelopment.add(schemeJudgeObject);
                        }
                        if (Objects.equal(schemeJudgeFunction.getName(), CalculationMethodNameEnum.MdCost.getName())) {
                            mdCost.add(schemeJudgeObject);
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(mdCompare)) {
            DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
                if (Objects.equal(CalculationMethodNameEnum.MdCompare.getName(), dataEvaluation.getName())) {
                    return true;
                }
                return false;
            }).findFirst().get();
            if (dataEvaluationMethod != null) {
                List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethod.getMethod());
                if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                    builder.append(dataEvaluationMethod.getName()).append(":");
                    mdCompare.stream().forEach(schemeJudgeObject -> builder.append(schemeJudgeObject.getName()));
                    builder.append(";").append("公式:").append(dataMethodFormulaList.get(0).getFormula()).append(". ");
                }
            }
        }
        if (CollectionUtils.isNotEmpty(mdIncome)) {
            DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(dataEvaluation -> {
                if (Objects.equal(CalculationMethodNameEnum.MdIncome.getName(), dataEvaluation.getName())) {
                    return true;
                }
                return false;
            }).findFirst().get();
            if (dataEvaluationMethod != null) {
                List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaService.getDataMethodFormulaList(dataEvaluationMethod.getMethod());
                if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
                    builder.append(dataEvaluationMethod.getName()).append(":");
                    mdIncome.stream().forEach(schemeJudgeObject -> builder.append(schemeJudgeObject.getName()));
                    builder.append(";").append("公式:").append(dataMethodFormulaList.get(0).getFormula()).append(". ");
                }
            }
        }
        return builder.toString();
    }

    /**
     * 估价对象权属状况表
     *
     * @return
     * @throws Exception
     */
    public String getEquityStatusValuatedObjects() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Map<SchemeJudgeObject, DeclareRealtyRealEstateCertVo> objectDeclareRealtyRealEstateCertVoMap = Maps.newHashMap();
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList().stream().filter(schemeJudgeObject -> {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                return false;
            }
            if (schemeJudgeObject.getDeclareRecordId().equals(0)) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                if (declareRecord != null) {
                    if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
                        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                        if (declareRealtyRealEstateCert != null) {
                            objectDeclareRealtyRealEstateCertVoMap.put(schemeJudgeObject, declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertVo(declareRealtyRealEstateCert));
                        }
                    }
                }
            }
        }
        if (!objectDeclareRealtyRealEstateCertVoMap.isEmpty()) {
            final int colMax = 11;
            Table table = builder.startTable();
            for (int j = 0; j < colMax; j++) {
                builder.insertCell();
                if (j == 0) builder.writeln("估价对象");
                if (j == 1) builder.writeln("不动产权证号");
                if (j == 2) builder.writeln("权利人");
                if (j == 3) builder.writeln("共有情况");
                if (j == 4) builder.writeln("坐落");
                if (j == 5) builder.writeln("用途");
                if (j == 6) builder.writeln("土地终止日期");
                if (j == 7) builder.writeln("房屋总层数");
                if (j == 8) builder.writeln("所在层数");
                if (j == 9) builder.writeln("分摊土地使用权面积");
                if (j == 10) builder.writeln("建筑面积（㎡）");
            }
            builder.endRow();
            for (Map.Entry<SchemeJudgeObject, DeclareRealtyRealEstateCertVo> realEstateCertVoEntry : objectDeclareRealtyRealEstateCertVoMap.entrySet()) {
                for (int j = 0; j < colMax; j++) {
                    builder.insertCell();
                    if (j == 0) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getKey().getNumber())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getKey().getNumber()) ? realEstateCertVoEntry.getKey().getNumber() : ""));
                        }
                    }
                    if (j == 1) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getCertName())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getCertName()) ? realEstateCertVoEntry.getValue().getCertName() : ""));
                        }
                    }
                    if (j == 2) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getOwnership())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getOwnership()) ? realEstateCertVoEntry.getValue().getOwnership() : ""));
                        }
                    }
                    if (j == 3) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPublicSituation())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPublicSituation()) ? realEstateCertVoEntry.getValue().getPublicSituation() : ""));
                        }
                    }
                    if (j == 4) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getBeLocated())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getBeLocated()) ? realEstateCertVoEntry.getValue().getBeLocated() : ""));
                        }
                    }
                    if (j == 5) {
                        if (StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPurpose())) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getPurpose()) ? baseDataDicService.getNameById(realEstateCertVoEntry.getValue().getPurpose()) : ""));
                        }
                    }
                    if (j == 6) {
                        if (realEstateCertVoEntry.getValue().getUseEndDate() != null) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getUseEndDate().toString()) ? DateUtils.format(realEstateCertVoEntry.getValue().getUseEndDate(), DateUtils.DATE_CHINESE_PATTERN) : ""));
                        }
                    }
                    if (j == 7) {
                        if (realEstateCertVoEntry.getValue().getFloorCount() != null) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getFloorCount().toString()) ? realEstateCertVoEntry.getValue().getFloorCount().toString() : ""));
                        }
                    }
                    if (j == 8) builder.writeln(String.format("%s", "无"));
                    if (j == 9) {
                        if (realEstateCertVoEntry.getValue().getApportionmentArea() != null) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getApportionmentArea().toString()) ? realEstateCertVoEntry.getValue().getApportionmentArea().toString() : ""));
                        }
                    }
                    if (j == 10) {
                        if (realEstateCertVoEntry.getValue().getFloorArea() != null) {
                            builder.writeln(String.format("%s", StringUtils.isNotBlank(realEstateCertVoEntry.getValue().getFloorArea().toString()) ? realEstateCertVoEntry.getValue().getFloorArea().toString() : ""));
                        }
                    }
                }
                builder.endRow();
            }
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
     * 土地使用权登记状况表
     *
     * @return
     * @throws Exception
     */
    public String getjudgeObjectLandUseCertificateSheet() throws Exception {
        List<DeclareRealtyLandCertVo> declareRealtyLandCertVoList = Lists.newArrayList();
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(getProjectId());
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            List<DeclareRecord> declareRecords = declareRecordList.stream().filter(declareRecord -> {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(declareRecords)) {
                declareRecords.stream().forEach(declareRecord -> {
                    if (declareRecord.getDataTableId() != null) {
                        DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                        if (declareRealtyLandCert != null) {
                            DeclareRealtyLandCertVo declareRealtyLandCertVo = declareRealtyLandCertService.getDeclareRealtyLandCertVo(declareRealtyLandCert);
                            declareRealtyLandCertVoList.add(declareRealtyLandCertVo);
                        }
                    }
                });
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        int num = 2;
        int length = 12;
        for (int j = 0; j < num; j++) {
            switch (j) {
                case 0:
                    for (int k = 0; k < length; k++) {
                        if (k == 0) {
                            builder.insertCell();
                            mergeCellModelList.add(new MergeCellModel(0, 0, 0, 11));
                            builder.writeln("估价对象《土地使用证》登记状况一览表");
                        } else {
                            builder.insertCell();
                            builder.writeln("");
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < length; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.writeln("土地证号");
                                break;
                            case 1:
                                builder.insertCell();
                                builder.writeln("座落");
                                break;
                            case 2:
                                builder.insertCell();
                                builder.writeln("土地使用权人");
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln("地号");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.writeln("图号");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.writeln("终止日期");
                                break;
                            case 6:
                                builder.insertCell();
                                builder.writeln("使用权类型");
                                break;
                            case 7:
                                builder.insertCell();
                                builder.writeln("用途");
                                break;
                            case 8:
                                builder.insertCell();
                                builder.writeln("使用权面积（㎡）");
                                break;
                            case 9:
                                builder.insertCell();
                                builder.writeln("其中：分摊面积（㎡）");
                                break;
                            case 10:
                                builder.insertCell();
                                builder.writeln("取得价格（万元）");
                                break;
                            case 11:
                                builder.insertCell();
                                builder.writeln("记   事");
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
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertVoList)) {
            for (int i = num; i < declareRealtyLandCertVoList.size() + num; i++) {
                DeclareRealtyLandCertVo declareRealtyLandCertVo = declareRealtyLandCertVoList.get(i - num);
                for (int k = 0; k < length; k++) {
                    switch (k) {
                        case 0:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getNumber()) ? declareRealtyLandCertVo.getNumber() : "");
                            break;
                        case 1:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getLandCertName()) ? declareRealtyLandCertVo.getLandCertName() : "");
                            break;
                        case 2:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getOwnership()) ? declareRealtyLandCertVo.getOwnership() : "");
                            break;
                        case 3:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getLandNumber()) ? declareRealtyLandCertVo.getLandNumber() : "");
                            break;
                        case 4:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getGraphNumber()) ? declareRealtyLandCertVo.getGraphNumber() : "");
                            break;
                        case 5:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getTerminationDate() != null) {
                                builder.writeln(DateUtils.format(declareRealtyLandCertVo.getTerminationDate(), DateUtils.DATE_CHINESE_PATTERN));
                            } else {
                                builder.writeln("");
                            }
                            break;
                        case 6:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getUseRightType()) ? declareRealtyLandCertVo.getUseRightType() : "");
                            break;
                        case 7:
                            builder.insertCell();
                            String s = baseDataDicService.getNameById(declareRealtyLandCertVo.getPurpose());
                            s = StringUtils.isNotBlank(s) ? s : "";
                            builder.writeln(s);
                            break;
                        case 8:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getUseRightArea() != null) {
                                builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getUseRightArea().toString()) ? declareRealtyLandCertVo.getUseRightArea().toString() : "");
                            }
                            break;
                        case 9:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getApportionmentArea() != null) {
                                builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getApportionmentArea().toString()) ? declareRealtyLandCertVo.getApportionmentArea().toString() : "");
                            }
                            break;
                        case 10:
                            builder.insertCell();
                            if (declareRealtyLandCertVo.getAcquisitionPrice() != null) {
                                builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getAcquisitionPrice().toString()) ? declareRealtyLandCertVo.getAcquisitionPrice().toString() : "");
                            }
                            break;
                        case 11:
                            builder.insertCell();
                            builder.writeln(StringUtils.isNotBlank(declareRealtyLandCertVo.getMemo()) ? declareRealtyLandCertVo.getMemo() : "");
                            break;
                        default:
                            builder.insertCell();
                            break;
                    }
                }
                builder.endRow();
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
        doc.save(localPath);
        return localPath;
    }

    /**
     * 估价结果一览表
     *
     * @return
     * @throws Exception
     */
    public String getjudgeBuildResultSurveySheet() throws Exception {
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.REIMBURSEMENT, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<SchemeJudgeObject, Integer> judgeObjectIntegerMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        query.setProjectPhaseId(projectPhase.getId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getId().intValue() != 0) {
                                    judgeObjectIntegerMap.put(schemeJudgeObject, projectPlanDetails.getId());
                                }
                            }
                        }
                    }
                }
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板1%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        builder.writeln("估价结果一览表");
        Table table = builder.startTable();
        final int colMax = 11;
        for (int j = 0; j < colMax; j++) {
            builder.insertCell();
            if (j == 0) builder.writeln("估价对象");
            if (j == 1) builder.writeln("坐落");
            if (j == 2) builder.writeln("用途(证载)");
            if (j == 3) builder.writeln("用途(实际)");
            if (j == 4) builder.writeln("房屋总层数");
            if (j == 5) builder.writeln("所在层数");
            if (j == 6) builder.writeln("建筑面积");
            if (j == 7) builder.writeln("单价（元/㎡）");
            if (j == 8) builder.writeln("评估总价（万元）");
            if (j == 9) builder.writeln("法定优先受偿款(万元)");
            if (j == 10) builder.writeln("抵押价值(万元)");
        }
        builder.endRow();
        if (!judgeObjectIntegerMap.isEmpty()) {
            for (Map.Entry<SchemeJudgeObject, Integer> integerEntry : judgeObjectIntegerMap.entrySet()) {
                for (int j = 0; j < colMax; j++) {
                    builder.insertCell();
                    if (j == 0) {
                        builder.writeln(integerEntry.getKey().getName());
                    }
                    if (Objects.equal(getSchemeAreaGroup().getEntrustPurpose(), baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE).getId())) {
                        //抵押=总价-法定
                        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByPlanDetailsId(integerEntry.getValue());
                        if (schemeReimbursement != null) {
                            if (schemeReimbursement.getKnowTotalPrice() != null) {
                                if (j == 9) builder.writeln(schemeReimbursement.getKnowTotalPrice().toString());
                            }
                            if (integerEntry.getKey().getPrice() != null && integerEntry.getKey().getEvaluationArea() != null) {
                                BigDecimal totol = integerEntry.getKey().getPrice().multiply(integerEntry.getKey().getEvaluationArea());
                                if (totol != null && schemeReimbursement.getKnowTotalPrice() != null) {
                                    BigDecimal mortgage = totol.subtract(schemeReimbursement.getKnowTotalPrice());
                                    if (j == 10) builder.writeln(mortgage.toString());
                                }
                            }
                        }

                    } else {
                        GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(integerEntry.getValue());
                        if (j == 1) builder.writeln(integerEntry.getKey().getSeat());
                        if (j == 2) builder.writeln(integerEntry.getKey().getCertUse());
                        if (j == 3) builder.writeln(integerEntry.getKey().getPracticalUse());
                        if (j == 4)
                            builder.writeln(generateBaseExamineService.getBasicBuilding().getFloorCount().toString());
                        if (j == 5) builder.writeln(generateBaseExamineService.getBasicHouse().getFloor());
                        if (j == 6) builder.writeln(integerEntry.getKey().getEvaluationArea().toString());
                        if (j == 7) builder.writeln(integerEntry.getKey().getPrice().toString());
                        if (j == 8) {
                            if (integerEntry.getKey().getPrice() != null && integerEntry.getKey().getEvaluationArea() != null) {
                                BigDecimal total = integerEntry.getKey().getPrice().multiply(integerEntry.getKey().getEvaluationArea());
                                builder.writeln(total.toString());
                            }
                        }
                    }
                }
                builder.endRow();
            }
        }
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
        doc.save(localPath);
        return localPath;
    }

    /**
     * 房屋所有权登记状况表
     *
     * @return
     * @throws Exception
     */
    public String getHousingOwnershipRegistrationStatementSheet() throws Exception {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<String> a_0_3 = Sets.newHashSet();
        Set<String> a_1_3 = Sets.newHashSet();
        Set<String> a_2_3 = Sets.newHashSet();
        Set<String> a_3_3 = Sets.newHashSet();
        Set<String> a_4_3 = Sets.newHashSet();
        Set<String> a_5_3 = Sets.newHashSet();
        Set<String> a_6_3 = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setProjectId(getProjectId());
                query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                    projectPlanDetailsList.stream().forEach(projectPlanDetails -> {
                        try {
                            GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                            declareRecord = declareRecord == null ? new DeclareRecord() : declareRecord;
                            for (int j = 0; j < 7; j++) {
                                switch (j) {
                                    case 0:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 0:
                                                    //权益状况
                                                    break;
                                                case 1:
                                                    //土地权益类型
                                                    break;
                                                case 3:
                                                    a_0_3.add(errorStr);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //土地管制情况
                                                    break;
                                                case 3:
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
                                                        a_1_3.add(stringBuilder.toString());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //土地他项权力
                                                    break;
                                                case 3:
                                                    a_2_3.add(getInventoryRight());
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 3:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //其他特殊情况
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        SurveyAssetInventory assetInventory = surveyAssetInventoryDao.getDataByPlanDetailsId(projectPlanDetails.getId());
                                                        if (assetInventory == null) {
                                                            assetInventory = new SurveyAssetInventory();
                                                        }
                                                        a_3_3.add(assetInventory.getSpecialCase());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //房屋所有权
                                                    break;
                                                case 3:
                                                    a_4_3.add(declareRecord.getPublicSituation());
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 5:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //出租或占用情况
                                                    break;
                                                case 3:
                                                    a_5_3.add(String.format("%s估价对象:%s", schemeJudgeObject.getNumber(), schemeJudgeObject.getRentalPossessionDesc()));
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 6:
                                        for (int k = 0; k < 4; k++) {
                                            switch (k) {
                                                case 1:
                                                    //物业管理情况
                                                    break;
                                                case 3:
                                                    if (true) {
                                                        StringBuilder stringBuilder = new StringBuilder();
                                                        if (generateBaseExamineService.getBasicBuilding().getPropertyType() != null) {
                                                            stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(generateBaseExamineService.getBasicBuilding().getProperty()) ? errorStr : generateBaseExamineService.getBasicBuilding().getProperty()).append("；");
                                                            stringBuilder.append(String.format("物业费:%s；", generateBaseExamineService.getBasicBuilding().getPropertyFee()));
                                                        }
                                                        a_6_3.add(stringBuilder.toString());
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } catch (Exception e) {

                        }
                    });
                }
            }
        }
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        builder.writeln("房屋所有权登记状况表");
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
                                builder.writeln(toSetString(a_0_3));
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
                                builder.writeln(toSetString(a_1_3));
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
                                builder.writeln(toSetString(a_2_3));
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
                                builder.writeln(toSetString(a_3_3));
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
                                builder.writeln(toSetString(a_4_3));
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
                                builder.writeln(toSetString(a_5_3));
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
                                builder.writeln(toSetString(a_6_3));
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
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        //坐落
        Set<String> beLocated = Sets.newHashSet();
        //方位
        Set<String> position = Sets.newHashSet();
        //与重要场所的距离
        Set<String> importantDistance = Sets.newHashSet();
        //临街（路）状况
        Set<String> houseFaceStreet = Sets.newHashSet();
        //楼栋
        Set<String> building = Sets.newHashSet();
        //楼层
        Set<String> floor = Sets.newHashSet();
        //朝向
        Set<String> orientation = Sets.newHashSet();
        //商业繁华度
        Set<String> businessProsperity = Sets.newHashSet();
        //人流量
        Set<String> visitorsFlowrates = Sets.newHashSet();
        //道路状况
        Set<String> roadCondition = Sets.newHashSet();
        //交通工具
        Set<String> vehicle = Sets.newHashSet();
        //交通管制
        Set<String> trafficControl = Sets.newHashSet();
        //停车方便度
        Set<String> parkingConvenience = Sets.newHashSet();
        //交通收费情况
        Set<String> trafficToll = Sets.newHashSet();
        //外部基础设施
        Set<String> externalInfrastructure = Sets.newHashSet();
        //购物
        Set<String> shop = Sets.newHashSet();
        //银行
        Set<String> bank = Sets.newHashSet();
        //教育文化
        Set<String> education = Sets.newHashSet();
        //医疗康养
        Set<String> medical = Sets.newHashSet();
        //娱乐休闲
        Set<String> entertainment = Sets.newHashSet();
        //餐饮文化
        Set<String> restaurant = Sets.newHashSet();
        //自然环境
        Set<String> naturalEnvironment = Sets.newHashSet();
        //人文环境
        Set<String> culturalEnvironment = Sets.newHashSet();
        //景观
        Set<String> scenery = Sets.newHashSet();
        //区位综述
        Set<String> locationSummary = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhaseScene : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getPlanDetailsId() != null && generateBaseExamineService.getBasicApply().getId() != null) {
                                    try {
                                        //座落
                                        beLocated.add(String.format("%s%s", getSchemeAreaGroup().getAreaName(), schemeJudgeObject.getSeat()));
                                        //方位
                                        position.add(StringUtils.isNotBlank(generateBaseExamineService.getByDataBlock().getPosition()) ? generateBaseExamineService.getByDataBlock().getPosition() : "方位：暂无");
                                        //与重要场所的距离
                                        importantDistance.add("0");
                                        if (true) {
                                            List<BasicHouseFaceStreetVo> list = generateBaseExamineService.getBasicHouseFaceStreetList();
                                            StringBuilder stringBuilder = new StringBuilder();
                                            if (CollectionUtils.isNotEmpty(list)) {
                                                for (BasicHouseFaceStreetVo faceStreet : list) {
                                                    stringBuilder.append(faceStreet.getStreetName())
                                                            .append(faceStreet.getStreetLevelName())
                                                            .append(faceStreet.getTrafficFlowName()).append(faceStreet.getVisitorsFlowrateName());
                                                    stringBuilder.append("\r\n");
                                                }
                                                //临街（路）状况
                                                houseFaceStreet.add(stringBuilder.toString());
                                                stringBuilder.delete(0, stringBuilder.toString().length());
                                                //人流量
                                                list.stream().forEach(faceStreet -> {
                                                    if (faceStreet.getVisitorsFlowrate() != null) {
                                                        stringBuilder.append(baseDataDicService.getNameById(faceStreet.getVisitorsFlowrate()));
                                                    }
                                                });
                                                visitorsFlowrates.add(stringBuilder.toString());
                                            }
                                        }
                                        //楼栋
                                        building.add(generateBaseExamineService.getBasicBuilding().getBuildingNumber());
                                        //楼层
                                        floor.add(generateBaseExamineService.getBasicBuilding().getFloorCount().toString());
                                        //商业繁华度
                                        businessProsperity.add("估价对象所在区域为规划新城区，区域商业待发展，目前以超市、零售商店为主，商业繁华度一般");
                                        //朝向
                                        orientation.add(baseDataDicService.getNameById(generateBaseExamineService.getBasicHouse().getOrientation()));
                                        //道路状况
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
                                            roadCondition.add(stringBuilder.toString());
                                        }
                                        //交通工具
                                        if (true) {
                                            List<BasicMatchingTrafficVo> matchingTrafficVoList = generateBaseExamineService.getBasicMatchingTrafficList();
                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                            if (CollectionUtils.isNotEmpty(matchingTrafficVoList)) {
                                                long a = matchingTrafficVoList.stream().filter(basicMatchingTrafficVo -> {
                                                    if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.METRO.getName())) {
                                                        return true;
                                                    }
                                                    return false;
                                                }).count();
                                                long b = matchingTrafficVoList.stream().filter(basicMatchingTrafficVo -> {
                                                    if (Objects.equal(basicMatchingTrafficVo.getType(), ExamineMatchingTrafficTypeEnum.TRANSIT.getName())) {
                                                        return true;
                                                    }
                                                    return false;
                                                }).count();
                                                if (a > 0) {
                                                    stringBuilder.append("地铁 ");
                                                }
                                                if (b > 0) {
                                                    stringBuilder.append("公交 ");
                                                }
                                            }
                                            vehicle.add(stringBuilder.toString());
                                        }
                                        //交通管制
                                        trafficControl.add("暂无交通管制信息");
                                        //停车方便度
                                        parkingConvenience.add("暂无停车方便度信息");
                                        //交通收费情况
                                        trafficToll.add("暂无交通收费情况信息");
                                        //外部基础设施
                                        if (true) {
                                            List<BasicEstateSupply> estateSupplyList = generateBaseExamineService.getBasicEstateSupplyList();
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
                                            externalInfrastructure.add(stringBuilder.toString());
                                        }
                                        //购物
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
                                            shop.add(stringBuilder.toString());
                                        }
                                        //银行 | 金融机构
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
                                            bank.add(stringBuilder.toString());
                                        }
                                        //教育文化
                                        if (true) {
                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                            List<BasicMatchingEducation> educationList = generateBaseExamineService.getBasicMatchingEducatioListn();
                                            if (CollectionUtils.isNotEmpty(educationList)) {
                                                for (BasicMatchingEducation matchingEducation : educationList) {
                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(matchingEducation.getSchoolName()) ? "" : String.format("学校名称:%s；", matchingEducation.getSchoolName()));
                                                    stringBuilder.append(matchingEducation.getSchoolNature() == null ? "" : String.format("学校性质:%s；", baseDataDicService.getNameById(matchingEducation.getSchoolNature())));
                                                    stringBuilder.append(matchingEducation.getSchoolGradation() == null ? "" : String.format("学校级次:%s；", baseDataDicService.getNameById(matchingEducation.getSchoolGradation())));
                                                    stringBuilder.append(org.apache.commons.lang.StringUtils.isBlank(matchingEducation.getSchoolLevel()) ? "" : String.format("学校等级:%s；", baseDataDicService.getNameById(Integer.valueOf(matchingEducation.getSchoolLevel()))));
                                                    stringBuilder.append(matchingEducation.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(matchingEducation.getDistance())));
                                                }
                                            }
                                            education.add(stringBuilder.toString());
                                        }
                                        //医疗康养
                                        if (true) {
                                            List<BasicMatchingMedical> matchingMedicalList = generateBaseExamineService.getBasicMatchingMedicalList();
                                            if (CollectionUtils.isNotEmpty(matchingMedicalList)) {
                                                StringBuilder stringBuilder = new StringBuilder(1024);
                                                matchingMedicalList.stream().forEach(basicMatchingMedical -> {
                                                    stringBuilder.append(basicMatchingMedical.getOrganizationName());
                                                    stringBuilder.append(" ");
                                                    stringBuilder.append(baseDataDicService.getNameById(basicMatchingMedical.getOrganizationLevel())).append(" ");
                                                    stringBuilder.append(baseDataDicService.getNameById(basicMatchingMedical.getBedNumber())).append(" ");
                                                    stringBuilder.append(baseDataDicService.getNameById(basicMatchingMedical.getDistance())).append(" ");
                                                });
                                                medical.add(stringBuilder.toString());
                                            }
                                        }
                                        if (true) {
                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                            List<BasicMatchingLeisurePlace> leisurePlaceList = generateBaseExamineService.getBasicMatchingLeisurePlaceList();
                                            if (CollectionUtils.isNotEmpty(leisurePlaceList)) {
                                                //娱乐休闲
                                                for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                    if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {
                                                        stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                        stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                        stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                        stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                    }
                                                }
                                                entertainment.add(stringBuilder.toString());
                                                stringBuilder.delete(0, stringBuilder.toString().length());
                                                //餐饮文化
                                                for (BasicMatchingLeisurePlace leisurePlace : leisurePlaceList) {
                                                    if (Objects.equal(leisurePlace.getType(), ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())) {
                                                        stringBuilder.append(org.apache.commons.lang.StringUtils.isEmpty(leisurePlace.getName()) ? "" : String.format("名称:%s；", leisurePlace.getName()));
                                                        stringBuilder.append(leisurePlace.getCategory() == null ? "" : String.format("类别:%s；", baseDataDicService.getNameById(leisurePlace.getCategory())));
                                                        stringBuilder.append(leisurePlace.getGrade() == null ? "" : String.format("档次:%s；", baseDataDicService.getNameById(leisurePlace.getGrade())));
                                                        stringBuilder.append(leisurePlace.getDistance() == null ? "" : String.format("距离:%s；", baseDataDicService.getNameById(leisurePlace.getDistance())));
                                                    }
                                                }
                                                restaurant.add(stringBuilder.toString());
                                            }
                                        }
                                        //自然环境
                                        if (true) {
                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                            List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                            if (CollectionUtils.isNotEmpty(environmentList)) {
                                                BaseDataDic naturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_NATURAL);
                                                for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                    generateBaseExamineService.getEnvironmentString(stringBuilder, naturalDic, examineMatchingEnvironment);
                                                }
                                            }
                                            naturalEnvironment.add(stringBuilder.toString());
                                        }
                                        //人文环境
                                        if (true) {
                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                            List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                            if (CollectionUtils.isNotEmpty(environmentList)) {
                                                BaseDataDic culturalDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_CULTURAL);
                                                for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                    generateBaseExamineService.getEnvironmentString(stringBuilder, culturalDic, examineMatchingEnvironment);
                                                }
                                            }
                                            culturalEnvironment.add(stringBuilder.toString());
                                        }
                                        //景观
                                        if (true) {
                                            List<BasicMatchingEnvironmentVo> environmentList = generateBaseExamineService.getBasicMatchingEnvironmentList();
                                            StringBuilder stringBuilder = new StringBuilder(1024);
                                            if (CollectionUtils.isNotEmpty(environmentList)) {
                                                BaseDataDic sceneryDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE_SCENERY);
                                                for (BasicMatchingEnvironment examineMatchingEnvironment : environmentList) {
                                                    generateBaseExamineService.getEnvironmentString(stringBuilder, sceneryDic, examineMatchingEnvironment);
                                                }
                                            }
                                            scenery.add(stringBuilder.toString());
                                        }
                                        //区位综述
                                        locationSummary.add("........");
                                    } catch (Exception e1) {
                                        //允许exception
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int j = 0; j < 5; j++) {
            switch (j) {
                case 0:
                    builder.writeln(String.format("%d%s", (j + 1), "位置状况包括:"));
                    builder.writeln(String.format("%s%s", "坐落:", toSetString(beLocated)));
                    builder.writeln(String.format("%s%s", "方位:", toSetString(position)));
                    builder.writeln(String.format("%s%s", "与重要场所的距离:", toSetString(importantDistance)));
                    builder.writeln(String.format("%s%s", "临街（路）状况:", toSetString(houseFaceStreet)));
                    builder.writeln(String.format("%s%s", "楼栋:", toSetString(building)));
                    builder.writeln(String.format("%s%s", "楼层:", toSetString(floor)));
                    builder.writeln(String.format("%s%s", "朝向:", toSetString(orientation)));
                    builder.writeln(String.format("%s%s", "商业繁华度:", toSetString(businessProsperity)));
                    builder.writeln(String.format("%s%s", "人流量:", toSetString(visitorsFlowrates)));
                    break;
                case 1:
                    builder.writeln(String.format("%d%s", (j + 1), "交通状况包括:"));
                    builder.writeln(String.format("%s%s", "道路状况:", toSetString(roadCondition)));
                    builder.writeln(String.format("%s%s", "出入可利用的交通工具:", toSetString(vehicle)));
                    builder.writeln(String.format("%s%s", "交通管制情况:", toSetString(trafficControl)));
                    builder.writeln(String.format("%s%s", "停车方便度:", toSetString(parkingConvenience)));
                    builder.writeln(String.format("%s%s", "交通收费情况:", toSetString(trafficToll)));
                    break;
                case 2:
                    builder.writeln(String.format("%d%s", (j + 1), "外部配套设施:"));
                    builder.writeln(String.format("%s%s", "外部基础设施:", toSetString(externalInfrastructure)));
                    break;
                case 3:
                    builder.writeln(String.format("%d%s", (j + 1), "外部公共服务设施:"));
                    builder.writeln(String.format("%s%s", "购物:", toSetString(shop)));
                    builder.writeln(String.format("%s%s", "银行:", toSetString(bank)));
                    builder.writeln(String.format("%s%s", "教育文化:", toSetString(education)));
                    builder.writeln(String.format("%s%s", "医疗康养:", toSetString(medical)));
                    builder.writeln(String.format("%s%s", "娱乐休闲:", toSetString(entertainment)));
                    builder.writeln(String.format("%s%s", "餐饮文化:", toSetString(restaurant)));
                    break;
                case 4:
                    builder.writeln(String.format("%d%s", (j + 1), "周围环境:"));
                    builder.writeln(String.format("%s%s", "自然环境:", toSetString(naturalEnvironment)));
                    builder.writeln(String.format("%s%s", "人文环境:", toSetString(culturalEnvironment)));
                    builder.writeln(String.format("%s%s", "景观:", toSetString(scenery)));
                    builder.writeln(String.format("%s%s", "区位综述:", toSetString(locationSummary)));
                    break;
                default:
                    break;
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
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        //名称
        Set<String> names = Sets.newHashSet();
        //四至
        Set<String> allDirections = Sets.newHashSet();
        //土地面积
        Set<String> landArea = Sets.newHashSet();
        //用途
        Set<String> purposes = Sets.newHashSet();
        //形状
        Set<String> shapes = Sets.newHashSet();
        //地形
        Set<String> terrains = Sets.newHashSet();
        //地势
        Set<String> topographys = Sets.newHashSet();
        //地质
        Set<String> geologys = Sets.newHashSet();
        //开发程度
        Set<String> developmentLevels = Sets.newHashSet();
        //综上所述
        Set<String> inSummary = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhaseScene : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getPlanDetailsId() != null) {
                                    try {
                                        //名称
                                        names.add(generateBaseExamineService.getBasicEstateLandState().getName());
                                        //"东至,南至,西至,北至"
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
                                            allDirections.add(stringBuilder.toString());
                                        }
                                        //土地面积
                                        landArea.add(generateBaseExamineService.getBasicEstateLandState().getLandArea());
                                        //用途
                                        if (true) {
                                            StringBuilder stringBuilder = new StringBuilder(10);
                                            stringBuilder.append("土地类型:");
                                            stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName()) ? generateBaseExamineService.getBasicEstateLandState().getLandUseTypeName() : errorStr);
                                            stringBuilder.append(";");
                                            stringBuilder.append("土地级别:");
                                            stringBuilder.append(StringUtils.isNotBlank(generateBaseExamineService.getBasicEstateLandState().getLandLevelName()) ? generateBaseExamineService.getBasicEstateLandState().getLandLevelName() : errorStr);
                                            purposes.add(stringBuilder.toString());
                                        }
                                        //形状
                                        shapes.add(generateBaseExamineService.getBasicEstateLandState().getShapeStateName());
                                        //地形
                                        terrains.add(generateBaseExamineService.getBasicEstateLandState().getPlanenessName());
                                        //地势
                                        topographys.add(generateBaseExamineService.getBasicEstateLandState().getTopographicTerrainName());
                                        //地质
                                        geologys.add("暂无");
                                        //开发程度
                                        developmentLevels.add(generateBaseExamineService.getBasicEstateLandState().getDevelopmentDegreeName());
                                        inSummary.add("暂无");
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        builder.writeln("估价土地实体状况表");
        for (int j = 0; j < 10; j++) {
            switch (j) {
                case 0:
                    builder.writeln(String.format("%d%s%s", (j + 1), "名称:", toSetString(names)));
                    break;
                case 1:
                    builder.writeln(String.format("%d%s%s", (j + 1), "东至,南至,西至,北至:", toSetString(allDirections)));
                    break;
                case 2:
                    builder.writeln(String.format("%d%s%s", (j + 1), "土地面积:", toSetString(landArea)));
                    break;
                case 3:
                    builder.writeln(String.format("%d%s%s", (j + 1), "用途:", toSetString(purposes)));
                    break;
                case 4:
                    builder.writeln(String.format("%d%s%s", (j + 1), "形状:", toSetString(shapes)));
                    break;
                case 5:
                    builder.writeln(String.format("%d%s%s", (j + 1), "地形:", toSetString(terrains)));
                    break;
                case 6:
                    builder.writeln(String.format("%d%s%s", (j + 1), "地势:", toSetString(topographys)));
                    break;
                case 7:
                    builder.writeln(String.format("%d%s%s", (j + 1), "地质:", toSetString(geologys)));
                    break;
                case 8:
                    builder.writeln(String.format("%d%s%s", (j + 1), "开发程度:", toSetString(developmentLevels)));
                    break;
                case 9:
                    builder.writeln(String.format("%s%s", "综上所述:", toSetString(inSummary)));
                    break;
                default:
                    break;
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
        List<ProjectPhase> projectPhases = projectPhaseService.queryProjectPhaseByCategory(
                getProjectInfo().getProjectTypeId(), getProjectInfo().getProjectCategoryId(), null)
                .stream()
                .filter(projectPhaseVo -> {
                    if (Objects.equal(AssessPhaseKeyConstant.SCENE_EXPLORE, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    if (Objects.equal(AssessPhaseKeyConstant.CASE_STUDY, projectPhaseVo.getPhaseKey())) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        //楼盘名称
        Set<String> estateName = Sets.newHashSet();
        //建筑结构
        Set<String> buildingStructures = Sets.newHashSet();
        //建筑规模
        Set<String> buildingScale = Sets.newHashSet();
        //设施设备
        Set<String> facilitiesEquipment = Sets.newHashSet();
        //装饰装修
        Set<String> decoration = Sets.newHashSet();
        //层高
        Set<String> storeyHeight = Sets.newHashSet();
        //空间布局
        Set<String> spatialDistributions = Sets.newHashSet();
        //建筑功能
        Set<String> buildingFunctions = Sets.newHashSet();
        //工程质量
        Set<String> constructionQuality = Sets.newHashSet();
        //外观
        Set<String> appearance = Sets.newHashSet();
        //新旧程度及维护使用情况
        Set<String> maintenance = Sets.newHashSet();
        //物业管理
        Set<String> estateManagement = Sets.newHashSet();
        //建筑实体分析
        Set<String> architecturalEntityAnalysis = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhaseScene : projectPhases) {
                if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
                    for (int i = 0; i < schemeJudgeObjectList.size(); i++) {
                        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectList.get(i);
                        ProjectPlanDetails query = new ProjectPlanDetails();
                        query.setProjectId(getProjectId());
                        query.setProjectPhaseId(projectPhaseScene.getId());
                        query.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
                        List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
                        if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                            for (ProjectPlanDetails projectPlanDetails : projectPlanDetailsList) {
                                GenerateBaseExamineService generateBaseExamineService = getGenerateBaseExamineService(projectPlanDetails.getId());
                                if (generateBaseExamineService.getBasicApply().getId() != null && generateBaseExamineService.getBasicApply().getPlanDetailsId() != null) {
                                    //楼盘名称
                                    if (generateBaseExamineService.getEstate().getId() != null) {
                                        estateName.add(generateBaseExamineService.getEstate().getName());
                                    }
                                    //建筑结构
                                    buildingStructures.add(baseDataDicService.getNameById(generateBaseExamineService.getBasicBuilding().getBuildingStructureCategory()));
                                    //建筑规模
                                    buildingScale.add("暂无");
                                    //设施设备
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
                                        facilitiesEquipment.add(stringBuilder.toString());
                                    }
                                    //装饰装修
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
                                        stringBuilder.append("室内净高和层高");
                                        stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getFloorHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getFloorHeight()));
                                        stringBuilder.append(",");
                                        stringBuilder.append(String.valueOf(generateBaseExamineService.getBasicBuilding().getNetHeight() == null ? errorStr : generateBaseExamineService.getBasicBuilding().getNetHeight()));
                                        decoration.add(stringBuilder.toString());
                                    }
                                    //层高
                                    if (generateBaseExamineService.getBasicBuilding().getId() != null && generateBaseExamineService.getBasicBuilding() != null) {
                                        storeyHeight.add(generateBaseExamineService.getBasicBuilding().getFloorHeight().toString());
                                    }
                                    //空间布局
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
                                        spatialDistributions.add(stringBuilder.toString());
                                    }
                                    //建筑功能-防水、保温、隔热、隔声、通风、采光、日照
                                    if (true) {
                                        StringBuilder stringBuilder = new StringBuilder(128);
                                        List<BasicBuildingFunction> functionList = generateBaseExamineService.getBasicBuildingFunctionList();
                                        if (CollectionUtils.isNotEmpty(functionList)) {
                                            functionList.stream().forEach(basicBuildingFunction -> {
                                                stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getType()));
                                                stringBuilder.append(",");
                                                stringBuilder.append(baseDataDicService.getNameById(basicBuildingFunction.getDecorationPart()));
                                            });
                                        }
                                        List<BasicHouseRoom> roomList = generateBaseExamineService.getBasicHouseRoomList();
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
                                        buildingFunctions.add(stringBuilder.toString());
                                    }
                                    //工程质量
                                    constructionQuality.add("暂无");
                                    //外观
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
                                        appearance.add(stringBuilder.toString());
                                    }
                                    //新旧程度及维护使用情况
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
                                        maintenance.add(stringBuilder.toString());
                                    }
                                    //物业管理
                                    estateManagement.add("较好");
                                    //建筑实体分析
                                    architecturalEntityAnalysis.add("暂无");
                                }
                            }
                        }
                    }
                }
            }
        }
        builder.writeln("估价对象建筑实体状况表");
        for (int j = 0; j < 13; j++) {
            switch (j) {
                case 0:
                    builder.writeln(String.format("%d%s%s", (j + 1), "楼盘名称:", toSetString(estateName)));
                    break;
                case 1:
                    builder.writeln(String.format("%d%s%s", (j + 1), "建筑结构:", toSetString(buildingStructures)));
                    break;
                case 2:
                    builder.writeln(String.format("%d%s%s", (j + 1), "建筑规模:", toSetString(buildingScale)));
                    break;
                case 3:
                    builder.writeln(String.format("%d%s%s", (j + 1), "设施设备:", toSetString(buildingScale)));
                    break;
                case 4:
                    builder.writeln(String.format("%d%s%s", (j + 1), "装饰装修:", toSetString(decoration)));
                    break;
                case 5:
                    builder.writeln(String.format("%d%s%s", (j + 1), "层高:", toSetString(storeyHeight)));
                    break;
                case 6:
                    builder.writeln(String.format("%d%s%s", (j + 1), "空间布局:", toSetString(spatialDistributions)));
                    break;
                case 7:
                    builder.writeln(String.format("%d%s%s", (j + 1), "建筑功能:", toSetString(buildingFunctions)));
                    break;
                case 8:
                    builder.writeln(String.format("%d%s%s", (j + 1), "工程质量:", toSetString(constructionQuality)));
                    break;
                case 9:
                    builder.writeln(String.format("%d%s%s", (j + 1), "外观:", toSetString(appearance)));
                    break;
                case 10:
                    builder.writeln(String.format("%d%s%s", (j + 1), "新旧程度及维护使用情况:", toSetString(maintenance)));
                    break;
                case 11:
                    builder.writeln(String.format("%d%s%s", (j + 1), "物业管理:", toSetString(estateManagement)));
                    break;
                case 12:
                    builder.writeln(String.format("%d%s%s", (j + 1), "建筑实体分析:", toSetString(architecturalEntityAnalysis)));
                    break;
                default:
                    break;
            }
        }
        doc.save(localPath);
        this.judgeBuildLandStateSheet = localPath;
        return judgeBuildLandStateSheet;
    }

    /**
     * 汇总表
     *
     * @return
     * @throws Exception
     */
    public String getJudgeSummarySheet() throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.writeln("汇总表");
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        builder.writeln("估价结果汇总表");
        Set<MergeCellModel> mergeCellModelList = Sets.newHashSet();
        Table table = builder.startTable();
        for (int j = 0; j < 2; j++) {
            switch (j) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        switch (k) {
                            case 0:
                                builder.insertCell();
                                builder.writeln("估价对象及结果\\估价方法及结果");
                                mergeCellModelList.add(new MergeCellModel(0, 0, 1, 2));
                                break;
                            case 3:
                                builder.insertCell();
                                builder.writeln("测算结果");
                                mergeCellModelList.add(new MergeCellModel(0, 3, 0, 5));
                                break;
                            case 6:
                                builder.insertCell();
                                builder.writeln("估价结果");
                                //未处理
                                mergeCellModelList.add(new MergeCellModel(j, 6, j + 1, 7));
                                break;
                            default:
                                builder.insertCell();
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        switch (k) {
                            case 3:
                                builder.insertCell();
                                builder.writeln("市场比较法");
                                break;
                            case 4:
                                builder.insertCell();
                                builder.writeln("收益法");
                                break;
                            case 5:
                                builder.insertCell();
                                builder.writeln("");
                                break;
                            case 6:
                                builder.insertCell();
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
        }
        BigDecimal totolCompare = new BigDecimal(0);
        BigDecimal priceCompare = new BigDecimal(0);
        BigDecimal totolIncome = new BigDecimal(0);
        BigDecimal priceIncome = new BigDecimal(0);
        for (int j = 2; j < 2 + schemeJudgeObjectList.size(); j++) {
            MdMarketCompare mdMarketCompare = null;
            MdIncome mdIncome = null;
            if (true) {
                Integer id = getSchemeInfoId(CalculationMethodNameEnum.MdCompare, schemeJudgeObjectList.get(j - 2));
                if (id != null) {
                    mdMarketCompare = mdMarketCompareService.getMdMarketCompare(id);
                }
            }
            if (true) {
                Integer id = getSchemeInfoId(CalculationMethodNameEnum.MdIncome, schemeJudgeObjectList.get(j - 2));
                if (id != null) {
                    mdIncome = mdIncomeService.getIncomeById(id);
                }
            }
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.writeln(String.format("委估对象%s", schemeJudgeObjectList.get(j - 2).getNumber()));
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.writeln("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdMarketCompare.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.writeln(temp.toString());
                                        totolCompare = totolCompare.add(temp);
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null && schemeJudgeObjectList.get(j - 2).getEvaluationArea() != null) {
                                        BigDecimal temp = mdIncome.getPrice().multiply(schemeJudgeObjectList.get(j - 2).getEvaluationArea());
                                        builder.writeln(temp.toString());
                                        totolIncome = totolIncome.add(temp);
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 1:
                                builder.writeln("单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                if (mdMarketCompare != null) {
                                    if (mdMarketCompare.getPrice() != null) {
                                        builder.writeln(mdMarketCompare.getPrice().toString());
                                        priceCompare = priceCompare.add(mdMarketCompare.getPrice());
                                    } else {
                                        builder.writeln("");
                                    }
                                }
                                break;
                            case 4:
                                if (mdIncome != null) {
                                    if (mdIncome.getPrice() != null) {
                                        builder.writeln(mdIncome.getPrice().toString());
                                        priceIncome = priceIncome.add(mdIncome.getPrice());
                                    }
                                } else {
                                    builder.writeln("");
                                }
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
            }
        }
        for (int j = 2 + schemeJudgeObjectList.size(); j < 2 + schemeJudgeObjectList.size() + 2; j++) {
            int num = j % 2;
            switch (num) {
                case 0:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 0:
                                builder.writeln("汇总平均价值");
                                mergeCellModelList.add(new MergeCellModel(j, 0, j + 1, 0));
                                break;
                            case 1:
                                builder.writeln("总价(元或万元)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.writeln(totolCompare.toString());
                                break;
                            case 4:
                                builder.writeln(totolIncome.toString());
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                case 1:
                    for (int k = 0; k < 8; k++) {
                        builder.insertCell();
                        switch (k) {
                            case 1:
                                builder.writeln("平均单价(元/m2)");
                                mergeCellModelList.add(new MergeCellModel(j, 1, j, 2));
                                break;
                            case 3:
                                builder.writeln(priceCompare.toString());
                                break;
                            case 4:
                                builder.writeln(priceIncome.toString());
                                break;
                            case 6:
                                mergeCellModelList.add(new MergeCellModel(j, 6, j, 7));
                                break;
                            default:
                                break;
                        }
                    }
                    builder.endRow();
                    break;
                default:
                    break;
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
        doc.save(localPath);
        return localPath;
    }

    /**
     * 收益法替换模板
     *
     * @return
     * @throws Exception
     */
    public String getMdIncomeSheet() throws Exception {
        Set<Integer> schemeInfoList = getSchemeInfoList(CalculationMethodNameEnum.MdIncome);
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            //暂时取一个
            GenerateMdIncomeService generateMdIncomeService = new GenerateMdIncomeService(schemeInfoList.stream().sorted(
                    (a, b) -> a.compareTo(b)
            ).findFirst().get(), getProjectId(), getAreaId());
            String localPath = generateMdIncomeService.generateCompareFile();
            return localPath;
        }
        return null;
    }

    /**
     * 市场比较法替换模板
     *
     * @return
     * @throws Exception
     */
    public String getMdCompareSheet() throws Exception {
        Set<Integer> schemeInfoList = getSchemeInfoList(CalculationMethodNameEnum.MdCompare);
        if (CollectionUtils.isNotEmpty(schemeInfoList)) {
            //暂时取一个
            GenerateMdCompareService generateMdCompareService = new GenerateMdCompareService(schemeInfoList.stream().sorted((a, b) -> a.compareTo(b)).findFirst().get(), new Date(), getAreaId());
            String s = generateMdCompareService.generateCompareFile();
            return s;
        }
        return null;
    }


    /**
     * 估价委托书复印件
     *
     * @return
     * @throws Exception
     */
    public String getJUDGEOBJECTPRINCIPALCOPYSHEET() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        new Document().save(localPath);
        SysAttachmentDto sysAttachmentDto = schemeReportFileService.getProjectProxyFileList(getProjectId());
        if (sysAttachmentDto != null) {
            String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
            List<String> images = Lists.newArrayList();
            if (FileUtils.checkImgSuffix(imgPath)) {
                images.add(imgPath);
            }
            if (CollectionUtils.isNotEmpty(images)) {
                AsposeUtils.insertImage(localPath, images, 200, 100);
            }
        }
        return localPath;
    }

    /**
     * 估计对象位置示意图
     *
     * @return
     */
    public String getEstimatedObjectLocationDiagram() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SysAttachmentDto> sysAttachmentDtoList = schemeReportFileService.getJudgeObjectPositionFileList(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.writeln(schemeJudgeObject.getName());
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价对象实况照片
     *
     * @return
     */
    public String getValuation_Target_Live_Photos() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        List<SchemeJudgeObject> schemeJudgeObjectFullList = schemeJudgeObjectService.getJudgeObjectFullListByAreaId(this.getAreaId());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectFullList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectFullList) {
                List<SchemeReportFileItem> sysAttachmentDtoList = schemeReportFileService.getLiveSituationSelect(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.writeln(schemeJudgeObject.getName());
                    for (SchemeReportFileItem sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getAttachmentId());
                        builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    private void builderInsertImage(DocumentBuilder builder, String imgPath) throws Exception {
        if (StringUtils.isNotBlank(imgPath) && FileUtils.checkImgSuffix(imgPath)) {
            File file = new File(imgPath);
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
            builder.insertImage(imgPath,
                    sourceImg.getWidth() > 400 ? 400 : sourceImg.getWidth(),
                    sourceImg.getHeight() > 500 ? 500 : sourceImg.getHeight());
            builder.write(" ");//为图片设置间隔
        }
    }

    /**
     * 估价对象权属证明复印件
     *
     * @return
     */
    public String getCopies_the_Ownership_Certificate_the_Valuation_Object() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Map<Integer, List<SysAttachmentDto>> ownershipCertFileList = schemeReportFileService.getOwnershipCertFileList(getAreaId());
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                List<SysAttachmentDto> sysAttachmentDtoList = ownershipCertFileList.get(schemeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
                    builder.writeln(schemeJudgeObject.getName());
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 估价中引用的专用文件资料
     *
     * @return
     * @throws Exception
     */
    public String getSpecial_documentation_referenced_in_valuation() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            Map<Integer, List<SysAttachmentDto>> inventoryAddressFileList = schemeReportFileService.getInventoryAddressFileList(getAreaId());
            Map<Integer, List<SysAttachmentDto>> reimbursementFileList = schemeReportFileService.getReimbursementFileList(getAreaId());
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
                //1.先取地址不一致附件与法定优先受偿款附件
                List<SysAttachmentDto> addressFileList = inventoryAddressFileList.get(schemeJudgeObject.getId());
                List<SysAttachmentDto> reimFileList = reimbursementFileList.get(schemeJudgeObject.getId());
                if (CollectionUtils.isEmpty(addressFileList) && CollectionUtils.isEmpty(reimFileList)) continue;
                builder.writeln(schemeJudgeObject.getName());
                if (CollectionUtils.isNotEmpty(addressFileList)) {
                    builder.writeln("地址不一致附件");
                    for (SysAttachmentDto sysAttachmentDto : addressFileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        builderInsertImage(builder, imgPath);
                    }
                }
                if (CollectionUtils.isNotEmpty(reimFileList)) {
                    builder.writeln("法定优先受偿款附件");
                    for (SysAttachmentDto sysAttachmentDto : reimFileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        //2.取得自定义的附件
        List<SchemeReportFileCustom> reportFileCustomList = schemeReportFileService.getReportFileCustomList(getAreaId());
        if (CollectionUtils.isNotEmpty(reportFileCustomList)) {
            for (SchemeReportFileCustom schemeReportFileCustom : reportFileCustomList) {
                List<SysAttachmentDto> fileList = schemeReportFileService.getCustomFileList(schemeReportFileCustom.getId());
                if (CollectionUtils.isNotEmpty(fileList)) {
                    builder.writeln(schemeReportFileCustom.getName());
                    for (SysAttachmentDto sysAttachmentDto : fileList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        builderInsertImage(builder, imgPath);
                    }
                }
            }
        }
        document.save(localPath);
        return localPath;
    }

    /**
     * 房地产估价机构营业执照复印件
     *
     * @return
     * @throws Exception
     */
    public String getCopyBusinessLicenseRealEstateValuationAgency() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForLicense();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                List<String> images = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                images.add(imgPath);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(images)) {
                    AsposeUtils.insertImage(localPath, images, 200, 100);
                }
            }
        }
        return localPath;
    }

    /**
     * '房地产估价机构资质证书复印件
     *
     * @return
     * @throws Exception
     */
    public String getCopyQualificationCertificateRealEstateValuationInstitution() throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        new Document().save(localPath);
        AdCompanyQualificationDto adCompanyQualificationDto = getCompanyQualificationForPractising();
        if (adCompanyQualificationDto != null) {
            if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                List<String> images = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                        String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                        if (StringUtils.isNotBlank(imgPath)) {
                            if (FileUtils.checkImgSuffix(imgPath)) {
                                images.add(imgPath);
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(images)) {
                    AsposeUtils.insertImage(localPath, images, 200, 100);
                }
            }
        }
        return localPath;
    }

    /**
     * 注册房地产估价师注册证书复印件
     *
     * @param str
     * @return
     * @throws Exception
     */
    public String getRegisteredRealEstateValuerValuationInstitution(String str) throws Exception {
        String localPath = String.format("%s\\报告模板%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        new Document().save(localPath);
        List<String> images = Lists.newArrayList();
        String[] strings = str.split(",");
        for (String id : strings) {
            DataQualificationVo dataQualificationVo = dataQualificationService.getByDataQualificationId(Integer.parseInt(id));
            if (dataQualificationVo != null) {
                if (StringUtils.isNotBlank(dataQualificationVo.getUserAccount())) {
                    for (String account : dataQualificationVo.getUserAccount().split(",")) {
                        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = adRpcQualificationsService.getAdPersonalQualificationDto(account, AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue());
                        if (CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)) {
                            adPersonalQualificationDtoList.stream().forEach(adCompanyQualificationDto -> {
                                if (StringUtils.isNotBlank(adCompanyQualificationDto.getStandardImageJson())) {
                                    List<SysAttachmentDto> attachmentDtoList = JSON.parseArray(adCompanyQualificationDto.getStandardImageJson(), SysAttachmentDto.class);
                                    if (CollectionUtils.isNotEmpty(attachmentDtoList)) {
                                        for (SysAttachmentDto sysAttachmentDto : attachmentDtoList) {
                                            try {
                                                String imgPath = baseAttachmentService.downloadFtpFileToLocal(sysAttachmentDto.getId());
                                                if (StringUtils.isNotBlank(imgPath)) {
                                                    if (FileUtils.checkImgSuffix(imgPath)) {
                                                        images.add(imgPath);
                                                    }
                                                }
                                            } catch (Exception e1) {
                                            }
                                        }
                                    }

                                }
                            });
                        }
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(images)) {
            AsposeUtils.insertImage(localPath, images, 200, 100);
        }
        return localPath;
    }

    /**
     * 获取如收益法,市场比较法，假设开发法，成本法等的id集合
     *
     * @param methodNameEnum
     * @return
     */
    private Set<Integer> getSchemeInfoList(CalculationMethodNameEnum methodNameEnum) {
        List<SchemeJudgeObject> schemeJudgeObjectList = getSchemeJudgeObjectList();
        Set<Integer> schemeInfoList = Sets.newHashSet();
        DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(oo -> {
            if (oo.getName().equals(methodNameEnum.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            schemeJudgeObjectList.stream().forEach(schemeJudgeObject -> {
                if (dataEvaluationMethod != null) {
                    SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), dataEvaluationMethod.getMethod());
                    if (schemeInfo != null) {
                        schemeInfoList.add(schemeInfo.getMethodDataId());
                    }
                }
            });
        }
        return schemeInfoList;
    }

    /**
     * 获取如收益法,市场比较法，假设开发法，成本法等的id
     *
     * @param methodNameEnum
     * @param schemeJudgeObject
     * @return
     */
    private Integer getSchemeInfoId(CalculationMethodNameEnum methodNameEnum, SchemeJudgeObject schemeJudgeObject) {
        DataEvaluationMethod dataEvaluationMethod = evaluationMethodService.getMethodAllList().stream().filter(oo -> {
            if (oo.getName().equals(methodNameEnum.getName())) {
                return true;
            }
            return false;
        }).findFirst().get();
        if (dataEvaluationMethod != null) {
            SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(schemeJudgeObject.getId(), dataEvaluationMethod.getMethod());
            if (schemeInfo != null) {
                return schemeInfo.getMethodDataId();
            }
        }
        return null;
    }

    /**
     * 他权信息公示
     *
     * @return
     * @throws Exception
     */
    public String getHisRightInfoPublicity() throws Exception {
        DataHisRightInfoPublicity dataHisRightInfoPublicity = null;
        SchemeAreaGroup schemeAreaGroup = getSchemeAreaGroup();
        Integer areaId = null;
        if (StringUtils.isNotBlank(schemeAreaGroup.getDistrict())) {
            areaId = Integer.parseInt(schemeAreaGroup.getDistrict());
        } else {
            if (StringUtils.isNotBlank(schemeAreaGroup.getCity())) {
                areaId = Integer.parseInt(schemeAreaGroup.getCity());
            }
        }
        if (areaId != null) {
            dataHisRightInfoPublicity = dataHisRightInfoPublicityService.getContent(areaId);
        }
        if (dataHisRightInfoPublicity != null) {
            return dataHisRightInfoPublicity.getContent();
        }
        return "";
    }

    /**
     * 功能描述: 申报所启用表单类型
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2019/2/25 10:09
     */
    public String getTypesFormEnabledDeclarationOffice() throws Exception {
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(getProjectId());
        List<DeclareRealtyLandCert> declareRealtyLandCertList = Lists.newArrayList();
        List<DeclareRealtyHouseCert> declareRealtyHouseCertList = Lists.newArrayList();
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCertList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            List<DeclareRecord> declareRecords = declareRecordList.stream().filter(declareRecord -> {
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    return true;
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    return true;
                }
                if (declareRecord.getDataTableName().equals(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(declareRecords)) {
                declareRecords.stream().forEach(declareRecord -> {
                    if (declareRecord.getDataTableId() != null) {
                        DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(declareRecord.getDataTableId());
                        if (declareRealtyLandCert != null) {
                            declareRealtyLandCertList.add(declareRealtyLandCert);
                        }
                        DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(declareRecord.getDataTableId());
                        if (declareRealtyHouseCert != null) {
                            declareRealtyHouseCertList.add(declareRealtyHouseCert);
                        }
                        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(declareRecord.getDataTableId());
                        if (declareRealtyRealEstateCert != null) {
                            declareRealtyRealEstateCertList.add(declareRealtyRealEstateCert);
                        }
                    }
                });
            }
        }
        StringBuilder stringBuilder = new StringBuilder(24);
        if (CollectionUtils.isNotEmpty(declareRealtyHouseCertList)) {
            stringBuilder.append(DeclareTypeEnum.HOUSE.getKey());
        }
        if (CollectionUtils.isNotEmpty(declareRealtyLandCertList)) {
            stringBuilder.append(",").append(DeclareTypeEnum.LAND.getKey());
        }
        if (CollectionUtils.isNotEmpty(declareRealtyRealEstateCertList)) {
            stringBuilder.append(",").append(DeclareTypeEnum.RealEstate.getKey());
        }
        return stringBuilder.toString();
    }


    public List<SchemeJudgeObject> getSchemeJudgeObjectList() {
        if (CollectionUtils.isEmpty(this.schemeJudgeObjectList)) {
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
        this.dataQualificationService = SpringContextUtils.getBean(DataQualificationService.class);
        this.declareRealtyLandCertService = SpringContextUtils.getBean(DeclareRealtyLandCertService.class);
        this.schemeInfoService = SpringContextUtils.getBean(SchemeInfoService.class);
        this.evaluationMethodService = SpringContextUtils.getBean(EvaluationMethodService.class);
        this.schemeLiquidationAnalysisService = SpringContextUtils.getBean(SchemeLiquidationAnalysisService.class);
        this.mdIncomeService = SpringContextUtils.getBean(MdIncomeService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.declareRealtyHouseCertService = SpringContextUtils.getBean(DeclareRealtyHouseCertService.class);
        this.declareRealtyRealEstateCertService = SpringContextUtils.getBean(DeclareRealtyRealEstateCertService.class);
        this.dataHisRightInfoPublicityService = SpringContextUtils.getBean(DataHisRightInfoPublicityService.class);
        this.surveyAssetInventoryContentService = SpringContextUtils.getBean(SurveyAssetInventoryContentService.class);
        this.dataMethodFormulaService = SpringContextUtils.getBean(DataMethodFormulaService.class);
    }

    private String toSetString(Set<String> stringSet) {
        StringBuilder builder = new StringBuilder(24);
        List<String> stringList = Lists.newArrayList(stringSet);
        if (CollectionUtils.isNotEmpty(stringList)) {
            for (int i = 0; i < stringList.size(); i++) {
                if (StringUtils.isNotBlank(stringList.get(i))) {
                    builder.append(stringList.get(i));
                    if (i == stringList.size() - 1) {
                        builder.append(";");
                    } else {
                        builder.append(",");
                    }
                }
            }
        }
        return builder.toString();
    }


}
