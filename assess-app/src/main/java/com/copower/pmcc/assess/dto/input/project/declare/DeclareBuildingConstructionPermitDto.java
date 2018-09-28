package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/28 10:41
 * @Description:
 */
public class DeclareBuildingConstructionPermitDto {
    private Integer id;

    private Integer planDetailsId;

    private String certificateNumber;

    private String issuingOrgan;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date date;

    private String buildUnit;

    private String name;

    private String buildAddress;

    private String scaleConstruction;

    private String reconnaissanceUnit;

    private String designUnit;

    private String constructionUnit;

    private String constructionControlUnit;

    private String reconnaissanceUnitPerson;

    private String designUnitPerson;

    private String constructionUnitPerson;

    private String chiefEngineerConstructionInspection;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date contractPeriod;

    private String remark;

    private String creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getIssuingOrgan() {
        return issuingOrgan;
    }

    public void setIssuingOrgan(String issuingOrgan) {
        this.issuingOrgan = issuingOrgan;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBuildUnit() {
        return buildUnit;
    }

    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildAddress() {
        return buildAddress;
    }

    public void setBuildAddress(String buildAddress) {
        this.buildAddress = buildAddress;
    }

    public String getScaleConstruction() {
        return scaleConstruction;
    }

    public void setScaleConstruction(String scaleConstruction) {
        this.scaleConstruction = scaleConstruction;
    }

    public String getReconnaissanceUnit() {
        return reconnaissanceUnit;
    }

    public void setReconnaissanceUnit(String reconnaissanceUnit) {
        this.reconnaissanceUnit = reconnaissanceUnit;
    }

    public String getDesignUnit() {
        return designUnit;
    }

    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getConstructionControlUnit() {
        return constructionControlUnit;
    }

    public void setConstructionControlUnit(String constructionControlUnit) {
        this.constructionControlUnit = constructionControlUnit;
    }

    public String getReconnaissanceUnitPerson() {
        return reconnaissanceUnitPerson;
    }

    public void setReconnaissanceUnitPerson(String reconnaissanceUnitPerson) {
        this.reconnaissanceUnitPerson = reconnaissanceUnitPerson;
    }

    public String getDesignUnitPerson() {
        return designUnitPerson;
    }

    public void setDesignUnitPerson(String designUnitPerson) {
        this.designUnitPerson = designUnitPerson;
    }

    public String getConstructionUnitPerson() {
        return constructionUnitPerson;
    }

    public void setConstructionUnitPerson(String constructionUnitPerson) {
        this.constructionUnitPerson = constructionUnitPerson;
    }

    public String getChiefEngineerConstructionInspection() {
        return chiefEngineerConstructionInspection;
    }

    public void setChiefEngineerConstructionInspection(String chiefEngineerConstructionInspection) {
        this.chiefEngineerConstructionInspection = chiefEngineerConstructionInspection;
    }

    public Date getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Date contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
