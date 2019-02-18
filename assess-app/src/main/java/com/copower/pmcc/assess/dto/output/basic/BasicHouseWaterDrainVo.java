package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterDrain;

/**
 * @Auther: zch
 * @Date: 2018/12/11 11:49
 * @Description:
 */
public class BasicHouseWaterDrainVo extends BasicHouseWaterDrain {
    private String drainSystemName;
    private String typeName;
    private String processingModeName;

    public String getDrainSystemName() {
        return drainSystemName;
    }

    public void setDrainSystemName(String drainSystemName) {
        this.drainSystemName = drainSystemName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProcessingModeName() {
        return processingModeName;
    }

    public void setProcessingModeName(String processingModeName) {
        this.processingModeName = processingModeName;
    }
}
