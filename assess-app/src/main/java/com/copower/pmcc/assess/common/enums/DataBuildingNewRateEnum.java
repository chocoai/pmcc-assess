package com.copower.pmcc.assess.common.enums;

public enum DataBuildingNewRateEnum {
    ONE(1),TWO(2),Three(3),ProductionRoom("生产用房"),CorrodedProductionRoom("受腐蚀生产用房"),NonProductionRoom("非生产用房");
    private Integer value;

    private String describe;

    DataBuildingNewRateEnum(Integer value) {
        this.value = value;
    }

    DataBuildingNewRateEnum(String describe) {
        this.describe = describe;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }
}
