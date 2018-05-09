package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyAssetOtherTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyAssetOtherTemplateExample() {
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

        public Criteria andPlanDetailIdIsNull() {
            addCriterion("plan_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdIsNotNull() {
            addCriterion("plan_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdEqualTo(Integer value) {
            addCriterion("plan_detail_id =", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdNotEqualTo(Integer value) {
            addCriterion("plan_detail_id <>", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdGreaterThan(Integer value) {
            addCriterion("plan_detail_id >", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_detail_id >=", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdLessThan(Integer value) {
            addCriterion("plan_detail_id <", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_detail_id <=", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdIn(List<Integer> values) {
            addCriterion("plan_detail_id in", values, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdNotIn(List<Integer> values) {
            addCriterion("plan_detail_id not in", values, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_detail_id between", value1, value2, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_detail_id not between", value1, value2, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarIsNull() {
            addCriterion("other_rights_registrar is null");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarIsNotNull() {
            addCriterion("other_rights_registrar is not null");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarEqualTo(Integer value) {
            addCriterion("other_rights_registrar =", value, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarNotEqualTo(Integer value) {
            addCriterion("other_rights_registrar <>", value, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarGreaterThan(Integer value) {
            addCriterion("other_rights_registrar >", value, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarGreaterThanOrEqualTo(Integer value) {
            addCriterion("other_rights_registrar >=", value, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarLessThan(Integer value) {
            addCriterion("other_rights_registrar <", value, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarLessThanOrEqualTo(Integer value) {
            addCriterion("other_rights_registrar <=", value, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarIn(List<Integer> values) {
            addCriterion("other_rights_registrar in", values, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarNotIn(List<Integer> values) {
            addCriterion("other_rights_registrar not in", values, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarBetween(Integer value1, Integer value2) {
            addCriterion("other_rights_registrar between", value1, value2, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andOtherRightsRegistrarNotBetween(Integer value1, Integer value2) {
            addCriterion("other_rights_registrar not between", value1, value2, "otherRightsRegistrar");
            return (Criteria) this;
        }

        public Criteria andRightHanderIsNull() {
            addCriterion("right_hander is null");
            return (Criteria) this;
        }

        public Criteria andRightHanderIsNotNull() {
            addCriterion("right_hander is not null");
            return (Criteria) this;
        }

        public Criteria andRightHanderEqualTo(Integer value) {
            addCriterion("right_hander =", value, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderNotEqualTo(Integer value) {
            addCriterion("right_hander <>", value, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderGreaterThan(Integer value) {
            addCriterion("right_hander >", value, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderGreaterThanOrEqualTo(Integer value) {
            addCriterion("right_hander >=", value, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderLessThan(Integer value) {
            addCriterion("right_hander <", value, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderLessThanOrEqualTo(Integer value) {
            addCriterion("right_hander <=", value, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderIn(List<Integer> values) {
            addCriterion("right_hander in", values, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderNotIn(List<Integer> values) {
            addCriterion("right_hander not in", values, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderBetween(Integer value1, Integer value2) {
            addCriterion("right_hander between", value1, value2, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRightHanderNotBetween(Integer value1, Integer value2) {
            addCriterion("right_hander not between", value1, value2, "rightHander");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaIsNull() {
            addCriterion("register_area is null");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaIsNotNull() {
            addCriterion("register_area is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaEqualTo(String value) {
            addCriterion("register_area =", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaNotEqualTo(String value) {
            addCriterion("register_area <>", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaGreaterThan(String value) {
            addCriterion("register_area >", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaGreaterThanOrEqualTo(String value) {
            addCriterion("register_area >=", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaLessThan(String value) {
            addCriterion("register_area <", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaLessThanOrEqualTo(String value) {
            addCriterion("register_area <=", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaLike(String value) {
            addCriterion("register_area like", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaNotLike(String value) {
            addCriterion("register_area not like", value, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaIn(List<String> values) {
            addCriterion("register_area in", values, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaNotIn(List<String> values) {
            addCriterion("register_area not in", values, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaBetween(String value1, String value2) {
            addCriterion("register_area between", value1, value2, "registerArea");
            return (Criteria) this;
        }

        public Criteria andRegisterAreaNotBetween(String value1, String value2) {
            addCriterion("register_area not between", value1, value2, "registerArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaIsNull() {
            addCriterion("actual_area is null");
            return (Criteria) this;
        }

        public Criteria andActualAreaIsNotNull() {
            addCriterion("actual_area is not null");
            return (Criteria) this;
        }

        public Criteria andActualAreaEqualTo(String value) {
            addCriterion("actual_area =", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaNotEqualTo(String value) {
            addCriterion("actual_area <>", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaGreaterThan(String value) {
            addCriterion("actual_area >", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaGreaterThanOrEqualTo(String value) {
            addCriterion("actual_area >=", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaLessThan(String value) {
            addCriterion("actual_area <", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaLessThanOrEqualTo(String value) {
            addCriterion("actual_area <=", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaLike(String value) {
            addCriterion("actual_area like", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaNotLike(String value) {
            addCriterion("actual_area not like", value, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaIn(List<String> values) {
            addCriterion("actual_area in", values, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaNotIn(List<String> values) {
            addCriterion("actual_area not in", values, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaBetween(String value1, String value2) {
            addCriterion("actual_area between", value1, value2, "actualArea");
            return (Criteria) this;
        }

        public Criteria andActualAreaNotBetween(String value1, String value2) {
            addCriterion("actual_area not between", value1, value2, "actualArea");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeIsNull() {
            addCriterion("register_purpose is null");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeIsNotNull() {
            addCriterion("register_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeEqualTo(String value) {
            addCriterion("register_purpose =", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeNotEqualTo(String value) {
            addCriterion("register_purpose <>", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeGreaterThan(String value) {
            addCriterion("register_purpose >", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("register_purpose >=", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeLessThan(String value) {
            addCriterion("register_purpose <", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeLessThanOrEqualTo(String value) {
            addCriterion("register_purpose <=", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeLike(String value) {
            addCriterion("register_purpose like", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeNotLike(String value) {
            addCriterion("register_purpose not like", value, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeIn(List<String> values) {
            addCriterion("register_purpose in", values, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeNotIn(List<String> values) {
            addCriterion("register_purpose not in", values, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeBetween(String value1, String value2) {
            addCriterion("register_purpose between", value1, value2, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterPurposeNotBetween(String value1, String value2) {
            addCriterion("register_purpose not between", value1, value2, "registerPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeIsNull() {
            addCriterion("actual_purpose is null");
            return (Criteria) this;
        }

        public Criteria andActualPurposeIsNotNull() {
            addCriterion("actual_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andActualPurposeEqualTo(String value) {
            addCriterion("actual_purpose =", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeNotEqualTo(String value) {
            addCriterion("actual_purpose <>", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeGreaterThan(String value) {
            addCriterion("actual_purpose >", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("actual_purpose >=", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeLessThan(String value) {
            addCriterion("actual_purpose <", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeLessThanOrEqualTo(String value) {
            addCriterion("actual_purpose <=", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeLike(String value) {
            addCriterion("actual_purpose like", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeNotLike(String value) {
            addCriterion("actual_purpose not like", value, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeIn(List<String> values) {
            addCriterion("actual_purpose in", values, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeNotIn(List<String> values) {
            addCriterion("actual_purpose not in", values, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeBetween(String value1, String value2) {
            addCriterion("actual_purpose between", value1, value2, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andActualPurposeNotBetween(String value1, String value2) {
            addCriterion("actual_purpose not between", value1, value2, "actualPurpose");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIsNull() {
            addCriterion("register_date is null");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIsNotNull() {
            addCriterion("register_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterDateEqualTo(Date value) {
            addCriterion("register_date =", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotEqualTo(Date value) {
            addCriterion("register_date <>", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateGreaterThan(Date value) {
            addCriterion("register_date >", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateGreaterThanOrEqualTo(Date value) {
            addCriterion("register_date >=", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateLessThan(Date value) {
            addCriterion("register_date <", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateLessThanOrEqualTo(Date value) {
            addCriterion("register_date <=", value, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateIn(List<Date> values) {
            addCriterion("register_date in", values, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotIn(List<Date> values) {
            addCriterion("register_date not in", values, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateBetween(Date value1, Date value2) {
            addCriterion("register_date between", value1, value2, "registerDate");
            return (Criteria) this;
        }

        public Criteria andRegisterDateNotBetween(Date value1, Date value2) {
            addCriterion("register_date not between", value1, value2, "registerDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNull() {
            addCriterion("due_date is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("due_date is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(Date value) {
            addCriterion("due_date =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(Date value) {
            addCriterion("due_date <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(Date value) {
            addCriterion("due_date >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(Date value) {
            addCriterion("due_date >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(Date value) {
            addCriterion("due_date <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(Date value) {
            addCriterion("due_date <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<Date> values) {
            addCriterion("due_date in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<Date> values) {
            addCriterion("due_date not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(Date value1, Date value2) {
            addCriterion("due_date between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(Date value1, Date value2) {
            addCriterion("due_date not between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateIsNull() {
            addCriterion("exercise_date is null");
            return (Criteria) this;
        }

        public Criteria andExerciseDateIsNotNull() {
            addCriterion("exercise_date is not null");
            return (Criteria) this;
        }

        public Criteria andExerciseDateEqualTo(Date value) {
            addCriterion("exercise_date =", value, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateNotEqualTo(Date value) {
            addCriterion("exercise_date <>", value, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateGreaterThan(Date value) {
            addCriterion("exercise_date >", value, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("exercise_date >=", value, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateLessThan(Date value) {
            addCriterion("exercise_date <", value, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateLessThanOrEqualTo(Date value) {
            addCriterion("exercise_date <=", value, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateIn(List<Date> values) {
            addCriterion("exercise_date in", values, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateNotIn(List<Date> values) {
            addCriterion("exercise_date not in", values, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateBetween(Date value1, Date value2) {
            addCriterion("exercise_date between", value1, value2, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andExerciseDateNotBetween(Date value1, Date value2) {
            addCriterion("exercise_date not between", value1, value2, "exerciseDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateIsNull() {
            addCriterion("predict_due_date is null");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateIsNotNull() {
            addCriterion("predict_due_date is not null");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateEqualTo(Date value) {
            addCriterion("predict_due_date =", value, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateNotEqualTo(Date value) {
            addCriterion("predict_due_date <>", value, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateGreaterThan(Date value) {
            addCriterion("predict_due_date >", value, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateGreaterThanOrEqualTo(Date value) {
            addCriterion("predict_due_date >=", value, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateLessThan(Date value) {
            addCriterion("predict_due_date <", value, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateLessThanOrEqualTo(Date value) {
            addCriterion("predict_due_date <=", value, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateIn(List<Date> values) {
            addCriterion("predict_due_date in", values, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateNotIn(List<Date> values) {
            addCriterion("predict_due_date not in", values, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateBetween(Date value1, Date value2) {
            addCriterion("predict_due_date between", value1, value2, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andPredictDueDateNotBetween(Date value1, Date value2) {
            addCriterion("predict_due_date not between", value1, value2, "predictDueDate");
            return (Criteria) this;
        }

        public Criteria andSpareFieldIsNull() {
            addCriterion("spare_field is null");
            return (Criteria) this;
        }

        public Criteria andSpareFieldIsNotNull() {
            addCriterion("spare_field is not null");
            return (Criteria) this;
        }

        public Criteria andSpareFieldEqualTo(String value) {
            addCriterion("spare_field =", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotEqualTo(String value) {
            addCriterion("spare_field <>", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldGreaterThan(String value) {
            addCriterion("spare_field >", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldGreaterThanOrEqualTo(String value) {
            addCriterion("spare_field >=", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldLessThan(String value) {
            addCriterion("spare_field <", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldLessThanOrEqualTo(String value) {
            addCriterion("spare_field <=", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldLike(String value) {
            addCriterion("spare_field like", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotLike(String value) {
            addCriterion("spare_field not like", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldIn(List<String> values) {
            addCriterion("spare_field in", values, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotIn(List<String> values) {
            addCriterion("spare_field not in", values, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldBetween(String value1, String value2) {
            addCriterion("spare_field between", value1, value2, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotBetween(String value1, String value2) {
            addCriterion("spare_field not between", value1, value2, "spareField");
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