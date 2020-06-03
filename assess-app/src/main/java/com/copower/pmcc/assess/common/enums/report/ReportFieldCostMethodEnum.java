package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 成本法
 */
public enum ReportFieldCostMethodEnum implements Serializable {
    MarketCost_JudgeObject("成本法估价对象"),
    MarketCost_Merge_JudgeObject("成本法合并估价对象"),
    MarketCost_Method("成本法土地测算方法"),
    MarketCost_region("成本法区域"),
    MarketCost_Unilateral_cost("成本法单方造价"),
    MarketCost_developYearNumberTax("成本法开发周期"),
    MarketCost_constructionInstallationEngineeringFee("成本法建筑安装工程费"),
    MarketCost_constructionInstallationEngineeringFeeTotal("成本法建筑安装工程费总计"),
    MarketCost_constructionInstallationEngineeringFee_Basis("成本法建筑安装工程费计费依据"),
    MarketCost_CalculatingMethodEngineeringCostSheet("成本法建安工程费用计算表"),
    MarketCost_extraterritorial_reality("成本法宗地外实际"),
    MarketCost_intra_territorial_reality("成本法宗地内实际"),
    MarketCost_extraterritorial_setting("成本法宗地外设定"),
    MarketCost_intra_territorial_setting("成本法宗地内设定"),
    MarketCost_GroundFloor_AreaCounted_volume_ratio("成本法地上计入容积率建筑面积"),
    MarketCost_Planning_land_area_construction("成本法规划建设净用地面积"),
    MarketCost_EconomicIndicatorsField1("成本法评估面积"),
    MarketCost_AssessBuildArea("成本法评估总建筑面积"),
    MarketCost_AssessUseLandArea("成本法评估用地面积"),
    MarketCost_landPurchasePriceExplain("成本法土地价格说明"),
    MarketCost_UnitAreaLandPrice("成本法单位面积地价"),
    MarketCost_Assessment_land_use_right_area("成本法分摊土地使用权面积"),
    MarketCost_landPurchasePrice("成本法土地购买价格"),
    MarketCost_landGetRelevantExplain("成本法土地取得税费说明"),
    MarketCost_landGetRelevant("成本法土地取得税费"),
    MarketCost_landGetRelevantRate("成本法土地取得税费率"),
    MarketCost_additionalCostLandAcquisitionExplain("成本法土地取得附加成本说明"),
    MarketCost_additionalCostLandAcquisition("成本法土地取得附加成本"),

    MarketCost_reconnaissanceDesignRate("成本法勘察设计和前期工程费率"),
    MarketCost_reconnaissanceDesign("成本法勘察设计和前期工程费"),
    MarketCost_reconnaissanceDesignTotal("成本法勘察设计和前期工程费总计"),
    MarketCost_infrastructureCost("成本法基础设施建设费"),
    MarketCost_infrastructureCostTotal("成本法基础设施建设费总计"),
    MarketCost_infrastructureCostBasis("成本法基础设施建设费收费依据"),
    MarketCost_infrastructureMatchingCost("成本法公共配套设施建设费"),
    MarketCost_infrastructureMatchingCostTotal("成本法公共配套设施建设费总计"),
    MarketCost_devDuring("成本法开发期间税费"),
    MarketCost_devDuringTotal("成本法开发期间税费总计"),
    MarketCost_otherEngineeringCost("成本法其他工程费"),
    MarketCost_otherEngineeringCostTotal("成本法其他工程费总计"),
    MarketCost_constructionSubtotal("成本法建设成本总计"),
    MarketCost_constructionSub("成本法建设成本"),
    MarketCost_unforeseenExpenses("成本法不可预见费用"),
    MarketCost_unforeseenExpensesTotal("成本法不可预见费总计"),
    MarketCost_unforeseenExpenses2("成本法不可预见费"),
    MarketCost_unforeseenExpensesRate("成本法不可预见费率"),
    MarketCost_unforeseenExpensesExplain("成本法不可预见费率用说明"),
    MarketCost_managementExpense("成本法管理费"),
    MarketCost_managementExpenseTotal("成本法管理费总计"),
    MarketCost_managementExpenseRate("成本法管理费率"),
    MarketCost_managementExpenseExplain("成本法管理费率说明"),
    MarketCost_salesFee("成本法销售费"),
    MarketCost_salesFeeTotal("成本法销售费总计"),
    MarketCost_salesFeeRate("成本法销售费率"),
    MarketCost_salesFeeExplain("成本法销售费率说明"),
    MarketCost_interestInvestment("成本法投资利息总计"),
    MarketCost_interestInvestmentRate("成本法投资利息率"),
    MarketCost_interestInvestmentCorrectRate("成本法投资利息修正率"),
    MarketCost_interestInvestmentTaxExplain("成本法投资利息率说明"),
    MarketCost_salesTaxAndAdditional("成本法销售税金及附加"),
    MarketCost_salesTaxAndAdditionalTotal("成本法销售税金及附加总计"),
    MarketCost_salesTaxAndAdditionalRate("成本法销售税金及附加率"),
    MarketCost_salesTaxAndAdditionalExplain("成本法销售税金及附加率说明"),
    MarketCost_investmentProfitRate("成本法开发利润率"),
    MarketCost_investmentProfitCorrectRate("成本法开发利润修正率"),
    MarketCost_investmentProfit("成本法开发利润总计"),
    MarketCost_investmentProfitTaxExplain("成本法开发利润率说明"),
    MarketCost_constructionAssessmentValue2Rate("在建工程评估价值修正率"),
    MarketCost_constructionAssessmentValue2("在建工程评估价值2"),
    MarketCost_constructionAssessmentValue("在建工程评估价值"),
    MarketCost_developBuildArea("成本法开发建筑面积"),
    MarketCost_EstateLandPrice("成本法楼面土地单价"),
    MarketCost_landGetCostTotal("成本法土地取得成本总计"),
    MarketCost_landGetCost("成本法土地取得成本"),
    MarketCost_Degree_land_development("成本法土地开发程度"),
    MarketCost_ResidueRatio("成本法综合成新率"),
    MarketCost_ResidueRatio_method("成本法成新率方法选择"),
    MarketCost_ResidueRatio_remark("成本法成新率选择方法说明"),
    MarketCost_ResidueRatio_formula("成本法成新率计算公式"),
    MarketCost_ResidueRatio_value("成本法成新率计算公式分别代值"),
    MarketCost_CompleteCostValue("成本法完全成本计算值"),
    MarketCost_CompleteCostTotalValue("成本法完全成本计算值总计"),
    MarketCost_constructionAssessmentPriceCorrecting("成本法单价"),
    MarketCost_constructionAssessmentPriceCorrecting2("成本法现值单价"),
    MarketCost_formula("成本法计算公式"),

    ;
    private String name;
    private ReportFieldCostMethodEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldCostMethodEnum getEnumByName(String name) {
        for (ReportFieldCostMethodEnum e : ReportFieldCostMethodEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
