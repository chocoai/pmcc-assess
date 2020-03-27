package com.copower.pmcc.assess.common.enums.basic;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 描述:查勘表单大类
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/11/2
 * @time: 15:46
 */
public enum BasicFormClassifyEnum {
    //----房产-----------------------------------
    ESTATE("estate", "楼盘", "tb_basic_estate", "basicEstateService"),
    BUILDING("building", "楼栋", "tb_basic_building", "basicBuildingService"),
    BUILDING_BASE("building.base", "楼栋（基础）", "tb_basic_building", "basicBuildingBaseService"),
    BUILDING_DIFFERENCE("building.difference", "楼栋（差异）", "tb_basic_building", "basicBuildingDifferenceService"),
    UNIT("unit", "单元", "tb_basic_unit", "basicUnitService"),
    HOUSE("house", "房屋", "tb_basic_house", "basicHouseService")


    ;

    private Integer level;
    private String key;
    private String value;
    private String tableName;
    private String serviceName;

    private BasicFormClassifyEnum(String key, String value, String tableName, String serviceName) {
        this.key = key;
        this.value = value;
        this.tableName = tableName;
        this.serviceName = serviceName;
    }

    public static BasicFormClassifyEnum getEnumByKey(String key) {
        for (BasicFormClassifyEnum e : BasicFormClassifyEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public static BasicFormClassifyEnum getEnumByTableName(String tableName) {
        for (BasicFormClassifyEnum e : BasicFormClassifyEnum.values()) {
            if (e.getTableName().equals(tableName)) {
                return e;
            }
        }
        return null;
    }

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
