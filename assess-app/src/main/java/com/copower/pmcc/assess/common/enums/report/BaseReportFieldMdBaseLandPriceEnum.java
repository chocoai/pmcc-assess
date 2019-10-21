package com.copower.pmcc.assess.common.enums.report;

import java.io.Serializable;

/**
 * @author: HH
 * @date: 2019/2/14 16:11
 * @description:报告模板字段(收益法)
 */
public enum BaseReportFieldMdBaseLandPriceEnum implements Serializable {
    BaseLandPriceProfile("基准地价概要"),
    BaseLandPriceArea("基准地价区域"),
    BaseLandPriceLevelName("基准地价土地级别名称"),
    BaseLandPriceUnivalence("基准地价单价"),
    BaseLandPriceDateAmendCalculatedMode("基准地价期日修正计算式"),
    BaseLandPriceDateAmendCalculatedResult("基准地价期日修正计算结果"),
    BaseLandPriceDurableYears("基准地价使用年限"),
    BaseLandPriceResidueYears("基准地价土地剩余年限"),
    BaseLandPriceOpportunityCostExplain("基准地价机会成本说明"),
    BaseLandPriceOpportunityCostRatio("基准地价机会成本率"),
    BaseLandPriceRewardRatio("基准地价报酬率"),
    BaseLandPriceSetPurpose("基准地价设定用途"),
    BaseLandPeriodAmend("基准地价年期修正系数"),
    BaseLandPricePlotRatioAmendExplain("基准地价容积率修正系数说明"),
    BaseLandPriceRightStateAmend("基准地价权利状况修正"),//?
    BaseLandPriceDevelopmentLevelAmendExplain("基准地价开发程度修正说明"),
    BaseLandPriceParcelUnivalence("基准地价宗地单价"),
    BaseLandPriceAssessArea("基准地价评估面积"),
    BaseLandPriceParcelArea("基准地价宗地面积"),
    BaseLandPriceVolumeFraction("基准地价容积率"),
    BaseLandPriceFloorPremium("基准地价楼面地价"),


    BaseLandPriceSchedule("基准地价价格一览表"),
    BaseLandPriceFactorExplain("基准地价因素说明表"),
    BaseLandPriceFactorCoefficient("基准地价因素系数表"),
    BaseLandPriceFactorAmend("基准地价因素修正表"),
    BaseLandPriceIndex("基准地价基础指数表"),
    BaseLandPriceCalculateCourseDetail("基准地价测算过程明细表"),//?
    ;
    private String name;
    BaseReportFieldMdBaseLandPriceEnum(String name) {
        this.name = name;
    }

    public static BaseReportFieldMdBaseLandPriceEnum getEnumByName(String name) {
        for (BaseReportFieldMdBaseLandPriceEnum e : BaseReportFieldMdBaseLandPriceEnum.values()) {
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
