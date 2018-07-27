package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/7/27 09:30
 * @Description:住宅商业办公评估调查表单
 */
public enum ExamineFileUpLoadFieldEnum {
    estateFloorTotalPlan("estate_floor_total_plan","总平面图id和字段 (楼盘)"),
    waterSupplyPlan("water_supply_plan","供水平面图id和字段 (楼盘)"),
    powerSupplyPlan("power_supply_plan","供电平面图id和字段 (楼盘)"),
    airSupplyPlan("air_supply_plan","供气平面图id和字段 (楼盘)"),
    heatingPlan("heating_plan","采暖平面图id和字段 (楼盘)"),
    estateFloorAppearanceFigure("estate_floor_Appearance_figure","外观图id和字段 (楼盘)"),

    buildingFloorPlan("building_floor_plan","平面图id和字段 (楼栋)"),
    buildingFigureOutside("building_figure_outside","外装图id和字段 (楼栋)"),
    buildingFloorAppearanceFigure("building_floor_Appearance_figure","外观图id和字段 (楼栋)"),

    houseLatestFamilyPlan("house_latest_family_plan","最新户型图id和字段 (房间)"),
    houseHousePlan("house_house_plan","房屋平面图id和字段 (房间)")
    ;
    private String name;
    private String des;

    ExamineFileUpLoadFieldEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }
}
