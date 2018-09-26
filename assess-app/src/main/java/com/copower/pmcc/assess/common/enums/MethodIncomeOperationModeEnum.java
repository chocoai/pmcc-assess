package com.copower.pmcc.assess.common.enums;

public enum MethodIncomeOperationModeEnum {
    PROPRIETARY(0, "自营"), LEASE(1, "租赁");
    private String name;
    private Integer id;

    private MethodIncomeOperationModeEnum(Integer id, String name) {
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
