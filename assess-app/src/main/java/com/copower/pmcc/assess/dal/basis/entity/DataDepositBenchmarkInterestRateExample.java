package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataDepositBenchmarkInterestRateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataDepositBenchmarkInterestRateExample() {
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

        public Criteria andAdjustTimeIsNull() {
            addCriterion("adjust_time is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIsNotNull() {
            addCriterion("adjust_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeEqualTo(Date value) {
            addCriterion("adjust_time =", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotEqualTo(Date value) {
            addCriterion("adjust_time <>", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeGreaterThan(Date value) {
            addCriterion("adjust_time >", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("adjust_time >=", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeLessThan(Date value) {
            addCriterion("adjust_time <", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeLessThanOrEqualTo(Date value) {
            addCriterion("adjust_time <=", value, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeIn(List<Date> values) {
            addCriterion("adjust_time in", values, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotIn(List<Date> values) {
            addCriterion("adjust_time not in", values, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeBetween(Date value1, Date value2) {
            addCriterion("adjust_time between", value1, value2, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andAdjustTimeNotBetween(Date value1, Date value2) {
            addCriterion("adjust_time not between", value1, value2, "adjustTime");
            return (Criteria) this;
        }

        public Criteria andDemandDepositIsNull() {
            addCriterion("demand_deposit is null");
            return (Criteria) this;
        }

        public Criteria andDemandDepositIsNotNull() {
            addCriterion("demand_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDemandDepositEqualTo(BigDecimal value) {
            addCriterion("demand_deposit =", value, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositNotEqualTo(BigDecimal value) {
            addCriterion("demand_deposit <>", value, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositGreaterThan(BigDecimal value) {
            addCriterion("demand_deposit >", value, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("demand_deposit >=", value, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositLessThan(BigDecimal value) {
            addCriterion("demand_deposit <", value, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("demand_deposit <=", value, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositIn(List<BigDecimal> values) {
            addCriterion("demand_deposit in", values, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositNotIn(List<BigDecimal> values) {
            addCriterion("demand_deposit not in", values, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("demand_deposit between", value1, value2, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDemandDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("demand_deposit not between", value1, value2, "demandDeposit");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthIsNull() {
            addCriterion("deposit_withdraw_lump_sum_three_month is null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthIsNotNull() {
            addCriterion("deposit_withdraw_lump_sum_three_month is not null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_month =", value, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthNotEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_month <>", value, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthGreaterThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_month >", value, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_month >=", value, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthLessThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_month <", value, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_month <=", value, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_three_month in", values, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthNotIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_three_month not in", values, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_three_month between", value1, value2, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeMonthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_three_month not between", value1, value2, "depositWithdrawLumpSumThreeMonth");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearIsNull() {
            addCriterion("deposit_withdraw_lump_sum_half_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearIsNotNull() {
            addCriterion("deposit_withdraw_lump_sum_half_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_half_year =", value, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_half_year <>", value, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_half_year >", value, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_half_year >=", value, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearLessThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_half_year <", value, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_half_year <=", value, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_half_year in", values, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_half_year not in", values, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_half_year between", value1, value2, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumHalfYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_half_year not between", value1, value2, "depositWithdrawLumpSumHalfYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearIsNull() {
            addCriterion("deposit_withdraw_lump_sum_one_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearIsNotNull() {
            addCriterion("deposit_withdraw_lump_sum_one_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_one_year =", value, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_one_year <>", value, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_one_year >", value, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_one_year >=", value, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearLessThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_one_year <", value, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_one_year <=", value, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_one_year in", values, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_one_year not in", values, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_one_year between", value1, value2, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumOneYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_one_year not between", value1, value2, "depositWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearIsNull() {
            addCriterion("deposit_withdraw_lump_sum_two_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearIsNotNull() {
            addCriterion("deposit_withdraw_lump_sum_two_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_two_year =", value, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_two_year <>", value, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_two_year >", value, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_two_year >=", value, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearLessThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_two_year <", value, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_two_year <=", value, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_two_year in", values, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_two_year not in", values, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_two_year between", value1, value2, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumTwoYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_two_year not between", value1, value2, "depositWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearIsNull() {
            addCriterion("deposit_withdraw_lump_sum_three_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearIsNotNull() {
            addCriterion("deposit_withdraw_lump_sum_three_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_year =", value, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_year <>", value, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_year >", value, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_year >=", value, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearLessThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_year <", value, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_three_year <=", value, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_three_year in", values, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_three_year not in", values, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_three_year between", value1, value2, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumThreeYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_three_year not between", value1, value2, "depositWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearIsNull() {
            addCriterion("deposit_withdraw_lump_sum_five_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearIsNotNull() {
            addCriterion("deposit_withdraw_lump_sum_five_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_five_year =", value, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_five_year <>", value, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_five_year >", value, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_five_year >=", value, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearLessThan(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_five_year <", value, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_withdraw_lump_sum_five_year <=", value, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_five_year in", values, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_withdraw_lump_sum_five_year not in", values, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_five_year between", value1, value2, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositWithdrawLumpSumFiveYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_withdraw_lump_sum_five_year not between", value1, value2, "depositWithdrawLumpSumFiveYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearIsNull() {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearIsNotNull() {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year =", value, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year <>", value, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year >", value, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year >=", value, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearLessThan(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year <", value, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year <=", value, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearIn(List<BigDecimal> values) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year in", values, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year not in", values, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year between", value1, value2, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumOneYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_installments_withdraw_lump_sum_one_year not between", value1, value2, "depositInstallmentsWithdrawLumpSumOneYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearIsNull() {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearIsNotNull() {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year =", value, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year <>", value, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year >", value, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year >=", value, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearLessThan(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year <", value, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year <=", value, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearIn(List<BigDecimal> values) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year in", values, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year not in", values, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year between", value1, value2, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumTwoYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_installments_withdraw_lump_sum_two_year not between", value1, value2, "depositInstallmentsWithdrawLumpSumTwoYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearIsNull() {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year is null");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearIsNotNull() {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year is not null");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year =", value, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearNotEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year <>", value, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearGreaterThan(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year >", value, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year >=", value, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearLessThan(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year <", value, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year <=", value, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearIn(List<BigDecimal> values) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year in", values, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearNotIn(List<BigDecimal> values) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year not in", values, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year between", value1, value2, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andDepositInstallmentsWithdrawLumpSumThreeYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_installments_withdraw_lump_sum_three_year not between", value1, value2, "depositInstallmentsWithdrawLumpSumThreeYear");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositIsNull() {
            addCriterion("time_demand_optional_deposit is null");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositIsNotNull() {
            addCriterion("time_demand_optional_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositEqualTo(BigDecimal value) {
            addCriterion("time_demand_optional_deposit =", value, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositNotEqualTo(BigDecimal value) {
            addCriterion("time_demand_optional_deposit <>", value, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositGreaterThan(BigDecimal value) {
            addCriterion("time_demand_optional_deposit >", value, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("time_demand_optional_deposit >=", value, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositLessThan(BigDecimal value) {
            addCriterion("time_demand_optional_deposit <", value, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("time_demand_optional_deposit <=", value, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositIn(List<BigDecimal> values) {
            addCriterion("time_demand_optional_deposit in", values, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositNotIn(List<BigDecimal> values) {
            addCriterion("time_demand_optional_deposit not in", values, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("time_demand_optional_deposit between", value1, value2, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andTimeDemandOptionalDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("time_demand_optional_deposit not between", value1, value2, "timeDemandOptionalDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositIsNull() {
            addCriterion("agreement_deposit is null");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositIsNotNull() {
            addCriterion("agreement_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositEqualTo(BigDecimal value) {
            addCriterion("agreement_deposit =", value, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositNotEqualTo(BigDecimal value) {
            addCriterion("agreement_deposit <>", value, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositGreaterThan(BigDecimal value) {
            addCriterion("agreement_deposit >", value, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agreement_deposit >=", value, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositLessThan(BigDecimal value) {
            addCriterion("agreement_deposit <", value, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agreement_deposit <=", value, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositIn(List<BigDecimal> values) {
            addCriterion("agreement_deposit in", values, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositNotIn(List<BigDecimal> values) {
            addCriterion("agreement_deposit not in", values, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agreement_deposit between", value1, value2, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andAgreementDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agreement_deposit not between", value1, value2, "agreementDeposit");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayIsNull() {
            addCriterion("call_deposit_one_day is null");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayIsNotNull() {
            addCriterion("call_deposit_one_day is not null");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayEqualTo(BigDecimal value) {
            addCriterion("call_deposit_one_day =", value, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayNotEqualTo(BigDecimal value) {
            addCriterion("call_deposit_one_day <>", value, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayGreaterThan(BigDecimal value) {
            addCriterion("call_deposit_one_day >", value, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("call_deposit_one_day >=", value, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayLessThan(BigDecimal value) {
            addCriterion("call_deposit_one_day <", value, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("call_deposit_one_day <=", value, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayIn(List<BigDecimal> values) {
            addCriterion("call_deposit_one_day in", values, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayNotIn(List<BigDecimal> values) {
            addCriterion("call_deposit_one_day not in", values, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("call_deposit_one_day between", value1, value2, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositOneDayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("call_deposit_one_day not between", value1, value2, "callDepositOneDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayIsNull() {
            addCriterion("call_deposit_seven_day is null");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayIsNotNull() {
            addCriterion("call_deposit_seven_day is not null");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayEqualTo(BigDecimal value) {
            addCriterion("call_deposit_seven_day =", value, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayNotEqualTo(BigDecimal value) {
            addCriterion("call_deposit_seven_day <>", value, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayGreaterThan(BigDecimal value) {
            addCriterion("call_deposit_seven_day >", value, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("call_deposit_seven_day >=", value, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayLessThan(BigDecimal value) {
            addCriterion("call_deposit_seven_day <", value, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("call_deposit_seven_day <=", value, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayIn(List<BigDecimal> values) {
            addCriterion("call_deposit_seven_day in", values, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayNotIn(List<BigDecimal> values) {
            addCriterion("call_deposit_seven_day not in", values, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("call_deposit_seven_day between", value1, value2, "callDepositSevenDay");
            return (Criteria) this;
        }

        public Criteria andCallDepositSevenDayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("call_deposit_seven_day not between", value1, value2, "callDepositSevenDay");
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