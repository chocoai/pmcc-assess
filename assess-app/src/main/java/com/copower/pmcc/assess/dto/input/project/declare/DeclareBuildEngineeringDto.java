package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:36
 * @Description:
 */
public class DeclareBuildEngineeringDto {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private String province;

    private String city;

    private String district;

    private String occupancyUnit;

    private String beLocated;

    private String name;

    private String structure;

    private BigDecimal buildArea;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date startDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date expectedCompletionDate;

    private String speedProgress;

    private String paymentRatio;

    private String bookNetValue;

    private String bookValue;

    private String declarer;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date declarationDate;

    private String remark;

    private String enable;
    private Integer buildingConstructionPermitId;

    private Integer buildingPermitId;

    private Integer landUsePermitId;

    private Integer preSalePermitId;

    private Integer landId;

    private Integer realEstateId;
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

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public BigDecimal getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(BigDecimal buildArea) {
        this.buildArea = buildArea;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Date expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public String getSpeedProgress() {
        return speedProgress;
    }

    public void setSpeedProgress(String speedProgress) {
        this.speedProgress = speedProgress;
    }

    public String getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(String paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public String getBookNetValue() {
        return bookNetValue;
    }

    public void setBookNetValue(String bookNetValue) {
        this.bookNetValue = bookNetValue;
    }

    public String getBookValue() {
        return bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue;
    }

    public String getDeclarer() {
        return declarer;
    }

    public void setDeclarer(String declarer) {
        this.declarer = declarer;
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
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

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }
}
