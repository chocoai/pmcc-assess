package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitElevator;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:17
 * @Description:
 */
public class BasicUnitElevatorVo extends BasicUnitElevator {
    private String typeName;
    private String maintenanceName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }
}
