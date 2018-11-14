package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseFaceStreet;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:28
 * @Description:
 */
public class BasicHouseFaceStreetVo extends BasicHouseFaceStreet {
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
