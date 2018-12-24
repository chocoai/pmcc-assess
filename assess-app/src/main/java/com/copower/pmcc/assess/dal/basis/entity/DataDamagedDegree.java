package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DataDamagedDegree {
    private Integer id;

    private Integer pid;

    private String name;

    private String fieldName;

    private BigDecimal standardScore;

    private BigDecimal weight;

    private String intact;

    private String basicallyIntact;

    private String generalDamage;

    private String seriousDamage;

    private Boolean bisEnable;

    private Boolean bisDelete;

    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public BigDecimal getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(BigDecimal standardScore) {
        this.standardScore = standardScore;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getIntact() {
        return intact;
    }

    public void setIntact(String intact) {
        this.intact = intact == null ? null : intact.trim();
    }

    public String getBasicallyIntact() {
        return basicallyIntact;
    }

    public void setBasicallyIntact(String basicallyIntact) {
        this.basicallyIntact = basicallyIntact == null ? null : basicallyIntact.trim();
    }

    public String getGeneralDamage() {
        return generalDamage;
    }

    public void setGeneralDamage(String generalDamage) {
        this.generalDamage = generalDamage == null ? null : generalDamage.trim();
    }

    public String getSeriousDamage() {
        return seriousDamage;
    }

    public void setSeriousDamage(String seriousDamage) {
        this.seriousDamage = seriousDamage == null ? null : seriousDamage.trim();
    }

    public Boolean getBisEnable() {
        return bisEnable;
    }

    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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