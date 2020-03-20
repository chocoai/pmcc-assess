package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectXlxCommission {
    private Integer id;

    private Integer projectId;

    private String processInsId;

    private String invoiceNumber;

    private BigDecimal invoiceTotalMoney;

    private BigDecimal projectMoney;

    private String paymentConfirmation;

    private String reportNumber;

    private String notCommissionConfirm;

    private String notRebateConfirm;

    private Date confirmTime;

    private Date reportBindDate;

    private Date pigeonholeDate;

    private String overduePigeonhole;

    private String pigeonholeConfirm;

    private String status;

    private String creator;

    private Date created;

    private Date modified;

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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber == null ? null : invoiceNumber.trim();
    }

    public BigDecimal getInvoiceTotalMoney() {
        return invoiceTotalMoney;
    }

    public void setInvoiceTotalMoney(BigDecimal invoiceTotalMoney) {
        this.invoiceTotalMoney = invoiceTotalMoney;
    }

    public BigDecimal getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(BigDecimal projectMoney) {
        this.projectMoney = projectMoney;
    }

    public String getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(String paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation == null ? null : paymentConfirmation.trim();
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber == null ? null : reportNumber.trim();
    }

    public String getNotCommissionConfirm() {
        return notCommissionConfirm;
    }

    public void setNotCommissionConfirm(String notCommissionConfirm) {
        this.notCommissionConfirm = notCommissionConfirm == null ? null : notCommissionConfirm.trim();
    }

    public String getNotRebateConfirm() {
        return notRebateConfirm;
    }

    public void setNotRebateConfirm(String notRebateConfirm) {
        this.notRebateConfirm = notRebateConfirm == null ? null : notRebateConfirm.trim();
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getReportBindDate() {
        return reportBindDate;
    }

    public void setReportBindDate(Date reportBindDate) {
        this.reportBindDate = reportBindDate;
    }

    public Date getPigeonholeDate() {
        return pigeonholeDate;
    }

    public void setPigeonholeDate(Date pigeonholeDate) {
        this.pigeonholeDate = pigeonholeDate;
    }

    public String getOverduePigeonhole() {
        return overduePigeonhole;
    }

    public void setOverduePigeonhole(String overduePigeonhole) {
        this.overduePigeonhole = overduePigeonhole == null ? null : overduePigeonhole.trim();
    }

    public String getPigeonholeConfirm() {
        return pigeonholeConfirm;
    }

    public void setPigeonholeConfirm(String pigeonholeConfirm) {
        this.pigeonholeConfirm = pigeonholeConfirm == null ? null : pigeonholeConfirm.trim();
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

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}