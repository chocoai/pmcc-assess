package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;

import java.io.Serializable;

/**
 * @author: zch
 * @date: 2019/5/5 10:42
 * @description:
 */
public class DataLandLevelDetailAchievementVo extends DataLandLevelDetailAchievement implements Serializable {
    private String typeName;
    private String gradeName;
    private String categoryName;
    private String modelStr;

    public String getModelStr() {
        return modelStr;
    }

    public void setModelStr(String modelStr) {
        this.modelStr = modelStr;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
