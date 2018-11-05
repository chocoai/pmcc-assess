package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicEstateLandState {
    private Integer id;

    private Integer caseEstateLandStateId;

    private Integer applyId;

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

    private String shapeState;

    private String planeness;

    private String developmentDegree;

    private String restrictiveCondition;

    private String soil;

    private String topographicTerrain;

    private String creator;

    private Integer version;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseEstateLandStateId() {
        return caseEstateLandStateId;
    }

    public void setCaseEstateLandStateId(Integer caseEstateLandStateId) {
        this.caseEstateLandStateId = caseEstateLandStateId;
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

    public String getShapeState() {
        return shapeState;
    }

    public void setShapeState(String shapeState) {
        this.shapeState = shapeState == null ? null : shapeState.trim();
    }

    public String getPlaneness() {
        return planeness;
    }

    public void setPlaneness(String planeness) {
        this.planeness = planeness == null ? null : planeness.trim();
    }

    public String getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(String developmentDegree) {
        this.developmentDegree = developmentDegree == null ? null : developmentDegree.trim();
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

    public String getTopographicTerrain() {
        return topographicTerrain;
    }

    public void setTopographicTerrain(String topographicTerrain) {
        this.topographicTerrain = topographicTerrain == null ? null : topographicTerrain.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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