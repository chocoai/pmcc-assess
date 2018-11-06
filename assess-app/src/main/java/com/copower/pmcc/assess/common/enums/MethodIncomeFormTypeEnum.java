package com.copower.pmcc.assess.common.enums;

public enum MethodIncomeFormTypeEnum {
    DEFUALT(0, "默认"), RESTAURANT(1, "餐饮、酒店、宾馆");
    private String name;
    private Integer id;

    private MethodIncomeFormTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
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
