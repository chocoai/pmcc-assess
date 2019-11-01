package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicMatchingEnvironment {
    private Integer id;

    private Integer estateId;

    private String type;

    private Integer category;

    private Integer influenceDegree;

    private String humanImpact;

    private String remark;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getInfluenceDegree() {
        return influenceDegree;
    }

    public void setInfluenceDegree(Integer influenceDegree) {
        this.influenceDegree = influenceDegree;
    }

    public String getHumanImpact() {
        return humanImpact;
    }

    public void setHumanImpact(String humanImpact) {
        this.humanImpact = humanImpact == null ? null : humanImpact.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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