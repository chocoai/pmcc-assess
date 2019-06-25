package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareEconomicIndicatorsHead {
    private Integer id;

    private Integer planDetailsId;

    private String name;

    private String certUse;

    private String grade;

    private String buildingStructure;

    private BigDecimal buildingHeightLimit;

    private BigDecimal buildingBaseCoverage;

    private String setVolumetricRate;

    private String volumetricRate;

    private String buildingDensity;

    private String greenSpaceRate;

    private Date planDate;

    private BigDecimal planTotalBuildArea;

    private BigDecimal planNetConstructionLandArea;

    private BigDecimal assessTotalBuildArea;

    private BigDecimal assessUseLandArea;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCertUse() {
        return certUse;
    }

    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure == null ? null : buildingStructure.trim();
    }

    public BigDecimal getBuildingHeightLimit() {
        return buildingHeightLimit;
    }

    public void setBuildingHeightLimit(BigDecimal buildingHeightLimit) {
        this.buildingHeightLimit = buildingHeightLimit;
    }

    public BigDecimal getBuildingBaseCoverage() {
        return buildingBaseCoverage;
    }

    public void setBuildingBaseCoverage(BigDecimal buildingBaseCoverage) {
        this.buildingBaseCoverage = buildingBaseCoverage;
    }

    public String getSetVolumetricRate() {
        return setVolumetricRate;
    }

    public void setSetVolumetricRate(String setVolumetricRate) {
        this.setVolumetricRate = setVolumetricRate == null ? null : setVolumetricRate.trim();
    }

    public String getVolumetricRate() {
        return volumetricRate;
    }

    public void setVolumetricRate(String volumetricRate) {
        this.volumetricRate = volumetricRate == null ? null : volumetricRate.trim();
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

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public BigDecimal getPlanTotalBuildArea() {
        return planTotalBuildArea;
    }

    public void setPlanTotalBuildArea(BigDecimal planTotalBuildArea) {
        this.planTotalBuildArea = planTotalBuildArea;
    }

    public BigDecimal getPlanNetConstructionLandArea() {
        return planNetConstructionLandArea;
    }

    public void setPlanNetConstructionLandArea(BigDecimal planNetConstructionLandArea) {
        this.planNetConstructionLandArea = planNetConstructionLandArea;
    }

    public BigDecimal getAssessTotalBuildArea() {
        return assessTotalBuildArea;
    }

    public void setAssessTotalBuildArea(BigDecimal assessTotalBuildArea) {
        this.assessTotalBuildArea = assessTotalBuildArea;
    }

    public BigDecimal getAssessUseLandArea() {
        return assessUseLandArea;
    }

    public void setAssessUseLandArea(BigDecimal assessUseLandArea) {
        this.assessUseLandArea = assessUseLandArea;
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