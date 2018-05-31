package com.copower.pmcc.assess.common.enums;

public enum CustomerTypeEnum {
    NATURAL(0, "自然人"), LEGAL(1, "法人");
    private String name;
    private Integer id;

    private CustomerTypeEnum(Integer id, String name) {
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
