package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DeclareBuildingConstructionPermit {
    private Integer id;

    private Integer planDetailsId;

    private String certificateNumber;

    private String issuingOrgan;

    private Date date;

    private String buildUnit;

    private String name;

    private String buildAddress;

    private String scaleConstruction;

    private String reconnaissanceUnit;

    private String designUnit;

    private String constructionUnit;

    private String constructionControlUnit;

    private String reconnaissanceUnitPerson;

    private String designUnitPerson;

    private String constructionUnitPerson;

    private String chiefEngineerConstructionInspection;

    private Date contractPeriod;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer masterId;

    private String masterType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
    }

    public String getIssuingOrgan() {
        return issuingOrgan;
    }

    public void setIssuingOrgan(String issuingOrgan) {
        this.issuingOrgan = issuingOrgan == null ? null : issuingOrgan.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBuildUnit() {
        return buildUnit;
    }

    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit == null ? null : buildUnit.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBuildAddress() {
        return buildAddress;
    }

    public void setBuildAddress(String buildAddress) {
        this.buildAddress = buildAddress == null ? null : buildAddress.trim();
    }

    public String getScaleConstruction() {
        return scaleConstruction;
    }

    public void setScaleConstruction(String scaleConstruction) {
        this.scaleConstruction = scaleConstruction == null ? null : scaleConstruction.trim();
    }

    public String getReconnaissanceUnit() {
        return reconnaissanceUnit;
    }

    public void setReconnaissanceUnit(String reconnaissanceUnit) {
        this.reconnaissanceUnit = reconnaissanceUnit == null ? null : reconnaissanceUnit.trim();
    }

    public String getDesignUnit() {
        return designUnit;
    }

    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit == null ? null : designUnit.trim();
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit == null ? null : constructionUnit.trim();
    }

    public String getConstructionControlUnit() {
        return constructionControlUnit;
    }

    public void setConstructionControlUnit(String constructionControlUnit) {
        this.constructionControlUnit = constructionControlUnit == null ? null : constructionControlUnit.trim();
    }

    public String getReconnaissanceUnitPerson() {
        return reconnaissanceUnitPerson;
    }

    public void setReconnaissanceUnitPerson(String reconnaissanceUnitPerson) {
        this.reconnaissanceUnitPerson = reconnaissanceUnitPerson == null ? null : reconnaissanceUnitPerson.trim();
    }

    public String getDesignUnitPerson() {
        return designUnitPerson;
    }

    public void setDesignUnitPerson(String designUnitPerson) {
        this.designUnitPerson = designUnitPerson == null ? null : designUnitPerson.trim();
    }

    public String getConstructionUnitPerson() {
        return constructionUnitPerson;
    }

    public void setConstructionUnitPerson(String constructionUnitPerson) {
        this.constructionUnitPerson = constructionUnitPerson == null ? null : constructionUnitPerson.trim();
    }

    public String getChiefEngineerConstructionInspection() {
        return chiefEngineerConstructionInspection;
    }

    public void setChiefEngineerConstructionInspection(String chiefEngineerConstructionInspection) {
        this.chiefEngineerConstructionInspection = chiefEngineerConstructionInspection == null ? null : chiefEngineerConstructionInspection.trim();
    }

    public Date getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Date contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType == null ? null : masterType.trim();
    }
}