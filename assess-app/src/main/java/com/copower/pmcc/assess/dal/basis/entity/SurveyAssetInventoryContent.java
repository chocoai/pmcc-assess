package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInventoryContent {
    private Integer id;

    private Integer masterId;

    private Integer infoItemId;

    private Integer groupId;

    private Integer projectId;

    private Integer planDetailsId;

    private Integer declareId;

    private Integer inventoryContent;

    private String areConsistent;

    private String registration;

    private String actual;

    private String differenceReason;

    private String credential;

    private String credentialAccessory;

    private String voucher;

    private Date surveyTime;

    private String sureConsistent;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getInfoItemId() {
        return infoItemId;
    }

    public void setInfoItemId(Integer infoItemId) {
        this.infoItemId = infoItemId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration == null ? null : registration.trim();
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual == null ? null : actual.trim();
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

    public String getSureConsistent() {
        return sureConsistent;
    }

    public void setSureConsistent(String sureConsistent) {
        this.sureConsistent = sureConsistent == null ? null : sureConsistent.trim();
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