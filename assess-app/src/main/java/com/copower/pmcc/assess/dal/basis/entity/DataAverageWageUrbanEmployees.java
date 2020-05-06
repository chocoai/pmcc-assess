package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataAverageWageUrbanEmployees {
    private Integer id;

    private String province;

    private String city;

    private String district;

    private Date date;

    private BigDecimal stateOwnedEconomy;

    private BigDecimal collectiveEconomy;

    private BigDecimal privateEconomy;

    private BigDecimal otherEconomy;

    private BigDecimal total;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getStateOwnedEconomy() {
        return stateOwnedEconomy;
    }

    public void setStateOwnedEconomy(BigDecimal stateOwnedEconomy) {
        this.stateOwnedEconomy = stateOwnedEconomy;
    }

    public BigDecimal getCollectiveEconomy() {
        return collectiveEconomy;
    }

    public void setCollectiveEconomy(BigDecimal collectiveEconomy) {
        this.collectiveEconomy = collectiveEconomy;
    }

    public BigDecimal getPrivateEconomy() {
        return privateEconomy;
    }

    public void setPrivateEconomy(BigDecimal privateEconomy) {
        this.privateEconomy = privateEconomy;
    }

    public BigDecimal getOtherEconomy() {
        return otherEconomy;
    }

    public void setOtherEconomy(BigDecimal otherEconomy) {
        this.otherEconomy = otherEconomy;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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