package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 建设银行
 */
public enum BaseReportFieldConstructionBankEnum {

    ;
    private String name;
    private BaseReportFieldConstructionBankEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BaseReportFieldConstructionBankEnum getEnumByName(String name) {
        for (BaseReportFieldConstructionBankEnum e : BaseReportFieldConstructionBankEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
