package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.DataBuildingNewRate;

public class DataBuildingNewRateVo extends DataBuildingNewRate {
    private String useStr;

    public String getUseStr() {
        return useStr;
    }

    public void setUseStr(String useStr) {
        this.useStr = useStr;
    }

    @Override
    public String toString() {
        return "DataBuildingNewRateVo{" +
                "useStr='" + useStr + '\'' +
                '}';
    }
}
