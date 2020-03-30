package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclarePreSalePermit {
    private Integer id;

    private Integer planDetailsId;

    private String certificateNumber;

    private String issuingOrgan;

    private String salesUnit;

    private String legalRepresentative;

    private String beLocated;

    private String name;

    private BigDecimal preSaleArea;

    private String preSaleScope;

    private String housingUse;

    private String buildingStructure;

    private String preSaleSupervisionInformation;

    private Date date;

    private String mortgageSituation;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String remark;

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

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit == null ? null : salesUnit.trim();
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPreSaleArea() {
        return preSaleArea;
    }

    public void setPreSaleArea(BigDecimal preSaleArea) {
        this.preSaleArea = preSaleArea;
    }

    public String getPreSaleScope() {
        return preSaleScope;
    }

    public void setPreSaleScope(String preSaleScope) {
        this.preSaleScope = preSaleScope == null ? null : preSaleScope.trim();
    }

    public String getHousingUse() {
        return housingUse;
    }

    public void setHousingUse(String housingUse) {
        this.housingUse = housingUse == null ? null : housingUse.trim();
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure == null ? null : buildingStructure.trim();
    }

    public String getPreSaleSupervisionInformation() {
        return preSaleSupervisionInformation;
    }

    public void setPreSaleSupervisionInformation(String preSaleSupervisionInformation) {
        this.preSaleSupervisionInformation = preSaleSupervisionInformation == null ? null : preSaleSupervisionInformation.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMortgageSituation() {
        return mortgageSituation;
    }

    public void setMortgageSituation(String mortgageSituation) {
        this.mortgageSituation = mortgageSituation == null ? null : mortgageSituation.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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