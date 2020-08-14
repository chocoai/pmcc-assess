package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicBuilding {
    private Integer id;

    private Integer applyId;

    private Integer estateId;

    private Integer quoteId;

    private Integer residenceUseYear;

    private Integer industryUseYear;

    private String buildingNumber;

    private String buildingName;

    private String floorCount;

    private String location;

    private Integer builder;

    private String builderName;

    private Integer property;

    private String propertyName;

    private String unitInterval;

    private Integer unitCount;

    private BigDecimal propertyFee;

    private BigDecimal facilitiesUseFee;

    private String buildingHeight;

    private BigDecimal buildingArea;

    private BigDecimal coverAnArea;

    private Integer propertyType;

    private Integer propertyCategory;

    private Date openTime;

    private Date roomTime;

    private Integer completedTimeType;

    private Date beCompletedTime;

    private String floorHeight;

    private BigDecimal diameterDepth;

    private BigDecimal landUseYear;

    private BigDecimal netHeight;

    private Integer buildingStructureType;

    private Integer buildingStructureCategory;

    private Integer firstFloor;

    private Integer maxFloor;

    private String inJacketArea;

    private String useArea;

    private Integer constructionQuality;

    private Integer appearanceStyle;

    private Integer appearanceNewAndOld;

    private Integer betweenDistance;

    private String betweenDistanceDescription;

    private String vStructura;

    private String vSpecifications;

    private String vStructuralConstruction;

    private String vBasicPractice;

    private String vStructuralPractice;

    private Integer propertySocialPrestige;

    private Integer propertyCompanyNature;

    private String minimumFloorDistance;

    private Integer streetInfoId;

    private Integer mapId;

    private Integer displayCaseId;

    private Integer relevanceId;

    private String remark;

    private Integer version;

    private Boolean bisCase;

    private String reference;

    private String orientation;

    private Boolean bisEnable;

    private Boolean bisDelete;

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

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
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

    public String getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(String floorCount) {
        this.floorCount = floorCount == null ? null : floorCount.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getBuilder() {
        return builder;
    }

    public void setBuilder(Integer builder) {
        this.builder = builder;
    }

    public String getBuilderName() {
        return builderName;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName == null ? null : builderName.trim();
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public String getUnitInterval() {
        return unitInterval;
    }

    public void setUnitInterval(String unitInterval) {
        this.unitInterval = unitInterval == null ? null : unitInterval.trim();
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
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

    public String getBuildingHeight() {
        return buildingHeight;
    }

    public void setBuildingHeight(String buildingHeight) {
        this.buildingHeight = buildingHeight == null ? null : buildingHeight.trim();
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

    public String getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(String floorHeight) {
        this.floorHeight = floorHeight == null ? null : floorHeight.trim();
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

    public String getInJacketArea() {
        return inJacketArea;
    }

    public void setInJacketArea(String inJacketArea) {
        this.inJacketArea = inJacketArea == null ? null : inJacketArea.trim();
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea == null ? null : useArea.trim();
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

    public String getMinimumFloorDistance() {
        return minimumFloorDistance;
    }

    public void setMinimumFloorDistance(String minimumFloorDistance) {
        this.minimumFloorDistance = minimumFloorDistance == null ? null : minimumFloorDistance.trim();
    }

    public Integer getStreetInfoId() {
        return streetInfoId;
    }

    public void setStreetInfoId(Integer streetInfoId) {
        this.streetInfoId = streetInfoId;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public Integer getDisplayCaseId() {
        return displayCaseId;
    }

    public void setDisplayCaseId(Integer displayCaseId) {
        this.displayCaseId = displayCaseId;
    }

    public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
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
}