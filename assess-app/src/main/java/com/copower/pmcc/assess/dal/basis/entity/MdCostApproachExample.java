package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdCostApproachExample {
    /**
     * tb_md_cost_approach
     */
    protected String orderByClause;

    /**
     * tb_md_cost_approach
     */
    protected boolean distinct;

    /**
     * tb_md_cost_approach
     */
    protected List<Criteria> oredCriteria;

    public MdCostApproachExample() {
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

    /**
     * tb_md_cost_approach
     */
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

        public Criteria andFarmlandAreaIsNull() {
            addCriterion("farmland_area is null");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaIsNotNull() {
            addCriterion("farmland_area is not null");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaEqualTo(BigDecimal value) {
            addCriterion("farmland_area =", value, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaNotEqualTo(BigDecimal value) {
            addCriterion("farmland_area <>", value, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaGreaterThan(BigDecimal value) {
            addCriterion("farmland_area >", value, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("farmland_area >=", value, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaLessThan(BigDecimal value) {
            addCriterion("farmland_area <", value, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("farmland_area <=", value, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaIn(List<BigDecimal> values) {
            addCriterion("farmland_area in", values, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaNotIn(List<BigDecimal> values) {
            addCriterion("farmland_area not in", values, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("farmland_area between", value1, value2, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andFarmlandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("farmland_area not between", value1, value2, "farmlandArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaIsNull() {
            addCriterion("plough_area is null");
            return (Criteria) this;
        }

        public Criteria andPloughAreaIsNotNull() {
            addCriterion("plough_area is not null");
            return (Criteria) this;
        }

        public Criteria andPloughAreaEqualTo(BigDecimal value) {
            addCriterion("plough_area =", value, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaNotEqualTo(BigDecimal value) {
            addCriterion("plough_area <>", value, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaGreaterThan(BigDecimal value) {
            addCriterion("plough_area >", value, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plough_area >=", value, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaLessThan(BigDecimal value) {
            addCriterion("plough_area <", value, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plough_area <=", value, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaIn(List<BigDecimal> values) {
            addCriterion("plough_area in", values, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaNotIn(List<BigDecimal> values) {
            addCriterion("plough_area not in", values, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plough_area between", value1, value2, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPloughAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plough_area not between", value1, value2, "ploughArea");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberIsNull() {
            addCriterion("population_number is null");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberIsNotNull() {
            addCriterion("population_number is not null");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberEqualTo(Integer value) {
            addCriterion("population_number =", value, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberNotEqualTo(Integer value) {
            addCriterion("population_number <>", value, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberGreaterThan(Integer value) {
            addCriterion("population_number >", value, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("population_number >=", value, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberLessThan(Integer value) {
            addCriterion("population_number <", value, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberLessThanOrEqualTo(Integer value) {
            addCriterion("population_number <=", value, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberIn(List<Integer> values) {
            addCriterion("population_number in", values, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberNotIn(List<Integer> values) {
            addCriterion("population_number not in", values, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberBetween(Integer value1, Integer value2) {
            addCriterion("population_number between", value1, value2, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andPopulationNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("population_number not between", value1, value2, "populationNumber");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdIsNull() {
            addCriterion("reward_rate_id is null");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdIsNotNull() {
            addCriterion("reward_rate_id is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdEqualTo(Integer value) {
            addCriterion("reward_rate_id =", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdNotEqualTo(Integer value) {
            addCriterion("reward_rate_id <>", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdGreaterThan(Integer value) {
            addCriterion("reward_rate_id >", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward_rate_id >=", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdLessThan(Integer value) {
            addCriterion("reward_rate_id <", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdLessThanOrEqualTo(Integer value) {
            addCriterion("reward_rate_id <=", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdIn(List<Integer> values) {
            addCriterion("reward_rate_id in", values, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdNotIn(List<Integer> values) {
            addCriterion("reward_rate_id not in", values, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdBetween(Integer value1, Integer value2) {
            addCriterion("reward_rate_id between", value1, value2, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reward_rate_id not between", value1, value2, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIsNull() {
            addCriterion("reward_rate is null");
            return (Criteria) this;
        }

        public Criteria andRewardRateIsNotNull() {
            addCriterion("reward_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRateEqualTo(String value) {
            addCriterion("reward_rate =", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotEqualTo(String value) {
            addCriterion("reward_rate <>", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateGreaterThan(String value) {
            addCriterion("reward_rate >", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateGreaterThanOrEqualTo(String value) {
            addCriterion("reward_rate >=", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLessThan(String value) {
            addCriterion("reward_rate <", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLessThanOrEqualTo(String value) {
            addCriterion("reward_rate <=", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLike(String value) {
            addCriterion("reward_rate like", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotLike(String value) {
            addCriterion("reward_rate not like", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateIn(List<String> values) {
            addCriterion("reward_rate in", values, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotIn(List<String> values) {
            addCriterion("reward_rate not in", values, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateBetween(String value1, String value2) {
            addCriterion("reward_rate between", value1, value2, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotBetween(String value1, String value2) {
            addCriterion("reward_rate not between", value1, value2, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseIsNull() {
            addCriterion("circulation_expense is null");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseIsNotNull() {
            addCriterion("circulation_expense is not null");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseEqualTo(BigDecimal value) {
            addCriterion("circulation_expense =", value, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseNotEqualTo(BigDecimal value) {
            addCriterion("circulation_expense <>", value, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseGreaterThan(BigDecimal value) {
            addCriterion("circulation_expense >", value, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("circulation_expense >=", value, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseLessThan(BigDecimal value) {
            addCriterion("circulation_expense <", value, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("circulation_expense <=", value, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseIn(List<BigDecimal> values) {
            addCriterion("circulation_expense in", values, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseNotIn(List<BigDecimal> values) {
            addCriterion("circulation_expense not in", values, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("circulation_expense between", value1, value2, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("circulation_expense not between", value1, value2, "circulationExpense");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkIsNull() {
            addCriterion("circulation_expense_remark is null");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkIsNotNull() {
            addCriterion("circulation_expense_remark is not null");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkEqualTo(String value) {
            addCriterion("circulation_expense_remark =", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkNotEqualTo(String value) {
            addCriterion("circulation_expense_remark <>", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkGreaterThan(String value) {
            addCriterion("circulation_expense_remark >", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("circulation_expense_remark >=", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkLessThan(String value) {
            addCriterion("circulation_expense_remark <", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkLessThanOrEqualTo(String value) {
            addCriterion("circulation_expense_remark <=", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkLike(String value) {
            addCriterion("circulation_expense_remark like", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkNotLike(String value) {
            addCriterion("circulation_expense_remark not like", value, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkIn(List<String> values) {
            addCriterion("circulation_expense_remark in", values, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkNotIn(List<String> values) {
            addCriterion("circulation_expense_remark not in", values, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkBetween(String value1, String value2) {
            addCriterion("circulation_expense_remark between", value1, value2, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andCirculationExpenseRemarkNotBetween(String value1, String value2) {
            addCriterion("circulation_expense_remark not between", value1, value2, "circulationExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseIsNull() {
            addCriterion("flat_expense is null");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseIsNotNull() {
            addCriterion("flat_expense is not null");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseEqualTo(BigDecimal value) {
            addCriterion("flat_expense =", value, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseNotEqualTo(BigDecimal value) {
            addCriterion("flat_expense <>", value, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseGreaterThan(BigDecimal value) {
            addCriterion("flat_expense >", value, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("flat_expense >=", value, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseLessThan(BigDecimal value) {
            addCriterion("flat_expense <", value, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("flat_expense <=", value, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseIn(List<BigDecimal> values) {
            addCriterion("flat_expense in", values, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseNotIn(List<BigDecimal> values) {
            addCriterion("flat_expense not in", values, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("flat_expense between", value1, value2, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("flat_expense not between", value1, value2, "flatExpense");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkIsNull() {
            addCriterion("flat_expense_remark is null");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkIsNotNull() {
            addCriterion("flat_expense_remark is not null");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkEqualTo(String value) {
            addCriterion("flat_expense_remark =", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkNotEqualTo(String value) {
            addCriterion("flat_expense_remark <>", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkGreaterThan(String value) {
            addCriterion("flat_expense_remark >", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("flat_expense_remark >=", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkLessThan(String value) {
            addCriterion("flat_expense_remark <", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkLessThanOrEqualTo(String value) {
            addCriterion("flat_expense_remark <=", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkLike(String value) {
            addCriterion("flat_expense_remark like", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkNotLike(String value) {
            addCriterion("flat_expense_remark not like", value, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkIn(List<String> values) {
            addCriterion("flat_expense_remark in", values, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkNotIn(List<String> values) {
            addCriterion("flat_expense_remark not in", values, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkBetween(String value1, String value2) {
            addCriterion("flat_expense_remark between", value1, value2, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andFlatExpenseRemarkNotBetween(String value1, String value2) {
            addCriterion("flat_expense_remark not between", value1, value2, "flatExpenseRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleIsNull() {
            addCriterion("machine_cycle is null");
            return (Criteria) this;
        }

        public Criteria andMachineCycleIsNotNull() {
            addCriterion("machine_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andMachineCycleEqualTo(BigDecimal value) {
            addCriterion("machine_cycle =", value, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleNotEqualTo(BigDecimal value) {
            addCriterion("machine_cycle <>", value, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleGreaterThan(BigDecimal value) {
            addCriterion("machine_cycle >", value, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("machine_cycle >=", value, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleLessThan(BigDecimal value) {
            addCriterion("machine_cycle <", value, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("machine_cycle <=", value, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleIn(List<BigDecimal> values) {
            addCriterion("machine_cycle in", values, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleNotIn(List<BigDecimal> values) {
            addCriterion("machine_cycle not in", values, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("machine_cycle between", value1, value2, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("machine_cycle not between", value1, value2, "machineCycle");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkIsNull() {
            addCriterion("machine_cycle_remark is null");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkIsNotNull() {
            addCriterion("machine_cycle_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkEqualTo(String value) {
            addCriterion("machine_cycle_remark =", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkNotEqualTo(String value) {
            addCriterion("machine_cycle_remark <>", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkGreaterThan(String value) {
            addCriterion("machine_cycle_remark >", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("machine_cycle_remark >=", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkLessThan(String value) {
            addCriterion("machine_cycle_remark <", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkLessThanOrEqualTo(String value) {
            addCriterion("machine_cycle_remark <=", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkLike(String value) {
            addCriterion("machine_cycle_remark like", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkNotLike(String value) {
            addCriterion("machine_cycle_remark not like", value, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkIn(List<String> values) {
            addCriterion("machine_cycle_remark in", values, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkNotIn(List<String> values) {
            addCriterion("machine_cycle_remark not in", values, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkBetween(String value1, String value2) {
            addCriterion("machine_cycle_remark between", value1, value2, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andMachineCycleRemarkNotBetween(String value1, String value2) {
            addCriterion("machine_cycle_remark not between", value1, value2, "machineCycleRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestIsNull() {
            addCriterion("calculated_interest is null");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestIsNotNull() {
            addCriterion("calculated_interest is not null");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestEqualTo(String value) {
            addCriterion("calculated_interest =", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestNotEqualTo(String value) {
            addCriterion("calculated_interest <>", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestGreaterThan(String value) {
            addCriterion("calculated_interest >", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestGreaterThanOrEqualTo(String value) {
            addCriterion("calculated_interest >=", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestLessThan(String value) {
            addCriterion("calculated_interest <", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestLessThanOrEqualTo(String value) {
            addCriterion("calculated_interest <=", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestLike(String value) {
            addCriterion("calculated_interest like", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestNotLike(String value) {
            addCriterion("calculated_interest not like", value, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestIn(List<String> values) {
            addCriterion("calculated_interest in", values, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestNotIn(List<String> values) {
            addCriterion("calculated_interest not in", values, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestBetween(String value1, String value2) {
            addCriterion("calculated_interest between", value1, value2, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestNotBetween(String value1, String value2) {
            addCriterion("calculated_interest not between", value1, value2, "calculatedInterest");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkIsNull() {
            addCriterion("calculated_interest_remark is null");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkIsNotNull() {
            addCriterion("calculated_interest_remark is not null");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkEqualTo(String value) {
            addCriterion("calculated_interest_remark =", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkNotEqualTo(String value) {
            addCriterion("calculated_interest_remark <>", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkGreaterThan(String value) {
            addCriterion("calculated_interest_remark >", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("calculated_interest_remark >=", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkLessThan(String value) {
            addCriterion("calculated_interest_remark <", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkLessThanOrEqualTo(String value) {
            addCriterion("calculated_interest_remark <=", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkLike(String value) {
            addCriterion("calculated_interest_remark like", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkNotLike(String value) {
            addCriterion("calculated_interest_remark not like", value, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkIn(List<String> values) {
            addCriterion("calculated_interest_remark in", values, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkNotIn(List<String> values) {
            addCriterion("calculated_interest_remark not in", values, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkBetween(String value1, String value2) {
            addCriterion("calculated_interest_remark between", value1, value2, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andCalculatedInterestRemarkNotBetween(String value1, String value2) {
            addCriterion("calculated_interest_remark not between", value1, value2, "calculatedInterestRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginIsNull() {
            addCriterion("profit_margin is null");
            return (Criteria) this;
        }

        public Criteria andProfitMarginIsNotNull() {
            addCriterion("profit_margin is not null");
            return (Criteria) this;
        }

        public Criteria andProfitMarginEqualTo(String value) {
            addCriterion("profit_margin =", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginNotEqualTo(String value) {
            addCriterion("profit_margin <>", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginGreaterThan(String value) {
            addCriterion("profit_margin >", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginGreaterThanOrEqualTo(String value) {
            addCriterion("profit_margin >=", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginLessThan(String value) {
            addCriterion("profit_margin <", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginLessThanOrEqualTo(String value) {
            addCriterion("profit_margin <=", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginLike(String value) {
            addCriterion("profit_margin like", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginNotLike(String value) {
            addCriterion("profit_margin not like", value, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginIn(List<String> values) {
            addCriterion("profit_margin in", values, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginNotIn(List<String> values) {
            addCriterion("profit_margin not in", values, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginBetween(String value1, String value2) {
            addCriterion("profit_margin between", value1, value2, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginNotBetween(String value1, String value2) {
            addCriterion("profit_margin not between", value1, value2, "profitMargin");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkIsNull() {
            addCriterion("profit_margin_remark is null");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkIsNotNull() {
            addCriterion("profit_margin_remark is not null");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkEqualTo(String value) {
            addCriterion("profit_margin_remark =", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkNotEqualTo(String value) {
            addCriterion("profit_margin_remark <>", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkGreaterThan(String value) {
            addCriterion("profit_margin_remark >", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("profit_margin_remark >=", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkLessThan(String value) {
            addCriterion("profit_margin_remark <", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkLessThanOrEqualTo(String value) {
            addCriterion("profit_margin_remark <=", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkLike(String value) {
            addCriterion("profit_margin_remark like", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkNotLike(String value) {
            addCriterion("profit_margin_remark not like", value, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkIn(List<String> values) {
            addCriterion("profit_margin_remark in", values, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkNotIn(List<String> values) {
            addCriterion("profit_margin_remark not in", values, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkBetween(String value1, String value2) {
            addCriterion("profit_margin_remark between", value1, value2, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andProfitMarginRemarkNotBetween(String value1, String value2) {
            addCriterion("profit_margin_remark not between", value1, value2, "profitMarginRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitIsNull() {
            addCriterion("incremental_benefit is null");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitIsNotNull() {
            addCriterion("incremental_benefit is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitEqualTo(String value) {
            addCriterion("incremental_benefit =", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitNotEqualTo(String value) {
            addCriterion("incremental_benefit <>", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitGreaterThan(String value) {
            addCriterion("incremental_benefit >", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitGreaterThanOrEqualTo(String value) {
            addCriterion("incremental_benefit >=", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitLessThan(String value) {
            addCriterion("incremental_benefit <", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitLessThanOrEqualTo(String value) {
            addCriterion("incremental_benefit <=", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitLike(String value) {
            addCriterion("incremental_benefit like", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitNotLike(String value) {
            addCriterion("incremental_benefit not like", value, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitIn(List<String> values) {
            addCriterion("incremental_benefit in", values, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitNotIn(List<String> values) {
            addCriterion("incremental_benefit not in", values, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitBetween(String value1, String value2) {
            addCriterion("incremental_benefit between", value1, value2, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitNotBetween(String value1, String value2) {
            addCriterion("incremental_benefit not between", value1, value2, "incrementalBenefit");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkIsNull() {
            addCriterion("incremental_benefit_remark is null");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkIsNotNull() {
            addCriterion("incremental_benefit_remark is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkEqualTo(String value) {
            addCriterion("incremental_benefit_remark =", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkNotEqualTo(String value) {
            addCriterion("incremental_benefit_remark <>", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkGreaterThan(String value) {
            addCriterion("incremental_benefit_remark >", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("incremental_benefit_remark >=", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkLessThan(String value) {
            addCriterion("incremental_benefit_remark <", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkLessThanOrEqualTo(String value) {
            addCriterion("incremental_benefit_remark <=", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkLike(String value) {
            addCriterion("incremental_benefit_remark like", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkNotLike(String value) {
            addCriterion("incremental_benefit_remark not like", value, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkIn(List<String> values) {
            addCriterion("incremental_benefit_remark in", values, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkNotIn(List<String> values) {
            addCriterion("incremental_benefit_remark not in", values, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkBetween(String value1, String value2) {
            addCriterion("incremental_benefit_remark between", value1, value2, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andIncrementalBenefitRemarkNotBetween(String value1, String value2) {
            addCriterion("incremental_benefit_remark not between", value1, value2, "incrementalBenefitRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustIsNull() {
            addCriterion("plot_ratio_adjust is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustIsNotNull() {
            addCriterion("plot_ratio_adjust is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustEqualTo(String value) {
            addCriterion("plot_ratio_adjust =", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustNotEqualTo(String value) {
            addCriterion("plot_ratio_adjust <>", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustGreaterThan(String value) {
            addCriterion("plot_ratio_adjust >", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustGreaterThanOrEqualTo(String value) {
            addCriterion("plot_ratio_adjust >=", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustLessThan(String value) {
            addCriterion("plot_ratio_adjust <", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustLessThanOrEqualTo(String value) {
            addCriterion("plot_ratio_adjust <=", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustLike(String value) {
            addCriterion("plot_ratio_adjust like", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustNotLike(String value) {
            addCriterion("plot_ratio_adjust not like", value, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustIn(List<String> values) {
            addCriterion("plot_ratio_adjust in", values, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustNotIn(List<String> values) {
            addCriterion("plot_ratio_adjust not in", values, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustBetween(String value1, String value2) {
            addCriterion("plot_ratio_adjust between", value1, value2, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustNotBetween(String value1, String value2) {
            addCriterion("plot_ratio_adjust not between", value1, value2, "plotRatioAdjust");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkIsNull() {
            addCriterion("plot_ratio_adjust_remark is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkIsNotNull() {
            addCriterion("plot_ratio_adjust_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkEqualTo(String value) {
            addCriterion("plot_ratio_adjust_remark =", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkNotEqualTo(String value) {
            addCriterion("plot_ratio_adjust_remark <>", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkGreaterThan(String value) {
            addCriterion("plot_ratio_adjust_remark >", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("plot_ratio_adjust_remark >=", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkLessThan(String value) {
            addCriterion("plot_ratio_adjust_remark <", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkLessThanOrEqualTo(String value) {
            addCriterion("plot_ratio_adjust_remark <=", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkLike(String value) {
            addCriterion("plot_ratio_adjust_remark like", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkNotLike(String value) {
            addCriterion("plot_ratio_adjust_remark not like", value, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkIn(List<String> values) {
            addCriterion("plot_ratio_adjust_remark in", values, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkNotIn(List<String> values) {
            addCriterion("plot_ratio_adjust_remark not in", values, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkBetween(String value1, String value2) {
            addCriterion("plot_ratio_adjust_remark between", value1, value2, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioAdjustRemarkNotBetween(String value1, String value2) {
            addCriterion("plot_ratio_adjust_remark not between", value1, value2, "plotRatioAdjustRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendIsNull() {
            addCriterion("plot_ratio_element_amend is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendIsNotNull() {
            addCriterion("plot_ratio_element_amend is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendEqualTo(BigDecimal value) {
            addCriterion("plot_ratio_element_amend =", value, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendNotEqualTo(BigDecimal value) {
            addCriterion("plot_ratio_element_amend <>", value, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendGreaterThan(BigDecimal value) {
            addCriterion("plot_ratio_element_amend >", value, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plot_ratio_element_amend >=", value, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendLessThan(BigDecimal value) {
            addCriterion("plot_ratio_element_amend <", value, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plot_ratio_element_amend <=", value, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendIn(List<BigDecimal> values) {
            addCriterion("plot_ratio_element_amend in", values, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendNotIn(List<BigDecimal> values) {
            addCriterion("plot_ratio_element_amend not in", values, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plot_ratio_element_amend between", value1, value2, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plot_ratio_element_amend not between", value1, value2, "plotRatioElementAmend");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkIsNull() {
            addCriterion("plot_ratio_element_amend_remark is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkIsNotNull() {
            addCriterion("plot_ratio_element_amend_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkEqualTo(String value) {
            addCriterion("plot_ratio_element_amend_remark =", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkNotEqualTo(String value) {
            addCriterion("plot_ratio_element_amend_remark <>", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkGreaterThan(String value) {
            addCriterion("plot_ratio_element_amend_remark >", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("plot_ratio_element_amend_remark >=", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkLessThan(String value) {
            addCriterion("plot_ratio_element_amend_remark <", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkLessThanOrEqualTo(String value) {
            addCriterion("plot_ratio_element_amend_remark <=", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkLike(String value) {
            addCriterion("plot_ratio_element_amend_remark like", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkNotLike(String value) {
            addCriterion("plot_ratio_element_amend_remark not like", value, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkIn(List<String> values) {
            addCriterion("plot_ratio_element_amend_remark in", values, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkNotIn(List<String> values) {
            addCriterion("plot_ratio_element_amend_remark not in", values, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkBetween(String value1, String value2) {
            addCriterion("plot_ratio_element_amend_remark between", value1, value2, "plotRatioElementAmendRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioElementAmendRemarkNotBetween(String value1, String value2) {
            addCriterion("plot_ratio_element_amend_remark not between", value1, value2, "plotRatioElementAmendRemark");
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

        public Criteria andLandRemainingYearIsNull() {
            addCriterion("land_remaining_year is null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIsNotNull() {
            addCriterion("land_remaining_year is not null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year =", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year <>", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearGreaterThan(BigDecimal value) {
            addCriterion("land_remaining_year >", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year >=", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearLessThan(BigDecimal value) {
            addCriterion("land_remaining_year <", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year <=", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIn(List<BigDecimal> values) {
            addCriterion("land_remaining_year in", values, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotIn(List<BigDecimal> values) {
            addCriterion("land_remaining_year not in", values, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_remaining_year between", value1, value2, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_remaining_year not between", value1, value2, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkIsNull() {
            addCriterion("land_remaining_year_remark is null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkIsNotNull() {
            addCriterion("land_remaining_year_remark is not null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkEqualTo(String value) {
            addCriterion("land_remaining_year_remark =", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkNotEqualTo(String value) {
            addCriterion("land_remaining_year_remark <>", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkGreaterThan(String value) {
            addCriterion("land_remaining_year_remark >", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("land_remaining_year_remark >=", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkLessThan(String value) {
            addCriterion("land_remaining_year_remark <", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkLessThanOrEqualTo(String value) {
            addCriterion("land_remaining_year_remark <=", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkLike(String value) {
            addCriterion("land_remaining_year_remark like", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkNotLike(String value) {
            addCriterion("land_remaining_year_remark not like", value, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkIn(List<String> values) {
            addCriterion("land_remaining_year_remark in", values, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkNotIn(List<String> values) {
            addCriterion("land_remaining_year_remark not in", values, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkBetween(String value1, String value2) {
            addCriterion("land_remaining_year_remark between", value1, value2, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearRemarkNotBetween(String value1, String value2) {
            addCriterion("land_remaining_year_remark not between", value1, value2, "landRemainingYearRemark");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIsNull() {
            addCriterion("parcel_setting_outer is null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIsNotNull() {
            addCriterion("parcel_setting_outer is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterEqualTo(String value) {
            addCriterion("parcel_setting_outer =", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotEqualTo(String value) {
            addCriterion("parcel_setting_outer <>", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterGreaterThan(String value) {
            addCriterion("parcel_setting_outer >", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_setting_outer >=", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLessThan(String value) {
            addCriterion("parcel_setting_outer <", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLessThanOrEqualTo(String value) {
            addCriterion("parcel_setting_outer <=", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLike(String value) {
            addCriterion("parcel_setting_outer like", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotLike(String value) {
            addCriterion("parcel_setting_outer not like", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIn(List<String> values) {
            addCriterion("parcel_setting_outer in", values, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotIn(List<String> values) {
            addCriterion("parcel_setting_outer not in", values, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterBetween(String value1, String value2) {
            addCriterion("parcel_setting_outer between", value1, value2, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotBetween(String value1, String value2) {
            addCriterion("parcel_setting_outer not between", value1, value2, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIsNull() {
            addCriterion("parcel_setting_inner is null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIsNotNull() {
            addCriterion("parcel_setting_inner is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerEqualTo(String value) {
            addCriterion("parcel_setting_inner =", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotEqualTo(String value) {
            addCriterion("parcel_setting_inner <>", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerGreaterThan(String value) {
            addCriterion("parcel_setting_inner >", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_setting_inner >=", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLessThan(String value) {
            addCriterion("parcel_setting_inner <", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLessThanOrEqualTo(String value) {
            addCriterion("parcel_setting_inner <=", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLike(String value) {
            addCriterion("parcel_setting_inner like", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotLike(String value) {
            addCriterion("parcel_setting_inner not like", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIn(List<String> values) {
            addCriterion("parcel_setting_inner in", values, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotIn(List<String> values) {
            addCriterion("parcel_setting_inner not in", values, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerBetween(String value1, String value2) {
            addCriterion("parcel_setting_inner between", value1, value2, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotBetween(String value1, String value2) {
            addCriterion("parcel_setting_inner not between", value1, value2, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceIsNull() {
            addCriterion("land_use_price is null");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceIsNotNull() {
            addCriterion("land_use_price is not null");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceEqualTo(BigDecimal value) {
            addCriterion("land_use_price =", value, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceNotEqualTo(BigDecimal value) {
            addCriterion("land_use_price <>", value, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceGreaterThan(BigDecimal value) {
            addCriterion("land_use_price >", value, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_use_price >=", value, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceLessThan(BigDecimal value) {
            addCriterion("land_use_price <", value, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_use_price <=", value, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceIn(List<BigDecimal> values) {
            addCriterion("land_use_price in", values, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceNotIn(List<BigDecimal> values) {
            addCriterion("land_use_price not in", values, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_use_price between", value1, value2, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andLandUsePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_use_price not between", value1, value2, "landUsePrice");
            return (Criteria) this;
        }

        public Criteria andYearFixedIsNull() {
            addCriterion("year_fixed is null");
            return (Criteria) this;
        }

        public Criteria andYearFixedIsNotNull() {
            addCriterion("year_fixed is not null");
            return (Criteria) this;
        }

        public Criteria andYearFixedEqualTo(BigDecimal value) {
            addCriterion("year_fixed =", value, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedNotEqualTo(BigDecimal value) {
            addCriterion("year_fixed <>", value, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedGreaterThan(BigDecimal value) {
            addCriterion("year_fixed >", value, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("year_fixed >=", value, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedLessThan(BigDecimal value) {
            addCriterion("year_fixed <", value, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("year_fixed <=", value, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedIn(List<BigDecimal> values) {
            addCriterion("year_fixed in", values, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedNotIn(List<BigDecimal> values) {
            addCriterion("year_fixed not in", values, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("year_fixed between", value1, value2, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andYearFixedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("year_fixed not between", value1, value2, "yearFixed");
            return (Criteria) this;
        }

        public Criteria andParcelUnitIsNull() {
            addCriterion("parcel_unit is null");
            return (Criteria) this;
        }

        public Criteria andParcelUnitIsNotNull() {
            addCriterion("parcel_unit is not null");
            return (Criteria) this;
        }

        public Criteria andParcelUnitEqualTo(BigDecimal value) {
            addCriterion("parcel_unit =", value, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitNotEqualTo(BigDecimal value) {
            addCriterion("parcel_unit <>", value, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitGreaterThan(BigDecimal value) {
            addCriterion("parcel_unit >", value, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_unit >=", value, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitLessThan(BigDecimal value) {
            addCriterion("parcel_unit <", value, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_unit <=", value, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitIn(List<BigDecimal> values) {
            addCriterion("parcel_unit in", values, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitNotIn(List<BigDecimal> values) {
            addCriterion("parcel_unit not in", values, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_unit between", value1, value2, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andParcelUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_unit not between", value1, value2, "parcelUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitIsNull() {
            addCriterion("land_cost_price_unit is null");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitIsNotNull() {
            addCriterion("land_cost_price_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitEqualTo(BigDecimal value) {
            addCriterion("land_cost_price_unit =", value, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitNotEqualTo(BigDecimal value) {
            addCriterion("land_cost_price_unit <>", value, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitGreaterThan(BigDecimal value) {
            addCriterion("land_cost_price_unit >", value, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_cost_price_unit >=", value, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitLessThan(BigDecimal value) {
            addCriterion("land_cost_price_unit <", value, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_cost_price_unit <=", value, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitIn(List<BigDecimal> values) {
            addCriterion("land_cost_price_unit in", values, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitNotIn(List<BigDecimal> values) {
            addCriterion("land_cost_price_unit not in", values, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_cost_price_unit between", value1, value2, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandCostPriceUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_cost_price_unit not between", value1, value2, "landCostPriceUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitIsNull() {
            addCriterion("land_acquisition_unit is null");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitIsNotNull() {
            addCriterion("land_acquisition_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitEqualTo(BigDecimal value) {
            addCriterion("land_acquisition_unit =", value, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitNotEqualTo(BigDecimal value) {
            addCriterion("land_acquisition_unit <>", value, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitGreaterThan(BigDecimal value) {
            addCriterion("land_acquisition_unit >", value, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_acquisition_unit >=", value, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitLessThan(BigDecimal value) {
            addCriterion("land_acquisition_unit <", value, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_acquisition_unit <=", value, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitIn(List<BigDecimal> values) {
            addCriterion("land_acquisition_unit in", values, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitNotIn(List<BigDecimal> values) {
            addCriterion("land_acquisition_unit not in", values, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_acquisition_unit between", value1, value2, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_acquisition_unit not between", value1, value2, "landAcquisitionUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitIsNull() {
            addCriterion("land_production_profit_unit is null");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitIsNotNull() {
            addCriterion("land_production_profit_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitEqualTo(BigDecimal value) {
            addCriterion("land_production_profit_unit =", value, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitNotEqualTo(BigDecimal value) {
            addCriterion("land_production_profit_unit <>", value, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitGreaterThan(BigDecimal value) {
            addCriterion("land_production_profit_unit >", value, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_production_profit_unit >=", value, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitLessThan(BigDecimal value) {
            addCriterion("land_production_profit_unit <", value, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_production_profit_unit <=", value, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitIn(List<BigDecimal> values) {
            addCriterion("land_production_profit_unit in", values, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitNotIn(List<BigDecimal> values) {
            addCriterion("land_production_profit_unit not in", values, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_production_profit_unit between", value1, value2, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionProfitUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_production_profit_unit not between", value1, value2, "landProductionProfitUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitIsNull() {
            addCriterion("land_production_interest_unit is null");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitIsNotNull() {
            addCriterion("land_production_interest_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitEqualTo(BigDecimal value) {
            addCriterion("land_production_interest_unit =", value, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitNotEqualTo(BigDecimal value) {
            addCriterion("land_production_interest_unit <>", value, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitGreaterThan(BigDecimal value) {
            addCriterion("land_production_interest_unit >", value, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_production_interest_unit >=", value, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitLessThan(BigDecimal value) {
            addCriterion("land_production_interest_unit <", value, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_production_interest_unit <=", value, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitIn(List<BigDecimal> values) {
            addCriterion("land_production_interest_unit in", values, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitNotIn(List<BigDecimal> values) {
            addCriterion("land_production_interest_unit not in", values, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_production_interest_unit between", value1, value2, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andLandProductionInterestUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_production_interest_unit not between", value1, value2, "landProductionInterestUnit");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionIsNull() {
            addCriterion("have_not_land_acquisition is null");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionIsNotNull() {
            addCriterion("have_not_land_acquisition is not null");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionEqualTo(BigDecimal value) {
            addCriterion("have_not_land_acquisition =", value, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionNotEqualTo(BigDecimal value) {
            addCriterion("have_not_land_acquisition <>", value, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionGreaterThan(BigDecimal value) {
            addCriterion("have_not_land_acquisition >", value, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("have_not_land_acquisition >=", value, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionLessThan(BigDecimal value) {
            addCriterion("have_not_land_acquisition <", value, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("have_not_land_acquisition <=", value, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionIn(List<BigDecimal> values) {
            addCriterion("have_not_land_acquisition in", values, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionNotIn(List<BigDecimal> values) {
            addCriterion("have_not_land_acquisition not in", values, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("have_not_land_acquisition between", value1, value2, "haveNotLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andHaveNotLandAcquisitionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("have_not_land_acquisition not between", value1, value2, "haveNotLandAcquisition");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_md_cost_approach
     */
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