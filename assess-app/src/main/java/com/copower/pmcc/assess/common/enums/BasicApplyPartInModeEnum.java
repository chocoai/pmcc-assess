package com.copower.pmcc.assess.common.enums;

/**
 * 描述:模板类型表
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BasicApplyPartInModeEnum {
    ADD("add", "添加"), REFERENCE("reference", "引用"), UPGRADE("upgrade", "升版本");

    private String key;
    private String value;

    private BasicApplyPartInModeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static BasicApplyPartInModeEnum getEnumByKey(String key) {
        for (BasicApplyPartInModeEnum e : BasicApplyPartInModeEnum.values()) {
            if (e.getKey() .equals(key)) {
                return e;
            }
        }
        return null;
    }

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
