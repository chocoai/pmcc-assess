package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SchemeReimbursementItem {
    private Integer id;

    private String name;

    private Integer projectId;

    private Integer masterId;

    private Integer inventoryRightRecordId;

    private Integer judgeObjectId;

    private String judgeObjectNumber;

    private Integer standardJudgeId;

    private Integer planDetailsId;

    private BigDecimal notSetUpUnitPrice;

    private BigDecimal notSetUpTotalPrice;

    private BigDecimal knowTotalPrice;

    private BigDecimal mortgagedTotalPrice;

    private BigDecimal owedTotalPrice;

    private BigDecimal otherTotalPrice;

    private BigDecimal mortgageUnitPrice;

    private BigDecimal mortgageTotalPrice;

    private Integer sorting;

    private Boolean bisEnable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getInventoryRightRecordId() {
        return inventoryRightRecordId;
    }

    public void setInventoryRightRecordId(Integer inventoryRightRecordId) {
        this.inventoryRightRecordId = inventoryRightRecordId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public String getJudgeObjectNumber() {
        return judgeObjectNumber;
    }

    public void setJudgeObjectNumber(String judgeObjectNumber) {
        this.judgeObjectNumber = judgeObjectNumber == null ? null : judgeObjectNumber.trim();
    }

    public Integer getStandardJudgeId() {
        return standardJudgeId;
    }

    public void setStandardJudgeId(Integer standardJudgeId) {
        this.standardJudgeId = standardJudgeId;
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

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
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