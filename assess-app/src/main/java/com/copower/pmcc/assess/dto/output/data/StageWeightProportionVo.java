package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.StageWeightProportion;

public class StageWeightProportionVo extends StageWeightProportion{
    private String entrustPurposeName;

    public String getEntrustPurposeName () {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }
}
