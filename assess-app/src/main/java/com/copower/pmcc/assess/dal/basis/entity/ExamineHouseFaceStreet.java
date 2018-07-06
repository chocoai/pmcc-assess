package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineHouseFaceStreet {
    private Integer id;

    private Integer houseId;

    private String streetName;

    private Integer streetLevel;

    private String trafficFlow;

    private String visitorsFlowrate;

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

    public String getTrafficFlow() {
        return trafficFlow;
    }

    public void setTrafficFlow(String trafficFlow) {
        this.trafficFlow = trafficFlow == null ? null : trafficFlow.trim();
    }

    public String getVisitorsFlowrate() {
        return visitorsFlowrate;
    }

    public void setVisitorsFlowrate(String visitorsFlowrate) {
        this.visitorsFlowrate = visitorsFlowrate == null ? null : visitorsFlowrate.trim();
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