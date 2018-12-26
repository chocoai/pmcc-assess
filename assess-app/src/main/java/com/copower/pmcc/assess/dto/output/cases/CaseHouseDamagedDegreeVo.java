package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegree;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-12-25.
 */
public class CaseHouseDamagedDegreeVo extends CaseHouseDamagedDegree {
    private String typeName;
    private String categoryName;
    private BigDecimal standardScore;
    private String entityConditionName;
    private Boolean hasChildren;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(BigDecimal standardScore) {
        this.standardScore = standardScore;
    }

    public String getEntityConditionName() {
        return entityConditionName;
    }

    public void setEntityConditionName(String entityConditionName) {
        this.entityConditionName = entityConditionName;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
