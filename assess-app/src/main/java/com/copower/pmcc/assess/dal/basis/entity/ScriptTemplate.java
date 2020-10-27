package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ScriptTemplate {
    private Integer id;

    private String templateName;

    private String templateKey;

    private String templateOriginalText;

    private String scriptTemplate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public String getTemplateKey() {
        return templateKey;
    }

    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey == null ? null : templateKey.trim();
    }

    public String getTemplateOriginalText() {
        return templateOriginalText;
    }

    public void setTemplateOriginalText(String templateOriginalText) {
        this.templateOriginalText = templateOriginalText == null ? null : templateOriginalText.trim();
    }

    public String getScriptTemplate() {
        return scriptTemplate;
    }

    public void setScriptTemplate(String scriptTemplate) {
        this.scriptTemplate = scriptTemplate == null ? null : scriptTemplate.trim();
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