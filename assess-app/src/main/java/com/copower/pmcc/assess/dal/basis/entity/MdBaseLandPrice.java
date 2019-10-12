package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdBaseLandPrice {
    private Integer id;

    private Integer planDetailsId;

    private String processInsId;

    private Integer rewardRateId;

    private String rewardRate;

    private BigDecimal standardPremium;

    private String standardPremiumRemark;

    private BigDecimal legalAge;

    private String legalAgeRemark;

    private BigDecimal landSurplusYear;

    private String landSurplusYearRemark;

    private BigDecimal periodAmend;

    private BigDecimal developCorrect;

    private String developCorrectRemark;

    private BigDecimal volumetricRate;

    private String volumetricRateRemark;

    private BigDecimal evaluationArea;

    private String evaluationAreaRemark;

    private BigDecimal dateAmend;

    private BigDecimal volumeFractionAmend;

    private BigDecimal areaAndSeveralAmend;

    private BigDecimal parcelPrice;

    private BigDecimal parcelBhouPrice;

    private BigDecimal parcelTotalPrice;

    private BigDecimal floorPremium;

    private String correctionDifference;

    private String landLevelContent;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public BigDecimal getStandardPremium() {
        return standardPremium;
    }

    public void setStandardPremium(BigDecimal standardPremium) {
        this.standardPremium = standardPremium;
    }

    public String getStandardPremiumRemark() {
        return standardPremiumRemark;
    }

    public void setStandardPremiumRemark(String standardPremiumRemark) {
        this.standardPremiumRemark = standardPremiumRemark == null ? null : standardPremiumRemark.trim();
    }

    public BigDecimal getLegalAge() {
        return legalAge;
    }

    public void setLegalAge(BigDecimal legalAge) {
        this.legalAge = legalAge;
    }

    public String getLegalAgeRemark() {
        return legalAgeRemark;
    }

    public void setLegalAgeRemark(String legalAgeRemark) {
        this.legalAgeRemark = legalAgeRemark == null ? null : legalAgeRemark.trim();
    }

    public BigDecimal getLandSurplusYear() {
        return landSurplusYear;
    }

    public void setLandSurplusYear(BigDecimal landSurplusYear) {
        this.landSurplusYear = landSurplusYear;
    }

    public String getLandSurplusYearRemark() {
        return landSurplusYearRemark;
    }

    public void setLandSurplusYearRemark(String landSurplusYearRemark) {
        this.landSurplusYearRemark = landSurplusYearRemark == null ? null : landSurplusYearRemark.trim();
    }

    public BigDecimal getPeriodAmend() {
        return periodAmend;
    }

    public void setPeriodAmend(BigDecimal periodAmend) {
        this.periodAmend = periodAmend;
    }

    public BigDecimal getDevelopCorrect() {
        return developCorrect;
    }

    public void setDevelopCorrect(BigDecimal developCorrect) {
        this.developCorrect = developCorrect;
    }

    public String getDevelopCorrectRemark() {
        return developCorrectRemark;
    }

    public void setDevelopCorrectRemark(String developCorrectRemark) {
        this.developCorrectRemark = developCorrectRemark == null ? null : developCorrectRemark.trim();
    }

    public BigDecimal getVolumetricRate() {
        return volumetricRate;
    }

    public void setVolumetricRate(BigDecimal volumetricRate) {
        this.volumetricRate = volumetricRate;
    }

    public String getVolumetricRateRemark() {
        return volumetricRateRemark;
    }

    public void setVolumetricRateRemark(String volumetricRateRemark) {
        this.volumetricRateRemark = volumetricRateRemark == null ? null : volumetricRateRemark.trim();
    }

    public BigDecimal getEvaluationArea() {
        return evaluationArea;
    }

    public void setEvaluationArea(BigDecimal evaluationArea) {
        this.evaluationArea = evaluationArea;
    }

    public String getEvaluationAreaRemark() {
        return evaluationAreaRemark;
    }

    public void setEvaluationAreaRemark(String evaluationAreaRemark) {
        this.evaluationAreaRemark = evaluationAreaRemark == null ? null : evaluationAreaRemark.trim();
    }

    public BigDecimal getDateAmend() {
        return dateAmend;
    }

    public void setDateAmend(BigDecimal dateAmend) {
        this.dateAmend = dateAmend;
    }

    public BigDecimal getVolumeFractionAmend() {
        return volumeFractionAmend;
    }

    public void setVolumeFractionAmend(BigDecimal volumeFractionAmend) {
        this.volumeFractionAmend = volumeFractionAmend;
    }

    public BigDecimal getAreaAndSeveralAmend() {
        return areaAndSeveralAmend;
    }

    public void setAreaAndSeveralAmend(BigDecimal areaAndSeveralAmend) {
        this.areaAndSeveralAmend = areaAndSeveralAmend;
    }

    public BigDecimal getParcelPrice() {
        return parcelPrice;
    }

    public void setParcelPrice(BigDecimal parcelPrice) {
        this.parcelPrice = parcelPrice;
    }

    public BigDecimal getParcelBhouPrice() {
        return parcelBhouPrice;
    }

    public void setParcelBhouPrice(BigDecimal parcelBhouPrice) {
        this.parcelBhouPrice = parcelBhouPrice;
    }

    public BigDecimal getParcelTotalPrice() {
        return parcelTotalPrice;
    }

    public void setParcelTotalPrice(BigDecimal parcelTotalPrice) {
        this.parcelTotalPrice = parcelTotalPrice;
    }

    public BigDecimal getFloorPremium() {
        return floorPremium;
    }

    public void setFloorPremium(BigDecimal floorPremium) {
        this.floorPremium = floorPremium;
    }

    public String getCorrectionDifference() {
        return correctionDifference;
    }

    public void setCorrectionDifference(String correctionDifference) {
        this.correctionDifference = correctionDifference == null ? null : correctionDifference.trim();
    }

    public String getLandLevelContent() {
        return landLevelContent;
    }

    public void setLandLevelContent(String landLevelContent) {
        this.landLevelContent = landLevelContent == null ? null : landLevelContent.trim();
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