package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareBuildEconomicIndicators {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private Integer baseDataDicId;

    private String name;

    private String huxing;

    private BigDecimal huxingArea;

    private Integer householdCount;

    private Integer number;

    private BigDecimal area;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getBaseDataDicId() {
        return baseDataDicId;
    }

    public void setBaseDataDicId(Integer baseDataDicId) {
        this.baseDataDicId = baseDataDicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing == null ? null : huxing.trim();
    }

    public BigDecimal getHuxingArea() {
        return huxingArea;
    }

    public void setHuxingArea(BigDecimal huxingArea) {
        this.huxingArea = huxingArea;
    }

    public Integer getHouseholdCount() {
        return householdCount;
    }

    public void setHouseholdCount(Integer householdCount) {
        this.householdCount = householdCount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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