package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicHouse {
    private Integer id;

    private Integer applyId;

    private Integer quoteId;

    private Integer estateId;

    private String houseNumber;

    private String floor;

    private String floorDesc;

    private Integer huxingId;

    private String huxingName;

    private String newHuxingName;

    private Integer spatialDistribution;

    private String spatialDistributionDesc;

    private String certUse;

    private String practicalUse;

    private Integer orientation;

    private BigDecimal area;

    private String areaDesc;

    private Integer researchType;

    private String rightInterestsRestriction;

    private Integer useEnvironment;

    private String description;

    private Date caseDate;

    private Integer useYear;

    private BigDecimal floorPrice;

    private String landLocation;

    private String newDegree;

    private Integer useCondition;

    private String useConditionDescription;

    private Integer decorateSituation;

    private Integer priceConnotation;

    private String decorateSituationDescription;

    private Integer mapId;

    private String huxingData;

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

    public Integer getUseYear() {
        return useYear;
    }

    public void setUseYear(Integer useYear) {
        this.useYear = useYear;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public String getLandLocation() {
        return landLocation;
    }

    public void setLandLocation(String landLocation) {
        this.landLocation = landLocation == null ? null : landLocation.trim();
    }

    public String getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(String newDegree) {
        this.newDegree = newDegree == null ? null : newDegree.trim();
    }

    public Integer getUseCondition() {
        return useCondition;
    }

    public void setUseCondition(Integer useCondition) {
        this.useCondition = useCondition;
    }

    public String getUseConditionDescription() {
        return useConditionDescription;
    }

    public void setUseConditionDescription(String useConditionDescription) {
        this.useConditionDescription = useConditionDescription == null ? null : useConditionDescription.trim();
    }

    public Integer getDecorateSituation() {
        return decorateSituation;
    }

    public void setDecorateSituation(Integer decorateSituation) {
        this.decorateSituation = decorateSituation;
    }

    public Integer getPriceConnotation() {
        return priceConnotation;
    }

    public void setPriceConnotation(Integer priceConnotation) {
        this.priceConnotation = priceConnotation;
    }

    public String getDecorateSituationDescription() {
        return decorateSituationDescription;
    }

    public void setDecorateSituationDescription(String decorateSituationDescription) {
        this.decorateSituationDescription = decorateSituationDescription == null ? null : decorateSituationDescription.trim();
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getHuxingData() {
        return huxingData;
    }

    public void setHuxingData(String huxingData) {
        this.huxingData = huxingData == null ? null : huxingData.trim();
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