package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseHouseTradingInfo {
    private Integer id;

    private Integer houseId;

    private Date tradingTime;

    private Integer tradingType;

    private BigDecimal tradingPrice;

    private String instalmentPeriod;

    private String instalmentInterest;

    private String buyerExtraTaxFee;

    private String rentingExtraTaxFee;

    private String rentPaymentTime;

    private String rentGrowthRate;

    private Integer descriptionType;

    private String descriptionContent;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public Integer getTradingType() {
        return tradingType;
    }

    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    public BigDecimal getTradingPrice() {
        return tradingPrice;
    }

    public void setTradingPrice(BigDecimal tradingPrice) {
        this.tradingPrice = tradingPrice;
    }

    public String getInstalmentPeriod() {
        return instalmentPeriod;
    }

    public void setInstalmentPeriod(String instalmentPeriod) {
        this.instalmentPeriod = instalmentPeriod == null ? null : instalmentPeriod.trim();
    }

    public String getInstalmentInterest() {
        return instalmentInterest;
    }

    public void setInstalmentInterest(String instalmentInterest) {
        this.instalmentInterest = instalmentInterest == null ? null : instalmentInterest.trim();
    }

    public String getBuyerExtraTaxFee() {
        return buyerExtraTaxFee;
    }

    public void setBuyerExtraTaxFee(String buyerExtraTaxFee) {
        this.buyerExtraTaxFee = buyerExtraTaxFee == null ? null : buyerExtraTaxFee.trim();
    }

    public String getRentingExtraTaxFee() {
        return rentingExtraTaxFee;
    }

    public void setRentingExtraTaxFee(String rentingExtraTaxFee) {
        this.rentingExtraTaxFee = rentingExtraTaxFee == null ? null : rentingExtraTaxFee.trim();
    }

    public String getRentPaymentTime() {
        return rentPaymentTime;
    }

    public void setRentPaymentTime(String rentPaymentTime) {
        this.rentPaymentTime = rentPaymentTime == null ? null : rentPaymentTime.trim();
    }

    public String getRentGrowthRate() {
        return rentGrowthRate;
    }

    public void setRentGrowthRate(String rentGrowthRate) {
        this.rentGrowthRate = rentGrowthRate == null ? null : rentGrowthRate.trim();
    }

    public Integer getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(Integer descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getDescriptionContent() {
        return descriptionContent;
    }

    public void setDescriptionContent(String descriptionContent) {
        this.descriptionContent = descriptionContent == null ? null : descriptionContent.trim();
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