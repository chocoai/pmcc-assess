package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;

public class DataReportAnalysisVo extends DataReportAnalysis {
    private String reportAnalysisTypeName;
    private String entrustmentPurposeName;
    private String provinceName;
    private String districtName;
    private String cityName;
    private String marketBackgroundTypeName;
    private String typeName;

    public String getReportAnalysisTypeName() {
        return reportAnalysisTypeName;
    }

    public void setReportAnalysisTypeName(String reportAnalysisTypeName) {
        this.reportAnalysisTypeName = reportAnalysisTypeName;
    }

    public String getEntrustmentPurposeName() {
        return entrustmentPurposeName;
    }

    public void setEntrustmentPurposeName(String entrustmentPurposeName) {
        this.entrustmentPurposeName = entrustmentPurposeName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMarketBackgroundTypeName() {
        return marketBackgroundTypeName;
    }

    public void setMarketBackgroundTypeName(String marketBackgroundTypeName) {
        this.marketBackgroundTypeName = marketBackgroundTypeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
