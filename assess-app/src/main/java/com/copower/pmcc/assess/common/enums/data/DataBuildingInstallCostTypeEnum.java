package com.copower.pmcc.assess.common.enums.data;

/**
 * Created by zch on 2019-9-30.
 * 建筑安装费计费依据
 */
public enum DataBuildingInstallCostTypeEnum {
    constructionInstallationEngineeringFee("constructionInstallationEngineeringFee","建筑安装费"),
    infrastructureCost("infrastructureCost","基础设施费"),
    ;
    private String key;
    private String value;

    private DataBuildingInstallCostTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }


    public String getValue() {
        return value;
    }
}
