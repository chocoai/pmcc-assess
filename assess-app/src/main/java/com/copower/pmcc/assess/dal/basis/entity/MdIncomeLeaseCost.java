package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeLeaseCost {
    private Integer id;

    private Integer incomeId;

    private Integer sectionId;

    private String managementCost;

    private BigDecimal managementCostRatio;

    private String maintenance;

    private BigDecimal maintenanceCostRatio;

    private BigDecimal replacementValue;

    private String additional;

    private BigDecimal taxRate;

    private String insurancePremium;

    private BigDecimal insuranceRate;

    private String landUseTax;

    private String usageTaxParameter;

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

    public BigDecimal getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional == null ? null : additional.trim();
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(String insurancePremium) {
        this.insurancePremium = insurancePremium == null ? null : insurancePremium.trim();
    }

    public BigDecimal getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(BigDecimal insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    public String getLandUseTax() {
        return landUseTax;
    }

    public void setLandUseTax(String landUseTax) {
        this.landUseTax = landUseTax == null ? null : landUseTax.trim();
    }

    public String getUsageTaxParameter() {
        return usageTaxParameter;
    }

    public void setUsageTaxParameter(String usageTaxParameter) {
        this.usageTaxParameter = usageTaxParameter == null ? null : usageTaxParameter.trim();
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