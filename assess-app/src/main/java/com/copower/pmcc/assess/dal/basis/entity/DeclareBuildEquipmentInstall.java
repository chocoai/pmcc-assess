package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DeclareBuildEquipmentInstall {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private String province;

    private String city;

    private String district;

    private String occupancyUnit;

    private String name;

    private String beLocated;

    private String specificationModel;

    private String manufacturer;

    private String measurementUnit;

    private Integer number;

    private Date expectedCompletionDate;

    private Date startDate;

    private String bookEquipmentFee;

    private String bookCapitalCost;

    private String bookInstallationFee;

    private String other;

    private Date declarationDate;

    private String declarer;

    private String remark;

    private String enable;

    private Integer buildingConstructionPermitId;

    private Integer buildingPermitId;

    private Integer landUsePermitId;

    private Integer preSalePermitId;

    private Integer landId;

    private String declareType;

    private Integer realEstateId;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
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

    public String getOccupancyUnit() {
        return occupancyUnit;
    }

    public void setOccupancyUnit(String occupancyUnit) {
        this.occupancyUnit = occupancyUnit == null ? null : occupancyUnit.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
    }

    public String getSpecificationModel() {
        return specificationModel;
    }

    public void setSpecificationModel(String specificationModel) {
        this.specificationModel = specificationModel == null ? null : specificationModel.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit == null ? null : measurementUnit.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Date expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getBookEquipmentFee() {
        return bookEquipmentFee;
    }

    public void setBookEquipmentFee(String bookEquipmentFee) {
        this.bookEquipmentFee = bookEquipmentFee == null ? null : bookEquipmentFee.trim();
    }

    public String getBookCapitalCost() {
        return bookCapitalCost;
    }

    public void setBookCapitalCost(String bookCapitalCost) {
        this.bookCapitalCost = bookCapitalCost == null ? null : bookCapitalCost.trim();
    }

    public String getBookInstallationFee() {
        return bookInstallationFee;
    }

    public void setBookInstallationFee(String bookInstallationFee) {
        this.bookInstallationFee = bookInstallationFee == null ? null : bookInstallationFee.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getDeclarer() {
        return declarer;
    }

    public void setDeclarer(String declarer) {
        this.declarer = declarer == null ? null : declarer.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public Integer getBuildingConstructionPermitId() {
        return buildingConstructionPermitId;
    }

    public void setBuildingConstructionPermitId(Integer buildingConstructionPermitId) {
        this.buildingConstructionPermitId = buildingConstructionPermitId;
    }

    public Integer getBuildingPermitId() {
        return buildingPermitId;
    }

    public void setBuildingPermitId(Integer buildingPermitId) {
        this.buildingPermitId = buildingPermitId;
    }

    public Integer getLandUsePermitId() {
        return landUsePermitId;
    }

    public void setLandUsePermitId(Integer landUsePermitId) {
        this.landUsePermitId = landUsePermitId;
    }

    public Integer getPreSalePermitId() {
        return preSalePermitId;
    }

    public void setPreSalePermitId(Integer preSalePermitId) {
        this.preSalePermitId = preSalePermitId;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType == null ? null : declareType.trim();
    }

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
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