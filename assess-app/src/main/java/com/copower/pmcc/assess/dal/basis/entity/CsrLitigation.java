package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class CsrLitigation {
    private Integer id;

    private Integer csrProjectId;

    private String borrowerId;

    private String litigationPreservation;

    private String litigationPreservationInfo;

    private Boolean bisImport;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCsrProjectId() {
        return csrProjectId;
    }

    public void setCsrProjectId(Integer csrProjectId) {
        this.csrProjectId = csrProjectId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId == null ? null : borrowerId.trim();
    }

    public String getLitigationPreservation() {
        return litigationPreservation;
    }

    public void setLitigationPreservation(String litigationPreservation) {
        this.litigationPreservation = litigationPreservation == null ? null : litigationPreservation.trim();
    }

    public String getLitigationPreservationInfo() {
        return litigationPreservationInfo;
    }

    public void setLitigationPreservationInfo(String litigationPreservationInfo) {
        this.litigationPreservationInfo = litigationPreservationInfo == null ? null : litigationPreservationInfo.trim();
    }

    public Boolean getBisImport() {
        return bisImport;
    }

    public void setBisImport(Boolean bisImport) {
        this.bisImport = bisImport;
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