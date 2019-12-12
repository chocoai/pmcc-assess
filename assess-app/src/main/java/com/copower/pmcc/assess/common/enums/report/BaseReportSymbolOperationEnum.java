package com.copower.pmcc.assess.common.enums.report;

/**
 * Created by zch on 2019-12-12.
 * 文号操作
 */
public enum BaseReportSymbolOperationEnum {

    GET("get","拿号"),
    RESET("reset","重新拿号"),
    NONE("none","不拿号"),
    ;
    private String key;
    private String remark;

    BaseReportSymbolOperationEnum(String key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

}
