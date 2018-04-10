package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class BaseDataDic {
    private Integer id;

    private Integer pid;

    private String name;

    private String groupKey;

    private Boolean bisEnable;

    private Boolean bisDelete;

    private String remark;

    private Integer sorting;

    private Date created;

    private Date mdified;

    private String creator;

    private String fieldName;

    private String url;

    private Boolean forbidModify;

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

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey == null ? null : groupKey.trim();
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getForbidModify() {
        return forbidModify;
    }

    public void setForbidModify(Boolean forbidModify) {
        this.forbidModify = forbidModify;
    }
}