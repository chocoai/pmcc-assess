package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTraffic;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:17
 * @Description:
 */
public class BasicMatchingTrafficVo extends BasicMatchingTraffic {
    private String flagName;
    private String distanceName;
    private String natureName;
    private String limitSpeialName;
    private String positionName;
    private String trafficFlowName;
    private String visitorsFlowrateName;

    public String getDistanceName() {
        return distanceName;
    }

    public void setDistanceName(String distanceName) {
        this.distanceName = distanceName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getLimitSpeialName() {
        return limitSpeialName;
    }

    public void setLimitSpeialName(String limitSpeialName) {
        this.limitSpeialName = limitSpeialName;
    }


    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }
}
