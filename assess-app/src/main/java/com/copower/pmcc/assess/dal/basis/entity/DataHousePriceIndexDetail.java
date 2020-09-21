package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataHousePriceIndexDetail {
    private Integer id;

    private Integer housePriceId;

    private BigDecimal indexNumber;

    private Date startDate;

    private Date endDate;

    private BigDecimal unitPremium;

    private BigDecimal floorPremium;

    private Boolean bisBase;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHousePriceId() {
        return housePriceId;
    }

    public void setHousePriceId(Integer housePriceId) {
        this.housePriceId = housePriceId;
    }

    public BigDecimal getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(BigDecimal indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getUnitPremium() {
        return unitPremium;
    }

    public void setUnitPremium(BigDecimal unitPremium) {
        this.unitPremium = unitPremium;
    }

    public BigDecimal getFloorPremium() {
        return floorPremium;
    }

    public void setFloorPremium(BigDecimal floorPremium) {
        this.floorPremium = floorPremium;
    }

    public Boolean getBisBase() {
        return bisBase;
    }

    public void setBisBase(Boolean bisBase) {
        this.bisBase = bisBase;
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