package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareRecord {
    private Integer id;

    private Integer projectId;

    private Integer number;

    private String dataTableName;

    private Integer dataTableId;

    private String dataFromType;

    private String province;

    private String city;

    private String district;

    private String type;

    private Boolean hasCert;

    private String name;

    private String ownership;

    private String seat;

    private String streetNumber;

    private String attachedNumber;

    private String buildingNumber;

    private String unit;

    private String floor;

    private String roomNumber;

    private String certUse;

    private String practicalUse;

    private String landCertUse;

    private String landCertUseCategory;

    private String landPracticalUse;

    private String publicSituation;

    private BigDecimal floorArea;

    private BigDecimal practicalArea;

    private String nature;

    private String landAcquisitionMethod;

    private String landRightType;

    private String landRightNature;

    private BigDecimal landUseRightArea;

    private String housingStructure;

    private Integer groupId;

    private Integer areaGroupId;

    private Date houseUseEndDate;

    private Date landUseEndDate;

    private String inventoryContentKey;

    private Date registrationDate;

    private BigDecimal price;

    private Integer buildingStatus;

    private Boolean bisPartIn;

    private Boolean bisGenerate;

    private String inventoryStatus;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName == null ? null : dataTableName.trim();
    }

    public Integer getDataTableId() {
        return dataTableId;
    }

    public void setDataTableId(Integer dataTableId) {
        this.dataTableId = dataTableId;
    }

    public String getDataFromType() {
        return dataFromType;
    }

    public void setDataFromType(String dataFromType) {
        this.dataFromType = dataFromType == null ? null : dataFromType.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Boolean getHasCert() {
        return hasCert;
    }

    public void setHasCert(Boolean hasCert) {
        this.hasCert = hasCert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber == null ? null : streetNumber.trim();
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

    public String getPracticalUse() {
        return practicalUse;
    }

    public void setPracticalUse(String practicalUse) {
        this.practicalUse = practicalUse == null ? null : practicalUse.trim();
    }

    public String getLandCertUse() {
        return landCertUse;
    }

    public void setLandCertUse(String landCertUse) {
        this.landCertUse = landCertUse == null ? null : landCertUse.trim();
    }

    public String getLandCertUseCategory() {
        return landCertUseCategory;
    }

    public void setLandCertUseCategory(String landCertUseCategory) {
        this.landCertUseCategory = landCertUseCategory == null ? null : landCertUseCategory.trim();
    }

    public String getLandPracticalUse() {
        return landPracticalUse;
    }

    public void setLandPracticalUse(String landPracticalUse) {
        this.landPracticalUse = landPracticalUse == null ? null : landPracticalUse.trim();
    }

    public String getPublicSituation() {
        return publicSituation;
    }

    public void setPublicSituation(String publicSituation) {
        this.publicSituation = publicSituation == null ? null : publicSituation.trim();
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getPracticalArea() {
        return practicalArea;
    }

    public void setPracticalArea(BigDecimal practicalArea) {
        this.practicalArea = practicalArea;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public String getLandAcquisitionMethod() {
        return landAcquisitionMethod;
    }

    public void setLandAcquisitionMethod(String landAcquisitionMethod) {
        this.landAcquisitionMethod = landAcquisitionMethod == null ? null : landAcquisitionMethod.trim();
    }

    public String getLandRightType() {
        return landRightType;
    }

    public void setLandRightType(String landRightType) {
        this.landRightType = landRightType == null ? null : landRightType.trim();
    }

    public String getLandRightNature() {
        return landRightNature;
    }

    public void setLandRightNature(String landRightNature) {
        this.landRightNature = landRightNature == null ? null : landRightNature.trim();
    }

    public BigDecimal getLandUseRightArea() {
        return landUseRightArea;
    }

    public void setLandUseRightArea(BigDecimal landUseRightArea) {
        this.landUseRightArea = landUseRightArea;
    }

    public String getHousingStructure() {
        return housingStructure;
    }

    public void setHousingStructure(String housingStructure) {
        this.housingStructure = housingStructure == null ? null : housingStructure.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Date getHouseUseEndDate() {
        return houseUseEndDate;
    }

    public void setHouseUseEndDate(Date houseUseEndDate) {
        this.houseUseEndDate = houseUseEndDate;
    }

    public Date getLandUseEndDate() {
        return landUseEndDate;
    }

    public void setLandUseEndDate(Date landUseEndDate) {
        this.landUseEndDate = landUseEndDate;
    }

    public String getInventoryContentKey() {
        return inventoryContentKey;
    }

    public void setInventoryContentKey(String inventoryContentKey) {
        this.inventoryContentKey = inventoryContentKey == null ? null : inventoryContentKey.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(Integer buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public Boolean getBisPartIn() {
        return bisPartIn;
    }

    public void setBisPartIn(Boolean bisPartIn) {
        this.bisPartIn = bisPartIn;
    }

    public Boolean getBisGenerate() {
        return bisGenerate;
    }

    public void setBisGenerate(Boolean bisGenerate) {
        this.bisGenerate = bisGenerate;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus == null ? null : inventoryStatus.trim();
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