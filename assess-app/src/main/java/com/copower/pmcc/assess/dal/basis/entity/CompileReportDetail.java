package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class CompileReportDetail {
    private Integer id;

    private Integer planDetailsId;

    private Integer reportAnalysisType;

    private String reportAnalysisName;

    private String name;

    private String template;

    private String content;

    private String jsonContent;

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

    public Integer getReportAnalysisType() {
        return reportAnalysisType;
    }

    public void setReportAnalysisType(Integer reportAnalysisType) {
        this.reportAnalysisType = reportAnalysisType;
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

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
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