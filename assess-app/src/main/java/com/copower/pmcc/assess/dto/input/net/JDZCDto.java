package com.copower.pmcc.assess.dto.input.net;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JDZCDto {
    private Integer id;
    private String title;
    private String province;
    private String city;
    private String startPrice;
    private String currentPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
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
