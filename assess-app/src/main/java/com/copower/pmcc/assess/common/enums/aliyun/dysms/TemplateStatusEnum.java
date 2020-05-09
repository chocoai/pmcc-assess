package com.copower.pmcc.assess.common.enums.aliyun.dysms;

import com.google.common.base.Objects;

/**
 * 短信模板状态以及签名审核状态 使用同样的状态
 */
public enum TemplateStatusEnum {
    Audit_Running(0, "审核中"),
    Audit_Fulfill(1, "审核通过"),
    Audit_Fail(2, "审核失败"),

    ;


    private Integer key;
    private String name;


    TemplateStatusEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getKey() {
        return key;
    }

    public static TemplateStatusEnum getEnumByName(Integer key) {
        for (TemplateStatusEnum e : TemplateStatusEnum.values()) {
            if (Objects.equal(key,e.getKey())) {
                return e;
            }
        }
        return null;
    }
}
