package com.copower.pmcc.assess.dal.entity;

public class BaseFormModule {
    private Integer id;

    private Integer formId;

    private String cnName;

    private String name;

    private Boolean bisEnable;

    private Boolean bisConfigure;

    private String tableName;

    private String foreignKeyName;

    private Boolean bisMultiple;

    private String customUrl;

    private String customDisplayUrl;

    private Boolean bisCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisConfigure() {
        return bisConfigure;
    }

    public void setBisConfigure(Boolean bisConfigure) {
        this.bisConfigure = bisConfigure;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName == null ? null : foreignKeyName.trim();
    }

    public Boolean getBisMultiple() {
        return bisMultiple;
    }

    public void setBisMultiple(Boolean bisMultiple) {
        this.bisMultiple = bisMultiple;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl == null ? null : customUrl.trim();
    }

    public String getCustomDisplayUrl() {
        return customDisplayUrl;
    }

    public void setCustomDisplayUrl(String customDisplayUrl) {
        this.customDisplayUrl = customDisplayUrl == null ? null : customDisplayUrl.trim();
    }

    public Boolean getBisCard() {
        return bisCard;
    }

    public void setBisCard(Boolean bisCard) {
        this.bisCard = bisCard;
    }
}