package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2018/7/20 18:22
 * @Description:区分ExamineMatchingLeisurePlace
 */
public enum ExamineMatchingLeisurePlaceTypeEnum {
    MATCHINGMARKET("matchingMarket","购物商场");
    private String name;
    private String des;

    ExamineMatchingLeisurePlaceTypeEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }
}
