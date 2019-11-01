package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicHouseWater {
    private Integer id;

    private Integer houseId;

    private Integer supplyMode;

    private Integer pipingLayout;

    private Integer pipeMaterial;

    private Integer grade;

    private Integer boosterEquipment;

    private Integer pretreatedWater;

    private Integer purificationEquipmentPrice;

    private Integer fireWaterSupply;

    private Boolean bisDelete;

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

    public Integer getSupplyMode() {
        return supplyMode;
    }

    public void setSupplyMode(Integer supplyMode) {
        this.supplyMode = supplyMode;
    }

    public Integer getPipingLayout() {
        return pipingLayout;
    }

    public void setPipingLayout(Integer pipingLayout) {
        this.pipingLayout = pipingLayout;
    }

    public Integer getPipeMaterial() {
        return pipeMaterial;
    }

    public void setPipeMaterial(Integer pipeMaterial) {
        this.pipeMaterial = pipeMaterial;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getBoosterEquipment() {
        return boosterEquipment;
    }

    public void setBoosterEquipment(Integer boosterEquipment) {
        this.boosterEquipment = boosterEquipment;
    }

    public Integer getPretreatedWater() {
        return pretreatedWater;
    }

    public void setPretreatedWater(Integer pretreatedWater) {
        this.pretreatedWater = pretreatedWater;
    }

    public Integer getPurificationEquipmentPrice() {
        return purificationEquipmentPrice;
    }

    public void setPurificationEquipmentPrice(Integer purificationEquipmentPrice) {
        this.purificationEquipmentPrice = purificationEquipmentPrice;
    }

    public Integer getFireWaterSupply() {
        return fireWaterSupply;
    }

    public void setFireWaterSupply(Integer fireWaterSupply) {
        this.fireWaterSupply = fireWaterSupply;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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