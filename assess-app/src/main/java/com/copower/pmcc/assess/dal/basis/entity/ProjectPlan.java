package com.copower.pmcc.assess.dal.basis.entity;

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

    private String processInsIdApproval;

    private String status;

    private String approvalStatus;

    private String restartReason;

    private Boolean bisRestart;

    private Boolean bisAutoComplete;

    private Date finishDate;

    private String projectStatus;

    private Integer specificGravity;

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

    public String getRestartReason() {
        return restartReason;
    }

    public void setRestartReason(String restartReason) {
        this.restartReason = restartReason == null ? null : restartReason.trim();
    }

    public Boolean getBisRestart() {
        return bisRestart;
    }

    public void setBisRestart(Boolean bisRestart) {
        this.bisRestart = bisRestart;
    }

    public Boolean getBisAutoComplete() {
        return bisAutoComplete;
    }

    public void setBisAutoComplete(Boolean bisAutoComplete) {
        this.bisAutoComplete = bisAutoComplete;
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

    public Integer getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(Integer specificGravity) {
        this.specificGravity = specificGravity;
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