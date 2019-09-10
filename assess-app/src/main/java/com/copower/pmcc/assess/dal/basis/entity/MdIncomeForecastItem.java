package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecastItem {
    private Integer id;

    private Integer incomeForecastId;

    private Integer accountingSubject;

    private String firstLevelNumber;

    private String secondLevelNumber;

    private BigDecimal amountMoney;

    private BigDecimal rateIncrease;

    private String rateIncreaseExplain;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncomeForecastId() {
        return incomeForecastId;
    }

    public void setIncomeForecastId(Integer incomeForecastId) {
        this.incomeForecastId = incomeForecastId;
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

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public BigDecimal getRateIncrease() {
        return rateIncrease;
    }

    public void setRateIncrease(BigDecimal rateIncrease) {
        this.rateIncrease = rateIncrease;
    }

    public String getRateIncreaseExplain() {
        return rateIncreaseExplain;
    }

    public void setRateIncreaseExplain(String rateIncreaseExplain) {
        this.rateIncreaseExplain = rateIncreaseExplain == null ? null : rateIncreaseExplain.trim();
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