package com.copower.pmcc.assess.common.enums;

public enum ResponsibileModelEnum {
    TASK(0, "提交成果"), PLAN(1, "计划编制"), ALLTASK(2, "整体复核"), NEWPLAN(3, "细分计划"),DETAIL(4,"详情计划");
    private String name;
    private Integer id;

    private ResponsibileModelEnum(Integer id, String name) {
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
