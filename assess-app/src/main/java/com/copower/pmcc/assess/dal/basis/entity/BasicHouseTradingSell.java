package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicHouseTradingSell {
    private Integer id;

    private Integer houseId;

    private Date instalmentPeriodStart;

    private Date instalmentPeriodEnd;

    private String instalmentInterest;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer tradingId;

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

    public Date getInstalmentPeriodStart() {
        return instalmentPeriodStart;
    }

    public void setInstalmentPeriodStart(Date instalmentPeriodStart) {
        this.instalmentPeriodStart = instalmentPeriodStart;
    }

    public Date getInstalmentPeriodEnd() {
        return instalmentPeriodEnd;
    }

    public void setInstalmentPeriodEnd(Date instalmentPeriodEnd) {
        this.instalmentPeriodEnd = instalmentPeriodEnd;
    }

    public String getInstalmentInterest() {
        return instalmentInterest;
    }

    public void setInstalmentInterest(String instalmentInterest) {
        this.instalmentInterest = instalmentInterest == null ? null : instalmentInterest.trim();
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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

    public Integer getTradingId() {
        return tradingId;
    }

    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }
}