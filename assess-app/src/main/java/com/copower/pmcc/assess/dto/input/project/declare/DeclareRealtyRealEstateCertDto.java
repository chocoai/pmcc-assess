package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/20 11:03
 * @Description:
 */
public class DeclareRealtyRealEstateCertDto {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private String province;

    private String city;

    private String district;

    private String certName;

    private String type;

    private String location;

    private String beLocated;

    private String number;

    private String name;

    private String ownership;

    private String publicSituation;

    private String streetNumber;

    private String attachedNumber;

    private String buildingNumber;

    private String unit;

    private String floor;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date registrationTime;

    private Integer nature;

    private String planningUse;

    private Integer floorCount;

    private BigDecimal floorArea;

    private BigDecimal evidenceArea;

    private BigDecimal innerArea;

    private String other;

    private String landNumber;

    private String graphNumber;

    private String roomNumber;

    private String landAcquisition;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date useStartDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date useEndDate;

    private BigDecimal publicArea;

    private String otherNote;

    private String registrationAuthority;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date registrationDate;

    private String useRightType;

    private BigDecimal acquisitionPrice;

    private String purpose;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date terminationDate;

    private String memo;

    private BigDecimal apportionmentArea;

    private BigDecimal acreage;

    private BigDecimal useRightArea;

    private String realEstateUnitNumber;
    private String declareType;
    private String enable;

    private String housingStructure;

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

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
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

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getPlanningUse() {
        return planningUse;
    }

    public void setPlanningUse(String planningUse) {
        this.planningUse = planningUse;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getEvidenceArea() {
        return evidenceArea;
    }

    public void setEvidenceArea(BigDecimal evidenceArea) {
        this.evidenceArea = evidenceArea;
    }

    public BigDecimal getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(BigDecimal innerArea) {
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

    public String getGraphNumber() {
        return graphNumber;
    }

    public void setGraphNumber(String graphNumber) {
        this.graphNumber = graphNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getApportionmentArea() {
        return apportionmentArea;
    }

    public void setApportionmentArea(BigDecimal apportionmentArea) {
        this.apportionmentArea = apportionmentArea;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public BigDecimal getUseRightArea() {
        return useRightArea;
    }

    public void setUseRightArea(BigDecimal useRightArea) {
        this.useRightArea = useRightArea;
    }

    public String getRealEstateUnitNumber() {
        return realEstateUnitNumber;
    }

    public void setRealEstateUnitNumber(String realEstateUnitNumber) {
        this.realEstateUnitNumber = realEstateUnitNumber;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHousingStructure() {
        return housingStructure;
    }

    public void setHousingStructure(String housingStructure) {
        this.housingStructure = housingStructure;
    }
}
