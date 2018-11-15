package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicBuildingOutfit {
    private Integer id;

    private Integer caseOutfitId;

    private String buildingNumber;

    private Integer buildingId;

    private Boolean temporary;

    private Integer decorationPart;

    private Integer decoratingMaterial;

    private Integer materialPrice;

    private Integer constructionTechnology;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String buildNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseOutfitId() {
        return caseOutfitId;
    }

    public void setCaseOutfitId(Integer caseOutfitId) {
        this.caseOutfitId = caseOutfitId;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public Integer getDecorationPart() {
        return decorationPart;
    }

    public void setDecorationPart(Integer decorationPart) {
        this.decorationPart = decorationPart;
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
}