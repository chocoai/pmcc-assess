package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectPlanExample() {
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

        public Criteria andWorkStageIdIsNull() {
            addCriterion("work_stage_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdIsNotNull() {
            addCriterion("work_stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdEqualTo(Integer value) {
            addCriterion("work_stage_id =", value, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdNotEqualTo(Integer value) {
            addCriterion("work_stage_id <>", value, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdGreaterThan(Integer value) {
            addCriterion("work_stage_id >", value, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_stage_id >=", value, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdLessThan(Integer value) {
            addCriterion("work_stage_id <", value, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdLessThanOrEqualTo(Integer value) {
            addCriterion("work_stage_id <=", value, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdIn(List<Integer> values) {
            addCriterion("work_stage_id in", values, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdNotIn(List<Integer> values) {
            addCriterion("work_stage_id not in", values, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdBetween(Integer value1, Integer value2) {
            addCriterion("work_stage_id between", value1, value2, "workStageId");
            return (Criteria) this;
        }

        public Criteria andWorkStageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("work_stage_id not between", value1, value2, "workStageId");
            return (Criteria) this;
        }

        public Criteria andStageSortIsNull() {
            addCriterion("stage_sort is null");
            return (Criteria) this;
        }

        public Criteria andStageSortIsNotNull() {
            addCriterion("stage_sort is not null");
            return (Criteria) this;
        }

        public Criteria andStageSortEqualTo(Integer value) {
            addCriterion("stage_sort =", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortNotEqualTo(Integer value) {
            addCriterion("stage_sort <>", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortGreaterThan(Integer value) {
            addCriterion("stage_sort >", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("stage_sort >=", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortLessThan(Integer value) {
            addCriterion("stage_sort <", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortLessThanOrEqualTo(Integer value) {
            addCriterion("stage_sort <=", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortIn(List<Integer> values) {
            addCriterion("stage_sort in", values, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortNotIn(List<Integer> values) {
            addCriterion("stage_sort not in", values, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortBetween(Integer value1, Integer value2) {
            addCriterion("stage_sort between", value1, value2, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortNotBetween(Integer value1, Integer value2) {
            addCriterion("stage_sort not between", value1, value2, "stageSort");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNull() {
            addCriterion("plan_name is null");
            return (Criteria) this;
        }

        public Criteria andPlanNameIsNotNull() {
            addCriterion("plan_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNameEqualTo(String value) {
            addCriterion("plan_name =", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotEqualTo(String value) {
            addCriterion("plan_name <>", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThan(String value) {
            addCriterion("plan_name >", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameGreaterThanOrEqualTo(String value) {
            addCriterion("plan_name >=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThan(String value) {
            addCriterion("plan_name <", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLessThanOrEqualTo(String value) {
            addCriterion("plan_name <=", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameLike(String value) {
            addCriterion("plan_name like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotLike(String value) {
            addCriterion("plan_name not like", value, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameIn(List<String> values) {
            addCriterion("plan_name in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotIn(List<String> values) {
            addCriterion("plan_name not in", values, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameBetween(String value1, String value2) {
            addCriterion("plan_name between", value1, value2, "planName");
            return (Criteria) this;
        }

        public Criteria andPlanNameNotBetween(String value1, String value2) {
            addCriterion("plan_name not between", value1, value2, "planName");
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

        public Criteria andProjectPlanStartIsNull() {
            addCriterion("project_plan_start is null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartIsNotNull() {
            addCriterion("project_plan_start is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartEqualTo(Date value) {
            addCriterion("project_plan_start =", value, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartNotEqualTo(Date value) {
            addCriterion("project_plan_start <>", value, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartGreaterThan(Date value) {
            addCriterion("project_plan_start >", value, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartGreaterThanOrEqualTo(Date value) {
            addCriterion("project_plan_start >=", value, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartLessThan(Date value) {
            addCriterion("project_plan_start <", value, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartLessThanOrEqualTo(Date value) {
            addCriterion("project_plan_start <=", value, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartIn(List<Date> values) {
            addCriterion("project_plan_start in", values, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartNotIn(List<Date> values) {
            addCriterion("project_plan_start not in", values, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartBetween(Date value1, Date value2) {
            addCriterion("project_plan_start between", value1, value2, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanStartNotBetween(Date value1, Date value2) {
            addCriterion("project_plan_start not between", value1, value2, "projectPlanStart");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndIsNull() {
            addCriterion("project_plan_end is null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndIsNotNull() {
            addCriterion("project_plan_end is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndEqualTo(Date value) {
            addCriterion("project_plan_end =", value, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndNotEqualTo(Date value) {
            addCriterion("project_plan_end <>", value, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndGreaterThan(Date value) {
            addCriterion("project_plan_end >", value, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndGreaterThanOrEqualTo(Date value) {
            addCriterion("project_plan_end >=", value, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndLessThan(Date value) {
            addCriterion("project_plan_end <", value, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndLessThanOrEqualTo(Date value) {
            addCriterion("project_plan_end <=", value, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndIn(List<Date> values) {
            addCriterion("project_plan_end in", values, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndNotIn(List<Date> values) {
            addCriterion("project_plan_end not in", values, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndBetween(Date value1, Date value2) {
            addCriterion("project_plan_end between", value1, value2, "projectPlanEnd");
            return (Criteria) this;
        }

        public Criteria andProjectPlanEndNotBetween(Date value1, Date value2) {
            addCriterion("project_plan_end not between", value1, value2, "projectPlanEnd");
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

        public Criteria andProcessInsIdApprovalIsNull() {
            addCriterion("process_ins_id_approval is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalIsNotNull() {
            addCriterion("process_ins_id_approval is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalEqualTo(String value) {
            addCriterion("process_ins_id_approval =", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalNotEqualTo(String value) {
            addCriterion("process_ins_id_approval <>", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalGreaterThan(String value) {
            addCriterion("process_ins_id_approval >", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id_approval >=", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalLessThan(String value) {
            addCriterion("process_ins_id_approval <", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id_approval <=", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalLike(String value) {
            addCriterion("process_ins_id_approval like", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalNotLike(String value) {
            addCriterion("process_ins_id_approval not like", value, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalIn(List<String> values) {
            addCriterion("process_ins_id_approval in", values, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalNotIn(List<String> values) {
            addCriterion("process_ins_id_approval not in", values, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalBetween(String value1, String value2) {
            addCriterion("process_ins_id_approval between", value1, value2, "processInsIdApproval");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdApprovalNotBetween(String value1, String value2) {
            addCriterion("process_ins_id_approval not between", value1, value2, "processInsIdApproval");
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

        public Criteria andApprovalStatusIsNull() {
            addCriterion("approval_status is null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNotNull() {
            addCriterion("approval_status is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusEqualTo(String value) {
            addCriterion("approval_status =", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotEqualTo(String value) {
            addCriterion("approval_status <>", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThan(String value) {
            addCriterion("approval_status >", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("approval_status >=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThan(String value) {
            addCriterion("approval_status <", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThanOrEqualTo(String value) {
            addCriterion("approval_status <=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLike(String value) {
            addCriterion("approval_status like", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotLike(String value) {
            addCriterion("approval_status not like", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIn(List<String> values) {
            addCriterion("approval_status in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotIn(List<String> values) {
            addCriterion("approval_status not in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusBetween(String value1, String value2) {
            addCriterion("approval_status between", value1, value2, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotBetween(String value1, String value2) {
            addCriterion("approval_status not between", value1, value2, "approvalStatus");
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

        public Criteria andFinishDateIsNull() {
            addCriterion("finish_date is null");
            return (Criteria) this;
        }

        public Criteria andFinishDateIsNotNull() {
            addCriterion("finish_date is not null");
            return (Criteria) this;
        }

        public Criteria andFinishDateEqualTo(Date value) {
            addCriterion("finish_date =", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateNotEqualTo(Date value) {
            addCriterion("finish_date <>", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateGreaterThan(Date value) {
            addCriterion("finish_date >", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_date >=", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateLessThan(Date value) {
            addCriterion("finish_date <", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateLessThanOrEqualTo(Date value) {
            addCriterion("finish_date <=", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateIn(List<Date> values) {
            addCriterion("finish_date in", values, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateNotIn(List<Date> values) {
            addCriterion("finish_date not in", values, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateBetween(Date value1, Date value2) {
            addCriterion("finish_date between", value1, value2, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateNotBetween(Date value1, Date value2) {
            addCriterion("finish_date not between", value1, value2, "finishDate");
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

        public Criteria andSpecificGravityIsNull() {
            addCriterion("specific_gravity is null");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityIsNotNull() {
            addCriterion("specific_gravity is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityEqualTo(Integer value) {
            addCriterion("specific_gravity =", value, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityNotEqualTo(Integer value) {
            addCriterion("specific_gravity <>", value, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityGreaterThan(Integer value) {
            addCriterion("specific_gravity >", value, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityGreaterThanOrEqualTo(Integer value) {
            addCriterion("specific_gravity >=", value, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityLessThan(Integer value) {
            addCriterion("specific_gravity <", value, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityLessThanOrEqualTo(Integer value) {
            addCriterion("specific_gravity <=", value, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityIn(List<Integer> values) {
            addCriterion("specific_gravity in", values, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityNotIn(List<Integer> values) {
            addCriterion("specific_gravity not in", values, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityBetween(Integer value1, Integer value2) {
            addCriterion("specific_gravity between", value1, value2, "specificGravity");
            return (Criteria) this;
        }

        public Criteria andSpecificGravityNotBetween(Integer value1, Integer value2) {
            addCriterion("specific_gravity not between", value1, value2, "specificGravity");
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