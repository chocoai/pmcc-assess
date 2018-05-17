package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.entity.SurveyCaseStudyDetail;

/**
 * Created by zly on 2018/5/17.
 */
public class SurveyCaseStudyDetailVo extends SurveyCaseStudyDetail {
    private String caseTypeName;
    private String informationSourceName;
    private String correlationCardName;

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getInformationSourceName() {
        return informationSourceName;
    }

    public void setInformationSourceName(String informationSourceName) {
        this.informationSourceName = informationSourceName;
    }

    public String getCorrelationCardName() {
        return correlationCardName;
    }

    public void setCorrelationCardName(String correlationCardName) {
        this.correlationCardName = correlationCardName;
    }
}
