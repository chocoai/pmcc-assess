package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncomeForecastAnalyse {
    private Integer id;

    private Integer incomeId;

    private Integer type;

    private Integer formType;

    private Integer year;

    private BigDecimal amountMoney;

    private BigDecimal quantitativeTrend;

    private BigDecimal univalentTrend;

    private Boolean bisParticipateIn;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String sourceType;

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

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public BigDecimal getQuantitativeTrend() {
        return quantitativeTrend;
    }

    public void setQuantitativeTrend(BigDecimal quantitativeTrend) {
        this.quantitativeTrend = quantitativeTrend;
    }

    public BigDecimal getUnivalentTrend() {
        return univalentTrend;
    }

    public void setUnivalentTrend(BigDecimal univalentTrend) {
        this.univalentTrend = univalentTrend;
    }

    public Boolean getBisParticipateIn() {
        return bisParticipateIn;
    }

    public void setBisParticipateIn(Boolean bisParticipateIn) {
        this.bisParticipateIn = bisParticipateIn;
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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }
}