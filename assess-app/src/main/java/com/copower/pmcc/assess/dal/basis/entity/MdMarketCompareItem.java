package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdMarketCompareItem {
    private Integer id;

    private Integer mcId;

    private String name;

    private Integer type;

    private Integer planDetailsId;

    private Integer basicApplyId;

    private String jsonContent;

    private String tradingTimeExplain;

    private Integer residueRatioId;

    private Integer usedYear;

    private Integer usableYear;

    private Integer houseId;

    private BigDecimal area;

    private BigDecimal initialPrice;

    private String priceConnotation;

    private Boolean mustAdjustPrice;

    private BigDecimal annualCoefficient;

    private BigDecimal volumeRatioCoefficient;

    private String specificPrice;

    private String correctionDifference;

    private String caseDifference;

    private String weight;

    private String weightDescription;

    private BigDecimal averagePrice;

    private BigDecimal deveDegree;

    private BigDecimal evaluatePrice;

    private BigDecimal locationFactorRatio;

    private BigDecimal equityFactorRatio;

    private BigDecimal entityFactorRatio;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getBasicApplyId() {
        return basicApplyId;
    }

    public void setBasicApplyId(Integer basicApplyId) {
        this.basicApplyId = basicApplyId;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent == null ? null : jsonContent.trim();
    }

    public String getTradingTimeExplain() {
        return tradingTimeExplain;
    }

    public void setTradingTimeExplain(String tradingTimeExplain) {
        this.tradingTimeExplain = tradingTimeExplain == null ? null : tradingTimeExplain.trim();
    }

    public Integer getResidueRatioId() {
        return residueRatioId;
    }

    public void setResidueRatioId(Integer residueRatioId) {
        this.residueRatioId = residueRatioId;
    }

    public Integer getUsedYear() {
        return usedYear;
    }

    public void setUsedYear(Integer usedYear) {
        this.usedYear = usedYear;
    }

    public Integer getUsableYear() {
        return usableYear;
    }

    public void setUsableYear(Integer usableYear) {
        this.usableYear = usableYear;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getPriceConnotation() {
        return priceConnotation;
    }

    public void setPriceConnotation(String priceConnotation) {
        this.priceConnotation = priceConnotation == null ? null : priceConnotation.trim();
    }

    public Boolean getMustAdjustPrice() {
        return mustAdjustPrice;
    }

    public void setMustAdjustPrice(Boolean mustAdjustPrice) {
        this.mustAdjustPrice = mustAdjustPrice;
    }

    public BigDecimal getAnnualCoefficient() {
        return annualCoefficient;
    }

    public void setAnnualCoefficient(BigDecimal annualCoefficient) {
        this.annualCoefficient = annualCoefficient;
    }

    public BigDecimal getVolumeRatioCoefficient() {
        return volumeRatioCoefficient;
    }

    public void setVolumeRatioCoefficient(BigDecimal volumeRatioCoefficient) {
        this.volumeRatioCoefficient = volumeRatioCoefficient;
    }

    public String getSpecificPrice() {
        return specificPrice;
    }

    public void setSpecificPrice(String specificPrice) {
        this.specificPrice = specificPrice == null ? null : specificPrice.trim();
    }

    public String getCorrectionDifference() {
        return correctionDifference;
    }

    public void setCorrectionDifference(String correctionDifference) {
        this.correctionDifference = correctionDifference == null ? null : correctionDifference.trim();
    }

    public String getCaseDifference() {
        return caseDifference;
    }

    public void setCaseDifference(String caseDifference) {
        this.caseDifference = caseDifference == null ? null : caseDifference.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getWeightDescription() {
        return weightDescription;
    }

    public void setWeightDescription(String weightDescription) {
        this.weightDescription = weightDescription == null ? null : weightDescription.trim();
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getDeveDegree() {
        return deveDegree;
    }

    public void setDeveDegree(BigDecimal deveDegree) {
        this.deveDegree = deveDegree;
    }

    public BigDecimal getEvaluatePrice() {
        return evaluatePrice;
    }

    public void setEvaluatePrice(BigDecimal evaluatePrice) {
        this.evaluatePrice = evaluatePrice;
    }

    public BigDecimal getLocationFactorRatio() {
        return locationFactorRatio;
    }

    public void setLocationFactorRatio(BigDecimal locationFactorRatio) {
        this.locationFactorRatio = locationFactorRatio;
    }

    public BigDecimal getEquityFactorRatio() {
        return equityFactorRatio;
    }

    public void setEquityFactorRatio(BigDecimal equityFactorRatio) {
        this.equityFactorRatio = equityFactorRatio;
    }

    public BigDecimal getEntityFactorRatio() {
        return entityFactorRatio;
    }

    public void setEntityFactorRatio(BigDecimal entityFactorRatio) {
        this.entityFactorRatio = entityFactorRatio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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