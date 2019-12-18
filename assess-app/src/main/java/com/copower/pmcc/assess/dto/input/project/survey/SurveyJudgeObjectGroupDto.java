package com.copower.pmcc.assess.dto.input.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;

import java.util.List;

/**
 * Created by kings on 2019-4-9.
 */
public class SurveyJudgeObjectGroupDto {
    private String judgeObjectNumber;
    private Integer judgeObjectId;
    private Integer declareRecordId;
    private String transferLimit;
    private List<SurveyAssetRightItem> rightList;
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

    public String getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(String transferLimit) {
        this.transferLimit = transferLimit;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<SurveyAssetRightItem> getRightList() {
        return rightList;
    }

    public void setRightList(List<SurveyAssetRightItem> rightList) {
        this.rightList = rightList;
    }
}
