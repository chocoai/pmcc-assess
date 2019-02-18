package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/15 14:30
 * @Description:报告模板字段
 */
public enum BaseReportFieldEnum {
    ReportNumber("", "文号"),
    ValuationProjectName("", "估价项目名称"),
    PRINCIPAL("", "估价委托人"),
    ReportIssuanceDate("", "报告出具日期"),
    ValueType("", "价值类型"),
    ValueTypeDesc("", "价值类型描述"),
    DelegatePurpose("", "委托目的"),
    Seat("", "坐落"),//new 包含街道号，楼盘名称
    CertificationPurpose("", "证载用途"),
    ScopePropertyExplain("", "财产范围说明"),//new 财产范围说明
    BuildingAndAssessArea("", "建筑面积及评估面积"),//new 建筑面积及评估面积
    PracticalUse("", "实际用途"),
    LandUseRightType("", "土地使用权类型"),
    PowerPerson("", "权利人"),//
    BuildingStructureCategory("", "建筑结构类别"),//new 建筑结构类别
    ValueTimePoint("", "价值时点"),
    ValueTimePointRemark("", "价值时点说明"),
    EvaluationMethod("", "评估方法"),//方案中选择使用的方法，只描述一次
    TotalRealEstatePrice("", "房地产总价"),
    CapitalizationAmount("", "房地产总价大写金额"),
    JudgeBuildResultSurveySheet("", "估价结果一览表"),
    HisRightInfoPublicity("", "他权信息公示"),//new 他权信息公示
    HisRightType("", "他权类别"),//new 他权类别
    AssetInventoryAgreement("", "资产清查一致说明"),//new 资产清查一致说明
    ExpertWorkOverview("", "外聘专家工作概况"),//new 外聘专家工作概况 ps最后才处理
    BusinessScope("", "经营范围"),//new 经营范围
    StatementPurposeEntrustment("", "委托目的描述"),
    JudgeObjectAreaStatusSheet("", "估价对象区位状况"),
    JudgeObjectLandStateSheet("", "估价土地实体状况"),
    JudgeBuildLandStateSheet("", "估价建筑物实体状况"),

    HisRightDeclare("","他项权申报"),//new 他项权申报;当该区域下有他项权信息并且填写了他权号，则将该文字显示出来前面加、号，否则先为空串
    BestUseDesc("","最佳利用描述"),//new 最佳利用描述
    DecalreFormTypeAll("","申报启用表单类型"),//new 申报启用表单类型; 比如 房产证、土地证、不动产证
    HisRightHasLease("","他权有无租赁权"),//new 他权有无租赁权
    /*
    ---------------------------------------------------------------------------------------------------
     */
    PrincipalAddress("", "委托人地址"),
    PrincipalLegalRepresentative("", "委托人法定代表人"),
    /*
    ---------------------------------------------------------------------------------------------------
     */
    RegisteredRealEstateValuer("", "注册房产估价师"),
    RegisteredRealEstateValuerAndNumber("", "注册房产估价师及注册号"),

    XIEHE_organizationName("", "机构名称"),
    XIEHE_RealEstateValuationAgency("", "房地产估价机构"),
    XIEHE_organizationAddress("", "机构住所"),
    XIEHE_legalRepresentative("", "机构法定代表人"),
    XIEHE_registeredNo("", "机构工商注册号"),
    XIEHE_organizationRank("", "机构资质等级"),
    XIEHE_certificateNo("", "机构证书号"),
    XIEHE_certificateEffectiveDate("", "机构证书有效期"),
    /*
    ---------------------------------------------------------------------------------------------------
     */


    Location("", "区位"),
    notPowerPerson("", "非权利人"),//(区位)
    ValueConnotation("", "价值内涵"),
    ValueImplicationDesc("", "价值内涵描述"),
    AssessArea("", "评估面积"),



    SetUse("", "设定用途"),
    LandUseControl("", "土地使用管制"),
    rentalPossessionDesc("", "出租或占用情况"),
    HouseType("", "房产类型"),

