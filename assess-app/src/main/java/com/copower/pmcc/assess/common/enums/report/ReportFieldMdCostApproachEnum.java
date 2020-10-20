package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/2/14 16:11
 * @description:报告模板字段(逼近法)
 */
public enum ReportFieldMdCostApproachEnum implements Serializable {
    approachSetUse("逼近法土地设定用途"),//方案中设定用途*
    approachArea("逼近法区域"),
    parcelMachineCycle("逼近法计息周期"),//计息周期
    parcelCalculatedInterest("逼近法计息利率"),//计息利率
    parcelLandAcquisitionRatio("逼近法代征地比例"),
    parcelCirculationExpense("逼近法宗地外开发费用"),//宗地外流通费用
    parcelFlatExpense("逼近法宗地内开发费用"),//场平费用
    parcelIncrementalBenefit("逼近法土地增值收益率"),//土地增值率
    parcelLandUsePrice("逼近法土地使用权价格"),//无限年期土地使用权价格（加字段）
    parcelRewardRate("逼近法还原率"),//还原率
    parcelLandRemainingYear("逼近法土地剩余年限"),//土地剩余年限
    parcelYearFixed("逼近法年期修正系数"),//年期修正系数(加字段)
    parcelPlotRatioElementAmend("逼近法个别因素修正系数"),//宗地个别因素修正
    parcelParcelUnit("逼近法委估宗地单价"),//委估宗地单价(加字段)
    parcelInfiniteYearLandCostPrice("逼近法无限年期土地成本价格"),//土地取得成本=土地取得费及相关税费+土地开发费+投资利息+投资利润(加字段)
    parcelPlotRatioAmendCoefficient("逼近法容积率修正系数"),//1+容积率调整
    parcelInvestProfitMargin("逼近法投资利润率"),//开发利润率
    parcelHaveAcquisitionPerTaxes("逼近法含代征地每平税费"),//土地取得费及相关税费(加字段)
    parcelLandDevelopmentExpenses("逼近法土地开发费用"),//土地开发费=宗地外+宗地内
    parcelInvestProfit("逼近法投资利润"),//土地开发利润=（土地取得费及相关税费+土地开发费）×开发利润率(加字段)
    parcelInvestInterest("逼近法投资利息"),//土地开发利息(加字段)
    parcelPlotRatioAdjustRemark("逼近法容积率修正说明"),
    parcelPlotRatioAdjust("逼近法实际容积率"),//容积率调整
    parcelHaveNotAcquisitionPerTaxes("逼近法不含代征地每平税费"),
    parcelDevelopmentLevelOuter("逼近法宗地外开发程度"),
    parcelDevelopmentLevelInner("逼近法宗地内开发程度"),
    parcelRewardRatioCalculate("逼近法报酬率测算表"),
    parcelLandPriceElementExplain("逼近法地价因素说明表"),
    parcelLandPriceElementCoefficient("逼近法地价因素系数表"),
    parcelLandPriceElementAmend("逼近法地价因素修正表"),


    parcelIncrementalBenefitPrice("逼近法土地增值收益额"),//土地出让价格V×土地增值收益率
    parcelNoRiskRewardRatioRemark("逼近法无风险报酬率取值说明"),
    parcelInformationCompensation("逼近法补偿信息"),
    parcelCalculateSheet("逼近法测算表"),
    ;
    private String name;
    ReportFieldMdCostApproachEnum(String name) {
        this.name = name;
    }

    public static ReportFieldMdCostApproachEnum getEnumByName(String name) {
        for (ReportFieldMdCostApproachEnum e : ReportFieldMdCostApproachEnum.values()) {
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
