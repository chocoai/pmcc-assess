package com.copower.pmcc.assess.dto.input.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 13426 on 2018/4/23.
 */
public class DataHousePriceIndexDto {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date yearMonthCalendar;

    private String province;

    private String district;

    private String city;

    private String indexCalendar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getYearMonthCalendar() {
        return yearMonthCalendar;
    }

    public void setYearMonthCalendar(Date yearMonthCalendar) {
        this.yearMonthCalendar = yearMonthCalendar;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndexCalendar() {
        return indexCalendar;
    }

    public void setIndexCalendar(String indexCalendar) {
        this.indexCalendar = indexCalendar;
    }
}
