package com.copower.pmcc.assess.common.enums.method;

public enum MethodDataTypeEnum {
    INCOME(0, "收入类"), COST(1, "成本类"), PARAMETER(2, "参数"), RESULT(3, "测算结果");
    private String name;
    private Integer id;

    private MethodDataTypeEnum(Integer id, String name) {
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
