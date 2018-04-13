package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class ProjectPlan {
    private Integer id;

    private Integer projectId;

    private String processInsId;

    private Integer workStageId;

    private Integer stageSort;

    private Integer categoryId;

    private String planName;

    private String planRemarks;

    private Date projectPlanStart;

    private Date projectPlanEnd;

    private String creator;

    private Date created;

    private String processInsIdApproval;

    private String status;

    private String approvalStatus;

    private Boolean bisRestart;

    private Date finishDate;

    private String projectStatus;

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

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Integer getWorkStageId() {
        return workStageId;
    }

    public void setWorkStageId(Integer workStageId) {
        this.workStageId = workStageId;
    }

    public Integer getStageSort() {
        return stageSort;
    }

    public void setStageSort(Integer stageSort) {
        this.stageSort = stageSort;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getPlanRemarks() {
        return planRemarks;
    }

    public void setPlanRemarks(String planRemarks) {
        this.planRemarks = planRemarks == null ? null : planRemarks.trim();
    }

    public Date getProjectPlanStart() {
        return projectPlanStart;
    }

    public void setProjectPlanStart(Date projectPlanStart) {
        this.projectPlanStart = projectPlanStart;
    }

    public Date getProjectPlanEnd() {
        return projectPlanEnd;
    }

    public void setProjectPlanEnd(Date projectPlanEnd) {
        this.projectPlanEnd = projectPlanEnd;
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

    public String getProcessInsIdApproval() {
        return processInsIdApproval;
    }

    public void setProcessInsIdApproval(String processInsIdApproval) {
        this.processInsIdApproval = processInsIdApproval == null ? null : processInsIdApproval.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus == null ? null : approvalStatus.trim();
    }

    public Boolean getBisRestart() {
        return bisRestart;
    }

    public void setBisRestart(Boolean bisRestart) {
        this.bisRestart = bisRestart;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }
}