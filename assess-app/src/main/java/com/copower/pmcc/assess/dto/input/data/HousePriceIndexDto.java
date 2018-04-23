package com.copower.pmcc.assess.dto.input.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by kings on 2018-4-23.
 */
public class HousePriceIndexDto {
    public static String INDEX_CALENDAR_KEY = "indexCalendar";
    public static String END_TIME = "endTime";
    public static String START_TIME = "startTime";
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM")
    private Date yearMonthCalendar;
    private String  yearMonthString;
    private String indexCalendar;

    public static String getIndexCalendarKey() {
        return INDEX_CALENDAR_KEY;
    }

    public static void setIndexCalendarKey(String indexCalendarKey) {
        INDEX_CALENDAR_KEY = indexCalendarKey;
    }

    public static String getEndTime() {
        return END_TIME;
    }

    public static void setEndTime(String endTime) {
        END_TIME = endTime;
    }

    public static String getStartTime() {
        return START_TIME;
    }

    public static void setStartTime(String startTime) {
        START_TIME = startTime;
    }

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

    public String getYearMonthString() {
        return yearMonthString;
    }

    public void setYearMonthString(String yearMonthString) {
        this.yearMonthString = yearMonthString;
    }

    public String getIndexCalendar() {
        return indexCalendar;
    }

    public void setIndexCalendar(String indexCalendar) {
        this.indexCalendar = indexCalendar;
    }
}
