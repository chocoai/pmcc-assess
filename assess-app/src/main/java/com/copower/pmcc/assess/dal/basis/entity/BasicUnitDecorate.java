package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicUnitDecorate {
    private Integer id;

    private Integer unitId;

    private Integer decorationPart;

    private Integer decoratingMaterial;

    private Integer materialPriceRange;

    private Integer constructionTechnology;

    private Integer materialGrade;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String unitCommonPart;

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

    public Integer getMaterialPriceRange() {
        return materialPriceRange;
    }

    public void setMaterialPriceRange(Integer materialPriceRange) {
        this.materialPriceRange = materialPriceRange;
    }

    public Integer getConstructionTechnology() {
        return constructionTechnology;
    }

    public void setConstructionTechnology(Integer constructionTechnology) {
        this.constructionTechnology = constructionTechnology;
    }

    public Integer getMaterialGrade() {
        return materialGrade;
    }

    public void setMaterialGrade(Integer materialGrade) {
        this.materialGrade = materialGrade;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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

    public String getUnitCommonPart() {
        return unitCommonPart;
    }

    public void setUnitCommonPart(String unitCommonPart) {
        this.unitCommonPart = unitCommonPart == null ? null : unitCommonPart.trim();
    }
}