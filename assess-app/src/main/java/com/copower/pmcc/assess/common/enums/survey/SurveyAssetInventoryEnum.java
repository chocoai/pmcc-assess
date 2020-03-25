package com.copower.pmcc.assess.common.enums.survey;

/**
 * Created by zch on 2020-3-25.
 * "资产清查
 */
public enum SurveyAssetInventoryEnum {
    group("group","资产清查组"),
    unit("unit","资产清查单个"),
    ;
    private String key,name;

    SurveyAssetInventoryEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }
}
