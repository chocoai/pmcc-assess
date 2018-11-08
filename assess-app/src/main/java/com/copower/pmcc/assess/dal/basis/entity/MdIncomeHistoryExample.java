package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeHistoryExample() {
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

        public Criteria andForecastAnalyseIdIsNull() {
            addCriterion("forecast_analyse_id is null");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdIsNotNull() {
            addCriterion("forecast_analyse_id is not null");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdEqualTo(Integer value) {
            addCriterion("forecast_analyse_id =", value, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdNotEqualTo(Integer value) {
            addCriterion("forecast_analyse_id <>", value, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdGreaterThan(Integer value) {
            addCriterion("forecast_analyse_id >", value, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("forecast_analyse_id >=", value, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdLessThan(Integer value) {
            addCriterion("forecast_analyse_id <", value, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdLessThanOrEqualTo(Integer value) {
            addCriterion("forecast_analyse_id <=", value, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdIn(List<Integer> values) {
            addCriterion("forecast_analyse_id in", values, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdNotIn(List<Integer> values) {
            addCriterion("forecast_analyse_id not in", values, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdBetween(Integer value1, Integer value2) {
            addCriterion("forecast_analyse_id between", value1, value2, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andForecastAnalyseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("forecast_analyse_id not between", value1, value2, "forecastAnalyseId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNull() {
            addCriterion("form_type is null");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNotNull() {
            addCriterion("form_type is not null");
            return (Criteria) this;
        }

        public Criteria andFormTypeEqualTo(Integer value) {
            addCriterion("form_type =", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotEqualTo(Integer value) {
            addCriterion("form_type <>", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThan(Integer value) {
            addCriterion("form_type >", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("form_type >=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThan(Integer value) {
            addCriterion("form_type <", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThanOrEqualTo(Integer value) {
            addCriterion("form_type <=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeIn(List<Integer> values) {
            addCriterion("form_type in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotIn(List<Integer> values) {
            addCriterion("form_type not in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeBetween(Integer value1, Integer value2) {
            addCriterion("form_type between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("form_type not between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(Integer value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(Integer value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(Integer value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(Integer value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(Integer value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<Integer> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<Integer> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(Integer value1, Integer value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectIsNull() {
            addCriterion("accounting_subject is null");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectIsNotNull() {
            addCriterion("accounting_subject is not null");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectEqualTo(Integer value) {
            addCriterion("accounting_subject =", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectNotEqualTo(Integer value) {
            addCriterion("accounting_subject <>", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectGreaterThan(Integer value) {
            addCriterion("accounting_subject >", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectGreaterThanOrEqualTo(Integer value) {
            addCriterion("accounting_subject >=", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectLessThan(Integer value) {
            addCriterion("accounting_subject <", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectLessThanOrEqualTo(Integer value) {
            addCriterion("accounting_subject <=", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectIn(List<Integer> values) {
            addCriterion("accounting_subject in", values, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectNotIn(List<Integer> values) {
            addCriterion("accounting_subject not in", values, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectBetween(Integer value1, Integer value2) {
            addCriterion("accounting_subject between", value1, value2, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectNotBetween(Integer value1, Integer value2) {
            addCriterion("accounting_subject not between", value1, value2, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberIsNull() {
            addCriterion("first_level_number is null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberIsNotNull() {
            addCriterion("first_level_number is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberEqualTo(String value) {
            addCriterion("first_level_number =", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberNotEqualTo(String value) {
            addCriterion("first_level_number <>", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberGreaterThan(String value) {
            addCriterion("first_level_number >", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("first_level_number >=", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberLessThan(String value) {
            addCriterion("first_level_number <", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberLessThanOrEqualTo(String value) {
            addCriterion("first_level_number <=", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberLike(String value) {
            addCriterion("first_level_number like", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberNotLike(String value) {
            addCriterion("first_level_number not like", value, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberIn(List<String> values) {
            addCriterion("first_level_number in", values, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberNotIn(List<String> values) {
            addCriterion("first_level_number not in", values, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberBetween(String value1, String value2) {
            addCriterion("first_level_number between", value1, value2, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andFirstLevelNumberNotBetween(String value1, String value2) {
            addCriterion("first_level_number not between", value1, value2, "firstLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberIsNull() {
            addCriterion("second_level_number is null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberIsNotNull() {
            addCriterion("second_level_number is not null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberEqualTo(String value) {
            addCriterion("second_level_number =", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberNotEqualTo(String value) {
            addCriterion("second_level_number <>", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberGreaterThan(String value) {
            addCriterion("second_level_number >", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("second_level_number >=", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberLessThan(String value) {
            addCriterion("second_level_number <", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberLessThanOrEqualTo(String value) {
            addCriterion("second_level_number <=", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberLike(String value) {
            addCriterion("second_level_number like", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberNotLike(String value) {
            addCriterion("second_level_number not like", value, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberIn(List<String> values) {
            addCriterion("second_level_number in", values, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberNotIn(List<String> values) {
            addCriterion("second_level_number not in", values, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberBetween(String value1, String value2) {
            addCriterion("second_level_number between", value1, value2, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andSecondLevelNumberNotBetween(String value1, String value2) {
            addCriterion("second_level_number not between", value1, value2, "secondLevelNumber");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(BigDecimal value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(BigDecimal value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<BigDecimal> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIsNull() {
            addCriterion("amount_money is null");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIsNotNull() {
            addCriterion("amount_money is not null");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyEqualTo(BigDecimal value) {
            addCriterion("amount_money =", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotEqualTo(BigDecimal value) {
            addCriterion("amount_money <>", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyGreaterThan(BigDecimal value) {
            addCriterion("amount_money >", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_money >=", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyLessThan(BigDecimal value) {
            addCriterion("amount_money <", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_money <=", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIn(List<BigDecimal> values) {
            addCriterion("amount_money in", values, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotIn(List<BigDecimal> values) {
            addCriterion("amount_money not in", values, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_money between", value1, value2, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_money not between", value1, value2, "amountMoney");
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

        public Criteria andUtilizationRatioIsNull() {
            addCriterion("utilization_ratio is null");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioIsNotNull() {
            addCriterion("utilization_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioEqualTo(BigDecimal value) {
            addCriterion("utilization_ratio =", value, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioNotEqualTo(BigDecimal value) {
            addCriterion("utilization_ratio <>", value, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioGreaterThan(BigDecimal value) {
            addCriterion("utilization_ratio >", value, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("utilization_ratio >=", value, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioLessThan(BigDecimal value) {
            addCriterion("utilization_ratio <", value, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("utilization_ratio <=", value, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioIn(List<BigDecimal> values) {
            addCriterion("utilization_ratio in", values, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioNotIn(List<BigDecimal> values) {
            addCriterion("utilization_ratio not in", values, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("utilization_ratio between", value1, value2, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("utilization_ratio not between", value1, value2, "utilizationRatio");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainIsNull() {
            addCriterion("utilization_ratio_explain is null");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainIsNotNull() {
            addCriterion("utilization_ratio_explain is not null");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainEqualTo(String value) {
            addCriterion("utilization_ratio_explain =", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainNotEqualTo(String value) {
            addCriterion("utilization_ratio_explain <>", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainGreaterThan(String value) {
            addCriterion("utilization_ratio_explain >", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainGreaterThanOrEqualTo(String value) {
            addCriterion("utilization_ratio_explain >=", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainLessThan(String value) {
            addCriterion("utilization_ratio_explain <", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainLessThanOrEqualTo(String value) {
            addCriterion("utilization_ratio_explain <=", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainLike(String value) {
            addCriterion("utilization_ratio_explain like", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainNotLike(String value) {
            addCriterion("utilization_ratio_explain not like", value, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainIn(List<String> values) {
            addCriterion("utilization_ratio_explain in", values, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainNotIn(List<String> values) {
            addCriterion("utilization_ratio_explain not in", values, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainBetween(String value1, String value2) {
            addCriterion("utilization_ratio_explain between", value1, value2, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andUtilizationRatioExplainNotBetween(String value1, String value2) {
            addCriterion("utilization_ratio_explain not between", value1, value2, "utilizationRatioExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceIsNull() {
            addCriterion("make_price is null");
            return (Criteria) this;
        }

        public Criteria andMakePriceIsNotNull() {
            addCriterion("make_price is not null");
            return (Criteria) this;
        }

        public Criteria andMakePriceEqualTo(BigDecimal value) {
            addCriterion("make_price =", value, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceNotEqualTo(BigDecimal value) {
            addCriterion("make_price <>", value, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceGreaterThan(BigDecimal value) {
            addCriterion("make_price >", value, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("make_price >=", value, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceLessThan(BigDecimal value) {
            addCriterion("make_price <", value, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("make_price <=", value, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceIn(List<BigDecimal> values) {
            addCriterion("make_price in", values, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceNotIn(List<BigDecimal> values) {
            addCriterion("make_price not in", values, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("make_price between", value1, value2, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("make_price not between", value1, value2, "makePrice");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainIsNull() {
            addCriterion("make_price_explain is null");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainIsNotNull() {
            addCriterion("make_price_explain is not null");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainEqualTo(String value) {
            addCriterion("make_price_explain =", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainNotEqualTo(String value) {
            addCriterion("make_price_explain <>", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainGreaterThan(String value) {
            addCriterion("make_price_explain >", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainGreaterThanOrEqualTo(String value) {
            addCriterion("make_price_explain >=", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainLessThan(String value) {
            addCriterion("make_price_explain <", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainLessThanOrEqualTo(String value) {
            addCriterion("make_price_explain <=", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainLike(String value) {
            addCriterion("make_price_explain like", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainNotLike(String value) {
            addCriterion("make_price_explain not like", value, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainIn(List<String> values) {
            addCriterion("make_price_explain in", values, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainNotIn(List<String> values) {
            addCriterion("make_price_explain not in", values, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainBetween(String value1, String value2) {
            addCriterion("make_price_explain between", value1, value2, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andMakePriceExplainNotBetween(String value1, String value2) {
            addCriterion("make_price_explain not between", value1, value2, "makePriceExplain");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceIsNull() {
            addCriterion("executive_price is null");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceIsNotNull() {
            addCriterion("executive_price is not null");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceEqualTo(BigDecimal value) {
            addCriterion("executive_price =", value, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceNotEqualTo(BigDecimal value) {
            addCriterion("executive_price <>", value, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceGreaterThan(BigDecimal value) {
            addCriterion("executive_price >", value, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("executive_price >=", value, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceLessThan(BigDecimal value) {
            addCriterion("executive_price <", value, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("executive_price <=", value, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceIn(List<BigDecimal> values) {
            addCriterion("executive_price in", values, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceNotIn(List<BigDecimal> values) {
            addCriterion("executive_price not in", values, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("executive_price between", value1, value2, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andExecutivePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("executive_price not between", value1, value2, "executivePrice");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNull() {
            addCriterion("discount_rate is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("discount_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(BigDecimal value) {
            addCriterion("discount_rate =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(BigDecimal value) {
            addCriterion("discount_rate <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(BigDecimal value) {
            addCriterion("discount_rate >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_rate >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(BigDecimal value) {
            addCriterion("discount_rate <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_rate <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<BigDecimal> values) {
            addCriterion("discount_rate in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<BigDecimal> values) {
            addCriterion("discount_rate not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_rate between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_rate not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainIsNull() {
            addCriterion("discount_rate_explain is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainIsNotNull() {
            addCriterion("discount_rate_explain is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainEqualTo(String value) {
            addCriterion("discount_rate_explain =", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainNotEqualTo(String value) {
            addCriterion("discount_rate_explain <>", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainGreaterThan(String value) {
            addCriterion("discount_rate_explain >", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainGreaterThanOrEqualTo(String value) {
            addCriterion("discount_rate_explain >=", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainLessThan(String value) {
            addCriterion("discount_rate_explain <", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainLessThanOrEqualTo(String value) {
            addCriterion("discount_rate_explain <=", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainLike(String value) {
            addCriterion("discount_rate_explain like", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainNotLike(String value) {
            addCriterion("discount_rate_explain not like", value, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainIn(List<String> values) {
            addCriterion("discount_rate_explain in", values, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainNotIn(List<String> values) {
            addCriterion("discount_rate_explain not in", values, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainBetween(String value1, String value2) {
            addCriterion("discount_rate_explain between", value1, value2, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andDiscountRateExplainNotBetween(String value1, String value2) {
            addCriterion("discount_rate_explain not between", value1, value2, "discountRateExplain");
            return (Criteria) this;
        }

        public Criteria andBisForecastIsNull() {
            addCriterion("bis_forecast is null");
            return (Criteria) this;
        }

        public Criteria andBisForecastIsNotNull() {
            addCriterion("bis_forecast is not null");
            return (Criteria) this;
        }

        public Criteria andBisForecastEqualTo(Boolean value) {
            addCriterion("bis_forecast =", value, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastNotEqualTo(Boolean value) {
            addCriterion("bis_forecast <>", value, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastGreaterThan(Boolean value) {
            addCriterion("bis_forecast >", value, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_forecast >=", value, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastLessThan(Boolean value) {
            addCriterion("bis_forecast <", value, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_forecast <=", value, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastIn(List<Boolean> values) {
            addCriterion("bis_forecast in", values, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastNotIn(List<Boolean> values) {
            addCriterion("bis_forecast not in", values, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_forecast between", value1, value2, "bisForecast");
            return (Criteria) this;
        }

        public Criteria andBisForecastNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_forecast not between", value1, value2, "bisForecast");
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