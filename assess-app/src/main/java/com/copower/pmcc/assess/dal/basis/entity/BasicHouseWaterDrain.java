package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicHouseWaterDrain {
    private Integer id;

    private Integer houseId;

    private Integer drainSystem;

    private Integer type;

    private Integer organization;

    private Integer processingMode;

    private String evaluate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public Integer getDrainSystem() {
        return drainSystem;
    }

    public void setDrainSystem(Integer drainSystem) {
        this.drainSystem = drainSystem;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    public Integer getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(Integer processingMode) {
        this.processingMode = processingMode;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
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