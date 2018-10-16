package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareRecord {
    private Integer id;

    private Integer projectId;

    private String dataTableName;

    private Integer dataTableId;

    private String province;

    private String city;

    private String district;

    private String name;

    private String ownership;

    private String seat;

    private String certUse;

    private String practicalUse;

    private BigDecimal floorArea;

    private Integer areaGroupId;

    private Date houseUseEndDate;

    private Date landUseEndDate;

    private String inventoryContentKey;

    private BigDecimal price;

    private BigDecimal assessArea;

    private BigDecimal alreadyOutArea;

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

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName == null ? null : dataTableName.trim();
    }

    public Integer getDataTableId() {
        return dataTableId;
    }

    public void setDataTableId(Integer dataTableId) {
        this.dataTableId = dataTableId;
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

    public String getCertUse() {
        return certUse;
    }

    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    public String getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(String practicalUse) {
        this.practicalUse = practicalUse == null ? null : practicalUse.trim();
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

    public String getInventoryContentKey() {
        return inventoryContentKey;
    }

    public void setInventoryContentKey(String inventoryContentKey) {
        this.inventoryContentKey = inventoryContentKey == null ? null : inventoryContentKey.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    public BigDecimal getAlreadyOutArea() {
        return alreadyOutArea;
    }

    public void setAlreadyOutArea(BigDecimal alreadyOutArea) {
        this.alreadyOutArea = alreadyOutArea;
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