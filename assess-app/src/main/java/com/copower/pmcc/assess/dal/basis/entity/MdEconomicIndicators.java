package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdEconomicIndicators {
    private Integer id;

    private String parcelSettingOuter;

    private String parcelSettingInner;

    private Integer planDetailsId;

    private String projectFileName;

    private String name;

    private String grade;

    private String certUse;

    private String buildingStructure;

    private BigDecimal buildingBaseCoverage;

    private BigDecimal buildingHeightLimit;

    private String volumetricRate;

    private String buildingDensity;

    private String greenSpaceRate;

    private BigDecimal planNetConstructionLandArea;

    private BigDecimal planTotalBuildArea;

    private String setVolumetricRate;

    private BigDecimal assessUseLandArea;

    private BigDecimal assessTotalBuildArea;

    private Date planDate;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParcelSettingOuter() {
        return parcelSettingOuter;
    }

    public void setParcelSettingOuter(String parcelSettingOuter) {
        this.parcelSettingOuter = parcelSettingOuter == null ? null : parcelSettingOuter.trim();
    }

    public String getParcelSettingInner() {
        return parcelSettingInner;
    }

    public void setParcelSettingInner(String parcelSettingInner) {
        this.parcelSettingInner = parcelSettingInner == null ? null : parcelSettingInner.trim();
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getProjectFileName() {
        return projectFileName;
    }

    public void setProjectFileName(String projectFileName) {
        this.projectFileName = projectFileName == null ? null : projectFileName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getCertUse() {
        return certUse;
    }

    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure == null ? null : buildingStructure.trim();
    }

    public BigDecimal getBuildingBaseCoverage() {
        return buildingBaseCoverage;
    }

    public void setBuildingBaseCoverage(BigDecimal buildingBaseCoverage) {
        this.buildingBaseCoverage = buildingBaseCoverage;
    }

    public BigDecimal getBuildingHeightLimit() {
        return buildingHeightLimit;
    }

    public void setBuildingHeightLimit(BigDecimal buildingHeightLimit) {
        this.buildingHeightLimit = buildingHeightLimit;
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

    public BigDecimal getPlanNetConstructionLandArea() {
        return planNetConstructionLandArea;
    }

    public void setPlanNetConstructionLandArea(BigDecimal planNetConstructionLandArea) {
        this.planNetConstructionLandArea = planNetConstructionLandArea;
    }

    public BigDecimal getPlanTotalBuildArea() {
        return planTotalBuildArea;
    }

    public void setPlanTotalBuildArea(BigDecimal planTotalBuildArea) {
        this.planTotalBuildArea = planTotalBuildArea;
    }

    public String getSetVolumetricRate() {
        return setVolumetricRate;
    }

    public void setSetVolumetricRate(String setVolumetricRate) {
        this.setVolumetricRate = setVolumetricRate == null ? null : setVolumetricRate.trim();
    }

    public BigDecimal getAssessUseLandArea() {
        return assessUseLandArea;
    }

    public void setAssessUseLandArea(BigDecimal assessUseLandArea) {
        this.assessUseLandArea = assessUseLandArea;
    }

    public BigDecimal getAssessTotalBuildArea() {
        return assessTotalBuildArea;
    }

    public void setAssessTotalBuildArea(BigDecimal assessTotalBuildArea) {
        this.assessTotalBuildArea = assessTotalBuildArea;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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