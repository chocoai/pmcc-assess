package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostConstruction {
    private Integer id;

    private BigDecimal constructionAssessmentPriceCorrecting;

    private String constructionAssessmentValue;

    private String investmentProfit;

    private String interestInvestment;

    private String constructionSubtotal;

    private String landGetCostTotal;

    private String jsonContent;

    private Integer engineeringId;

    private Integer pid;

    private String creator;

    private Date gmtModified;

    private Date gmtCreated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getConstructionAssessmentPriceCorrecting() {
        return constructionAssessmentPriceCorrecting;
    }

    public void setConstructionAssessmentPriceCorrecting(BigDecimal constructionAssessmentPriceCorrecting) {
        this.constructionAssessmentPriceCorrecting = constructionAssessmentPriceCorrecting;
    }

    public String getConstructionAssessmentValue() {
        return constructionAssessmentValue;
    }

    public void setConstructionAssessmentValue(String constructionAssessmentValue) {
        this.constructionAssessmentValue = constructionAssessmentValue == null ? null : constructionAssessmentValue.trim();
    }

    public String getInvestmentProfit() {
        return investmentProfit;
    }

    public void setInvestmentProfit(String investmentProfit) {
        this.investmentProfit = investmentProfit == null ? null : investmentProfit.trim();
    }

    public String getInterestInvestment() {
        return interestInvestment;
    }

    public void setInterestInvestment(String interestInvestment) {
        this.interestInvestment = interestInvestment == null ? null : interestInvestment.trim();
    }

    public String getConstructionSubtotal() {
        return constructionSubtotal;
    }

    public void setConstructionSubtotal(String constructionSubtotal) {
        this.constructionSubtotal = constructionSubtotal == null ? null : constructionSubtotal.trim();
    }

    public String getLandGetCostTotal() {
        return landGetCostTotal;
    }

    public void setLandGetCostTotal(String landGetCostTotal) {
        this.landGetCostTotal = landGetCostTotal == null ? null : landGetCostTotal.trim();
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public Integer getEngineeringId() {
        return engineeringId;
    }

    public void setEngineeringId(Integer engineeringId) {
        this.engineeringId = engineeringId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}