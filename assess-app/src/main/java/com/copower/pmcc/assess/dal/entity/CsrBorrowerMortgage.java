package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CsrBorrowerMortgage {
    private Integer id;

    private Integer borrowerId;

    private String contractNumber;

    private String signData;

    private String mortgagor;

    private String mortgageCompany;

    private String loanAmount;

    private String mortgageAddress;

    private String landNature;

    private String landArea;

    private String houseNature;

    private String area;

    private String evaluationValue;

    private String principalBalance;

    private String mortgageStatus;

    private String bisSealUp;

    private Boolean bisImport;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData == null ? null : signData.trim();
    }

    public String getMortgagor() {
        return mortgagor;
    }

    public void setMortgagor(String mortgagor) {
        this.mortgagor = mortgagor == null ? null : mortgagor.trim();
    }

    public String getMortgageCompany() {
        return mortgageCompany;
    }

    public void setMortgageCompany(String mortgageCompany) {
        this.mortgageCompany = mortgageCompany == null ? null : mortgageCompany.trim();
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount == null ? null : loanAmount.trim();
    }

    public String getMortgageAddress() {
        return mortgageAddress;
    }

    public void setMortgageAddress(String mortgageAddress) {
        this.mortgageAddress = mortgageAddress == null ? null : mortgageAddress.trim();
    }

    public String getLandNature() {
        return landNature;
    }

    public void setLandNature(String landNature) {
        this.landNature = landNature == null ? null : landNature.trim();
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea == null ? null : landArea.trim();
    }

    public String getHouseNature() {
        return houseNature;
    }

    public void setHouseNature(String houseNature) {
        this.houseNature = houseNature == null ? null : houseNature.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(String evaluationValue) {
        this.evaluationValue = evaluationValue == null ? null : evaluationValue.trim();
    }

    public String getPrincipalBalance() {
        return principalBalance;
    }

    public void setPrincipalBalance(String principalBalance) {
        this.principalBalance = principalBalance == null ? null : principalBalance.trim();
    }

    public String getMortgageStatus() {
        return mortgageStatus;
    }

    public void setMortgageStatus(String mortgageStatus) {
        this.mortgageStatus = mortgageStatus == null ? null : mortgageStatus.trim();
    }

    public String getBisSealUp() {
        return bisSealUp;
    }

    public void setBisSealUp(String bisSealUp) {
        this.bisSealUp = bisSealUp == null ? null : bisSealUp.trim();
    }

    public Boolean getBisImport() {
        return bisImport;
    }

    public void setBisImport(Boolean bisImport) {
        this.bisImport = bisImport;
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