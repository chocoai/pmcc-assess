package com.copower.pmcc.assess.dal.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HousePriceIndex {
    public static String INDEX_CALENDAR_KEY = "indexCalendar";
    public static String END_TIME = "endTime";
    public static String START_TIME = "startTime";
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date yearMonthCalendar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String  yearMonthString;

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

    @Override
    public String toString() {
        return "HousePriceIndex{" +
                "id=" + id +
                ", yearMonthCalendar=" + yearMonthCalendar +
                ", indexCalendar='" + indexCalendar + '\'' +
                ", creator='" + creator + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public String getYearMonthString() {
        return yearMonthString;
    }

    public void setYearMonthString(String yearMonthString) {
        this.yearMonthString = yearMonthString;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}