package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SchemeReimbursement {
    private Integer id;

    private Integer projectId;

    private Integer judgeObjectId;

    private Integer planDetailsId;

    private String processInsId;

    private BigDecimal notSetUpUnitPrice;

    private BigDecimal notSetUpTotalPrice;

    private BigDecimal knowTotalPrice;

    private BigDecimal mortgagedTotalPrice;

    private BigDecimal owedTotalPrice;

    private BigDecimal otherTotalPrice;

    private BigDecimal mortgageUnitPrice;

    private BigDecimal mortgageTotalPrice;

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

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
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

    public BigDecimal getNotSetUpUnitPrice() {
        return notSetUpUnitPrice;
    }

    public void setNotSetUpUnitPrice(BigDecimal notSetUpUnitPrice) {
        this.notSetUpUnitPrice = notSetUpUnitPrice;
    }

    public BigDecimal getNotSetUpTotalPrice() {
        return notSetUpTotalPrice;
    }

    public void setNotSetUpTotalPrice(BigDecimal notSetUpTotalPrice) {
        this.notSetUpTotalPrice = notSetUpTotalPrice;
    }

    public BigDecimal getKnowTotalPrice() {
        return knowTotalPrice;
    }

    public void setKnowTotalPrice(BigDecimal knowTotalPrice) {
        this.knowTotalPrice = knowTotalPrice;
    }

    public BigDecimal getMortgagedTotalPrice() {
        return mortgagedTotalPrice;
    }

    public void setMortgagedTotalPrice(BigDecimal mortgagedTotalPrice) {
        this.mortgagedTotalPrice = mortgagedTotalPrice;
    }

    public BigDecimal getOwedTotalPrice() {
        return owedTotalPrice;
    }

    public void setOwedTotalPrice(BigDecimal owedTotalPrice) {
        this.owedTotalPrice = owedTotalPrice;
    }

    public BigDecimal getOtherTotalPrice() {
        return otherTotalPrice;
    }

    public void setOtherTotalPrice(BigDecimal otherTotalPrice) {
        this.otherTotalPrice = otherTotalPrice;
    }

    public BigDecimal getMortgageUnitPrice() {
        return mortgageUnitPrice;
    }

    public void setMortgageUnitPrice(BigDecimal mortgageUnitPrice) {
        this.mortgageUnitPrice = mortgageUnitPrice;
    }

    public BigDecimal getMortgageTotalPrice() {
        return mortgageTotalPrice;
    }

    public void setMortgageTotalPrice(BigDecimal mortgageTotalPrice) {
        this.mortgageTotalPrice = mortgageTotalPrice;
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