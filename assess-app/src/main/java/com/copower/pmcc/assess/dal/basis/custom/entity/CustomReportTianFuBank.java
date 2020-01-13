package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportTianFuBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    private Date valuationDate;//评估时间
    private String unitName;//报告使用单位(所属支行)
    private String consignor;//委托人(客户名称)
    private BigDecimal assessTotal;//借款金额
    private Integer projectCategoryId;
    private String projectCategoryName;//抵押物品种

    private String assessOrganization;//评估机构
    private String numberValue;//评估报告编号
    private BigDecimal assessCost;//评估费金额
    private String customerManager;//经办客户经理
    private String principal;//机构负责人
    private Date enterTieme;//入账日期
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

    public BigDecimal getAssessTotal() {
        return assessTotal;
    }

    public void setAssessTotal(BigDecimal assessTotal) {
        this.assessTotal = assessTotal;
    }


    public String getAssessOrganization() {
        return assessOrganization;
    }

    public void setAssessOrganization(String assessOrganization) {
        this.assessOrganization = assessOrganization;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public BigDecimal getAssessCost() {
        return assessCost;
    }

    public void setAssessCost(BigDecimal assessCost) {
        this.assessCost = assessCost;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Date getEnterTieme() {
        return enterTieme;
    }

    public void setEnterTieme(Date enterTieme) {
        this.enterTieme = enterTieme;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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
}
