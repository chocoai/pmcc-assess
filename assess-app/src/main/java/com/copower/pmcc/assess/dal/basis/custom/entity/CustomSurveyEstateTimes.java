package com.copower.pmcc.assess.dal.basis.custom.entity;

public class CustomSurveyEstateTimes {
    private String estateName;
    private Integer surveyCount;
    private String projectName;
    private String surveyDate;

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(Integer surveyCount) {
        this.surveyCount = surveyCount;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}
