package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;

/**
 * Created by 13426 on 2018/4/27.
 */
public class EvaluationPrincipleVo extends EvaluationPrincipleDto {
    private String methodStr ;

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }
}
