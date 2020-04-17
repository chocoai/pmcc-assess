package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchemeLiquidationAnalysisItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchemeLiquidationAnalysisItemExample() {
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

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andTypeKeyIsNull() {
            addCriterion("type_key is null");
            return (Criteria) this;
        }

        public Criteria andTypeKeyIsNotNull() {
            addCriterion("type_key is not null");
            return (Criteria) this;
        }

        public Criteria andTypeKeyEqualTo(String value) {
            addCriterion("type_key =", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyNotEqualTo(String value) {
            addCriterion("type_key <>", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyGreaterThan(String value) {
            addCriterion("type_key >", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyGreaterThanOrEqualTo(String value) {
            addCriterion("type_key >=", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyLessThan(String value) {
            addCriterion("type_key <", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyLessThanOrEqualTo(String value) {
            addCriterion("type_key <=", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyLike(String value) {
            addCriterion("type_key like", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyNotLike(String value) {
            addCriterion("type_key not like", value, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyIn(List<String> values) {
            addCriterion("type_key in", values, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyNotIn(List<String> values) {
            addCriterion("type_key not in", values, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyBetween(String value1, String value2) {
            addCriterion("type_key between", value1, value2, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTypeKeyNotBetween(String value1, String value2) {
            addCriterion("type_key not between", value1, value2, "typeKey");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdIsNull() {
            addCriterion("tax_rate_id is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdIsNotNull() {
            addCriterion("tax_rate_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdEqualTo(Integer value) {
            addCriterion("tax_rate_id =", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdNotEqualTo(Integer value) {
            addCriterion("tax_rate_id <>", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdGreaterThan(Integer value) {
            addCriterion("tax_rate_id >", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tax_rate_id >=", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdLessThan(Integer value) {
            addCriterion("tax_rate_id <", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdLessThanOrEqualTo(Integer value) {
            addCriterion("tax_rate_id <=", value, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdIn(List<Integer> values) {
            addCriterion("tax_rate_id in", values, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdNotIn(List<Integer> values) {
            addCriterion("tax_rate_id not in", values, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdBetween(Integer value1, Integer value2) {
            addCriterion("tax_rate_id between", value1, value2, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tax_rate_id not between", value1, value2, "taxRateId");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIsNull() {
            addCriterion("tax_rate_value is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIsNotNull() {
            addCriterion("tax_rate_value is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueEqualTo(String value) {
            addCriterion("tax_rate_value =", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotEqualTo(String value) {
            addCriterion("tax_rate_value <>", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueGreaterThan(String value) {
            addCriterion("tax_rate_value >", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueGreaterThanOrEqualTo(String value) {
            addCriterion("tax_rate_value >=", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLessThan(String value) {
            addCriterion("tax_rate_value <", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLessThanOrEqualTo(String value) {
            addCriterion("tax_rate_value <=", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueLike(String value) {
            addCriterion("tax_rate_value like", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotLike(String value) {
            addCriterion("tax_rate_value not like", value, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueIn(List<String> values) {
            addCriterion("tax_rate_value in", values, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotIn(List<String> values) {
            addCriterion("tax_rate_value not in", values, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueBetween(String value1, String value2) {
            addCriterion("tax_rate_value between", value1, value2, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andTaxRateValueNotBetween(String value1, String value2) {
            addCriterion("tax_rate_value not between", value1, value2, "taxRateValue");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodIsNull() {
            addCriterion("calculation_method is null");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodIsNotNull() {
            addCriterion("calculation_method is not null");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodEqualTo(Integer value) {
            addCriterion("calculation_method =", value, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodNotEqualTo(Integer value) {
            addCriterion("calculation_method <>", value, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodGreaterThan(Integer value) {
            addCriterion("calculation_method >", value, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("calculation_method >=", value, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodLessThan(Integer value) {
            addCriterion("calculation_method <", value, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodLessThanOrEqualTo(Integer value) {
            addCriterion("calculation_method <=", value, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodIn(List<Integer> values) {
            addCriterion("calculation_method in", values, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodNotIn(List<Integer> values) {
            addCriterion("calculation_method not in", values, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodBetween(Integer value1, Integer value2) {
            addCriterion("calculation_method between", value1, value2, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andCalculationMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("calculation_method not between", value1, value2, "calculationMethod");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameIsNull() {
            addCriterion("tax_rate_name is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameIsNotNull() {
            addCriterion("tax_rate_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameEqualTo(String value) {
            addCriterion("tax_rate_name =", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotEqualTo(String value) {
            addCriterion("tax_rate_name <>", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameGreaterThan(String value) {
            addCriterion("tax_rate_name >", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameGreaterThanOrEqualTo(String value) {
            addCriterion("tax_rate_name >=", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameLessThan(String value) {
            addCriterion("tax_rate_name <", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameLessThanOrEqualTo(String value) {
            addCriterion("tax_rate_name <=", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameLike(String value) {
            addCriterion("tax_rate_name like", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotLike(String value) {
            addCriterion("tax_rate_name not like", value, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameIn(List<String> values) {
            addCriterion("tax_rate_name in", values, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotIn(List<String> values) {
            addCriterion("tax_rate_name not in", values, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameBetween(String value1, String value2) {
            addCriterion("tax_rate_name between", value1, value2, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andTaxRateNameNotBetween(String value1, String value2) {
            addCriterion("tax_rate_name not between", value1, value2, "taxRateName");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseIsNull() {
            addCriterion("calculate_base is null");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseIsNotNull() {
            addCriterion("calculate_base is not null");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseEqualTo(String value) {
            addCriterion("calculate_base =", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseNotEqualTo(String value) {
            addCriterion("calculate_base <>", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseGreaterThan(String value) {
            addCriterion("calculate_base >", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseGreaterThanOrEqualTo(String value) {
            addCriterion("calculate_base >=", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseLessThan(String value) {
            addCriterion("calculate_base <", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseLessThanOrEqualTo(String value) {
            addCriterion("calculate_base <=", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseLike(String value) {
            addCriterion("calculate_base like", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseNotLike(String value) {
            addCriterion("calculate_base not like", value, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseIn(List<String> values) {
            addCriterion("calculate_base in", values, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseNotIn(List<String> values) {
            addCriterion("calculate_base not in", values, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseBetween(String value1, String value2) {
            addCriterion("calculate_base between", value1, value2, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculateBaseNotBetween(String value1, String value2) {
            addCriterion("calculate_base not between", value1, value2, "calculateBase");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaIsNull() {
            addCriterion("calculation_formula is null");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaIsNotNull() {
            addCriterion("calculation_formula is not null");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaEqualTo(String value) {
            addCriterion("calculation_formula =", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaNotEqualTo(String value) {
            addCriterion("calculation_formula <>", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaGreaterThan(String value) {
            addCriterion("calculation_formula >", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("calculation_formula >=", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaLessThan(String value) {
            addCriterion("calculation_formula <", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaLessThanOrEqualTo(String value) {
            addCriterion("calculation_formula <=", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaLike(String value) {
            addCriterion("calculation_formula like", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaNotLike(String value) {
            addCriterion("calculation_formula not like", value, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaIn(List<String> values) {
            addCriterion("calculation_formula in", values, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaNotIn(List<String> values) {
            addCriterion("calculation_formula not in", values, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaBetween(String value1, String value2) {
            addCriterion("calculation_formula between", value1, value2, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andCalculationFormulaNotBetween(String value1, String value2) {
            addCriterion("calculation_formula not between", value1, value2, "calculationFormula");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenIsNull() {
            addCriterion("taxes_burden is null");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenIsNotNull() {
            addCriterion("taxes_burden is not null");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenEqualTo(String value) {
            addCriterion("taxes_burden =", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenNotEqualTo(String value) {
            addCriterion("taxes_burden <>", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenGreaterThan(String value) {
            addCriterion("taxes_burden >", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenGreaterThanOrEqualTo(String value) {
            addCriterion("taxes_burden >=", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenLessThan(String value) {
            addCriterion("taxes_burden <", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenLessThanOrEqualTo(String value) {
            addCriterion("taxes_burden <=", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenLike(String value) {
            addCriterion("taxes_burden like", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenNotLike(String value) {
            addCriterion("taxes_burden not like", value, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenIn(List<String> values) {
            addCriterion("taxes_burden in", values, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenNotIn(List<String> values) {
            addCriterion("taxes_burden not in", values, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenBetween(String value1, String value2) {
            addCriterion("taxes_burden between", value1, value2, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andTaxesBurdenNotBetween(String value1, String value2) {
            addCriterion("taxes_burden not between", value1, value2, "taxesBurden");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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

        public Criteria andSellerScaleIsNull() {
            addCriterion("seller_scale is null");
            return (Criteria) this;
        }

        public Criteria andSellerScaleIsNotNull() {
            addCriterion("seller_scale is not null");
            return (Criteria) this;
        }

        public Criteria andSellerScaleEqualTo(BigDecimal value) {
            addCriterion("seller_scale =", value, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleNotEqualTo(BigDecimal value) {
            addCriterion("seller_scale <>", value, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleGreaterThan(BigDecimal value) {
            addCriterion("seller_scale >", value, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_scale >=", value, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleLessThan(BigDecimal value) {
            addCriterion("seller_scale <", value, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_scale <=", value, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleIn(List<BigDecimal> values) {
            addCriterion("seller_scale in", values, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleNotIn(List<BigDecimal> values) {
            addCriterion("seller_scale not in", values, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_scale between", value1, value2, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andSellerScaleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_scale not between", value1, value2, "sellerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleIsNull() {
            addCriterion("buyer_scale is null");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleIsNotNull() {
            addCriterion("buyer_scale is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleEqualTo(BigDecimal value) {
            addCriterion("buyer_scale =", value, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleNotEqualTo(BigDecimal value) {
            addCriterion("buyer_scale <>", value, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleGreaterThan(BigDecimal value) {
            addCriterion("buyer_scale >", value, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_scale >=", value, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleLessThan(BigDecimal value) {
            addCriterion("buyer_scale <", value, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_scale <=", value, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleIn(List<BigDecimal> values) {
            addCriterion("buyer_scale in", values, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleNotIn(List<BigDecimal> values) {
            addCriterion("buyer_scale not in", values, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_scale between", value1, value2, "buyerScale");
            return (Criteria) this;
        }

        public Criteria andBuyerScaleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_scale not between", value1, value2, "buyerScale");
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