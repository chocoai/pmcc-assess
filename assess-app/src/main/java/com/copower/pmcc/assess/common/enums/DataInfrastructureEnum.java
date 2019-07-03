package com.copower.pmcc.assess.common.enums;

/**
 * Created by zch on 2019/7/3.
 */
public enum DataInfrastructureEnum {
    CommunalFacilities("2","公共配套设施费用"),
    devTaxTotal("1","开发期间税费"),
    InfrastructureSupportingFacilities("3","基础配套设施费用"),
    ;
    private String name;
    private String remark;

    DataInfrastructureEnum(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}
