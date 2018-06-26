package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;

/**
 * Created by zly on 2018/5/15.
 */
public class SurveyLocaleExploreDetailDto extends SurveyLocaleExploreDetail {
    public static String SURVEYPICTURE = "surveyPicture";   //查勘图片附件
    public static String SURVEYIMAGE = "surveyImage";   //查勘图像附件
    private String dynamicFormData;

    public String getDynamicFormData() {
        return dynamicFormData;
    }

    public void setDynamicFormData(String dynamicFormData) {
        this.dynamicFormData = dynamicFormData;
    }
}
