package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class CompileReportDetails {
    private Integer id;

    private Integer planDetailsId;

    private String content;

    private Integer reportAnalysisId;

    private Integer category;

    private Integer categoryField;

    private String template;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReportAnalysisId() {
        return reportAnalysisId;
    }

    public void setReportAnalysisId(Integer reportAnalysisId) {
        this.reportAnalysisId = reportAnalysisId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCategoryField() {
        return categoryField;
    }

    public void setCategoryField(Integer categoryField) {
        this.categoryField = categoryField;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
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