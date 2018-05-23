package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.StageWeightProportion;

public class StageWeightProportionVo extends StageWeightProportion{
    private String entrustPurposeName;
    private String stageName;

    public String getEntrustPurposeName () {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }



}
