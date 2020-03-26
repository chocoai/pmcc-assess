package com.copower.pmcc.assess.dto.input.basic;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:26
 * @Description:
 */
public class BasicFormClassifyDto {
    private Integer level;
    private String key;
    private String value;
    private String tableName;
    private String serviceName;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
