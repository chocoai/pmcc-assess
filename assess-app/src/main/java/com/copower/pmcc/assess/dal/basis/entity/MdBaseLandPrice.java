package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdBaseLandPrice {
    private Integer id;

    private Integer rewardRateId;

    private String rewardRate;

    private BigDecimal developCorrect;

    private BigDecimal periodAmend;

    private BigDecimal parcelPrice;

    private BigDecimal parcelBhouPrice;

    private BigDecimal parcelTotalPrice;

    private BigDecimal floorPremium;

    private String correctionDifference;

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

    public BigDecimal getDevelopCorrect() {
        return developCorrect;
    }

    public void setDevelopCorrect(BigDecimal developCorrect) {
        this.developCorrect = developCorrect;
    }

    public BigDecimal getPeriodAmend() {
        return periodAmend;
    }

    public void setPeriodAmend(BigDecimal periodAmend) {
        this.periodAmend = periodAmend;
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