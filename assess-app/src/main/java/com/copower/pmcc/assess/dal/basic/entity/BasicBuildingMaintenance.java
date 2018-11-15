package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicBuildingMaintenance {
    private Integer id;

    private Integer caseMaintenanceId;

    private String buildingNumber;

    private Integer buildingId;

    private String name;

    private Boolean temporary;

    private Integer category;

    private Integer materialQuality;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String buildNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseMaintenanceId() {
        return caseMaintenanceId;
    }

    public void setCaseMaintenanceId(Integer caseMaintenanceId) {
        this.caseMaintenanceId = caseMaintenanceId;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(Integer materialQuality) {
        this.materialQuality = materialQuality;
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

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber == null ? null : buildNumber.trim();
    }
}