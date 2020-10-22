package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreet;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:28
 * @Description:
 */
public class BasicHouseFaceStreetVo extends BasicHouseFaceStreet {
    private String streetLevelName;

    private String trafficFlowName;

    private String visitorsFlowrateName;
    private String positionName;
    private String creatorName;
    private String distanceName;

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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }
}
