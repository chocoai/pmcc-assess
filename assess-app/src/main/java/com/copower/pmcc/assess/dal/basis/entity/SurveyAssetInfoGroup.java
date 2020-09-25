package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class SurveyAssetInfoGroup {
    private Integer id;

    private Integer assetInfoId;

    private String groupName;

    private Integer inventoryId;

    private String formType;

    private Integer viewSpiltId;

    private Integer taxArrearsId;

    private Integer damageId;

    private Integer transferId;

    private String status;

    private String remark;

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

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType == null ? null : formType.trim();
    }

    public Integer getViewSpiltId() {
        return viewSpiltId;
    }

    public void setViewSpiltId(Integer viewSpiltId) {
        this.viewSpiltId = viewSpiltId;
    }

    public Integer getTaxArrearsId() {
        return taxArrearsId;
    }

    public void setTaxArrearsId(Integer taxArrearsId) {
        this.taxArrearsId = taxArrearsId;
    }

    public Integer getDamageId() {
        return damageId;
    }

    public void setDamageId(Integer damageId) {
        this.damageId = damageId;
    }

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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