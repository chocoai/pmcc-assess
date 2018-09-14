package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationHypothesis;

/**
 * Created by 13426 on 2018/4/28.
 */
public class DataEvaluationHypothesisVo extends DataEvaluationHypothesis {
    private String methodStr ;
   private String  entrustmentPurposeStr;
    private String typeName;
    private String categoryName;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
