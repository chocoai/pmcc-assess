package com.copower.pmcc.assess.dto.input;

import java.io.Serializable;

/**
 * @Auther: zch
 * @Date: 2018/7/24 11:43
 * @Description:
 */
public class baseDto implements Serializable {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
