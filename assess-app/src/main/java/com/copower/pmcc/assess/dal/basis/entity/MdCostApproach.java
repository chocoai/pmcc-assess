package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostApproach {
    private Integer id;

    private Integer rewardRateId;

    private String rewardRate;

    private String imprevisionCost;

    private BigDecimal circulationExpense;

    private BigDecimal flatExpense;

    private BigDecimal machineCycle;

    private String calculatedInterest;

    private String profitMargin;

    private String incrementalBenefit;

    private String processInsId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRewardRateId() {
        return rewardRateId;
    }

    public void setRewardRateId(Integer rewardRateId) {
        this.rewardRateId = rewardRateId;
    }

    public String getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(String rewardRate) {
        this.rewardRate = rewardRate == null ? null : rewardRate.trim();
    }

    public String getImprevisionCost() {
        return imprevisionCost;
    }

    public void setImprevisionCost(String imprevisionCost) {
        this.imprevisionCost = imprevisionCost == null ? null : imprevisionCost.trim();
    }

    public BigDecimal getCirculationExpense() {
        return circulationExpense;
    }

    public void setCirculationExpense(BigDecimal circulationExpense) {
        this.circulationExpense = circulationExpense;
    }

    public BigDecimal getFlatExpense() {
        return flatExpense;
    }

    public void setFlatExpense(BigDecimal flatExpense) {
        this.flatExpense = flatExpense;
    }

    public BigDecimal getMachineCycle() {
        return machineCycle;
    }

    public void setMachineCycle(BigDecimal machineCycle) {
        this.machineCycle = machineCycle;
    }

    public String getCalculatedInterest() {
        return calculatedInterest;
    }

    public void setCalculatedInterest(String calculatedInterest) {
        this.calculatedInterest = calculatedInterest == null ? null : calculatedInterest.trim();
    }

    public String getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin == null ? null : profitMargin.trim();
    }

    public String getIncrementalBenefit() {
        return incrementalBenefit;
    }

    public void setIncrementalBenefit(String incrementalBenefit) {
        this.incrementalBenefit = incrementalBenefit == null ? null : incrementalBenefit.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
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