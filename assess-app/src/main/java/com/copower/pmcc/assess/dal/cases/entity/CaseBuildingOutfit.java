package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseBuildingOutfit {
    private Integer id;

    private Integer buildingId;

    private String decorationPart;

    private Integer decoratingMaterial;

    private Integer materialPrice;

    private Integer constructionTechnology;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String buildNumber;

    private Integer materialGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getDecorationPart() {
        return decorationPart;
    }

    public void setDecorationPart(String decorationPart) {
        this.decorationPart = decorationPart == null ? null : decorationPart.trim();
    }

    public Integer getDecoratingMaterial() {
        return decoratingMaterial;
    }

    public void setDecoratingMaterial(Integer decoratingMaterial) {
        this.decoratingMaterial = decoratingMaterial;
    }

    public Integer getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Integer materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Integer getConstructionTechnology() {
        return constructionTechnology;
    }

    public void setConstructionTechnology(Integer constructionTechnology) {
        this.constructionTechnology = constructionTechnology;
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

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber == null ? null : buildNumber.trim();
    }

    public Integer getMaterialGrade() {
        return materialGrade;
    }

    public void setMaterialGrade(Integer materialGrade) {
        this.materialGrade = materialGrade;
    }
}