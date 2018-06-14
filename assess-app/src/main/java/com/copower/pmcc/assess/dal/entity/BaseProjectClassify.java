package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class BaseProjectClassify {
    private Integer id;

    private Integer pid;

    private String name;

    private String fieldName;

    private Integer formModuleId;

    private String applyUrl;

    private String detailUrl;

    private Boolean bisDefault;

    private Boolean bisEnable;

    private Boolean bisDelete;

    private String remark;

    private Integer sorting;

    private Date created;

    private Date mdified;

    private String creator;

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

    public Integer getFormModuleId() {
        return formModuleId;
    }

    public void setFormModuleId(Integer formModuleId) {
        this.formModuleId = formModuleId;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl == null ? null : applyUrl.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    public Boolean getBisDefault() {
        return bisDefault;
    }

    public void setBisDefault(Boolean bisDefault) {
        this.bisDefault = bisDefault;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getMdified() {
        return mdified;
    }

    public void setMdified(Date mdified) {
        this.mdified = mdified;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}