package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterial;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:36
 * @Description:
 */
public class ExamineMatchingMaterialVo extends ExamineMatchingMaterial {
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
