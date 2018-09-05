package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;

/**
 * Created by 13426 on 2018/4/23.
 */
public class DataHousePriceIndexVo extends DataHousePriceIndex {
    private String yearMonthCalendarName;

    private String provinceName;

    private String districtName;

    private String cityName;

    public String getYearMonthCalendarName() {
        return yearMonthCalendarName;
    }

    public void setYearMonthCalendarName(String yearMonthCalendarName) {
        this.yearMonthCalendarName = yearMonthCalendarName;
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
}
