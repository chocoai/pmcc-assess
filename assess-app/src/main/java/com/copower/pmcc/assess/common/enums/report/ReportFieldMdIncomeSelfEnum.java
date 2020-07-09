package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/2/14 16:11
 * @description:报告模板字段(收益法)
 */
public enum ReportFieldMdIncomeSelfEnum implements Serializable {
    PropertyRightCertificateIncomeLaw("收益法自营申报权证类型"),// 房产证、不动产证*
    IncomeSetUse("收益法自营设定用途"),//方案中设定用途*
    TerminationDateLand("收益法自营土地终止日期"),//土地证终止日期*
    IncomeValuePoint("收益法自营价值时点"),//方案价值时点*
    IncomeSurplusLandUseYear("收益法自营土地剩余使用年限"),//*
    IncomeCompletionTime("收益法自营竣工时间"),//楼栋竣工时间*
    IncomeUsedLife("收益法自营房屋已使用年限"),//价值时点-竣工时间*
    IncomebuildingStructureType("收益法自营建筑结构类别"),//楼栋建筑结构类别*
    IncomeBuildEconomicLife("收益法自营经济耐用年限"),//楼栋建筑使用年限*
    IncomeHouseSurplusYear("收益法自营剩余经济年限"),//*
    IncomeArea("收益法自营区域"),
    IncomeYears("收益法自营收益年限"),//收益法房产剩余年限 收益法剩余土地使用年限 中最小值*

    IncomeOpportunityCostReamrk("收益法自营机会成本说明"),
    IncomeRiskCompensation("收益法自营投资风险补偿"),
    IncomeManageCompensation("收益法自营管理负担补偿"),
    IncomeLiquidCompensation("收益法自营缺乏流动性补偿"),
    IncomeFinancingAdvantage("收益法自营投资带来的优惠"),
    IncomePayBack("收益法自营报酬率"),

    IncomeEngageSituation("收益法自营经营情况"),
    IncomeEngageEarningAndOutGeneral("收益法自营经营收支一览表"),
    IncomeEngageEarningGeneral("收益法自营经营收入一览表"),
    IncomeSurveyItems("收益法自营同档次物业调查表"),
    IncomeForecastEarned("收益法自营预测经营收益"),
    IncomeForecastEarnedTotal("收益法自营预测经营收入合计"),
    IncomeOperatingCostCalculateCollectName("收益法自营运营费用测算汇总表名称"),

    IncomeEngageCost("收益法自营经营成本"),
    IncomeOperatingTaxRemark("收益法自营增值税及附加取值说明"),
    IncomeSelfSupportYearOperating("收益法自营年营运费"),
    IncomeOperatingProfitRemark("收益法自营商业利润取值说明"),
    IncomeRealtyNetEarning("收益法自营房地产净收益"),
    IncomeOperatingCalculateCollect("估价对象运营费用测算汇总表"),
    IncomeOperatingGeneral("估价对象运营费一览表"),
    //IncomeOperatingExpenses("收益法自营经营费用"),
    //IncomeManagementCost("收益法自营管理费用"),
    //IncomeFinancialCost("收益法自营财务费用"),
    //IncomeOperatingTax("收益法自营增值税及附加"),
    //IncomeYearOperatingCost("收益法自营年营运费"),
    //IncomeManageEarning("收益法自营经营收入"),
    //IncomeOperatingProfitRatio("收益法自营商业利润率"),

    IncomeMethodFormula("收益法计算公式"),
    IncomeRateIncrease("收益法自营增长率"),

    IncomeCalculateFormulaExpression("收益法自营测算公式表达式"),
    IncomeCalculateFormulaValue("收益法自营测算公式带值"),
    IncomeFormulaResult("收益法自营公式计算结果"),
    IncomeCalculatePrice("收益法自营测算价格")
    ;
    private String name;
    ReportFieldMdIncomeSelfEnum(String name) {
        this.name = name;
    }

    public static ReportFieldMdIncomeSelfEnum getEnumByName(String name) {
        for (ReportFieldMdIncomeSelfEnum e : ReportFieldMdIncomeSelfEnum.values()) {
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
