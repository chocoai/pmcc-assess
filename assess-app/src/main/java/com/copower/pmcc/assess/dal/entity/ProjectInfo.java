package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class ProjectInfo {
    private Integer id;

    private Integer urgency;

    private String projectName;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer projectClassId;

    private Integer entrustPurpose;

    private Integer valueType;

    private Integer departmentId;

    private String remarks;

    private Date completeDatePlan;

    private Date completeDateActual;

    private Date completeDateStart;

    private String processInsId;

    private String status;

    private String projectStatus;

    private Integer publicProjectId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String attachmentProjectInfoId;

    private Integer consignorId;

    private Integer unitInformationId;

    private Integer possessorId;

    private Integer projectMemberId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getProjectClassId() {
        return projectClassId;
    }

    public void setProjectClassId(Integer projectClassId) {
        this.projectClassId = projectClassId;
    }

    public Integer getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(Integer entrustPurpose) {
        this.entrustPurpose = entrustPurpose;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCompleteDatePlan() {
        return completeDatePlan;
    }

    public void setCompleteDatePlan(Date completeDatePlan) {
        this.completeDatePlan = completeDatePlan;
    }

    public Date getCompleteDateActual() {
        return completeDateActual;
    }

    public void setCompleteDateActual(Date completeDateActual) {
        this.completeDateActual = completeDateActual;
    }

    public Date getCompleteDateStart() {
        return completeDateStart;
    }

    public void setCompleteDateStart(Date completeDateStart) {
        this.completeDateStart = completeDateStart;
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

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public Integer getPublicProjectId() {
        return publicProjectId;
    }

    public void setPublicProjectId(Integer publicProjectId) {
        this.publicProjectId = publicProjectId;
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

    public String getAttachmentProjectInfoId() {
        return attachmentProjectInfoId;
    }

    public void setAttachmentProjectInfoId(String attachmentProjectInfoId) {
        this.attachmentProjectInfoId = attachmentProjectInfoId == null ? null : attachmentProjectInfoId.trim();
    }

    public Integer getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(Integer consignorId) {
        this.consignorId = consignorId;
    }

    public Integer getUnitInformationId() {
        return unitInformationId;
    }

    public void setUnitInformationId(Integer unitInformationId) {
        this.unitInformationId = unitInformationId;
    }

    public Integer getPossessorId() {
        return possessorId;
    }

    public void setPossessorId(Integer possessorId) {
        this.possessorId = possessorId;
    }

    public Integer getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(Integer projectMemberId) {
        this.projectMemberId = projectMemberId;
    }
}