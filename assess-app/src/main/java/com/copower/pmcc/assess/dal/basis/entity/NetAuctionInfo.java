package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class NetAuctionInfo {
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

    private String webName;

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
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice == null ? null : currentPrice.trim();
    }

    public String getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(String consultPrice) {
        this.consultPrice = consultPrice == null ? null : consultPrice.trim();
    }

    public String getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(String initPrice) {
        this.initPrice = initPrice == null ? null : initPrice.trim();
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
        this.content = content == null ? null : content.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName == null ? null : webName.trim();
    }
}