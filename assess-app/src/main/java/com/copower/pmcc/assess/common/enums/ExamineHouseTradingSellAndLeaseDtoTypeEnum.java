package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/7/30 11:26
 * @Description:区分ExamineHouseTradingLease和ExamineHouseTradingSell
 */
public enum ExamineHouseTradingSellAndLeaseDtoTypeEnum {
    ExamineHouseTradingLease("ExamineHouseTradingLease","房屋出售"),
    ExamineHouseTradingSell("ExamineHouseTradingSell","房屋出租");
    private String key;

    private String name;

    ExamineHouseTradingSellAndLeaseDtoTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }
}
