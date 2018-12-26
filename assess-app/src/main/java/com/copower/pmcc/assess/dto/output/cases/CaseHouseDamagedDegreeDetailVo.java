package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetail;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-12-26.
 */
public class CaseHouseDamagedDegreeDetailVo extends CaseHouseDamagedDegreeDetail {
    private String typeName;
    private BigDecimal standardScore;
    private String entityConditionName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
}
