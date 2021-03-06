package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicMatchingEducation {
    private Integer id;

    private Integer estateId;

    private String schoolName;

    private Integer schoolNature;

    private Integer schoolGradation;

    private String schoolLevel;

    private Integer distance;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Integer getSchoolNature() {
        return schoolNature;
    }

    public void setSchoolNature(Integer schoolNature) {
        this.schoolNature = schoolNature;
    }

    public Integer getSchoolGradation() {
        return schoolGradation;
    }

    public void setSchoolGradation(Integer schoolGradation) {
        this.schoolGradation = schoolGradation;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel == null ? null : schoolLevel.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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