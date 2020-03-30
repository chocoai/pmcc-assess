package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 工商银行
 */
public enum BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum {

    District_Analysis("工行区位分析"),
    ICBCValuationCaseInformationSheet("工行估价案例情况表"),

    ;
    private String name;
    private BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum getEnumByName(String name) {
        for (BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum e : BaseReportField_INDUSTRIAL_AND_COMMERCIAL_BANKEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
