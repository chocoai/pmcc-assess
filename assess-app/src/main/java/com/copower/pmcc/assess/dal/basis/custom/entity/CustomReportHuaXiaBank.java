package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportHuaXiaBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    private String unitName;//报告使用单位(支行)
    private String consignor;//委托人(委托企业名称)
    private Integer projectCategoryId;//项目类别（评估标的）
    private String projectCategoryName;//项目类别
    private String seat;//项目具体地址及位置
    private String pledger;//抵押人
    private BigDecimal acquirePrice;//抵押人取得项目所有权时支付的价格
    private Date acquireTieme;//抵押人取得项目所有权时间
    private BigDecimal assessTotal;//评估价值
    private String hasPreviews;//是否出具预评估报告
    private String previewsNumber;//预评报告文号
    private String hasResult;//是否出具正式报告
    private String resultNumber;//正式报告编号
    private BigDecimal contractPrice;//实际收费金额(合同金额)
    private BigDecimal standardPrice;//标准收费金额(合同金额)
    private String discount;//实际收费折扣
    private String reason;//未出具正式评估报告的详细原因
    private String remark;//备注
    private String numberValue;//文号
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

    public String getPledger() {
        return pledger;
    }

    public void setPledger(String pledger) {
        this.pledger = pledger;
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

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
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

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BigDecimal getAcquirePrice() {
        return acquirePrice;
    }

    public void setAcquirePrice(BigDecimal acquirePrice) {
        this.acquirePrice = acquirePrice;
    }

    public Date getAcquireTieme() {
        return acquireTieme;
    }

    public void setAcquireTieme(Date acquireTieme) {
        this.acquireTieme = acquireTieme;
    }

    public BigDecimal getAssessTotal() {
        return assessTotal;
    }

    public void setAssessTotal(BigDecimal assessTotal) {
        this.assessTotal = assessTotal;
    }

    public String getHasPreviews() {
        return hasPreviews;
    }

    public void setHasPreviews(String hasPreviews) {
        this.hasPreviews = hasPreviews;
    }

    public String getPreviewsNumber() {
        return previewsNumber;
    }

    public void setPreviewsNumber(String previewsNumber) {
        this.previewsNumber = previewsNumber;
    }

    public String getHasResult() {
        return hasResult;
    }

    public void setHasResult(String hasResult) {
        this.hasResult = hasResult;
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
