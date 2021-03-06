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
    BUILDING_MONOLAYER("building.monolayer", "楼栋（单层）", "tb_basic_building", "basicBuildingMonolayerService"),
    BUILDING_BASE("building.base", "综合楼（基础部分）", "tb_basic_building", "basicBuildingBaseService"),
    BUILDING_DIFFERENCE("building.difference", "综合楼（差异部分）", "tb_basic_building", "basicBuildingDifferenceService"),
    UNIT("unit", "单元", "tb_basic_unit", "basicUnitService"),
    UNIT_RESIDENCE("unit.residence", "单元（住宅）", "tb_basic_unit", "basicUnitResidenceService"),
    HOUSE("house", "房屋", "tb_basic_house", "basicHouseService"),
    //----纯土地-----------------------------------
    ESTATE_LAND("estate.land", "土地", "tb_basic_estate", "basicEstateLandService"),
    HOUSE_LAND("house.land", "土地类型", "tb_basic_house", "basicHouseLandService"),
    //----土地带房产-----------------------------------
    ESTATE_LAND_INCLUD("estate.land.includ", "土地[房产]", "tb_basic_estate", "basicEstateLandIncludService"),
    BUILDING_LAND_INCLUD("building.land.includ", "楼栋[房产]", "tb_basic_building", "basicBuildingLandIncludService"),
    UNIT_LAND_INCLUD("unit.land.includ", "单元[房产]", "tb_basic_unit", "basicUnitLandIncludService"),
    HOUSE_LAND_INCLUD("house.land.includ", "房屋[房产]", "tb_basic_house", "basicHouseLandIncludService")
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

    /**
     * 用来处理本枚举无法解析的问题 类似的building.difference 转为 building_difference 到 解析完转回building.difference
     * @param flag
     * @return
     */
    public static String transform(boolean flag){
        if (flag){
            return "_" ;
        }else {
            return "." ;
        }
    }

    public static List<BasicFormClassifyEnum> getEnumByFuzzyKey(String key) {
        List<BasicFormClassifyEnum> list = Lists.newArrayList();
        for (BasicFormClassifyEnum e : BasicFormClassifyEnum.values()) {
            if (e.getKey().contains(key)) {
                list.add(e);
            }
        }
        return list;
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
