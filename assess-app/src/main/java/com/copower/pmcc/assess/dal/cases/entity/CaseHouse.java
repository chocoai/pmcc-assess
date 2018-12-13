package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseHouse {
    private Integer id;

    private Integer unitId;

    private Integer type;

    private String houseNumber;

    private Integer floor;

    private String huxingName;

    private String newHuxingName;

    private Integer certUse;

    private Integer practicalUse;

    private Integer orientation;

    private BigDecimal area;

    private String rightInterestsRestriction;

    private Integer useEnvironment;

    private String description;

    private Date caseDate;

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

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getHuxingName() {
        return huxingName;
    }

    public void setHuxingName(String huxingName) {
        this.huxingName = huxingName == null ? null : huxingName.trim();
    }

    public String getNewHuxingName() {
        return newHuxingName;
    }

    public void setNewHuxingName(String newHuxingName) {
        this.newHuxingName = newHuxingName == null ? null : newHuxingName.trim();
    }

    public Integer getCertUse() {
        return certUse;
    }

    public void setCertUse(Integer certUse) {
        this.certUse = certUse;
    }

    public Integer getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(Integer practicalUse) {
        this.practicalUse = practicalUse;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getRightInterestsRestriction() {
        return rightInterestsRestriction;
    }

    public void setRightInterestsRestriction(String rightInterestsRestriction) {
        this.rightInterestsRestriction = rightInterestsRestriction == null ? null : rightInterestsRestriction.trim();
    }

    public Integer getUseEnvironment() {
        return useEnvironment;
    }

    public void setUseEnvironment(Integer useEnvironment) {
        this.useEnvironment = useEnvironment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(Date caseDate) {
        this.caseDate = caseDate;
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