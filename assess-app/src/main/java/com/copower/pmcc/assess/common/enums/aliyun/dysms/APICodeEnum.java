package com.copower.pmcc.assess.common.enums.aliyun.dysms;

/**
 *
 * 阿里云 copy 下来的 ,如果枚举定义错误
 *
 * Code	描述
 * OK	请求成功
 * isp.RAM_PERMISSION_DENY	RAM权限DENY
 * isv.OUT_OF_SERVICE	业务停机
 * isv.PRODUCT_UN_SUBSCRIPT	未开通云通信产品的阿里云客户
 * isv.PRODUCT_UNSUBSCRIBE	产品未开通
 * isv.ACCOUNT_NOT_EXISTS	账户不存在
 * isv.ACCOUNT_ABNORMAL	账户异常
 * isv.SMS_TEMPLATE_ILLEGAL	短信模板不合法
 * isv.SMS_SIGNATURE_ILLEGAL	短信签名不合法
 * isv.INVALID_PARAMETERS	参数异常
 * isp.SYSTEM_ERROR	系统错误
 * isv.MOBILE_NUMBER_ILLEGAL	非法手机号
 * isv.MOBILE_COUNT_OVER_LIMIT	手机号码数量超过限制
 * isv.TEMPLATE_MISSING_PARAMETERS	模板缺少变量
 * isv.BUSINESS_LIMIT_CONTROL	业务限流
 * isv.INVALID_JSON_PARAM	JSON参数不合法，只接受字符串值
 * isv.BLACK_KEY_CONTROL_LIMIT	黑名单管控
 * isv.PARAM_LENGTH_LIMIT	参数超出长度限制
 * isv.PARAM_NOT_SUPPORT_URL	不支持URL
 * isv.AMOUNT_NOT_ENOUGH	账户余额不足
 */
public enum APICodeEnum {

    OK("请求成功", "OK", "成功"),
    RAM_PERMISSION_DENY("RAM权限DENY", "isp.RAM_PERMISSION_DENY"),
    OUT_OF_SERVICE("业务停机", "isv.OUT_OF_SERVICE"),
    PRODUCT_UN_SUBSCRIPT("未开通云通信产品的阿里云客户", "isv.PRODUCT_UN_SUBSCRIPT"),
    PRODUCT_UNSUBSCRIBE("产品未开通", "isv.PRODUCT_UNSUBSCRIBE"),
    ACCOUNT_NOT_EXISTS("账户不存在", "isv.ACCOUNT_NOT_EXISTS"),
    ACCOUNT_ABNORMAL("账户异常", "isv.ACCOUNT_ABNORMAL"),
    SMS_TEMPLATE_ILLEGAL("短信模板不合法", "isv.SMS_TEMPLATE_ILLEGAL"),
    SMS_SIGNATURE_ILLEGAL("短信签名不合法", "isv.SMS_SIGNATURE_ILLEGAL"),
    INVALID_PARAMETERS("参数异常", "isv.INVALID_PARAMETERS"),
    SYSTEM_ERROR("系统错误", "isv.SYSTEM_ERROR"),
    MOBILE_NUMBER_ILLEGAL("非法手机号", "isv.MOBILE_NUMBER_ILLEGAL"),
    MOBILE_COUNT_OVER_LIMIT("手机号码数量超过限制", "isv.MOBILE_COUNT_OVER_LIMIT"),
    TEMPLATE_MISSING_PARAMETERS("模板缺少变量", "isv.TEMPLATE_MISSING_PARAMETERS"),
    BUSINESS_LIMIT_CONTROL("业务限流", "isv.BUSINESS_LIMIT_CONTROL"),
    INVALID_JSON_PARAM("JSON参数不合法，只接受字符串值", "isv.INVALID_JSON_PARAM"),
    BLACK_KEY_CONTROL_LIMIT("黑名单管控", "isv.BLACK_KEY_CONTROL_LIMIT"),
    PARAM_LENGTH_LIMIT("参数超出长度限制", "isv.PARAM_LENGTH_LIMIT"),
    PARAM_NOT_SUPPORT_URL("不支持URL", "isv.PARAM_NOT_SUPPORT_URL"),
    AMOUNT_NOT_ENOUGH("账户余额不足", "isv.AMOUNT_NOT_ENOUGH"),
    SMS_SIGNATURE_SCENE_ILLEGAL("短信所使用签名场景非法,验证码签名只能匹配验证码模板", "isv.SMS_SIGNATURE_SCENE_ILLEGAL"),
    ;

    private String message;
    private String code;
    private String help;

    APICodeEnum(String message, String code, String help) {
        this.message = message;
        this.code = code;
        this.help = help;
    }

    APICodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getHelp() {
        return help;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static APICodeEnum getEnumByName(String code) {
        for (APICodeEnum e : APICodeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
