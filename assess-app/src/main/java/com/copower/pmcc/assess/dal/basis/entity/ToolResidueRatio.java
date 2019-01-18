package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ToolResidueRatio {
    private Integer id;

    private Integer type;

    private Integer houseId;

    private String parameterValue;

    private BigDecimal usableYear;

    private BigDecimal usedYear;

    private BigDecimal observeRate;

    private BigDecimal ageRate;

    private String resultValue;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue == null ? null : parameterValue.trim();
    }

    public BigDecimal getUsableYear() {
        return usableYear;
    }

    public void setUsableYear(BigDecimal usableYear) {
        this.usableYear = usableYear;
    }

    public BigDecimal getUsedYear() {
        return usedYear;
    }

    public void setUsedYear(BigDecimal usedYear) {
        this.usedYear = usedYear;
    }

    public BigDecimal getObserveRate() {
        return observeRate;
    }

    public void setObserveRate(BigDecimal observeRate) {
        this.observeRate = observeRate;
    }

    public BigDecimal getAgeRate() {
        return ageRate;
    }

    public void setAgeRate(BigDecimal ageRate) {
        this.ageRate = ageRate;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue == null ? null : resultValue.trim();
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