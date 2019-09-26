package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ToolResidueRatioObserve {
    private Integer id;

    private Integer masterId;

    private Integer type;

    private Integer category;

    private String entityCondition;

    private String entityConditionContent;

    private BigDecimal standardScore;

    private BigDecimal score;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getEntityCondition() {
        return entityCondition;
    }

    public void setEntityCondition(String entityCondition) {
        this.entityCondition = entityCondition == null ? null : entityCondition.trim();
    }

    public String getEntityConditionContent() {
        return entityConditionContent;
    }

    public void setEntityConditionContent(String entityConditionContent) {
        this.entityConditionContent = entityConditionContent == null ? null : entityConditionContent.trim();
    }

    public BigDecimal getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(BigDecimal standardScore) {
        this.standardScore = standardScore;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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