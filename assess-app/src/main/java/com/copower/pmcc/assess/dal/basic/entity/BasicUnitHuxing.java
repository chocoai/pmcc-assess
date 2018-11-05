package com.copower.pmcc.assess.dal.basic.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicUnitHuxing {
    private Integer id;

    private Integer caseId;

    private Integer unitId;

    private Integer houseLayout;

    private BigDecimal area;

    private BigDecimal spanLength;

    private Integer spanWidth;

    private BigDecimal spanNumber;

    private String description;

    private String creator;

    private String houseCategory;

    private String name;

    private String orientation;

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

    public Integer getHouseLayout() {
        return houseLayout;
    }

    public void setHouseLayout(Integer houseLayout) {
        this.houseLayout = houseLayout;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory == null ? null : houseCategory.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
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