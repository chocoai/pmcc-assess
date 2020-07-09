package com.copower.pmcc.assess.dto.input;

import java.io.Serializable;

/**
 * Created by kings on 2018-4-10.
 */
public class ZtreeDto implements Serializable,Cloneable,Comparable<ZtreeDto> {
    private Integer id;
    private Integer pid;
    private String name;
    private String displayName;
    private String key;
    private Integer _parentId;
    private Integer area;
    private String number;
    private Boolean isParent;
    private String type;
    private String tableName;
    private Integer tableId;
    private Boolean bisModify;
    private String creator;
    private String creatorName;
    private String executor;
    private Integer declareRecordId;
    private String declareRecordName;
    private Integer applyBatchId;
    private String icon;
    private boolean bisFromCase;

    public boolean isBisFromCase() {
        return bisFromCase;
    }

    public void setBisFromCase(boolean bisFromCase) {
        this.bisFromCase = bisFromCase;
    }

    public Integer getApplyBatchId() {
        return applyBatchId;
    }

    public void setApplyBatchId(Integer applyBatchId) {
        this.applyBatchId = applyBatchId;
    }

    public String getDeclareRecordName() {
        return declareRecordName;
    }

    public void setDeclareRecordName(String declareRecordName) {
        this.declareRecordName = declareRecordName;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Integer get_parentId() {
        return _parentId;
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public int compareTo(ZtreeDto o) {
        return this.getId().compareTo(o.getId());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Boolean getBisModify() {
        return bisModify;
    }

    public void setBisModify(Boolean bisModify) {
        this.bisModify = bisModify;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
