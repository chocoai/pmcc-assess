package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SchemeReportGeneration {
    private Integer id;

    private Integer projectId;

    private Integer projectPlanId;

    private Date investigationsStartDate;

    private Date investigationsEndDate;

    private Date reportIssuanceDate;

    private Date homeWorkEndTime;

    private Integer realEstateAppraiser;

    private Integer areaGroupId;

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

    public Integer getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    public void setRealEstateAppraiser(Integer realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
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