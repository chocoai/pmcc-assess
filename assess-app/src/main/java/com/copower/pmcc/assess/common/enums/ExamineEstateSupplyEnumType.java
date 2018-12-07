package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/7/20 10:31
 * @Description:供应信息
 */
public enum ExamineEstateSupplyEnumType {
    ESTATE_SUPPLY_GAS("estateSupplyGas","供气"),
    ESTATE_SUPPLY_HEATING("estateSupplyHeating","供热"),
    ESTATE_SUPPLY_WATER("estateSupplyWater","供水"),
    ESTATE_DRAIN_WATER("estateDrainWater","排水"),
    ESTATE_SUPPLY_POWER("estateSupplyPower","供电");
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
