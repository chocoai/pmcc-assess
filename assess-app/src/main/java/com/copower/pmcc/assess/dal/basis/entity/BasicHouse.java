package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicHouse {
    private Integer id;

    private Integer applyId;

    private Integer unitId;

    private String houseNumber;

    private String floor;

    private String floorDesc;

    private Integer huxingId;

    private String huxingName;

    private String newHuxingName;

    private Integer spatialDistribution;

    private String spatialDistributionDesc;

    private Integer certUse;

    private Integer practicalUse;

    private Integer orientation;

    private BigDecimal area;

    private Integer researchType;

    private String rightInterestsRestriction;

    private Integer useEnvironment;

    private String description;

    private Date caseDate;

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

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getFloorDesc() {
        return floorDesc;
    }

    public void setFloorDesc(String floorDesc) {
        this.floorDesc = floorDesc == null ? null : floorDesc.trim();
    }

    public Integer getHuxingId() {
        return huxingId;
    }

    public void setHuxingId(Integer huxingId) {
        this.huxingId = huxingId;
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

    public Integer getSpatialDistribution() {
        return spatialDistribution;
    }

    public void setSpatialDistribution(Integer spatialDistribution) {
        this.spatialDistribution = spatialDistribution;
    }

    public String getSpatialDistributionDesc() {
        return spatialDistributionDesc;
    }

    public void setSpatialDistributionDesc(String spatialDistributionDesc) {
        this.spatialDistributionDesc = spatialDistributionDesc == null ? null : spatialDistributionDesc.trim();
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

    public Integer getResearchType() {
        return researchType;
    }

    public void setResearchType(Integer researchType) {
        this.researchType = researchType;
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