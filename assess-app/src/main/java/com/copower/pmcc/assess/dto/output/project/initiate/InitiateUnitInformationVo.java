package com.copower.pmcc.assess.dto.output.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateUnitInformation;

/**
 * Created by 13426 on 2018/5/4.
 */
public class InitiateUnitInformationVo extends InitiateUnitInformation {
    private String uUnitPropertiesName;

    public String getuUnitPropertiesName() {
        return uUnitPropertiesName;
    }

    public void setuUnitPropertiesName(String uUnitPropertiesName) {
        this.uUnitPropertiesName = uUnitPropertiesName;
    }
}
