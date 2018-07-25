package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdMarketCompareItem {
    private Integer id;

    private Integer mcId;

    private String name;

    private Integer type;

    private String jsonContent;

    private String specificPrice;

    private String correctionDifference;

    private String caseDifference;

    private BigDecimal weight;

    private String weightDescription;

    private String averagePrice;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
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

    public String getWeightDescription() {
        return weightDescription;
    }

    public void setWeightDescription(String weightDescription) {
        this.weightDescription = weightDescription == null ? null : weightDescription.trim();
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice == null ? null : averagePrice.trim();
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