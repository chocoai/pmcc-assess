package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class DataNumberRule {
    private Integer id;

    private Integer assessClass;

    private Integer reportType;

    private String prefix;

    private String dateRule;

    private Integer figures;

    private Integer startNumber;

    private Integer sameReportType;

    private Integer recount;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssessClass() {
        return assessClass;
    }

    public void setAssessClass(Integer assessClass) {
        this.assessClass = assessClass;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getDateRule() {
        return dateRule;
    }

    public void setDateRule(String dateRule) {
        this.dateRule = dateRule == null ? null : dateRule.trim();
    }

    public Integer getFigures() {
        return figures;
    }

    public void setFigures(Integer figures) {
        this.figures = figures;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getSameReportType() {
        return sameReportType;
    }

    public void setSameReportType(Integer sameReportType) {
        this.sameReportType = sameReportType;
    }

    public Integer getRecount() {
        return recount;
    }

    public void setRecount(Integer recount) {
        this.recount = recount;
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