package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeHistory {
    private Integer id;

    private Integer incomeId;

    private Integer type;

    private String year;

    private Integer accountingSubject;

    private String firstLevelNumber;

    private String secondLevelNumber;

    private String month;

    private String unit;

    private BigDecimal unitPrice;

    private Integer number;

    private BigDecimal amountMoney;

    private Date beginDate;

    private Date endDate;

    private BigDecimal utilizationRatio;

    private String utilizationRatioExplain;

    private BigDecimal makePrice;

    private String makePriceExplain;

    private BigDecimal executivePrice;

    private BigDecimal discountRate;

    private String discountRateExplain;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getAccountingSubject() {
        return accountingSubject;
    }

    public void setAccountingSubject(Integer accountingSubject) {
        this.accountingSubject = accountingSubject;
    }

    public String getFirstLevelNumber() {
        return firstLevelNumber;
    }

    public void setFirstLevelNumber(String firstLevelNumber) {
        this.firstLevelNumber = firstLevelNumber == null ? null : firstLevelNumber.trim();
    }

    public String getSecondLevelNumber() {
        return secondLevelNumber;
    }

    public void setSecondLevelNumber(String secondLevelNumber) {
        this.secondLevelNumber = secondLevelNumber == null ? null : secondLevelNumber.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
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

    public BigDecimal getUtilizationRatio() {
        return utilizationRatio;
    }

    public void setUtilizationRatio(BigDecimal utilizationRatio) {
        this.utilizationRatio = utilizationRatio;
    }

    public String getUtilizationRatioExplain() {
        return utilizationRatioExplain;
    }

    public void setUtilizationRatioExplain(String utilizationRatioExplain) {
        this.utilizationRatioExplain = utilizationRatioExplain == null ? null : utilizationRatioExplain.trim();
    }

    public BigDecimal getMakePrice() {
        return makePrice;
    }

    public void setMakePrice(BigDecimal makePrice) {
        this.makePrice = makePrice;
    }

    public String getMakePriceExplain() {
        return makePriceExplain;
    }

    public void setMakePriceExplain(String makePriceExplain) {
        this.makePriceExplain = makePriceExplain == null ? null : makePriceExplain.trim();
    }

    public BigDecimal getExecutivePrice() {
        return executivePrice;
    }

    public void setExecutivePrice(BigDecimal executivePrice) {
        this.executivePrice = executivePrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountRateExplain() {
        return discountRateExplain;
    }

    public void setDiscountRateExplain(String discountRateExplain) {
        this.discountRateExplain = discountRateExplain == null ? null : discountRateExplain.trim();
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