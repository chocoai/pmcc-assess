package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicUnit {
    private Integer id;

    private Integer applyId;

    private Integer quoteId;

    private Integer estateId;

    private Integer buildingId;

    private String unitNumber;

    private String elevatorHouseholdRatio;

    private String huxingExplain;

    private String huxingNum;

    private Integer mapId;

    private Integer relevanceId;

    private Integer displayCaseId;

    private Integer version;

    private Boolean bisCase;

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

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
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

    public String getHuxingNum() {
        return huxingNum;
    }

    public void setHuxingNum(String huxingNum) {
        this.huxingNum = huxingNum == null ? null : huxingNum.trim();
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
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