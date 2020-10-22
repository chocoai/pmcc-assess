package com.copower.pmcc.assess.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zch on 2019/8/12.
 * 评估类型
 */
public enum AssessProjectTypeEnum {
    ASSESS_PROJECT_TYPE_HOUSE("房产", 0, "house"),
    ASSESS_PROJECT_TYPE_LAND("土地", 1, "land"),
    ASSESS_PROJECT_TYPE_ASSETS("资产", 2, "assets");
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

    public static String getDecByKey(String key) {
        if (StringUtils.isBlank(key)) return null;
        for (AssessProjectTypeEnum assessProjectTypeEnum : values()) {
            if (assessProjectTypeEnum.getKey().equals(key))
                return assessProjectTypeEnum.getDec();
        }
        return null;
    }

    public static AssessProjectTypeEnum getAssessProjectTypeEnumByKey(String key) {
        if (StringUtils.isBlank(key)) return null;
        for (AssessProjectTypeEnum assessProjectTypeEnum : values()) {
            if (assessProjectTypeEnum.getKey().equals(key))
                return assessProjectTypeEnum;
        }
        return null;
    }
}
