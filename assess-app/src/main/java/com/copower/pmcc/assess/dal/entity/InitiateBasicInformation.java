package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class InitiateBasicInformation {
    private Integer id;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private String bProjectName;

    private Integer bProjectClassId;

    private String bEntrustPurpose;

    private Date bEvaluationBenchmarkDay;

    private Integer bDepartmentId;

    private String bProjectManager;

    private String bEnclosureLocation;

    private Integer bValueType;

    private String bSubordinateAssignment;

    private String bSpareField1;

    private String bSpareField2;

    private String bSpareField3;

    private String bSpareField4;

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

    public String getbProjectName() {
        return bProjectName;
    }

    public void setbProjectName(String bProjectName) {
        this.bProjectName = bProjectName == null ? null : bProjectName.trim();
    }

    public Integer getbProjectClassId() {
        return bProjectClassId;
    }

    public void setbProjectClassId(Integer bProjectClassId) {
        this.bProjectClassId = bProjectClassId;
    }

    public String getbEntrustPurpose() {
        return bEntrustPurpose;
    }

    public void setbEntrustPurpose(String bEntrustPurpose) {
        this.bEntrustPurpose = bEntrustPurpose == null ? null : bEntrustPurpose.trim();
    }

    public Date getbEvaluationBenchmarkDay() {
        return bEvaluationBenchmarkDay;
    }

    public void setbEvaluationBenchmarkDay(Date bEvaluationBenchmarkDay) {
        this.bEvaluationBenchmarkDay = bEvaluationBenchmarkDay;
    }

    public Integer getbDepartmentId() {
        return bDepartmentId;
    }

    public void setbDepartmentId(Integer bDepartmentId) {
        this.bDepartmentId = bDepartmentId;
    }

    public String getbProjectManager() {
        return bProjectManager;
    }

    public void setbProjectManager(String bProjectManager) {
        this.bProjectManager = bProjectManager == null ? null : bProjectManager.trim();
    }

    public String getbEnclosureLocation() {
        return bEnclosureLocation;
    }

    public void setbEnclosureLocation(String bEnclosureLocation) {
        this.bEnclosureLocation = bEnclosureLocation == null ? null : bEnclosureLocation.trim();
    }

    public Integer getbValueType() {
        return bValueType;
    }

    public void setbValueType(Integer bValueType) {
        this.bValueType = bValueType;
    }

    public String getbSubordinateAssignment() {
        return bSubordinateAssignment;
    }

    public void setbSubordinateAssignment(String bSubordinateAssignment) {
        this.bSubordinateAssignment = bSubordinateAssignment == null ? null : bSubordinateAssignment.trim();
    }

    public String getbSpareField1() {
        return bSpareField1;
    }

    public void setbSpareField1(String bSpareField1) {
        this.bSpareField1 = bSpareField1 == null ? null : bSpareField1.trim();
    }

    public String getbSpareField2() {
        return bSpareField2;
    }

    public void setbSpareField2(String bSpareField2) {
        this.bSpareField2 = bSpareField2 == null ? null : bSpareField2.trim();
    }

    public String getbSpareField3() {
        return bSpareField3;
    }

    public void setbSpareField3(String bSpareField3) {
        this.bSpareField3 = bSpareField3 == null ? null : bSpareField3.trim();
    }

    public String getbSpareField4() {
        return bSpareField4;
    }

    public void setbSpareField4(String bSpareField4) {
        this.bSpareField4 = bSpareField4 == null ? null : bSpareField4.trim();
    }
}