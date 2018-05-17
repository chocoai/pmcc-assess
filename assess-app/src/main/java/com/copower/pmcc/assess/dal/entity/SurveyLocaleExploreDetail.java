package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class SurveyLocaleExploreDetail {
    private Integer id;

    private Integer planDetailsId;

    private Integer surveySheetNumber;

    private String surveyPeople;

    private Date surveyTime;

    private String belongWarrant;

    private String ledLuminousPeople;

    private String surveyImage;

    private String surveyVideo;

    private String locationPicture;

    private String surveyLocaltion;

    private String correlationCard;

    private String houseName;

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

    public Integer getSurveySheetNumber() {
        return surveySheetNumber;
    }

    public void setSurveySheetNumber(Integer surveySheetNumber) {
        this.surveySheetNumber = surveySheetNumber;
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

    public String getSurveyImage() {
        return surveyImage;
    }

    public void setSurveyImage(String surveyImage) {
        this.surveyImage = surveyImage == null ? null : surveyImage.trim();
    }

    public String getSurveyVideo() {
        return surveyVideo;
    }

    public void setSurveyVideo(String surveyVideo) {
        this.surveyVideo = surveyVideo == null ? null : surveyVideo.trim();
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

    public String getCorrelationCard() {
        return correlationCard;
    }

    public void setCorrelationCard(String correlationCard) {
        this.correlationCard = correlationCard == null ? null : correlationCard.trim();
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
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