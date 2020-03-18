package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 银行 (基础)
 */
public enum BaseReportBankEnum {

    ;
    private String name;
    private BaseReportBankEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BaseReportBankEnum getEnumByName(String name) {
        for (BaseReportBankEnum e : BaseReportBankEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
