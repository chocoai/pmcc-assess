package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBlock;

/**
 * Created by kings on 2018-7-18.
 */
public class ExamineBlockVo extends ExamineBlock {
    private String provinceName;
    private String cityName;
    private String districtName;
    private String regionalNatureName;

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

    public String getRegionalNatureName() {
        return regionalNatureName;
    }

    public void setRegionalNatureName(String regionalNatureName) {
        this.regionalNatureName = regionalNatureName;
    }
}
