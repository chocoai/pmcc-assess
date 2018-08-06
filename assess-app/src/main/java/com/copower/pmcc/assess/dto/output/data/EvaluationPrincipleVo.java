package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationPrinciple;

/**
 * Created by 13426 on 2018/4/27.
 */
public class EvaluationPrincipleVo extends EvaluationPrinciple {
    private String methodStr ;
    private String entrustmentPurposeStr;


    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }

    public String getEntrustmentPurposeStr() {
        return entrustmentPurposeStr;
    }

    public void setEntrustmentPurposeStr(String entrustmentPurposeStr) {
        this.entrustmentPurposeStr = entrustmentPurposeStr;
    }

}
