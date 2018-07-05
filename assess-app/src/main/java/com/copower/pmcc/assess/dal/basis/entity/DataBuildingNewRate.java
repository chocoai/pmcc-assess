package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DataBuildingNewRate {
    private String useChange;
    private Integer id;

    private String buildingStructure;

    private Integer buildingUse;

    private Integer durableLife;

    private String residualValue;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure == null ? null : buildingStructure.trim();
    }

    public Integer getBuildingUse() {
        return buildingUse;
    }

    public void setBuildingUse(Integer buildingUse) {
        this.buildingUse = buildingUse;
    }

    public Integer getDurableLife() {
        return durableLife;
    }

    public void setDurableLife(Integer durableLife) {
        this.durableLife = durableLife;
    }

    public String getResidualValue() {
        return residualValue;
    }

    public void setResidualValue(String residualValue) {
        this.residualValue = residualValue == null ? null : residualValue.trim();
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

    public void setUseChange(String useChange) {
        this.useChange = useChange;
    }

    public String getUseChange() {
        return useChange;
    }

    @Override
    public String toString() {
        return "DataBuildingNewRate{" +
                "useChange='" + useChange + '\'' +
                ", id=" + id +
                ", buildingStructure='" + buildingStructure + '\'' +
                ", buildingUse=" + buildingUse +
                ", durableLife=" + durableLife +
                ", residualValue='" + residualValue + '\'' +
                ", creator='" + creator + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}