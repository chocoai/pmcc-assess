package com.copower.pmcc.assess.dal.basis.entity;

import java.util.Date;

public class ExamineEstateSupplyWater {
    private Integer id;

    private Integer estateId;

    private String name;

    private String grade;

    private String lineGrade;

    private String power;

    private String drainageMode;

    private String drainageTreatment;

    private String drainageAmount;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getLineGrade() {
        return lineGrade;
    }

    public void setLineGrade(String lineGrade) {
        this.lineGrade = lineGrade == null ? null : lineGrade.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public String getDrainageMode() {
        return drainageMode;
    }

    public void setDrainageMode(String drainageMode) {
        this.drainageMode = drainageMode == null ? null : drainageMode.trim();
    }

    public String getDrainageTreatment() {
        return drainageTreatment;
    }

    public void setDrainageTreatment(String drainageTreatment) {
        this.drainageTreatment = drainageTreatment == null ? null : drainageTreatment.trim();
    }

    public String getDrainageAmount() {
        return drainageAmount;
    }

    public void setDrainageAmount(String drainageAmount) {
        this.drainageAmount = drainageAmount == null ? null : drainageAmount.trim();
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