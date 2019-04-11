package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;

import java.util.List;

/**
 * Created by kings on 2019-4-9.
 */
public class SurveyJudgeObjectGroupDto {
    private String judgeObjectNumber;
    private Integer judgeObjectId;
    private Integer declareRecordId;
    private String specialcase;
    private List<SurveyAssetInventoryRight> rightList;
    private String result;

    public String getJudgeObjectNumber() {
        return judgeObjectNumber;
    }

    public void setJudgeObjectNumber(String judgeObjectNumber) {
        this.judgeObjectNumber = judgeObjectNumber;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public String getSpecialcase() {
        return specialcase;
    }

    public void setSpecialcase(String specialcase) {
        this.specialcase = specialcase;
    }

    public List<SurveyAssetInventoryRight> getRightList() {
        return rightList;
    }

    public void setRightList(List<SurveyAssetInventoryRight> rightList) {
        this.rightList = rightList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
