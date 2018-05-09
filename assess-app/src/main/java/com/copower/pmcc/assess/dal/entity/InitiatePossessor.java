package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class InitiatePossessor {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer pType;

    private String pEntrustmentUnit;

    private String pLegalRepresentative;

    private String pSociologyCode;

    private String pAddress;

    private String pScopeOperation;

    private String pUnitProperties;

    private String pAttachmentProjectEnclosureId;

    private String pName;

    private String spareField;

    private String pIdcard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    public String getpEntrustmentUnit() {
        return pEntrustmentUnit;
    }

    public void setpEntrustmentUnit(String pEntrustmentUnit) {
        this.pEntrustmentUnit = pEntrustmentUnit == null ? null : pEntrustmentUnit.trim();
    }

    public String getpLegalRepresentative() {
        return pLegalRepresentative;
    }

    public void setpLegalRepresentative(String pLegalRepresentative) {
        this.pLegalRepresentative = pLegalRepresentative == null ? null : pLegalRepresentative.trim();
    }

    public String getpSociologyCode() {
        return pSociologyCode;
    }

    public void setpSociologyCode(String pSociologyCode) {
        this.pSociologyCode = pSociologyCode == null ? null : pSociologyCode.trim();
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress == null ? null : pAddress.trim();
    }

    public String getpScopeOperation() {
        return pScopeOperation;
    }

    public void setpScopeOperation(String pScopeOperation) {
        this.pScopeOperation = pScopeOperation == null ? null : pScopeOperation.trim();
    }

    public String getpUnitProperties() {
        return pUnitProperties;
    }

    public void setpUnitProperties(String pUnitProperties) {
        this.pUnitProperties = pUnitProperties == null ? null : pUnitProperties.trim();
    }

    public String getpAttachmentProjectEnclosureId() {
        return pAttachmentProjectEnclosureId;
    }

    public void setpAttachmentProjectEnclosureId(String pAttachmentProjectEnclosureId) {
        this.pAttachmentProjectEnclosureId = pAttachmentProjectEnclosureId == null ? null : pAttachmentProjectEnclosureId.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getSpareField() {
        return spareField;
    }

    public void setSpareField(String spareField) {
        this.spareField = spareField == null ? null : spareField.trim();
    }

    public String getpIdcard() {
        return pIdcard;
    }

    public void setpIdcard(String pIdcard) {
        this.pIdcard = pIdcard == null ? null : pIdcard.trim();
    }
}