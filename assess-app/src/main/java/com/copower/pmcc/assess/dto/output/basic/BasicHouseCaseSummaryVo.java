package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;

/**
 * Created by kings on 2018-12-5.
 */
public class BasicHouseCaseSummaryVo extends BasicHouseCaseSummary {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String practicalUseName;
    private String tradingTypeName;
    private String approverName;
    private String creatorName;
    private String dealTypeName;
    private Integer basicApplyBatchId;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDealTypeName() {
        return dealTypeName;
    }

    public void setDealTypeName(String dealTypeName) {
        this.dealTypeName = dealTypeName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getPracticalUseName() {
        return practicalUseName;
    }

    public void setPracticalUseName(String practicalUseName) {
        this.practicalUseName = practicalUseName;
    }

    public String getTradingTypeName() {
        return tradingTypeName;
    }

    public void setTradingTypeName(String tradingTypeName) {
        this.tradingTypeName = tradingTypeName;
    }

    public Integer getBasicApplyBatchId() {
        return basicApplyBatchId;
    }

    public void setBasicApplyBatchId(Integer basicApplyBatchId) {
        this.basicApplyBatchId = basicApplyBatchId;
    }
}
