package com.copower.pmcc.assess.common.enums.basic;

/**
 * Created by zch on 2020-4-17.
 * 公共引用字段
 */
public enum ExamineCommonQuoteFieldEnum {
    COMMON_QUOTE_OPENTIME_ENUM("openTime","开盘时间"),
    COMMON_QUOTE_LANDUSEYEAR_ENUM("landUseYear","土地使用年限"),
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
