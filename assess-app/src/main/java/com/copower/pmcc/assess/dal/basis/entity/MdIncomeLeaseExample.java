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

        public Criteria andRentalsEqualTo(String value) {
            addCriterion("rentals =", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotEqualTo(String value) {
            addCriterion("rentals <>", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsGreaterThan(String value) {
            addCriterion("rentals >", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsGreaterThanOrEqualTo(String value) {
            addCriterion("rentals >=", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsLessThan(String value) {
            addCriterion("rentals <", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsLessThanOrEqualTo(String value) {
            addCriterion("rentals <=", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsLike(String value) {
            addCriterion("rentals like", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotLike(String value) {
            addCriterion("rentals not like", value, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsIn(List<String> values) {
            addCriterion("rentals in", values, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotIn(List<String> values) {
            addCriterion("rentals not in", values, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsBetween(String value1, String value2) {
            addCriterion("rentals between", value1, value2, "rentals");
            return (Criteria) this;
        }

        public Criteria andRentalsNotBetween(String value1, String value2) {
            addCriterion("rentals not between", value1, value2, "rentals");
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

        public Criteria andDepositEqualTo(String value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(String value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(String value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(String value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(String value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(String value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLike(String value) {
            addCriterion("deposit like", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotLike(String value) {
            addCriterion("deposit not like", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<String> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<String> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(String value1, String value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(String value1, String value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andInterestRateIsNull() {
            addCriterion("interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateIsNotNull() {
            addCriterion("interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateEqualTo(String value) {
            addCriterion("interest_rate =", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotEqualTo(String value) {
            addCriterion("interest_rate <>", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThan(String value) {
            addCriterion("interest_rate >", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThanOrEqualTo(String value) {
            addCriterion("interest_rate >=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThan(String value) {
            addCriterion("interest_rate <", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThanOrEqualTo(String value) {
            addCriterion("interest_rate <=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLike(String value) {
            addCriterion("interest_rate like", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotLike(String value) {
            addCriterion("interest_rate not like", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateIn(List<String> values) {
            addCriterion("interest_rate in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotIn(List<String> values) {
            addCriterion("interest_rate not in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateBetween(String value1, String value2) {
            addCriterion("interest_rate between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotBetween(String value1, String value2) {
            addCriterion("interest_rate not between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeIsNull() {
            addCriterion("interest_income is null");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeIsNotNull() {
            addCriterion("interest_income is not null");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeEqualTo(String value) {
            addCriterion("interest_income =", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeNotEqualTo(String value) {
            addCriterion("interest_income <>", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeGreaterThan(String value) {
            addCriterion("interest_income >", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeGreaterThanOrEqualTo(String value) {
            addCriterion("interest_income >=", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeLessThan(String value) {
            addCriterion("interest_income <", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeLessThanOrEqualTo(String value) {
            addCriterion("interest_income <=", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeLike(String value) {
            addCriterion("interest_income like", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeNotLike(String value) {
            addCriterion("interest_income not like", value, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeIn(List<String> values) {
            addCriterion("interest_income in", values, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeNotIn(List<String> values) {
            addCriterion("interest_income not in", values, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeBetween(String value1, String value2) {
            addCriterion("interest_income between", value1, value2, "interestIncome");
            return (Criteria) this;
        }

        public Criteria andInterestIncomeNotBetween(String value1, String value2) {
            addCriterion("interest_income not between", value1, value2, "interestIncome");
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

        public Criteria andOtherIncomeEqualTo(String value) {
            addCriterion("other_income =", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotEqualTo(String value) {
            addCriterion("other_income <>", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeGreaterThan(String value) {
            addCriterion("other_income >", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeGreaterThanOrEqualTo(String value) {
            addCriterion("other_income >=", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeLessThan(String value) {
            addCriterion("other_income <", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeLessThanOrEqualTo(String value) {
            addCriterion("other_income <=", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeLike(String value) {
            addCriterion("other_income like", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotLike(String value) {
            addCriterion("other_income not like", value, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeIn(List<String> values) {
            addCriterion("other_income in", values, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotIn(List<String> values) {
            addCriterion("other_income not in", values, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeBetween(String value1, String value2) {
            addCriterion("other_income between", value1, value2, "otherIncome");
            return (Criteria) this;
        }

        public Criteria andOtherIncomeNotBetween(String value1, String value2) {
            addCriterion("other_income not between", value1, value2, "otherIncome");
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

        public Criteria andInterestRateRemarkIsNull() {
            addCriterion("interest_rate_remark is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkIsNotNull() {
            addCriterion("interest_rate_remark is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkEqualTo(String value) {
            addCriterion("interest_rate_remark =", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkNotEqualTo(String value) {
            addCriterion("interest_rate_remark <>", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkGreaterThan(String value) {
            addCriterion("interest_rate_remark >", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("interest_rate_remark >=", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkLessThan(String value) {
            addCriterion("interest_rate_remark <", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkLessThanOrEqualTo(String value) {
            addCriterion("interest_rate_remark <=", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkLike(String value) {
            addCriterion("interest_rate_remark like", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkNotLike(String value) {
            addCriterion("interest_rate_remark not like", value, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkIn(List<String> values) {
            addCriterion("interest_rate_remark in", values, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkNotIn(List<String> values) {
            addCriterion("interest_rate_remark not in", values, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkBetween(String value1, String value2) {
            addCriterion("interest_rate_remark between", value1, value2, "interestRateRemark");
            return (Criteria) this;
        }

        public Criteria andInterestRateRemarkNotBetween(String value1, String value2) {
            addCriterion("interest_rate_remark not between", value1, value2, "interestRateRemark");
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

        public Criteria andManagementCostIsNull() {
            addCriterion("management_cost is null");
            return (Criteria) this;
        }

        public Criteria andManagementCostIsNotNull() {
            addCriterion("management_cost is not null");
            return (Criteria) this;
        }

        public Criteria andManagementCostEqualTo(String value) {
            addCriterion("management_cost =", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotEqualTo(String value) {
            addCriterion("management_cost <>", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostGreaterThan(String value) {
            addCriterion("management_cost >", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostGreaterThanOrEqualTo(String value) {
            addCriterion("management_cost >=", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLessThan(String value) {
            addCriterion("management_cost <", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLessThanOrEqualTo(String value) {
            addCriterion("management_cost <=", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLike(String value) {
            addCriterion("management_cost like", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotLike(String value) {
            addCriterion("management_cost not like", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostIn(List<String> values) {
            addCriterion("management_cost in", values, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotIn(List<String> values) {
            addCriterion("management_cost not in", values, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostBetween(String value1, String value2) {
            addCriterion("management_cost between", value1, value2, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotBetween(String value1, String value2) {
            addCriterion("management_cost not between", value1, value2, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIsNull() {
            addCriterion("management_cost_ratio is null");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIsNotNull() {
            addCriterion("management_cost_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioEqualTo(String value) {
            addCriterion("management_cost_ratio =", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotEqualTo(String value) {
            addCriterion("management_cost_ratio <>", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioGreaterThan(String value) {
            addCriterion("management_cost_ratio >", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioGreaterThanOrEqualTo(String value) {
            addCriterion("management_cost_ratio >=", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLessThan(String value) {
            addCriterion("management_cost_ratio <", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLessThanOrEqualTo(String value) {
            addCriterion("management_cost_ratio <=", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLike(String value) {
            addCriterion("management_cost_ratio like", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotLike(String value) {
            addCriterion("management_cost_ratio not like", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIn(List<String> values) {
            addCriterion("management_cost_ratio in", values, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotIn(List<String> values) {
            addCriterion("management_cost_ratio not in", values, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioBetween(String value1, String value2) {
            addCriterion("management_cost_ratio between", value1, value2, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotBetween(String value1, String value2) {
            addCriterion("management_cost_ratio not between", value1, value2, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkIsNull() {
            addCriterion("payment_remark is null");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkIsNotNull() {
            addCriterion("payment_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkEqualTo(String value) {
            addCriterion("payment_remark =", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkNotEqualTo(String value) {
            addCriterion("payment_remark <>", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkGreaterThan(String value) {
            addCriterion("payment_remark >", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("payment_remark >=", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkLessThan(String value) {
            addCriterion("payment_remark <", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkLessThanOrEqualTo(String value) {
            addCriterion("payment_remark <=", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkLike(String value) {
            addCriterion("payment_remark like", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkNotLike(String value) {
            addCriterion("payment_remark not like", value, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkIn(List<String> values) {
            addCriterion("payment_remark in", values, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkNotIn(List<String> values) {
            addCriterion("payment_remark not in", values, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkBetween(String value1, String value2) {
            addCriterion("payment_remark between", value1, value2, "paymentRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentRemarkNotBetween(String value1, String value2) {
            addCriterion("payment_remark not between", value1, value2, "paymentRemark");
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

        public Criteria andGrossIncomeEqualTo(String value) {
            addCriterion("gross_income =", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotEqualTo(String value) {
            addCriterion("gross_income <>", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeGreaterThan(String value) {
            addCriterion("gross_income >", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeGreaterThanOrEqualTo(String value) {
            addCriterion("gross_income >=", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeLessThan(String value) {
            addCriterion("gross_income <", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeLessThanOrEqualTo(String value) {
            addCriterion("gross_income <=", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeLike(String value) {
            addCriterion("gross_income like", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotLike(String value) {
            addCriterion("gross_income not like", value, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeIn(List<String> values) {
            addCriterion("gross_income in", values, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotIn(List<String> values) {
            addCriterion("gross_income not in", values, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeBetween(String value1, String value2) {
            addCriterion("gross_income between", value1, value2, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andGrossIncomeNotBetween(String value1, String value2) {
            addCriterion("gross_income not between", value1, value2, "grossIncome");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIsNull() {
            addCriterion("maintenance is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIsNotNull() {
            addCriterion("maintenance is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceEqualTo(String value) {
            addCriterion("maintenance =", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotEqualTo(String value) {
            addCriterion("maintenance <>", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceGreaterThan(String value) {
            addCriterion("maintenance >", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceGreaterThanOrEqualTo(String value) {
            addCriterion("maintenance >=", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLessThan(String value) {
            addCriterion("maintenance <", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLessThanOrEqualTo(String value) {
            addCriterion("maintenance <=", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLike(String value) {
            addCriterion("maintenance like", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotLike(String value) {
            addCriterion("maintenance not like", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIn(List<String> values) {
            addCriterion("maintenance in", values, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotIn(List<String> values) {
            addCriterion("maintenance not in", values, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceBetween(String value1, String value2) {
            addCriterion("maintenance between", value1, value2, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotBetween(String value1, String value2) {
            addCriterion("maintenance not between", value1, value2, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioIsNull() {
            addCriterion("maintenance_cost_ratio is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioIsNotNull() {
            addCriterion("maintenance_cost_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioEqualTo(String value) {
            addCriterion("maintenance_cost_ratio =", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotEqualTo(String value) {
            addCriterion("maintenance_cost_ratio <>", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioGreaterThan(String value) {
            addCriterion("maintenance_cost_ratio >", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioGreaterThanOrEqualTo(String value) {
            addCriterion("maintenance_cost_ratio >=", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioLessThan(String value) {
            addCriterion("maintenance_cost_ratio <", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioLessThanOrEqualTo(String value) {
            addCriterion("maintenance_cost_ratio <=", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioLike(String value) {
            addCriterion("maintenance_cost_ratio like", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotLike(String value) {
            addCriterion("maintenance_cost_ratio not like", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioIn(List<String> values) {
            addCriterion("maintenance_cost_ratio in", values, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotIn(List<String> values) {
            addCriterion("maintenance_cost_ratio not in", values, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioBetween(String value1, String value2) {
            addCriterion("maintenance_cost_ratio between", value1, value2, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotBetween(String value1, String value2) {
            addCriterion("maintenance_cost_ratio not between", value1, value2, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andReplacementValueIsNull() {
            addCriterion("replacement_value is null");
            return (Criteria) this;
        }

        public Criteria andReplacementValueIsNotNull() {
            addCriterion("replacement_value is not null");
            return (Criteria) this;
        }

        public Criteria andReplacementValueEqualTo(String value) {
            addCriterion("replacement_value =", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotEqualTo(String value) {
            addCriterion("replacement_value <>", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueGreaterThan(String value) {
            addCriterion("replacement_value >", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueGreaterThanOrEqualTo(String value) {
            addCriterion("replacement_value >=", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueLessThan(String value) {
            addCriterion("replacement_value <", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueLessThanOrEqualTo(String value) {
            addCriterion("replacement_value <=", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueLike(String value) {
            addCriterion("replacement_value like", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotLike(String value) {
            addCriterion("replacement_value not like", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueIn(List<String> values) {
            addCriterion("replacement_value in", values, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotIn(List<String> values) {
            addCriterion("replacement_value not in", values, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueBetween(String value1, String value2) {
            addCriterion("replacement_value between", value1, value2, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotBetween(String value1, String value2) {
            addCriterion("replacement_value not between", value1, value2, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andAdditionalIsNull() {
            addCriterion("additional is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalIsNotNull() {
            addCriterion("additional is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalEqualTo(String value) {
            addCriterion("additional =", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotEqualTo(String value) {
            addCriterion("additional <>", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalGreaterThan(String value) {
            addCriterion("additional >", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalGreaterThanOrEqualTo(String value) {
            addCriterion("additional >=", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLessThan(String value) {
            addCriterion("additional <", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLessThanOrEqualTo(String value) {
            addCriterion("additional <=", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLike(String value) {
            addCriterion("additional like", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotLike(String value) {
            addCriterion("additional not like", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalIn(List<String> values) {
            addCriterion("additional in", values, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotIn(List<String> values) {
            addCriterion("additional not in", values, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalBetween(String value1, String value2) {
            addCriterion("additional between", value1, value2, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotBetween(String value1, String value2) {
            addCriterion("additional not between", value1, value2, "additional");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(String value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(String value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(String value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(String value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(String value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(String value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLike(String value) {
            addCriterion("tax_rate like", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotLike(String value) {
            addCriterion("tax_rate not like", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<String> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<String> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(String value1, String value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(String value1, String value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumIsNull() {
            addCriterion("insurance_premium is null");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumIsNotNull() {
            addCriterion("insurance_premium is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumEqualTo(String value) {
            addCriterion("insurance_premium =", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotEqualTo(String value) {
            addCriterion("insurance_premium <>", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumGreaterThan(String value) {
            addCriterion("insurance_premium >", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_premium >=", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumLessThan(String value) {
            addCriterion("insurance_premium <", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumLessThanOrEqualTo(String value) {
            addCriterion("insurance_premium <=", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumLike(String value) {
            addCriterion("insurance_premium like", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotLike(String value) {
            addCriterion("insurance_premium not like", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumIn(List<String> values) {
            addCriterion("insurance_premium in", values, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotIn(List<String> values) {
            addCriterion("insurance_premium not in", values, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumBetween(String value1, String value2) {
            addCriterion("insurance_premium between", value1, value2, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotBetween(String value1, String value2) {
            addCriterion("insurance_premium not between", value1, value2, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateIsNull() {
            addCriterion("insurance_rate is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateIsNotNull() {
            addCriterion("insurance_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateEqualTo(String value) {
            addCriterion("insurance_rate =", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotEqualTo(String value) {
            addCriterion("insurance_rate <>", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateGreaterThan(String value) {
            addCriterion("insurance_rate >", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_rate >=", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateLessThan(String value) {
            addCriterion("insurance_rate <", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateLessThanOrEqualTo(String value) {
            addCriterion("insurance_rate <=", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateLike(String value) {
            addCriterion("insurance_rate like", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotLike(String value) {
            addCriterion("insurance_rate not like", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateIn(List<String> values) {
            addCriterion("insurance_rate in", values, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotIn(List<String> values) {
            addCriterion("insurance_rate not in", values, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateBetween(String value1, String value2) {
            addCriterion("insurance_rate between", value1, value2, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotBetween(String value1, String value2) {
            addCriterion("insurance_rate not between", value1, value2, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxIsNull() {
            addCriterion("land_use_tax is null");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxIsNotNull() {
            addCriterion("land_use_tax is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxEqualTo(String value) {
            addCriterion("land_use_tax =", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotEqualTo(String value) {
            addCriterion("land_use_tax <>", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxGreaterThan(String value) {
            addCriterion("land_use_tax >", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_tax >=", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxLessThan(String value) {
            addCriterion("land_use_tax <", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxLessThanOrEqualTo(String value) {
            addCriterion("land_use_tax <=", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxLike(String value) {
            addCriterion("land_use_tax like", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotLike(String value) {
            addCriterion("land_use_tax not like", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxIn(List<String> values) {
            addCriterion("land_use_tax in", values, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotIn(List<String> values) {
            addCriterion("land_use_tax not in", values, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxBetween(String value1, String value2) {
            addCriterion("land_use_tax between", value1, value2, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotBetween(String value1, String value2) {
            addCriterion("land_use_tax not between", value1, value2, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterIsNull() {
            addCriterion("usage_tax_parameter is null");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterIsNotNull() {
            addCriterion("usage_tax_parameter is not null");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterEqualTo(String value) {
            addCriterion("usage_tax_parameter =", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterNotEqualTo(String value) {
            addCriterion("usage_tax_parameter <>", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterGreaterThan(String value) {
            addCriterion("usage_tax_parameter >", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterGreaterThanOrEqualTo(String value) {
            addCriterion("usage_tax_parameter >=", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterLessThan(String value) {
            addCriterion("usage_tax_parameter <", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterLessThanOrEqualTo(String value) {
            addCriterion("usage_tax_parameter <=", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterLike(String value) {
            addCriterion("usage_tax_parameter like", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterNotLike(String value) {
            addCriterion("usage_tax_parameter not like", value, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterIn(List<String> values) {
            addCriterion("usage_tax_parameter in", values, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterNotIn(List<String> values) {
            addCriterion("usage_tax_parameter not in", values, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterBetween(String value1, String value2) {
            addCriterion("usage_tax_parameter between", value1, value2, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUsageTaxParameterNotBetween(String value1, String value2) {
            addCriterion("usage_tax_parameter not between", value1, value2, "usageTaxParameter");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseIsNull() {
            addCriterion("operating_expense is null");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseIsNotNull() {
            addCriterion("operating_expense is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseEqualTo(String value) {
            addCriterion("operating_expense =", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseNotEqualTo(String value) {
            addCriterion("operating_expense <>", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseGreaterThan(String value) {
            addCriterion("operating_expense >", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseGreaterThanOrEqualTo(String value) {
            addCriterion("operating_expense >=", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseLessThan(String value) {
            addCriterion("operating_expense <", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseLessThanOrEqualTo(String value) {
            addCriterion("operating_expense <=", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseLike(String value) {
            addCriterion("operating_expense like", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseNotLike(String value) {
            addCriterion("operating_expense not like", value, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseIn(List<String> values) {
            addCriterion("operating_expense in", values, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseNotIn(List<String> values) {
            addCriterion("operating_expense not in", values, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseBetween(String value1, String value2) {
            addCriterion("operating_expense between", value1, value2, "operatingExpense");
            return (Criteria) this;
        }

        public Criteria andOperatingExpenseNotBetween(String value1, String value2) {
            addCriterion("operating_expense not between", value1, value2, "operatingExpense");
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

        public Criteria andIncomePriceIsNull() {
            addCriterion("income_price is null");
            return (Criteria) this;
        }

        public Criteria andIncomePriceIsNotNull() {
            addCriterion("income_price is not null");
            return (Criteria) this;
        }

        public Criteria andIncomePriceEqualTo(String value) {
            addCriterion("income_price =", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotEqualTo(String value) {
            addCriterion("income_price <>", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceGreaterThan(String value) {
            addCriterion("income_price >", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceGreaterThanOrEqualTo(String value) {
            addCriterion("income_price >=", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceLessThan(String value) {
            addCriterion("income_price <", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceLessThanOrEqualTo(String value) {
            addCriterion("income_price <=", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceLike(String value) {
            addCriterion("income_price like", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotLike(String value) {
            addCriterion("income_price not like", value, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceIn(List<String> values) {
            addCriterion("income_price in", values, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotIn(List<String> values) {
            addCriterion("income_price not in", values, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceBetween(String value1, String value2) {
            addCriterion("income_price between", value1, value2, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andIncomePriceNotBetween(String value1, String value2) {
            addCriterion("income_price not between", value1, value2, "incomePrice");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateIsNull() {
            addCriterion("capitalization_rate is null");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateIsNotNull() {
            addCriterion("capitalization_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateEqualTo(String value) {
            addCriterion("capitalization_rate =", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateNotEqualTo(String value) {
            addCriterion("capitalization_rate <>", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateGreaterThan(String value) {
            addCriterion("capitalization_rate >", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateGreaterThanOrEqualTo(String value) {
            addCriterion("capitalization_rate >=", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateLessThan(String value) {
            addCriterion("capitalization_rate <", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateLessThanOrEqualTo(String value) {
            addCriterion("capitalization_rate <=", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateLike(String value) {
            addCriterion("capitalization_rate like", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateNotLike(String value) {
            addCriterion("capitalization_rate not like", value, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateIn(List<String> values) {
            addCriterion("capitalization_rate in", values, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateNotIn(List<String> values) {
            addCriterion("capitalization_rate not in", values, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateBetween(String value1, String value2) {
            addCriterion("capitalization_rate between", value1, value2, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andCapitalizationRateNotBetween(String value1, String value2) {
            addCriterion("capitalization_rate not between", value1, value2, "capitalizationRate");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodIsNull() {
            addCriterion("return_period is null");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodIsNotNull() {
            addCriterion("return_period is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodEqualTo(String value) {
            addCriterion("return_period =", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodNotEqualTo(String value) {
            addCriterion("return_period <>", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodGreaterThan(String value) {
            addCriterion("return_period >", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("return_period >=", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodLessThan(String value) {
            addCriterion("return_period <", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodLessThanOrEqualTo(String value) {
            addCriterion("return_period <=", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodLike(String value) {
            addCriterion("return_period like", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodNotLike(String value) {
            addCriterion("return_period not like", value, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodIn(List<String> values) {
            addCriterion("return_period in", values, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodNotIn(List<String> values) {
            addCriterion("return_period not in", values, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodBetween(String value1, String value2) {
            addCriterion("return_period between", value1, value2, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andReturnPeriodNotBetween(String value1, String value2) {
            addCriterion("return_period not between", value1, value2, "returnPeriod");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateIsNull() {
            addCriterion("lease_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateIsNotNull() {
            addCriterion("lease_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateEqualTo(Date value) {
            addCriterion("lease_begin_date =", value, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateNotEqualTo(Date value) {
            addCriterion("lease_begin_date <>", value, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateGreaterThan(Date value) {
            addCriterion("lease_begin_date >", value, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("lease_begin_date >=", value, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateLessThan(Date value) {
            addCriterion("lease_begin_date <", value, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("lease_begin_date <=", value, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateIn(List<Date> values) {
            addCriterion("lease_begin_date in", values, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateNotIn(List<Date> values) {
            addCriterion("lease_begin_date not in", values, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateBetween(Date value1, Date value2) {
            addCriterion("lease_begin_date between", value1, value2, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("lease_begin_date not between", value1, value2, "leaseBeginDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateIsNull() {
            addCriterion("lease_end_date is null");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateIsNotNull() {
            addCriterion("lease_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateEqualTo(Date value) {
            addCriterion("lease_end_date =", value, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateNotEqualTo(Date value) {
            addCriterion("lease_end_date <>", value, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateGreaterThan(Date value) {
            addCriterion("lease_end_date >", value, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("lease_end_date >=", value, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateLessThan(Date value) {
            addCriterion("lease_end_date <", value, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("lease_end_date <=", value, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateIn(List<Date> values) {
            addCriterion("lease_end_date in", values, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateNotIn(List<Date> values) {
            addCriterion("lease_end_date not in", values, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateBetween(Date value1, Date value2) {
            addCriterion("lease_end_date between", value1, value2, "leaseEndDate");
            return (Criteria) this;
        }

        public Criteria andLeaseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("lease_end_date not between", value1, value2, "leaseEndDate");
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

        public Criteria andRentalGrowthRateEqualTo(String value) {
            addCriterion("rental_growth_rate =", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotEqualTo(String value) {
            addCriterion("rental_growth_rate <>", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateGreaterThan(String value) {
            addCriterion("rental_growth_rate >", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateGreaterThanOrEqualTo(String value) {
            addCriterion("rental_growth_rate >=", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateLessThan(String value) {
            addCriterion("rental_growth_rate <", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateLessThanOrEqualTo(String value) {
            addCriterion("rental_growth_rate <=", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateLike(String value) {
            addCriterion("rental_growth_rate like", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotLike(String value) {
            addCriterion("rental_growth_rate not like", value, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateIn(List<String> values) {
            addCriterion("rental_growth_rate in", values, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotIn(List<String> values) {
            addCriterion("rental_growth_rate not in", values, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateBetween(String value1, String value2) {
            addCriterion("rental_growth_rate between", value1, value2, "rentalGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentalGrowthRateNotBetween(String value1, String value2) {
            addCriterion("rental_growth_rate not between", value1, value2, "rentalGrowthRate");
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

        public Criteria andCorrectionFactorEqualTo(String value) {
            addCriterion("correction_factor =", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotEqualTo(String value) {
            addCriterion("correction_factor <>", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorGreaterThan(String value) {
            addCriterion("correction_factor >", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorGreaterThanOrEqualTo(String value) {
            addCriterion("correction_factor >=", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorLessThan(String value) {
            addCriterion("correction_factor <", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorLessThanOrEqualTo(String value) {
            addCriterion("correction_factor <=", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorLike(String value) {
            addCriterion("correction_factor like", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotLike(String value) {
            addCriterion("correction_factor not like", value, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorIn(List<String> values) {
            addCriterion("correction_factor in", values, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotIn(List<String> values) {
            addCriterion("correction_factor not in", values, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorBetween(String value1, String value2) {
            addCriterion("correction_factor between", value1, value2, "correctionFactor");
            return (Criteria) this;
        }

        public Criteria andCorrectionFactorNotBetween(String value1, String value2) {
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

        public Criteria andPresentValueFactorEqualTo(String value) {
            addCriterion("present_value_factor =", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotEqualTo(String value) {
            addCriterion("present_value_factor <>", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorGreaterThan(String value) {
            addCriterion("present_value_factor >", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorGreaterThanOrEqualTo(String value) {
            addCriterion("present_value_factor >=", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorLessThan(String value) {
            addCriterion("present_value_factor <", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorLessThanOrEqualTo(String value) {
            addCriterion("present_value_factor <=", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorLike(String value) {
            addCriterion("present_value_factor like", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotLike(String value) {
            addCriterion("present_value_factor not like", value, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorIn(List<String> values) {
            addCriterion("present_value_factor in", values, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotIn(List<String> values) {
            addCriterion("present_value_factor not in", values, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorBetween(String value1, String value2) {
            addCriterion("present_value_factor between", value1, value2, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andPresentValueFactorNotBetween(String value1, String value2) {
            addCriterion("present_value_factor not between", value1, value2, "presentValueFactor");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNull() {
            addCriterion("floor_area is null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNotNull() {
            addCriterion("floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaEqualTo(BigDecimal value) {
            addCriterion("floor_area =", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotEqualTo(BigDecimal value) {
            addCriterion("floor_area <>", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThan(BigDecimal value) {
            addCriterion("floor_area >", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area >=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThan(BigDecimal value) {
            addCriterion("floor_area <", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area <=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIn(List<BigDecimal> values) {
            addCriterion("floor_area in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotIn(List<BigDecimal> values) {
            addCriterion("floor_area not in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area not between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNull() {
            addCriterion("land_area is null");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNotNull() {
            addCriterion("land_area is not null");
            return (Criteria) this;
        }

        public Criteria andLandAreaEqualTo(BigDecimal value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(BigDecimal value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(BigDecimal value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<BigDecimal> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(BigDecimal value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(BigDecimal value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<BigDecimal> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
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