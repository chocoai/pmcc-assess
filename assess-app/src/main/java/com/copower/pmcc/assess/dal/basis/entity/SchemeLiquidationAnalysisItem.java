package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SchemeLiquidationAnalysisItem {
    private Integer id;

    private Integer areaId;

    private Integer judgeObjectId;

    private Integer planDetailsId;

    private Integer groupId;

    private String typeKey;

    private Integer taxRateId;

    private String taxRateValue;

    private Integer calculationMethod;

    private String taxRateName;

    private String calculateBase;

    private String calculationFormula;

    private String taxesBurden;

    private BigDecimal price;

    private String remark;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private BigDecimal sellerScale;

    private BigDecimal buyerScale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey == null ? null : typeKey.trim();
    }

    public Integer getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    public String getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(String taxRateValue) {
        this.taxRateValue = taxRateValue == null ? null : taxRateValue.trim();
    }

    public Integer getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(Integer calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getTaxRateName() {
        return taxRateName;
    }

    public void setTaxRateName(String taxRateName) {
        this.taxRateName = taxRateName == null ? null : taxRateName.trim();
    }

    public String getCalculateBase() {
        return calculateBase;
    }

    public void setCalculateBase(String calculateBase) {
        this.calculateBase = calculateBase == null ? null : calculateBase.trim();
    }

    public String getCalculationFormula() {
        return calculationFormula;
    }

    public void setCalculationFormula(String calculationFormula) {
        this.calculationFormula = calculationFormula == null ? null : calculationFormula.trim();
    }

    public String getTaxesBurden() {
        return taxesBurden;
    }

    public void setTaxesBurden(String taxesBurden) {
        this.taxesBurden = taxesBurden == null ? null : taxesBurden.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public BigDecimal getSellerScale() {
        return sellerScale;
    }

    public void setSellerScale(BigDecimal sellerScale) {
        this.sellerScale = sellerScale;
    }

    public BigDecimal getBuyerScale() {
        return buyerScale;
    }

    public void setBuyerScale(BigDecimal buyerScale) {
        this.buyerScale = buyerScale;
    }
}