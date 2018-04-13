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

        public Criteria andProjectTypeIdIsNull() {
            addCriterion("project_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIsNotNull() {
            addCriterion("project_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdEqualTo(Integer value) {
            addCriterion("project_type_id =", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotEqualTo(Integer value) {
            addCriterion("project_type_id <>", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdGreaterThan(Integer value) {
            addCriterion("project_type_id >", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_type_id >=", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdLessThan(Integer value) {
            addCriterion("project_type_id <", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_type_id <=", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIn(List<Integer> values) {
            addCriterion("project_type_id in", values, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotIn(List<Integer> values) {
            addCriterion("project_type_id not in", values, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("project_type_id between", value1, value2, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_type_id not between", value1, value2, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIsNull() {
            addCriterion("project_category_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIsNotNull() {
            addCriterion("project_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdEqualTo(Integer value) {
            addCriterion("project_category_id =", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotEqualTo(Integer value) {
            addCriterion("project_category_id <>", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdGreaterThan(Integer value) {
            addCriterion("project_category_id >", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_category_id >=", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLessThan(Integer value) {
            addCriterion("project_category_id <", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_category_id <=", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIn(List<Integer> values) {
            addCriterion("project_category_id in", values, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotIn(List<Integer> values) {
            addCriterion("project_category_id not in", values, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("project_category_id between", value1, value2, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_category_id not between", value1, value2, "projectCategoryId");
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

        public Criteria andDepartmentNextIdsIsNull() {
            addCriterion("department_next_ids is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsIsNotNull() {
            addCriterion("department_next_ids is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsEqualTo(String value) {
            addCriterion("department_next_ids =", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsNotEqualTo(String value) {
            addCriterion("department_next_ids <>", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsGreaterThan(String value) {
            addCriterion("department_next_ids >", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsGreaterThanOrEqualTo(String value) {
            addCriterion("department_next_ids >=", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsLessThan(String value) {
            addCriterion("department_next_ids <", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsLessThanOrEqualTo(String value) {
            addCriterion("department_next_ids <=", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsLike(String value) {
            addCriterion("department_next_ids like", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsNotLike(String value) {
            addCriterion("department_next_ids not like", value, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsIn(List<String> values) {
            addCriterion("department_next_ids in", values, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsNotIn(List<String> values) {
            addCriterion("department_next_ids not in", values, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsBetween(String value1, String value2) {
            addCriterion("department_next_ids between", value1, value2, "departmentNextIds");
            return (Criteria) this;
        }

        public Criteria andDepartmentNextIdsNotBetween(String value1, String value2) {
            addCriterion("department_next_ids not between", value1, value2, "departmentNextIds");
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

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(Date value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Date value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Date value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Date value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Date value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Date> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Date> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Date value1, Date value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Date value1, Date value2) {
            addCriterion("modified not between", value1, value2, "modified");
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

        public Criteria andPrintShareIsNull() {
            addCriterion("print_share is null");
            return (Criteria) this;
        }

        public Criteria andPrintShareIsNotNull() {
            addCriterion("print_share is not null");
            return (Criteria) this;
        }

        public Criteria andPrintShareEqualTo(Integer value) {
            addCriterion("print_share =", value, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareNotEqualTo(Integer value) {
            addCriterion("print_share <>", value, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareGreaterThan(Integer value) {
            addCriterion("print_share >", value, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareGreaterThanOrEqualTo(Integer value) {
            addCriterion("print_share >=", value, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareLessThan(Integer value) {
            addCriterion("print_share <", value, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareLessThanOrEqualTo(Integer value) {
            addCriterion("print_share <=", value, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareIn(List<Integer> values) {
            addCriterion("print_share in", values, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareNotIn(List<Integer> values) {
            addCriterion("print_share not in", values, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareBetween(Integer value1, Integer value2) {
            addCriterion("print_share between", value1, value2, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintShareNotBetween(Integer value1, Integer value2) {
            addCriterion("print_share not between", value1, value2, "printShare");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberIsNull() {
            addCriterion("print_page_number is null");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberIsNotNull() {
            addCriterion("print_page_number is not null");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberEqualTo(Integer value) {
            addCriterion("print_page_number =", value, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberNotEqualTo(Integer value) {
            addCriterion("print_page_number <>", value, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberGreaterThan(Integer value) {
            addCriterion("print_page_number >", value, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("print_page_number >=", value, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberLessThan(Integer value) {
            addCriterion("print_page_number <", value, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberLessThanOrEqualTo(Integer value) {
            addCriterion("print_page_number <=", value, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberIn(List<Integer> values) {
            addCriterion("print_page_number in", values, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberNotIn(List<Integer> values) {
            addCriterion("print_page_number not in", values, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberBetween(Integer value1, Integer value2) {
            addCriterion("print_page_number between", value1, value2, "printPageNumber");
            return (Criteria) this;
        }

        public Criteria andPrintPageNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("print_page_number not between", value1, value2, "printPageNumber");
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