package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/5/25.
 */
public enum SchemeSupportTypeEnum {
    HYPOTHESIS(0, "评估假设"),//假设
    PRINCIPLE(1, "评估原则"),//原则
    BASIS(2, "评估依据"),//依据
    BEST_USE(3, "最佳利用描述"),//利用描述
    VALUE_TIME_POINT(4, "价值时点说明")//价值时点
    ;
    private int id;
    private String name;

    SchemeSupportTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // name替换id
    public static String getName(int id) {
        for (SchemeSupportTypeEnum c : SchemeSupportTypeEnum.values()) {
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

