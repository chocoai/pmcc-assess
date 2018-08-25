package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectPlanDetails {
    private Integer id;

    private Integer projectWorkStageId;

    private Integer planId;

    private Integer projectId;

    private Integer projectPhaseId;

    private Integer projectPhaseDetailsId;

    private String projectPhaseName;

    private Date planStartDate;

    private Date planEndDate;

    private BigDecimal planHours;

    private String planRemarks;

    private String executeUserAccount;

    private Integer executeDepartmentId;

    private Boolean bisEnable;

    private BigDecimal proportion;

    private Integer sorting;

    private Integer pid;

    private Integer firstPid;

    private Boolean bisStart;

    private String processInsId;

    private Date taskSubmitTime;

    private String taskRemarks;

    private BigDecimal actualHours;

    private String status;

    private Integer returnDetailsId;

    private String returnProcessInsId;

    private String returnDetailsReason;

    private Boolean bisNew;

    private Boolean bisLastLayer;

    private Integer companyNature;

    private Integer declareFormId;

    private Integer declareRecordId;

    private Integer areaGroupId;

    private Integer groupNumber;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectWorkStageId() {
        return projectWorkStageId;
    }

    public void setProjectWorkStageId(Integer projectWorkStageId) {
        this.projectWorkStageId = projectWorkStageId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public String getProjectPhaseName() {
        return projectPhaseName;
    }

    public void setProjectPhaseName(String projectPhaseName) {
        this.projectPhaseName = projectPhaseName == null ? null : projectPhaseName.trim();
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

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFirstPid() {
        return firstPid;
    }

    public void setFirstPid(Integer firstPid) {
        this.firstPid = firstPid;
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

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getReturnDetailsId() {
        return returnDetailsId;
    }

    public void setReturnDetailsId(Integer returnDetailsId) {
        this.returnDetailsId = returnDetailsId;
    }

    public String getReturnProcessInsId() {
        return returnProcessInsId;
    }

    public void setReturnProcessInsId(String returnProcessInsId) {
        this.returnProcessInsId = returnProcessInsId == null ? null : returnProcessInsId.trim();
    }

    public String getReturnDetailsReason() {
        return returnDetailsReason;
    }

    public void setReturnDetailsReason(String returnDetailsReason) {
        this.returnDetailsReason = returnDetailsReason == null ? null : returnDetailsReason.trim();
    }

    public Boolean getBisNew() {
        return bisNew;
    }

    public void setBisNew(Boolean bisNew) {
        this.bisNew = bisNew;
    }

    public Boolean getBisLastLayer() {
        return bisLastLayer;
    }

    public void setBisLastLayer(Boolean bisLastLayer) {
        this.bisLastLayer = bisLastLayer;
    }

    public Integer getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(Integer companyNature) {
        this.companyNature = companyNature;
    }

    public Integer getDeclareFormId() {
        return declareFormId;
    }

    public void setDeclareFormId(Integer declareFormId) {
        this.declareFormId = declareFormId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
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