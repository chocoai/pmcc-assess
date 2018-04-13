package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class BaseFormModuleField {
    private Integer id;

    private Integer formModuleId;

    private String name;

    private String jsonName;

    private String displayName;

    private String tableName;

    private Integer fieldType;

    private Integer fieldLength;

    private String defaultValue;

    private String dataSourceSql;

    private Integer sorting;

    private Boolean bisCacheDataSource;

    private String customUrl;

    private String customDisplayUrl;

    private String dataViewSql;

    private Integer width;

    private Integer listWidth;

    private Boolean bisCacheDataView;

    private Boolean bisJson;

    private Boolean bisRequired;

    private Boolean bisShow;

    private Boolean bisListShow;

    private Boolean bisAloneLine;

    private Boolean bisQuery;

    private Boolean bisEnable;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormModuleId() {
        return formModuleId;
    }

    public void setFormModuleId(Integer formModuleId) {
        this.formModuleId = formModuleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName == null ? null : jsonName.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getDataSourceSql() {
        return dataSourceSql;
    }

    public void setDataSourceSql(String dataSourceSql) {
        this.dataSourceSql = dataSourceSql == null ? null : dataSourceSql.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean getBisCacheDataSource() {
        return bisCacheDataSource;
    }

    public void setBisCacheDataSource(Boolean bisCacheDataSource) {
        this.bisCacheDataSource = bisCacheDataSource;
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

    public String getDataViewSql() {
        return dataViewSql;
    }

    public void setDataViewSql(String dataViewSql) {
        this.dataViewSql = dataViewSql == null ? null : dataViewSql.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getListWidth() {
        return listWidth;
    }

    public void setListWidth(Integer listWidth) {
        this.listWidth = listWidth;
    }

    public Boolean getBisCacheDataView() {
        return bisCacheDataView;
    }

    public void setBisCacheDataView(Boolean bisCacheDataView) {
        this.bisCacheDataView = bisCacheDataView;
    }

    public Boolean getBisJson() {
        return bisJson;
    }

    public void setBisJson(Boolean bisJson) {
        this.bisJson = bisJson;
    }

    public Boolean getBisRequired() {
        return bisRequired;
    }

    public void setBisRequired(Boolean bisRequired) {
        this.bisRequired = bisRequired;
    }

    public Boolean getBisShow() {
        return bisShow;
    }

    public void setBisShow(Boolean bisShow) {
        this.bisShow = bisShow;
    }

    public Boolean getBisListShow() {
        return bisListShow;
    }

    public void setBisListShow(Boolean bisListShow) {
        this.bisListShow = bisListShow;
    }

    public Boolean getBisAloneLine() {
        return bisAloneLine;
    }

    public void setBisAloneLine(Boolean bisAloneLine) {
        this.bisAloneLine = bisAloneLine;
    }

    public Boolean getBisQuery() {
        return bisQuery;
    }

    public void setBisQuery(Boolean bisQuery) {
        this.bisQuery = bisQuery;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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