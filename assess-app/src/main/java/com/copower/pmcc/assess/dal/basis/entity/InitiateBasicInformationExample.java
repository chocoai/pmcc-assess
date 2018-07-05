package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitiateBasicInformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InitiateBasicInformationExample() {
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

        public Criteria andBProjectNameIsNull() {
            addCriterion("b_project_name is null");
            return (Criteria) this;
        }

        public Criteria andBProjectNameIsNotNull() {
            addCriterion("b_project_name is not null");
            return (Criteria) this;
        }

        public Criteria andBProjectNameEqualTo(String value) {
            addCriterion("b_project_name =", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameNotEqualTo(String value) {
            addCriterion("b_project_name <>", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameGreaterThan(String value) {
            addCriterion("b_project_name >", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("b_project_name >=", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameLessThan(String value) {
            addCriterion("b_project_name <", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameLessThanOrEqualTo(String value) {
            addCriterion("b_project_name <=", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameLike(String value) {
            addCriterion("b_project_name like", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameNotLike(String value) {
            addCriterion("b_project_name not like", value, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameIn(List<String> values) {
            addCriterion("b_project_name in", values, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameNotIn(List<String> values) {
            addCriterion("b_project_name not in", values, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameBetween(String value1, String value2) {
            addCriterion("b_project_name between", value1, value2, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectNameNotBetween(String value1, String value2) {
            addCriterion("b_project_name not between", value1, value2, "bProjectName");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdIsNull() {
            addCriterion("b_project_class_id is null");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdIsNotNull() {
            addCriterion("b_project_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdEqualTo(Integer value) {
            addCriterion("b_project_class_id =", value, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdNotEqualTo(Integer value) {
            addCriterion("b_project_class_id <>", value, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdGreaterThan(Integer value) {
            addCriterion("b_project_class_id >", value, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("b_project_class_id >=", value, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdLessThan(Integer value) {
            addCriterion("b_project_class_id <", value, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("b_project_class_id <=", value, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdIn(List<Integer> values) {
            addCriterion("b_project_class_id in", values, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdNotIn(List<Integer> values) {
            addCriterion("b_project_class_id not in", values, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdBetween(Integer value1, Integer value2) {
            addCriterion("b_project_class_id between", value1, value2, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBProjectClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("b_project_class_id not between", value1, value2, "bProjectClassId");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeIsNull() {
            addCriterion("b_entrust_purpose is null");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeIsNotNull() {
            addCriterion("b_entrust_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeEqualTo(String value) {
            addCriterion("b_entrust_purpose =", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeNotEqualTo(String value) {
            addCriterion("b_entrust_purpose <>", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeGreaterThan(String value) {
            addCriterion("b_entrust_purpose >", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("b_entrust_purpose >=", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeLessThan(String value) {
            addCriterion("b_entrust_purpose <", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeLessThanOrEqualTo(String value) {
            addCriterion("b_entrust_purpose <=", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeLike(String value) {
            addCriterion("b_entrust_purpose like", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeNotLike(String value) {
            addCriterion("b_entrust_purpose not like", value, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeIn(List<String> values) {
            addCriterion("b_entrust_purpose in", values, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeNotIn(List<String> values) {
            addCriterion("b_entrust_purpose not in", values, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeBetween(String value1, String value2) {
            addCriterion("b_entrust_purpose between", value1, value2, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEntrustPurposeNotBetween(String value1, String value2) {
            addCriterion("b_entrust_purpose not between", value1, value2, "bEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayIsNull() {
            addCriterion("b_evaluation_benchmark_day is null");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayIsNotNull() {
            addCriterion("b_evaluation_benchmark_day is not null");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayEqualTo(Date value) {
            addCriterion("b_evaluation_benchmark_day =", value, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayNotEqualTo(Date value) {
            addCriterion("b_evaluation_benchmark_day <>", value, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayGreaterThan(Date value) {
            addCriterion("b_evaluation_benchmark_day >", value, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayGreaterThanOrEqualTo(Date value) {
            addCriterion("b_evaluation_benchmark_day >=", value, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayLessThan(Date value) {
            addCriterion("b_evaluation_benchmark_day <", value, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayLessThanOrEqualTo(Date value) {
            addCriterion("b_evaluation_benchmark_day <=", value, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayIn(List<Date> values) {
            addCriterion("b_evaluation_benchmark_day in", values, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayNotIn(List<Date> values) {
            addCriterion("b_evaluation_benchmark_day not in", values, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayBetween(Date value1, Date value2) {
            addCriterion("b_evaluation_benchmark_day between", value1, value2, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBEvaluationBenchmarkDayNotBetween(Date value1, Date value2) {
            addCriterion("b_evaluation_benchmark_day not between", value1, value2, "bEvaluationBenchmarkDay");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdIsNull() {
            addCriterion("b_department_id is null");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdIsNotNull() {
            addCriterion("b_department_id is not null");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdEqualTo(Integer value) {
            addCriterion("b_department_id =", value, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdNotEqualTo(Integer value) {
            addCriterion("b_department_id <>", value, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdGreaterThan(Integer value) {
            addCriterion("b_department_id >", value, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("b_department_id >=", value, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdLessThan(Integer value) {
            addCriterion("b_department_id <", value, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("b_department_id <=", value, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdIn(List<Integer> values) {
            addCriterion("b_department_id in", values, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdNotIn(List<Integer> values) {
            addCriterion("b_department_id not in", values, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("b_department_id between", value1, value2, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("b_department_id not between", value1, value2, "bDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerIsNull() {
            addCriterion("b_project_manager is null");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerIsNotNull() {
            addCriterion("b_project_manager is not null");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerEqualTo(String value) {
            addCriterion("b_project_manager =", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerNotEqualTo(String value) {
            addCriterion("b_project_manager <>", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerGreaterThan(String value) {
            addCriterion("b_project_manager >", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerGreaterThanOrEqualTo(String value) {
            addCriterion("b_project_manager >=", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerLessThan(String value) {
            addCriterion("b_project_manager <", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerLessThanOrEqualTo(String value) {
            addCriterion("b_project_manager <=", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerLike(String value) {
            addCriterion("b_project_manager like", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerNotLike(String value) {
            addCriterion("b_project_manager not like", value, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerIn(List<String> values) {
            addCriterion("b_project_manager in", values, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerNotIn(List<String> values) {
            addCriterion("b_project_manager not in", values, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerBetween(String value1, String value2) {
            addCriterion("b_project_manager between", value1, value2, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBProjectManagerNotBetween(String value1, String value2) {
            addCriterion("b_project_manager not between", value1, value2, "bProjectManager");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationIsNull() {
            addCriterion("b_enclosure_location is null");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationIsNotNull() {
            addCriterion("b_enclosure_location is not null");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationEqualTo(String value) {
            addCriterion("b_enclosure_location =", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationNotEqualTo(String value) {
            addCriterion("b_enclosure_location <>", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationGreaterThan(String value) {
            addCriterion("b_enclosure_location >", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationGreaterThanOrEqualTo(String value) {
            addCriterion("b_enclosure_location >=", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationLessThan(String value) {
            addCriterion("b_enclosure_location <", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationLessThanOrEqualTo(String value) {
            addCriterion("b_enclosure_location <=", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationLike(String value) {
            addCriterion("b_enclosure_location like", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationNotLike(String value) {
            addCriterion("b_enclosure_location not like", value, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationIn(List<String> values) {
            addCriterion("b_enclosure_location in", values, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationNotIn(List<String> values) {
            addCriterion("b_enclosure_location not in", values, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationBetween(String value1, String value2) {
            addCriterion("b_enclosure_location between", value1, value2, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBEnclosureLocationNotBetween(String value1, String value2) {
            addCriterion("b_enclosure_location not between", value1, value2, "bEnclosureLocation");
            return (Criteria) this;
        }

        public Criteria andBValueTypeIsNull() {
            addCriterion("b_value_type is null");
            return (Criteria) this;
        }

        public Criteria andBValueTypeIsNotNull() {
            addCriterion("b_value_type is not null");
            return (Criteria) this;
        }

        public Criteria andBValueTypeEqualTo(Integer value) {
            addCriterion("b_value_type =", value, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeNotEqualTo(Integer value) {
            addCriterion("b_value_type <>", value, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeGreaterThan(Integer value) {
            addCriterion("b_value_type >", value, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("b_value_type >=", value, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeLessThan(Integer value) {
            addCriterion("b_value_type <", value, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeLessThanOrEqualTo(Integer value) {
            addCriterion("b_value_type <=", value, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeIn(List<Integer> values) {
            addCriterion("b_value_type in", values, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeNotIn(List<Integer> values) {
            addCriterion("b_value_type not in", values, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeBetween(Integer value1, Integer value2) {
            addCriterion("b_value_type between", value1, value2, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBValueTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("b_value_type not between", value1, value2, "bValueType");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentIsNull() {
            addCriterion("b_subordinate_assignment is null");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentIsNotNull() {
            addCriterion("b_subordinate_assignment is not null");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentEqualTo(String value) {
            addCriterion("b_subordinate_assignment =", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentNotEqualTo(String value) {
            addCriterion("b_subordinate_assignment <>", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentGreaterThan(String value) {
            addCriterion("b_subordinate_assignment >", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentGreaterThanOrEqualTo(String value) {
            addCriterion("b_subordinate_assignment >=", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentLessThan(String value) {
            addCriterion("b_subordinate_assignment <", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentLessThanOrEqualTo(String value) {
            addCriterion("b_subordinate_assignment <=", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentLike(String value) {
            addCriterion("b_subordinate_assignment like", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentNotLike(String value) {
            addCriterion("b_subordinate_assignment not like", value, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentIn(List<String> values) {
            addCriterion("b_subordinate_assignment in", values, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentNotIn(List<String> values) {
            addCriterion("b_subordinate_assignment not in", values, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentBetween(String value1, String value2) {
            addCriterion("b_subordinate_assignment between", value1, value2, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSubordinateAssignmentNotBetween(String value1, String value2) {
            addCriterion("b_subordinate_assignment not between", value1, value2, "bSubordinateAssignment");
            return (Criteria) this;
        }

        public Criteria andBSpareField1IsNull() {
            addCriterion("b_spare_field1 is null");
            return (Criteria) this;
        }

        public Criteria andBSpareField1IsNotNull() {
            addCriterion("b_spare_field1 is not null");
            return (Criteria) this;
        }

        public Criteria andBSpareField1EqualTo(String value) {
            addCriterion("b_spare_field1 =", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1NotEqualTo(String value) {
            addCriterion("b_spare_field1 <>", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1GreaterThan(String value) {
            addCriterion("b_spare_field1 >", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1GreaterThanOrEqualTo(String value) {
            addCriterion("b_spare_field1 >=", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1LessThan(String value) {
            addCriterion("b_spare_field1 <", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1LessThanOrEqualTo(String value) {
            addCriterion("b_spare_field1 <=", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1Like(String value) {
            addCriterion("b_spare_field1 like", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1NotLike(String value) {
            addCriterion("b_spare_field1 not like", value, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1In(List<String> values) {
            addCriterion("b_spare_field1 in", values, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1NotIn(List<String> values) {
            addCriterion("b_spare_field1 not in", values, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1Between(String value1, String value2) {
            addCriterion("b_spare_field1 between", value1, value2, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField1NotBetween(String value1, String value2) {
            addCriterion("b_spare_field1 not between", value1, value2, "bSpareField1");
            return (Criteria) this;
        }

        public Criteria andBSpareField2IsNull() {
            addCriterion("b_spare_field2 is null");
            return (Criteria) this;
        }

        public Criteria andBSpareField2IsNotNull() {
            addCriterion("b_spare_field2 is not null");
            return (Criteria) this;
        }

        public Criteria andBSpareField2EqualTo(String value) {
            addCriterion("b_spare_field2 =", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2NotEqualTo(String value) {
            addCriterion("b_spare_field2 <>", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2GreaterThan(String value) {
            addCriterion("b_spare_field2 >", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2GreaterThanOrEqualTo(String value) {
            addCriterion("b_spare_field2 >=", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2LessThan(String value) {
            addCriterion("b_spare_field2 <", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2LessThanOrEqualTo(String value) {
            addCriterion("b_spare_field2 <=", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2Like(String value) {
            addCriterion("b_spare_field2 like", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2NotLike(String value) {
            addCriterion("b_spare_field2 not like", value, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2In(List<String> values) {
            addCriterion("b_spare_field2 in", values, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2NotIn(List<String> values) {
            addCriterion("b_spare_field2 not in", values, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2Between(String value1, String value2) {
            addCriterion("b_spare_field2 between", value1, value2, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField2NotBetween(String value1, String value2) {
            addCriterion("b_spare_field2 not between", value1, value2, "bSpareField2");
            return (Criteria) this;
        }

        public Criteria andBSpareField3IsNull() {
            addCriterion("b_spare_field3 is null");
            return (Criteria) this;
        }

        public Criteria andBSpareField3IsNotNull() {
            addCriterion("b_spare_field3 is not null");
            return (Criteria) this;
        }

        public Criteria andBSpareField3EqualTo(String value) {
            addCriterion("b_spare_field3 =", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3NotEqualTo(String value) {
            addCriterion("b_spare_field3 <>", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3GreaterThan(String value) {
            addCriterion("b_spare_field3 >", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3GreaterThanOrEqualTo(String value) {
            addCriterion("b_spare_field3 >=", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3LessThan(String value) {
            addCriterion("b_spare_field3 <", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3LessThanOrEqualTo(String value) {
            addCriterion("b_spare_field3 <=", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3Like(String value) {
            addCriterion("b_spare_field3 like", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3NotLike(String value) {
            addCriterion("b_spare_field3 not like", value, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3In(List<String> values) {
            addCriterion("b_spare_field3 in", values, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3NotIn(List<String> values) {
            addCriterion("b_spare_field3 not in", values, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3Between(String value1, String value2) {
            addCriterion("b_spare_field3 between", value1, value2, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField3NotBetween(String value1, String value2) {
            addCriterion("b_spare_field3 not between", value1, value2, "bSpareField3");
            return (Criteria) this;
        }

        public Criteria andBSpareField4IsNull() {
            addCriterion("b_spare_field4 is null");
            return (Criteria) this;
        }

        public Criteria andBSpareField4IsNotNull() {
            addCriterion("b_spare_field4 is not null");
            return (Criteria) this;
        }

        public Criteria andBSpareField4EqualTo(String value) {
            addCriterion("b_spare_field4 =", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4NotEqualTo(String value) {
            addCriterion("b_spare_field4 <>", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4GreaterThan(String value) {
            addCriterion("b_spare_field4 >", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4GreaterThanOrEqualTo(String value) {
            addCriterion("b_spare_field4 >=", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4LessThan(String value) {
            addCriterion("b_spare_field4 <", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4LessThanOrEqualTo(String value) {
            addCriterion("b_spare_field4 <=", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4Like(String value) {
            addCriterion("b_spare_field4 like", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4NotLike(String value) {
            addCriterion("b_spare_field4 not like", value, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4In(List<String> values) {
            addCriterion("b_spare_field4 in", values, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4NotIn(List<String> values) {
            addCriterion("b_spare_field4 not in", values, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4Between(String value1, String value2) {
            addCriterion("b_spare_field4 between", value1, value2, "bSpareField4");
            return (Criteria) this;
        }

        public Criteria andBSpareField4NotBetween(String value1, String value2) {
            addCriterion("b_spare_field4 not between", value1, value2, "bSpareField4");
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