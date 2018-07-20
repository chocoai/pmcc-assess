package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/7/20 10:31
 * @Description:供应信息
 */
public enum ExamineEstateSupplyEnumType {
    ESTATESUPPLYGAS("estateSupplyGas","供气"),
    ESTATESUPPLYHEATING("estateSupplyHeating","供热"),
    ESTATESUPPLYWATER("estateSupplyWater","供水"),
    ESTATESUPPLYPOWER("estateSupplyPower","供电");
    private String name;
    private String des;

    ExamineEstateSupplyEnumType(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }
}
