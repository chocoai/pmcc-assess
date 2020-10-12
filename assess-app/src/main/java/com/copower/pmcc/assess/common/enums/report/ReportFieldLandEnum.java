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
    LAND_ENUM_landNumber("土地地号") ,
    LAND_ENUM_PrincipalInfo("土地估价委托人信息"),
    LAND_ENUM_LandRightType("土地使用权类型"),
    LAND_ENUM_acquisitionType("土地取得方式"),
    LAND_ENUM_RightType_Desc("土地他项权力"),
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
