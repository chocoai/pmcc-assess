package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicApplyBatchDetail {
    private Integer id;

    private Integer pid;

    private Integer applyBatchId;

    private String tableName;

    private Integer tableId;

    private String name;

    private String displayName;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Boolean bisStandard;

    private Integer quoteId;

    private String baseType;

    private Integer caseTablePid;

    private Boolean bisEnable;

    private Integer upgradeTableId;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer caseTablePid;

    private Boolean bisEnable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer upgradeTableId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getApplyBatchId() {
        return applyBatchId;
    }

    public void setApplyBatchId(Integer applyBatchId) {
        this.applyBatchId = applyBatchId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
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

    public Boolean getBisStandard() {
        return bisStandard;
    }

    public void setBisStandard(Boolean bisStandard) {
        this.bisStandard = bisStandard;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType == null ? null : baseType.trim();
    }
}