    EvaluationThink("", "估价技术思路"),
    SelectionValuationMethod("", "选择估价方法"),
    EvaluationPriceCateGory("", "分类评估单价"),
    EvaluationAreaCateGory("", "分类评估面积"),
    EvaluationPriceCateGoryTotal("", "分类评估总价"),
    EvaluationExpression("", "分类评估单价计算试"),
    EvaluationMethodResult("", "分类评估方法结果"),
    WeightSpecification("", "权重说明"),

    ValueExpressionResult("", "价值表达结果"),
    LIQUIDATION_ANALYSIS("", "变现分析税费"),
    StatutoryOptimumReimbursement("", "法定优先受偿款"),

    ReportingCategories("", "报告类别"),


    ComputationProcess("", "计算过程"),
    SelectionApplicationParameters("", "参数选取与应用"),
    StatusBuildingRightsInterests("", "建筑物权益状况"),
    theGeneralIdeaOfThisEvaluationAndTheSelectionOfEvaluationMethods("", "本次估价的总体思路和评估方法的选取"),

    CostAssistApplyReason("", "成本法适用原因"),
    CostAssistNotApplicableReason("", "成本法不适用原因"),
    CostAssistThink("", "成本法评估思路"),

    CompareAssistApplyReason("", "市场比较法适用原因"),
    CompareAssistNotApplicableReason("", "市场比较法不适用原因"),
    CompareAssistThink("", "市场比较法评估思路"),

    IncomeAssistApplyReason("", "收益法适用原因"),
    IncomeAssistNotApplicableReason("", "收益法不适用原因"),
    IncomeAssistThink("", "收益法评估思路"),

    DevelopmentAssistApplyReason("", "假设开发法适用原因"),
    DevelopmentAssistNotApplicableReason("", "假设开发法不适用原因"),
    DevelopmentAssistThink("", "假设开发法评估思路"),

    SeparationCertificateUses("", "证载用途分述"),
    SummaryCertificateUses("", "证载用途总括"),


    registrationNumber("", "注册号"),
    surveyExamineDate("", "现场查勘期"),
    surveyExamineCreate("", "现场查勘人员"),
    HomeWorkEndTime("", "作业结束时间"),
    HomeWorkStartTime("", "作业开始时间"),

    EVALUATION_HYPOTHESIS("", "评估假设"),
    EVALUATION_BASIS("", "评估依据"),
    EVALUATION_PRINCIPLE("", "评估原则"),
    ANALYSIS_CATEGORY_LIQUIDITY("", "变现能力分析"),
    ANALYSIS_CATEGORY_RISK("", "风险提示"),

    AssistanceStaff("", "协助工作人员"),


    HousingOwnershipRegistrationStatementSheet("", "房屋所有权登记状况"),




    judgeSummarySheet("", "估价汇总表"),
    //土地证
    judgeObjectLandUseCertificateSheet("", "土地使用权登记状况"),

    JUDGEOBJECTPRINCIPALCOPYSHEET("", "估价委托书复印件"),
    EstimatedObjectLocationDiagram("", "估价对象位置示意图"),
    Valuation_Target_Live_Photos("", "估价对象实况照片"),
    Copies_the_Ownership_Certificate_the_Valuation_Object("", "估价对象权属证明复印件"),
    Special_documentation_referenced_in_valuation("", "估价中引用的专用文件资料"),

    CopyBusinessLicenseRealEstateValuationAgency("", "房地产估价机构营业执照复印件"),
    CopyQualificationCertificateRealEstateValuationInstitution("", "房地产估价机构资质证书复印件"),
    RegisteredRealEstateValuerValuationInstitution("", "注册房地产估价师注册证书复印件"),

    inventoryRight("", "土地他项权利情况"),

    SummaryEvaluationMethod("", "评估方法总括"),
    AssessmentMethods("", "评估方法分述"),

    MdIncome("", "收益法字段"),
    MdCompare("", "市场比较法字段");

    private String key;

    private String name;


    private BaseReportFieldEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public static BaseReportFieldEnum getEnumByName(String id) {
        for (BaseReportFieldEnum e : BaseReportFieldEnum.values()) {
            if (e.getKey().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldEnum e : BaseReportFieldEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
