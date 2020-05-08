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
    private String creatorName;
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }

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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
