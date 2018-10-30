package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecast {
    private Integer id;

    private Integer incomeId;

    private Integer sectionId;

    private Integer type;

    private BigDecimal initialAmount;

    private BigDecimal operatingCost;

    private BigDecimal operatingExpenses;

    private BigDecimal operatingTax;

    private BigDecimal managementCost;

    private BigDecimal financialCost;

    private BigDecimal operatingProfit;

    private BigDecimal excessProfit;

    private BigDecimal growthRate;

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

    public BigDecimal getOperatingCost() {
        return operatingCost;
    }

    public void setOperatingCost(BigDecimal operatingCost) {
        this.operatingCost = operatingCost;
    }

    public BigDecimal getOperatingExpenses() {
        return operatingExpenses;
    }

    public void setOperatingExpenses(BigDecimal operatingExpenses) {
        this.operatingExpenses = operatingExpenses;
    }

    public BigDecimal getOperatingTax() {
        return operatingTax;
    }

    public void setOperatingTax(BigDecimal operatingTax) {
        this.operatingTax = operatingTax;
    }

    public BigDecimal getManagementCost() {
        return managementCost;
    }

    public void setManagementCost(BigDecimal managementCost) {
        this.managementCost = managementCost;
    }

    public BigDecimal getFinancialCost() {
        return financialCost;
    }

    public void setFinancialCost(BigDecimal financialCost) {
        this.financialCost = financialCost;
    }

    public BigDecimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(BigDecimal operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public BigDecimal getExcessProfit() {
        return excessProfit;
    }

    public void setExcessProfit(BigDecimal excessProfit) {
        this.excessProfit = excessProfit;
    }

    public BigDecimal getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(BigDecimal growthRate) {
        this.growthRate = growthRate;
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