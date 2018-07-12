package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouseTradingLease {
    private Integer id;

    private Integer tradingId;

    private Date rentPaymentTimeStart;

    private Date rentPaymentTimeEnd;

    private String rentGrowthRate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTradingId() {
        return tradingId;
    }

    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }

    public Date getRentPaymentTimeStart() {
        return rentPaymentTimeStart;
    }

    public void setRentPaymentTimeStart(Date rentPaymentTimeStart) {
        this.rentPaymentTimeStart = rentPaymentTimeStart;
    }

    public Date getRentPaymentTimeEnd() {
        return rentPaymentTimeEnd;
    }

    public void setRentPaymentTimeEnd(Date rentPaymentTimeEnd) {
        this.rentPaymentTimeEnd = rentPaymentTimeEnd;
    }

    public String getRentGrowthRate() {
        return rentGrowthRate;
    }

    public void setRentGrowthRate(String rentGrowthRate) {
        this.rentGrowthRate = rentGrowthRate == null ? null : rentGrowthRate.trim();
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