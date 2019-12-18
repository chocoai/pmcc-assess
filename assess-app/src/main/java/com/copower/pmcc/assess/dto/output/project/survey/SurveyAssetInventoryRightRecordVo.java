package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroup;

/**
 * @author: zch
 * @date: 2019/4/1 13:59
 * @description:
 */
public class SurveyAssetInventoryRightRecordVo extends SurveyAssetRightGroup {
    private String recordNames;

    public String getRecordNames() {
        return recordNames;
    }

    public void setRecordNames(String recordNames) {
        this.recordNames = recordNames;
    }
}
