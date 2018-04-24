package com.copower.pmcc.assess.dto.input.data;

import com.copower.pmcc.assess.dal.entity.EvaluationMethod;

/**
 * Created by 13426 on 2018/4/24.
 */
public class EvaluationMethodDto extends EvaluationMethod {
    public static String METHOD_FIELD = "method";

    @Override
    public String toString() {
        return "EvaluationMethodDto{"+super.toString()+"}";
    }
}
