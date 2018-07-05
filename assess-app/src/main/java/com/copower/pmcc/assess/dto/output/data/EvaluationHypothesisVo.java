package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.EvaluationHypothesis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/4/28.
 */
public class EvaluationHypothesisVo extends EvaluationHypothesis {
    private String methodStr ;
   private String  entrustmentPurposeStr;
   private List<EvaluationHypothesisFieldVo> fieldVos = new ArrayList<>();
   private int size;

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

    public List<EvaluationHypothesisFieldVo> getFieldVos() {
        return fieldVos;
    }

    public void setFieldVos(List<EvaluationHypothesisFieldVo> fieldVos) {
        this.fieldVos = fieldVos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
