package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectPlanHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectPlanHistoryExample() {
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

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Integer value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Integer value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Integer value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Integer value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Integer> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Integer> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameIsNull() {
            addCriterion("project_phase_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameIsNotNull() {
            addCriterion("project_phase_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameEqualTo(String value) {
            addCriterion("project_phase_name =", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameNotEqualTo(String value) {
            addCriterion("project_phase_name <>", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameGreaterThan(String value) {
            addCriterion("project_phase_name >", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_phase_name >=", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameLessThan(String value) {
            addCriterion("project_phase_name <", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameLessThanOrEqualTo(String value) {
            addCriterion("project_phase_name <=", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameLike(String value) {
            addCriterion("project_phase_name like", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameNotLike(String value) {
            addCriterion("project_phase_name not like", value, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameIn(List<String> values) {
            addCriterion("project_phase_name in", values, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameNotIn(List<String> values) {
            addCriterion("project_phase_name not in", values, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameBetween(String value1, String value2) {
            addCriterion("project_phase_name between", value1, value2, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseNameNotBetween(String value1, String value2) {
            addCriterion("project_phase_name not between", value1, value2, "projectPhaseName");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartIsNull() {
            addCriterion("before_plan_start is null");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartIsNotNull() {
            addCriterion("before_plan_start is not null");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartEqualTo(Date value) {
            addCriterion("before_plan_start =", value, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartNotEqualTo(Date value) {
            addCriterion("before_plan_start <>", value, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartGreaterThan(Date value) {
            addCriterion("before_plan_start >", value, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartGreaterThanOrEqualTo(Date value) {
            addCriterion("before_plan_start >=", value, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartLessThan(Date value) {
            addCriterion("before_plan_start <", value, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartLessThanOrEqualTo(Date value) {
            addCriterion("before_plan_start <=", value, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartIn(List<Date> values) {
            addCriterion("before_plan_start in", values, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartNotIn(List<Date> values) {
            addCriterion("before_plan_start not in", values, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartBetween(Date value1, Date value2) {
            addCriterion("before_plan_start between", value1, value2, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanStartNotBetween(Date value1, Date value2) {
            addCriterion("before_plan_start not between", value1, value2, "beforePlanStart");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndIsNull() {
            addCriterion("before_plan_end is null");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndIsNotNull() {
            addCriterion("before_plan_end is not null");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndEqualTo(Date value) {
            addCriterion("before_plan_end =", value, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndNotEqualTo(Date value) {
            addCriterion("before_plan_end <>", value, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndGreaterThan(Date value) {
            addCriterion("before_plan_end >", value, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndGreaterThanOrEqualTo(Date value) {
            addCriterion("before_plan_end >=", value, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndLessThan(Date value) {
            addCriterion("before_plan_end <", value, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndLessThanOrEqualTo(Date value) {
            addCriterion("before_plan_end <=", value, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndIn(List<Date> values) {
            addCriterion("before_plan_end in", values, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndNotIn(List<Date> values) {
            addCriterion("before_plan_end not in", values, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndBetween(Date value1, Date value2) {
            addCriterion("before_plan_end between", value1, value2, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanEndNotBetween(Date value1, Date value2) {
            addCriterion("before_plan_end not between", value1, value2, "beforePlanEnd");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksIsNull() {
            addCriterion("before_plan_remarks is null");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksIsNotNull() {
            addCriterion("before_plan_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksEqualTo(String value) {
            addCriterion("before_plan_remarks =", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksNotEqualTo(String value) {
            addCriterion("before_plan_remarks <>", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksGreaterThan(String value) {
            addCriterion("before_plan_remarks >", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("before_plan_remarks >=", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksLessThan(String value) {
            addCriterion("before_plan_remarks <", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksLessThanOrEqualTo(String value) {
            addCriterion("before_plan_remarks <=", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksLike(String value) {
            addCriterion("before_plan_remarks like", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksNotLike(String value) {
            addCriterion("before_plan_remarks not like", value, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksIn(List<String> values) {
            addCriterion("before_plan_remarks in", values, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksNotIn(List<String> values) {
            addCriterion("before_plan_remarks not in", values, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksBetween(String value1, String value2) {
            addCriterion("before_plan_remarks between", value1, value2, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andBeforePlanRemarksNotBetween(String value1, String value2) {
            addCriterion("before_plan_remarks not between", value1, value2, "beforePlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartIsNull() {
            addCriterion("after_plan_start is null");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartIsNotNull() {
            addCriterion("after_plan_start is not null");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartEqualTo(Date value) {
            addCriterion("after_plan_start =", value, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartNotEqualTo(Date value) {
            addCriterion("after_plan_start <>", value, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartGreaterThan(Date value) {
            addCriterion("after_plan_start >", value, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartGreaterThanOrEqualTo(Date value) {
            addCriterion("after_plan_start >=", value, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartLessThan(Date value) {
            addCriterion("after_plan_start <", value, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartLessThanOrEqualTo(Date value) {
            addCriterion("after_plan_start <=", value, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartIn(List<Date> values) {
            addCriterion("after_plan_start in", values, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartNotIn(List<Date> values) {
            addCriterion("after_plan_start not in", values, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartBetween(Date value1, Date value2) {
            addCriterion("after_plan_start between", value1, value2, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanStartNotBetween(Date value1, Date value2) {
            addCriterion("after_plan_start not between", value1, value2, "afterPlanStart");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndIsNull() {
            addCriterion("after_plan_end is null");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndIsNotNull() {
            addCriterion("after_plan_end is not null");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndEqualTo(Date value) {
            addCriterion("after_plan_end =", value, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndNotEqualTo(Date value) {
            addCriterion("after_plan_end <>", value, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndGreaterThan(Date value) {
            addCriterion("after_plan_end >", value, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndGreaterThanOrEqualTo(Date value) {
            addCriterion("after_plan_end >=", value, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndLessThan(Date value) {
            addCriterion("after_plan_end <", value, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndLessThanOrEqualTo(Date value) {
            addCriterion("after_plan_end <=", value, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndIn(List<Date> values) {
            addCriterion("after_plan_end in", values, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndNotIn(List<Date> values) {
            addCriterion("after_plan_end not in", values, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndBetween(Date value1, Date value2) {
            addCriterion("after_plan_end between", value1, value2, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanEndNotBetween(Date value1, Date value2) {
            addCriterion("after_plan_end not between", value1, value2, "afterPlanEnd");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksIsNull() {
            addCriterion("after_plan_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksIsNotNull() {
            addCriterion("after_plan_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksEqualTo(String value) {
            addCriterion("after_plan_remarks =", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksNotEqualTo(String value) {
            addCriterion("after_plan_remarks <>", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksGreaterThan(String value) {
            addCriterion("after_plan_remarks >", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("after_plan_remarks >=", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksLessThan(String value) {
            addCriterion("after_plan_remarks <", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksLessThanOrEqualTo(String value) {
            addCriterion("after_plan_remarks <=", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksLike(String value) {
            addCriterion("after_plan_remarks like", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksNotLike(String value) {
            addCriterion("after_plan_remarks not like", value, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksIn(List<String> values) {
            addCriterion("after_plan_remarks in", values, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksNotIn(List<String> values) {
            addCriterion("after_plan_remarks not in", values, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksBetween(String value1, String value2) {
            addCriterion("after_plan_remarks between", value1, value2, "afterPlanRemarks");
            return (Criteria) this;
        }

        public Criteria andAfterPlanRemarksNotBetween(String value1, String value2) {
            addCriterion("after_plan_remarks not between", value1, value2, "afterPlanRemarks");
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