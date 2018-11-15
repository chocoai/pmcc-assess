package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicUnitElevator {
    private Integer id;

    private Integer caseId;

    private Integer unitId;

    private String type;

    private String brand;

    private Integer number;

    private Integer quasiLoadNumber;

    private String quasiLoadWeight;

    private String runningSpeed;

    private Boolean temporary;

    private String maintenance;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getQuasiLoadNumber() {
        return quasiLoadNumber;
    }

    public void setQuasiLoadNumber(Integer quasiLoadNumber) {
        this.quasiLoadNumber = quasiLoadNumber;
    }

    public String getQuasiLoadWeight() {
        return quasiLoadWeight;
    }

    public void setQuasiLoadWeight(String quasiLoadWeight) {
        this.quasiLoadWeight = quasiLoadWeight == null ? null : quasiLoadWeight.trim();
    }

    public String getRunningSpeed() {
        return runningSpeed;
    }

    public void setRunningSpeed(String runningSpeed) {
        this.runningSpeed = runningSpeed == null ? null : runningSpeed.trim();
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance == null ? null : maintenance.trim();
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