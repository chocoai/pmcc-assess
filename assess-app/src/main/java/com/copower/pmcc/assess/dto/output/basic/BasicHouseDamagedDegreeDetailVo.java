package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegreeDetail;

import java.math.BigDecimal;

/**
 * Created by kings on 2018-12-25.
 */
public class BasicHouseDamagedDegreeDetailVo extends BasicHouseDamagedDegreeDetail {
    private String typeName;
    private BigDecimal standardScore;
    private String entityConditionName;
    private String intact;
    private String basicallyIntact;
    private String generalDamage;
    private String seriousDamage;

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

    public String getIntact() {
        return intact;
    }

    public void setIntact(String intact) {
        this.intact = intact;
    }

    public String getBasicallyIntact() {
        return basicallyIntact;
    }

    public void setBasicallyIntact(String basicallyIntact) {
        this.basicallyIntact = basicallyIntact;
    }

    public String getGeneralDamage() {
        return generalDamage;
    }

    public void setGeneralDamage(String generalDamage) {
        this.generalDamage = generalDamage;
    }

    public String getSeriousDamage() {
        return seriousDamage;
    }

    public void setSeriousDamage(String seriousDamage) {
        this.seriousDamage = seriousDamage;
    }
}
