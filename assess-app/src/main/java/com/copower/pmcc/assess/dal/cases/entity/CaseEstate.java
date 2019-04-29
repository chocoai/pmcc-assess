package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseEstate {
    private Integer id;

    private Integer type;

    private String province;

    private String city;

    private String district;

    private Integer blockId;

    private String blockName;

    private String developer;

    private String name;

    private String streetNumber;

    private String street;

    private String number;

    private Integer landLevel;

    private String attachNumber;

    private BigDecimal floorArea;

    private BigDecimal coverAnArea;

    private String volumetricRate;

    private String greeningRate;

    private Integer buildingNumber;

    private Integer position;

    private String description;

    private String averagePrice;

    private String priceRange;

    private Integer supplyPower;

    private Integer supplyWater;

    private Integer drainWater;

    private Integer supplyGas;

    private Integer supplyHeating;

    private Integer version;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String locationDescribe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber == null ? null : streetNumber.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getLandLevel() {
        return landLevel;
    }

    public void setLandLevel(Integer landLevel) {
        this.landLevel = landLevel;
    }

    public String getAttachNumber() {
        return attachNumber;
    }

    public void setAttachNumber(String attachNumber) {
        this.attachNumber = attachNumber == null ? null : attachNumber.trim();
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getCoverAnArea() {
        return coverAnArea;
    }

    public void setCoverAnArea(BigDecimal coverAnArea) {
        this.coverAnArea = coverAnArea;
    }

    public String getVolumetricRate() {
        return volumetricRate;
    }

    public void setVolumetricRate(String volumetricRate) {
        this.volumetricRate = volumetricRate == null ? null : volumetricRate.trim();
    }

    public String getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(String greeningRate) {
        this.greeningRate = greeningRate == null ? null : greeningRate.trim();
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice == null ? null : averagePrice.trim();
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange == null ? null : priceRange.trim();
    }

    public Integer getSupplyPower() {
        return supplyPower;
    }

    public void setSupplyPower(Integer supplyPower) {
        this.supplyPower = supplyPower;
    }

    public Integer getSupplyWater() {
        return supplyWater;
    }

    public void setSupplyWater(Integer supplyWater) {
        this.supplyWater = supplyWater;
    }

    public Integer getDrainWater() {
        return drainWater;
    }

    public void setDrainWater(Integer drainWater) {
        this.drainWater = drainWater;
    }

    public Integer getSupplyGas() {
        return supplyGas;
    }

    public void setSupplyGas(Integer supplyGas) {
        this.supplyGas = supplyGas;
    }

    public Integer getSupplyHeating() {
        return supplyHeating;
    }

    public void setSupplyHeating(Integer supplyHeating) {
        this.supplyHeating = supplyHeating;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe == null ? null : locationDescribe.trim();
    }
}