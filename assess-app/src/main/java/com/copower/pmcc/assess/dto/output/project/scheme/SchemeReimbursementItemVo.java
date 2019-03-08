package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItem;

/**
 * Created by kings on 2018-10-9.
 */
public class SchemeReimbursementItemVo extends SchemeReimbursementItem {
    private String judgeObjectName;

    public String getJudgeObjectName() {
        return judgeObjectName;
    }

    public void setJudgeObjectName(String judgeObjectName) {
        this.judgeObjectName = judgeObjectName;
    }
}
