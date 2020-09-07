package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectPlanDetails {
    private Integer id;

    private Integer pid;

    private String projectPhaseName;

    private Integer projectId;

    private Integer planId;

    private Integer projectWorkStageId;

    private Integer projectPhaseId;

    private Integer projectPhaseDetailsId;

    private Date planStartDate;

    private Date planEndDate;

    private BigDecimal planHours;

    private String planRemarks;

    private String executeUserAccount;

    private Integer executeDepartmentId;

    private Boolean bisEnable;

    private Integer sorting;

    private Boolean bisStart;

    private String processInsId;

    private Date taskSubmitTime;

    private String taskRemarks;

    private String submitUser;

    private String submitUserAll;

    private BigDecimal actualHours;

    private Integer areaId;

    private Integer judgeObjectId;

    private Integer declareRecordId;

    private String status;

    private Boolean bisRestart;

    private Boolean bisLastLayer;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProjectPhaseName() {
        return projectPhaseName;
    }

    public void setProjectPhaseName(String projectPhaseName) {
        this.projectPhaseName = projectPhaseName == null ? null : projectPhaseName.trim();
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

    public Integer getProjectWorkStageId() {
        return projectWorkStageId;
    }

    public void setProjectWorkStageId(Integer projectWorkStageId) {
        this.projectWorkStageId = projectWorkStageId;
    }

    public Integer getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Integer projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
    }

    public Integer getProjectPhaseDetailsId() {
        return projectPhaseDetailsId;
    }

    public void setProjectPhaseDetailsId(Integer projectPhaseDetailsId) {
        this.projectPhaseDetailsId = projectPhaseDetailsId;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public BigDecimal getPlanHours() {
        return planHours;
    }

    public void setPlanHours(BigDecimal planHours) {
        this.planHours = planHours;
    }

    public String getPlanRemarks() {
        return planRemarks;
    }

    public void setPlanRemarks(String planRemarks) {
        this.planRemarks = planRemarks == null ? null : planRemarks.trim();
    }

    public String getExecuteUserAccount() {
        return executeUserAccount;
    }

    public void setExecuteUserAccount(String executeUserAccount) {
        this.executeUserAccount = executeUserAccount == null ? null : executeUserAccount.trim();
    }

    public Integer getExecuteDepartmentId() {
        return executeDepartmentId;
    }

    public void setExecuteDepartmentId(Integer executeDepartmentId) {
        this.executeDepartmentId = executeDepartmentId;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean getBisStart() {
        return bisStart;
    }

    public void setBisStart(Boolean bisStart) {
        this.bisStart = bisStart;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public Date getTaskSubmitTime() {
        return taskSubmitTime;
    }

    public void setTaskSubmitTime(Date taskSubmitTime) {
        this.taskSubmitTime = taskSubmitTime;
    }

    public String getTaskRemarks() {
        return taskRemarks;
    }

    public void setTaskRemarks(String taskRemarks) {
        this.taskRemarks = taskRemarks == null ? null : taskRemarks.trim();
    }

    public String getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser == null ? null : submitUser.trim();
    }

    public String getSubmitUserAll() {
        return submitUserAll;
    }

    public void setSubmitUserAll(String submitUserAll) {
        this.submitUserAll = submitUserAll == null ? null : submitUserAll.trim();
    }

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getBisRestart() {
        return bisRestart;
    }

    public void setBisRestart(Boolean bisRestart) {
        this.bisRestart = bisRestart;
    }

    public Boolean getBisLastLayer() {
        return bisLastLayer;
    }

    public void setBisLastLayer(Boolean bisLastLayer) {
        this.bisLastLayer = bisLastLayer;
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