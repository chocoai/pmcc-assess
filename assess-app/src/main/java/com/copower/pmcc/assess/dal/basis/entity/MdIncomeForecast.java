package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecast {
    private Integer id;

    private Integer incomeId;

    private Integer sectionId;

    private Integer type;

    private BigDecimal initialAmount;

    private String initialAmountRemark;

    private BigDecimal operatingCost;

    private String operatingCostRemark;

    private BigDecimal operatingExpenses;

    private String operatingExpensesRemark;

    private BigDecimal operatingTax;

    private String operatingTaxRemark;

    private BigDecimal managementCost;

    private String managementCostRemark;

    private BigDecimal financialCost;

    private String financialCostRemark;

    private BigDecimal operatingProfit;

    private String operatingProfitRemark;

    private BigDecimal excessProfit;

    private String excessProfitRemark;

    private BigDecimal growthRate;

    private String growthRateRemark;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getInitialAmountRemark() {
        return initialAmountRemark;
    }

    public void setInitialAmountRemark(String initialAmountRemark) {
        this.initialAmountRemark = initialAmountRemark == null ? null : initialAmountRemark.trim();
    }

    public BigDecimal getOperatingCost() {
        return operatingCost;
    }

    public void setOperatingCost(BigDecimal operatingCost) {
        this.operatingCost = operatingCost;
    }

    public String getOperatingCostRemark() {
        return operatingCostRemark;
    }

    public void setOperatingCostRemark(String operatingCostRemark) {
        this.operatingCostRemark = operatingCostRemark == null ? null : operatingCostRemark.trim();
    }

    public BigDecimal getOperatingExpenses() {
        return operatingExpenses;
    }

    public void setOperatingExpenses(BigDecimal operatingExpenses) {
        this.operatingExpenses = operatingExpenses;
    }

    public String getOperatingExpensesRemark() {
        return operatingExpensesRemark;
    }

    public void setOperatingExpensesRemark(String operatingExpensesRemark) {
        this.operatingExpensesRemark = operatingExpensesRemark == null ? null : operatingExpensesRemark.trim();
    }

    public BigDecimal getOperatingTax() {
        return operatingTax;
    }

    public void setOperatingTax(BigDecimal operatingTax) {
        this.operatingTax = operatingTax;
    }

    public String getOperatingTaxRemark() {
        return operatingTaxRemark;
    }

    public void setOperatingTaxRemark(String operatingTaxRemark) {
        this.operatingTaxRemark = operatingTaxRemark == null ? null : operatingTaxRemark.trim();
    }

    public BigDecimal getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(BigDecimal managementCost) {
        this.managementCost = managementCost;
    }

    public String getManagementCostRemark() {
        return managementCostRemark;
    }

    public void setManagementCostRemark(String managementCostRemark) {
        this.managementCostRemark = managementCostRemark == null ? null : managementCostRemark.trim();
    }

    public BigDecimal getFinancialCost() {
        return financialCost;
    }

    public void setFinancialCost(BigDecimal financialCost) {
        this.financialCost = financialCost;
    }

    public String getFinancialCostRemark() {
        return financialCostRemark;
    }

    public void setFinancialCostRemark(String financialCostRemark) {
        this.financialCostRemark = financialCostRemark == null ? null : financialCostRemark.trim();
    }

    public BigDecimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(BigDecimal operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public String getOperatingProfitRemark() {
        return operatingProfitRemark;
    }

    public void setOperatingProfitRemark(String operatingProfitRemark) {
        this.operatingProfitRemark = operatingProfitRemark == null ? null : operatingProfitRemark.trim();
    }

    public BigDecimal getExcessProfit() {
        return excessProfit;
    }

    public void setExcessProfit(BigDecimal excessProfit) {
        this.excessProfit = excessProfit;
    }

    public String getExcessProfitRemark() {
        return excessProfitRemark;
    }

    public void setExcessProfitRemark(String excessProfitRemark) {
        this.excessProfitRemark = excessProfitRemark == null ? null : excessProfitRemark.trim();
    }

    public BigDecimal getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(BigDecimal growthRate) {
        this.growthRate = growthRate;
    }

    public String getGrowthRateRemark() {
        return growthRateRemark;
    }

    public void setGrowthRateRemark(String growthRateRemark) {
        this.growthRateRemark = growthRateRemark == null ? null : growthRateRemark.trim();
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