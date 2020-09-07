package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectPlanDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectPlanDetailsExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andProjectWorkStageIdIsNull() {
            addCriterion("project_work_stage_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdIsNotNull() {
            addCriterion("project_work_stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdEqualTo(Integer value) {
            addCriterion("project_work_stage_id =", value, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdNotEqualTo(Integer value) {
            addCriterion("project_work_stage_id <>", value, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdGreaterThan(Integer value) {
            addCriterion("project_work_stage_id >", value, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_work_stage_id >=", value, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdLessThan(Integer value) {
            addCriterion("project_work_stage_id <", value, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_work_stage_id <=", value, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdIn(List<Integer> values) {
            addCriterion("project_work_stage_id in", values, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdNotIn(List<Integer> values) {
            addCriterion("project_work_stage_id not in", values, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdBetween(Integer value1, Integer value2) {
            addCriterion("project_work_stage_id between", value1, value2, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectWorkStageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_work_stage_id not between", value1, value2, "projectWorkStageId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdIsNull() {
            addCriterion("project_phase_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdIsNotNull() {
            addCriterion("project_phase_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdEqualTo(Integer value) {
            addCriterion("project_phase_id =", value, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdNotEqualTo(Integer value) {
            addCriterion("project_phase_id <>", value, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdGreaterThan(Integer value) {
            addCriterion("project_phase_id >", value, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_phase_id >=", value, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdLessThan(Integer value) {
            addCriterion("project_phase_id <", value, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_phase_id <=", value, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdIn(List<Integer> values) {
            addCriterion("project_phase_id in", values, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdNotIn(List<Integer> values) {
            addCriterion("project_phase_id not in", values, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdBetween(Integer value1, Integer value2) {
            addCriterion("project_phase_id between", value1, value2, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_phase_id not between", value1, value2, "projectPhaseId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdIsNull() {
            addCriterion("project_phase_details_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdIsNotNull() {
            addCriterion("project_phase_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdEqualTo(Integer value) {
            addCriterion("project_phase_details_id =", value, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdNotEqualTo(Integer value) {
            addCriterion("project_phase_details_id <>", value, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdGreaterThan(Integer value) {
            addCriterion("project_phase_details_id >", value, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_phase_details_id >=", value, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdLessThan(Integer value) {
            addCriterion("project_phase_details_id <", value, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_phase_details_id <=", value, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdIn(List<Integer> values) {
            addCriterion("project_phase_details_id in", values, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdNotIn(List<Integer> values) {
            addCriterion("project_phase_details_id not in", values, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("project_phase_details_id between", value1, value2, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andProjectPhaseDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_phase_details_id not between", value1, value2, "projectPhaseDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateIsNull() {
            addCriterion("plan_start_date is null");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateIsNotNull() {
            addCriterion("plan_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateEqualTo(Date value) {
            addCriterion("plan_start_date =", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateNotEqualTo(Date value) {
            addCriterion("plan_start_date <>", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateGreaterThan(Date value) {
            addCriterion("plan_start_date >", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_start_date >=", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateLessThan(Date value) {
            addCriterion("plan_start_date <", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateLessThanOrEqualTo(Date value) {
            addCriterion("plan_start_date <=", value, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateIn(List<Date> values) {
            addCriterion("plan_start_date in", values, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateNotIn(List<Date> values) {
            addCriterion("plan_start_date not in", values, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateBetween(Date value1, Date value2) {
            addCriterion("plan_start_date between", value1, value2, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanStartDateNotBetween(Date value1, Date value2) {
            addCriterion("plan_start_date not between", value1, value2, "planStartDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateIsNull() {
            addCriterion("plan_end_date is null");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateIsNotNull() {
            addCriterion("plan_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateEqualTo(Date value) {
            addCriterion("plan_end_date =", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateNotEqualTo(Date value) {
            addCriterion("plan_end_date <>", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateGreaterThan(Date value) {
            addCriterion("plan_end_date >", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_end_date >=", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateLessThan(Date value) {
            addCriterion("plan_end_date <", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateLessThanOrEqualTo(Date value) {
            addCriterion("plan_end_date <=", value, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateIn(List<Date> values) {
            addCriterion("plan_end_date in", values, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateNotIn(List<Date> values) {
            addCriterion("plan_end_date not in", values, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateBetween(Date value1, Date value2) {
            addCriterion("plan_end_date between", value1, value2, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanEndDateNotBetween(Date value1, Date value2) {
            addCriterion("plan_end_date not between", value1, value2, "planEndDate");
            return (Criteria) this;
        }

        public Criteria andPlanHoursIsNull() {
            addCriterion("plan_hours is null");
            return (Criteria) this;
        }

        public Criteria andPlanHoursIsNotNull() {
            addCriterion("plan_hours is not null");
            return (Criteria) this;
        }

        public Criteria andPlanHoursEqualTo(BigDecimal value) {
            addCriterion("plan_hours =", value, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursNotEqualTo(BigDecimal value) {
            addCriterion("plan_hours <>", value, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursGreaterThan(BigDecimal value) {
            addCriterion("plan_hours >", value, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_hours >=", value, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursLessThan(BigDecimal value) {
            addCriterion("plan_hours <", value, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_hours <=", value, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursIn(List<BigDecimal> values) {
            addCriterion("plan_hours in", values, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursNotIn(List<BigDecimal> values) {
            addCriterion("plan_hours not in", values, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_hours between", value1, value2, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanHoursNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_hours not between", value1, value2, "planHours");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksIsNull() {
            addCriterion("plan_remarks is null");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksIsNotNull() {
            addCriterion("plan_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksEqualTo(String value) {
            addCriterion("plan_remarks =", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksNotEqualTo(String value) {
            addCriterion("plan_remarks <>", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksGreaterThan(String value) {
            addCriterion("plan_remarks >", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("plan_remarks >=", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksLessThan(String value) {
            addCriterion("plan_remarks <", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksLessThanOrEqualTo(String value) {
            addCriterion("plan_remarks <=", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksLike(String value) {
            addCriterion("plan_remarks like", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksNotLike(String value) {
            addCriterion("plan_remarks not like", value, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksIn(List<String> values) {
            addCriterion("plan_remarks in", values, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksNotIn(List<String> values) {
            addCriterion("plan_remarks not in", values, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksBetween(String value1, String value2) {
            addCriterion("plan_remarks between", value1, value2, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andPlanRemarksNotBetween(String value1, String value2) {
            addCriterion("plan_remarks not between", value1, value2, "planRemarks");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountIsNull() {
            addCriterion("execute_user_account is null");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountIsNotNull() {
            addCriterion("execute_user_account is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountEqualTo(String value) {
            addCriterion("execute_user_account =", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountNotEqualTo(String value) {
            addCriterion("execute_user_account <>", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountGreaterThan(String value) {
            addCriterion("execute_user_account >", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("execute_user_account >=", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountLessThan(String value) {
            addCriterion("execute_user_account <", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountLessThanOrEqualTo(String value) {
            addCriterion("execute_user_account <=", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountLike(String value) {
            addCriterion("execute_user_account like", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountNotLike(String value) {
            addCriterion("execute_user_account not like", value, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountIn(List<String> values) {
            addCriterion("execute_user_account in", values, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountNotIn(List<String> values) {
            addCriterion("execute_user_account not in", values, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountBetween(String value1, String value2) {
            addCriterion("execute_user_account between", value1, value2, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteUserAccountNotBetween(String value1, String value2) {
            addCriterion("execute_user_account not between", value1, value2, "executeUserAccount");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdIsNull() {
            addCriterion("execute_department_id is null");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdIsNotNull() {
            addCriterion("execute_department_id is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdEqualTo(Integer value) {
            addCriterion("execute_department_id =", value, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdNotEqualTo(Integer value) {
            addCriterion("execute_department_id <>", value, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdGreaterThan(Integer value) {
            addCriterion("execute_department_id >", value, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("execute_department_id >=", value, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdLessThan(Integer value) {
            addCriterion("execute_department_id <", value, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("execute_department_id <=", value, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdIn(List<Integer> values) {
            addCriterion("execute_department_id in", values, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdNotIn(List<Integer> values) {
            addCriterion("execute_department_id not in", values, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("execute_department_id between", value1, value2, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andExecuteDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("execute_department_id not between", value1, value2, "executeDepartmentId");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNull() {
            addCriterion("bis_enable is null");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNotNull() {
            addCriterion("bis_enable is not null");
            return (Criteria) this;
        }

        public Criteria andBisEnableEqualTo(Boolean value) {
            addCriterion("bis_enable =", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotEqualTo(Boolean value) {
            addCriterion("bis_enable <>", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThan(Boolean value) {
            addCriterion("bis_enable >", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable >=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThan(Boolean value) {
            addCriterion("bis_enable <", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable <=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableIn(List<Boolean> values) {
            addCriterion("bis_enable in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotIn(List<Boolean> values) {
            addCriterion("bis_enable not in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable not between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andBisStartIsNull() {
            addCriterion("bis_start is null");
            return (Criteria) this;
        }

        public Criteria andBisStartIsNotNull() {
            addCriterion("bis_start is not null");
            return (Criteria) this;
        }

        public Criteria andBisStartEqualTo(Boolean value) {
            addCriterion("bis_start =", value, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartNotEqualTo(Boolean value) {
            addCriterion("bis_start <>", value, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartGreaterThan(Boolean value) {
            addCriterion("bis_start >", value, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_start >=", value, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartLessThan(Boolean value) {
            addCriterion("bis_start <", value, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_start <=", value, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartIn(List<Boolean> values) {
            addCriterion("bis_start in", values, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartNotIn(List<Boolean> values) {
            addCriterion("bis_start not in", values, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_start between", value1, value2, "bisStart");
            return (Criteria) this;
        }

        public Criteria andBisStartNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_start not between", value1, value2, "bisStart");
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

        public Criteria andTaskSubmitTimeIsNull() {
            addCriterion("task_submit_time is null");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeIsNotNull() {
            addCriterion("task_submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeEqualTo(Date value) {
            addCriterion("task_submit_time =", value, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeNotEqualTo(Date value) {
            addCriterion("task_submit_time <>", value, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeGreaterThan(Date value) {
            addCriterion("task_submit_time >", value, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("task_submit_time >=", value, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeLessThan(Date value) {
            addCriterion("task_submit_time <", value, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("task_submit_time <=", value, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeIn(List<Date> values) {
            addCriterion("task_submit_time in", values, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeNotIn(List<Date> values) {
            addCriterion("task_submit_time not in", values, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("task_submit_time between", value1, value2, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("task_submit_time not between", value1, value2, "taskSubmitTime");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksIsNull() {
            addCriterion("task_remarks is null");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksIsNotNull() {
            addCriterion("task_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksEqualTo(String value) {
            addCriterion("task_remarks =", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksNotEqualTo(String value) {
            addCriterion("task_remarks <>", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksGreaterThan(String value) {
            addCriterion("task_remarks >", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("task_remarks >=", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksLessThan(String value) {
            addCriterion("task_remarks <", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksLessThanOrEqualTo(String value) {
            addCriterion("task_remarks <=", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksLike(String value) {
            addCriterion("task_remarks like", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksNotLike(String value) {
            addCriterion("task_remarks not like", value, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksIn(List<String> values) {
            addCriterion("task_remarks in", values, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksNotIn(List<String> values) {
            addCriterion("task_remarks not in", values, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksBetween(String value1, String value2) {
            addCriterion("task_remarks between", value1, value2, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andTaskRemarksNotBetween(String value1, String value2) {
            addCriterion("task_remarks not between", value1, value2, "taskRemarks");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIsNull() {
            addCriterion("submit_user is null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIsNotNull() {
            addCriterion("submit_user is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserEqualTo(String value) {
            addCriterion("submit_user =", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserNotEqualTo(String value) {
            addCriterion("submit_user <>", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserGreaterThan(String value) {
            addCriterion("submit_user >", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserGreaterThanOrEqualTo(String value) {
            addCriterion("submit_user >=", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserLessThan(String value) {
            addCriterion("submit_user <", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserLessThanOrEqualTo(String value) {
            addCriterion("submit_user <=", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserLike(String value) {
            addCriterion("submit_user like", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserNotLike(String value) {
            addCriterion("submit_user not like", value, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIn(List<String> values) {
            addCriterion("submit_user in", values, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserNotIn(List<String> values) {
            addCriterion("submit_user not in", values, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserBetween(String value1, String value2) {
            addCriterion("submit_user between", value1, value2, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserNotBetween(String value1, String value2) {
            addCriterion("submit_user not between", value1, value2, "submitUser");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllIsNull() {
            addCriterion("submit_user_all is null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllIsNotNull() {
            addCriterion("submit_user_all is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllEqualTo(String value) {
            addCriterion("submit_user_all =", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllNotEqualTo(String value) {
            addCriterion("submit_user_all <>", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllGreaterThan(String value) {
            addCriterion("submit_user_all >", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllGreaterThanOrEqualTo(String value) {
            addCriterion("submit_user_all >=", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllLessThan(String value) {
            addCriterion("submit_user_all <", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllLessThanOrEqualTo(String value) {
            addCriterion("submit_user_all <=", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllLike(String value) {
            addCriterion("submit_user_all like", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllNotLike(String value) {
            addCriterion("submit_user_all not like", value, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllIn(List<String> values) {
            addCriterion("submit_user_all in", values, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllNotIn(List<String> values) {
            addCriterion("submit_user_all not in", values, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllBetween(String value1, String value2) {
            addCriterion("submit_user_all between", value1, value2, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andSubmitUserAllNotBetween(String value1, String value2) {
            addCriterion("submit_user_all not between", value1, value2, "submitUserAll");
            return (Criteria) this;
        }

        public Criteria andActualHoursIsNull() {
            addCriterion("actual_hours is null");
            return (Criteria) this;
        }

        public Criteria andActualHoursIsNotNull() {
            addCriterion("actual_hours is not null");
            return (Criteria) this;
        }

        public Criteria andActualHoursEqualTo(BigDecimal value) {
            addCriterion("actual_hours =", value, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursNotEqualTo(BigDecimal value) {
            addCriterion("actual_hours <>", value, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursGreaterThan(BigDecimal value) {
            addCriterion("actual_hours >", value, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_hours >=", value, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursLessThan(BigDecimal value) {
            addCriterion("actual_hours <", value, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_hours <=", value, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursIn(List<BigDecimal> values) {
            addCriterion("actual_hours in", values, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursNotIn(List<BigDecimal> values) {
            addCriterion("actual_hours not in", values, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_hours between", value1, value2, "actualHours");
            return (Criteria) this;
        }

        public Criteria andActualHoursNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_hours not between", value1, value2, "actualHours");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdIsNull() {
            addCriterion("judge_object_id is null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdIsNotNull() {
            addCriterion("judge_object_id is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdEqualTo(Integer value) {
            addCriterion("judge_object_id =", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdNotEqualTo(Integer value) {
            addCriterion("judge_object_id <>", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdGreaterThan(Integer value) {
            addCriterion("judge_object_id >", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("judge_object_id >=", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdLessThan(Integer value) {
            addCriterion("judge_object_id <", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("judge_object_id <=", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdIn(List<Integer> values) {
            addCriterion("judge_object_id in", values, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdNotIn(List<Integer> values) {
            addCriterion("judge_object_id not in", values, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdBetween(Integer value1, Integer value2) {
            addCriterion("judge_object_id between", value1, value2, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("judge_object_id not between", value1, value2, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIsNull() {
            addCriterion("declare_record_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIsNotNull() {
            addCriterion("declare_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdEqualTo(Integer value) {
            addCriterion("declare_record_id =", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotEqualTo(Integer value) {
            addCriterion("declare_record_id <>", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdGreaterThan(Integer value) {
            addCriterion("declare_record_id >", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("declare_record_id >=", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdLessThan(Integer value) {
            addCriterion("declare_record_id <", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("declare_record_id <=", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIn(List<Integer> values) {
            addCriterion("declare_record_id in", values, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotIn(List<Integer> values) {
            addCriterion("declare_record_id not in", values, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("declare_record_id between", value1, value2, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("declare_record_id not between", value1, value2, "declareRecordId");
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

        public Criteria andBisRestartIsNull() {
            addCriterion("bis_restart is null");
            return (Criteria) this;
        }

        public Criteria andBisRestartIsNotNull() {
            addCriterion("bis_restart is not null");
            return (Criteria) this;
        }

        public Criteria andBisRestartEqualTo(Boolean value) {
            addCriterion("bis_restart =", value, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartNotEqualTo(Boolean value) {
            addCriterion("bis_restart <>", value, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartGreaterThan(Boolean value) {
            addCriterion("bis_restart >", value, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_restart >=", value, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartLessThan(Boolean value) {
            addCriterion("bis_restart <", value, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_restart <=", value, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartIn(List<Boolean> values) {
            addCriterion("bis_restart in", values, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartNotIn(List<Boolean> values) {
            addCriterion("bis_restart not in", values, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_restart between", value1, value2, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisRestartNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_restart not between", value1, value2, "bisRestart");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerIsNull() {
            addCriterion("bis_last_layer is null");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerIsNotNull() {
            addCriterion("bis_last_layer is not null");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerEqualTo(Boolean value) {
            addCriterion("bis_last_layer =", value, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerNotEqualTo(Boolean value) {
            addCriterion("bis_last_layer <>", value, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerGreaterThan(Boolean value) {
            addCriterion("bis_last_layer >", value, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_last_layer >=", value, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerLessThan(Boolean value) {
            addCriterion("bis_last_layer <", value, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_last_layer <=", value, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerIn(List<Boolean> values) {
            addCriterion("bis_last_layer in", values, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerNotIn(List<Boolean> values) {
            addCriterion("bis_last_layer not in", values, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_last_layer between", value1, value2, "bisLastLayer");
            return (Criteria) this;
        }

        public Criteria andBisLastLayerNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_last_layer not between", value1, value2, "bisLastLayer");
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