package com.copower.pmcc.assess.dto.input.net;

import java.util.Date;

public class TBSFDto {
    private Integer id;

    private String itemUrl;

    private String title;

    private String currentPrice;

    private String consultPrice;

    private String initPrice;

    private Date start;

    private Date end;

    private String content;

    private String provinceName;

    private String cityName;

    private String type;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(String consultPrice) {
        this.consultPrice = consultPrice;
    }

    public String getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(String initPrice) {
        this.initPrice = initPrice;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
