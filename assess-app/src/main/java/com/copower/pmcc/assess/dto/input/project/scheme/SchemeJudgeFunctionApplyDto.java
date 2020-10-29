package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeJudgeFunctionApplyDto {
    private Integer areaId;
    private Integer judgeObjectId;
    private String notApplicableReason;
    private String singleMethodRationale;
    private List<SchemeJudgeFunction> judgeFunctions;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public String getNotApplicableReason() {
        return notApplicableReason;
    }

    public void setNotApplicableReason(String notApplicableReason) {
        this.notApplicableReason = notApplicableReason;
    }

    public List<SchemeJudgeFunction> getJudgeFunctions() {
        return judgeFunctions;
    }

    public void setJudgeFunctions(List<SchemeJudgeFunction> judgeFunctions) {
        this.judgeFunctions = judgeFunctions;
    }

    public String getSingleMethodRationale() {
        return singleMethodRationale;
    }

    public void setSingleMethodRationale(String singleMethodRationale) {
        this.singleMethodRationale = singleMethodRationale;
    }
}
