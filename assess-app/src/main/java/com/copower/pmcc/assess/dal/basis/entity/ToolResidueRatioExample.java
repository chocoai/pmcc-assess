package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToolResidueRatioExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ToolResidueRatioExample() {
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

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andUsedYearIsNull() {
            addCriterion("used_year is null");
            return (Criteria) this;
        }

        public Criteria andUsedYearIsNotNull() {
            addCriterion("used_year is not null");
            return (Criteria) this;
        }

        public Criteria andUsedYearEqualTo(BigDecimal value) {
            addCriterion("used_year =", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearNotEqualTo(BigDecimal value) {
            addCriterion("used_year <>", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearGreaterThan(BigDecimal value) {
            addCriterion("used_year >", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("used_year >=", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearLessThan(BigDecimal value) {
            addCriterion("used_year <", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("used_year <=", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearIn(List<BigDecimal> values) {
            addCriterion("used_year in", values, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearNotIn(List<BigDecimal> values) {
            addCriterion("used_year not in", values, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("used_year between", value1, value2, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("used_year not between", value1, value2, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearIsNull() {
            addCriterion("usable_year is null");
            return (Criteria) this;
        }

        public Criteria andUsableYearIsNotNull() {
            addCriterion("usable_year is not null");
            return (Criteria) this;
        }

        public Criteria andUsableYearEqualTo(BigDecimal value) {
            addCriterion("usable_year =", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearNotEqualTo(BigDecimal value) {
            addCriterion("usable_year <>", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearGreaterThan(BigDecimal value) {
            addCriterion("usable_year >", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("usable_year >=", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearLessThan(BigDecimal value) {
            addCriterion("usable_year <", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("usable_year <=", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearIn(List<BigDecimal> values) {
            addCriterion("usable_year in", values, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearNotIn(List<BigDecimal> values) {
            addCriterion("usable_year not in", values, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usable_year between", value1, value2, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usable_year not between", value1, value2, "usableYear");
            return (Criteria) this;
        }

        public Criteria andObserveRateIsNull() {
            addCriterion("observe_rate is null");
            return (Criteria) this;
        }

        public Criteria andObserveRateIsNotNull() {
            addCriterion("observe_rate is not null");
            return (Criteria) this;
        }

        public Criteria andObserveRateEqualTo(BigDecimal value) {
            addCriterion("observe_rate =", value, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateNotEqualTo(BigDecimal value) {
            addCriterion("observe_rate <>", value, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateGreaterThan(BigDecimal value) {
            addCriterion("observe_rate >", value, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("observe_rate >=", value, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateLessThan(BigDecimal value) {
            addCriterion("observe_rate <", value, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("observe_rate <=", value, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateIn(List<BigDecimal> values) {
            addCriterion("observe_rate in", values, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateNotIn(List<BigDecimal> values) {
            addCriterion("observe_rate not in", values, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("observe_rate between", value1, value2, "observeRate");
            return (Criteria) this;
        }

        public Criteria andObserveRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("observe_rate not between", value1, value2, "observeRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateIsNull() {
            addCriterion("age_rate is null");
            return (Criteria) this;
        }

        public Criteria andAgeRateIsNotNull() {
            addCriterion("age_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAgeRateEqualTo(BigDecimal value) {
            addCriterion("age_rate =", value, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateNotEqualTo(BigDecimal value) {
            addCriterion("age_rate <>", value, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateGreaterThan(BigDecimal value) {
            addCriterion("age_rate >", value, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("age_rate >=", value, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateLessThan(BigDecimal value) {
            addCriterion("age_rate <", value, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("age_rate <=", value, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateIn(List<BigDecimal> values) {
            addCriterion("age_rate in", values, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateNotIn(List<BigDecimal> values) {
            addCriterion("age_rate not in", values, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("age_rate between", value1, value2, "ageRate");
            return (Criteria) this;
        }

        public Criteria andAgeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("age_rate not between", value1, value2, "ageRate");
            return (Criteria) this;
        }

        public Criteria andResultValueIsNull() {
            addCriterion("result_value is null");
            return (Criteria) this;
        }

        public Criteria andResultValueIsNotNull() {
            addCriterion("result_value is not null");
            return (Criteria) this;
        }

        public Criteria andResultValueEqualTo(String value) {
            addCriterion("result_value =", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueNotEqualTo(String value) {
            addCriterion("result_value <>", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueGreaterThan(String value) {
            addCriterion("result_value >", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueGreaterThanOrEqualTo(String value) {
            addCriterion("result_value >=", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueLessThan(String value) {
            addCriterion("result_value <", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueLessThanOrEqualTo(String value) {
            addCriterion("result_value <=", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueLike(String value) {
            addCriterion("result_value like", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueNotLike(String value) {
            addCriterion("result_value not like", value, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueIn(List<String> values) {
            addCriterion("result_value in", values, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueNotIn(List<String> values) {
            addCriterion("result_value not in", values, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueBetween(String value1, String value2) {
            addCriterion("result_value between", value1, value2, "resultValue");
            return (Criteria) this;
        }

        public Criteria andResultValueNotBetween(String value1, String value2) {
            addCriterion("result_value not between", value1, value2, "resultValue");
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

        public Criteria andResidualRatioIsNull() {
            addCriterion("residual_ratio is null");
            return (Criteria) this;
        }

        public Criteria andResidualRatioIsNotNull() {
            addCriterion("residual_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andResidualRatioEqualTo(BigDecimal value) {
            addCriterion("residual_ratio =", value, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioNotEqualTo(BigDecimal value) {
            addCriterion("residual_ratio <>", value, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioGreaterThan(BigDecimal value) {
            addCriterion("residual_ratio >", value, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("residual_ratio >=", value, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioLessThan(BigDecimal value) {
            addCriterion("residual_ratio <", value, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("residual_ratio <=", value, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioIn(List<BigDecimal> values) {
            addCriterion("residual_ratio in", values, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioNotIn(List<BigDecimal> values) {
            addCriterion("residual_ratio not in", values, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("residual_ratio between", value1, value2, "residualRatio");
            return (Criteria) this;
        }

        public Criteria andResidualRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("residual_ratio not between", value1, value2, "residualRatio");
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