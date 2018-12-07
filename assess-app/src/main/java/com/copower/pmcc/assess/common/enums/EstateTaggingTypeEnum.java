package com.copower.pmcc.assess.common.enums;

/**
 * 描述:模板类型表
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum EstateTaggingTypeEnum {
    ESTATE("estate", "楼盘"), BUILDING("building", "楼栋"), UNIT("unit", "单元"), OTHER("other", "其它"),HOUSE("house","房屋");

    private String key;
    private String value;

    private EstateTaggingTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static EstateTaggingTypeEnum getEnumByKey(String key) {
        for (EstateTaggingTypeEnum e : EstateTaggingTypeEnum.values()) {
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
