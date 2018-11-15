package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/11/15 17:48
 * @Description:
 */
public enum BasicJsonFieldStrEnum {
    INDUSTRY("industry"),
    BASICESTATE("basicEstate"),
    BASICESTATELANDSTATE("basicEstateLandState"),
    BASICBUILDINGMAIN("basicBuildingMain"),
    BASICBUILDINGS("basicBuildings"),
    BASICUNIT("basicUnit"),
    BASICHOUSE("basicHouse"),
    BASICTRADING("basicTrading");
    private String var;

    BasicJsonFieldStrEnum(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}
