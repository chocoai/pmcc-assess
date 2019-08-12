package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class GenerateReportInfo {
    private Integer id;

    private Integer projectId;

    private Integer projectPlanId;

    private String qualificationType;

    private Date investigationsStartDate;

    private Date investigationsEndDate;

    private Date reportIssuanceDate;

    private Date homeWorkEndTime;

    private String realEstateAppraiser;

    private Integer areaGroupId;

    private String processInsId;

    private Integer assessCategory;

    private String status;

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

    public Integer getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(Integer projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType == null ? null : qualificationType.trim();
    }

    public Date getInvestigationsStartDate() {
        return investigationsStartDate;
    }

    public void setInvestigationsStartDate(Date investigationsStartDate) {
        this.investigationsStartDate = investigationsStartDate;
    }

    public Date getInvestigationsEndDate() {
        return investigationsEndDate;
    }

    public void setInvestigationsEndDate(Date investigationsEndDate) {
        this.investigationsEndDate = investigationsEndDate;
    }

    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    public void setReportIssuanceDate(Date reportIssuanceDate) {
        this.reportIssuanceDate = reportIssuanceDate;
    }

    public Date getHomeWorkEndTime() {
        return homeWorkEndTime;
    }

    public void setHomeWorkEndTime(Date homeWorkEndTime) {
        this.homeWorkEndTime = homeWorkEndTime;
    }

    public String getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    public void setRealEstateAppraiser(String realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser == null ? null : realEstateAppraiser.trim();
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Integer getAssessCategory() {
        return assessCategory;
    }

    public void setAssessCategory(Integer assessCategory) {
        this.assessCategory = assessCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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