package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeJudgeObjectVo extends SchemeJudgeObject {
    private String setUseName;
    private String bestUseName;

    public String getSetUseName() {
        return setUseName;
    }

    public void setSetUseName(String setUseName) {
        this.setUseName = setUseName;
    }

    public String getBestUseName() {
        return bestUseName;
    }

    public void setBestUseName(String bestUseName) {
        this.bestUseName = bestUseName;
    }
}
