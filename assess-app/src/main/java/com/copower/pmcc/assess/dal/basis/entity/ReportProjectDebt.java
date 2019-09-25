package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ReportProjectDebt {
    private Integer id;

    private Integer projectId;//*

    private Integer publicProjectId;//*

    private String projectName;//*

    private String consignorName;//*

    private String entrustPurposeName;//*

    private String departmentName;//*

    private String loanTypeName;//*

    private String reportUseUnitName;//*

    private String preauditNumber;

    private String resultNumber;

    private String projectManagerName;//*

    private BigDecimal contractPrice;//*

    private BigDecimal amount;//*

    private BigDecimal actualAmount;//*

    private BigDecimal payAmount;//*

    private BigDecimal debtAmount;//*

    private Boolean bisHasDebt;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

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

    public Integer getPublicProjectId() {
        return publicProjectId;
    }

    public void setPublicProjectId(Integer publicProjectId) {
        this.publicProjectId = publicProjectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName == null ? null : consignorName.trim();
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName == null ? null : entrustPurposeName.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName == null ? null : loanTypeName.trim();
    }

    public String getReportUseUnitName() {
        return reportUseUnitName;
    }

    public void setReportUseUnitName(String reportUseUnitName) {
        this.reportUseUnitName = reportUseUnitName == null ? null : reportUseUnitName.trim();
    }

    public String getPreauditNumber() {
        return preauditNumber;
    }

    public void setPreauditNumber(String preauditNumber) {
        this.preauditNumber = preauditNumber == null ? null : preauditNumber.trim();
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber == null ? null : resultNumber.trim();
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName == null ? null : projectManagerName.trim();
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

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Boolean getBisHasDebt() {
        return bisHasDebt;
    }

    public void setBisHasDebt(Boolean bisHasDebt) {
        this.bisHasDebt = bisHasDebt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}