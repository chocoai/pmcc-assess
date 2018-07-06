package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseBuildingOutfit {
    private Integer id;

    private Integer buildingId;

    private String decorationPart;

    private String decoratingMaterial;

    private String materialPrice;

    private String constructionTechnology;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public String getDecoratingMaterial() {
        return decoratingMaterial;
    }

    public void setDecoratingMaterial(String decoratingMaterial) {
        this.decoratingMaterial = decoratingMaterial == null ? null : decoratingMaterial.trim();
    }

    public String getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(String materialPrice) {
        this.materialPrice = materialPrice == null ? null : materialPrice.trim();
    }

    public String getConstructionTechnology() {
        return constructionTechnology;
    }

    public void setConstructionTechnology(String constructionTechnology) {
        this.constructionTechnology = constructionTechnology == null ? null : constructionTechnology.trim();
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