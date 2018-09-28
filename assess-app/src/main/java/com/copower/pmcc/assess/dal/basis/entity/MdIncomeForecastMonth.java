package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecastMonth {
    private Integer id;

    private Integer yearId;

    private Integer type;

    private Integer accountingSubject;

    private String firstLevelNumber;

    private String secondLevelNumber;

    private String month;

    private BigDecimal unitPrice;

    private Integer number;

    private BigDecimal amountMoney;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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