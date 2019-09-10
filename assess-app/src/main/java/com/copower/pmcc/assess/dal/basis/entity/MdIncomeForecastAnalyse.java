package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecastAnalyse {
    private Integer id;

    private Integer incomeId;

    private Integer type;

    private Integer formType;

    private Integer year;

    private BigDecimal amountMoney;

    private BigDecimal quantitativeTrend;

    private BigDecimal univalentTrend;

    private Boolean bisParticipateIn;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String sourceType;

    private BigDecimal costRatio;

    private BigDecimal earnedProfitRatio;

    private BigDecimal earnedProfit;

    private BigDecimal operatingExpensesRatio;

    private BigDecimal operatingTaxRatio;

    private BigDecimal managementCostRatio;

    private BigDecimal financialCostRatio;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public BigDecimal getQuantitativeTrend() {
        return quantitativeTrend;
    }

    public void setQuantitativeTrend(BigDecimal quantitativeTrend) {
        this.quantitativeTrend = quantitativeTrend;
    }

    public BigDecimal getUnivalentTrend() {
        return univalentTrend;
    }

    public void setUnivalentTrend(BigDecimal univalentTrend) {
        this.univalentTrend = univalentTrend;
    }

    public Boolean getBisParticipateIn() {
        return bisParticipateIn;
    }

    public void setBisParticipateIn(Boolean bisParticipateIn) {
        this.bisParticipateIn = bisParticipateIn;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public BigDecimal getCostRatio() {
        return costRatio;
    }

    public void setCostRatio(BigDecimal costRatio) {
        this.costRatio = costRatio;
    }

    public BigDecimal getEarnedProfitRatio() {
        return earnedProfitRatio;
    }

    public void setEarnedProfitRatio(BigDecimal earnedProfitRatio) {
        this.earnedProfitRatio = earnedProfitRatio;
    }

    public BigDecimal getEarnedProfit() {
        return earnedProfit;
    }

    public void setEarnedProfit(BigDecimal earnedProfit) {
        this.earnedProfit = earnedProfit;
    }

    public BigDecimal getOperatingExpensesRatio() {
        return operatingExpensesRatio;
    }

    public void setOperatingExpensesRatio(BigDecimal operatingExpensesRatio) {
        this.operatingExpensesRatio = operatingExpensesRatio;
    }

    public BigDecimal getOperatingTaxRatio() {
        return operatingTaxRatio;
    }

    public void setOperatingTaxRatio(BigDecimal operatingTaxRatio) {
        this.operatingTaxRatio = operatingTaxRatio;
    }

    public BigDecimal getManagementCostRatio() {
        return managementCostRatio;
    }

    public void setManagementCostRatio(BigDecimal managementCostRatio) {
        this.managementCostRatio = managementCostRatio;
    }

    public BigDecimal getFinancialCostRatio() {
        return financialCostRatio;
    }

    public void setFinancialCostRatio(BigDecimal financialCostRatio) {
        this.financialCostRatio = financialCostRatio;
    }
}