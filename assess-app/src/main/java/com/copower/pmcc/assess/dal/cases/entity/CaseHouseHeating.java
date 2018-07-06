package com.copower.pmcc.assess.dal.cases.entity;

import java.util.Date;

public class CaseHouseHeating {
    private Integer id;

    private Integer houseId;

    private Integer heatingMode;

    private String heatingEquipment;

    private String heatingEquipmentPrice;

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

    public Integer getHeatingMode() {
        return heatingMode;
    }

    public void setHeatingMode(Integer heatingMode) {
        this.heatingMode = heatingMode;
    }

    public String getHeatingEquipment() {
        return heatingEquipment;
    }

    public void setHeatingEquipment(String heatingEquipment) {
        this.heatingEquipment = heatingEquipment == null ? null : heatingEquipment.trim();
    }

    public String getHeatingEquipmentPrice() {
        return heatingEquipmentPrice;
    }

    public void setHeatingEquipmentPrice(String heatingEquipmentPrice) {
        this.heatingEquipmentPrice = heatingEquipmentPrice == null ? null : heatingEquipmentPrice.trim();
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