package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.Infrastructure;

public class InfrastructureVo extends Infrastructure {
    public final static String fileName = "file_name";
    private String provinceName;
    private String cityName;
    private String districtName;
    /**执行开始日期*/
    private String startDateName;
    /**执行结束日期*/
    private String endDateName;

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

    public String getStartDateName() {
        return startDateName;
    }

    public void setStartDateName(String startDateName) {
        this.startDateName = startDateName;
    }

    public String getEndDateName() {
        return endDateName;
    }

    public void setEndDateName(String endDateName) {
        this.endDateName = endDateName;
    }
}
