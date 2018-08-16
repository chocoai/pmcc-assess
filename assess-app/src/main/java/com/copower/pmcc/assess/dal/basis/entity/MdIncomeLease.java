package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeLease {
    private Integer id;

    private Integer incomeId;

    private BigDecimal rentalIncome;

    private String rentals;

    private Integer monthNumber;

    private String deposit;

    private String interestRate;

    private String interestIncome;

    private String otherIncome;

    private String rentalsRemark;

    private String interestRateRemark;

    private String otherIncomeRemark;

    private String managementCost;

    private String managementCostRatio;

    private String paymentRemark;

    private String maintenance;

    private String maintenanceCostRatio;

    private String replacementValue;

    private String additional;

    private String taxRate;

    private String insurancePremium;

    private String insuranceRate;

    private String landUseTax;

    private String usageTaxParameter;

    private String netProfit;

    private String incomePrice;

    private String capitalizationRate;

    private String returnPeriod;

    private Date leaseBeginDate;

    private Date leaseEndDate;

    private String rentalGrowthRate;

    private String correctionFactor;

    private String presentValueFactor;

    private BigDecimal floorArea;

    private BigDecimal landArea;

    private BigDecimal totalPrice;

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

    public BigDecimal getRentalIncome() {
        return rentalIncome;
    }

    public void setRentalIncome(BigDecimal rentalIncome) {
        this.rentalIncome = rentalIncome;
    }

    public String getRentals() {
        return rentals;
    }

    public void setRentals(String rentals) {
        this.rentals = rentals == null ? null : rentals.trim();
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate == null ? null : interestRate.trim();
    }

    public String getInterestIncome() {
        return interestIncome;
    }

    public void setInterestIncome(String interestIncome) {
        this.interestIncome = interestIncome == null ? null : interestIncome.trim();
    }

    public String getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        this.otherIncome = otherIncome == null ? null : otherIncome.trim();
    }

    public String getRentalsRemark() {
        return rentalsRemark;
    }

    public void setRentalsRemark(String rentalsRemark) {
        this.rentalsRemark = rentalsRemark == null ? null : rentalsRemark.trim();
    }

    public String getInterestRateRemark() {
        return interestRateRemark;
    }

    public void setInterestRateRemark(String interestRateRemark) {
        this.interestRateRemark = interestRateRemark == null ? null : interestRateRemark.trim();
    }

    public String getOtherIncomeRemark() {
        return otherIncomeRemark;
    }

    public void setOtherIncomeRemark(String otherIncomeRemark) {
        this.otherIncomeRemark = otherIncomeRemark == null ? null : otherIncomeRemark.trim();
    }

    public String getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(String managementCost) {
        this.managementCost = managementCost == null ? null : managementCost.trim();
    }

    public String getManagementCostRatio() {
        return managementCostRatio;
    }

    public void setManagementCostRatio(String managementCostRatio) {
        this.managementCostRatio = managementCostRatio == null ? null : managementCostRatio.trim();
    }

    public String getPaymentRemark() {
        return paymentRemark;
    }

    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark == null ? null : paymentRemark.trim();
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance == null ? null : maintenance.trim();
    }

    public String getMaintenanceCostRatio() {
        return maintenanceCostRatio;
    }

    public void setMaintenanceCostRatio(String maintenanceCostRatio) {
        this.maintenanceCostRatio = maintenanceCostRatio == null ? null : maintenanceCostRatio.trim();
    }

    public String getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(String replacementValue) {
        this.replacementValue = replacementValue == null ? null : replacementValue.trim();
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional == null ? null : additional.trim();
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate == null ? null : taxRate.trim();
    }

    public String getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(String insurancePremium) {
        this.insurancePremium = insurancePremium == null ? null : insurancePremium.trim();
    }

    public String getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(String insuranceRate) {
        this.insuranceRate = insuranceRate == null ? null : insuranceRate.trim();
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

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit == null ? null : netProfit.trim();
    }

    public String getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(String incomePrice) {
        this.incomePrice = incomePrice == null ? null : incomePrice.trim();
    }

    public String getCapitalizationRate() {
        return capitalizationRate;
    }

    public void setCapitalizationRate(String capitalizationRate) {
        this.capitalizationRate = capitalizationRate == null ? null : capitalizationRate.trim();
    }

    public String getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(String returnPeriod) {
        this.returnPeriod = returnPeriod == null ? null : returnPeriod.trim();
    }

    public Date getLeaseBeginDate() {
        return leaseBeginDate;
    }

    public void setLeaseBeginDate(Date leaseBeginDate) {
        this.leaseBeginDate = leaseBeginDate;
    }

    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public String getRentalGrowthRate() {
        return rentalGrowthRate;
    }

    public void setRentalGrowthRate(String rentalGrowthRate) {
        this.rentalGrowthRate = rentalGrowthRate == null ? null : rentalGrowthRate.trim();
    }

    public String getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(String correctionFactor) {
        this.correctionFactor = correctionFactor == null ? null : correctionFactor.trim();
    }

    public String getPresentValueFactor() {
        return presentValueFactor;
    }

    public void setPresentValueFactor(String presentValueFactor) {
        this.presentValueFactor = presentValueFactor == null ? null : presentValueFactor.trim();
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getLandArea() {
        return landArea;
    }

    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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