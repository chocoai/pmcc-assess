package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class BasicHouseFaceStreet {
    private Integer id;

    private Integer houseId;

    private String streetName;

    private Integer streetLevel;

    private Integer trafficFlow;

    private Integer visitorsFlowrate;

    private Integer position;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    public Integer getStreetLevel() {
        return streetLevel;
    }

    public void setStreetLevel(Integer streetLevel) {
        this.streetLevel = streetLevel;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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