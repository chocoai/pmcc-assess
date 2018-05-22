package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CompileReportDetails {
    private Integer id;

    private String textReplace;

    private String evaluationType;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextReplace() {
        return textReplace;
    }

    public void setTextReplace(String textReplace) {
        this.textReplace = textReplace == null ? null : textReplace.trim();
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType == null ? null : evaluationType.trim();
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}