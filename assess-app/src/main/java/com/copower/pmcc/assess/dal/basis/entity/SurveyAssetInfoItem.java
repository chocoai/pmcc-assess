package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInfoItem {
    private Integer id;

    private Integer assetInfoId;

    private String groupName;

    private String name;

    private String status;

    private Integer groupId;

    private Integer declareId;

    private Integer inventoryId;

    private Boolean bisFinishDamage;

    private Boolean bisFinishUniformity;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssetInfoId() {
        return assetInfoId;
    }

    public void setAssetInfoId(Integer assetInfoId) {
        this.assetInfoId = assetInfoId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDeclareId() {
        return declareId;
    }

    public void setDeclareId(Integer declareId) {
        this.declareId = declareId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Boolean getBisFinishDamage() {
        return bisFinishDamage;
    }

    public void setBisFinishDamage(Boolean bisFinishDamage) {
        this.bisFinishDamage = bisFinishDamage;
    }

    public Boolean getBisFinishUniformity() {
        return bisFinishUniformity;
    }

    public void setBisFinishUniformity(Boolean bisFinishUniformity) {
        this.bisFinishUniformity = bisFinishUniformity;
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