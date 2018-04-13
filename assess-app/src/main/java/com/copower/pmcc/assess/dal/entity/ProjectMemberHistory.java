package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class ProjectMemberHistory {
    private Integer id;

    private Integer projectId;

    private String userAccountManagerNew;

    private String userAccountMemberNew;

    private String userAccountQualityNew;

    private String userAccountManagerOld;

    private String userAccountMemberOld;

    private String userAccountQualityOld;

    private String creator;

    private Date created;

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

    public String getUserAccountManagerNew() {
        return userAccountManagerNew;
    }

    public void setUserAccountManagerNew(String userAccountManagerNew) {
        this.userAccountManagerNew = userAccountManagerNew == null ? null : userAccountManagerNew.trim();
    }

    public String getUserAccountMemberNew() {
        return userAccountMemberNew;
    }

    public void setUserAccountMemberNew(String userAccountMemberNew) {
        this.userAccountMemberNew = userAccountMemberNew == null ? null : userAccountMemberNew.trim();
    }

    public String getUserAccountQualityNew() {
        return userAccountQualityNew;
    }

    public void setUserAccountQualityNew(String userAccountQualityNew) {
        this.userAccountQualityNew = userAccountQualityNew == null ? null : userAccountQualityNew.trim();
    }

    public String getUserAccountManagerOld() {
        return userAccountManagerOld;
    }

    public void setUserAccountManagerOld(String userAccountManagerOld) {
        this.userAccountManagerOld = userAccountManagerOld == null ? null : userAccountManagerOld.trim();
    }

    public String getUserAccountMemberOld() {
        return userAccountMemberOld;
    }

    public void setUserAccountMemberOld(String userAccountMemberOld) {
        this.userAccountMemberOld = userAccountMemberOld == null ? null : userAccountMemberOld.trim();
    }

    public String getUserAccountQualityOld() {
        return userAccountQualityOld;
    }

    public void setUserAccountQualityOld(String userAccountQualityOld) {
        this.userAccountQualityOld = userAccountQualityOld == null ? null : userAccountQualityOld.trim();
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
}