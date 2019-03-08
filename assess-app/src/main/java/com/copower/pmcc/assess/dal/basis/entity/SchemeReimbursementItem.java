package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;

public class SchemeReimbursementItem {
    private Integer id;

    private Integer projectId;

    private Integer judgeObjectId;

    private Integer planDetailsId;

    private BigDecimal notSetUpUnitPrice;

    private BigDecimal notSetUpTotalPrice;

    private BigDecimal knowTotalPrice;

    private BigDecimal mortgagedTotalPrice;

    private BigDecimal owedTotalPrice;

    private BigDecimal otherTotalPrice;

    private BigDecimal mortgageUnitPrice;

    private BigDecimal mortgageTotalPrice;

    private Integer masterId;

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

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
}