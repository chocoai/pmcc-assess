package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleFieldDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/4/27.
 */
public class EvaluationPrincipleVo extends EvaluationPrincipleDto {
    private String methodStr ;
    private String entrustmentPurposeStr;

    private List<EvaluationPrincipleFieldDto> fieldVos = new ArrayList<>();
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

    public List<EvaluationPrincipleFieldDto> getFieldVos() {
        return fieldVos;
    }

    public void setFieldVos(List<EvaluationPrincipleFieldDto> fieldVos) {
        this.fieldVos = fieldVos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
