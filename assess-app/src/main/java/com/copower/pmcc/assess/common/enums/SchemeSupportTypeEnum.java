package com.copower.pmcc.assess.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by 13426 on 2018/5/25.
 */
public enum SchemeSupportTypeEnum {
    HYPOTHESIS("hypothesis", "评估假设"),//假设
    PRINCIPLE("principle", "评估原则"),//原则
    BASIS("basis", "评估依据");//依据
    private String key;
    private String name;

    SchemeSupportTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    // name替换id
    public static String getName(String key) {
        for (SchemeSupportTypeEnum c : SchemeSupportTypeEnum.values()) {
            if (StringUtils.equals(c.getKey(),key)) {
                return c.name;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

