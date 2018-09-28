package com.copower.pmcc.assess.dto.input.project.declare;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/9/28 10:49
 * @Description:
 */
public class DeclarePreSalePermitDto {
    private Integer id;

    private Integer planDetailsId;

    private String certificateNumber;

    private String issuingOrgan;

    private String salesUnit;

    private String legalRepresentative;

    private String beLocated;

    private String name;

    private BigDecimal preSaleArea;

    private String preSaleScope;

    private String housingUse;

    private String buildingStructure;

    private String preSaleSupervisionInformation;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date date;

    private String mortgageSituation;

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

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPreSaleArea() {
        return preSaleArea;
    }

    public void setPreSaleArea(BigDecimal preSaleArea) {
        this.preSaleArea = preSaleArea;
    }

    public String getPreSaleScope() {
        return preSaleScope;
    }

    public void setPreSaleScope(String preSaleScope) {
        this.preSaleScope = preSaleScope;
    }

    public String getHousingUse() {
        return housingUse;
    }

    public void setHousingUse(String housingUse) {
        this.housingUse = housingUse;
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getPreSaleSupervisionInformation() {
        return preSaleSupervisionInformation;
    }

    public void setPreSaleSupervisionInformation(String preSaleSupervisionInformation) {
        this.preSaleSupervisionInformation = preSaleSupervisionInformation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMortgageSituation() {
        return mortgageSituation;
    }

    public void setMortgageSituation(String mortgageSituation) {
        this.mortgageSituation = mortgageSituation;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
