package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class InitiateContacts {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer cType;

    private String cName;

    private String cDept;

    private String cPhone;

    private String cEmail;

    private String cSpareField;

    private Integer cPid;

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

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcDept() {
        return cDept;
    }

    public void setcDept(String cDept) {
        this.cDept = cDept == null ? null : cDept.trim();
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone == null ? null : cPhone.trim();
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail == null ? null : cEmail.trim();
    }

    public String getcSpareField() {
        return cSpareField;
    }

    public void setcSpareField(String cSpareField) {
        this.cSpareField = cSpareField == null ? null : cSpareField.trim();
    }

    public Integer getcPid() {
        return cPid;
    }

    public void setcPid(Integer cPid) {
        this.cPid = cPid;
    }
}