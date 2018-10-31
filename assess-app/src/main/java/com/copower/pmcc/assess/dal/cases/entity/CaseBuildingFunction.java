package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseBuildingFunction {
    private Integer id;

    private String buildingNumber;

    private Integer version;

    private Integer buildingId;

    private Integer decorationPart;

    private Integer decoratingMaterial;

    private Integer materialPrice;

    private Integer constructionTechnology;

    private String creator;

    private String waterProof;

    private String heatPreservation;

    private String buildNumber;

    private String heatInsulation;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
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

    public String getWaterProof() {
        return waterProof;
    }

    public void setWaterProof(String waterProof) {
        this.waterProof = waterProof == null ? null : waterProof.trim();
    }

    public String getHeatPreservation() {
        return heatPreservation;
    }

    public void setHeatPreservation(String heatPreservation) {
        this.heatPreservation = heatPreservation == null ? null : heatPreservation.trim();
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber == null ? null : buildNumber.trim();
    }

    public String getHeatInsulation() {
        return heatInsulation;
    }

    public void setHeatInsulation(String heatInsulation) {
        this.heatInsulation = heatInsulation == null ? null : heatInsulation.trim();
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