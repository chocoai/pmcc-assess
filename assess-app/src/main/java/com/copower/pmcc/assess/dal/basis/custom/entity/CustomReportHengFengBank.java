package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportHengFengBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    private String month;//月份
    private String consignor;//委托人(委托企业名称)
    private String unitName;//报告使用单位(支行)
    private String numberValue;//报告编号
    private String projectName;//项目名称
    private String workDate;//工作日期
    private Integer projectCategoryId;
    private String projectCategoryName;//抵押物性质
    private String seat;//抵押物地址
    private BigDecimal area;//面积
    private BigDecimal assessTotal;//评估金额
    private String methodNames;//评估方法
    private String appraiser;//签字估价师
    private String loan;//贷款金额
    private String pledgeRatio;//抵押率
    private String chargePrice;//收费金额
    private String assessCompany;//评估公司
    private String remark;//备注
    private String qualificationType;//资质类型
    private String realEstateAppraiser;//估价师
    private Integer areaId;//
    private Date reportIssuanceDate;
    private Date homeWorkEndTime;
    private Date valuationDate;

    private Date preauditNumberDate;//预评时间
    private Date resultNumberDate;//结果时间

    public Date getPreauditNumberDate() {
        return preauditNumberDate;
    }

    public void setPreauditNumberDate(Date preauditNumberDate) {
        this.preauditNumberDate = preauditNumberDate;
    }

    public Date getResultNumberDate() {
        return resultNumberDate;
    }

    public void setResultNumberDate(Date resultNumberDate) {
        this.resultNumberDate = resultNumberDate;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    public void setReportIssuanceDate(Date reportIssuanceDate) {
        this.reportIssuanceDate = reportIssuanceDate;
    }

    public Date getHomeWorkEndTime() {
        return homeWorkEndTime;
    }

    public void setHomeWorkEndTime(Date homeWorkEndTime) {
        this.homeWorkEndTime = homeWorkEndTime;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
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

    public String getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(String appraiser) {
        this.appraiser = appraiser;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(String pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    public String getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(String chargePrice) {
        this.chargePrice = chargePrice;
    }

    public String getAssessCompany() {
        return assessCompany;
    }

    public void setAssessCompany(String assessCompany) {
        this.assessCompany = assessCompany;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
