package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SchemeAreaGroup {
    private Integer id;

    private Integer pid;

    private Integer projectId;

    private String province;

    private String city;

    private String district;

    private String areaName;

    private Date valueTimePoint;

    private String timePointExplain;

    private Integer entrustPurpose;

    private String remarkEntrustPurpose;

    private Integer valueDefinition;

    private String valueDefinitionDesc;

    private String valueConnotation;

    private String valueConnotationDesc;

    private Boolean bisEnable;

    private Boolean bisMerge;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Date getValueTimePoint() {
        return valueTimePoint;
    }

    public void setValueTimePoint(Date valueTimePoint) {
        this.valueTimePoint = valueTimePoint;
    }

    public String getTimePointExplain() {
        return timePointExplain;
    }

    public void setTimePointExplain(String timePointExplain) {
        this.timePointExplain = timePointExplain == null ? null : timePointExplain.trim();
    }

    public Integer getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(Integer entrustPurpose) {
        this.entrustPurpose = entrustPurpose;
    }

    public String getRemarkEntrustPurpose() {
        return remarkEntrustPurpose;
    }

    public void setRemarkEntrustPurpose(String remarkEntrustPurpose) {
        this.remarkEntrustPurpose = remarkEntrustPurpose == null ? null : remarkEntrustPurpose.trim();
    }

    public Integer getValueDefinition() {
        return valueDefinition;
    }

    public void setValueDefinition(Integer valueDefinition) {
        this.valueDefinition = valueDefinition;
    }

    public String getValueDefinitionDesc() {
        return valueDefinitionDesc;
    }

    public void setValueDefinitionDesc(String valueDefinitionDesc) {
        this.valueDefinitionDesc = valueDefinitionDesc == null ? null : valueDefinitionDesc.trim();
    }

    public String getValueConnotation() {
        return valueConnotation;
    }

    public void setValueConnotation(String valueConnotation) {
        this.valueConnotation = valueConnotation == null ? null : valueConnotation.trim();
    }

    public String getValueConnotationDesc() {
        return valueConnotationDesc;
    }

    public void setValueConnotationDesc(String valueConnotationDesc) {
        this.valueConnotationDesc = valueConnotationDesc == null ? null : valueConnotationDesc.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisMerge() {
        return bisMerge;
    }

    public void setBisMerge(Boolean bisMerge) {
        this.bisMerge = bisMerge;
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