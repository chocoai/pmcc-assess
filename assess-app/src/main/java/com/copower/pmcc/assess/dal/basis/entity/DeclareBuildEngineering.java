package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareBuildEngineering {
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

    private Date startDate;

    private Date expectedCompletionDate;

    private String speedProgress;

    private String paymentRatio;

    private String bookNetValue;

    private String bookValue;

    private String declarer;

    private Date declarationDate;

    private String remark;

    private String enable;

    private String declareType;

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

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated == null ? null : beLocated.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure == null ? null : structure.trim();
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
        this.speedProgress = speedProgress == null ? null : speedProgress.trim();
    }

    public String getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(String paymentRatio) {
        this.paymentRatio = paymentRatio == null ? null : paymentRatio.trim();
    }

    public String getBookNetValue() {
        return bookNetValue;
    }

    public void setBookNetValue(String bookNetValue) {
        this.bookNetValue = bookNetValue == null ? null : bookNetValue.trim();
    }

    public String getBookValue() {
        return bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue == null ? null : bookValue.trim();
    }

    public String getDeclarer() {
        return declarer;
    }

    public void setDeclarer(String declarer) {
        this.declarer = declarer == null ? null : declarer.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType == null ? null : declareType.trim();
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