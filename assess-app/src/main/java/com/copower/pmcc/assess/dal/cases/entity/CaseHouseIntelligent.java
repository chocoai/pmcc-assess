package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseHouseIntelligent {
    private Integer id;

    private Integer houseId;

    private Integer switchCircuit;

    private Integer layingMethod;

    private String lampsLanterns;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer grade;

    private String intelligentSystem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getSwitchCircuit() {
        return switchCircuit;
    }

    public void setSwitchCircuit(Integer switchCircuit) {
        this.switchCircuit = switchCircuit;
    }

    public Integer getLayingMethod() {
        return layingMethod;
    }

    public void setLayingMethod(Integer layingMethod) {
        this.layingMethod = layingMethod;
    }

    public String getLampsLanterns() {
        return lampsLanterns;
    }

    public void setLampsLanterns(String lampsLanterns) {
        this.lampsLanterns = lampsLanterns == null ? null : lampsLanterns.trim();
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIntelligentSystem() {
        return intelligentSystem;
    }

    public void setIntelligentSystem(String intelligentSystem) {
        this.intelligentSystem = intelligentSystem == null ? null : intelligentSystem.trim();
    }
}