package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/4/27.
 */
public enum EvaluationThinkingFieldDaoVoEnum {
    ONE(1),ZERO(0),STR1("适用原因"),STR2("不适用原因");
    private String var;
    private Integer num;

    EvaluationThinkingFieldDaoVoEnum(Integer num) {
        this.num = num;
    }
    EvaluationThinkingFieldDaoVoEnum(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public Integer getNum() {
        return num;
    }
}
