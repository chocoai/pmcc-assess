package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class SurveyAssetOtherTemplate {
    private Integer id;

    private Integer projectId;

    private Integer planDetailId;

    private Integer otherRightsRegistrar;

    private Integer rightHander;

    private String registerArea;

    private String actualArea;

    private String registerPurpose;

    private String actualPurpose;

    private Date registerDate;

    private Date dueDate;

    private Date exerciseDate;

    private Date predictDueDate;

    private String spareField;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer pid;

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

    public Integer getOtherRightsRegistrar() {
        return otherRightsRegistrar;
    }

    public void setOtherRightsRegistrar(Integer otherRightsRegistrar) {
        this.otherRightsRegistrar = otherRightsRegistrar;
    }

    public Integer getRightHander() {
        return rightHander;
    }

    public void setRightHander(Integer rightHander) {
        this.rightHander = rightHander;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea == null ? null : registerArea.trim();
    }

    public String getActualArea() {
        return actualArea;
    }

    public void setActualArea(String actualArea) {
        this.actualArea = actualArea == null ? null : actualArea.trim();
    }

    public String getRegisterPurpose() {
        return registerPurpose;
    }

    public void setRegisterPurpose(String registerPurpose) {
        this.registerPurpose = registerPurpose == null ? null : registerPurpose.trim();
    }

    public String getActualPurpose() {
        return actualPurpose;
    }

    public void setActualPurpose(String actualPurpose) {
        this.actualPurpose = actualPurpose == null ? null : actualPurpose.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public Date getPredictDueDate() {
        return predictDueDate;
    }

    public void setPredictDueDate(Date predictDueDate) {
        this.predictDueDate = predictDueDate;
    }

    public String getSpareField() {
        return spareField;
    }

    public void setSpareField(String spareField) {
        this.spareField = spareField == null ? null : spareField.trim();
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}