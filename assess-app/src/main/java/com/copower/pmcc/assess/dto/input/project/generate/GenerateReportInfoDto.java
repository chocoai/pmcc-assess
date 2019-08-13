package com.copower.pmcc.assess.dto.input.project.generate;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2019/1/28 11:27
 * @Description:
 */
public class GenerateReportInfoDto implements Serializable {
    private Integer id;

    private Integer projectId;

    private Integer projectPlanId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date investigationsStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date investigationsEndDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reportIssuanceDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date homeWorkEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;

    private String realEstateAppraiser;

    private String qualificationType;

    private Integer areaGroupId;
    private String processInsId;

    private Integer assessCategory;
    private String queryCode;

    private String recordNo;

    private String status;

    private String creator;

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

    public String getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    public void setRealEstateAppraiser(String realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
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
        this.creator = creator;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
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
        this.status = status;
    }

    public String getQueryCode() {
        return queryCode;
    }

    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
