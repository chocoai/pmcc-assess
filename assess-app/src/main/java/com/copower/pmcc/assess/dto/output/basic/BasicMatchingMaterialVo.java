package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMaterial;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:11
 * @Description:
 */
public class BasicMatchingMaterialVo extends BasicMatchingMaterial {
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
