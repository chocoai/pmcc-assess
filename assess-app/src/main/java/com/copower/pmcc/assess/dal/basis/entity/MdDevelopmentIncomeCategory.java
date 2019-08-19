package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdDevelopmentIncomeCategory {
    private Integer id;

    private String name;

    private BigDecimal plannedBuildingArea;

    private BigDecimal totalSaleableAreaPrice;

    private BigDecimal saleableArea;

    private BigDecimal number;

    private BigDecimal unitPrice;

    private BigDecimal assessArea;

    private String remark;

    private Integer planDetailsId;

    private String type;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPlannedBuildingArea() {
        return plannedBuildingArea;
    }

    public void setPlannedBuildingArea(BigDecimal plannedBuildingArea) {
        this.plannedBuildingArea = plannedBuildingArea;
    }

    public BigDecimal getTotalSaleableAreaPrice() {
        return totalSaleableAreaPrice;
    }

    public void setTotalSaleableAreaPrice(BigDecimal totalSaleableAreaPrice) {
        this.totalSaleableAreaPrice = totalSaleableAreaPrice;
    }

    public BigDecimal getSaleableArea() {
        return saleableArea;
    }

    public void setSaleableArea(BigDecimal saleableArea) {
        this.saleableArea = saleableArea;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}