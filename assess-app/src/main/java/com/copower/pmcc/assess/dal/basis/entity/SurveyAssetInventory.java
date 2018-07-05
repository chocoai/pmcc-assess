package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInventory {
    private Integer id;

    private Integer projectId;

    private Integer planDetailId;

    private String processInsId;

    private Integer declareRecordId;

    private String evaluator;

    private Date checkDate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String defaultLocaltion;

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

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator == null ? null : evaluator.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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

    public String getDefaultLocaltion() {
        return defaultLocaltion;
    }

    public void setDefaultLocaltion(String defaultLocaltion) {
        this.defaultLocaltion = defaultLocaltion == null ? null : defaultLocaltion.trim();
    }
}