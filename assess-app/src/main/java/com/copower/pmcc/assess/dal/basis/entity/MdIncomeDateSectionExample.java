package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeDateSectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeDateSectionExample() {
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

        public Criteria andIncomeIdIsNull() {
            addCriterion("income_id is null");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIsNotNull() {
            addCriterion("income_id is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeIdEqualTo(Integer value) {
            addCriterion("income_id =", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotEqualTo(Integer value) {
            addCriterion("income_id <>", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdGreaterThan(Integer value) {
            addCriterion("income_id >", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_id >=", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLessThan(Integer value) {
            addCriterion("income_id <", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLessThanOrEqualTo(Integer value) {
            addCriterion("income_id <=", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIn(List<Integer> values) {
            addCriterion("income_id in", values, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotIn(List<Integer> values) {
            addCriterion("income_id not in", values, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdBetween(Integer value1, Integer value2) {
            addCriterion("income_id between", value1, value2, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("income_id not between", value1, value2, "incomeId");
            return (Criteria) this;
        }

        public Criteria andOperationModeIsNull() {
            addCriterion("operation_mode is null");
            return (Criteria) this;
        }

        public Criteria andOperationModeIsNotNull() {
            addCriterion("operation_mode is not null");
            return (Criteria) this;
        }

        public Criteria andOperationModeEqualTo(Integer value) {
            addCriterion("operation_mode =", value, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeNotEqualTo(Integer value) {
            addCriterion("operation_mode <>", value, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeGreaterThan(Integer value) {
            addCriterion("operation_mode >", value, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operation_mode >=", value, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeLessThan(Integer value) {
            addCriterion("operation_mode <", value, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeLessThanOrEqualTo(Integer value) {
            addCriterion("operation_mode <=", value, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeIn(List<Integer> values) {
            addCriterion("operation_mode in", values, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeNotIn(List<Integer> values) {
            addCriterion("operation_mode not in", values, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeBetween(Integer value1, Integer value2) {
            addCriterion("operation_mode between", value1, value2, "operationMode");
            return (Criteria) this;
        }

        public Criteria andOperationModeNotBetween(Integer value1, Integer value2) {
            addCriterion("operation_mode not between", value1, value2, "operationMode");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterion("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterion("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterion("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterion("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterion("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterion("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterion("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andYearCountIsNull() {
            addCriterion("year_count is null");
            return (Criteria) this;
        }

        public Criteria andYearCountIsNotNull() {
            addCriterion("year_count is not null");
            return (Criteria) this;
        }

        public Criteria andYearCountEqualTo(BigDecimal value) {
            addCriterion("year_count =", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountNotEqualTo(BigDecimal value) {
            addCriterion("year_count <>", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountGreaterThan(BigDecimal value) {
            addCriterion("year_count >", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("year_count >=", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountLessThan(BigDecimal value) {
            addCriterion("year_count <", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("year_count <=", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountIn(List<BigDecimal> values) {
            addCriterion("year_count in", values, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountNotIn(List<BigDecimal> values) {
            addCriterion("year_count not in", values, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("year_count between", value1, value2, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("year_count not between", value1, value2, "yearCount");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalIsNull() {
            addCriterion("income_total is null");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalIsNotNull() {
            addCriterion("income_total is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalEqualTo(BigDecimal value) {
            addCriterion("income_total =", value, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalNotEqualTo(BigDecimal value) {
            addCriterion("income_total <>", value, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalGreaterThan(BigDecimal value) {
            addCriterion("income_total >", value, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("income_total >=", value, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalLessThan(BigDecimal value) {
            addCriterion("income_total <", value, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("income_total <=", value, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalIn(List<BigDecimal> values) {
            addCriterion("income_total in", values, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalNotIn(List<BigDecimal> values) {
            addCriterion("income_total not in", values, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_total between", value1, value2, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andIncomeTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_total not between", value1, value2, "incomeTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalIsNull() {
            addCriterion("cost_total is null");
            return (Criteria) this;
        }

        public Criteria andCostTotalIsNotNull() {
            addCriterion("cost_total is not null");
            return (Criteria) this;
        }

        public Criteria andCostTotalEqualTo(BigDecimal value) {
            addCriterion("cost_total =", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalNotEqualTo(BigDecimal value) {
            addCriterion("cost_total <>", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalGreaterThan(BigDecimal value) {
            addCriterion("cost_total >", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_total >=", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalLessThan(BigDecimal value) {
            addCriterion("cost_total <", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_total <=", value, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalIn(List<BigDecimal> values) {
            addCriterion("cost_total in", values, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalNotIn(List<BigDecimal> values) {
            addCriterion("cost_total not in", values, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_total between", value1, value2, "costTotal");
            return (Criteria) this;
        }

        public Criteria andCostTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_total not between", value1, value2, "costTotal");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitIsNull() {
            addCriterion("operating_profit is null");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitIsNotNull() {
            addCriterion("operating_profit is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitEqualTo(BigDecimal value) {
            addCriterion("operating_profit =", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitNotEqualTo(BigDecimal value) {
            addCriterion("operating_profit <>", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitGreaterThan(BigDecimal value) {
            addCriterion("operating_profit >", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_profit >=", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitLessThan(BigDecimal value) {
            addCriterion("operating_profit <", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_profit <=", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitIn(List<BigDecimal> values) {
            addCriterion("operating_profit in", values, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitNotIn(List<BigDecimal> values) {
            addCriterion("operating_profit not in", values, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_profit between", value1, value2, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_profit not between", value1, value2, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitIsNull() {
            addCriterion("net_profit is null");
            return (Criteria) this;
        }

        public Criteria andNetProfitIsNotNull() {
            addCriterion("net_profit is not null");
            return (Criteria) this;
        }

        public Criteria andNetProfitEqualTo(String value) {
            addCriterion("net_profit =", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitNotEqualTo(String value) {
            addCriterion("net_profit <>", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitGreaterThan(String value) {
            addCriterion("net_profit >", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitGreaterThanOrEqualTo(String value) {
            addCriterion("net_profit >=", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitLessThan(String value) {
            addCriterion("net_profit <", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitLessThanOrEqualTo(String value) {
            addCriterion("net_profit <=", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitLike(String value) {
            addCriterion("net_profit like", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitNotLike(String value) {
            addCriterion("net_profit not like", value, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitIn(List<String> values) {
            addCriterion("net_profit in", values, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitNotIn(List<String> values) {
            addCriterion("net_profit not in", values, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitBetween(String value1, String value2) {
            addCriterion("net_profit between", value1, value2, "netProfit");
            return (Criteria) this;
        }

        public Criteria andNetProfitNotBetween(String value1, String value2) {
            addCriterion("net_profit not between", value1, value2, "netProfit");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateIsNull() {
            addCriterion("rental_growth_rate is null");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateIsNotNull() {
            addCriterion("rental_growth_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateEqualTo(BigDecimal value) {
            addCriterion("rental_growth_rate =", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotEqualTo(BigDecimal value) {
            addCriterion("rental_growth_rate <>", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateGreaterThan(BigDecimal value) {
            addCriterion("rental_growth_rate >", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rental_growth_rate >=", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateLessThan(BigDecimal value) {
            addCriterion("rental_growth_rate <", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rental_growth_rate <=", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateIn(List<BigDecimal> values) {
            addCriterion("rental_growth_rate in", values, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotIn(List<BigDecimal> values) {
            addCriterion("rental_growth_rate not in", values, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rental_growth_rate between", value1, value2, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rental_growth_rate not between", value1, value2, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainIsNull() {
            addCriterion("rental_growth_rate_explain is null");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainIsNotNull() {
            addCriterion("rental_growth_rate_explain is not null");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainEqualTo(String value) {
            addCriterion("rental_growth_rate_explain =", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainNotEqualTo(String value) {
            addCriterion("rental_growth_rate_explain <>", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainGreaterThan(String value) {
            addCriterion("rental_growth_rate_explain >", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainGreaterThanOrEqualTo(String value) {
            addCriterion("rental_growth_rate_explain >=", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainLessThan(String value) {
            addCriterion("rental_growth_rate_explain <", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainLessThanOrEqualTo(String value) {
            addCriterion("rental_growth_rate_explain <=", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainLike(String value) {
            addCriterion("rental_growth_rate_explain like", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainNotLike(String value) {
            addCriterion("rental_growth_rate_explain not like", value, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainIn(List<String> values) {
            addCriterion("rental_growth_rate_explain in", values, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainNotIn(List<String> values) {
            addCriterion("rental_growth_rate_explain not in", values, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainBetween(String value1, String value2) {
            addCriterion("rental_growth_rate_explain between", value1, value2, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateExplainNotBetween(String value1, String value2) {
            addCriterion("rental_growth_rate_explain not between", value1, value2, "rentalGrowthRateExplain");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorIsNull() {
            addCriterion("correction_factor is null");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorIsNotNull() {
            addCriterion("correction_factor is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorEqualTo(BigDecimal value) {
            addCriterion("correction_factor =", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotEqualTo(BigDecimal value) {
            addCriterion("correction_factor <>", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorGreaterThan(BigDecimal value) {
            addCriterion("correction_factor >", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("correction_factor >=", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorLessThan(BigDecimal value) {
            addCriterion("correction_factor <", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("correction_factor <=", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorIn(List<BigDecimal> values) {
            addCriterion("correction_factor in", values, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotIn(List<BigDecimal> values) {
            addCriterion("correction_factor not in", values, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("correction_factor between", value1, value2, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("correction_factor not between", value1, value2, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorIsNull() {
            addCriterion("present_value_factor is null");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorIsNotNull() {
            addCriterion("present_value_factor is not null");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorEqualTo(BigDecimal value) {
            addCriterion("present_value_factor =", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotEqualTo(BigDecimal value) {
            addCriterion("present_value_factor <>", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorGreaterThan(BigDecimal value) {
            addCriterion("present_value_factor >", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("present_value_factor >=", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorLessThan(BigDecimal value) {
            addCriterion("present_value_factor <", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("present_value_factor <=", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorIn(List<BigDecimal> values) {
            addCriterion("present_value_factor in", values, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotIn(List<BigDecimal> values) {
            addCriterion("present_value_factor not in", values, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("present_value_factor between", value1, value2, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("present_value_factor not between", value1, value2, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andIncomePriceIsNull() {
            addCriterion("income_price is null");
            return (Criteria) this;
        }

        public Criteria andIncomePriceIsNotNull() {
            addCriterion("income_price is not null");
            return (Criteria) this;
        }

        public Criteria andIncomePriceEqualTo(BigDecimal value) {
            addCriterion("income_price =", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotEqualTo(BigDecimal value) {
            addCriterion("income_price <>", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceGreaterThan(BigDecimal value) {
            addCriterion("income_price >", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("income_price >=", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceLessThan(BigDecimal value) {
            addCriterion("income_price <", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("income_price <=", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceIn(List<BigDecimal> values) {
            addCriterion("income_price in", values, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotIn(List<BigDecimal> values) {
            addCriterion("income_price not in", values, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_price between", value1, value2, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_price not between", value1, value2, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
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