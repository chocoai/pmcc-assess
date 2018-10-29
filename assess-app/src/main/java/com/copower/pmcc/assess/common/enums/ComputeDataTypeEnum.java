package com.copower.pmcc.assess.common.enums;

public enum ComputeDataTypeEnum {
    ABSOLUTE(0, "绝对值"), RELATIVE(1, "相对值");
    private String name;
    private Integer id;

    private ComputeDataTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // 普通方法
    public static String getName(int id) {
        for (ComputeDataTypeEnum c : ComputeDataTypeEnum.values()) {
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
