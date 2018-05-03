package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/4/27.
 */
public enum EvaluationThinkingFieldVoEnum {
    ONE(1),ZERO(0),STR1("适用"),STR2("不适用"),START("启用"),END("不启用");
    private String var;
    private Integer num;

    EvaluationThinkingFieldVoEnum(Integer num) {
        this.num = num;
    }
    EvaluationThinkingFieldVoEnum(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public Integer getNum() {
        return num;
    }
}
