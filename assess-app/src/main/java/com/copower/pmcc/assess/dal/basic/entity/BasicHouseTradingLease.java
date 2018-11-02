package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicHouseTradingLease {
    private Integer id;

    private Integer houseId;

    private Date rentPaymentTimeStart;

    private Date rentPaymentTimeEnd;

    private String rentGrowthRate;

    private String creator;

    private Integer version;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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