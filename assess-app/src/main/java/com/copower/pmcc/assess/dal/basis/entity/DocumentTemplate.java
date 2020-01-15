package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DocumentTemplate {
    private Integer id;

    private String templateName;

    private Integer templateType;

    private Date provideDate;

    private Date gmtCreated;

    private Date gmtModified;

    private String assessProjectType;

    private Integer numbetRuleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public Date getProvideDate() {
        return provideDate;
    }

    public void setProvideDate(Date provideDate) {
        this.provideDate = provideDate;
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

    public String getAssessProjectType() {
        return assessProjectType;
    }

    public void setAssessProjectType(String assessProjectType) {
        this.assessProjectType = assessProjectType == null ? null : assessProjectType.trim();
    }

    public Integer getNumbetRuleId() {
        return numbetRuleId;
    }

    public void setNumbetRuleId(Integer numbetRuleId) {
        this.numbetRuleId = numbetRuleId;
    }
}