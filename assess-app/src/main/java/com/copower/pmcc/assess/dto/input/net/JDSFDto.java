package com.copower.pmcc.assess.dto.input.net;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JDSFDto {
    private Integer id;
    private String title;
    private String province;
    private String city;
    private String assessmentPriceStr;
    private String currentPriceStr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public String getCurrentPriceStr() {
        return currentPriceStr;
    }

    public void setCurrentPriceStr(String currentPriceStr) {
        this.currentPriceStr = currentPriceStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAssessmentPriceStr() {
        return assessmentPriceStr;
    }

    public void setAssessmentPriceStr(String assessmentPriceStr) {
        this.assessmentPriceStr = assessmentPriceStr;
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
