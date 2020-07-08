package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectPhaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectPhaseExample() {
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

        public Criteria andAssetsSettingIdIsNull() {
            addCriterion("assets_setting_id is null");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdIsNotNull() {
            addCriterion("assets_setting_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdEqualTo(Integer value) {
            addCriterion("assets_setting_id =", value, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdNotEqualTo(Integer value) {
            addCriterion("assets_setting_id <>", value, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdGreaterThan(Integer value) {
            addCriterion("assets_setting_id >", value, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("assets_setting_id >=", value, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdLessThan(Integer value) {
            addCriterion("assets_setting_id <", value, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdLessThanOrEqualTo(Integer value) {
            addCriterion("assets_setting_id <=", value, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdIn(List<Integer> values) {
            addCriterion("assets_setting_id in", values, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdNotIn(List<Integer> values) {
            addCriterion("assets_setting_id not in", values, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdBetween(Integer value1, Integer value2) {
            addCriterion("assets_setting_id between", value1, value2, "assetsSettingId");
            return (Criteria) this;
        }

        public Criteria andAssetsSettingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("assets_setting_id not between", value1, value2, "assetsSettingId");
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

        public Criteria andPhaseTimeIsNull() {
            addCriterion("phase_time is null");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeIsNotNull() {
            addCriterion("phase_time is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeEqualTo(BigDecimal value) {
            addCriterion("phase_time =", value, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeNotEqualTo(BigDecimal value) {
            addCriterion("phase_time <>", value, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeGreaterThan(BigDecimal value) {
            addCriterion("phase_time >", value, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("phase_time >=", value, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeLessThan(BigDecimal value) {
            addCriterion("phase_time <", value, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("phase_time <=", value, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeIn(List<BigDecimal> values) {
            addCriterion("phase_time in", values, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeNotIn(List<BigDecimal> values) {
            addCriterion("phase_time not in", values, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("phase_time between", value1, value2, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("phase_time not between", value1, value2, "phaseTime");
            return (Criteria) this;
        }

        public Criteria andPhaseFormIsNull() {
            addCriterion("phase_form is null");
            return (Criteria) this;
        }

        public Criteria andPhaseFormIsNotNull() {
            addCriterion("phase_form is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseFormEqualTo(String value) {
            addCriterion("phase_form =", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormNotEqualTo(String value) {
            addCriterion("phase_form <>", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormGreaterThan(String value) {
            addCriterion("phase_form >", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormGreaterThanOrEqualTo(String value) {
            addCriterion("phase_form >=", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormLessThan(String value) {
            addCriterion("phase_form <", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormLessThanOrEqualTo(String value) {
            addCriterion("phase_form <=", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormLike(String value) {
            addCriterion("phase_form like", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormNotLike(String value) {
            addCriterion("phase_form not like", value, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormIn(List<String> values) {
            addCriterion("phase_form in", values, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormNotIn(List<String> values) {
            addCriterion("phase_form not in", values, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormBetween(String value1, String value2) {
            addCriterion("phase_form between", value1, value2, "phaseForm");
            return (Criteria) this;
        }

        public Criteria andPhaseFormNotBetween(String value1, String value2) {
            addCriterion("phase_form not between", value1, value2, "phaseForm");
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

        public Criteria andPhaseKeyIsNull() {
            addCriterion("phase_key is null");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyIsNotNull() {
            addCriterion("phase_key is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyEqualTo(String value) {
            addCriterion("phase_key =", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyNotEqualTo(String value) {
            addCriterion("phase_key <>", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyGreaterThan(String value) {
            addCriterion("phase_key >", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyGreaterThanOrEqualTo(String value) {
            addCriterion("phase_key >=", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyLessThan(String value) {
            addCriterion("phase_key <", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyLessThanOrEqualTo(String value) {
            addCriterion("phase_key <=", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyLike(String value) {
            addCriterion("phase_key like", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyNotLike(String value) {
            addCriterion("phase_key not like", value, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyIn(List<String> values) {
            addCriterion("phase_key in", values, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyNotIn(List<String> values) {
            addCriterion("phase_key not in", values, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyBetween(String value1, String value2) {
            addCriterion("phase_key between", value1, value2, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseKeyNotBetween(String value1, String value2) {
            addCriterion("phase_key not between", value1, value2, "phaseKey");
            return (Criteria) this;
        }

        public Criteria andPhaseSortIsNull() {
            addCriterion("phase_sort is null");
            return (Criteria) this;
        }

        public Criteria andPhaseSortIsNotNull() {
            addCriterion("phase_sort is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseSortEqualTo(Integer value) {
            addCriterion("phase_sort =", value, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortNotEqualTo(Integer value) {
            addCriterion("phase_sort <>", value, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortGreaterThan(Integer value) {
            addCriterion("phase_sort >", value, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("phase_sort >=", value, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortLessThan(Integer value) {
            addCriterion("phase_sort <", value, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortLessThanOrEqualTo(Integer value) {
            addCriterion("phase_sort <=", value, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortIn(List<Integer> values) {
            addCriterion("phase_sort in", values, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortNotIn(List<Integer> values) {
            addCriterion("phase_sort not in", values, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortBetween(Integer value1, Integer value2) {
            addCriterion("phase_sort between", value1, value2, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andPhaseSortNotBetween(Integer value1, Integer value2) {
            addCriterion("phase_sort not between", value1, value2, "phaseSort");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreIsNull() {
            addCriterion("assessment_max_score is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreIsNotNull() {
            addCriterion("assessment_max_score is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreEqualTo(BigDecimal value) {
            addCriterion("assessment_max_score =", value, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreNotEqualTo(BigDecimal value) {
            addCriterion("assessment_max_score <>", value, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreGreaterThan(BigDecimal value) {
            addCriterion("assessment_max_score >", value, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assessment_max_score >=", value, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreLessThan(BigDecimal value) {
            addCriterion("assessment_max_score <", value, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assessment_max_score <=", value, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreIn(List<BigDecimal> values) {
            addCriterion("assessment_max_score in", values, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreNotIn(List<BigDecimal> values) {
            addCriterion("assessment_max_score not in", values, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assessment_max_score between", value1, value2, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andAssessmentMaxScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assessment_max_score not between", value1, value2, "assessmentMaxScore");
            return (Criteria) this;
        }

        public Criteria andBisWaitIsNull() {
            addCriterion("bis_wait is null");
            return (Criteria) this;
        }

        public Criteria andBisWaitIsNotNull() {
            addCriterion("bis_wait is not null");
            return (Criteria) this;
        }

        public Criteria andBisWaitEqualTo(Boolean value) {
            addCriterion("bis_wait =", value, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitNotEqualTo(Boolean value) {
            addCriterion("bis_wait <>", value, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitGreaterThan(Boolean value) {
            addCriterion("bis_wait >", value, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_wait >=", value, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitLessThan(Boolean value) {
            addCriterion("bis_wait <", value, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_wait <=", value, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitIn(List<Boolean> values) {
            addCriterion("bis_wait in", values, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitNotIn(List<Boolean> values) {
            addCriterion("bis_wait not in", values, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_wait between", value1, value2, "bisWait");
            return (Criteria) this;
        }

        public Criteria andBisWaitNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_wait not between", value1, value2, "bisWait");
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

        public Criteria andBisUseBoxIsNull() {
            addCriterion("bis_use_box is null");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxIsNotNull() {
            addCriterion("bis_use_box is not null");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxEqualTo(Boolean value) {
            addCriterion("bis_use_box =", value, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxNotEqualTo(Boolean value) {
            addCriterion("bis_use_box <>", value, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxGreaterThan(Boolean value) {
            addCriterion("bis_use_box >", value, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_use_box >=", value, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxLessThan(Boolean value) {
            addCriterion("bis_use_box <", value, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_use_box <=", value, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxIn(List<Boolean> values) {
            addCriterion("bis_use_box in", values, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxNotIn(List<Boolean> values) {
            addCriterion("bis_use_box not in", values, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_use_box between", value1, value2, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisUseBoxNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_use_box not between", value1, value2, "bisUseBox");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnIsNull() {
            addCriterion("bis_can_return is null");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnIsNotNull() {
            addCriterion("bis_can_return is not null");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnEqualTo(Boolean value) {
            addCriterion("bis_can_return =", value, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnNotEqualTo(Boolean value) {
            addCriterion("bis_can_return <>", value, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnGreaterThan(Boolean value) {
            addCriterion("bis_can_return >", value, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_can_return >=", value, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnLessThan(Boolean value) {
            addCriterion("bis_can_return <", value, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_can_return <=", value, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnIn(List<Boolean> values) {
            addCriterion("bis_can_return in", values, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnNotIn(List<Boolean> values) {
            addCriterion("bis_can_return not in", values, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_can_return between", value1, value2, "bisCanReturn");
            return (Criteria) this;
        }

        public Criteria andBisCanReturnNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_can_return not between", value1, value2, "bisCanReturn");
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

        public Criteria andServiceBeanIsNull() {
            addCriterion("service_bean is null");
            return (Criteria) this;
        }

        public Criteria andServiceBeanIsNotNull() {
            addCriterion("service_bean is not null");
            return (Criteria) this;
        }

        public Criteria andServiceBeanEqualTo(String value) {
            addCriterion("service_bean =", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanNotEqualTo(String value) {
            addCriterion("service_bean <>", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanGreaterThan(String value) {
            addCriterion("service_bean >", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanGreaterThanOrEqualTo(String value) {
            addCriterion("service_bean >=", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanLessThan(String value) {
            addCriterion("service_bean <", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanLessThanOrEqualTo(String value) {
            addCriterion("service_bean <=", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanLike(String value) {
            addCriterion("service_bean like", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanNotLike(String value) {
            addCriterion("service_bean not like", value, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanIn(List<String> values) {
            addCriterion("service_bean in", values, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanNotIn(List<String> values) {
            addCriterion("service_bean not in", values, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanBetween(String value1, String value2) {
            addCriterion("service_bean between", value1, value2, "serviceBean");
            return (Criteria) this;
        }

        public Criteria andServiceBeanNotBetween(String value1, String value2) {
            addCriterion("service_bean not between", value1, value2, "serviceBean");
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