package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlace;

/**
 * @Auther: zch
 * @Date: 2018/7/20 18:10
 * @Description:
 */
public class ExamineMatchingLeisurePlaceVo extends ExamineMatchingLeisurePlace {
    private String categoryName;

    private String gradeName;

    private String distanceName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }
}
