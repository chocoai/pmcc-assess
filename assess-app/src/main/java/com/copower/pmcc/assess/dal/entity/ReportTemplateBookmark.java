package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class ReportTemplateBookmark {
    private Integer id;

    private Integer templateId;

    private String bookmarkName;

    private Integer dataPoolType;

    private Integer dataPoolTableId;

    private Integer dataPoolColumnsId;

    private Integer dataPoolTemplateId;

    private Integer pid;

    private Boolean bisEnable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName == null ? null : bookmarkName.trim();
    }

    public Integer getDataPoolType() {
        return dataPoolType;
    }

    public void setDataPoolType(Integer dataPoolType) {
        this.dataPoolType = dataPoolType;
    }

    public Integer getDataPoolTableId() {
        return dataPoolTableId;
    }

    public void setDataPoolTableId(Integer dataPoolTableId) {
        this.dataPoolTableId = dataPoolTableId;
    }

    public Integer getDataPoolColumnsId() {
        return dataPoolColumnsId;
    }

    public void setDataPoolColumnsId(Integer dataPoolColumnsId) {
        this.dataPoolColumnsId = dataPoolColumnsId;
    }

    public Integer getDataPoolTemplateId() {
        return dataPoolTemplateId;
    }

    public void setDataPoolTemplateId(Integer dataPoolTemplateId) {
        this.dataPoolTemplateId = dataPoolTemplateId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
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