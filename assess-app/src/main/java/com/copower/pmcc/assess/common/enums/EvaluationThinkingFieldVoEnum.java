package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/4/27.
 */
public enum EvaluationThinkingFieldVoEnum {
    APPLICABLE(1,"适用"),NOT_APPLICABLE(0, "不适用"), ENABLE(1, "启用"), DISABLE(0, "不启用");
    private String name;
    private Integer id;

    private EvaluationThinkingFieldVoEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // name替换id
    public static String getName(int id) {
        for (EvaluationThinkingFieldVoEnum c : EvaluationThinkingFieldVoEnum.values()) {
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
