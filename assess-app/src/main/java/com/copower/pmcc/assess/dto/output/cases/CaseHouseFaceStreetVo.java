package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreet;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:19
 * @Description:
 */
public class CaseHouseFaceStreetVo extends CaseHouseFaceStreet {
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
