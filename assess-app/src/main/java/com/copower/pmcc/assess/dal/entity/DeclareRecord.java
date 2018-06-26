package com.copower.pmcc.assess.dal.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareRecord {
    private Integer id;

    private Integer projectId;

    private Integer projectClassifyId;

    private String name;

    private String ownership;

    private String province;

    private String city;

    private String district;

    private Integer areaGroupId;

    private BigDecimal floorArea;

    private BigDecimal assessArea;

    private BigDecimal assessUnitPrice;

    private BigDecimal alreadyOutArea;

    private Boolean bisGenerateAll;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectClassifyId() {
        return projectClassifyId;
    }

    public void setProjectClassifyId(Integer projectClassifyId) {
        this.projectClassifyId = projectClassifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getAssessArea() {
        return assessArea;
    }

    public void setAssessArea(BigDecimal assessArea) {
        this.assessArea = assessArea;
    }

    public BigDecimal getAssessUnitPrice() {
        return assessUnitPrice;
    }

    public void setAssessUnitPrice(BigDecimal assessUnitPrice) {
        this.assessUnitPrice = assessUnitPrice;
    }

    public BigDecimal getAlreadyOutArea() {
        return alreadyOutArea;
    }

    public void setAlreadyOutArea(BigDecimal alreadyOutArea) {
        this.alreadyOutArea = alreadyOutArea;
    }

    public Boolean getBisGenerateAll() {
        return bisGenerateAll;
    }

    public void setBisGenerateAll(Boolean bisGenerateAll) {
        this.bisGenerateAll = bisGenerateAll;
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