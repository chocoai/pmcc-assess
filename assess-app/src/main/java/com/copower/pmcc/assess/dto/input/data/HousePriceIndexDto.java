package com.copower.pmcc.assess.dto.input.data;

import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 13426 on 2018/4/23.
 */
public class HousePriceIndexDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date yearMonthCalendar;

    private Integer id;

    private String indexCalendar;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;
    ///-------------------
    public static String INDEX_CALENDAR_KEY = "indexCalendar";
    public static String END_TIME = "endTime";
    public static String START_TIME = "startTime";
    private String yearMonthString;
    private String yearMonthSource;

    public String getYearMonthString() {
        return yearMonthString;
    }

    public void setYearMonthString(String yearMonthString) {
        this.yearMonthString = yearMonthString;
    }

    public String getYearMonthSource() {
        return yearMonthSource;
    }

    public void setYearMonthSource(String yearMonthSource) {
        this.yearMonthSource = yearMonthSource;
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
        this.indexCalendar = indexCalendar;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
