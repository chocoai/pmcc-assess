package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareApply {
    private Integer id;

    private Integer projectId;

    private Integer planDetailsId;

    private String processInsId;

    private String applyType;

    private String client;

    private Date dateLimit;

    private String status;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String name;

    private BigDecimal assessArea;

    private BigDecimal assessMoney;

    private String assessOrganization;

    private String remark;

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

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
    }

    public Date getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Date dateLimit) {
        this.dateLimit = dateLimit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    public BigDecimal getAssessMoney() {
        return assessMoney;
    }

    public void setAssessMoney(BigDecimal assessMoney) {
        this.assessMoney = assessMoney;
    }

    public String getAssessOrganization() {
        return assessOrganization;
    }

    public void setAssessOrganization(String assessOrganization) {
        this.assessOrganization = assessOrganization == null ? null : assessOrganization.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}