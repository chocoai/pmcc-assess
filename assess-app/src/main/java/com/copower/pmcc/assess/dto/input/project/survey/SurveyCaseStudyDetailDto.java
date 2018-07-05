package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyCaseStudyDetail;

/**
 * Created by zly on 2018/5/18.
 */
public class SurveyCaseStudyDetailDto extends SurveyCaseStudyDetail{
    private String dynamicFormData;

    public String getDynamicFormData() {
        return dynamicFormData;
    }

    public void setDynamicFormData(String dynamicFormData) {
        this.dynamicFormData = dynamicFormData;
    }
}
