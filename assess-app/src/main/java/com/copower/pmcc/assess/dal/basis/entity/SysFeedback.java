package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SysFeedback {
    private Integer id;

    private Integer systemType;

    private Integer urgencyLevel;

    private String questionTitle;

    private String deatilDescription;

    private String detailEncode;

    private String feedbackPerson;

    private Integer status;

    private String disposeScheme;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(Integer urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle == null ? null : questionTitle.trim();
    }

    public String getDeatilDescription() {
        return deatilDescription;
    }

    public void setDeatilDescription(String deatilDescription) {
        this.deatilDescription = deatilDescription == null ? null : deatilDescription.trim();
    }

    public String getDetailEncode() {
        return detailEncode;
    }

    public void setDetailEncode(String detailEncode) {
        this.detailEncode = detailEncode == null ? null : detailEncode.trim();
    }

    public String getFeedbackPerson() {
        return feedbackPerson;
    }

    public void setFeedbackPerson(String feedbackPerson) {
        this.feedbackPerson = feedbackPerson == null ? null : feedbackPerson.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisposeScheme() {
        return disposeScheme;
    }

    public void setDisposeScheme(String disposeScheme) {
        this.disposeScheme = disposeScheme == null ? null : disposeScheme.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}