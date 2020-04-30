package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLoanBenchmarkInterestRateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataLoanBenchmarkInterestRateExample() {
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

        public Criteria andLessThanSixMonthLoanTermTaxIsNull() {
            addCriterion("less_than_six_month_loan_term_tax is null");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxIsNotNull() {
            addCriterion("less_than_six_month_loan_term_tax is not null");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxEqualTo(BigDecimal value) {
            addCriterion("less_than_six_month_loan_term_tax =", value, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxNotEqualTo(BigDecimal value) {
            addCriterion("less_than_six_month_loan_term_tax <>", value, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxGreaterThan(BigDecimal value) {
            addCriterion("less_than_six_month_loan_term_tax >", value, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("less_than_six_month_loan_term_tax >=", value, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxLessThan(BigDecimal value) {
            addCriterion("less_than_six_month_loan_term_tax <", value, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("less_than_six_month_loan_term_tax <=", value, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxIn(List<BigDecimal> values) {
            addCriterion("less_than_six_month_loan_term_tax in", values, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxNotIn(List<BigDecimal> values) {
            addCriterion("less_than_six_month_loan_term_tax not in", values, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("less_than_six_month_loan_term_tax between", value1, value2, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andLessThanSixMonthLoanTermTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("less_than_six_month_loan_term_tax not between", value1, value2, "lessThanSixMonthLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxIsNull() {
            addCriterion("between_six_month_and_one_year_loan_term_tax is null");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxIsNotNull() {
            addCriterion("between_six_month_and_one_year_loan_term_tax is not null");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxEqualTo(BigDecimal value) {
            addCriterion("between_six_month_and_one_year_loan_term_tax =", value, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxNotEqualTo(BigDecimal value) {
            addCriterion("between_six_month_and_one_year_loan_term_tax <>", value, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxGreaterThan(BigDecimal value) {
            addCriterion("between_six_month_and_one_year_loan_term_tax >", value, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("between_six_month_and_one_year_loan_term_tax >=", value, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxLessThan(BigDecimal value) {
            addCriterion("between_six_month_and_one_year_loan_term_tax <", value, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("between_six_month_and_one_year_loan_term_tax <=", value, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxIn(List<BigDecimal> values) {
            addCriterion("between_six_month_and_one_year_loan_term_tax in", values, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxNotIn(List<BigDecimal> values) {
            addCriterion("between_six_month_and_one_year_loan_term_tax not in", values, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("between_six_month_and_one_year_loan_term_tax between", value1, value2, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenSixMonthAndOneYearLoanTermTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("between_six_month_and_one_year_loan_term_tax not between", value1, value2, "betweenSixMonthAndOneYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxIsNull() {
            addCriterion("between_one_and_three_year_loan_term_tax is null");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxIsNotNull() {
            addCriterion("between_one_and_three_year_loan_term_tax is not null");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxEqualTo(BigDecimal value) {
            addCriterion("between_one_and_three_year_loan_term_tax =", value, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxNotEqualTo(BigDecimal value) {
            addCriterion("between_one_and_three_year_loan_term_tax <>", value, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxGreaterThan(BigDecimal value) {
            addCriterion("between_one_and_three_year_loan_term_tax >", value, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("between_one_and_three_year_loan_term_tax >=", value, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxLessThan(BigDecimal value) {
            addCriterion("between_one_and_three_year_loan_term_tax <", value, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("between_one_and_three_year_loan_term_tax <=", value, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxIn(List<BigDecimal> values) {
            addCriterion("between_one_and_three_year_loan_term_tax in", values, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxNotIn(List<BigDecimal> values) {
            addCriterion("between_one_and_three_year_loan_term_tax not in", values, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("between_one_and_three_year_loan_term_tax between", value1, value2, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenOneAndThreeYearLoanTermTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("between_one_and_three_year_loan_term_tax not between", value1, value2, "betweenOneAndThreeYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxIsNull() {
            addCriterion("between_three_and_five_year_loan_term_tax is null");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxIsNotNull() {
            addCriterion("between_three_and_five_year_loan_term_tax is not null");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxEqualTo(BigDecimal value) {
            addCriterion("between_three_and_five_year_loan_term_tax =", value, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxNotEqualTo(BigDecimal value) {
            addCriterion("between_three_and_five_year_loan_term_tax <>", value, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxGreaterThan(BigDecimal value) {
            addCriterion("between_three_and_five_year_loan_term_tax >", value, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("between_three_and_five_year_loan_term_tax >=", value, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxLessThan(BigDecimal value) {
            addCriterion("between_three_and_five_year_loan_term_tax <", value, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("between_three_and_five_year_loan_term_tax <=", value, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxIn(List<BigDecimal> values) {
            addCriterion("between_three_and_five_year_loan_term_tax in", values, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxNotIn(List<BigDecimal> values) {
            addCriterion("between_three_and_five_year_loan_term_tax not in", values, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("between_three_and_five_year_loan_term_tax between", value1, value2, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andBetweenThreeAndFiveYearLoanTermTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("between_three_and_five_year_loan_term_tax not between", value1, value2, "betweenThreeAndFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxIsNull() {
            addCriterion("more_than_five_year_loan_term_tax is null");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxIsNotNull() {
            addCriterion("more_than_five_year_loan_term_tax is not null");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxEqualTo(BigDecimal value) {
            addCriterion("more_than_five_year_loan_term_tax =", value, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxNotEqualTo(BigDecimal value) {
            addCriterion("more_than_five_year_loan_term_tax <>", value, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxGreaterThan(BigDecimal value) {
            addCriterion("more_than_five_year_loan_term_tax >", value, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("more_than_five_year_loan_term_tax >=", value, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxLessThan(BigDecimal value) {
            addCriterion("more_than_five_year_loan_term_tax <", value, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("more_than_five_year_loan_term_tax <=", value, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxIn(List<BigDecimal> values) {
            addCriterion("more_than_five_year_loan_term_tax in", values, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxNotIn(List<BigDecimal> values) {
            addCriterion("more_than_five_year_loan_term_tax not in", values, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("more_than_five_year_loan_term_tax between", value1, value2, "moreThanFiveYearLoanTermTax");
            return (Criteria) this;
        }

        public Criteria andMoreThanFiveYearLoanTermTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("more_than_five_year_loan_term_tax not between", value1, value2, "moreThanFiveYearLoanTermTax");
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