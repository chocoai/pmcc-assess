package com.copower.pmcc.assess.common.enums.chks;

/**
 * Created by zch on 2019-12-31.
 * 考核枚举
 */
public enum ChksRuningEnum {
    CHKS_RUNING_ENUM_RUN(1,"考核可以进行"),
    CHKS_FINSH_ENUM_RUN(-1,"考核不可以进行"),
    ;
    private int key;
    private String remark;

    ChksRuningEnum(int key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public int getKey() {
        return key;
    }
}
