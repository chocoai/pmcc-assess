package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeJudgeFunctionApplyDto {
    private Integer judgeId;
    private List<SchemeJudgeFunction> judgeFunctionList;

    public Integer getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Integer judgeId) {
        this.judgeId = judgeId;
    }

    public List<SchemeJudgeFunction> getJudgeFunctionList() {
        return judgeFunctionList;
    }

    public void setJudgeFunctionList(List<SchemeJudgeFunction> judgeFunctionList) {
        this.judgeFunctionList = judgeFunctionList;
    }
}
