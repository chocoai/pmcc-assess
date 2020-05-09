package com.copower.pmcc.assess.dto.input.aliyun;


import java.io.Serializable;

/**
 * 短信模板 Dto
 */
public class SmsTemplateDto implements Serializable, Comparable<SmsTemplateDto> {

    //这里和阿里云是匹配的  阿里那前缀就是大写开头,不要随便更改成员变量

    //短信模板CODE 这个是最重要的,发短信就需要这个 相当于id
    private String TemplateCode;
    //状态码的描述
    private String Message;
    private String RequestId;
    //状态代码 请求状态码。 返回OK代表请求成功

    private String Code;

    //短信模板的创建日期和时间
    private String CreateDate;

    //短信类型
    private Integer TemplateType;
    //模板审核状态
    private Integer TemplateStatus;
    //审核备注
    private String Reason;

    //模板内容
    private String TemplateContent;
    private String TemplateName;
    private String Remark;
    private String Action;

    public String getTemplateCode() {
        return TemplateCode;
    }

    public void setTemplateCode(String templateCode) {
        TemplateCode = templateCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }


    @Override
    public int compareTo(SmsTemplateDto o) {
        return this.TemplateCode.compareTo(o.TemplateCode);
    }

    @Override
    public String toString() {
        return "SmsTemplateDto{" +
                "TemplateCode='" + TemplateCode + '\'' +
                ", Message='" + Message + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", Code='" + Code + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                ", TemplateType=" + TemplateType +
                ", TemplateStatus=" + TemplateStatus +
                ", Reason='" + Reason + '\'' +
                ", TemplateContent='" + TemplateContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsTemplateDto that = (SmsTemplateDto) o;

        if (TemplateCode != null ? !TemplateCode.equals(that.TemplateCode) : that.TemplateCode != null) return false;
        if (Message != null ? !Message.equals(that.Message) : that.Message != null) return false;
        if (RequestId != null ? !RequestId.equals(that.RequestId) : that.RequestId != null) return false;
        if (Code != null ? !Code.equals(that.Code) : that.Code != null) return false;
        if (CreateDate != null ? !CreateDate.equals(that.CreateDate) : that.CreateDate != null) return false;
        if (TemplateType != null ? !TemplateType.equals(that.TemplateType) : that.TemplateType != null) return false;
        if (TemplateStatus != null ? !TemplateStatus.equals(that.TemplateStatus) : that.TemplateStatus != null)
            return false;
        if (Reason != null ? !Reason.equals(that.Reason) : that.Reason != null) return false;
        return TemplateContent != null ? TemplateContent.equals(that.TemplateContent) : that.TemplateContent == null;
    }

    @Override
    public int hashCode() {
        int result = TemplateCode != null ? TemplateCode.hashCode() : 0;
        result = 31 * result + (Message != null ? Message.hashCode() : 0);
        result = 31 * result + (RequestId != null ? RequestId.hashCode() : 0);
        result = 31 * result + (Code != null ? Code.hashCode() : 0);
        result = 31 * result + (CreateDate != null ? CreateDate.hashCode() : 0);
        result = 31 * result + (TemplateType != null ? TemplateType.hashCode() : 0);
        result = 31 * result + (TemplateStatus != null ? TemplateStatus.hashCode() : 0);
        result = 31 * result + (Reason != null ? Reason.hashCode() : 0);
        result = 31 * result + (TemplateContent != null ? TemplateContent.hashCode() : 0);
        return result;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public Integer getTemplateType() {
        return TemplateType;
    }

    public void setTemplateType(Integer templateType) {
        TemplateType = templateType;
    }

    public Integer getTemplateStatus() {
        return TemplateStatus;
    }

    public void setTemplateStatus(Integer templateStatus) {
        TemplateStatus = templateStatus;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getTemplateContent() {
        return TemplateContent;
    }

    public void setTemplateContent(String templateContent) {
        TemplateContent = templateContent;
    }

    public String getTemplateName() {
        return TemplateName;
    }

    public void setTemplateName(String templateName) {
        TemplateName = templateName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
