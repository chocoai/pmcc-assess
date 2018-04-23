package com.copower.pmcc.assess.dal.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HousePriceIndex {
    private Integer id;

    private Date yearMonthCalendar;


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