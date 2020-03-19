package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 司法
 */
public enum BaseReportFieldJudicialEnum {
    ValuationProjectName("司法估价项目名称"),
    JudgeBuildResultSurveySheet("司法估价结果一览表"),
    ;
    private String name;
    private BaseReportFieldJudicialEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BaseReportFieldJudicialEnum getEnumByName(String name) {
        for (BaseReportFieldJudicialEnum e : BaseReportFieldJudicialEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
