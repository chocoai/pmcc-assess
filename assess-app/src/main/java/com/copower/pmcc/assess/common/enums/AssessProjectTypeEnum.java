package com.copower.pmcc.assess.common.enums;

/**
 * Created by zch on 2019/8/12.
 * 评估类型
 */
public enum AssessProjectTypeEnum {
    ASSESS_PROJECT_TYPE_HOUSE("房产评估", 0, "house"),
    ASSESS_PROJECT_TYPE_LAND("土地评估", 1, "land"),
    ASSESS_PROJECT_TYPE_ASSETS("资产评估", 2, "assets");
    private String dec;
    private Integer number;
    private String key;

    AssessProjectTypeEnum(String dec, Integer number, String key) {
        this.dec = dec;
        this.number = number;
        this.key = key;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDec() {
        return dec;
    }

    public String getKey() {
        return key;
    }
}
