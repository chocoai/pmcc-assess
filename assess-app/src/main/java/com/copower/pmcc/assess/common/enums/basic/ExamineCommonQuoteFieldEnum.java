package com.copower.pmcc.assess.common.enums.basic;

/**
 * Created by zch on 2020-4-17.
 * 公共引用字段
 */
public enum ExamineCommonQuoteFieldEnum {
    OPEN_TIME_ENUM("openTime","开盘时间"),
    COVER_AN_AREA("coverAnArea","占地面积"),
    LAND_USE_YEAR_ENUM("landUseYear","土地使用年限"),
    ;
    private String key;
    private String name;

    ExamineCommonQuoteFieldEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
