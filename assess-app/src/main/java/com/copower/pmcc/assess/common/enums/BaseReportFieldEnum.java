package com.copower.pmcc.assess.common.enums;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段
 */
public enum BaseReportFieldEnum implements Serializable {
    ReportNumber("文号"),
    queryCode("查询码"),
    recordNo("备案号"),
    recordDate("备案日期"),
    ReportNumber2("报告编号"),//文号中的具体编号
    ReportQrcode("报告二维码"),
    ReportHouseQrCode("房产二维码"),
    ReportASSETSQrCode("资产评估二维码"),
    ReportLandQrCode("土地评估二维码"),
    ReportUnitString("报告使用单位"),
    RegistrationDate("登记时间"),
    ReportingCategories("报告类别"),
    ValuationProjectName("估价项目名称"),
    ValuationProjectName2("司法估价项目名称"),
    ReportIssuanceDate("报告出具日期"),
    ValueType("价值类型"),
    ValueTypeDesc("价值类型描述"),
    DelegatePurpose("委托目的"),
    CertificationPurpose("证载用途"),
    CertificationPurpose2("小微贷证载用途"),
    ScopePropertyExplain("财产范围说明"),
    PracticalUse("实际用途"),
    ValueTimePoint("价值时点"),
    ValueTimePointRemark("价值时点说明"),
    EvaluationMethod("评估方法"),
    TotalRealEstatePrice("房地产总价"),
    CapitalizationAmount("房地产总价大写金额"),
    totalAmountMortgageValue("抵押价值总金额"),
    totalAmountMortgageValueCapitalization("抵押价值总金额大写"),
    HisRightType("他权类别"),
    PROPERTY_TYPE("物业类型"),
    HisRightTypeAndDetail("他权类别及明细"),
    ExpertWorkOverview("外聘专家工作概况"),
    BusinessScope("经营范围"),
    StatementPurposeEntrustment("委托目的描述"),
    BestUseDesc("最佳利用方式"),
    DecalreFormTypeAll("申报启用表单类型"),
    Co_ownership("共有权情况"),
    AssessTotal("评估总价"),
    AssessTotalRMB("评估总价大写"),
    AssessArea("评估面积"),
    BuildArea("建筑面积"),
    CoverArea("套内建筑面积"),
    AssessPrice("评估单价"),
    NetAssessmentOne("建行个贷评估净值one"),
    NetAssessmentTwo("建行个贷评估净值two"),
    NetAssessmentOther("建行个贷其它"),
    NetAssessmentGroundNum("建行个贷丘地号"),
    AssessPriceClassification("评估总价分述"),
    surveyExamineDate("现场查勘期"),
    surveyExamineCreate("现场查勘人员"),
    HomeWorkEndTime("作业结束时间"),
    HomeWorkStartTime("作业开始时间"),
    AssistanceStaff("协助工作人员"),
    SetUse("设定用途"),
    rentalPossessionDesc("出租或占用情况"),
    HouseType("房产类型"),
    Seat("坐落"),
    SetUse2("小微贷设定用途"),
    Seat2("小微贷坐落"),
    CERT_NAME2("小微贷权证号"),
    floor2("小微贷楼层"),
    floorCount2("小微贷总层数"),
    housingStructure2("小微贷房屋结构"),
    housingStructure("建行个贷房屋结构"),
    UsageStatus2("小微贷使用状况"),
    CERT_NAME1("建行个贷权证号"),
    CERT_NAME3("金融机构权证号"),
    ICBCValuationCaseInformationSheet("工行估价案例情况表"),
    floorCount("建行个贷总层数"),
    houseNature("房屋性质"),
    ownership("房屋所有权人"),
    ArchivesDepositNumber("档案保管号"),
    floor("建行个贷楼层"),
    LayerNumber("建行个贷层户数"),
    unitType("户型"),
    ThisYear("当前年份"),//今年
    estateName("楼盘名称"),
    Orientation("建行个贷朝向"),
    StoreyHeight("建行个贷层高"),
    BeCompletedTimeGetInteger("建成年代"),
    FillingUnit("填发单位"),
    exteriorWallDecorate("建行个贷外墙装饰"),
    LobbyDecorate("建行个贷大堂装饰"),
    FoundationAndWall("建行个贷地基及墙面"),
    AttachmentReark("附记"),
    DecorationStatus("装修状况"),
    UsageStatus("建行个贷使用状况"),
    EntrustedUnit("委托单位"),
    HuxingLayout("建行个贷户型及布局"),
    CCB_Pre_Evaluation_Data_Form("建行预评数据表格"),
    LandArea("土地面积"),
    LandCertificateField1("土地证号"),
    LandCertificateField2("土地所有权人"),
    LandCertificateField3("土地证载用途"),
    LandCertificateField4("土地权利性质"),
    LandCertificateField5("土地分摊面积"),
    LandCertificateField6("土地终止日期"),
    LandCertificateField7("土地登记机关"),
    LandCertificateField8("土地登记日期"),
    JudgeObjectLoactionField1("建行个贷位置"),
    JudgeObjectLoactionField2("建行个贷购物条件"),
    JudgeObjectLoactionField3("建行个贷公交便捷度"),
    JudgeObjectLoactionField4("建行个贷交通通达度"),
    JudgeObjectLoactionField5("建行个贷地铁"),
    JudgeObjectLoactionField6("建行个贷基础设施"),
    JudgeObjectLoactionField6B("建行个贷基础外部设施"),
    JudgeObjectLoactionField7("建行个贷教育设施"),
    JudgeObjectLoactionField8("建行个贷医疗设施"),
    JudgeObjectLoactionField9("建行个贷自然环境"),
    JudgeObjectLoactionField10("建行个贷人文环境"),
    JudgeObjectDamagedDegreeField1("建行个贷门装修情况"),
    JudgeObjectDamagedDegreeField2("建行个贷窗装修情况"),
    JudgeObjectDamagedDegreeField3("建行个贷地面装修情况"),
    JudgeObjectDamagedDegreeField4("建行个贷内墙装修情况"),
    JudgeObjectDamagedDegreeField5("建行个贷天棚装修情况"),
    JudgeObjectDamagedDegreeField6("建行个贷卫生间装修情况"),
    JudgeObjectDamagedDegreeField7("建行个贷厨房装修情况"),
    JudgeObjectDamagedDegreeField8("建行个贷维护保养状况"),
    JudgeObjectOtherField1("建行个贷个别景观"),
    JudgeObjectOtherField2("建行个贷临街状态"),
    JudgeObjectOtherField3("建行个贷楼间距"),
    JudgeObjectOtherField4("建行个贷绿地率"),
    JudgeObjectOtherField5("建行个贷建筑覆盖率"),
    JudgeObjectOtherField6("建行个贷停车场"),
    JudgeObjectOtherField7("建行个贷物业管理"),
    JudgeObjectOtherField8("建行个贷成新率"),
    AversionFacility("建行个贷厌恶设施"),

