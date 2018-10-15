package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostBuilding {
    private Integer id;

    private BigDecimal assessprice;

    private String replacementvalue;

    private String investmentprofit;

    private String interestinvestment;

    private String constructioncost;

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

    public BigDecimal getAssessprice() {
        return assessprice;
    }

    public void setAssessprice(BigDecimal assessprice) {
        this.assessprice = assessprice;
    }

    public String getReplacementvalue() {
        return replacementvalue;
    }

    public void setReplacementvalue(String replacementvalue) {
        this.replacementvalue = replacementvalue == null ? null : replacementvalue.trim();
    }

    public String getInvestmentprofit() {
        return investmentprofit;
    }

    public void setInvestmentprofit(String investmentprofit) {
        this.investmentprofit = investmentprofit == null ? null : investmentprofit.trim();
    }

    public String getInterestinvestment() {
        return interestinvestment;
    }

    public void setInterestinvestment(String interestinvestment) {
        this.interestinvestment = interestinvestment == null ? null : interestinvestment.trim();
    }

    public String getConstructioncost() {
        return constructioncost;
    }

    public void setConstructioncost(String constructioncost) {
        this.constructioncost = constructioncost == null ? null : constructioncost.trim();
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