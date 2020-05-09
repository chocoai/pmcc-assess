package com.copower.pmcc.assess.dto.input.aliyun;

import java.io.Serializable;

/**
 * 短信签名
 */
public class SmsSignDto implements Serializable {
    //这里和阿里云是匹配的  阿里那前缀就是大写开头,不要随便更改成员变量



    //请求状态码 返回OK代表请求成功
    private String Code;

    //短信签名的创建日期和时间
    private String CreateDate;

    //状态码描述
    private String Message;

    //审核备注
    private String Reason;

    private String RequestId;

    //短信签名 ,相当于唯一 id
    private String SignName;

    //签名审核状态
    private Integer SignStatus;

    private String SignSource ;

    private String Remark;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getSignName() {
        return SignName;
    }

    public void setSignName(String signName) {
        SignName = signName;
    }

    public Integer getSignStatus() {
        return SignStatus;
    }

    public void setSignStatus(Integer signStatus) {
        SignStatus = signStatus;
    }

    public String getSignSource() {
        return SignSource;
    }

    public void setSignSource(String signSource) {
        SignSource = signSource;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
