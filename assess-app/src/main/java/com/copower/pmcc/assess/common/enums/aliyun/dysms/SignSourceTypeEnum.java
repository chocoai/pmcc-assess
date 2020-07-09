package com.copower.pmcc.assess.common.enums.aliyun.dysms;

import com.google.common.base.Objects;

/**
 * 签名来源。其中：
 * <p>
 * 0：企事业单位的全称或简称。
 * 1：工信部备案网站的全称或简称。
 * 2：APP应用的全称或简称。
 * 3：公众号或小程序的全称或简称。
 * 4：电商平台店铺名的全称或简称。
 * 5：商标名的全称或简称
 * 签名来源为1时，请在申请说明中添加网站域名，加快审核速度。
 */
public enum SignSourceTypeEnum {
    SIGNATURE_SOURCE_ZERO(0, "企事业单位的全称或简称"),
    SIGNATURE_SOURCE_ONE(1, "工信部备案网站的全称或简称"),
    SIGNATURE_SOURCE_TWO(2, "APP应用的全称或简称"),
    SIGNATURE_SOURCE_THREE(3, "公众号或小程序的全称或简称"),
    SIGNATURE_SOURCE_FOUR(4, "电商平台店铺名的全称或简称"),
    SIGNATURE_SOURCE_FIVE(5, "商标名的全称或简称"),

    ;

    private Integer key;
    private String name;


    SignSourceTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static SignSourceTypeEnum getEnumByName(Integer key) {
        for (SignSourceTypeEnum e : SignSourceTypeEnum.values()) {
            if (Objects.equal(key, e.getKey())) {
                return e;
            }
        }
        return null;
    }

}
