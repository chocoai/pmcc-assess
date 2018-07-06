package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouseAirConditioner {
    private Integer id;

    private Integer houseId;

    private String airConditioningSystem;

    private String airConditioningEquipment;

    private String airConditioningEquipmentPrice;

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

    public String getAirConditioningSystem() {
        return airConditioningSystem;
    }

    public void setAirConditioningSystem(String airConditioningSystem) {
        this.airConditioningSystem = airConditioningSystem == null ? null : airConditioningSystem.trim();
    }

    public String getAirConditioningEquipment() {
        return airConditioningEquipment;
    }

    public void setAirConditioningEquipment(String airConditioningEquipment) {
        this.airConditioningEquipment = airConditioningEquipment == null ? null : airConditioningEquipment.trim();
    }

    public String getAirConditioningEquipmentPrice() {
        return airConditioningEquipmentPrice;
    }

    public void setAirConditioningEquipmentPrice(String airConditioningEquipmentPrice) {
        this.airConditioningEquipmentPrice = airConditioningEquipmentPrice == null ? null : airConditioningEquipmentPrice.trim();
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