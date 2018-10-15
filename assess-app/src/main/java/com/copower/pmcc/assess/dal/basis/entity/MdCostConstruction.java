package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostConstruction {
    private Integer id;

    private BigDecimal constructionassessmentprice;

    private String constructionassessmentvalue;

    private String investmentprofit;

    private String interestinvestment;

    private String constructionsubtotal;

    private String landgetcosttotal;

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

    public BigDecimal getConstructionassessmentprice() {
        return constructionassessmentprice;
    }

    public void setConstructionassessmentprice(BigDecimal constructionassessmentprice) {
        this.constructionassessmentprice = constructionassessmentprice;
    }

    public String getConstructionassessmentvalue() {
        return constructionassessmentvalue;
    }

    public void setConstructionassessmentvalue(String constructionassessmentvalue) {
        this.constructionassessmentvalue = constructionassessmentvalue == null ? null : constructionassessmentvalue.trim();
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

    public String getConstructionsubtotal() {
        return constructionsubtotal;
    }

    public void setConstructionsubtotal(String constructionsubtotal) {
        this.constructionsubtotal = constructionsubtotal == null ? null : constructionsubtotal.trim();
    }

    public String getLandgetcosttotal() {
        return landgetcosttotal;
    }

    public void setLandgetcosttotal(String landgetcosttotal) {
        this.landgetcosttotal = landgetcosttotal == null ? null : landgetcosttotal.trim();
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