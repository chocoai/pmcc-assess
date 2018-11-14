package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitElevator;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:17
 * @Description:
 */
public class BasicUnitElevatorVo extends BasicUnitElevator {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
