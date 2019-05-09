package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdCostApproachExample {
    protected String orderByClause;

    protected boolean distinct;

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

        public Criteria andImprevisionCostIsNull() {
            addCriterion("imprevision_cost is null");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostIsNotNull() {
            addCriterion("imprevision_cost is not null");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostEqualTo(String value) {
            addCriterion("imprevision_cost =", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostNotEqualTo(String value) {
            addCriterion("imprevision_cost <>", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostGreaterThan(String value) {
            addCriterion("imprevision_cost >", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostGreaterThanOrEqualTo(String value) {
            addCriterion("imprevision_cost >=", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostLessThan(String value) {
            addCriterion("imprevision_cost <", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostLessThanOrEqualTo(String value) {
            addCriterion("imprevision_cost <=", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostLike(String value) {
            addCriterion("imprevision_cost like", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostNotLike(String value) {
            addCriterion("imprevision_cost not like", value, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostIn(List<String> values) {
            addCriterion("imprevision_cost in", values, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostNotIn(List<String> values) {
            addCriterion("imprevision_cost not in", values, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostBetween(String value1, String value2) {
            addCriterion("imprevision_cost between", value1, value2, "imprevisionCost");
            return (Criteria) this;
        }

        public Criteria andImprevisionCostNotBetween(String value1, String value2) {
            addCriterion("imprevision_cost not between", value1, value2, "imprevisionCost");
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