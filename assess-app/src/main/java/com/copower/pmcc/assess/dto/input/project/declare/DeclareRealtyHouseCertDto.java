package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/19 16:34
 * @Description:
 */
public class DeclareRealtyHouseCertDto {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private String certName;

    private String province;

    private String city;

    private String district;
    private String type;

    private String number;

    private String ownership;

    private String publicSituation;

    private String beLocated;

    private String streetNumber;

    private String attachedNumber;

    private String buildingNumber;

    private String unit;

    private String floor;

    private String roomNumber;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date registrationTime;

    private String nature;

    private String floorCount;

    private String planningUse;

    private String floorArea;

    private String location;

    private BigDecimal evidenceArea;

    private String innerArea;

    private String other;

    private String landNumber;

    private String landAcquisition;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date useStartDate;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date useEndDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date landRegistrationDate;
    private BigDecimal publicArea;

    private String otherNote;

    private String registrationAuthority;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date registrationDate;
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

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
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

    public String getPublicSituation() {
        return publicSituation;
    }

    public void setPublicSituation(String publicSituation) {
        this.publicSituation = publicSituation;
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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(String floorCount) {
        this.floorCount = floorCount;
    }

    public String getPlanningUse() {
        return planningUse;
    }

    public void setPlanningUse(String planningUse) {
        this.planningUse = planningUse;
    }

    public String getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getEvidenceArea() {
        return evidenceArea;
    }

    public void setEvidenceArea(BigDecimal evidenceArea) {
        this.evidenceArea = evidenceArea;
    }

    public String getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(String innerArea) {
        this.innerArea = innerArea;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getLandNumber() {
        return landNumber;
    }

    public void setLandNumber(String landNumber) {
        this.landNumber = landNumber;
    }

    public String getLandAcquisition() {
        return landAcquisition;
    }

    public void setLandAcquisition(String landAcquisition) {
        this.landAcquisition = landAcquisition;
    }

    public Date getUseStartDate() {
        return useStartDate;
    }

    public void setUseStartDate(Date useStartDate) {
        this.useStartDate = useStartDate;
    }

    public Date getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    public BigDecimal getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(BigDecimal publicArea) {
        this.publicArea = publicArea;
    }

    public String getOtherNote() {
        return otherNote;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote;
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

    public Date getLandRegistrationDate() {
        return landRegistrationDate;
    }

    public void setLandRegistrationDate(Date landRegistrationDate) {
        this.landRegistrationDate = landRegistrationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }
}
