package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrain;

/**
 * @Auther: zch
 * @Date: 2018/12/11 14:45
 * @Description:
 */
public class CaseHouseWaterDrainVo extends CaseHouseWaterDrain {
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
