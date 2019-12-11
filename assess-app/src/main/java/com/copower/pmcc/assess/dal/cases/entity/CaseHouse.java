package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CaseHouse {
    private Integer id;

    private Integer unitId;

    private Integer type;

    private String houseNumber;

    private String floor;

    private String floorDesc;

    private String huxingName;

    private String newHuxingName;

    private String certUse;

    private String practicalUse;

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

    private String newDegree;

    private Integer priceConnotation;

    private Integer huxingId;

    private String areaDesc;

    private Integer researchType;

    private String spatialDistributionDesc;

    private Integer spatialDistribution;

    private BigDecimal floorPrice;

    private Integer useYear;

    private String landLocation;

    private Boolean newVersions;

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

    public String getCertUse() {
        return certUse;
    }

    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    public String getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(String practicalUse) {
        this.practicalUse = practicalUse == null ? null : practicalUse.trim();
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

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree == null ? null : newDegree.trim();
    }

    public Integer getPriceConnotation() {
        return priceConnotation;
    }

    public void setPriceConnotation(Integer priceConnotation) {
        this.priceConnotation = priceConnotation;
    }

    public Integer getHuxingId() {
        return huxingId;
    }

    public void setHuxingId(Integer huxingId) {
        this.huxingId = huxingId;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc == null ? null : areaDesc.trim();
    }

    public Integer getResearchType() {
        return researchType;
    }

    public void setResearchType(Integer researchType) {
        this.researchType = researchType;
    }

    public String getSpatialDistributionDesc() {
        return spatialDistributionDesc;
    }

    public void setSpatialDistributionDesc(String spatialDistributionDesc) {
        this.spatialDistributionDesc = spatialDistributionDesc == null ? null : spatialDistributionDesc.trim();
    }

    public Integer getSpatialDistribution() {
        return spatialDistribution;
    }

    public void setSpatialDistribution(Integer spatialDistribution) {
        this.spatialDistribution = spatialDistribution;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Integer getUseYear() {
        return useYear;
    }

    public void setUseYear(Integer useYear) {
        this.useYear = useYear;
    }

    public String getLandLocation() {
        return landLocation;
    }

    public void setLandLocation(String landLocation) {
        this.landLocation = landLocation == null ? null : landLocation.trim();
    }

    public Boolean getNewVersions() {
        return newVersions;
    }

    public void setNewVersions(Boolean newVersions) {
        this.newVersions = newVersions;
    }
}