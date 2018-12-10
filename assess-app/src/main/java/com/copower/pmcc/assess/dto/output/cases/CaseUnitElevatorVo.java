package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevator;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:34
 * @Description:
 */
public class CaseUnitElevatorVo extends CaseUnitElevator {
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
