package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MdCostApproachTaxes {
    private Integer id;

    private Integer masterId;

    private String typeName;

    private String typeKey;

    private BigDecimal standardFirst;

    private BigDecimal standardSecond;

    private BigDecimal price;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String remark;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey == null ? null : typeKey.trim();
    }

    public BigDecimal getStandardFirst() {
        return standardFirst;
    }

    public void setStandardFirst(BigDecimal standardFirst) {
        this.standardFirst = standardFirst;
    }

    public BigDecimal getStandardSecond() {
        return standardSecond;
    }

    public void setStandardSecond(BigDecimal standardSecond) {
        this.standardSecond = standardSecond;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}