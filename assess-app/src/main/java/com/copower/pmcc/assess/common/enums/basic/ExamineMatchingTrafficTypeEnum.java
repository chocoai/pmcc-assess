package com.copower.pmcc.assess.common.enums.basic;

/**
 * @Auther: zch
 * @Date: 2018/7/19 15:10
 * @Description:
 */
public enum ExamineMatchingTrafficTypeEnum {
    METRO("metro","地铁类型"),
    TRANSIT("transit","公交类型"),
    MainRoad("mainRoad","主干道类型"),
    MainConversion("mainConversion","主要转换"),
    TrafficHub("trafficHub","交通枢纽")
    ;
    private String name;
    private String des;

    ExamineMatchingTrafficTypeEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }
}
