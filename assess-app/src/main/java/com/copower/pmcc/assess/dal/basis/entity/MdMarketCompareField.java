package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class MdMarketCompareField {
    private Integer id;

    private Integer mcId;

    private String name;

    private String value;

    private Boolean bisPrimaryKey;

    private Boolean bisPrice;

    private Boolean bisOnlyView;

    private Integer sorting;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Boolean getBisPrimaryKey() {
        return bisPrimaryKey;
    }

    public void setBisPrimaryKey(Boolean bisPrimaryKey) {
        this.bisPrimaryKey = bisPrimaryKey;
    }

    public Boolean getBisPrice() {
        return bisPrice;
    }

    public void setBisPrice(Boolean bisPrice) {
        this.bisPrice = bisPrice;
    }

    public Boolean getBisOnlyView() {
        return bisOnlyView;
    }

    public void setBisOnlyView(Boolean bisOnlyView) {
        this.bisOnlyView = bisOnlyView;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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