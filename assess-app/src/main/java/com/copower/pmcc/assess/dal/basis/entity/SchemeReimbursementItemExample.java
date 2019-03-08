package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SchemeReimbursementItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchemeReimbursementItemExample() {
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

        public Criteria andNotSetUpUnitPriceIsNull() {
            addCriterion("not_set_up_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceIsNotNull() {
            addCriterion("not_set_up_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceEqualTo(BigDecimal value) {
            addCriterion("not_set_up_unit_price =", value, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("not_set_up_unit_price <>", value, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("not_set_up_unit_price >", value, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("not_set_up_unit_price >=", value, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceLessThan(BigDecimal value) {
            addCriterion("not_set_up_unit_price <", value, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("not_set_up_unit_price <=", value, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceIn(List<BigDecimal> values) {
            addCriterion("not_set_up_unit_price in", values, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("not_set_up_unit_price not in", values, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("not_set_up_unit_price between", value1, value2, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("not_set_up_unit_price not between", value1, value2, "notSetUpUnitPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceIsNull() {
            addCriterion("not_set_up_total_price is null");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceIsNotNull() {
            addCriterion("not_set_up_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceEqualTo(BigDecimal value) {
            addCriterion("not_set_up_total_price =", value, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("not_set_up_total_price <>", value, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("not_set_up_total_price >", value, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("not_set_up_total_price >=", value, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceLessThan(BigDecimal value) {
            addCriterion("not_set_up_total_price <", value, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("not_set_up_total_price <=", value, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceIn(List<BigDecimal> values) {
            addCriterion("not_set_up_total_price in", values, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("not_set_up_total_price not in", values, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("not_set_up_total_price between", value1, value2, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNotSetUpTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("not_set_up_total_price not between", value1, value2, "notSetUpTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceIsNull() {
            addCriterion("know_total_price is null");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceIsNotNull() {
            addCriterion("know_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceEqualTo(BigDecimal value) {
            addCriterion("know_total_price =", value, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("know_total_price <>", value, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("know_total_price >", value, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("know_total_price >=", value, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceLessThan(BigDecimal value) {
            addCriterion("know_total_price <", value, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("know_total_price <=", value, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceIn(List<BigDecimal> values) {
            addCriterion("know_total_price in", values, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("know_total_price not in", values, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("know_total_price between", value1, value2, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andKnowTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("know_total_price not between", value1, value2, "knowTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceIsNull() {
            addCriterion("mortgaged_total_price is null");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceIsNotNull() {
            addCriterion("mortgaged_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceEqualTo(BigDecimal value) {
            addCriterion("mortgaged_total_price =", value, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("mortgaged_total_price <>", value, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("mortgaged_total_price >", value, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mortgaged_total_price >=", value, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceLessThan(BigDecimal value) {
            addCriterion("mortgaged_total_price <", value, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mortgaged_total_price <=", value, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceIn(List<BigDecimal> values) {
            addCriterion("mortgaged_total_price in", values, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("mortgaged_total_price not in", values, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mortgaged_total_price between", value1, value2, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgagedTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mortgaged_total_price not between", value1, value2, "mortgagedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceIsNull() {
            addCriterion("owed_total_price is null");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceIsNotNull() {
            addCriterion("owed_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceEqualTo(BigDecimal value) {
            addCriterion("owed_total_price =", value, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("owed_total_price <>", value, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("owed_total_price >", value, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("owed_total_price >=", value, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceLessThan(BigDecimal value) {
            addCriterion("owed_total_price <", value, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("owed_total_price <=", value, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceIn(List<BigDecimal> values) {
            addCriterion("owed_total_price in", values, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("owed_total_price not in", values, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("owed_total_price between", value1, value2, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOwedTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("owed_total_price not between", value1, value2, "owedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceIsNull() {
            addCriterion("other_total_price is null");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceIsNotNull() {
            addCriterion("other_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceEqualTo(BigDecimal value) {
            addCriterion("other_total_price =", value, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("other_total_price <>", value, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("other_total_price >", value, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_total_price >=", value, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceLessThan(BigDecimal value) {
            addCriterion("other_total_price <", value, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_total_price <=", value, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceIn(List<BigDecimal> values) {
            addCriterion("other_total_price in", values, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("other_total_price not in", values, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_total_price between", value1, value2, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andOtherTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_total_price not between", value1, value2, "otherTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceIsNull() {
            addCriterion("mortgage_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceIsNotNull() {
            addCriterion("mortgage_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceEqualTo(BigDecimal value) {
            addCriterion("mortgage_unit_price =", value, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("mortgage_unit_price <>", value, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("mortgage_unit_price >", value, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mortgage_unit_price >=", value, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceLessThan(BigDecimal value) {
            addCriterion("mortgage_unit_price <", value, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mortgage_unit_price <=", value, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceIn(List<BigDecimal> values) {
            addCriterion("mortgage_unit_price in", values, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("mortgage_unit_price not in", values, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mortgage_unit_price between", value1, value2, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mortgage_unit_price not between", value1, value2, "mortgageUnitPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceIsNull() {
            addCriterion("mortgage_total_price is null");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceIsNotNull() {
            addCriterion("mortgage_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceEqualTo(BigDecimal value) {
            addCriterion("mortgage_total_price =", value, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("mortgage_total_price <>", value, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("mortgage_total_price >", value, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mortgage_total_price >=", value, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceLessThan(BigDecimal value) {
            addCriterion("mortgage_total_price <", value, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mortgage_total_price <=", value, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceIn(List<BigDecimal> values) {
            addCriterion("mortgage_total_price in", values, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("mortgage_total_price not in", values, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mortgage_total_price between", value1, value2, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMortgageTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mortgage_total_price not between", value1, value2, "mortgageTotalPrice");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(Integer value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(Integer value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(Integer value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(Integer value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(Integer value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<Integer> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<Integer> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(Integer value1, Integer value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
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