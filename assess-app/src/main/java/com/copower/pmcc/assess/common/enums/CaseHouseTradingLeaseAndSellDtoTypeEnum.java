package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/9/14 11:09
 * @Description:区分CaseHouseTradingLease和CaseHouseTradingSell
 */
public enum CaseHouseTradingLeaseAndSellDtoTypeEnum {
    CaseHouseTradingLease("CaseHouseTradingLease","房屋出租"),
    CaseHouseTradingSell("CaseHouseTradingSell","房屋出售");
    private String key;

    private String name;

    CaseHouseTradingLeaseAndSellDtoTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }
}
