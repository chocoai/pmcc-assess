package com.copower.pmcc.assess.common.enums.report;

/**
 * Created by zch on 2019-12-12.
 * 文号操作
 */
public enum ReportSymbolOperationEnum {

    GET("get","拿号"),
    RESET("reset","重新拿号"),
    NONE("none","不拿号"),
    ;
    private String key;
    private String remark;
    private String name;

    ReportSymbolOperationEnum(String key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ReportSymbolOperationEnum getEnumByName(String name) {
        for (ReportSymbolOperationEnum e : ReportSymbolOperationEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
