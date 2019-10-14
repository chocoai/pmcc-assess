package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostApproach {
    private Integer id;

    private Integer planDetailsId;

    private String processInsId;

    private BigDecimal farmlandArea;

    private BigDecimal ploughArea;

    private Integer populationNumber;

    private Integer rewardRateId;

    private String rewardRate;

    private BigDecimal circulationExpense;

    private String circulationExpenseRemark;

    private BigDecimal flatExpense;

    private String flatExpenseRemark;

    private BigDecimal machineCycle;

    private String machineCycleRemark;

    private String calculatedInterest;

    private String calculatedInterestRemark;

    private String profitMargin;

    private String profitMarginRemark;

    private String incrementalBenefit;

    private String incrementalBenefitRemark;

    private String plotRatioAdjust;

    private String plotRatioAdjustRemark;

    private BigDecimal plotRatioElementAmend;

    private String plotRatioElementAmendRemark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal landRemainingYear;

    private String landRemainingYearRemark;

    private String parcelSettingOuter;

    private String parcelSettingInner;

    private String landLevelContent;

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

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public BigDecimal getFarmlandArea() {
        return farmlandArea;
    }

    public void setFarmlandArea(BigDecimal farmlandArea) {
        this.farmlandArea = farmlandArea;
    }

    public BigDecimal getPloughArea() {
        return ploughArea;
    }

    public void setPloughArea(BigDecimal ploughArea) {
        this.ploughArea = ploughArea;
    }

    public Integer getPopulationNumber() {
        return populationNumber;
    }

    public void setPopulationNumber(Integer populationNumber) {
        this.populationNumber = populationNumber;
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

    public BigDecimal getCirculationExpense() {
        return circulationExpense;
    }

    public void setCirculationExpense(BigDecimal circulationExpense) {
        this.circulationExpense = circulationExpense;
    }

    public String getCirculationExpenseRemark() {
        return circulationExpenseRemark;
    }

    public void setCirculationExpenseRemark(String circulationExpenseRemark) {
        this.circulationExpenseRemark = circulationExpenseRemark == null ? null : circulationExpenseRemark.trim();
    }

    public BigDecimal getFlatExpense() {
        return flatExpense;
    }

    public void setFlatExpense(BigDecimal flatExpense) {
        this.flatExpense = flatExpense;
    }

    public String getFlatExpenseRemark() {
        return flatExpenseRemark;
    }

    public void setFlatExpenseRemark(String flatExpenseRemark) {
        this.flatExpenseRemark = flatExpenseRemark == null ? null : flatExpenseRemark.trim();
    }

    public BigDecimal getMachineCycle() {
        return machineCycle;
    }

    public void setMachineCycle(BigDecimal machineCycle) {
        this.machineCycle = machineCycle;
    }

    public String getMachineCycleRemark() {
        return machineCycleRemark;
    }

    public void setMachineCycleRemark(String machineCycleRemark) {
        this.machineCycleRemark = machineCycleRemark == null ? null : machineCycleRemark.trim();
    }

    public String getCalculatedInterest() {
        return calculatedInterest;
    }

    public void setCalculatedInterest(String calculatedInterest) {
        this.calculatedInterest = calculatedInterest == null ? null : calculatedInterest.trim();
    }

    public String getCalculatedInterestRemark() {
        return calculatedInterestRemark;
    }

    public void setCalculatedInterestRemark(String calculatedInterestRemark) {
        this.calculatedInterestRemark = calculatedInterestRemark == null ? null : calculatedInterestRemark.trim();
    }

    public String getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        this.profitMargin = profitMargin == null ? null : profitMargin.trim();
    }

    public String getProfitMarginRemark() {
        return profitMarginRemark;
    }

    public void setProfitMarginRemark(String profitMarginRemark) {
        this.profitMarginRemark = profitMarginRemark == null ? null : profitMarginRemark.trim();
    }

    public String getIncrementalBenefit() {
        return incrementalBenefit;
    }

    public void setIncrementalBenefit(String incrementalBenefit) {
        this.incrementalBenefit = incrementalBenefit == null ? null : incrementalBenefit.trim();
    }

    public String getIncrementalBenefitRemark() {
        return incrementalBenefitRemark;
    }

    public void setIncrementalBenefitRemark(String incrementalBenefitRemark) {
        this.incrementalBenefitRemark = incrementalBenefitRemark == null ? null : incrementalBenefitRemark.trim();
    }

    public String getPlotRatioAdjust() {
        return plotRatioAdjust;
    }

    public void setPlotRatioAdjust(String plotRatioAdjust) {
        this.plotRatioAdjust = plotRatioAdjust == null ? null : plotRatioAdjust.trim();
    }

    public String getPlotRatioAdjustRemark() {
        return plotRatioAdjustRemark;
    }

    public void setPlotRatioAdjustRemark(String plotRatioAdjustRemark) {
        this.plotRatioAdjustRemark = plotRatioAdjustRemark == null ? null : plotRatioAdjustRemark.trim();
    }

    public BigDecimal getPlotRatioElementAmend() {
        return plotRatioElementAmend;
    }

    public void setPlotRatioElementAmend(BigDecimal plotRatioElementAmend) {
        this.plotRatioElementAmend = plotRatioElementAmend;
    }

    public String getPlotRatioElementAmendRemark() {
        return plotRatioElementAmendRemark;
    }

    public void setPlotRatioElementAmendRemark(String plotRatioElementAmendRemark) {
        this.plotRatioElementAmendRemark = plotRatioElementAmendRemark == null ? null : plotRatioElementAmendRemark.trim();
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

    public BigDecimal getLandRemainingYear() {
        return landRemainingYear;
    }

    public void setLandRemainingYear(BigDecimal landRemainingYear) {
        this.landRemainingYear = landRemainingYear;
    }

    public String getLandRemainingYearRemark() {
        return landRemainingYearRemark;
    }

    public void setLandRemainingYearRemark(String landRemainingYearRemark) {
        this.landRemainingYearRemark = landRemainingYearRemark == null ? null : landRemainingYearRemark.trim();
    }

    public String getParcelSettingOuter() {
        return parcelSettingOuter;
    }

    public void setParcelSettingOuter(String parcelSettingOuter) {
        this.parcelSettingOuter = parcelSettingOuter == null ? null : parcelSettingOuter.trim();
    }

    public String getParcelSettingInner() {
        return parcelSettingInner;
    }

    public void setParcelSettingInner(String parcelSettingInner) {
        this.parcelSettingInner = parcelSettingInner == null ? null : parcelSettingInner.trim();
    }

    public String getLandLevelContent() {
        return landLevelContent;
    }

    public void setLandLevelContent(String landLevelContent) {
        this.landLevelContent = landLevelContent == null ? null : landLevelContent.trim();
    }
}