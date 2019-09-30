package com.copower.pmcc.assess.common.enums.word;

/**
 * Created by zch on 2019/8/12.
 * 评估类型(增加封面)
 */
public enum AssessTypeEnum {
    ASSESS_TYPE_ENUM_HOUSE("房产评估",0),
    ASSESS_TYPE_ENUM_LAND("土地评估",1),
    ASSESS_TYPE_ENUM_ASSETS("资产评估",2),
    ;
    private String dec;
    private Integer number;
    private String key;

    AssessTypeEnum(String dec, Integer number) {
        this.dec = dec;
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDec() {
        return dec;
    }

}
