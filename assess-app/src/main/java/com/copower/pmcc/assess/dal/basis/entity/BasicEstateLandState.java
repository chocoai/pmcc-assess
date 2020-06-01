package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicEstateLandState {
    private Integer id;

    private Integer applyId;

    private Integer estateId;

    private String name;

    private String landUseType;

    private String landUseCategory;

    private BigDecimal landUseYear;

    private BigDecimal landFactorTotalScore;

    private String landLevelContent;

    private Integer landLevel;

    private String landAreaUnit;

    private String landArea;

    private String eastToName;

    private String eastTo;

    private String southToName;

    private String southTo;

    private String westToName;

    private String westTo;

    private String northToName;

    private String northTo;

    private Integer shapeState;

    private String shapeStateRemark;

    private Integer planeness;

    private Integer topographicTerrain;

    private Integer developmentDegree;

    private String developmentDegreeRemark;

    private String developmentDegreeContent;

    private Date acquisitionTime;

    private String plotRatio;

    private String buildingDensity;

    private String greenSpaceRate;

    private String compatibleRatio;

    private String bearingCapacity;

    private String contaminated;

    private String ph;

    private String fertility;

    private String conclusion;

    private String holdOn;

    private BigDecimal buildingHeightLimit;

    private BigDecimal investmentIntensity;

    private String specialRestrictions;

    private String presentSituationLandUse;

    private Integer infrastructureCompleteness;

    private Date developmentTime;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String currentSituation;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLandUseType() {
        return landUseType;
    }

    public void setLandUseType(String landUseType) {
        this.landUseType = landUseType == null ? null : landUseType.trim();
    }

    public String getLandUseCategory() {
        return landUseCategory;
    }

    public void setLandUseCategory(String landUseCategory) {
        this.landUseCategory = landUseCategory == null ? null : landUseCategory.trim();
    }

    public BigDecimal getLandUseYear() {
        return landUseYear;
    }

    public void setLandUseYear(BigDecimal landUseYear) {
        this.landUseYear = landUseYear;
    }

    public BigDecimal getLandFactorTotalScore() {
        return landFactorTotalScore;
    }

    public void setLandFactorTotalScore(BigDecimal landFactorTotalScore) {
        this.landFactorTotalScore = landFactorTotalScore;
    }

    public String getLandLevelContent() {
        return landLevelContent;
    }

    public void setLandLevelContent(String landLevelContent) {
        this.landLevelContent = landLevelContent == null ? null : landLevelContent.trim();
    }

    public Integer getLandLevel() {
        return landLevel;
    }

    public void setLandLevel(Integer landLevel) {
        this.landLevel = landLevel;
    }

    public String getLandAreaUnit() {
        return landAreaUnit;
    }

    public void setLandAreaUnit(String landAreaUnit) {
        this.landAreaUnit = landAreaUnit == null ? null : landAreaUnit.trim();
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea == null ? null : landArea.trim();
    }

    public String getEastToName() {
        return eastToName;
    }

    public void setEastToName(String eastToName) {
        this.eastToName = eastToName == null ? null : eastToName.trim();
    }

    public String getEastTo() {
        return eastTo;
    }

    public void setEastTo(String eastTo) {
        this.eastTo = eastTo == null ? null : eastTo.trim();
    }

    public String getSouthToName() {
        return southToName;
    }

    public void setSouthToName(String southToName) {
        this.southToName = southToName == null ? null : southToName.trim();
    }

    public String getSouthTo() {
        return southTo;
    }

    public void setSouthTo(String southTo) {
        this.southTo = southTo == null ? null : southTo.trim();
    }

    public String getWestToName() {
        return westToName;
    }

    public void setWestToName(String westToName) {
        this.westToName = westToName == null ? null : westToName.trim();
    }

    public String getWestTo() {
        return westTo;
    }

    public void setWestTo(String westTo) {
        this.westTo = westTo == null ? null : westTo.trim();
    }

    public String getNorthToName() {
        return northToName;
    }

    public void setNorthToName(String northToName) {
        this.northToName = northToName == null ? null : northToName.trim();
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

    public String getShapeStateRemark() {
        return shapeStateRemark;
    }

    public void setShapeStateRemark(String shapeStateRemark) {
        this.shapeStateRemark = shapeStateRemark == null ? null : shapeStateRemark.trim();
    }

    public Integer getPlaneness() {
        return planeness;
    }

    public void setPlaneness(Integer planeness) {
        this.planeness = planeness;
    }

    public Integer getTopographicTerrain() {
        return topographicTerrain;
    }

    public void setTopographicTerrain(Integer topographicTerrain) {
        this.topographicTerrain = topographicTerrain;
    }

    public Integer getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(Integer developmentDegree) {
        this.developmentDegree = developmentDegree;
    }

    public String getDevelopmentDegreeRemark() {
        return developmentDegreeRemark;
    }

    public void setDevelopmentDegreeRemark(String developmentDegreeRemark) {
        this.developmentDegreeRemark = developmentDegreeRemark == null ? null : developmentDegreeRemark.trim();
    }

    public String getDevelopmentDegreeContent() {
        return developmentDegreeContent;
    }

    public void setDevelopmentDegreeContent(String developmentDegreeContent) {
        this.developmentDegreeContent = developmentDegreeContent == null ? null : developmentDegreeContent.trim();
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
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

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    public String getHoldOn() {
        return holdOn;
    }

    public void setHoldOn(String holdOn) {
        this.holdOn = holdOn == null ? null : holdOn.trim();
    }

    public BigDecimal getBuildingHeightLimit() {
        return buildingHeightLimit;
    }

    public void setBuildingHeightLimit(BigDecimal buildingHeightLimit) {
        this.buildingHeightLimit = buildingHeightLimit;
    }

    public BigDecimal getInvestmentIntensity() {
        return investmentIntensity;
    }

    public void setInvestmentIntensity(BigDecimal investmentIntensity) {
        this.investmentIntensity = investmentIntensity;
    }

    public String getSpecialRestrictions() {
        return specialRestrictions;
    }

    public void setSpecialRestrictions(String specialRestrictions) {
        this.specialRestrictions = specialRestrictions == null ? null : specialRestrictions.trim();
    }

    public String getPresentSituationLandUse() {
        return presentSituationLandUse;
    }

    public void setPresentSituationLandUse(String presentSituationLandUse) {
        this.presentSituationLandUse = presentSituationLandUse == null ? null : presentSituationLandUse.trim();
    }

    public Integer getInfrastructureCompleteness() {
        return infrastructureCompleteness;
    }

    public void setInfrastructureCompleteness(Integer infrastructureCompleteness) {
        this.infrastructureCompleteness = infrastructureCompleteness;
    }

    public Date getDevelopmentTime() {
        return developmentTime;
    }

    public void setDevelopmentTime(Date developmentTime) {
        this.developmentTime = developmentTime;
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

    public String getCurrentSituation() {
        return currentSituation;
    }

    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation == null ? null : currentSituation.trim();
    }
}