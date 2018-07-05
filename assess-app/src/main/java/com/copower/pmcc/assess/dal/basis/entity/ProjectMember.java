package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectMember {
    private Integer id;

    private Integer projectId;

    private String userAccountManager;

    private String userAccountMember;

    private String userAccountQuality;

    private String remarks;

    private String creator;

    private Date created;

    private Date modified;

    private Boolean bisEnable;

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

    public String getUserAccountManager() {
        return userAccountManager;
    }

    public void setUserAccountManager(String userAccountManager) {
        this.userAccountManager = userAccountManager == null ? null : userAccountManager.trim();
    }

    public String getUserAccountMember() {
        return userAccountMember;
    }

    public void setUserAccountMember(String userAccountMember) {
        this.userAccountMember = userAccountMember == null ? null : userAccountMember.trim();
    }

    public String getUserAccountQuality() {
        return userAccountQuality;
    }

    public void setUserAccountQuality(String userAccountQuality) {
        this.userAccountQuality = userAccountQuality == null ? null : userAccountQuality.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }
}