package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SchemeJudgeFunction {
    private Integer id;

    private Integer areaGroupId;

    private Integer judgeObjectId;

    private String name;

    private Integer methodType;

    private Boolean bisApplicable;

    private String applicableReason;

    private String notApplicableReason;

    private String thinking;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getThinking() {
        return thinking;
    }

    public void setThinking(String thinking) {
        this.thinking = thinking == null ? null : thinking.trim();
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