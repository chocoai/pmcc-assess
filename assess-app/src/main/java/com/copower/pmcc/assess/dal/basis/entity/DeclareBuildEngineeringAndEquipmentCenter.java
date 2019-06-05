package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class DeclareBuildEngineeringAndEquipmentCenter {
    private Integer id;

    private Integer planDetailsId;

    private Integer buildingConstructionPermitId;

    private Integer buildingPermitId;

    private Integer landUsePermitId;

    private Integer preSalePermitId;

    private Integer landId;

    private Integer houseId;

    private Integer indicatorId;

    private Integer realEstateId;

    private Integer buildEngineeringId;

    private Integer buildEquipmentId;

    private String type;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public Integer getBuildingConstructionPermitId() {
        return buildingConstructionPermitId;
    }

    public void setBuildingConstructionPermitId(Integer buildingConstructionPermitId) {
        this.buildingConstructionPermitId = buildingConstructionPermitId;
    }

    public Integer getBuildingPermitId() {
        return buildingPermitId;
    }

    public void setBuildingPermitId(Integer buildingPermitId) {
        this.buildingPermitId = buildingPermitId;
    }

    public Integer getLandUsePermitId() {
        return landUsePermitId;
    }

    public void setLandUsePermitId(Integer landUsePermitId) {
        this.landUsePermitId = landUsePermitId;
    }

    public Integer getPreSalePermitId() {
        return preSalePermitId;
    }

    public void setPreSalePermitId(Integer preSalePermitId) {
        this.preSalePermitId = preSalePermitId;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    public Integer getBuildEngineeringId() {
        return buildEngineeringId;
    }

    public void setBuildEngineeringId(Integer buildEngineeringId) {
        this.buildEngineeringId = buildEngineeringId;
    }

    public Integer getBuildEquipmentId() {
        return buildEquipmentId;
    }

    public void setBuildEquipmentId(Integer buildEquipmentId) {
        this.buildEquipmentId = buildEquipmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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