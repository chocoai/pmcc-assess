package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRate;

/**
 * Created by 13426 on 2018/4/24.
 */
public class DataBuildingNewRateVo extends DataBuildingNewRate {
    private String buildingUseName;

    public String getBuildingUseName() {
        return buildingUseName;
    }

    public void setBuildingUseName(String buildingUseName) {
        this.buildingUseName = buildingUseName;
    }
}
