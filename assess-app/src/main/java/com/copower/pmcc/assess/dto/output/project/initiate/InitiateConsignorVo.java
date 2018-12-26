package com.copower.pmcc.assess.dto.output.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateConsignor;

/**
 * Created by 13426 on 2018/5/4.
 */
public class InitiateConsignorVo extends InitiateConsignor {
    private String csEntrustmentUnitName;
    private String csUnitPropertiesName;

    public String getCsEntrustmentUnitName() {
        return csEntrustmentUnitName;
    }

    public void setCsEntrustmentUnitName(String csEntrustmentUnitName) {
        this.csEntrustmentUnitName = csEntrustmentUnitName;
    }

    public String getCsUnitPropertiesName() {
        return csUnitPropertiesName;
    }

    public void setCsUnitPropertiesName(String csUnitPropertiesName) {
        this.csUnitPropertiesName = csUnitPropertiesName;
    }
}
