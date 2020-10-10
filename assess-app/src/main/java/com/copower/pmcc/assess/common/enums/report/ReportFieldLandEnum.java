package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2020/10/10 14:30
 * @description:报告模板字段 土地
 */
public enum ReportFieldLandEnum {
    LAND_ENUM_OWNERSHIP("土地所有权人") ,
    LAND_ENUM_EndTime("土地终止日期") ,
    LAND_ENUM_RemainingYear("土地剩余年限") ,
    LAND_ENUM_Seat("土地坐落") ,
    ;
    private String name;
    private ReportFieldLandEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ReportFieldLandEnum getEnumByName(String name) {
        for (ReportFieldLandEnum e : ReportFieldLandEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
