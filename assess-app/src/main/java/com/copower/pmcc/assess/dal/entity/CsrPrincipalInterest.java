package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CsrPrincipalInterest {
    private Integer id;

    private Integer csrProjectId;

    private String borrowerId;

    private String analysisDatumDate;

    private String principal;

    private String interest;

    private String principalInterestTotal;

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

    public String getAnalysisDatumDate() {
        return analysisDatumDate;
    }

    public void setAnalysisDatumDate(String analysisDatumDate) {
        this.analysisDatumDate = analysisDatumDate == null ? null : analysisDatumDate.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public String getPrincipalInterestTotal() {
        return principalInterestTotal;
    }

    public void setPrincipalInterestTotal(String principalInterestTotal) {
        this.principalInterestTotal = principalInterestTotal == null ? null : principalInterestTotal.trim();
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