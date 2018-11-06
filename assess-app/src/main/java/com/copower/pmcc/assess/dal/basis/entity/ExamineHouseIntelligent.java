package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouseIntelligent {
    private Integer id;

    private Integer declareId;

    private Integer planDetailsId;

    private Integer examineType;

    private Integer wireErection;

    private Integer wireMaterial;

    private Integer switchCircuit;

    private Integer lampsLanterns;

    private Integer internalCommunication;

    private Integer monitoringSystem;

    private Integer intelligentSystem;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public Integer getWireErection() {
        return wireErection;
    }

    public void setWireErection(Integer wireErection) {
        this.wireErection = wireErection;
    }

    public Integer getWireMaterial() {
        return wireMaterial;
    }

    public void setWireMaterial(Integer wireMaterial) {
        this.wireMaterial = wireMaterial;
    }

    public Integer getSwitchCircuit() {
        return switchCircuit;
    }

    public void setSwitchCircuit(Integer switchCircuit) {
        this.switchCircuit = switchCircuit;
    }

    public Integer getLampsLanterns() {
        return lampsLanterns;
    }

    public void setLampsLanterns(Integer lampsLanterns) {
        this.lampsLanterns = lampsLanterns;
    }

    public Integer getInternalCommunication() {
        return internalCommunication;
    }

    public void setInternalCommunication(Integer internalCommunication) {
        this.internalCommunication = internalCommunication;
    }

    public Integer getMonitoringSystem() {
        return monitoringSystem;
    }

    public void setMonitoringSystem(Integer monitoringSystem) {
        this.monitoringSystem = monitoringSystem;
    }

    public Integer getIntelligentSystem() {
        return intelligentSystem;
    }

    public void setIntelligentSystem(Integer intelligentSystem) {
        this.intelligentSystem = intelligentSystem;
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