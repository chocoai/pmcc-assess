package com.copower.pmcc.assess.common.enums.method;

/**
 * Created by zch on 2019-11-8.
 * 假设开发法 枚举
 */
public enum MdDevelopmentTypeEnum {
    developmentLand("土地",1,"1"),
    developmentEngineering("在建工程",2,"2"),
    ;
    private String remark;
    private int number;
    private String key;

    MdDevelopmentTypeEnum(String remark, int number, String key) {
        this.remark = remark;
        this.number = number;
        this.key = key;
    }

    MdDevelopmentTypeEnum(String remark, String key) {
        this.remark = remark;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getNumber() {
        return number;
    }

    public String getRemark() {
        return remark;
    }
}
