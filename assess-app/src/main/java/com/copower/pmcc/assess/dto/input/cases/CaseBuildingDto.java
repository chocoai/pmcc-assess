package com.copower.pmcc.assess.dto.input.cases;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/13 15:55
 * @Description:
 */
public class CaseBuildingDto {
    private Integer id;

    private Integer estateId;

    private Integer builderId;

    private Integer propertyId;

    private String buildingNumber;

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
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date openTime;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date roomTime;

    private BigDecimal floorHeight;

    private BigDecimal diameterDepth;

    private Integer landUseYear;

    private String location;

    private BigDecimal netHeight;

    private Integer buildingStructure;

    private Integer buildingStructurePid;

    private String jsonContent;

    private String identifier;

    private Integer version;

    private String creator;

    private String name;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
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

    public Integer getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(Integer buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public Integer getBuildingStructurePid() {
        return buildingStructurePid;
    }

    public void setBuildingStructurePid(Integer buildingStructurePid) {
        this.buildingStructurePid = buildingStructurePid;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
