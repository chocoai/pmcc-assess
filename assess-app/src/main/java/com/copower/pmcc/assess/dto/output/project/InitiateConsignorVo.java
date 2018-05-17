package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dto.input.project.InitiateConsignorDto;

/**
 * Created by 13426 on 2018/5/4.
 */
public class InitiateConsignorVo extends InitiateConsignorDto {
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
