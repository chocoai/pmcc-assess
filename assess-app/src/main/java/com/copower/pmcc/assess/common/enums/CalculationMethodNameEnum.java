package com.copower.pmcc.assess.common.enums;

/**
 * @Auther: zch
 * @Date: 2019/2/15 16:44
 * @Description:
 */
public enum CalculationMethodNameEnum {
    MdIncome("收益法"),
    MdCompare("市场比较法"),
    MdCost("成本法"),
    MdDevelopment("假设开发法"),
    ;
    private String name;

    CalculationMethodNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
