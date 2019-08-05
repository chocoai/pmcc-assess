package com.copower.pmcc.assess.dto.output.ureport;

import java.util.Date;

/**
 * Created by kings on 2019-8-2.
 */
public class UProjectFinanceVo {
    private Integer id;
    private String projectName;//项目名称 -查询
    private String consignorName;//委托人 -查询
    private String reportUseUnitName;//报告使用单位 -查询
    private String reportNumber;//报告文号 -查询
    private Date reportNumberCreated;//报告文号创建时间 -查询
    private String projectManagerName;//项目经理 -查询
    private String contractName;//合同名称
    private String contractPrice;//合同金额
    private String amount;//开票金额
    private String actualAmount;//实际金额
    private String payAmount;//支付金额


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getReportUseUnitName() {
        return reportUseUnitName;
    }

    public void setReportUseUnitName(String reportUseUnitName) {
        this.reportUseUnitName = reportUseUnitName;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Date getReportNumberCreated() {
        return reportNumberCreated;
    }

    public void setReportNumberCreated(Date reportNumberCreated) {
        this.reportNumberCreated = reportNumberCreated;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
