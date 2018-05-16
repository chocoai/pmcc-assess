package com.copower.pmcc.assess.dto.input.project;

/**
 * Created by zly on 2018/5/16.
 */
public class SurveyLocaleCommonDataDto {

    private SurveyLocaleExploreDetailDto surveyLocaleExploreDto;
    private SurveyLocaleCorrelationCardDto surveyLocaleCorrelationCardDto;

    public SurveyLocaleExploreDetailDto getSurveyLocaleExploreDto() {
        return surveyLocaleExploreDto;
    }

    public void setSurveyLocaleExploreDto(SurveyLocaleExploreDetailDto surveyLocaleExploreDto) {
        this.surveyLocaleExploreDto = surveyLocaleExploreDto;
    }
}
