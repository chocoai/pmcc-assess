package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunction;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeJudgeFunctionApplyDto {
    private List<SchemeJudgeFunction> judgeFunctionList;

    public List<SchemeJudgeFunction> getJudgeFunctionList() {
        return judgeFunctionList;
    }

    public void setJudgeFunctionList(List<SchemeJudgeFunction> judgeFunctionList) {
        this.judgeFunctionList = judgeFunctionList;
    }
}
