package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicHouseTradingSell {
    private Integer id;

    private Integer caseTradingSellId;

    private Integer houseId;

    private Date instalmentPeriodStart;

    private Date instalmentPeriodEnd;

    private String instalmentInterest;

    private Integer version;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseTradingSellId() {
        return caseTradingSellId;
    }

    public void setCaseTradingSellId(Integer caseTradingSellId) {
        this.caseTradingSellId = caseTradingSellId;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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