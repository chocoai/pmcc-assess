package com.copower.pmcc.assess.dto.input.project.generate;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2019/1/28 11:27
 * @Description:
 */
public class GenerateReportGenerationDto implements Serializable {
    private Integer id;
    private Integer projectPlanId;
    private Integer projectId;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date investigationsStartDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date investigationsEndDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date reportIssuanceDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date homeWorkEndTime;

    private Integer realEstateAppraiser;

    private Integer areaGroupId;

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

    public Integer getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(Integer projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

}
