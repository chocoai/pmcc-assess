package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataPropertyServiceItem {
    private Integer id;

    private Integer masterId;

    private Integer serviceType;

    private Integer serviceContent;

    private Date serviceTime;

    private Integer gradeEvaluation;

    private String chargesNotes;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(Integer serviceContent) {
        this.serviceContent = serviceContent;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getGradeEvaluation() {
        return gradeEvaluation;
    }

    public void setGradeEvaluation(Integer gradeEvaluation) {
        this.gradeEvaluation = gradeEvaluation;
    }

    public String getChargesNotes() {
        return chargesNotes;
    }

    public void setChargesNotes(String chargesNotes) {
        this.chargesNotes = chargesNotes == null ? null : chargesNotes.trim();
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