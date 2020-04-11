package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasicApply {
    private Integer id;

    private String name;

    private Integer batchDetailId;

    private String structuralInfo;

    private Integer type;

    private String address;

    private Integer declareRecordId;

    private Integer basicEstateId;

    private Integer basicBuildingId;

    private Integer basicUnitId;

    private Integer basicHouseId;

    private Integer planDetailsId;

    private BigDecimal area;

    private Boolean bisDelete;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBatchDetailId() {
        return batchDetailId;
    }

    public void setBatchDetailId(Integer batchDetailId) {
        this.batchDetailId = batchDetailId;
    }

    public String getStructuralInfo() {
        return structuralInfo;
    }

    public void setStructuralInfo(String structuralInfo) {
        this.structuralInfo = structuralInfo == null ? null : structuralInfo.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    public Integer getBasicEstateId() {
        return basicEstateId;
    }

    public void setBasicEstateId(Integer basicEstateId) {
        this.basicEstateId = basicEstateId;
    }

    public Integer getBasicBuildingId() {
        return basicBuildingId;
    }

    public void setBasicBuildingId(Integer basicBuildingId) {
        this.basicBuildingId = basicBuildingId;
    }

    public Integer getBasicUnitId() {
        return basicUnitId;
    }

    public void setBasicUnitId(Integer basicUnitId) {
        this.basicUnitId = basicUnitId;
    }

    public Integer getBasicHouseId() {
        return basicHouseId;
    }

    public void setBasicHouseId(Integer basicHouseId) {
        this.basicHouseId = basicHouseId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Boolean getBisDelete() {
        return bisDelete;
    }

    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
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