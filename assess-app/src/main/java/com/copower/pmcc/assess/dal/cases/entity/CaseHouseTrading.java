package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseHouseTrading {
    private Integer id;

    private Integer houseId;

    private Date tradingTime;

    private Integer tradingType;

    private BigDecimal tradingUnitPrice;

    private BigDecimal tradingTotalPrice;

    private String buyerExtraTax;

    private String buyerExtraFee;

    private String rentingExtraTax;

    private String rentingExtraFee;

    private Integer descriptionType;

    private String descriptionContent;

    private String installmentInterestRate;

    private Integer paymentMethod;

    private Integer transactionSituation;

    private Integer taxBurden;

    private Integer scopeProperty;

    private String scopePropertyExplain;

    private String downPaymentRatio;

    private String lendingRate;

    private Integer loanPeriod;

    private String deposit;

    private Integer informationType;

    private Integer informationCategory;

    private String name;

    private String phone;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer priceConnotation;

    private String scopeNotInclude;

    private String scopeInclude;

    private String landBuyerSeller;

    private Integer priceType;

    private String priceConnotationUnit;

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

    public String getBuyerExtraTax() {
        return buyerExtraTax;
    }

    public void setBuyerExtraTax(String buyerExtraTax) {
        this.buyerExtraTax = buyerExtraTax == null ? null : buyerExtraTax.trim();
    }

    public String getBuyerExtraFee() {
        return buyerExtraFee;
    }

    public void setBuyerExtraFee(String buyerExtraFee) {
        this.buyerExtraFee = buyerExtraFee == null ? null : buyerExtraFee.trim();
    }

    public String getRentingExtraTax() {
        return rentingExtraTax;
    }

    public void setRentingExtraTax(String rentingExtraTax) {
        this.rentingExtraTax = rentingExtraTax == null ? null : rentingExtraTax.trim();
    }

    public String getRentingExtraFee() {
        return rentingExtraFee;
    }

    public void setRentingExtraFee(String rentingExtraFee) {
        this.rentingExtraFee = rentingExtraFee == null ? null : rentingExtraFee.trim();
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

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getTransactionSituation() {
        return transactionSituation;
    }

    public void setTransactionSituation(Integer transactionSituation) {
        this.transactionSituation = transactionSituation;
    }

    public Integer getTaxBurden() {
        return taxBurden;
    }

    public void setTaxBurden(Integer taxBurden) {
        this.taxBurden = taxBurden;
    }

    public Integer getScopeProperty() {
        return scopeProperty;
    }

    public void setScopeProperty(Integer scopeProperty) {
        this.scopeProperty = scopeProperty;
    }

    public String getScopePropertyExplain() {
        return scopePropertyExplain;
    }

    public void setScopePropertyExplain(String scopePropertyExplain) {
        this.scopePropertyExplain = scopePropertyExplain == null ? null : scopePropertyExplain.trim();
    }

    public String getDownPaymentRatio() {
        return downPaymentRatio;
    }

    public void setDownPaymentRatio(String downPaymentRatio) {
        this.downPaymentRatio = downPaymentRatio == null ? null : downPaymentRatio.trim();
    }

    public String getLendingRate() {
        return lendingRate;
    }

    public void setLendingRate(String lendingRate) {
        this.lendingRate = lendingRate == null ? null : lendingRate.trim();
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public Integer getInformationType() {
        return informationType;
    }

    public void setInformationType(Integer informationType) {
        this.informationType = informationType;
    }

    public Integer getInformationCategory() {
        return informationCategory;
    }

    public void setInformationCategory(Integer informationCategory) {
        this.informationCategory = informationCategory;
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

    public Integer getPriceConnotation() {
        return priceConnotation;
    }

    public void setPriceConnotation(Integer priceConnotation) {
        this.priceConnotation = priceConnotation;
    }

    public String getScopeNotInclude() {
        return scopeNotInclude;
    }

    public void setScopeNotInclude(String scopeNotInclude) {
        this.scopeNotInclude = scopeNotInclude == null ? null : scopeNotInclude.trim();
    }

    public String getScopeInclude() {
        return scopeInclude;
    }

    public void setScopeInclude(String scopeInclude) {
        this.scopeInclude = scopeInclude == null ? null : scopeInclude.trim();
    }

    public String getLandBuyerSeller() {
        return landBuyerSeller;
    }

    public void setLandBuyerSeller(String landBuyerSeller) {
        this.landBuyerSeller = landBuyerSeller == null ? null : landBuyerSeller.trim();
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public String getPriceConnotationUnit() {
        return priceConnotationUnit;
    }

    public void setPriceConnotationUnit(String priceConnotationUnit) {
        this.priceConnotationUnit = priceConnotationUnit == null ? null : priceConnotationUnit.trim();
    }
}