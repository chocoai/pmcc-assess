package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class SurveyLocaleExplore {
    private Integer id;

    private Integer projectId;

    private Integer planDetailId;

    private String processInsId;

    private String surveySheetNumber;

    private String surveyPeople;

    private Date surveyTime;

    private String belongWarrant;

    private String ledLuminousPeople;

    private String surveyPicture;

    private String surveyImage;

    private String locationPicture;

    private String surveyLocaltion;

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

    public Integer getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(Integer planDetailId) {
        this.planDetailId = planDetailId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getSurveySheetNumber() {
        return surveySheetNumber;
    }

    public void setSurveySheetNumber(String surveySheetNumber) {
        this.surveySheetNumber = surveySheetNumber == null ? null : surveySheetNumber.trim();
    }

    public String getSurveyPeople() {
        return surveyPeople;
    }

    public void setSurveyPeople(String surveyPeople) {
        this.surveyPeople = surveyPeople == null ? null : surveyPeople.trim();
    }

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getBelongWarrant() {
        return belongWarrant;
    }

    public void setBelongWarrant(String belongWarrant) {
        this.belongWarrant = belongWarrant == null ? null : belongWarrant.trim();
    }

    public String getLedLuminousPeople() {
        return ledLuminousPeople;
    }

    public void setLedLuminousPeople(String ledLuminousPeople) {
        this.ledLuminousPeople = ledLuminousPeople == null ? null : ledLuminousPeople.trim();
    }

    public String getSurveyPicture() {
        return surveyPicture;
    }

    public void setSurveyPicture(String surveyPicture) {
        this.surveyPicture = surveyPicture == null ? null : surveyPicture.trim();
    }

    public String getSurveyImage() {
        return surveyImage;
    }

    public void setSurveyImage(String surveyImage) {
        this.surveyImage = surveyImage == null ? null : surveyImage.trim();
    }

    public String getLocationPicture() {
        return locationPicture;
    }

    public void setLocationPicture(String locationPicture) {
        this.locationPicture = locationPicture == null ? null : locationPicture.trim();
    }

    public String getSurveyLocaltion() {
        return surveyLocaltion;
    }

    public void setSurveyLocaltion(String surveyLocaltion) {
        this.surveyLocaltion = surveyLocaltion == null ? null : surveyLocaltion.trim();
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