package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectReviewScore {
    private Integer id;

    private Integer projectId;

    private String projectName;

    private BigDecimal contractNegotiation;

    private String contractNegotiationExplain;

    private BigDecimal customerActivities;

    private String customerActivitiesExplain;

    private BigDecimal workDivision;

    private String workDivisionExplain;

    private BigDecimal invoiceCollection;

    private String invoiceCollectionExplain;

    private String remark;

    private String processInsId;

    private String status;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public BigDecimal getContractNegotiation() {
        return contractNegotiation;
    }

    public void setContractNegotiation(BigDecimal contractNegotiation) {
        this.contractNegotiation = contractNegotiation;
    }

    public String getContractNegotiationExplain() {
        return contractNegotiationExplain;
    }

    public void setContractNegotiationExplain(String contractNegotiationExplain) {
        this.contractNegotiationExplain = contractNegotiationExplain == null ? null : contractNegotiationExplain.trim();
    }

    public BigDecimal getCustomerActivities() {
        return customerActivities;
    }

    public void setCustomerActivities(BigDecimal customerActivities) {
        this.customerActivities = customerActivities;
    }

    public String getCustomerActivitiesExplain() {
        return customerActivitiesExplain;
    }

    public void setCustomerActivitiesExplain(String customerActivitiesExplain) {
        this.customerActivitiesExplain = customerActivitiesExplain == null ? null : customerActivitiesExplain.trim();
    }

    public BigDecimal getWorkDivision() {
        return workDivision;
    }

    public void setWorkDivision(BigDecimal workDivision) {
        this.workDivision = workDivision;
    }

    public String getWorkDivisionExplain() {
        return workDivisionExplain;
    }

    public void setWorkDivisionExplain(String workDivisionExplain) {
        this.workDivisionExplain = workDivisionExplain == null ? null : workDivisionExplain.trim();
    }

    public BigDecimal getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(BigDecimal invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    public String getInvoiceCollectionExplain() {
        return invoiceCollectionExplain;
    }

    public void setInvoiceCollectionExplain(String invoiceCollectionExplain) {
        this.invoiceCollectionExplain = invoiceCollectionExplain == null ? null : invoiceCollectionExplain.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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