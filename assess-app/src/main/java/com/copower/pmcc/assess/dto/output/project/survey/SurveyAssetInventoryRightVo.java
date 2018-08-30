package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;

/**
 * Created by zly on 2018/6/12.
 */
public class SurveyAssetInventoryRightVo extends SurveyAssetInventoryRight {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
