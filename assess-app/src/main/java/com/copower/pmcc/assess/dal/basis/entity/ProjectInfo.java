package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ProjectInfo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 项目大类
     */
    private Integer projectClassId;

    /**
     * 项目类型
     */
    private Integer projectTypeId;

    /**
     * 项目类别
     */
    private Integer projectCategoryId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 紧急程度
     */
    private Integer urgency;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 评估基准日
     */
    private Date valuationDate;

    /**
     * 委托目的名称
     */
    private String entrustPurposeName;

    /**
     * 委托目的
     */
    private Integer entrustPurpose;

    /**
     * 委托目的类别
     */
    private Integer entrustAimType;

    /**
     * 委托说明备注
     */
    private String remarkEntrustPurpose;

    /**
     * 价值类型
     */
    private Integer valueType;

    /**
     * 价值定义
     */
    private String remarkValueType;

    /**
     * 执业部门
     */
    private Integer departmentId;

    /**
     * 备注说明
     */
    private String remarks;

    /**
     * 计划完成时间
     */
    private Date completeDatePlan;

    /**
     * 实际完成日期
     */
    private Date completeDateActual;

    /**
     * 接收任务时间
     */
    private Date completeDateStart;

    /**
     * 流程实例编号
     */
    private String processInsId;

    /**
     * 评估(财产)范围
     */
    private String propertyScope;

    /**
     * 范围包含
     */
    private String scopeInclude;

    /**
     * 范围不包含
     */
    private String scopeNotInclude;

    /**
     * 贷款类型
     */
    private Integer loanType;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 合同金额
     */
    private BigDecimal contractPrice;

    /**
     * 业务来源
     */
    private String serviceComeFrom;

    /**
     * 业务来源说明
     */
    private String serviceComeFromExplain;

    /**
     * 流程状态
     */
    private String status;

    /**
     * 项目状态
     */
    private String projectStatus;

    /**
     * ERP中公共项目ID
     */
    private Integer publicProjectId;

    /**
     * 任务再分派流程实例编号
     */
    private String assignProcessInsId;

    /**
     * 任务再分派流程状态
     */
    private String assignStatus;

    /**
     * 楼盘名称
     */
    private String estateName;

    /**
     * 出预评报告时间
     */
    private Date preauditNumberDate;

    /**
     * 评估范围名称
     */
    private String propertyScopeName;

    /**
     * 出结果报告文号时间
     */
    private Date resultNumberDate;

    /**
     * 
     */
    private Boolean bisAssign;

    /**
     * 年度编号(每年从1开始计算)
     */
    private Integer serialNumber;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * tb_project_info
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 项目大类
     * @return project_class_id 项目大类
     */
    public Integer getProjectClassId() {
        return projectClassId;
    }

    /**
     * 项目大类
     * @param projectClassId 项目大类
     */
    public void setProjectClassId(Integer projectClassId) {
        this.projectClassId = projectClassId;
    }

    /**
     * 项目类型
     * @return project_type_id 项目类型
     */
    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    /**
     * 项目类型
     * @param projectTypeId 项目类型
     */
    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    /**
     * 项目类别
     * @return project_category_id 项目类别
     */
    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    /**
     * 项目类别
     * @param projectCategoryId 项目类别
     */
    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    /**
     * 项目名称
     * @return project_name 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 项目名称
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * 紧急程度
     * @return urgency 紧急程度
     */
    public Integer getUrgency() {
        return urgency;
    }

    /**
     * 紧急程度
     * @param urgency 紧急程度
     */
    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    /**
     * 省
     * @return province 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 市
     * @return city 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 市
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 区县
     * @return district 区县
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区县
     * @param district 区县
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 评估基准日
     * @return valuation_date 评估基准日
     */
    public Date getValuationDate() {
        return valuationDate;
    }

    /**
     * 评估基准日
     * @param valuationDate 评估基准日
     */
    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }

    /**
     * 委托目的名称
     * @return entrust_purpose_name 委托目的名称
     */
    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    /**
     * 委托目的名称
     * @param entrustPurposeName 委托目的名称
     */
    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName == null ? null : entrustPurposeName.trim();
    }

    /**
     * 委托目的
     * @return entrust_purpose 委托目的
     */
    public Integer getEntrustPurpose() {
        return entrustPurpose;
    }

    /**
     * 委托目的
     * @param entrustPurpose 委托目的
     */
    public void setEntrustPurpose(Integer entrustPurpose) {
        this.entrustPurpose = entrustPurpose;
    }

    /**
     * 委托目的类别
     * @return entrust_aim_type 委托目的类别
     */
    public Integer getEntrustAimType() {
        return entrustAimType;
    }

    /**
     * 委托目的类别
     * @param entrustAimType 委托目的类别
     */
    public void setEntrustAimType(Integer entrustAimType) {
        this.entrustAimType = entrustAimType;
    }

    /**
     * 委托说明备注
     * @return remark_entrust_purpose 委托说明备注
     */
    public String getRemarkEntrustPurpose() {
        return remarkEntrustPurpose;
    }

    /**
     * 委托说明备注
     * @param remarkEntrustPurpose 委托说明备注
     */
    public void setRemarkEntrustPurpose(String remarkEntrustPurpose) {
        this.remarkEntrustPurpose = remarkEntrustPurpose == null ? null : remarkEntrustPurpose.trim();
    }

    /**
     * 价值类型
     * @return value_type 价值类型
     */
    public Integer getValueType() {
        return valueType;
    }

    /**
     * 价值类型
     * @param valueType 价值类型
     */
    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    /**
     * 价值定义
     * @return remark_value_type 价值定义
     */
    public String getRemarkValueType() {
        return remarkValueType;
    }

    /**
     * 价值定义
     * @param remarkValueType 价值定义
     */
    public void setRemarkValueType(String remarkValueType) {
        this.remarkValueType = remarkValueType == null ? null : remarkValueType.trim();
    }

    /**
     * 执业部门
     * @return department_id 执业部门
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 执业部门
     * @param departmentId 执业部门
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 备注说明
     * @return remarks 备注说明
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注说明
     * @param remarks 备注说明
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 计划完成时间
     * @return complete_date_plan 计划完成时间
     */
    public Date getCompleteDatePlan() {
        return completeDatePlan;
    }

    /**
     * 计划完成时间
     * @param completeDatePlan 计划完成时间
     */
    public void setCompleteDatePlan(Date completeDatePlan) {
        this.completeDatePlan = completeDatePlan;
    }

    /**
     * 实际完成日期
     * @return complete_date_actual 实际完成日期
     */
    public Date getCompleteDateActual() {
        return completeDateActual;
    }

    /**
     * 实际完成日期
     * @param completeDateActual 实际完成日期
     */
    public void setCompleteDateActual(Date completeDateActual) {
        this.completeDateActual = completeDateActual;
    }

    /**
     * 接收任务时间
     * @return complete_date_start 接收任务时间
     */
    public Date getCompleteDateStart() {
        return completeDateStart;
    }

    /**
     * 接收任务时间
     * @param completeDateStart 接收任务时间
     */
    public void setCompleteDateStart(Date completeDateStart) {
        this.completeDateStart = completeDateStart;
    }

    /**
     * 流程实例编号
     * @return process_ins_id 流程实例编号
     */
    public String getProcessInsId() {
        return processInsId;
    }

    /**
     * 流程实例编号
     * @param processInsId 流程实例编号
     */
    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    /**
     * 评估(财产)范围
     * @return property_scope 评估(财产)范围
     */
    public String getPropertyScope() {
        return propertyScope;
    }

    /**
     * 评估(财产)范围
     * @param propertyScope 评估(财产)范围
     */
    public void setPropertyScope(String propertyScope) {
        this.propertyScope = propertyScope == null ? null : propertyScope.trim();
    }

    /**
     * 范围包含
     * @return scope_include 范围包含
     */
    public String getScopeInclude() {
        return scopeInclude;
    }

    /**
     * 范围包含
     * @param scopeInclude 范围包含
     */
    public void setScopeInclude(String scopeInclude) {
        this.scopeInclude = scopeInclude == null ? null : scopeInclude.trim();
    }

    /**
     * 范围不包含
     * @return scope_not_include 范围不包含
     */
    public String getScopeNotInclude() {
        return scopeNotInclude;
    }

    /**
     * 范围不包含
     * @param scopeNotInclude 范围不包含
     */
    public void setScopeNotInclude(String scopeNotInclude) {
        this.scopeNotInclude = scopeNotInclude == null ? null : scopeNotInclude.trim();
    }

    /**
     * 贷款类型
     * @return loan_type 贷款类型
     */
    public Integer getLoanType() {
        return loanType;
    }

    /**
     * 贷款类型
     * @param loanType 贷款类型
     */
    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    /**
     * 合同id
     * @return contract_id 合同id
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * 合同id
     * @param contractId 合同id
     */
    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    /**
     * 合同名称
     * @return contract_name 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 合同名称
     * @param contractName 合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    /**
     * 合同金额
     * @return contract_price 合同金额
     */
    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    /**
     * 合同金额
     * @param contractPrice 合同金额
     */
    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * 业务来源
     * @return service_come_from 业务来源
     */
    public String getServiceComeFrom() {
        return serviceComeFrom;
    }

    /**
     * 业务来源
     * @param serviceComeFrom 业务来源
     */
    public void setServiceComeFrom(String serviceComeFrom) {
        this.serviceComeFrom = serviceComeFrom == null ? null : serviceComeFrom.trim();
    }

    /**
     * 业务来源说明
     * @return service_come_from_explain 业务来源说明
     */
    public String getServiceComeFromExplain() {
        return serviceComeFromExplain;
    }

    /**
     * 业务来源说明
     * @param serviceComeFromExplain 业务来源说明
     */
    public void setServiceComeFromExplain(String serviceComeFromExplain) {
        this.serviceComeFromExplain = serviceComeFromExplain == null ? null : serviceComeFromExplain.trim();
    }

    /**
     * 流程状态
     * @return status 流程状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 流程状态
     * @param status 流程状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 项目状态
     * @return project_status 项目状态
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * 项目状态
     * @param projectStatus 项目状态
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    /**
     * ERP中公共项目ID
     * @return public_project_id ERP中公共项目ID
     */
    public Integer getPublicProjectId() {
        return publicProjectId;
    }

    /**
     * ERP中公共项目ID
     * @param publicProjectId ERP中公共项目ID
     */
    public void setPublicProjectId(Integer publicProjectId) {
        this.publicProjectId = publicProjectId;
    }

    /**
     * 任务再分派流程实例编号
     * @return assign_process_ins_id 任务再分派流程实例编号
     */
    public String getAssignProcessInsId() {
        return assignProcessInsId;
    }

    /**
     * 任务再分派流程实例编号
     * @param assignProcessInsId 任务再分派流程实例编号
     */
    public void setAssignProcessInsId(String assignProcessInsId) {
        this.assignProcessInsId = assignProcessInsId == null ? null : assignProcessInsId.trim();
    }

    /**
     * 任务再分派流程状态
     * @return assign_status 任务再分派流程状态
     */
    public String getAssignStatus() {
        return assignStatus;
    }

    /**
     * 任务再分派流程状态
     * @param assignStatus 任务再分派流程状态
     */
    public void setAssignStatus(String assignStatus) {
        this.assignStatus = assignStatus == null ? null : assignStatus.trim();
    }

    /**
     * 楼盘名称
     * @return estate_name 楼盘名称
     */
    public String getEstateName() {
        return estateName;
    }

    /**
     * 楼盘名称
     * @param estateName 楼盘名称
     */
    public void setEstateName(String estateName) {
        this.estateName = estateName == null ? null : estateName.trim();
    }

    /**
     * 出预评报告时间
     * @return preaudit_number_date 出预评报告时间
     */
    public Date getPreauditNumberDate() {
        return preauditNumberDate;
    }

    /**
     * 出预评报告时间
     * @param preauditNumberDate 出预评报告时间
     */
    public void setPreauditNumberDate(Date preauditNumberDate) {
        this.preauditNumberDate = preauditNumberDate;
    }

    /**
     * 评估范围名称
     * @return property_scope_name 评估范围名称
     */
    public String getPropertyScopeName() {
        return propertyScopeName;
    }

    /**
     * 评估范围名称
     * @param propertyScopeName 评估范围名称
     */
    public void setPropertyScopeName(String propertyScopeName) {
        this.propertyScopeName = propertyScopeName == null ? null : propertyScopeName.trim();
    }

    /**
     * 出结果报告文号时间
     * @return result_number_date 出结果报告文号时间
     */
    public Date getResultNumberDate() {
        return resultNumberDate;
    }

    /**
     * 出结果报告文号时间
     * @param resultNumberDate 出结果报告文号时间
     */
    public void setResultNumberDate(Date resultNumberDate) {
        this.resultNumberDate = resultNumberDate;
    }

    /**
     * 
     * @return bis_assign 
     */
    public Boolean getBisAssign() {
        return bisAssign;
    }

    /**
     * 
     * @param bisAssign 
     */
    public void setBisAssign(Boolean bisAssign) {
        this.bisAssign = bisAssign;
    }

    /**
     * 年度编号(每年从1开始计算)
     * @return serial_number 年度编号(每年从1开始计算)
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 年度编号(每年从1开始计算)
     * @param serialNumber 年度编号(每年从1开始计算)
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public static ProjectInfo.Builder builder() {
        return new ProjectInfo.Builder();
    }

    /**
     * tb_project_info
     */
    public static class Builder {
        /**
         * tb_project_info
         */
        private ProjectInfo obj;

        public Builder() {
            this.obj = new ProjectInfo();
        }

        /**
         * id
         * @param id id
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 项目大类
         * @param projectClassId 项目大类
         */
        public Builder projectClassId(Integer projectClassId) {
            obj.setProjectClassId(projectClassId);
            return this;
        }

        /**
         * 项目类型
         * @param projectTypeId 项目类型
         */
        public Builder projectTypeId(Integer projectTypeId) {
            obj.setProjectTypeId(projectTypeId);
            return this;
        }

        /**
         * 项目类别
         * @param projectCategoryId 项目类别
         */
        public Builder projectCategoryId(Integer projectCategoryId) {
            obj.setProjectCategoryId(projectCategoryId);
            return this;
        }

        /**
         * 项目名称
         * @param projectName 项目名称
         */
        public Builder projectName(String projectName) {
            obj.setProjectName(projectName);
            return this;
        }

        /**
         * 紧急程度
         * @param urgency 紧急程度
         */
        public Builder urgency(Integer urgency) {
            obj.setUrgency(urgency);
            return this;
        }

        /**
         * 省
         * @param province 省
         */
        public Builder province(String province) {
            obj.setProvince(province);
            return this;
        }

        /**
         * 市
         * @param city 市
         */
        public Builder city(String city) {
            obj.setCity(city);
            return this;
        }

        /**
         * 区县
         * @param district 区县
         */
        public Builder district(String district) {
            obj.setDistrict(district);
            return this;
        }

        /**
         * 评估基准日
         * @param valuationDate 评估基准日
         */
        public Builder valuationDate(Date valuationDate) {
            obj.setValuationDate(valuationDate);
            return this;
        }

        /**
         * 委托目的
         * @param entrustPurpose 委托目的
         */
        public Builder entrustPurpose(Integer entrustPurpose) {
            obj.setEntrustPurpose(entrustPurpose);
            return this;
        }

        /**
         * 委托目的名称
         * @param entrustPurposeName 委托目的名称
         */
        public Builder entrustPurposeName(String entrustPurposeName) {
            obj.setEntrustPurposeName(entrustPurposeName);
            return this;
        }

        /**
         * 委托目的类别
         * @param entrustAimType 委托目的类别
         */
        public Builder entrustAimType(Integer entrustAimType) {
            obj.setEntrustAimType(entrustAimType);
            return this;
        }

        /**
         * 委托说明备注
         * @param remarkEntrustPurpose 委托说明备注
         */
        public Builder remarkEntrustPurpose(String remarkEntrustPurpose) {
            obj.setRemarkEntrustPurpose(remarkEntrustPurpose);
            return this;
        }

        /**
         * 价值类型
         * @param valueType 价值类型
         */
        public Builder valueType(Integer valueType) {
            obj.setValueType(valueType);
            return this;
        }

        /**
         * 价值定义
         * @param remarkValueType 价值定义
         */
        public Builder remarkValueType(String remarkValueType) {
            obj.setRemarkValueType(remarkValueType);
            return this;
        }

        /**
         * 执业部门
         * @param departmentId 执业部门
         */
        public Builder departmentId(Integer departmentId) {
            obj.setDepartmentId(departmentId);
            return this;
        }

        /**
         * 备注说明
         * @param remarks 备注说明
         */
        public Builder remarks(String remarks) {
            obj.setRemarks(remarks);
            return this;
        }

        /**
         * 计划完成时间
         * @param completeDatePlan 计划完成时间
         */
        public Builder completeDatePlan(Date completeDatePlan) {
            obj.setCompleteDatePlan(completeDatePlan);
            return this;
        }

        /**
         * 实际完成日期
         * @param completeDateActual 实际完成日期
         */
        public Builder completeDateActual(Date completeDateActual) {
            obj.setCompleteDateActual(completeDateActual);
            return this;
        }

        /**
         * 接收任务时间
         * @param completeDateStart 接收任务时间
         */
        public Builder completeDateStart(Date completeDateStart) {
            obj.setCompleteDateStart(completeDateStart);
            return this;
        }

        /**
         * 流程实例编号
         * @param processInsId 流程实例编号
         */
        public Builder processInsId(String processInsId) {
            obj.setProcessInsId(processInsId);
            return this;
        }

        /**
         * 评估(财产)范围
         * @param propertyScope 评估(财产)范围
         */
        public Builder propertyScope(String propertyScope) {
            obj.setPropertyScope(propertyScope);
            return this;
        }

        /**
         * 评估范围名称
         * @param propertyScopeName 评估范围名称
         */
        public Builder propertyScopeName(String propertyScopeName) {
            obj.setPropertyScopeName(propertyScopeName);
            return this;
        }

        /**
         * 范围包含
         * @param scopeInclude 范围包含
         */
        public Builder scopeInclude(String scopeInclude) {
            obj.setScopeInclude(scopeInclude);
            return this;
        }

        /**
         * 范围不包含
         * @param scopeNotInclude 范围不包含
         */
        public Builder scopeNotInclude(String scopeNotInclude) {
            obj.setScopeNotInclude(scopeNotInclude);
            return this;
        }

        /**
         * 贷款类型
         * @param loanType 贷款类型
         */
        public Builder loanType(Integer loanType) {
            obj.setLoanType(loanType);
            return this;
        }

        /**
         * 合同id
         * @param contractId 合同id
         */
        public Builder contractId(String contractId) {
            obj.setContractId(contractId);
            return this;
        }

        /**
         * 合同名称
         * @param contractName 合同名称
         */
        public Builder contractName(String contractName) {
            obj.setContractName(contractName);
            return this;
        }

        /**
         * 合同金额
         * @param contractPrice 合同金额
         */
        public Builder contractPrice(BigDecimal contractPrice) {
            obj.setContractPrice(contractPrice);
            return this;
        }

        /**
         * 业务来源
         * @param serviceComeFrom 业务来源
         */
        public Builder serviceComeFrom(String serviceComeFrom) {
            obj.setServiceComeFrom(serviceComeFrom);
            return this;
        }

        /**
         * 业务来源说明
         * @param serviceComeFromExplain 业务来源说明
         */
        public Builder serviceComeFromExplain(String serviceComeFromExplain) {
            obj.setServiceComeFromExplain(serviceComeFromExplain);
            return this;
        }

        /**
         * 流程状态
         * @param status 流程状态
         */
        public Builder status(String status) {
            obj.setStatus(status);
            return this;
        }

        /**
         * 项目状态
         * @param projectStatus 项目状态
         */
        public Builder projectStatus(String projectStatus) {
            obj.setProjectStatus(projectStatus);
            return this;
        }

        /**
         * ERP中公共项目ID
         * @param publicProjectId ERP中公共项目ID
         */
        public Builder publicProjectId(Integer publicProjectId) {
            obj.setPublicProjectId(publicProjectId);
            return this;
        }

        /**
         * 任务再分派流程实例编号
         * @param assignProcessInsId 任务再分派流程实例编号
         */
        public Builder assignProcessInsId(String assignProcessInsId) {
            obj.setAssignProcessInsId(assignProcessInsId);
            return this;
        }

        /**
         * 任务再分派流程状态
         * @param assignStatus 任务再分派流程状态
         */
        public Builder assignStatus(String assignStatus) {
            obj.setAssignStatus(assignStatus);
            return this;
        }

        /**
         * 楼盘名称
         * @param estateName 楼盘名称
         */
        public Builder estateName(String estateName) {
            obj.setEstateName(estateName);
            return this;
        }

        /**
         * 出预评报告时间
         * @param preauditNumberDate 出预评报告时间
         */
        public Builder preauditNumberDate(Date preauditNumberDate) {
            obj.setPreauditNumberDate(preauditNumberDate);
            return this;
        }

        /**
         * 出结果报告文号时间
         * @param resultNumberDate 出结果报告文号时间
         */
        public Builder resultNumberDate(Date resultNumberDate) {
            obj.setResultNumberDate(resultNumberDate);
            return this;
        }

        /**
         * 
         * @param bisAssign 
         */
        public Builder bisAssign(Boolean bisAssign) {
            obj.setBisAssign(bisAssign);
            return this;
        }

        /**
         * 年度编号(每年从1开始计算)
         * @param serialNumber 年度编号(每年从1开始计算)
         */
        public Builder serialNumber(Integer serialNumber) {
            obj.setSerialNumber(serialNumber);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        public ProjectInfo build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        projectClassId("project_class_id", "projectClassId", "INTEGER", false),
        projectTypeId("project_type_id", "projectTypeId", "INTEGER", false),
        projectCategoryId("project_category_id", "projectCategoryId", "INTEGER", false),
        projectName("project_name", "projectName", "VARCHAR", false),
        urgency("urgency", "urgency", "INTEGER", false),
        province("province", "province", "VARCHAR", false),
        city("city", "city", "VARCHAR", false),
        district("district", "district", "VARCHAR", false),
        valuationDate("valuation_date", "valuationDate", "TIMESTAMP", false),
        entrustPurposeName("entrust_purpose_name", "entrustPurposeName", "VARCHAR", false),
        entrustPurpose("entrust_purpose", "entrustPurpose", "INTEGER", false),
        entrustAimType("entrust_aim_type", "entrustAimType", "INTEGER", false),
        remarkEntrustPurpose("remark_entrust_purpose", "remarkEntrustPurpose", "VARCHAR", false),
        valueType("value_type", "valueType", "INTEGER", false),
        remarkValueType("remark_value_type", "remarkValueType", "VARCHAR", false),
        departmentId("department_id", "departmentId", "INTEGER", false),
        remarks("remarks", "remarks", "VARCHAR", false),
        completeDatePlan("complete_date_plan", "completeDatePlan", "TIMESTAMP", false),
        completeDateActual("complete_date_actual", "completeDateActual", "TIMESTAMP", false),
        completeDateStart("complete_date_start", "completeDateStart", "TIMESTAMP", false),
        processInsId("process_ins_id", "processInsId", "VARCHAR", false),
        propertyScope("property_scope", "propertyScope", "VARCHAR", false),
        scopeInclude("scope_include", "scopeInclude", "VARCHAR", false),
        scopeNotInclude("scope_not_include", "scopeNotInclude", "VARCHAR", false),
        loanType("loan_type", "loanType", "INTEGER", false),
        contractId("contract_id", "contractId", "VARCHAR", false),
        contractName("contract_name", "contractName", "VARCHAR", false),
        contractPrice("contract_price", "contractPrice", "DECIMAL", false),
        serviceComeFrom("service_come_from", "serviceComeFrom", "VARCHAR", false),
        serviceComeFromExplain("service_come_from_explain", "serviceComeFromExplain", "VARCHAR", false),
        status("status", "status", "VARCHAR", false),
        projectStatus("project_status", "projectStatus", "VARCHAR", false),
        publicProjectId("public_project_id", "publicProjectId", "INTEGER", false),
        assignProcessInsId("assign_process_ins_id", "assignProcessInsId", "VARCHAR", false),
        assignStatus("assign_status", "assignStatus", "VARCHAR", false),
        estateName("estate_name", "estateName", "VARCHAR", false),
        preauditNumberDate("preaudit_number_date", "preauditNumberDate", "TIMESTAMP", false),
        propertyScopeName("property_scope_name", "propertyScopeName", "VARCHAR", false),
        resultNumberDate("result_number_date", "resultNumberDate", "TIMESTAMP", false),
        bisAssign("bis_assign", "bisAssign", "BIT", false),
        serialNumber("serial_number", "serialNumber", "INTEGER", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_project_info
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_project_info
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_project_info
         */
        private final String column;

        /**
         * tb_project_info
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_project_info
         */
        private final String javaProperty;

        /**
         * tb_project_info
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}