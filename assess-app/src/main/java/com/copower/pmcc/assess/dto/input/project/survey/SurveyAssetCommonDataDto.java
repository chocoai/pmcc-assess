package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;

import java.util.List;

/**
 * Created by zly on 2018/5/10.
 */
public class SurveyAssetCommonDataDto {

    private SurveyAssetInventory surveyAssetInventory;
    private List<SurveyAssetInventoryContent> assetInventoryContentList;

    public SurveyAssetInventory getSurveyAssetInventory() {
        return surveyAssetInventory;
    }

    public void setSurveyAssetInventory(SurveyAssetInventory surveyAssetInventory) {
        this.surveyAssetInventory = surveyAssetInventory;
    }

    public List<SurveyAssetInventoryContent> getAssetInventoryContentList() {
        return assetInventoryContentList;
    }

    public void setAssetInventoryContentList(List<SurveyAssetInventoryContent> assetInventoryContentList) {
        this.assetInventoryContentList = assetInventoryContentList;
    }
}
