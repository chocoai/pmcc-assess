package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportProjectDebtExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportProjectDebtExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdIsNull() {
            addCriterion("public_project_id is null");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdIsNotNull() {
            addCriterion("public_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdEqualTo(Integer value) {
            addCriterion("public_project_id =", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdNotEqualTo(Integer value) {
            addCriterion("public_project_id <>", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdGreaterThan(Integer value) {
            addCriterion("public_project_id >", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("public_project_id >=", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdLessThan(Integer value) {
            addCriterion("public_project_id <", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("public_project_id <=", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdIn(List<Integer> values) {
            addCriterion("public_project_id in", values, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdNotIn(List<Integer> values) {
            addCriterion("public_project_id not in", values, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("public_project_id between", value1, value2, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("public_project_id not between", value1, value2, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameIsNull() {
            addCriterion("consignor_name is null");
            return (Criteria) this;
        }

        public Criteria andConsignorNameIsNotNull() {
            addCriterion("consignor_name is not null");
            return (Criteria) this;
        }

        public Criteria andConsignorNameEqualTo(String value) {
            addCriterion("consignor_name =", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameNotEqualTo(String value) {
            addCriterion("consignor_name <>", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameGreaterThan(String value) {
            addCriterion("consignor_name >", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameGreaterThanOrEqualTo(String value) {
            addCriterion("consignor_name >=", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameLessThan(String value) {
            addCriterion("consignor_name <", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameLessThanOrEqualTo(String value) {
            addCriterion("consignor_name <=", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameLike(String value) {
            addCriterion("consignor_name like", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameNotLike(String value) {
            addCriterion("consignor_name not like", value, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameIn(List<String> values) {
            addCriterion("consignor_name in", values, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameNotIn(List<String> values) {
            addCriterion("consignor_name not in", values, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameBetween(String value1, String value2) {
            addCriterion("consignor_name between", value1, value2, "consignorName");
            return (Criteria) this;
        }

        public Criteria andConsignorNameNotBetween(String value1, String value2) {
            addCriterion("consignor_name not between", value1, value2, "consignorName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameIsNull() {
            addCriterion("entrust_purpose_name is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameIsNotNull() {
            addCriterion("entrust_purpose_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameEqualTo(String value) {
            addCriterion("entrust_purpose_name =", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotEqualTo(String value) {
            addCriterion("entrust_purpose_name <>", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameGreaterThan(String value) {
            addCriterion("entrust_purpose_name >", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_purpose_name >=", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameLessThan(String value) {
            addCriterion("entrust_purpose_name <", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameLessThanOrEqualTo(String value) {
            addCriterion("entrust_purpose_name <=", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameLike(String value) {
            addCriterion("entrust_purpose_name like", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotLike(String value) {
            addCriterion("entrust_purpose_name not like", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameIn(List<String> values) {
            addCriterion("entrust_purpose_name in", values, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotIn(List<String> values) {
            addCriterion("entrust_purpose_name not in", values, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameBetween(String value1, String value2) {
            addCriterion("entrust_purpose_name between", value1, value2, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotBetween(String value1, String value2) {
            addCriterion("entrust_purpose_name not between", value1, value2, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameIsNull() {
            addCriterion("loan_type_name is null");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameIsNotNull() {
            addCriterion("loan_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameEqualTo(String value) {
            addCriterion("loan_type_name =", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameNotEqualTo(String value) {
            addCriterion("loan_type_name <>", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameGreaterThan(String value) {
            addCriterion("loan_type_name >", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("loan_type_name >=", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameLessThan(String value) {
            addCriterion("loan_type_name <", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameLessThanOrEqualTo(String value) {
            addCriterion("loan_type_name <=", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameLike(String value) {
            addCriterion("loan_type_name like", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameNotLike(String value) {
            addCriterion("loan_type_name not like", value, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameIn(List<String> values) {
            addCriterion("loan_type_name in", values, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameNotIn(List<String> values) {
            addCriterion("loan_type_name not in", values, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameBetween(String value1, String value2) {
            addCriterion("loan_type_name between", value1, value2, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNameNotBetween(String value1, String value2) {
            addCriterion("loan_type_name not between", value1, value2, "loanTypeName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameIsNull() {
            addCriterion("report_use_unit_name is null");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameIsNotNull() {
            addCriterion("report_use_unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameEqualTo(String value) {
            addCriterion("report_use_unit_name =", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameNotEqualTo(String value) {
            addCriterion("report_use_unit_name <>", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameGreaterThan(String value) {
            addCriterion("report_use_unit_name >", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("report_use_unit_name >=", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameLessThan(String value) {
            addCriterion("report_use_unit_name <", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameLessThanOrEqualTo(String value) {
            addCriterion("report_use_unit_name <=", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameLike(String value) {
            addCriterion("report_use_unit_name like", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameNotLike(String value) {
            addCriterion("report_use_unit_name not like", value, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameIn(List<String> values) {
            addCriterion("report_use_unit_name in", values, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameNotIn(List<String> values) {
            addCriterion("report_use_unit_name not in", values, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameBetween(String value1, String value2) {
            addCriterion("report_use_unit_name between", value1, value2, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andReportUseUnitNameNotBetween(String value1, String value2) {
            addCriterion("report_use_unit_name not between", value1, value2, "reportUseUnitName");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberIsNull() {
            addCriterion("preaudit_number is null");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberIsNotNull() {
            addCriterion("preaudit_number is not null");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberEqualTo(String value) {
            addCriterion("preaudit_number =", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberNotEqualTo(String value) {
            addCriterion("preaudit_number <>", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberGreaterThan(String value) {
            addCriterion("preaudit_number >", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberGreaterThanOrEqualTo(String value) {
            addCriterion("preaudit_number >=", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberLessThan(String value) {
            addCriterion("preaudit_number <", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberLessThanOrEqualTo(String value) {
            addCriterion("preaudit_number <=", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberLike(String value) {
            addCriterion("preaudit_number like", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberNotLike(String value) {
            addCriterion("preaudit_number not like", value, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberIn(List<String> values) {
            addCriterion("preaudit_number in", values, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberNotIn(List<String> values) {
            addCriterion("preaudit_number not in", values, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberBetween(String value1, String value2) {
            addCriterion("preaudit_number between", value1, value2, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberNotBetween(String value1, String value2) {
            addCriterion("preaudit_number not between", value1, value2, "preauditNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberIsNull() {
            addCriterion("result_number is null");
            return (Criteria) this;
        }

        public Criteria andResultNumberIsNotNull() {
            addCriterion("result_number is not null");
            return (Criteria) this;
        }

        public Criteria andResultNumberEqualTo(String value) {
            addCriterion("result_number =", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberNotEqualTo(String value) {
            addCriterion("result_number <>", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberGreaterThan(String value) {
            addCriterion("result_number >", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberGreaterThanOrEqualTo(String value) {
            addCriterion("result_number >=", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberLessThan(String value) {
            addCriterion("result_number <", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberLessThanOrEqualTo(String value) {
            addCriterion("result_number <=", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberLike(String value) {
            addCriterion("result_number like", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberNotLike(String value) {
            addCriterion("result_number not like", value, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberIn(List<String> values) {
            addCriterion("result_number in", values, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberNotIn(List<String> values) {
            addCriterion("result_number not in", values, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberBetween(String value1, String value2) {
            addCriterion("result_number between", value1, value2, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andResultNumberNotBetween(String value1, String value2) {
            addCriterion("result_number not between", value1, value2, "resultNumber");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameIsNull() {
            addCriterion("project_manager_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameIsNotNull() {
            addCriterion("project_manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameEqualTo(String value) {
            addCriterion("project_manager_name =", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameNotEqualTo(String value) {
            addCriterion("project_manager_name <>", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameGreaterThan(String value) {
            addCriterion("project_manager_name >", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_manager_name >=", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameLessThan(String value) {
            addCriterion("project_manager_name <", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameLessThanOrEqualTo(String value) {
            addCriterion("project_manager_name <=", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameLike(String value) {
            addCriterion("project_manager_name like", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameNotLike(String value) {
            addCriterion("project_manager_name not like", value, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameIn(List<String> values) {
            addCriterion("project_manager_name in", values, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameNotIn(List<String> values) {
            addCriterion("project_manager_name not in", values, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameBetween(String value1, String value2) {
            addCriterion("project_manager_name between", value1, value2, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andProjectManagerNameNotBetween(String value1, String value2) {
            addCriterion("project_manager_name not between", value1, value2, "projectManagerName");
            return (Criteria) this;
        }

        public Criteria andContractPriceIsNull() {
            addCriterion("contract_price is null");
            return (Criteria) this;
        }

        public Criteria andContractPriceIsNotNull() {
            addCriterion("contract_price is not null");
            return (Criteria) this;
        }

        public Criteria andContractPriceEqualTo(BigDecimal value) {
            addCriterion("contract_price =", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotEqualTo(BigDecimal value) {
            addCriterion("contract_price <>", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceGreaterThan(BigDecimal value) {
            addCriterion("contract_price >", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_price >=", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceLessThan(BigDecimal value) {
            addCriterion("contract_price <", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_price <=", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceIn(List<BigDecimal> values) {
            addCriterion("contract_price in", values, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotIn(List<BigDecimal> values) {
            addCriterion("contract_price not in", values, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_price between", value1, value2, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_price not between", value1, value2, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(BigDecimal value) {
            addCriterion("actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(BigDecimal value) {
            addCriterion("actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(BigDecimal value) {
            addCriterion("actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(BigDecimal value) {
            addCriterion("actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<BigDecimal> values) {
            addCriterion("actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<BigDecimal> values) {
            addCriterion("actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_amount not between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountIsNull() {
            addCriterion("debt_amount is null");
            return (Criteria) this;
        }

        public Criteria andDebtAmountIsNotNull() {
            addCriterion("debt_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDebtAmountEqualTo(BigDecimal value) {
            addCriterion("debt_amount =", value, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountNotEqualTo(BigDecimal value) {
            addCriterion("debt_amount <>", value, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountGreaterThan(BigDecimal value) {
            addCriterion("debt_amount >", value, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("debt_amount >=", value, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountLessThan(BigDecimal value) {
            addCriterion("debt_amount <", value, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("debt_amount <=", value, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountIn(List<BigDecimal> values) {
            addCriterion("debt_amount in", values, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountNotIn(List<BigDecimal> values) {
            addCriterion("debt_amount not in", values, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("debt_amount between", value1, value2, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andDebtAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("debt_amount not between", value1, value2, "debtAmount");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtIsNull() {
            addCriterion("bis_has_debt is null");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtIsNotNull() {
            addCriterion("bis_has_debt is not null");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtEqualTo(Boolean value) {
            addCriterion("bis_has_debt =", value, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtNotEqualTo(Boolean value) {
            addCriterion("bis_has_debt <>", value, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtGreaterThan(Boolean value) {
            addCriterion("bis_has_debt >", value, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_has_debt >=", value, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtLessThan(Boolean value) {
            addCriterion("bis_has_debt <", value, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_has_debt <=", value, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtIn(List<Boolean> values) {
            addCriterion("bis_has_debt in", values, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtNotIn(List<Boolean> values) {
            addCriterion("bis_has_debt not in", values, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_has_debt between", value1, value2, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andBisHasDebtNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_has_debt not between", value1, value2, "bisHasDebt");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}