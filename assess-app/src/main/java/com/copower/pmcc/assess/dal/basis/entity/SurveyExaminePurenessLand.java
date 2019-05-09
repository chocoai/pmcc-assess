package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SurveyExaminePurenessLand {
    private Integer id;

    private Integer projectId;

    private Integer planDetailsId;

    private String processInsId;

    private Integer declareId;

    private String province;

    private String city;

    private String district;

    private String name;

    private Integer landUseType;

    private Integer landUseCategory;

    private Integer landLevel;

    private String landArea;

    private String eastTo;

    private String southTo;

    private String westTo;

    private String northTo;

    private Integer shapeState;

    private String shapeStateRemark;

    private Integer planeness;

    private Integer topographicTerrain;

    private Integer developmentDegree;

    private String developmentDegreeRemark;

    private String developmentDegreeContent;

    private String plotRatio;

    private String buildingDensity;

    private String greenSpaceRate;

    private String compatibleRatio;

    private String bearingCapacity;

    private String contaminated;

    private String ph;

    private String fertility;

    private String conclusion;

    private String holdOn;

    private Integer infrastructureCompleteness;

    private Integer supplyHeating;

    private Integer supplyPower;

    private Integer supplyWater;

    private Integer drainWater;

    private Integer supplyGas;

    private Date tradingTime;

    private BigDecimal tradingTotalPrice;

    private Integer priceConnotation;

    private BigDecimal tradingUnitPrice;

    private Integer informationType;

    private Integer informationCategory;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLandUseType() {
        return landUseType;
    }

    public void setLandUseType(Integer landUseType) {
        this.landUseType = landUseType;
    }

    public Integer getLandUseCategory() {
        return landUseCategory;
    }

    public void setLandUseCategory(Integer landUseCategory) {
        this.landUseCategory = landUseCategory;
    }

    public Integer getLandLevel() {
        return landLevel;
    }

    public void setLandLevel(Integer landLevel) {
        this.landLevel = landLevel;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea == null ? null : landArea.trim();
    }

    public String getEastTo() {
        return eastTo;
    }

    public void setEastTo(String eastTo) {
        this.eastTo = eastTo == null ? null : eastTo.trim();
    }

    public String getSouthTo() {
        return southTo;
    }

    public void setSouthTo(String southTo) {
        this.southTo = southTo == null ? null : southTo.trim();
    }

    public String getWestTo() {
        return westTo;
    }

    public void setWestTo(String westTo) {
        this.westTo = westTo == null ? null : westTo.trim();
    }

    public String getNorthTo() {
        return northTo;
    }

    public void setNorthTo(String northTo) {
        this.northTo = northTo == null ? null : northTo.trim();
    }

    public Integer getShapeState() {
        return shapeState;
    }

    public void setShapeState(Integer shapeState) {
        this.shapeState = shapeState;
    }

    public String getShapeStateRemark() {
        return shapeStateRemark;
    }

    public void setShapeStateRemark(String shapeStateRemark) {
        this.shapeStateRemark = shapeStateRemark == null ? null : shapeStateRemark.trim();
    }

    public Integer getPlaneness() {
        return planeness;
    }

    public void setPlaneness(Integer planeness) {
        this.planeness = planeness;
    }

    public Integer getTopographicTerrain() {
        return topographicTerrain;
    }

    public void setTopographicTerrain(Integer topographicTerrain) {
        this.topographicTerrain = topographicTerrain;
    }

    public Integer getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(Integer developmentDegree) {
        this.developmentDegree = developmentDegree;
    }

    public String getDevelopmentDegreeRemark() {
        return developmentDegreeRemark;
    }

    public void setDevelopmentDegreeRemark(String developmentDegreeRemark) {
        this.developmentDegreeRemark = developmentDegreeRemark == null ? null : developmentDegreeRemark.trim();
    }

    public String getDevelopmentDegreeContent() {
        return developmentDegreeContent;
    }

    public void setDevelopmentDegreeContent(String developmentDegreeContent) {
        this.developmentDegreeContent = developmentDegreeContent == null ? null : developmentDegreeContent.trim();
    }

    public String getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(String plotRatio) {
        this.plotRatio = plotRatio == null ? null : plotRatio.trim();
    }

    public String getBuildingDensity() {
        return buildingDensity;
    }

    public void setBuildingDensity(String buildingDensity) {
        this.buildingDensity = buildingDensity == null ? null : buildingDensity.trim();
    }

    public String getGreenSpaceRate() {
        return greenSpaceRate;
    }

    public void setGreenSpaceRate(String greenSpaceRate) {
        this.greenSpaceRate = greenSpaceRate == null ? null : greenSpaceRate.trim();
    }

    public String getCompatibleRatio() {
        return compatibleRatio;
    }

    public void setCompatibleRatio(String compatibleRatio) {
        this.compatibleRatio = compatibleRatio == null ? null : compatibleRatio.trim();
    }

    public String getBearingCapacity() {
        return bearingCapacity;
    }

    public void setBearingCapacity(String bearingCapacity) {
        this.bearingCapacity = bearingCapacity == null ? null : bearingCapacity.trim();
    }

    public String getContaminated() {
        return contaminated;
    }

    public void setContaminated(String contaminated) {
        this.contaminated = contaminated == null ? null : contaminated.trim();
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph == null ? null : ph.trim();
    }

    public String getFertility() {
        return fertility;
    }

    public void setFertility(String fertility) {
        this.fertility = fertility == null ? null : fertility.trim();
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    public String getHoldOn() {
        return holdOn;
    }

    public void setHoldOn(String holdOn) {
        this.holdOn = holdOn == null ? null : holdOn.trim();
    }

    public Integer getInfrastructureCompleteness() {
        return infrastructureCompleteness;
    }

    public void setInfrastructureCompleteness(Integer infrastructureCompleteness) {
        this.infrastructureCompleteness = infrastructureCompleteness;
    }

    public Integer getSupplyHeating() {
        return supplyHeating;
    }

    public void setSupplyHeating(Integer supplyHeating) {
        this.supplyHeating = supplyHeating;
    }

    public Integer getSupplyPower() {
        return supplyPower;
    }

    public void setSupplyPower(Integer supplyPower) {
        this.supplyPower = supplyPower;
    }

    public Integer getSupplyWater() {
        return supplyWater;
    }

    public void setSupplyWater(Integer supplyWater) {
        this.supplyWater = supplyWater;
    }

    public Integer getDrainWater() {
        return drainWater;
    }

    public void setDrainWater(Integer drainWater) {
        this.drainWater = drainWater;
    }

    public Integer getSupplyGas() {
        return supplyGas;
    }

    public void setSupplyGas(Integer supplyGas) {
        this.supplyGas = supplyGas;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public BigDecimal getTradingTotalPrice() {
        return tradingTotalPrice;
    }

    public void setTradingTotalPrice(BigDecimal tradingTotalPrice) {
        this.tradingTotalPrice = tradingTotalPrice;
    }

    public Integer getPriceConnotation() {
        return priceConnotation;
    }

    public void setPriceConnotation(Integer priceConnotation) {
        this.priceConnotation = priceConnotation;
    }

    public BigDecimal getTradingUnitPrice() {
        return tradingUnitPrice;
    }

    public void setTradingUnitPrice(BigDecimal tradingUnitPrice) {
        this.tradingUnitPrice = tradingUnitPrice;
    }

    public Integer getInformationType() {
        return informationType;
    }

    public void setInformationType(Integer informationType) {
        this.informationType = informationType;
    }

    public Integer getInformationCategory() {
        return informationCategory;
    }

    public void setInformationCategory(Integer informationCategory) {
        this.informationCategory = informationCategory;
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