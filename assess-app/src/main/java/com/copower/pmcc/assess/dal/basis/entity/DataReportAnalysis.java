package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataReportAnalysis {
    private Integer id;

    private String name;

    private Integer reportAnalysisType;

    private String template;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String entrustment;

    private String purpose;

    private String province;

    private String city;

    private String district;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getEntrustment() {
        return entrustment;
    }

    public void setEntrustment(String entrustment) {
        this.entrustment = entrustment == null ? null : entrustment.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
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
}