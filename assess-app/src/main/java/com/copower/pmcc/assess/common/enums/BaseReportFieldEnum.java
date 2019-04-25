package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段
 */
public enum BaseReportFieldEnum implements Serializable {
    ReportNumber("文号"),
    ReportingCategories("报告类别"),
    ValuationProjectName("估价项目名称"),
    ReportIssuanceDate("报告出具日期"),
    ValueType("价值类型"),
    ValueTypeDesc("价值类型描述"),
    DelegatePurpose("委托目的"),
    CertificationPurpose("证载用途"),
    ScopePropertyExplain("财产范围说明"),
    BuildingAndAssessArea("建筑面积及评估面积"),
    PracticalUse("实际用途"),
    LandUseRightType("土地使用权类型"),
    PowerPerson("权利人"),
    BuildingStructureCategory("建筑结构类别"),
    TypesFormEnabledDeclarationOffice("申报所启用表单类型"),
    ValueTimePoint("价值时点"),
    ValueTimePointRemark("价值时点说明"),
    EvaluationMethod("评估方法"),
    TotalRealEstatePrice("房地产总价"),
    CapitalizationAmount("房地产总价大写金额"),
    totalAmountMortgageValue("抵押价值总金额"),
    totalAmountMortgageValueCapitalization("抵押价值总金额大写"),
    HisRightInfoPublicity("他权信息公示"),
    HisRightType("他权类别"),
    AssetInventoryAgreement("资产清查一致说明"),
    ActualAddressAssetInventory("资产清查实际地址"),
    CertificateAssetInventory("资产清查证明人"),
    AssetInventoryConfirmConsistency("资产清查确认一致"),
    ExpertWorkOverview("外聘专家工作概况"),
    BusinessScope("经营范围"),
    StatementPurposeEntrustment("委托目的描述"),
    HisRightDeclare("他项权申报"),
    BestUseDesc("最佳利用方式"),
    DecalreFormTypeAll("申报启用表单类型"),
    Co_ownership("共有权情况"),
    AssessArea("评估面积"),
    surveyExamineDate("现场查勘期"),
    surveyExamineCreate("现场查勘人员"),
    HomeWorkEndTime("作业结束时间"),
    HomeWorkStartTime("作业开始时间"),
    AssistanceStaff("协助工作人员"),
    SetUse("设定用途"),
    rentalPossessionDesc("出租或占用情况"),
    HouseType("房产类型"),

    JudgeBuildResultSurveySheet("估价结果一览表"),
    JudgeBuildResultSurveySheet2("估价结果一览表2"),
    EquityStatusObjectSheet("估价对象权属"),
    JudgeObjectAreaStatusSheet("估价对象区位状况"),
    JudgeObjectLandStateSheet("估价土地实体状况"),
    JudgeBuildLandStateSheet("估价建筑物实体状况"),
    JudgeObjectEquitySheet("估价对象权益状况"),
    judgeSummarySheet("估价汇总表"),
    DetailedCalculationProcessValuationObject("估价对象详细测算过程"),

    PRINCIPAL("估价委托人"),
    EvaluationThink("估价技术思路"),
    PrincipalInfo("估价委托人信息"),
    PrincipalDescribe("估价对象描述"),
    DeterminationMarketValueValuationObject("估价对象市场价值的确定"),
    HotTip("特别提示"),

    RegisteredRealEstateValuer("注册房产估价师"),
    RegisteredRealEstateValuerAndNumber("注册房产估价师及注册号"),
    XIEHE_organizationInfo("房地产估价机构信息"),
    XIEHE_organizationName("机构名称"),
    XIEHE_RealEstateValuationAgency("房地产估价机构"),
    XIEHE_organizationAddress("机构住所"),
    XIEHE_legalRepresentative("机构法定代表人"),
    XIEHE_registeredNo("机构工商注册号"),
    XIEHE_organizationRank("机构资质等级"),
    XIEHE_certificateNo("机构证书号"),
    XIEHE_certificateEffectiveDate("机构证书有效期"),

    SelectionValuationMethod("估价对象选择估价方法"),
    NotSelectionValuationMethod("估价对象不选择估价方法"),
    EvaluationExpression("分类评估单价计算试"),
    EvaluationMethodResult("分类评估方法结果"),

    StatutoryOptimumReimbursement("法定优先受偿款"),
    StatutoryPriorityAmountTotal("法定优先受偿款总金额"),

    collateralFound("担保物权设立情况"),
    ComputationProcess("主要计算过程"),
    SelectionApplicationParameters("相关参数选取与应用"),
    StatusBuildingRightsInterests("建筑物权益状况"),
    TaxBurden("税费负担"),
    PaymentMethod("付款方式"),

    EVALUATION_HYPOTHESIS("评估假设"),
    EVALUATION_BASIS("评估依据"),
    EVALUATION_PRINCIPLE("评估原则"),
    ANALYSIS_CATEGORY_LIQUIDITY("变现能力分析"),
    LIQUIDATION_ANALYSIS("变现分析税费"),
    ANALYSIS_CATEGORY_RISK("风险提示"),
    ReportArea("出具报告区域"),


    JUDGEOBJECTPRINCIPALCOPYSHEET("估价委托书复印件"),
    EstimatedObjectLocationDiagram("估价对象位置示意图"),
    Valuation_Target_Live_Photos("估价对象实况照片"),
    Copies_the_Ownership_Certificate_the_Valuation_Object("估价对象权属证明复印件"),
    Special_documentation_referenced_in_valuation("估价中引用的专用文件资料"),
    CopyBusinessLicenseRealEstateValuationAgency("房地产估价机构营业执照复印件"),
    CopyQualificationCertificateRealEstateValuationInstitution("房地产估价机构资质证书复印件"),
    RegisteredRealEstateValuerValuationInstitution("注册房地产估价师注册证书复印件"),

    BACKGROUND_ANALYSIS_DEVELOPMENT("社会经济发展概况"),
    BACKGROUND_ANALYSIS_GENERAL("房地产市场总体概况"),
    BACKGROUND_ANALYSIS_MARKET("同类房地产市场状况"),
    BACKGROUND_ANALYSIS_BLOCK("同类房地产市场版块状况"),
    BACKGROUND_ANALYSIS_PROPERTY("背景估价对象区域物业总体状况"),

    //------------------------||待废除--------------------------
    UnitPriceValuator("估价对象的单价"),
    EvaluationPriceCateGory("分类评估单价"),
    PrincipalLegalRepresentative("委托人法定代表人"),
    ;

    private String key;

    private String name;


    private BaseReportFieldEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    private BaseReportFieldEnum(String name) {
        this.name = name;
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
