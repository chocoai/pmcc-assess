package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class SchemeJudgeFunction {
    private Integer id;

    private Integer judgeObjectId;

    private Integer methodType;

    private Boolean bisApplicable;

    private String applicableReason;

    private String notApplicableReason;

    private String applicableThinking;

    private String notApplicableThinking;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    public Boolean getBisApplicable() {
        return bisApplicable;
    }

    public void setBisApplicable(Boolean bisApplicable) {
        this.bisApplicable = bisApplicable;
    }

    public String getApplicableReason() {
        return applicableReason;
    }

    public void setApplicableReason(String applicableReason) {
        this.applicableReason = applicableReason == null ? null : applicableReason.trim();
    }

    public String getNotApplicableReason() {
        return notApplicableReason;
    }

    public void setNotApplicableReason(String notApplicableReason) {
        this.notApplicableReason = notApplicableReason == null ? null : notApplicableReason.trim();
    }

    public String getApplicableThinking() {
        return applicableThinking;
    }

    public void setApplicableThinking(String applicableThinking) {
        this.applicableThinking = applicableThinking == null ? null : applicableThinking.trim();
    }

    public String getNotApplicableThinking() {
        return notApplicableThinking;
    }

    public void setNotApplicableThinking(String notApplicableThinking) {
        this.notApplicableThinking = notApplicableThinking == null ? null : notApplicableThinking.trim();
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