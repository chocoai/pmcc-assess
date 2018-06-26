package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class SurveyAssetTemplateDto extends SurveyAssetTemplate {
    public static String CREDENTIALACCESSORY = "credentialAccessory";   //附件字段
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
