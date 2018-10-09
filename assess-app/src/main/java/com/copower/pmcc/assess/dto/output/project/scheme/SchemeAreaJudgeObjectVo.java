package com.copower.pmcc.assess.dto.output.project.scheme;

import java.util.List;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeAreaJudgeObjectVo {
    private Integer areaGroupId;
    private String areaGroupName;
    private List<SchemeJudgeObjectVo> judgeObjectVoList;

    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public String getAreaGroupName() {
        return areaGroupName;
    }

    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName;
    }

    public List<SchemeJudgeObjectVo> getJudgeObjectVoList() {
        return judgeObjectVoList;
    }

    public void setJudgeObjectVoList(List<SchemeJudgeObjectVo> judgeObjectVoList) {
        this.judgeObjectVoList = judgeObjectVoList;
    }
}
