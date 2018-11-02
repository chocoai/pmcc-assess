package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/9/14 11:09
 * @Description:区分BasicHouseTradingLease和BasicHouseTradingSell
 */
public enum BasicHouseTradingLeaseAndSellDtoTypeEnum {
    BasicHouseTradingLease("BasicHouseTradingLease","房屋出租"),
    BasicHouseTradingSell("BasicHouseTradingSell","房屋出售");
    private String key;

    private String name;

    BasicHouseTradingLeaseAndSellDtoTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String className(Class<?> c){
        return c.getSimpleName();
    }
}
