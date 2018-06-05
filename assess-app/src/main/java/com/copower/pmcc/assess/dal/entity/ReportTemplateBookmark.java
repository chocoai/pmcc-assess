package com.copower.pmcc.assess.dal.entity;

public class ReportTemplateBookmark {
    private Integer id;

    private Integer templateId;

    private Integer templateType;

    private String bookmarkName;

    private Integer dataPoolType;

    private Integer dataPoolTableId;

    private Integer dataPoolColumnsId;

    private Integer dataPoolTemplateId;

    private Boolean bisEnable;

    private Integer pid;

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

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
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

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}