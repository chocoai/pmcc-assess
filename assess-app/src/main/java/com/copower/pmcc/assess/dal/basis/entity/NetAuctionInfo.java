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

    private String provincename;

    private String cityname;

    private String type;

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

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename == null ? null : provincename.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}