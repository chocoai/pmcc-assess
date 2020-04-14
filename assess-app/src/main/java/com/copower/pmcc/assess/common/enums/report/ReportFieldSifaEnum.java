package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2019/1/15 14:30
 * @description:报告模板字段 司法
 */
public enum ReportFieldSifaEnum {
    SifaValuationProjectName("司法估价项目名称"),
    SifaJudgeBuildResultSurveySheet("司法估价结果一览表"),
    ;
    private String name;
    private ReportFieldSifaEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldSifaEnum getEnumByName(String name) {
        for (ReportFieldSifaEnum e : ReportFieldSifaEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
