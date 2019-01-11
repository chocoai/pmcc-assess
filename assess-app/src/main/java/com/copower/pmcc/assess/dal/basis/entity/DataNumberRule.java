package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataNumberRule {
    private Integer id;

    private Integer reportType;

    private String prefix;

    private String numberRule;

    private Integer figures;

    private Integer startYear;

    private Integer startNumber;

    private Integer sameReportType;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNumberRule() {
        return numberRule;
    }

    public void setNumberRule(String numberRule) {
        this.numberRule = numberRule == null ? null : numberRule.trim();
    }

    public Integer getFigures() {
        return figures;
    }

    public void setFigures(Integer figures) {
        this.figures = figures;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
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