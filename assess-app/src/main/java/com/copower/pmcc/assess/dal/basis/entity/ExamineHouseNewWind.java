package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouseNewWind {
    private Integer id;

    private Integer houseId;

    private String airSupplyMode;

    private String airSupplyEquipment;

    private String airSupplyEquipmentPrice;

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

    public String getAirSupplyMode() {
        return airSupplyMode;
    }

    public void setAirSupplyMode(String airSupplyMode) {
        this.airSupplyMode = airSupplyMode == null ? null : airSupplyMode.trim();
    }

    public String getAirSupplyEquipment() {
        return airSupplyEquipment;
    }

    public void setAirSupplyEquipment(String airSupplyEquipment) {
        this.airSupplyEquipment = airSupplyEquipment == null ? null : airSupplyEquipment.trim();
    }

    public String getAirSupplyEquipmentPrice() {
        return airSupplyEquipmentPrice;
    }

    public void setAirSupplyEquipmentPrice(String airSupplyEquipmentPrice) {
        this.airSupplyEquipmentPrice = airSupplyEquipmentPrice == null ? null : airSupplyEquipmentPrice.trim();
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