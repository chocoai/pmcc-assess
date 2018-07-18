package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExamineHouseTrading {
    private Integer id;

    private Integer declareId;

    private Integer examineType;

    private Date tradingTime;

    private Integer tradingType;

    private BigDecimal tradingPrice;

    private String buyerExtraTaxFee;

    private String rentingExtraTaxFee;

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

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
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