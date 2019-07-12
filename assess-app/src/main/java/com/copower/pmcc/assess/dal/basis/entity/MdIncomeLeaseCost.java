package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeLeaseCost {
    private Integer id;

    private Integer incomeId;

    private Integer sectionId;

    private BigDecimal replacementValue;

    private String managementCost;

    private BigDecimal managementCostRatio;

    private String maintenance;

    private BigDecimal maintenanceCostRatio;

    private String additional;

    private BigDecimal additionalRatio;

    private String insurancePremium;

    private BigDecimal insurancePremiumRatio;

    private BigDecimal landUseTax;

    private String useTaxParameter;

    private BigDecimal transactionTaxeFeeRatio;

    private String transactionTaxeFeeExplain;

    private BigDecimal propertyTaxRatio;

    private BigDecimal stampDutyRatio;

    private BigDecimal salesTaxRatio;

    private BigDecimal constructionTaxRatio;

    private BigDecimal localEducationRatio;

    private BigDecimal educationRatio;

    private Integer sorting;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public BigDecimal getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }

    public String getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(String managementCost) {
        this.managementCost = managementCost == null ? null : managementCost.trim();
    }

    public BigDecimal getManagementCostRatio() {
        return managementCostRatio;
    }

    public void setManagementCostRatio(BigDecimal managementCostRatio) {
        this.managementCostRatio = managementCostRatio;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance == null ? null : maintenance.trim();
    }

    public BigDecimal getMaintenanceCostRatio() {
        return maintenanceCostRatio;
    }

    public void setMaintenanceCostRatio(BigDecimal maintenanceCostRatio) {
        this.maintenanceCostRatio = maintenanceCostRatio;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional == null ? null : additional.trim();
    }

    public BigDecimal getAdditionalRatio() {
        return additionalRatio;
    }

    public void setAdditionalRatio(BigDecimal additionalRatio) {
        this.additionalRatio = additionalRatio;
    }

    public String getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(String insurancePremium) {
        this.insurancePremium = insurancePremium == null ? null : insurancePremium.trim();
    }

    public BigDecimal getInsurancePremiumRatio() {
        return insurancePremiumRatio;
    }

    public void setInsurancePremiumRatio(BigDecimal insurancePremiumRatio) {
        this.insurancePremiumRatio = insurancePremiumRatio;
    }

    public BigDecimal getLandUseTax() {
        return landUseTax;
    }

    public void setLandUseTax(BigDecimal landUseTax) {
        this.landUseTax = landUseTax;
    }

    public String getUseTaxParameter() {
        return useTaxParameter;
    }

    public void setUseTaxParameter(String useTaxParameter) {
        this.useTaxParameter = useTaxParameter == null ? null : useTaxParameter.trim();
    }

    public BigDecimal getTransactionTaxeFeeRatio() {
        return transactionTaxeFeeRatio;
    }

    public void setTransactionTaxeFeeRatio(BigDecimal transactionTaxeFeeRatio) {
        this.transactionTaxeFeeRatio = transactionTaxeFeeRatio;
    }

    public String getTransactionTaxeFeeExplain() {
        return transactionTaxeFeeExplain;
    }

    public void setTransactionTaxeFeeExplain(String transactionTaxeFeeExplain) {
        this.transactionTaxeFeeExplain = transactionTaxeFeeExplain == null ? null : transactionTaxeFeeExplain.trim();
    }

    public BigDecimal getPropertyTaxRatio() {
        return propertyTaxRatio;
    }

    public void setPropertyTaxRatio(BigDecimal propertyTaxRatio) {
        this.propertyTaxRatio = propertyTaxRatio;
    }

    public BigDecimal getStampDutyRatio() {
        return stampDutyRatio;
    }

    public void setStampDutyRatio(BigDecimal stampDutyRatio) {
        this.stampDutyRatio = stampDutyRatio;
    }

    public BigDecimal getSalesTaxRatio() {
        return salesTaxRatio;
    }

    public void setSalesTaxRatio(BigDecimal salesTaxRatio) {
        this.salesTaxRatio = salesTaxRatio;
    }

    public BigDecimal getConstructionTaxRatio() {
        return constructionTaxRatio;
    }

    public void setConstructionTaxRatio(BigDecimal constructionTaxRatio) {
        this.constructionTaxRatio = constructionTaxRatio;
    }

    public BigDecimal getLocalEducationRatio() {
        return localEducationRatio;
    }

    public void setLocalEducationRatio(BigDecimal localEducationRatio) {
        this.localEducationRatio = localEducationRatio;
    }

    public BigDecimal getEducationRatio() {
        return educationRatio;
    }

    public void setEducationRatio(BigDecimal educationRatio) {
        this.educationRatio = educationRatio;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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