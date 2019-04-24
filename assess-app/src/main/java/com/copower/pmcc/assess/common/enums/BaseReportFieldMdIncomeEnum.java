package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/2/14 16:11
 * @description:报告模板字段(收益法)
 */
public enum BaseReportFieldMdIncomeEnum implements Serializable {
    PropertyRightCertificateIncomeLaw("收益法申报产权证类型"),// 房产证、不动产证
    IncomeSetUse("收益法设定用途"),//方案中设定用途
    TerminationDateLand("收益法土地终止日期"),//土地证终止日期
    IncomeValuePoint("收益法价值时点"),//方案价值时点
    IncomeSurplusLandUseYear("收益法剩余土地使用年限"),
    IncomeCompletionTime("收益法竣工时间"),//楼栋竣工时间
    IncomeUsedLife("收益法已使用年限"),//价值时点-竣工时间
    IncomebuildingStructureType("收益法建筑结构类别"),//楼栋建筑结构类别
    IncomeBuildEconomicLife("收益法经济耐用年限"),//楼栋建筑使用年限
    IncomeHouseSurplusYear("收益法房产剩余年限"),
    IncomeYears("收益法收益年限"),//收益法房产剩余年限 收益法剩余土地使用年限 中最小值
    IncomeGetMdCompare("收益法中的比较法"),

    IncomeRegionalCities("收益法区域城市"),//当前所在区域的城市
    IncomeAssessmentArea("收益法评估面积"),//方案中评估面积
    TenancyrestrictionRemark("收益法租赁限制说明"),
    OtherIncomeExplain("收益法其他收入说明"),
    RestrictionExplain("收益法出租率说明"),
    IncomeDepositExplain("收益法押金说明"),
    TransactionTaxeFeeExplain("收益法其它交易费说明"),
    RentalGrowthRateExplain("收益法租金增长率说明"),

    Rentals("收益法出租率"),
    MaintenanceCostRatio("收益法维修保养费率"),
    ManagementCostTax("收益法管理费率"),
    InsurancePremiumTax("收益法保险费率"),
    IncomeAdditionalRatio("收益法租赁税率"),
    IncomePropertyTax("收益法房产税"),// ==收益法房产税税率
    IncomestampTax("收益法印花税"),
    IncomeTransactionTax("收益法其它交易费率"),
    RentGrowthForecast("收益法租金增长率"),
    IncomeLocalEducationRatio("收益法地方教育费附加税率"),
    IncomeEducationRatio("收益法教育费附加税率"),
    IncomeconstructionTaxRatio("收益法城建税税率"),
    IncomesalesTaxRatio("收益法增值税率"),
    IncomesalesTaxRatioCorrect("收益法增值修正税率"),
    IncomePropertyTaxCorrect("收益法房产税修正税率"),
    IncomeCityLocalEducationTaxCorrect("城市地方教育修正"),
    LandUseTax("收益法土地使用税"),
    IncomeAdditionalCapture("收益法有效收缴率"),

    LandUseCost("收益法土地使用税费"),
    IncomeAdditionalRatioCost("收益法租赁税费"),
    IncomeTransaction("收益法其它相关费用"),
    InsurancePremiumCost("收益法年保险费"),
    ManagementCost("收益法年管理费"),
    MaintenanceCost("收益法年维修费"),
    OtherIncome("收益法其它收入"),
    ReplacementCost("收益法重置成本"),
    GrossIncome("收益法年有效毛收入"),
    MonthRentalIncome("收益法月租金收入"),
    AnnualOperatingCost("收益法年营运费用"),
    AnnualNetIncome("收益法年净收益"),

    IncomeOpportunityCostReamrk("收益法机会成本说明"),
    IncomeRiskCompensation("收益法投资风险补偿"),
    IncomeManageCompensation("收益法管理负担补偿"),
    IncomeLiquidCompensation("收益法缺乏流动性补偿"),
    IncomeFinancingAdvantage("收益法投资带来的优惠"),

    IncomePayBack("收益法报酬率"),
    IncomePrice("收益法测算价格"),
    IncomeUnitPriceConnotation("收益法单价内涵"),
    IncomeMethodFormula("收益法计算公式"),
    IncomeDetermineRentalWay("收益法确定月租金方式"),
    MonthNumber("收益法出租月份数"),

    //------------------------------------------------------

    YearDepositRate("一年期定期存款利率"),
    EffectiveIncomeFormula("有效收入公式"),

    IncomeMethodPriceCalculatingSheet("收益法价格测算表")
    ;
    private String name;
    BaseReportFieldMdIncomeEnum(String name) {
        this.name = name;
    }

    public static BaseReportFieldMdIncomeEnum getEnumByName(String name) {
        for (BaseReportFieldMdIncomeEnum e : BaseReportFieldMdIncomeEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
