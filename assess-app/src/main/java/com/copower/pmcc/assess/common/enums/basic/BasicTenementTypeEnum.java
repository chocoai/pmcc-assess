package com.copower.pmcc.assess.common.enums.basic;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 物业类型
 */
public enum BasicTenementTypeEnum implements Serializable {

    RESIDENTIAL("residential" ,"住宅"),
    SHOP("shop" ,"商铺"),
    MARKET("market" ,"商场"),
    HOTEL("Hotel" ,"酒店"),
    REPAST("repast" ,"餐饮"),
    OFFICE("office" ,"办公"),
    PRODUCE("produce" ,"生产"),
    WARE_HOUSE("Ware_house" ,"仓储"),
    PARKING_SPACE("parking_space" ,"车位"),

    ;
    private String key;
    private String name;

    BasicTenementTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public static BasicTenementTypeEnum getEnumByKey(String key) {
        for (BasicTenementTypeEnum e : BasicTenementTypeEnum.values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public static BasicTenementTypeEnum getEnumByName(String name) {
        for (BasicTenementTypeEnum e : BasicTenementTypeEnum.values()) {
//            if (StringUtils.contains(e.getName(),name)) {
//                return e;
//            }
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static String[] toTypeArray(BasicTenementTypeEnum ...typeEnums){
        String[] arr = new String[typeEnums.length] ;
        int i = 0;
        for (BasicTenementTypeEnum typeEnum:typeEnums){
            arr[i] = typeEnum.getKey();
            i++;
        }
        return arr;
    }
}
