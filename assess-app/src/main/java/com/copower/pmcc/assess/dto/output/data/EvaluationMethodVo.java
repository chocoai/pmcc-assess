package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethod;

/**
 * Created by 13426 on 2018/4/24.
 */
public class EvaluationMethodVo extends EvaluationMethod {
    private String methodStr ;


    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }
}
