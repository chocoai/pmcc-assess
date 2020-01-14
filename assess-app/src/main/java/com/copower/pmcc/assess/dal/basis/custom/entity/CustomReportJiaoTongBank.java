package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportJiaoTongBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型

    private String previewsNumber;//预估号
    private String resultNumber;//正式报告文号
    private Integer projectCategoryId;
    private String projectCategoryName;//类型
    private Date reportIssuanceDate;//报告日
    private Date valuationDate;//基准日
    private String unitName;//细分2
    private String consignor;//委托方(委托企业名称)
    private String possessor;//产权人
    private String seat;//地址
    private BigDecimal area;//面积
    private BigDecimal assessPrice;//单价
    private BigDecimal assessTotal;//总价
    private String methodNames;//方法
    private BigDecimal assessCost;//收费金额
    private String assessOrganization;//评估公司
    private String firstAppraiser;//签字估价师1
    private String secondAppraiser;//签字估价师2

    private String numberValue;//文号
    private String qualificationType;//资质类型
    private String realEstateAppraiser;//估价师
    private Integer areaId;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getPreviewsNumber() {
        return previewsNumber;
    }

    public void setPreviewsNumber(String previewsNumber) {
        this.previewsNumber = previewsNumber;
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }

    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    public void setReportIssuanceDate(Date reportIssuanceDate) {
        this.reportIssuanceDate = reportIssuanceDate;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getPossessor() {
        return possessor;
    }

    public void setPossessor(String possessor) {
        this.possessor = possessor;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public BigDecimal getAssessTotal() {
        return assessTotal;
    }

    public void setAssessTotal(BigDecimal assessTotal) {
        this.assessTotal = assessTotal;
    }

    public String getMethodNames() {
        return methodNames;
    }

    public void setMethodNames(String methodNames) {
        this.methodNames = methodNames;
    }

    public BigDecimal getAssessCost() {
        return assessCost;
    }

    public void setAssessCost(BigDecimal assessCost) {
        this.assessCost = assessCost;
    }

    public String getAssessOrganization() {
        return assessOrganization;
    }

    public void setAssessOrganization(String assessOrganization) {
        this.assessOrganization = assessOrganization;
    }

    public String getFirstAppraiser() {
        return firstAppraiser;
    }

    public void setFirstAppraiser(String firstAppraiser) {
        this.firstAppraiser = firstAppraiser;
    }

    public String getSecondAppraiser() {
        return secondAppraiser;
    }

    public void setSecondAppraiser(String secondAppraiser) {
        this.secondAppraiser = secondAppraiser;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public String getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    public void setRealEstateAppraiser(String realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
