package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;

/**
 * @author: zch
 * @date: 2019/5/5 10:42
 * @description:
 */
public class DataLandDetailAchievementVo extends DataLandDetailAchievement {
    private String typeName;
    private String categoryName;
    private String gradeName;

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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
