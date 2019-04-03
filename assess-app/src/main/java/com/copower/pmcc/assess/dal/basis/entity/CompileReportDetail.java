package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class CompileReportDetail {
    private Integer id;

    private Integer planDetailsId;

    private Integer areaId;

    private Integer reportAnalysisType;

    private Integer marketBackgroundType;

    private String reportAnalysisName;

    private String name;

    private String template;

    private String content;

    private Boolean bisModifiable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String jsonContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getReportAnalysisType() {
        return reportAnalysisType;
    }

    public void setReportAnalysisType(Integer reportAnalysisType) {
        this.reportAnalysisType = reportAnalysisType;
    }

    public Integer getMarketBackgroundType() {
        return marketBackgroundType;
    }

    public void setMarketBackgroundType(Integer marketBackgroundType) {
        this.marketBackgroundType = marketBackgroundType;
    }

    public String getReportAnalysisName() {
        return reportAnalysisName;
    }

    public void setReportAnalysisName(String reportAnalysisName) {
        this.reportAnalysisName = reportAnalysisName == null ? null : reportAnalysisName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }
}