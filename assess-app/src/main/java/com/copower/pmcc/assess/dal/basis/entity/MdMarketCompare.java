package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdMarketCompare {
    private Integer id;

    private String name;

    private Date valueTimePoint;

    private BigDecimal rewardRate;

    private Integer rewardRateId;

    private BigDecimal judgeIndexNumber;

    private BigDecimal price;

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

    public Date getValueTimePoint() {
        return valueTimePoint;
    }

    public void setValueTimePoint(Date valueTimePoint) {
        this.valueTimePoint = valueTimePoint;
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

    public BigDecimal getJudgeIndexNumber() {
        return judgeIndexNumber;
    }

    public void setJudgeIndexNumber(BigDecimal judgeIndexNumber) {
        this.judgeIndexNumber = judgeIndexNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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