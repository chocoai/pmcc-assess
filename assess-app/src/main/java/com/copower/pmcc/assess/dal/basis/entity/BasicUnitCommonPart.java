package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicUnitCommonPart {
    private Integer id;

    private Integer unitId;

    private String unitCommonPart;

    private String unitLocation;

    private String unitMonad;

    private String unitQuantity;

    private Boolean bisDelete;

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

    public String getUnitCommonPart() {
        return unitCommonPart;
    }

    public void setUnitCommonPart(String unitCommonPart) {
        this.unitCommonPart = unitCommonPart == null ? null : unitCommonPart.trim();
    }

    public String getUnitLocation() {
        return unitLocation;
    }

    public void setUnitLocation(String unitLocation) {
        this.unitLocation = unitLocation == null ? null : unitLocation.trim();
    }

    public String getUnitMonad() {
        return unitMonad;
    }

    public void setUnitMonad(String unitMonad) {
        this.unitMonad = unitMonad == null ? null : unitMonad.trim();
    }

    public String getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(String unitQuantity) {
        this.unitQuantity = unitQuantity == null ? null : unitQuantity.trim();
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
}