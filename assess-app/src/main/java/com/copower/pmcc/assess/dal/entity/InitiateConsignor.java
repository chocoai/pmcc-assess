package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class InitiateConsignor {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer csType;

    private String csEntrustmentUnit;

    private String csLegalRepresentative;

    private String csSociologyCode;

    private String csAddress;

    private String csScopeOperation;

    private String csUnitProperties;

    private String csAttachmentProjectEnclosureId;

    private String csName;

    private String csSpareField;

    private String csIdcard;

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

    public Integer getCsType() {
        return csType;
    }

    public void setCsType(Integer csType) {
        this.csType = csType;
    }

    public String getCsEntrustmentUnit() {
        return csEntrustmentUnit;
    }

    public void setCsEntrustmentUnit(String csEntrustmentUnit) {
        this.csEntrustmentUnit = csEntrustmentUnit == null ? null : csEntrustmentUnit.trim();
    }

    public String getCsLegalRepresentative() {
        return csLegalRepresentative;
    }

    public void setCsLegalRepresentative(String csLegalRepresentative) {
        this.csLegalRepresentative = csLegalRepresentative == null ? null : csLegalRepresentative.trim();
    }

    public String getCsSociologyCode() {
        return csSociologyCode;
    }

    public void setCsSociologyCode(String csSociologyCode) {
        this.csSociologyCode = csSociologyCode == null ? null : csSociologyCode.trim();
    }

    public String getCsAddress() {
        return csAddress;
    }

    public void setCsAddress(String csAddress) {
        this.csAddress = csAddress == null ? null : csAddress.trim();
    }

    public String getCsScopeOperation() {
        return csScopeOperation;
    }

    public void setCsScopeOperation(String csScopeOperation) {
        this.csScopeOperation = csScopeOperation == null ? null : csScopeOperation.trim();
    }

    public String getCsUnitProperties() {
        return csUnitProperties;
    }

    public void setCsUnitProperties(String csUnitProperties) {
        this.csUnitProperties = csUnitProperties == null ? null : csUnitProperties.trim();
    }

    public String getCsAttachmentProjectEnclosureId() {
        return csAttachmentProjectEnclosureId;
    }

    public void setCsAttachmentProjectEnclosureId(String csAttachmentProjectEnclosureId) {
        this.csAttachmentProjectEnclosureId = csAttachmentProjectEnclosureId == null ? null : csAttachmentProjectEnclosureId.trim();
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName == null ? null : csName.trim();
    }

    public String getCsSpareField() {
        return csSpareField;
    }

    public void setCsSpareField(String csSpareField) {
        this.csSpareField = csSpareField == null ? null : csSpareField.trim();
    }

    public String getCsIdcard() {
        return csIdcard;
    }

    public void setCsIdcard(String csIdcard) {
        this.csIdcard = csIdcard == null ? null : csIdcard.trim();
    }
}