package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CsrProjectInfo {
    private Integer id;

    private Integer projectClassId;

    private Integer projectTypeId;

    private Integer projectCategoryId;

    private String processInsId;

    private String name;

    private Integer customerType;

    private Integer entrustmentUnitId;

    private String entrustmentUnitName;

    private Integer entrustPurpose;

    private Date valuationDate;

    private String distributionUser;

    private String remark;

    private Integer startRowNumber;

    private String status;

    private String projectStatus;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getEntrustmentUnitId() {
        return entrustmentUnitId;
    }

    public void setEntrustmentUnitId(Integer entrustmentUnitId) {
        this.entrustmentUnitId = entrustmentUnitId;
    }

    public String getEntrustmentUnitName() {
        return entrustmentUnitName;
    }

    public void setEntrustmentUnitName(String entrustmentUnitName) {
        this.entrustmentUnitName = entrustmentUnitName == null ? null : entrustmentUnitName.trim();
    }

    public Integer getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(Integer entrustPurpose) {
        this.entrustPurpose = entrustPurpose;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getDistributionUser() {
        return distributionUser;
    }

    public void setDistributionUser(String distributionUser) {
        this.distributionUser = distributionUser == null ? null : distributionUser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStartRowNumber() {
        return startRowNumber;
    }

    public void setStartRowNumber(Integer startRowNumber) {
        this.startRowNumber = startRowNumber;
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