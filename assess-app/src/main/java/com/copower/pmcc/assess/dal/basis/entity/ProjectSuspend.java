package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectSuspend {
    private Integer id;

    private Integer projectId;

    private String suspendUserAccount;

    private Date supendDate;

    private String suspendReason;

    private String creator;

    private Date created;

    private Boolean bisEnable;

    private Date restartDate;

    private String restartUserAccount;

    private String processInsId;

    private String status;

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

    public String getSuspendUserAccount() {
        return suspendUserAccount;
    }

    public void setSuspendUserAccount(String suspendUserAccount) {
        this.suspendUserAccount = suspendUserAccount == null ? null : suspendUserAccount.trim();
    }

    public Date getSupendDate() {
        return supendDate;
    }

    public void setSupendDate(Date supendDate) {
        this.supendDate = supendDate;
    }

    public String getSuspendReason() {
        return suspendReason;
    }

    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason == null ? null : suspendReason.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Date getRestartDate() {
        return restartDate;
    }

    public void setRestartDate(Date restartDate) {
        this.restartDate = restartDate;
    }

    public String getRestartUserAccount() {
        return restartUserAccount;
    }

    public void setRestartUserAccount(String restartUserAccount) {
        this.restartUserAccount = restartUserAccount == null ? null : restartUserAccount.trim();
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
}