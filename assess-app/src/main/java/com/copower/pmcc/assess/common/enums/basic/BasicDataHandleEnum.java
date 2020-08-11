package com.copower.pmcc.assess.common.enums.basic;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;

import java.io.Serializable;

/**
 * 查勘  数据状态
 */
public enum BasicDataHandleEnum implements Serializable {
    BASIC_DATA_HANDLE_NOMAL_ENUM(ProjectStatusEnum.NORMAL.getKey(),"正常"),
    BASIC_DATA_HANDLE_REFERENCE_ENUM("reference","引用状态"),
    ;
    private String key;
    private String name;


    BasicDataHandleEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
