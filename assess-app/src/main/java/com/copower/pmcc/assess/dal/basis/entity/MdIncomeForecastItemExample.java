package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeForecastItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeForecastItemExample() {
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

        public Criteria andIncomeForecastIdIsNull() {
            addCriterion("income_forecast_id is null");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdIsNotNull() {
            addCriterion("income_forecast_id is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdEqualTo(Integer value) {
            addCriterion("income_forecast_id =", value, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdNotEqualTo(Integer value) {
            addCriterion("income_forecast_id <>", value, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdGreaterThan(Integer value) {
            addCriterion("income_forecast_id >", value, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_forecast_id >=", value, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdLessThan(Integer value) {
            addCriterion("income_forecast_id <", value, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdLessThanOrEqualTo(Integer value) {
            addCriterion("income_forecast_id <=", value, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdIn(List<Integer> values) {
            addCriterion("income_forecast_id in", values, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdNotIn(List<Integer> values) {
            addCriterion("income_forecast_id not in", values, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdBetween(Integer value1, Integer value2) {
            addCriterion("income_forecast_id between", value1, value2, "incomeForecastId");
            return (Criteria) this;
        }

        public Criteria andIncomeForecastIdNotBetween(Integer value1, Integer value2) {
            addCriterion("income_forecast_id not between", value1, value2, "incomeForecastId");
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

        public Criteria andRateIncreaseIsNull() {
            addCriterion("rate_increase is null");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseIsNotNull() {
            addCriterion("rate_increase is not null");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseEqualTo(BigDecimal value) {
            addCriterion("rate_increase =", value, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseNotEqualTo(BigDecimal value) {
            addCriterion("rate_increase <>", value, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseGreaterThan(BigDecimal value) {
            addCriterion("rate_increase >", value, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rate_increase >=", value, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseLessThan(BigDecimal value) {
            addCriterion("rate_increase <", value, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rate_increase <=", value, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseIn(List<BigDecimal> values) {
            addCriterion("rate_increase in", values, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseNotIn(List<BigDecimal> values) {
            addCriterion("rate_increase not in", values, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate_increase between", value1, value2, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate_increase not between", value1, value2, "rateIncrease");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainIsNull() {
            addCriterion("rate_increase_explain is null");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainIsNotNull() {
            addCriterion("rate_increase_explain is not null");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainEqualTo(String value) {
            addCriterion("rate_increase_explain =", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainNotEqualTo(String value) {
            addCriterion("rate_increase_explain <>", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainGreaterThan(String value) {
            addCriterion("rate_increase_explain >", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainGreaterThanOrEqualTo(String value) {
            addCriterion("rate_increase_explain >=", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainLessThan(String value) {
            addCriterion("rate_increase_explain <", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainLessThanOrEqualTo(String value) {
            addCriterion("rate_increase_explain <=", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainLike(String value) {
            addCriterion("rate_increase_explain like", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainNotLike(String value) {
            addCriterion("rate_increase_explain not like", value, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainIn(List<String> values) {
            addCriterion("rate_increase_explain in", values, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainNotIn(List<String> values) {
            addCriterion("rate_increase_explain not in", values, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainBetween(String value1, String value2) {
            addCriterion("rate_increase_explain between", value1, value2, "rateIncreaseExplain");
            return (Criteria) this;
        }

        public Criteria andRateIncreaseExplainNotBetween(String value1, String value2) {
            addCriterion("rate_increase_explain not between", value1, value2, "rateIncreaseExplain");
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