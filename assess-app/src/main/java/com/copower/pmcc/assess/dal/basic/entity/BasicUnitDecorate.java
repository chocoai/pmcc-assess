package com.copower.pmcc.assess.dal.basic.entity;

import java.util.Date;

public class BasicUnitDecorate {
    private Integer id;

    private Integer caseId;

    private Integer unitId;

    private Integer decorationPart;

    private Integer decoratingMaterial;

    private Integer materialPriceRange;

    private String location;

    private Integer constructionTechnology;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
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
}