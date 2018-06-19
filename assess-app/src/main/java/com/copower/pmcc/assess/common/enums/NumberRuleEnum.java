package com.copower.pmcc.assess.common.enums;

public enum NumberRuleEnum {
    NEWNUMBER(-1,"新号"),CONTINUOUS(0, "一直连续"), DISJUNCTION(1, "按年分断");
    private String name;
    private Integer id;

    private NumberRuleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // name替换id
    public static String getName(int id) {
        for (NumberRuleEnum c : NumberRuleEnum.values()) {
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
