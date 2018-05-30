package com.copower.pmcc.assess.dal.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MethodMarketCompareResult {
    private Integer id;

    private Integer evaluationObjectId;

    private Integer type;

    private String realEstateName;

    private String specificPrice;

    private String correctionDifference;

    private String caseDifference;

    private BigDecimal weight;

    private BigDecimal weightedAveragePrice;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvaluationObjectId() {
        return evaluationObjectId;
    }

    public void setEvaluationObjectId(Integer evaluationObjectId) {
        this.evaluationObjectId = evaluationObjectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRealEstateName() {
        return realEstateName;
    }

    public void setRealEstateName(String realEstateName) {
        this.realEstateName = realEstateName == null ? null : realEstateName.trim();
    }

    public String getSpecificPrice() {
        return specificPrice;
    }

    public void setSpecificPrice(String specificPrice) {
        this.specificPrice = specificPrice == null ? null : specificPrice.trim();
    }

    public String getCorrectionDifference() {
        return correctionDifference;
    }

    public void setCorrectionDifference(String correctionDifference) {
        this.correctionDifference = correctionDifference == null ? null : correctionDifference.trim();
    }

    public String getCaseDifference() {
        return caseDifference;
    }

    public void setCaseDifference(String caseDifference) {
        this.caseDifference = caseDifference == null ? null : caseDifference.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeightedAveragePrice() {
        return weightedAveragePrice;
    }

    public void setWeightedAveragePrice(BigDecimal weightedAveragePrice) {
        this.weightedAveragePrice = weightedAveragePrice;
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