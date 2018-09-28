package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:38
 * @Description:
 */
public class DeclareBuildEquipmentInstallDto {
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
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date expectedCompletionDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date startDate;

    private String bookEquipmentFee;

    private String bookCapitalCost;

    private String bookInstallationFee;

    private String other;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date declarationDate;

    private String declarer;

    private String remark;

    private String enable;
    private Integer buildingConstructionPermitId;

    private Integer buildingPermitId;

    private Integer landUsePermitId;

    private Integer preSalePermitId;

    private Integer landId;

    private Integer realEstateId;

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
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOccupancyUnit() {
        return occupancyUnit;
    }

    public void setOccupancyUnit(String occupancyUnit) {
        this.occupancyUnit = occupancyUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getSpecificationModel() {
        return specificationModel;
    }

    public void setSpecificationModel(String specificationModel) {
        this.specificationModel = specificationModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
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
        this.bookEquipmentFee = bookEquipmentFee;
    }

    public String getBookCapitalCost() {
        return bookCapitalCost;
    }

    public void setBookCapitalCost(String bookCapitalCost) {
        this.bookCapitalCost = bookCapitalCost;
    }

    public String getBookInstallationFee() {
        return bookInstallationFee;
    }

    public void setBookInstallationFee(String bookInstallationFee) {
        this.bookInstallationFee = bookInstallationFee;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
        this.declarer = declarer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
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

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }
}
