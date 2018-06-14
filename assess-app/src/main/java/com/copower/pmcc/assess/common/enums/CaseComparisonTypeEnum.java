package com.copower.pmcc.assess.common.enums;

public enum CaseComparisonTypeEnum {
    EXPLORE(0, "查勘"), CASE(1, "案例");
    private String name;
    private Integer id;

    private CaseComparisonTypeEnum(Integer id, String name) {
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
