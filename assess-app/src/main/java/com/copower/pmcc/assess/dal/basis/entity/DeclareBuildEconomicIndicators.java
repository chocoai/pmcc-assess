package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeclareBuildEconomicIndicators {
    private Integer id;

    private Integer pid;

    private Integer planDetailsId;

    private String customKey;

    private String name;

    private String huxing;

    private BigDecimal huxingArea;

    private Integer householdCount;

    private Integer number;

    private String content;

    private String childData;

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

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey == null ? null : customKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing == null ? null : huxing.trim();
    }

    public BigDecimal getHuxingArea() {
        return huxingArea;
    }

    public void setHuxingArea(BigDecimal huxingArea) {
        this.huxingArea = huxingArea;
    }

    public Integer getHouseholdCount() {
        return householdCount;
    }

    public void setHouseholdCount(Integer householdCount) {
        this.householdCount = householdCount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getChildData() {
        return childData;
    }

    public void setChildData(String childData) {
        this.childData = childData == null ? null : childData.trim();
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