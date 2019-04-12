package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicMatchingTraffic {
    private Integer id;

    private Integer nature;

    private Integer estateId;

    private String type;

    private String name;

    private Integer distance;

    private String lineName;

    private String theLine;

    private String limitSpeed;

    private String limitTime;

    private Integer limitSpeial;

    private Boolean flag;

    private String costStandard;

    private Integer position;

    private Integer trafficFlow;

    private Integer visitorsFlowrate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getTheLine() {
        return theLine;
    }

    public void setTheLine(String theLine) {
        this.theLine = theLine == null ? null : theLine.trim();
    }

    public String getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(String limitSpeed) {
        this.limitSpeed = limitSpeed == null ? null : limitSpeed.trim();
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime == null ? null : limitTime.trim();
    }

    public Integer getLimitSpeial() {
        return limitSpeial;
    }

    public void setLimitSpeial(Integer limitSpeial) {
        this.limitSpeial = limitSpeial;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getCostStandard() {
        return costStandard;
    }

    public void setCostStandard(String costStandard) {
        this.costStandard = costStandard == null ? null : costStandard.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getTrafficFlow() {
        return trafficFlow;
    }

    public void setTrafficFlow(Integer trafficFlow) {
        this.trafficFlow = trafficFlow;
    }

    public Integer getVisitorsFlowrate() {
        return visitorsFlowrate;
    }

    public void setVisitorsFlowrate(Integer visitorsFlowrate) {
        this.visitorsFlowrate = visitorsFlowrate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}