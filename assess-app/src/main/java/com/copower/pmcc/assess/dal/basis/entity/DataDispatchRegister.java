package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataDispatchRegister {
    private Integer id;

    private String dispatchDate;

    private String dispatchNumber;

    private String businessType;

    private String entrustPurpose;

    private String projectName;

    private String clientCompany;

    private String entrustUnit;

    private String assessArea;

    private String assessAmount;

    private String sendNumber;

    private String remainNumber;

    private String operator;

    private String approver;

    private String depositPerson;

    private String redactPerson;

    private String pigeonholeDate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate == null ? null : dispatchDate.trim();
    }

    public String getDispatchNumber() {
        return dispatchNumber;
    }

    public void setDispatchNumber(String dispatchNumber) {
        this.dispatchNumber = dispatchNumber == null ? null : dispatchNumber.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(String entrustPurpose) {
        this.entrustPurpose = entrustPurpose == null ? null : entrustPurpose.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany == null ? null : clientCompany.trim();
    }

    public String getEntrustUnit() {
        return entrustUnit;
    }

    public void setEntrustUnit(String entrustUnit) {
        this.entrustUnit = entrustUnit == null ? null : entrustUnit.trim();
    }

    public String getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(String assessArea) {
        this.assessArea = assessArea == null ? null : assessArea.trim();
    }

    public String getAssessAmount() {
        return assessAmount;
    }

    public void setAssessAmount(String assessAmount) {
        this.assessAmount = assessAmount == null ? null : assessAmount.trim();
    }

    public String getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber == null ? null : sendNumber.trim();
    }

    public String getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(String remainNumber) {
        this.remainNumber = remainNumber == null ? null : remainNumber.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getDepositPerson() {
        return depositPerson;
    }

    public void setDepositPerson(String depositPerson) {
        this.depositPerson = depositPerson == null ? null : depositPerson.trim();
    }

    public String getRedactPerson() {
        return redactPerson;
    }

    public void setRedactPerson(String redactPerson) {
        this.redactPerson = redactPerson == null ? null : redactPerson.trim();
    }

    public String getPigeonholeDate() {
        return pigeonholeDate;
    }

    public void setPigeonholeDate(String pigeonholeDate) {
        this.pigeonholeDate = pigeonholeDate == null ? null : pigeonholeDate.trim();
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