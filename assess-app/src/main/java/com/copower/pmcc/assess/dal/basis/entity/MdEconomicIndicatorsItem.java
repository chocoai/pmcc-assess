package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdEconomicIndicatorsItem {
    private Integer id;

    private Integer pid;

    private Integer economicId;

    private Integer planDetailsId;

    private BigDecimal plannedBuildingArea;

    private BigDecimal saleableArea;

    private Integer number;

    private BigDecimal unitPrice;

    private String remark;

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

    public Integer getEconomicId() {
        return economicId;
    }

    public void setEconomicId(Integer economicId) {
        this.economicId = economicId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public BigDecimal getPlannedBuildingArea() {
        return plannedBuildingArea;
    }

    public void setPlannedBuildingArea(BigDecimal plannedBuildingArea) {
        this.plannedBuildingArea = plannedBuildingArea;
    }

    public BigDecimal getSaleableArea() {
        return saleableArea;
    }

    public void setSaleableArea(BigDecimal saleableArea) {
        this.saleableArea = saleableArea;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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