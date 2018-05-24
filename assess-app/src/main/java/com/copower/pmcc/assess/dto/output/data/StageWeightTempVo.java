package com.copower.pmcc.assess.dto.output.data;

import com.copower.pmcc.assess.dal.entity.StageWeightProportion;

import java.util.List;

/**
 * Created by zly on 2018/5/24.
 */
public class StageWeightTempVo extends ProportionTempVo{
    private List<StageWeightProportion> stageWeightProportions;

    public List<StageWeightProportion> getStageWeightProportions() {
        return stageWeightProportions;
    }

    public void setStageWeightProportions(List<StageWeightProportion> stageWeightProportions) {
        this.stageWeightProportions = stageWeightProportions;
    }
}
