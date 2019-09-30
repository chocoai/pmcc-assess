package com.copower.pmcc.assess.common.enums.data;

/**
 * 描述:模板类型表
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum DataDamagedDegreeEnum {
    INTACT("intact", "完好"),
    BASICALLY_INTACT("basicallyIntact", "基本完好"),
    GENERAL_DAMAGE("generalDamage", "一般损坏"),
    SERIOUS_DAMAGE("seriousDamage", "严重损坏");

    private String key;
    private String value;

    private DataDamagedDegreeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static DataDamagedDegreeEnum getEnumByKey(String key) {
        for (DataDamagedDegreeEnum e : DataDamagedDegreeEnum.values()) {
            if (e.getKey().equals(key)) {
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
