package com.copower.pmcc.assess.common.enums;

/**
 * Created by 13426 on 2018/5/25.
 */
public enum SchemeInfoDetailEnum {
    HYPOTHESIS(0),//假设
    PRINCIPLE(1),//原则
    Basis(2),//依据
    BestUse(3),//利用描述
    Timepoint(4)//价值时点
    ;
    private int dataType;

    SchemeInfoDetailEnum(int dataType) {
        this.dataType = dataType;
    }

    public int getDataType() {
        return dataType;
    }
}

