package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecastAnalyseItem {
    private Integer id;

    private Integer incomeId;

    private Integer forecastAnalyseId;

    private Integer formType;

    private Integer type;

    private Integer year;

    private String sourceType;

    private Integer accountingSubject;

    private String firstLevelNumber;

    private String secondLevelNumber;

    private BigDecimal amountMoney;

    private Integer number;

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

    public Integer getForecastAnalyseId() {
        return forecastAnalyseId;
    }

    public void setForecastAnalyseId(Integer forecastAnalyseId) {
        this.forecastAnalyseId = forecastAnalyseId;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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