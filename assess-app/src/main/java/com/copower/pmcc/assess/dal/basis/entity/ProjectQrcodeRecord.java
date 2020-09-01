package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectQrcodeRecord {
    private Integer id;

    private Integer projectId;

    private Integer reportType;

    private Integer areaId;

    private Integer reportGroupId;

    private Integer projectDocumentId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String qrcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getReportGroupId() {
        return reportGroupId;
    }

    public void setReportGroupId(Integer reportGroupId) {
        this.reportGroupId = reportGroupId;
    }

    public Integer getProjectDocumentId() {
        return projectDocumentId;
    }

    public void setProjectDocumentId(Integer projectDocumentId) {
        this.projectDocumentId = projectDocumentId;
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

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }
}