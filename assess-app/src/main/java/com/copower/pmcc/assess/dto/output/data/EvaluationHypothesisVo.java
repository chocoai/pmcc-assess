package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.EvaluationHypothesis;

/**
 * Created by 13426 on 2018/4/28.
 */
public class EvaluationHypothesisVo extends EvaluationHypothesis {
    private String methodStr ;

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }
}
