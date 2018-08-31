package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExamineUnitHuxing {
    private Integer id;

    private Integer declareId;

    private Integer planDetailsId;

    private Integer examineType;

    private Integer houseLayout;

    private BigDecimal area;

    private BigDecimal spanLength;

    private Integer spanWidth;

    private BigDecimal spanNumber;

    private String description;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String houseCategory;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
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
}