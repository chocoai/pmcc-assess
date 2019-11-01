package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicHouseEquipment {
    private Integer id;

    private Integer houseId;

    private String type;

    private Integer grade;

    private Integer category;

    private String supplyWeast;

    private String equipment;

    private Integer supplyMode;

    private Integer equipmentPrice;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSupplyWeast() {
        return supplyWeast;
    }

    public void setSupplyWeast(String supplyWeast) {
        this.supplyWeast = supplyWeast == null ? null : supplyWeast.trim();
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    public Integer getSupplyMode() {
        return supplyMode;
    }

    public void setSupplyMode(Integer supplyMode) {
        this.supplyMode = supplyMode;
    }

    public Integer getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(Integer equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
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