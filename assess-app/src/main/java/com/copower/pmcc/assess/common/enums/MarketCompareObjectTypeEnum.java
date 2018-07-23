package com.copower.pmcc.assess.common.enums;

public enum MarketCompareObjectTypeEnum {
    EXPLORE(0, "委估对象"), CASE(1, "案例");
    private String name;
    private Integer id;

    private MarketCompareObjectTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // 普通方法
    public static String getName(int id) {
        for (MarketCompareObjectTypeEnum c : MarketCompareObjectTypeEnum.values()) {
            if (c.getId() == id) {
                return c.name;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
