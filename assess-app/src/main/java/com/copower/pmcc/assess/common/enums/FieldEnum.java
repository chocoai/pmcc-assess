package com.copower.pmcc.assess.common.enums;

public enum  FieldEnum {
    APPLICABLE(0),//适用原因字段
    NO_APPLICABLE(1)//不适用原因字段
    ;
    private int num;

    FieldEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
