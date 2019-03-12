package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SchemeJudgeObject {
    private Integer id;

    private Integer pid;

    private Integer projectId;

    private Integer areaGroupId;

    private Integer originalAreaGroupId;

    private Integer declareRecordId;

    private String number;

    private String originalNumber;

    private Integer splitNumber;

    private String name;

    private String certName;

    private String ownership;

    private String seat;

    private String certUse;

    private String practicalUse;

    private String landCertUse;

    private String landPracticalUse;

    private Date landUseEndDate;

    private BigDecimal landRemainingYear;

    private Integer setUse;

    private Integer bestUse;

    private BigDecimal floorArea;

    private BigDecimal evaluationArea;

    private BigDecimal price;

    private BigDecimal originalPrice;

    private String rentalPossessionDesc;

    private String collateralFound;

    private String liquidRatio;

    private String liquidRatioExplain;

    private Integer standardJudgeId;

    private String standardJudgeExplain;

    private String notApplicableReason;

    private Boolean bisSplit;

    private Boolean bisMerge;

    private Boolean bisEnable;

    private Boolean bisSetFunction;

    private Integer sorting;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Integer getOriginalAreaGroupId() {
        return originalAreaGroupId;
    }

    public void setOriginalAreaGroupId(Integer originalAreaGroupId) {
        this.originalAreaGroupId = originalAreaGroupId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

    public void setOriginalNumber(String originalNumber) {
        this.originalNumber = originalNumber == null ? null : originalNumber.trim();
    }

    public Integer getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
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

    public String getLandPracticalUse() {
        return landPracticalUse;
    }

    public void setLandPracticalUse(String landPracticalUse) {
        this.landPracticalUse = landPracticalUse == null ? null : landPracticalUse.trim();
    }

    public Date getLandUseEndDate() {
        return landUseEndDate;
    }

    public void setLandUseEndDate(Date landUseEndDate) {
        this.landUseEndDate = landUseEndDate;
    }

    public BigDecimal getLandRemainingYear() {
        return landRemainingYear;
    }

    public void setLandRemainingYear(BigDecimal landRemainingYear) {
        this.landRemainingYear = landRemainingYear;
    }

    public Integer getSetUse() {
        return setUse;
    }

    public void setSetUse(Integer setUse) {
        this.setUse = setUse;
    }

    public Integer getBestUse() {
        return bestUse;
    }

    public void setBestUse(Integer bestUse) {
        this.bestUse = bestUse;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getEvaluationArea() {
        return evaluationArea;
    }

    public void setEvaluationArea(BigDecimal evaluationArea) {
        this.evaluationArea = evaluationArea;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getRentalPossessionDesc() {
        return rentalPossessionDesc;
    }

    public void setRentalPossessionDesc(String rentalPossessionDesc) {
        this.rentalPossessionDesc = rentalPossessionDesc == null ? null : rentalPossessionDesc.trim();
    }

    public String getCollateralFound() {
        return collateralFound;
    }

    public void setCollateralFound(String collateralFound) {
        this.collateralFound = collateralFound == null ? null : collateralFound.trim();
    }

    public String getLiquidRatio() {
        return liquidRatio;
    }

    public void setLiquidRatio(String liquidRatio) {
        this.liquidRatio = liquidRatio == null ? null : liquidRatio.trim();
    }

    public String getLiquidRatioExplain() {
        return liquidRatioExplain;
    }

    public void setLiquidRatioExplain(String liquidRatioExplain) {
        this.liquidRatioExplain = liquidRatioExplain == null ? null : liquidRatioExplain.trim();
    }

    public Integer getStandardJudgeId() {
        return standardJudgeId;
    }

    public void setStandardJudgeId(Integer standardJudgeId) {
        this.standardJudgeId = standardJudgeId;
    }

    public String getStandardJudgeExplain() {
        return standardJudgeExplain;
    }

    public void setStandardJudgeExplain(String standardJudgeExplain) {
        this.standardJudgeExplain = standardJudgeExplain == null ? null : standardJudgeExplain.trim();
    }

    public String getNotApplicableReason() {
        return notApplicableReason;
    }

    public void setNotApplicableReason(String notApplicableReason) {
        this.notApplicableReason = notApplicableReason == null ? null : notApplicableReason.trim();
    }

    public Boolean getBisSplit() {
        return bisSplit;
    }

    public void setBisSplit(Boolean bisSplit) {
        this.bisSplit = bisSplit;
    }

    public Boolean getBisMerge() {
        return bisMerge;
    }

    public void setBisMerge(Boolean bisMerge) {
        this.bisMerge = bisMerge;
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisSetFunction() {
        return bisSetFunction;
    }

    public void setBisSetFunction(Boolean bisSetFunction) {
        this.bisSetFunction = bisSetFunction;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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