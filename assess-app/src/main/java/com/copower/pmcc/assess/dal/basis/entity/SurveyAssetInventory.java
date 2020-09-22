package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInventory {
    private Integer id;

    private Integer projectId;

    private Integer planDetailId;

    private String processInsId;

    private Integer groupId;

    private Integer declareRecordId;

    private String evaluator;

    private Date checkDate;

    private Integer findMethod;

    private Integer findOriginal;

    private String networkRemark;

    private String networkAddress;

    private String affected;

    private String influenceFactor;

    private String influenceFactorRemarkText;

    private Boolean bisCheckOriginal;

    private String remark;

    private String specialCase;

    private String segmentationLimit;

    private String canUse;

    private Integer application;

    private String certificate;

    private String rimIsNormal;

    private String zoneDamage;

    private String abnormality;

    private String entityIsDamage;

    private String entityDamage;

    private String damageRemark;

    private String paymentStatus;

    private String paymentContent;

    private String transferLimit;

    private String otherProject;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String location;

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

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator == null ? null : evaluator.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getFindMethod() {
        return findMethod;
    }

    public void setFindMethod(Integer findMethod) {
        this.findMethod = findMethod;
    }

    public Integer getFindOriginal() {
        return findOriginal;
    }

    public void setFindOriginal(Integer findOriginal) {
        this.findOriginal = findOriginal;
    }

    public String getNetworkRemark() {
        return networkRemark;
    }

    public void setNetworkRemark(String networkRemark) {
        this.networkRemark = networkRemark == null ? null : networkRemark.trim();
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress == null ? null : networkAddress.trim();
    }

    public String getAffected() {
        return affected;
    }

    public void setAffected(String affected) {
        this.affected = affected == null ? null : affected.trim();
    }

    public String getInfluenceFactor() {
        return influenceFactor;
    }

    public void setInfluenceFactor(String influenceFactor) {
        this.influenceFactor = influenceFactor == null ? null : influenceFactor.trim();
    }

    public String getInfluenceFactorRemarkText() {
        return influenceFactorRemarkText;
    }

    public void setInfluenceFactorRemarkText(String influenceFactorRemarkText) {
        this.influenceFactorRemarkText = influenceFactorRemarkText == null ? null : influenceFactorRemarkText.trim();
    }

    public Boolean getBisCheckOriginal() {
        return bisCheckOriginal;
    }

    public void setBisCheckOriginal(Boolean bisCheckOriginal) {
        this.bisCheckOriginal = bisCheckOriginal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(String specialCase) {
        this.specialCase = specialCase == null ? null : specialCase.trim();
    }

    public String getSegmentationLimit() {
        return segmentationLimit;
    }

    public void setSegmentationLimit(String segmentationLimit) {
        this.segmentationLimit = segmentationLimit == null ? null : segmentationLimit.trim();
    }

    public String getCanUse() {
        return canUse;
    }

    public void setCanUse(String canUse) {
        this.canUse = canUse == null ? null : canUse.trim();
    }

    public Integer getApplication() {
        return application;
    }

    public void setApplication(Integer application) {
        this.application = application;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public String getRimIsNormal() {
        return rimIsNormal;
    }

    public void setRimIsNormal(String rimIsNormal) {
        this.rimIsNormal = rimIsNormal == null ? null : rimIsNormal.trim();
    }

    public String getZoneDamage() {
        return zoneDamage;
    }

    public void setZoneDamage(String zoneDamage) {
        this.zoneDamage = zoneDamage == null ? null : zoneDamage.trim();
    }

    public String getAbnormality() {
        return abnormality;
    }

    public void setAbnormality(String abnormality) {
        this.abnormality = abnormality == null ? null : abnormality.trim();
    }

    public String getEntityIsDamage() {
        return entityIsDamage;
    }

    public void setEntityIsDamage(String entityIsDamage) {
        this.entityIsDamage = entityIsDamage == null ? null : entityIsDamage.trim();
    }

    public String getEntityDamage() {
        return entityDamage;
    }

    public void setEntityDamage(String entityDamage) {
        this.entityDamage = entityDamage == null ? null : entityDamage.trim();
    }

    public String getDamageRemark() {
        return damageRemark;
    }

    public void setDamageRemark(String damageRemark) {
        this.damageRemark = damageRemark == null ? null : damageRemark.trim();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus == null ? null : paymentStatus.trim();
    }

    public String getPaymentContent() {
        return paymentContent;
    }

    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent == null ? null : paymentContent.trim();
    }

    public String getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(String transferLimit) {
        this.transferLimit = transferLimit == null ? null : transferLimit.trim();
    }

    public String getOtherProject() {
        return otherProject;
    }

    public void setOtherProject(String otherProject) {
        this.otherProject = otherProject == null ? null : otherProject.trim();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}