package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportJianSheBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    private String unitName;//报告使用单位(支行)
    private String consignor;//委托人(委托企业名称)
    private String pledger;//抵押人
    private Integer loanType;//业务类型
    private String loanTypeName;//业务类型
    private Integer projectCategoryId;//项目类别（评估类型）
    private String projectCategoryName;//项目类别
    private String areaName;//估价对象所处区域
    private String estateName;//小区名称
    private String seat;//估价对象坐落位置
    private Date handleTieme;//业务受理时间(立项时间)
    private Date preauditNumberDate;//预评出具时间
    private Date reportIssuanceDate;//报告出具时间
    private BigDecimal area;//面积
    private BigDecimal assessTotal;//评估总价
    private BigDecimal assessPrice;//评估单价
    private BigDecimal assessCost;//评估费用
    private String assessOrganization;//评估机构
    private String appraiser;//评估师
    private String remark;//备注
    private String numberValue;//文号
    private String qualificationType;//资质类型
    private String realEstateAppraiser;//估价师
    private Integer areaId;//

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

    public String getPledger() {
        return pledger;
    }

    public void setPledger(String pledger) {
        this.pledger = pledger;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Date getHandleTieme() {
        return handleTieme;
    }

    public void setHandleTieme(Date handleTieme) {
        this.handleTieme = handleTieme;
    }

    public Date getPreauditNumberDate() {
        return preauditNumberDate;
    }

    public void setPreauditNumberDate(Date preauditNumberDate) {
        this.preauditNumberDate = preauditNumberDate;
    }

    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    public void setReportIssuanceDate(Date reportIssuanceDate) {
        this.reportIssuanceDate = reportIssuanceDate;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getAssessTotal() {
        return assessTotal;
    }

    public void setAssessTotal(BigDecimal assessTotal) {
        this.assessTotal = assessTotal;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
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

    public String getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(String appraiser) {
        this.appraiser = appraiser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
