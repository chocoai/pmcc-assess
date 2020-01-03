package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateInvestigation;

/**
 * @Auther: zch
 * @Date: 2018/12/4 10:35
 * @Description:
 */
public class BasicEstateInvestigationVo extends BasicEstateInvestigation {
    private String planningUseName;

    public String getPlanningUseName() {
        return planningUseName;
    }

    public void setPlanningUseName(String planningUseName) {
        this.planningUseName = planningUseName;
    }
}
