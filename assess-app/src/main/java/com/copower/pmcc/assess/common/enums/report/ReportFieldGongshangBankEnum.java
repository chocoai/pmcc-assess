package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 工商银行
 */
public enum ReportFieldGongshangBankEnum {

    GongshangDistrict_Analysis("工行区位分析"),
    GongshangICBCValuationCaseInformationSheet("工行估价案例情况表"),

    ;
    private String name;
    private ReportFieldGongshangBankEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldGongshangBankEnum getEnumByName(String name) {
        for (ReportFieldGongshangBankEnum e : ReportFieldGongshangBankEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
