package com.copower.pmcc.assess.dto.output.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiatePossessor;

/**
 * Created by 13426 on 2018/5/4.
 */
public class InitiatePossessorVo extends InitiatePossessor {
    private String pEntrustmentUnitName;
    private String pUnitPropertiesName;

    public String getpEntrustmentUnitName() {
        return pEntrustmentUnitName;
    }

    public void setpEntrustmentUnitName(String pEntrustmentUnitName) {
        this.pEntrustmentUnitName = pEntrustmentUnitName;
    }

    public String getpUnitPropertiesName() {
        return pUnitPropertiesName;
    }

    public void setpUnitPropertiesName(String pUnitPropertiesName) {
        this.pUnitPropertiesName = pUnitPropertiesName;
    }
}
