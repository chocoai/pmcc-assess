package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectWorkStageRestartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectWorkStageRestartExample() {
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

        public Criteria andProjectRestartStageIdIsNull() {
            addCriterion("project_restart_stage_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdIsNotNull() {
            addCriterion("project_restart_stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdEqualTo(Integer value) {
            addCriterion("project_restart_stage_id =", value, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdNotEqualTo(Integer value) {
            addCriterion("project_restart_stage_id <>", value, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdGreaterThan(Integer value) {
            addCriterion("project_restart_stage_id >", value, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_restart_stage_id >=", value, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdLessThan(Integer value) {
            addCriterion("project_restart_stage_id <", value, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_restart_stage_id <=", value, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdIn(List<Integer> values) {
            addCriterion("project_restart_stage_id in", values, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdNotIn(List<Integer> values) {
            addCriterion("project_restart_stage_id not in", values, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdBetween(Integer value1, Integer value2) {
            addCriterion("project_restart_stage_id between", value1, value2, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_restart_stage_id not between", value1, value2, "projectRestartStageId");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameIsNull() {
            addCriterion("project_restart_stage_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameIsNotNull() {
            addCriterion("project_restart_stage_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameEqualTo(String value) {
            addCriterion("project_restart_stage_name =", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameNotEqualTo(String value) {
            addCriterion("project_restart_stage_name <>", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameGreaterThan(String value) {
            addCriterion("project_restart_stage_name >", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_restart_stage_name >=", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameLessThan(String value) {
            addCriterion("project_restart_stage_name <", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameLessThanOrEqualTo(String value) {
            addCriterion("project_restart_stage_name <=", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameLike(String value) {
            addCriterion("project_restart_stage_name like", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameNotLike(String value) {
            addCriterion("project_restart_stage_name not like", value, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameIn(List<String> values) {
            addCriterion("project_restart_stage_name in", values, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameNotIn(List<String> values) {
            addCriterion("project_restart_stage_name not in", values, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameBetween(String value1, String value2) {
            addCriterion("project_restart_stage_name between", value1, value2, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectRestartStageNameNotBetween(String value1, String value2) {
            addCriterion("project_restart_stage_name not between", value1, value2, "projectRestartStageName");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdIsNull() {
            addCriterion("project_plan_old_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdIsNotNull() {
            addCriterion("project_plan_old_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdEqualTo(Integer value) {
            addCriterion("project_plan_old_id =", value, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdNotEqualTo(Integer value) {
            addCriterion("project_plan_old_id <>", value, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdGreaterThan(Integer value) {
            addCriterion("project_plan_old_id >", value, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_plan_old_id >=", value, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdLessThan(Integer value) {
            addCriterion("project_plan_old_id <", value, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_plan_old_id <=", value, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdIn(List<Integer> values) {
            addCriterion("project_plan_old_id in", values, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdNotIn(List<Integer> values) {
            addCriterion("project_plan_old_id not in", values, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdBetween(Integer value1, Integer value2) {
            addCriterion("project_plan_old_id between", value1, value2, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanOldIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_plan_old_id not between", value1, value2, "projectPlanOldId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdIsNull() {
            addCriterion("project_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdIsNotNull() {
            addCriterion("project_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdEqualTo(Integer value) {
            addCriterion("project_plan_id =", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdNotEqualTo(Integer value) {
            addCriterion("project_plan_id <>", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdGreaterThan(Integer value) {
            addCriterion("project_plan_id >", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_plan_id >=", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdLessThan(Integer value) {
            addCriterion("project_plan_id <", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_plan_id <=", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdIn(List<Integer> values) {
            addCriterion("project_plan_id in", values, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdNotIn(List<Integer> values) {
            addCriterion("project_plan_id not in", values, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("project_plan_id between", value1, value2, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_plan_id not between", value1, value2, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageIsNull() {
            addCriterion("project_this_work_stage is null");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageIsNotNull() {
            addCriterion("project_this_work_stage is not null");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageEqualTo(String value) {
            addCriterion("project_this_work_stage =", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageNotEqualTo(String value) {
            addCriterion("project_this_work_stage <>", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageGreaterThan(String value) {
            addCriterion("project_this_work_stage >", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageGreaterThanOrEqualTo(String value) {
            addCriterion("project_this_work_stage >=", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageLessThan(String value) {
            addCriterion("project_this_work_stage <", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageLessThanOrEqualTo(String value) {
            addCriterion("project_this_work_stage <=", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageLike(String value) {
            addCriterion("project_this_work_stage like", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageNotLike(String value) {
            addCriterion("project_this_work_stage not like", value, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageIn(List<String> values) {
            addCriterion("project_this_work_stage in", values, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageNotIn(List<String> values) {
            addCriterion("project_this_work_stage not in", values, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageBetween(String value1, String value2) {
            addCriterion("project_this_work_stage between", value1, value2, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andProjectThisWorkStageNotBetween(String value1, String value2) {
            addCriterion("project_this_work_stage not between", value1, value2, "projectThisWorkStage");
            return (Criteria) this;
        }

        public Criteria andRestartReasonIsNull() {
            addCriterion("restart_reason is null");
            return (Criteria) this;
        }

        public Criteria andRestartReasonIsNotNull() {
            addCriterion("restart_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRestartReasonEqualTo(String value) {
            addCriterion("restart_reason =", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonNotEqualTo(String value) {
            addCriterion("restart_reason <>", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonGreaterThan(String value) {
            addCriterion("restart_reason >", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonGreaterThanOrEqualTo(String value) {
            addCriterion("restart_reason >=", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonLessThan(String value) {
            addCriterion("restart_reason <", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonLessThanOrEqualTo(String value) {
            addCriterion("restart_reason <=", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonLike(String value) {
            addCriterion("restart_reason like", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonNotLike(String value) {
            addCriterion("restart_reason not like", value, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonIn(List<String> values) {
            addCriterion("restart_reason in", values, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonNotIn(List<String> values) {
            addCriterion("restart_reason not in", values, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonBetween(String value1, String value2) {
            addCriterion("restart_reason between", value1, value2, "restartReason");
            return (Criteria) this;
        }

        public Criteria andRestartReasonNotBetween(String value1, String value2) {
            addCriterion("restart_reason not between", value1, value2, "restartReason");
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