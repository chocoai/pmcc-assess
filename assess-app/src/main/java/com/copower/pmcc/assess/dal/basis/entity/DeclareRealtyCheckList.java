package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareRealtyCheckList {
    private Integer id;

    private Integer autoInitNumber;

    private Integer marsterId;

    private Integer planDetailsId;

    private String district;

    private String streetNumber;

    private String houseNumber;

    private String attachedNumber;

    private String buildingNumber;

    private String unit;

    private String floor;

    private String roomNumber;

    private String certUse;

    private BigDecimal floorArea;

    private BigDecimal apportionmentArea;

    private String realEstateUnitNumber;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutoInitNumber() {
        return autoInitNumber;
    }

    public void setAutoInitNumber(Integer autoInitNumber) {
        this.autoInitNumber = autoInitNumber;
    }

    public Integer getMarsterId() {
        return marsterId;
    }

    public void setMarsterId(Integer marsterId) {
        this.marsterId = marsterId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber == null ? null : streetNumber.trim();
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public String getAttachedNumber() {
        return attachedNumber;
    }

    public void setAttachedNumber(String attachedNumber) {
        this.attachedNumber = attachedNumber == null ? null : attachedNumber.trim();
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber == null ? null : buildingNumber.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public String getCertUse() {
        return certUse;
    }

    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getApportionmentArea() {
        return apportionmentArea;
    }

    public void setApportionmentArea(BigDecimal apportionmentArea) {
        this.apportionmentArea = apportionmentArea;
    }

    public String getRealEstateUnitNumber() {
        return realEstateUnitNumber;
    }

    public void setRealEstateUnitNumber(String realEstateUnitNumber) {
        this.realEstateUnitNumber = realEstateUnitNumber == null ? null : realEstateUnitNumber.trim();
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