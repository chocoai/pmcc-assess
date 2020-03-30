package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicApplyBatchDetail {
    private Integer id;

    private Integer pid;

    private Integer applyBatchId;

    private Integer declareRecordId;

    private String declareRecordName;

    private String tableName;

    private Integer tableId;

    private String type;

    private String name;

    private String displayName;

    private String executor;

    private Integer quoteId;

    private String baseType;

    private Integer caseTablePid;

    private Integer upgradeTableId;

    private Boolean bisStructure;

    private Boolean bisStandard;

    private Boolean bisFromCase;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String fullName;

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

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getDeclareRecordName() {
        return declareRecordName;
    }

    public void setDeclareRecordName(String declareRecordName) {
        this.declareRecordName = declareRecordName == null ? null : declareRecordName.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor == null ? null : executor.trim();
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

    public Integer getCaseTablePid() {
        return caseTablePid;
    }

    public void setCaseTablePid(Integer caseTablePid) {
        this.caseTablePid = caseTablePid;
    }

    public Integer getUpgradeTableId() {
        return upgradeTableId;
    }

    public void setUpgradeTableId(Integer upgradeTableId) {
        this.upgradeTableId = upgradeTableId;
    }

    public Boolean getBisStructure() {
        return bisStructure;
    }

    public void setBisStructure(Boolean bisStructure) {
        this.bisStructure = bisStructure;
    }

    public Boolean getBisStandard() {
        return bisStandard;
    }

    public void setBisStandard(Boolean bisStandard) {
        this.bisStandard = bisStandard;
    }

    public Boolean getBisFromCase() {
        return bisFromCase;
    }

    public void setBisFromCase(Boolean bisFromCase) {
        this.bisFromCase = bisFromCase;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }
}