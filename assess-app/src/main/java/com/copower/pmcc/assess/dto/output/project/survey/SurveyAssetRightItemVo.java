package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;

/**
 * Created by zch on 2019-12-17.
 */
public class SurveyAssetRightItemVo extends SurveyAssetRightItem {
    private String typeName;
    private String categoryName;

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
}
