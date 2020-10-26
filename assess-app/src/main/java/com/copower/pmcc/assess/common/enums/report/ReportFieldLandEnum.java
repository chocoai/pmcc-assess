package com.copower.pmcc.assess.common.enums.report;

/**
 * @author: zch
 * @date: 2020/10/10 14:30
 * @description:报告模板字段 土地
 */
public enum ReportFieldLandEnum {
    LAND_ENUM_CERTIFICATE_CONTRACT("土地出证合同") ,
    LAND_ENUM_OWNERSHIP("土地所有权人") ,
    LAND_ENUM_EndTime("土地终止日期") ,
    LAND_ENUM_RemainingYear("土地剩余年限") ,
    LAND_ENUM_Seat("土地坐落") ,
    LAND_ENUM_landNumber("土地地号") ,
    LAND_ENUM_PrincipalInfo("土地估价委托人信息"),
    LAND_ENUM_PrincipalIdNumber("土地委托人证件号"),
    LAND_ENUM_LandRightType("土地使用权类型"),
    LAND_ENUM_acquisitionType("土地取得方式"),
    LAND_ENUM_RightType_Desc("土地他项权力"),
    LAND_ENUM_RightType_Content("土地他项权力内容"),
    LAND_ENUM_PlotRatio("土地容积率"),
    LAND_ENUM_PlotRatio_Desc("土地容积率描述"),
    LAND_ENUM_PLANNING_CONSTRAINTS("土地规划限制条件"),
    LAND_ENUM_RESTRICTIONS("土地限制事项"),
    LAND_ENUM_BuildingDensity("土地建筑密度"),
    LAND_ENUM_GreeningRate("土地绿地率"),
    LAND_ENUM_CompatibilityRate("土地兼容比"),
    LAND_ENUM_SetPlotRatio("土地设定容积率"),
    LAND_ENUM_TOTAL_LAND_AREA("土地评估面积合计"),
    LAND_ENUM_UNIT_LAND_PRICE("土地单位地价"),
    LAND_ENUM_ESTATE_LAND_PRICE("土地楼面地价"),
    LAND_ENUM_USE_MATERIAL("土地使用资料"),
    LAND_BaseLandPrice("土地基准地价级别"),
    LAND_ENUM_EVALUATION_EFFECTIVE_DATE("土地评估有效日"),
    LAND_ENUM_JudgeObjectSheet("土地估价对象基本情况表"),
    LAND_JudgeBuildResultSurveySheet("土地估价结果表"),
    LAND_ENUM_BEST_USE("土地最佳利用方式"),
    LAND_ENUM_NATURE("土地权利性质"),
    LAND_ENUM_OpenTime("土地取得日期"),
    LAND_ENUM_SurveyExplore_TYPE("土地查勘类型"),
    LAND_ENUM_FaceStreet_TYPE("土地临街状况类型"),
    LAND_ENUM_RegionalFactorsDesc("土地区域因素描述"),
    LAND_ENUM_IndividualFactorsDesc("土地个别因素描述"),
    LAND_ENUM_Asset_InventoryDesc("土地资产清查描述"),
    LAND_ENUM_Asset_InventoryAddRess("土地资产清查证载地址"),
    LAND_ENUM_NumberDenominations("土地宗数"),
    ENUM_OPTIMAL_USE_LAND("土地最佳利用"),//模板
    LAND_ENUM_EVALUATION_PRINCIPLE("土地估价原则"),//模板
    LAND_ENUM_JudgeObjectPRINCIPLE_UseCert("土地估价结果有证依据"),


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
