package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataHousePriceIndex {
    private Integer id;

    private Date yearMonthCalendar;

    private String province;

    private String district;

    private String city;

    private String indexCalendar;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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
        this.province = province == null ? null : province.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getIndexCalendar() {
        return indexCalendar;
    }

    public void setIndexCalendar(String indexCalendar) {
        this.indexCalendar = indexCalendar == null ? null : indexCalendar.trim();
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