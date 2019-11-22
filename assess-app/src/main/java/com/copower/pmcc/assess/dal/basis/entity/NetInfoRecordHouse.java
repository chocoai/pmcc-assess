package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NetInfoRecordHouse {
    private Integer id;

    private Integer masterId;

    private String province;

    private String city;

    private String district;

    private String belongType;

    private String street;

    private BigDecimal area;

    private String name;

    private Integer dealType;

    private BigDecimal currentPrice;

    private Date negotiatedDate;

    private BigDecimal consultPrice;

    private Date assessStandardDate;

    private BigDecimal unitPrice;

    private BigDecimal houseRealizationRatios;

    private String realizationCycle;

    private String dealPartInfo;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String type;

    private String belongCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType == null ? null : belongType.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getNegotiatedDate() {
        return negotiatedDate;
    }

    public void setNegotiatedDate(Date negotiatedDate) {
        this.negotiatedDate = negotiatedDate;
    }

    public BigDecimal getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(BigDecimal consultPrice) {
        this.consultPrice = consultPrice;
    }

    public Date getAssessStandardDate() {
        return assessStandardDate;
    }

    public void setAssessStandardDate(Date assessStandardDate) {
        this.assessStandardDate = assessStandardDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getHouseRealizationRatios() {
        return houseRealizationRatios;
    }

    public void setHouseRealizationRatios(BigDecimal houseRealizationRatios) {
        this.houseRealizationRatios = houseRealizationRatios;
    }

    public String getRealizationCycle() {
        return realizationCycle;
    }

    public void setRealizationCycle(String realizationCycle) {
        this.realizationCycle = realizationCycle == null ? null : realizationCycle.trim();
    }

    public String getDealPartInfo() {
        return dealPartInfo;
    }

    public void setDealPartInfo(String dealPartInfo) {
        this.dealPartInfo = dealPartInfo == null ? null : dealPartInfo.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(String belongCategory) {
        this.belongCategory = belongCategory == null ? null : belongCategory.trim();
    }
}