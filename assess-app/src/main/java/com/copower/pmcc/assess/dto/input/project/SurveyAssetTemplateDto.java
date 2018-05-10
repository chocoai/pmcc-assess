package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class SurveyAssetTemplateDto extends SurveyAssetTemplate {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date surveyTime;

    @Override
    public Date getSurveyTime() {
        return surveyTime;
    }

    @Override
    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }
}
