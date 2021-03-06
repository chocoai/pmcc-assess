package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdIncome {
    private Integer id;

    private String name;

    private BigDecimal area;

    private BigDecimal price;

    private Integer operationMode;

    private Integer leaseMode;

    private Integer formType;

    private String restrictionExplain;

    private String averageProfitRate;

    private String averageProfitRateRemark;

    private BigDecimal rewardRate;

    private Integer rewardRateId;

    private BigDecimal houseRemainingYear;

    private BigDecimal landRemainingYear;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(Integer operationMode) {
        this.operationMode = operationMode;
    }

    public Integer getLeaseMode() {
        return leaseMode;
    }

    public void setLeaseMode(Integer leaseMode) {
        this.leaseMode = leaseMode;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public String getRestrictionExplain() {
        return restrictionExplain;
    }

    public void setRestrictionExplain(String restrictionExplain) {
        this.restrictionExplain = restrictionExplain == null ? null : restrictionExplain.trim();
    }

    public String getAverageProfitRate() {
        return averageProfitRate;
    }

    public void setAverageProfitRate(String averageProfitRate) {
        this.averageProfitRate = averageProfitRate == null ? null : averageProfitRate.trim();
    }

    public String getAverageProfitRateRemark() {
        return averageProfitRateRemark;
    }

    public void setAverageProfitRateRemark(String averageProfitRateRemark) {
        this.averageProfitRateRemark = averageProfitRateRemark == null ? null : averageProfitRateRemark.trim();
    }

    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    public Integer getRewardRateId() {
        return rewardRateId;
    }

    public void setRewardRateId(Integer rewardRateId) {
        this.rewardRateId = rewardRateId;
    }

    public BigDecimal getHouseRemainingYear() {
        return houseRemainingYear;
    }

    public void setHouseRemainingYear(BigDecimal houseRemainingYear) {
        this.houseRemainingYear = houseRemainingYear;
    }

    public BigDecimal getLandRemainingYear() {
        return landRemainingYear;
    }

    public void setLandRemainingYear(BigDecimal landRemainingYear) {
        this.landRemainingYear = landRemainingYear;
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