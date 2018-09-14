package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeSelfSupportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeSelfSupportExample() {
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