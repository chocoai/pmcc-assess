package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 基础
 */
public enum ReportFieldEnum implements Serializable {
    ReportHouseQrCode("房产二维码"),
    ReportASSETSQrCode("资产评估二维码"),
    ReportLandQrCode("土地评估二维码"),

    ScopePropertyExplain("财产范围说明"),
    TotalRealEstatePrice("房地产总价"),
    CapitalizationAmount("房地产总价大写金额"),
    totalAmountMortgageValue("抵押价值总金额"),
    totalAmountMortgageValueCapitalization("抵押价值总金额大写"),
    HisRightType("他权类别"),
    PROPERTY_TYPE("物业类型"),
    HisRightTypeAndDetail("他权类别及明细"),
    ExpertWorkOverview("外聘专家工作概况"),
    BusinessScope("经营范围"),
    BestUseDesc("最佳利用方式"),
    DecalreFormTypeAll("申报启用表单类型"),
    surveyExamineDate("现场查勘期"),
    surveyExamineCreate("现场查勘人员"),


    rentalPossessionDesc("出租或占用情况"),
    HouseType("房产类型"),

    JudgeObjectWeights("委估对象号权重"),
    JudgeObjectNumber("委估对象号"),
    JudgeBuildResultSurveySheet("估价结果一览表"),
    JudgeObjectFactorSheet("估价对象因素表"),
    JudgeBuildResultSurveySheetNotBeLocated("估价结果一览表不含坐落"),
    EquityStatusObjectSheet("估价对象权属"),
    EquityStatusObjectCheckListSheet("估价对象权属明细清单"),
    EquityStatusObjectNumber("权属证号"),
    JudgeObjectAreaStatusSheet("估价对象区位状况"),
    JudgeObjectLandStateSheet("估价土地实体状况"),
    JudgeBuildLandStateSheet("估价建筑物实体状况"),
    JudgeObjectEquitySheet("估价对象权益状况"),
    judgeSummarySheet("估价汇总表"),
    DetailedCalculationProcessValuationObject("估价对象详细测算过程"),


    DeterminationMarketValueValuationObject("估价对象市场价值的确定"),

    SelectionValuationMethod("估价对象选择估价方法"),
    SelectionValuationMethodFoundation("选择估价方法的依据"),
    NotSelectionValuationMethod("估价对象不选择估价方法"),
    EvaluationMethodResult("分类评估方法结果"),


    ComputationProcess("主要计算过程"),
    SelectionApplicationParameters("相关参数选取与应用"),

    EVALUATION_HYPOTHESIS("评估假设"),
    EVALUATION_BASIS("评估依据"),
    EVALUATION_PRINCIPLE("评估原则"),
    ANALYSIS_CATEGORY_LIQUIDITY("变现能力分析"),

    LIQUIDATION_ANALYSIS("变现分析税费"),
    ANALYSIS_CATEGORY_RISK("风险提示"),
    ReportArea("出具报告区域"),

    ENUM_CITY_RESOURCE_STATUS("城市资源状况"),//模板 （按区、县、或扩大区域护模板，定期修改）
    ENUM_REAL_ESTATE_SYSTEM("房地产制度"),//模板 （按省级行政区护模板，定期修改）
    ENUM_land_MARKET_CONDITION("地产状况"),//模板 （按区、县、或扩大区域护模板，定期修改）
    ENUM_HOUSE_MARKET_CONDITION("房地产状况"),//模板 （按区、县、或扩大区域护模板，定期修改）
    ENUM_INDUSTRIAL_POLICY("产业政策"),//模板 （按区、县、或扩大区域护模板，定期修改）
    ENUM_TAX_POLICY("税收政策"),//模板 （按省级行政区护模板，定期修改）
    ENUM_FINANCIAL_POLICY("金融政策"),//模板 （按省级行政区护模板，定期修改）
    ENUM_URBAN_PLANNING_DEVELOPMENT_GOALS("城市规划与发展目标"),//模板 （按区、县、或扩大区域护模板，定期修改）
    ENUM_URBAN_SOCIAL_ECONOMIC_DEVELOPMENT("城市社会经济发展状况"),//模板 （按区、县、或扩大区域护模板，定期修改）
    ENUM_SUMMARY_GENERAL_FACTORS("一般因素总结"),//模板
    ENUM_MARKET_RISK("市场风险"),//模板 ${市场风险，区（县）或扩大的区（县）市设模板}
    ENUM_REALIZATION_RISK("变现风险"),//模板
    ENUM_TAX_RISK("税收风险"),//模板

    ReportGeneralFactorsSetting("一般因素配置"),



    AssetInventoryFile("清查证明文件"),
    DeclareRecordFile("申报证书复印件"),
    JudgeObjectPrincipalCopySheet("估价委托书复印件"),
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

    ;
    private String name;

    private ReportFieldEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldEnum getEnumByName(String name) {
        for (ReportFieldEnum e : ReportFieldEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
