package com.copower.pmcc.assess.dto.input.project;

/**
 * Created by zly on 2018/5/10.
 */
public class SurveyAssetCommonDataDto {
    private SurveyAssetInventoryDto surveyAssetInventoryDto;
    private SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto;

    public SurveyAssetInventoryDto getSurveyAssetInventoryDto() {
        return surveyAssetInventoryDto;
    }

    public void setSurveyAssetInventoryDto(SurveyAssetInventoryDto surveyAssetInventoryDto) {
        this.surveyAssetInventoryDto = surveyAssetInventoryDto;
    }

    public SurveyAssetOtherTemplateDto getSurveyAssetOtherTemplateDto() {
        return surveyAssetOtherTemplateDto;
    }

    public void setSurveyAssetOtherTemplateDto(SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto) {
        this.surveyAssetOtherTemplateDto = surveyAssetOtherTemplateDto;
    }
}
