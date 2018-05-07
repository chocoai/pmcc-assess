package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/5/7.
 */
public enum InitiateContactsEnum {
    ONE(1),TWO(2),THREE(3),CONTACTS_ENUM_A("委托人"),CONTACTS_ENUM_B("占有人"),CONTACTS_ENUM_C("报告使用单位");
    private String val;
    private int num;

    InitiateContactsEnum(String val) {
        this.val = val;
    }

    InitiateContactsEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getVal() {
        return val;
    }
}
