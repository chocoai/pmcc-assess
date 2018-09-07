package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;

public class DataReportAnalysisVo extends DataReportAnalysis {

    private String reportAnalysisTypeName;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String entrustmentName;
    private String purposeName;


    public String getReportAnalysisTypeName() {
        return reportAnalysisTypeName;
    }

    public void setReportAnalysisTypeName(String reportAnalysisTypeName) {
        this.reportAnalysisTypeName = reportAnalysisTypeName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getEntrustmentName() {
        return entrustmentName;
    }

    public void setEntrustmentName(String entrustmentName) {
        this.entrustmentName = entrustmentName;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }
}
