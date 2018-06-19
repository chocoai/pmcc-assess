package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CsrContract {
    private Integer id;

    private Integer csrProjectId;

    private String borrowerId;

    private String contractNumber;

    private String loanTime;

    private String amount;

    private String variety;

    private String term;

    private String guaranteeMethod;

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

    public Integer getCsrProjectId() {
        return csrProjectId;
    }

    public void setCsrProjectId(Integer csrProjectId) {
        this.csrProjectId = csrProjectId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId == null ? null : borrowerId.trim();
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime == null ? null : loanTime.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety == null ? null : variety.trim();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    public String getGuaranteeMethod() {
        return guaranteeMethod;
    }

    public void setGuaranteeMethod(String guaranteeMethod) {
        this.guaranteeMethod = guaranteeMethod == null ? null : guaranteeMethod.trim();
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