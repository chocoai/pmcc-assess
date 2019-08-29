package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeDateSection {
    private Integer id;

    private Integer incomeId;

    private Integer operationMode;

    private Date beginDate;

    private Date endDate;

    private BigDecimal yearCount;

    private BigDecimal incomeTotal;

    private BigDecimal costTotal;

    private BigDecimal operatingProfit;

    private String netProfit;

    private BigDecimal rentalGrowthRate;

    private String rentalGrowthRateExplain;

    private String rentalGrowthRateExplainSupplement;

    private BigDecimal correctionFactor;

    private BigDecimal presentValueFactor;

    private BigDecimal incomePrice;

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

    public Integer getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(Integer operationMode) {
        this.operationMode = operationMode;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getYearCount() {
        return yearCount;
    }

    public void setYearCount(BigDecimal yearCount) {
        this.yearCount = yearCount;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public BigDecimal getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
    }

    public BigDecimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(BigDecimal operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit == null ? null : netProfit.trim();
    }

    public BigDecimal getRentalGrowthRate() {
        return rentalGrowthRate;
    }

    public void setRentalGrowthRate(BigDecimal rentalGrowthRate) {
        this.rentalGrowthRate = rentalGrowthRate;
    }

    public String getRentalGrowthRateExplain() {
        return rentalGrowthRateExplain;
    }

    public void setRentalGrowthRateExplain(String rentalGrowthRateExplain) {
        this.rentalGrowthRateExplain = rentalGrowthRateExplain == null ? null : rentalGrowthRateExplain.trim();
    }

    public String getRentalGrowthRateExplainSupplement() {
        return rentalGrowthRateExplainSupplement;
    }

    public void setRentalGrowthRateExplainSupplement(String rentalGrowthRateExplainSupplement) {
        this.rentalGrowthRateExplainSupplement = rentalGrowthRateExplainSupplement == null ? null : rentalGrowthRateExplainSupplement.trim();
    }

    public BigDecimal getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(BigDecimal correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public BigDecimal getPresentValueFactor() {
        return presentValueFactor;
    }

    public void setPresentValueFactor(BigDecimal presentValueFactor) {
        this.presentValueFactor = presentValueFactor;
    }

    public BigDecimal getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(BigDecimal incomePrice) {
        this.incomePrice = incomePrice;
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