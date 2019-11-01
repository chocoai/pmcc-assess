package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseBuilding {
    private Integer id;

    private Integer estateId;

    private Integer residenceUseYear;

    private Integer industryUseYear;

    private Integer type;

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

    private Date beCompletedTime;

    private BigDecimal floorHeight;

    private BigDecimal diameterDepth;

    private BigDecimal landUseYear;

    private BigDecimal netHeight;

    private Integer buildingStructureType;

    private Integer buildingStructureCategory;

    private Integer version;

    private BigDecimal useArea;

    private Integer completedTimeType;

    private BigDecimal inJacketArea;

    private String propertyName;

    private Integer appearanceNewAndOld;

    private Integer betweenDistance;

    private String betweenDistanceDescription;

    private String builderName;

    private Integer maxFloor;

    private Integer appearanceStyle;

    private Integer firstFloor;

    private Integer constructionQuality;

    private String vStructura;

    private String vSpecifications;

    private String vStructuralConstruction;

    private String vBasicPractice;

    private String vStructuralPractice;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer propertySocialPrestige;

    private Integer propertyCompanyNature;

    private Boolean newVersions;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public BigDecimal getLandUseYear() {
        return landUseYear;
    }

    public void setLandUseYear(BigDecimal landUseYear) {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigDecimal getUseArea() {
        return useArea;
    }

    public void setUseArea(BigDecimal useArea) {
        this.useArea = useArea;
    }

    public Integer getCompletedTimeType() {
        return completedTimeType;
    }

    public void setCompletedTimeType(Integer completedTimeType) {
        this.completedTimeType = completedTimeType;
    }

    public BigDecimal getInJacketArea() {
        return inJacketArea;
    }

    public void setInJacketArea(BigDecimal inJacketArea) {
        this.inJacketArea = inJacketArea;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public Integer getAppearanceNewAndOld() {
        return appearanceNewAndOld;
    }

    public void setAppearanceNewAndOld(Integer appearanceNewAndOld) {
        this.appearanceNewAndOld = appearanceNewAndOld;
    }

    public Integer getBetweenDistance() {
        return betweenDistance;
    }

    public void setBetweenDistance(Integer betweenDistance) {
        this.betweenDistance = betweenDistance;
    }

    public String getBetweenDistanceDescription() {
        return betweenDistanceDescription;
    }

    public void setBetweenDistanceDescription(String betweenDistanceDescription) {
        this.betweenDistanceDescription = betweenDistanceDescription == null ? null : betweenDistanceDescription.trim();
    }

    public String getBuilderName() {
        return builderName;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName == null ? null : builderName.trim();
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
    }

    public Integer getAppearanceStyle() {
        return appearanceStyle;
    }

    public void setAppearanceStyle(Integer appearanceStyle) {
        this.appearanceStyle = appearanceStyle;
    }

    public Integer getFirstFloor() {
        return firstFloor;
    }

    public void setFirstFloor(Integer firstFloor) {
        this.firstFloor = firstFloor;
    }

    public Integer getConstructionQuality() {
        return constructionQuality;
    }

    public void setConstructionQuality(Integer constructionQuality) {
        this.constructionQuality = constructionQuality;
    }

    public String getvStructura() {
        return vStructura;
    }

    public void setvStructura(String vStructura) {
        this.vStructura = vStructura == null ? null : vStructura.trim();
    }

    public String getvSpecifications() {
        return vSpecifications;
    }

    public void setvSpecifications(String vSpecifications) {
        this.vSpecifications = vSpecifications == null ? null : vSpecifications.trim();
    }

    public String getvStructuralConstruction() {
        return vStructuralConstruction;
    }

    public void setvStructuralConstruction(String vStructuralConstruction) {
        this.vStructuralConstruction = vStructuralConstruction == null ? null : vStructuralConstruction.trim();
    }

    public String getvBasicPractice() {
        return vBasicPractice;
    }

    public void setvBasicPractice(String vBasicPractice) {
        this.vBasicPractice = vBasicPractice == null ? null : vBasicPractice.trim();
    }

    public String getvStructuralPractice() {
        return vStructuralPractice;
    }

    public void setvStructuralPractice(String vStructuralPractice) {
        this.vStructuralPractice = vStructuralPractice == null ? null : vStructuralPractice.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getPropertySocialPrestige() {
        return propertySocialPrestige;
    }

    public void setPropertySocialPrestige(Integer propertySocialPrestige) {
        this.propertySocialPrestige = propertySocialPrestige;
    }

    public Integer getPropertyCompanyNature() {
        return propertyCompanyNature;
    }

    public void setPropertyCompanyNature(Integer propertyCompanyNature) {
        this.propertyCompanyNature = propertyCompanyNature;
    }

    public Boolean getNewVersions() {
        return newVersions;
    }

    public void setNewVersions(Boolean newVersions) {
        this.newVersions = newVersions;
    }
}