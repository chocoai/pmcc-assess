package com.copower.pmcc.assess.dal.basis.custom.entity;

/**
 * Created by kings on 2018-11-21.
 */
public class CustomProjectPlanDetailCount {
    private Integer projectPhaseId;
    private Integer planDetailsCount;

    public Integer getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Integer projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
    }

    public Integer getPlanDetailsCount() {
        return planDetailsCount;
    }

    public void setPlanDetailsCount(Integer planDetailsCount) {
        this.planDetailsCount = planDetailsCount;
    }
}
