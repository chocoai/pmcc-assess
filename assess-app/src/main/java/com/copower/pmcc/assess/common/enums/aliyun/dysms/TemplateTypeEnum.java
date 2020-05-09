package com.copower.pmcc.assess.common.enums.aliyun.dysms;

/**
 * 短信模板类型
 */
public enum TemplateTypeEnum {
    Captcha(1,"验证码") ,
    SMS_NOTIFICATION(2,"短信通知") ,
    PromoteSMS(3,"推广短信") ,
    International_SMS(4,"国际/港澳台消息") ,
    ;
    private Integer key;
    private String name;

    TemplateTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
