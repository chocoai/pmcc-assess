package com.copower.pmcc.assess.common.enums.method;

import com.copower.pmcc.erp.api.enums.IEnum;
import com.copower.pmcc.erp.common.utils.EnumUtils;

public enum MethodIncomeFormTypeEnum implements IEnum {
    DEFUALT(0, "默认"), RESTAURANT(1, "餐饮、酒店、宾馆");
    private String name;
    private Integer value;

    private MethodIncomeFormTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static MethodIncomeFormTypeEnum create(int value) {
        return EnumUtils.getEnum(MethodIncomeFormTypeEnum.values(), value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
