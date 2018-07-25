package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreet;

/**
 * @Auther: zch
 * @Date: 2018/7/25 14:53
 * @Description:
 */
public class ExamineHouseFaceStreetVo extends ExamineHouseFaceStreet {
    private String streetLevelName;

    private String trafficFlowName;

    private String visitorsFlowrateName;

    public String getStreetLevelName() {
        return streetLevelName;
    }

    public void setStreetLevelName(String streetLevelName) {
        this.streetLevelName = streetLevelName;
    }

    public String getTrafficFlowName() {
        return trafficFlowName;
    }

    public void setTrafficFlowName(String trafficFlowName) {
        this.trafficFlowName = trafficFlowName;
    }

    public String getVisitorsFlowrateName() {
        return visitorsFlowrateName;
    }

    public void setVisitorsFlowrateName(String visitorsFlowrateName) {
        this.visitorsFlowrateName = visitorsFlowrateName;
    }
}
