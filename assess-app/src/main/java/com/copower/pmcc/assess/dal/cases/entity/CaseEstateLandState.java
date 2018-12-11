package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseEstateLandState {
    private Integer id;

    private Integer estateId;

    private String name;

    private Integer landUseType;

    private Integer landUseCategory;

    private Integer landLevel;

    private String landArea;

    private String eastTo;

    private String southTo;

    private String westTo;

    private String northTo;

    private Integer shapeState;

    private Integer planeness;

    private Integer developmentDegree;

    private String developmentDegreeContent;

    private String restrictiveCondition;

    private String soil;

    private Integer topographicTerrain;

    private String plotRatio;

    private String buildingDensity;

    private String greenSpaceRate;

    private String compatibleRatio;

    private String bearingCapacity;

    private String contaminated;

    private String ph;

    private String fertility;

    private String creator;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLandUseType() {
        return landUseType;
    }

    public void setLandUseType(Integer landUseType) {
        this.landUseType = landUseType;
    }

    public Integer getLandUseCategory() {
        return landUseCategory;
    }

    public void setLandUseCategory(Integer landUseCategory) {
        this.landUseCategory = landUseCategory;
    }

    public Integer getLandLevel() {
        return landLevel;
    }

    public void setLandLevel(Integer landLevel) {
        this.landLevel = landLevel;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea == null ? null : landArea.trim();
    }

    public String getEastTo() {
        return eastTo;
    }

    public void setEastTo(String eastTo) {
        this.eastTo = eastTo == null ? null : eastTo.trim();
    }

    public String getSouthTo() {
        return southTo;
    }

    public void setSouthTo(String southTo) {
        this.southTo = southTo == null ? null : southTo.trim();
    }

    public String getWestTo() {
        return westTo;
    }

    public void setWestTo(String westTo) {
        this.westTo = westTo == null ? null : westTo.trim();
    }

    public String getNorthTo() {
        return northTo;
    }

    public void setNorthTo(String northTo) {
        this.northTo = northTo == null ? null : northTo.trim();
    }

    public Integer getShapeState() {
        return shapeState;
    }

    public void setShapeState(Integer shapeState) {
        this.shapeState = shapeState;
    }

    public Integer getPlaneness() {
        return planeness;
    }

    public void setPlaneness(Integer planeness) {
        this.planeness = planeness;
    }

    public Integer getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(Integer developmentDegree) {
        this.developmentDegree = developmentDegree;
    }

    public String getDevelopmentDegreeContent() {
        return developmentDegreeContent;
    }

    public void setDevelopmentDegreeContent(String developmentDegreeContent) {
        this.developmentDegreeContent = developmentDegreeContent == null ? null : developmentDegreeContent.trim();
    }

    public String getRestrictiveCondition() {
        return restrictiveCondition;
    }

    public void setRestrictiveCondition(String restrictiveCondition) {
        this.restrictiveCondition = restrictiveCondition == null ? null : restrictiveCondition.trim();
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil == null ? null : soil.trim();
    }

    public Integer getTopographicTerrain() {
        return topographicTerrain;
    }

    public void setTopographicTerrain(Integer topographicTerrain) {
        this.topographicTerrain = topographicTerrain;
    }

    public String getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(String plotRatio) {
        this.plotRatio = plotRatio == null ? null : plotRatio.trim();
    }

    public String getBuildingDensity() {
        return buildingDensity;
    }

    public void setBuildingDensity(String buildingDensity) {
        this.buildingDensity = buildingDensity == null ? null : buildingDensity.trim();
    }

    public String getGreenSpaceRate() {
        return greenSpaceRate;
    }

    public void setGreenSpaceRate(String greenSpaceRate) {
        this.greenSpaceRate = greenSpaceRate == null ? null : greenSpaceRate.trim();
    }

    public String getCompatibleRatio() {
        return compatibleRatio;
    }

    public void setCompatibleRatio(String compatibleRatio) {
        this.compatibleRatio = compatibleRatio == null ? null : compatibleRatio.trim();
    }

    public String getBearingCapacity() {
        return bearingCapacity;
    }

    public void setBearingCapacity(String bearingCapacity) {
        this.bearingCapacity = bearingCapacity == null ? null : bearingCapacity.trim();
    }

    public String getContaminated() {
        return contaminated;
    }

    public void setContaminated(String contaminated) {
        this.contaminated = contaminated == null ? null : contaminated.trim();
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph == null ? null : ph.trim();
    }

    public String getFertility() {
        return fertility;
    }

    public void setFertility(String fertility) {
        this.fertility = fertility == null ? null : fertility.trim();
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