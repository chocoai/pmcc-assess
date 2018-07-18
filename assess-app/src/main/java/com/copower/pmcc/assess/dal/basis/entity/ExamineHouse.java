package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouse {
    private Integer id;

    private Integer declareId;

    private Integer examineType;

    private String houseNumber;

    private Integer floor;

    private Integer huxingId;

    private Integer newsHuxing;

    private Integer certUse;

    private Integer practicalUse;

    private String rightInterestsRestriction;

    private Integer useEnvironment;

    private String description;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getHuxingId() {
        return huxingId;
    }

    public void setHuxingId(Integer huxingId) {
        this.huxingId = huxingId;
    }

    public Integer getNewsHuxing() {
        return newsHuxing;
    }

    public void setNewsHuxing(Integer newsHuxing) {
        this.newsHuxing = newsHuxing;
    }

    public Integer getCertUse() {
        return certUse;
    }

    public void setCertUse(Integer certUse) {
        this.certUse = certUse;
    }

    public Integer getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(Integer practicalUse) {
        this.practicalUse = practicalUse;
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