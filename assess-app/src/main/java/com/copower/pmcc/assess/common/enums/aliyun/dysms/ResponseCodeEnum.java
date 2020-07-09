package com.copower.pmcc.assess.common.enums.aliyun.dysms;

public enum ResponseCodeEnum {
    REFERENCE_STATUS("", "", "", ""),

    REFERENCE_STATUS_MINUS_1005("-1005", "内容含有违禁词", "供应商", "建议修改发送内容"),

    REFERENCE_STATUS_MINUS_182("-182", "内容中超过空格限制", "供应商", "建议修改发送内容"),

    REFERENCE_STATUS_MINUS_119("-119", "不符合本用户任何模版", "供应商", "建议按规定模版发送"),

    REFERENCE_STATUS_MINUS_118("-118", "找不到用户", "供应商", "建议更换号码"),

    REFERENCE_STATUS_MINUS_115("-115", "用户状态为测试用户", "供应商", "建议更改正确手机号码"),

    REFERENCE_STATUS_MINUS_112("-112", "手机号码格式不正确", "供应商", "建议使用正确的手机号"),

    REFERENCE_STATUS_MINUS_108("-108", "超过手机号条数限制", "供应商", "建议24小时后发送"),

    REFERENCE_STATUS_MINUS_107("-107", "手机号内容重复", "供应商", "建议修改内容"),

    REFERENCE_STATUS_MINUS_106("-106", "内容含有黑内容", "供应商", "建议修改内容"),

    REFERENCE_STATUS_MINUS_95("-95", "空号", "供应商", "建议使用正确的手机号"),

    REFERENCE_STATUS_MINUS_37("-37", "关机", "供应商", "建议联系用户开机"),
    ;

    private String code; //状态码

    private String info; //状态消息

    private String CodeType;//状态码归属

    private String help; //操作建议


    ResponseCodeEnum(String code, String info, String codeType, String help) {
        this.code = code;
        this.info = info;
        CodeType = codeType;
        this.help = help;
    }

    public String getCode() {
        return code;
    }

    public String getCodeType() {
        return CodeType;
    }

    public String getHelp() {
        return help;
    }

    public String getInfo() {
        return info;
    }
}
