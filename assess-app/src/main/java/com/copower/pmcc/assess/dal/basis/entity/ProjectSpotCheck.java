package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectSpotCheck {
    private Integer id;

    private String title;

    private String bySpotUser;

    private String spotMonth;

    private String remark;

    private String processInsId;

    private String status;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBySpotUser() {
        return bySpotUser;
    }

    public void setBySpotUser(String bySpotUser) {
        this.bySpotUser = bySpotUser == null ? null : bySpotUser.trim();
    }

    public String getSpotMonth() {
        return spotMonth;
    }

    public void setSpotMonth(String spotMonth) {
        this.spotMonth = spotMonth == null ? null : spotMonth.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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