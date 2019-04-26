package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeLeaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeLeaseExample() {
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

        public Criteria andSectionIdIsNull() {
            addCriterion("section_id is null");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNotNull() {
            addCriterion("section_id is not null");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualTo(Integer value) {
            addCriterion("section_id =", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualTo(Integer value) {
            addCriterion("section_id <>", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThan(Integer value) {
            addCriterion("section_id >", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("section_id >=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThan(Integer value) {
            addCriterion("section_id <", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("section_id <=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdIn(List<Integer> values) {
            addCriterion("section_id in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotIn(List<Integer> values) {
            addCriterion("section_id not in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdBetween(Integer value1, Integer value2) {
            addCriterion("section_id between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("section_id not between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andMcIdIsNull() {
            addCriterion("mc_id is null");
            return (Criteria) this;
        }

        public Criteria andMcIdIsNotNull() {
            addCriterion("mc_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcIdEqualTo(Integer value) {
            addCriterion("mc_id =", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotEqualTo(Integer value) {
            addCriterion("mc_id <>", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThan(Integer value) {
            addCriterion("mc_id >", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_id >=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThan(Integer value) {
            addCriterion("mc_id <", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_id <=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdIn(List<Integer> values) {
            addCriterion("mc_id in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotIn(List<Integer> values) {
            addCriterion("mc_id not in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_id between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_id not between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeIsNull() {
            addCriterion("rental_income is null");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeIsNotNull() {
            addCriterion("rental_income is not null");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeEqualTo(BigDecimal value) {
            addCriterion("rental_income =", value, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeNotEqualTo(BigDecimal value) {
            addCriterion("rental_income <>", value, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeGreaterThan(BigDecimal value) {
            addCriterion("rental_income >", value, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rental_income >=", value, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeLessThan(BigDecimal value) {
            addCriterion("rental_income <", value, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rental_income <=", value, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeIn(List<BigDecimal> values) {
            addCriterion("rental_income in", values, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeNotIn(List<BigDecimal> values) {
            addCriterion("rental_income not in", values, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rental_income between", value1, value2, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rental_income not between", value1, value2, "rentalIncome");
            return (Criteria) this;
        }

        public Criteria andRentalsIsNull() {
            addCriterion("rentals is null");
            return (Criteria) this;
        }

        public Criteria andRentalsIsNotNull() {
            addCriterion("rentals is not null");
            return (Criteria) this;
        }

        public Criteria andRentalsEqualTo(BigDecimal value) {
            addCriterion("rentals =", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotEqualTo(BigDecimal value) {
            addCriterion("rentals <>", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsGreaterThan(BigDecimal value) {
            addCriterion("rentals >", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rentals >=", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsLessThan(BigDecimal value) {
            addCriterion("rentals <", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rentals <=", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsIn(List<BigDecimal> values) {
            addCriterion("rentals in", values, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotIn(List<BigDecimal> values) {
            addCriterion("rentals not in", values, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rentals between", value1, value2, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rentals not between", value1, value2, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkIsNull() {
            addCriterion("rentals_remark is null");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkIsNotNull() {
            addCriterion("rentals_remark is not null");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkEqualTo(String value) {
            addCriterion("rentals_remark =", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkNotEqualTo(String value) {
            addCriterion("rentals_remark <>", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkGreaterThan(String value) {
            addCriterion("rentals_remark >", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("rentals_remark >=", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkLessThan(String value) {
            addCriterion("rentals_remark <", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkLessThanOrEqualTo(String value) {
            addCriterion("rentals_remark <=", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkLike(String value) {
            addCriterion("rentals_remark like", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkNotLike(String value) {
            addCriterion("rentals_remark not like", value, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkIn(List<String> values) {
            addCriterion("rentals_remark in", values, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkNotIn(List<String> values) {
            addCriterion("rentals_remark not in", values, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkBetween(String value1, String value2) {
            addCriterion("rentals_remark between", value1, value2, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andRentalsRemarkNotBetween(String value1, String value2) {
            addCriterion("rentals_remark not between", value1, value2, "rentalsRemark");
            return (Criteria) this;
        }

        public Criteria andMonthNumberIsNull() {
            addCriterion("month_number is null");
            return (Criteria) this;
        }

        public Criteria andMonthNumberIsNotNull() {
            addCriterion("month_number is not null");
            return (Criteria) this;
        }

        public Criteria andMonthNumberEqualTo(Integer value) {
            addCriterion("month_number =", value, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberNotEqualTo(Integer value) {
            addCriterion("month_number <>", value, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberGreaterThan(Integer value) {
            addCriterion("month_number >", value, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("month_number >=", value, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberLessThan(Integer value) {
            addCriterion("month_number <", value, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberLessThanOrEqualTo(Integer value) {
            addCriterion("month_number <=", value, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberIn(List<Integer> values) {
            addCriterion("month_number in", values, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberNotIn(List<Integer> values) {
            addCriterion("month_number not in", values, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberBetween(Integer value1, Integer value2) {
            addCriterion("month_number between", value1, value2, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andMonthNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("month_number not between", value1, value2, "monthNumber");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkIsNull() {
            addCriterion("deposit_remark is null");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkIsNotNull() {
            addCriterion("deposit_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkEqualTo(String value) {
            addCriterion("deposit_remark =", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkNotEqualTo(String value) {
            addCriterion("deposit_remark <>", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkGreaterThan(String value) {
            addCriterion("deposit_remark >", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_remark >=", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkLessThan(String value) {
            addCriterion("deposit_remark <", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkLessThanOrEqualTo(String value) {
            addCriterion("deposit_remark <=", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkLike(String value) {
            addCriterion("deposit_remark like", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkNotLike(String value) {
            addCriterion("deposit_remark not like", value, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkIn(List<String> values) {
            addCriterion("deposit_remark in", values, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkNotIn(List<String> values) {
            addCriterion("deposit_remark not in", values, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkBetween(String value1, String value2) {
            addCriterion("deposit_remark between", value1, value2, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRemarkNotBetween(String value1, String value2) {
            addCriterion("deposit_remark not between", value1, value2, "depositRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateIsNull() {
            addCriterion("deposit_rate is null");
            return (Criteria) this;
        }

        public Criteria andDepositRateIsNotNull() {
            addCriterion("deposit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDepositRateEqualTo(BigDecimal value) {
            addCriterion("deposit_rate =", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotEqualTo(BigDecimal value) {
            addCriterion("deposit_rate <>", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateGreaterThan(BigDecimal value) {
            addCriterion("deposit_rate >", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_rate >=", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateLessThan(BigDecimal value) {
            addCriterion("deposit_rate <", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_rate <=", value, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateIn(List<BigDecimal> values) {
            addCriterion("deposit_rate in", values, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotIn(List<BigDecimal> values) {
            addCriterion("deposit_rate not in", values, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_rate between", value1, value2, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_rate not between", value1, value2, "depositRate");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkIsNull() {
            addCriterion("deposit_rate_remark is null");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkIsNotNull() {
            addCriterion("deposit_rate_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkEqualTo(String value) {
            addCriterion("deposit_rate_remark =", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkNotEqualTo(String value) {
            addCriterion("deposit_rate_remark <>", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkGreaterThan(String value) {
            addCriterion("deposit_rate_remark >", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_rate_remark >=", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkLessThan(String value) {
            addCriterion("deposit_rate_remark <", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkLessThanOrEqualTo(String value) {
            addCriterion("deposit_rate_remark <=", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkLike(String value) {
            addCriterion("deposit_rate_remark like", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkNotLike(String value) {
            addCriterion("deposit_rate_remark not like", value, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkIn(List<String> values) {
            addCriterion("deposit_rate_remark in", values, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkNotIn(List<String> values) {
            addCriterion("deposit_rate_remark not in", values, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkBetween(String value1, String value2) {
            addCriterion("deposit_rate_remark between", value1, value2, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andDepositRateRemarkNotBetween(String value1, String value2) {
            addCriterion("deposit_rate_remark not between", value1, value2, "depositRateRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeIsNull() {
            addCriterion("other_income is null");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeIsNotNull() {
            addCriterion("other_income is not null");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeEqualTo(BigDecimal value) {
            addCriterion("other_income =", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotEqualTo(BigDecimal value) {
            addCriterion("other_income <>", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeGreaterThan(BigDecimal value) {
            addCriterion("other_income >", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_income >=", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeLessThan(BigDecimal value) {
            addCriterion("other_income <", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_income <=", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeIn(List<BigDecimal> values) {
            addCriterion("other_income in", values, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotIn(List<BigDecimal> values) {
            addCriterion("other_income not in", values, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_income between", value1, value2, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_income not between", value1, value2, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkIsNull() {
            addCriterion("other_income_remark is null");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkIsNotNull() {
            addCriterion("other_income_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkEqualTo(String value) {
            addCriterion("other_income_remark =", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkNotEqualTo(String value) {
            addCriterion("other_income_remark <>", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkGreaterThan(String value) {
            addCriterion("other_income_remark >", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("other_income_remark >=", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkLessThan(String value) {
            addCriterion("other_income_remark <", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkLessThanOrEqualTo(String value) {
            addCriterion("other_income_remark <=", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkLike(String value) {
            addCriterion("other_income_remark like", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkNotLike(String value) {
            addCriterion("other_income_remark not like", value, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkIn(List<String> values) {
            addCriterion("other_income_remark in", values, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkNotIn(List<String> values) {
            addCriterion("other_income_remark not in", values, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkBetween(String value1, String value2) {
            addCriterion("other_income_remark between", value1, value2, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeRemarkNotBetween(String value1, String value2) {
            addCriterion("other_income_remark not between", value1, value2, "otherIncomeRemark");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeIsNull() {
            addCriterion("gross_income is null");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeIsNotNull() {
            addCriterion("gross_income is not null");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeEqualTo(BigDecimal value) {
            addCriterion("gross_income =", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotEqualTo(BigDecimal value) {
            addCriterion("gross_income <>", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeGreaterThan(BigDecimal value) {
            addCriterion("gross_income >", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("gross_income >=", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeLessThan(BigDecimal value) {
            addCriterion("gross_income <", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("gross_income <=", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeIn(List<BigDecimal> values) {
            addCriterion("gross_income in", values, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotIn(List<BigDecimal> values) {
            addCriterion("gross_income not in", values, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gross_income between", value1, value2, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("gross_income not between", value1, value2, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureIsNull() {
            addCriterion("additional_capture is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureIsNotNull() {
            addCriterion("additional_capture is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureEqualTo(String value) {
            addCriterion("additional_capture =", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureNotEqualTo(String value) {
            addCriterion("additional_capture <>", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureGreaterThan(String value) {
            addCriterion("additional_capture >", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureGreaterThanOrEqualTo(String value) {
            addCriterion("additional_capture >=", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureLessThan(String value) {
            addCriterion("additional_capture <", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureLessThanOrEqualTo(String value) {
            addCriterion("additional_capture <=", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureLike(String value) {
            addCriterion("additional_capture like", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureNotLike(String value) {
            addCriterion("additional_capture not like", value, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureIn(List<String> values) {
            addCriterion("additional_capture in", values, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureNotIn(List<String> values) {
            addCriterion("additional_capture not in", values, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureBetween(String value1, String value2) {
            addCriterion("additional_capture between", value1, value2, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureNotBetween(String value1, String value2) {
            addCriterion("additional_capture not between", value1, value2, "additionalCapture");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkIsNull() {
            addCriterion("additional_capture_remark is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkIsNotNull() {
            addCriterion("additional_capture_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkEqualTo(String value) {
            addCriterion("additional_capture_remark =", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkNotEqualTo(String value) {
            addCriterion("additional_capture_remark <>", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkGreaterThan(String value) {
            addCriterion("additional_capture_remark >", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("additional_capture_remark >=", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkLessThan(String value) {
            addCriterion("additional_capture_remark <", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkLessThanOrEqualTo(String value) {
            addCriterion("additional_capture_remark <=", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkLike(String value) {
            addCriterion("additional_capture_remark like", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkNotLike(String value) {
            addCriterion("additional_capture_remark not like", value, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkIn(List<String> values) {
            addCriterion("additional_capture_remark in", values, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkNotIn(List<String> values) {
            addCriterion("additional_capture_remark not in", values, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkBetween(String value1, String value2) {
            addCriterion("additional_capture_remark between", value1, value2, "additionalCaptureRemark");
            return (Criteria) this;
        }

        public Criteria andAdditionalCaptureRemarkNotBetween(String value1, String value2) {
            addCriterion("additional_capture_remark not between", value1, value2, "additionalCaptureRemark");
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