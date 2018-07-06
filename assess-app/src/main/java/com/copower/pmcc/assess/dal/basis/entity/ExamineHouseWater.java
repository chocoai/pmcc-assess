package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouseWater {
    private Integer id;

    private Integer houseId;

    private Integer supplyErectionMethod;

    private String intakePointNumber;

    private Integer pretreatedWater;

    private String purificationEquipment;

    private String purificationEquipmentPrice;

    private String natrueIntakePointNumber;

    private String waterIntakeEquipment;

    private String waterIntakeEquipmentPrice;

    private String drainageErectionMethod;

    private Integer drainageCircuit;

    private Integer drainageCircuitCount;

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

    public Integer getSupplyErectionMethod() {
        return supplyErectionMethod;
    }

    public void setSupplyErectionMethod(Integer supplyErectionMethod) {
        this.supplyErectionMethod = supplyErectionMethod;
    }

    public String getIntakePointNumber() {
        return intakePointNumber;
    }

    public void setIntakePointNumber(String intakePointNumber) {
        this.intakePointNumber = intakePointNumber == null ? null : intakePointNumber.trim();
    }

    public Integer getPretreatedWater() {
        return pretreatedWater;
    }

    public void setPretreatedWater(Integer pretreatedWater) {
        this.pretreatedWater = pretreatedWater;
    }

    public String getPurificationEquipment() {
        return purificationEquipment;
    }

    public void setPurificationEquipment(String purificationEquipment) {
        this.purificationEquipment = purificationEquipment == null ? null : purificationEquipment.trim();
    }

    public String getPurificationEquipmentPrice() {
        return purificationEquipmentPrice;
    }

    public void setPurificationEquipmentPrice(String purificationEquipmentPrice) {
        this.purificationEquipmentPrice = purificationEquipmentPrice == null ? null : purificationEquipmentPrice.trim();
    }

    public String getNatrueIntakePointNumber() {
        return natrueIntakePointNumber;
    }

    public void setNatrueIntakePointNumber(String natrueIntakePointNumber) {
        this.natrueIntakePointNumber = natrueIntakePointNumber == null ? null : natrueIntakePointNumber.trim();
    }

    public String getWaterIntakeEquipment() {
        return waterIntakeEquipment;
    }

    public void setWaterIntakeEquipment(String waterIntakeEquipment) {
        this.waterIntakeEquipment = waterIntakeEquipment == null ? null : waterIntakeEquipment.trim();
    }

    public String getWaterIntakeEquipmentPrice() {
        return waterIntakeEquipmentPrice;
    }

    public void setWaterIntakeEquipmentPrice(String waterIntakeEquipmentPrice) {
        this.waterIntakeEquipmentPrice = waterIntakeEquipmentPrice == null ? null : waterIntakeEquipmentPrice.trim();
    }

    public String getDrainageErectionMethod() {
        return drainageErectionMethod;
    }

    public void setDrainageErectionMethod(String drainageErectionMethod) {
        this.drainageErectionMethod = drainageErectionMethod == null ? null : drainageErectionMethod.trim();
    }

    public Integer getDrainageCircuit() {
        return drainageCircuit;
    }

    public void setDrainageCircuit(Integer drainageCircuit) {
        this.drainageCircuit = drainageCircuit;
    }

    public Integer getDrainageCircuitCount() {
        return drainageCircuitCount;
    }

    public void setDrainageCircuitCount(Integer drainageCircuitCount) {
        this.drainageCircuitCount = drainageCircuitCount;
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