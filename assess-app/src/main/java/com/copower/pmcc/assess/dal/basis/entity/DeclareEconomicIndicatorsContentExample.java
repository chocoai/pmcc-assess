package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareEconomicIndicatorsContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareEconomicIndicatorsContentExample() {
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

        public Criteria andIndicatorsHeadIdIsNull() {
            addCriterion("indicators_head_id is null");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdIsNotNull() {
            addCriterion("indicators_head_id is not null");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdEqualTo(Integer value) {
            addCriterion("indicators_head_id =", value, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdNotEqualTo(Integer value) {
            addCriterion("indicators_head_id <>", value, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdGreaterThan(Integer value) {
            addCriterion("indicators_head_id >", value, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("indicators_head_id >=", value, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdLessThan(Integer value) {
            addCriterion("indicators_head_id <", value, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdLessThanOrEqualTo(Integer value) {
            addCriterion("indicators_head_id <=", value, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdIn(List<Integer> values) {
            addCriterion("indicators_head_id in", values, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdNotIn(List<Integer> values) {
            addCriterion("indicators_head_id not in", values, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdBetween(Integer value1, Integer value2) {
            addCriterion("indicators_head_id between", value1, value2, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andIndicatorsHeadIdNotBetween(Integer value1, Integer value2) {
            addCriterion("indicators_head_id not between", value1, value2, "indicatorsHeadId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNull() {
            addCriterion("plan_details_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNotNull() {
            addCriterion("plan_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdEqualTo(Integer value) {
            addCriterion("plan_details_id =", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotEqualTo(Integer value) {
            addCriterion("plan_details_id <>", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThan(Integer value) {
            addCriterion("plan_details_id >", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id >=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThan(Integer value) {
            addCriterion("plan_details_id <", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id <=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIn(List<Integer> values) {
            addCriterion("plan_details_id in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotIn(List<Integer> values) {
            addCriterion("plan_details_id not in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id not between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCustomKeyIsNull() {
            addCriterion("custom_key is null");
            return (Criteria) this;
        }

        public Criteria andCustomKeyIsNotNull() {
            addCriterion("custom_key is not null");
            return (Criteria) this;
        }

        public Criteria andCustomKeyEqualTo(String value) {
            addCriterion("custom_key =", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyNotEqualTo(String value) {
            addCriterion("custom_key <>", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyGreaterThan(String value) {
            addCriterion("custom_key >", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyGreaterThanOrEqualTo(String value) {
            addCriterion("custom_key >=", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyLessThan(String value) {
            addCriterion("custom_key <", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyLessThanOrEqualTo(String value) {
            addCriterion("custom_key <=", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyLike(String value) {
            addCriterion("custom_key like", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyNotLike(String value) {
            addCriterion("custom_key not like", value, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyIn(List<String> values) {
            addCriterion("custom_key in", values, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyNotIn(List<String> values) {
            addCriterion("custom_key not in", values, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyBetween(String value1, String value2) {
            addCriterion("custom_key between", value1, value2, "customKey");
            return (Criteria) this;
        }

        public Criteria andCustomKeyNotBetween(String value1, String value2) {
            addCriterion("custom_key not between", value1, value2, "customKey");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberIsNull() {
            addCriterion("salability_number is null");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberIsNotNull() {
            addCriterion("salability_number is not null");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberEqualTo(String value) {
            addCriterion("salability_number =", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberNotEqualTo(String value) {
            addCriterion("salability_number <>", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberGreaterThan(String value) {
            addCriterion("salability_number >", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberGreaterThanOrEqualTo(String value) {
            addCriterion("salability_number >=", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberLessThan(String value) {
            addCriterion("salability_number <", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberLessThanOrEqualTo(String value) {
            addCriterion("salability_number <=", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberLike(String value) {
            addCriterion("salability_number like", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberNotLike(String value) {
            addCriterion("salability_number not like", value, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberIn(List<String> values) {
            addCriterion("salability_number in", values, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberNotIn(List<String> values) {
            addCriterion("salability_number not in", values, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberBetween(String value1, String value2) {
            addCriterion("salability_number between", value1, value2, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andSalabilityNumberNotBetween(String value1, String value2) {
            addCriterion("salability_number not between", value1, value2, "salabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberIsNull() {
            addCriterion("assess_salability_number is null");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberIsNotNull() {
            addCriterion("assess_salability_number is not null");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberEqualTo(String value) {
            addCriterion("assess_salability_number =", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberNotEqualTo(String value) {
            addCriterion("assess_salability_number <>", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberGreaterThan(String value) {
            addCriterion("assess_salability_number >", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberGreaterThanOrEqualTo(String value) {
            addCriterion("assess_salability_number >=", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberLessThan(String value) {
            addCriterion("assess_salability_number <", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberLessThanOrEqualTo(String value) {
            addCriterion("assess_salability_number <=", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberLike(String value) {
            addCriterion("assess_salability_number like", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberNotLike(String value) {
            addCriterion("assess_salability_number not like", value, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberIn(List<String> values) {
            addCriterion("assess_salability_number in", values, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberNotIn(List<String> values) {
            addCriterion("assess_salability_number not in", values, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberBetween(String value1, String value2) {
            addCriterion("assess_salability_number between", value1, value2, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andAssessSalabilityNumberNotBetween(String value1, String value2) {
            addCriterion("assess_salability_number not between", value1, value2, "assessSalabilityNumber");
            return (Criteria) this;
        }

        public Criteria andPlanIndexIsNull() {
            addCriterion("plan_index is null");
            return (Criteria) this;
        }

        public Criteria andPlanIndexIsNotNull() {
            addCriterion("plan_index is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIndexEqualTo(String value) {
            addCriterion("plan_index =", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexNotEqualTo(String value) {
            addCriterion("plan_index <>", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexGreaterThan(String value) {
            addCriterion("plan_index >", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexGreaterThanOrEqualTo(String value) {
            addCriterion("plan_index >=", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexLessThan(String value) {
            addCriterion("plan_index <", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexLessThanOrEqualTo(String value) {
            addCriterion("plan_index <=", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexLike(String value) {
            addCriterion("plan_index like", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexNotLike(String value) {
            addCriterion("plan_index not like", value, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexIn(List<String> values) {
            addCriterion("plan_index in", values, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexNotIn(List<String> values) {
            addCriterion("plan_index not in", values, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexBetween(String value1, String value2) {
            addCriterion("plan_index between", value1, value2, "planIndex");
            return (Criteria) this;
        }

        public Criteria andPlanIndexNotBetween(String value1, String value2) {
            addCriterion("plan_index not between", value1, value2, "planIndex");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andChildDataIsNull() {
            addCriterion("child_data is null");
            return (Criteria) this;
        }

        public Criteria andChildDataIsNotNull() {
            addCriterion("child_data is not null");
            return (Criteria) this;
        }

        public Criteria andChildDataEqualTo(String value) {
            addCriterion("child_data =", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataNotEqualTo(String value) {
            addCriterion("child_data <>", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataGreaterThan(String value) {
            addCriterion("child_data >", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataGreaterThanOrEqualTo(String value) {
            addCriterion("child_data >=", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataLessThan(String value) {
            addCriterion("child_data <", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataLessThanOrEqualTo(String value) {
            addCriterion("child_data <=", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataLike(String value) {
            addCriterion("child_data like", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataNotLike(String value) {
            addCriterion("child_data not like", value, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataIn(List<String> values) {
            addCriterion("child_data in", values, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataNotIn(List<String> values) {
            addCriterion("child_data not in", values, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataBetween(String value1, String value2) {
            addCriterion("child_data between", value1, value2, "childData");
            return (Criteria) this;
        }

        public Criteria andChildDataNotBetween(String value1, String value2) {
            addCriterion("child_data not between", value1, value2, "childData");
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