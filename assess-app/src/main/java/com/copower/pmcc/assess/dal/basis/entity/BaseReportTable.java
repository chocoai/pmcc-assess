package com.copower.pmcc.assess.dal.basis.entity;

public class BaseReportTable {
    private Integer id;

    private String tableName;

    private String cnName;

    private Integer tableType;

    private Boolean bisEnable;

    private String foreignKey;

    private String foreignKeyType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey == null ? null : foreignKey.trim();
    }

    public String getForeignKeyType() {
        return foreignKeyType;
    }

    public void setForeignKeyType(String foreignKeyType) {
        this.foreignKeyType = foreignKeyType == null ? null : foreignKeyType.trim();
    }
}