package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExamineUnitHuxing {
    private Integer id;

    private Integer unitId;

    private String houseLayout;

    private BigDecimal area;

    private BigDecimal spanLength;

    private Integer spanWidth;

    private BigDecimal spanNumber;

    private String description;

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

    public String getHouseLayout() {
        return houseLayout;
    }

    public void setHouseLayout(String houseLayout) {
        this.houseLayout = houseLayout == null ? null : houseLayout.trim();
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