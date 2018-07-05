package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BaseReportTemplate {
    private Integer id;

    private String bookmarkName;

    private String bookmarkNameCn;

    private Integer dataPoolType;

    private Integer dataPoolTableId;

    private Integer dataPoolColumnsId;

    private Integer dataPoolTemplateId;

    private Integer pid;

    private Boolean bisEnable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer customerId;

    private Integer entrustId;

    private Integer reportTypeId;

    private Integer csType;

    private Integer templateType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName == null ? null : bookmarkName.trim();
    }

    public String getBookmarkNameCn() {
        return bookmarkNameCn;
    }

    public void setBookmarkNameCn(String bookmarkNameCn) {
        this.bookmarkNameCn = bookmarkNameCn == null ? null : bookmarkNameCn.trim();
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(Integer reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public Integer getCsType() {
        return csType;
    }

    public void setCsType(Integer csType) {
        this.csType = csType;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }
}