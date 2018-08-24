package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdDevelopmentHypothesis {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String newRate;

    private BigDecimal assessPrice;

    private BigDecimal constructionInstallationEngineeringFee;

    private String jsonContent;

    private Integer costId;

    private Integer engineeringId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNewRate() {
        return newRate;
    }

    public void setNewRate(String newRate) {
        this.newRate = newRate == null ? null : newRate.trim();
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public BigDecimal getConstructionInstallationEngineeringFee() {
        return constructionInstallationEngineeringFee;
    }

    public void setConstructionInstallationEngineeringFee(BigDecimal constructionInstallationEngineeringFee) {
        this.constructionInstallationEngineeringFee = constructionInstallationEngineeringFee;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public Integer getEngineeringId() {
        return engineeringId;
    }

    public void setEngineeringId(Integer engineeringId) {
        this.engineeringId = engineeringId;
    }
}