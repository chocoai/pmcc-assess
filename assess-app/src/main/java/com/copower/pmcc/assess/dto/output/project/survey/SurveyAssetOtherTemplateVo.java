package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetOtherTemplate;

/**
 * Created by zly on 2018/6/12.
 */
public class SurveyAssetOtherTemplateVo extends SurveyAssetOtherTemplate{
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
