package com.copower.pmcc.assess.common.enums.report;

/**
 * Created by zch on 2020-3-18.
 * 报告模板字段 假设法
 */
public enum ReportFieldDevelopmentEnum {
    Development_projectConstructionPeriod("假设法项目建设期"),
    Development_projectConstructionPeriod2("假设法开发周期"),
    Development_developedYear("假设法已开发时间"),
    Development_remainingDevelopmentYear("假设法剩余开发时间"),
    Development_constructionSubTotal("假设法工程建设成本总计"),
    Development_unforeseenExpensesRate("假设法不可预见费率"),
    Development_unforeseenExpensesTotal("假设法不可预见费总计"),
    Development_LandGetCostTotal("假设法在建工程取得成本总计"),
    Development_LandGetCostTotal2("假设法土地取得成本总计"),
    Development_LandGetRelevantValue("假设法在建工程修复费"),
    Development_managementExpenseRate("假设法管理费率"),
    Development_managementExpenseCorrectRate("假设法管理费计算率"),
    Development_managementExpenseTotal("假设法管理费总计"),
    Development_salesFeeRate("假设法销售费率"),
    Development_salesFeeTotal("假设法销售费总计"),
    Development_TotalSaleableAreaPrice("假设法预期销售合计"),
    Development_salesFeeExplain("假设法销售费用说明"),
    Development_infrastructureCost("假设法基础设施建设费"),
    Development_infrastructureCostTotal("假设法基础设施建设费总计"),
    Development_infrastructureMatchingCost("假设法公共配套设施建设费"),
    Development_infrastructureMatchingCostTotal("假设法公共配套设施建设费总计"),
    Development_landGetRelevant("假设法土地取得附加成本"),
    Development_devDuring("假设法开发期间税费"),
    Development_devDuringTotal("假设法开发期间税费总计"),
    Development_otherEngineeringCost("假设法其他工程费"),
    Development_otherEngineeringCostTotal("假设法其他工程费总计"),
    Development_reconnaissanceDesignTotal("假设法勘察设计和前期工程费总计"),
    Development_reconnaissanceDesign("假设法勘察设计和前期工程费"),
    Development_interestInvestmentRate("假设法投资利息率"),
    Development_interestInvestmentCorrectRate("假设法投资计算利率"),
    Development_interestInvestment("假设法投资利息总计"),
    Development_investmentProfitRate("假设法投资利润率"),
    Development_investmentProfitCorrectRate("假设法投资利润计算率"),
    Development_investmentProfit("假设法投资利润总计"),
    Development_projectDevelopmentIncomeValue("假设法增值税金及附加总计"),
    Development_LandPriceCorrectRate("假设法地价计算率"),
    Development_LandPriceCorrectValue("假设法地价总计"),
    Development_LandPriceValue("假设法地价计算值"),
    Development_LandPriceCoefficient("假设法地价修正系数"),
    Development_assessPrice("假设法委估对象单价"),
    Development_Price("假设法修正后单价"),
    Development_OtherAmendments("假设法其他修正值"),
    Development_OtherAmendmentsRemark("假设法其他修正说明"),
    Development_DevelopmentDegreeCorrectionValue("假设法开发程度修正"),
    Development_DevelopmentDegreeCorrectionValueRemark("假设法开发程度修正说明"),
    Development_AmendmentStatusRights("假设法权力状况修正值"),
    Development_AmendmentStatusRightsRemark("假设法权利状况修正说明"),
    Development_extraterritorial_reality("假设法宗地外设定"),
    Development_intra_territorial_reality("假设法宗地内设定"),
    Development_region("假设法区域"),
    Development_EconomicIndicators("假设法规划经济指标"),
    Development_developed_value_sheet("假设法开发价值一览表"),
    Development_PriceForecast("假设法售价预测"),
    Development_constructionSubtotal_ComputationalProcess("假设法建安工程费一览表"),
    Development_deedCorrecting("假设法契税率"),
    Development_transactionCostCorrecting("假设法交易费率"),
    Development_landIncrementTax("假设法土地增值税率"),
    Development_Land_SetUse("假设法土地设定用途"),
    Development_SetUse("假设法设定用途"),
    Development_total_saleableArea("假设法总可售面积"),//f18
    Development_Land_Area("假设法总土地面积"),
    Development_reconnaissanceDesignRate("假设法勘察设计和前期工程费率"),
    Development_constructionInstallationEngineeringFee("假设法建筑安装工程费"),
    Development_projectDevelopmentIncomeCorrectRate("假设法项目开发税及附加计算率"),
    Development_LandGetCost("假设法土地取得成本"),
    Development_constructionInstallationEngineeringFee_Basis("假设法建安工程费用计算依据"),
    Development_TotalCompletedPriceRealEstateDevelopment("假设法不动产开发完成总价"),
    Development_total_area("假设法总面积"),
    Development_PublicTrialRealEstateComputing("假设法不动产计算公试"),
    Development_Planning_constraints("假设法规划限制条件"),
    Development_Relevant_basis_technical_economic_indicators("假设法技术经济指标相关依据"),

    Development_infrastructureCost_FileName("假设法基础设施费文件名称"),//暂时不确定删除
    Development_constructionSubtotalContent("假设法建设成本内容"),//暂时不确定删除
    Development_SalesTaxAndAdditional("假设法增值税及附加"),//暂时不确定删除
    Development_formula("假设法计算公式"),//暂时不确定删除
    ;
    private String name;
    private ReportFieldDevelopmentEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldDevelopmentEnum getEnumByName(String name) {
        for (ReportFieldDevelopmentEnum e : ReportFieldDevelopmentEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
