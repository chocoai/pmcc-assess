package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataReportAnalysis {
    private Integer id;

    private String name;

    private Integer reportAnalysisType;

    private String template;

    private String entrustmentPurpose;

    private Boolean bisModifiable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getReportAnalysisType() {
        return reportAnalysisType;
    }

    public void setReportAnalysisType(Integer reportAnalysisType) {
        this.reportAnalysisType = reportAnalysisType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getEntrustmentPurpose() {
        return entrustmentPurpose;
    }

    public void setEntrustmentPurpose(String entrustmentPurpose) {
        this.entrustmentPurpose = entrustmentPurpose == null ? null : entrustmentPurpose.trim();
    }

    public Boolean getBisModifiable() {
        return bisModifiable;
    }

    public void setBisModifiable(Boolean bisModifiable) {
        this.bisModifiable = bisModifiable;
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