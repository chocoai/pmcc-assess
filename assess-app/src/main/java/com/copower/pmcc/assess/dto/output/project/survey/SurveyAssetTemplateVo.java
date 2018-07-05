package com.copower.pmcc.assess.dto.output.project.survey;


import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetTemplate;

public class SurveyAssetTemplateVo extends SurveyAssetTemplate {

    private String inventoryContentName;

    public String getInventoryContentName() {
        return inventoryContentName;
    }

    public void setInventoryContentName(String inventoryContentName) {
        this.inventoryContentName = inventoryContentName;
    }
}
