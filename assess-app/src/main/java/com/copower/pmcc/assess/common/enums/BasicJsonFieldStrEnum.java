package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/11/15 17:48
 * @Description:
 */
public enum BasicJsonFieldStrEnum {
    BASICAPPLY("basicApply","申请信息"),
    INDUSTRY("industry","工业仓储"),
    BASICESTATE("basicEstate","楼盘"),
    BASICESTATELANDSTATE("basicEstateLandState"," 楼盘实体"),
    BASICBUILDINGMAIN("basicBuildingMain","楼栋主 json"),
    BASICBUILDINGS("basicBuildings","楼栋列表 json"),
    BASICUNIT("basicUnit","单元"),
    BASICHOUSE("basicHouse","房屋"),
    BASICTRADING("basicTrading","房屋交易");
    private String var;
    private String describe;

    BasicJsonFieldStrEnum(String var,String describe) {
        this.var = var;
        this.describe = describe;
    }

    public String getVar() {
        return var;
    }
}
