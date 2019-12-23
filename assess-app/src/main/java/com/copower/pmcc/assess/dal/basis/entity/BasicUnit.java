package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicUnit {
    private Integer id;

    private Integer applyId;

    private Integer buildingId;

    private String unitNumber;

    private String elevatorHouseholdRatio;

    private String huxingExplain;

    private Integer mapId;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer relevanceId;

    private Integer displayCaseId;

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

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber == null ? null : unitNumber.trim();
    }

    public String getElevatorHouseholdRatio() {
        return elevatorHouseholdRatio;
    }

    public void setElevatorHouseholdRatio(String elevatorHouseholdRatio) {
        this.elevatorHouseholdRatio = elevatorHouseholdRatio == null ? null : elevatorHouseholdRatio.trim();
    }

    public String getHuxingExplain() {
        return huxingExplain;
    }

    public void setHuxingExplain(String huxingExplain) {
        this.huxingExplain = huxingExplain == null ? null : huxingExplain.trim();
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
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

    public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public Integer getDisplayCaseId() {
        return displayCaseId;
    }

    public void setDisplayCaseId(Integer displayCaseId) {
        this.displayCaseId = displayCaseId;
    }
}