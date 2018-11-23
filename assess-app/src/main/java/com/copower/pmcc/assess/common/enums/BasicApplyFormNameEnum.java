package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/11/15 17:48
 * @Description:
 */
public enum BasicApplyFormNameEnum {
    BASIC_APPLY("basicApply","申请信息"),
    INDUSTRY("industry","工业仓储"),
    BASIC_ESTATE("basicEstate","楼盘"),
    BASIC_ESTATELAND_STATE("basicEstateLandState"," 楼盘实体"),
    BASIC_BUILDING_MAIN("basicBuildingMain","楼栋主信息"),
    BASIC_BUILDING("basicBuilding","楼栋部分"),
    BASIC_UNIT("basicUnit","单元"),
    BASIC_HOUSE("basicHouse","房屋"),
    BASIC_TRADING("basicTrading","房屋交易");
    private String var;
    private String describe;

    BasicApplyFormNameEnum(String var, String describe) {
        this.var = var;
        this.describe = describe;
    }

    public String getVar() {
        return var;
    }
}
