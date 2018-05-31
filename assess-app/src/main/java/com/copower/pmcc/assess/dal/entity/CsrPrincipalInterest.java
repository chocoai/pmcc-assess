package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class CsrPrincipalInterest {
    private Integer id;

    private Integer borrowerId;

    private Date analysisDatumDate;

    private String principal;

    private String interest;

    private String principalInterestTotal;

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

    public Date getAnalysisDatumDate() {
        return analysisDatumDate;
    }

    public void setAnalysisDatumDate(Date analysisDatumDate) {
        this.analysisDatumDate = analysisDatumDate;
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