package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.DataBuildingNewRate;

/**
 * Created by 13426 on 2018/4/24.
 */
public class DataBuildingNewRateVo extends DataBuildingNewRate {
    private String useChange;

    public String getUseChange() {
        return useChange;
    }

    public void setUseChange(String useChange) {
        this.useChange = useChange;
    }
}
