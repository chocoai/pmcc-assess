package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;

import java.util.List;

/**
 * Created by 13426 on 2018/5/21.
 */
public class SchemeJudgeFunctionApplyDto {
    private Integer areaGroupId;
    private Integer groupNumber;
    private List<SchemeJudgeFunction> judgeFunctionList;

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<SchemeJudgeFunction> getJudgeFunctionList() {
        return judgeFunctionList;
    }

    public void setJudgeFunctionList(List<SchemeJudgeFunction> judgeFunctionList) {
        this.judgeFunctionList = judgeFunctionList;
    }
}
