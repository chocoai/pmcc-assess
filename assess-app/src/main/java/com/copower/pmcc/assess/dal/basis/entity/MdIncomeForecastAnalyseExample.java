package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeForecastAnalyseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeForecastAnalyseExample() {
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

        public Criteria andQuantitativeTrendIsNull() {
            addCriterion("quantitative_trend is null");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendIsNotNull() {
            addCriterion("quantitative_trend is not null");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendEqualTo(BigDecimal value) {
            addCriterion("quantitative_trend =", value, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendNotEqualTo(BigDecimal value) {
            addCriterion("quantitative_trend <>", value, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendGreaterThan(BigDecimal value) {
            addCriterion("quantitative_trend >", value, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quantitative_trend >=", value, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendLessThan(BigDecimal value) {
            addCriterion("quantitative_trend <", value, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quantitative_trend <=", value, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendIn(List<BigDecimal> values) {
            addCriterion("quantitative_trend in", values, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendNotIn(List<BigDecimal> values) {
            addCriterion("quantitative_trend not in", values, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quantitative_trend between", value1, value2, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andQuantitativeTrendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quantitative_trend not between", value1, value2, "quantitativeTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendIsNull() {
            addCriterion("univalent_trend is null");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendIsNotNull() {
            addCriterion("univalent_trend is not null");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendEqualTo(BigDecimal value) {
            addCriterion("univalent_trend =", value, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendNotEqualTo(BigDecimal value) {
            addCriterion("univalent_trend <>", value, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendGreaterThan(BigDecimal value) {
            addCriterion("univalent_trend >", value, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("univalent_trend >=", value, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendLessThan(BigDecimal value) {
            addCriterion("univalent_trend <", value, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("univalent_trend <=", value, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendIn(List<BigDecimal> values) {
            addCriterion("univalent_trend in", values, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendNotIn(List<BigDecimal> values) {
            addCriterion("univalent_trend not in", values, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("univalent_trend between", value1, value2, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andUnivalentTrendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("univalent_trend not between", value1, value2, "univalentTrend");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInIsNull() {
            addCriterion("bis_participate_in is null");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInIsNotNull() {
            addCriterion("bis_participate_in is not null");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInEqualTo(Boolean value) {
            addCriterion("bis_participate_in =", value, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInNotEqualTo(Boolean value) {
            addCriterion("bis_participate_in <>", value, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInGreaterThan(Boolean value) {
            addCriterion("bis_participate_in >", value, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_participate_in >=", value, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInLessThan(Boolean value) {
            addCriterion("bis_participate_in <", value, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_participate_in <=", value, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInIn(List<Boolean> values) {
            addCriterion("bis_participate_in in", values, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInNotIn(List<Boolean> values) {
            addCriterion("bis_participate_in not in", values, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_participate_in between", value1, value2, "bisParticipateIn");
            return (Criteria) this;
        }

        public Criteria andBisParticipateInNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_participate_in not between", value1, value2, "bisParticipateIn");
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("source_type is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("source_type is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(String value) {
            addCriterion("source_type =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(String value) {
            addCriterion("source_type <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(String value) {
            addCriterion("source_type >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("source_type >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(String value) {
            addCriterion("source_type <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(String value) {
            addCriterion("source_type <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLike(String value) {
            addCriterion("source_type like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotLike(String value) {
            addCriterion("source_type not like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<String> values) {
            addCriterion("source_type in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<String> values) {
            addCriterion("source_type not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(String value1, String value2) {
            addCriterion("source_type between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(String value1, String value2) {
            addCriterion("source_type not between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andCostRatioIsNull() {
            addCriterion("cost_ratio is null");
            return (Criteria) this;
        }

        public Criteria andCostRatioIsNotNull() {
            addCriterion("cost_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andCostRatioEqualTo(BigDecimal value) {
            addCriterion("cost_ratio =", value, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioNotEqualTo(BigDecimal value) {
            addCriterion("cost_ratio <>", value, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioGreaterThan(BigDecimal value) {
            addCriterion("cost_ratio >", value, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_ratio >=", value, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioLessThan(BigDecimal value) {
            addCriterion("cost_ratio <", value, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_ratio <=", value, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioIn(List<BigDecimal> values) {
            addCriterion("cost_ratio in", values, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioNotIn(List<BigDecimal> values) {
            addCriterion("cost_ratio not in", values, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_ratio between", value1, value2, "costRatio");
            return (Criteria) this;
        }

        public Criteria andCostRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_ratio not between", value1, value2, "costRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioIsNull() {
            addCriterion("earned_profit_ratio is null");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioIsNotNull() {
            addCriterion("earned_profit_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioEqualTo(BigDecimal value) {
            addCriterion("earned_profit_ratio =", value, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioNotEqualTo(BigDecimal value) {
            addCriterion("earned_profit_ratio <>", value, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioGreaterThan(BigDecimal value) {
            addCriterion("earned_profit_ratio >", value, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("earned_profit_ratio >=", value, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioLessThan(BigDecimal value) {
            addCriterion("earned_profit_ratio <", value, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("earned_profit_ratio <=", value, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioIn(List<BigDecimal> values) {
            addCriterion("earned_profit_ratio in", values, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioNotIn(List<BigDecimal> values) {
            addCriterion("earned_profit_ratio not in", values, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("earned_profit_ratio between", value1, value2, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("earned_profit_ratio not between", value1, value2, "earnedProfitRatio");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitIsNull() {
            addCriterion("earned_profit is null");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitIsNotNull() {
            addCriterion("earned_profit is not null");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitEqualTo(BigDecimal value) {
            addCriterion("earned_profit =", value, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitNotEqualTo(BigDecimal value) {
            addCriterion("earned_profit <>", value, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitGreaterThan(BigDecimal value) {
            addCriterion("earned_profit >", value, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("earned_profit >=", value, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitLessThan(BigDecimal value) {
            addCriterion("earned_profit <", value, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("earned_profit <=", value, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitIn(List<BigDecimal> values) {
            addCriterion("earned_profit in", values, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitNotIn(List<BigDecimal> values) {
            addCriterion("earned_profit not in", values, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("earned_profit between", value1, value2, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andEarnedProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("earned_profit not between", value1, value2, "earnedProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioIsNull() {
            addCriterion("operating_expenses_ratio is null");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioIsNotNull() {
            addCriterion("operating_expenses_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioEqualTo(BigDecimal value) {
            addCriterion("operating_expenses_ratio =", value, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioNotEqualTo(BigDecimal value) {
            addCriterion("operating_expenses_ratio <>", value, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioGreaterThan(BigDecimal value) {
            addCriterion("operating_expenses_ratio >", value, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_expenses_ratio >=", value, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioLessThan(BigDecimal value) {
            addCriterion("operating_expenses_ratio <", value, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_expenses_ratio <=", value, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioIn(List<BigDecimal> values) {
            addCriterion("operating_expenses_ratio in", values, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioNotIn(List<BigDecimal> values) {
            addCriterion("operating_expenses_ratio not in", values, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_expenses_ratio between", value1, value2, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_expenses_ratio not between", value1, value2, "operatingExpensesRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioIsNull() {
            addCriterion("operating_tax_ratio is null");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioIsNotNull() {
            addCriterion("operating_tax_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioEqualTo(BigDecimal value) {
            addCriterion("operating_tax_ratio =", value, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioNotEqualTo(BigDecimal value) {
            addCriterion("operating_tax_ratio <>", value, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioGreaterThan(BigDecimal value) {
            addCriterion("operating_tax_ratio >", value, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_tax_ratio >=", value, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioLessThan(BigDecimal value) {
            addCriterion("operating_tax_ratio <", value, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_tax_ratio <=", value, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioIn(List<BigDecimal> values) {
            addCriterion("operating_tax_ratio in", values, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioNotIn(List<BigDecimal> values) {
            addCriterion("operating_tax_ratio not in", values, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_tax_ratio between", value1, value2, "operatingTaxRatio");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_tax_ratio not between", value1, value2, "operatingTaxRatio");
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

        public Criteria andManagementCostRatioEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio =", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio <>", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioGreaterThan(BigDecimal value) {
            addCriterion("management_cost_ratio >", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio >=", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLessThan(BigDecimal value) {
            addCriterion("management_cost_ratio <", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio <=", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIn(List<BigDecimal> values) {
            addCriterion("management_cost_ratio in", values, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotIn(List<BigDecimal> values) {
            addCriterion("management_cost_ratio not in", values, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_cost_ratio between", value1, value2, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_cost_ratio not between", value1, value2, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioIsNull() {
            addCriterion("financial_cost_ratio is null");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioIsNotNull() {
            addCriterion("financial_cost_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioEqualTo(BigDecimal value) {
            addCriterion("financial_cost_ratio =", value, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioNotEqualTo(BigDecimal value) {
            addCriterion("financial_cost_ratio <>", value, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioGreaterThan(BigDecimal value) {
            addCriterion("financial_cost_ratio >", value, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("financial_cost_ratio >=", value, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioLessThan(BigDecimal value) {
            addCriterion("financial_cost_ratio <", value, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("financial_cost_ratio <=", value, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioIn(List<BigDecimal> values) {
            addCriterion("financial_cost_ratio in", values, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioNotIn(List<BigDecimal> values) {
            addCriterion("financial_cost_ratio not in", values, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financial_cost_ratio between", value1, value2, "financialCostRatio");
            return (Criteria) this;
        }

        public Criteria andFinancialCostRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financial_cost_ratio not between", value1, value2, "financialCostRatio");
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