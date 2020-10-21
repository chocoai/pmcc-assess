package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataReportAnalysis {
    private Integer id;

    private String province;

    private String city;

    private String district;

    private String name;

    private String type;

    private String category;

    private Integer marketBackgroundType;

    private Integer reportAnalysisType;

    private String template;

    private String entrustmentPurpose;

    private Integer setUse;

    private Integer relYear;

    private Integer blockId;

    private String blockName;

    private String fieldName;

    private Integer sorting;

    private Boolean bisModifiable;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getMarketBackgroundType() {
        return marketBackgroundType;
    }

    public void setMarketBackgroundType(Integer marketBackgroundType) {
        this.marketBackgroundType = marketBackgroundType;
    }

    public Integer getReportAnalysisType() {
        return reportAnalysisType;
    }

    public void setReportAnalysisType(Integer reportAnalysisType) {
        this.reportAnalysisType = reportAnalysisType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getEntrustmentPurpose() {
        return entrustmentPurpose;
    }

    public void setEntrustmentPurpose(String entrustmentPurpose) {
        this.entrustmentPurpose = entrustmentPurpose == null ? null : entrustmentPurpose.trim();
    }

    public Integer getSetUse() {
        return setUse;
    }

    public void setSetUse(Integer setUse) {
        this.setUse = setUse;
    }

    public Integer getRelYear() {
        return relYear;
    }

    public void setRelYear(Integer relYear) {
        this.relYear = relYear;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean getBisModifiable() {
        return bisModifiable;
    }

    public void setBisModifiable(Boolean bisModifiable) {
        this.bisModifiable = bisModifiable;
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