    JudgeBuildResultSurveySheet("估价结果一览表"),
    JudgeBuildResultSurveySheet2("估价结果一览表不含坐落"),
    JudgeBuildResultSurveySheet3("司法估价结果一览表"),
    EquityStatusObjectSheet("估价对象权属"),
    EquityStatusObjectNumber("权属证号"),
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
    PrincipalDataDescribe("估价信息描述"),
    DeterminationMarketValueValuationObject("估价对象市场价值的确定"),
    HotTip("特别提示"),
    HotTip2("建行个贷特别提示"),
    HotTipBank("金融机构特别提示"),
    HotTip4("工商银行特别提示"),
    Atypism2("小微贷不一致"),

    RegisteredRealEstateValuerAndNumber("注册估价师及注册号"),
    RegisteredHouseRealEstateValuerAndNumber("注册房产估价师及注册号"),
    RegisteredRealEstateValuer("注册估价师"),
    XIEHE_organizationHouseInfo("房地产估价机构信息"),
    XIEHE_organizationInfo("估价机构信息"),
    XIEHE_organizationName("机构名称"),
    XIEHE_RealEstateValuationAgencyHouse("房地产估价机构"),
    XIEHE_RealEstateValuationAgency("估价机构"),
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
    StatutoryOptimumReimbursement3("其他法定优先受偿款"),
    StatutoryOptimumReimbursement2("拖欠的建设工程价款"),
    StatutoryOptimumReimbursement1("已抵押担保债权数额"),
    StatutoryPriorityAmountTotal("法定优先受偿款总金额"),

    ComputationProcess("主要计算过程"),
    SelectionApplicationParameters("相关参数选取与应用"),

    EVALUATION_HYPOTHESIS("评估假设"),
    EVALUATION_BASIS("评估依据"),
    EVALUATION_PRINCIPLE("评估原则"),
    ANALYSIS_CATEGORY_LIQUIDITY("变现能力分析"),
    ANALYSIS_CATEGORY_LIQUIDITY3("工商银行变现能力分析"),
    ANALYSIS_CATEGORY_LIQUIDITY2("建行个贷变现能力分析"),
    LIQUIDATION_ANALYSIS("变现分析税费"),
    ANALYSIS_CATEGORY_RISK("风险提示"),
    ReportArea("出具报告区域"),


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

