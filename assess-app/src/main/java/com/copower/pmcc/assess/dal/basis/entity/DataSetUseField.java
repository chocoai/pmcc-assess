package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataSetUseField {
    private Integer id;

    private Integer pid;

    private String name;

    private String fieldName;

    private Integer setUse;

    private String level;

    private Boolean bisPrimaryKey;

    private Boolean bisPrice;

    private Boolean bisOnlyView;

    private Boolean bisEnable;

    private Boolean bisDelete;

    private Boolean canShrink;

    private Boolean bisShow;

    private Integer sorting;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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
        this.name = name == null ? null : name.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public Integer getSetUse() {
        return setUse;
    }

    public void setSetUse(Integer setUse) {
        this.setUse = setUse;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Boolean getBisPrimaryKey() {
        return bisPrimaryKey;
    }

    public void setBisPrimaryKey(Boolean bisPrimaryKey) {
        this.bisPrimaryKey = bisPrimaryKey;
    }

    public Boolean getBisPrice() {
        return bisPrice;
    }

    public void setBisPrice(Boolean bisPrice) {
        this.bisPrice = bisPrice;
    }

    public Boolean getBisOnlyView() {
        return bisOnlyView;
    }

    public void setBisOnlyView(Boolean bisOnlyView) {
        this.bisOnlyView = bisOnlyView;
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

    public Boolean getCanShrink() {
        return canShrink;
    }

    public void setCanShrink(Boolean canShrink) {
        this.canShrink = canShrink;
    }

    public Boolean getBisShow() {
        return bisShow;
    }

    public void setBisShow(Boolean bisShow) {
        this.bisShow = bisShow;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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