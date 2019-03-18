package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInventoryRightRecord {
    private Integer id;

    private Integer projectId;

    private Integer inventoryRightRecordCenterId;

    private Integer planDetailsId;

    private String specialcase;

    private String recordIds;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getInventoryRightRecordCenterId() {
        return inventoryRightRecordCenterId;
    }

    public void setInventoryRightRecordCenterId(Integer inventoryRightRecordCenterId) {
        this.inventoryRightRecordCenterId = inventoryRightRecordCenterId;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getSpecialcase() {
        return specialcase;
    }

    public void setSpecialcase(String specialcase) {
        this.specialcase = specialcase == null ? null : specialcase.trim();
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds == null ? null : recordIds.trim();
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