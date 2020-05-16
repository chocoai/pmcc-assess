package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicEstateLandCategoryInfo {
    private Integer id;

    private Integer landId;

    private Integer houseId;

    private String landUseType;

    private String landUseCategory;

    private Date acquisitionTime;

    private BigDecimal landUseYear;

    private BigDecimal landFactorTotalScore;

    private String landLevelContentResult;

    private Integer landLevel;

    private String landLevelName;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String landShape;

    private Date developTime;

    private String plotRatio;

    private String buildingDensity;

    private String greeningRate;

    private String compatibilityType;

    private String compatibilityRate;

    private String heightPermitted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
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

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
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

    public String getLandLevelContentResult() {
        return landLevelContentResult;
    }

    public void setLandLevelContentResult(String landLevelContentResult) {
        this.landLevelContentResult = landLevelContentResult == null ? null : landLevelContentResult.trim();
    }

    public Integer getLandLevel() {
        return landLevel;
    }

    public void setLandLevel(Integer landLevel) {
        this.landLevel = landLevel;
    }

    public String getLandLevelName() {
        return landLevelName;
    }

    public void setLandLevelName(String landLevelName) {
        this.landLevelName = landLevelName == null ? null : landLevelName.trim();
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

    public String getLandShape() {
        return landShape;
    }

    public void setLandShape(String landShape) {
        this.landShape = landShape == null ? null : landShape.trim();
    }

    public Date getDevelopTime() {
        return developTime;
    }

    public void setDevelopTime(Date developTime) {
        this.developTime = developTime;
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

    public String getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(String greeningRate) {
        this.greeningRate = greeningRate == null ? null : greeningRate.trim();
    }

    public String getCompatibilityType() {
        return compatibilityType;
    }

    public void setCompatibilityType(String compatibilityType) {
        this.compatibilityType = compatibilityType == null ? null : compatibilityType.trim();
    }

    public String getCompatibilityRate() {
        return compatibilityRate;
    }

    public void setCompatibilityRate(String compatibilityRate) {
        this.compatibilityRate = compatibilityRate == null ? null : compatibilityRate.trim();
    }

    public String getHeightPermitted() {
        return heightPermitted;
    }

    public void setHeightPermitted(String heightPermitted) {
        this.heightPermitted = heightPermitted == null ? null : heightPermitted.trim();
    }
}