    Development_projectConstructionPeriod("假设法开发周期"),
    Development_totalSaleableAreaPrice("假设法总可售面积售价"),
    Development_saleableArea("假设法可售面积"),//f16
    Development_unsaleableBuildingArea("假设法不可售建筑面积"),//f17
    Development_total_saleableArea("假设法总可售面积"),//f18
    Development_reconnaissanceDesignRate("假设法勘察设计和前期工程费率"),
    Development_reconnaissanceDesign("假设法勘察设计和前期工程费"),
    Development_constructionInstallationEngineeringFee("假设法建筑安装工程费"),
    Development_infrastructureCost("假设法基础设施建设费"),
    Development_infrastructureMatchingCost("假设法公共配套设施建设费"),
    Development_devDuring("假设法开发期间税费"),
    Development_otherEngineeringCost("假设法其他工程费"),
    Development_constructionSubtotal("假设法工程建设成本小计"),
    Development_constructionSubtotal3("假设法工程建设成本"),
    Development_constructionSubtotal2("假设法建设成本"),
    Development_unforeseenExpensesTax("假设法不可预见费率"),
    Development_unforeseenExpenses("假设法不可预见费"),
    Development_LandGetCost("假设法土地取得成本"),//
    Development_managementExpenseRate("假设法管理费率"),
    Development_managementExpenseCorrectRate("假设法管理费计算率"),
    Development_managementExpense("假设法管理费额"),
    Development_salesFeeTax("假设法销售费率"),
    Development_salesFee("假设法销售费额"),
    Development_interestInvestmentRate("假设法投资利率"),
    Development_interestInvestmentCorrectRate("假设法投资计算利率"),
    Development_interestInvestment("假设法投资利息"),
    Development_investmentProfitRate("假设法投资利润率"),
    Development_investmentProfitCorrectRate("假设法投资利润计算率"),
    Development_investmentProfit("假设法投资利润"),
    Development_projectDevelopmentIncomeCorrectRate("假设法项目开发税及附加计算率"),
    Development_projectDevelopmentIncomeValue("假设法项目开发税及附加计算值"),
    Development_LandPriceCorrectRate("假设法地价计算率"),
    Development_LandPriceCorrectValue("假设法地价计算矫正值"),
    Development_LandPriceValue("假设法地价计算值"),
    Development_LandPriceCoefficient("假设法地价修正系数"),
    Development_assessPrice("假设法委估土地单价"),
    Development_Price("假设法评估单价"),

    Development_Land_SetUse("假设法土地设定用途"),
    Development_SetUse("假设法设定用途"),
    Development_RelevantBasisEconomicIndicators("假设法技术经济指标相关依据"),
    Development_PlanningConstraints("假设法规划限制条件"),
    Development_region("假设法区域"),
    Development_EconomicIndicators("假设法估价对象技术经济指标"),
    Development_PriceForecast("假设法售价预测"),
    Development_constructionInstallationEngineeringFee_Basis("假设法建安工程费用计算依据"),
    Development_infrastructureCost_FileName("假设法基础设施费文件名称"),
    Development_constructionSubtotalContent("假设法建设成本内容"),
    Development_constructionSubtotal_ComputationalProcess("假设法建安成本计算过程"),
    Development_deedCorrecting("假设法契税率"),
    Development_transactionCostCorrecting("假设法交易费率"),
    Development_LandAcquisitionCost("假设法地价总额"),//土地取得成本 同
    Development_LandAcquisitionCostTax("假设法土地取得税费"),
    Development_SalesTaxAndAdditional("假设法增值税金及附加"),
    Development_landIncrementTax("假设法土地增值税率"),
    Development_TotalCompletedPriceRealEstateDevelopment("假设法不动产开发完成总价"),
    Development_total_area("假设法总面积"),
    Development_OtherAmendments("假设法其他修正值"),
    Development_OtherAmendmentsRemark("假设法其他修正说明"),
    Development_DevelopmentDegreeCorrectionValue("假设法开发程度修正值"),
    Development_AmendmentStatusRights("假设法宗地权利状况修正值"),
    Development_AmendmentStatusRightsRemark("假设法宗地权利状况修正说明"),


