package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterial;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:51
 * @Description:
 */
public class CaseMatchingMaterialVo  extends CaseMatchingMaterial {
    private String categoryName;

    private String scaleName;

    private String distanceName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }
}
