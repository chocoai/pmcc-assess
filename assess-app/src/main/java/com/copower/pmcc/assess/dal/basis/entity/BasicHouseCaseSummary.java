package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicHouseCaseSummary {
    private Integer id;

    private Integer caseHouseId;

    private Integer type;

    private String province;

    private String city;

    private String district;

    private String blockName;

    private String fullName;

    private String street;

    private Integer practicalUse;

    private Integer tradingType;

    private Date tradingTime;

    private BigDecimal tradingUnitPrice;

    private String houseType;

    private String houseCategory;

    private BigDecimal area;

    private String estateName;

    private Integer dealType;

    private BigDecimal currentPrice;

    private BigDecimal consultPrice;

    private Date assessStandardDate;

    private BigDecimal realizationRatios;

    private String realizationCycle;

    private String dealPartInfo;

    private String approver;

    private Integer version;

    private Boolean bisNewest;

    private Boolean bisFromSelf;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseHouseId() {
        return caseHouseId;
    }

    public void setCaseHouseId(Integer caseHouseId) {
        this.caseHouseId = caseHouseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public Integer getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(Integer practicalUse) {
        this.practicalUse = practicalUse;
    }

    public Integer getTradingType() {
        return tradingType;
    }

    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public BigDecimal getTradingUnitPrice() {
        return tradingUnitPrice;
    }

    public void setTradingUnitPrice(BigDecimal tradingUnitPrice) {
        this.tradingUnitPrice = tradingUnitPrice;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory == null ? null : houseCategory.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName == null ? null : estateName.trim();
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

    public BigDecimal getRealizationRatios() {
        return realizationRatios;
    }

    public void setRealizationRatios(BigDecimal realizationRatios) {
        this.realizationRatios = realizationRatios;
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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getBisNewest() {
        return bisNewest;
    }

    public void setBisNewest(Boolean bisNewest) {
        this.bisNewest = bisNewest;
    }

    public Boolean getBisFromSelf() {
        return bisFromSelf;
    }

    public void setBisFromSelf(Boolean bisFromSelf) {
        this.bisFromSelf = bisFromSelf;
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
}