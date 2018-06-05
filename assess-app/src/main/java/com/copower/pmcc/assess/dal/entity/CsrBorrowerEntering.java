package com.copower.pmcc.assess.dal.entity;

public class CsrBorrowerEntering {
    private Integer id;

    private Integer borrowerId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
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
}