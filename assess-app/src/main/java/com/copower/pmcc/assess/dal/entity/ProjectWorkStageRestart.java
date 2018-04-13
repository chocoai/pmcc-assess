package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class ProjectWorkStageRestart {
    private Integer id;

    private Integer projectId;

    private String projectName;

    private Integer projectRestartStageId;

    private String projectRestartStageName;

    private Integer projectPlanOldId;

    private Integer projectPlanId;

    private String projectThisWorkStage;

    private String restartReason;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getProjectRestartStageId() {
        return projectRestartStageId;
    }

    public void setProjectRestartStageId(Integer projectRestartStageId) {
        this.projectRestartStageId = projectRestartStageId;
    }

    public String getProjectRestartStageName() {
        return projectRestartStageName;
    }

    public void setProjectRestartStageName(String projectRestartStageName) {
        this.projectRestartStageName = projectRestartStageName == null ? null : projectRestartStageName.trim();
    }

    public Integer getProjectPlanOldId() {
        return projectPlanOldId;
    }

    public void setProjectPlanOldId(Integer projectPlanOldId) {
        this.projectPlanOldId = projectPlanOldId;
    }

    public Integer getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(Integer projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

    public String getProjectThisWorkStage() {
        return projectThisWorkStage;
    }

    public void setProjectThisWorkStage(String projectThisWorkStage) {
        this.projectThisWorkStage = projectThisWorkStage == null ? null : projectThisWorkStage.trim();
    }

    public String getRestartReason() {
        return restartReason;
    }

    public void setRestartReason(String restartReason) {
        this.restartReason = restartReason == null ? null : restartReason.trim();
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