package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NetInfoRecordBackup {
    private Integer id;

    private String province;

    private String city;

    private String title;

    private String content;

    private String type;

    private String noticeDate;

    private Date beginTime;

    private Date endTime;

    private String sourceSiteName;

    private String sourceSiteUrl;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String currentPrice;

    private String consultPrice;

    private String initPrice;

    private String liquidRatios;

    private String unitName;

    private Integer amount;

    private Date assessBaseDate;

    private BigDecimal area;

    private BigDecimal negotiatedTotalPrice;

    private Date negotiatedDate;

    private Integer status;

    private String executor;

    private String belongType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate == null ? null : noticeDate.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSourceSiteName() {
        return sourceSiteName;
    }

    public void setSourceSiteName(String sourceSiteName) {
        this.sourceSiteName = sourceSiteName == null ? null : sourceSiteName.trim();
    }

    public String getSourceSiteUrl() {
        return sourceSiteUrl;
    }

    public void setSourceSiteUrl(String sourceSiteUrl) {
        this.sourceSiteUrl = sourceSiteUrl == null ? null : sourceSiteUrl.trim();
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

    public String getLiquidRatios() {
        return liquidRatios;
    }

    public void setLiquidRatios(String liquidRatios) {
        this.liquidRatios = liquidRatios == null ? null : liquidRatios.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getAssessBaseDate() {
        return assessBaseDate;
    }

    public void setAssessBaseDate(Date assessBaseDate) {
        this.assessBaseDate = assessBaseDate;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getNegotiatedTotalPrice() {
        return negotiatedTotalPrice;
    }

    public void setNegotiatedTotalPrice(BigDecimal negotiatedTotalPrice) {
        this.negotiatedTotalPrice = negotiatedTotalPrice;
    }

    public Date getNegotiatedDate() {
        return negotiatedDate;
    }

    public void setNegotiatedDate(Date negotiatedDate) {
        this.negotiatedDate = negotiatedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor == null ? null : executor.trim();
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType == null ? null : belongType.trim();
    }
}