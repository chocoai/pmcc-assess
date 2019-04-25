package com.copower.pmcc.assess.dto.input.project.scheme;

/**
 * Created by kings on 2019-4-25.
 */
public class SchemeJudgeObjectSimpleDto {
    private Integer declareRecordId;
    private Integer judgeObjectId;
    private Integer number;
    private String transferLimit;
    private String rightOther;
    private String socialPrestige;

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(String transferLimit) {
        this.transferLimit = transferLimit;
    }

    public String getRightOther() {
        return rightOther;
    }

    public void setRightOther(String rightOther) {
        this.rightOther = rightOther;
    }

    public String getSocialPrestige() {
        return socialPrestige;
    }

    public void setSocialPrestige(String socialPrestige) {
        this.socialPrestige = socialPrestige;
    }
}
