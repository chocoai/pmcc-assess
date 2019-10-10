package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostApproachItem {
    private Integer id;

    private Integer masterId;

    private String yearDescribe;

    private BigDecimal averageProduction;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getYearDescribe() {
        return yearDescribe;
    }

    public void setYearDescribe(String yearDescribe) {
        this.yearDescribe = yearDescribe == null ? null : yearDescribe.trim();
    }

    public BigDecimal getAverageProduction() {
        return averageProduction;
    }

    public void setAverageProduction(BigDecimal averageProduction) {
        this.averageProduction = averageProduction;
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