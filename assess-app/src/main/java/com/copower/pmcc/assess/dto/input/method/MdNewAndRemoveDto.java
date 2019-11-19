package com.copower.pmcc.assess.dto.input.method;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;

import java.util.List;

/**
 * Created by wangpc on 2019-11-18.
 */
public class MdNewAndRemoveDto {
    private Integer judgeObjectId;
    private SchemeJudgeObject schemeJudgeObject;
    private SchemeAreaGroup schemeAreaGroup;
    private List<Integer> newMethodList;//新被采用的方法
    private List<Integer> removeMethodList;//将被取消的使用方法

    public Integer getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(Integer judgeObjectId) {
        this.judgeObjectId = judgeObjectId;
    }

    public SchemeJudgeObject getSchemeJudgeObject() {
        return schemeJudgeObject;
    }

    public void setSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        this.schemeJudgeObject = schemeJudgeObject;
    }

    public SchemeAreaGroup getSchemeAreaGroup() {
        return schemeAreaGroup;
    }

    public void setSchemeAreaGroup(SchemeAreaGroup schemeAreaGroup) {
        this.schemeAreaGroup = schemeAreaGroup;
    }

    public List<Integer> getNewMethodList() {
        return newMethodList;
    }

    public void setNewMethodList(List<Integer> newMethodList) {
        this.newMethodList = newMethodList;
    }

    public List<Integer> getRemoveMethodList() {
        return removeMethodList;
    }

    public void setRemoveMethodList(List<Integer> removeMethodList) {
        this.removeMethodList = removeMethodList;
    }
}
