package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class ReportTemplateBookmark {
    private Integer id;

    private Integer templateId;

    private String name;

    private String valueSql;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer fieldId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValueSql() {
        return valueSql;
    }

    public void setValueSql(String valueSql) {
        this.valueSql = valueSql == null ? null : valueSql.trim();
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

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }
}