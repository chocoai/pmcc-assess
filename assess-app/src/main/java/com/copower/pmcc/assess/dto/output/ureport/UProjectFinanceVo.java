package com.copower.pmcc.assess.dto.output.ureport;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2019-8-2.
 */
public class UProjectFinanceVo {
    private Integer id;
    private String projectName;//项目名称 -查询
    private String consignorName;//委托人 -查询
    private String reportUseUnitName;//报告使用单位 -查询
    private String preauditNumber;//评估报告文号 -查询
    private String technologyNumber;//技术报告文号 -查询
    private String resultNumber;//结果报告文号 -查询
    private Date reportNumberCreated;//报告文号创建时间 -查询
    private String projectManagerName;//项目经理 -查询
    private String contractName;//合同名称
    private BigDecimal contractPrice;//合同金额
    private BigDecimal amount;//开票金额
    private BigDecimal actualAmount;//实际开票金额（总金额）
    private BigDecimal payAmount;//付款金额
    private String entrustPurposeName;//委托目的
    private String departmentName;//评估部门
    private String loanTypeName;//贷款类型
    private String serviceComeFromExplain;//业务来源说明
    private Date projectCreated;//立项时间 -查询
    private BigDecimal debtAmount;//欠款金额

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

    public String getPreauditNumber() {
        return preauditNumber;
    }

    public void setPreauditNumber(String preauditNumber) {
        this.preauditNumber = preauditNumber;
    }

    public String getTechnologyNumber() {
        return technologyNumber;
    }

    public void setTechnologyNumber(String technologyNumber) {
        this.technologyNumber = technologyNumber;
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber;
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

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getProjectCreated() {
        return projectCreated;
    }

    public void setProjectCreated(Date projectCreated) {
        this.projectCreated = projectCreated;
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public String getServiceComeFromExplain() {
        return serviceComeFromExplain;
    }

    public void setServiceComeFromExplain(String serviceComeFromExplain) {
        this.serviceComeFromExplain = serviceComeFromExplain;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }
}