    MarketCost_JudgeObject("成本法估价对象"),
    MarketCost_Merge_JudgeObject("成本法合并估价对象"),
    MarketCost_Method("成本法土地测算方法"),
    MarketCost_region("成本法区域"),
    MarketCost_developYearNumberTax("成本法开发周期"),
    MarketCost_constructionInstallationEngineeringFee("成本法建筑安装工程费"),
    MarketCost_constructionInstallationEngineeringFee_Sheet("成本法建安工程费用计算表"),
    MarketCost_constructionInstallationEngineeringFee_Basis("成本法建筑安装工程费计费依据"),
    MarketCost_extraterritorial_reality("成本法宗地外实际"),
    MarketCost_intra_territorial_reality("成本法宗地内实际"),
    MarketCost_extraterritorial_setting("成本法宗地外设定"),
    MarketCost_intra_territorial_setting("成本法宗地内设定"),
    MarketCost_GroundFloor_AreaCounted_volume_ratio("成本法地上计入容积率建筑面积"),
    MarketCost_Planning_land_area_construction("成本法规划建设净用地面积"),
    MarketCost_EconomicIndicatorsField1("成本法地上计入容积率的建筑面积"),
    MarketCost_AssessBuildArea("成本法评估总建筑面积"),
    MarketCost_AssessUseLandArea("成本法评估用地面积"),
    MarketCost_landPurchasePriceExplain("成本法土地价格说明"),
    MarketCost_UnitAreaLandPrice("成本法单位面积地价"),
    MarketCost_landPurchasePrice("成本法土地购买价格"),
    MarketCost_landGetRelevantExplain("成本法土地取得税费说明"),
    MarketCost_landGetRelevant("成本法土地取得税费"),
    MarketCost_landGetRelevantRate("成本法土地取得税费率"),
    MarketCost_additionalCostLandAcquisitionExplain("成本法土地取得附加成本说明"),
    MarketCost_additionalCostLandAcquisition("成本法土地取得附加成本"),

    MarketCost_reconnaissanceDesignRate("成本法勘察设计和前期工程费率"),
    MarketCost_reconnaissanceDesign("成本法勘察设计和前期工程费"),
    MarketCost_infrastructureCost("成本法基础设施建设费"),
    MarketCost_infrastructureCostBasis("成本法基础设施建设费收费依据"),
    MarketCost_infrastructureMatchingCost("成本法公共配套设施建设费"),
    MarketCost_devDuring("成本法开发期间税费"),
    MarketCost_otherEngineeringCost("成本法其他工程费"),
    MarketCost_constructionSubtotal("成本法建设成本"),
    MarketCost_unforeseenExpenses("成本法不可预见费用"),
    MarketCost_unforeseenExpenses2("成本法不可预见费"),
    MarketCost_unforeseenExpensesRate("成本法不可预见费率"),
    MarketCost_unforeseenExpensesExplain("成本法不可预见费率用说明"),
    MarketCost_managementExpense("成本法管理费"),
    MarketCost_managementExpenseRate("成本法管理费率"),
    MarketCost_managementExpenseExplain("成本法管理费率说明"),
    MarketCost_salesFee("成本法销售费"),
    MarketCost_salesFeeRate("成本法销售费率"),
    MarketCost_salesFeeExplain("成本法销售费率说明"),
    MarketCost_interestInvestment("成本法投资利息"),
    MarketCost_interestInvestmentRate("成本法投资利息率"),
    MarketCost_interestInvestmentCorrectRate("成本法投资利息修正率"),
    MarketCost_interestInvestmentTaxExplain("成本法投资利息率说明"),
    MarketCost_salesTaxAndAdditional("成本法销售税金及附加"),
    MarketCost_salesTaxAndAdditionalRate("成本法销售税金及附加率"),
    MarketCost_salesTaxAndAdditionalExplain("成本法销售税金及附加率说明"),
    MarketCost_investmentProfitRate("成本法开发利润率"),
    MarketCost_investmentProfitCorrectRate("成本法开发利润修正率"),
    MarketCost_investmentProfit("成本法开发利润"),
    MarketCost_investmentProfitTaxExplain("成本法开发利润率说明"),
    MarketCost_constructionAssessmentValue2Rate("在建工程评估价值修正率"),
    MarketCost_constructionAssessmentValue2("在建工程评估价值2"),
    MarketCost_constructionAssessmentValue("在建工程评估价值"),
    MarketCost_developBuildArea("成本法开发建筑面积"),
    MarketCost_EstateLandPrice("成本法楼面土地单价"),
    MarketCost_landGetCostTotal("成本法土地取得成本"),
    MarketCost_Degree_land_development("成本法土地开发程度"),
    MarketCost_ResidueRatio("成本法综合成新率"),
    MarketCost_ResidueRatio_method("成本法成新率方法选择"),
    MarketCost_ResidueRatio_remark("成本法成新率选择方法说明"),
    MarketCost_ResidueRatio_formula("成本法成新率计算公式"),
    MarketCost_ResidueRatio_value("成本法成新率计算公式分别代值"),
    MarketCost_CompleteCostValue("成本法完全成本计算值"),
    MarketCost_constructionAssessmentPriceCorrecting2("成本法单价源"),
    MarketCost_constructionAssessmentPriceCorrecting("成本法单价"),
    MarketCost_CalculatingMethodEngineeringCostSheet("成本法建安工程费用计算表"),

    ;
    private String name;
    private BaseReportFieldEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
