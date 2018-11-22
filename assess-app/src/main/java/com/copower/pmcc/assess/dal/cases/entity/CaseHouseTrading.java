package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseHouseTrading {
    private Integer id;

    private Integer houseId;

    private Date tradingTime;

    private Integer tradingType;

    private String informationType;

    private BigDecimal tradingUnitPrice;

    private BigDecimal tradingTotalPrice;

    private BigDecimal tradingPrice;

    private String buyerExtraTaxFee;

    private String rentingExtraTaxFee;

    private Integer descriptionType;

    private String descriptionContent;

    private String installmentInterestRate;

    private String paymentMethod;

    private String normalTransaction;

    private String taxBurden;

    private String scopeProperty;

    private String financingConditions;

    private String deposit;

    private String creator;

    private String totalSale;

    private String name;

    private String phone;

    private String information;

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

    public String getInformationType() {
        return informationType;
    }

    public void setInformationType(String informationType) {
        this.informationType = informationType == null ? null : informationType.trim();
    }

    public BigDecimal getTradingUnitPrice() {
        return tradingUnitPrice;
    }

    public void setTradingUnitPrice(BigDecimal tradingUnitPrice) {
        this.tradingUnitPrice = tradingUnitPrice;
    }

    public BigDecimal getTradingTotalPrice() {
        return tradingTotalPrice;
    }

    public void setTradingTotalPrice(BigDecimal tradingTotalPrice) {
        this.tradingTotalPrice = tradingTotalPrice;
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

    public String getInstallmentInterestRate() {
        return installmentInterestRate;
    }

    public void setInstallmentInterestRate(String installmentInterestRate) {
        this.installmentInterestRate = installmentInterestRate == null ? null : installmentInterestRate.trim();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public String getNormalTransaction() {
        return normalTransaction;
    }

    public void setNormalTransaction(String normalTransaction) {
        this.normalTransaction = normalTransaction == null ? null : normalTransaction.trim();
    }

    public String getTaxBurden() {
        return taxBurden;
    }

    public void setTaxBurden(String taxBurden) {
        this.taxBurden = taxBurden == null ? null : taxBurden.trim();
    }

    public String getScopeProperty() {
        return scopeProperty;
    }

    public void setScopeProperty(String scopeProperty) {
        this.scopeProperty = scopeProperty == null ? null : scopeProperty.trim();
    }

    public String getFinancingConditions() {
        return financingConditions;
    }

    public void setFinancingConditions(String financingConditions) {
        this.financingConditions = financingConditions == null ? null : financingConditions.trim();
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(String totalSale) {
        this.totalSale = totalSale == null ? null : totalSale.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information == null ? null : information.trim();
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