package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/5/3.
 */
public enum  CaseComparisonEnum {
    Text("文本"),NO_Text("非文本"),CASE_COMPARISON_ONE_ENUM(1),CASE_COMPARISON_TWO_ENUM(2);
    private String var;
    private Integer num;

    CaseComparisonEnum(String var) {
        this.var = var;
    }

    CaseComparisonEnum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public String getVar() {
        return var;
    }
}
