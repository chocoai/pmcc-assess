package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectWorkStageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectWorkStageExample() {
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

        public Criteria andWorkStageNameIsNull() {
            addCriterion("work_stage_name is null");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameIsNotNull() {
            addCriterion("work_stage_name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameEqualTo(String value) {
            addCriterion("work_stage_name =", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameNotEqualTo(String value) {
            addCriterion("work_stage_name <>", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameGreaterThan(String value) {
            addCriterion("work_stage_name >", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("work_stage_name >=", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameLessThan(String value) {
            addCriterion("work_stage_name <", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameLessThanOrEqualTo(String value) {
            addCriterion("work_stage_name <=", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameLike(String value) {
            addCriterion("work_stage_name like", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameNotLike(String value) {
            addCriterion("work_stage_name not like", value, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameIn(List<String> values) {
            addCriterion("work_stage_name in", values, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameNotIn(List<String> values) {
            addCriterion("work_stage_name not in", values, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameBetween(String value1, String value2) {
            addCriterion("work_stage_name between", value1, value2, "workStageName");
            return (Criteria) this;
        }

        public Criteria andWorkStageNameNotBetween(String value1, String value2) {
            addCriterion("work_stage_name not between", value1, value2, "workStageName");
            return (Criteria) this;
        }

        public Criteria andStageFormIsNull() {
            addCriterion("stage_form is null");
            return (Criteria) this;
        }

        public Criteria andStageFormIsNotNull() {
            addCriterion("stage_form is not null");
            return (Criteria) this;
        }

        public Criteria andStageFormEqualTo(String value) {
            addCriterion("stage_form =", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormNotEqualTo(String value) {
            addCriterion("stage_form <>", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormGreaterThan(String value) {
            addCriterion("stage_form >", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormGreaterThanOrEqualTo(String value) {
            addCriterion("stage_form >=", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormLessThan(String value) {
            addCriterion("stage_form <", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormLessThanOrEqualTo(String value) {
            addCriterion("stage_form <=", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormLike(String value) {
            addCriterion("stage_form like", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormNotLike(String value) {
            addCriterion("stage_form not like", value, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormIn(List<String> values) {
            addCriterion("stage_form in", values, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormNotIn(List<String> values) {
            addCriterion("stage_form not in", values, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormBetween(String value1, String value2) {
            addCriterion("stage_form between", value1, value2, "stageForm");
            return (Criteria) this;
        }

        public Criteria andStageFormNotBetween(String value1, String value2) {
            addCriterion("stage_form not between", value1, value2, "stageForm");
            return (Criteria) this;
        }

        public Criteria andBoxNameIsNull() {
            addCriterion("box_name is null");
            return (Criteria) this;
        }

        public Criteria andBoxNameIsNotNull() {
            addCriterion("box_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoxNameEqualTo(String value) {
            addCriterion("box_name =", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotEqualTo(String value) {
            addCriterion("box_name <>", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameGreaterThan(String value) {
            addCriterion("box_name >", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameGreaterThanOrEqualTo(String value) {
            addCriterion("box_name >=", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLessThan(String value) {
            addCriterion("box_name <", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLessThanOrEqualTo(String value) {
            addCriterion("box_name <=", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLike(String value) {
            addCriterion("box_name like", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotLike(String value) {
            addCriterion("box_name not like", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameIn(List<String> values) {
            addCriterion("box_name in", values, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotIn(List<String> values) {
            addCriterion("box_name not in", values, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameBetween(String value1, String value2) {
            addCriterion("box_name between", value1, value2, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotBetween(String value1, String value2) {
            addCriterion("box_name not between", value1, value2, "boxName");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreIsNull() {
            addCriterion("manager_review_score is null");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreIsNotNull() {
            addCriterion("manager_review_score is not null");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreEqualTo(BigDecimal value) {
            addCriterion("manager_review_score =", value, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreNotEqualTo(BigDecimal value) {
            addCriterion("manager_review_score <>", value, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreGreaterThan(BigDecimal value) {
            addCriterion("manager_review_score >", value, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("manager_review_score >=", value, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreLessThan(BigDecimal value) {
            addCriterion("manager_review_score <", value, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("manager_review_score <=", value, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreIn(List<BigDecimal> values) {
            addCriterion("manager_review_score in", values, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreNotIn(List<BigDecimal> values) {
            addCriterion("manager_review_score not in", values, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manager_review_score between", value1, value2, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manager_review_score not between", value1, value2, "managerReviewScore");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardIsNull() {
            addCriterion("manager_review_standard is null");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardIsNotNull() {
            addCriterion("manager_review_standard is not null");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardEqualTo(String value) {
            addCriterion("manager_review_standard =", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardNotEqualTo(String value) {
            addCriterion("manager_review_standard <>", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardGreaterThan(String value) {
            addCriterion("manager_review_standard >", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardGreaterThanOrEqualTo(String value) {
            addCriterion("manager_review_standard >=", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardLessThan(String value) {
            addCriterion("manager_review_standard <", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardLessThanOrEqualTo(String value) {
            addCriterion("manager_review_standard <=", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardLike(String value) {
            addCriterion("manager_review_standard like", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardNotLike(String value) {
            addCriterion("manager_review_standard not like", value, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardIn(List<String> values) {
            addCriterion("manager_review_standard in", values, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardNotIn(List<String> values) {
            addCriterion("manager_review_standard not in", values, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardBetween(String value1, String value2) {
            addCriterion("manager_review_standard between", value1, value2, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andManagerReviewStandardNotBetween(String value1, String value2) {
            addCriterion("manager_review_standard not between", value1, value2, "managerReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreIsNull() {
            addCriterion("ce_review_score is null");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreIsNotNull() {
            addCriterion("ce_review_score is not null");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreEqualTo(BigDecimal value) {
            addCriterion("ce_review_score =", value, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreNotEqualTo(BigDecimal value) {
            addCriterion("ce_review_score <>", value, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreGreaterThan(BigDecimal value) {
            addCriterion("ce_review_score >", value, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ce_review_score >=", value, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreLessThan(BigDecimal value) {
            addCriterion("ce_review_score <", value, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ce_review_score <=", value, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreIn(List<BigDecimal> values) {
            addCriterion("ce_review_score in", values, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreNotIn(List<BigDecimal> values) {
            addCriterion("ce_review_score not in", values, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ce_review_score between", value1, value2, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ce_review_score not between", value1, value2, "ceReviewScore");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardIsNull() {
            addCriterion("ce_review_standard is null");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardIsNotNull() {
            addCriterion("ce_review_standard is not null");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardEqualTo(String value) {
            addCriterion("ce_review_standard =", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardNotEqualTo(String value) {
            addCriterion("ce_review_standard <>", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardGreaterThan(String value) {
            addCriterion("ce_review_standard >", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardGreaterThanOrEqualTo(String value) {
            addCriterion("ce_review_standard >=", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardLessThan(String value) {
            addCriterion("ce_review_standard <", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardLessThanOrEqualTo(String value) {
            addCriterion("ce_review_standard <=", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardLike(String value) {
            addCriterion("ce_review_standard like", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardNotLike(String value) {
            addCriterion("ce_review_standard not like", value, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardIn(List<String> values) {
            addCriterion("ce_review_standard in", values, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardNotIn(List<String> values) {
            addCriterion("ce_review_standard not in", values, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardBetween(String value1, String value2) {
            addCriterion("ce_review_standard between", value1, value2, "ceReviewStandard");
            return (Criteria) this;
        }

        public Criteria andCeReviewStandardNotBetween(String value1, String value2) {
            addCriterion("ce_review_standard not between", value1, value2, "ceReviewStandard");
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

        public Criteria andBisLoadDefalutIsNull() {
            addCriterion("bis_load_defalut is null");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutIsNotNull() {
            addCriterion("bis_load_defalut is not null");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutEqualTo(Boolean value) {
            addCriterion("bis_load_defalut =", value, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutNotEqualTo(Boolean value) {
            addCriterion("bis_load_defalut <>", value, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutGreaterThan(Boolean value) {
            addCriterion("bis_load_defalut >", value, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_load_defalut >=", value, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutLessThan(Boolean value) {
            addCriterion("bis_load_defalut <", value, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_load_defalut <=", value, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutIn(List<Boolean> values) {
            addCriterion("bis_load_defalut in", values, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutNotIn(List<Boolean> values) {
            addCriterion("bis_load_defalut not in", values, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_load_defalut between", value1, value2, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBisLoadDefalutNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_load_defalut not between", value1, value2, "bisLoadDefalut");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeIsNull() {
            addCriterion("box_role_type is null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeIsNotNull() {
            addCriterion("box_role_type is not null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeEqualTo(String value) {
            addCriterion("box_role_type =", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeNotEqualTo(String value) {
            addCriterion("box_role_type <>", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeGreaterThan(String value) {
            addCriterion("box_role_type >", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("box_role_type >=", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeLessThan(String value) {
            addCriterion("box_role_type <", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeLessThanOrEqualTo(String value) {
            addCriterion("box_role_type <=", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeLike(String value) {
            addCriterion("box_role_type like", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeNotLike(String value) {
            addCriterion("box_role_type not like", value, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeIn(List<String> values) {
            addCriterion("box_role_type in", values, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeNotIn(List<String> values) {
            addCriterion("box_role_type not in", values, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeBetween(String value1, String value2) {
            addCriterion("box_role_type between", value1, value2, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleTypeNotBetween(String value1, String value2) {
            addCriterion("box_role_type not between", value1, value2, "boxRoleType");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdIsNull() {
            addCriterion("box_role_id is null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdIsNotNull() {
            addCriterion("box_role_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdEqualTo(Integer value) {
            addCriterion("box_role_id =", value, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdNotEqualTo(Integer value) {
            addCriterion("box_role_id <>", value, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdGreaterThan(Integer value) {
            addCriterion("box_role_id >", value, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_role_id >=", value, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdLessThan(Integer value) {
            addCriterion("box_role_id <", value, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("box_role_id <=", value, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdIn(List<Integer> values) {
            addCriterion("box_role_id in", values, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdNotIn(List<Integer> values) {
            addCriterion("box_role_id not in", values, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("box_role_id between", value1, value2, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("box_role_id not between", value1, value2, "boxRoleId");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyIsNull() {
            addCriterion("box_role_key is null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyIsNotNull() {
            addCriterion("box_role_key is not null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyEqualTo(String value) {
            addCriterion("box_role_key =", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyNotEqualTo(String value) {
            addCriterion("box_role_key <>", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyGreaterThan(String value) {
            addCriterion("box_role_key >", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyGreaterThanOrEqualTo(String value) {
            addCriterion("box_role_key >=", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyLessThan(String value) {
            addCriterion("box_role_key <", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyLessThanOrEqualTo(String value) {
            addCriterion("box_role_key <=", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyLike(String value) {
            addCriterion("box_role_key like", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyNotLike(String value) {
            addCriterion("box_role_key not like", value, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyIn(List<String> values) {
            addCriterion("box_role_key in", values, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyNotIn(List<String> values) {
            addCriterion("box_role_key not in", values, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyBetween(String value1, String value2) {
            addCriterion("box_role_key between", value1, value2, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleKeyNotBetween(String value1, String value2) {
            addCriterion("box_role_key not between", value1, value2, "boxRoleKey");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameIsNull() {
            addCriterion("box_role_name is null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameIsNotNull() {
            addCriterion("box_role_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameEqualTo(String value) {
            addCriterion("box_role_name =", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameNotEqualTo(String value) {
            addCriterion("box_role_name <>", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameGreaterThan(String value) {
            addCriterion("box_role_name >", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("box_role_name >=", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameLessThan(String value) {
            addCriterion("box_role_name <", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameLessThanOrEqualTo(String value) {
            addCriterion("box_role_name <=", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameLike(String value) {
            addCriterion("box_role_name like", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameNotLike(String value) {
            addCriterion("box_role_name not like", value, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameIn(List<String> values) {
            addCriterion("box_role_name in", values, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameNotIn(List<String> values) {
            addCriterion("box_role_name not in", values, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameBetween(String value1, String value2) {
            addCriterion("box_role_name between", value1, value2, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andBoxRoleNameNotBetween(String value1, String value2) {
            addCriterion("box_role_name not between", value1, value2, "boxRoleName");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedIsNull() {
            addCriterion("allow_issued is null");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedIsNotNull() {
            addCriterion("allow_issued is not null");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedEqualTo(Boolean value) {
            addCriterion("allow_issued =", value, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedNotEqualTo(Boolean value) {
            addCriterion("allow_issued <>", value, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedGreaterThan(Boolean value) {
            addCriterion("allow_issued >", value, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_issued >=", value, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedLessThan(Boolean value) {
            addCriterion("allow_issued <", value, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_issued <=", value, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedIn(List<Boolean> values) {
            addCriterion("allow_issued in", values, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedNotIn(List<Boolean> values) {
            addCriterion("allow_issued not in", values, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_issued between", value1, value2, "allowIssued");
            return (Criteria) this;
        }

        public Criteria andAllowIssuedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_issued not between", value1, value2, "allowIssued");
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

        public Criteria andReviewBoxNameIsNull() {
            addCriterion("review_box_name is null");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameIsNotNull() {
            addCriterion("review_box_name is not null");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameEqualTo(String value) {
            addCriterion("review_box_name =", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameNotEqualTo(String value) {
            addCriterion("review_box_name <>", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameGreaterThan(String value) {
            addCriterion("review_box_name >", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameGreaterThanOrEqualTo(String value) {
            addCriterion("review_box_name >=", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameLessThan(String value) {
            addCriterion("review_box_name <", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameLessThanOrEqualTo(String value) {
            addCriterion("review_box_name <=", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameLike(String value) {
            addCriterion("review_box_name like", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameNotLike(String value) {
            addCriterion("review_box_name not like", value, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameIn(List<String> values) {
            addCriterion("review_box_name in", values, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameNotIn(List<String> values) {
            addCriterion("review_box_name not in", values, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameBetween(String value1, String value2) {
            addCriterion("review_box_name between", value1, value2, "reviewBoxName");
            return (Criteria) this;
        }

        public Criteria andReviewBoxNameNotBetween(String value1, String value2) {
            addCriterion("review_box_name not between", value1, value2, "reviewBoxName");
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