package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/20 10:58
 * @Description:
 */
public class DeclareRealtyLandCertDto {
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
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date terminationDate;

    private BigDecimal useRightArea;

    private BigDecimal acreage;

    private BigDecimal apportionmentArea;

    private String memo;

    private String registrationAuthority;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date registrationDate;

    private String enable;

    private String creator;
    private String declareType;

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
        this.landCertName = landCertName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAttachedNumber() {
        return attachedNumber;
    }

    public void setAttachedNumber(String attachedNumber) {
        this.attachedNumber = attachedNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getLandNumber() {
        return landNumber;
    }

    public void setLandNumber(String landNumber) {
        this.landNumber = landNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getGraphNumber() {
        return graphNumber;
    }

    public void setGraphNumber(String graphNumber) {
        this.graphNumber = graphNumber;
    }

    public String getUseRightType() {
        return useRightType;
    }

    public void setUseRightType(String useRightType) {
        this.useRightType = useRightType;
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
        this.memo = memo;
    }

    public String getRegistrationAuthority() {
        return registrationAuthority;
    }

    public void setRegistrationAuthority(String registrationAuthority) {
        this.registrationAuthority = registrationAuthority;
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
        this.enable = enable;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }
}
