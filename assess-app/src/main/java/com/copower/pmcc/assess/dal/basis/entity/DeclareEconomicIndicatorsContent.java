package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DeclareEconomicIndicatorsContent {
    private Integer id;

    private Integer indicatorsHeadId;

    private Integer planDetailsId;

    private String name;

    private String customKey;

    private String salabilityNumber;

    private String assessSalabilityNumber;

    private String planIndex;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndicatorsHeadId() {
        return indicatorsHeadId;
    }

    public void setIndicatorsHeadId(Integer indicatorsHeadId) {
        this.indicatorsHeadId = indicatorsHeadId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey == null ? null : customKey.trim();
    }

    public String getSalabilityNumber() {
        return salabilityNumber;
    }

    public void setSalabilityNumber(String salabilityNumber) {
        this.salabilityNumber = salabilityNumber == null ? null : salabilityNumber.trim();
    }

    public String getAssessSalabilityNumber() {
        return assessSalabilityNumber;
    }

    public void setAssessSalabilityNumber(String assessSalabilityNumber) {
        this.assessSalabilityNumber = assessSalabilityNumber == null ? null : assessSalabilityNumber.trim();
    }

    public String getPlanIndex() {
        return planIndex;
    }

    public void setPlanIndex(String planIndex) {
        this.planIndex = planIndex == null ? null : planIndex.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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