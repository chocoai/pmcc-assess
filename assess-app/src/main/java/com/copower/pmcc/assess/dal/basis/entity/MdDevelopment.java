package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdDevelopment {
    private Integer id;

    private String name;

    private BigDecimal area;

    private BigDecimal price;

    private String type;

    private Integer planDetailsId;

    private BigDecimal constructionCostSubtotal;

    private BigDecimal interestInvestment;

    private BigDecimal investmentProfit;

    private BigDecimal assessPrice;

    private String headContent;

    private String content;

    private BigDecimal projectConstructionPeriod;

    private BigDecimal developedYear;

    private BigDecimal remainingDevelopmentYear;

    private Integer rewardRateId;

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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public BigDecimal getConstructionCostSubtotal() {
        return constructionCostSubtotal;
    }

    public void setConstructionCostSubtotal(BigDecimal constructionCostSubtotal) {
        this.constructionCostSubtotal = constructionCostSubtotal;
    }

    public BigDecimal getInterestInvestment() {
        return interestInvestment;
    }

    public void setInterestInvestment(BigDecimal interestInvestment) {
        this.interestInvestment = interestInvestment;
    }

    public BigDecimal getInvestmentProfit() {
        return investmentProfit;
    }

    public void setInvestmentProfit(BigDecimal investmentProfit) {
        this.investmentProfit = investmentProfit;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public String getHeadContent() {
        return headContent;
    }

    public void setHeadContent(String headContent) {
        this.headContent = headContent == null ? null : headContent.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public BigDecimal getProjectConstructionPeriod() {
        return projectConstructionPeriod;
    }

    public void setProjectConstructionPeriod(BigDecimal projectConstructionPeriod) {
        this.projectConstructionPeriod = projectConstructionPeriod;
    }

    public BigDecimal getDevelopedYear() {
        return developedYear;
    }

    public void setDevelopedYear(BigDecimal developedYear) {
        this.developedYear = developedYear;
    }

    public BigDecimal getRemainingDevelopmentYear() {
        return remainingDevelopmentYear;
    }

    public void setRemainingDevelopmentYear(BigDecimal remainingDevelopmentYear) {
        this.remainingDevelopmentYear = remainingDevelopmentYear;
    }

    public Integer getRewardRateId() {
        return rewardRateId;
    }

    public void setRewardRateId(Integer rewardRateId) {
        this.rewardRateId = rewardRateId;
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