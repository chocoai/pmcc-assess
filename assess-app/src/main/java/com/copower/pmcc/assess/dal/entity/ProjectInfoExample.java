package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectInfoExample() {
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

        public Criteria andUrgencyIsNull() {
            addCriterion("urgency is null");
            return (Criteria) this;
        }

        public Criteria andUrgencyIsNotNull() {
            addCriterion("urgency is not null");
            return (Criteria) this;
        }

        public Criteria andUrgencyEqualTo(Integer value) {
            addCriterion("urgency =", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotEqualTo(Integer value) {
            addCriterion("urgency <>", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyGreaterThan(Integer value) {
            addCriterion("urgency >", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyGreaterThanOrEqualTo(Integer value) {
            addCriterion("urgency >=", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLessThan(Integer value) {
            addCriterion("urgency <", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLessThanOrEqualTo(Integer value) {
            addCriterion("urgency <=", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyIn(List<Integer> values) {
            addCriterion("urgency in", values, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotIn(List<Integer> values) {
            addCriterion("urgency not in", values, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyBetween(Integer value1, Integer value2) {
            addCriterion("urgency between", value1, value2, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotBetween(Integer value1, Integer value2) {
            addCriterion("urgency not between", value1, value2, "urgency");
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(Integer value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Integer value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Integer value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Integer value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Integer value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Integer value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Integer> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Integer> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Integer value1, Integer value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Integer value1, Integer value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Integer value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Integer value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Integer value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Integer value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Integer value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Integer> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Integer> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Integer value1, Integer value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Integer value1, Integer value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(Integer value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(Integer value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(Integer value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(Integer value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(Integer value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(Integer value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<Integer> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<Integer> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(Integer value1, Integer value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(Integer value1, Integer value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdIsNull() {
            addCriterion("project_class_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdIsNotNull() {
            addCriterion("project_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdEqualTo(Integer value) {
            addCriterion("project_class_id =", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdNotEqualTo(Integer value) {
            addCriterion("project_class_id <>", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdGreaterThan(Integer value) {
            addCriterion("project_class_id >", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_class_id >=", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdLessThan(Integer value) {
            addCriterion("project_class_id <", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_class_id <=", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdIn(List<Integer> values) {
            addCriterion("project_class_id in", values, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdNotIn(List<Integer> values) {
            addCriterion("project_class_id not in", values, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdBetween(Integer value1, Integer value2) {
            addCriterion("project_class_id between", value1, value2, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_class_id not between", value1, value2, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIsNull() {
            addCriterion("entrust_purpose is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIsNotNull() {
            addCriterion("entrust_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeEqualTo(Integer value) {
            addCriterion("entrust_purpose =", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotEqualTo(Integer value) {
            addCriterion("entrust_purpose <>", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeGreaterThan(Integer value) {
            addCriterion("entrust_purpose >", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeGreaterThanOrEqualTo(Integer value) {
            addCriterion("entrust_purpose >=", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLessThan(Integer value) {
            addCriterion("entrust_purpose <", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLessThanOrEqualTo(Integer value) {
            addCriterion("entrust_purpose <=", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIn(List<Integer> values) {
            addCriterion("entrust_purpose in", values, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotIn(List<Integer> values) {
            addCriterion("entrust_purpose not in", values, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeBetween(Integer value1, Integer value2) {
            addCriterion("entrust_purpose between", value1, value2, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotBetween(Integer value1, Integer value2) {
            addCriterion("entrust_purpose not between", value1, value2, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(Integer value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(Integer value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(Integer value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(Integer value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(Integer value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<Integer> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<Integer> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(Integer value1, Integer value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanIsNull() {
            addCriterion("complete_date_plan is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanIsNotNull() {
            addCriterion("complete_date_plan is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanEqualTo(Date value) {
            addCriterion("complete_date_plan =", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanNotEqualTo(Date value) {
            addCriterion("complete_date_plan <>", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanGreaterThan(Date value) {
            addCriterion("complete_date_plan >", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date_plan >=", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanLessThan(Date value) {
            addCriterion("complete_date_plan <", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanLessThanOrEqualTo(Date value) {
            addCriterion("complete_date_plan <=", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanIn(List<Date> values) {
            addCriterion("complete_date_plan in", values, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanNotIn(List<Date> values) {
            addCriterion("complete_date_plan not in", values, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanBetween(Date value1, Date value2) {
            addCriterion("complete_date_plan between", value1, value2, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanNotBetween(Date value1, Date value2) {
            addCriterion("complete_date_plan not between", value1, value2, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualIsNull() {
            addCriterion("complete_date_actual is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualIsNotNull() {
            addCriterion("complete_date_actual is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualEqualTo(Date value) {
            addCriterion("complete_date_actual =", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualNotEqualTo(Date value) {
            addCriterion("complete_date_actual <>", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualGreaterThan(Date value) {
            addCriterion("complete_date_actual >", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date_actual >=", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualLessThan(Date value) {
            addCriterion("complete_date_actual <", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualLessThanOrEqualTo(Date value) {
            addCriterion("complete_date_actual <=", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualIn(List<Date> values) {
            addCriterion("complete_date_actual in", values, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualNotIn(List<Date> values) {
            addCriterion("complete_date_actual not in", values, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualBetween(Date value1, Date value2) {
            addCriterion("complete_date_actual between", value1, value2, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualNotBetween(Date value1, Date value2) {
            addCriterion("complete_date_actual not between", value1, value2, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartIsNull() {
            addCriterion("complete_date_start is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartIsNotNull() {
            addCriterion("complete_date_start is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartEqualTo(Date value) {
            addCriterion("complete_date_start =", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartNotEqualTo(Date value) {
            addCriterion("complete_date_start <>", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartGreaterThan(Date value) {
            addCriterion("complete_date_start >", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date_start >=", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartLessThan(Date value) {
            addCriterion("complete_date_start <", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartLessThanOrEqualTo(Date value) {
            addCriterion("complete_date_start <=", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartIn(List<Date> values) {
            addCriterion("complete_date_start in", values, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartNotIn(List<Date> values) {
            addCriterion("complete_date_start not in", values, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartBetween(Date value1, Date value2) {
            addCriterion("complete_date_start between", value1, value2, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartNotBetween(Date value1, Date value2) {
            addCriterion("complete_date_start not between", value1, value2, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIsNull() {
            addCriterion("project_status is null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIsNotNull() {
            addCriterion("project_status is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusEqualTo(String value) {
            addCriterion("project_status =", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotEqualTo(String value) {
            addCriterion("project_status <>", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThan(String value) {
            addCriterion("project_status >", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("project_status >=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThan(String value) {
            addCriterion("project_status <", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThanOrEqualTo(String value) {
            addCriterion("project_status <=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLike(String value) {
            addCriterion("project_status like", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotLike(String value) {
            addCriterion("project_status not like", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIn(List<String> values) {
            addCriterion("project_status in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotIn(List<String> values) {
            addCriterion("project_status not in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusBetween(String value1, String value2) {
            addCriterion("project_status between", value1, value2, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotBetween(String value1, String value2) {
            addCriterion("project_status not between", value1, value2, "projectStatus");
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

        public Criteria andAttachmentProjectInfoIdIsNull() {
            addCriterion("attachment_project_info_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdIsNotNull() {
            addCriterion("attachment_project_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdEqualTo(String value) {
            addCriterion("attachment_project_info_id =", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdNotEqualTo(String value) {
            addCriterion("attachment_project_info_id <>", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdGreaterThan(String value) {
            addCriterion("attachment_project_info_id >", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("attachment_project_info_id >=", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdLessThan(String value) {
            addCriterion("attachment_project_info_id <", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdLessThanOrEqualTo(String value) {
            addCriterion("attachment_project_info_id <=", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdLike(String value) {
            addCriterion("attachment_project_info_id like", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdNotLike(String value) {
            addCriterion("attachment_project_info_id not like", value, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdIn(List<String> values) {
            addCriterion("attachment_project_info_id in", values, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdNotIn(List<String> values) {
            addCriterion("attachment_project_info_id not in", values, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdBetween(String value1, String value2) {
            addCriterion("attachment_project_info_id between", value1, value2, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andAttachmentProjectInfoIdNotBetween(String value1, String value2) {
            addCriterion("attachment_project_info_id not between", value1, value2, "attachmentProjectInfoId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdIsNull() {
            addCriterion("consignor_id is null");
            return (Criteria) this;
        }

        public Criteria andConsignorIdIsNotNull() {
            addCriterion("consignor_id is not null");
            return (Criteria) this;
        }

        public Criteria andConsignorIdEqualTo(Integer value) {
            addCriterion("consignor_id =", value, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdNotEqualTo(Integer value) {
            addCriterion("consignor_id <>", value, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdGreaterThan(Integer value) {
            addCriterion("consignor_id >", value, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("consignor_id >=", value, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdLessThan(Integer value) {
            addCriterion("consignor_id <", value, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdLessThanOrEqualTo(Integer value) {
            addCriterion("consignor_id <=", value, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdIn(List<Integer> values) {
            addCriterion("consignor_id in", values, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdNotIn(List<Integer> values) {
            addCriterion("consignor_id not in", values, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdBetween(Integer value1, Integer value2) {
            addCriterion("consignor_id between", value1, value2, "consignorId");
            return (Criteria) this;
        }

        public Criteria andConsignorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("consignor_id not between", value1, value2, "consignorId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdIsNull() {
            addCriterion("unit_information_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdIsNotNull() {
            addCriterion("unit_information_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdEqualTo(Integer value) {
            addCriterion("unit_information_id =", value, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdNotEqualTo(Integer value) {
            addCriterion("unit_information_id <>", value, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdGreaterThan(Integer value) {
            addCriterion("unit_information_id >", value, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_information_id >=", value, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdLessThan(Integer value) {
            addCriterion("unit_information_id <", value, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdLessThanOrEqualTo(Integer value) {
            addCriterion("unit_information_id <=", value, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdIn(List<Integer> values) {
            addCriterion("unit_information_id in", values, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdNotIn(List<Integer> values) {
            addCriterion("unit_information_id not in", values, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdBetween(Integer value1, Integer value2) {
            addCriterion("unit_information_id between", value1, value2, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andUnitInformationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_information_id not between", value1, value2, "unitInformationId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdIsNull() {
            addCriterion("possessor_id is null");
            return (Criteria) this;
        }

        public Criteria andPossessorIdIsNotNull() {
            addCriterion("possessor_id is not null");
            return (Criteria) this;
        }

        public Criteria andPossessorIdEqualTo(Integer value) {
            addCriterion("possessor_id =", value, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdNotEqualTo(Integer value) {
            addCriterion("possessor_id <>", value, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdGreaterThan(Integer value) {
            addCriterion("possessor_id >", value, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("possessor_id >=", value, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdLessThan(Integer value) {
            addCriterion("possessor_id <", value, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdLessThanOrEqualTo(Integer value) {
            addCriterion("possessor_id <=", value, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdIn(List<Integer> values) {
            addCriterion("possessor_id in", values, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdNotIn(List<Integer> values) {
            addCriterion("possessor_id not in", values, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdBetween(Integer value1, Integer value2) {
            addCriterion("possessor_id between", value1, value2, "possessorId");
            return (Criteria) this;
        }

        public Criteria andPossessorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("possessor_id not between", value1, value2, "possessorId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdIsNull() {
            addCriterion("project_member_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdIsNotNull() {
            addCriterion("project_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdEqualTo(Integer value) {
            addCriterion("project_member_id =", value, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdNotEqualTo(Integer value) {
            addCriterion("project_member_id <>", value, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdGreaterThan(Integer value) {
            addCriterion("project_member_id >", value, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_member_id >=", value, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdLessThan(Integer value) {
            addCriterion("project_member_id <", value, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_member_id <=", value, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdIn(List<Integer> values) {
            addCriterion("project_member_id in", values, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdNotIn(List<Integer> values) {
            addCriterion("project_member_id not in", values, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("project_member_id between", value1, value2, "projectMemberId");
            return (Criteria) this;
        }

        public Criteria andProjectMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_member_id not between", value1, value2, "projectMemberId");
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