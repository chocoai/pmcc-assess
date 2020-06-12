package com.copower.pmcc.assess.common.enums.basic;

/**
 * @Auther: zch
 * @Date: 2018/11/15 17:48
 * @Description:
 */
public enum BasicApplyFormNameEnum {
    BASIC_APPLY("basicApply","申请信息"),
    INDUSTRY("industry","工业仓储"),
    BASIC_ESTATE("basicEstate","楼盘"),
    BASIC_ESTATELandUseTypeCategory("landUseTypeCategory","土地类型"),
    BASIC_ESTATELAND_STATE("basicEstateLandState"," 土地实体"),
    BASIC_BUILDING("basicBuilding","楼栋部分"),
    BASIC_UNIT("basicUnit","单元"),
    BASIC_HOUSE("basicHouse","房屋"),
    BASIC_TRADING("basicTrading","房屋交易"),
    BASIC_TRADING_GROUPS("basicHouseTradingGroups","房屋交易"),
    BASIC_LANDCATEGORYINFO("landCategoryInfo","房屋中类型类别"),
    BASIC_HOUSE_HUXING("basicHouseHuxing","房屋户型"),
    BASIC_DAMAGED_DEGREE("basicDamagedDegree","房屋完好度"),
    BASIC_BuildLandState("buildLandState","建筑实体"),
    ;
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
