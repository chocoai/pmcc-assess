package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/5/7.
 */
public enum InitiateContactsEnum {
    CONSIGNOR(1, "委托人"), POSSESSOR(2, "占有人"), UNIT_INFORMATION(3, "报告使用单位"), legalPerson(1, "法人"), naturalPerson(0, "自然人");
    private String name;
    private Integer id;

    private InitiateContactsEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // name替换id
    public static String getName(int id) {
        for (InitiateContactsEnum c : InitiateContactsEnum.values()) {
            if (c.getId().intValue() == id) {
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
