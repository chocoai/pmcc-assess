package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.EvaluationThinking;

/**
 * Created by 13426 on 2018/4/27.
 */
public class EvaluationThinkingVo extends EvaluationThinking {
    private String methodStr ;

    @Override
    public String toString() {
        return "EvaluationMethodVo{" +
                "methodStr='" + methodStr+ super.toString()+ "}";
    }

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }
}
