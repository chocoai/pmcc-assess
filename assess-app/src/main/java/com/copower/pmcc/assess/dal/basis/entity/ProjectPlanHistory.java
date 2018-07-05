package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ProjectPlanHistory {
    private Integer id;

    private Integer projectId;

    private Integer planId;

    private String projectPhaseName;

    private Date beforePlanStart;

    private Date beforePlanEnd;

    private String beforePlanRemarks;

    private Date afterPlanStart;

    private Date afterPlanEnd;

    private String afterPlanRemarks;

    private String processInsId;

    private String status;

    private String creator;

    private Date created;

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

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getProjectPhaseName() {
        return projectPhaseName;
    }

    public void setProjectPhaseName(String projectPhaseName) {
        this.projectPhaseName = projectPhaseName == null ? null : projectPhaseName.trim();
    }

    public Date getBeforePlanStart() {
        return beforePlanStart;
    }

    public void setBeforePlanStart(Date beforePlanStart) {
        this.beforePlanStart = beforePlanStart;
    }

    public Date getBeforePlanEnd() {
        return beforePlanEnd;
    }

    public void setBeforePlanEnd(Date beforePlanEnd) {
        this.beforePlanEnd = beforePlanEnd;
    }

    public String getBeforePlanRemarks() {
        return beforePlanRemarks;
    }

    public void setBeforePlanRemarks(String beforePlanRemarks) {
        this.beforePlanRemarks = beforePlanRemarks == null ? null : beforePlanRemarks.trim();
    }

    public Date getAfterPlanStart() {
        return afterPlanStart;
    }

    public void setAfterPlanStart(Date afterPlanStart) {
        this.afterPlanStart = afterPlanStart;
    }

    public Date getAfterPlanEnd() {
        return afterPlanEnd;
    }

    public void setAfterPlanEnd(Date afterPlanEnd) {
        this.afterPlanEnd = afterPlanEnd;
    }

    public String getAfterPlanRemarks() {
        return afterPlanRemarks;
    }

    public void setAfterPlanRemarks(String afterPlanRemarks) {
        this.afterPlanRemarks = afterPlanRemarks == null ? null : afterPlanRemarks.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}