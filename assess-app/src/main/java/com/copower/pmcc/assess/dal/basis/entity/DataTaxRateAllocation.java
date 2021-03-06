package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataTaxRateAllocation {
    private Integer id;

    private String province;

    private String city;

    private String district;

    private Integer type;

    private String exExplain;

    private BigDecimal taxRate;

    private BigDecimal amount;

    private Boolean bisNationalUnity;

    private String calculateBase;

    private String calculationFormula;

    private Integer taxesBurden;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String fieldName;

    private Integer sorting;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExExplain() {
        return exExplain;
    }

    public void setExExplain(String exExplain) {
        this.exExplain = exExplain == null ? null : exExplain.trim();
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getBisNationalUnity() {
        return bisNationalUnity;
    }

    public void setBisNationalUnity(Boolean bisNationalUnity) {
        this.bisNationalUnity = bisNationalUnity;
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

    public Integer getTaxesBurden() {
        return taxesBurden;
    }

    public void setTaxesBurden(Integer taxesBurden) {
        this.taxesBurden = taxesBurden;
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }
}