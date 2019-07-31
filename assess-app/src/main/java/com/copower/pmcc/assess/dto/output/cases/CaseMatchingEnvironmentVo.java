package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironment;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:48
 * @Description:
 */
public class CaseMatchingEnvironmentVo extends CaseMatchingEnvironment {
    private String typeName;

    private String categoryName;

    private String humanImpactName;

    private String influenceDegreeName;

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

    public String getInfluenceDegreeName() {
        return influenceDegreeName;
    }

    public void setInfluenceDegreeName(String influenceDegreeName) {
        this.influenceDegreeName = influenceDegreeName;
    }

    public String getHumanImpactName() {
        return humanImpactName;
    }

    public void setHumanImpactName(String humanImpactName) {
        this.humanImpactName = humanImpactName;
    }
}
