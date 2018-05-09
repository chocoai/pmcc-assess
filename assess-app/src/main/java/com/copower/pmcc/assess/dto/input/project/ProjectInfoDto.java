package com.copower.pmcc.assess.dto.input.project;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 17:34
 * 2018-05-09 进行过一次添加修改
 */
public class ProjectInfoDto {
    public static String ATTACHMENTPROJECTINFOID = "attachmentProjectInfoId"; //附件字段
    private String userAccountManager;//项目经理
    private String userAccountMember;//项目成员 或者说下级成员
    private Integer urgency;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer entrustPurpose;

    private Integer valueType;

    private String processInsId;

    private String status;

    private String projectStatus;

    private Integer publicProjectId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String attachmentProjectInfoId;
    private Integer id;

    private String projectName;

    private Integer projectClassId;

    private Integer projectTypeId;

    private Integer projectCategoryId;

    private Integer departmentId;

    private String remarks;

    private Integer customerId;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date completeDatePlan;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date completeDateActual;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date completeDateStart;

    private String departmentIdName;

    private Integer boxId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectClassId() {
        return projectClassId;
    }

    public void setProjectClassId(Integer projectClassId) {
        this.projectClassId = projectClassId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
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
        this.remarks = remarks;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
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

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
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
        this.creator = creator;
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
        this.attachmentProjectInfoId = attachmentProjectInfoId;
    }

    public String getUserAccountManager() {
        return userAccountManager;
    }

    public void setUserAccountManager(String userAccountManager) {
        this.userAccountManager = userAccountManager;
    }

    public String getUserAccountMember() {
        return userAccountMember;
    }

    public void setUserAccountMember(String userAccountMember) {
        this.userAccountMember = userAccountMember;
    }
}
