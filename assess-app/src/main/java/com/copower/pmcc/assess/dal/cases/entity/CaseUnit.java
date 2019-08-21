package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseUnit {
    private Integer id;

    private Integer buildingId;

    private Integer type;

    private String unitNumber;

    private String elevatorHouseholdRatio;

    private Integer version;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String huxingExplain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getHuxingExplain() {
        return huxingExplain;
    }

    public void setHuxingExplain(String huxingExplain) {
        this.huxingExplain = huxingExplain == null ? null : huxingExplain.trim();
    }
}