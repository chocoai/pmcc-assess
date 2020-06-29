package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectInfo {
    private Integer id;

    private Integer projectClassId;

    private Integer projectTypeId;

    private Integer projectCategoryId;

    private String projectName;

    private Integer urgency;

    private String province;

    private String city;

    private String district;

    private Date valuationDate;

    private String entrustPurposeName;

    private Integer entrustPurpose;

    private Integer entrustAimType;

    private String remarkEntrustPurpose;

    private Integer valueType;

    private String remarkValueType;

    private Integer departmentId;

    private String remarks;

    private Date completeDatePlan;

    private Date completeDateActual;

    private Date completeDateStart;

    private String processInsId;

    private String propertyScope;

    private String scopeInclude;

    private String scopeNotInclude;

    private Integer loanType;

    private String contractId;

    private String contractName;

    private BigDecimal contractPrice;

    private String serviceComeFrom;

    private String serviceComeFromExplain;

    private String status;

    private String projectStatus;

    private Integer publicProjectId;

    private String assignProcessInsId;

    private String assignStatus;

    private String estateName;

    private Date preauditNumberDate;

    private String propertyScopeName;

    private Date resultNumberDate;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    private Boolean bisAssign;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectClassId() {
        return projectClassId;
    }

    public void setProjectClassId(Integer projectClassId) {
        this.projectClassId = projectClassId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName == null ? null : entrustPurposeName.trim();
    }

    public Integer getEntrustPurpose() {
        return entrustPurpose;
    }

    public void setEntrustPurpose(Integer entrustPurpose) {
        this.entrustPurpose = entrustPurpose;
    }

    public Integer getEntrustAimType() {
        return entrustAimType;
    }

    public void setEntrustAimType(Integer entrustAimType) {
        this.entrustAimType = entrustAimType;
    }

    public String getRemarkEntrustPurpose() {
        return remarkEntrustPurpose;
    }

    public void setRemarkEntrustPurpose(String remarkEntrustPurpose) {
        this.remarkEntrustPurpose = remarkEntrustPurpose == null ? null : remarkEntrustPurpose.trim();
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public String getRemarkValueType() {
        return remarkValueType;
    }

    public void setRemarkValueType(String remarkValueType) {
        this.remarkValueType = remarkValueType == null ? null : remarkValueType.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCompleteDatePlan() {
        return completeDatePlan;
    }

    public void setCompleteDatePlan(Date completeDatePlan) {
        this.completeDatePlan = completeDatePlan;
    }

    public Date getCompleteDateActual() {
        return completeDateActual;
    }

    public void setCompleteDateActual(Date completeDateActual) {
        this.completeDateActual = completeDateActual;
    }

    public Date getCompleteDateStart() {
        return completeDateStart;
    }

    public void setCompleteDateStart(Date completeDateStart) {
        this.completeDateStart = completeDateStart;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    public String getPropertyScope() {
        return propertyScope;
    }

    public void setPropertyScope(String propertyScope) {
        this.propertyScope = propertyScope == null ? null : propertyScope.trim();
    }

    public String getScopeInclude() {
        return scopeInclude;
    }

    public void setScopeInclude(String scopeInclude) {
        this.scopeInclude = scopeInclude == null ? null : scopeInclude.trim();
    }

    public String getScopeNotInclude() {
        return scopeNotInclude;
    }

    public void setScopeNotInclude(String scopeNotInclude) {
        this.scopeNotInclude = scopeNotInclude == null ? null : scopeNotInclude.trim();
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public String getServiceComeFrom() {
        return serviceComeFrom;
    }

    public void setServiceComeFrom(String serviceComeFrom) {
        this.serviceComeFrom = serviceComeFrom == null ? null : serviceComeFrom.trim();
    }

    public String getServiceComeFromExplain() {
        return serviceComeFromExplain;
    }

    public void setServiceComeFromExplain(String serviceComeFromExplain) {
        this.serviceComeFromExplain = serviceComeFromExplain == null ? null : serviceComeFromExplain.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public Integer getPublicProjectId() {
        return publicProjectId;
    }

    public void setPublicProjectId(Integer publicProjectId) {
        this.publicProjectId = publicProjectId;
    }

    public String getAssignProcessInsId() {
        return assignProcessInsId;
    }

    public void setAssignProcessInsId(String assignProcessInsId) {
        this.assignProcessInsId = assignProcessInsId == null ? null : assignProcessInsId.trim();
    }

    public String getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(String assignStatus) {
        this.assignStatus = assignStatus == null ? null : assignStatus.trim();
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName == null ? null : estateName.trim();
    }

    public Date getPreauditNumberDate() {
        return preauditNumberDate;
    }

    public void setPreauditNumberDate(Date preauditNumberDate) {
        this.preauditNumberDate = preauditNumberDate;
    }

    public String getPropertyScopeName() {
        return propertyScopeName;
    }

    public void setPropertyScopeName(String propertyScopeName) {
        this.propertyScopeName = propertyScopeName == null ? null : propertyScopeName.trim();
    }

    public Date getResultNumberDate() {
        return resultNumberDate;
    }

    public void setResultNumberDate(Date resultNumberDate) {
        this.resultNumberDate = resultNumberDate;
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

    public Boolean getBisAssign() {
        return bisAssign;
    }

    public void setBisAssign(Boolean bisAssign) {
        this.bisAssign = bisAssign;
    }
}