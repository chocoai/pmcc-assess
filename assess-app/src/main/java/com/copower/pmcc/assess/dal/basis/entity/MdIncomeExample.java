package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(BigDecimal value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(BigDecimal value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(BigDecimal value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(BigDecimal value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<BigDecimal> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<BigDecimal> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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

        public Criteria andLeaseModeIsNull() {
            addCriterion("lease_mode is null");
            return (Criteria) this;
        }

        public Criteria andLeaseModeIsNotNull() {
            addCriterion("lease_mode is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseModeEqualTo(Integer value) {
            addCriterion("lease_mode =", value, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeNotEqualTo(Integer value) {
            addCriterion("lease_mode <>", value, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeGreaterThan(Integer value) {
            addCriterion("lease_mode >", value, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lease_mode >=", value, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeLessThan(Integer value) {
            addCriterion("lease_mode <", value, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeLessThanOrEqualTo(Integer value) {
            addCriterion("lease_mode <=", value, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeIn(List<Integer> values) {
            addCriterion("lease_mode in", values, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeNotIn(List<Integer> values) {
            addCriterion("lease_mode not in", values, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeBetween(Integer value1, Integer value2) {
            addCriterion("lease_mode between", value1, value2, "leaseMode");
            return (Criteria) this;
        }

        public Criteria andLeaseModeNotBetween(Integer value1, Integer value2) {
            addCriterion("lease_mode not between", value1, value2, "leaseMode");
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

        public Criteria andRestrictionExplainIsNull() {
            addCriterion("restriction_explain is null");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainIsNotNull() {
            addCriterion("restriction_explain is not null");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainEqualTo(String value) {
            addCriterion("restriction_explain =", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainNotEqualTo(String value) {
            addCriterion("restriction_explain <>", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainGreaterThan(String value) {
            addCriterion("restriction_explain >", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainGreaterThanOrEqualTo(String value) {
            addCriterion("restriction_explain >=", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainLessThan(String value) {
            addCriterion("restriction_explain <", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainLessThanOrEqualTo(String value) {
            addCriterion("restriction_explain <=", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainLike(String value) {
            addCriterion("restriction_explain like", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainNotLike(String value) {
            addCriterion("restriction_explain not like", value, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainIn(List<String> values) {
            addCriterion("restriction_explain in", values, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainNotIn(List<String> values) {
            addCriterion("restriction_explain not in", values, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainBetween(String value1, String value2) {
            addCriterion("restriction_explain between", value1, value2, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andRestrictionExplainNotBetween(String value1, String value2) {
            addCriterion("restriction_explain not between", value1, value2, "restrictionExplain");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateIsNull() {
            addCriterion("average_profit_rate is null");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateIsNotNull() {
            addCriterion("average_profit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateEqualTo(String value) {
            addCriterion("average_profit_rate =", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateNotEqualTo(String value) {
            addCriterion("average_profit_rate <>", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateGreaterThan(String value) {
            addCriterion("average_profit_rate >", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateGreaterThanOrEqualTo(String value) {
            addCriterion("average_profit_rate >=", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateLessThan(String value) {
            addCriterion("average_profit_rate <", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateLessThanOrEqualTo(String value) {
            addCriterion("average_profit_rate <=", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateLike(String value) {
            addCriterion("average_profit_rate like", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateNotLike(String value) {
            addCriterion("average_profit_rate not like", value, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateIn(List<String> values) {
            addCriterion("average_profit_rate in", values, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateNotIn(List<String> values) {
            addCriterion("average_profit_rate not in", values, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateBetween(String value1, String value2) {
            addCriterion("average_profit_rate between", value1, value2, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateNotBetween(String value1, String value2) {
            addCriterion("average_profit_rate not between", value1, value2, "averageProfitRate");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkIsNull() {
            addCriterion("average_profit_rate_remark is null");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkIsNotNull() {
            addCriterion("average_profit_rate_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkEqualTo(String value) {
            addCriterion("average_profit_rate_remark =", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkNotEqualTo(String value) {
            addCriterion("average_profit_rate_remark <>", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkGreaterThan(String value) {
            addCriterion("average_profit_rate_remark >", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("average_profit_rate_remark >=", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkLessThan(String value) {
            addCriterion("average_profit_rate_remark <", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkLessThanOrEqualTo(String value) {
            addCriterion("average_profit_rate_remark <=", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkLike(String value) {
            addCriterion("average_profit_rate_remark like", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkNotLike(String value) {
            addCriterion("average_profit_rate_remark not like", value, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkIn(List<String> values) {
            addCriterion("average_profit_rate_remark in", values, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkNotIn(List<String> values) {
            addCriterion("average_profit_rate_remark not in", values, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkBetween(String value1, String value2) {
            addCriterion("average_profit_rate_remark between", value1, value2, "averageProfitRateRemark");
            return (Criteria) this;
        }

        public Criteria andAverageProfitRateRemarkNotBetween(String value1, String value2) {
            addCriterion("average_profit_rate_remark not between", value1, value2, "averageProfitRateRemark");
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

        public Criteria andRewardRateEqualTo(BigDecimal value) {
            addCriterion("reward_rate =", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotEqualTo(BigDecimal value) {
            addCriterion("reward_rate <>", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateGreaterThan(BigDecimal value) {
            addCriterion("reward_rate >", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reward_rate >=", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLessThan(BigDecimal value) {
            addCriterion("reward_rate <", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reward_rate <=", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateIn(List<BigDecimal> values) {
            addCriterion("reward_rate in", values, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotIn(List<BigDecimal> values) {
            addCriterion("reward_rate not in", values, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reward_rate between", value1, value2, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reward_rate not between", value1, value2, "rewardRate");
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

        public Criteria andHouseRemainingYearIsNull() {
            addCriterion("house_remaining_year is null");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearIsNotNull() {
            addCriterion("house_remaining_year is not null");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearEqualTo(BigDecimal value) {
            addCriterion("house_remaining_year =", value, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearNotEqualTo(BigDecimal value) {
            addCriterion("house_remaining_year <>", value, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearGreaterThan(BigDecimal value) {
            addCriterion("house_remaining_year >", value, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("house_remaining_year >=", value, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearLessThan(BigDecimal value) {
            addCriterion("house_remaining_year <", value, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("house_remaining_year <=", value, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearIn(List<BigDecimal> values) {
            addCriterion("house_remaining_year in", values, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearNotIn(List<BigDecimal> values) {
            addCriterion("house_remaining_year not in", values, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("house_remaining_year between", value1, value2, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andHouseRemainingYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("house_remaining_year not between", value1, value2, "houseRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIsNull() {
            addCriterion("land_remaining_year is null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIsNotNull() {
            addCriterion("land_remaining_year is not null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year =", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year <>", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearGreaterThan(BigDecimal value) {
            addCriterion("land_remaining_year >", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year >=", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearLessThan(BigDecimal value) {
            addCriterion("land_remaining_year <", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year <=", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIn(List<BigDecimal> values) {
            addCriterion("land_remaining_year in", values, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotIn(List<BigDecimal> values) {
            addCriterion("land_remaining_year not in", values, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_remaining_year between", value1, value2, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_remaining_year not between", value1, value2, "landRemainingYear");
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