package com.copower.pmcc.assess.dal.entity;

public class ReportColumns {
    private Integer id;

    private Integer tableId;

    private String columnsName;

    private String columnsCnName;

    private Boolean bisEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getColumnsName() {
        return columnsName;
    }

    public void setColumnsName(String columnsName) {
        this.columnsName = columnsName == null ? null : columnsName.trim();
    }

    public String getColumnsCnName() {
        return columnsCnName;
    }

    public void setColumnsCnName(String columnsCnName) {
        this.columnsCnName = columnsCnName == null ? null : columnsCnName.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }
}