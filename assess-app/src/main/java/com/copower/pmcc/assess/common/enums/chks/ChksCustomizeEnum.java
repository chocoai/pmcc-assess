package com.copower.pmcc.assess.common.enums.chks;

/**
 * Created by zch on 2020-1-16.
 */
public enum ChksCustomizeEnum {
    declare("申报","declare"),
    explore("查勘","declare"),
    ;
    private String remark;
    private String key;

    ChksCustomizeEnum(String remark, String key) {
        this.remark = remark;
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
