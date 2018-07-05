package com.copower.pmcc.assess.dto.input.data;


import com.copower.pmcc.assess.dal.basis.entity.DataStageWeightProportion;

/**
 * Created by zly on 2018/6/13.
 */
public class StageWeightProportionDto extends DataStageWeightProportion {
    private String proportionList;

    public String getProportionList() {
        return proportionList;
    }

    public void setProportionList(String proportionList) {
        this.proportionList = proportionList;
    }
}
