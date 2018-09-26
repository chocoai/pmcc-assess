package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareRealtyLandCert {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private String landCertName;

    private String province;

    private String city;

    private String district;

    private String type;

    private String location;

    private String year;

    private String number;

    private String ownership;

    private String beLocated;

    private String streetNumber;

    private String attachedNumber;

    private String buildingNumber;

    private String unit;

    private String floor;

    private String roomNumber;

    private String landNumber;

    private String purpose;

    private String graphNumber;

    private String useRightType;

    private BigDecimal acquisitionPrice;

    private Date terminationDate;

    private BigDecimal useRightArea;

    private BigDecimal acreage;

    private BigDecimal apportionmentArea;

    private String memo;

    private String registrationAuthority;

    private Date registrationDate;

    private String enable;

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

    public String getLandCertName() {
        return landCertName;
    }

    public void setLandCertName(String landCertName) {
        this.landCertName = landCertName == null ? null : landCertName.trim();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
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

    public String getLandNumber() {
        return landNumber;
    }

    public void setLandNumber(String landNumber) {
        this.landNumber = landNumber == null ? null : landNumber.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getGraphNumber() {
        return graphNumber;
    }

    public void setGraphNumber(String graphNumber) {
        this.graphNumber = graphNumber == null ? null : graphNumber.trim();
    }

    public String getUseRightType() {
        return useRightType;
    }

    public void setUseRightType(String useRightType) {
        this.useRightType = useRightType == null ? null : useRightType.trim();
    }

    public BigDecimal getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(BigDecimal acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public BigDecimal getUseRightArea() {
        return useRightArea;
    }

    public void setUseRightArea(BigDecimal useRightArea) {
        this.useRightArea = useRightArea;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public BigDecimal getApportionmentArea() {
        return apportionmentArea;
    }

    public void setApportionmentArea(BigDecimal apportionmentArea) {
        this.apportionmentArea = apportionmentArea;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getRegistrationAuthority() {
        return registrationAuthority;
    }

    public void setRegistrationAuthority(String registrationAuthority) {
        this.registrationAuthority = registrationAuthority == null ? null : registrationAuthority.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
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