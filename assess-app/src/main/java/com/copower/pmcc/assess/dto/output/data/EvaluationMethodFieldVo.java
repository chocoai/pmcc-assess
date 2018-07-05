package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethodField;

/**
 * Created by 13426 on 2018/4/24.
 */
public class EvaluationMethodFieldVo extends EvaluationMethodField {
    private String typeStr ;
    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
