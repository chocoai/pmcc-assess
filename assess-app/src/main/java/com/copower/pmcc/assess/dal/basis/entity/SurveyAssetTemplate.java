package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetTemplate {
    private Integer id;

    private Integer projectId;

    private Integer planDetailId;

    private Integer inventoryContent;

    private String areConsistent;

    private String registrationAddress;

    private String actualAddress;

    private String differenceReason;

    private String credential;

    private String credentialAccessory;

    private String voucher;

    private Date surveyTime;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String spareField;

    private Integer pid;

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

    public Integer getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(Integer planDetailId) {
        this.planDetailId = planDetailId;
    }

    public Integer getInventoryContent() {
        return inventoryContent;
    }

    public void setInventoryContent(Integer inventoryContent) {
        this.inventoryContent = inventoryContent;
    }

    public String getAreConsistent() {
        return areConsistent;
    }

    public void setAreConsistent(String areConsistent) {
        this.areConsistent = areConsistent == null ? null : areConsistent.trim();
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress == null ? null : registrationAddress.trim();
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress == null ? null : actualAddress.trim();
    }

    public String getDifferenceReason() {
        return differenceReason;
    }

    public void setDifferenceReason(String differenceReason) {
        this.differenceReason = differenceReason == null ? null : differenceReason.trim();
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
    }

    public String getCredentialAccessory() {
        return credentialAccessory;
    }

    public void setCredentialAccessory(String credentialAccessory) {
        this.credentialAccessory = credentialAccessory == null ? null : credentialAccessory.trim();
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher == null ? null : voucher.trim();
    }

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
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

    public String getSpareField() {
        return spareField;
    }

    public void setSpareField(String spareField) {
        this.spareField = spareField == null ? null : spareField.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}