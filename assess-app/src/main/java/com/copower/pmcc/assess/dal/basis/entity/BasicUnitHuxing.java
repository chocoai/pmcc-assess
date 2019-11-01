package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicUnitHuxing {
    private Integer id;

    private Integer unitId;

    private Integer type;

    private String name;

    private BigDecimal area;

    private String orientation;

    private BigDecimal spanLength;

    private Integer spanWidth;

    private BigDecimal spanNumber;

    private BigDecimal bay;

    private BigDecimal deep;

    private String houseCategory;

    private String description;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    public BigDecimal getSpanLength() {
        return spanLength;
    }

    public void setSpanLength(BigDecimal spanLength) {
        this.spanLength = spanLength;
    }

    public Integer getSpanWidth() {
        return spanWidth;
    }

    public void setSpanWidth(Integer spanWidth) {
        this.spanWidth = spanWidth;
    }

    public BigDecimal getSpanNumber() {
        return spanNumber;
    }

    public void setSpanNumber(BigDecimal spanNumber) {
        this.spanNumber = spanNumber;
    }

    public BigDecimal getBay() {
        return bay;
    }

    public void setBay(BigDecimal bay) {
        this.bay = bay;
    }

    public BigDecimal getDeep() {
        return deep;
    }

    public void setDeep(BigDecimal deep) {
        this.deep = deep;
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory == null ? null : houseCategory.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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