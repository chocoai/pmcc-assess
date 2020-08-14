package com.copower.pmcc.assess.common.enums.basic;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;

import java.io.Serializable;

/**
 * 查勘  数据状态
 */
public enum BasicDataHandleEnum implements Serializable {
    NOMAL("normal", "正常"),
    REFERENCE("reference", "引用"),
    MODIFY("modify", "修改"),//表示引用后，对数据进行过修改
    SAME("same", "相同")//表示项目中存在了相同的数据
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
