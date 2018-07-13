package com.copower.pmcc.assess.dto.input.project.survey;

/**
 * Created by kings on 2018-7-13.
 */
public class SurveyExamineTaskDto {
    private Integer planDetailsId;
    private String examineFormType;
    private Integer declareRecordId;
    private Integer examineType;

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getExamineFormType() {
        return examineFormType;
    }

    public void setExamineFormType(String examineFormType) {
        this.examineFormType = examineFormType;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }
}
