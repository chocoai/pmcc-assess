package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicUnit {
    private Integer id;

    private Integer caseUnitId;

    private Integer applyId;

    private Integer basicBuildingMainId;

    private Boolean temporary;

    private String unitNumber;

    private String elevatorHouseholdRatio;

    private Integer version;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseUnitId() {
        return caseUnitId;
    }

    public void setCaseUnitId(Integer caseUnitId) {
        this.caseUnitId = caseUnitId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getBasicBuildingMainId() {
        return basicBuildingMainId;
    }

    public void setBasicBuildingMainId(Integer basicBuildingMainId) {
        this.basicBuildingMainId = basicBuildingMainId;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
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
}