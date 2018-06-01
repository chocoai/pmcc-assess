package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CsrBorrower {
    private Integer id;

    private Integer csrProjectId;

    private Integer projectId;

    private Integer groupId;

    private String firstLevelBranch;

    private String secondLevelBranch;

    private String name;

    private String idNumber;

    private String maritalStatus;

    private String education;

    private String workUnit;

    private String post;

    private String domicilePlace;

    private String presentAddress;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getFirstLevelBranch() {
        return firstLevelBranch;
    }

    public void setFirstLevelBranch(String firstLevelBranch) {
        this.firstLevelBranch = firstLevelBranch == null ? null : firstLevelBranch.trim();
    }

    public String getSecondLevelBranch() {
        return secondLevelBranch;
    }

    public void setSecondLevelBranch(String secondLevelBranch) {
        this.secondLevelBranch = secondLevelBranch == null ? null : secondLevelBranch.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getDomicilePlace() {
        return domicilePlace;
    }

    public void setDomicilePlace(String domicilePlace) {
        this.domicilePlace = domicilePlace == null ? null : domicilePlace.trim();
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress == null ? null : presentAddress.trim();
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