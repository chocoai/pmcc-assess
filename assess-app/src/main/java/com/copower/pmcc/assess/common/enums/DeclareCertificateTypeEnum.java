package com.copower.pmcc.assess.common.enums;

/**
 * Created by zch on 2019/8/12.
 * 权证类型
 */
public enum DeclareCertificateTypeEnum {
    HOUSE("房产证", 0, "house"),
    LAND("土地证", 1, "land"),
    REAL_ESTATE("不动产证", 2, "real.estate");
    private String dec;
    private Integer number;
    private String key;

    DeclareCertificateTypeEnum(String dec, Integer number, String key) {
        this.dec = dec;
        this.number = number;
        this.key = key;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDec() {
        return dec;
    }

    public String getKey() {
        return key;
    }
}
