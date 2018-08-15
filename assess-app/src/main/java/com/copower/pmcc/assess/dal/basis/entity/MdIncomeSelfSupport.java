package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class MdIncomeSelfSupport {
    private Integer id;

    private Integer incomeId;

    private String averageProfitRate;

    private String averageProfitRateRemark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
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