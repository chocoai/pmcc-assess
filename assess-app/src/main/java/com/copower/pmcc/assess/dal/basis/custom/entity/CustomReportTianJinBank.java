package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportTianJinBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    private String pledger;//抵押人
    private Integer loanType;
    private String loanTypeName;//抵押物
    private String seat;//位置
    private Date valuationDate;//价值时点
    private Date reportIssuanceDate;//报告日
    private BigDecimal area;//面积
    private BigDecimal assessTotal;//评估总价
    private String previewsNumber;//预估文号
    private String resultNumber;//正式报告文号
    private BigDecimal assessCost;//评估费用
    private BigDecimal standardCost;//标准收费
    private String discount;//折扣
    private String remark;//备注
    private String numberValue;//文号
    private String unitName;//使用单位
    private Integer areaId;//
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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

    public BigDecimal getAssessCost() {
        return assessCost;
    }

    public void setAssessCost(BigDecimal assessCost) {
        this.assessCost = assessCost;
    }

    public BigDecimal getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
