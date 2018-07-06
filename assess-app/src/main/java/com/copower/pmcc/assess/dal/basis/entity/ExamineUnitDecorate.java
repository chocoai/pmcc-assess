package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineUnitDecorate {
    private Integer id;

    private Integer unitId;

    private String decorationPart;

    private String decoratingMaterial;

    private String materialPriceRange;

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

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
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

    public String getMaterialPriceRange() {
        return materialPriceRange;
    }

    public void setMaterialPriceRange(String materialPriceRange) {
        this.materialPriceRange = materialPriceRange == null ? null : materialPriceRange.trim();
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