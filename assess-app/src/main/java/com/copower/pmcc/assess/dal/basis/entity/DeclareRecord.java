package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareRecord {
    private Integer id;

    private Integer projectId;

    private Integer projectClassifyId;

    private String province;

    private String city;

    private String district;

    private String name;

    private String ownership;

    private String seat;

    private Integer certUse;

    private Integer practicalUse;

    private BigDecimal floorArea;

    private Integer areaGroupId;

    private Date houseUseEndDate;

    private Date landUseEndDate;

    private BigDecimal assessArea;

    private BigDecimal assessUnitPrice;

    private BigDecimal alreadyOutArea;

    private String inventoryContentKey;

    private Boolean bisGenerateAll;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectClassifyId() {
        return projectClassifyId;
    }

    public void setProjectClassifyId(Integer projectClassifyId) {
        this.projectClassifyId = projectClassifyId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public Integer getCertUse() {
        return certUse;
    }

    public void setCertUse(Integer certUse) {
        this.certUse = certUse;
    }

    public Integer getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(Integer practicalUse) {
        this.practicalUse = practicalUse;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Date getHouseUseEndDate() {
        return houseUseEndDate;
    }

    public void setHouseUseEndDate(Date houseUseEndDate) {
        this.houseUseEndDate = houseUseEndDate;
    }

    public Date getLandUseEndDate() {
        return landUseEndDate;
    }

    public void setLandUseEndDate(Date landUseEndDate) {
        this.landUseEndDate = landUseEndDate;
    }

    public BigDecimal getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    public BigDecimal getAssessUnitPrice() {
        return assessUnitPrice;
    }

    public void setAssessUnitPrice(BigDecimal assessUnitPrice) {
        this.assessUnitPrice = assessUnitPrice;
    }

    public BigDecimal getAlreadyOutArea() {
        return alreadyOutArea;
    }

    public void setAlreadyOutArea(BigDecimal alreadyOutArea) {
        this.alreadyOutArea = alreadyOutArea;
    }

    public String getInventoryContentKey() {
        return inventoryContentKey;
    }

    public void setInventoryContentKey(String inventoryContentKey) {
        this.inventoryContentKey = inventoryContentKey == null ? null : inventoryContentKey.trim();
    }

    public Boolean getBisGenerateAll() {
        return bisGenerateAll;
    }

    public void setBisGenerateAll(Boolean bisGenerateAll) {
        this.bisGenerateAll = bisGenerateAll;
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