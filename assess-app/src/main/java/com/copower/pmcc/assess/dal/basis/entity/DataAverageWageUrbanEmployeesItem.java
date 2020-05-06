package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataAverageWageUrbanEmployeesItem {
    private Integer id;

    private Integer masterId;

    private BigDecimal stateOwnedEconomy;

    private BigDecimal collectiveEconomy;

    private BigDecimal privateEconomy;

    private BigDecimal otherEconomy;

    private BigDecimal total;

    private String type;

    private String category;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
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