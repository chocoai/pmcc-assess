package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicEstate {
    private Integer id;

    private Integer applyId;

    private Integer quoteId;

    private Integer classify;

    private Integer type;

    private String province;

    private String city;

    private String district;

    private Integer blockId;

    private String blockName;

    private String blockDescription;

    private String developer;

    private String developerName;

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

    private Date openTime;

    private Integer buildingNumber;

    private Integer position;

    private String description;

    private String averagePrice;

    private String priceRange;

    private Integer supplyHeating;

    private Integer supplyPower;

    private Integer supplyCommunication;

    private Integer supplyRoad;

    private Integer supplyWater;

    private Integer drainWater;

    private Integer supplyGas;

    private String infrastructure;

    private Integer infrastructureCompleteness;

    private String locationDescribe;

    private Integer displayCaseId;

    private Integer mapId;

    private Integer relevanceId;

    private Integer version;

    private Boolean bisCase;

    private Boolean bisEnable;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String averagePriceUnit;

    private String priceRangeUnit;

    private String coverAnAreaUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
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

    public String getBlockDescription() {
        return blockDescription;
    }

    public void setBlockDescription(String blockDescription) {
        this.blockDescription = blockDescription == null ? null : blockDescription.trim();
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName == null ? null : developerName.trim();
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

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
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

    public Integer getSupplyHeating() {
        return supplyHeating;
    }

    public void setSupplyHeating(Integer supplyHeating) {
        this.supplyHeating = supplyHeating;
    }

    public Integer getSupplyPower() {
        return supplyPower;
    }

    public void setSupplyPower(Integer supplyPower) {
        this.supplyPower = supplyPower;
    }

    public Integer getSupplyCommunication() {
        return supplyCommunication;
    }

    public void setSupplyCommunication(Integer supplyCommunication) {
        this.supplyCommunication = supplyCommunication;
    }

    public Integer getSupplyRoad() {
        return supplyRoad;
    }

    public void setSupplyRoad(Integer supplyRoad) {
        this.supplyRoad = supplyRoad;
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

    public String getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure == null ? null : infrastructure.trim();
    }

    public Integer getInfrastructureCompleteness() {
        return infrastructureCompleteness;
    }

    public void setInfrastructureCompleteness(Integer infrastructureCompleteness) {
        this.infrastructureCompleteness = infrastructureCompleteness;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe == null ? null : locationDescribe.trim();
    }

    public Integer getDisplayCaseId() {
        return displayCaseId;
    }

    public void setDisplayCaseId(Integer displayCaseId) {
        this.displayCaseId = displayCaseId;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getBisCase() {
        return bisCase;
    }

    public void setBisCase(Boolean bisCase) {
        this.bisCase = bisCase;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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

    public String getAveragePriceUnit() {
        return averagePriceUnit;
    }

    public void setAveragePriceUnit(String averagePriceUnit) {
        this.averagePriceUnit = averagePriceUnit == null ? null : averagePriceUnit.trim();
    }

    public String getPriceRangeUnit() {
        return priceRangeUnit;
    }

    public void setPriceRangeUnit(String priceRangeUnit) {
        this.priceRangeUnit = priceRangeUnit == null ? null : priceRangeUnit.trim();
    }

    public String getCoverAnAreaUnit() {
        return coverAnAreaUnit;
    }

    public void setCoverAnAreaUnit(String coverAnAreaUnit) {
        this.coverAnAreaUnit = coverAnAreaUnit == null ? null : coverAnAreaUnit.trim();
    }
}