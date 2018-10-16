package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostBuilding {
    private Integer id;

    private BigDecimal assessPrice;

    private String replacementValue;

    private String investmentProfit;

    private String interestInvestment;

    private String constructionCost;

    private String jsonContent;

    private Integer engineeringId;

    private Integer pid;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public String getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(String replacementValue) {
        this.replacementValue = replacementValue == null ? null : replacementValue.trim();
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

    public String getConstructionCost() {
        return constructionCost;
    }

    public void setConstructionCost(String constructionCost) {
        this.constructionCost = constructionCost == null ? null : constructionCost.trim();
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