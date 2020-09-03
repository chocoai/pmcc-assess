package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NetInfoRecordHouse {
    private Integer id;

    private Integer assignTaskId;

    private Integer masterId;

    private String type;

    private String province;

    private String city;

    private String district;

    private String belongType;

    private String belongCategory;

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

    private Integer status;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String buildingNumber;

    private String unitNumber;

    private String houseNumber;

    private String approver;

    private Integer tradingType;

    private String purchaseLimitStatus;

    private Boolean bisNewest;

    private Integer beUpgradeId;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignTaskId() {
        return assignTaskId;
    }

    public void setAssignTaskId(Integer assignTaskId) {
        this.assignTaskId = assignTaskId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public String getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(String belongCategory) {
        this.belongCategory = belongCategory == null ? null : belongCategory.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber == null ? null : unitNumber.trim();
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public Integer getTradingType() {
        return tradingType;
    }

    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    public String getPurchaseLimitStatus() {
        return purchaseLimitStatus;
    }

    public void setPurchaseLimitStatus(String purchaseLimitStatus) {
        this.purchaseLimitStatus = purchaseLimitStatus == null ? null : purchaseLimitStatus.trim();
    }

    public Boolean getBisNewest() {
        return bisNewest;
    }

    public void setBisNewest(Boolean bisNewest) {
        this.bisNewest = bisNewest;
    }

    public Integer getBeUpgradeId() {
        return beUpgradeId;
    }

    public void setBeUpgradeId(Integer beUpgradeId) {
        this.beUpgradeId = beUpgradeId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}