package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/8/2 17:11
 * @Description:工业与仓储评估调查表单
 */
public enum ExamineFileUpLoadTwoFieldEnum {
    houseHousePlan(ExamineFileUpLoadFieldEnum.houseHousePlan.getName(),"房屋平面图id和字段 (房间)"),
    buildingFloorPlan(ExamineFileUpLoadFieldEnum.buildingFloorPlan.getName(),"平面图id和字段 (楼栋)"),
    positionDiagramFileID("positionDiagramFileID","新增配套设备设施 位置图");
    private String name;
    private String des;

    ExamineFileUpLoadTwoFieldEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }
}
