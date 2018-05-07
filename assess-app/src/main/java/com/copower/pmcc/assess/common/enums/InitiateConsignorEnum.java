package com.copower.pmcc.assess.common.enums;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/7.
 */
public enum InitiateConsignorEnum {
    ONE(1), TWO(2),THREE(3),BANK("银行"),OTHER("其他"),GOVERNMENT_AFFILIATED_INSTITUTIONS("事业单位");
    private Integer value;

    private String dec;

    InitiateConsignorEnum(Integer value) {
        this.value = value;
    }

    InitiateConsignorEnum(String dec) {
        this.dec = dec;
    }

    public Integer getValue() {
        return value;
    }

    public String getDec() {
        return dec;
    }
}
