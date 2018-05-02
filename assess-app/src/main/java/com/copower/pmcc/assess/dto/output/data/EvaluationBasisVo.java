package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.EvaluationBasis;

/**
 * Created by 13426 on 2018/4/28.
 */
public class EvaluationBasisVo extends EvaluationBasis {
    private String methodStr;
    private String  entrustmentPurposeStr;

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
