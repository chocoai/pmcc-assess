package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NetInfoRecordLand {
    private Integer id;

    private Integer assignTaskId;

    private Integer masterId;

    private String type;

    private String province;

    private String city;

    private String district;

    private String belongType;

    private String belongCategory;

    private String landPurpose;

    private String street;

    private BigDecimal area;

    private String areaUnit;

    private String name;

    private String parcelNumber;

    private String parcelSite;

    private Integer dealType;

    private BigDecimal currentPrice;

    private Date negotiatedDate;

    private BigDecimal consultPrice;

    private BigDecimal consultPriceMu;

    private Date assessStandardDate;

    private BigDecimal unitPrice;

    private BigDecimal unitPriceMu;

    private BigDecimal floorPrice;

    private BigDecimal landRealizationRatios;

    private String realizationCycle;

    private BigDecimal landArea;

    private String landAreaUnit;

    private BigDecimal plotRatio;

    private String plotRatioRemark;

    private BigDecimal greeningRate;

    private String greeningRateRemark;

    private String buildDensity;

    private String buildDensityRemark;

    private BigDecimal buildHeight;

    private String buildHeightRemark;

    private BigDecimal indexAmount;

    private String indexAmountRemark;

    private String dealPartInfo;

    private Integer status;

    private String approver;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public String getLandPurpose() {
        return landPurpose;
    }

    public void setLandPurpose(String landPurpose) {
        this.landPurpose = landPurpose == null ? null : landPurpose.trim();
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

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit == null ? null : areaUnit.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber == null ? null : parcelNumber.trim();
    }

    public String getParcelSite() {
        return parcelSite;
    }

    public void setParcelSite(String parcelSite) {
        this.parcelSite = parcelSite == null ? null : parcelSite.trim();
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

    public BigDecimal getConsultPriceMu() {
        return consultPriceMu;
    }

    public void setConsultPriceMu(BigDecimal consultPriceMu) {
        this.consultPriceMu = consultPriceMu;
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

    public BigDecimal getUnitPriceMu() {
        return unitPriceMu;
    }

    public void setUnitPriceMu(BigDecimal unitPriceMu) {
        this.unitPriceMu = unitPriceMu;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public BigDecimal getLandRealizationRatios() {
        return landRealizationRatios;
    }

    public void setLandRealizationRatios(BigDecimal landRealizationRatios) {
        this.landRealizationRatios = landRealizationRatios;
    }

    public String getRealizationCycle() {
        return realizationCycle;
    }

    public void setRealizationCycle(String realizationCycle) {
        this.realizationCycle = realizationCycle == null ? null : realizationCycle.trim();
    }

    public BigDecimal getLandArea() {
        return landArea;
    }

    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    public String getLandAreaUnit() {
        return landAreaUnit;
    }

    public void setLandAreaUnit(String landAreaUnit) {
        this.landAreaUnit = landAreaUnit == null ? null : landAreaUnit.trim();
    }

    public BigDecimal getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(BigDecimal plotRatio) {
        this.plotRatio = plotRatio;
    }

    public String getPlotRatioRemark() {
        return plotRatioRemark;
    }

    public void setPlotRatioRemark(String plotRatioRemark) {
        this.plotRatioRemark = plotRatioRemark == null ? null : plotRatioRemark.trim();
    }

    public BigDecimal getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(BigDecimal greeningRate) {
        this.greeningRate = greeningRate;
    }

    public String getGreeningRateRemark() {
        return greeningRateRemark;
    }

    public void setGreeningRateRemark(String greeningRateRemark) {
        this.greeningRateRemark = greeningRateRemark == null ? null : greeningRateRemark.trim();
    }

    public String getBuildDensity() {
        return buildDensity;
    }

    public void setBuildDensity(String buildDensity) {
        this.buildDensity = buildDensity == null ? null : buildDensity.trim();
    }

    public String getBuildDensityRemark() {
        return buildDensityRemark;
    }

    public void setBuildDensityRemark(String buildDensityRemark) {
        this.buildDensityRemark = buildDensityRemark == null ? null : buildDensityRemark.trim();
    }

    public BigDecimal getBuildHeight() {
        return buildHeight;
    }

    public void setBuildHeight(BigDecimal buildHeight) {
        this.buildHeight = buildHeight;
    }

    public String getBuildHeightRemark() {
        return buildHeightRemark;
    }

    public void setBuildHeightRemark(String buildHeightRemark) {
        this.buildHeightRemark = buildHeightRemark == null ? null : buildHeightRemark.trim();
    }

    public BigDecimal getIndexAmount() {
        return indexAmount;
    }

    public void setIndexAmount(BigDecimal indexAmount) {
        this.indexAmount = indexAmount;
    }

    public String getIndexAmountRemark() {
        return indexAmountRemark;
    }

    public void setIndexAmountRemark(String indexAmountRemark) {
        this.indexAmountRemark = indexAmountRemark == null ? null : indexAmountRemark.trim();
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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
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