package com.copower.pmcc.assess.common.enums.basic;

/**
 * @Auther: zch
 * @Date: 2018/7/20 18:22
 * @Description:区分ExamineMatchingLeisurePlace
 */
public enum ExamineMatchingLeisurePlaceTypeEnum {
    MATCHINGMARKET("matchingMarket","购物商场"),
    MATCHINGRECREATION("matchingRecreation","休闲娱乐"),
    MATCHINGRESTAURANT("matchingRestaurant","餐饮")
    ;
    private String key;

    private String name;

    ExamineMatchingLeisurePlaceTypeEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public static String getNameByKey(String key){
        for (ExamineMatchingLeisurePlaceTypeEnum e : ExamineMatchingLeisurePlaceTypeEnum.values()) {
            if (e.getKey().equals(key)) {
                return e.getName();
            }
        }
        return null;
    }

    public static ExamineMatchingLeisurePlaceTypeEnum getEnumByName(String name) {
        for (ExamineMatchingLeisurePlaceTypeEnum e : ExamineMatchingLeisurePlaceTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
