package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlace;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:49
 * @Description:
 */
public class CaseMatchingLeisurePlaceVo extends CaseMatchingLeisurePlace {
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
