package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicBuilding {
    private Integer id;

    private Integer applyId;

    private Integer estateId;

    private Integer residenceUseYear;

    private Integer industryUseYear;

    private String buildingNumber;

    private String buildingName;

    private Integer floorCount;

    private String location;

    private String builder;

    private String property;

    private String unitInterval;

    private BigDecimal propertyFee;

    private BigDecimal facilitiesUseFee;

    private BigDecimal buildingHeight;

    private BigDecimal buildingArea;

    private BigDecimal coverAnArea;

    private Integer propertyType;

    private Integer propertyCategory;

    private Date openTime;

    private Date roomTime;

    private Integer completedTimeType;

    private Date beCompletedTime;

    private BigDecimal floorHeight;

    private BigDecimal diameterDepth;

    private Integer landUseYear;

    private BigDecimal netHeight;

    private Integer buildingStructureType;

    private Integer buildingStructureCategory;

    private Integer firstFloor;

    private Integer maxFloor;

    private BigDecimal inJacketArea;

    private BigDecimal useArea;

    private Integer constructionQuality;

    private Integer appearanceStyle;

    private Integer appearanceNewAndOld;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public Integer getResidenceUseYear() {
        return residenceUseYear;
    }

    public void setResidenceUseYear(Integer residenceUseYear) {
        this.residenceUseYear = residenceUseYear;
    }

    public Integer getIndustryUseYear() {
        return industryUseYear;
    }

    public void setIndustryUseYear(Integer industryUseYear) {
        this.industryUseYear = industryUseYear;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder == null ? null : builder.trim();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getUnitInterval() {
        return unitInterval;
    }

    public void setUnitInterval(String unitInterval) {
        this.unitInterval = unitInterval == null ? null : unitInterval.trim();
    }

    public BigDecimal getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(BigDecimal propertyFee) {
        this.propertyFee = propertyFee;
    }

    public BigDecimal getFacilitiesUseFee() {
        return facilitiesUseFee;
    }

    public void setFacilitiesUseFee(BigDecimal facilitiesUseFee) {
        this.facilitiesUseFee = facilitiesUseFee;
    }

    public BigDecimal getBuildingHeight() {
        return buildingHeight;
    }

    public void setBuildingHeight(BigDecimal buildingHeight) {
        this.buildingHeight = buildingHeight;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public BigDecimal getCoverAnArea() {
        return coverAnArea;
    }

    public void setCoverAnArea(BigDecimal coverAnArea) {
        this.coverAnArea = coverAnArea;
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(Integer propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(Date roomTime) {
        this.roomTime = roomTime;
    }

    public Integer getCompletedTimeType() {
        return completedTimeType;
    }

    public void setCompletedTimeType(Integer completedTimeType) {
        this.completedTimeType = completedTimeType;
    }

    public Date getBeCompletedTime() {
        return beCompletedTime;
    }

    public void setBeCompletedTime(Date beCompletedTime) {
        this.beCompletedTime = beCompletedTime;
    }

    public BigDecimal getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(BigDecimal floorHeight) {
        this.floorHeight = floorHeight;
    }

    public BigDecimal getDiameterDepth() {
        return diameterDepth;
    }

    public void setDiameterDepth(BigDecimal diameterDepth) {
        this.diameterDepth = diameterDepth;
    }

    public Integer getLandUseYear() {
        return landUseYear;
    }

    public void setLandUseYear(Integer landUseYear) {
        this.landUseYear = landUseYear;
    }

    public BigDecimal getNetHeight() {
        return netHeight;
    }

    public void setNetHeight(BigDecimal netHeight) {
        this.netHeight = netHeight;
    }

    public Integer getBuildingStructureType() {
        return buildingStructureType;
    }

    public void setBuildingStructureType(Integer buildingStructureType) {
        this.buildingStructureType = buildingStructureType;
    }

    public Integer getBuildingStructureCategory() {
        return buildingStructureCategory;
    }

    public void setBuildingStructureCategory(Integer buildingStructureCategory) {
        this.buildingStructureCategory = buildingStructureCategory;
    }

    public Integer getFirstFloor() {
        return firstFloor;
    }

    public void setFirstFloor(Integer firstFloor) {
        this.firstFloor = firstFloor;
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
    }

    public BigDecimal getInJacketArea() {
        return inJacketArea;
    }

    public void setInJacketArea(BigDecimal inJacketArea) {
        this.inJacketArea = inJacketArea;
    }

    public BigDecimal getUseArea() {
        return useArea;
    }

    public void setUseArea(BigDecimal useArea) {
        this.useArea = useArea;
    }

    public Integer getConstructionQuality() {
        return constructionQuality;
    }

    public void setConstructionQuality(Integer constructionQuality) {
        this.constructionQuality = constructionQuality;
    }

    public Integer getAppearanceStyle() {
        return appearanceStyle;
    }

    public void setAppearanceStyle(Integer appearanceStyle) {
        this.appearanceStyle = appearanceStyle;
    }

    public Integer getAppearanceNewAndOld() {
        return appearanceNewAndOld;
    }

    public void setAppearanceNewAndOld(Integer appearanceNewAndOld) {
        this.appearanceNewAndOld = appearanceNewAndOld;
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