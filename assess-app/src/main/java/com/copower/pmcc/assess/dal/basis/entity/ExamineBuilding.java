package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExamineBuilding {
    private Integer id;

    private Integer declareId;

    private Integer planDetailsId;

    private Integer examineType;

    private Integer builderId;

    private Integer propertyId;

    private String buildingNumber;

    private String buildingName;

    private Integer propertyType;

    private Integer floorCount;

    private String unitInterval;

    private BigDecimal propertyFee;

    private BigDecimal facilitiesUseFee;

    private Integer floorStart;

    private Integer floorEnd;

    private BigDecimal buildingHeight;

    private BigDecimal buildingArea;

    private BigDecimal coverAnArea;

    private Integer buildingCategory;

    private Date openTime;

    private Date roomTime;

    private BigDecimal floorHeight;

    private BigDecimal diameterDepth;

    private Integer landUseYear;

    private String location;

    private BigDecimal netHeight;

    private Integer buildingStructureType;

    private Integer buildingStructureCategory;

    private String identifier;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public Integer getBuilderId() {
        return builderId;
    }

    public void setBuilderId(Integer builderId) {
        this.builderId = builderId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
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

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
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

    public Integer getFloorStart() {
        return floorStart;
    }

    public void setFloorStart(Integer floorStart) {
        this.floorStart = floorStart;
    }

    public Integer getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(Integer floorEnd) {
        this.floorEnd = floorEnd;
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

    public Integer getBuildingCategory() {
        return buildingCategory;
    }

    public void setBuildingCategory(Integer buildingCategory) {
        this.buildingCategory = buildingCategory;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
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