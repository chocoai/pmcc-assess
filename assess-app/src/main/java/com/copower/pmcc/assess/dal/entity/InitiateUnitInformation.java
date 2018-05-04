package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class InitiateUnitInformation {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String uUnitProperties;

    private String uScopeOperation;

    private String uAddress;

    private String uCertificateNumber;

    private String uLegalRepresentative;

    private String uUseUnit;

    private String spareField;

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

    public String getuUnitProperties() {
        return uUnitProperties;
    }

    public void setuUnitProperties(String uUnitProperties) {
        this.uUnitProperties = uUnitProperties == null ? null : uUnitProperties.trim();
    }

    public String getuScopeOperation() {
        return uScopeOperation;
    }

    public void setuScopeOperation(String uScopeOperation) {
        this.uScopeOperation = uScopeOperation == null ? null : uScopeOperation.trim();
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress == null ? null : uAddress.trim();
    }

    public String getuCertificateNumber() {
        return uCertificateNumber;
    }

    public void setuCertificateNumber(String uCertificateNumber) {
        this.uCertificateNumber = uCertificateNumber == null ? null : uCertificateNumber.trim();
    }

    public String getuLegalRepresentative() {
        return uLegalRepresentative;
    }

    public void setuLegalRepresentative(String uLegalRepresentative) {
        this.uLegalRepresentative = uLegalRepresentative == null ? null : uLegalRepresentative.trim();
    }

    public String getuUseUnit() {
        return uUseUnit;
    }

    public void setuUseUnit(String uUseUnit) {
        this.uUseUnit = uUseUnit == null ? null : uUseUnit.trim();
    }

    public String getSpareField() {
        return spareField;
    }

    public void setSpareField(String spareField) {
        this.spareField = spareField == null ? null : spareField.trim();
    }
}