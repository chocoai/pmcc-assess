package com.copower.pmcc.assess.common.enums.method;

/**
 * Created by zch on 2019-10-21.
 */
public enum EconomicIndicatorsItemEnum {
    groundBuildingArea("groundBuildingArea","地上计入容积率建筑面积"),
    groundExcludBuildingArea("groundExcludBuildingArea","地上不计入容积率建筑面积"),
    undergroundBuildingArea("undergroundBuildingArea","地下建筑面积"),
    undergroundIncludedBuildingArea("undergroundIncludedBuildingArea","地下不计入建筑面积"),
    otherBuildingArea("otherBuildingArea","其他"),
    ;
    private String key;
    private String description;

    EconomicIndicatorsItemEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
