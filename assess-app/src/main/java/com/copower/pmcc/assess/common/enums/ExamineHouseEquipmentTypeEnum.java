package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/7/25 16:08
 * @Description:
 */
public enum ExamineHouseEquipmentTypeEnum {
    houseAirConditioner("houseAirConditioner","空调情况"),
    houseNewWind("houseNewWind","新风情况"),
    houseHeating("houseHeating","供暖情况")
    ;
    private String key;

    private String name;

    ExamineHouseEquipmentTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static String getNameByKey(String key){
        for (ExamineHouseEquipmentTypeEnum e : ExamineHouseEquipmentTypeEnum.values()) {
            if (e.getKey().equals(key)) {
                return e.getName();
            }
        }
        return null;
    }

    public static ExamineHouseEquipmentTypeEnum getEnumByName(String name) {
        for (ExamineHouseEquipmentTypeEnum e : ExamineHouseEquipmentTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
