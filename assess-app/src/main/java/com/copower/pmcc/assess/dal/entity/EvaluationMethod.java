package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class EvaluationMethod {
    private Integer id;

    private Integer method;

    private String applicableReason;

    private String notApplicableReason;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
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

    @Override
    public String toString() {
        return "EvaluationMethod{" +
                "id=" + id +
                ", method=" + method +
                ", applicableReason='" + applicableReason + '\'' +
                ", notApplicableReason='" + notApplicableReason + '\'' +
                ", creator='" + creator + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}